// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IAutoDealSOPMgr.java

package com.boco.eoms.sheetflowline.mgr;

import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.model.AutoDealSopSheet;
import java.util.*;

public interface IAutoDealSOPMgr
{

	public abstract void saveObject(AutoDealSOP autodealsop)
		throws Exception;

	public abstract void deleteObject(AutoDealSOP autodealsop)
		throws Exception;

	public abstract void updateObject(AutoDealSOP autodealsop)
		throws Exception;

	public abstract Map listSOP(Integer integer, Integer integer1, String s, String s1)
		throws Exception;

	public abstract AutoDealSOP getSOP(String s)
		throws Exception;

	public abstract Integer executeHsql(String s)
		throws Exception;

	public abstract Map listSOP(Map map, Integer integer, Integer integer1, String s, String s1)
		throws Exception;

	public abstract List searchSOP(Map map)
		throws Exception;

	public abstract void saveSopSheet(AutoDealSopSheet autodealsopsheet)
		throws Exception;

	public abstract HashMap listSopSheet(String s, Integer integer, Integer integer1, String s1, String s2)
		throws Exception;
}
