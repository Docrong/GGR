// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ITechniqueSupportHbFlowManager.java

package com.boco.eoms.sheet.techniquesupporthb.service;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import java.util.HashMap;

public interface ITechniqueSupportHbFlowManager
	extends IBusinessFlowService
{

	public abstract String getProcessInstanceCustomPropertyValue(String s, String s1, HashMap hashmap)
		throws Exception;

	public abstract void setProcessInstanceCustomProperty(String s, String s1, String s2, HashMap hashmap)
		throws Exception;

	public abstract int querySubProcessTaskNum(String s, HashMap hashmap)
		throws Exception;

	public abstract String queryParentProcessId(String s, HashMap hashmap)
		throws Exception;
}
