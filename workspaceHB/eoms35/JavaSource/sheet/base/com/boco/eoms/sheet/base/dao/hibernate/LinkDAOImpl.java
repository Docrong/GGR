/*
 * Created on 2007-8-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:LinkDAO基础方法，步骤列表
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-22 10:12:42
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class LinkDAOImpl extends LinkDAO {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
     */
    public List listAllLinkOfWorkSheet(String id, Object linkObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadLinkOfStep(java.lang.String,
     *      java.lang.String)
     */
    public List loadLinkOfStep(String processId, String stepId, Object linkObject)
            throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
     */
    public BaseLink loadSinglePO(String id, Object linkObject) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }

}
