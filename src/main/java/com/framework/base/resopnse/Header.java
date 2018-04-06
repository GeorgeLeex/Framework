package com.framework.base.resopnse;

/**
 * 请求或响应报文头.
 * @author CYC
 *
 */
public class Header {
	/** 请求或响应码 .*/
	private String code;
	/** 请求或响应消息. */
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
