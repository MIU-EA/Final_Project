package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.service.AppointmentService;
import com.bestofa.project.service.SessionService;

@RestController
@RequestMapping("/clients/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SessionService sessionsService;

	@GetMapping
	public List<Appointment> getAllAppointments(Authentication authentication) {
		return appointmentService.getAllAppointmentByUserUsername(authentication.getName());
	}

	@GetMapping("/{appointmentId}")
	public Appointment getAppointmentById(@PathVariable Integer appointmentId) {
		return appointmentService.getAppointmentById(appointmentId);
	}

	@PostMapping
	public ResponseEntity<Appointment> createAppointmentRequest(Authentication auth,
			@RequestParam(value = "sessionId", required = true) Integer sessionId) {
		Person person = personRepository.findByUsername(auth.getName());
		Session session = sessionsService.getSessionById(sessionId);
		Appointment appointment = appointmentService.bookAppointmet(session, person);
		if (appointment != null) {
			return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
		}

		return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Integer id) {
		appointmentService.deleteAppointment(id);
	}

	@PutMapping("/{id}/cancel")
	public ResponseEntity<Appointment> cancelAppointment(Authentication authentication, @PathVariable Integer id) {
		Appointment appointment = appointmentService.getAppointmentById(id);
		// String personUsername=authentication.getName();
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

		Person person = personRepository.findByUsername(authentication.getName());
		boolean canceled = appointmentService.cancelAppointmet(appointment, person);

		if (canceled) {
			status = HttpStatus.OK;
		}

		return new ResponseEntity<Appointment>(appointmentService.getAppointmentById(appointment.getId()), status);
	}

	@PutMapping("/{id}/approve")
	public ResponseEntity<Appointment> approveAppointment(Authentication authentication, @PathVariable Integer id) {
		// String personUsername=authentication.getName();
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		Appointment appointment = appointmentService.getAppointmentById(id);
		Person person = personRepository.findByUsername(authentication.getName());
		if (appointmentService.approveAppointmet(appointment, person))
			status = HttpStatus.OK;
		return new ResponseEntity<Appointment>(appointment, status);

	}

}
