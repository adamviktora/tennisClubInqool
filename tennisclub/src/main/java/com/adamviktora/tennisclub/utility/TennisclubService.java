package com.adamviktora.tennisclub.utility;

import com.adamviktora.tennisclub.entity.Court;
import com.adamviktora.tennisclub.repository.CourtRepository;
import com.adamviktora.tennisclub.repository.SurfaceRepository;
import com.adamviktora.tennisclub.entity.Reservation;
import com.adamviktora.tennisclub.repository.ReservationRepository;
import com.adamviktora.tennisclub.entity.User;
import com.adamviktora.tennisclub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TennisclubService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CourtRepository courtRepository;
    private final SurfaceRepository surfaceRepository;

    @Autowired
    public TennisclubService(ReservationRepository reservationRepository,
                             UserRepository userRepository,
                             CourtRepository courtRepository,
                             SurfaceRepository surfaceRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.courtRepository = courtRepository;
        this.surfaceRepository = surfaceRepository;
    }

    /**
     * Returns list of courts with their corresponding reservations.
     *
     * @return list of Court instances
     */
    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }

    /**
     * Returns list of reservations with input phoneNumber same as theirs.
     *
     * @param phoneNumber input String to match with reservations phoneNumber
     * @return list of corresponding reservations, may be empty
     */
    public List<Reservation> getReservationsByPhoneNumber(String phoneNumber) {
        return reservationRepository.findReservationsByPhoneNumber(phoneNumber);
    }

    /**
     * Returns list of reservations with court_id in database same as input courtId.
     *
     * @param courtId input String to match with reservations court_id
     * @return list of corresponding reservations, may be empty
     */
    public List<Reservation> getReservationsByCourtId(int courtId) {
        return reservationRepository.findReservationsByCourtId(courtId);
    }

    /**
     * Creates new reservation, returns the price of the new reservation,
     * if creation was successful.
     *
     * @param input input data class
     * @return -1 on fail, price of the reservation otherwise
     */
    public double createNewReservation(ReservationRawInput input) {
        Court court = getCourtById(input.getCourtId());
        if (court == null) {
            return -1;
        }

        if (inputReservationColliding(court, input.getDate(), input.getTime(),
                input.getTimeInterval())) {
            return -1;
        }

        Reservation reservation = insertNewReservation(input);
        updateCourt(court, reservation);
        updateUser(input.getUserName(), reservation);

        return countPrice(reservation, court);
    }

    private Court getCourtById(int id) {
        try {
            return courtRepository.getById(id);
        } catch (EntityNotFoundException e) {
            System.err.println("input court id is invalid");
            return null;
        }
    }

    private boolean inputReservationColliding(Court court, Date inputDate, Time inputTime, int timeInterval) {
        List<Reservation> potentialCollidingReservations =
                reservationRepository.findReservationsByDateAndCourtId(court.getId(), inputDate);
        for (Reservation reservation: potentialCollidingReservations) {
            if (isTimeCollision(reservation.getTime().toLocalTime(), inputTime.toLocalTime(),
                    reservation.getTimeInterval(), timeInterval)) {
                System.err.println("reservation on this court at this time and date already exists");
                return true;
            }
        }
        return false;
    }

    private boolean isTimeCollision(LocalTime existingResStart, LocalTime newResStart,
                                   int existingTimeInterval, int newTimeInterval) {
        return ! (newResStart.compareTo(existingResStart.plusMinutes(existingTimeInterval)) >= 0
                || existingResStart.compareTo(newResStart.plusMinutes(newTimeInterval)) >= 0);
    }

    private Reservation insertNewReservation(ReservationRawInput input) {
        Reservation reservation = new Reservation(
                input.getDate(),
                input.getTime(),
                input.getTimeInterval(),
                input.isPlayingDoubles(),
                input.getPhoneNumber()
        );
        reservationRepository.save(reservation);
        return reservation;
    }

    private void updateCourt(Court court, Reservation reservation) {
        List<Reservation> courtReservations = new ArrayList<>(court.getReservations());
        courtReservations.add(reservation);
        court.setReservations(courtReservations);
        courtRepository.save(court);
    }

    private void updateUser(String userName, Reservation reservation) {
        User user;
        List<Integer> userIds = userRepository.getIdByPhoneNumber(reservation.getPhoneNumber());

        if (userIds.isEmpty()) {
            user = new User(reservation.getPhoneNumber(), userName);
        } else {
            user = userRepository.getById(userIds.get(0));
            if (!user.getName().equals(userName)) {
                user.setName(userName);
            }
        }
        userRepository.save(user);
    }

    private double countPrice(Reservation reservation, Court court) {
        int pricePerMinute = getPricePerMinute(court);
        double price = pricePerMinute * reservation.getTimeInterval();
        if (reservation.isPlayingDoubles()) {
            price *= 1.5;
        }
        return price;
    }

    private int getPricePerMinute(Court court) {
        int surfaceId = courtRepository.getSurfaceId(court.getId());
        return surfaceRepository.getById(surfaceId).getPricePerMinute();
    }

}
