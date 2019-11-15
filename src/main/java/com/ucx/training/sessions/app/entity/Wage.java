package com.ucx.training.sessions.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wage extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Double wage;

    private Byte month;
    private Short year;
}
