/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IGroupComplaintMainManager extends IMainService {

	/**
	 * 通过告警号获取工单
	 * @param alarmId 告警号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByAlarmId(String alarmId);
	public BaseMain loadSinglePO(String id);
	/**
	 * 通过工单link表字段查找工单
	 * @param map <columnName,value>
	 * @param linkObject TODO
	 * @return
	 */
	public List getMainByLink(Map map, Object linkObject);
	/**
	 * 通过工单main表字段查找工单
	 * @param map
	 * @return
	 */
	public List getMainList(Map map);
	
	/**
	 * 待质检（后质检）的已归档工单数
	 * @return
	 * @throws HibernateException
	 * @throws SheetException 
	 */
	public Integer getCountUndoForCheck() throws HibernateException, SheetException;
	
	/**
	 * 待质检（后质检）的已归档工单列表
	 * @return
	 * @throws HibernateException
	 * @throws SheetException 
	 */	
	public List getListUndoForCheck(final Integer curPage, final Integer pageSize)throws HibernateException, SheetException;
	
	/**
	 * 已质检（后质检）的已归档工单数
	 * @return
	 * @throws HibernateException
	 * @throws SheetException 
	 */
	public Integer getCountDoneForCheck() throws HibernateException, SheetException;	
	
	/**
	 * 已质检（后质检）的已归档工单列表
	 * @return
	 * @throws HibernateException
	 * @throws SheetException 
	 */	
	public List getListDoneForCheck(final Integer curPage, final Integer pageSize)throws HibernateException, SheetException;
	
	/**
	 * 工单查询
	 * @return
	 * @throws HibernateException
	 * @throws SheetException 
	 */	
	public Map getListQuery(final String sql,final Integer pageSize,final Integer curPage)throws HibernateException, SheetException;
}
