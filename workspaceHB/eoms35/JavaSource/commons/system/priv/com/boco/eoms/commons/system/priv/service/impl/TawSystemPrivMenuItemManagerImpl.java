package com.boco.eoms.commons.system.priv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao;
import com.boco.eoms.commons.system.priv.exception.InvalidSelItemsException;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager;

public class TawSystemPrivMenuItemManagerImpl extends BaseManager implements
		ITawSystemPrivMenuItemManager {
	private TawSystemPrivMenuItemDao dao;

	private TawSystemPrivMenuCommonDao commDao;

	private TawSystemPrivAssignDao assigndao;

	private TawSystemPrivUserAssignDao userassigndao;

	private TawSystemPrivOperationDao operationdao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemPrivMenuItemDao(TawSystemPrivMenuItemDao dao) {
		this.dao = dao;
	}

	/**
	 * Set the commDao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemPrivMenuCommonDao(TawSystemPrivMenuCommonDao dao) {
		this.commDao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getTawSystemPrivMenuItems(com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem)
	 */
	public List getTawSystemPrivMenuItems(
			final TawSystemPrivMenuItem tawSystemPrivMenuItem) {
		return dao.getTawSystemPrivMenuItems(tawSystemPrivMenuItem);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getTawSystemPrivMenuItem(String
	 *      id)
	 */
	public TawSystemPrivMenuItem getTawSystemPrivMenuItem(final String id) {
		return dao.getTawSystemPrivMenuItem(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#saveTawSystemPrivMenuItem(TawSystemPrivMenuItem
	 *      tawSystemPrivMenuItem)
	 */
	public void saveTawSystemPrivMenuItem(
			TawSystemPrivMenuItem tawSystemPrivMenuItem) {

		dao.saveTawSystemPrivMenuItem(tawSystemPrivMenuItem);

	}

	/**
	 * 保存菜单项并更新父的ISLEAF为0
	 * 
	 * @param tawSystemPrivMenuItem
	 */
	public void saveMenuItemAndParentMenu(
			TawSystemPrivMenuItem tawSystemPrivMenuItem) {
		String parentcode = tawSystemPrivMenuItem.getParentcode();
		String privid = tawSystemPrivMenuItem.getMenuid();
		dao.saveTawSystemPrivMenuItem(tawSystemPrivMenuItem);

		// 除了一级菜单之外，保存时更新父节点的leaf
		if (!parentcode.equals(StaticVariable.AREA_DEFAULT_PARENTVAL)) {
			TawSystemPrivMenuItem parentMitem = getMenuItemByPrividAndCode(
					privid, parentcode);
			parentMitem.setIsLeaf(StaticVariable.AREA_DEFAULT_LEAFZERO);
			dao.saveTawSystemPrivMenuItem(parentMitem);
		}

	 
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#removeTawSystemPrivMenuItem(String
	 *      id)
	 */
	public void removeTawSystemPrivMenuItem(final String id) {
		dao.removeTawSystemPrivMenuItem(new String(id));
	}

	/**
	 * 
	 */
	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawSystemPrivMenuItems(curPage, pageSize, null);
	}

	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawSystemPrivMenuItems(curPage, pageSize, whereStr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getAllMenus()
	 */
	public List getAllMenus() {
		return dao.getAllMenus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getFirLevelMenus(java.lang.String)
	 */
	public List getFirLevelMenus(String privid) {
		return dao.getFirLevelMenus(privid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getMenuName(java.lang.String)
	 */
	public Object getMenuName(String privid) {
		return dao.getMenuName(privid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getNextLevelMenus(java.lang.String,
	 *      java.lang.String)
	 */
	public List getNextLevelMenus(String privid, String code) {
		return dao.getNextLevelMenus(privid, code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getSelfMenus(java.lang.String)
	 */
	public List getSelfMenus(String _strOwnerId) {
		return dao.getSelfMenus(_strOwnerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getSubMenuItems(java.util.List,
	 *      java.util.List)
	 */
	public List getSubMenuItems(List _objTotals, List _objExists)
			throws InvalidSelItemsException {
		List _objRet = dao.getSubMenuItems(_objTotals, _objExists);
		if (_objRet == null) {
			throw new InvalidSelItemsException();
		}
		return _objRet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getValidSubMenuItems(java.lang.String,
	 *      java.lang.String)
	 */
	public List getValidSubMenuItems(String _strUserId, String _strParentItemId) {
		return dao.getValidSubMenuItems(_strUserId, _strParentItemId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#getSpecMenuItems(java.lang.String)
	 */
	public List getSpecMenuItems(String privid) {
		return commDao.getSpecMenuItems(privid);
	}

	public void saveParentMenuItems(String privid, String _code) {
		dao.saveParentMenuItems(privid, _code);
	}

	public boolean checkIsExists(String privid, String _code) {
		return dao.checkIsExists(privid, _code);
	}

	public boolean hasChild(String privid, String _strItemId) {
		return commDao.hasChild(privid, _strItemId);
	}

	/**
	 * 根据PRIVID和CODE查询某菜单记录
	 * 
	 * @param privid
	 * @param code
	 * @return
	 */
	public TawSystemPrivMenuItem getMenuItemByPrividAndCode(String privid,
			String code) {
		return dao.getMenuItemByPrividAndCode(privid, code);
	}

	/**
	 * 根据PRIVID和CODE删除CODE的属于子节点
	 * 
	 * @param privid
	 * @param code
	 */
	public void removeMenuItemAndSon(String privid, String code) {
		commDao.removeMenuItemAndSon(privid, code);
		commDao.removeUserAssignPriv(privid, code);
	}

	public TawSystemPrivAssignDao getAssigndao() {
		return assigndao;
	}

	public void setAssigndao(TawSystemPrivAssignDao assigndao) {
		this.assigndao = assigndao;
	}

	public TawSystemPrivUserAssignDao getUserassigndao() {
		return userassigndao;
	}

	public void setUserassigndao(TawSystemPrivUserAssignDao userassigndao) {
		this.userassigndao = userassigndao;
	}

	public TawSystemPrivOperationDao getOperationdao() {
		return operationdao;
	}

	public void setOperationdao(TawSystemPrivOperationDao operationdao) {
		this.operationdao = operationdao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager#listTawSystemPrivMenuItems(java.lang.String)
	 */
	public List listTawSystemPrivMenuItems(String menuId) {
		return dao.listTawSystemPrivMenuItems(menuId);
	}

}
