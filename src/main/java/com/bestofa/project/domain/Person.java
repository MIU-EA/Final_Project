package com.bestofa.project.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people")
@SecondaryTable(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class Person {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String surname;
	private String email;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(table = "users")
	private String username;

	@Column(table = "users")
	private String password;

	@ManyToMany
	@MapKey(name = "name")
	private Map<String, Role> roles;

	@OneToMany(mappedBy="councelor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Session> sessions; // as a councelor

	@OneToMany(mappedBy = "personApproved", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Appointment> approvedAppointments;

	@OneToMany(mappedBy = "personRequested", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AppointmentRequest> requestedAppointment;

	public Person(String name, String surname, String email, String username, String password, Map<String, Role> roles) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = new HashMap<>(roles);
	}
}
