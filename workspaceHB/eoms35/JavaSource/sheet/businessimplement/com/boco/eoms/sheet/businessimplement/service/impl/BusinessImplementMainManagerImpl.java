
package com.boco.eoms.sheet.businessimplement.service.impl;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.businessimplement.dao.IBusinessImplementMainDAO;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementMainManager;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;

public class BusinessImplementMainManagerImpl extends MainService implements IBusinessImplementMainManager {

	/**
	 * 通过定单号获取工单
	 * @param orderSheetId 定单号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByOrderSheetId(String orderSheetId){
		IBusinessImplementMainDAO iBusinessImplementMainDAO = (IBusinessImplementMainDAO)this.getMainDAO();
		return iBusinessImplementMainDAO.getMainByOrderSheetId(orderSheetId);
	}


}
