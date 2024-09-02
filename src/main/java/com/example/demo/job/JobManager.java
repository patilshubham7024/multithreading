package com.example.demo.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.function.Function;

@Component
@Log4j2
public class JobManager {
    private final ConcurrentHashMap<String, MyJob> JOB = new ConcurrentHashMap<>();

    @Autowired
    JobExecutor jobExecutor;

    public MyJob addJob(MyJob myJob){
        JOB.put(myJob.getJobId(), myJob);
        jobExecutor.executeJob(myJob);
        return myJob;
    }

}
