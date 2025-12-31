package com.davideleonino.palestra.controller;

import com.davideleonino.palestra.dto.request.CoachRequest;
import com.davideleonino.palestra.dto.response.CoachResponse;
import com.davideleonino.palestra.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public ResponseEntity<List<CoachResponse>> getAllCoaches() {
        return ResponseEntity.ok(coachService.getAllCoaches());
    }

    @PostMapping
    public ResponseEntity<CoachResponse> createCoach(@Valid @RequestBody CoachRequest request) {
        CoachResponse created = coachService.createCoach(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachResponse> getCoachById(@PathVariable Long id) {
        CoachResponse coach = coachService.getCoachById(id);
        if (coach == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coach);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}
