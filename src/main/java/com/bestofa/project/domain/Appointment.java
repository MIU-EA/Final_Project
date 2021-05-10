package com.bestofa.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "appointments")
public class Appointment {
 
	@Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Person requestor;
    @ManyToOne
    private Session session;
    
    
    public Appointment(Person approved, Session session) {
 		this.requestor = approved;
 		this.session = session;
 	}
}
