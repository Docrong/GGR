// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceMainManagerImpl.java

package com.boco.eoms.sheet.agentmaintenance.service.impl;

import com.boco.eoms.sheet.agentmaintenance.dao.IAgentMaintenanceMainDAO;
import com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceMainManager;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.impl.MainService;

public class AgentMaintenanceMainManagerImpl extends MainService
        implements IAgentMaintenanceMainManager {

    private IAgentMaintenanceMainDAO agentMaintenanceMainDAO;

    public AgentMaintenanceMainManagerImpl() {
    }

    public BaseMain getMainBySourceId(String sourceId, String operatedeptid) {
        return (AgentMaintenanceMain) agentMaintenanceMainDAO.getMainBySourceId(sourceId, operatedeptid);
    }

    public IAgentMaintenanceMainDAO getAgentMaintenanceMainDAO() {
        return agentMaintenanceMainDAO;
    }

    public void setAgentMaintenanceMainDAO(IAgentMaintenanceMainDAO agentMaintenanceMainDAO) {
        this.agentMaintenanceMainDAO = agentMaintenanceMainDAO;
    }
}