package com.cdu.domain.vo;

import java.io.Serializable;

public class HomePictureVo implements Serializable{

    private static final long serialVersionUID = -6230151476439715756L;
    private Integer id;
    private String name;
    private String url;
    private String statu;
    private String path;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getStatu() {
        return statu;
    }
    public void setStatu(String statu) {
        this.statu = statu;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
}
