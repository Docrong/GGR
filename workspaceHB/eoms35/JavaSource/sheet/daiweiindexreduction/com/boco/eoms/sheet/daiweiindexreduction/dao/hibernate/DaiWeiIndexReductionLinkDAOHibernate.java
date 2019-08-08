package com.boco.eoms.sheet.daiweiindexreduction.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.daiweiindexreduction.dao.IDaiWeiIndexReductionLinkDAO;

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

public class DaiWeiIndexReductionLinkDAOHibernate extends LinkDAO implements IDaiWeiIndexReductionLinkDAO {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }
}