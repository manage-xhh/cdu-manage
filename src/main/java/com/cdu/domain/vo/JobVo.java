package com.cdu.domain.vo;

import java.util.Date;

public class JobVo {
    private Byte jobId;
    private String jobName;
    private Integer jobCount;
    private String region;
    private String type;
    private Byte regionId;
    private Byte typeId;
    private String createTime;
    private Date time;
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
        this.jobName = jobName;
    }
    public Integer getJobCount() {
        return jobCount;
    }
    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Byte getRegionId() {
        return regionId;
    }
    public void setRegionId(Byte regionId) {
        this.regionId = regionId;
    }
    public Byte getTypeId() {
        return typeId;
    }
    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }
    
}
