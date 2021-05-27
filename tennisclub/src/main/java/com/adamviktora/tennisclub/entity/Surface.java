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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Surface {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int pricePerMinute;

    @OneToMany(targetEntity = Court.class)
    @JoinColumn(name = "surfaceId", referencedColumnName = "id")
    private List<Court> courts;
}
