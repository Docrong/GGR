package com.boco.eoms.km.kmOperate.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.dao.KmOperateDao;

/**
 * <p> Title:知识管理权限配置 </p>
 * <p> Description:知识管理权限配置 </p>
 * <p> Fri May 22 14:03:33 CST 2009 </p>
 * 
 * @author 戴志刚
 * @version 1.0
 * 
 */
public class KmOperateMgrImpl implements KmOperateMgr {

	private KmOperateDao kmOperateDao;

	public KmOperateDao getKmOperateDao() {
		return this.kmOperateDao;
	}

	public void setKmOperateDao(KmOperateDao kmOperateDao) {
		this.kmOperateDao = kmOperateDao;
	}

	public List getKmOperates() {
		return kmOperateDao.getKmOperates();
	}

	public List getKmOperates(final String nodeId, final String nodeType) {
		return kmOperateDao.getKmOperates(nodeId, nodeType);
	}

	public List getKmOperates(final String whereStr) {
		return kmOperateDao.getKmOperates(whereStr);
	}

	public KmOperate getKmOperate(final String id) {
		return kmOperateDao.getKmOperate(id);
	}

	public KmOperate getKmOperate(final String nodeId, final String nodeType,
			final String orgId, final String orgType) {
		return kmOperateDao.getKmOperate(nodeId, nodeType, orgId, orgType);
	}

	public void saveKmOperate(KmOperate kmOperate) {
		kmOperateDao.saveKmOperate(kmOperate);
	}

	public void removeKmOperate(final String id) {
		kmOperateDao.removeKmOperate(id);
	}

	public Map getKmOperates(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmOperateDao.getKmOperates(curPage, pageSize, whereStr);
	}

	/**
	 * 根据操作者和操作者类型取知识管理权限配置列表
	 * 
	 * @return 返回知识管理权限配置列表
	 */
	public List getKmOperatesByOrgIdAndOrgType(final String nodeType, final String orgId,
			final String orgType) {
		return kmOperateDao.getKmOperatesByOrgIdAndOrgType(nodeType, orgId, orgType);
	}
}