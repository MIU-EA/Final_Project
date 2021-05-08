package com.bestofa.project.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Session {
    @Id
    private Integer id;
    private LocalDate date;
    private LocalTime startTime;
    private Integer duration;
    private Integer capacity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="person_session")
    private Person councelor;
}
