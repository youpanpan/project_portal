package com.chengxuunion.projectportal.business.email.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chengxuunion.projectportal.business.email.model.Email;

/**
 * 邮件DAO
 *
 * @author youpanpan
 * @date 2018年8月30日
 * @version V1.0
 */
@Repository
public interface EmailDao {

	/**
	 * 查询最近邮件列表
	 * 
	 * @param params
	 * @return
	 * @throws EmailException
	 */
	List<Email> queryRecentlyEmails(Map<String, Object> params);
	
	/**
	 * 添加邮件记录
	 * 
	 * @param email
	 * @return
	 * @throws EmailException
	 */
	int saveEmail(Email email);
	
	/**
	 * 更新邮件信息
	 * 
	 * @param email
	 * @return
	 * @throws EmailException
	 */
	int updateEmail(Email email);
	
}
