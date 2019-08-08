// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ICommonFaultMainDAO.java

package com.boco.eoms.sheet.commonfault.dao;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.*;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface ICommonFaultMainDAO extends IMainDAO {

    public abstract List showInvokeRelationShipList(String s)
            throws SheetException;

    public abstract BaseLink getHasInvokeBaseLink(String s)
            throws SheetException;

    public abstract TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(
            String s) throws SheetException;

    public abstract BaseMain getMainByAlarmId(String s);

    public abstract BaseMain loadSinglePO(String s, Object obj)
            throws HibernateException;

    public abstract BaseMain getMainBySheetId(String s, Object obj);

    public abstract List getMainByLink(Map map);

    public abstract List getMainList(Map map);

    public abstract Integer getCountUndoForCheck(Object obj)
            throws HibernateException;

    public abstract List getListUndoForCheck(Integer integer, Integer integer1,
                                             Object obj) throws HibernateException;

    public abstract Integer getCountDoneForCheck(Object obj)
            throws HibernateException;

    public abstract List getListDoneForCheck(Integer integer, Integer integer1,
                                             Object obj) throws HibernateException;

    public abstract List getHoldedSheetListByTime(Object obj, String s,
                                                  String s1);

    public List getListAlarm(final String mainId, final String mainAlarmState)
            throws HibernateException;


    /**
     * 删除未生成工单的数据
     *
     * @return
     * @throws HibernateException
     */
    public void DeleteEarlyEmptyMain();

    /**
     * 查询即将超市的工勍
     *
     * @return
     * @throws HibernateException
     */
    public List getListOverTime(final String residualHour,
                                final String startTime) throws HibernateException;

    /**
     * 通过ID得到工单状态标示（张晓杰添加）
     *
     * @param id
     * @return
     */
    public Integer getStatusById(final String id);

    /**
     * 通过Sheetid得到工单状态标示（张晓杰添加）
     *
     * @param sheetId
     * @return
     */
    public Integer getStatusBySheetId(final String sheetId);

    /**
     * 创建者：张晓杰
     * 功能：根据ID查询Main表信息
     *
     * @param id
     * @return
     */
    public CommonFaultMain getCommonFaultMainById(final String id);

    /**
     * 创建者：张晓杰
     * 功能：根据ID查询Main表信息(可能为多个Main所以返回List)
     *
     * @param id
     * @return
     */
    public List getCommonFaultMainBySheetId(final String sheetId);

    public List getListByUserId(final String userId);

    public String getSecondExcuteHumTaskById(String id);
}
