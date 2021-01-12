package com.xroad.springbootquartzdemo.jobManage;

import com.alibaba.fastjson.JSONObject;
import com.xroad.springbootquartzdemo.config.JobEntity;

/**
 * @Author : JCccc
 * @CreateTime : 2020/3/21
 * @Description :
 **/
public interface QuartzService {

    /**
     * 创建Job
     * @param job
     */
    Boolean addJob(JobEntity job);

    /**
     * 执行Job
     * @param job
     */
    Boolean runJob(JobEntity job);

    /**
     * 修改Job
     * @param job
     */
    Boolean updateJob(JobEntity job);

    /**
     * 暂定Job
     * @param job
     */
    Boolean pauseJob(JobEntity job);

    /**
     * 唤醒Job
     * @param job
     */
    Boolean resumeJob(JobEntity job);

    /**
     * 删除Job
     * @param job
     */
    Boolean deleteJob(JobEntity job);

    /**
     * 获取Job
     * @param job
     */
    JSONObject queryJob(JobEntity job);

}
