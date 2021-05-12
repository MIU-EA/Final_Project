package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.service.AppointmentService;

@Controller
@RequestMapping("/admin/appointments")
public class AdminAppointmentController {
	
	@Autowired
	private AppointmentService service;
	
	
	@GetMapping
	public List<Appointment> getAllAppointments(String username) {
		return service.getAllappointments();
	} 
}
