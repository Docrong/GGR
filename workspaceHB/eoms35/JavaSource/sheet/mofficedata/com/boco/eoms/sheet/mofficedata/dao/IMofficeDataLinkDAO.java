package com.boco.eoms.sheet.mofficedata.dao;

import java.util.List;

import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.ILinkDAO;

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

public interface IMofficeDataLinkDAO extends ILinkDAO {
    public List getLinksBySql(final String queryCountStr)
            throws HibernateException;
}