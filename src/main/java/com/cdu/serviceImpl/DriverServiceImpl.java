package com.cdu.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cdu.common.FormatUtils;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.common.Enum.DriverStatusEnum;
import com.cdu.domain.Driver;
import com.cdu.domain.DriverExample;
import com.cdu.domain.vo.DriverVo;
import com.cdu.mapper.DriverMapper;
import com.cdu.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{
    
    @Autowired
    DriverMapper driverMapper;
    
    public String insertWaitAuditDriver(Driver driver) {
        List<String> phone = driverMapper.selectAllPhone();
        for (int i = 0;i < phone.size();i++) {
            if (driver.getDriverPhone().equals(phone.get(i))) {
                return "1";
            }
        }
        driver.setCreateTime(new Date());
        driver.setDriverStatus((byte)1);
        driverMapper.insert(driver);
        return "2";
    }

    public Page<DriverVo> driverWaitAuditList(PageCondition pageCondition) {
        List<DriverVo> list = null;
        DriverExample exp = new DriverExample();
        DriverExample.Criteria criteria = exp.createCriteria();
        criteria.andDriverStatusEqualTo((byte)1);
        exp.setPage(pageCondition.getPageNo(), pageCondition.getPageSize());
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("driverName"))) {
                criteria.andDriverNameLike(pageCondition.getFilters().get("driverName").toString());
            }
        }
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("driverPhone"))) {
                criteria.andDriverPhoneLike(pageCondition.getFilters().get("driverPhone").toString());
            }
        }
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("driverArea"))) {
                criteria.andDriverAreaEqualTo(pageCondition.getFilters().get("driverArea").toString());
            }
        }
        List<Driver> dList = driverMapper.selectByExample(exp);
        if (dList.size() > 0) {
            list = new ArrayList<DriverVo>();
            for (int i = 0;i < dList.size();i++) {
                DriverVo driver = new DriverVo();
                driver.setDriverId(dList.get(i).getDriverId().intValue());
                driver.setDriverName(dList.get(i).getDriverName());
                driver.setDriverPhone(dList.get(i).getDriverPhone());
                driver.setCreateTime(FormatUtils.dateToString(dList.get(i).getCreateTime()));
                driver.setArea(dList.get(i).getDriverArea());
                driver.setStatus(DriverStatusEnum.getValueByName(dList.get(i).getDriverStatus()));
                list.add(driver);
            }
        }
        Page<DriverVo> page = new Page<DriverVo>();
        page.setTotalSize(driverMapper.countByExample(exp));
        page.setPageNo(pageCondition.getPageNo());
        page.setList(list);
        return page;
    }

    public int updateDriverStatus(Driver driver) {
        driver.setDriverStatus((byte)2);
        int flag = driverMapper.updateByPrimaryKeySelective(driver);
        return flag;
    }

    public Page<DriverVo> authList(PageCondition pageCondition) {
        List<DriverVo> list = null;
        DriverExample exp = new DriverExample();
        DriverExample.Criteria criteria = exp.createCriteria();
        criteria.andDriverStatusEqualTo((byte)2);
        exp.setPage(pageCondition.getPageNo(), pageCondition.getPageSize());
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("driverName"))) {
                criteria.andDriverNameLike(pageCondition.getFilters().get("driverName").toString());
            }
        }
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("driverPhone"))) {
                criteria.andDriverPhoneLike(pageCondition.getFilters().get("driverPhone").toString());
            }
        }
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("driverArea"))) {
                criteria.andDriverAreaEqualTo(pageCondition.getFilters().get("driverArea").toString());
            }
        }
        List<Driver> dList = driverMapper.selectByExample(exp);
        if (dList.size() > 0) {
            list = new ArrayList<DriverVo>();
            for (int i = 0;i < dList.size();i++) {
                DriverVo driver = new DriverVo();
                driver.setDriverId(dList.get(i).getDriverId().intValue());
                driver.setDriverName(dList.get(i).getDriverName());
                driver.setDriverPhone(dList.get(i).getDriverPhone());
                driver.setCreateTime(FormatUtils.dateToString(dList.get(i).getCreateTime()));
                driver.setArea(dList.get(i).getDriverArea());
                driver.setStatus(DriverStatusEnum.getValueByName(dList.get(i).getDriverStatus()));
                list.add(driver);
            }
        }
        Page<DriverVo> page = new Page<DriverVo>();
        page.setTotalSize(driverMapper.countByExample(exp));
        page.setPageNo(pageCondition.getPageNo());
        page.setList(list);
        return page;
    }

    public String deleteDriver(Integer driverId) {
        return driverMapper.deleteByPrimaryKey(driverId.byteValue()) + "";
    } 
}
