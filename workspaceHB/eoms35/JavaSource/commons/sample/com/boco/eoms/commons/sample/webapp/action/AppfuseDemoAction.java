
package com.boco.eoms.commons.sample.webapp.action;

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
import com.boco.eoms.commons.sample.model.AppfuseDemo;
import com.boco.eoms.commons.sample.service.IAppfuseDemoManager;
import com.boco.eoms.commons.sample.webapp.form.AppfuseDemoForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a AppfuseDemo object
 *
 * @struts.action name="appfuseDemoForm" path="/appfuseDemos" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="appfuseDemoForm" path="/editAppfuseDemo" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="appfuseDemoForm" path="/saveAppfuseDemo" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/appfuseDemo/appfuseDemoForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/appfuseDemo/appfuseDemoList.jsp"
 * @struts.action-forward name="search" path="/appfuseDemos.html" redirect="true"
 */
public final class AppfuseDemoAction extends BaseAction {
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
        AppfuseDemoForm appfuseDemoForm = (AppfuseDemoForm) form;

        // Exceptions are caught by ActionExceptionHandler
        IAppfuseDemoManager mgr = (IAppfuseDemoManager) getBean("IappfuseDemoManager");
        mgr.removeAppfuseDemo(appfuseDemoForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("appfuseDemo.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        AppfuseDemoForm appfuseDemoForm = (AppfuseDemoForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (appfuseDemoForm.getId() != null) {
            IAppfuseDemoManager mgr = (IAppfuseDemoManager) getBean("IappfuseDemoManager");
            AppfuseDemo appfuseDemo = mgr.getAppfuseDemo(appfuseDemoForm.getId());
            appfuseDemoForm = (AppfuseDemoForm) convert(appfuseDemo);
            updateFormBean(mapping, request, appfuseDemoForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        AppfuseDemoForm appfuseDemoForm = (AppfuseDemoForm) form;
        boolean isNew = ("".equals(appfuseDemoForm.getId()) || appfuseDemoForm.getId() == null);

        IAppfuseDemoManager mgr = (IAppfuseDemoManager) getBean("IappfuseDemoManager");
        AppfuseDemo appfuseDemo = (AppfuseDemo) convert(appfuseDemoForm);
        mgr.saveAppfuseDemo(appfuseDemo);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("appfuseDemo.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("appfuseDemo.updated"));
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
	    	final int pageSize = 25;   
	    	final int pageIndex = GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1);  //当前页数

        IAppfuseDemoManager mgr = (IAppfuseDemoManager) getBean("IappfuseDemoManager");
        Map map = (Map)mgr.getAppfuseDemos(new Integer(pageIndex),new Integer(pageSize));	
        List list = (List)map.get("result");
        request.setAttribute(Constants.APPFUSEDEMO_LIST, list);
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
