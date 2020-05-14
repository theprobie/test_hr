package com.example.hrservice.hr.mapper;

import com.example.hrservice.hr.model.MenuRole;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteMenusByRoleId(Integer rid);

    Integer insertMenusByRoleId(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}