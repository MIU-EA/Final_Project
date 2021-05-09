package com.bestofa.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestofa.project.dto.PersonDto;
import com.bestofa.project.repository.PersonRepository;

@RestController
@RequestMapping("/me")
public class MeController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping
	public PersonDto me(Principal principal) {
		return new PersonDto(personRepository.findByUsername(principal.getName()));
	}
}
