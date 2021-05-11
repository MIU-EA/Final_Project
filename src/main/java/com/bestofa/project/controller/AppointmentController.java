package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.repository.SessionRepository;
import com.bestofa.project.service.AppointmentService;

@RestController
@RequestMapping("/clients/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@GetMapping
	public List<Appointment> getAllAppointments(Authentication authentication) {
//    	String personUsername=authentication.getName();
//        return personRepository.findByUsername(personUsername).getAppointments();
		return appointmentService.getAllAppointmentByUserId(2);
	}

	@GetMapping("/{appointmentId}")
	public Appointment getAppointmentById(@PathVariable Integer appointmentId) {
		return appointmentService.getappointmentById(appointmentId);
	}

	@PostMapping
	public Appointment createAppointmentRequest(Authentication authentication,
			@RequestParam(value = "appointmenId", required = true) Integer appointmenId) {
		Appointment appointment = new Appointment(
				// TODO: get person from privilege
				personRepository.findByUsername("username22"),
				// why APPOINTMENT ID?
				sessionRepository.findById(appointmenId).get());
		return appointmentService.saveOrUpdateappointment(appointment);
	}

	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Integer id) {
		appointmentService.deleteappointment(id);
	}

	@PutMapping("/{id}/cancel")
	public Appointment cancelAppointment(@PathVariable Integer id) {
		Appointment appointment = appointmentService.getappointmentById(id);
		appointment.getSession().setAppointmentApproved(null);
		appointment.setStatus("Canceld");
		appointment = appointmentService.saveOrUpdateappointment(appointment);
		return appointment;

	}

	@PutMapping("/{id}/approve")
	public Appointment approveAppointment(@PathVariable Integer id) {
		Appointment appointment = appointmentService.getappointmentById(id);
		appointment.getSession().setAppointmentApproved(appointment);
		appointment.setStatus("Approved");
		appointment = appointmentService.saveOrUpdateappointment(appointment);
		return appointment;

	}

}
