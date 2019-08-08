package com.boco.eoms.sheet.circuitcontrol.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

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

public interface ICircuitControlLinkDAO extends ILinkDAO {
    public List getLinksBycondition(String condition, String linkName) throws HibernateException;
}