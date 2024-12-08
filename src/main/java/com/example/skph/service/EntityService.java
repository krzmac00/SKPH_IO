package com.example.skph.service;

import com.example.skph.model.Entity;
import com.example.skph.repository.EntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Transactional
    public Entity getEntity(Long id) {
        return entityRepository.findById(id).isPresent() ? entityRepository.findById(id).get() : null;
    }
}
