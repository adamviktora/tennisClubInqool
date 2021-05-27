package com.adamviktora.tennisclub.utility;

import com.adamviktora.tennisclub.entity.Court;
import com.adamviktora.tennisclub.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping(path = "tennis_club")
public class TennisclubController {

    private final TennisclubService tennisService;

    @Autowired
    public TennisclubController(TennisclubService tennisService) {
        this.tennisService = tennisService;
    }

    @GetMapping(path = "courts")
    public List<Court> getAllCourts() {
        return tennisService.getAllCourts();
    }

    @GetMapping(path = "reservations/phone_number={phoneNumber}")
    public List<Reservation> getReservationsByTimeInterval(@PathVariable String phoneNumber) {
        return tennisService.getReservationsByPhoneNumber(phoneNumber);
    }

    @GetMapping(path = "reservations/court_id={courtId}")
    public List<Reservation> getReservationsByCourtId(@PathVariable int courtId) {
        return tennisService.getReservationsByCourtId(courtId);
    }

    @PostMapping(path = "new_reservation")
    public double createNewReservation(@RequestBody ReservationRawInput input) {
        return tennisService.createNewReservation(input);
    }
}
