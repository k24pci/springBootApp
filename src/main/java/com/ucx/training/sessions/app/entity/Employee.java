package com.ucx.training.sessions.app.entity;


import com.ucx.training.sessions.app.type.Gender;
import com.ucx.training.sessions.app.type.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity<Integer> {
    @Column(unique = true)
    private String personalNo;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Address address;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Double calculateWage(Double workingHours){
        return workingHours * getPosition().getPayPerHour();
    }
}