package com.boco.eoms.sheet.offlineData.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;

/**
 * <p>
 * Title:申请工单
 * </p>
 * <p>
 * Description:申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.6
 */

public class OfflineDataMethod extends BaseSheet {

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
            if (main == null) {
                main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
            }
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

        Map main = (HashMap) sheetMap.get("main");
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
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
    }

    /**
     * 工单初始化时即初始化mainid
     */
    public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputNewSheetPage(mapping, form, request, response);
        try {
            // 新增一条记录，该记录中只有id和sendtime字段的数据
            BaseMain main = (BaseMain) request.getAttribute("sheetMain");
            main.setId(UUIDHexGenerator.getInstance().getID());

            request.setAttribute("sheetMain", main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 引用模板时初始化mainid
     */
    public void showInputTemplateSheetPage(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputTemplateSheetPage(mapping, form, request, response);
        try {

            // 新增一条记录，该记录中只有id和sendtime字段的数据
            BaseMain main = (BaseMain) request.getAttribute("sheetMain");
            main.setId(UUIDHexGenerator.getInstance().getID());
            request.setAttribute("sheetMain", main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "OfflineDataProcesses";
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return "offlineData";
    }
}