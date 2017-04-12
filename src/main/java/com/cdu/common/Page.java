package com.cdu.common;

import java.util.ArrayList;
import java.util.List;

/**
 *分页工具 
 * */
public class Page<T> {
    @SuppressWarnings("unused")
    private int total; //总页数
    private int pageSize = 10; //每页显示条数
    private int pageNo; //当前页数
    private int totalSize; //总条数
    private List<T> list; //封装的集合
    
    public int getTotal() {
        if (getTotalSize()%getPageSize() == 0) {
            return getTotalSize()/10;
        } else {
            return getTotalSize()/10 + 1;
        }      
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        if (list == null) {
            this.list = new ArrayList<T>();
        }
        this.list = list;
    }  
}
