package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.StudentMapper;
import com.example.hrservice.hr.mapper.TeacherMapper;
import com.example.hrservice.hr.model.Student;
import com.example.hrservice.hr.model.Teacher;
import com.example.hrservice.hr.model.UpLoadStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

   public List<Student> getStudents(){
       return studentMapper.getStudents();
   }

    public List<Student> getStudents2() {
        return studentMapper.getStudents2();
    }

    public List<Student> getStudentInfor(Map map){
       return studentMapper.getStudentInfor(map);
    }

    public Integer save(List<UpLoadStudent> data) {
       return studentMapper.save(data);
    }

    public int insert(Student student) {
       return  studentMapper.insert(student);
    }


//    public Integer batch(List<Model> data) {
//       return studentMapper.batch(data);
//    }
}
