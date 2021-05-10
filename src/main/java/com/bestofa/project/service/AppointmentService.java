package com.bestofa.project.service;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.AppointmentRepository;
import com.bestofa.project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

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
}
