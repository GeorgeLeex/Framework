package com.framework.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.framework.base.service.IBaseEntitySV;


public class BaseEntitySVImpl<T> extends SqlSessionDaoSupport implements IBaseEntitySV<T> {

	public static Logger logger = Logger.getLogger(BaseEntitySVImpl.class);

	public Class<T> entityClass = null;

	private String mapperName;

	/**
	 * 创建默认构造方法，以取得真正的泛型类型
	 * 
	 */
	@SuppressWarnings("unchecked")
	public BaseEntitySVImpl() {
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			entityClass = (Class<T>) parameterizedType[0];
			mapperName = entityClass.getName().replace("model", "mapper");
			// System.out.println("获得实体类:"+entityClass.getSimpleName());
		}

	}

	@Resource(name = "sqlSessionFactory")
	public void setSuperSqlSessionFactory(SqlSessionFactory sysSqlSessionFactory) {
		super.setSqlSessionFactory(sysSqlSessionFactory);
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public String getMapperName() {
		return this.mapperName + "Mapper";
	}

	// 保存实体对象
	public T insert(T entity) {
		getSqlSession().insert(mapperName + "Mapper.insertSelective", entity);
		return entity;
	}

	public int update(T entity) {

		return getSqlSession().update(mapperName + "Mapper.updateByPKSelective", entity);

	}

	public int deleteById(Object id) {

		return getSqlSession().delete(mapperName + "Mapper.deleteByPK", id);

	}

	public int deleteByListKeys(List<Object> list) {
		return getSqlSession().delete(mapperName + "Mapper.deleteByListKeys", list);
	}

	public T fetch(Object id) {
		return getSqlSession().selectOne(mapperName + "Mapper.selectByPK", id);

	}

	public List<T> findAll() {
		return getSqlSession().selectList(mapperName + "Mapper.selectAll", null);

	}
	

	public List<T> findList(List<Object> list) {

		return getSqlSession().selectList(mapperName + "Mapper.selectByListKeys", list);
	}

	public List<T> queryPage(Map<String, Object> condition) {
		return getSqlSession().selectList(mapperName + "Mapper.selectByCondition", condition);
	}
	
	

	public int count(Map<String, Object> condition) {

		int count = getSqlSession().selectOne(mapperName + "Mapper.count", condition);

		return count;

	}

	// @CacheEvict(value = "ehcache", allEntries = true)
	public T updateOrSave(T t, Object id) {

		if (null != fetch(id)) {

			update(t);

		} else {
			return insert(t);

		}

		return t;

	}

	public Integer selectMaxId() {
		return getSqlSession().selectOne(mapperName + "Mapper.selectMaxId");

	}

	public int deleteByCondition(Map<String, Object> condition) {
		return getSqlSession().delete(mapperName + "Mapper.deleteByCondition", condition);

	}

	public List<T> insertList(List<T> entities) {

		return getSqlSession().selectList(mapperName + "Mapper.insertBylist", entities);

	}

	public int updateByList(List<T> entities) {

		return getSqlSession().update(mapperName + "Mapper.updateByList", entities);
	}

	public int deleteByLogicList(List<Object> list) {
		return getSqlSession().update(mapperName + "Mapper.deleteByLogicList", list);
	}

	public int deleteByLogicId(Object id) {
		return getSqlSession().update(mapperName + "Mapper.deleteByLogicId", id);
	}
}
