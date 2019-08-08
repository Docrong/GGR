package com.boco.eoms.sheet.maintenanceservice.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;

/**
 * <p>
 * Title:维保服务审批
 * </p>
 * <p>
 * Description:维保服务审批
 * </p>
 * <p>
 * Thu Mar 16 15:48:02 CST 2017
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class MaintenanceServiceAction extends SheetAction {

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

    public ActionForward showNewSheetPage(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String actionForward = baseSheet.showNewSheetPage(mapping, form, request, response);// 实现各自流程的特殊处理
        System.out.println("=====" + request.getParameter("templateId"));
        String auto = StaticMethod.nullObject2String(request.getParameter("auto"));
        request.setAttribute("auto", auto);
        return mapping.findForward(actionForward);

    }

    public ActionForward showNewInputSheetPage(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        String processname = StaticMethod.nullObject2String(request
                .getParameter("processname"));
        String taskname = StaticMethod.nullObject2String(request
                .getParameter("taskname"));

        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showInputNewSheetPage(mapping, form, request, response);

        ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
                .getInstance().getBean("ItawSheetAccessManager");
        TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processname, taskname);
        request.setAttribute("tawSheetAccess", access);
        String auto = StaticMethod.nullObject2String(request.getParameter("auto"));
        request.setAttribute("auto", auto);
        return mapping.findForward("inputNew");
    }
}
 



