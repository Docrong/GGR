// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TawSystemRoleWorkflowManagerImpl.java

package com.boco.eoms.roleWorkflow.mgr.impl;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.roleWorkflow.dao.ITawSystemRoleWorkflowDAO;
import com.boco.eoms.roleWorkflow.mgr.ITawSystemRoleWorkflowManager;
import com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow;

import java.util.List;

public class TawSystemRoleWorkflowManagerImpl extends BaseManager
        implements ITawSystemRoleWorkflowManager {

    private ITawSystemRoleWorkflowDAO dao;

    public TawSystemRoleWorkflowManagerImpl() {
    }

    public ITawSystemRoleWorkflowDAO getITawSystemRoleWorkflowDAO() {
        return dao;
    }

    public void setITawSystemRoleWorkflowDAO(ITawSystemRoleWorkflowDAO dao) {
        this.dao = dao;
    }

    public List getTawSystemWorkflows() {
        return dao.getTawSystemWorkflows();
    }

    public TawSystemRoleWorkflow getTawSystemWorkflow(long id) {
        return dao.getTawSystemWorkflow(id);
    }

    public void saveTawSystemWorkflow(TawSystemRoleWorkflow tawSystemWorkflow) {
        dao.saveTawSystemWorkflow(tawSystemWorkflow);
    }

    public void removeTawSystemWorkflow(long id)
            throws Exception {
        dao.removeTawSystemWorkflow(id);
    }

    public TawSystemRoleWorkflow getTawSystemWorkflowByName(String name)
            throws Exception {
        return dao.getTawSystemWorkflowByName(name);
    }

    public TawSystemRoleWorkflow getTawSystemWorkflowByBeanId(String mainBeanId)
            throws Exception {
        return dao.getTawSystemWorkflowByBeanId(mainBeanId);
    }
}
