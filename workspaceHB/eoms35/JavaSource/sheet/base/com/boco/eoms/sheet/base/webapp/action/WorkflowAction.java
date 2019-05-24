/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-9-23 
 * </p>
 * 
 * @author 王建华
 * @version 1.0
 *  
 */
public class WorkflowAction extends BaseAction {
    /**
     * 得到所有的流程
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void getAllWorkflow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {   	
    	JSONArray jsonRoot = new JSONArray();
    	
    	ITawSystemWorkflowManager workflowService =  (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
    	List workflows = workflowService.getTawSystemWorkflows();
    	
    	for (Iterator it = workflows.iterator(); it.hasNext(); ){
    		TawSystemWorkflow systemWorkflow = (TawSystemWorkflow)it.next();
    		String workflowId = systemWorkflow.getFlowId();
    		String workflowName = systemWorkflow.getRemark();
    		JSONObject j = new JSONObject();
    		j.put(UIConstants.JSON_ID, workflowId);
    		j.put(UIConstants.JSON_TEXT, workflowName);
    		j.put(UIConstants.JSON_NODETYPE, "workflow");
    		jsonRoot.put(j);
    	}
    	JSONUtil.print(response, jsonRoot.toString());
    }
}
