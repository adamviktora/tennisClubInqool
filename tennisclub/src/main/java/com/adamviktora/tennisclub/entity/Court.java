package com.adamviktora.tennisclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Court {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "courtId", referencedColumnName = "id")
    private List<Reservation> reservations;
}
