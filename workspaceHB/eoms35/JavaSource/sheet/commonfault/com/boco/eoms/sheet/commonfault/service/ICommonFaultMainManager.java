// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ICommonFaultMainManager.java

package com.boco.eoms.sheet.commonfault.service;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.*;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;

import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;

public interface ICommonFaultMainManager
	extends IMainService
{

	public abstract List showInvokeRelationShipList(String s)
		throws SheetException;

	public abstract BaseLink getHasInvokeBaseLink(String s)
		throws SheetException;

	public abstract TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String s)
		throws SheetException;

	public abstract BaseMain getMainByAlarmId(String s);

	public abstract BaseMain loadSinglePO(String s);

	public abstract BaseMain getMainBySheetId(String s);

	public abstract List getMainByLink(Map map);

	public abstract List getMainList(Map map);

	public abstract Integer getCountUndoForCheck()
		throws HibernateException, SheetException;

	public abstract List getListUndoForCheck(Integer integer, Integer integer1)
		throws HibernateException, SheetException;

	public abstract Integer getCountDoneForCheck()
		throws HibernateException, SheetException;

	public abstract List getListDoneForCheck(Integer integer, Integer integer1)
		throws HibernateException, SheetException;

	public abstract List getHoldedSheetListByTime(String s, String s1);

	public abstract List getQueryResult(String as[], Map map, Integer integer, Integer integer1, int ai[], String s)
		throws SheetException;
	 /**
     * 添加者：张晓杰
     * 功能：通过工单号码，或是工单ID判断工单是否已归档()
     * @param id 如果id不为空则通过ID判断工单状态，否则按照sheetid判断
     * @param sheetId 如果参数ID为空，则用sheetid进行判断工单状态
     * @return true为已经归档，false为为归档
     */
      public boolean isHoldedBySheetId(String id, String sheetId);
      
      /**
       * 创建者：张晓杰
       * 功能：功过MAIN表ID查询工单所有信息。
       * @param id Main表ID0
       * @return id对应的Main表信息。
       */
      public CommonFaultMain getCommonFaultMainById(String id);
      
      /**
       * 创建者：张晓杰
       * 功能：功过MAIN表SHEET查询尚未处理的工单。
       * @param id Main表ID0
       * @return id对应的Main表信息。
       */
      public CommonFaultMain getCommonFaultMainBySheetId(String id);
      
      /**
       * 创建者：张晓杰
       * 功能：通过ID查询出当前Main信息，将ForeStr（例如：【追】）加至Title的前面。
       * @param mainId ID值
       * @param foreStr 前缀字符串
       * @return 加补前缀完成后的Title标题。
       */
      public String updateSheetMainTitle(String mainId, String foreStr);
      
      public abstract List getQueryListBySql(String s);
      
  	/**
  	 * 更新main表记录JDBC
  	 * @param sql
  	 */
  	public void updateMainBySql(String sql);
  	/**
  	 * 获取告警核查中的记录
  	 * @param condition
  	 * @param curPage
  	 * @param pageSize
  	 * @return
  	 */
  	public Map getCheckList(String condition, final Integer curPage, final Integer pageSize);
  	
//	public abstract String getListByUserId( String userId); 
	public abstract Map getQueryListByCondition(String sql, Integer pageIndex, Integer pageSize);

//  	public String getSecondExcuteHumTaskById(String id) throws SheetException;
  	
}
