package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.TeacherMapper;
import com.example.hrservice.hr.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public Teacher getTeacher(Integer id){
        return teacherMapper.getTeacher(id);
    }

    public Teacher getTeacher2(Integer id){
        return teacherMapper.getTeacher2(id);
    }
}
