package com.aiplatform.tracking_service.ai.dto;

import lombok.Getter;

@Getter
public class AIResponse {
    private String answer;

    public AIResponse(String answer) {
        this.answer = answer;
    }
}
