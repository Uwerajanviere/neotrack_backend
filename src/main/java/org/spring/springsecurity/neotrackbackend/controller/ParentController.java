package org.spring.springsecurity.neotrackbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.ParentRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.ParentResponse;
import org.spring.springsecurity.neotrackbackend.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public ResponseEntity<ParentResponse> create(@Valid @RequestBody ParentRequest request) {
        return ResponseEntity.ok(parentService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ParentResponse>> getAll() {
        return ResponseEntity.ok(parentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentResponse> update(@PathVariable Long id, @Valid @RequestBody ParentRequest request) {
        return ResponseEntity.ok(parentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
