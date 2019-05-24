
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
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;
import com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemDeptRefPostForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemDeptRefPost object
 *
 * @struts.action name="tawSystemDeptRefPostForm" path="/tawSystemDeptRefPosts" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemDeptRefPostForm" path="/editTawSystemDeptRefPost" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemDeptRefPostForm" path="/saveTawSystemDeptRefPost" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSystemDeptRefPost/tawSystemDeptRefPostForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSystemDeptRefPost/tawSystemDeptRefPostList.jsp"
 * @struts.action-forward name="search" path="/tawSystemDeptRefPosts.html" redirect="true"
 */
public final class TawSystemDeptRefPostAction extends BaseAction {
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
        TawSystemDeptRefPostForm tawSystemDeptRefPostForm = (TawSystemDeptRefPostForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemDeptRefPostManager mgr = (ITawSystemDeptRefPostManager) getBean("ItawSystemDeptRefPostManager");
        mgr.removeTawSystemDeptRefPost(tawSystemDeptRefPostForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSystemDeptRefPost.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSystemDeptRefPostForm tawSystemDeptRefPostForm = (TawSystemDeptRefPostForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSystemDeptRefPostForm.getId() != null) {
            ITawSystemDeptRefPostManager mgr = (ITawSystemDeptRefPostManager) getBean("ItawSystemDeptRefPostManager");
            TawSystemDeptRefPost tawSystemDeptRefPost = mgr.getTawSystemDeptRefPost(tawSystemDeptRefPostForm.getId());
            tawSystemDeptRefPostForm = (TawSystemDeptRefPostForm) convert(tawSystemDeptRefPost);
            updateFormBean(mapping, request, tawSystemDeptRefPostForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemDeptRefPostForm tawSystemDeptRefPostForm = (TawSystemDeptRefPostForm) form;
        boolean isNew = ("".equals(tawSystemDeptRefPostForm.getId()) || tawSystemDeptRefPostForm.getId() == null);

        ITawSystemDeptRefPostManager mgr = (ITawSystemDeptRefPostManager) getBean("ItawSystemDeptRefPostManager");
        TawSystemDeptRefPost tawSystemDeptRefPost = (TawSystemDeptRefPost) convert(tawSystemDeptRefPostForm);
        mgr.saveTawSystemDeptRefPost(tawSystemDeptRefPost);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemDeptRefPost.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemDeptRefPost.updated"));
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

        ITawSystemDeptRefPostManager mgr = (ITawSystemDeptRefPostManager) getBean("ItawSystemDeptRefPostManager");
        Map map = (Map)mgr.getTawSystemDeptRefPosts(pageIndex,pageSize);
        List list = (List)map.get("result");
        request.setAttribute(Constants.TAWSYSTEMDEPTREFPOST_LIST, list);
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
