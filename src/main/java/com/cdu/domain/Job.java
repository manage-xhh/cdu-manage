package com.cdu.domain;

import java.util.Date;

public class Job {
    private Byte jobId;

    private String jobName;

    private Integer jobCount;

    private Byte regionId;

    private Byte jobTypeId;

    private Date createTime;

    private String content;

    public Byte getJobId() {
        return jobId;
    }

    public void setJobId(Byte jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Byte getRegionId() {
        return regionId;
    }

    public void setRegionId(Byte regionId) {
        this.regionId = regionId;
    }

    public Byte getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Byte jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}