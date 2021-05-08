package com.bestofa.project.domain;

import javax.persistence.*;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sessions")
@Getter
public class Session {
    @Id @GeneratedValue
    private Integer id;
    private LocalDate date;
    private LocalTime startTime;
    private Integer duration;
    private Integer capacity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person councelor;
    
	public Session(LocalDate date, LocalTime startTime, Integer duration, Integer capacity, Person councelor) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.duration = duration;
		this.capacity = capacity;
		this.councelor = councelor;
	}    
}
