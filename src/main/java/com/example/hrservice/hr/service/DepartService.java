package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.DepartmentMapper;
import com.example.hrservice.hr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.departmentMapperByParentId(-1);
    }

    public Integer addDep(Department department) {
        department.setEnabled(true);
        if (departmentMapper.insert(department) == 1) {
            Department dep1 = departmentMapper.selectByPrimaryKey(department.getParentId());//且使用到了mybatis中的主键回填
            department.setDepPath(dep1.getDepPath() + "." + department.getId());
            department.setParent(false);
            if(departmentMapper.updateByPrimaryKeySelective(department)==1){
                dep1.setParent(true);
                departmentMapper.updateByPrimaryKeySelective(dep1);
            }
            return 1;
        }
        return 0;
    }

    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }

    public Department getDepartmentsById(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
}
