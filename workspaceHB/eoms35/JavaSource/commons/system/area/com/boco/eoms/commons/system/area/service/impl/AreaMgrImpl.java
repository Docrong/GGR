package com.boco.eoms.commons.system.area.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.commons.system.area.dao.TawSystemAreaDao;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.IAreaMgr;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 18, 2008 4:23:41 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */

public class AreaMgrImpl implements IAreaMgr {

	/**
	 * 区域dao
	 */
	private TawSystemAreaDao tawSystemAreaDao;

	/**
	 * 
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#getArea(java.lang.String)
	 */
	public TawSystemArea getArea(String areaId) {
		return tawSystemAreaDao.getAreaByAreaId(areaId);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#getAreaOfDept(java.lang.String)
	 */
	public TawSystemArea getAreaOfDept(String deptId) {
		return tawSystemAreaDao.getAreaOfDept(deptId);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#getAreaOfUser(java.lang.String)
	 */
	public TawSystemArea getAreaOfUser(String userId) {
		return tawSystemAreaDao.getAreaOfUser(userId);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#getAreaType(java.lang.String)
	 */
	public String getAreaType(String areaId) {
		return tawSystemAreaDao.getAreaByAreaId(areaId).getCapital();
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#getParentArea(java.lang.String)
	 */
	public TawSystemArea getParentArea(String areaId) {
		return tawSystemAreaDao.getAreaByAreaId(tawSystemAreaDao
				.getAreaByAreaId(areaId).getParentAreaid());
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#listAllChildArea(java.lang.String)
	 */
	public List listAllChildArea(String areaId) {
		TawSystemArea area = getArea(areaId);
		List result = new ArrayList();
		getChildDepts(area, result);
		result.add(area);
		return result;
	}

	/**
	 * 递归取子部门
	 * 
	 * @param dept
	 *            部门
	 * @param result
	 *            当级至要查询地域级别间的部门集合
	 */
	private void getChildDepts(TawSystemArea area, List result) {
		List list = listChildArea(area.getAreaid());
		// 逐级取子部门
		if (list != null && !list.isEmpty()) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawSystemArea item = (TawSystemArea) it.next();
				getChildDepts(item, result);
			}
			result.addAll(list);
		}
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#listChildArea(java.lang.String)
	 */
	public List listChildArea(String areaId) {
		return tawSystemAreaDao.getChildAreas(areaId);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.area.service.IAreaMgr#listParentAreas(java.lang.String)
	 */
	public List listParentAreas(String areaId) {

		List areas = new ArrayList();
		// 为防止改变参数，新增一临时变量
		TawSystemArea area = getArea(areaId);

		if (area != null) {
			areas.add(area);
			String pAreaId = area.getParentAreaid();
			while (true) {
				TawSystemArea item = getArea(pAreaId);
				// 若未取到部门则返回结果
				if (null == item || null == item.getId()
						|| "".equals(item.getId())) {
					return areas;
				} else {
					areas.add(item);
					pAreaId = item.getParentAreaid();
				}
			}
		}
		return areas;

	}

	/**
	 * @param tawSystemAreaDao
	 *            the tawSystemAreaDao to set
	 */
	public void setTawSystemAreaDao(TawSystemAreaDao tawSystemAreaDao) {
		this.tawSystemAreaDao = tawSystemAreaDao;
	}

	
	public List listArea() {
		return this.tawSystemAreaDao.getAreas();
	}

}
