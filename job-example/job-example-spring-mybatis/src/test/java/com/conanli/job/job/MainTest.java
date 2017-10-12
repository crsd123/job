package com.conanli.job.job;

import com.conanli.job.quartz.QuartzManager;
import com.conanli.job.test.SpringJobApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJobApplication.class)
public class MainTest {

    @Autowired
    QuartzManager quartzManager;

    @Test
    public void test() throws Exception {
        String jobName = "test";
        String jobGroup = "example";
        Class<? extends Job> jobClass = Main.class;
        Map<String, Object> jobData = new HashMap<>();

        quartzManager.addJob(jobName, jobGroup, jobClass, jobData);
        quartzManager.triggerJob(jobName, jobGroup);

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
