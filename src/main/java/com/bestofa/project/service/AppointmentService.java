package com.bestofa.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Session;
import com.bestofa.project.jms.EmailService;
import com.bestofa.project.repository.AppointmentRepository;
import com.bestofa.project.repository.PersonRepository;

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

	public Appointment getAppointmentById(Integer appointmentId) {
		return appointmentRepository.findById(appointmentId).orElse(null);
	}

	public Appointment saveOrUpdateappointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	public void deleteAppointment(Integer id) {
		appointmentRepository.deleteById(id);
	}

	public List<Appointment> getAllAppointmentByUserId(Integer id) {
		return personRepository.findById(id).get().getAppointments();
	}

	public List<Appointment> getAllAppointmentByUserUsername(String username) {
		return personRepository.findByUsername(username).getAppointments();
	}

	public boolean cancelAppointmet(Appointment appointment, Person person) {
		LocalDate appointmentDate = appointment.getSession().getDate();
		LocalTime appointmentTime = appointment.getSession().getStartTime();
		LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentDate, appointmentTime);
		
		LocalDateTime after2Days = LocalDateTime.now().plusHours(48);

		if (appointmentDateTime.isBefore(after2Days)) {
			if (person.isAdmin()) {
				cancelAppointmentHelper(appointment);

				return true;
			}
		} else {
			cancelAppointmentHelper(appointment);

			return true;
		}
		return false;
	}

	private Appointment cancelAppointmentHelper(Appointment canceledAppointment) {
		Session session = canceledAppointment.getSession();
		
		canceledAppointment.setStatus("Canceled");
		appointmentService.saveOrUpdateappointment(canceledAppointment); 

		Appointment newApprovedAppointment = session.getAppointmentsRequest().stream().filter(r -> r != null)
				.findFirst().orElse(null);
		
		if (newApprovedAppointment != null) {
			session.setAppointmentApproved(newApprovedAppointment);
			newApprovedAppointment.setStatus("Approved");
			appointmentService.saveOrUpdateappointment(newApprovedAppointment);
		}

		return canceledAppointment;
	}

	public boolean approveAppointmet(Appointment appointment, Person person) {
		// if person has a role as Admin or provider
		if (person.isAdmin() || person.isProvider()) {
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
