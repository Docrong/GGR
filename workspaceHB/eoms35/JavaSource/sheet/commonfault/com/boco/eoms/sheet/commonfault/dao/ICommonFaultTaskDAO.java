/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ITaskDAO;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface ICommonFaultTaskDAO extends ITaskDAO {
    /**
     * 根据查询条件查询任务信息, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public abstract HashMap getCommonfaultTaskListByCondition(HashMap map,
                                                              String hsql, final Integer curPage, final Integer pageSize)
            throws HibernateException;

    /**
     * 根据count SQL语句查询
     *
     * @param queryCountStr
     * @return
     * @throws HibernateException
     */
    public Integer getCountTaskBySQL(final String queryCountStr)
            throws HibernateException;

    /**
     * 根据SQL语句查询任务信息, 并进行分页处理
     *
     * @param queryStr
     * @param curPage
     * @param pageSize
     * @return
     * @throws HibernateException
     */
    public HashMap getTaskListBySQL(final String queryStr,
                                    final Integer curPage, final Integer pageSize)
            throws HibernateException;

    /**
     * @param title  主题
     * @param mainId main表主键 针对故障工单,修改task记录的主题信息
     * @author qinmin 2009-07-29
     */
    public void updateTitleByMainId(String mainId, String title)
            throws HibernateException;

    public abstract HashMap getTasksomelengthListBySQL(String s, Integer integer, Integer integer1)
            throws HibernateException;

    /**
     * 根据sql分页查询
     * add by weichao 201501515
     *
     * @param queryStr
     * @param curPage
     * @param pageSize
     * @return
     * @throws HibernateException
     */
    public HashMap getTasksListBySQL(final String queryStr, final Integer curPage, final Integer pageSize)
            throws HibernateException;

    /**
     * add by liuyonggang 201700601 显示不同情况下的待办列表
     * add by weichao 201501515
     *
     * @param queryStr
     * @param curPage
     * @param pageSize
     * @return
     * @throws HibernateException
     */
    public HashMap getEeveundo(final String queryStr, final Integer curPage, final Integer pageSize)
            throws HibernateException;
}
