package com.boco.eoms.commons.system.priv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.loging.BocoLog;

public class TawSystemPrivAssignManagerImpl extends BaseManager implements
		ITawSystemPrivAssignManager {
	private TawSystemPrivAssignDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemPrivAssignDao(TawSystemPrivAssignDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#getTawSystemPrivAssigns(com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign)
	 */
	public List getTawSystemPrivAssigns(
			final TawSystemPrivAssign tawSystemPrivAssign) {
		return dao.getTawSystemPrivAssigns(tawSystemPrivAssign);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#getTawSystemPrivAssign(String
	 *      id)
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(final String id) {
		return dao.getTawSystemPrivAssign(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#saveTawSystemPrivAssign(TawSystemPrivAssign
	 *      tawSystemPrivAssign)
	 */
	public void saveTawSystemPrivAssign(TawSystemPrivAssign tawSystemPrivAssign) {
		dao.saveTawSystemPrivAssign(tawSystemPrivAssign);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#removeTawSystemPrivAssign(String
	 *      id)
	 */
	public void removeTawSystemPrivAssign(final String id) {
		dao.removeTawSystemPrivAssign(new String(id));
	}

	/**
	 * 
	 */
	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawSystemPrivAssigns(curPage, pageSize, null);
	}

	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawSystemPrivAssigns(curPage, pageSize, whereStr);
	}

	/**
	 * 查询某用户分配的权限信息
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserCreatePrivs(final String operuserid) {

		List list = new ArrayList();
		list = dao.getUserCreatePrivs(operuserid);
		return list;
	}

	/**
	 * 查询OBJECT 对应的权限
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectPriv(final String objectid) {
		return dao.getObjectPriv(objectid);
	}

	/**
	 * 查询某权限方案被分配的情况
	 * 
	 * @return
	 */
	public List getPrivassigninfos(final String privid) {
		List list = new ArrayList();
		list = dao.getPrivassigninfos(privid);
		return list;
	}

	/**
	 * 删除某个对象的权限
	 * 
	 * @param objectid
	 */
	public void removePrivassign(final String objectid) {
		TawSystemPrivAssign privassign = null;
		ArrayList list = (ArrayList) dao.getObjectPriv(objectid);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				privassign = (TawSystemPrivAssign) list.get(i);
				if (privassign != null) {
					dao.removeTawSystemPrivAssign(privassign.getId());
					BocoLog.info(this, "删除:" + objectid + " 权限成功");
				}
			}
		}

	}

	/**
	 * 更新某个对象的权限信息
	 * 
	 * @param objectid
	 */
	public void updateObjectPriv(final String objectid,
			TawSystemPrivAssign assign) {
		TawSystemPrivAssign privassign = null;
		ArrayList list = (ArrayList) dao.getObjectPriv(objectid);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				privassign = (TawSystemPrivAssign) list.get(i);
				assign.setId(privassign.getId());
				dao.saveTawSystemPrivAssign(assign);
			}
		}

	}

	/**
	 * 判断某GROUP是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean hasAssign(String objectid) {
		return dao.hasAssign(objectid);
	}

	/**
	 * 判断某菜单方案是否被分配过
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isMenuHasAssign(String menuid) {
		return dao.isMenuHasAssign(menuid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#getAssignsByUrl(java.lang.String)
	 */
	public List getAssignsByUrl(String url) {
		return dao.getAssignsByUrl(url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#hasAssign(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public boolean hasAssign(String orgId, String type, String menuId) {
		TawSystemPrivAssign assign = this.dao.getTawSystemPrivAssign(orgId,
				type, menuId);

		if (assign == null || assign.getId() == null
				|| "".equals(assign.getId())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#listModuleMenu(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public List listModuleMenu(String userId, String deptId, List roleIds) {
		return this.dao.listMenu(userId, deptId, roleIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager#listAssign(java.lang.String)
	 */
	public List listAssign(String url) {

		return this.dao.listAssign(url);
	}

}
