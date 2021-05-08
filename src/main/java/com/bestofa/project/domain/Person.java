package com.bestofa.project.domain;

import javax.persistence.*;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "people")
@Getter
public class Person {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
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
    
    
	public Person(String name, String surname, String email, String password) { //TODO: add roles
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
    
    


}
