package com.adamviktora.tennisclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue
    private int id;
    private Date date;
    private Time time;
    private int timeInterval;
    private boolean playingDoubles;
    private String phoneNumber;

    public Reservation(Date date, Time time, int timeInterval, boolean doublesGame, String phoneNumber) {
        this.date = date;
        this.time = time;
        this.timeInterval = timeInterval;
        this.playingDoubles = doublesGame;
        this.phoneNumber = phoneNumber;
    }
}
