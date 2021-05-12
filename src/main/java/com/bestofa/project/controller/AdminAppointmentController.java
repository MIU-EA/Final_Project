package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.service.AppointmentService;

@RestController
@RequestMapping("/admin/appointments")
@Secured("ROLE_Admin")
public class AdminAppointmentController {
	
	@Autowired
	private AppointmentService service;
	
	
	@GetMapping
	public List<Appointment> getAllAppointments() {
		return service.getAllappointments();
	} 
}
