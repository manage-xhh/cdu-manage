package com.cdu.mapper;

import com.cdu.domain.HomePicture;
import com.cdu.domain.HomePictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomePictureMapper {
    int countByExample(HomePictureExample example);

    int deleteByExample(HomePictureExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(HomePicture record);

    int insertSelective(HomePicture record);

    List<HomePicture> selectByExample(HomePictureExample example);

    HomePicture selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") HomePicture record, @Param("example") HomePictureExample example);

    int updateByExample(@Param("record") HomePicture record, @Param("example") HomePictureExample example);

    int updateByPrimaryKeySelective(HomePicture record);

    int updateByPrimaryKey(HomePicture record);
}