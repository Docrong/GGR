package com.boco.eoms.sheet.daiweiindexreduction.dao;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

import java.util.List;

import org.hibernate.HibernateException;

/**
 * <p>
 * Title:代维公司指标核减流程
 * </p>
 * <p>
 * Description:代维公司指标核减流程
 * </p>
 * <p>
 * Tue Aug 01 17:34:54 CST 2017
 * </p>
 *
 * @author wangmingming
 * @version 1.0
 */

public interface IDaiWeiIndexReductionLinkDAO extends ILinkDAO {
    public List getLinksBycondition(String condition, String linkName) throws HibernateException;
}