package com.boco.eoms.commons.system.myinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;

import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.myinfo.service.ITawSystemMyinfoManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivRegionManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-7-30 10:04:17
 * </p>
 * 
 * @author 龚玉峰
 * @version 1.0
 */
public class TawSystemMyinfoManager extends BaseManager implements
		ITawSystemMyinfoManager {
	// 注入
	TawSystemUserRefRoleDao tawSystemUserRefRoleDao;

	public TawSystemUserRefRoleDao getTawSystemUserRefRoleDao() {
		return tawSystemUserRefRoleDao;
	}

	public void setTawSystemUserRefRoleDao(
			TawSystemUserRefRoleDao tawSystemUserRefRoleDao) {
		this.tawSystemUserRefRoleDao = tawSystemUserRefRoleDao;
	}

	/*
	 * 根据用户id得到所有的角色
	 */
	public List getRoleByUserid(String userid) {

		List roleList = new ArrayList();

		try {

			roleList = tawSystemUserRefRoleDao.getRoleidByuserid(userid);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleList;
	}

	/*
	 * 根据用户id得到所有的角色
	 */
	public List getDeptByUserid(String userid) {

		List privList = new ArrayList();
		List deptList = new ArrayList();
		TawSystemDept tawSystemDept = null;
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		TawSystemPrivRegion tawSystemPrivRegion = null;
		try {
			privList = privBO
					.getPermissions(
							userid,
							com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
							com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT);
			if (privList.size() > 0) {
				for (int i = 0; i < privList.size(); i++) {

					tawSystemPrivRegion = (TawSystemPrivRegion) privList.get(i);
					tawSystemDept = mgr.getDeptinfobydeptid(tawSystemPrivRegion
							.getRegionid(), "0");
					deptList.add(tawSystemDept);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deptList;
	}

	/*
	 * 根据用户id得到所有的角色是否存在
	 * 
	 */
	public boolean isExist(String subRoleid, String userid) {
		boolean bool = false;
		ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		try {
			bool = mgr.isExist(subRoleid, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/*
	 * 为用户添加角色
	 * 
	 */
	public void addRole(String subRoleid, String userid, String roleType) {
		ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		try {
			mgr.updateSubRole(subRoleid, userid, roleType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 删除某用户的某角色
	 * 
	 */
	public void deleteRole(String subRoleid, String userid) {
		ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		try {
			mgr.deleteRole(subRoleid, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 為某用戶添加部門
	 * 
	 */
	public void addDept(String deptid, String userid) {
		TawSystemAssignBo tawSystemAssignBo = TawSystemAssignBo.getInstance();
		try {
			tawSystemAssignBo
					.savePrivRegions2(
							userid,
							com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
							deptid,
							com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 刪除某用戶的某個部門
	 * 
	 */
	public void deleteDept(String dept, String userid) {
		ITawSystemPrivRegionManager regionmanager = (ITawSystemPrivRegionManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivRegionManager");
		try {
			regionmanager.removedeptregion(userid, dept);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 判斷是否存在
	 * 
	 */
	public boolean isExistDept(String dept, String userid) {
		TawSystemAssignBo tawSystemAssignBo = TawSystemAssignBo.getInstance();
		boolean bool = tawSystemAssignBo.hasRegionsByObject(userid,
				com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
				dept,
				com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT);
		return bool;
	}
}
