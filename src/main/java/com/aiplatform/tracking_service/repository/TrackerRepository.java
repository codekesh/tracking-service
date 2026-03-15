package com.aiplatform.tracking_service.repository;

import com.aiplatform.tracking_service.entity.TrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackerRepository extends JpaRepository<TrackerEntity, Long> {
    List<TrackerEntity> findByUserEmail(String userEmail);
}
