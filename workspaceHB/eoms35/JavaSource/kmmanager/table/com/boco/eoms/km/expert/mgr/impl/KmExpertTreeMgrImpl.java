package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;

import com.boco.eoms.km.expert.dao.KmExpertTreeDao;
import com.boco.eoms.km.expert.mgr.KmExpertTreeMgr;

public class KmExpertTreeMgrImpl implements KmExpertTreeMgr {
	
	private KmExpertTreeDao kmExpertTreeDao;

	public KmExpertTreeDao getKmExpertTreeDao() {
		return kmExpertTreeDao;
	}

	public void setKmExpertTreeDao(KmExpertTreeDao kmExpertTreeDao) {
		this.kmExpertTreeDao = kmExpertTreeDao;
	}

	/**
	 * 得到部门的所有USER（不包含专家身份的用户）
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsNoSelf(String deptid, String userid) {
		return kmExpertTreeDao.getUserBydeptidsNoSelf(deptid, userid);
	}

	/**
	 * 得到部门的所有USER（不包含专家身份的用户）
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptids(String deptid) {
		return kmExpertTreeDao.getUserBydeptids(deptid);
	}

	/**
	 * 得到分类下的所有专家USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserByKmExpertBasicField(String name, String value) {
		return kmExpertTreeDao.getUserByKmExpertBasicField(name, value);
	}
}
