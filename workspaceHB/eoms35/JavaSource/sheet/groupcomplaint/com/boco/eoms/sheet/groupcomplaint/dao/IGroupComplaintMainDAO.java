/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IGroupComplaintMainDAO extends IMainDAO {

    /**
     * 通过告警号获取工单
     *
     * @param alarmId    告警号
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public BaseMain getMainByAlarmId(String alarmId, Object mainObject);

    public BaseMain loadSinglePO(String id, Object obj) throws HibernateException;

    public List getMainByLink(Map map, Object mainObject, Object linkObject);

    public List getMainList(Map map, Object mainObject);

    /**
     * 待质检（后质检）的已归档工单数
     *
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public Integer getCountUndoForCheck(final Object mainObject) throws HibernateException;

    /**
     * 待质检（后质检）的已归档工单列表
     *
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public List getListUndoForCheck(final Integer curPage, final Integer pageSize, final Object mainObject) throws HibernateException;

    /**
     * 已质检（后质检）的已归档工单数
     *
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public Integer getCountDoneForCheck(final Object mainObject) throws HibernateException;

    /**
     * 待质检（后质检）的已归档工单列表
     *
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public List getListDoneForCheck(final Integer curPage, final Integer pageSize, final Object mainObject) throws HibernateException;

    public Map getListQuery(final String sql, final Integer num1, final Integer num2) throws HibernateException;
}
