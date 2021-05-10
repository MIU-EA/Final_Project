package com.bestofa.project.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "sessions")
public class Session {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalDate date;
	private LocalTime startTime;
	private Integer duration;

	@ManyToOne
	private Person counselor;

	@ManyToOne
	private Appointment appointmentApproved;

	@OneToMany(mappedBy = "requestedSession", cascade = CascadeType.ALL)
	@OrderColumn(name = "sequence")
	private List<AppointmentRequest> appointmentsRequest;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	public Session(LocalDate date, LocalTime startTime, Integer duration, Person councelor, Address address) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.duration = duration;
		this.counselor = councelor;
		this.address = address;
	}
}
