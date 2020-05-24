package com.example.hrservice.hr.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hrservice.hr.model.Teacher;

public interface TeacherMapper  extends BaseMapper<Teacher> {
    Teacher getTeacher( Integer id);
    Teacher getTeacher2(Integer id);
}
