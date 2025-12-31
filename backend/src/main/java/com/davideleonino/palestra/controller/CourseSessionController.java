package com.davideleonino.palestra.controller;

import com.davideleonino.palestra.dto.request.CourseSessionRequest;
import com.davideleonino.palestra.dto.response.CourseSessionResponse;
import com.davideleonino.palestra.service.CourseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class CourseSessionController {

    @Autowired
    private CourseSessionService sessionService;

    //  GET: Recupera tutte le sessioni esistenti
    @GetMapping
    public List<CourseSessionResponse> getAllSessions() {
        return sessionService.getAllSessions();
    }

    //  GET: Recupera le sessioni per un corso specifico
    @GetMapping("/course/{courseId}")
    public List<CourseSessionResponse> getSessionsForCourse(@PathVariable Long courseId) {
        return sessionService.getSessionsForCourse(courseId);
    }

    //  POST: Crea una nuova sessione
    @PostMapping
    public CourseSessionResponse createSession(@RequestBody CourseSessionRequest request) {
        return sessionService.createSession(request);
    }

    //  DELETE: Elimina una sessione per ID
    @DeleteMapping("/{sessionId}")
    public void deleteSession(@PathVariable Long sessionId) {
        sessionService.deleteSession(sessionId);
    }
}