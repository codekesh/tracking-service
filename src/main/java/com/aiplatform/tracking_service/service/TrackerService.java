package com.aiplatform.tracking_service.service;

import com.aiplatform.tracking_service.client.AuthClient;
import com.aiplatform.tracking_service.dto.TrackerRequest;
import com.aiplatform.tracking_service.entity.TrackerEntity;
import com.aiplatform.tracking_service.repository.TrackerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class TrackerService {

    private final TrackerRepository repository;
    private final AuthClient authClient;

    public TrackerService(TrackerRepository repository, AuthClient authClient) {
        this.repository = repository;
        this.authClient = authClient;
    }

    public void createTracker(String authHeader, TrackerRequest request) {

    String userEmail = authClient.validateToken(authHeader);

    TrackerEntity tracker = new TrackerEntity();
    tracker.setUserEmail(userEmail);
    tracker.setDomain(request.getDomain());
    tracker.setCreatedAt(LocalDateTime.now());
    repository.save(tracker);
}

    public List<TrackerEntity> getAll(String authHeader) {
        String userEmail = authClient.validateToken(authHeader);
        return repository.findByUserEmail(userEmail);
    }

}
