// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultTaskManagerImpl.java

package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.sheet.commonfault.dao.ICommonFaultViSheetInfoDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultViSheetInfo;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultViSheetInfoManager;

public class CommonFaultViSheetInfoManagerImpl implements ICommonFaultViSheetInfoManager {

	private ICommonFaultViSheetInfoDAO infoDao;
	
	public ICommonFaultViSheetInfoDAO getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(ICommonFaultViSheetInfoDAO infoDao) {
		this.infoDao = infoDao;
	}

	public CommonFaultViSheetInfo getCommonFaultViSheetInfoBymainId(String id) throws Exception {
		
		return infoDao.getCommonFaultViSheetInfoBymainId(id);
	}

	public void saveOrUpdate(CommonFaultViSheetInfo obj) throws Exception {
		infoDao.saveOrUpdate(obj);
		
	}

	public CommonFaultViSheetInfo getCommonFaultViSheetInfoByVisId(String mid, String vid) throws Exception {
		return infoDao.getCommonFaultViSheetInfoByVisId(mid,vid);
	}
	
}
