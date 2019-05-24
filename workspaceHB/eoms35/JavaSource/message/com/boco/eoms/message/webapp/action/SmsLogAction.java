
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
import com.boco.eoms.message.mgr.ISmsLogManager;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.SmsLogForm;

/**
 * Action class to handle CRUD on a SmsLog object
 *
 * @struts.action name="smsLogForm" path="/smsLogs" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="smsLogForm" path="/editSmsLog" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="smsLogForm" path="/saveSmsLog" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/smsLog/smsLogForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/smsLog/smsLogList.jsp"
 * @struts.action-forward name="search" path="/smsLogs.html" redirect="true"
 */
public final class SmsLogAction extends BaseAction {
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
        SmsLogForm smsLogForm = (SmsLogForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ISmsLogManager mgr = (ISmsLogManager) getBean("IsmsLogManager");
        mgr.removeSmsLog(smsLogForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("smsLog.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        SmsLogForm smsLogForm = (SmsLogForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (smsLogForm.getId() != null) {
            ISmsLogManager mgr = (ISmsLogManager) getBean("IsmsLogManager");
            SmsLog smsLog = mgr.getSmsLog(smsLogForm.getId());
            smsLogForm = (SmsLogForm) convert(smsLog);
            updateFormBean(mapping, request, smsLogForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        SmsLogForm smsLogForm = (SmsLogForm) form;
        boolean isNew = ("".equals(smsLogForm.getId()) || smsLogForm.getId() == null);

        ISmsLogManager mgr = (ISmsLogManager) getBean("IsmsLogManager");
        SmsLog smsLog = (SmsLog) convert(smsLogForm);
        mgr.saveSmsLog(smsLog);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("smsLog.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("smsLog.updated"));
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

        ISmsLogManager mgr = (ISmsLogManager) getBean("IsmsLogManager");
        Map map = (Map)mgr.getSmsLogs(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List)map.get("result");
        request.setAttribute(MsgConstants.SMSLOG_LIST, list);
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
