package com.conanli.job.job;

import com.conanli.job.job.mybatis.MybatisConfiguration;
import com.conanli.job.job.mybatis.SqlMapper;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({MybatisConfiguration.class})
@Component
public class Main implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SqlMapper sqlMapper;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        logger.info("select1: " + sqlMapper.select1());
    }
}
