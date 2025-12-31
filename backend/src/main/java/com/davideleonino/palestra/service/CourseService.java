package com.davideleonino.palestra.service;

import com.davideleonino.palestra.dto.request.CourseRequest;
import com.davideleonino.palestra.dto.response.CourseResponse;
import com.davideleonino.palestra.mapper.CourseMapper;
import com.davideleonino.palestra.model.Coach;
import com.davideleonino.palestra.model.Course;
import com.davideleonino.palestra.model.CourseCategory;
import com.davideleonino.palestra.repository.CoachRepository;
import com.davideleonino.palestra.repository.CourseCategoryRepository;
import com.davideleonino.palestra.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseCategoryRepository categoryRepository;
    private final CoachRepository coachRepository;
    private final CourseMapper courseMapper;  // ✅ Aggiunto

    public CourseService(CourseRepository courseRepository,
                         CourseCategoryRepository categoryRepository,
                         CoachRepository coachRepository,
                         CourseMapper courseMapper) { //  Aggiunto al costruttore
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.coachRepository = coachRepository;
        this.courseMapper = courseMapper; //
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponse) //  metodo di istanza
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toResponse)
                .orElse(null);
    }

    public List<CourseResponse> getCoursesByCategory(Long categoryId) {
        return courseRepository.findByCategoryId(categoryId)
                .stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse createCourse(CourseRequest request) {
        Optional<CourseCategory> categoryOpt = categoryRepository.findByName(request.getCategoryName());
        Optional<Coach> coachOpt = coachRepository.findByName(request.getCoachName());

        if (categoryOpt.isEmpty() || coachOpt.isEmpty()) {
            throw new IllegalArgumentException("Coach o categoria non trovati.");
        }

        Course course = courseMapper.toEntity(request, categoryOpt.get(), coachOpt.get());
        Course saved = courseRepository.save(course);
        return courseMapper.toResponse(saved);
    }

    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Optional<Course> existingOpt = courseRepository.findById(id);
        Optional<CourseCategory> categoryOpt = categoryRepository.findByName(request.getCategoryName());
        Optional<Coach> coachOpt = coachRepository.findByName(request.getCoachName());

        if (existingOpt.isEmpty() || categoryOpt.isEmpty() || coachOpt.isEmpty()) {
            return null;
        }

        Course existing = existingOpt.get();
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setSchedule(request.getSchedule());
        existing.setMaxParticipants(request.getMaxParticipants());
        existing.setCurrentParticipants(request.getCurrentParticipants());
        existing.setCategory(categoryOpt.get());
        existing.setCoach(coachOpt.get());

        Course updated = courseRepository.save(existing);
        return courseMapper.toResponse(updated);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}