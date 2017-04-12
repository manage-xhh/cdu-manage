package com.cdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cdu.domain.Customer;
import com.cdu.service.CustomerService;

@Controller
public class ResetPasswordController {
    
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping(value = "getLoginCustomer.do" , method = RequestMethod.GET)
    @ResponseBody
    public void getLoginCustomer(HttpServletResponse reponse , HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession(false);
        Customer loginer = (Customer) session.getAttribute("user");
        PrintWriter out = reponse.getWriter();
        out.println(JSON.toJSONString(loginer));
        out.flush();
        out.close();
    }
    

    @RequestMapping(value = "resetLoginPassword.do" , method = RequestMethod.POST)
    @ResponseBody
    public void resetLoginPassword(@RequestParam("pwd") String pwd , HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Customer loginer = (Customer) session.getAttribute("user");
        loginer.setPassword(pwd);
        customerService.updateByCustomer(loginer);
    }
    
    @RequestMapping(value = "confirmPwd.do" , method = RequestMethod.POST)
    @ResponseBody
    public String confirmPwd(@RequestParam String oldPwd , @RequestParam("pwd") String pwd){
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if (pwd.equals(oldPwd)) {
            map.put("valid" , true);
        } else {
            map.put("valid" , false);
        }      
        return JSON.toJSONString(map);
    }
}
