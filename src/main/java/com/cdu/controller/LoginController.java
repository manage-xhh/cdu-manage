package com.cdu.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdu.domain.Customer;
import com.cdu.service.CustomerService;

@Controller
public class LoginController {
    
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping(value = "login.do" , method = RequestMethod.POST)
    public ModelAndView login(Customer customer , HttpServletRequest request) {        
        Customer cus = customerService.login(customer);
        if (cus != null) {
            cus.setLastLoginTime(new Date());
            customerService.updateByCustomer(cus);
            HttpSession session = request.getSession();
            session.setAttribute("user", cus);
            ModelAndView model = new ModelAndView("redirect:template/pages/main.html");            
            return model;
        } else {
            ModelAndView model = new ModelAndView("index.jsp");
            model.addObject("message", "帐号或密码错误!");
            return model;
        }
    }
    
    @RequestMapping(value = "loginOut.do" , method = RequestMethod.GET)
    public ModelAndView loginOut(HttpServletRequest request) {        
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        session.invalidate();
        ModelAndView model = new ModelAndView("redirect:index.jsp");
        return model;
    }
}
