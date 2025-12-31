package com.davideleonino.palestra.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private LocalDateTime schedule;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CourseCategory category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSession> sessions;

    public Course() {
    }

    public Course(Long id, String name, String description, Integer maxParticipants, Integer currentParticipants, LocalDateTime schedule, Coach coach, CourseCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = currentParticipants;
        this.schedule = schedule;
        this.coach = coach;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }

    public Integer getCurrentParticipants() { return currentParticipants; }
    public void setCurrentParticipants(Integer currentParticipants) { this.currentParticipants = currentParticipants; }

    public LocalDateTime getSchedule() { return schedule; }
    public void setSchedule(LocalDateTime schedule) { this.schedule = schedule; }

    public Coach getCoach() { return coach; }
    public void setCoach(Coach coach) { this.coach = coach; }

    public CourseCategory getCategory() { return category; }
    public void setCategory(CourseCategory category) { this.category = category; }

    public List<CourseSession> getSessions() { return sessions; }
    public void setSessions(List<CourseSession> sessions) { this.sessions = sessions; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schedule=" + schedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}