package com.example.hrservice.hr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hrservice.hr.model.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);


    List<Menu> getMenusById(Integer hrid);

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);
}