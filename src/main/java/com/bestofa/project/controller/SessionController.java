package com.bestofa.project.controller;

import com.bestofa.project.domain.Session;
import com.bestofa.project.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
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


}