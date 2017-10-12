package com.conanli.job.schedule;

import com.conanli.job.common.BusinessException;
import com.conanli.job.quartz.QuartzManager;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScheduleManager {

    private QuartzManager quartzManager;
    private ScheduleStore scheduleStore;
    private ScheduleLoader scheduleLoader;

    public ScheduleManager(QuartzManager quartzManager, ScheduleStore scheduleStore, ScheduleLoader scheduleLoader) {
        this.quartzManager = quartzManager;
        this.scheduleStore = scheduleStore;
        this.scheduleLoader = scheduleLoader;
    }

    public void init() {
        List<Schedule> schedules = scheduleStore.list();
        for (Schedule schedule : schedules) {
            try {
                start(schedule);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        quartzManager.startScheduler();
    }

    public void start(String jobName) {
        Schedule schedule = scheduleStore.get(jobName);
        if (schedule == null)
            throw new BusinessException("Job不存在");
        start(schedule);
    }

    public void start(Schedule schedule) {
        if (!"1".equals(schedule.getIsEnable()))
            return;

        Class<? extends Job> jobClass = null;
        try {
            jobClass = scheduleLoader.loadClass(schedule.getJobName(), schedule.getJobClassName(), schedule.getJarPath());
        } catch (IOException e) {
            throw new BusinessException("加载Jar失败", e);
        } catch (ClassNotFoundException e) {
            throw new BusinessException("加载Class失败", e);
        }

        Map<String, Object> jobData = getJobData(schedule.getJobParams());
        jobData.put("jobName", schedule.getJobName());
        jobData.put("jobGroup", schedule.getJobGroup());
        jobData.put("jarPath", schedule.getJarPath());

        if (Schedule.TRIGGER_SIMPLE.equals(schedule.getTriggerType())) {
            quartzManager.addJob(schedule.getJobName(), schedule.getJobGroup(), jobClass, jobData, schedule.getTriggerRepeat(), schedule.getTriggerInterval(), schedule.getTriggerStartTime(), schedule.getTriggerStopTime());
        } else if (Schedule.TRIGGER_CRON.equals(schedule.getTriggerType())) {
            quartzManager.addJob(schedule.getJobName(), schedule.getJobGroup(), jobClass, jobData, schedule.getTriggerCron(), schedule.getTriggerStartTime(), schedule.getTriggerStopTime());
        }
    }

    public void stop(String jobName) {
        Schedule schedule = scheduleStore.get(jobName);
        if (schedule == null)
            throw new BusinessException("Job不存在");
        stop(schedule);
    }

    public void stop(Schedule schedule) {
        quartzManager.removeJob(schedule.getJobName(), schedule.getJobGroup());
    }

    public void restart(String jobName) {
        Schedule schedule = scheduleStore.get(jobName);
        if (schedule == null)
            throw new BusinessException("Job不存在");
        restart(schedule);
    }

    public void restart(Schedule schedule) {
        stop(schedule);
        start(schedule);
    }

    public void run(String jobName) {
        Schedule schedule = scheduleStore.get(jobName);
        if (schedule == null)
            throw new BusinessException("Job不存在");
        run(schedule);
    }

    public void run(Schedule schedule) {
        Class<? extends Job> jobClass = null;
        try {
            jobClass = scheduleLoader.loadClass(schedule.getJobName(), schedule.getJobClassName(), schedule.getJarPath());
        } catch (IOException e) {
            throw new BusinessException("加载Jar失败", e);
        } catch (ClassNotFoundException e) {
            throw new BusinessException("加载Class失败", e);
        }

        Map<String, Object> jobData = getJobData(schedule.getJobParams());
        jobData.put("jobName", schedule.getJobName());
        jobData.put("jobGroup", schedule.getJobGroup());
        jobData.put("jarPath", schedule.getJarPath());

        if (quartzManager.hasJob(schedule.getJobName() + "$$ONCE", schedule.getJobGroup())) {
            quartzManager.removeJob(schedule.getJobName() + "$$ONCE", schedule.getJobGroup());
        }
        quartzManager.addJob(schedule.getJobName() + "$$ONCE", schedule.getJobGroup(), jobClass, jobData);
        quartzManager.triggerJob(schedule.getJobName() + "$$ONCE", schedule.getJobGroup());
    }

    public void save(Schedule schedule) {
        scheduleStore.save(schedule);
    }

    public void delete(String jobName) {
        stop(jobName);
        scheduleStore.delete(jobName);
    }

    public List<Schedule> list() {
        List<Schedule> schedules = scheduleStore.list();

        for (Schedule schedule : schedules) {
            List<? extends Trigger> triggers = quartzManager.listTrigger(JobKey.jobKey(schedule.getJobName(), schedule.getJobGroup()));
            for (Trigger trigger : triggers) {
                if (trigger.getNextFireTime() == null)
                    continue;
                schedule.setIsRunning("1");
                if (schedule.getNextFireTime() == null || schedule.getNextFireTime().compareTo(trigger.getNextFireTime()) == 1) {
                    schedule.setNextFireTime(trigger.getNextFireTime());
                }
            }
        }

        return schedules;
    }

    public List<String> listName() {
        return scheduleStore.listName();
    }

    public List<ScheduleLog> listLog(String jobName) {
        return scheduleStore.listLog(jobName);
    }

    private static Map<String, Object> getJobData(String jobParams) {
        if (jobParams == null || jobParams.length() == 0)
            return new HashMap<>();
        return Arrays.stream(jobParams.split(";"))
                .filter(t -> t.matches("^(\\w+)=(\\w+)$"))
                .collect(Collectors.toMap(t -> t.replaceAll("=(\\w+)$", ""), t -> t.replaceAll("^(\\w+)=", ""), (t1, t2) -> t2));
    }
}
