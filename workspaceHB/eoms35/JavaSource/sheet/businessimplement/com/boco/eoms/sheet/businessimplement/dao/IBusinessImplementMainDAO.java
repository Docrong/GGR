
package com.boco.eoms.sheet.businessimplement.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

public interface IBusinessImplementMainDAO extends IMainDAO {

    /**
     * 通过定单号获取工单
     *
     * @param orderSheetId 定单号
     * @return
     * @throws HibernateException
     */
    public BaseMain getMainByOrderSheetId(String orderSheetId);

}

