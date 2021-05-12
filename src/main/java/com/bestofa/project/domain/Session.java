package com.bestofa.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@JsonIgnore
	private Appointment appointmentApproved;

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
	@OrderColumn(name = "sequence")
	@JsonIgnore
	private List<Appointment> appointmentsRequest;

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
