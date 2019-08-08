package com.boco.eoms.sheet.commonfaultcorrigendum.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

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

public interface ICommonfaultCorrigendumLinkDAO extends ILinkDAO {
    public List getLinksBycondition(String condition, String linkName) throws HibernateException;
}