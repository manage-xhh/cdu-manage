package com.cdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cdu.domain.Represent;

@Controller
public class RepresentController {
    
    @RequestMapping(value = "addRepresent.do")
    @ResponseBody
    public String addRepresent(@RequestParam("image") MultipartFile file , @RequestParam("name") String name){
        return null;
    }
}
