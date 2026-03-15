package com.aiplatform.tracking_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trackers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String domain;

    private LocalDateTime createdAt;
}