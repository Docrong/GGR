package com.boco.eoms.sheet.commonfaultcorrigendum.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.commonfaultcorrigendum.dao.ICommonfaultCorrigendumLinkDAO;

/**
 * <p>
 * Title:故障工单勘误流程
 * </p>
 * <p>
 * Description:故障工单勘误流程
 * </p>
 * <p>
 * Mon Sep 29 11:24:17 CST 2014
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CommonfaultCorrigendumLinkDAOHibernate extends LinkDAO implements ICommonfaultCorrigendumLinkDAO {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }
}