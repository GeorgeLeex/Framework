package com.framework.base.controller;

import org.codehaus.jackson.map.ObjectMapper;

import com.framework.base.enums.ResponseCode;


/**
 * Controller基础类型.
 * 
 * @author CYC
 * @version V1.0
 * @date 2015年4月21日
 *
 */
public abstract class BaseController {
	/** 响应标识(Y:OK,N:NO). */
	private ResponseCode respCode = ResponseCode.YES;
	/** 响应消息. */
	private String respMessage = "成功";
	/** json To Object的对象. */
	protected final ObjectMapper mapper = new ObjectMapper();

	/**
	 * getter 响应标识.
	 * 
	 * @return 响应标识
	 */
	protected final String getRespCode() {
		return respCode.name();
	}

	/**
	 * 响应标识.
	 * 
	 * @param code
	 *            响应标识
	 */
	protected final void setRespCode(final ResponseCode code) {
		respCode = code;
	}

	/**
	 * getter 响应消息.
	 * 
	 * @return 响应消息
	 */
	protected final String getRespMessage() {
		return respMessage;
	}

	/**
	 * setter 响应消息.
	 * 
	 * @param message
	 *            响应消息
	 */
	protected final void setRespMessage(final String message) {
		respMessage = message;
	}
}
