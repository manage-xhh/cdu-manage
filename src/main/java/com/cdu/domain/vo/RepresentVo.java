package com.cdu.domain.vo;

import java.io.Serializable;

public class RepresentVo implements Serializable{
    private static final long serialVersionUID = 4382557051231621577L;
    private int repId;

    private String name;

    private String motto;

    private String image;

    private String status;

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
