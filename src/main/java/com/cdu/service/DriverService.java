package com.cdu.service;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Driver;
import com.cdu.domain.vo.DriverVo;

public interface DriverService {
    //添加待审核司机
    String insertWaitAuditDriver(Driver driver); 
    //获取所有待审核的司机
    Page<DriverVo> driverWaitAuditList(PageCondition pageCondition);
    //认证司机
    Page<DriverVo> authList(PageCondition pageCondition);
    //审核通过
    int updateDriverStatus(Driver driver);
    //删除认证司机
    String deleteDriver(Integer driverId);
}
