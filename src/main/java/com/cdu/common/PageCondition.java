package com.cdu.common;

import java.util.Map;

public class PageCondition {
    private int pageNo; //当前页数
    private int pageSize;//每页条数
    private Map<String , Object> filters; //过滤条件集合
    private int start;
    
    private int end;

    public int getStart() {
        return start;
    }

    public void setPage(int start , int size) {
        this.start = (start - 1) * size;
        this.end = size;
    }

    public int getEnd() {
        return end;
    }
    
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public Map<String, Object> getFilters() {
        return filters;
    }
    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
        
}
