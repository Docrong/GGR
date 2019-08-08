package com.boco.eoms.workbench.infopub.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm;

/**
 * <p>
 * Title:信息审核
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 2, 2008 9:18:44 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public final class ThreadAuditHistoryAction extends BaseAction {

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        ThreadAuditHistoryForm threadAuditHistoryForm = (ThreadAuditHistoryForm) form;

        // Exceptions are caught by ActionExceptionHandler
        IThreadAuditHistoryManager mgr = (IThreadAuditHistoryManager) getBean("IthreadAuditHistoryManager");
        mgr.removeThreadAuditHistory(threadAuditHistoryForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "threadAuditHistory.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    /**
     * 新建或修改信息审核
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // ThreadAuditHistoryForm threadAuditHistoryForm =
        // (ThreadAuditHistoryForm) form;
        IThreadAuditHistoryManager mgr = (IThreadAuditHistoryManager) getBean("IthreadAuditHistoryManager");
        // 信息id
        String threadId = request.getParameter("threadId");
        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        // if (threadAuditHistoryForm.getId() != null
        // && !"".equals(threadAuditHistoryForm.getId())) {
        // ThreadAuditHistory threadAuditHistory = mgr
        // .getThreadAuditHistory(threadAuditHistoryForm.getId());
        // threadAuditHistoryForm = (ThreadAuditHistoryForm)
        // convert(threadAuditHistory);
        // updateFormBean(mapping, request, threadAuditHistoryForm);
        // }
        IThreadManager threadMgr = (IThreadManager) getBean("IthreadManager");
        Thread thread = threadMgr.getThread(threadId);
        request.setAttribute("thread", thread);

        // 根据url获取可以执行审核的权限的人员列表，并转换成json的字符串
        IThreadAuditHistoryManager auditMgr = (IThreadAuditHistoryManager) getBean("IthreadAuditHistoryManager");
        ThreadAuditHistory Audit = auditMgr
                .getCurrentThreadAuditHistory(threadId);
        String jsonAudit = threadAuditHistoryAuditer2jsonStr(Audit);

        request.setAttribute("jsonOrgs", jsonAudit);

        // 审核历史信息
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                "threadAuditHistoryList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        Map map = (Map) mgr.getThreadAuditHistorys(pageIndex, pageSize,
                " where threadId='" + threadId + "'");

        List list = (List) map.get("result");
        request.setAttribute(InfopubConstants.THREADAUDITHISTORY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("edit");
    }

    /**
     * 查看审核信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward device(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // ThreadAuditHistoryForm threadAuditHistoryForm =
        // (ThreadAuditHistoryForm) form;
        IThreadAuditHistoryManager mgr = (IThreadAuditHistoryManager) getBean("IthreadAuditHistoryManager");
        // 信息id
        String threadId = request.getParameter("threadId");
        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        // if (threadAuditHistoryForm.getId() != null
        // && !"".equals(threadAuditHistoryForm.getId())) {
        // ThreadAuditHistory threadAuditHistory = mgr
        // .getThreadAuditHistory(threadAuditHistoryForm.getId());
        // threadAuditHistoryForm = (ThreadAuditHistoryForm)
        // convert(threadAuditHistory);
        // updateFormBean(mapping, request, threadAuditHistoryForm);
        // }
        IThreadManager threadMgr = (IThreadManager) getBean("IthreadManager");
        Thread thread = threadMgr.getThread(threadId);
        request.setAttribute("thread", thread);

        // 审核历史信息
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                "threadAuditHistoryList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        Map map = (Map) mgr.getThreadAuditHistorys(pageIndex, pageSize,
                " where threadId='" + threadId + "'");

        List list = (List) map.get("result");
        request.setAttribute(InfopubConstants.THREADAUDITHISTORY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("device");
    }

    /**
     * 保存
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ThreadAuditHistoryForm threadAuditHistoryForm = (ThreadAuditHistoryForm) form;

        IThreadManager mgr = (IThreadManager) getBean("IthreadManager");
        Thread thread = mgr.getThread(threadAuditHistoryForm.getThreadId());

        if (thread == null) {
            // 给予提示，信息已删除
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "threadHistoryForm.tips.infoDeleted"));
            saveMessages(request, messages);
            return mapping.findForward("fail");
        }
        // 判断该贴是否提交审核，并等待审核结果
        if (InfopubConstants.ADUITING.equals(thread.getStatus())) {
            // 应给予提示，已提交审核，请等待批示
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "threadHistoryForm.tips.infoSubmitd"));
            saveMessages(request, messages);
            return mapping.findForward("fail");
        }
        // 判断该贴是否审核通过
        if (InfopubConstants.AUDIT_PASS.equals(thread.getStatus())) {
            // 应给予提示，审核通过，不需要再次提交审核
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "threadHistoryForm.tips.submitPassed"));
            saveMessages(request, messages);
            return mapping.findForward("fail");
        }

        ThreadAuditHistory threadAuditHistory = (ThreadAuditHistory) convert(threadAuditHistoryForm);

        // 判断是否选择审核组织
        if (threadAuditHistoryForm.getOrgId() == null
                || "".equals(threadAuditHistoryForm.getOrgId().trim())) {
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "threadAuditHistoryForm.tips.noChooseOrg"));
            saveMessages(request, messages);
            return mapping.findForward("fail");

        }
        List audit = this.jsonOrg2Audit(threadAuditHistoryForm.getOrgId(),
                thread.getId());
        for (int i = 0; i < audit.size(); i++) {
            threadAuditHistory = (ThreadAuditHistory) audit.get(i);
            threadAuditHistory.setNote(threadAuditHistoryForm.getNote());
            mgr.submitAudit4nopass(threadAuditHistory);
        }
        return mapping.findForward("success");
    }

    /**
     * 待审核列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward waitList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                "threadAuditHistoryList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        IThreadAuditHistoryManager mgr = (IThreadAuditHistoryManager) getBean("IthreadAuditHistoryManager");
        TawSystemSessionForm user = this.getUser(request);
        List roles = user.getRolelist();
        // 将subrole list转换为字符串形式
        String roleStr = this.list2roleStr(roles);
        String userStr = this.getUserId(request);
        String hql = " where isCurrent='" + InfopubConstants.TRUE_STR
                + "'and status='" + InfopubConstants.ADUITING
                + "' and ((orgId in " + roleStr + " and orgType='"
                + StaticVariable.PRIV_ASSIGNTYPE_ROLE + "') or (orgId='"
                + userStr + "' and orgType='"
                + StaticVariable.PRIV_ASSIGNTYPE_USER + "')) ";
        Map map = (Map) mgr.getThreadAuditHistorys(pageIndex, pageSize, hql);
        List list = (List) map.get("result");
        request.setAttribute(InfopubConstants.THREADAUDITHISTORY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 将subrole list转换为字符串形式
     *
     * @param subRoles subRole列表
     * @return (' subrole1 ', ' subrole2 ')
     */
    private String list2roleStr(List subRoles) {
        if (subRoles == null || subRoles.isEmpty()) {
            return ("('')");
        }
        String roleStr = "(";
        if (null != subRoles) {
            for (Iterator it = subRoles.iterator(); it.hasNext(); ) {
                TawSystemSubRole role = (TawSystemSubRole) it.next();
                roleStr = roleStr + "'" + role.getId() + "',";
            }
        }
        if (roleStr.indexOf(",") > -1) {
            roleStr = roleStr.substring(0, roleStr.length() - 1);
        }
        return roleStr + ")";
    }

    /**
     * 审核
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward audit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ThreadAuditHistoryForm threadAuditHistoryForm = (ThreadAuditHistoryForm) form;
        IThreadManager mgr = (IThreadManager) getBean("IthreadManager");
        // 审核信息
        try {
            mgr.auditThread(threadAuditHistoryForm);
        } catch (Throwable e) {
            return mapping.findForward("fail");
        }
        return mapping.findForward("success");
    }

    /**
     * 信息权限列表转换成json的字符串
     *
     * @param threadPermimissionOrgs 信息权限列表
     * @return 信息权限转换后的字符串
     */
    private String threadAuditHistoryAuditer2jsonStr(ThreadAuditHistory Audit) {
        String jsonOrgs = "[]";
        // 构造信息发布范围json对象
        if (null != Audit) {
            JSONArray jsonRoot = new JSONArray();
            JSONObject item = new JSONObject();
            // 构造json对象
            item.put(UIConstants.JSON_ID, Audit.getOrgId());
            item.put(UIConstants.JSON_NAME, this.getOrgName(Audit.getOrgId(),
                    Audit.getOrgType()));
            item.put(UIConstants.JSON_NODETYPE, Audit.getOrgType());
            jsonRoot.put(item);
            jsonOrgs = jsonRoot.toString();
        }
        return jsonOrgs;
    }

    /**
     * 组织名称
     *
     * @return 组织名称
     */
    public String getOrgName(String orgId, String orgType) {
        String orgName = "";
        // 若为角色则显示角色名称
        if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(orgType)) {
            ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemSubRoleManager");
            return roleMgr.getTawSystemSubRole(orgId).getSubRoleName();

        }
        // 若为用户则显示用户名称
        else if (StaticVariable.PRIV_ASSIGNTYPE_USER.equals(orgType)) {
            ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
                    .getInstance().getBean("itawSystemUserManager");
            return userMgr.getUserByuserid(orgId).getUsername();
        }
        return orgName;

    }

    /**
     * 从json中取审批组织
     *
     * @param auditers json串
     * @param threadId 信息ID
     * @return 审批组织列表
     */
    private List jsonOrg2Audit(String auditers, String threadId) {
        JSONArray jsonAudit = JSONArray.fromString(auditers);
        List auditList = new ArrayList();
        for (Iterator it = jsonAudit.iterator(); it.hasNext(); ) {
            JSONObject audit = (JSONObject) it.next();
            // 审核组织id
            String orgId = audit.getString(UIConstants.JSON_ID);
            // 节点类型
            String nodeType = audit.getString(UIConstants.JSON_NODETYPE);
            //
            // 写入orgList，供保存
            ThreadAuditHistory threadAuditHistory = new ThreadAuditHistory();
            if (InfopubConstants.ORG_USER.equals(nodeType)
                    || StaticVariable.PRIV_ASSIGNTYPE_USER.equals(nodeType)) {
                auditList.add(threadAuditHistory.JSON2ThreadAuditHistory(orgId,
                        StaticVariable.PRIV_ASSIGNTYPE_USER, threadId,
                        threadAuditHistory));
            } else if (InfopubConstants.ORG_DEPT.equals(nodeType)
                    || StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(nodeType)) {
                auditList.add(threadAuditHistory.JSON2ThreadAuditHistory(orgId,
                        StaticVariable.PRIV_ASSIGNTYPE_DEPT, threadId,
                        threadAuditHistory));
            } else if (InfopubConstants.ORG_ROLE.equals(nodeType)
                    || StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(nodeType)) {
                auditList.add(threadAuditHistory.JSON2ThreadAuditHistory(orgId,
                        StaticVariable.PRIV_ASSIGNTYPE_ROLE, threadId,
                        threadAuditHistory));
            }
        }
        return auditList;
    }
}
