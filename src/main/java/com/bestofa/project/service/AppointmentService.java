package com.bestofa.project.service;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Session;
import com.bestofa.project.jms.EmailService;
import com.bestofa.project.repository.AppointmentRepository;
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Appointment> getAllappointments(){
        return appointmentRepository.findAll();
    }

    public Appointment getappointmentById(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    public Appointment saveOrUpdateappointment(Appointment appointment){
    	return appointmentRepository.save(appointment);
   
    }


    public void deleteappointment(Integer id){
    	appointmentRepository.deleteById(id);
    }
    
    public List<Appointment> getAllAppointmentByUserId(Integer id){
    	return personRepository.findById(id).get().getAppointments();
    }
}
