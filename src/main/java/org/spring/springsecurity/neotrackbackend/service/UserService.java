package org.spring.springsecurity.neotrackbackend.service;

import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.RegisterRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.UserResponse;
import org.spring.springsecurity.neotrackbackend.exception.BadRequestException;
import org.spring.springsecurity.neotrackbackend.exception.ResourceNotFoundException;
import org.spring.springsecurity.neotrackbackend.model.User;
import org.spring.springsecurity.neotrackbackend.repostitory.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse getById(Long id) {
        return toResponse(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)));
    }

    public UserResponse update(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getEmail().equals(request.getEmail()) &&
                userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return toResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setCreatedAt(user.getCreatedAt());
        return res;
    }
}
