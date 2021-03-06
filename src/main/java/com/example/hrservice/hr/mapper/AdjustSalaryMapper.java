package com.example.hrservice.hr.mapper;

import com.example.hrservice.hr.model.AdjustSalary;
import org.apache.ibatis.annotations.Param;

public interface AdjustSalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdjustSalary record);

    int insertSelective(AdjustSalary record);

    AdjustSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdjustSalary record);

    int updateByPrimaryKey(AdjustSalary record);


}