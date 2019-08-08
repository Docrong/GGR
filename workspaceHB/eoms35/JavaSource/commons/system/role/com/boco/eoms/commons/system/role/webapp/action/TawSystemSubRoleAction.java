// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TawSystemSubRoleAction.java

package com.boco.eoms.commons.system.role.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.*;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemSubRoleForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.roleWorkflow.mgr.ITawSystemRoleWorkflowManager;
import com.boco.eoms.roleWorkflow.model.TawSystemRoleWorkflow;

import java.net.URLDecoder;
import java.util.*;
import javax.servlet.http.*;

import net.sf.json.*;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

public final class TawSystemSubRoleAction extends BaseAction {

    public TawSystemSubRoleAction() {
    }

    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();
        TawSystemSubRoleForm tawSystemSubRoleForm = (TawSystemSubRoleForm) form;
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        mgr.removeTawSystemSubRole(tawSystemSubRoleForm.getId());
        messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemSubRole.deleted"));
        saveMessages(request.getSession(), messages);
        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSubRoleForm tawSystemSubRoleForm = (TawSystemSubRoleForm) form;
        if (tawSystemSubRoleForm.getId() != null) {
            ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
            TawSystemSubRole tawSystemSubRole = mgr.getTawSystemSubRole(tawSystemSubRoleForm.getId());
            tawSystemSubRoleForm = (TawSystemSubRoleForm) convert(tawSystemSubRole);
            updateFormBean(mapping, request, tawSystemSubRoleForm);
        }
        request.setAttribute("roleId", new Long(tawSystemSubRoleForm.getRoleId()));
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();
        TawSystemSubRoleForm tawSystemSubRoleForm = (TawSystemSubRoleForm) form;
        boolean isNew = tawSystemSubRoleForm.getId() == null;
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) convert(tawSystemSubRoleForm);
        Map userMap = new HashMap();
        String users[] = tawSystemSubRoleForm.getUserId().split(",");
        for (int i = 0; i < users.length; i++)
            userMap.put(users[i], "1");

        mgr.saveTawSystemSubRole(tawSystemSubRole, userMap);
        ActionForward forward = null;
        if (isNew) {
            messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemSubRole.added"));
            saveMessages(request.getSession(), messages);
            forward = mapping.findForward("search");
        } else {
            messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemSubRole.updated"));
            saveMessages(request, messages);
            forward = mapping.findForward("search");
        }
        String path = forward.getPath() + "?method=search&roleId=" + tawSystemSubRole.getRoleId();
        return new ActionForward(path, false);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String roleId = request.getParameter("roleId");
        String pageIndexName = (new ParamEncoder("tawDemoMytableList")).encodeParameterName("p");
        Integer pageSize = new Integer(25);
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        Map map = mgr.getTawSystemSubRoles(pageIndex, pageSize, "roleId=" + roleId);
        List list = (List) map.get("result");
        request.setAttribute("tawSystemSubRoleList", list);
        request.setAttribute("resultSize", map.get("total"));
        return mapping.findForward("list");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String roleId = request.getParameter("roleId");
        request.setAttribute("roleId", roleId);
        return mapping.findForward("create");
    }

    public ActionForward createSubRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSubRoleForm tawSystemSubRoleForm = (TawSystemSubRoleForm) form;
        long roleId = tawSystemSubRoleForm.getRoleId();
        ITawSystemSubRoleManager smgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        smgr.createSubRolesByDept(roleId, String.valueOf(tawSystemSubRoleForm.getDeptId()));
        ActionForward forward = mapping.findForward("search");
        String path = forward.getPath() + "?method=search&roleId=" + roleId;
        return new ActionForward(path, false);
    }

    public void xsave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSubRoleForm tawSystemSubRoleForm = (TawSystemSubRoleForm) form;
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) convert(tawSystemSubRoleForm);
        Map userMap = new HashMap();
        String users[] = tawSystemSubRoleForm.getUserId().split(",");
        for (int i = 0; i < users.length; i++)
            userMap.put(users[i], "1");

        mgr.saveTawSystemSubRole(tawSystemSubRole, userMap);
        JSONUtil.success(response, "success");
    }

    public void jsonSubRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSubRoleForm tawSystemSubRoleForm = (TawSystemSubRoleForm) form;
        String subRolesJSON = StaticMethod.null2String(request.getParameter("subRoles"));
        try {
            JSONArray subRoleJSONList = new JSONArray();
            subRoleJSONList = JSONArray.fromString(subRolesJSON);

            List subRoleList = new ArrayList();

            for (int i = 0; i < subRoleJSONList.length(); i++) {
                JSONObject jsonObj = (JSONObject) subRoleJSONList.get(i);

                TawSystemSubRole subRole = (TawSystemSubRole) convert(tawSystemSubRoleForm);

                if (jsonObj.has(RoleConstants.SUBROLE_DEPT))
                    subRole.setDeptId(jsonObj
                            .getString(RoleConstants.SUBROLE_DEPT));

                String buzinessName = RoleXmlSchema.getInstance()
                        .getBusinessName(RoleConstants.SUBROLE_TYPE1);
                if (jsonObj.has("class1"))
                    subRole.setType1(jsonObj.getString(buzinessName));

                buzinessName = RoleXmlSchema.getInstance().getBusinessName(
                        RoleConstants.SUBROLE_TYPE2);
                if (jsonObj.has("class2"))
                    subRole.setType2(jsonObj.getString(buzinessName));

                buzinessName = RoleXmlSchema.getInstance().getBusinessName(
                        RoleConstants.SUBROLE_TYPE3);
                if (jsonObj.has("class3"))
                    subRole.setType3(jsonObj.getString(buzinessName));

                buzinessName = RoleXmlSchema.getInstance().getBusinessName(
                        RoleConstants.SUBROLE_TYPE4);
                if (jsonObj.has("class4"))
                    subRole.setType4(jsonObj.getString(buzinessName));

                if (jsonObj.has("subRoleName"))
                    subRole.setSubRoleName(jsonObj.getString("subRoleName"));

                subRoleList.add(subRole);
            }

            if (subRoleList.size() > 0) {
                TawSystemSubRole subRole = (TawSystemSubRole) subRoleList.get(0);
                long roleId = subRole.getRoleId();
                ITawSystemRoleManager mgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
                TawSystemRole role = mgr.getTawSystemRole(roleId);

                ITawSystemSubRoleManager smgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
                boolean b = true;

                if (String.valueOf(role.getRoleTypeId()).equals(RoleConstants.ROLETYPE_VIRTUAL))
                    b = false;
                String roleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("ignoreRoleId"));
                String areaid = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("ignoreAreaId"));
                if (roleid.equals(String.valueOf(roleId))) {
                    smgr.saveTawSystemSubRoles(subRoleList, b, areaid);
                } else {
                    smgr.saveTawSystemSubRoles(subRoleList, b);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONUtil.success(response, "success");
    }

    public void xGetUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String subRoleId = StaticMethod.null2String(request.getParameter("id"));
        ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        ITawSystemUserManager ugr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        Map map = mgr.getGroupUserbyroleid(subRoleId);
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem;
        for (Iterator it = map.keySet().iterator(); it.hasNext(); jsonRoot.put(jitem)) {
            String userId = it.next().toString();
            TawSystemUser user = ugr.getUserByuserid(userId);
            jitem = new JSONObject();
            String userName = user.getUsername();
            String groupType = (String) map.get(userId);
            String text = "2".equals(groupType) ? userName + "(" + "组长" + ")" : userName;
            jitem.put("id", user.getUserid());
            jitem.put("text", text);
            jitem.put("name", userName);
            jitem.put("grouptype", groupType);
            jitem.put("iconCls", "2".equals(groupType) ? "leader" : "user");
        }

        JSONUtil.print(response, jsonRoot.toString());
    }

    public void xUpdateUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String subRoleId = StaticMethod.null2String(request.getParameter("id"));
        String userIds = StaticMethod.null2String(request.getParameter("userId"));
        String roleType = StaticMethod.null2String(request.getParameter("roleType"));
        if ("" == roleType)
            roleType = "1";
        ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        mgr.updateUsers2SubRole(subRoleId, userIds, roleType);
        JSONUtil.success(response, "success");
    }

    public void xDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String subRoleIds = StaticMethod.null2String(request.getParameter("subRoleIds"));
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        mgr.deleteSubRoles(subRoleIds);
        JSONUtil.success(response, "success");
    }

    public ActionForward getRoleFiler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String modelId = StaticMethod.null2String(request.getParameter("id"));
        List filters = RoleMapSchema.getInstance().getRoleMappingListById(modelId);
        request.setAttribute("filter", filters);
        return mapping.findForward("roleFilter");
    }

    public void reflushWpsUserList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RoleStaticMethod.reFlushWpsUser();
    }

    public void xSetLeader(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String groupId = StaticMethod.null2String(request.getParameter("id"));
        String userId = StaticMethod.null2String(request.getParameter("userId"));
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        mgr.saveLeader(groupId, userId);
        JSONUtil.success(response, "虚拟组组长设置成功");
    }

    public void xSetName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));
        String newName = StaticMethod.null2String(URLDecoder.decode(request.getParameter("newName"), "UTF-8"));
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        TawSystemSubRole subrole = mgr.getTawSystemSubRole(id);
        subrole.setSubRoleName(newName);
        mgr.saveTawSystemSubRole(subrole);
        JSONUtil.success(response, "修改子角色名称成功");
    }

    public void xgetUsersBySubRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String subRoleId = StaticMethod.null2String(request.getParameter("subRoleId"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String currentUserId = sessionform.getUserid();
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        JSONArray jsonRoot = new JSONArray();
        List userList = userRefRoleMgr.getUserbyroleid(subRoleId);
        for (Iterator it = userList.iterator(); it.hasNext(); ) {
            JSONObject jitem = new JSONObject();
            TawSystemUser user = (TawSystemUser) it.next();
            if (!currentUserId.equals(user.getUserid())) {
                jitem.put("id", user.getUserid());
                jitem.put("text", user.getUsername());
                jitem.put("leaf", true);
                jitem.put("iconCls", "user");
                jitem.put("nodeType", "user");
                jsonRoot.put(jitem);
            }
        }

        JSONUtil.print(response, jsonRoot.toString());
    }

    public void xgetUsersByRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("node"));
        String roleId = StaticMethod.null2String(request.getParameter("roleId"));
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String currentUserId = sessionform.getUserid();
        JSONArray jsonRoot = new JSONArray();
        if ("-1".equals(id) || "".equals(id)) {
            ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
            List subRoleList = subRoleMgr.getTawSystemSubRoles(Long.parseLong(roleId));
            JSONObject jitem;
            for (Iterator iter = subRoleList.iterator(); iter.hasNext(); jsonRoot.put(jitem)) {
                jitem = new JSONObject();
                TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
                List userList = userRefRoleMgr.getUserbyroleid(subRole.getId());
                jitem.put("id", subRole.getId());
                jitem.put("text", subRole.getSubRoleName());
                if (userList == null || userList.size() <= 0)
                    jitem.put("leaf", true);
                else
                    jitem.put("leaf", false);
                jitem.put("iconCls", "subrole");
                jitem.put("nodeType", "subrole");
            }

        } else {
            List userList = userRefRoleMgr.getUserbyroleid(id);
            for (Iterator it = userList.iterator(); it.hasNext(); ) {
                JSONObject jitem = new JSONObject();
                TawSystemUser user = (TawSystemUser) it.next();
                if (!currentUserId.equals(user.getUserid())) {
                    jitem.put("id", user.getUserid());
                    jitem.put("text", user.getUsername());
                    jitem.put("leaf", true);
                    jitem.put("iconCls", "user");
                    jitem.put("nodeType", "user");
                    jsonRoot.put(jitem);
                }
            }

        }
        JSONUtil.print(response, jsonRoot.toString());
    }

    public void xgetAllRolesAndSubRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("node"));
        String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
        ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
        ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        RoleIdList roleIdList = (RoleIdList) getBean("roleIdInGroup");
        TawSystemRole srole = roleMgr.getTawSystemRole(roleIdList.getUserAdminRoleId().longValue());
        long proleId = srole.getParentId();
        JSONArray jsonRoot = new JSONArray();
        if ("-1".equals(id) && "root".equals(nodeType)) {
            JSONObject flowRole = new JSONObject();
            flowRole.put("id", "1");
            flowRole.put("text", "流程角色");
            flowRole.put("nodeType", "flowRole");
            flowRole.put("iconCls", "folder");
            flowRole.put("leaf", false);
            jsonRoot.put(flowRole);
        } else if ("workflow".equals(nodeType)) {
            List roleList = (ArrayList) roleMgr.getFlwRolesByWorkflowFlag(Integer.parseInt(id));
            JSONObject jitem;
            for (Iterator roleIt = roleList.iterator(); roleIt.hasNext(); jsonRoot.put(jitem)) {
                jitem = new JSONObject();
                TawSystemRole role = (TawSystemRole) roleIt.next();
                jitem.put("id", role.getRoleId());
                jitem.put("text", role.getRoleName());
                jitem.put("nodeType", "role");
                jitem.put("iconCls", "folder");
                if (role.getLeaf().equals(new Integer(1))) {
                    List subRoleList = subRoleMgr.getTawSystemSubRoles(role.getRoleId());
                    if (subRoleList == null || subRoleList.size() <= 0)
                        jitem.put("leaf", true);
                    else
                        jitem.put("leaf", false);
                } else {
                    jitem.put("leaf", false);
                }
            }

        } else if ("1".equals(id)) {
            ITawSystemRoleWorkflowManager workflowService = (ITawSystemRoleWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemRoleWorkflowManager");
            List workflows = workflowService.getTawSystemWorkflows();
            JSONObject j;
            for (Iterator it = workflows.iterator(); it.hasNext(); jsonRoot.put(j)) {
                TawSystemRoleWorkflow systemWorkflow = (TawSystemRoleWorkflow) it.next();
                String workflowId = systemWorkflow.getFlowId();
                String workflowName = systemWorkflow.getRemark();
                j = new JSONObject();
                j.put("id", workflowId);
                j.put("text", workflowName);
                j.put("nodeType", "workflow");
            }

        } else if ("2".equals(id)) {
            List roleList = (ArrayList) roleMgr.getChildrenByRoleId(1L);
            for (Iterator roleIt = roleList.iterator(); roleIt.hasNext(); ) {
                JSONObject jitem = new JSONObject();
                TawSystemRole role = (TawSystemRole) roleIt.next();
                if (role.getRoleId() != proleId) {
                    jitem.put("id", role.getRoleId());
                    jitem.put("text", role.getRoleName());
                    jitem.put("nodeType", "role");
                    jitem.put("iconCls", "folder");
                    if (role.getLeaf().equals(new Integer(1))) {
                        List subRoleList = subRoleMgr.getTawSystemSubRoles(role.getRoleId());
                        if (subRoleList == null || subRoleList.size() <= 0)
                            jitem.put("leaf", true);
                        else
                            jitem.put("leaf", false);
                    } else {
                        jitem.put("leaf", false);
                    }
                    jsonRoot.put(jitem);
                }
            }

        } else if ("role".equals(nodeType)) {
            List roleList = (ArrayList) roleMgr.getChildrenByRoleId(Long.parseLong(id));
            if (roleList == null || roleList.size() <= 0) {
                List subRoleList = subRoleMgr.getTawSystemSubRoles(Long.parseLong(id));
                List subRoleListUser = sessionform.getRolelist();
                if (sessionform.getUserid().equals("admin"))
                    subRoleListUser = subRoleList;
                for (Iterator iter = subRoleList.iterator(); iter.hasNext(); ) {
                    JSONObject jitem = new JSONObject();
                    TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
                    for (Iterator ituser = subRoleListUser.iterator(); ituser.hasNext(); ) {
                        TawSystemSubRole subRoleUser = (TawSystemSubRole) ituser.next();
                        if (subRoleUser.getId().equals(subRole.getId())) {
                            jitem.put("id", subRole.getId());
                            jitem.put("text", subRole.getSubRoleName());
                            jitem.put("nodeType", "subrole");
                            jitem.put("iconCls", "subrole");
                            jitem.put("leaf", true);
                            jitem.put("checked", false);
                            jsonRoot.put(jitem);
                        }
                    }

                }

            } else {
                JSONObject jitem;
                for (Iterator roleIt = roleList.iterator(); roleIt.hasNext(); jsonRoot.put(jitem)) {
                    jitem = new JSONObject();
                    TawSystemRole role = (TawSystemRole) roleIt.next();
                    jitem.put("id", role.getRoleId());
                    jitem.put("text", role.getRoleName());
                    jitem.put("nodeType", "role");
                    jitem.put("iconCls", "folder");
                    if (role.getLeaf().equals(new Integer(1))) {
                        List subRoleList = subRoleMgr.getTawSystemSubRoles(role.getRoleId());
                        if (subRoleList == null || subRoleList.size() <= 0)
                            jitem.put("leaf", true);
                        else
                            jitem.put("leaf", false);
                    } else {
                        jitem.put("leaf", false);
                    }
                }

            }
        }
        JSONUtil.print(response, jsonRoot.toString());
    }
}
