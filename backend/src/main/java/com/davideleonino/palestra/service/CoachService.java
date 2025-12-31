package com.davideleonino.palestra.service;

import com.davideleonino.palestra.dto.request.CoachRequest;
import com.davideleonino.palestra.dto.response.CoachResponse;
import com.davideleonino.palestra.model.Coach;
import com.davideleonino.palestra.repository.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<CoachResponse> getAllCoaches() {
        return coachRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CoachResponse createCoach(CoachRequest request) {
        Coach coach = new Coach();
        coach.setName(request.getName());

        Coach saved = coachRepository.save(coach);
        return convertToResponse(saved);
    }

    public CoachResponse getCoachById(Long id) {
        return coachRepository.findById(id)
                .map(this::convertToResponse)
                .orElse(null);
    }

    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }

    private CoachResponse convertToResponse(Coach coach) {
        return new CoachResponse(coach.getId(), coach.getName());
    }
}