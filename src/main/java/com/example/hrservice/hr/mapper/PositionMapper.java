package com.example.hrservice.hr.mapper;

import com.example.hrservice.hr.model.Position;
import com.example.hrservice.hr.util.RespBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPosition();

    Integer deletePositionByIds(@Param("ids") Integer[] ids);
}