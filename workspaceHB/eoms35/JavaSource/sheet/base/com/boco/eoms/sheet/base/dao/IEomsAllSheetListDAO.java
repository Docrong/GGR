package com.boco.eoms.sheet.base.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;

public interface IEomsAllSheetListDAO {
    /**
     * 得到所有待处理的任务，方式是通过Native Query的方式来动态查询视图
     *
     * @param hql
     * @param curPage
     * @param pageSize
     * @return
     * @throws HibernateException
     */
    public HashMap getAllTasksByHql(final String hql, final Integer curPage,
                                    final Integer pageSize) throws HibernateException;

    /**
     * 得到所有待处理的任务，方式是通过Native Query的方式来动态查询视图
     *
     * @param hql
     * @param curPage
     * @param pageSize
     * @return
     * @throws HibernateException
     */
    public HashMap getAllTasksCountByHql(final String hql) throws HibernateException;

    /**
     * 根据查询条件查询任务信息, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public abstract HashMap getTaskListByCondition(String hsql,
                                                   final Integer curPage, final Integer pageSize)
            throws HibernateException;
}
