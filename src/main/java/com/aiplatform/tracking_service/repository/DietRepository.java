package com.aiplatform.tracking_service.repository;

import com.aiplatform.tracking_service.entity.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<DietEntity, Long> {
    List<DietEntity> findByUserEmail(String userEmail);
}
