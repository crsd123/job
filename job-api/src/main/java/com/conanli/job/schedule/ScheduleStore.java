package com.conanli.job.schedule;

import com.conanli.job.jooq_generated.tables.Job;
import com.conanli.job.jooq_generated.tables.JobHistory;
import com.conanli.job.jooq_generated.tables.records.JobHistoryRecord;
import com.conanli.job.jooq_generated.tables.records.JobRecord;
import org.jooq.DSLContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleStore {

    private DSLContext dsl;

    public ScheduleStore(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void save(Schedule schedule) {
        Job JOB = Job.JOB;

        JobRecord job = dsl.newRecord(JOB);
        job.setJobName(schedule.getJobName());
        if (isNotBlank(schedule.getJobGroup()))
            job.setJobGroup(schedule.getJobGroup());
        if (isNotBlank(schedule.getJobClassName()))
            job.setJobClassName(schedule.getJobClassName());
        if (isNotBlank(schedule.getJobParams()))
            job.setJobParams(schedule.getJobParams());
        if (isNotBlank(schedule.getTriggerType()))
            job.setTriggerType(schedule.getTriggerType());
        if (schedule.getTriggerRepeat() != null)
            job.setTriggerRepeat(schedule.getTriggerRepeat());
        if (schedule.getTriggerInterval() != null)
            job.setTriggerInterval(schedule.getTriggerInterval().intValue());
        if (isNotBlank(schedule.getTriggerCron()))
            job.setTriggerCron(schedule.getTriggerCron());
        if (schedule.getTriggerStartTime() != null)
            job.setTriggerStartTime(new Timestamp(schedule.getTriggerStartTime().getTime()));
        if (schedule.getTriggerStopTime() != null)
            job.setTriggerStopTime(new Timestamp(schedule.getTriggerStopTime().getTime()));
        if (isNotBlank(schedule.getJarPath()))
            job.setJarPath(schedule.getJarPath());
        if (isNotBlank(schedule.getIsEnable()))
            job.setIsEnable(schedule.getIsEnable());

        dsl.insertInto(JOB)
                .set(job)
                .onDuplicateKeyUpdate()
                .set(job)
                .execute();
    }

    public void delete(String jobName) {
        Job JOB = Job.JOB;
        JobHistory JOB_HISTORY = JobHistory.JOB_HISTORY;

        dsl.deleteFrom(JOB)
                .where(JOB.JOB_NAME.eq(jobName))
                .execute();
        dsl.deleteFrom(JOB_HISTORY)
                .where(JOB_HISTORY.JOB_NAME.eq(jobName))
                .execute();
    }

    public List<Schedule> list() {
        Job JOB = Job.JOB;

        List<JobRecord> jobs = dsl.select()
                .from(JOB)
                .fetchInto(JOB);

        return jobs.stream()
                .map(job -> {
                    Schedule schedule = new Schedule();
                    schedule.setJobName(job.getJobName());
                    schedule.setJobGroup(job.getJobGroup());
                    schedule.setJobClassName(job.getJobClassName());
                    schedule.setJobParams(job.getJobParams());
                    schedule.setTriggerType(job.getTriggerType());
                    schedule.setTriggerRepeat(job.getTriggerRepeat());
                    schedule.setTriggerInterval(job.getTriggerInterval() != null ? job.getTriggerInterval().longValue() : 0L);
                    schedule.setTriggerCron(job.getTriggerCron());
                    schedule.setTriggerStartTime(job.getTriggerStartTime());
                    schedule.setTriggerStopTime(job.getTriggerStopTime());
                    schedule.setJarPath(job.getJarPath());
                    schedule.setIsEnable(job.getIsEnable());
                    return schedule;
                }).collect(Collectors.toList());
    }

    public List<String> listName() {
        Job JOB = Job.JOB;

        return dsl.select(JOB.JOB_NAME)
                .from(JOB)
                .fetch(JOB.JOB_NAME);
    }

    public Schedule get(String jobName) {
        Job JOB = Job.JOB;

        JobRecord job = dsl.select()
                .from(JOB)
                .where(JOB.JOB_NAME.eq(jobName))
                .fetchOneInto(JOB);

        if (job == null)
            return null;

        Schedule schedule = new Schedule();
        schedule.setJobName(job.getJobName());
        schedule.setJobGroup(job.getJobGroup());
        schedule.setJobClassName(job.getJobClassName());
        schedule.setJobParams(job.getJobParams());
        schedule.setTriggerType(job.getTriggerType());
        schedule.setTriggerRepeat(job.getTriggerRepeat());
        schedule.setTriggerInterval(job.getTriggerInterval() != null ? job.getTriggerInterval().longValue() : 0L);
        schedule.setTriggerCron(job.getTriggerCron());
        schedule.setTriggerStartTime(job.getTriggerStartTime());
        schedule.setTriggerStopTime(job.getTriggerStopTime());
        schedule.setJarPath(job.getJarPath());
        schedule.setIsEnable(job.getIsEnable());
        return schedule;
    }

    public void saveLog(ScheduleLog scheduleLog) {
        JobHistory JOB_HISTORY = JobHistory.JOB_HISTORY;

        JobHistoryRecord jobHistory = dsl.newRecord(JOB_HISTORY);
        jobHistory.setJobName(scheduleLog.getJobName());
        if (isNotBlank(scheduleLog.getJobLog()))
            jobHistory.setJobLog(scheduleLog.getJobLog());

        dsl.insertInto(JOB_HISTORY)
                .set(jobHistory)
                .onDuplicateKeyUpdate()
                .set(jobHistory)
                .execute();
    }

    public List<ScheduleLog> listLog(String jobName) {
        JobHistory JOB_HISTORY = JobHistory.JOB_HISTORY;

        List<JobHistoryRecord> jobHistories = new ArrayList<>();

        if (jobName != null && jobName.trim().length() > 0) {
            jobHistories = dsl.select(JOB_HISTORY.JOB_NAME, JOB_HISTORY.JOB_LOG, JOB_HISTORY.CREATE_DATE)
                    .from(JOB_HISTORY)
                    .where(JOB_HISTORY.JOB_NAME.eq(jobName))
                    .orderBy(JOB_HISTORY.CREATE_DATE.desc())
                    .limit(10)
                    .fetchInto(JOB_HISTORY);
        } else {
            jobHistories = dsl.select(JOB_HISTORY.JOB_NAME, JOB_HISTORY.JOB_LOG, JOB_HISTORY.CREATE_DATE)
                    .from(JOB_HISTORY)
                    .orderBy(JOB_HISTORY.CREATE_DATE.desc())
                    .limit(10)
                    .fetchInto(JOB_HISTORY);
        }

        return jobHistories.stream().map(jobHistory -> {
            ScheduleLog scheduleLog = new ScheduleLog();
            scheduleLog.setJobName(jobHistory.getJobName());
            scheduleLog.setJobLog(jobHistory.getJobLog());
            scheduleLog.setCreateDate(jobHistory.getCreateDate());
            return scheduleLog;
        }).collect(Collectors.toList());
    }

    private boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }
}
