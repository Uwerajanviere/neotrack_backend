package org.spring.springsecurity.neotrackbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.ShiftLogRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.ShiftLogResponse;
import org.spring.springsecurity.neotrackbackend.service.ShiftLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shift-logs")
@RequiredArgsConstructor
public class ShiftLogController {

    private final ShiftLogService shiftLogService;

    @PostMapping
    public ResponseEntity<ShiftLogResponse> create(@Valid @RequestBody ShiftLogRequest request) {
        return ResponseEntity.ok(shiftLogService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<ShiftLogResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(shiftLogService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftLogResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(shiftLogService.getById(id));
    }

    @GetMapping("/baby/{babyId}")
    public ResponseEntity<Page<ShiftLogResponse>> getByBabyId(
            @PathVariable Long babyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(shiftLogService.getByBabyId(babyId, page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shiftLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
