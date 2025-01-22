package com.example.skph.service;

import com.example.skph.model.Resource;
import com.example.skph.model.resources.TransportResource;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransportService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public TransportService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Optional<TransportResource> findById(Long transportId) {
        // Wyszukujemy w ResourceRepository, a następnie rzutujemy
        return resourceRepository.findById(transportId)
                .filter(r -> r instanceof TransportResource)
                .map(r -> (TransportResource) r);
    }

    public TransportResource save(TransportResource transportResource) {
        return resourceRepository.save(transportResource);
    }
}
