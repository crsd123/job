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
public class ScheduleTest {

    @Autowired
    private ScheduleManager scheduleManager;

    @Test
    public void test() {
        Schedule schedule = new Schedule();
        schedule.setJobName("myjob");
        schedule.setJobGroup("test");
        schedule.setJobClassName("com.conanli.job.job.Main");
        schedule.setTriggerType(Schedule.TRIGGER_SIMPLE);
        schedule.setTriggerRepeat(0);
        schedule.setTriggerInterval(1000L);
        // schedule.setTriggerType(Schedule.TRIGGER_CRON);
        // schedule.setTriggerCron("*/1 * * * * ?");
        schedule.setJarPath("d:/job-example-java-simple-0.0.1.jar");
        schedule.setIsEnable("1");

        scheduleManager.start(schedule);

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
