package com.example.demo.job;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

@Data
@Builder
public class MyJob {
    private String jobId;
    private List<Function<String, Future<String>>> function;
}
