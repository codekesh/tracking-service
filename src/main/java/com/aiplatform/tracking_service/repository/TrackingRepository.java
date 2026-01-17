package com.aiplatform.tracking_service.repository;

import com.aiplatform.tracking_service.entity.TrackingEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository extends JpaRepository<TrackingEntry, Long> {
    List<TrackingEntry> findByUserEmail(String userEmail);
}
