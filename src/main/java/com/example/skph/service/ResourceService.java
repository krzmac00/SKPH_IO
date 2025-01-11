package com.example.skph.service;

import com.example.skph.model.Request;
import com.example.skph.model.Resource;
import com.example.skph.repository.RequestRepository;
import com.example.skph.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public void saveResource(Resource resource) {
        resourceRepository.save(resource); // Zapisz dane zgłoszenia
    }
}
