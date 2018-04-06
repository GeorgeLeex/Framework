package com.framework.base.util;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	private static CommonUtil pu;

	public static CommonUtil getInstacne() {
		if (pu == null) {
			pu = new CommonUtil();
			return pu;
		}
		return pu;
	}

	/**
	 * 将数据对象转换为json格式字符串，并发送给前端.
	 * 
	 * @param obj
	 * @param resp
	 */
	public static void sendJson(Object obj, HttpServletResponse resp) {
		String json = JsonUtil.toJson(obj);
		log.info("正在往前端发送数据......"+json);
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			log.error("数据发送失败!");
			e.printStackTrace();
		} finally {
			log.info("数据成功发送至前端......");
			out.println(json);
			out.flush();
			out.close();
		}
	}

	/**
	 * 将数据封装成json发送到前段页面 obj ,resp为必填 total为选填
	 */
	public static void sendJson(Object obj, HttpServletResponse resp, int total) {
		String json = null;
		// total为0，就意味着只是返回提示信息，如果不为0，则返回相关数据信息
		if (total == 0) {
			json = JsonUtil.toJson(obj);
		} else {
			json = "{\"total\":" + total + " , \"data\":"
					+ JsonUtil.toJson(obj) + "}";
		}
		if (json.length() > 100) {
			log.debug("ajax发送json数据：" + json.subSequence(0, 98) + "……");
		} else {

			log.debug("ajax发送json数据：" + json + "……");
		}
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			log.error("数据发送失败!");
			e.printStackTrace();
		} finally {
			out.println(json);
			out.flush();
			out.close();
		}
	}

	/**
	 * 响应文本信息.即text/plain.
	 * 
	 * @param text
	 * @param resp
	 */
	public static void sendText(String text, HttpServletResponse resp) {
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			log.error("数据发送失败!");
			e.printStackTrace();
		} finally {
			out.println(text);
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 获取session值。
	 * @param sessionId
	 * @param request
	 * @return
	 */
	public static Object getSessionValue(String sessionId,HttpServletRequest request){
		if(!StringUtil.isNullOrBlank(sessionId)){
			Object obj = request.getSession().getAttribute(sessionId);
			if(null != obj){
				return obj;
			}else{
				return null;
			}
		}
		return null;
	}

}
