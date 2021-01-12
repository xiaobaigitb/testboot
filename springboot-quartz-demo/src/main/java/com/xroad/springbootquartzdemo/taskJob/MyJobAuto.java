package com.xroad.springbootquartzdemo.taskJob;

/**
 * @Author : JCccc
 * @CreateTime : 2020/3/21
 * @Description :
 **/

import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MyJobAuto implements Job {
    private static Logger log = LoggerFactory.getLogger(MyJobAuto.class);
    private void before() {

        log.info("******MyJobAuto任务开始执行******");
    }
    @Override
    public void execute(JobExecutionContext context) {
        before();
        //定时任务处理的业务逻辑
        //...
        //...

        String name = context.getJobDetail().getKey().getName();
        log.info("******MyJobAuto任务[{}]正在执行******",name);
        JobDataMap jobDataMap=context.getJobDetail().getJobDataMap();
        JSONObject jsonObject = (JSONObject) jobDataMap.get("myValue");
        log.info("MyJobAuto任务[{}]携带的参数[{}]",name,jsonObject.toString());

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("当前时间[{}],MyJobAuto任务[{}]的线程名[{}]",time,name,Thread.currentThread().getName());
        after();
    }

    private void after() {

        log.info("******MyJobAuto任务执行结束******");
    }

}
