package com.cdu.service;

import java.util.List;

import com.cdu.common.Page;
import com.cdu.common.PageCondition;
import com.cdu.domain.HomePicture;
import com.cdu.domain.vo.HomePictureVo;

public interface HomePictureService {
    void insert(HomePicture picture);
    
    Page<HomePictureVo> selectAll(PageCondition pageCondition);
    
    int deletePicture(byte id);
    
    HomePicture selectById(Integer id);
    
    void update(HomePicture homePicture);
    
    List<HomePicture> getHome();
}
