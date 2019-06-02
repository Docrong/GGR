// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IAgentMaintenanceLinkDAO.java

package com.boco.eoms.sheet.agentmaintenance.dao;

import com.boco.eoms.sheet.base.dao.ILinkDAO;
import java.util.Map;

public interface IAgentMaintenanceLinkDAO
	extends ILinkDAO
{

	public abstract Map getLastLinkBeforeHold(String s, String s1, String s2);
}