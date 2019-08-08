package com.boco.eoms.sheet.plannconfirm.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.plannconfirm.dao.IPlannConfirmLinkDAO;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannConfirmLinkDAOHibernate extends LinkDAO implements IPlannConfirmLinkDAO {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }
}