package com.boco.eoms.sheet.focusresourceerrata.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public interface IFocusResourceErrataLinkDAO extends ILinkDAO {
    public List getLinksBycondition(String condition, String linkName) throws HibernateException;
}