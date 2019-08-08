
package com.boco.eoms.extra.supplierkpi.webapp.action;

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
import com.boco.eoms.extra.supplierkpi.model.TawsuCheckModule;
import com.boco.eoms.extra.supplierkpi.service.ITawsuCheckModuleManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawsuCheckModuleForm;

import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawsuCheckModule object
 *
 * @struts.action name="tawsuCheckModuleForm" path="/tawsuCheckModules" scope="request"
 * validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawsuCheckModuleForm" path="/editTawsuCheckModule" scope="request"
 * validate="false" parameter="method" input="list"
 * @struts.action name="tawsuCheckModuleForm" path="/saveTawsuCheckModule" scope="request"
 * validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit" path="/WEB-INF/pages/tawsuCheckModule/tawsuCheckModuleForm.jsp"
 * @struts.action-forward name="list" path="/WEB-INF/pages/tawsuCheckModule/tawsuCheckModuleList.jsp"
 * @struts.action-forward name="search" path="/tawsuCheckModules.html" redirect="true"
 */
public final class TawsuCheckModuleAction extends BaseAction {
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
        TawsuCheckModuleForm tawsuCheckModuleForm = (TawsuCheckModuleForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawsuCheckModuleManager mgr = (ITawsuCheckModuleManager) getBean("ItawsuCheckModuleManager");
        mgr.removeTawsuCheckModule(tawsuCheckModuleForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("tawsuCheckModule.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {
        TawsuCheckModuleForm tawsuCheckModuleForm = (TawsuCheckModuleForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawsuCheckModuleForm.getId() != null) {
            ITawsuCheckModuleManager mgr = (ITawsuCheckModuleManager) getBean("ItawsuCheckModuleManager");
            TawsuCheckModule tawsuCheckModule = mgr.getTawsuCheckModule(tawsuCheckModuleForm.getId());
            tawsuCheckModuleForm = (TawsuCheckModuleForm) convert(tawsuCheckModule);
            updateFormBean(mapping, request, tawsuCheckModuleForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawsuCheckModuleForm tawsuCheckModuleForm = (TawsuCheckModuleForm) form;
        boolean isNew = ("".equals(tawsuCheckModuleForm.getId()) || tawsuCheckModuleForm.getId() == null);

        ITawsuCheckModuleManager mgr = (ITawsuCheckModuleManager) getBean("ItawsuCheckModuleManager");
        TawsuCheckModule tawsuCheckModule = (TawsuCheckModule) convert(tawsuCheckModuleForm);
        mgr.saveTawsuCheckModule(tawsuCheckModule);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("tawsuCheckModule.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("tawsuCheckModule.updated"));
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
        final int pageSize = 25;   //ÿҳ��ʾ������
        final int pageIndex = GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : ((Integer.parseInt(request.getParameter(pageIndexName))) - 1);  //��ǰҳ��


        ITawsuCheckModuleManager mgr = (ITawsuCheckModuleManager) getBean("ItawsuCheckModuleManager");
        Map map = (Map) mgr.getTawsuCheckModules(pageIndex, pageSize);    //map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List) map.get("result");
        request.setAttribute(Constants.TAWSUCHECKMODULE_LIST, list);
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
