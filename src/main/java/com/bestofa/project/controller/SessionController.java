package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.SessionRepository;

@RestController
@RequestMapping("/sessions")
public class SessionController {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@GetMapping
	public List<Session> listSessions() {
 		return sessionRepository.findAll();
	}
}
