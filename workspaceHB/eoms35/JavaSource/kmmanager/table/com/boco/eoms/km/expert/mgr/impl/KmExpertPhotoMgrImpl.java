package com.boco.eoms.km.expert.mgr.impl;

import com.boco.eoms.km.expert.dao.KmExpertPhotoDao;
import com.boco.eoms.km.expert.model.KmExpertPhoto;
import com.boco.eoms.km.expert.mgr.KmExpertPhotoMgr;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertPhotoMgrImpl implements KmExpertPhotoMgr {
	
	private KmExpertPhotoDao kmExpertPhotoDao;

	public KmExpertPhotoDao getKmExpertPhotoDao() {
		return kmExpertPhotoDao;
	}

	public void setKmExpertPhotoDao(KmExpertPhotoDao kmExpertPhotoDao) {
		this.kmExpertPhotoDao = kmExpertPhotoDao;
	}
	
	public KmExpertPhoto getKmExpertPhotoByUserId(String userId) {
		return kmExpertPhotoDao.getKmExpertPhotoByUserId(userId);
	}

	public void saveKmExpertPhoto(KmExpertPhoto kmExpertPhoto) {
		kmExpertPhotoDao.saveKmExpertPhoto(kmExpertPhoto);
	}
}