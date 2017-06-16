package com.cdu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.News;
import com.cdu.service.NewsService;

@Controller
public class NewsController {
    
    @Autowired
    NewsService newsService;
    
    @RequestMapping(value = "getNewsList.do")
    @ResponseBody
    public void getNewsList(PageCondition pageCondition , HttpServletResponse response) throws IOException {
        Page<News> page = newsService.selectAll(pageCondition);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(page));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value="addNews.do")
    public ModelAndView addNews(@RequestParam("upload") MultipartFile file , News news , HttpServletRequest request){
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName == null || "".equals(fileName)) {
                news.setNewsImage("/template/images/head_background.jpg");
                newsService.addNews(news);
                return new ModelAndView("/template/pages/news/news.html");
            }
            String type = fileName.substring(fileName.indexOf(".") + 1);
            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                String realPath=request.getSession().getServletContext().getRealPath("/");
                String realFileName=String.valueOf(System.currentTimeMillis())+fileName;
                String path = realPath + "template/upload/" + realFileName;
                news.setNewsImage("/template/upload/" + realFileName);
                newsService.addNews(news);
                try {
                    file.transferTo(new File(path));
                    return new ModelAndView("/template/pages/news/news.html");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
        return new ModelAndView("/template/pages/news/news.html");
    }
    
    @RequestMapping(value = "getNews.do" , method = RequestMethod.GET)
    @ResponseBody
    public void getNews(@RequestParam("id") Integer id , HttpServletResponse response) throws IOException {  
        News news = newsService.getNews(id);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(news));
        out.flush();
        out.close();
    }
    
    @RequestMapping(value="updateNews.do")
    public ModelAndView updateNews(@RequestParam("upload") MultipartFile file , News news , HttpServletRequest request){
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName == null || "".equals(fileName)) {
                newsService.update(news);
                return new ModelAndView("/template/pages/news/news.html");
            }
            String type = fileName.substring(fileName.indexOf(".") + 1);
            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                String realPath=request.getSession().getServletContext().getRealPath("/");
                String realFileName=String.valueOf(System.currentTimeMillis())+fileName;
                String path = realPath + "template/upload/" + realFileName;
                news.setNewsImage("/template/upload/" + realFileName);
                newsService.update(news);
                try {
                    file.transferTo(new File(path));
                    return new ModelAndView("/template/pages/news/news.html");
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
        return new ModelAndView("/template/pages/news/news.html");
    }
    
    @RequestMapping(value = "deleteNews.do" , method = RequestMethod.GET)
    @ResponseBody
    public String deleteNews(@RequestParam("id") Integer id) {  
        Integer result = newsService.delete(id);        
        return result.toString();
    }
}
