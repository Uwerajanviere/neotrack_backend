package org.spring.springsecurity.neotrackbackend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertResponse {
    private Long id;
    private BabyResponse baby;
    private ShiftLogResponse shiftLog;
    private String type;
    private String message;
    private String status;
    private LocalDateTime createdAt;
}
