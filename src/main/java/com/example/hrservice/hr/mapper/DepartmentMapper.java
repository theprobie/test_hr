package com.example.hrservice.hr.mapper;

import com.example.hrservice.hr.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> departmentMapperByParentId(Integer i);//mybatis懒加载


    void deleteDepById(Department dep);
}