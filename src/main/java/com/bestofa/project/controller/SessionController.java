package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.Session;
import com.bestofa.project.service.SessionsService;

@RestController
@RequestMapping("providers/sessions")
@CrossOrigin
public class SessionController {

    @Autowired
    private SessionsService sessionsService;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionsService.getAllSessions();
    }

    @GetMapping("/{sessionId}")
    public Session getSessionById(@PathVariable("sessionId") Integer sessionId) {
        return sessionsService.getSessionById(sessionId);
    }

    @PostMapping
    public Integer saveSession(@RequestBody Session session) {
        sessionsService.saveOrUpdate(session);
        return session.getId();
    }

    @PutMapping
    public Session updateSession(@RequestBody Session session) {
        sessionsService.saveOrUpdate(session);
        return session;
    }

    @DeleteMapping
    public void deleteAllSession() {
        sessionsService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteSessionById(@PathVariable("id") Integer id) {
        sessionsService.deleteSession(id);
    }
    
    @GetMapping("/{id}/appointments")
    public List<Appointment> getAllAppointmentOfSession(@PathVariable("id") Integer id) {
        return sessionsService.getAllAppointmets(id);
    }


}
