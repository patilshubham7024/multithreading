package com.example.demo.job;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JobExecutor {

	@Async
    public Future<?> executeJob(MyJob myJob) {
        Thread thread = myJob.getThread();
        thread.setPriority(1);
        int priority = thread.getPriority();
        log.info("Priority of execution : " + priority);
	    log.info("Attempt to start the job : {}", myJob.getJobId());
	    thread.start();
	    log.info("Started running the job : {}", myJob.getJobId());
	    return CompletableFuture.completedFuture("Shubham");
	}
}
