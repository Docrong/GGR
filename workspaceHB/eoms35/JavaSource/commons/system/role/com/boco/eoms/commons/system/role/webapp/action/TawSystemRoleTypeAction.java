package com.boco.eoms.commons.system.role.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.model.TawSystemRoleType;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleTypeManager;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemRoleTypeForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemRoleType object
 *
 * @struts.action name="tawSystemRoleTypeForm" path="/tawSystemRoleTypes" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemRoleTypeForm" path="/editTawSystemRoleType" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemRoleTypeForm" path="/saveTawSystemRoleType" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSystemRoleType/tawSystemRoleTypeForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSystemRoleType/tawSystemRoleTypeList.jsp"
 * @struts.action-forward name="search" path="/tawSystemRoleTypes.html" redirect="true"
 */
public final class TawSystemRoleTypeAction extends BaseAction {
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
        TawSystemRoleTypeForm tawSystemRoleTypeForm = (TawSystemRoleTypeForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemRoleTypeManager mgr = (ITawSystemRoleTypeManager) getBean("ItawSystemRoleTypeManager");
        mgr.removeTawSystemRoleType(tawSystemRoleTypeForm.getRoletype_id());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSystemRoleType.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSystemRoleTypeForm tawSystemRoleTypeForm = (TawSystemRoleTypeForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSystemRoleTypeForm.getRoletype_id() != null) {
            ITawSystemRoleTypeManager mgr = (ITawSystemRoleTypeManager) getBean("ItawSystemRoleTypeManager");
            TawSystemRoleType tawSystemRoleType = mgr.getTawSystemRoleType(tawSystemRoleTypeForm.getRoletype_id());
            tawSystemRoleTypeForm = (TawSystemRoleTypeForm) convert(tawSystemRoleType);
            updateFormBean(mapping, request, tawSystemRoleTypeForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemRoleTypeForm tawSystemRoleTypeForm = (TawSystemRoleTypeForm) form;
        boolean isNew = ("".equals(tawSystemRoleTypeForm.getRoletype_id()) || tawSystemRoleTypeForm.getRoletype_id() == null);

        ITawSystemRoleTypeManager mgr = (ITawSystemRoleTypeManager) getBean("ItawSystemRoleTypeManager");
        TawSystemRoleType tawSystemRoleType = (TawSystemRoleType) convert(tawSystemRoleTypeForm);
        mgr.saveTawSystemRoleType(tawSystemRoleType);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemRoleType.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemRoleType.updated"));
            saveMessages(request, messages);

            return mapping.findForward("search");
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE); 
	    	final Integer pageSize = new Integer(25); 
	    	final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1)); 

        ITawSystemRoleTypeManager mgr = (ITawSystemRoleTypeManager) getBean("ItawSystemRoleTypeManager");
        Map map = (Map)mgr.getTawSystemRoleTypes(pageIndex,pageSize);	
        List list = (List)map.get("result");
        request.setAttribute(Constants.TAWSYSTEMROLETYPE_LIST, list);
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
