package com.example.skph.service;

import com.example.skph.config.JpaConfig;
import com.example.skph.model.Requester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(classes = {EntityService.class, EntityRepository.class, Requester.class, JpaConfig.class})
public class EntityServiceTest {

    @Autowired
    private EntityService entityService;

    @Test
    void testGetEntity_Found() {
        Requester entity = new Requester();
        Assertions.assertEquals("Entity 1", entityService.getEntity(1L).getName());
    }

    @Test
    void testGetEntity_NotFound() {
        Assertions.assertNull(entityService.getEntity(10L));
    }
}



