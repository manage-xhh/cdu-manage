package com.cdu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdu.domain.HomePicture;
import com.cdu.mapper.HomePictureMapper;
import com.cdu.service.HomePictureService;

@Service
public class HomePictureServiceImpl implements HomePictureService{
    
    @Autowired
    private HomePictureMapper mapper;
    
    public void insert(HomePicture picture) {
        mapper.insert(picture);
    }

}
