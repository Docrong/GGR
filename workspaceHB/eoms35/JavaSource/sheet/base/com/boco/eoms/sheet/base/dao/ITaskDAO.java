/*
 * Created on 2008-4-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.base.task.ITask;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ITaskDAO extends Dao {

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

    /**
     * 根据taskId获取task model对象
     *
     * @param id taskId
     * @return
     * @throws Exception
     */
    public ITask loadSinglePO(String id, Object obj) throws HibernateException;

    public ITask loadTaskPO(String sheetKey);

    public List loadTaskList(String sheetKey);

    /**
     * 根据sheetkey查找出该工单下的任务
     *
     * @param sheetKey
     * @return
     * @throws HibernateException
     */
    public List getTasksBySheetKey(String hql) throws HibernateException;

    /**
     * 查找子任务
     *
     * @param sheetKey
     * @return
     * @throws HibernateException
     */
    public List getAllSubTask(String hql) throws HibernateException;

    /**
     * 根据service层传过来的hql进行查询
     *
     * @param sheetKey
     * @return
     * @throws HibernateException
     */
    public List getTasksByHql(String hql) throws HibernateException;

    public abstract HashMap getTaskListByConditions(String s, String s1, Integer integer, Integer integer1)
            throws HibernateException;

}
