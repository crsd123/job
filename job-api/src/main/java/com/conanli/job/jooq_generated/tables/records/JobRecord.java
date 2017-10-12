/**
 * This class is generated by jOOQ
 */
package com.conanli.job.jooq_generated.tables.records;


import com.conanli.job.jooq_generated.tables.Job;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 定时任务
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JobRecord extends UpdatableRecordImpl<JobRecord> implements Record12<String, String, String, String, String, Integer, Integer, String, Timestamp, Timestamp, String, String> {

    private static final long serialVersionUID = -1498969481;

    /**
     * Setter for <code>job.JOB_NAME</code>. 任务名
     */
    public void setJobName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>job.JOB_NAME</code>. 任务名
     */
    public String getJobName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>job.JOB_GROUP</code>. 任务组
     */
    public void setJobGroup(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>job.JOB_GROUP</code>. 任务组
     */
    public String getJobGroup() {
        return (String) get(1);
    }

    /**
     * Setter for <code>job.JOB_CLASS_NAME</code>. 任务实现类
     */
    public void setJobClassName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>job.JOB_CLASS_NAME</code>. 任务实现类
     */
    public String getJobClassName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>job.JOB_PARAMS</code>. 任务参数
     */
    public void setJobParams(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>job.JOB_PARAMS</code>. 任务参数
     */
    public String getJobParams() {
        return (String) get(3);
    }

    /**
     * Setter for <code>job.TRIGGER_TYPE</code>. 触发器类型 SIMPLE 普通 CRON 表达式
     */
    public void setTriggerType(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>job.TRIGGER_TYPE</code>. 触发器类型 SIMPLE 普通 CRON 表达式
     */
    public String getTriggerType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>job.TRIGGER_REPEAT</code>. 重复执行次数
     */
    public void setTriggerRepeat(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>job.TRIGGER_REPEAT</code>. 重复执行次数
     */
    public Integer getTriggerRepeat() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>job.TRIGGER_INTERVAL</code>. 重复执行间隔（毫秒）
     */
    public void setTriggerInterval(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>job.TRIGGER_INTERVAL</code>. 重复执行间隔（毫秒）
     */
    public Integer getTriggerInterval() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>job.TRIGGER_CRON</code>. CRON 表达式
     */
    public void setTriggerCron(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>job.TRIGGER_CRON</code>. CRON 表达式
     */
    public String getTriggerCron() {
        return (String) get(7);
    }

    /**
     * Setter for <code>job.TRIGGER_START_TIME</code>. 触发器开始时间
     */
    public void setTriggerStartTime(Timestamp value) {
        set(8, value);
    }

    /**
     * Getter for <code>job.TRIGGER_START_TIME</code>. 触发器开始时间
     */
    public Timestamp getTriggerStartTime() {
        return (Timestamp) get(8);
    }

    /**
     * Setter for <code>job.TRIGGER_STOP_TIME</code>. 触发器结束时间
     */
    public void setTriggerStopTime(Timestamp value) {
        set(9, value);
    }

    /**
     * Getter for <code>job.TRIGGER_STOP_TIME</code>. 触发器结束时间
     */
    public Timestamp getTriggerStopTime() {
        return (Timestamp) get(9);
    }

    /**
     * Setter for <code>job.JAR_PATH</code>. JAR包文件名，包括目录
     */
    public void setJarPath(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>job.JAR_PATH</code>. JAR包文件名，包括目录
     */
    public String getJarPath() {
        return (String) get(10);
    }

    /**
     * Setter for <code>job.IS_ENABLE</code>. 0 禁用 1 启动
     */
    public void setIsEnable(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>job.IS_ENABLE</code>. 0 禁用 1 启动
     */
    public String getIsEnable() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, String, String, String, Integer, Integer, String, Timestamp, Timestamp, String, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, String, String, String, Integer, Integer, String, Timestamp, Timestamp, String, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Job.JOB.JOB_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Job.JOB.JOB_GROUP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Job.JOB.JOB_CLASS_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Job.JOB.JOB_PARAMS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Job.JOB.TRIGGER_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Job.JOB.TRIGGER_REPEAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Job.JOB.TRIGGER_INTERVAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Job.JOB.TRIGGER_CRON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field9() {
        return Job.JOB.TRIGGER_START_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field10() {
        return Job.JOB.TRIGGER_STOP_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Job.JOB.JAR_PATH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Job.JOB.IS_ENABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getJobName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getJobGroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getJobClassName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getJobParams();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getTriggerType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getTriggerRepeat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getTriggerInterval();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getTriggerCron();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value9() {
        return getTriggerStartTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value10() {
        return getTriggerStopTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getJarPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getIsEnable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value1(String value) {
        setJobName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value2(String value) {
        setJobGroup(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value3(String value) {
        setJobClassName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value4(String value) {
        setJobParams(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value5(String value) {
        setTriggerType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value6(Integer value) {
        setTriggerRepeat(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value7(Integer value) {
        setTriggerInterval(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value8(String value) {
        setTriggerCron(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value9(Timestamp value) {
        setTriggerStartTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value10(Timestamp value) {
        setTriggerStopTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value11(String value) {
        setJarPath(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value12(String value) {
        setIsEnable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord values(String value1, String value2, String value3, String value4, String value5, Integer value6, Integer value7, String value8, Timestamp value9, Timestamp value10, String value11, String value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JobRecord
     */
    public JobRecord() {
        super(Job.JOB);
    }

    /**
     * Create a detached, initialised JobRecord
     */
    public JobRecord(String jobName, String jobGroup, String jobClassName, String jobParams, String triggerType, Integer triggerRepeat, Integer triggerInterval, String triggerCron, Timestamp triggerStartTime, Timestamp triggerStopTime, String jarPath, String isEnable) {
        super(Job.JOB);

        set(0, jobName);
        set(1, jobGroup);
        set(2, jobClassName);
        set(3, jobParams);
        set(4, triggerType);
        set(5, triggerRepeat);
        set(6, triggerInterval);
        set(7, triggerCron);
        set(8, triggerStartTime);
        set(9, triggerStopTime);
        set(10, jarPath);
        set(11, isEnable);
    }
}
