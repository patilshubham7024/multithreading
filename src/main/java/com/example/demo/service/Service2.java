package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Log4j2
public class Service2 {

    public Future<String> execute(String id) {
        log.info("Job 2 execution has started for id : {}", id);
        boolean loop = true;
        int wait = 0;
        do{
            log.info("Waiting to complete job 2 : {}", wait++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(wait == 10) {
                loop = false;
            }
        } while (loop);
        return CompletableFuture.completedFuture("Job 2 has done!");
    }

}
