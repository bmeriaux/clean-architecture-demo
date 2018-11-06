package com.example.demo.job.infrastructure.flink;

import org.apache.flink.api.common.JobExecutionResult;

public interface Job {
    JobExecutionResult start() throws Exception;

    String getName();
}
