package com.cdu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.cdu.domain.HomePicture;
import com.cdu.domain.vo.HomePictureVo;
import com.cdu.service.HomePictureService;

@Controller
public class HomePictureController {
    
    @Autowired
    private HomePictureService service;
    
    @RequestMapping(value = "homePictureList.do" , method = RequestMethod.GET)
    @ResponseBody
    public void getAll(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<HomePictureVo> page = service.selectAll(pageCondition);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "addHomePicture.do")
    public ModelAndView addHomePicture(@RequestParam("upload") MultipartFile file , HomePicture homePicture , HttpServletRequest request){
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName == null || "".equals(fileName)) {
                homePicture.setPath("/template/images/head_background.jpg");
                service.insert(homePicture);
                return new ModelAndView("template/pages/home_picture/home_picture.html");
            }
            String type = fileName.substring(fileName.indexOf(".") + 1);
            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                String realPath=request.getSession().getServletContext().getRealPath("/");
                String realFileName=String.valueOf(System.currentTimeMillis())+fileName;
                String path = realPath + "template/upload/" + realFileName;
                homePicture.setPath("/template/upload/" + realFileName);
                service.insert(homePicture);
                try {
                    file.transferTo(new File(path));
                    return new ModelAndView("template/pages/home_picture/home_picture.html");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
        return new ModelAndView("template/pages/home_picture/home_picture.html");
    }
    
    @RequestMapping(value = "deletePicture.do" , method = RequestMethod.GET)
    @ResponseBody
    public String deletePicture(@RequestParam("id") Integer id) {  
        Integer result = service.deletePicture(id.byteValue());        
        return result.toString();
    }
        
    @RequestMapping(value = "editPicture.do" , method = RequestMethod.GET)
    @ResponseBody
    public void editPicture(@RequestParam("id") Integer id , HttpServletResponse response) throws IOException {  
        HomePicture homePicture = service.selectById(id);        
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(homePicture));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "updatePicture.do")
    public ModelAndView updatePicture(@RequestParam("upload") MultipartFile file , HomePicture homePicture , HttpServletRequest request){
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                String type = fileName.substring(fileName.indexOf(".") + 1);
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String realPath=request.getSession().getServletContext().getRealPath("/");
                    String realFileName=String.valueOf(System.currentTimeMillis())+fileName;
                    String path = realPath + "template/upload/" + realFileName;
                    homePicture.setPath("/template/upload/" + realFileName);
                    try {
                        file.transferTo(new File(path));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                }
            }      
        }
        service.update(homePicture);
        return new ModelAndView("template/pages/home_picture/home_picture.html");
    }
    
    @RequestMapping(value = "getHomeList.do")
    @ResponseBody
    public void getHome(HttpServletResponse response) throws IOException{
        List<HomePicture> list = service.getHome();
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(list));
        out.flush();
        out.close();
    }
}
