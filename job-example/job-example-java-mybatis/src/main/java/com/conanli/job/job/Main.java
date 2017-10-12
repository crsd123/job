package com.conanli.job.job;

import com.conanli.job.job.mybatis.SqlMapper;
import com.conanli.job.job.mybatis.MybatisConfig;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    SqlMapper sqlMapper;

    public Main() {
        MybatisConfig mybatisConfig = new MybatisConfig();
        sqlMapper = mybatisConfig.getSqlMapper();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        logger.info("select1: " + sqlMapper.select1());
    }

}
