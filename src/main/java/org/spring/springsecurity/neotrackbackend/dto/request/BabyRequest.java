package org.spring.springsecurity.neotrackbackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BabyRequest {
    @NotBlank(message = "Baby name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be Male or Female")
    private String gender;

    @NotNull(message = "Birth weight is required")
    @DecimalMin(value = "0.1", message = "Birth weight must be greater than 0")
    @DecimalMax(value = "10.0", message = "Birth weight must be realistic (max 10kg)")
    private Double birthWeight;

    @NotNull(message = "Gestational age is required")
    @Min(value = 22, message = "Gestational age must be at least 22 weeks")
    @Max(value = 45, message = "Gestational age must be at most 45 weeks")
    private Integer gestationalAge;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(STABLE|CRITICAL|IMPROVING)$", message = "Status must be STABLE, CRITICAL or IMPROVING")
    private String status;

    @NotNull(message = "Parent ID is required")
    private Long parentId;
}
