
package com.boco.eoms.sheet.businessimplement.service;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;

public interface IBusinessImplementMainManager extends IMainService {
	
	/**
	 * 通过定单号获取工单
	 * @param orderSheetId 定单号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByOrderSheetId(String orderSheetId);
}

