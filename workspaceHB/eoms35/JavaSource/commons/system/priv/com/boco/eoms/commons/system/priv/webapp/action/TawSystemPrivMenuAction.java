package com.boco.eoms.commons.system.priv.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivMenuForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemPrivMenu object
 *
 * @struts.action name="tawSystemPrivMenuForm" path="/tawSystemPrivMenus"
 * scope="request" validate="false" parameter="method"
 * input="mainMenu"
 * @struts.action name="tawSystemPrivMenuForm" path="/editTawSystemPrivMenu"
 * scope="request" validate="false" parameter="method"
 * input="list"
 * @struts.action name="tawSystemPrivMenuForm" path="/saveTawSystemPrivMenu"
 * scope="request" validate="true" parameter="method"
 * input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/tawSystemPrivMenu/tawSystemPrivMenuForm.jsp"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/tawSystemPrivMenu/tawSystemPrivMenuList.jsp"
 * @struts.action-forward name="search" path="/tawSystemPrivMenus.html"
 * redirect="true"
 */
public final class TawSystemPrivMenuAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public void xdelete(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));
        ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
        mgr.removeTawSystemPrivMenu(id);
    }

    public void xedit(ActionMapping mapping, ActionForm form,
                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemPrivMenuForm tawSystemPrivMenuForm = (TawSystemPrivMenuForm) form;

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String _strCurUserId = sessionform.getUserid();
        tawSystemPrivMenuForm.setOwnerId(_strCurUserId);

        if (tawSystemPrivMenuForm.getPrivid() != null) {
            ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
            TawSystemPrivMenu tawSystemPrivMenu = mgr
                    .getTawSystemPrivMenu(String.valueOf(tawSystemPrivMenuForm
                            .getPrivid()));
            tawSystemPrivMenuForm = (TawSystemPrivMenuForm) convert(tawSystemPrivMenu);
            updateFormBean(mapping, request, tawSystemPrivMenuForm);
        }
    }

    // AJAX 保存菜单方案
    public void xsave(ActionMapping mapping, ActionForm form,
                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSystemPrivMenuForm tawSystemPrivMenuForm = (TawSystemPrivMenuForm) form;
        boolean isNew = ("".equals(tawSystemPrivMenuForm.getPrivid()) || tawSystemPrivMenuForm
                .getPrivid() == null);

        ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
        TawSystemPrivMenu tawSystemPrivMenu = (TawSystemPrivMenu) convert(tawSystemPrivMenuForm);
        mgr.saveTawSystemPrivMenu(tawSystemPrivMenu);
    }

    public void xget(ActionMapping mapping, ActionForm form,
                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strId = request.getParameter("id");
        ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
        TawSystemPrivMenu priv = (TawSystemPrivMenu) mgr
                .getTawSystemPrivMenu(_strId);

        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(priv);

        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }

    // 获取菜单方案的JSON数据列表
    public void xGetJSONList(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int pageIndex = StaticMethod.nullObject2int(request
                .getParameter("pageIndex"));
        // The first pageIndex of Ext grid is 1 but here we need 0
        pageIndex = pageIndex > 0 ? pageIndex - 1 : 0;
        Integer pageSize = new Integer(StaticMethod.nullObject2int(request
                .getParameter("pageSize")));

        ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        Map map = (Map) mgr.getTawSystemPrivMenus(new Integer(pageIndex),
                pageSize); //
        List list;
        if (sessionform.isAdmin()) {
            list = (List) map.get("result");
        } else {
            list = mgr.getPrivMenubyUserid(sessionform.getUserid());
        }
        JSONArray jsonList = new JSONArray();

        for (int i = 0; i < list.size(); i++) {
            TawSystemPrivMenu priv = (TawSystemPrivMenu) list.get(i);
            JSONObject jitem = new JSONObject();
            jitem.put("id", priv.getPrivid());
            jitem.put("name", priv.getName());
            jitem.put("ownerId", priv.getOwnerId());
            jitem.put("remark", priv.getRemark());
            jsonList.put(jitem);
        }

        JSONObject jsonRoot = new JSONObject();
        jsonRoot.put("total", (Integer) map.get("total"));
        jsonRoot.put("rows", jsonList);

        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }

    /**
     *
     */
    public ActionForward next(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemPrivMenuForm tawSystemPrivMenuForm = (TawSystemPrivMenuForm) form;

        boolean isNew = ("".equals(tawSystemPrivMenuForm.getPrivid()) || tawSystemPrivMenuForm
                .getPrivid() == null);

        ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
        TawSystemPrivMenu tawSystemPrivMenu = (TawSystemPrivMenu) convert(tawSystemPrivMenuForm);
        mgr.saveTawSystemPrivMenu(tawSystemPrivMenu);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSystemPrivMenu.added"));
            saveMessages(request.getSession(), messages);
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSystemPrivMenu.updated"));
            saveMessages(request, messages);
        }

        request.setAttribute("name", tawSystemPrivMenu.getName());
        request.setAttribute("privid", tawSystemPrivMenu.getPrivid());
        return mapping.findForward("next");
    }

}
