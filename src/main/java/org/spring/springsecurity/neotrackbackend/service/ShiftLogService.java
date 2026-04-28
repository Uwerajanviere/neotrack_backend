package org.spring.springsecurity.neotrackbackend.service;

import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.ShiftLogRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.ShiftLogResponse;
import org.spring.springsecurity.neotrackbackend.dto.response.UserResponse;
import org.spring.springsecurity.neotrackbackend.exception.ResourceNotFoundException;
import org.spring.springsecurity.neotrackbackend.model.Baby;
import org.spring.springsecurity.neotrackbackend.model.ShiftLog;
import org.spring.springsecurity.neotrackbackend.model.User;
import org.spring.springsecurity.neotrackbackend.repostitory.BabyRepository;
import org.spring.springsecurity.neotrackbackend.repostitory.ShiftLogRepository;
import org.spring.springsecurity.neotrackbackend.repostitory.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftLogService {

    private final ShiftLogRepository shiftLogRepository;
    private final BabyRepository babyRepository;
    private final UserRepository userRepository;
    private final BabyService babyService;

    public ShiftLogResponse create(ShiftLogRequest request) {
        Baby baby = babyRepository.findById(request.getBabyId())
                .orElseThrow(() -> new ResourceNotFoundException("Baby not found with id: " + request.getBabyId()));
        User nurse = userRepository.findById(request.getNurseId())
                .orElseThrow(() -> new ResourceNotFoundException("Nurse not found with id: " + request.getNurseId()));
        ShiftLog log = new ShiftLog();
        log.setBaby(baby);
        log.setNurse(nurse);
        log.setWeight(request.getWeight());
        log.setTemperature(request.getTemperature());
        log.setFeedingAmount(request.getFeedingAmount());
        log.setMedications(request.getMedications());
        log.setNotes(request.getNotes());
        return toResponse(shiftLogRepository.save(log));
    }

    public Page<ShiftLogResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return shiftLogRepository.findAll(pageable).map(this::toResponse);
    }

    public Page<ShiftLogResponse> getByBabyId(Long babyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return shiftLogRepository.findByBabyId(babyId, pageable).map(this::toResponse);
    }

    public ShiftLogResponse getById(Long id) {
        return toResponse(shiftLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShiftLog not found with id: " + id)));
    }

    public void delete(Long id) {
        shiftLogRepository.deleteById(id);
    }

    public ShiftLogResponse toResponse(ShiftLog log) {
        ShiftLogResponse res = new ShiftLogResponse();
        res.setId(log.getId());
        res.setWeight(log.getWeight());
        res.setTemperature(log.getTemperature());
        res.setFeedingAmount(log.getFeedingAmount());
        res.setMedications(log.getMedications());
        res.setNotes(log.getNotes());
        res.setCreatedAt(log.getCreatedAt());
        if (log.getBaby() != null) res.setBaby(babyService.toResponse(log.getBaby()));
        if (log.getNurse() != null) res.setNurse(toUserResponse(log.getNurse()));
        return res;
    }

    private UserResponse toUserResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setCreatedAt(user.getCreatedAt());
        return res;
    }
}
