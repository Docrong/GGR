package com.boco.eoms.commons.system.dept.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.IDeptMgr;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;

/**
 * 部门管理实现类
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 11, 2008 10:21:50 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class DeptMgrImpl implements IDeptMgr {

	/**
	 * 部门管理
	 */
	private ITawSystemDeptManager tawSystemDeptManager;

	/**
	 * 部门dao
	 */
	private TawSystemDeptDao tawSystemDeptDao;

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#createDept(TawSystemDept)
	 */
	public void createDept(TawSystemDept dept) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#getDept(String)
	 */
	public TawSystemDept getDept(String deptId) {
		return tawSystemDeptManager.getDeptinfobydeptid(deptId,
				Constants.NOT_DELETED_FLAG);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#getParentDept(String)
	 */
	public TawSystemDept getParentDept(String deptId) {
		TawSystemDept dept = this.getDept(deptId);
		return this.getDept(dept.getParentDeptid());
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#getRootDept()
	 */
	public List getRootDept() {
		return this.tawSystemDeptManager.getRootDept();
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listAllSubDept(String)
	 */
	public List listAllSubDept(String deptId) {
		return this.tawSystemDeptManager.getSubDepts(deptId);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listDeptOfArea(String)
	 */
	public List listDeptOfArea(String areaId) {
		return this.tawSystemDeptDao.listDeptOfArea(areaId);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listParentDept(String)
	 */
	public List listParentDept(String deptId) {
		return this.tawSystemDeptManager.getParentDepts(deptId);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listSubDept(String)
	 */
	public List listSubDept(String deptId) {
		return this.tawSystemDeptManager.getNextLevecDepts(deptId,
				Constants.NOT_DELETED_FLAG);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#mapDept(int, int,
	 *      String)
	 */
	public Map mapDept(Integer curPage, Integer pageSize, String condition) {
		return this.tawSystemDeptDao.getDepts(curPage, pageSize, condition);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#modifyDept(TawSystemDept)
	 */
	public void modifyDept(TawSystemDept dept) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#moveDept(String,
	 *      String)
	 */
	public void moveDept(String originDeptId, String targetDeptId) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#removeDept(TawSystemDept)
	 */
	public void removeDept(TawSystemDept dept) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#removeDepts(List)
	 */
	public void removeDepts(List depts) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#removeDepts(String[])
	 */
	public void removeDepts(String[] deptIds) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#users2Dept(String[],
	 *      String)
	 */
	public void users2Dept(String[] userIds, String deptId) {
		// TODO Auto-generated method stub

	}

	public void setTawSystemDeptManager(
			ITawSystemDeptManager tawSystemDeptManager) {
		this.tawSystemDeptManager = tawSystemDeptManager;
	}

	public void setTawSystemDeptDao(TawSystemDeptDao tawSystemDeptDao) {
		this.tawSystemDeptDao = tawSystemDeptDao;
	}

}
