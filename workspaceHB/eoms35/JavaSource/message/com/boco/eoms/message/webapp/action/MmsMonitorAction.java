
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
import com.boco.eoms.message.mgr.IMmsMonitorManager;
import com.boco.eoms.message.model.MmsMonitor;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.webapp.form.MmsMonitorForm;

/**
 * Action class to handle CRUD on a MmsMonitor object
 *
 * @struts.action name="mmsMonitorForm" path="/mmsMonitors" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="mmsMonitorForm" path="/editMmsMonitor" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="mmsMonitorForm" path="/saveMmsMonitor" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/mmsMonitor/mmsMonitorForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/mmsMonitor/mmsMonitorList.jsp"
 * @struts.action-forward name="search" path="/mmsMonitors.html" redirect="true"
 */
public final class MmsMonitorAction extends BaseAction {
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
        MmsMonitorForm mmsMonitorForm = (MmsMonitorForm) form;

        // Exceptions are caught by ActionExceptionHandler
        IMmsMonitorManager mgr = (IMmsMonitorManager) getBean("ImmsMonitorManager");
        mgr.removeMmsMonitor(mmsMonitorForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("mmsMonitor.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        MmsMonitorForm mmsMonitorForm = (MmsMonitorForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (mmsMonitorForm.getId() != null) {
            IMmsMonitorManager mgr = (IMmsMonitorManager) getBean("ImmsMonitorManager");
            MmsMonitor mmsMonitor = mgr.getMmsMonitor(mmsMonitorForm.getId());
            mmsMonitorForm = (MmsMonitorForm) convert(mmsMonitor);
            updateFormBean(mapping, request, mmsMonitorForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        MmsMonitorForm mmsMonitorForm = (MmsMonitorForm) form;
        boolean isNew = ("".equals(mmsMonitorForm.getId()) || mmsMonitorForm.getId() == null);

        IMmsMonitorManager mgr = (IMmsMonitorManager) getBean("ImmsMonitorManager");
        MmsMonitor mmsMonitor = (MmsMonitor) convert(mmsMonitorForm);
        mgr.saveMmsMonitor(mmsMonitor);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("mmsMonitor.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("mmsMonitor.updated"));
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

        IMmsMonitorManager mgr = (IMmsMonitorManager) getBean("ImmsMonitorManager");
        Map map = (Map)mgr.getMmsMonitors(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
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
