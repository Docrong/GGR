// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IPreAllocatedNewMgr.java

package com.boco.eoms.sheetflowline.mgr;

import com.boco.eoms.sheetflowline.model.PreAllocatedNew;
import com.boco.eoms.sheetflowline.model.PreAllocatedSepcail;
import java.util.*;

public interface IPreAllocatedNewMgr
{

	public abstract void saveObject(PreAllocatedNew preallocatednew)
		throws Exception;

	public abstract void deleteObject(PreAllocatedNew preallocatednew)
		throws Exception;

	public abstract void updateObject(PreAllocatedNew preallocatednew)
		throws Exception;

	public abstract Map listPreAllocated(Integer integer, Integer integer1)
		throws Exception;

	public abstract PreAllocatedNew getPreAllocated(String s)
		throws Exception;

	public abstract Integer executeHsql(String s)
		throws Exception;

	public abstract Map listPreAllocated(Map map, Integer integer, Integer integer1)
		throws Exception;

	public abstract HashMap searchPreUser(String s, String s1, String s2, String s3)
		throws Exception;

	public abstract List getLists(String s)
		throws Exception;

	public abstract Map listNetPreAllocated(Integer integer, Integer integer1)
		throws Exception;

	public abstract void saveNetObject(PreAllocatedSepcail preallocatedsepcail)
		throws Exception;

	public abstract boolean ifExistSameRule(String s, String s1, String s2)
		throws Exception;

	public abstract PreAllocatedSepcail getNetPreAllocated(String s)
		throws Exception;
}
