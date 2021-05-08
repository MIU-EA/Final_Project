package com.bestofa.project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AppointmentRequest {
    @Id
    private Integer id;
    @ManyToOne()
    private Person requisted;
}
