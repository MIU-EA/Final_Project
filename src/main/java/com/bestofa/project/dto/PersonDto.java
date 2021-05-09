package com.bestofa.project.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bestofa.project.domain.Person;
import com.bestofa.project.security.JwtGrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDto {
	private Integer id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private List<JwtGrantedAuthority> roles;
	
	public PersonDto(Person person) {
		this.id = person.getId();
		this.username = person.getUsername();
		this.name = person.getName();
		this.surname = person.getSurname();
		this.email = person.getEmail();
		this.roles = person.getRoles().values().stream()
				.map(role -> new JwtGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
