package com.boco.eoms.sheet.numberapply.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.numberapply.model.NumberApplyHlrid;

public interface INumberApplyHlrDAO {
	
	/**
	 * 根据id获得HLRID信息
	 * @param id
	 * @return NumberApplyHlrid
	 */
	
	public NumberApplyHlrid getNumberApplyHlrid( final String id) throws HibernateException;
	
	/**
	 * 保存HLRID的单条记录
	 * @param NumberApplyHlrid
	 * @return void
	 */
	
	public void saveNumberApplyHlrid( NumberApplyHlrid  numberApplyHlrid) throws HibernateException;
	
	/**
	 *删除单条记录
	 * @param NumberApplyHlrid  
	 * @return void
	 */
	
	public void delNumberApplyHlrid(NumberApplyHlrid  numberApplyHlrid) throws HibernateException;
	
	/**
	 * 根据mainid获得该张工单在HLRID的所有信息
	 * @param mainid
	 * @return HashMap
	 */
	
	public HashMap getAllNumberApplyHlridByMainid( final  String mainid ,final  Integer pageSize, final Integer curPage) throws HibernateException;
	

	
}
