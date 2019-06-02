package com.boco.eoms.sheet.mofficedata.service.impl;

import java.util.List;

import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataBuisTypeDAO;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataBuisType;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataBuisTypeManager;

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
 * 
 */

public class MofficeDataBuisTypeManagerImpl implements IMofficeDataBuisTypeManager {

	private IMofficeDataBuisTypeDAO buisTypeDao;
	private MofficeDataBuisType buisTypeObject;
	
	public MofficeDataBuisType getBuisTypeObject() {
		return buisTypeObject;
	}

	public void setBuisTypeObject(MofficeDataBuisType buisTypeObject) {
		this.buisTypeObject = buisTypeObject;
	}

	public IMofficeDataBuisTypeDAO getBuisTypeDao() {
		return buisTypeDao;
	}

	public void setBuisTypeDao(IMofficeDataBuisTypeDAO buisTypeDao) {
		this.buisTypeDao = buisTypeDao;
	}

	public void saveOrUpdate(MofficeDataBuisType obj) throws Exception {
		buisTypeDao.saveOrUpdate(obj);

	}

	public List getBuisTypeObjects() throws Exception {
		return buisTypeDao.getBuisTypeObjects();
	}

	public List getBuisTypeObjectsByHql(String hql) throws Exception {
		return buisTypeDao.getBuisTypeObjectsByHql(hql);
	}

	public void clearData() throws Exception {
		buisTypeDao.clearData();
	}

	


}