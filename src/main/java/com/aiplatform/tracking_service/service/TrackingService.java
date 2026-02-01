package com.aiplatform.tracking_service.service;

import com.aiplatform.tracking_service.client.AuthClient;
import com.aiplatform.tracking_service.dto.TrackingRequest;
import com.aiplatform.tracking_service.entity.TrackingEntry;
import com.aiplatform.tracking_service.repository.TrackingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrackingService {

    private final TrackingRepository repository;
    private final AuthClient authClient;

    public TrackingService(TrackingRepository repository, AuthClient authClient) {
        this.repository = repository;
        this.authClient = authClient;
    }

    public void track(String authHeader, TrackingRequest request) {
        String userEmail = authClient.validateToken(authHeader);

        TrackingEntry entry = new TrackingEntry();
        entry.setUserEmail(userEmail);
        entry.setDomain(request.getDomain());
        entry.setMetric(request.getMetric());
        entry.setMetricValue(request.getValue());
        entry.setTimestamp(LocalDateTime.now());

        repository.save(entry);
    }
}
