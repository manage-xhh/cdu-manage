package com.cdu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.common.Enum.RepresentStatusEnum;
import com.cdu.domain.HomePicture;
import com.cdu.domain.HomePictureExample;
import com.cdu.domain.vo.HomePictureVo;
import com.cdu.mapper.HomePictureMapper;
import com.cdu.service.HomePictureService;

@Service
public class HomePictureServiceImpl implements HomePictureService{
    
    @Autowired
    private HomePictureMapper mapper;
    
    public void insert(HomePicture picture) {
        mapper.insert(picture);
    }

    public Page<HomePictureVo> selectAll(PageCondition pageCondition) {
        List<HomePictureVo> list = null;
        HomePictureExample example = new HomePictureExample();
        HomePictureExample.Criteria criteria = example.createCriteria();
        example.setPage(pageCondition.getPageNo(), pageCondition.getPageSize());
        if (pageCondition.getFilters() != null) {
            if (!StringUtils.isEmpty(pageCondition.getFilters().get("name"))) {
                criteria.andNameLike(pageCondition.getFilters().get("name").toString());
            }
        }
        List<HomePicture> rList = mapper.selectByExample(example);
        if(rList.size() > 0){
            list = new ArrayList<HomePictureVo>();
            for(int i = 0;i < rList.size();i++) {
                HomePictureVo vo = new HomePictureVo();
                vo.setId(rList.get(i).getId().intValue());
                vo.setName(rList.get(i).getName());
                vo.setPath(rList.get(i).getPath());
                vo.setUrl(rList.get(i).getUrl());
                vo.setStatu(RepresentStatusEnum.getValueByName(rList.get(i).getStatu()));
                list.add(vo);
            }
        }
        Page<HomePictureVo> page = new Page<HomePictureVo>();
        page.setTotalSize(mapper.countByExample(example));
        page.setPageNo(pageCondition.getPageNo());
        page.setList(list);
        return page;
    }

    public int deletePicture(byte id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public HomePicture selectById(Integer id) {
        return mapper.selectByPrimaryKey(id.byteValue());
    }

    public void update(HomePicture homePicture) {
        mapper.updateByPrimaryKeySelective(homePicture);
    }

    public List<HomePicture> getHome() {
        HomePictureExample example = new HomePictureExample();
        HomePictureExample.Criteria criteria = example.createCriteria();
        criteria.andStatuEqualTo((byte)1);
        List<HomePicture> list = mapper.selectByStatu(example);
        return list;
    }

}
