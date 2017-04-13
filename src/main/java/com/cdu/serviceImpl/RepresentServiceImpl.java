package com.cdu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.common.Enum.RepresentStatusEnum;
import com.cdu.domain.Represent;
import com.cdu.domain.RepresentExample;
import com.cdu.domain.vo.RepresentVo;
import com.cdu.mapper.RepresentMapper;
import com.cdu.service.RepresentService;

@Service
public class RepresentServiceImpl implements RepresentService{
    
    @Autowired
    RepresentMapper representMapper;
    
    public int addRepresent(Represent represent) {
        return representMapper.insert(represent);
    }

    public Page<RepresentVo> getAll(PageCondition pageCondition) {
        List<RepresentVo> list = null;
        RepresentExample example = new RepresentExample();
        RepresentExample.Criteria criteria = example.createCriteria();
        example.setPage(pageCondition.getPageNo(), pageCondition.getPageSize());
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("name"))) {
                criteria.andNameLike(pageCondition.getFilters().get("name").toString());
            }
        }
        List<Represent> rList = representMapper.selectByExample(example);
        if(rList.size() > 0){
            list = new ArrayList<RepresentVo>();
            for(int i = 0;i < rList.size();i++) {
                RepresentVo vo = new RepresentVo();
                vo.setRepId(rList.get(i).getRepId().intValue());
                vo.setName(rList.get(i).getName());
                vo.setMotto(rList.get(i).getMotto());
                vo.setImage(rList.get(i).getImage());
                vo.setStatus(RepresentStatusEnum.getValueByName(rList.get(i).getStatus()));
                list.add(vo);
            }
        }
        Page<RepresentVo> page = new Page<RepresentVo>();
        page.setTotalSize(representMapper.countByExample(example));
        page.setPageNo(pageCondition.getPageNo());
        page.setList(list);
        return page;
    }

    public int deleteRepresent(Integer id) {
        return representMapper.deleteByPrimaryKey(id.byteValue());
    }

    public Represent selectById(Integer id) {
        return representMapper.selectByPrimaryKey(id.byteValue());
    }

    public void updateRepresent(Represent represent) {
        representMapper.updateByPrimaryKeySelective(represent);
    }

}
