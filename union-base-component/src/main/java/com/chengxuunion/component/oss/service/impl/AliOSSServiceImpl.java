package com.chengxuunion.component.oss.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.chengxuunion.component.oss.exception.AliOSSException;
import com.chengxuunion.component.oss.model.AliOSSProperties;
import com.chengxuunion.component.oss.service.AliOSSService;

/**
 * 阿里云OSS服务实现
 * <pre>
 * 注：操作完成后，如果后续不再操作，必须执行close方法关闭连接，释放资源
 * </pre>
 *
 * @author yp2
 * @date 2018年12月7日
 * @version V1.0
 */
@Service
public class AliOSSServiceImpl implements AliOSSService {
	
	/**
	 * 日志记录对象
	 */
	private static Logger logger = LoggerFactory.getLogger(AliOSSServiceImpl.class);
	
	/**
	 * 阿里云OSS操作对象
	 */
	@Autowired
	private OSSClient ossClient;
	
	/**
	 * 阿里云OSS配置信息对象
	 */
	@Autowired
	private AliOSSProperties aliOSSProperties;

	@Override
	public void createBucket(String bucketName) throws AliOSSException {
		try {
			ossClient.createBucket(bucketName);
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void putFile(String fileName, InputStream inputStream) throws AliOSSException {
		try {
			ossClient.putObject(aliOSSProperties.getBucketName(), fileName, inputStream);
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void putFile(String fileName, File file) throws AliOSSException {
		try {
			ossClient.putObject(aliOSSProperties.getBucketName(), fileName, file);
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void putFilePart(String fileName, String localFileName, Callback callback) throws AliOSSException {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			// 指定上传的内容类型。
			meta.setContentType("text/plain");

			// 通过UploadFileRequest设置多个参数。
			UploadFileRequest uploadFileRequest = new UploadFileRequest(aliOSSProperties.getBucketName(), fileName);
			// 指定上传的本地文件。
			uploadFileRequest.setUploadFile(localFileName);
			// 指定上传并发线程数，默认为1。
			uploadFileRequest.setTaskNum(5);
			// 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
			uploadFileRequest.setPartSize(1 * 1024 * 1024);
			// 开启断点续传，默认关闭。
			uploadFileRequest.setEnableCheckpoint(true);
			// 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
			uploadFileRequest.setCheckpointFile("uploadFile.ucp");
			// 文件的元数据。
			uploadFileRequest.setObjectMetadata(meta);
			// 设置上传成功回调，参数为Callback类型。
			uploadFileRequest.setCallback(callback);
			
			// 断点续传上传。
			ossClient.uploadFile(uploadFileRequest);
		} catch (Throwable e) {
			logger.error("断点续传发生异常", e);
			throw new AliOSSException(e);
		}
		
	}

	@Override
	public InputStream getFile(String fileName) throws AliOSSException {
		try {
			OSSObject ossObject = ossClient.getObject(aliOSSProperties.getBucketName(), fileName);
			if (ossObject == null) {
				return null;
			}
			
			return ossObject.getObjectContent();
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void getFile(String fileName, File file) throws AliOSSException {
		try {
			ossClient.getObject(new GetObjectRequest(aliOSSProperties.getBucketName(), fileName), file);
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void deleteFile(String fileName) throws AliOSSException {
		try {
			ossClient.deleteObject(aliOSSProperties.getBucketName(), fileName);
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void deleteFile(List<String> fileNames) throws AliOSSException {
		try {
			ossClient.deleteObjects(new DeleteObjectsRequest(aliOSSProperties.getBucketName()).withKeys(fileNames));
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public boolean isExist(String fileName) throws AliOSSException {
		try {
			boolean found = ossClient.doesObjectExist(aliOSSProperties.getBucketName(), fileName);
			return found;
		} catch (OSSException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		} catch (ClientException e) {
			logger.error(e.getMessage(), e);
			throw new AliOSSException(e);
		}
	}

	@Override
	public void close() {
		ossClient.shutdown();
	}

}
