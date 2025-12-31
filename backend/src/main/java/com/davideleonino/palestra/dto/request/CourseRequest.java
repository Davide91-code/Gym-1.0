package com.davideleonino.palestra.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class CourseRequest {

    private Long id;

    @NotBlank(message = "Il nome del corso è obbligatorio")
    private String name;

    private String description;

    @Min(value = 1, message = "Il numero massimo di partecipanti deve essere almeno 1")
    private int maxParticipants;

    @Min(value = 0, message = "Il numero di partecipanti attuali non può essere negativo")
    private int currentParticipants;

    @NotNull(message = "La data e ora del corso sono obbligatorie")
    private LocalDateTime schedule;

    @NotBlank(message = "Il nome del coach è obbligatorio")
    private String coachName;

    @NotBlank(message = "Il nome della categoria è obbligatorio")
    private String categoryName;

    public CourseRequest() {}

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