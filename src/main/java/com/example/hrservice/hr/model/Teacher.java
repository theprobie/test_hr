package com.example.hrservice.hr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    //使用collection，否则直接resultType 导致student查询出为null。
    private List<Student> students;
}
