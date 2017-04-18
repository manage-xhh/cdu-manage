package com.cdu.domain;

public class HomePicture {
    private Byte id;

    private String name;

    private String url;

    private Byte statu;

    private String path;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getStatu() {
        return statu;
    }

    public void setStatu(Byte statu) {
        this.statu = statu;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}