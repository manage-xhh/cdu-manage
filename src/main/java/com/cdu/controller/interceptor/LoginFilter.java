package com.cdu.controller.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdu.domain.Customer;

public class LoginFilter  extends HttpServlet implements Filter{

    private static final long serialVersionUID = 2682919169022554182L;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response  =(HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        if (url.indexOf("template") > 0) {
            Customer cus = (Customer) session.getAttribute("user");
            if (cus != null) {
                filter.doFilter(req, resp);
                return ;
            } else {
                response.sendRedirect("/index.jsp");           
                return ;
            }
        } else {
            filter.doFilter(req, resp);
        }  
    }

    public void init(FilterConfig arg0) throws ServletException {      
    }

}
