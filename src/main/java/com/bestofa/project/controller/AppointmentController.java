package com.bestofa.project.controller;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.AppointmentRequest;
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("clients")
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AppointmentRequestRepository requestRepository;

	// List of Appointments under one Customer
	@GetMapping("/{id}/appointments")
	public List<Appointment> listAppointments(@PathVariable(name = "id") Integer personId) {

		return personRepository.findById(personId).get().getApprovedAppointments();
	}

	@GetMapping(params = "paged=true")
	public Page<Appointment> findAll(@RequestParam("page") int page,
									 @RequestParam("size") int size,Pageable pageable) {
		return appointmentRepository.findAll(pageable);
	}
	// Customer appointment using appointmentId
	@GetMapping("/{pId}/appointments/{id}")
	public Appointment findByID(@PathVariable(name = "pId") Integer personId,
								@PathVariable(name = "id") Integer appointmentId) {
		//System.out.println("ssssssssss" + personRepository.findById(personId).get().getApprovedAppointments().g);
		return appointmentRepository.findById(appointmentId).get();
	}

	@PostMapping("/{pId}/appointments")
	public Appointment createAppointment(@PathVariable(name = "pId") Integer personId,
			@Valid @RequestBody Appointment appointment) {
		// get client using param
		Session s = null;
		Person p =personRepository.findById(personId).orElseThrow(RuntimeException::new);
		List<AppointmentRequest> requests = p.getRequestedAppointments();
//		for(AppointmentRequest r:requests){
//			// some other conditions
//			if(r.getRequestedSession().getDate().equals( LocalDate.now())){
//				s = requests.get(0).getRequestedSession();
//			}
//		}
		s = requests.get(0).getRequestedSession();

		appointment.setPersonApproved(p);
		appointment.setSession(s);
		return appointmentRepository.save(appointment);
	}

	@PutMapping ("/{pId}/appointments/{id}")
	public Appointment updateAppointment(@PathVariable(name = "id") Integer appointmentId,
										 @PathVariable(name = "pId") Integer personId,
										 @Valid @RequestBody Appointment appointment){
		appointmentRepository.findById(appointmentId).orElseThrow(RuntimeException::new);

		if(appointmentId.equals(appointment.getId())) {
			appointment.setPersonApproved(appointment.getPersonApproved());
			appointment.setSession(appointment.getSession());
			return appointmentRepository.save(appointment);
		}
		else{
			throw new NullPointerException();
		}
	}

	@DeleteMapping("/{id}/appointments/{id}")
	public void deleteAppointment(@PathVariable(name = "id") Integer appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(RuntimeException::new);
		LocalDate appointmentDate = appointment.getSession().getDate();
		LocalDate after2Days = appointmentDate.plusDays(2);
		if( appointmentDate.isAfter(after2Days)){
			System.out.println("Appointment is not Cancled");
		}
		else {
			appointmentRepository.deleteById(appointmentId);
		}
	}
}
