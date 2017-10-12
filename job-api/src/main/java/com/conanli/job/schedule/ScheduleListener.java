package com.conanli.job.schedule;

import com.conanli.job.contact.Contact;
import com.conanli.job.contact.ContactStore;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScheduleListener implements JobListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ScheduleAppender scheduleAppender;
    private ScheduleStore scheduleStore;
    private ContactStore contactStore;
    private ScheduleAlerter scheduleAlerter;

    @Override
    public String getName() {
        return "scheduleListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        Schedule schedule = new Schedule();
        schedule.setJobName(jobDetail.getKey().getName());
        schedule.setJobGroup(jobDetail.getKey().getGroup());
        schedule.setJobName(jobDetail.getJobDataMap().getString("jobName"));
        schedule.setJobGroup(jobDetail.getJobDataMap().getString("jobGroup"));
        schedule.setJarPath(jobDetail.getJobDataMap().getString("jarPath"));
        ScheduleHolder.set(schedule);
        logger.info("Schedule 开始: job={}, data={}", jobDetail.getKey().toString(), jobDetail.getJobDataMap().getWrappedMap());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException ex) {
        JobDetail jobDetail = context.getJobDetail();
        logger.info("Schedule 结束: job={}, data={}", jobDetail.getKey().toString(), jobDetail.getJobDataMap().getWrappedMap());

        Schedule schedule = ScheduleHolder.get();
        if (schedule == null)
            return;

        /*
         * 异常时发送邮件通知
         */
        if (ex != null && contactStore != null && scheduleAlerter != null) {
            List<Contact> contacts = contactStore.list(schedule.getJobGroup());
            for (Contact contact : contacts) {
                if (contact.getEmail() == null || contact.getEmail().trim().length() == 0)
                    continue;
                scheduleAlerter.alert(contact.getEmail(), schedule.getJobName(), ex);
            }
        }


        /*
         * 保存运行日志
         */
        if (scheduleAppender != null) {
            if (scheduleStore != null) {
                ScheduleLog scheduleLog = new ScheduleLog();
                scheduleLog.setJobName(schedule.getJobName());
                scheduleLog.setJobLog(scheduleAppender.getLog());
                scheduleStore.saveLog(scheduleLog);
            }
            scheduleAppender.close();
        }
        ScheduleHolder.set(null);
    }

    public void setScheduleAppender(ScheduleAppender scheduleAppender) {
        this.scheduleAppender = scheduleAppender;
    }

    public void setScheduleStore(ScheduleStore scheduleStore) {
        this.scheduleStore = scheduleStore;
    }

    public void setContactStore(ContactStore contactStore) {
        this.contactStore = contactStore;
    }

    public void setScheduleAlerter(ScheduleAlerter scheduleAlerter) {
        this.scheduleAlerter = scheduleAlerter;
    }
}
