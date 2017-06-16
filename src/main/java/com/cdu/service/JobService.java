package com.cdu.service;

import java.util.List;
import java.util.Map;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Job;
import com.cdu.domain.JobType;
import com.cdu.domain.Region;
import com.cdu.domain.vo.JobVo;

public interface JobService {
    
    Page<JobVo> selectAll(PageCondition pageCondition);
    
    void addRegion(Region region);
    
    List<Region> region();
    
    void addType(JobType type);
    
    List<JobType> type();
    
    Map<String , Object> addJobInit();
    
    void insertJob(Job job);
    
    int delete(Integer id);
    
    JobVo selectById(Integer id);
    
    void updateJob(Job job);
}
