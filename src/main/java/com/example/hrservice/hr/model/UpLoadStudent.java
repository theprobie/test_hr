package com.example.hrservice.hr.model;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class UpLoadStudent extends BaseRowModel {

    private Integer id;

    private String name;

    private Integer tid;
}
