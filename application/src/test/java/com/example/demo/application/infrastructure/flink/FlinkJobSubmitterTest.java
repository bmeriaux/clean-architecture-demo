package com.example.demo.application.infrastructure.flink;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.JobID;
import org.apache.flink.client.program.ClusterClient;
import org.apache.flink.client.program.PackagedProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class FlinkJobSubmitterTest {

    private static final String JOB_NAME = "WordCount";
    private static final String JOB_JAR_NAME = "job.jar";
    private static final String JOB_JAR_ROOT_PATH = "jobs";
    private static final int DEFAULT_PARALLELISM = 3;

    private FlinkProperties flinkProperties;
    private FlinkJobSubmitter flinkJobSubmitter;

    @Mock
    private ClusterClient clusterClient;

    @Mock
    private PackagedProgramFactory packagedProgramFactory;

    @Mock
    private PackagedProgram packagedProgram;

    private File jobJarFile;

    @BeforeEach
    void setup() {
        flinkProperties = initFlinkProperties();
        flinkJobSubmitter = new FlinkJobSubmitter(flinkProperties,
                clusterClient,
                packagedProgramFactory);

        jobJarFile = Paths.get(flinkProperties.getJobJarRootPath(), flinkProperties.getJobJarName()).toAbsolutePath().toFile();
    }

    @Test
    void submitJob_shouldPackageJarWithCliArgsAndRunTheJob() throws Exception {
        // Given
        Map<String, String> jobArgs = new LinkedHashMap<>();

        String[] cliArgs = new String[]{
                "--job.args.jobName=WordCount"
        };
        Long jobDuration = 100L;
        JobExecutionResult jobExecutionResult = new JobExecutionResult(new JobID(), jobDuration, null);
        given(packagedProgramFactory.getInstance(jobJarFile, cliArgs)).willReturn(packagedProgram);
        given(clusterClient.run(packagedProgram, DEFAULT_PARALLELISM)).willReturn(jobExecutionResult);

        // When
        String jobId = flinkJobSubmitter.submitJob(JOB_NAME, jobArgs);

        // Then
        assertThat(jobId).isNotEmpty();
        then(packagedProgramFactory).should().getInstance(jobJarFile, cliArgs);
        then(clusterClient).should().run(packagedProgram, DEFAULT_PARALLELISM);
    }

    private FlinkProperties initFlinkProperties() {
        flinkProperties = new FlinkProperties();
        flinkProperties.setJobJarName(JOB_JAR_NAME);
        flinkProperties.setJobJarRootPath(JOB_JAR_ROOT_PATH);
        flinkProperties.setDefaultParallelism(DEFAULT_PARALLELISM);
        return flinkProperties;
    }

}