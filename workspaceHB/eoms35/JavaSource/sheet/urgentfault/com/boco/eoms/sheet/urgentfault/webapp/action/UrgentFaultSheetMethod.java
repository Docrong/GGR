/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.dao.hibernate.TawSystemSubRoleDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.util.SheetUtils;

/**
 * @author yyk
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UrgentFaultSheetMethod extends BaseSheet {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        System.out.println("operateName is -----------------------" + operatName);
        if (operatName.equals("forceHold")) {
            HashMap map = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            map.put("main", main);
            try {
                map.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            map.put("operate", getPageColumnName());
            hashMap.put("selfSheet", map);
        } else if (taskName.equals("")) {
            //新增工单
            HashMap sheetMap = new HashMap();
            try {
                sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("DraftHumTask") || taskName.equals("HoldHumTask") || taskName.equals("BackHumTask")) {
            //草稿、待归档、被驳回（驳回建单人）
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            System.out.println("==$$$$==sheetKey===" + sheetKey);
            if (sheetKey == null && sheetKey.equals("")) {
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            }
            HashMap sheetMap = new HashMap();
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);


        } else {
            //其他人工处理
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null && sheetKey.equals("")) {
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            }
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        }
        return hashMap;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#dealFlowEngineMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        // TODO Auto-generated method stub
        String roleId = StaticMethod.nullObject2String(request.getParameter("roleId"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        System.out.println("activeTemplateId=" + taskName);
        HashMap sheetMap = this.getFlowEngineMap();
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);

        }

        //给统计使用的字段赋值
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        System.out.println("operateType=====" + operateType);
        if (operateType.equals("93")) {

            Map link = (HashMap) sheetMap.get("link");
            Object operate = link.get("linkFaultAvoidTime");
            if (operate != null && operate.getClass().isArray()) {
                operate = ((Object[]) operate)[0];
            }
            Map main = (HashMap) sheetMap.get("main");
            main.put("mainFaultAvoidTime", operate);
            sheetMap.put("main", main);
        }

        this.setFlowEngineMap(sheetMap);


    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);

        //add by yangyankang
        String operateRoleId = StaticMethod.nullObject2String(request
                .getParameter("operateRoleId"));

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
        List subRoleList = new ArrayList();
        int listLength = subRoleList.size();
        long roleId = 0;
        System.out.println("===operateRoleId====" + operateRoleId);

        TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);


        request.setAttribute("operateRoleId", operateRoleId);
        if (subrole != null) {
            request.setAttribute("roleId", subrole.getRoleId() + "");
        }
        request.setAttribute("operateDeptId", sessionform.getDeptid());

        // 取上条TASK
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        if (taskName.equals("MiddleReportAffirmHumTask") || taskName.equals("BackReportAffirmHumTask")
                || taskName.equals("SolveFaultHumTask") || taskName.equals("SolveFaultAffirmHumTask")
                || taskName.equals("ReportHumTask") || taskName.equals("SumUpHumTask")) {
            super.setParentTaskOperateWhenRejct(request);
        }

    }

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public void performSubAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

    }

    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();

        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("sheetAccessories");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");

        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
