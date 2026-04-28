package org.spring.springsecurity.neotrackbackend.repostitory;

import org.spring.springsecurity.neotrackbackend.model.Baby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BabyRepository extends JpaRepository<Baby, Long> {
    @Query("SELECT b FROM Baby b WHERE " +
           "(:status IS NULL OR b.status = :status) AND " +
           "(:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<Baby> search(@Param("status") String status, @Param("name") String name, Pageable pageable);
}
