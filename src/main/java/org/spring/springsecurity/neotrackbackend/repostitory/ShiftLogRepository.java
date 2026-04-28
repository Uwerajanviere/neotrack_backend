package org.spring.springsecurity.neotrackbackend.repostitory;

import org.spring.springsecurity.neotrackbackend.model.ShiftLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftLogRepository extends JpaRepository<ShiftLog, Long> {
    Page<ShiftLog> findByBabyId(Long babyId, Pageable pageable);
}
