package com.xroad.springbootquartzdemo.listener;

import com.alibaba.fastjson.JSONObject;
import com.xroad.springbootquartzdemo.config.JobEntity;
import com.xroad.springbootquartzdemo.jobManage.QuartzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.UUID;


/**
 * @Author : JCccc
 * @CreateTime : 2020/3/21
 * @Description :
 **/

@Configuration
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger log = LoggerFactory.getLogger(ApplicationStartListener.class);
    @Autowired
    private QuartzService quartzService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        JobEntity job=new JobEntity();

        job.setJobId(UUID.randomUUID().toString().replaceAll("-",""));

        job.setClassName("com.bsapple.vshop.quartz.taskJob.MyJobAuto");//注意,这里的路径请改成你自己的
        job.setCronExpression("0/3 * * * * ?");
        job.setJobName("AutoJob");
        job.setJobGroup("AutoJobGroup");
        job.setTriggerName("AutoJobTrigger");
        job.setTriggerGroup("AutoJobTriggerGroup");
        job.setDescription("AutoJob-随项目启动");
        //可以将任务跟数据库挂钩,做个任务管理模块,获取需要自启动的任务,记录各个参数等
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("testKey","测试即启动");
        job.setData(jsonObject);
        quartzService.addJob(job);
        log.info("即触发定时任务已经开始执行.. .");
        log.info("************application已经启动完毕************");
    }


}
