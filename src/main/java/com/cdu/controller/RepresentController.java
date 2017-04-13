package com.cdu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Represent;
import com.cdu.domain.vo.RepresentVo;
import com.cdu.service.RepresentService;

@Controller
public class RepresentController {
    
    @Autowired
    RepresentService representService;
    
    @RequestMapping(value = "representList.do" , method = RequestMethod.GET)
    @ResponseBody
    public void getAll(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<RepresentVo> page = representService.getAll(pageCondition);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "addRepresent.do")
    public ModelAndView addRepresent(@RequestParam("upload") MultipartFile file , Represent represent , HttpServletRequest request){
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName == null || "".equals(fileName)) {
                represent.setImage("/template/images/head_background.jpg");
                representService.addRepresent(represent);
                return new ModelAndView("template/pages/driver/represent.html");
            }
            String type = fileName.substring(fileName.indexOf(".") + 1);
            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                String realPath=request.getSession().getServletContext().getRealPath("/");
                String realFileName=String.valueOf(System.currentTimeMillis())+fileName;
                String path = realPath + "template/upload/" + realFileName;
                represent.setImage("/template/upload/" + realFileName);
                representService.addRepresent(represent);
                try {
                    file.transferTo(new File(path));
                    return new ModelAndView("template/pages/driver/represent.html");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
        return new ModelAndView("template/pages/driver/represent.html");
    }
    
    @RequestMapping(value = "deleteRepresent.do" , method = RequestMethod.GET)
    @ResponseBody
    public String deleteRepresent(@RequestParam("id") Integer id) {  
        Integer result = representService.deleteRepresent(id);        
        return result.toString();
    }
    
    @RequestMapping(value = "editRepresent.do" , method = RequestMethod.GET)
    @ResponseBody
    public void editRepresent(@RequestParam("repId") Integer repId , HttpServletResponse response) throws IOException {  
        Represent represent = representService.selectById(repId);        
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(represent));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "updateRepresent.do")
    public ModelAndView updateRepresent(@RequestParam("upload") MultipartFile file , Represent represent , HttpServletRequest request){
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                String type = fileName.substring(fileName.indexOf(".") + 1);
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String realPath=request.getSession().getServletContext().getRealPath("/");
                    String realFileName=String.valueOf(System.currentTimeMillis())+fileName;
                    String path = realPath + "template/upload/" + realFileName;
                    represent.setImage("/template/upload/" + realFileName);
                    try {
                        file.transferTo(new File(path));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                }
            }      
        }
        representService.updateRepresent(represent);
        return new ModelAndView("template/pages/driver/represent.html");
    }
}
