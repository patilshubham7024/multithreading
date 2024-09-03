package com.example.demo.service;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.job.JobManager;
import com.example.demo.job.MyJob;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
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
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				service1.execute("From service");
				service2.execute("From service");
				service3.execute("From service");
			}
		}, jobId);
		return jobManager.addJob(MyJob.builder().jobId(jobId).thread(thread).build());
    }
    
    public boolean stopJob(String jobId) {
    	log.info("Attempting to stop the job : " + jobId);
    	MyJob myJob = jobManager.getJob(jobId);
    	
    	Thread job = myJob.getThread();
    	log.info("Job thread priority : " + job.getPriority());

    	Thread currentThread = Thread.currentThread();
    	log.info("Current thread priority : " + currentThread.getPriority());
    	currentThread.setPriority(10);
    	log.info("Current thread priority : " + currentThread.getPriority());

    	Future<?> future = myJob.getFuture();
    	boolean cancel = future.cancel(true);
    	
    	log.info(cancel + " : Stopped the job : " + jobId);

    	if(!cancel) {
        	log.info("Attempting to stop the job by interupting : " + jobId);
    		job.interrupt();
    		log.info("Attempted to stop the job by interupting : " + jobId);
    	}
    	return true;
    }

}
