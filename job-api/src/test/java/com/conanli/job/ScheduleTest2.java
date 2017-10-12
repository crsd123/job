package com.conanli.job;

import com.conanli.job.schedule.Schedule;
import com.conanli.job.schedule.ScheduleManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApiApplication.class)
public class ScheduleTest2 {

    @Autowired
    private ScheduleManager scheduleManager;

    @Test
    public void test() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setJobName("myjob1");
        schedule.setJobGroup("test");
        schedule.setJobClassName("com.zhidian.cloud.job.job.Main");
        schedule.setTriggerType(Schedule.TRIGGER_CRON);
        schedule.setTriggerCron("*/2 * * * * ?");
        schedule.setJarPath("d:/job-example-springboot-mybatis-0.0.1.jar");
        schedule.setIsEnable("1");

        Schedule schedule2 = new Schedule();
        schedule2.setJobName("myjob2");
        schedule2.setJobGroup("test");
        schedule2.setJobClassName("com.zhidian.cloud.job.job.Main");
        schedule2.setTriggerType(Schedule.TRIGGER_CRON);
        schedule2.setTriggerCron("*/1 * * * * ?");
        schedule2.setJarPath("d:/job-example-springcloud-feign-0.0.1.jar");
        schedule2.setIsEnable("1");

        for (int i = 0; i < 9; i++) {
            scheduleManager.start(schedule);
            Thread.sleep(3000L);
            scheduleManager.stop(schedule);
            scheduleManager.start(schedule2);
            Thread.sleep(3000L);
            scheduleManager.stop(schedule2);
        }

        while (Thread.activeCount() > 1)
            Thread.yield();
    }
}
