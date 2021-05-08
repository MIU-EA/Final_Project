package com.bestofa.project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
    @Id
    private Integer id;
    @ManyToOne()
    private Person approved;
}
