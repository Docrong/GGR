// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ITechniqueSupportHbTaskManager.java

package com.boco.eoms.sheet.techniquesupporthb.service;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.ITaskService;

public interface ITechniqueSupportHbTaskManager
	extends ITaskService
{

	public abstract Integer getCountOfBrother(String s, String s1)
		throws SheetException;
}
