package com.example.hrservice.hr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hrservice.hr.model.Employee;

public interface EmployeeMapper extends BaseMapper<Employee> {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}