package com.cdu.domain;

import java.util.Date;

public class Driver {
    private Byte driverId;

    private String driverName;

    private String driverPhone;

    private String driverArea;

    private Byte driverStatus;

    private Date createTime;

    public Byte getDriverId() {
        return driverId;
    }

    public void setDriverId(Byte driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public String getDriverArea() {
        return driverArea;
    }

    public void setDriverArea(String driverArea) {
        this.driverArea = driverArea == null ? null : driverArea.trim();
    }

    public Byte getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(Byte driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}