// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoDealSOPMgrImpl.java

package com.boco.eoms.sheetflowline.mgr.impl;

import com.boco.eoms.sheetflowline.dao.IAutoDealSOPDao;
import com.boco.eoms.sheetflowline.mgr.IAutoDealSOPMgr;
import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.model.AutoDealSopSheet;
import java.util.*;

public class AutoDealSOPMgrImpl
	implements IAutoDealSOPMgr
{

	private IAutoDealSOPDao autoDealSopDao;

	public AutoDealSOPMgrImpl()
	{
	}

	public void setAutoDealSopDao(IAutoDealSOPDao autoDealSopDao)
	{
		this.autoDealSopDao = autoDealSopDao;
	}

	public void deleteObject(AutoDealSOP object)
		throws Exception
	{
		autoDealSopDao.deleteObject(object);
	}

	public AutoDealSOP getSOP(String id)
		throws Exception
	{
		return autoDealSopDao.getSOP(id);
	}

	public Map listSOP(Integer startIndex, Integer pasesize, String lastMonths, String currentTime)
		throws Exception
	{
		return autoDealSopDao.listSOP(startIndex, pasesize, lastMonths, currentTime);
	}

	public void saveObject(AutoDealSOP object)
		throws Exception
	{
		if (object.getId() == null || "".equals(object.getId()))
			autoDealSopDao.saveObject(object);
		else
			autoDealSopDao.updateObject(object);
	}

	public void updateObject(AutoDealSOP object)
		throws Exception
	{
		autoDealSopDao.updateObject(object);
	}

	public Integer executeHsql(String hsql)
		throws Exception
	{
		return autoDealSopDao.executeHsql(hsql);
	}

	public Map listSOP(Map object, Integer pageIndex, Integer pageSize, String lastMonths, String currentTime)
		throws Exception
	{
		return autoDealSopDao.listSOP(object, pageIndex, pageSize, lastMonths, currentTime);
	}

	public List searchSOP(Map conditionMap)
		throws Exception
	{
		return autoDealSopDao.searchSOP(conditionMap);
	}

	public void saveSopSheet(AutoDealSopSheet object)
	{
		autoDealSopDao.saveSopSheet(object);
	}

	public HashMap listSopSheet(String ruleId, Integer pageIndex, Integer pageSize, String lastMonths, String currentTime)
		throws Exception
	{
		return autoDealSopDao.listSopSheet(ruleId, pageIndex, pageSize, lastMonths, currentTime);
	}
}
