package com.boco.eoms.commons.system.role.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleDao;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public class TawSystemSubRoleManagerImpl extends BaseManager implements
		ITawSystemSubRoleManager {
	private TawSystemSubRoleDao dao;

	private TawSystemUserRefRoleDao userRoleDao;

	private TawSystemRoleDao roleDao;

	private TawSystemDeptDao deptDao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setDeptDao(TawSystemDeptDao dao) {
		this.deptDao = dao;
	}

	public void setRoleDao(TawSystemRoleDao dao) {
		this.roleDao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager#getTawSystemSubRoles(String
	 *      whereStr)
	 */
	public List getTawSystemSubRoles(final String whereStr) {
		return dao.getTawSystemSubRoles(whereStr);
	}

	/**
	 * @return Returns the userRoleDao.
	 */
	public TawSystemUserRefRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	/**
	 * @param userRoleDao
	 *            The userRoleDao to set.
	 */
	public void setUserRoleDao(TawSystemUserRefRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemSubRoleDao(TawSystemSubRoleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager#getTawSystemSubRoles(com.boco.eoms.commons.system.role.model.TawSystemSubRole)
	 */
	public List getTawSystemSubRoles(final long roleId) {
		return dao.getTawSystemSubRoles(roleId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager#getTawSystemSubRole(String
	 *      id)
	 */
	public TawSystemSubRole getTawSystemSubRole(final String id) {
		return dao.getTawSystemSubRole(id);
	}

	/**
	 * 保存子角色 Map userMap(userid,grouptype)
	 */
	public void saveTawSystemSubRole(TawSystemSubRole tawSystemSubRole,
			Map userMap) {
		dao.saveTawSystemSubRole(tawSystemSubRole);
		userRoleDao.removeUseridByroleid(tawSystemSubRole.getId());
		if (userMap == null)
			return;
		Iterator keys = userMap.keySet().iterator();
		while (keys.hasNext()) {
			String userId = StaticMethod.nullObject2String(keys.next()
					.toString());
			if (userId != "") {
				TawSystemUserRefRole refrole = new TawSystemUserRefRole();
				refrole.setSubRoleid(tawSystemSubRole.getId());
				refrole.setUserid(userId);
				refrole.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
				refrole.setGroupType(userMap.get(userId).toString());
				userRoleDao.saveTawSystemUserRefRole(refrole);
			}
		}
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager#saveTawSystemSubRole(TawSystemSubRole
	 *      tawSystemSubRole)
	 */
	public void saveTawSystemSubRole(TawSystemSubRole tawSystemSubRole) {
		dao.saveTawSystemSubRole(tawSystemSubRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager#removeTawSystemSubRole(String
	 *      id)
	 */
	public void removeTawSystemSubRole(final String id) {
		dao.removeTawSystemSubRole(id);
	}

	/**
	 * 
	 */
	public Map getTawSystemSubRoles(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawSystemSubRoles(curPage, pageSize, null);
	}

	public Map getTawSystemSubRoles(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawSystemSubRoles(curPage, pageSize, whereStr);
	}
	
	public Map mapTawSystemSubRoles(final Integer start,
			final Integer limit, final String whereStr) {
		return dao.mapTawSystemSubRoles(start, limit, whereStr);
	}

	public void createSubRolesByDept(final long roleId, final String deptId) {
		TawSystemRole role = roleDao.getTawSystemRole(roleId);
		List list = deptDao.getALLSondept(deptId, String
				.valueOf(StaticVariable.UNDELETED)); // 所有子部门

		deleteSubRolesByRoleId(roleId);// 删除原来所有子角色
		for (int i = 0; i < list.size(); i++) {
			TawSystemDept tawSystemDept = (TawSystemDept) list.get(i);
			dao.createSubRolesByDept(role, tawSystemDept);
		}
	}

	public void deleteSubRolesByRoleId(final long roleId) {
		dao.removeTawSystemSubRole(roleId);
	}

	public List getSubRolesByDeptId(final String deptId) {
		return dao.getSubRolesByDeptId(deptId);
	}

	/**
	 * 批量保存子角色
	 * 
	 * @param tawSystemSubRole
	 * @param isLimit
	 *            是否限制增加相同区分度的子角色，true不允许增加相同区分度的子角色，false允许
	 */
	public void saveTawSystemSubRoles(List subRoleList, boolean isLimit)
			throws Exception {
		//从area表里找到一级地市
		ITawSystemAreaManager areaMgr = (ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
		List areaList = areaMgr.getSonAreaByAreaId("-1");
		//判断所选择的是不是一级地市，如果是county和deptid都存
		//如果不是则county存选的值，然后在循环出一级地市的名称存到deptId
		//因为加字段的原因，需要找出subRole编辑的地方，然后取到county值，然后在存
		
		isLimit = false;
		for (int i = 0; i < subRoleList.size(); i++) {
			TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) subRoleList.get(i);
			
			String logo = "" + tawSystemSubRole.getDeptId()
					+ StaticMethod.null2String(tawSystemSubRole.getType1())
					+ StaticMethod.null2String(tawSystemSubRole.getType2())
					+ StaticMethod.null2String(tawSystemSubRole.getType3())
					+ StaticMethod.null2String(tawSystemSubRole.getType4())
					+ tawSystemSubRole.getRoleId();
			tawSystemSubRole.setLogo(logo);
			if (isLimit) {
				TawSystemSubRole subRole = dao.getTawSystemSubRoleByLogo(logo);
				if (subRole != null) {
					System.out.println("数据库中已存在区分度相同的角色，不能插入'"
							+ subRole.getSubRoleName() + "'");
					break;
				}
			}

			dao.saveTawSystemSubRole(tawSystemSubRole);
		}
	}

	public void deleteSubRoles(String subRoleIds) {
		String ids = "";
		if (subRoleIds.indexOf(",") > 0) {
			String[] subRoles = subRoleIds.split(",");
			for (int i = 0; i < subRoles.length; i++)
				ids += "'" + subRoles[i] + "',";

			if (ids.length() > 0)
				ids = ids.substring(0, ids.lastIndexOf(","));
		} else
			ids = "'" + subRoleIds + "'";
		List list = dao.getSubRolesByIds(ids);

		for (int i = 0; i < list.size(); i++) {
			TawSystemSubRole subRole = (TawSystemSubRole) list.get(i);
			// dao.removeObject(TawSystemSubRole.class,subRole.getId());
			dao.removeTawSystemSubRole(subRole.getId());
			userRoleDao.removeUseridByroleid(subRole.getId());
		}
	}

	/**
	 * 
	 * @param roleId
	 *            角色类别ID
	 * @param filterHash
	 *            key为TawSystemSubRole表中区分度对应的业务名称
	 * @return List<TawSystemSubRole>
	 * @throws Exception
	 */
	public List getSubRoles(String roleId, Hashtable filterHash)
			throws Exception {
		return dao.getSubRoles(roleId, filterHash);
	}

	/**
	 * 根据角色ID和USERID获取子角色列表
	 * 
	 * @param userId
	 * @param roleId
	 * @return <TawSystemSubRole>
	 */
	public List getSubRoles(String userId, String roleId) {
		return dao.getSubRoles(userId, roleId);
	}

	/**
	 * 部门树，根据部门ID和流程ID获取子角色
	 * 
	 * @param deptId
	 * @param systemId
	 * @return
	 */
	public List getSubRolesByDeptId(final String deptId, final String systemId,
			final String roleId) {
		if ((roleId == null || roleId == "")
				&& (systemId == null || systemId == ""))
			return dao.getSubRolesByDeptId(deptId);
		else if (roleId == null || roleId == "")
			return dao.getSubRolesByDeptId(deptId, systemId);
		else
			return dao.getSubRolesByDeptId(deptId, systemId, roleId);
	}

	/**
	 * 设置虚拟组的组长
	 * 
	 * @param groupId
	 *            虚拟组或子角色ID
	 * @param userId
	 */
	public void setLeader(String groupId, String userId) {
		List list = userRoleDao.getUserRefRoleBySubRoleid(groupId);
		for (int i = 0; i < list.size(); i++) {
			TawSystemUserRefRole ur = (TawSystemUserRefRole) list.get(i);
			if (ur.getUserid().equals(userId))
				ur.setGroupType(RoleConstants.groupType_leader);
			else
				ur.setGroupType(RoleConstants.groupType_common);
		}

	}

	/**
	 * 设置虚拟组的组长
	 * 
	 * @param groupId
	 *            虚拟组或子角色ID
	 * @param userId
	 */
	public void saveLeader(String groupId, String userId) throws Exception {
		try {
			// List list = userRoleDao.getUserRefRoleBySubRoleid(groupId);
			// for(int i=0;i<list.size();i++){
			// TawSystemUserRefRole ur = (TawSystemUserRefRole)list.get(i);
			// if(ur.getUserid().equals(userId))
			// ur.setGroupType(RoleConstants.groupType_leader);
			// else
			// ur.setGroupType(RoleConstants.groupType_common);
			// userRoleDao.saveTawSystemUserRefRole(ur);
			// }
			TawSystemUserRefRole leaderUser = userRoleDao
					.getRoleLeaderBySubRoleid(groupId);
			TawSystemUserRefRole currentUser = userRoleDao
					.getUserRefRoleByUserid(groupId, userId);
			if (currentUser != null) {
				if (leaderUser != null) {
					// 已经存在虚拟组组长
					String leaderUserId = StaticMethod
							.nullObject2String(leaderUser.getUserid());
					if (!leaderUserId.equals(userId)) {
						// 虚拟组组长不是当前用户，则需要修改当前组长以及当前用户的状态
						leaderUser.setGroupType(RoleConstants.groupType_common);
						userRoleDao.saveTawSystemUserRefRole(leaderUser);
						currentUser
								.setGroupType(RoleConstants.groupType_leader);
						userRoleDao.saveTawSystemUserRefRole(currentUser);
					}
				} else {
					// 当前虚拟组没设置组长，只需要修改当前用户的状态
					currentUser.setGroupType(RoleConstants.groupType_leader);
					userRoleDao.saveTawSystemUserRefRole(currentUser);
				}
			} else {
				throw new Exception("设置虚拟组组长报错，请联系管理员！");
			}
		} catch (Exception e) {
			throw new Exception("设置虚拟组组长报错，请联系管理员!");
		}
	}
	
	public boolean getSubRoleByRole(String roleId, String subRoleId) throws Exception {
		return dao.getSubRoleByRole(roleId,subRoleId);
	}
	
	/**
	 * 批量保存子角色（维护班组roleId的保存方法，会存入county字段的值）
	 * add by weichao 20130625
	 * @param tawSystemSubRole
	 * @param isLimit
	 *            是否限制增加相同区分度的子角色，true不允许增加相同区分度的子角色，false允许
	 */
	public void saveTawSystemSubRoles(List subRoleList, boolean isLimit,String areaid)
			throws Exception {
		//从area表里找到一级地市
		ITawSystemAreaManager areaMgr = (ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
		List areas = areaMgr.getSonAreaByAreaId(areaid);
		List areaList = new ArrayList();
		for(int n = 0; n < areas.size(); n++){
			TawSystemArea a = (TawSystemArea)areas.get(n);
			areaList.add(a.getAreaid());
		}
		
		//判断所选择的是不是一级地市，如果是county和deptid都存
		//如果不是则county存选的值，然后在循环出一级地市的名称存到deptId
		//因为加字段的原因，需要找出subRole编辑的地方，然后取到county值，然后在存
		
		isLimit = false;
		for (int i = 0; i < subRoleList.size(); i++) {
			TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) subRoleList.get(i);
			
			String logo = "" + tawSystemSubRole.getDeptId()
					+ StaticMethod.null2String(tawSystemSubRole.getType1())
					+ StaticMethod.null2String(tawSystemSubRole.getType2())
					+ StaticMethod.null2String(tawSystemSubRole.getType3())
					+ StaticMethod.null2String(tawSystemSubRole.getType4())
					+ tawSystemSubRole.getRoleId();
			tawSystemSubRole.setLogo(logo);
			String deptid = tawSystemSubRole.getDeptId();
			if(areaList.contains(deptid)){
				tawSystemSubRole.setCounty(deptid);
			}else{
				tawSystemSubRole.setCounty(deptid);		
				boolean flag = true;
				while(flag){
					TawSystemArea area = areaMgr.getAreaByAreaId(deptid);
					if(areaList.contains(area.getParentAreaid())){
						tawSystemSubRole.setDeptId(area.getParentAreaid());
						flag = false;
					}else{
						deptid = area.getParentAreaid();
					}
				}			
				
			}
			
			if (isLimit) {
				TawSystemSubRole subRole = dao.getTawSystemSubRoleByLogo(logo);
				if (subRole != null) {
					System.out.println("数据库中已存在区分度相同的角色，不能插入'"
							+ subRole.getSubRoleName() + "'");
					break;
				}
			}

			dao.saveTawSystemSubRole(tawSystemSubRole);
		}
	}
	
	public List getCountyByRoleId(String areaId, int roleId) {
		return dao.getCountyByRoleId(areaId, roleId);
	}
	
	public List listCountySubRole(String areaId, int roleId) {
		return dao.listCountySubRole(areaId, roleId);
	}
}
