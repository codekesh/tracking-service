package com.aiplatform.tracking_service.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aiplatform.tracking_service.ai.client.OpenAIClient;
import com.aiplatform.tracking_service.ai.engine.PromptBuilder;
import com.aiplatform.tracking_service.client.AuthClient;
import com.aiplatform.tracking_service.entity.DietEntity;
import com.aiplatform.tracking_service.repository.DietRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIService {

    private final DietRepository dietRepository;
    private final PromptBuilder promptBuilder;
    private final OpenAIClient openAIClient;
    private final AuthClient authClient;

    public String askAI(String question, String token) {

        String userEmail = authClient.getEmailFromToken(token);
        System.out.println("User email from token: " + question + " -> " + userEmail);
        // Fetch ALL diet entries for now
        List<DietEntity> entries = dietRepository.findByUserEmail(userEmail);

        // limit to last 10 entries (avoid huge prompt)
        if (entries.size() > 10) {
            entries = entries.subList(entries.size() - 10, entries.size());
        }
        // Build prompt
        String prompt = promptBuilder.buildPrompt(entries, question);

        // Call AI
        return openAIClient.getResponse(prompt);
    }
}