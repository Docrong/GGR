
package com.boco.eoms.sheet.netdata.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

public interface INetDataLinkDAO extends ILinkDAO {
    /**
     * 根据operateType取得link记录
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public List getLinkbyOperateType(String sheetKey, String operateType) throws HibernateException;
}

