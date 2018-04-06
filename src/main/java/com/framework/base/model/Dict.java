package com.framework.base.model;

import java.io.Serializable;
import java.util.List;
public class Dict	implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;  //数据字典ID，自增长
	private String dictCode;  //编码
	private String dictName;  //字典名称
	private String dictLevel;  //字典级别,0.系统级 （不能删除）1.普通级（可以删除）
	private Long delFlag;  //删除标志
	private Long sort;  //排序
	private String createTime;  //创建时间
	private String editTime;  //编辑时间
	private Long createId;  //创建人ID
	private String createName;  //创建人名称
	private String dictDescription;  //数据字典说明
	private Long parentId;  //父ID
	
	private List<Dict> children;// 子集合
	private List<Dict> dictList;//字典键值对

	/**
	获取数据字典ID，自增长
	*/
	public Long getId(){
		return this.id;
	}
	/**
	赋值数据字典ID，自增长
	*/
	public void setId(Long id){
		this.id=id;
	}
	/**
	获取编码
	*/
	public String getDictCode(){
		return this.dictCode;
	}
	/**
	赋值编码
	*/
	public void setDictCode(String dictCode){
		this.dictCode=dictCode== null||dictCode.equals("") ? null : dictCode.trim();
	}
	/**
	获取字典名称
	*/
	public String getDictName(){
		return this.dictName;
	}
	/**
	赋值字典名称
	*/
	public void setDictName(String dictName){
		this.dictName=dictName== null||dictName.equals("") ? null : dictName.trim();
	}
	/**
	获取字典级别,0.系统级 （不能删除）1.普通级（可以删除）
	*/
	public String getDictLevel(){
		return this.dictLevel;
	}
	/**
	赋值字典级别,0.系统级 （不能删除）1.普通级（可以删除）
	*/
	public void setDictLevel(String dictLevel){
		this.dictLevel=dictLevel== null||dictLevel.equals("") ? null : dictLevel.trim();
	}
	/**
	获取删除标志
	*/
	public Long getDelFlag(){
		return this.delFlag;
	}
	/**
	赋值删除标志
	*/
	public void setDelFlag(Long delFlag){
		this.delFlag=delFlag;
	}
	/**
	获取排序
	*/
	public Long getSort(){
		return this.sort;
	}
	/**
	赋值排序
	*/
	public void setSort(Long sort){
		this.sort=sort;
	}
	/**
	获取创建时间
	*/
	public String getCreateTime(){
		return this.createTime;
	}
	/**
	赋值创建时间
	*/
	public void setCreateTime(String createTime){
		this.createTime=createTime== null||createTime.equals("") ? null : createTime.trim();
	}
	/**
	获取编辑时间
	*/
	public String getEditTime(){
		return this.editTime;
	}
	/**
	赋值编辑时间
	*/
	public void setEditTime(String editTime){
		this.editTime=editTime== null||editTime.equals("") ? null : editTime.trim();
	}
	/**
	获取创建人ID
	*/
	public Long getCreateId(){
		return this.createId;
	}
	/**
	赋值创建人ID
	*/
	public void setCreateId(Long createId){
		this.createId=createId;
	}
	/**
	获取创建人名称
	*/
	public String getCreateName(){
		return this.createName;
	}
	/**
	赋值创建人名称
	*/
	public void setCreateName(String createName){
		this.createName=createName== null||createName.equals("") ? null : createName.trim();
	}
	/**
	获取数据字典说明
	*/
	public String getDictDescription(){
		return this.dictDescription;
	}
	/**
	赋值数据字典说明
	*/
	public void setDictDescription(String dictDescription){
		this.dictDescription=dictDescription== null||dictDescription.equals("") ? null : dictDescription.trim();
	}
	/**
	获取父ID
	*/
	public Long getParentId(){
		return this.parentId;
	}
	/**
	赋值父ID
	*/
	public void setParentId(Long parentId){
		this.parentId=parentId;
	}
	public String toString() {
		 return " 数据字典ID，自增长:"+ getId()+ ",  编码:"+ getDictCode()+ ",  字典名称:"+ getDictName()+ ",  字典级别,0.系统级 （不能删除）1.普通级（可以删除）:"+ getDictLevel()+ ",  删除标志:"+ getDelFlag()+ ",  排序:"+ getSort()+ ",  创建时间:"+ getCreateTime()+ ",  编辑时间:"+ getEditTime()+ ",  创建人ID:"+ getCreateId()+ ",  创建人名称:"+ getCreateName()+ ",  数据字典说明:"+ getDictDescription()+ ",  父ID:"+ getParentId()+ ", ";
	}

	private Long oldVersion;  // 原版本号，防止并发修改数据
	/**
	获取 原版本号，防止并发修改数据
	*/
	public Long getOldVersion(){
		return this.oldVersion;
	}
	/**
	赋值 原版本号，防止并发修改数据
	*/
	public void setOldVersion(Long oldVersion){
		this.oldVersion=oldVersion;
	}
	
	public List<Dict> getChildren() {
		return children;
	}
	public void setChildren(List<Dict> children) {
		this.children = children;
	}
	public List<Dict> getDictList() {
		return dictList;
	}
	public void setDictList(List<Dict> dictList) {
		this.dictList = dictList;
	}
	
	
}