package com.davideleonino.palestra.repository;

import com.davideleonino.palestra.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Trova corsi per categoria
    List<Course> findByCategoryId(Long categoryId);

    // Trova corsi per coach
    List<Course> findByCoachId(Long coachId);

    // Trova corsi con posti disponibili
    List<Course> findByCurrentParticipantsLessThan(Integer max);
}