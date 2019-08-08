/*
 * Created on 2008-5-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.sheet.expert.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.sheet.expert.service.ITawSheetExpertManager;
import com.boco.eoms.commons.sheet.expert.util.StaticMethod;
import com.boco.eoms.commons.sheet.expert.webapp.form.TawSheetExpertForm;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.role.model.TawSystemDeptRefPost;
import com.boco.eoms.commons.system.role.service.ITawSystemDeptRefPostManager;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemDeptRefPostForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.webapp.form.TawSystemUserForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;


/**
 * @author admin
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSheetExpertAction extends BaseAction {
    public ActionForward xdelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSheetExpertForm expertForm = (TawSheetExpertForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSheetExpertManager mgr = (ITawSheetExpertManager) getBean("ItawSheetExpertManager");
        mgr.removeTawSheetExpert(expertForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "awSheetExpertForm.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return null;
    }

    public ActionForward xedit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TawSheetExpertForm tawSheetExpertForm = (TawSheetExpertForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSheetExpertForm.getId() != null) {
            ITawSheetExpertManager mgr = (ITawSheetExpertManager) getBean("ItawSheetExpertManager");

            TawSheetExpert tawSheetExpert = (TawSheetExpert) convert(tawSheetExpertForm);
            mgr.saveTawSheetExpert(tawSheetExpert);
            updateFormBean(mapping, request, tawSheetExpertForm);
        }

        return null;
    }

    public ActionForward getNodes(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String nodeId = request.getParameter("node");
        TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
        //JSONArray jsonRoot = treebo.getExpertTree(nodeId, "");
        JSONArray jsonRoot = StaticMethod.getExpertTree(nodeId, "");

        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    public ActionForward xget(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String expertId = request.getParameter("id");

        ITawSheetExpertManager mgr = (ITawSheetExpertManager) getBean("ItawSheetExpertManager");
        TawSheetExpert expert = (TawSheetExpert) mgr.getTawSheetExpert(Integer.valueOf(expertId));
        String type = request.getParameter("nodeType");

        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(expert);

        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSheetExpertForm expertFrom = (TawSheetExpertForm) form;
        boolean isNew = ("".equals(expertFrom.getId()) || expertFrom.getId() == null);

        ITawSheetExpertManager mgr = (ITawSheetExpertManager) getBean("ItawSheetExpertManager");
        TawSheetExpert expert = (TawSheetExpert) convert(expertFrom);


        mgr.saveTawSheetExpert(expert);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("TawSheetExpert.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("null");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("TawSheetExpert.updated"));
            saveMessages(request, messages);

            return mapping.findForward("null");
        }
    }
}
