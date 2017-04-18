package com.cdu.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        
    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        /*HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("user");
        String url = request.getRequestURI();
        if (url.indexOf("driver") < 0) {
            if (url.indexOf("login.do") < 0) {
                if (cus != null) {
                    return true;
                } else {
                    response.sendRedirect("/index.jsp");
                    return false;
                }
            } else {
                return true;
            }     
        } else {
            return true;
        }*/
        return true;
    }
}
