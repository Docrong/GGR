package com.boco.eoms.commons.demo.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.demo.model.TawPrefixTable;
import com.boco.eoms.commons.demo.dao.TawPrefixTableDao;
import com.boco.eoms.commons.demo.service.TawPrefixTableManager;

public class TawPrefixTableManagerImpl extends BaseManager implements
		TawPrefixTableManager {
	private TawPrefixTableDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawPrefixTableDao(TawPrefixTableDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawPrefixTableManager#getTawPrefixTables(com.boco.eoms.commons.demo.model.TawPrefixTable)
	 */
	public List getTawPrefixTables(final TawPrefixTable tawPrefixTable) {
		return dao.getTawPrefixTables(tawPrefixTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawPrefixTableManager#getTawPrefixTable(String
	 *      id)
	 */
	public TawPrefixTable getTawPrefixTable(final String id) {
		return dao.getTawPrefixTable(new Long(id));
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawPrefixTableManager#saveTawPrefixTable(TawPrefixTable
	 *      tawPrefixTable)
	 */
	public void saveTawPrefixTable(TawPrefixTable tawPrefixTable) {
		dao.saveTawPrefixTable(tawPrefixTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TawPrefixTableManager#removeTawPrefixTable(String
	 *      id)
	 */
	public void removeTawPrefixTable(final String id) {
		dao.removeTawPrefixTable(new Long(id));
	}
}
