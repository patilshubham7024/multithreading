package com.example.demo.controller;

import com.example.demo.job.MyJob;
import com.example.demo.service.MyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log4j2
@RequestMapping
public class Controller {
    @Autowired
    MyService service;

    @GetMapping()
    public String startJob() {
        log.info("Starting the job");
        String id = UUID.randomUUID().toString();
        MyJob myJob = service.startJob(id);
        log.info("myJob = {}", myJob);
        return id;
    }



}
