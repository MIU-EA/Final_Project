package com.bestofa.project.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "people")
public class Person {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ElementCollection
    private Map<String, Role> roles;
    @OneToMany(mappedBy = "councelor")
    private List<Session> sessions;
    @OneToMany(mappedBy = "approved")
    private List<Appointment> approvedAppointments;
    @OneToMany(mappedBy = "requisted")
    private List<AppointmentRequest> requestedAppointment;


}
