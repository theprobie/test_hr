package com.example.hrservice.hr.controller;

import com.example.hrservice.hr.model.Department;
import com.example.hrservice.hr.service.DepartService;
import com.example.hrservice.hr.service.RoleService;
import com.example.hrservice.hr.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/deparment")
public class DepartController {

    @Autowired
    DepartService departService;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departService.getAllDepartments();//嵌套查询
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department department) {
        if (departService.addDep(department) == 1) {
            return RespBean.ok("添加成功", department);
        }
        return RespBean.error("添加失败");
    }

    /**
     * 存储过程删除，
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departService.deleteDepById(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下存在子部门，无法删除");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门有员工存在，无法删除");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("未知错误，无法删除");
    }

}

