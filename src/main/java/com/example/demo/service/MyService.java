package com.example.demo.service;

import com.example.demo.job.JobManager;
import com.example.demo.job.MyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

@Service
public class MyService {

    @Autowired
    Service1 service1;
    @Autowired
    Service2 service2;
    @Autowired
    Service3 service3;


    @Autowired
    private JobManager jobManager;

    public MyJob startJob(String jobId) {
        List<Function<String, Future<String>>> functionsList
                = Arrays.asList(
                (jobId1) -> service1.execute("From service"),
                (jobId1) -> service2.execute("From service"),
                (jobId1) -> service3.execute("From service")
        );

        return jobManager.addJob(MyJob.builder()
                        .jobId(jobId)
                        .function(functionsList)
                .build());
    }
}
