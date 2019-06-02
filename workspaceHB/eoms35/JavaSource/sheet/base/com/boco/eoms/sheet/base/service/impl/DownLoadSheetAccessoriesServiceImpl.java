package com.boco.eoms.sheet.base.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.dao.IDownLoadSheetAccessiorsDAO;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;

public class DownLoadSheetAccessoriesServiceImpl implements
		IDownLoadSheetAccessoriesService {

	private IDownLoadSheetAccessiorsDAO dao;
	
	
	
	public IDownLoadSheetAccessiorsDAO getDao() {
		return dao;
	}



	public void setDao(IDownLoadSheetAccessiorsDAO dao) {
		this.dao = dao;
	}



	public List getSheetAccessoriesList(String sql) throws Exception {
		
		return this.dao.getSheetAccessoriesList(sql);
	}
	public void updateTasks(String sql) throws Exception {		
		this.dao.updateTasks(sql);
	}
	
	public boolean executeProcedure(String procedureSql) throws Exception {		
		return this.dao.executeProcedure(procedureSql);
	}



	public void batchExcuteSql(List list, Object obj, String str) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
