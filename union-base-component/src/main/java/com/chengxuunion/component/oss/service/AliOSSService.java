package com.chengxuunion.component.oss.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.aliyun.oss.model.Callback;
import com.chengxuunion.component.oss.exception.AliOSSException;

/**
 * 阿里云OSS服务接口
 *
 * @author yp2
 * @date 2018年12月7日
 * @version V1.0
 */
public interface AliOSSService {
	
	/**
	 * 创建存储空间
	 * 
	 * @param bucketName	存储空间名称
	 * @throws AliOSSException
	 */
	void createBucket(String bucketName) throws AliOSSException;

	/**
	 * 上传文件
	 * 
	 * @param fileName	文件名称（包含路径）
	 * @param inputStream	流（文件、字节流等）
	 * @throws AliOSSException
	 */
	void putFile(String fileName, InputStream inputStream) throws AliOSSException;
	
	/**
	 * 上传文件
	 * 
	 * @param fileName	文件名称（包含路径）
	 * @param file	文件对象, 如果指定的本地文件存在会覆盖，不存在则新建。
	 * @throws AliOSSException
	 */
	void putFile(String fileName, File file) throws AliOSSException;
	
	/**
	 * 断点续传文件
	 * 
	 * @param fileName	文件名称（包含路径）
	 * @param localFileName	本地文件名称
	 * @param callback	成功回调
	 * @throws AliOSSException
	 */
	void putFilePart(String fileName, String localFileName, Callback callback) throws AliOSSException;
	
	/**
	 * 下载文件
	 * 
	 * @param fileName	文件名称（包含路径）
	 * @return
	 * @throws AliOSSException
	 */
	InputStream getFile(String fileName) throws AliOSSException;
	
	/**
	 * 下载文件到本地文件对象中，如果指定的本地文件存在会覆盖，不存在则新建
	 * 
	 * @param fileName	OSS中的文件名称（包含路径）
	 * @param file	要保存的文件对象
	 * @throws AliOSSException
	 */
	void getFile(String fileName, File file) throws AliOSSException;
	
	/**
	 * 删除文件
	 * 
	 * @param fileName	文件名称（包含路径）
	 * @throws AliOSSException
	 */
	void deleteFile(String fileName) throws AliOSSException;
	
	/**
	 * 删除多个文件
	 * 
	 * @param fileNames	要删除的文件列表
	 * @throws AliOSSException
	 */
	void deleteFile(List<String> fileNames) throws AliOSSException;
	
	/**
	 * 查询指定的文件是否存在，如果存在则返回true，否则返回false
	 * 
	 * @param fileName	文件名称（包含路径）
	 * @return
	 * @throws AliOSSException
	 */
	boolean isExist(String fileName) throws AliOSSException;
	
	/**
	 * 关闭连接对象
	 */
	void close();
}
