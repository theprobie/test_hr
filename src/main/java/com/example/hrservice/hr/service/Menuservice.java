package com.example.hrservice.hr.service;

import com.example.hrservice.hr.mapper.MenuMapper;
import com.example.hrservice.hr.mapper.MenuRoleMapper;
import com.example.hrservice.hr.model.Hr;
import com.example.hrservice.hr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jws.Oneway;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteMenusByRoleId(rid);//把一个角色里下面的所有menu全部删除
        Integer result = menuRoleMapper.insertMenusByRoleId(rid, mids);
        return result == mids.length;
    }

    public List<Menu> getMenuByMP() {
        List<Menu> menus = menuMapper.selectList(null);
        List<Menu> collect = menus.stream().filter((menu) -> {
            return (menu.getParentId() == null ? 0 : menu.getParentId()) == 1;
            //parentId是integer，可能为空，如果直接写menu.getParentId() == 1会造成空指针异常
        }).map((menu) -> {
            menu.setChildren(getChildren(menu, menus));
            return menu;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * @param root  当前菜单
     * @param all 全部菜单
     *            递归查询所有菜单的子菜单
     * @return
     */
    private List<Menu> getChildren(Menu root, List<Menu> all) {
        List<Menu> collect = all.stream().filter((menus) -> {
            return menus.getParentId() == root.getId();
        }).map(menu -> {
            menu.setChildren(getChildren(menu, all));
            return menu;
        }).collect(Collectors.toList());
        return collect;
    }
}
