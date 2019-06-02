package com.boco.eoms.commons.system.priv.bo;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;

/**
 * 用户权限的业务逻辑处理
 * 
 * @author panlong May 31, 2007 10:15:37 AM
 */
public class TawSystemUserAssignBo {

	public TawSystemUserAssignBo() {

	}

	private static TawSystemUserAssignBo instance = new TawSystemUserAssignBo();

	public static TawSystemUserAssignBo getInstance() {
		return instance;
	}

	/**
	 * 删除某用户对应权限的信息
	 * 
	 * @param userid
	 * @param menuid
	 */
	public void removeUsermenu(String userid) {

		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		assignmanager.removeUserAssignByUserid(userid);
	}

	/**
	 * 删除某用户对应的权限
	 * 
	 * @param userid
	 * @param menuid
	 */
	public void removeUserRefmenu(String userid, String menuid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		assignmanager.removeUserRefmenu(userid, menuid);
	}

	/**
	 * 根据用户ID获取用户当前的权限
	 * 
	 * @param userid
	 * @return
	 */
	public List getPrivUserMenu(final String userid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getPrivUserMenu(userid);
		return list;
	}

	/**
	 * 更新某用户对应权限的信息
	 * 
	 * @param userid
	 * @param menuid
	 * @param tawSystemPrivUserAssign
	 */
	public void updateUsermenu(String userid, String menuid,
			TawSystemPrivUserAssign tawSystemPrivUserAssign) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		assignmanager.updateUsermenu(userid, menuid, tawSystemPrivUserAssign);
	}

	/**
	 * 根据用户ID 一级菜单标志获取一级菜单
	 * 
	 * @param userid
	 * @param isone
	 * @return
	 */
	public List getOnelevMenus(String userid, String isone) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getOnelevMenus(userid, isone);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取菜单的所有子菜单
	 * 
	 * @param userid
	 * @param menutid
	 * @return
	 */
	public List getSonMenus(String userid, final String menutid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getSonMenus(userid, menutid);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取某菜单的所有父菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getFatherMenus(String userid, String menuid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getFatherMenus(userid, menuid);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取上一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getSuperiorMenus(String userid, String menuid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getSuperiorMenus(userid, menuid);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getLowerleveMenus(String userid, String menuid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getLowerleveMenus(userid, menuid);
		return list;
	}

	/**
	 * IN查询某用户的一些权限
	 * 
	 * @param userid
	 * @param menuids
	 * @return
	 */
	public List getUserMenuInserachs(String userid, String menuids) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getUserMenuInserachs(userid, menuids);
		return list;
	}

	/**
	 * 判断是用户是否已经有权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isExitsUserassign(String userid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");

		boolean flag = assignmanager.isExitsUserassign(userid);
		return flag;
	}

	/**
	 * 删除某个用户的权限后需要重新合并用户的权限
	 * 
	 * @param userid
	 */
	public void saveObjectPriv(String objectid) {
		ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivAssignManager");
		ArrayList objectprivlist = (ArrayList) assignmanager
				.getObjectPriv(objectid);
		try {
			if (objectprivlist != null && objectprivlist.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				String objecttype = "";
				for (int i = 0; i < objectprivlist.size(); i++) {
					if (i > 0) {
						buffer.append("'");
					}
					TawSystemPrivAssign privassign = (TawSystemPrivAssign) objectprivlist
							.get(i);
					buffer.append(privassign.getPrivid());
					objecttype = privassign.getAssigntype();
					if (i + 1 != objectprivlist.size()) {
						buffer.append("'");
						buffer.append(",");
					}
				}
				TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
						.getInstance();
				String str = buffer.toString();
				if (objecttype.equals(StaticVariable.PRIV_ASSIGNTYPE_USER)) {
					assignout.saveuserMenu(objectid, str);
				} else if (objecttype
						.equals(StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
					assignout.saveRolemenu(objectid, str);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this.getClass(), "删除用户后重新合并用户权限出错:"
					+ ex.getStackTrace());
		}
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
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");

		return assignmanager.getTawSystemUserAssign(userid, privid);
	}

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");

		return assignmanager.isHaveUrl(url, userid);
	}

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid, String urltype) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		return assignmanager.isHaveUrl(url, userid, urltype);
	}

}
