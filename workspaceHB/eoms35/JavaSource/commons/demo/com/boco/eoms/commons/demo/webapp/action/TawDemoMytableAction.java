
package com.boco.eoms.commons.demo.webapp.action;

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
import com.boco.eoms.commons.demo.model.TawDemoMytable;
import com.boco.eoms.commons.demo.service.TawDemoMytableManager;
import com.boco.eoms.commons.demo.webapp.form.TawDemoMytableForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawDemoMytable object
 *
 * @struts.action name="tawDemoMytableForm" path="/tawDemoMytables" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawDemoMytableForm" path="/editTawDemoMytable" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawDemoMytableForm" path="/saveTawDemoMytable" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawDemoMytable/tawDemoMytableForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawDemoMytable/tawDemoMytableList.jsp"
 * @struts.action-forward name="search" path="/tawDemoMytables.html" redirect="true"
 */
public final class TawDemoMytableAction extends BaseAction {
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
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        ActionMessages messages = new ActionMessages();
        TawDemoMytableForm tawDemoMytableForm = (TawDemoMytableForm) form;

        // Exceptions are caught by ActionExceptionHandler
        TawDemoMytableManager mgr = (TawDemoMytableManager) getBean("tawDemoMytableManager");
        mgr.removeTawDemoMytable(tawDemoMytableForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawDemoMytable.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

        TawDemoMytableForm tawDemoMytableForm = (TawDemoMytableForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawDemoMytableForm.getId() != null) {
            TawDemoMytableManager mgr = (TawDemoMytableManager) getBean("tawDemoMytableManager");
            TawDemoMytable tawDemoMytable = mgr.getTawDemoMytable(tawDemoMytableForm.getId());
            tawDemoMytableForm = (TawDemoMytableForm) convert(tawDemoMytable);
            updateFormBean(mapping, request, tawDemoMytableForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawDemoMytableForm tawDemoMytableForm = (TawDemoMytableForm) form;
        boolean isNew = ("".equals(tawDemoMytableForm.getId()) || tawDemoMytableForm.getId() == null);

        TawDemoMytableManager mgr = (TawDemoMytableManager) getBean("tawDemoMytableManager");
        TawDemoMytable tawDemoMytable = (TawDemoMytable) convert(tawDemoMytableForm);
        mgr.saveTawDemoMytable(tawDemoMytable);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawDemoMytable.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawDemoMytable.updated"));
            saveMessages(request, messages);

            return mapping.findForward("search");
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	String userId = request.getParameter("userId");
    	String postId = request.getParameter("postId");
    	String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
    			org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // 页数的参数名,其中"tawDemoMytableList"是页面中displayTag的id
    	final int pageSize = 25;   //每页显示的条数
    	final int pageIndex = GenericValidator.isBlankOrNull(request.getParameter(pageIndexName))?0:(Integer.parseInt(request.getParameter(pageIndexName)) - 1);  //当前页数

        TawDemoMytableManager mgr = (TawDemoMytableManager) getBean("tawDemoMytableManager");
        Map map = (Map)mgr.getTawDemoMytables(new Integer(pageIndex),new Integer(pageSize));	//map中有两个key值，一个是"total",保存总记录条数，另一个是"result"，保存要显示页面的list
        List list = (List)map.get("result");
        //request.setAttribute(Constants.TAWDEMOMYTABLE_LIST, list);
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
