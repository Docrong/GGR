package com.boco.eoms.sheet.circuitcontrol.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.circuitcontrol.dao.ICircuitControlLinkDAO;

/**
 * <p>
 * Title:电路调度申请工单
 * </p>
 * <p>
 * Description:电路调度申请工单
 * </p>
 * <p>
 * Sun Sep 29 16:51:15 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CircuitControlLinkDAOHibernate extends LinkDAO implements ICircuitControlLinkDAO {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }
}