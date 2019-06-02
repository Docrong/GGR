package com.boco.eoms.commons.demo.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.demo.model.TawDemoTable;
import com.boco.eoms.commons.demo.dao.TawDemoTableDao;
import com.boco.eoms.commons.demo.service.TawDemoTableManager;

public class TawDemoTableManagerImpl extends BaseManager implements
		TawDemoTableManager {
	private TawDemoTableDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawDemoTableDao(TawDemoTableDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawDemoTableManager#getTawDemoTables(com.boco.eoms.commons.demo.model.TawDemoTable)
	 */
	public List getTawDemoTables(final TawDemoTable tawDemoTable) {
		return dao.getTawDemoTables(tawDemoTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawDemoTableManager#getTawDemoTable(String
	 *      id)
	 */
	public TawDemoTable getTawDemoTable(final String id) {
		return dao.getTawDemoTable(new Long(id));
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawDemoTableManager#saveTawDemoTable(TawDemoTable
	 *      tawDemoTable)
	 */
	public void saveTawDemoTable(TawDemoTable tawDemoTable) {
		dao.saveTawDemoTable(tawDemoTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawDemoTableManager#removeTawDemoTable(String
	 *      id)
	 */
	public void removeTawDemoTable(final String id) {
		dao.removeTawDemoTable(new Long(id));
	}
}
