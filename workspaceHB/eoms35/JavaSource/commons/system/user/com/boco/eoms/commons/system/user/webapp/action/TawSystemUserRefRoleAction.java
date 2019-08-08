package com.boco.eoms.commons.system.user.webapp.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.webapp.form.TawSystemUserRefRoleForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;

/**
 * Action class to handle CRUD on a TawSystemUserRefRole object
 *
 * @struts.action name="tawSystemUserRefRoleForm" path="/tawSystemUserRefRoles"
 * scope="request" validate="false" parameter="method"
 * input="mainMenu"
 * @struts.action name="tawSystemUserRefRoleForm"
 * path="/editTawSystemUserRefRole" scope="request"
 * validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemUserRefRoleForm"
 * path="/saveTawSystemUserRefRole" scope="request"
 * validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/tawSystemUserRefRole/tawSystemUserRefRoleForm.jsp"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/tawSystemUserRefRole/tawSystemUserRefRoleList.jsp"
 * @struts.action-forward name="search" path="/tawSystemUserRefRoles.html"
 * redirect="true"
 */
public final class TawSystemUserRefRoleAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        ActionMessages messages = new ActionMessages();
        TawSystemUserRefRoleForm tawSystemUserRefRoleForm = (TawSystemUserRefRoleForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        mgr.removeTawSystemUserRefRole(tawSystemUserRefRoleForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "tawSystemUserRefRole.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

        TawSystemUserRefRoleForm tawSystemUserRefRoleForm = (TawSystemUserRefRoleForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSystemUserRefRoleForm.getId() != null) {
            ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
            TawSystemUserRefRole tawSystemUserRefRole = mgr
                    .getTawSystemUserRefRole(tawSystemUserRefRoleForm.getId());
            tawSystemUserRefRoleForm = (TawSystemUserRefRoleForm) convert(tawSystemUserRefRole);
            updateFormBean(mapping, request, tawSystemUserRefRoleForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSystemUserRefRoleForm tawSystemUserRefRoleForm = (TawSystemUserRefRoleForm) form;
        boolean isNew = ("".equals(tawSystemUserRefRoleForm.getId()) || tawSystemUserRefRoleForm
                .getId() == null);

        ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        TawSystemUserRefRole tawSystemUserRefRole = (TawSystemUserRefRole) convert(tawSystemUserRefRoleForm);
        mgr.saveTawSystemUserRefRole(tawSystemUserRefRole);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSystemUserRefRole.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("search");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSystemUserRefRole.updated"));
            saveMessages(request, messages);

            return mapping.findForward("search");
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }

        TawSystemUserRefRoleForm tawSystemUserRefRoleForm = (TawSystemUserRefRoleForm) form;
        TawSystemUserRefRole tawSystemUserRefRole = (TawSystemUserRefRole) convert(tawSystemUserRefRoleForm);

        ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        request.setAttribute(Constants.TAWSYSTEMUSERREFROLE_LIST, mgr
                .getTawSystemUserRefRoles(tawSystemUserRefRole));

        return mapping.findForward("list");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * 取得某用户所有大角色和子角色，返回JSON
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xgetRoleAndSubRole(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        JSONArray jsonRoot = new JSONArray();
        if ("-1".equals(id) || "".equals(id)) {
            List subRoleList = userRefRoleMgr.getSubRoleByUserId(userId, RoleConstants.ROLETYPE_SUBROLE);
            HashMap hm = new HashMap();
            for (Iterator iter = subRoleList.iterator(); iter.hasNext(); ) {
                JSONObject jitem = new JSONObject();
                TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
                if (null == hm.get(String.valueOf(subRole.getRoleId()))) {
                    ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
                    TawSystemRole role = roleMgr.getTawSystemRole(subRole.getRoleId());
                    jitem.put("id", role.getRoleId());
                    jitem.put("name", role.getRoleName());
                    jitem.put("iconCls", "subrole");
                    jsonRoot.put(jitem);
                    hm.put(String.valueOf(role.getRoleId()), role);
                }
            }
        } else {
            List subRoleList = userRefRoleMgr.getSubRoleByUserId(userId, RoleConstants.ROLETYPE_SUBROLE);
            for (Iterator iter = subRoleList.iterator(); iter.hasNext(); ) {
                JSONObject jitem = new JSONObject();
                TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
                if (id.equals(String.valueOf(subRole.getRoleId()))) {
                    jitem.put("id", subRole.getId());
                    jitem.put("name", subRole.getSubRoleName());
                    jitem.put("iconCls", "subrole");
                    jitem.put("leaf", true);
                    jsonRoot.put(jitem);
                }
            }
        }
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 取得某用户所有子角色，返回JSON
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xgetSubRolsByUser(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));
        ITawSystemUserManager userMgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        TawSystemUser user = userMgr.getTawSystemUser(id);
        String userId = "";
        if (null != user) {
            userId = user.getUserid();
        }
        List subRoleList = userRefRoleMgr.getSubRoleByUserId(userId, RoleConstants.ROLETYPE_SUBROLE);
        JSONArray jsonRoot = new JSONArray();
        for (Iterator iter = subRoleList.iterator(); iter.hasNext(); ) {
            JSONObject jitem = new JSONObject();
            TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
            jitem.put("id", subRole.getId());
            jitem.put("text", subRole.getSubRoleName());
            jitem.put("iconCls", "subrole");
            jitem.put("checked", false);
            jitem.put("leaf", true);
            jsonRoot.put(jitem);
        }
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 保存用户角色关联(角色批量复制)，返回JSON
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xsaveUserRefRole(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String[] subRoleIdArray = request.getParameterValues("subRoleIdArray");
        String userIds = StaticMethod.null2String(request.getParameter("userId"));
        String[] userIdArray = userIds.split(",");
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");

        if (null == subRoleIdArray) {
            JSONUtil.fail(response, "请选择子角色！");
        } else if (null == userIdArray || "".equals(userIds.trim())) {
            JSONUtil.fail(response, "请选择人员！");
        } else {
            try {
                for (int i = 0; i < subRoleIdArray.length; i++) {
                    String subRoleId = subRoleIdArray[i];
                    for (int j = 0; j < userIdArray.length; j++) {
                        String userId = userIdArray[j];
                        if (!userRefRoleMgr.isExist(subRoleId, userId)) {
                            TawSystemUserRefRole userRefRole = new TawSystemUserRefRole();
                            userRefRole.setSubRoleid(subRoleId);
                            userRefRole.setUserid(userId);
                            userRefRole.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
                            userRefRoleMgr.saveTawSystemUserRefRole(userRefRole);
                        }
                    }
                }
            } catch (Exception e) {
                JSONUtil.fail(response, "保存失败！");
                e.printStackTrace();
            }
            JSONUtil.success(response, "保存成功！");
        }
    }

    /**
     * 保存用户调整后的子角色，返回JSON
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void xsaveAdjustSubRoles(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String[] selSubRoleIdArray = request.getParameterValues("subRoleIds");
        String id = StaticMethod.null2String(request.getParameter("id"));
        String selSubRoleIds = StaticMethod.null2String(request.getParameter("subRoleId"));
        String[] addSubRoleIdArray = selSubRoleIds.split(",");
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        ITawSystemUserManager userMgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        TawSystemUser user = userMgr.getTawSystemUser(id);
        String userId = user.getUserid();
        //保存调整后的结果,未勾选的已有子角色删除,从树图中新选择的子角色增加
        try {
            userRefRoleMgr.saveAdujustUserRefRole(userId, selSubRoleIdArray, addSubRoleIdArray);
        } catch (Exception e) {
            JSONUtil.fail(response, "保存失败！");
            e.printStackTrace();
        }
        JSONUtil.success(response, "保存成功！");
    }
}
