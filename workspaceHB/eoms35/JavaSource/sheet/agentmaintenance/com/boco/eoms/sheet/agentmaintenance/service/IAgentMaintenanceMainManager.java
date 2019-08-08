// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IAgentMaintenanceMainManager.java

package com.boco.eoms.sheet.agentmaintenance.service;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;

public interface IAgentMaintenanceMainManager
        extends IMainService {

    public abstract BaseMain getMainBySourceId(String s, String s1);
}
