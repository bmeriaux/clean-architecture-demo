package com.example.demo.application.infrastructure.flink;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("flink")
public class FlinkProperties {

    private Integer defaultParallelism;
    private String managerHost;
    private Integer managerRpcPort;
    private String jobJarRootPath;
    private String jobJarName;
    private String jobIntegrationRootPath;
    private Boolean useRestClient;

    public String getJobJarRootPath() {
        return jobJarRootPath;
    }

    public void setJobJarRootPath(String jobJarRootPath) {
        this.jobJarRootPath = jobJarRootPath;
    }

    public String getJobJarName() {
        return jobJarName;
    }

    public void setJobJarName(String jobJarName) {
        this.jobJarName = jobJarName;
    }

    public Integer getDefaultParallelism() {
        return defaultParallelism;
    }

    public void setDefaultParallelism(Integer defaultParallelism) {
        this.defaultParallelism = defaultParallelism;
    }

    public String getManagerHost() {
        return managerHost;
    }

    public void setManagerHost(String managerHost) {
        this.managerHost = managerHost;
    }

    public Integer getManagerRpcPort() {
        return managerRpcPort;
    }

    public void setManagerRpcPort(Integer managerRpcPort) {
        this.managerRpcPort = managerRpcPort;
    }

    public Boolean getUseRestClient() {
        return useRestClient;
    }

    public void setUseRestClient(Boolean useRestClient) {
        this.useRestClient = useRestClient;
    }

    public String getJobIntegrationRootPath() {
        return jobIntegrationRootPath;
    }

    public void setJobIntegrationRootPath(String jobIntegrationRootPath) {
        this.jobIntegrationRootPath = jobIntegrationRootPath;
    }
}
