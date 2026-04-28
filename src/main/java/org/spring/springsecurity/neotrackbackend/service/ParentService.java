package org.spring.springsecurity.neotrackbackend.service;

import lombok.RequiredArgsConstructor;
import org.spring.springsecurity.neotrackbackend.dto.request.ParentRequest;
import org.spring.springsecurity.neotrackbackend.dto.response.ParentResponse;
import org.spring.springsecurity.neotrackbackend.exception.ResourceNotFoundException;
import org.spring.springsecurity.neotrackbackend.model.Parent;
import org.spring.springsecurity.neotrackbackend.repostitory.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public ParentResponse create(ParentRequest request) {
        Parent parent = new Parent();
        parent.setName(request.getName());
        parent.setPhoneNumber(request.getPhoneNumber());
        parent.setLanguage(request.getLanguage());
        return toResponse(parentRepository.save(parent));
    }

    public List<ParentResponse> getAll() {
        return parentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ParentResponse getById(Long id) {
        return toResponse(parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found with id: " + id)));
    }

    public ParentResponse update(Long id, ParentRequest request) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found with id: " + id));
        parent.setName(request.getName());
        parent.setPhoneNumber(request.getPhoneNumber());
        parent.setLanguage(request.getLanguage());
        return toResponse(parentRepository.save(parent));
    }

    public void delete(Long id) {
        parentRepository.deleteById(id);
    }

    public ParentResponse toResponse(Parent parent) {
        ParentResponse res = new ParentResponse();
        res.setId(parent.getId());
        res.setName(parent.getName());
        res.setPhoneNumber(parent.getPhoneNumber());
        res.setLanguage(parent.getLanguage());
        return res;
    }
}
