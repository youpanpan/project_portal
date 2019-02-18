package com.chengxuunion.projectportal.business.user.model.request;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-29 10:19
 * @Modified By:
 */
public class UpdatePasswordParam {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
