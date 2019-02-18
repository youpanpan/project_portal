package com.chengxuunion.projectportal.business.user.controller;


import com.chengxuunion.projectportal.business.user.model.request.UpdatePasswordParam;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.chengxuunion.projectportal.business.user.model.User;
import com.chengxuunion.projectportal.business.user.model.request.UserPageParam;
import com.chengxuunion.projectportal.business.user.service.UserService;
import com.chengxuunion.projectportal.common.controller.BaseController;
import com.chengxuunion.util.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author youpanpan
 * @Description:    用户控制器
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
@Controller
@RequestMapping("/user/manager")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    
    @Value("${default.password}")
    private String defaultPassword;

    @GetMapping
    public String index() {
        return "user/index";
    }

    @GetMapping("/listselect")
    public String listSelect(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String names = request.getParameter("names");
        model.addAttribute("maxNum", request.getParameter("maxNum"));
        model.addAttribute("ids", ids);
        model.addAttribute("names", names);
        return "user/list-select";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(UserPageParam userPageParam) {
        return userService.listUserPage(userPageParam);
    }

    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(User user) {
    	user.setPassword(SecurityUtils.encryptMD5(defaultPassword));
        return userService.saveUser(user);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        User user = userService.getUser(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @GetMapping("/update-open/{id}")
    public String updateOpen(Model model, @PathVariable("id") Long id) {
        User user = userService.getUser(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update-open";
    }

    @GetMapping("/update-password")
    public String updatePassword(Model model) {
        model.addAttribute("user", SessionUtils.getUser());
        return "user/update-password";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(User user) {
        return userService.updateUser(user);
    }

    @PutMapping("/do-update-password")
    @ResponseBody
    public Object doUpdatePassword(UpdatePasswordParam updatePasswordParam) {
        return userService.updateUserPassword(updatePasswordParam);
    }


    @PutMapping("/reset-password/{id}")
    @ResponseBody
    public Object resetPassword(@PathVariable("id") Long id) {
        return userService.resetPassword(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        User user = userService.getUser(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/detail";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object uploadHeadPhoto(MultipartFile file) {
        return userService.uploadHeadPhoto(file);
    }

    @GetMapping("/download/{id}")
    public String downloadHeadPhoto(HttpServletResponse response, @PathVariable("id") Long id) {
        Boolean success = userService.downloadHeadPhoto(id, response);
        if (success) {
            return null;
        } else {
            return "public/file-notfind";
        }
    }
}
