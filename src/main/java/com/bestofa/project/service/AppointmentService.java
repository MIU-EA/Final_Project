package com.bestofa.project.service;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Role;
import com.bestofa.project.domain.Session;
import com.bestofa.project.jms.EmailService;
import com.bestofa.project.repository.AppointmentRepository;
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	AppointmentService appointmentService;

	public List<Appointment> getAllappointments() {
		return appointmentRepository.findAll();
	}

	public Appointment getappointmentById(Integer appointmentId) {
		return appointmentRepository.findById(appointmentId).orElse(null);
	}

	public Appointment saveOrUpdateappointment(Appointment appointment) {
		return appointmentRepository.save(appointment);

	}

	public void deleteappointment(Integer id) {
		appointmentRepository.deleteById(id);
	}

	public List<Appointment> getAllAppointmentByUserId(Integer id) {
		return personRepository.findById(id).get().getAppointments();
	}

	public boolean cancelAppointmet(Appointment appointment, Person person) {
		Collection<Role> roles = person.getRoles().values();
		LocalDate appointmentDate = appointment.getSession().getDate();
		LocalTime appointmentTime = appointment.getSession().getStartTime();
		LocalDateTime after2Days = LocalDateTime.now().plusHours(48);
		LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentDate, appointmentTime);

		if (appointmentDateTime.isBefore(after2Days)) {
			for (Role r : roles) {
				// if person has a role as Admin
				if (r.getName().equals("Admin")) {
					appointment.getSession().setAppointmentApproved(null);
					appointment.setStatus("Canceld");
					appointment = appointmentService.saveOrUpdateappointment(appointment);
					return true;
				}
			}
		} else {
			appointment.getSession().setAppointmentApproved(null);
			appointment.setStatus("Canceld");
			appointment = appointmentService.saveOrUpdateappointment(appointment);
			return true;
		}
		return false;
	}

	public boolean approveAppointmet(Appointment appointment, Person person) {
			// if person has a role as Admin or provider
			if (person.isAdmin()||person.isProvider()) {
				appointment.getSession().setAppointmentApproved(appointment);
				appointment.setStatus("Approved");
				appointment = appointmentService.saveOrUpdateappointment(appointment);
				return true;
			}
		
		return false;

	}

	public Appointment bookAppointmet(Session session, Person person) {
		Appointment appointment = new Appointment();
		appointment.setRequestor(person);
		if (session.getDate().isAfter(LocalDate.now())) {
			appointment.setSession(session);
			session.getAppointmentsRequest().add(appointment);
			appointment = appointmentService.saveOrUpdateappointment(appointment);
			return appointment;
		}
		return null;
	}

}
