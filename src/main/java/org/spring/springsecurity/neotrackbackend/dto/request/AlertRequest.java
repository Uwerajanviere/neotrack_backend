package org.spring.springsecurity.neotrackbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AlertRequest {
    @NotNull(message = "Baby ID is required")
    private Long babyId;

    @NotNull(message = "ShiftLog ID is required")
    private Long shiftLogId;

    @NotBlank(message = "Alert type is required")
    @Pattern(regexp = "^(LOW_TEMP|HIGH_TEMP|LOW_WEIGHT|HIGH_WEIGHT|OTHER)$",
            message = "Type must be LOW_TEMP, HIGH_TEMP, LOW_WEIGHT, HIGH_WEIGHT or OTHER")
    private String type;

    @NotBlank(message = "Message is required")
    private String message;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(ACTIVE|RESOLVED)$", message = "Status must be ACTIVE or RESOLVED")
    private String status;
}
