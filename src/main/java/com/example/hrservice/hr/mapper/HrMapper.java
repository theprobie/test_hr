package com.example.hrservice.hr.mapper;

import com.example.hrservice.hr.model.Hr;
import com.example.hrservice.hr.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String s);

    List<Role> getHrRoleById(Integer id);

    List<Hr> getAllHrs(@Param("id") Integer id, @Param("keywords") String keywords);
}