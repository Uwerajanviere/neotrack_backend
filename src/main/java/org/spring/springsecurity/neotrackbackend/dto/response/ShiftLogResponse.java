package org.spring.springsecurity.neotrackbackend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShiftLogResponse {
    private Long id;
    private BabyResponse baby;
    private UserResponse nurse;
    private Double weight;
    private Double temperature;
    private Double feedingAmount;
    private String medications;
    private String notes;
    private LocalDateTime createdAt;
}
