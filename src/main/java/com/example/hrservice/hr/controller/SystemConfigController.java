package com.example.hrservice.hr.controller;

import com.example.hrservice.hr.model.Menu;
import com.example.hrservice.hr.service.Menuservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/config")
public class SystemConfigController {
    @Autowired
    Menuservice menuservice;
    //此接口是根据用户角色查询菜单与HelloController中的/menu不一样
    @GetMapping("/menu")
    public List<Menu> getMenusById(){
        return menuservice.getMenusById();
    }
}
