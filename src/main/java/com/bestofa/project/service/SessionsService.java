package com.bestofa.project.service;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionsService {
    @Autowired
    SessionRepository sessionRepository;
    

    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

    public Session getSessionById(Integer sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }

    public void saveOrUpdate(Session session){
        sessionRepository.save(session);
    }

    public void deleteAll(){
        sessionRepository.deleteAll();
    }

    public void deleteSession(Integer id){
        sessionRepository.deleteById(id);
    }
    
    public List<Appointment> getAllAppointmets(Integer id){
    	List<Appointment> appointmentList=sessionRepository.findById(id).get().getAppointmentsRequest();
        if(sessionRepository.findById(id).get().getAppointmentApproved() != null)
        	appointmentList.add(sessionRepository.findById(id).get().getAppointmentApproved());
    return appointmentList;
    }
}
