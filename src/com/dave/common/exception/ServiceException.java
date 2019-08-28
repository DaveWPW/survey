package com.dave.common.exception;
/**
 * 自定义业务异常类
 * 
 * @author Dave2019
 *
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 8029523183323748146L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}