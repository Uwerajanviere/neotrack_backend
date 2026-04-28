package org.spring.springsecurity.neotrackbackend.repostitory;

import org.spring.springsecurity.neotrackbackend.model.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Page<Alert> findByBabyId(Long babyId, Pageable pageable);
    Page<Alert> findByStatus(String status, Pageable pageable);
}
