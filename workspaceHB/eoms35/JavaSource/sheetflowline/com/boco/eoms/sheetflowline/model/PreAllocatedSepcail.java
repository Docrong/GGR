// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PreAllocatedSepcail.java

package com.boco.eoms.sheetflowline.model;

import java.util.Date;

public class PreAllocatedSepcail
{

	private String id;
	private String specailType;
	private String netTypeOne;
	private String netTypeTwo;
	private Date createTime;
	private String createUser;
	private String netTypeTwoName;
	private String netTypeOneName;

	public PreAllocatedSepcail()
	{
	}

	public String getNetTypeOneName()
	{
		return netTypeOneName;
	}

	public void setNetTypeOneName(String netTypeOneName)
	{
		this.netTypeOneName = netTypeOneName;
	}

	public String getNetTypeTwoName()
	{
		return netTypeTwoName;
	}

	public void setNetTypeTwoName(String netTypeTwoName)
	{
		this.netTypeTwoName = netTypeTwoName;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getNetTypeOne()
	{
		return netTypeOne;
	}

	public void setNetTypeOne(String netTypeOne)
	{
		this.netTypeOne = netTypeOne;
	}

	public String getNetTypeTwo()
	{
		return netTypeTwo;
	}

	public void setNetTypeTwo(String netTypeTwo)
	{
		this.netTypeTwo = netTypeTwo;
	}

	public String getSpecailType()
	{
		return specailType;
	}

	public void setSpecailType(String specailType)
	{
		this.specailType = specailType;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getCreateUser()
	{
		return createUser;
	}

	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}
}
