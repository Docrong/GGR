// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TechniqueSupportHbTask.java

package com.boco.eoms.sheet.techniquesupporthb.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

public class TechniqueSupportHbTask extends TaskImpl
	implements ITask
{

	private String preDealDept;
	private String preDealUserId;
	private String correlationKey;
	private String levelId;
	private String parentLevelId;

	public TechniqueSupportHbTask()
	{
	}

	public String getParentLevelId()
	{
		return parentLevelId;
	}

	public void setParentLevelId(String parentLevelId)
	{
		this.parentLevelId = parentLevelId;
	}

	public String getCorrelationKey()
	{
		return correlationKey;
	}

	public void setCorrelationKey(String correlationKey)
	{
		this.correlationKey = correlationKey;
	}

	public String getLevelId()
	{
		return levelId;
	}

	public void setLevelId(String levelId)
	{
		this.levelId = levelId;
	}

	public String getPreDealDept()
	{
		return preDealDept;
	}

	public void setPreDealDept(String preDealDept)
	{
		this.preDealDept = preDealDept;
	}

	public String getPreDealUserId()
	{
		return preDealUserId;
	}

	public void setPreDealUserId(String preDealUserId)
	{
		this.preDealUserId = preDealUserId;
	}
}
