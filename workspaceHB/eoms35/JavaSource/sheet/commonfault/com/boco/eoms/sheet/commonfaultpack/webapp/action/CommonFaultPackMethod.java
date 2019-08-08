package com.boco.eoms.sheet.commonfaultpack.webapp.action;

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
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfaultpack.dao.ICommonFaultPackMainDAO;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;

public class CommonFaultPackMethod extends BaseSheet {
    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {

            HashMap sheetMap = new HashMap();
            sheetMap.put("main", getMainService().getMainObject());
            sheetMap.put("link", getLinkService().getLinkObject());
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("DraftTask") || taskName.equals("HoldTask")) {

            HashMap sheetMap = new HashMap();
            sheetMap.put("main", getMainService().getMainObject());
            sheetMap.put("link", getLinkService().getLinkObject());
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else {
            HashMap sheetMap = new HashMap();
            sheetMap.put("link", getLinkService().getLinkObject());
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
        // TODO Auto-generated method stub
        String roleId = StaticMethod.nullObject2String(request
                .getParameter("roleId"));
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        System.out.println("activeTemplateId=" + taskName);
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

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("audit", "audit");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    /**
     * 工单的初始化页面 本流程新增调用
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String showNewSheetPage(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String mainId = StaticMethod.nullObject2String(request
                .getParameter("mainId"), "");
        request.setAttribute("mainId", mainId);
        return "new";
    }

    public void showDetailPage(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("alarmId"));
        System.out.println("faultpackId:" + sheetKey);
        String isSender = "false";
        String alarmMethod = StaticMethod.nullObject2String(request
                .getParameter("alarmMethod"));
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder
                .getInstance().getBean(beanName);
        CommonFaultPackMain mainObject = (CommonFaultPackMain) baseSheet
                .getMainService().getSingleMainPO(sheetKey);
        String mainId = mainObject.getMainId();
        IBaseSheet baseSheet1 = (IBaseSheet) ApplicationContextHolder
                .getInstance().getBean("CommonFault");
        CommonFaultMain faultMain = (CommonFaultMain) baseSheet1
                .getMainService().getSingleMainPO(mainId);
        String sendUserId = "";
        if (faultMain != null)
            sendUserId = StaticMethod.nullObject2String(faultMain.getSendUserId());
        else
            sendUserId = userId;
        if (sendUserId.equals(userId))
            isSender = "true";
        request.setAttribute("isSender", isSender);
        request.setAttribute("alarmMethod", alarmMethod);
        request.setAttribute("sheetMain", mainObject);

    }

}
