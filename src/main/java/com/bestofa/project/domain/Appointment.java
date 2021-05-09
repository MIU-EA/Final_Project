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
@Table(name = "appointments")
public class Appointment {
 
	@Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Person personApproved;
    @ManyToOne
    private Session session;
    
    
    public Appointment(Person approved, Session session) {
 		this.personApproved = approved;
 		this.session = session;
 	}
}
