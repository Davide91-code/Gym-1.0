package com.davideleonino.palestra.service;

import com.davideleonino.palestra.dto.request.CourseSessionRequest;
import com.davideleonino.palestra.dto.response.CourseSessionResponse;
import com.davideleonino.palestra.model.Course;
import com.davideleonino.palestra.model.CourseSession;
import com.davideleonino.palestra.repository.CourseRepository;
import com.davideleonino.palestra.repository.CourseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseSessionService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSessionRepository sessionRepository;

    //  Tutte le sessioni
    public List<CourseSessionResponse> getAllSessions() {
        return sessionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //  Sessioni per corso
    public List<CourseSessionResponse> getSessionsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new IllegalArgumentException("Corso non trovato con id: " + courseId)
        );

        return sessionRepository.findByCourse(course)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //  Crea nuova sessione
    public CourseSessionResponse createSession(CourseSessionRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Corso non trovato con id: " + request.getCourseId()));

        CourseSession session = new CourseSession();
        session.setCourse(course);
        session.setDayOfWeek(request.getDayOfWeek());
        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());

        CourseSession saved = sessionRepository.save(session);
        return mapToResponse(saved);
    }

    //  Elimina sessione
    public void deleteSession(Long sessionId) {
        if (!sessionRepository.existsById(sessionId)) {
            throw new IllegalArgumentException("Sessione non trovata con id: " + sessionId);
        }
        sessionRepository.deleteById(sessionId);
    }

    private CourseSessionResponse mapToResponse(CourseSession session) {
        return new CourseSessionResponse(
                session.getId(),
                session.getDayOfWeek(),
                session.getStartTime(),
                session.getEndTime(),
                session.getCourse().getId()
        );
    }
}