package com.cdu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Driver;
import com.cdu.domain.vo.DriverVo;
import com.cdu.service.DriverService;

@Controller
public class DriverController {
    
    @Autowired
    DriverService driverService;
    
    @RequestMapping(value = "driverRegister.do")
    @ResponseBody
    public String driverRegister(Driver driver , HttpServletResponse  response){
        String message = driverService.insertWaitAuditDriver(driver);
        return message;
    }
    
    @RequestMapping(value = "waitAuditList.do" , method = RequestMethod.GET)
    @ResponseBody
    public void driverWaitAuditList(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<DriverVo> page = driverService.driverWaitAuditList(pageCondition);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "accept.do")
    @ResponseBody
    public String accept(Driver driver){
        return driverService.updateDriverStatus(driver) + "";
    }
    
    @RequestMapping(value = "authList.do" , method = RequestMethod.GET)
    @ResponseBody
    public void authList(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<DriverVo> page = driverService.authList(pageCondition);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "delete.do")
    @ResponseBody
    public String delete(@RequestParam("driverId") Integer driverId){
        return driverService.deleteDriver(driverId);
    }
}
