package com.conanli.job.schedule.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
public class ScheduleSaveParams {

    @NotNull(message = "任务名不能为空")
    @ApiModelProperty(value = "任务名", example = "")
    private String jobName;
    @ApiModelProperty(value = "任务组", example = "DEFAULT")
    private String jobGroup;
    @ApiModelProperty(value = "实现类名", example = "com.conanli.job.job.Main")
    private String jobClassName;
    @ApiModelProperty(value = "任务参数 p1=a;p2=b", example = "")
    private String jobParams;
    @ApiModelProperty(value = "触发器类型 SIMPLE CRON", example = "CRON")
    private String triggerType;
    @ApiModelProperty(value = "重复执行次数", example = "0")
    private Integer triggerRepeat;
    @ApiModelProperty(value = "重复执行间隔（毫秒）", example = "1000")
    private Long triggerInterval;
    @ApiModelProperty(value = "CRON 表达式", example = "0 */1 * * * ?")
    private String triggerCron;
    @ApiModelProperty(value = "触发器开始时间", example = "1504195200000")
    private Date triggerStartTime;
    @ApiModelProperty(value = "触发器结束时间", example = "1504281599000")
    private Date triggerStopTime;
    @ApiModelProperty(value = "JAR包文件名，包括目录", example = "d:/example.jar")
    private String jarPath;
    @ApiModelProperty(value = "0 禁用 1 启动", example = "0")
    private String isEnable;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobParams() {
        return jobParams;
    }

    public void setJobParams(String jobParams) {
        this.jobParams = jobParams;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Integer getTriggerRepeat() {
        return triggerRepeat;
    }

    public void setTriggerRepeat(Integer triggerRepeat) {
        this.triggerRepeat = triggerRepeat;
    }

    public Long getTriggerInterval() {
        return triggerInterval;
    }

    public void setTriggerInterval(Long triggerInterval) {
        this.triggerInterval = triggerInterval;
    }

    public String getTriggerCron() {
        return triggerCron;
    }

    public void setTriggerCron(String triggerCron) {
        this.triggerCron = triggerCron;
    }

    public Date getTriggerStartTime() {
        return triggerStartTime;
    }

    public void setTriggerStartTime(Date triggerStartTime) {
        this.triggerStartTime = triggerStartTime;
    }

    public Date getTriggerStopTime() {
        return triggerStopTime;
    }

    public void setTriggerStopTime(Date triggerStopTime) {
        this.triggerStopTime = triggerStopTime;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}
