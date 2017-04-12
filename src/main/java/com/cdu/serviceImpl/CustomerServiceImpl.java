package com.cdu.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cdu.common.FormatUtils;
import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Customer;
import com.cdu.domain.CustomerExample;
import com.cdu.domain.vo.CustomerVo;
import com.cdu.mapper.CustomerMapper;
import com.cdu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
    
    @Autowired
    private CustomerMapper customerMapper;
    
    public Customer login(Customer customer) {
        return customerMapper.selectByCustomer(customer);
    }

    public Page<CustomerVo> getAll(PageCondition pageCondition) {
        List<CustomerVo> list = null;
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        example.setPage(pageCondition.getPageNo(), pageCondition.getPageSize());
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("account"))) {
                criteria.andAccountLike(pageCondition.getFilters().get("account").toString());
            }
        }
        List<Customer> cList = customerMapper.selectAll(example);
        if(cList.size() > 0){
            list = new ArrayList<CustomerVo>();
            for(int i = 0;i < cList.size();i++) {
                CustomerVo vo = new CustomerVo();
                vo.setCustomerId(cList.get(i).getCustomerId());
                vo.setAccount(cList.get(i).getAccount());
                if(cList.get(i).getRole() != null){
                    vo.setRole(cList.get(i).getRole().getRoleName());
                }              
                vo.setCreateTime(FormatUtils.dateToString(cList.get(i).getCreateTime()));
                if (cList.get(i).getLastLoginTime() != null) {
                    vo.setLastLoginTime(FormatUtils.dateToString(cList.get(i).getLastLoginTime()));
                }else{
                    vo.setLastLoginTime("");
                }
                list.add(vo);
            }
        }
        Page<CustomerVo> page = new Page<CustomerVo>();
        page.setTotalSize(customerMapper.countByExample(example));
        page.setPageNo(pageCondition.getPageNo());
        page.setList(list);
        return page;
    }

    public void updateByCustomer(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);   
    }

    public Customer getByAccount(String account) {
        CustomerExample exp = new CustomerExample();
        CustomerExample.Criteria criteria = exp.createCriteria();
        criteria.andAccountEqualTo(account);
        if (customerMapper.selectByExample(exp).size() > 0) {
            return customerMapper.selectByExample(exp).get(0);
        }
        return null;
    }

    public String addCustomer(Customer customer) {
        customer.setCreateTime(new Date());
        int flag = customerMapper.insertSelective(customer);
        Customer c = this.getByAccount(customer.getAccount());
        Map<String , Byte> map = new HashMap<String , Byte>();
        map.put("customerId", c.getCustomerId());
        map.put("roleId", customer.getRoleId());
        customerMapper.insertCustomerAndRole(map);
        if (flag > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    public void deleteCustomer(Integer id) {
        customerMapper.deleteByPrimaryKey(id.byteValue());
    }

    public Customer getByCostomerId(Integer id) {
        return customerMapper.selectByCustomerId(id.byteValue());
    }
    
}
