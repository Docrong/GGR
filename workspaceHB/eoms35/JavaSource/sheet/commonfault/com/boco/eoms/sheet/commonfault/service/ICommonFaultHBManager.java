// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ICommonFaultHBManager.java

package com.boco.eoms.sheet.commonfault.service;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;

public interface ICommonFaultHBManager
{

	public abstract void updateObject(String as[])
		throws HibernateException;
	


	public abstract List selectObject(String s, Integer integer, Integer integer1)
		throws HibernateException;

	public abstract int selectCount(String s);

	public abstract void deleteFordel(String as[]);
	
	//public abstract boolean ifAuto(String mainnetsorttwo, String mainnetsortthree, String mainfaultresponselevel, String todeptid);
	
	//public abstract String getSubRoleId(String mainnetsorttwo, String mainnetsortthree, String todeptid);
	public abstract void inserNetOpt(String sheetkey)
	throws HibernateException;
}