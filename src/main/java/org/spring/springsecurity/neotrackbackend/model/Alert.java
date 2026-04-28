package org.spring.springsecurity.neotrackbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @ManyToOne
    @JoinColumn(name = "shift_log_id")
    private ShiftLog shiftLog;

    private String type; // LOW_TEMP, HIGH_TEMP

    private String message;

    private String status; // ACTIVE, RESOLVED

    private LocalDateTime createdAt = LocalDateTime.now();
}
