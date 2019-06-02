package com.boco.eoms.commons.system.priv.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.bo.TawSystemUserAssignBo;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao;
import com.boco.eoms.commons.system.priv.exception.AuthorizationException;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

public class TawSystemPrivUserAssignManagerImpl extends BaseManager implements
		ITawSystemPrivUserAssignManager {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#getAssigns(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List getAssigns(String parentprivid, String userId, String hide) {
		return dao.getAssignsByConditions(parentprivid, userId, hide);
	}

	// private TawSystemPrivAssignDao tawSystemPrivAssignDao;
	/**
	 * 菜单方案赋权mgr
	 */
	private ITawSystemPrivAssignManager tawSystemPrivAssignManager;

	/**
	 * 菜单项操作
	 */
	private ITawSystemPrivMenuItemManager tawSystemPrivMenuItemManager;

	private TawSystemPrivUserAssignDao dao;

	private TawSystemPrivMenuCommonDao tawSystemPrivUserAssignJdbc;

	/**
	 * 用户角色关联manager
	 */
	private ITawSystemUserRefRoleManager tawSystemUserRefRoleManager;

	/**
	 * 菜单表
	 */
	private TawSystemPrivOperationDao tawSystemPrivOperationDao;

	/**
	 * 用户manager
	 */
	private ITawSystemUserManager tawSystemUserManager;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemPrivUserAssignDao(TawSystemPrivUserAssignDao dao) {
		this.dao = dao;
	}

	public void setTawSystemPrivMenuCommonDao(
			TawSystemPrivMenuCommonDao tawSystemPrivUserAssignJdbc) {
		this.tawSystemPrivUserAssignJdbc = tawSystemPrivUserAssignJdbc;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#getTawSystemPrivUserAssigns(com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign)
	 */
	public List getTawSystemPrivUserAssigns(
			final TawSystemPrivUserAssign tawSystemPrivUserAssign) {
		return dao.getTawSystemPrivUserAssigns(tawSystemPrivUserAssign);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#getTawSystemPrivUserAssign(String
	 *      id)
	 */
	public TawSystemPrivUserAssign getTawSystemPrivUserAssign(final String id) {
		return dao.getTawSystemPrivUserAssign(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#saveTawSystemPrivUserAssign(TawSystemPrivUserAssign
	 *      tawSystemPrivUserAssign)
	 */
	public void saveTawSystemPrivUserAssign(
			TawSystemPrivUserAssign tawSystemPrivUserAssign) {
		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#removeTawSystemPrivUserAssign(String
	 *      id)
	 */
	public void removeTawSystemPrivUserAssign(final String id) {
		dao.removeTawSystemPrivUserAssign(new String(id));
	}

	/**
	 * 
	 */
	public Map getTawSystemPrivUserAssigns(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawSystemPrivUserAssigns(curPage, pageSize, null);
	}

	public Map getTawSystemPrivUserAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawSystemPrivUserAssigns(curPage, pageSize, whereStr);
	}

	/**
	 * 更新某用户对应权限的信息
	 * 
	 * @param userid
	 * @param menuid
	 */
	public void removeUsermenu(final String userid) {

		List list = new ArrayList();
		list = dao.getPrivUserMenu(userid);
		if (list != null) {
			if (list.size() > 0) {
				TawSystemPrivUserAssign userassign = new TawSystemPrivUserAssign();
				for (int i = 0; i < list.size(); i++) {

					userassign = (TawSystemPrivUserAssign) list.get(i);
					dao.removeTawSystemPrivUserAssign(userassign.getId());
				}
				BocoLog.info(this, "删除用户:" + userid + " 的权限成功");
			}
		} else {
			BocoLog.info(this, " 您删除的用户:" + userid + " 的权限还没有被创建或者已被删除");
		}
	}

	/**
	 * 删除某用户对应的权限
	 * 
	 * @param userid
	 * @param menuid
	 */
	public void removeUserRefmenu(final String userid, final String menuid) {

		TawSystemPrivUserAssign userassign = new TawSystemPrivUserAssign();
		userassign = dao.getUserToPirvInfo(userid, menuid);

		if (userassign != null) {
			String privname = userassign.getCurrentprivname();
			dao.removeTawSystemPrivUserAssign(userassign.getId());
			BocoLog.info(this, "删除用户:" + userid + " 的权限:" + privname + "成功");
		} else {
			BocoLog.info(this, " 您删除用户:" + userid + " 的权限还没有被创建或者已被删除");
		}
	}

	/**
	 * 根据用户ID获取用户当前的用户权限
	 * 
	 * @param userid
	 * @return
	 */
	public List getPrivUserMenu(final String userid) {

		List list = new ArrayList();
		list = dao.getPrivUserMenu(userid);

		return list;
	}

	/**
	 * 更新某用户对应权限的信息
	 * 
	 * @param userid
	 * @param menuid
	 * @param tawSystemPrivUserAssign
	 */
	public void updateUsermenu(final String userid, final String menuid,
			TawSystemPrivUserAssign tawSystemPrivUserAssign) {

	}

	/**
	 * 根据用户ID 一级菜单标志获取一级菜单
	 * 
	 * @param userid
	 * @param isone
	 * @return
	 */
	public List getOnelevMenus(final String userid, final String isone) {
		List list = new ArrayList();
		list = dao.getOnelevMents(userid, isone);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取菜单的所有子菜单
	 * 
	 * @param userid
	 * @param menutid
	 * @return
	 */
	public List getSonMenus(final String userid, final String menutid) {
		List list = new ArrayList();
		list = dao.getSonMenus(userid, menutid);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取某菜单的所有父菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getFatherMenus(final String userid, final String menuid) {

		List list = new ArrayList();
		list = dao.getFatherMenus(userid, menuid);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取上一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getSuperiorMenus(final String userid, final String fmenuid) {
		List list = new ArrayList();
		list = dao.getSuperiorMenus(userid, fmenuid);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getLowerleveMenus(final String userid, final String menuid) {
		List list = new ArrayList();
		list = dao.getLowerleveMenus(userid, menuid);
		return list;
	}

	/**
	 * IN查询某用户的一些权限
	 * 
	 * @param userid
	 * @param menuids
	 * @return
	 */
	public List getUserMenuInserachs(final String userid, final String menuids) {
		List list = new ArrayList();
		list = dao.getUserMenuInserachs(userid, menuids);
		return list;
	}

	/**
	 * 判断某用户是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isExitsUserassign(String userid) {
		boolean flag = dao.isExitsUserassign(userid);
		return flag;
	}

	/**
	 * 根据用户ID 菜单ID 获取上一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public TawSystemPrivUserAssign getTawSystemUserAssign(final String userid,
			final String privid) {
		return dao.getTawSystemUserAssign(userid, privid);
	}

	/**
	 * 删除某用户的权限taw_system_priv_userassign
	 * 
	 * @param userid
	 */
	public void removeUserAssignByUserid(String userid) {
		tawSystemPrivUserAssignJdbc.removeUserAssignByUserid(userid);
	}

	public TawSystemPrivUserAssignDao getDao() {
		return dao;
	}

	public void setDao(TawSystemPrivUserAssignDao dao) {
		this.dao = dao;
	}

	public TawSystemPrivMenuCommonDao getTawSystemPrivUserAssignJdbc() {
		return tawSystemPrivUserAssignJdbc;
	}

	public void setTawSystemPrivUserAssignJdbc(
			TawSystemPrivMenuCommonDao tawSystemPrivUserAssignJdbc) {
		this.tawSystemPrivUserAssignJdbc = tawSystemPrivUserAssignJdbc;
	}

	/**
	 * 根据用户ID 菜单ID 父权限ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getNextUserPrivMenus(final String menuid, final String userid,
			String parentprivid) {
		return dao.getNextUserPrivMenus(menuid, userid, parentprivid);
	}

	/**
	 * 删除某用户的权限taw_system_priv_userassign
	 * 
	 * @param userid
	 */
	public void removeUserAssignByUseridAndPrivid(String userid,
			String currentprivid) {
		tawSystemPrivUserAssignJdbc.removeUserAssignByUseridAndPrivid(userid,
				currentprivid);
	}

	/**
	 * 根据权限ID 删除所有本身以及所有的子权限
	 * 
	 * @param privid
	 */
	public void removeMenuItemAndSonByPrivid(String privid, String menuid) {
		tawSystemPrivUserAssignJdbc
				.removeMenuItemAndSonByPrivid(privid, menuid);
	}

	/**
	 * 查询某菜单方案被分配给的用户
	 * 
	 * @param menuid
	 */
	public List getUserAssignByMenuid(String menuid) {

		return dao.getUserAssignByMenuid(menuid);
	}

	/**
	 * 根据USERID和是否一级菜单标志查询当前用户的一级菜单
	 * 
	 * @param userid
	 * @param isonepriv
	 * @return
	 */
	public List getOnePriv(String userid, String isonepriv) {
		return tawSystemPrivUserAssignJdbc.getOnePriv(userid, isonepriv);
	}

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid) {
		return dao.isHaveUrl(url, userid);
	}

	/**
	 * 根据用户ID 菜单ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getLowerleveMenusforJdbc(final String userid,
			final String menuid) {
		return tawSystemPrivUserAssignJdbc.getLowerleveMenus(userid, menuid);
	}

	/**
	 * 判断某菜单项是否已经被分配
	 * 
	 * @param privid
	 * @return
	 */
	public boolean isExitsPrivid(String privid) {
		return dao.isExitsPrivid(privid);
	}

	/**
	 * 修改用户已经拥有的菜单项
	 * 
	 * @param privid
	 */
	public void updateUserAssignByPrivid(String privid, String hide) {
		tawSystemPrivUserAssignJdbc.updateUserAssignByPrivid(privid, hide);
	}

	/**
	 * 修改用户已经拥有的菜单项 add by jiangcheng
	 * 
	 * @param _objOneOpt
	 */
	public void updateUserAssignByPrivid(TawSystemPrivOperation _objOneOpt) {
		dao.updateUserAssignByPrivid(_objOneOpt);
	}

	/**
	 * 更加菜单ID 和USERID 判断某用户是否拥有某功能项的权限
	 * 
	 * @param userid
	 * @param code
	 * @return
	 */
	public boolean hasCode(String userid, String code) {
		return dao.hasCode(userid, code);
	}

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid, String urltype) {
		return dao.isHaveUrl(url, userid, urltype);
	}

	/**
	 * 根据具体的菜单项获取人员的集合
	 * 
	 * @return
	 */
	public List getUserAssignByPrivid(String privId) {
		return dao.getUserAssignByPrivid(privId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#getAssignsByUrl(java.lang.String)
	 */
	public List getAssignsByUrl(String url) {
		return dao.getAssignsByUrl(url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#authorization(com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign)
	 */
	public void authorization(TawSystemPrivAssign tawSystemPrivAssign)
			throws AuthorizationException {

		// 为部门授权（赋予菜单）
		if (StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(tawSystemPrivAssign
				.getAssigntype())) {
			List users = this.tawSystemUserManager
					.listUsersByDeptId(tawSystemPrivAssign.getObjectid());
			if (users != null) {

				for (Iterator userIt = users.iterator(); userIt.hasNext();) {

					TawSystemUser user = (TawSystemUser) userIt.next();

					// 若菜单未被赋予则新赋予菜单方案,菜单方案有可能修改过，故此处不可用菜单方案判断是否有必要赋权
					if (!this.hasAssigned(tawSystemPrivAssign.getPrivid(), user
							.getUserid(), StaticVariable.PRIV_ASSIGNTYPE_USER)) {
						TawSystemPrivAssign assign = copyAssign4user(
								tawSystemPrivAssign, user.getUserid());
						this.tawSystemPrivAssignManager
								.saveTawSystemPrivAssign(assign);
					}

					List userAssigns = TawSystemPrivAssignOut
							.getInstance()
							.getMenubyCodeids(
									"'" + tawSystemPrivAssign.getPrivid() + "'");
					// 若用户赋权不为空（即之前可能用角色赋过值，则保存最终菜单权限
					if (userAssigns != null) {
						for (Iterator it = userAssigns.iterator(); it.hasNext();) {
							TawSystemPrivUserAssign userAssign = (TawSystemPrivUserAssign) it
									.next();
							
							if (!"admin".equals(user.getUserid())
									&& !this.isExistUserassign(userAssign
											.getMenuid(), userAssign
											.getCurrentprivid(), user
											.getUserid())) {
								userAssign.setUserid(user.getUserid());
								userAssign.setHrefTarget("mainFrame");
								userAssign.setOrderby("100");
								userAssign.setMenuid(tawSystemPrivAssign.getPrivid());
								// 保存userAssign
								this.saveTawSystemPrivUserAssign(userAssign);
							}
						}
					}

				}
			}
		}
		// 权限类型为用户
		else if (StaticVariable.PRIV_ASSIGNTYPE_USER.equals(tawSystemPrivAssign
				.getAssigntype())) {
			String menuid = tawSystemPrivAssign.getPrivid();
			String userid = tawSystemPrivAssign.getObjectid();

			TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
					.getInstance();
			// 若已存在菜单方案赋给某组织，则不做操作
			if (!hasAssigned(tawSystemPrivAssign.getPrivid(),
					tawSystemPrivAssign.getObjectid(), tawSystemPrivAssign
							.getAssigntype())) {
				this.tawSystemPrivAssignManager
						.saveTawSystemPrivAssign(tawSystemPrivAssign);
			}

			try {
				assignout.saveuserMenu(userid, menuid);
			} catch (TawSystemUserException e) {
				throw new AuthorizationException(e);
			}
		}
		// 权限类型为角色
		else if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(tawSystemPrivAssign
				.getAssigntype())) {
			String roleid = tawSystemPrivAssign.getObjectid();
			String _strMenuCode = tawSystemPrivAssign.getPrivid();
			TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
					.getInstance();
			// 若已存在菜单方案赋给某组织，则不做操作
			if (!hasAssigned(tawSystemPrivAssign.getPrivid(),
					tawSystemPrivAssign.getObjectid(), tawSystemPrivAssign
							.getAssigntype())) {
				this.tawSystemPrivAssignManager
						.saveTawSystemPrivAssign(tawSystemPrivAssign);
			}
			try {
				assignout.saveRolemenu(roleid, _strMenuCode);
			} catch (TawSystemUserException e) {
				throw new AuthorizationException(e);
			}

		}

	}

	/**
	 * 复制一个新的tawSystemPrivAssign对象
	 * 
	 * @param tawSystemPrivAssign
	 * @return
	 */
	private TawSystemPrivAssign copyAssign4user(
			TawSystemPrivAssign tawSystemPrivAssign, String userId) {
		TawSystemPrivAssign assign = new TawSystemPrivAssign();
		assign.setAssigntype(StaticVariable.PRIV_ASSIGNTYPE_USER);
		assign.setObjectid(userId);
		assign.setOperuserid(tawSystemPrivAssign.getOperuserid());
		assign.setPrivid(tawSystemPrivAssign.getPrivid());
		assign.setRemark(tawSystemPrivAssign.getRemark());
		return assign;
	}

	/**
	 * 将菜单项列表转换成菜单项的codes，如：'223','312'，首先判断该菜单是否已被赋予某人，之后再取codes
	 * 
	 * @param menuItems
	 *            菜单项列表
	 * @param menuId
	 *            菜单方案
	 * @param userId
	 *            用户id
	 * @return
	 */
	private String menuItems2menuCodes(List menuItems, String menuId,
			String userId) {
		StringBuffer menuCodes = new StringBuffer();
		if (menuItems != null) {
			for (Iterator it = menuItems.iterator(); it.hasNext();) {
				TawSystemPrivMenuItem menuItem = (TawSystemPrivMenuItem) it
						.next();
				// 若该用户未被分配此菜单方案的菜单项则分配，否则不做
				if (!isExistUserassign(menuId, menuItem.getCode(), userId)) {
					menuCodes.append("'" + menuItem.getCode() + "',");
				}
			}
		}
		String result = menuCodes.toString();
		// 若不为空去掉最后逗号
		if (!"".equals(result)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * 判断某个组织是否已被赋予某个菜单id
	 * 
	 * @param tawSystemPrivAssign
	 * @return
	 */
	public boolean hasAssigned(String menuId, String orgId, String type) {
		// TawSystemPrivAssign assign = this.tawSystemPrivAssignDao
		// .getTawSystemPrivAssign(tawSystemPrivAssign.getObjectid(),
		// tawSystemPrivAssign.getAssigntype(),
		// tawSystemPrivAssign.getPrivid());

		return this.tawSystemPrivAssignManager.hasAssign(orgId, type, menuId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#isExistUserassign(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public boolean isExistUserassign(String menuId, String menuItemCode,
			String userId) {
		TawSystemPrivUserAssign userassign = this.dao
				.getTawSystemPrivUserAssign(menuId, menuItemCode, userId);
		if (userassign == null || userassign.getId() == null
				|| "".equals(userassign.getId())) {
			return false;
		}
		return true;
	}

	/**
	 * @param tawSystemPrivMenuItemManager
	 *            the tawSystemPrivMenuItemManager to set
	 */
	public void setTawSystemPrivMenuItemManager(
			ITawSystemPrivMenuItemManager tawSystemPrivMenuItemManager) {
		this.tawSystemPrivMenuItemManager = tawSystemPrivMenuItemManager;
	}

	/**
	 * @param tawSystemUserManager
	 *            the tawSystemUserManager to set
	 */
	public void setTawSystemUserManager(
			ITawSystemUserManager tawSystemUserManager) {
		this.tawSystemUserManager = tawSystemUserManager;
	}

	/**
	 * @param tawSystemPrivOperationDao
	 *            the tawSystemPrivOperationDao to set
	 */
	public void setTawSystemPrivOperationDao(
			TawSystemPrivOperationDao tawSystemPrivOperationDao) {
		this.tawSystemPrivOperationDao = tawSystemPrivOperationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager#removeAuthorization(com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign)
	 */
	public void removeAuthorization(TawSystemPrivAssign tawSystemPrivAssign)
			throws AuthorizationException {
		TawSystemUserAssignBo userassignbo = TawSystemUserAssignBo
				.getInstance();
		if (tawSystemPrivAssign.getAssigntype().equals(
				StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
			TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
					.getInstance();
			this.tawSystemPrivAssignManager
					.removeTawSystemPrivAssign(tawSystemPrivAssign.getId());
			List list = this.tawSystemUserRefRoleManager
					.getUserbyroleid(tawSystemPrivAssign.getObjectid());
			if (list != null) {
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawSystemUser user = (TawSystemUser) it.next();
					tawSystemPrivAssignManager.removePrivassign(user
							.getUserid());
				}
			}
			try {
				assignout.removeRolePriv(tawSystemPrivAssign.getObjectid());
			} catch (TawSystemUserException e) {
				throw new AuthorizationException(e);
			}
			userassignbo.saveObjectPriv(tawSystemPrivAssign.getObjectid());
		} else if (tawSystemPrivAssign.getAssigntype().equals(
				StaticVariable.PRIV_ASSIGNTYPE_USER)) {

			userassignbo.removeUsermenu(tawSystemPrivAssign.getObjectid());
			tawSystemPrivAssignManager
					.removeTawSystemPrivAssign(tawSystemPrivAssign.getId());
			userassignbo.saveObjectPriv(tawSystemPrivAssign.getObjectid());
		}
	}

	/**
	 * @param tawSystemUserRefRoleManager
	 *            the tawSystemUserRefRoleManager to set
	 */
	public void setTawSystemUserRefRoleManager(
			ITawSystemUserRefRoleManager tawSystemUserRefRoleManager) {
		this.tawSystemUserRefRoleManager = tawSystemUserRefRoleManager;
	}

	/**
	 * @param tawSystemPrivAssignManager
	 *            the tawSystemPrivAssignManager to set
	 */
	public void setTawSystemPrivAssignManager(
			ITawSystemPrivAssignManager tawSystemPrivAssignManager) {
		this.tawSystemPrivAssignManager = tawSystemPrivAssignManager;
	}

}
