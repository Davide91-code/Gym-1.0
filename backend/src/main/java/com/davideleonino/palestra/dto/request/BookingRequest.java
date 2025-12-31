package com.davideleonino.palestra.dto.request;

import jakarta.validation.constraints.NotNull;

public class BookingRequest {

    @NotNull(message = "sessionId è obbligatorio")
    private Long sessionId;

    public BookingRequest() {}

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}