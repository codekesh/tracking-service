package com.aiplatform.tracking_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;

@Component
public class AuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public String validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                authServiceUrl + "/auth/validate",
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }

    public String getEmailFromToken(String token) {
        return "testuser@example.com";
    }
}
