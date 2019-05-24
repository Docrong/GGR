/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.service.ITemplateService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 15:04:15
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class TemplateAction extends BaseAction {
    private ITemplateService templateService;

    /**
     * @return the templateService
     */
    public ITemplateService getTemplateService() {
        return templateService;
    }

    /**
     * @param templateService
     *            the templateService to set
     */
    public void setTemplateService(ITemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * 模板选择
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward select(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        return super.execute(mapping, form, request, response);
    }
}
