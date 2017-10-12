package com.conanli.job.job;

import com.conanli.job.job.client.ConfigClient;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

@Component
@EnableFeignClients(clients = ConfigClient.class)
public class Main implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ConfigClient configClient;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        logger.info("content: " + configClient.getConfig());
    }
}
