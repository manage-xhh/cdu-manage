package com.cdu.domain;

public class Represent {
    private Byte repId;

    private String name;

    private String motto;

    private String image;

    private Byte status;

    public Byte getRepId() {
        return repId;
    }

    public void setRepId(Byte repId) {
        this.repId = repId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto == null ? null : motto.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}