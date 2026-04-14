package com.aiplatform.tracking_service.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiplatform.tracking_service.ai.dto.AIRequest;
import com.aiplatform.tracking_service.ai.dto.AIResponse;
import com.aiplatform.tracking_service.ai.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/chat")
    public ResponseEntity<AIResponse> chat(
            @RequestBody AIRequest request,
            @RequestHeader("Authorization") String token) {
        String response = aiService.askAI(request.getQuestion(), token);
        return ResponseEntity.ok(new AIResponse(response));
    }

    @GetMapping("/test-ai")
    public String testAI() {
        return aiService.askAI("Give me a simple fitness tip", "dummy-token");
    }
}