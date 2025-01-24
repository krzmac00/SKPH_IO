package com.example.skph;


import com.example.skph.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {
    @Autowired
    TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Add your initialization logic here
    }
}
