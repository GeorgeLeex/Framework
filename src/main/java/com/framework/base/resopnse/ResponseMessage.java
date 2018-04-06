package com.framework.base.resopnse;

import com.framework.base.enums.ResponseCode;


/**
 * 响应报文.
 * @author CYC
 *
 * @param <T> 响应报文的数据参数类型
 */
public class ResponseMessage<T> {
	/** 响应头信息. */
	private Header header;
	/** 响应体. */
	private T rows;
	/** 分页信息. */
	private Integer total;
	
	public ResponseMessage() {
		header = new Header();
		header.setCode(ResponseCode.OK.getCode());
		header.setMessage(ResponseCode.OK.getMessage());
	}

	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public T getRows() {
		return rows;
	}
	public void setRows(T body) {
		this.rows = body;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
