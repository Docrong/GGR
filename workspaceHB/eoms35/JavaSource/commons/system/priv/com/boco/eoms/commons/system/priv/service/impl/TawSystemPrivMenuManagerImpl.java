package com.boco.eoms.commons.system.priv.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;

public class TawSystemPrivMenuManagerImpl extends BaseManager implements
		ITawSystemPrivMenuManager {
	private TawSystemPrivMenuDao dao;

	private TawSystemPrivMenuCommonDao commDao;

	/**
	 * Set the commDao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemPrivMenuCommonDao(TawSystemPrivMenuCommonDao dao) {
		this.commDao = dao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemPrivMenuDao(TawSystemPrivMenuDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#getTawSystemPrivMenus(com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu)
	 */
	public List getTawSystemPrivMenus(final TawSystemPrivMenu tawSystemPrivMenu) {
		return dao.getTawSystemPrivMenus(tawSystemPrivMenu);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#getTawSystemPrivMenu(String
	 *      id)
	 */
	public TawSystemPrivMenu getTawSystemPrivMenu(final String id) {
		return dao.getTawSystemPrivMenu(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#saveTawSystemPrivMenu(TawSystemPrivMenu
	 *      tawSystemPrivMenu)
	 */
	public void saveTawSystemPrivMenu(TawSystemPrivMenu tawSystemPrivMenu) {
		dao.saveTawSystemPrivMenu(tawSystemPrivMenu);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#removeTawSystemPrivMenu(String
	 *      id)
	 */
	public void removeTawSystemPrivMenu(final String id) {
		dao.removeTawSystemPrivMenu(new String(id));
		commDao.removeAssignByMenuid(id);
		commDao.removeUserAssignByMenuid(id);
	}

	/**
	 * 查询用户所拥有的菜单方案
	 * 
	 * @param userid
	 * @return
	 */
	public List getPrivMenubyUserid(String userid) {

		return commDao.getPrivMenubyUserid(userid);
	}

	/**
	 * 
	 */
	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawSystemPrivMenus(curPage, pageSize, null);
	}

	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawSystemPrivMenus(curPage, pageSize, whereStr);
	}

	/**
	 * 验证菜单名称是否已经存在
	 * 
	 * @param menuname
	 * @return
	 */
	public boolean isExits(String menuname) {
		return commDao.isExits(menuname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#listMenu(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public List listMenu(String userId, String deptId, List roleIds) {
		return dao.listMenu(userId, deptId, roleIds);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#listMenu(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public List listMenu(String userId, String deptId, List roleIds,String privid) {
		return dao.listMenu(userId, deptId, roleIds,privid);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager#listMenu(java.lang.String,
	 *      java.lang.String)
	 */
	public List listMenu(String orgId, String orgType) {
		return dao.listMenu(orgId, orgType);
	}

}
