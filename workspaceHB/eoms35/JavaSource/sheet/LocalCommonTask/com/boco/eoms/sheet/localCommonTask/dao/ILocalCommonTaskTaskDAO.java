package com.boco.eoms.sheet.localCommonTask.dao;

import java.util.HashMap;

import org.hibernate.HibernateException;


/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.5
 */

public interface ILocalCommonTaskTaskDAO {
    /**
     * 根据count SQL语句查询
     *
     * @param queryCountStr
     * @return
     * @throws HibernateException
     */
    public Integer getCountTaskBySQL(final String queryCountStr) throws HibernateException;


    /**
     * 根据SQL语句查询任务信息, 并进行分页处理
     *
     * @param queryStr
     * @param curPage
     * @param pageSize
     * @return
     * @throws HibernateException
     */
    public HashMap getTaskListBySQL(final String queryStr, final Integer curPage, final Integer pageSize) throws HibernateException;
}