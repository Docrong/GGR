package com.boco.eoms.workplan.mgr.impl;

import java.util.List;


import com.boco.eoms.workplan.dao.ITawwpLogShowDao;
import com.boco.eoms.workplan.mgr.ITawwpLogShowMgr;
import com.boco.eoms.workplan.model.TawwpNewLog;

public class TawwpLogShowMgrImpl implements ITawwpLogShowMgr{
	private ITawwpLogShowDao tawwpLogShowDao;
	public ITawwpLogShowDao getTawwpLogShowDao() {
		return tawwpLogShowDao;
	}

	public void setTawwpLogShowDao(ITawwpLogShowDao tawwpLogShowDao) {
		this.tawwpLogShowDao = tawwpLogShowDao;
	}

	public TawwpNewLog getOne(String id) {
		TawwpNewLog tawwpNewLog = new TawwpNewLog();
		tawwpNewLog = tawwpLogShowDao.getOne(id);
		return tawwpNewLog;
	}

	public List listTable() {
		List list = null;
		list = tawwpLogShowDao.listTable();
		return list;
	}

	public List listTableSearch(String sheetId, String timeStart, String timeEnd) {
		List list = null;
		list = tawwpLogShowDao.listTable(sheetId, timeStart, timeEnd);
		return list;
	}

}
