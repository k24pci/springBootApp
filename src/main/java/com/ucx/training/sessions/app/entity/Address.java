package com.ucx.training.sessions.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity<Integer> {
    private String country;
    private String state;
    private String city;
    private String street;
    private int zipCode;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}