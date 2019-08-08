
package com.boco.eoms.sheet.businessbackout.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.businessbackout.model.BusinessBackoutLink;
import com.boco.eoms.sheet.businessbackout.service.IBusinessBackoutFlowManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public class BusinessBackoutMethod extends BaseSheet {
    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operateName = StaticMethod.nullObject2String(request
                .getParameter("operateName"));

        try {
            if (operateName.equals("forceHold")) {
                //非流程动作Event
                HashMap map = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
                if (sheetKey == null || sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                } else {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                }
                System.out.println("sheetKey is ===========" + sheetKey);
                //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                map.put("main", main);
                System.out.println("main is ===========" + main.getId());
                map.put("link", getLinkService().getLinkObject().getClass().newInstance());
                map.put("operate", getPageColumnName());
                hashMap.put("selfSheet", map);
            } else if (taskName.equals("")) {
                //新增工单
                HashMap sheetMap = new HashMap();
                try {
                    sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                    sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                } catch (IllegalAccessException e1) {
                } catch (InstantiationException e1) {
                }
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("DraftHumTask") || taskName.equals("HoldHumTask") || taskName.equals("ByRejectHumTask") || taskName.equals("ExcuteHumTask")) {
                HashMap sheetMap = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }
                //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("cc") || taskName.equals("reply") || taskName.equals("advice")) {
                HashMap sheetMap = new HashMap();

                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey == null || sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else {
                HashMap sheetMap = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("mainId"));
                //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);

            }
        } catch (Exception e) {
            e.printStackTrace();
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
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        HashMap sheetMap = this.getFlowEngineMap();
        Map main = (HashMap) sheetMap.get("main");
        Map operate = (HashMap) sheetMap.get("operate");
        Map link = (HashMap) sheetMap.get("link");
        String auditperformer = StaticMethod.nullObject2String(operate.get("auditPerformer"));
        System.out.println("=======taskName========" + taskName);
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        if (!operatName.equals("forceHold")) {
            if ((taskName.equals("") && auditperformer.equals("")) || taskName.equals("ExcuteHumTask") || taskName.equals("DraftHumTask") || taskName.equals("ByRejectHumTask") || taskName.equals("HoldHumTask") || taskName.equals("TaskCreateAuditHumTask")) {

                String[] dealperformers = ((String) operate.get("dealPerformer")).split(",");
                String key = "";
                for (int i = 0; i < dealperformers.length; i++) {
                    String tempKey = "";
                    try {
                        tempKey = UUIDHexGenerator.getInstance().getID();
                    } catch (Exception e) {
                    }

                    if (dealperformers.length == 1) {
                        key = tempKey;
                    } else {
                        if (key.equals("")) {
                            key = tempKey;
                        } else {
                            key = key + "," + tempKey;
                        }

                    }
                }
                System.out.println("=======corrKey========" + key);
                sheetMap.put("corrKey", key);
                //需要添加operatetype判断
//			if (operateType.equals("0")||operateType.equals("3")||operateType.equals("201")||operateType.equals("54")||operateType.equals("10")) {
//  			   String linkId = "";
//			   String tmpLink = "";
//			   for(int i=0;i<dealperformers.length;i++){
//				 tmpLink = UUIDHexGenerator.getInstance().getID();
//				 if(dealperformers.length == 1){
//					linkId = tmpLink;
//				 }else{
//					if(linkId.equals("")){
//						linkId = tmpLink;
//					}else{
//						linkId = linkId + "," + tmpLink;	
//					}
//				}
//			}
//			 System.out.println("linkId is======================="+linkId);
//			 link.put("id", linkId);
//		 }
            }
        }
        System.out.println("=======operateType========" + operateType);
        if (taskName.equals("ExcuteHumTask") && operateType.equals("111")) {
            System.out.println("互调-------taskname is ==========" + taskName);
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"));
            System.out.println("互调-------sheetKey is ==========" + sheetKey);
            if (sheetKey != null || !sheetKey.equals("")) {
                ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                        .getInstance().getBean("ITawSheetRelationManager");
                List relationAllList = rmgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
                System.out.println("互调-------relationAllList is ==========" + relationAllList.size());
                if (relationAllList != null && relationAllList.size() > 0) {
                    link.put("operateType", new Integer(111));
                    operate.put("phaseId", "receiveNet");
                    System.out.println("互调-------被执行了！！！！！");

                }
            }
        }
        System.out.println("main=" + main);
        if (StaticMethod.nullObject2String(main.get("isCustomerFlag")).equalsIgnoreCase("1")) {
            String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessBackout-crm.xml").getProperty("base.SendImmediately"));
            if (!sendImmediately.equalsIgnoreCase("true")) {
                String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
                ITaskService iTaskService = this.getTaskService();
                ITask task = iTaskService.getSinglePO(taskId);

                TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                        .getSession().getAttribute("sessionform");
                WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
                Subject subject = safeService.logIn(sessionform.getUserid(), "111");

                HashMap sessionMap = new HashMap();
                sessionMap.put("wpsSubject", subject);
                sessionMap.put("userId", sessionform.getUserid());
                sessionMap.put("password", "111");

                Map variableMap = getBusinessFlowService().getVariable(task.getProcessId(), "parentCorrKey", sessionMap);
                String parentCorrKey = StaticMethod.nullObject2String(variableMap.get("parentCorrKey"));

                String invokeReplyAll = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessBackout-crm.xml").getProperty("base.InvokeReplyAll"));

                if (taskName.equals("ExcuteHumTask") && operateType.equals("4")) {// 驳回
                    if (!parentCorrKey.equalsIgnoreCase(StaticMethod.nullObject2String(main.get("correlationKey")))) {//分派
                        if (invokeReplyAll.equalsIgnoreCase("true")) {
                            operate.put("interfaceType", "withdrawWorkSheet");
                            operate.put("methodType", "withdrawWorkSheet");
                        }
                    } else {
                        operate.put("interfaceType", "withdrawWorkSheet");
                        operate.put("methodType", "withdrawWorkSheet");
                    }
                } else if (operateType.equals("9")) {// 阶段回复
                    operate.put("interfaceType", "notifyWorkSheet");
                    operate.put("methodType", "notifyWorkSheet");
                } else if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
                    if (!parentCorrKey.equalsIgnoreCase(StaticMethod.nullObject2String(main.get("correlationKey")))) {//分派
                        if (invokeReplyAll.equalsIgnoreCase("true")) {
                            operate.put("interfaceType", "confirmWorkSheet");
                            operate.put("methodType", "confirmWorkSheet");
                        }
                    } else {
                        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
                        BaseLink baseLink = getLinkService().getSingleLinkPO(preLinkId);
                        if (baseLink.getOperateType() == null || baseLink.getOperateType().intValue() == 8) {
                            operate.put("interfaceType", "confirmWorkSheet");
                            operate.put("methodType", "confirmWorkSheet");
                        }
                    }
                } else if (operateType.equals("205")) {// 回复
                    if (!parentCorrKey.equalsIgnoreCase(StaticMethod.nullObject2String(main.get("correlationKey")))) {
                        if (invokeReplyAll.equalsIgnoreCase("true")) {
                            operate.put("interfaceType", "replyWorkSheet");
                            operate.put("methodType", "replyWorkSheet");
                        }
                    } else {
                        operate.put("interfaceType", "replyWorkSheet");
                        operate.put("methodType", "replyWorkSheet");
                    }

                }
            }


        }
        sheetMap.put("operate", operate);
        sheetMap.put("link", link);
        sheetMap.put("main", main);
        this.setFlowEngineMap(sheetMap);

    }

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    /**
     * 工单提交预处理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public void performPreCommit(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));

        if (taskName.equals("ExcuteHumTask") && operateType.equals("111")) {
            //查询工单互调表
            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
            System.out.println("relationAllList.size() is =========" + relationAllList.size());
            //如果已经调用了其他工单则继续执行父类的performPreCommit方法,否则返回status为-1
            if (relationAllList != null && relationAllList.size() > 0) {
                super.performPreCommit(mapping, form, request, response);
            } else {

                JSONArray data = new JSONArray();
                JSONObject o = new JSONObject();
                o.put("text", "您还没有调用其他流程，不能提交工单！");
                data.put(o);
                JSONObject jsonRoot = new JSONObject();
                jsonRoot.put("data", data);
                jsonRoot.put("status", "2");
                JSONUtil.print(response, jsonRoot.toString());
            }
        } else {
            super.performPreCommit(mapping, form, request, response);
        }
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

        String taskName = (String) request.getAttribute("taskName");
        System.out.println("taskName is =============" + taskName);
        if (taskName != null && taskName.equals("ExcuteHumTask")) {
            String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
            ITaskService iTaskService = getTaskService();
            ITask task = iTaskService.getSinglePO(taskId);
            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
            javax.security.auth.Subject subject = safeService.logIn(sessionform.getUserid(), "111");
            HashMap sessionMap = new HashMap();
            sessionMap.put("wpsSubject", subject);
            sessionMap.put("userId", sessionform.getUserid());
            sessionMap.put("password", "111");
            Map variableMap = getBusinessFlowService().getVariable(task.getProcessId(), "parentCorrKey", sessionMap);
            String parentCorrKey = StaticMethod.nullObject2String(variableMap.get("parentCorrKey"));
            request.setAttribute("mainparentCorrKey", parentCorrKey);
        }
        if (taskName != null && taskName.equals("TaskCreateAuditHumTask")) {
            BusinessBackoutLink link = (BusinessBackoutLink) request.getAttribute("sheetLink");
            // 取得流程听Operate里的角色
            HashMap sessionMap = new HashMap();
            TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            sessionMap.put("userId", sessionform.getUserid());
            sessionMap.put("password", sessionform.getPassword());
            Map processOjbectAttribute = this.getProcessOjbectAttribute();
            JSONArray sendUserAndRoles = new JSONArray();

            String objectName = (processOjbectAttribute.get("objectName") == null ? ""
                    : (String) processOjbectAttribute.get("objectName"));
            System.out.println("objectName is =============" + objectName);
            Map parameterValuemap = businessFlowService.getVariable(link.getPiid(),
                    objectName, sessionMap);

            // 从Map里移出流程对象
            processOjbectAttribute.remove("objectName");


            // 依次循环取出Map里对象中的属性
            for (Iterator it = processOjbectAttribute.keySet().iterator(); it
                    .hasNext(); ) {
                String dealPerformer = (String) it.next();
                String dealPerformerLeader = dealPerformer + "Leader";
                String dealPerformerType = dealPerformer + "Type";

                String dealPerformerValue = (String) parameterValuemap
                        .get(dealPerformer);
                String dealPerformerLeaderValue = (String) parameterValuemap
                        .get(dealPerformerLeader);
                String dealPerformerTypeValue = (String) parameterValuemap
                        .get(dealPerformerType);

                ID2NameService service = (ID2NameService) ApplicationContextHolder
                        .getInstance().getBean("ID2NameGetServiceCatch");

                if (dealPerformerValue != null
                        && !dealPerformerValue.equals("")) {
                    String[] dealPerformerValues = dealPerformerValue
                            .split(",");
                    for (int i = 0; i < dealPerformerValues.length; i++) {
                        JSONObject data = new JSONObject();
                        String finalDealPerformerValue = dealPerformerValues[i];
                        System.out.println("finalDealPerformerValue is =============" + finalDealPerformerValue);
                        data.put("id", finalDealPerformerValue);
                        String finalDealPerformerTypeValue = dealPerformerTypeValue
                                .split(",")[i];
                        data.put("nodeType", finalDealPerformerTypeValue);
                        data.put("categoryId", dealPerformer);
                        String finalDealPerformerLeaderValue = dealPerformerLeaderValue
                                .split(",")[i];
                        data.put("leaderId", finalDealPerformerLeaderValue);
                        String name = service.id2Name(
                                finalDealPerformerLeaderValue,
                                "tawSystemUserDao");
                        data.put("leaderName", name);
                        sendUserAndRoles.put(data.toString());

                    }

                }

            }

            request.setAttribute("sendUserAndRoles", sendUserAndRoles);
        }
        //需要调用外部流程

        if (taskName.equals("pilot")) {
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"));

            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
            if (relationAllList != null) {
                for (int i = 0; i < relationAllList.size(); i++) {
                    TawSheetRelation relation = (TawSheetRelation) relationAllList.get(i);
                    int state = relation.getInvokeState();
                    if (state == Constants.INVOKE_STATE_RUNNING) {
                        request.setAttribute("ifInvoke", "no");
                        break;
                    }
                    request.setAttribute("ifInvoke", "yes");
                }
            } else {
                request.setAttribute("ifInvoke", "no");
            }
        }
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
