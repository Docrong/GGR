package com.boco.eoms.commons.statistic.base.mgr.impl;

import com.boco.eoms.commons.statistic.base.dao.ID2NameDAO;
import com.boco.eoms.commons.statistic.base.exception.Id2NameDAOException;
import com.boco.eoms.commons.statistic.base.exception.Id2NameStatException;
import com.boco.eoms.commons.statistic.base.mgr.IStatID2Name;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.Constants;

public class StatID2NameV30Impl implements IStatID2Name{
	private String ID2NameV30BeanId;
	
	public String id2Name(String id) throws Id2NameStatException {
		
		ID2NameDAO dao = (ID2NameDAO) ApplicationContextHolder.getInstance().getBean(ID2NameV30BeanId);
		
		String reString = Constants.ID_NO_NAME;
		try {
			reString = dao.id2Name(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reString;
		
	}
	
	public String idType2Name(String id, String type)
			throws Id2NameDAOException {
		
		ID2NameDAO dao = (ID2NameDAO) ApplicationContextHolder.getInstance().getBean(ID2NameV30BeanId);
		
		String reString = Constants.ID_NO_NAME;
		try {
			reString = dao.idType2Name(id,type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reString;
	}

	public String getID2NameV30BeanId() {
		return ID2NameV30BeanId;
	}

	public void setID2NameV30BeanId(String nameV30BeanId) {
		ID2NameV30BeanId = nameV30BeanId;
	}
}
