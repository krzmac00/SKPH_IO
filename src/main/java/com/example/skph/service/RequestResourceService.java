package com.example.skph.service;

import com.example.skph.model.Request;
import com.example.skph.model.RequestResource;
import com.example.skph.repository.RequestRepository;
import com.example.skph.repository.RequestResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RequestResourceService {
    private RequestResourceRepository requestResourceRepository;

    @Autowired
    public RequestResourceService(RequestResourceRepository requestResourceRepository) {
        this.requestResourceRepository = requestResourceRepository;
    }

    public void saveRequestResource(RequestResource requestResource) {
        requestResourceRepository.save(requestResource); // Zapisz dane zgłoszenia
    }

    public RequestResource getRequestResourceById(Long id) {
        return requestResourceRepository.findById(id).orElse(null);
    }

    public List<RequestResource> getByRequestId(Long requestId) {
        return requestResourceRepository.findByRequest(requestId);
    }

    public List<RequestResource> getByResourceId(Long resourceId) {
        return requestResourceRepository.findByResource(resourceId);
    }
}
