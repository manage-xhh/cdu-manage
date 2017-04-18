package com.cdu.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cdu.domain.HomePicture;
import com.cdu.service.HomePictureService;

@Controller
public class HomePictureController {
    
    @Autowired
    private HomePictureService service;
    
    @RequestMapping(value = "addHomePicture.do")
    public ModelAndView addRepresent(@RequestParam("upload") MultipartFile file , HomePicture homePicture , HttpServletRequest request){
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
}
