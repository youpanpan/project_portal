package com.chengxuunion.component.oss.exception;


/**
 * 阿里云OSS操作异常类
 *
 * @author yp2
 * @date 2018年12月7日
 * @version V1.0
 */
public class AliOSSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AliOSSException() {
		super();
	}

	public AliOSSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AliOSSException(String message, Throwable cause) {
		super(message, cause);
	}

	public AliOSSException(String message) {
		super(message);
	}

	public AliOSSException(Throwable cause) {
		super(cause);
	}


}
