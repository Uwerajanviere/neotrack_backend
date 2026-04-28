package org.spring.springsecurity.neotrackbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Entity
@Table(name="baby")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Baby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender;

    private Double birthWeight;
    private Integer gestationalAge;
    private String diagnosis;
    private LocalDateTime admissionDate = LocalDateTime.now();
    private String status; // STABLE, CRITICAL, IMPROVING
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
