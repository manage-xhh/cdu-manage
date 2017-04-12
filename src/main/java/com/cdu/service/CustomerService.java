package com.cdu.service;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Customer;
import com.cdu.domain.vo.CustomerVo;

public interface CustomerService {
    //后台登录
    Customer login(Customer customer);
    //更新账户
    void updateByCustomer(Customer customer);
    //获取所有用户
    Page<CustomerVo> getAll(PageCondition pageCondition);
    //根据帐号查询
    Customer getByAccount(String account);
    //添加账户
    String addCustomer(Customer customer);
    //删除用户
    void deleteCustomer(Integer id);
    //根据id查询
    Customer getByCostomerId(Integer id);
}
