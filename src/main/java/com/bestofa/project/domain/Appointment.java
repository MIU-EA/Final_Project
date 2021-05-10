package com.bestofa.project.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Appointment {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
