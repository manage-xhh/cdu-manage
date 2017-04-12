package com.cdu.domain.vo;

import java.io.Serializable;

public class DriverVo implements Serializable{

    private static final long serialVersionUID = -6928298387991501544L;
    private Integer driverId;
    private String driverName;
    private String driverPhone;
    private String createTime;
    private String status;
    private String area;
    
    public Integer getDriverId() {
        return driverId;
    }
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getDriverPhone() {
        return driverPhone;
    }
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    
}
