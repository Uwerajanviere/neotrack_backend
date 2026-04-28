package org.spring.springsecurity.neotrackbackend.service;

import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.AlertRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.AlertResponse;
import org.spring.springsecurity.neotrackbackend.exception.ResourceNotFoundException;
import org.spring.springsecurity.neotrackbackend.model.Alert;
import org.spring.springsecurity.neotrackbackend.model.Baby;
import org.spring.springsecurity.neotrackbackend.model.ShiftLog;
import org.spring.springsecurity.neotrackbackend.repostitory.AlertRepository;
import org.spring.springsecurity.neotrackbackend.repostitory.BabyRepository;
import org.spring.springsecurity.neotrackbackend.repostitory.ShiftLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final BabyRepository babyRepository;
    private final ShiftLogRepository shiftLogRepository;
    private final BabyService babyService;
    private final ShiftLogService shiftLogService;

    public AlertResponse create(AlertRequest request) {
        Baby baby = babyRepository.findById(request.getBabyId())
                .orElseThrow(() -> new ResourceNotFoundException("Baby not found with id: " + request.getBabyId()));
        ShiftLog shiftLog = shiftLogRepository.findById(request.getShiftLogId())
                .orElseThrow(() -> new ResourceNotFoundException("ShiftLog not found with id: " + request.getShiftLogId()));
        Alert alert = new Alert();
        alert.setBaby(baby);
        alert.setShiftLog(shiftLog);
        alert.setType(request.getType());
        alert.setMessage(request.getMessage());
        alert.setStatus(request.getStatus());
        return toResponse(alertRepository.save(alert));
    }

    public Page<AlertResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return alertRepository.findAll(pageable).map(this::toResponse);
    }

    public Page<AlertResponse> getByBabyId(Long babyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return alertRepository.findByBabyId(babyId, pageable).map(this::toResponse);
    }

    public Page<AlertResponse> getByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return alertRepository.findByStatus(status, pageable).map(this::toResponse);
    }

    public AlertResponse resolve(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found with id: " + id));
        alert.setStatus("RESOLVED");
        return toResponse(alertRepository.save(alert));
    }

    public void delete(Long id) {
        alertRepository.deleteById(id);
    }

    private AlertResponse toResponse(Alert alert) {
        AlertResponse res = new AlertResponse();
        res.setId(alert.getId());
        res.setType(alert.getType());
        res.setMessage(alert.getMessage());
        res.setStatus(alert.getStatus());
        res.setCreatedAt(alert.getCreatedAt());
        if (alert.getBaby() != null) res.setBaby(babyService.toResponse(alert.getBaby()));
        if (alert.getShiftLog() != null) res.setShiftLog(shiftLogService.toResponse(alert.getShiftLog()));
        return res;
    }
}
