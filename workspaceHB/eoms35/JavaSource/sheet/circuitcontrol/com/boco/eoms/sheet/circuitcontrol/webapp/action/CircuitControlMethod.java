package com.boco.eoms.sheet.circuitcontrol.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.circuitcontrol.service.ICircuitControlLinkManager;

/**
 * <p>
 * Title:电路调度申请工单
 * </p>
 * <p>
 * Description:电路调度申请工单
 * </p>
 * <p>
 * Sun Sep 29 16:51:15 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CircuitControlMethod extends BaseSheet {

    public String getPageColumnName() {

        return super.getPageColumnName() + "gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
                + "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";

    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap hashMap = new HashMap();

        HashMap sheetMap = new HashMap();
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
        BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
        if (!sheetKey.equals("")) {
            sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

        }
        if (!sheetKey.equals("")) {
            main = this.getMainService().getSingleMainPO(sheetKey);
        }
        sheetMap.put("main", main);
        sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
        sheetMap.put("operate", getPageColumnName());
        hashMap.put("selfSheet", sheetMap);

        return hashMap;
    }

    /**
     * 提交流程引擎前作最后一次参数处理
     */
    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);

        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        //String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        //String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        HashMap sheetMap = this.getFlowEngineMap();

        //Map main = (HashMap) sheetMap.get("main");
        Map operate = (HashMap) sheetMap.get("operate");
        Map link = (HashMap) sheetMap.get("link");

        String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

        if (taskName.equals("reply") || taskName.equals("advice")) {
            link.put("id", "");
        }

        if (dealperformers.length >= 1) {

            String corrKey = "";
            String tmp = "";
            for (int i = 0; i < dealperformers.length; i++) {
                tmp = UUIDHexGenerator.getInstance().getID();
                if (dealperformers.length == 1) {
                    corrKey = tmp;
                } else {
                    if (corrKey.equals("")) {
                        corrKey = tmp;
                    } else {
                        corrKey = corrKey + "," + tmp;
                    }

                }
            }
            System.out.println("corrKey" + corrKey);
            operate.put("extendKey1", corrKey);

        }

        sheetMap.put("link", link);
        sheetMap.put("operate", operate);

        this.setFlowEngineMap(sheetMap);
    }


    /**
     * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
     */
    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getParameterMap() {
        Map attributeMap = new HashMap();
        return attributeMap;
    }

    /**
     * 设置main和link保存附件字段属性
     */
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

    /**
     * 进入处理环节
     */
    public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        //驳回上一级（不是移交的任务），需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
            BaseLink prelink = this.getLinkService().getSingleLinkPO(preLinkId);
            if (prelink != null) {
                request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
                request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
            }
        }
        //如果是移交后的任务被驳回
        if (operateType.equals("4")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            ICircuitControlLinkManager service = (ICircuitControlLinkManager) ApplicationContextHolder.getInstance().getBean("iCircuitControlLinkManager");
            String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));
            String condition = " mainId='" + sheetKey + "' and operateType=8 and aiid='" + taskId + "' order by operateTime desc";
            List linkList = service.getLinksBycondition(condition);
            if (linkList != null && linkList.size() > 0) {
                BaseLink prelink = (BaseLink) linkList.get(0);
                request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
                request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
            }
        }

    }

}