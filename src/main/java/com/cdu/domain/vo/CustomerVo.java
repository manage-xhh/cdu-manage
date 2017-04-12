package com.cdu.domain.vo;

import java.io.Serializable;

public class CustomerVo implements Serializable{
    private static final long serialVersionUID = 8748179445926549338L;
    private Byte customerId;
    private String account;
    private String createTime;
    private String lastLoginTime;
    private String role;
    
    public Byte getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Byte customerId) {
        this.customerId = customerId;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    } 
}
