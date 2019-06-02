// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IAgentMaintenanceMainDAO.java

package com.boco.eoms.sheet.agentmaintenance.dao;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

public interface IAgentMaintenanceMainDAO
	extends IMainDAO
{

	public abstract BaseMain getMainBySourceId(String s, String s1);
}