package com.cdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
import com.cdu.domain.Customer;
import com.cdu.domain.vo.CustomerVo;
import com.cdu.service.CustomerService;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "userList.do" , method = RequestMethod.GET)
    @ResponseBody
    public void getAll(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<CustomerVo> page = customerService.getAll(pageCondition);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "confirmAccount.do" , method = RequestMethod.POST)
    @ResponseBody
    public String confirmAccount(@RequestParam String account){
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        Customer cus = customerService.getByAccount(account);
        if (cus != null) {
            map.put("valid" , false);
        } else {
            map.put("valid" , true);
        }        
        return JSON.toJSONString(map);
    }
    
    @RequestMapping(value = "addCustomer.do" , method = RequestMethod.POST)
    @ResponseBody
    public String addCustomer(Customer customer){
        return JSON.toJSONString(customerService.addCustomer(customer));
    }
    
    @RequestMapping(value = "deleteCustomer.do" , method = RequestMethod.GET)
    @ResponseBody
    public void deleteCustomer(@RequestParam("id") Integer id , HttpServletResponse response) throws IOException{
        customerService.deleteCustomer(id);
        PrintWriter out = response.getWriter();
        out.println(true);
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "editCustomer.do" , method = RequestMethod.GET)
    @ResponseBody
    public void editCustomer(@RequestParam("customerId") Integer customerId , HttpServletResponse response) throws IOException{
        Customer cus = customerService.getByCostomerId(customerId);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(cus));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "updateCustomer.do" , method = RequestMethod.POST)
    @ResponseBody
    public void updateCustomer(Customer customer){
        customerService.updateByCustomer(customer);
    }
}
