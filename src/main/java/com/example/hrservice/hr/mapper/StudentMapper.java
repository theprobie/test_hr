package com.example.hrservice.hr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hrservice.hr.model.Student;
import com.example.hrservice.hr.model.UpLoadStudent;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 1. 按照查询嵌套处理
     * @return
     */
     List<Student> getStudents();

    /**
     * 按照结果嵌套处理
     * @return
     */
    List<Student> getStudents2();

    List<Student> getStudentInfo(Map map);

    List<Student> getStudentInfor(Map map);


    Integer save(List<UpLoadStudent> list);
}
