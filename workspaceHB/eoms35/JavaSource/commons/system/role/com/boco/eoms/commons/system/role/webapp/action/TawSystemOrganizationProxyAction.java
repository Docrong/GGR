
package com.boco.eoms.commons.system.role.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;
import com.boco.eoms.commons.system.role.service.ITawSystemOrganizationProxyManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemOrganizationProxyForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemOrganizationProxy object
 *
 * @struts.action name="tawSystemOrganizationProxyForm" path="/tawSystemOrganizationProxys" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemOrganizationProxyForm" path="/editTawSystemOrganizationProxy" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemOrganizationProxyForm" path="/saveTawSystemOrganizationProxy" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSystemOrganizationProxy/tawSystemOrganizationProxyForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSystemOrganizationProxy/tawSystemOrganizationProxyList.jsp"
 * @struts.action-forward name="search" path="/tawSystemOrganizationProxys.html" redirect="true"
 */
public final class TawSystemOrganizationProxyAction extends BaseAction {
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
        TawSystemOrganizationProxyForm tawSystemOrganizationProxyForm = (TawSystemOrganizationProxyForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemOrganizationProxyManager mgr = (ITawSystemOrganizationProxyManager) getBean("ItawSystemOrganizationProxyManager");
        mgr.removeTawSystemOrganizationProxy(tawSystemOrganizationProxyForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSystemOrganizationProxy.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSystemOrganizationProxyForm tawSystemOrganizationProxyForm = (TawSystemOrganizationProxyForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSystemOrganizationProxyForm.getId() != null) {
            ITawSystemOrganizationProxyManager mgr = (ITawSystemOrganizationProxyManager) getBean("ItawSystemOrganizationProxyManager");
            TawSystemOrganizationProxy tawSystemOrganizationProxy = mgr.getTawSystemOrganizationProxy(tawSystemOrganizationProxyForm.getId());
            tawSystemOrganizationProxyForm = (TawSystemOrganizationProxyForm) convert(tawSystemOrganizationProxy);
            updateFormBean(mapping, request, tawSystemOrganizationProxyForm);
        }
        
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		String operuserid = sessionform.getUserid();
		ITawSystemUserRefRoleManager userRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
		List roleList = userRoleMgr.getSubRoleByUserId(operuserid,RoleConstants.ROLETYPE_SUBROLE);
		
		request.setAttribute("roleList", roleList);

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemOrganizationProxyForm tawSystemOrganizationProxyForm = (TawSystemOrganizationProxyForm) form;
        boolean isNew = ("".equals(tawSystemOrganizationProxyForm.getId()) || tawSystemOrganizationProxyForm.getId() == null);
        
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		String operuserid = sessionform.getUserid();

        ITawSystemOrganizationProxyManager mgr = (ITawSystemOrganizationProxyManager) getBean("ItawSystemOrganizationProxyManager");
        TawSystemOrganizationProxy tawSystemOrganizationProxy = (TawSystemOrganizationProxy) convert(tawSystemOrganizationProxyForm);
        tawSystemOrganizationProxy.setFromUserId(operuserid);
        tawSystemOrganizationProxy.setStartTime(StaticMethod.getLocalTime());
        mgr.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemOrganizationProxy.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemOrganizationProxy.updated"));
            saveMessages(request, messages);

            return mapping.findForward("search");
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
	    	final Integer pageSize = new Integer(25);   //ÿҳ��ʾ������
	    	final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1));  //��ǰҳ��

        ITawSystemOrganizationProxyManager mgr = (ITawSystemOrganizationProxyManager) getBean("ItawSystemOrganizationProxyManager");
        Map map = (Map)mgr.getTawSystemOrganizationProxys(pageIndex,pageSize);	//map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List)map.get("result");
        request.setAttribute(Constants.TAWSYSTEMORGANIZATIONPROXY_LIST, list);
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
