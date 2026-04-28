package org.spring.springsecurity.neotrackbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.BabyRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.BabyResponse;
import org.spring.springsecurity.neotrackbackend.service.BabyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/babies")
@RequiredArgsConstructor
public class BabyController {

    private final BabyService babyService;

    @PostMapping
    public ResponseEntity<BabyResponse> create(@Valid @RequestBody BabyRequest request) {
        return ResponseEntity.ok(babyService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<BabyResponse>> search(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(babyService.search(status, name, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BabyResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(babyService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BabyResponse> update(@PathVariable Long id, @Valid @RequestBody BabyRequest request) {
        return ResponseEntity.ok(babyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        babyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
