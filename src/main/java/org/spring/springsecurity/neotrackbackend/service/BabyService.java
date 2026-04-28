package org.spring.springsecurity.neotrackbackend.service;

import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.BabyRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.BabyResponse;
import org.spring.springsecurity.neotrackbackend.exception.ResourceNotFoundException;
import org.spring.springsecurity.neotrackbackend.model.Baby;
import org.spring.springsecurity.neotrackbackend.model.Parent;
import org.spring.springsecurity.neotrackbackend.repostitory.BabyRepository;
import org.spring.springsecurity.neotrackbackend.repostitory.ParentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BabyService {

    private final BabyRepository babyRepository;
    private final ParentRepository parentRepository;
    private final ParentService parentService;

    public BabyResponse create(BabyRequest request) {
        Parent parent = parentRepository.findById(request.getParentId())
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found with id: " + request.getParentId()));
        Baby baby = new Baby();
        baby.setName(request.getName());
        baby.setGender(request.getGender());
        baby.setBirthWeight(request.getBirthWeight());
        baby.setGestationalAge(request.getGestationalAge());
        baby.setDiagnosis(request.getDiagnosis());
        baby.setStatus(request.getStatus());
        baby.setParent(parent);
        return toResponse(babyRepository.save(baby));
    }

    public Page<BabyResponse> search(String status, String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("admissionDate").descending());
        return babyRepository.search(status, name, pageable).map(this::toResponse);
    }

    public BabyResponse getById(Long id) {
        return toResponse(babyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baby not found with id: " + id)));
    }

    public BabyResponse update(Long id, BabyRequest request) {
        Baby baby = babyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baby not found with id: " + id));
        Parent parent = parentRepository.findById(request.getParentId())
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found with id: " + request.getParentId()));
        baby.setName(request.getName());
        baby.setGender(request.getGender());
        baby.setBirthWeight(request.getBirthWeight());
        baby.setGestationalAge(request.getGestationalAge());
        baby.setDiagnosis(request.getDiagnosis());
        baby.setStatus(request.getStatus());
        baby.setParent(parent);
        return toResponse(babyRepository.save(baby));
    }

    public void delete(Long id) {
        babyRepository.deleteById(id);
    }

    public BabyResponse toResponse(Baby baby) {
        BabyResponse res = new BabyResponse();
        res.setId(baby.getId());
        res.setName(baby.getName());
        res.setGender(baby.getGender());
        res.setBirthWeight(baby.getBirthWeight());
        res.setGestationalAge(baby.getGestationalAge());
        res.setDiagnosis(baby.getDiagnosis());
        res.setStatus(baby.getStatus());
        res.setAdmissionDate(baby.getAdmissionDate());
        if (baby.getParent() != null) res.setParent(parentService.toResponse(baby.getParent()));
        return res;
    }
}
