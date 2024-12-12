package com.example.skph.service;

import com.example.skph.model.Requester;
import com.example.skph.repository.RequesterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequesterService {
    private final RequesterRepository requesterRepository;

    @Autowired
    public RequesterService(RequesterRepository requesterRepository) {
        this.requesterRepository = requesterRepository;
    }

    @Transactional
    public Requester getRequesterById(Long id) {
//        return requesterRepository.findById(id).orElse(null); // troche krocej
        return requesterRepository.findById(id).isPresent() ? requesterRepository.findById(id).get() : null;
    }

    @Transactional
    public List<Requester> getRequesterByLastName(String lastName) {
        return requesterRepository.findByLastName(lastName);
    }

    @Transactional
    public List<Requester> getRequesterByFirstAndLastName(String firstName, String lastName) {
        return requesterRepository.findByFirstAndLastName(lastName, firstName);
    }
}
