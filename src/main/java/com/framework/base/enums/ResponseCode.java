package com.framework.base.enums;

/**
 * 响应码枚举.
 * @author venro
 * @version V1.0
 * @date 2016年12月21日
 *
 */
public enum ResponseCode {
	/** 成功的响应码. */
	YES("YES",""),
	/** 没有绑定数据. */
	NOT_BIND("NOT_BIND",""),
	/** 失败的响应码. */
	NO("NO","ERROR"),
	/** 数据已经存在的响应码. */
	EXIST("001","该书本已存在"),
	/** 数据未存在的响应码. */
	NOTEXIST("NOTEXIST","数据不存在"),
	/** 成功的响应码. */
	OK("000","OK"),
	/** 用户名或者密码为空 */
	IS_NULL("E001","用户名或者密码为空"),
	/** 传入参数为空 */
	PRAM_IS_NULL("PRAM_IS_NULL","传入参数为空"),
	/** 查询结果为空 */
	RESULT_IS_NULL("RESULT_IS_NULL","查询结果为空"),
	/** 余额不足 */
	BALANCE_NOT_ENOUGH("BALANCE_NOT_ENOUGH","余额不足"),
	/** 错误 */
	ERROR("ERROR","错误"),
	/** 数据字典级别为系统级,不可删除  */
	E900("E100", "数据字典级别为系统级,不可删除"), 
	
	IS_NOT_MULTIPART("IS_NOT_MULTIPART","不是MultiPart");
	
	
	private String code;
	private String message;
	
	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
