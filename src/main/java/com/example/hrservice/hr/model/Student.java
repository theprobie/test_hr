package com.example.hrservice.hr.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Student extends BaseRowModel {
    @ExcelProperty("学号")
    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("教师ID")
    private Integer tid;
    //使用association解决，直接resultType导致查询teacher为null
    @ExcelProperty("教师姓名")
//    @TableField(exist = false)
    private String tname;

    @ExcelIgnore
//    @TableField(exist = false)
    private Teacher teacher;
}
