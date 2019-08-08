
package com.boco.eoms.sheet.netFault.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.util.InterfaceUtil;

public class NetFaultMethod extends BaseSheet {
    public String getPageColumnName() {

        return super.getPageColumnName() + ",toMorePhaseId@java.lang.String,supplier1Performer@java.lang.String,supplier1PerformerLeader@java.lang.String,"
                + "supplier1PerformerType@java.lang.String,supplier2Performer@java.lang.String,supplier2PerformerLeader@java.lang.String,supplier2PerformerType@java.lang.String,"
                + "supplier1CorrKey@java.lang.String,supplier2CorrKey@java.lang.String";

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));


        HashMap sheetMap = new HashMap();
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("mainId"));
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

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#dealFlowEngineMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getParameterMap() {
        // TODO Auto-generated method stub
        return this.getParameterMap();
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

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"), "");
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"), "");
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        if (taskName.equals("RejectTask") || taskName.equals("AcceptTask") || taskName.equals("ExplorateTask") || taskName.equals("MakeTask") || taskName.equals("HandleTask") || taskName.equals("HoldTask")) {
            super.setParentTaskOperateWhenRejct(request);
        }
//		 if(operateType.equals("18")){
//				//需要回调外部流程
//				BaseMain main=this.getMainService().getSingleMainPO(sheetKey);
//				String parentSheetName=main.getParentSheetName();
//				String parentSheetKey=main.getParentSheetId();											
//				IMainService parentMainService = (IMainService) ApplicationContextHolder
//					.getInstance().getBean(parentSheetName);													
//				BaseMain parentMain=parentMainService.getSingleMainPO(parentSheetKey);
//				request.setAttribute("parentMain", parentMain);
//				request.setAttribute("parentProcessName", parentSheetName);
//				}

    }


    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "NetFaultProces";
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return "netFault";
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        return null;
    }

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String phaseId = StaticMethod.nullObject2String(request
                .getParameter("phaseId"));
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        HashMap sheetMap = this.getFlowEngineMap();
        Map operate = (HashMap) sheetMap.get("operate");
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());

        Map mainMap = (HashMap) sheetMap.get("main");
        Map linkMap = (HashMap) sheetMap.get("link");
        String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");
        String[] toMorePhaseIds = (StaticMethod.nullObject2String(operate.get("toMorePhaseId"))).split(",");

        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }

        if (dealperformers.length > 1) {

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
            sheetMap.put("operate", operate);

        }

        System.out.println("main=" + mainMap);

        this.setFlowEngineMap(sheetMap);
    }
}
