
package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiLog;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiLogManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiLogForm;

/**
 * Action class to handle CRUD on a TawSupplierkpiLog object
 *
 * @struts.action name="tawSupplierkpiLogForm" path="/tawSupplierkpiLogs" scope="request"
 * validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSupplierkpiLogForm" path="/editTawSupplierkpiLog" scope="request"
 * validate="false" parameter="method" input="list"
 * @struts.action name="tawSupplierkpiLogForm" path="/saveTawSupplierkpiLog" scope="request"
 * validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSupplierkpiLog/tawSupplierkpiLogForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSupplierkpiLog/tawSupplierkpiLogList.jsp"
 * @struts.action-forward name="search" path="/tawSupplierkpiLogs.html" redirect="true"
 */
public final class TawSupplierkpiLogAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSupplierkpiLogForm tawSupplierkpiLogForm = (TawSupplierkpiLogForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSupplierkpiLogManager mgr = (ITawSupplierkpiLogManager) getBean("ItawSupplierkpiLogManager");
        mgr.removeTawSupplierkpiLog(tawSupplierkpiLogForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("tawSupplierkpiLog.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {
        TawSupplierkpiLogForm tawSupplierkpiLogForm = (TawSupplierkpiLogForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSupplierkpiLogForm.getId() != null) {
            ITawSupplierkpiLogManager mgr = (ITawSupplierkpiLogManager) getBean("ItawSupplierkpiLogManager");
            TawSupplierkpiLog tawSupplierkpiLog = mgr.getTawSupplierkpiLog(tawSupplierkpiLogForm.getId());
            tawSupplierkpiLogForm = (TawSupplierkpiLogForm) convert(tawSupplierkpiLog);
            updateFormBean(mapping, request, tawSupplierkpiLogForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSupplierkpiLogForm tawSupplierkpiLogForm = (TawSupplierkpiLogForm) form;
        boolean isNew = ("".equals(tawSupplierkpiLogForm.getId()) || tawSupplierkpiLogForm.getId() == null);

        ITawSupplierkpiLogManager mgr = (ITawSupplierkpiLogManager) getBean("ItawSupplierkpiLogManager");
        TawSupplierkpiLog tawSupplierkpiLog = (TawSupplierkpiLog) convert(tawSupplierkpiLogForm);
        mgr.saveTawSupplierkpiLog(tawSupplierkpiLog);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("tawSupplierkpiLog.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("tawSupplierkpiLog.updated"));
            saveMessages(request, messages);

            return mapping.findForward("search");
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {

        return mapping.findForward("list");
    }

    public ActionForward query(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception {
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        String specialType = StaticMethod.null2String(request.getParameter("specialType"));
        String serviceType = "";
        String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
        TawSupplierkpiTemplate template = templateMgr.getTawSupplierkpiTemplate(templateId);

        if (template != null) {
            serviceType = template.getServiceType();
        }

        request.setAttribute("serviceType", serviceType);
        request.setAttribute("specialType", specialType);

        return mapping.findForward("query");
    }

    public ActionForward queryDo(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        //TawSupplierkpiLogForm tawSupplierkpiLogForm = (TawSupplierkpiLogForm) form;
        ITawSupplierkpiLogManager logMgr = (ITawSupplierkpiLogManager) getBean("ItawSupplierkpiLogManager");
        String serviceType = StaticMethod.null2String(request.getParameter("serviceType"));
        String specialType = StaticMethod.null2String(request.getParameter("specialType"));
        String startTime = StaticMethod.null2String(request.getParameter("startTime"));
        String endTime = StaticMethod.null2String(request.getParameter("endTime"));
        String operator = StaticMethod.null2String(request.getParameter("operator"));
        String operType = StaticMethod.null2String(request.getParameter("operType"));

        String whereStr = "from TawSupplierkpiLog where specialType like('%" + specialType + "%')";

        if ((operator != null) && (!"".equals(operator))) {
            whereStr += " and operator='" + operator + "'";
        }
        if ((operType != null) && (!"".equals(operType))) {
            whereStr += " and operType='" + operType + "'";
        }
        if ((startTime != null) && (!"".equals(startTime))) {
            whereStr += " and operTime>='" + startTime + "'";
        }
        if ((endTime != null) && (!"".equals(endTime))) {
            whereStr += " and operTime<='" + endTime + "'";
        }

        List list = logMgr.getTawSupplierkpiLogs(whereStr);
        ListIterator it = list.listIterator();

        request.setAttribute("it", it);
        request.setAttribute("serviceType", serviceType);
        request.setAttribute("specialType", specialType);

        return mapping.findForward("queryDo");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }
}
