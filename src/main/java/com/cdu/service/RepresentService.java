package com.cdu.service;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.Represent;
import com.cdu.domain.vo.RepresentVo;

public interface RepresentService {
    
    int addRepresent(Represent represent);
    
    Page<RepresentVo> getAll(PageCondition pageCondition);
    
    int deleteRepresent(Integer id);
    
    Represent selectById(Integer id);
    
    void updateRepresent(Represent represent);
    
}
