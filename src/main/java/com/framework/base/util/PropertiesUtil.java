package com.framework.base.util;

import java.util.Properties;

/**
 * 读取应用配置文件公共类
 * 
 * @author 茹康
 *
 */
public class PropertiesUtil {

	private  Properties properties;

	public PropertiesUtil(String files) {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(files));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String name) {
		return properties.getProperty(name);
	}
}
