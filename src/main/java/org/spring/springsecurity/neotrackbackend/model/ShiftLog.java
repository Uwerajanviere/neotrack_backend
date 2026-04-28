package org.spring.springsecurity.neotrackbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="shiftLogs")
public class ShiftLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private User nurse;

    private Double weight;

    private Double temperature;

    private Double feedingAmount;

    private String medications;

    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();
}
