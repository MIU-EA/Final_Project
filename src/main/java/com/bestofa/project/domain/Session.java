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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date;
	private LocalTime startTime;
	private Integer duration;

	@ManyToOne (cascade = CascadeType.ALL)
	private Person counselor;

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Appointment> appointmentApproved;

	@OneToMany(mappedBy = "requestedSession", cascade = CascadeType.ALL)
	@OrderColumn(name = "sequence")
	@JsonIgnore
	private List<AppointmentRequest> appointmentsRequest;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	public Session(LocalDate date, LocalTime startTime, Integer duration, Person counselor, Address address) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.duration = duration;
		this.counselor = counselor;
		this.address = address;
	}
}
