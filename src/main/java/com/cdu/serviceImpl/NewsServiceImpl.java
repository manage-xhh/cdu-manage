package com.cdu.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cdu.common.FormatUtils;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.News;
import com.cdu.domain.NewsExample;
import com.cdu.mapper.NewsMapper;
import com.cdu.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
    
    @Autowired
    NewsMapper newsMapper;
    
    public void addNews(News news) {
        news.setCreateTime(new Date());
        newsMapper.insert(news);
    }

    public Page<News> selectAll(PageCondition pageCondition) {
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        example.setPage(pageCondition.getPageNo(), pageCondition.getPageSize());
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("newsTitle"))) {
                criteria.andNewsTitleEqualTo(pageCondition.getFilters().get("newsTitle").toString());
            }
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("newsType"))) {
                criteria.andNewsTypeEqualTo(pageCondition.getFilters().get("newsType").toString());
            }
        }
        List<News> list = newsMapper.selectByExample(example);
        for(News news : list) {
            news.setTime(FormatUtils.dateToString(news.getCreateTime()));
        }
        Page<News> page = new Page<News>();
        page.setTotalSize(newsMapper.countByExample(example));
        page.setPageNo(pageCondition.getPageNo());
        page.setList(list);
        return page;
    }

    public News getNews(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id.byteValue());
        news.setTime(FormatUtils.dateToString(news.getCreateTime()));
        return news;
    }

    public void update(News news) {
        newsMapper.updateByPrimaryKeySelective(news);
    }

    public int delete(Integer id) {
        return newsMapper.deleteByPrimaryKey(id.byteValue());
    }
}
