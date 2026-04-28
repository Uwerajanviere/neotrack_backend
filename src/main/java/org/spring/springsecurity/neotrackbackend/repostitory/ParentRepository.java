package org.spring.springsecurity.neotrackbackend.repostitory;

import org.spring.springsecurity.neotrackbackend.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent,Long> {
}
