package com.aiplatform.tracking_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class AuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://auth-service:8080/auth/validate",
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }
}
