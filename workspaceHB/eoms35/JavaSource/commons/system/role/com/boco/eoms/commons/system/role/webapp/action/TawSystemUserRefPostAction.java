
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
import com.boco.eoms.commons.system.role.model.TawSystemUserRefPost;
import com.boco.eoms.commons.system.role.service.ITawSystemUserRefPostManager;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemUserRefPostForm;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemUserRefPost object
 *
 * @struts.action name="tawSystemUserRefPostForm" path="/tawSystemUserRefPosts" scope="request"
 *  validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemUserRefPostForm" path="/editTawSystemUserRefPost" scope="request"
 *  validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemUserRefPostForm" path="/saveTawSystemUserRefPost" scope="request"
 *  validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawSystemUserRefPost/tawSystemUserRefPostForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawSystemUserRefPost/tawSystemUserRefPostList.jsp"
 * @struts.action-forward name="search" path="/tawSystemUserRefPosts.html" redirect="true"
 */
public final class TawSystemUserRefPostAction extends BaseAction {
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
        TawSystemUserRefPostForm tawSystemUserRefPostForm = (TawSystemUserRefPostForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemUserRefPostManager mgr = (ITawSystemUserRefPostManager) getBean("ItawSystemUserRefPostManager");
        mgr.removeTawSystemUserRefPost(tawSystemUserRefPostForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                     new ActionMessage("tawSystemUserRefPost.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        TawSystemUserRefPostForm tawSystemUserRefPostForm = (TawSystemUserRefPostForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSystemUserRefPostForm.getId() != null) {
            ITawSystemUserRefPostManager mgr = (ITawSystemUserRefPostManager) getBean("ItawSystemUserRefPostManager");
            TawSystemUserRefPost tawSystemUserRefPost = mgr.getTawSystemUserRefPost(tawSystemUserRefPostForm.getId());
            tawSystemUserRefPostForm = (TawSystemUserRefPostForm) convert(tawSystemUserRefPost);
            updateFormBean(mapping, request, tawSystemUserRefPostForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemUserRefPostForm tawSystemUserRefPostForm = (TawSystemUserRefPostForm) form;
        boolean isNew = ("".equals(tawSystemUserRefPostForm.getId()) || tawSystemUserRefPostForm.getId() == null);

        ITawSystemUserRefPostManager mgr = (ITawSystemUserRefPostManager) getBean("ItawSystemUserRefPostManager");
        TawSystemUserRefPost tawSystemUserRefPost = (TawSystemUserRefPost) convert(tawSystemUserRefPostForm);
        mgr.saveTawSystemUserRefPost(tawSystemUserRefPost);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemUserRefPost.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                         new ActionMessage("tawSystemUserRefPost.updated"));
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

        ITawSystemUserRefPostManager mgr = (ITawSystemUserRefPostManager) getBean("ItawSystemUserRefPostManager");
        Map map = (Map)mgr.getTawSystemUserRefPosts(pageIndex,pageSize);
        List list = (List)map.get("result");
        request.setAttribute(Constants.TAWSYSTEMUSERREFPOST_LIST, list);
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
