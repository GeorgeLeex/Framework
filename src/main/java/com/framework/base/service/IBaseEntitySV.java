package	com.framework.base.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 数据库基本操作
 * 
 * @author venor
 * @date 2016年12月21日
 */
public interface IBaseEntitySV<T> {

	/**
	 * 插入数据，直接传对象，如果对象有为空的值，则不会插入
	 */
	public T insert(T entity);

	/**
	 * 数据批量插入，传入list实体对象
	 */
	public List<T> insertList(List<T> entities);

	/**
	 * 修改实体对象
	 */
	public int update(T entity);

	/**
	 * 批量修改
	 */
	public int updateByList(List<T> list);// 批量选择性更新

	/**
	 * 物理删除，根据主键id删除
	 */
	public int deleteById(Object id);

	/**
	 * 根据复杂条件删除，物理删除
	 */
	public int deleteByCondition(Map<String, Object> condition);

	/**
	 * 根据主键集合删除
	 */
	public int deleteByListKeys(List<java.lang.Object> list);

	/**
	 * 根据主键查询，返回对象
	 */
	public T fetch(Object id);

	/**
	 * 根据主键集合查询，返回对象集合
	 */
	public List<T> findList(List<java.lang.Object> list);

	/**
	 * 查询所有非删除数据 ，返回对象集合
	 */
	public List<T> findAll();

	/**
	 * 分页条件查询，返回对象集合
	 */
	public List<T> queryPage(Map<String, Object> condition);

	/**
	 * 条件查询，返回实际数量
	 */

	public int count(Map<String, Object> condition);



	/**
		   * 
		   */
	public Integer selectMaxId();

	public T updateOrSave(T t, Object id);

	/**
	 * 按主键集合，批量删除，逻辑删除，将数据标为删除状态
	 */
	public int deleteByLogicList(List<Object> list);// 逻辑批量删除

	/**
	 * 按主键，逻辑删除，将数据标为删除状态
	 */
	public int deleteByLogicId(Object id);// 逻辑删除

}
