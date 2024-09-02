package com.example.demo.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

@Component
@Log4j2
public class JobExecutor {

    @Async
    public void executeJob(MyJob myJob) {
        List<Function<String, Future<String>>> functionList = myJob.getFunction();
        log.info("Started running the job : {}", myJob.getJobId());
        int jobCount= 1;
        for (Function<String, Future<String>> function : functionList) {
            Future<String> job = function.apply("From executor");
            log.info("Completed jobs : {}", jobCount++);
        }
    }

}
