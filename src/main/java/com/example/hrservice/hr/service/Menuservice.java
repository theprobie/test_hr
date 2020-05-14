package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.MenuMapper;
import com.example.hrservice.hr.mapper.MenuRoleMapper;
import com.example.hrservice.hr.model.Hr;
import com.example.hrservice.hr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.List;

@Service
public class Menuservice {
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<Menu> getMenusById() {
        /**
         * 所有用户信息保存在SecurityContextHolder..getContext().getAuthentication()
         * .getPrincipal()之中，需要强转成Hr
         */
        List<Menu> menus = menuMapper.getMenusById(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        return menus;
    }

    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
       return  menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteMenusByRoleId(rid);//把一个角色里下面的所有menu全部删除
        Integer result = menuRoleMapper.insertMenusByRoleId(rid,mids);
        return result==mids.length;
    }
}
