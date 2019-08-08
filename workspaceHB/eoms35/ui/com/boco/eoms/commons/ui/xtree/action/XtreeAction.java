package com.boco.eoms.commons.ui.xtree.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemPostManager;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleRefWorkflowManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.workbench.contact.service.bo.TawWorkbenchContactBO;


import com.boco.eoms.base.util.*;
import com.boco.eoms.commons.system.priv.a_fsh.Ip_Double_choice;
import com.boco.eoms.commons.system.priv.a_fsh.Ipconfig_creat;
import com.boco.eoms.commons.system.role.model.*;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.ui.util.JSONUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;


/**
 * <p>
 * Title:XtreeAction
 * </p>
 * <p>
 * Description:ajax树图类暂时入口，各action应该放到各自的模块的action中，避免耦合
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007 0514
 * </p>
 * <p>
 * Company: BOCO
 * </p>
 */

public final class XtreeAction extends BaseAction {

    /**
     * 未定义method时，转向到main
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return main(mapping, form, request, response);
    }

    public ActionForward main(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("main");
    }

    /**
     * 导航树
     *
     * @template tpl-priv-xtree
     */
    public ActionForward nav(ActionMapping mapping, ActionForm actionForm,
                             HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // 获取当前用户
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        boolean _bIsAdmin = "admin".equals(sessionform.getUserid());

        String node = StaticMethod.null2String(request.getParameter("node"));
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-priv-xtree");
        List list = null;
        EOMSAttributes attr = (EOMSAttributes) ApplicationContextHolder.getInstance().getBean("eomsAttributes");
        String menu_ip = attr.getMenu_ipOpen();
        if (menu_ip.equals("on")) {
            String ip = request.getServerName();
            int port = request.getServerPort();
            String type = Ip_Double_choice.checkIp(ip + ":" + port);
            com.boco.eoms.commons.system.priv.a_fsh.Ipconfig ipconfig = Ipconfig_creat.getIpconfig();
            if (_bIsAdmin) {
                ITawSystemPrivOperationManager operationMgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
                list = Ip_Double_choice.ip2DoubleIpFromOperation(operationMgr.getTawSystemPrivOerationsByParentCode(node), ipconfig, type);
            } else {
                list = Ip_Double_choice.ip2DoubleIpFromOperation(PrivMgrLocator.getPrivMgr().listOpertion(sessionform.getUserid(), sessionform.getDeptid(), sessionform.getRolelist(), "MOUDLE_FUNCTION", node), ipconfig, type);
            }
        } else

            // TODO 重构代码，将业务写入mgr中，by leo
            if (_bIsAdmin) {
                ITawSystemPrivOperationManager operationMgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
                list = operationMgr
                        .getTawSystemPrivOerationsByParentCode(node);
            } else {
                list = PrivMgrLocator.getPrivMgr().listOpertion(
                        sessionform.getUserid(), sessionform.getDeptid(),
                        sessionform.getRolelist(),
                        PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, node);
            }

        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    /**
     * 部门树
     *
     * @template tpl-dept-xtree
     */
    public ActionForward dept(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-dept-xtree");
        ArrayList list = new ArrayList();

        try {
            TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
            TawSystemDept sept = deptbo.getDeptinfobydeptid(node, "0");

            if (sept.getTmpdeptid() != null && !"".equals(sept.getTmpdeptid())) {
                list = (ArrayList) deptbo.getNextLevecDepts(
                        sept.getTmpdeptid(), "0");
            } else {
                list = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            }
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门树图时报错：" + ex);
        }

        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    /**
     * 部门树 值班用
     *
     * @template tpl-dept-xtree
     */
    public ActionForward deptForDuty(ActionMapping mapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = saveSessionBeanForm.getUserid();

        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-dept-xtree");

        ArrayList list = new ArrayList();

        try {
            if (userId.equals(StaticVariable.ADMIN)) {
                TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
                list = (ArrayList) deptbo.getNextLevecDepts(node, "0");

            } else {
                TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
                if (node.equals("-1")) {

                    List privList = privBO.getPermissions(userId,
                            StaticVariable.PRIV_ASSIGNTYPE_USER,
                            StaticVariable.PRIV_TYPE_REGION_DEPT);
                    TawSystemDept tawSystemDept;
                    for (Iterator it = privList.iterator(); it.hasNext(); list.add(tawSystemDept)) {

                        TawSystemPrivRegion tawSystemPrivRegion = (TawSystemPrivRegion) it
                                .next();

                        String deptId = tawSystemPrivRegion.getRegionid();
                        ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
                                .getInstance().getBean("ItawSystemDeptManager");
                        tawSystemDept = mgr.getDeptinfobydeptid(
                                deptId, "0");
                        tawSystemDept.setLeaf("1");
                    }

                } else {
                    TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
                    list = (ArrayList) deptbo.getNextLevecDepts(node, "0");
                }

            }

        } catch (Exception ex) {
            BocoLog.error(this, "生成部门值班树图时报错：" + ex);
        }
        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    /**
     * 大角色树图
     * roleTypeId=1 显示流程大角色
     * others 显示系统大角色
     *
     * @template tpl-role-xtree
     */
    public ActionForward role(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        long node = StaticMethod.null2Long(request.getParameter("node"));
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-role-xtree");
        String roleTypeId = request.getParameter("roleTypeId");
        ArrayList list = new ArrayList();

        try {

            ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
            if (RoleConstants.flowRole.equals(roleTypeId)) {
                // roleTypeId=1,则按流程取角色，将nodeId视为workFlowFlag
                int workflowFlag = (int) node;
                list = (ArrayList) roleMgr
                        .getFlwRolesByWorkflowFlag(workflowFlag);
            } else {
                // 否则取系统角色
                list = (ArrayList) roleMgr.getSysRolesByRoleId(node);
            }

        } catch (Exception ex) {
            BocoLog.error(this, "生成角色树图时报错：" + ex);
        }

        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    /**
     * 流程大角色和子角色树图
     *
     * @template tpl-role-xtree,tpl-subrole-xtree
     */
    public ActionForward flowRoleSubrole(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {

        long node = StaticMethod.null2Long(request.getParameter("node"));
        String nodeType = StaticMethod.null2String(request
                .getParameter("nodeType"));
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-role-xtree");
        List list = new ArrayList();
        ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");

        try {
            if ("workflow".equals(nodeType)) { // 如果父节点是流程，取此流程下的大角色
                ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
                list = (ArrayList) roleMgr
                        .getFlwRolesByWorkflowFlag((int) node); // 取流程大角色，将node视为workFlowFlag

                // 修正leaf 属性 有子角色则leaf=0
                for (int i = 0; i < list.size(); i++) {
                    TawSystemRole role = (TawSystemRole) list.get(i);
                    List subRoleList = subRoleMgr.getTawSystemSubRoles(role
                            .getRoleId());
                    if (null == subRoleList || 0 >= subRoleList.size()) {
                        role.setLeaf(new Integer(StaticVariable.LEAF));
                    } else {
                        role.setLeaf(new Integer(StaticVariable.NOTLEAF));
                    }
                    list.set(i, role);
                }
                template = "tpl-role-xtree";
            } else if (UIConstants.NODETYPE_ROLE.equals(nodeType)) { // 如果父节点是大角色，取此大角色下的子角色
                list = (ArrayList) subRoleMgr.getTawSystemSubRoles(node);

                // 修正leaf 属性 全设为1
                for (int i = 0; i < list.size(); i++) {
                    TawSystemSubRole subrole = (TawSystemSubRole) list.get(i);
                    subrole.setLeaf(new Integer(StaticVariable.LEAF));
                    list.set(i, subrole);
                }
                template = "tpl-subrole-xtree";
            }

        } catch (Exception ex) {
            BocoLog.error(this, "生成流程大角色和子角色树图时报错：" + ex);
        }

        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    /**
     * 联系人树
     *
     * @template tpl-contact-xtree
     */
    public ActionForward getContactTree(ActionMapping mapping, ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String userId = sessionform.getUserid();
        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");

        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-contact-xtree");
        TawWorkbenchContactBO contactbo = TawWorkbenchContactBO.getInstance();
        ArrayList grouplist = new ArrayList();
        ArrayList contactlist = new ArrayList();
        try {
            grouplist = (ArrayList) contactbo.getNextLevecGroups(node,
                    userId, "0");
            contactlist = (ArrayList) contactbo.getNextLevecContact(userId,
                    node, "0");
        } catch (Exception ex) {
            BocoLog.error(this, "生成联系人树图时报错：" + ex);
        }
        request.setAttribute("grouplist", grouplist);
        request.setAttribute("contactlist", contactlist);
        return mapping.findForward(template);
    }

    /**
     * 用户树(某部门下)
     *
     * @template tpl-user-xtree
     */
    public ActionForward user(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-user-xtree");
        ArrayList userlist = new ArrayList();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        try {
            userlist = (ArrayList) userrolebo.getUserBydeptids(node);
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门用户树图时报错：" + ex);
        }
        request.setAttribute("list", userlist);
        return mapping.findForward(template);
    }

    /**
     * 用户树
     */
    public ActionForward userByDept(ActionMapping mapping, ActionForm actionForm,
                                    HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return this.user(mapping, actionForm, request, response);
    }

    /**
     * 用户树
     */
    public ActionForward userByDeptForTaskplan(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        return this.user(mapping, actionForm, request, response);
    }

    /**
     * 部门和用户树
     *
     * @template tpl-user-xtree-fromdept
     */
    public ActionForward userFromDept(ActionMapping mapping, ActionForm actionForm,
                                      HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String selfFlag = StaticMethod.null2String(request.getParameter("noself"), "");
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-user-xtree-fromdept");
        ArrayList userlist = new ArrayList();
        ArrayList deptlist = new ArrayList();
        try {
            deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (selfFlag.equals("true")) {//不包括自己的人员list
                TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                        .getSession().getAttribute("sessionform");
                userlist = (ArrayList) userrolebo.getUserBydeptidsNoSelf(node, sessionform.getUserid());
            } else {
                userlist = (ArrayList) userrolebo.getUserBydeptids(node);
            }

        } catch (Exception ex) {
            BocoLog.error(this, "生成部门用户树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("userlist", userlist);
        return mapping.findForward(template);
    }

    /**
     * 用户树（某子角色下） 目前用于工单选择人员
     *
     * @template tpl-user-xtree
     */
    public ActionForward userFromRole(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {

        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-user-xtree");
        ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserRefRoleManager");

        List list = new ArrayList();
        try {
            list = (ArrayList) mgr.getUserbyroleid(node);
        } catch (Exception ex) {
            BocoLog.error(this, "生成子角色用户树图时报错：" + ex);
        }
        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    public ActionForward getSubRoleUserTree(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {

        return this.userFromRole(mapping, actionForm, request, response);
    }

    /**
     * 字典树
     *
     * @template tpl-dict-xtree
     */
    public ActionForward dict(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String node = StaticMethod.null2String(request.getParameter("node"));
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-dict-xtree");
        List list = new ArrayList();

        try {
            ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
            list = mgr.getDictSonsByDictid(node);

        } catch (Exception ex) {
            BocoLog.error(this, "生成字典树图时报错：" + ex);
        }

        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    /**
     * 地域树
     *
     * @template tpl-area-xtree
     */
    public ActionForward area(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-area-xtree");
        ArrayList list = new ArrayList();

        try {
            ITawSystemAreaManager mgr = (ITawSystemAreaManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemAreaManager");
            list = (ArrayList) mgr.getSonAreaByAreaId(node);

        } catch (Exception ex) {
            BocoLog.error(this, "生成地域树图时报错：" + ex);
        }

        request.setAttribute("list", list);
        return mapping.findForward(template);
    }

    public ActionForward areaTree(ActionMapping mapping, ActionForm actionForm,
                                  HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return this.area(mapping, actionForm, request, response);
    }

    /**
     * 部门岗位树
     *
     * @template tpl-post-xtree-fromdept
     */
    public ActionForward postFromDept(ActionMapping mapping, ActionForm actionForm,
                                      HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-post-xtree-fromdept");
        ArrayList deptlist = new ArrayList();
        ArrayList postlist = new ArrayList();
        try {
            TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
            deptlist = (ArrayList) deptbo.getNextLevecDepts(id, "0");
            ITawSystemPostManager mgr = (ITawSystemPostManager) ApplicationContextHolder.getInstance().getBean("ItawSystemPostManager");
            postlist = (ArrayList) mgr.getPostsByDeptId(id);
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门岗位树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("postlist", postlist);
        return mapping.findForward(template);
    }

    /**
     * 部门子角色树，根据部门ID、大角色ID和流程ID获取子角色
     *
     * @paramter systemId 流程ID
     * @paramter roleId 大角色ID
     * @template tpl-subrole-xtree-fromdept
     */
    public ActionForward subroleFromDept(ActionMapping mapping, ActionForm actionForm,
                                         HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String deptid = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String systemId = StaticMethod.null2String(request
                .getParameter("systemId"));
        String roleId = StaticMethod
                .null2String(request.getParameter("roleId"));
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-subrole-xtree-fromdept");
        ArrayList deptlist = new ArrayList();
        ArrayList rolelist = new ArrayList();
        try {
            TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
            deptlist = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
            ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemSubRoleManager");
            rolelist = (ArrayList) mgr.getSubRolesByDeptId(deptid,
                    systemId, roleId);
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门子角色树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("rolelist", rolelist);
        return mapping.findForward(template);
    }

    /**
     * 部门、子角色、用户树
     *
     * @template tpl-dept-xtree-subroleuser
     */
    public ActionForward getDeptSubRoleUserTree(ActionMapping mapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {

        String deptid = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-dept-xtree-subroleuser");
        ArrayList userlist = new ArrayList();
        ArrayList subrolelist = new ArrayList();
        ArrayList deptlist = new ArrayList();
        try {
            TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
            deptlist = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
            ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemSubRoleManager");
            subrolelist = (ArrayList) mgr.getSubRolesByDeptId(deptid);
            ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
                    .getInstance().getBean("iTawSystemUserBo");
            userlist = (ArrayList) sysuserbo.getUserBydeptids(deptid);
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门子角色用户树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("subrolelist", subrolelist);
        request.setAttribute("userlist", userlist);
        return mapping.findForward(template);
    }


    /**
     * 机房组织树
     *
     * @template tpl-cptroom-xtree
     */
    public ActionForward cptroom(ActionMapping mapping, ActionForm actionForm,
                                 HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");
        String template = StaticMethod.null2String(request.getParameter("tpl"),
                "tpl-cptroom-xtree");
        ArrayList list = new ArrayList();
        TawSystemCptroomBo cptroombo = TawSystemCptroomBo.getInstance();

        try {
            list = (ArrayList) cptroombo.getNextLevelCptrooms(id, "0");
        } catch (Exception ex) {
            BocoLog.error(this, "生成机房组织树图时报错：" + ex);
        }
        request.setAttribute("list", list);
        return mapping.findForward(template);

    }

    public ActionForward getCptroomTree(ActionMapping mapping, ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return this.cptroom(mapping, actionForm, request, response);

    }


    /**
     * 创建atom源
     */
    public ActionForward getAtomTreeLists(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String node = StaticMethod.null2String(request.getParameter("node"),
                StaticVariable.ProvinceID + "");

        List list = new ArrayList();
        // 获取当前用户
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        boolean _bIsAdmin = "admin".equals(sessionform.getUserid());


        try {
            TawSystemPrivOperation tawSystemPrivOperation = null;
            if (_bIsAdmin) {
                ITawSystemPrivOperationManager operationMgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
                list = (ArrayList) operationMgr.getAllEnableSubObjects(node);
            } else {
                list = PrivMgrLocator.getPrivMgr().listOperationAll(
                        sessionform.getUserid(), sessionform.getDeptid(),
                        sessionform.getRolelist(),
                        PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, node);
            }


            String path = request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getServerPort() + request.getContextPath() + "/";

            // 创建ATOM源
            Factory factory = Abdera.getNewFactory();
            Feed feed = factory.newFeed();
            // 分页
            for (int i = 0; i < list.size(); i++) {
                tawSystemPrivOperation = (TawSystemPrivOperation) list.get(i);

                Entry entry = feed.insertEntry();
                entry.setId(tawSystemPrivOperation.getCode());
                entry.setTitle(tawSystemPrivOperation.getName());
                entry.setContent(path + tawSystemPrivOperation.getUrl());
                entry.setSummary(tawSystemPrivOperation.getParentcode());
                entry.setLanguage(tawSystemPrivOperation.getOrderby());
                entry.setDraft(tawSystemPrivOperation.getIsApp() == "1" ? true : false);


            }
            // 显示的总条数
            feed.setText(String.valueOf(list.size()));

            OutputStream os = response.getOutputStream();
            PrintStream ps = new PrintStream(os);
            feed.getDocument().writeTo(ps);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信息推送---1103---
     */
    public ActionForward modifyShowornot(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().setAttribute("showornot", "no");
        return null;
    }

    public ActionForward auditUserList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String priv = "/WORKBENCH/INFOPUB/PERMISSION/THREADAUDIT";
        String node = StaticMethod.null2String(request.getParameter("node"), "1");
        String selfFlag = StaticMethod.null2String(request.getParameter("noself"), "");
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-user-xtree-fromdept");
        ArrayList userlist = new ArrayList();
        ArrayList deptlist = new ArrayList();
        ArrayList backList = new ArrayList();
        try {
            deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (selfFlag.equals("true")) {
                TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                userlist = (ArrayList) userrolebo.getUserBydeptidsNoSelf(node, sessionform.getUserid());
                int len = userlist.size();
                for (int i = 0; i < len; i++) {
                    TawSystemUser tawSystemUser = (TawSystemUser) userlist.get(i);
                    boolean b = PrivMgrLocator.getPrivMgr().hasPriv(tawSystemUser.getId(), priv);
                    if (b)
                        backList.add(tawSystemUser);
                }

            } else {
                userlist = (ArrayList) userrolebo.getUserBydeptids(node);
                int len = userlist.size();
                for (int i = 0; i < len; i++) {
                    TawSystemUser tawSystemUser = (TawSystemUser) userlist.get(i);
                    boolean b = PrivMgrLocator.getPrivMgr().hasPriv(tawSystemUser.getUserid(), priv);
                    if (b)
                        backList.add(tawSystemUser);
                }

            }
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门用户树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("userlist", backList);
        return mapping.findForward(template);
    }

    public void getAllWorkflow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONArray jsonRoot = new JSONArray();
        ITawSystemRoleRefWorkflowManager workflow = (ITawSystemRoleRefWorkflowManager) ApplicationContextHolder.getInstance().getBean("ItawSystemRoleRefWorkflowManager");
        List workflows = workflow.getTawSystemWorkflows();
        JSONObject j;
        for (Iterator it = workflows.iterator(); it.hasNext(); jsonRoot.put(j)) {
            TawSystemWorkflow systemWorkflow = (TawSystemWorkflow) it.next();
            String workflowId = systemWorkflow.getFlowId();
            String workflowName = systemWorkflow.getRemark();
            j = new JSONObject();
            j.put("id", workflowId);
            j.put("text", workflowName);
            j.put("nodeType", "workflow");
        }

        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward teamUserList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String flag = StaticMethod.null2String(request.getParameter("flag"));
        String priv = "";
        if (flag.equals("1"))
            priv = "/workplan/tawwpexecute/dutyexecutelist.do";
        else
            priv = "/workplan/tawwpmonth/checklist.do";
        String node = StaticMethod.null2String(request.getParameter("node"), "1");
        String selfFlag = StaticMethod.null2String(request.getParameter("noself"), "");
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
        String template = StaticMethod.null2String(request.getParameter("tpl"), "tpl-user-xtree-fromdept");
        ArrayList userlist = new ArrayList();
        ArrayList deptlist = new ArrayList();
        ArrayList backList = new ArrayList();
        try {
            deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
            if (selfFlag.equals("true")) {
                TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
                userlist = (ArrayList) userrolebo.getUserBydeptidsNoSelf(node, sessionform.getUserid());
                int len = userlist.size();
                for (int i = 0; i < len; i++) {
                    TawSystemUser tawSystemUser = (TawSystemUser) userlist.get(i);
                    boolean b = PrivMgrLocator.getPrivMgr().hasPriv(tawSystemUser.getId(), priv);
                    if (b)
                        backList.add(tawSystemUser);
                }

            } else {
                userlist = (ArrayList) userrolebo.getUserBydeptids(node);
                int len = userlist.size();
                for (int i = 0; i < len; i++) {
                    TawSystemUser tawSystemUser = (TawSystemUser) userlist.get(i);
                    boolean b = PrivMgrLocator.getPrivMgr().hasPriv(tawSystemUser.getUserid(), priv);
                    if (b)
                        backList.add(tawSystemUser);
                }

            }
        } catch (Exception ex) {
            BocoLog.error(this, "生成部门用户树图时报错：" + ex);
        }
        request.setAttribute("deptlist", deptlist);
        request.setAttribute("userlist", backList);
        return mapping.findForward(template);
    }
}

