package com.davideleonino.palestra.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CourseSessionResponse {

    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long courseId;

    public CourseSessionResponse() {}

    public CourseSessionResponse(Long id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Long courseId) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Long getCourseId() {
        return courseId;
    }
}