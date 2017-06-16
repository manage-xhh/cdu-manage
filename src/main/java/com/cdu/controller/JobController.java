package com.cdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Job;
import com.cdu.domain.JobType;
import com.cdu.domain.Region;
import com.cdu.domain.vo.JobVo;
import com.cdu.service.JobService;

@Controller
public class JobController {
    
    @Autowired
    JobService jobService;
    
    @RequestMapping(value = "jobList.do")
    @ResponseBody
    public void getAll(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<JobVo> page = jobService.selectAll(pageCondition);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "addRegion.do")
    @ResponseBody
    public String addRegion(Region region){
        jobService.addRegion(region);
        return null;
    }
    
    @RequestMapping(value = "regionList.do")
    @ResponseBody
    public void region(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(jobService.region()));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "addType.do")
    @ResponseBody
    public String addType(JobType type){
        jobService.addType(type);
        return null;
    }
    
    @RequestMapping(value = "typeList.do")
    @ResponseBody
    public void type(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(jobService.type()));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "addJobInit.do")
    @ResponseBody
    public void addJobInit(HttpServletResponse response) throws IOException{
        Map<String , Object> map = jobService.addJobInit();
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "addJob.do")
    public ModelAndView addJob(Job job , String content){
        jobService.insertJob(job);
        ModelAndView model = new ModelAndView("/template/pages/job/job.html");
        return model;
    }
    
    @RequestMapping(value = "deleteJob.do" , method = RequestMethod.GET)
    @ResponseBody
    public String deleteJob(@RequestParam("id") Integer id) {  
        Integer result = jobService.delete(id);        
        return result.toString();
    }
    
    @RequestMapping(value = "getJob.do" , method = RequestMethod.GET)
    @ResponseBody
    public void getJob(@RequestParam("id") Integer id , HttpServletResponse response) throws IOException {  
        JobVo vo = jobService.selectById(id);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(vo));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "updateJob.do")
    public ModelAndView updateJob(Job job) {
        ModelAndView model = new ModelAndView("/template/pages/job/job.html");
        jobService.updateJob(job);
        return model; 
    }
}
