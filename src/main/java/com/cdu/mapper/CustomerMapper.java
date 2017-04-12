package com.cdu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cdu.domain.Customer;
import com.cdu.domain.CustomerExample;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Byte customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Byte customerId);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
		
    //login
    Customer selectByCustomer(Customer customer);
    //管理员列表
    List<Customer> selectAll(CustomerExample example);
    //添加管理员-角色关系表
    void insertCustomerAndRole(Map<String , Byte> map);
    Customer selectByCustomerId(Byte id);
}