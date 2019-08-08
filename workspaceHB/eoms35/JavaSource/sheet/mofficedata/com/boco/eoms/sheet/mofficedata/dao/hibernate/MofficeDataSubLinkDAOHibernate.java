package com.boco.eoms.sheet.mofficedata.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataSubLinkDAO;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataSubLink;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public class MofficeDataSubLinkDAOHibernate extends BaseSheetDaoHibernate implements IMofficeDataSubLinkDAO {

    public List getSubLinksByHql(String hql) throws HibernateException {
        return getHibernateTemplate().find(hql);
    }

    public void saveOrUpdate(MofficeDataSubLink obj) throws HibernateException {
        getHibernateTemplate().saveOrUpdate(obj);
    }

}