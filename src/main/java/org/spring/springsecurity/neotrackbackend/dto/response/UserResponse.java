package org.spring.springsecurity.neotrackbackend.dto.response;

import lombok.Data;
import org.spring.springsecurity.neotrackbackend.model.Role;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
