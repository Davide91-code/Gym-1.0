package com.davideleonino.palestra.dto.response;

import java.time.LocalDateTime;

public class CourseResponse {

    private Long id;
    private String name;
    private String description;
    private int maxParticipants;
    private int currentParticipants;
    private LocalDateTime schedule;
    private String coachName;
    private String categoryName;

    public CourseResponse() {}

    public CourseResponse(Long id, String name, String description, int maxParticipants, int currentParticipants,
                          LocalDateTime schedule, String coachName, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = currentParticipants;
        this.schedule = schedule;
        this.coachName = coachName;
        this.categoryName = categoryName;
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }

    public int getCurrentParticipants() { return currentParticipants; }
    public void setCurrentParticipants(int currentParticipants) { this.currentParticipants = currentParticipants; }

    public LocalDateTime getSchedule() { return schedule; }
    public void setSchedule(LocalDateTime schedule) { this.schedule = schedule; }

    public String getCoachName() { return coachName; }
    public void setCoachName(String coachName) { this.coachName = coachName; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}