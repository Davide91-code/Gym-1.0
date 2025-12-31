package com.davideleonino.palestra.repository;

import com.davideleonino.palestra.model.Course;
import com.davideleonino.palestra.model.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    List<CourseSession> findByCourse(Course course);
}