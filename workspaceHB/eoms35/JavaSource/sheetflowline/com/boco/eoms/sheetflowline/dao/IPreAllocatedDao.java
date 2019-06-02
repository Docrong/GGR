package com.boco.eoms.sheetflowline.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheetflowline.model.PreAllocated;


public interface IPreAllocatedDao extends Dao{
	/**
	 * 保存字段
	 * @author liendan
	 *
	 */
	public void saveObject(PreAllocated object) throws HibernateException;
	/**
	 * 物理删除
	 * @param object
	 */
	public void deleteObject(PreAllocated object) throws HibernateException;
	/**
	 * 更新对象
	 * @param object
	 * @throws HibernateException
	 */
	public void updateObject(PreAllocated object) throws HibernateException;
	/**
	 * 获取所有预分配集合
	 * @return
	 * @throws HibernateException
	 */
	public Map listPreAllocated(Integer startIndex,Integer length) throws HibernateException;
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public PreAllocated getPreAllocated(String id) throws HibernateException;
	
	public Integer executeHsql(String hsql)throws HibernateException;
	/**
	 * 条件查询
	 * @param object
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Map listPreAllocated(Map object, Integer pageIndex, Integer pageSize)throws HibernateException;
	public List search(String mainNetSortOne,String mainNetSortTwo,String mainNetSortThree,String mainEquipmentFactory,String mainFaultResponseLevel,String currentTime)throws HibernateException;
	public List getLists(String hsql)throws HibernateException;
	
}
