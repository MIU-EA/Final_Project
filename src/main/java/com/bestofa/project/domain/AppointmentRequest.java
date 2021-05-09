package com.bestofa.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "appointment_requests")
public class AppointmentRequest {
 
	@Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Person personRequested;
    @ManyToOne
    private Session requestedSession;
    
    
    public AppointmentRequest(Person approved, Session session) {
 		this.personRequested = approved;
 		this.requestedSession = session;
 	}
}
