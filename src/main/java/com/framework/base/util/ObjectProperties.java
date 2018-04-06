package com.framework.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



public class ObjectProperties {
	
	private static Logger log=Logger.getLogger(ObjectProperties.class);
	
	/**
	 * 将对象的属性名和属性值放入map中
	 */
	public static Map<String, Object> getProperties(Object o){
		Map<String, Object> obj=new HashMap<String, Object>();
		List<String> fieldNames=getFieldNames(o);
		List<Object> fieldValues=getFieldValues(o);
		for(int i=0;i<fieldNames.size();i++){
			obj.put(fieldNames.get(i),fieldValues.get(i));
		}
		return obj;
	}
	
	/**
     * 获取对象属性名
     * */
	public static List<String> getFieldNames(Object o){
    	Field[] fields=o.getClass().getDeclaredFields();
    	 List<String> fieldNames = new ArrayList<String>();
    	for(int i=0;i<fields.length;i++){
//    		System.out.println(fields[i].getType());
    		fieldNames.add(fields[i].getName());
    	}
    	return fieldNames;
    }
	
	 /**
     * 获取对象属性的类型
     * */
	/**
	 * @param o
	 * @return
	 */
	public static List<Object>  getFieldType(Object o){
		List<Object> fieldTypes=new ArrayList<Object>();
		Field[] fields=o.getClass().getDeclaredFields();
		for(int i=0;i<fieldTypes.size();i++){
			fieldTypes.add(fields[i].getType());
		}
		return fieldTypes;
		
	}
	
    
    /**
    * 获取对象的所有属性值
    * 
     * */
	public static List<Object> getFieldValues(Object o){
		List<String> fieldNames=getFieldNames(o);
    	List<Object> value=new ArrayList<Object>();
    	for(int i=0;i<fieldNames.size();i++){
    		value.add(getFieldValueByName(fieldNames.get(i), o));
    	}
    	return value;
    }	
    
    /**
     * 根据对象的属性取出对应属性的值
	 * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
            String getter = "get" + firstLetter + fieldName.substring(1);  
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  
            return value;  
        } catch (Exception e) {  
            log.error(e.getMessage(),e);  
            return null;  
        }  
    } 

}
