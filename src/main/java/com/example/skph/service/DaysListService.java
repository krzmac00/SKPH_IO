package com.example.skph.service;

import com.example.skph.model.Day;
import com.example.skph.repository.DaysListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaysListService {
    private final DaysListRepository daysListRepository;

    @Autowired
    public DaysListService(DaysListRepository daysListRepository) {
        this.daysListRepository = daysListRepository;
    }

    public Day getDaysListById(Long id) {
        return daysListRepository.findById(id).orElse(null);
    }
}
