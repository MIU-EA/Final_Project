package com.bestofa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.service.SessionService;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping("providers/sessions")
@CrossOrigin
@Secured({
	"ROLE_Admin",
	"ROLE_Provider"
})
public class SessionController {

    @Autowired
    private SessionService sessionsService;
    
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    @Secured({
		"ROLE_Admin",
		"ROLE_Provider",
		"ROLE_Customer"
    })
    public List<Session> getAllSessions() {
        return sessionsService.getAllSessions();
    }

    @GetMapping("/{sessionId}")
    public Session getSessionById(@PathVariable("sessionId") Integer sessionId) {
        return sessionsService.getSessionById(sessionId);
    }

    @PostMapping
    public Session saveSession(Authentication auth, @RequestBody Session session) {
    	Person authenticatedPerson = personRepository.findByUsername(auth.getName());
    	session.setCounselor(authenticatedPerson);
        return sessionsService.save(session);
    }
    
    @PostMapping("/{counselorUsername}")
    public Session saveSession(@RequestBody Session session, @PathVariable("counselorUsername") String counselorUsername) throws BadHttpRequest {
    	Person counselor = personRepository.findByUsername(counselorUsername);
    	if (counselor == null)
    		throw new BadHttpRequest();
    	session.setCounselor(counselor);
        return sessionsService.save(session);
    }

    @PutMapping("/{id}")
    public Session updateSession(@RequestBody Session session, @PathVariable("id") Integer id) {
        return sessionsService.update(id, session);
    }

    @DeleteMapping
    public void deleteAllSessions() {
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
