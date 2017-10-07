package com.base;

import java.util.List;

public interface BaseService<T> {

	/**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
	public List<T> select(T t);
	
	
	/**
     * 获取所有实体对象
     *
     * @param record
     * @return
     */
	public List<T> selectAll();

	 /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
	public T selectByPrimaryKey(Integer key);
	
	
	/**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
	public int selectCount(T t);
	
	/**
     * 根据条件进行查询
     *
     * @param example
     * @return
     */
	public List<T> selectByCriteria(T t);
	
	 /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
	public int insert(T t);
	
	/**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
	public int update(T t);
	
	/**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
	public int updateByPrimaryKeySelective(T t);
	
	/**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
	public int deleteByPrimaryKey(Integer key);
	
	/**
     * 根据条件进行删除
     *
     * @param example
     * @return
     */
	public int deleteByCriteria(T t);
	
}
