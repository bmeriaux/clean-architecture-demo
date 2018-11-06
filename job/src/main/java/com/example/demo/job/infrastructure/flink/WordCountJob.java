package com.example.demo.job.infrastructure.flink;

import org.apache.flink.api.common.JobExecutionResult;
import org.springframework.stereotype.Component;

@Component
public class WordCountJob implements Job {

    public static final String JOB_NAME = "wordCount";

    @Override
    public JobExecutionResult start() throws Exception {
        return null;
    }

    @Override
    public String getName() {
        return JOB_NAME;
    }
}
