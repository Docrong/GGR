// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   WorkflowAction.java

package com.boco.eoms.roleWorkflow.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.roleWorkflow.mgr.ITawSystemRoleWorkflowManager;
import com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class WorkflowAction extends BaseAction {

    public WorkflowAction() {
    }

    public void getAllWorkflow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONArray jsonRoot = new JSONArray();
        ITawSystemRoleWorkflowManager workflowService = (ITawSystemRoleWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemRoleWorkflowManager");
        List workflows = workflowService.getTawSystemWorkflows();
        JSONObject j;
        for (Iterator it = workflows.iterator(); it.hasNext(); jsonRoot.put(j)) {
            TawSystemRoleWorkflow systemWorkflow = (TawSystemRoleWorkflow) it.next();
            String workflowId = systemWorkflow.getFlowId();
            String workflowName = systemWorkflow.getRemark();
            j = new JSONObject();
            j.put("id", workflowId);
            j.put("text", workflowName);
            j.put("nodeType", "workflow");
        }

        JSONUtil.print(response, jsonRoot.toString());
    }
}
