package com.adamviktora.tennisclub.utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@Data
public class ReservationRawInput {

    private int courtId;
    private String phoneNumber;
    private String userName;
    private boolean playingDoubles;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date date;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time time;
    private int timeInterval;

}
