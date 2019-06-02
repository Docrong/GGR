/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.service;

import java.util.*;

import com.boco.eoms.sheet.base.service.ITaskService;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICommonFaultTaskManager extends ITaskService{
	public abstract List getCommonfaultTasksByCondition(String s)
    throws Exception;

public abstract HashMap getAllTaskByUserid(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
	throws Exception;

public abstract HashMap getNoAcceptTaskByUserid(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
	throws Exception;

public abstract HashMap getNoDealTaskByUserid(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
	throws Exception;

public abstract HashMap getUndoTaskByOverTimeByNet(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
throws Exception;

/*
public abstract HashMap getUndoTaskByOverTimePerformace(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
throws Exception;*/
/**
 * add by weichao 20150515 根据SQL查找task列表
 * @param hsql
 * @param curPage
 * @param pageSize
 * @return
 * @throws HibernateException
 */
public abstract HashMap getTaskListByCondition(String hsql, Integer curPage, Integer pageSize) throws Exception;

/**
 * add by liuyonggang 201700601 显示不同情况下的待办列表
 * @param hsql
 * @param curPage
 * @param pageSize
 * @return
 * @throws HibernateException
 */
public abstract HashMap getEeveundo(String hsql, Integer curPage, Integer pageSize) throws Exception;
}
