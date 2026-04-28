package org.spring.springsecurity.neotrackbackend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BabyResponse {
    private Long id;
    private String name;
    private String gender;
    private Double birthWeight;
    private Integer gestationalAge;
    private String diagnosis;
    private String status;
    private LocalDateTime admissionDate;
    private ParentResponse parent;
}
