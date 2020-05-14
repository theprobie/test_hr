package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.RoleMapper;
import com.example.hrservice.hr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRoleByRid(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
