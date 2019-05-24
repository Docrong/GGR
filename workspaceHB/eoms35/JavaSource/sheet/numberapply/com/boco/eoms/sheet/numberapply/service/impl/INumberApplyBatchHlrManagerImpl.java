package com.boco.eoms.sheet.numberapply.service.impl;

import java.util.List;

import com.boco.eoms.sheet.numberapply.dao.INumberApplyBatchHlrDAO;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchHlrManager;

public class INumberApplyBatchHlrManagerImpl implements
		INumberApplyBatchHlrManager {
	
	private INumberApplyBatchHlrDAO numberApplyBatchHlrDAO;
	
	
	public INumberApplyBatchHlrDAO getNumberApplyBatchHlrDAO() {
		return numberApplyBatchHlrDAO;
	}

	public void setNumberApplyBatchHlrDAO(
			INumberApplyBatchHlrDAO numberApplyBatchHlrDAO) {
		this.numberApplyBatchHlrDAO = numberApplyBatchHlrDAO;
	}

	public List batchInsert(String mainid, List columnValue) throws Exception {
		return numberApplyBatchHlrDAO.batchInsert(mainid, columnValue);
	}

	public void batchPreUpdate(final String mainid) throws Exception {
		 numberApplyBatchHlrDAO.batchPreUpdate(mainid);
	}
	/**
	 * 根据拼成的sql进行更新
	 */
	public void batchUpdate(List sql) throws Exception {
		numberApplyBatchHlrDAO.batchUpdate(sql);
		
	}

	

}
