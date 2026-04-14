package com.aiplatform.tracking_service.ai.client;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.aiplatform.tracking_service.model.OpenAIResponse;

import reactor.netty.http.client.HttpClient;

@Component
public class OpenAIClient {

        @Value("${openai.api.key}")
        private String apiKey;

        private final WebClient webClient = WebClient.builder()
                        .baseUrl("https://api.openai.com")
                        .clientConnector(
                                        new ReactorClientHttpConnector(
                                                        HttpClient.create()
                                                                        .responseTimeout(Duration.ofSeconds(30))))
                        .build();

        public String getResponse(String prompt) {

                Map<String, Object> request = Map.of(
                                "model", "gpt-4o-mini",
                                "messages", List.of(
                                                Map.of("role", "system", "content", "You are a helpful fitness coach."),
                                                Map.of("role", "user", "content", prompt)),
                                "max_tokens", 200);

                try {
                        OpenAIResponse response = webClient.post()
                                        .uri("/v1/chat/completions")
                                        .header("Authorization", "Bearer " + apiKey)
                                        .header("Content-Type", "application/json")
                                        .bodyValue(request)
                                        .retrieve()
                                        .bodyToMono(OpenAIResponse.class)
                                        .block();

                        return response.getChoices()
                                        .get(0)
                                        .getMessage()
                                        .getContent();

                } catch (Exception e) {
                        e.printStackTrace();
                        return "Error calling AI: " + e.getMessage();
                }
        }
}