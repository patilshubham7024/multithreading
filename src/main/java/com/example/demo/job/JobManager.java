package com.example.demo.job;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JobManager {

    private final ConcurrentHashMap<String, MyJob> JOB = new ConcurrentHashMap<>();

    @Autowired
    JobExecutor jobExecutor;

    public MyJob getJob(String jobId) {
    	return JOB.get(jobId);
    }

    public MyJob addJob(MyJob myJob){
        JOB.put(myJob.getJobId(), myJob);
        Future<?> future = jobExecutor.executeJob(myJob);
        myJob.setFuture(future);
        return myJob;
    }
}
