package com.chengxuunion.projectportal.business.project.service.impl;

import com.chengxuunion.projectportal.common.constant.Constants;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import com.chengxuunion.util.file.FileUtils;
import com.chengxuunion.util.id.IdGenerator;
import com.chengxuunion.util.string.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import com.chengxuunion.projectportal.business.project.model.Project;
import com.chengxuunion.projectportal.business.project.service.ProjectService;
import com.chengxuunion.projectportal.common.model.FilePath;
import com.chengxuunion.projectportal.common.model.PageResult;
import com.chengxuunion.projectportal.common.util.DownloadUtil;
import com.chengxuunion.projectportal.common.util.PathUtils;
import com.chengxuunion.projectportal.business.project.dao.ProjectDao;
import com.chengxuunion.projectportal.business.project.model.request.ProjectPageParam;


/**
 * @Author youpanpan
 * @Description:    项目服务实现
 * @Date:创建时间: 2019-01-22 21:37:34
 * @Modified By:
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	
	private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;
    
    @Value("${project.image.dir}")
    private String projectImageDir;

    @Override
    public PageResult<Project> listProjectPage(ProjectPageParam projectPageParam) {
        PageHelper.startPage(projectPageParam.getPageNum() , projectPageParam.getPageSize());
        List<Project> projectList = projectDao.listProject(projectPageParam);
        //得到分页的结果对象
        PageInfo<Project> pageInfo = new PageInfo<>(projectList);

        return new PageResult<Project>(projectPageParam, pageInfo.getTotal(), projectList);
    }

    @Override
    public List<Project> listProject(ProjectPageParam projectPageParam) {

        if (!SessionUtils.isLogin() || SessionUtils.isAdmin()) {
            projectPageParam.setOfficial(Constants.ENABLE);
            projectPageParam.setStatus(Constants.ENABLE);
        } else {
            projectPageParam.setOfficial(Constants.DISABLE);
            projectPageParam.setStatus(Constants.ENABLE);
            projectPageParam.setUserId(SessionUtils.getUser().getId());
        }
        List<Project> projectList = projectDao.listProject(projectPageParam);
        return projectList;
    }

    /**
     * 根据主键查询单个项目对象
     *
     * @param id 主键
     * @return  单个项目对象
     */
    @Override
    public Project getProject(Long id) {
        return projectDao.getProject(id);
    }

    /**
     * 保存项目对象
     *
     * @param project 项目对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveProject(Project project) {
        project.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        project.setCreateDate(nowDate);
        project.setCreateUserId(SessionUtils.getUser().getId());
        if (SessionUtils.isAdmin()) {
            project.setOfficial(Constants.ENABLE);
        } else {
            project.setOfficial(Constants.DISABLE);
        }
        return projectDao.saveProject(project);
    }

    /**
     * 更新项目对象
     *
     * @param project 项目对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateProject(Project project) {
        Project dbProject = getProject(project.getId());
        if (!SessionUtils.isAdmin() && !SessionUtils.isCurrentUser(dbProject.getCreateUserId())) {
            throw new RuntimeException("非法操作");
        }
        return  projectDao.updateProject(project);
    }

    /**
     * 根据主键删除项目
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteProject(Long id) {
        Project dbProject = getProject(id);
        if (!SessionUtils.isAdmin() && !SessionUtils.isCurrentUser(dbProject.getCreateUserId())) {
            throw new RuntimeException("非法操作");
        }
        return  projectDao.deleteProject(id);
    }

	@Override
	public Map<String, Object> uploadProjectImage(MultipartFile file) {
		FilePath filePath = PathUtils.getFilePathWithDate(projectImageDir, file.getOriginalFilename());
        try {
            FileUtils.saveFile(filePath.getFileFullPath(), file.getInputStream());
        } catch (IOException e) {
            logger.error("保存项目图片出现异常", e);
            return Collections.emptyMap();
        }

        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("projectImage", filePath.getFilePath());
        return fileMap;
	}

	@Override
	public Boolean downloadProjectImage(Long id, HttpServletResponse response) {
		Project project = getProject(id);
        if (project == null || StringUtils.isEmpty(project.getProjectImage())) {
            return false;
        }

        String filePath = projectImageDir + File.separator + project.getProjectImage();
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        
        return DownloadUtil.download(response, filePath, id.toString() + "." + FileUtils.getSuffix(project.getProjectImage()));
	}
	
	

}

