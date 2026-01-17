package com.aiplatform.tracking_service.dto;

import lombok.Data;

@Data
public class TrackingRequest {
    private String domain;
    private String metric;
    private Double value;
}
