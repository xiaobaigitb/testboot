package com.xroad.springbootquartzdemo.config;



import com.alibaba.fastjson.JSONObject;
import java.util.Date;

/**
 * @Author :
 * @CreateTime : 2020/3/21
 * @Description : 定时任务用到的参数
 **/

public class JobEntity  {
    private String jobId; //唯一id
    private String className; //定时任务示例的 class路径
    private String cronExpression; //cron表达式
    private String jobName; //定时任务名称
    private String jobGroup; //所属组
    private String triggerName; //触发器名称
    private String triggerGroup; //触发器组
    private String description; //备注
    private JSONObject data; //携带参数

    /**
     * 预留的数据库字段 如果任务信息选择手动自己存入数据库的话,会使用到
     */
    private Boolean pauseStatus;  //是否暂停
    private Boolean deleteStatus; //是否有效
    private Date createTime; //创建时间
    private Date updateTime; //更新时间


    @Override
    public String toString() {
        return "JobEntity{" +
                "jobId='" + jobId + '\'' +
                ", className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", triggerGroup='" + triggerGroup + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                ", pauseStatus=" + pauseStatus +
                ", deleteStatus=" + deleteStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

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

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public Boolean getPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(Boolean pauseStatus) {
        this.pauseStatus = pauseStatus;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
