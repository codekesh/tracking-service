package com.aiplatform.tracking_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String domain; // fitness, finance, study, etc.
    private String metric; // weight, hours, amount
    private Double value;

    private LocalDateTime timestamp;
}
