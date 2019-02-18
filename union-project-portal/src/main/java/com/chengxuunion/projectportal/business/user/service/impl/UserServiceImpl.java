package com.chengxuunion.projectportal.business.user.service.impl;

import com.chengxuunion.projectportal.business.user.dao.UserDao;
import com.chengxuunion.projectportal.business.user.model.User;
import com.chengxuunion.projectportal.business.user.model.request.UpdatePasswordParam;
import com.chengxuunion.projectportal.business.user.model.request.UserPageParam;
import com.chengxuunion.projectportal.business.user.service.UserService;
import com.chengxuunion.projectportal.common.model.FilePath;
import com.chengxuunion.projectportal.common.model.PageResult;
import com.chengxuunion.projectportal.common.util.DownloadUtil;
import com.chengxuunion.projectportal.common.util.PathUtils;
import com.chengxuunion.projectportal.common.util.SessionUtils;
import com.chengxuunion.util.file.FileUtils;
import com.chengxuunion.util.id.IdGenerator;
import com.chengxuunion.util.image.ImageUtils;
import com.chengxuunion.util.security.RSAUtils;
import com.chengxuunion.util.security.SecurityUtils;
import com.chengxuunion.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * @Author youpanpan
 * @Description:    用户服务实现
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Value("${headphoto.dir}")
    private String headPhotoDir;

    @Value("${default.password}")
    private String defaultPassword;

    @Override
    public PageResult<User> listUserPage(UserPageParam userPageParam) {
        PageHelper.startPage(userPageParam.getPageNum() , userPageParam.getPageSize());
        List<User> userList = userDao.listUser(userPageParam);
        //得到分页的结果对象
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        return new PageResult<User>(userPageParam, pageInfo.getTotal(), userList);
    }

    /**
     * 根据主键查询单个用户对象
     *
     * @param id 主键
     * @return  单个用户对象
     */
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByEmail(String account) {
        return userDao.getUserByEmail(account);
    }

    /**
     * 保存用户对象
     *
     * @param user 用户对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveUser(User user) {
        user.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        user.setCreateDate(nowDate);
        user.setUpdateDate(nowDate);

        String headPhotoFilePath = "";
        try {
            headPhotoFilePath = ImageUtils.generateImage(headPhotoDir, user.getUserName());
        } catch (IOException e) {
            throw new RuntimeException("生成用户头像发生异常", e);
        }

        user.setHeadPhoto(headPhotoFilePath);

        return userDao.saveUser(user);
    }

    /**
     * 更新用户对象
     *
     * @param user 用户对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateUser(User user) {
        if (!SessionUtils.isAdmin() && !SessionUtils.isCurrentUser(user.getId())) {
            throw new RuntimeException("非法操作");
        }
        user.setUpdateDate(new Date());
        return  userDao.updateUser(user);
    }

    @Override
    public int updateUserPassword(UpdatePasswordParam updatePasswordParam) {
        User dbUser = getUser(SessionUtils.getUser().getId());
        try {
            String oldPassword = SecurityUtils.encryptMD5(new String(RSAUtils.decryptByPrivateKey(updatePasswordParam.getOldPassword().getBytes())));
            if (!StringUtils.isEquals(dbUser.getPassword(), oldPassword)) {
                throw new RuntimeException("旧密码不对");
            }

            String newPassword = SecurityUtils.encryptMD5(new String(RSAUtils.decryptByPrivateKey(updatePasswordParam.getNewPassword().getBytes())));
            User user = new User();
            user.setId(dbUser.getId());
            user.setPassword(newPassword);
            updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    @Override
    public int resetPassword(Long id) {
        User user = new User();
        user.setId(id);
        user.setPassword(SecurityUtils.encryptMD5(defaultPassword));
        user.setUpdateDate(new Date());
        return userDao.updateUser(user);
    }

    /**
     * 根据主键删除用户
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteUser(Long id) {
        User user = getUser(id);

        // 删除用户头像
        FileUtils.deleteFile(headPhotoDir + File.separator + user.getHeadPhoto());


        return  userDao.deleteUser(id);
    }

    @Override
    public Map<String, Object> uploadHeadPhoto(MultipartFile file) {
        FilePath filePath = PathUtils.getFilePathWithDate(headPhotoDir, file.getOriginalFilename());
        try {
            FileUtils.saveFile(filePath.getFileFullPath(), file.getInputStream());
        } catch (IOException e) {
            logger.error("保存头像出现异常", e);
            return Collections.emptyMap();
        }

        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("headPhoto", filePath.getFilePath());
        return fileMap;
    }

    @Override
    public Boolean downloadHeadPhoto(Long id, HttpServletResponse response) {
        User user = getUser(id);
        if (user == null || StringUtils.isEmpty(user.getHeadPhoto())) {
            return false;
        }

        String filePath = headPhotoDir + File.separator + user.getHeadPhoto();
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        return DownloadUtil.download(response, filePath, id.toString()+".png");
    }
}

