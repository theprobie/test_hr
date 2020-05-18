package com.example.hrservice.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hrservice.hr.mapper.EmployeeMapper;
import com.example.hrservice.hr.mapper.EmployeeecMapper;
import com.example.hrservice.hr.mapper.StudentMapper;
import com.example.hrservice.hr.model.Employee;
import com.example.hrservice.hr.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mp")
public class MPController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/")
    public List<Employee> select() {
        return employeeMapper.selectList(null);
    }

    /**
     * TODO 哈哈 太垃圾了 postman不会把数组转换成json格式的字符串
     *
     * @param ids
     * @return
     */
    @GetMapping("/selectByBatch")
    public List<Employee> select(@RequestBody Integer[] ids) {
        List<Integer> strs = Arrays.asList(ids);
        return employeeMapper.selectBatchIds(strs);

    }

    @GetMapping("/selectByBatch1")
    public List<Employee> select1() {

        List<Integer> strs = Arrays.asList(1, 2);
        return employeeMapper.selectBatchIds(strs);

    }

    @GetMapping("/selectByBatchMap")
    public List<Employee> selectMap(@RequestBody Map<String, Object> map) {
//        map.put("id",1);
//        map.put("name","江南一点雨");
        return employeeMapper.selectByMap(map);
    }

    @GetMapping("/selectByWrapper")
    public List<Employee> selectByWrapper() {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
       queryWrapper.like("name","雨").eq("wedlock","已婚");
         return employeeMapper.selectList(queryWrapper);

    }


}
