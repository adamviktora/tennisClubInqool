package com.adamviktora.tennisclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String phoneNumber;
    private String name;

    public User(String phoneNumber, String userName) {
        this.phoneNumber = phoneNumber;
        this.name = userName;
    }
}
