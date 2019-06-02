
package com.boco.eoms.message.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.SmsMonitorForm;

/**
 * Action class to handle CRUD on a SmsMonitor object
 *
 * @struts.action name="smsMonitorForm" path="/smsMonitors" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="smsMonitorForm" path="/editSmsMonitor" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="smsMonitorForm" path="/saveSmsMonitor" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/smsMonitor/smsMonitorForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/smsMonitor/smsMonitorList.jsp"
 * @struts.action-forward name="search" path="/smsMonitors.html" redirect="true"
 */
public final class SmsMonitorAction extends BaseAction {
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
        SmsMonitorForm smsMonitorForm = (SmsMonitorForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ISmsMonitorManager mgr = (ISmsMonitorManager) getBean("IsmsMonitorManager");
        mgr.removeSmsMonitor(smsMonitorForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("smsMonitor.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        SmsMonitorForm smsMonitorForm = (SmsMonitorForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (smsMonitorForm.getId() != null) {
            ISmsMonitorManager mgr = (ISmsMonitorManager) getBean("IsmsMonitorManager");
            SmsMonitor smsMonitor = mgr.getSmsMonitor(smsMonitorForm.getId());
            smsMonitorForm = (SmsMonitorForm) convert(smsMonitor);
            updateFormBean(mapping, request, smsMonitorForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        SmsMonitorForm smsMonitorForm = (SmsMonitorForm) form;
        boolean isNew = ("".equals(smsMonitorForm.getId()) || smsMonitorForm.getId() == null);

        ISmsMonitorManager mgr = (ISmsMonitorManager) getBean("IsmsMonitorManager");
        SmsMonitor smsMonitor = (SmsMonitor) convert(smsMonitorForm);
        mgr.saveSmsMonitor(smsMonitor);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("smsMonitor.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("smsMonitor.updated"));
            saveMessages(request, messages);

            return mapping.findForward("search");
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // ҳ��Ĳ����ￄ1�7,����"tawDemoMytableList"��ҳ����displayTag��id
	    	final Integer pageSize = new Integer(25);   //ÿҳ��ʾ������
	    	final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1));  //��ǰҳ��

        ISmsMonitorManager mgr = (ISmsMonitorManager) getBean("IsmsMonitorManager");
        Map map = (Map)mgr.getSmsMonitors(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List)map.get("result");
        request.setAttribute(MsgConstants.SMSMONITOR_LIST, list);
        request.setAttribute("resultSize", map.get("total"));

        return mapping.findForward("list");
    }
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }
}
