package com.chengxuunion.component.mail.push.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.BatchSendMailRequest;
import com.aliyuncs.dm.model.v20151123.BatchSendMailResponse;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.chengxuunion.component.mail.push.model.MailPushResponse;
import com.chengxuunion.component.mail.push.service.AbstractMailPushService;
import com.chengxuunion.component.mail.push.service.MailPushService;

/**
 * 邮件推送服务实现
 *
 * @author yp2
 * @date 2018年12月9日
 * @version V1.0
 */
@Service
public class MailPushServiceImpl extends AbstractMailPushService implements MailPushService {
	
	private static Logger logger = LoggerFactory.getLogger(MailPushServiceImpl.class);

	@Override
	public MailPushResponse sendSingleMail(String receiver, String emailSubject, String emailContent) {
		MailPushResponse mailPushResponse = new MailPushResponse();
		
		try {
			IAcsClient client = getAcsClient();
			SingleSendMailRequest request = new SingleSendMailRequest();
			
			setVersion(request);
			request.setAccountName(mailPushProperties.getFrom());
			request.setFromAlias(mailPushProperties.getAlias());
			request.setAddressType(1);
			request.setTagName(mailPushProperties.getTagName());
			request.setReplyToAddress(true);
			request.setToAddress(receiver);
			request.setSubject(emailSubject);
			request.setHtmlBody(emailContent);
			
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
			mailPushResponse.setIsSuccess(true);
			mailPushResponse.setRequestId(httpResponse.getRequestId());
			mailPushResponse.setEnvId(httpResponse.getEnvId());
		} catch (Exception e) {
			logger.error("邮件推送服务，单一邮件发送出现异常", e);
			mailPushResponse.setIsSuccess(false);
		} 
		
		return mailPushResponse;
	}

	@Override
	public MailPushResponse sendBatchMail(String receiversName, String templateName) {
		MailPushResponse mailPushResponse = new MailPushResponse();
		
		try {
			IAcsClient client = getAcsClient();
			BatchSendMailRequest request = new BatchSendMailRequest();
			
			setVersion(request);
			request.setAccountName(mailPushProperties.getBatchFrom());
			request.setTemplateName(templateName);
			request.setAddressType(1);
			request.setTagName(mailPushProperties.getTagName());
			request.setReceiversName(receiversName);
			
			BatchSendMailResponse httpResponse = client.getAcsResponse(request);
			mailPushResponse.setIsSuccess(true);
			mailPushResponse.setRequestId(httpResponse.getRequestId());
			mailPushResponse.setEnvId(httpResponse.getEnvId());
		} catch (Exception e) {
			logger.error("邮件推送服务，批量邮件发送出现异常", e);
			mailPushResponse.setIsSuccess(false);
		} 
		
		return mailPushResponse;
	}

}
