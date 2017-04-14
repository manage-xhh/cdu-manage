package com.cdu.mapper;

import com.cdu.domain.Represent;
import com.cdu.domain.RepresentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepresentMapper {
    int countByExample(RepresentExample example);

    int deleteByExample(RepresentExample example);

    int deleteByPrimaryKey(Byte repId);

    int insert(Represent record);

    int insertSelective(Represent record);

    List<Represent> selectByExample(RepresentExample example);

    Represent selectByPrimaryKey(Byte repId);

    int updateByExampleSelective(@Param("record") Represent record, @Param("example") RepresentExample example);

    int updateByExample(@Param("record") Represent record, @Param("example") RepresentExample example);

    int updateByPrimaryKeySelective(Represent record);

    int updateByPrimaryKey(Represent record);
    
    List<Represent> selectHomeRepresent();
}