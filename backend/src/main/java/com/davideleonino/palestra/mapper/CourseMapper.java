package com.davideleonino.palestra.mapper;

import com.davideleonino.palestra.dto.request.CourseRequest;
import com.davideleonino.palestra.dto.response.CourseResponse;
import com.davideleonino.palestra.model.Coach;
import com.davideleonino.palestra.model.Course;
import com.davideleonino.palestra.model.CourseCategory;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request, CourseCategory category, Coach coach) {
        Course course = new Course();
        course.setId(request.getId());
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setMaxParticipants(request.getMaxParticipants());
        course.setCurrentParticipants(request.getCurrentParticipants());
        course.setSchedule(request.getSchedule());
        course.setCategory(category);
        course.setCoach(coach);
        return course;
    }

    public CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getMaxParticipants(),
                course.getCurrentParticipants(),
                course.getSchedule(),
                course.getCoach() != null ? course.getCoach().getName() : null,
                course.getCategory() != null ? course.getCategory().getName() : null
        );
    }
}