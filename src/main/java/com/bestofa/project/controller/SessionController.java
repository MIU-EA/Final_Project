package com.bestofa.project.controller;

import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Session getSessionById(@PathVariable("id") Integer id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionRepository.save(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSession(@PathVariable("id") Integer id, @RequestBody Session session) {
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        if (!sessionOptional.isPresent())
            return ResponseEntity.notFound().build();
        session.setId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteAllSession() {
        sessionRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteSessionById(@PathVariable("id") Integer id) {
        sessionRepository.deleteById(id);
    }


}
