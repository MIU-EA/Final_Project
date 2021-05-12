package com.bestofa.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.SessionRepository;

@Service
public class SessionService {
	@Autowired
	SessionRepository sessionRepository;

	public List<Session> getAllSessions() {
		return sessionRepository.findAll();
	}

	public Session getSessionById(Integer sessionId) {
		return sessionRepository.findById(sessionId).orElse(null);
	}

	public Session save(Session session) {
		return sessionRepository.save(session);
	}

	public Session update(Integer id, Session session) {
		Session oldSession = sessionRepository.getOne(id);
		
		oldSession.setDate(session.getDate());
		oldSession.setStartTime(session.getStartTime());
		oldSession.setDuration(session.getDuration());
		
		return sessionRepository.save(oldSession);
	}
	
	public void deleteAll() {
		sessionRepository.deleteAll();
	}

	public void deleteSession(Integer id) {
		sessionRepository.deleteById(id);
	}

	public List<Appointment> getAllAppointmets(Integer id) {
		List<Appointment> appointmentList = sessionRepository.findById(id).get().getAppointmentsRequest();
		if (sessionRepository.findById(id).get().getAppointmentApproved() != null)
			appointmentList.add(sessionRepository.findById(id).get().getAppointmentApproved());
		return appointmentList;
	}
}
