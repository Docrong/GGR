// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceLinkManagerImpl.java

package com.boco.eoms.sheet.agentmaintenance.service.impl;

import com.boco.eoms.sheet.agentmaintenance.dao.IAgentMaintenanceLinkDAO;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceLinkManager;
import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import java.util.Map;

public class AgentMaintenanceLinkManagerImpl extends LinkServiceImpl
	implements IAgentMaintenanceLinkManager
{

	private IAgentMaintenanceLinkDAO dao;

	public AgentMaintenanceLinkManagerImpl()
	{
	}

	public Map getLastLinkBeforeHold(String sourceId, String type, String operatedeptid)
	{
		return dao.getLastLinkBeforeHold(sourceId, type, operatedeptid);
	}

	public IAgentMaintenanceLinkDAO getDao()
	{
		return dao;
	}

	public void setDao(IAgentMaintenanceLinkDAO dao)
	{
		this.dao = dao;
	}
}