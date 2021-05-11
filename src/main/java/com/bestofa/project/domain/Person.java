package com.bestofa.project.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToMany(fetch=FetchType.EAGER)
	@MapKey(name = "name" )
	private Map<String, Role> roles;

	@OneToMany(mappedBy = "counselor", cascade = CascadeType.ALL)
    @JsonIgnore
	private List<Session> sessions; // as a counselor


	@OneToMany(mappedBy = "requestor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Appointment> Appointments;

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
