package com.framework.base.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("baseEntityMapper")
public interface IBaseEntityMapper<T> {
	
	
	
       public  int deleteByPK(java.lang.Object id);//按主键单个删除
	   
	   public int deleteByListKeys(List<java.lang.Object> list);//批量删除
	   
	   public int insertSelective(T o);//单个选择性插入
	   
	   public int insertBylist(List<T> list);//批量插入
	   
	   public T selectByPK(java.lang.Object id);//单个查询
	 
	   
	   public List<T> selectAll();//查询表中所有数据
	   
	   public List<T> selectByListKeys(List<java.lang.Object> list);//根据主键列表查询
	   
	   public List<T> selectByCondition(Map<String, java.lang.Object> map);//根据条件查询，*经常使用
	   
	   
	   public int count(Map<String, java.lang.Object> map);//根据条件查询数量，一般用于分页

	   public int updateByPKSelective(T o);//单个选择性更新
	   
	   public int updateByList(List<T> list);//批量选择性更新
	   
	   public int deleteByLogicList(List<Object> list);//逻辑批量删除
	   
	   public int deleteByLogicId(Object id);//逻辑删除
	   

}
