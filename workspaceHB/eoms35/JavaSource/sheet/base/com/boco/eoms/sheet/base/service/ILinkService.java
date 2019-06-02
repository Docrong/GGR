/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:31:16
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface ILinkService {

    /**
     * @see 获取具体的link
     * @param id
     * @return
     * @throws Exception
     */
    public BaseLink getSingleLinkPO(String id) throws Exception;

    /**
     * @see 根据sql语句获取link集合
     * @param sql
     * @return
     * @throws Exception
     */
    public List getLinksByMainId(String mainId) throws Exception;

    /**
     * @see 根据sql语句获取link集合
     * @param sql
     * @return
     * @throws Exception
     */
    public abstract List getLinksBySql(String sql) throws Exception;

    public ILinkDAO getLinkDAO();

    public void setLinkDAO(ILinkDAO linkDAO);

    public BaseLink getLinkObject();

    public void setLinkObject(BaseLink linkObject);

    /**
     * 保存link至db
     * 
     * @param obj
     *            orm对象
     */
    public String addLink(Object obj) throws Exception;
    
    /**
	 * 根据用户ID查找出他的所有模板（带分页）
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param userId
	 * @return sheets列表
	 * @throws SheetException
	 */
	public abstract List getDealTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal, String linkName, String codition) throws SheetException;



    public String getOperateRoleId(String operateRoleId);

    public String getOperateUserId(String operateRoleId);
    
    /**
	 * 根据task表里的id，查找link表的操作记录
	 * @param aiid
	 * @return
	 * @throws HibernateException
	 */
    public List getLinkOperateByAiid(String aiid, String linkName);
    
        /**
	 * 删除link对象
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
    
    public List getLinksBycondition(String condition, String linkName) ;
}