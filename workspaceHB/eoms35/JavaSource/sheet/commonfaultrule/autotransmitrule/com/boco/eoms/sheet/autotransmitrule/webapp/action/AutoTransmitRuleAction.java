package com.boco.eoms.sheet.autotransmitrule.webapp.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.sheet.autotransmitrule.util.AutoTransmitConstants;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.autotransmitrule.model.AutoTransmitRule;
import com.boco.eoms.sheet.autotransmitrule.service.IAutoTransmitRuleManager;
import com.boco.eoms.sheet.autotransmitrule.webapp.form.AutoTransmitRuleForm;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.commonfault.webapp.action.CommonFaultSheetMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AutoTransmitRuleAction extends BaseAction {

    /**
     * 撤销
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author zhangying
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws
            Exception {
        return search(mapping, form, request, response);
    }

    /**
     * 删除
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author zhangying
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        AutoTransmitRuleForm autoDistributeForm = (AutoTransmitRuleForm) form;

        IAutoTransmitRuleManager mgr = (IAutoTransmitRuleManager) getBean("iAutoTransmitRuleManager");

        mgr.removeAutoTransmitRule(autoDistributeForm.getId());

        return search(mapping, form, request, response);
    }

    /**
     * 保存
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author zhangying
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        //保存动态分配任务配置
        String id = StaticMethod.null2String(request.getParameter("id"));
        System.out.println("id===" + id);
        String netTypeOne = StaticMethod.null2String(request.getParameter("netTypeOne"));
        String netTypeTwo = StaticMethod.null2String(request.getParameter("netTypeTwo"));
        String netTypeThree = StaticMethod.null2String(request.getParameter("netTypeThree"));
        String roleId = StaticMethod.null2String(request.getParameter("roleId"));
        String stopTime = StaticMethod.null2String(request.getParameter("stopTime"));
        String domainId = StaticMethod.null2String(request.getParameter("domainId"));
        String equipmentFactory = StaticMethod.null2String(request.getParameter("equipmentFactory"));
        String faultResponseLevel = StaticMethod.null2String(request.getParameter("faultResponseLevel"));

        AutoTransmitRule autoTransmitRule = new AutoTransmitRule();
        if (id == null || id.equals("")) {
            System.out.println("=========================");
            autoTransmitRule.setId(UUIDHexGenerator.getInstance().getID());
        } else {
            autoTransmitRule.setId(id);
        }

        autoTransmitRule.setNetTypeOne(netTypeOne);
        autoTransmitRule.setNetTypeTwo(netTypeTwo);
        autoTransmitRule.setNetTypeThree(netTypeThree);
        autoTransmitRule.setDomainId(domainId);
        autoTransmitRule.setEquipmentFactory(equipmentFactory);
        autoTransmitRule.setFaultResponseLevel(faultResponseLevel);
        autoTransmitRule.setRoleId(roleId);
        autoTransmitRule.setStopTime(stopTime);
        IAutoTransmitRuleManager mgr = (IAutoTransmitRuleManager) getBean("iAutoTransmitRuleManager");
        mgr.saveAutoTransmitRule(autoTransmitRule);

        return search(mapping, form, request, response);
    }

    /**
     * 查询列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author zhangying
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        //获取所有记录
        IAutoTransmitRuleManager mgr = (IAutoTransmitRuleManager) getBean("iAutoTransmitRuleManager");
        HashMap autoMap = mgr.getAllAutoTransmitRule(pageIndex, pageSize);

        request.setAttribute("autoList", autoMap.get("autoList"));
        request.setAttribute("autoTotal", autoMap.get("autoTotal"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 默认执行方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author zhangying
     */

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("unspecified");
        return search(mapping, form, request, response);
    }

    /**
     * 显示保存和修改页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author zhangying
     */
    public ActionForward showInputPage(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'showInputPage' method");
        }
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        System.out.println("show input page id is=======" + id);
        if (!id.equals("")) {
            IAutoTransmitRuleManager mgr = (IAutoTransmitRuleManager) getBean("iAutoTransmitRuleManager");
            AutoTransmitRule autorule = mgr.getAutoTransmitRule(id);
            request.setAttribute("AutoTransmitRule", autorule);
        }

        return mapping.findForward("input");
    }

    public void getSubRoleByData(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        //CommonFaultSheetMethod commonfault = new CommonFaultSheetMethod();
        //List list = explain.explain(commonfault.getRoleConfigPath());
        //System.out.println("role path is ==========="+commonfault.getRoleConfigPath());
        String domainId = StaticMethod.null2String(request.getParameter("domainId"));
        System.out.println("domainId  is ===========" + domainId);
        HashMap columnMap = RoleMapSchema.getInstance().getStyleIDsBySheet("CommonFaultMainFlowProcess");
        System.out.println("domainId  is ===========" + AutoTransmitConstants.sheet_name);
        Hashtable tempTable = new Hashtable();
        tempTable.put("deptId", domainId);
        Set filterSet = columnMap.keySet();
        Iterator it = filterSet.iterator();
        while (it.hasNext()) {
            String key = StaticMethod.nullObject2String(it.next());
            String name = StaticMethod.nullObject2String(columnMap.get(key));
            if (!name.equals("")) {
                String value = StaticMethod.nullObject2String(request.getParameter(name));
                System.out.println("value  is ===========" + value);
                tempTable.put(key, value);
            }

        }
        List perform = RoleManage.getInstance().getSubRoles(String.valueOf(AutoTransmitConstants.big_role), tempTable);
        //TawSystemSubRole subRole = SheetUtils.getMaxFilterSubRole(perform, filterSet.iterator());
        // System.out.println("subRole  is ==========="+subRole.getId());
        JSONArray sendUserAndRoles = new JSONArray();
        for (int i = 0; i < perform.size(); i++) {
            TawSystemSubRole subRole = (TawSystemSubRole) perform.get(i);
            JSONObject jitem = new JSONObject();
            jitem.put(UIConstants.JSON_ID, subRole.getId());
            jitem.put(UIConstants.JSON_TEXT, subRole.getSubRoleName());
            sendUserAndRoles.put(jitem);


        }
        JSONUtil.print(response, sendUserAndRoles.toString());
    }

}
