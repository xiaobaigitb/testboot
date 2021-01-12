package com.xroad.springbootquartzdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.xroad.springbootquartzdemo.config.JobEntity;
import com.xroad.springbootquartzdemo.jobManage.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : JCccc
 * @CreateTime : 2020/3/21
 * @Description :
 **/
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @PostMapping("/add")
    public String addJob(@RequestBody JobEntity job) {
        Boolean result = quartzService.addJob(job);
        if (!result) {
            return "创建定时任务失败";
        }
        return "创建定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/run")
    public String runJob(@RequestBody JobEntity job) {
        Boolean result = quartzService.runJob(job);
        if (!result) {
            return "启动定时任务失败";
        }
        return "启动定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/update")
    public String updateJob(@RequestBody JobEntity job) {
        Boolean result = quartzService.updateJob(job);
        if (!result) {
            return "修改定时任务失败";
        }
        return "修改定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/pause")
    public String pauseJob(@RequestBody JobEntity job) {
        Boolean result = quartzService.pauseJob(job);
        if (!result) {
            return "暂停定时任务失败";
        }
        return "暂停定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/resume")
    public String resumeJob(@RequestBody JobEntity job) {
        Boolean result = quartzService.resumeJob(job);
        if (!result) {
            return "唤醒定时任务失败";
        }
        return "唤醒定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/delete")
    public String deleteJob(@RequestBody JobEntity job) {
        Boolean result = quartzService.deleteJob(job);
        if (!result) {
            return "删除定时任务失败";
        }
        return "删除定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @GetMapping("/query")
    public String queryJob(@RequestBody JobEntity job) {
        JSONObject result = quartzService.queryJob(job);
        if (null == result) {
            return "不存在对应的任务:"+job.getJobId()+job.getJobName();
        }
        return result.toString();
    }

}