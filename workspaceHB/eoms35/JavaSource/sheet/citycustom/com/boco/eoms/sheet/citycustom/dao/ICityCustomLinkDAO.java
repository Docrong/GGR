package com.boco.eoms.sheet.citycustom.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:地市集客业务工单
 * </p>
 * <p>
 * Description:地市集客业务工单
 * </p>
 * <p>
 * Fri Sep 28 14:06:48 CST 2012
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public interface ICityCustomLinkDAO extends ILinkDAO {
    public List getLinksBycondition(String condition, String linkName) throws HibernateException;
}