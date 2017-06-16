package com.cdu.mapper;

import com.cdu.common.PageCondition;
import com.cdu.domain.Job;
import com.cdu.domain.JobExample;
import com.cdu.domain.JobType;
import com.cdu.domain.Region;
import com.cdu.domain.vo.JobVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {
    int countByExample(PageCondition pageCondition);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Byte jobId);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(Byte jobId);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
    
    void insertRegion(Region region);
    
    void insertType(JobType type);
    
    List<Region> selectRegion();
    
    List<JobType> selectType();
    
    List<JobVo> selectAll(PageCondition pageCondition);
    
    JobVo selectById(Byte jobId);
}