package com.conanli.job.schedule.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class ScheduleQueryParams {

    @NotNull(message = "任务名不能为空")
    @ApiModelProperty(value = "任务名", example = "")
    private String jobName;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
