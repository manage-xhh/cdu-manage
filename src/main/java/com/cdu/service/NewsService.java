package com.cdu.service;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.News;

public interface NewsService {
    void addNews(News news);
    
    Page<News> selectAll(PageCondition pageCondition);
    
    News getNews(Integer id);
    
    void update(News news);
    
    int delete(Integer id); 
}
