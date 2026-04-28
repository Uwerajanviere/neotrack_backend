package org.spring.springsecurity.neotrackbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.AlertRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.AlertResponse;
import org.spring.springsecurity.neotrackbackend.service.AlertService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertResponse> create(@Valid @RequestBody AlertRequest request) {
        return ResponseEntity.ok(alertService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<AlertResponse>> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (status != null) return ResponseEntity.ok(alertService.getByStatus(status, page, size));
        return ResponseEntity.ok(alertService.getAll(page, size));
    }

    @GetMapping("/baby/{babyId}")
    public ResponseEntity<Page<AlertResponse>> getByBabyId(
            @PathVariable Long babyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(alertService.getByBabyId(babyId, page, size));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<AlertResponse> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolve(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alertService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
