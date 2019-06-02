/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dao;

import java.util.List;
import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:27:35
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface ILinkDAO extends Dao{

	public abstract BaseLink loadSinglePO(String id, Object linkObject) throws HibernateException;

	public abstract List listAllLinkOfWorkSheet(String id, Object linkObject)
			throws HibernateException;

	public abstract List loadLinkOfStep(String processId, String stepId, Object linkObject)
			throws HibernateException;
	
	/**
	 * 根据用户ID查找出他的所有模板
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param userId, curPage, pageSize, aTotal , linkMain
	 * @return 
	 * @throws Exception
	 */
	public List getDealTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal, String linkName, String codition) throws HibernateException;
	
	/**
	 * 根据mainId取出派发给我的所有link对象，根据时间倒序排序
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param userId, curPage, pageSize, aTotal , linkMain
	 * @return 
	 * @throws Exception
	 */
	public List getSendToMeLink(String linkName,String mainId, String operateRoleId) throws HibernateException;
	
	/**
	 * 根据task表里的id，查找link表的操作记录
	 * @param aiid
	 * @return
	 * @throws HibernateException
	 */
	public List getLinkOperateByAiid(String aiid, String linkName) throws HibernateException;
	
	/**
	 * 保存或更新link对象
	 * @param aiid
	 * @return
	 * @throws HibernateException
	 */
    	public void saveOrUpdate(Object obj);
    	
    	 /**
	 *删除link对象
	 * @param aiid
	 * @return
	 * @throws HibernateException
	 */
    public void removeLink(Object obj);
    
	/**
	 * 清除当前session
	 */
    public void clearObjectOfCurrentSession();
	/**
	 * 当有两个相同标识不同实体时执行
	 */
    public void mergeObject(Object obj);
    
    /**
     * 根据条件查出link
     * @param condition
     * @return
     */
    public List getLinksBycondition(String condition, String linkName) ;
    
    
}
