package com.chengxuunion.projectportal.business.roleurl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.chengxuunion.projectportal.business.role.model.Role;
import com.chengxuunion.projectportal.business.roleurl.model.RoleUrl;
import com.chengxuunion.projectportal.business.roleurl.model.request.RoleUrlPageParam;
import com.chengxuunion.projectportal.business.roleurl.service.RoleUrlService;
import com.chengxuunion.projectportal.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    角色URL控制器
 * @Date:创建时间: 2019-01-17 17:44:49
 * @Modified By:
 */
@Controller
@RequestMapping("/roleurl/manager")
public class RoleUrlController extends BaseController {

    @Autowired
    private RoleUrlService roleUrlService;

    @GetMapping
    public String index() {
        return "roleurl/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(RoleUrlPageParam roleUrlPageParam) {
        return roleUrlService.listRoleUrlPage(roleUrlPageParam);
    }

    @GetMapping("/list/{roleId}")
    @ResponseBody
    public Object listUrl(@PathVariable("roleId") Long roleId) {
        return roleUrlService.listRoleUrlByRoleId(roleId);
    }

    @GetMapping("/add")
    public String add() {
        return "roleurl/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(RoleUrl roleUrl) {
        return roleUrlService.saveRoleUrl(roleUrl);
    }

    @PostMapping("do-save")
    @ResponseBody
    public Object doSave(@RequestBody Role role) {
        return roleUrlService.saveRoleUrlBatch(role);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        RoleUrl roleUrl = roleUrlService.getRoleUrl(id);
        model.addAttribute("roleUrl", roleUrl);
        return "roleurl/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(RoleUrl roleUrl) {
        return roleUrlService.updateRoleUrl(roleUrl);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return roleUrlService.deleteRoleUrl(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        RoleUrl roleUrl = roleUrlService.getRoleUrl(id);
        model.addAttribute("roleUrl", roleUrl);
        return "roleurl/detail";
    }

}
