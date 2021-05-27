package com.adamviktora.tennisclub.repository;

import com.adamviktora.tennisclub.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(
            value = "SELECT * FROM reservation WHERE phone_number = ?1",
            nativeQuery = true
    )
    List<Reservation> findReservationsByPhoneNumber(String phoneNumber);

    @Query(
            value = "SELECT * FROM reservation WHERE court_id = ?1",
            nativeQuery = true
    )
    List<Reservation> findReservationsByCourtId(int courtId);

    @Query(
            value = "SELECT * FROM reservation WHERE court_id = ?1 AND date = ?2",
            nativeQuery = true
    )
    List<Reservation> findReservationsByDateAndCourtId(int courtId, Date date);

}
