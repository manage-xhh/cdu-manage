package com.cdu.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdu.common.FormatUtils;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Job;
import com.cdu.domain.JobType;
import com.cdu.domain.Region;
import com.cdu.domain.vo.JobVo;
import com.cdu.mapper.JobMapper;
import com.cdu.service.JobService;

@Service
public class JobServiceImpl implements JobService{
    
    @Autowired
    JobMapper jobMapper;
    
    public void addRegion(Region region) {
        jobMapper.insertRegion(region);
    }

    public void addType(JobType type) {
        jobMapper.insertType(type);
    }

    public Map<String, Object> addJobInit() {
        Map<String, Object> map = new HashMap<String , Object>();
        List<Region> regions = jobMapper.selectRegion();
        List<JobType> type = jobMapper.selectType();
        map.put("region", regions);
        map.put("type", type);
        return map;
    }

    public void insertJob(Job job) {
        job.setCreateTime(new Date());
        jobMapper.insert(job);
    }

    public Page<JobVo> selectAll(PageCondition pageCondition) {    
        pageCondition.setPage(pageCondition.getPageNo() , pageCondition.getPageSize());
        List<JobVo> list = jobMapper.selectAll(pageCondition);
        for (JobVo vo : list) {
            vo.setCreateTime(FormatUtils.simpleFormate(vo.getTime()));
        }
        Page<JobVo> page = new Page<JobVo>();
        page.setTotalSize(jobMapper.countByExample(pageCondition));
        page.setPageNo(pageCondition.getPageNo());
        page.setPageSize(pageCondition.getPageSize());
        page.setList(list);
        return page;
    }

    public int delete(Integer id) {
        return jobMapper.deleteByPrimaryKey(id.byteValue());
    }

    public JobVo selectById(Integer id) {
        JobVo vo = jobMapper.selectById(id.byteValue());
        if (vo != null) {
            vo.setCreateTime(FormatUtils.dateToString(vo.getTime()));
        }
        return vo;
    }

    public void updateJob(Job job) {
        jobMapper.updateByPrimaryKeySelective(job);
    }

    public List<Region> region() {
        return jobMapper.selectRegion();
    }

    public List<JobType> type() {
        return jobMapper.selectType();
    }
}
