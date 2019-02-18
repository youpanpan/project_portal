package com.chengxuunion.projectportal.business.project.controller;

import com.chengxuunion.projectportal.business.project.model.Project;
import com.chengxuunion.projectportal.business.project.model.request.ProjectPageParam;
import com.chengxuunion.projectportal.business.project.service.ProjectService;
import com.chengxuunion.projectportal.common.controller.BaseController;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author youpanpan
 * @Description:    项目控制器
 * @Date:创建时间: 2019-01-22 21:37:34
 * @Modified By:
 */
@Controller
@RequestMapping("/project/manager")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String index() {
        return "project/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(ProjectPageParam projectPageParam) {
        return projectService.listProjectPage(projectPageParam);
    }

    /**
     * 查询用户能访问的项目列表
     *
     * @param projectPageParam
     * @return
     */
    @GetMapping("/list/project")
    @ResponseBody
    public Object listProject(ProjectPageParam projectPageParam) {
        return projectService.listProject(projectPageParam);
    }

    @GetMapping("/add")
    public String add() {
        return "project/add";
    }

    @GetMapping("/add-open")
    public String addOpen() {
        return "project/add-open";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/update";
    }

    @GetMapping("/update-open/{id}")
    public String updateOpen(Model model, @PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/update-open";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(Project project) {
        return projectService.updateProject(project);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return projectService.deleteProject(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/detail";
    }

    @GetMapping("/detail-open/{id}")
    public String detailOpen(Model model, @PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/detail-open";
    }
    
    @PostMapping("/upload")
    @ResponseBody
    public Object uploadProjectImage(MultipartFile file) {
        return projectService.uploadProjectImage(file);
    }

    @GetMapping("/download/{id}")
    public String downloadProjectImage(HttpServletResponse response, @PathVariable("id") Long id) {
        projectService.downloadProjectImage(id, response);
        return null;
    }
}
