package com.example.hrservice.hr.controller;

import com.example.hrservice.hr.model.Department;
import com.example.hrservice.hr.model.Menu;
import com.example.hrservice.hr.model.Role;
import com.example.hrservice.hr.service.DepartService;
import com.example.hrservice.hr.service.Menuservice;
import com.example.hrservice.hr.service.RoleService;
import com.example.hrservice.hr.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;

    @Autowired
    Menuservice menuservice;

    @Autowired
    DepartService departService;

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuservice.getAllMenus();
    }

    @GetMapping("mid/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {
        return menuservice.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuservice.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/role{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid) {
        if (roleService.deleteRoleByRid(rid) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 存储过程实现
     * @param department
     * @return
     */

}
