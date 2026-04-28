package org.spring.springsecurity.neotrackbackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ShiftLogRequest {
    @NotNull(message = "Baby ID is required")
    private Long babyId;

    @NotNull(message = "Nurse ID is required")
    private Long nurseId;

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    @DecimalMax(value = "10.0", message = "Weight must be realistic (max 10kg)")
    private Double weight;

    @NotNull(message = "Temperature is required")
    @DecimalMin(value = "30.0", message = "Temperature must be at least 30°C")
    @DecimalMax(value = "43.0", message = "Temperature must be at most 43°C")
    private Double temperature;

    @NotNull(message = "Feeding amount is required")
    @DecimalMin(value = "0.0", message = "Feeding amount cannot be negative")
    private Double feedingAmount;

    private String medications;
    private String notes;
}
