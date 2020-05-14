package com.example.hrservice.hr.controller;

import com.example.hrservice.hr.model.Hr;
import com.example.hrservice.hr.model.Role;
import com.example.hrservice.hr.service.RoleService;
import com.example.hrservice.hr.service.UserService;
import com.example.hrservice.hr.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys/hr")
public class HrController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords) {
        return userService.getAllHrs(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (userService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/update")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (userService.updateHrRole(hrid, rids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")//所有删除不是逻辑删除，可以实现一下逻辑删除思路之一是把enabled设置成0 更新enabled=0
    public RespBean deleteHrById(@PathVariable Integer id) {
        if (userService.deleteHrById(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
