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
@Table(name = "appointments")
public class Appointment {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
