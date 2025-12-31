package com.davideleonino.palestra.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CoachRequest {

    @NotBlank(message = "Il nome del coach è obbligatorio")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}