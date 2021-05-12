package com.bestofa.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity

public class Appointment {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private String status;

    @ManyToOne
    private Person requestor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Session session;
    
    public Appointment() {
 		this.status="Pending";
    }
    
    public Appointment(Person approved, Session session) {
 		this.requestor = approved;
 		this.session = session;
 		this.status="Pending";
 	}
}

