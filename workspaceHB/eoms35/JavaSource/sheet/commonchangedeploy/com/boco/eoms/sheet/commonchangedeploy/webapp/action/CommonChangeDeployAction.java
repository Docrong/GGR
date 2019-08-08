package com.boco.eoms.sheet.commonchangedeploy.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.sheet.base.webapp.action.SheetAction;

/**
 * <p>
 * Title:通用变更配置工单
 * </p>
 * <p>
 * Description:通用变更配置工单
 * </p>
 * <p>
 * Fri Jun 12 09:08:01 CST 2009
 * </p>
 *
 * @author yangwei
 * @version 3.5
 */

public class CommonChangeDeployAction extends SheetAction {

    /**
     * showDrawing
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("draw");
    }


    /**
     * showPic
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showPic(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("pic");
    }


    /**
     * showKPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showKPI(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("kpi");
    }

}
 



