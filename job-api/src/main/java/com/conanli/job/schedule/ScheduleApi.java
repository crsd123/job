package com.conanli.job.schedule;

import com.conanli.job.common.Response;
import com.conanli.job.schedule.model.ScheduleQueryParams;
import com.conanli.job.schedule.model.ScheduleSaveParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "定时任务")
@RestController
@RequestMapping("/schedule")
public class ScheduleApi {

    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    @Value("${schedule.uploadPath}")
    String uploadPath;
    @Autowired
    ScheduleManager scheduleManager;

    @ApiOperation(value = "启动", response = String.class)
    @PostMapping("/start")
    public Response<Boolean> start(@Valid @RequestBody ScheduleQueryParams params) {
        scheduleManager.start(params.getJobName());
        return Response.success(true);
    }

    @ApiOperation(value = "暂停", response = String.class)
    @PostMapping("/stop")
    public Response<Boolean> stop(@Valid @RequestBody ScheduleQueryParams params) {
        scheduleManager.stop(params.getJobName());
        return Response.success(true);
    }

    @ApiOperation(value = "重启", response = String.class)
    @PostMapping("/restart")
    public Response<Boolean> restart(@Valid @RequestBody ScheduleQueryParams params) {
        scheduleManager.restart(params.getJobName());
        return Response.success(true);
    }

    @ApiOperation(value = "执行一次", response = String.class)
    @PostMapping("/run")
    public Response<Boolean> run(@Valid @RequestBody ScheduleQueryParams params) {
        scheduleManager.run(params.getJobName());
        return Response.success(true);
    }

    @ApiOperation(value = "上传JAR", response = String.class)
    @PostMapping("/uploadJar")
    public Response<String> uploadJar(ScheduleQueryParams params, @RequestParam("file") MultipartFile part) {
        File path = new File(uploadPath);
        if (!path.exists())
            path.mkdirs();
        if (params.getJobName() == null || params.getJobName().trim().length() == 0) {
            params.setJobName(part.getOriginalFilename().replaceAll("(-\\d\\.\\d\\.\\d)|(\\.jar)", ""));
        }
        File file = new File(path, String.format("%s-%s.jar", params.getJobName(), df.format(new Date())));
        try {
            part.transferTo(file);
            return Response.success(file.getAbsolutePath().replaceAll("\\\\", "/"));
        } catch (IOException e) {
            return Response.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "保存", response = String.class)
    @PostMapping("/save")
    public Response<Boolean> save(@RequestBody ScheduleSaveParams params) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(params, schedule);
        scheduleManager.save(schedule);
        return Response.success(true);
    }

    @ApiOperation(value = "删除", response = String.class)
    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody ScheduleQueryParams params) {
        scheduleManager.delete(params.getJobName());
        return Response.success(true);
    }

    @ApiOperation(value = "列表", response = List.class)
    @PostMapping("/list")
    public Response<List<Schedule>> list() {
        List<Schedule> schedules = scheduleManager.list();
        return Response.success(schedules);
    }

    @ApiOperation(value = "任务名列表", response = List.class)
    @PostMapping("/listName")
    public Response<List<String>> listName() {
        List<String> jobNames = scheduleManager.listName();
        return Response.success(jobNames);
    }

    @ApiOperation(value = "执行记录列表", response = List.class)
    @PostMapping("/listLog")
    public Response<List<ScheduleLog>> listLog(@RequestBody ScheduleQueryParams params) {
        List<ScheduleLog> scheduleLogs = scheduleManager.listLog(params.getJobName());
        return Response.success(scheduleLogs);
    }
}
