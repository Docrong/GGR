
package com.boco.eoms.sheet.businessdredgebroad.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.boco.eoms.sheet.base.util.local.LocalUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.businesschange.service.IBusinessChangeFlowManager;
import com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadLink;
import com.boco.eoms.sheet.businessdredgebroad.service.IBusinessDredgebroadFlowManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public class BusinessDredgebroadMethod extends BaseSheet {
    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub 
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        try {
            String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
            System.out.println("operateName is -----------------------" + operatName);
            if (operatName.equals("forceHold")) {
                try {
                    HashMap map = new HashMap();
                    String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
                    if (sheetKey == null || sheetKey.equals("")) {
                        sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                    }
                    //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                    BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                    map.put("main", main);
                    map.put("link", getLinkService().getLinkObject().getClass().newInstance());
                    map.put("operate", getPageColumnName());
                    hashMap.put("selfSheet", map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (taskName.equals("")) {
                //新增工单
                HashMap sheetMap = new HashMap();
                sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
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

                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);


            } else if (taskName.equals("cc") || taskName.equals("reply") || taskName.equals("advice")) {
                HashMap sheetMap = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
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
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }
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
        Map operate = (HashMap) sheetMap.get("operate");
        String auditperformer = StaticMethod.nullObject2String(operate.get("auditPerformer"));
        Map main = (HashMap) sheetMap.get("main");
        Map link = (HashMap) sheetMap.get("link");

        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));

//add by qinbo begin on 20100726
        System.out.println("当前的操作状态为：" + operateType);
        if (operateType.equals("205")) {
            Date date = (Date) link.get("operateTime");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = df.format(date).toString();
            System.out.println("提交回复的操作时间为：：" + df.format(date).toString());

            String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
            System.out.println("状态为205回复的前一条preLinkId=" + preLinkId);
            BaseLink baseLink = this.getLinkService().getSingleLinkPO(preLinkId);

            String previousTime = baseLink.getOperateTime().toString().substring(0, 19);
            System.out.println("回复的前一条操作时间：：" + previousTime);

            long timeDifference = LocalUtils.getResumeTimeSlot(previousTime, nowTime);

            Long ltime = new Long(timeDifference);
            System.out.println("时间差为：" + ltime.toString());
            link.put("swichConfTime", ltime.toString());
        }
//add by qinbo end on 20100726

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

//		if(StaticMethod.nullObject2String(main.get("isCustomerFlag")).equalsIgnoreCase("1")){
//			String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.SendImmediately"));
//			if(!sendImmediately.equalsIgnoreCase("true")){
//				if (taskName.equals("ExcuteHumTask") && operateType.equals("4")) {// 驳回
//					operate.put("interfaceType", "withdrawWorkSheet");
//					operate.put("methodType", "withdrawWorkSheet");
//				} else if (operateType.equals("9")) {// 阶段回复
//					operate.put("interfaceType", "notifyWorkSheet");
//					operate.put("methodType", "notifyWorkSheet");
//				}else if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
//					operate.put("interfaceType", "confirmWorkSheet");
//					operate.put("methodType", "confirmWorkSheet");
//				} else if (operateType.equals("205")) {// 回复
//					operate.put("interfaceType", "replyWorkSheet");
//					operate.put("methodType", "replyWorkSheet");
//				}
//			}
//		}
        //alter by yangna
        System.out.println("main=" + main);
        if (main != null && StaticMethod.nullObject2String(main.get("isCustomerFlag")).equalsIgnoreCase("1")) {
            String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.SendImmediately"));
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

                String invokeReplyAll = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.InvokeReplyAll"));

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
                        System.out.println("preLinkId=" + preLinkId);
                        BaseLink baseLink = this.getLinkService().getSingleLinkPO(preLinkId);
                        if (baseLink.getOperateType() == null || baseLink.getOperateType().intValue() == 0 || baseLink.getOperateType().intValue() == 199 || baseLink.getOperateType().intValue() == 54) {//是第一步受理

                            operate.put("interfaceType", "confirmWorkSheet");
                            operate.put("methodType", "confirmWorkSheet");
                        }
                    }
                } else if (operateType.equals("205")) {// 回复
                    if (!parentCorrKey.equalsIgnoreCase(StaticMethod.nullObject2String(main.get("correlationKey")))) {//分派
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
        //end by yangna
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
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();

        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("sheetAccessories");
        mainAttachmentAttributes.add("numDetail");
        mainAttachmentAttributes.add("appProgramme");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");

        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
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
        System.out.println("operateType is =========" + operateType);
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
                System.out.println("else =========");
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

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);

        String taskName = (String) request.getAttribute("taskName");
        System.out.println("taskName is =============" + taskName);
        if (taskName != null && taskName.equals("TaskCreateAuditHumTask")) {
            BusinessDredgebroadLink link = (BusinessDredgebroadLink) request.getAttribute("sheetLink");
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
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


//	/**
//	 * LINK的提交
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public void performDeal(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		super.performDeal(mapping, form, request, response);
//		String activeTemplateId = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
//		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
//		String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
//		ITask task = this.getTaskService().getSinglePO(taskId);
//		//如果为处理回复通过
//		if(activeTemplateId.equals("AffirmHumTask")&&phaseId.equals("receive")){
//			System.out.println("处理回复通过");
//			IBusinessDredgebroadFlowManager service = (IBusinessDredgebroadFlowManager)this.getBusinessFlowService();
//			
//			HashMap sessionMap = new HashMap();
//			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//			.getSession().getAttribute("sessionform");
//			sessionMap.put("userId", sessionform.getUserid());
//			sessionMap.put("password", sessionform.getPassword());
//			//得到父流程实例号
//			String parentProcessId = service.queryParentProcessId(task.getProcessId(), sessionMap);
//			//查询是否所有分派的任务都处理回复通过了
//			if(parentProcessId!=null){
//				BaseMain main = this.getMainService().getSingleMainPO(task.getSheetKey());
//				int num = service.querySubProcessTaskNum(parentProcessId, sessionMap);
//				if(num>0){
//					Thread.currentThread().sleep(2000);
//					num = service.querySubProcessTaskNum(parentProcessId, sessionMap);
//				}
//				if(num==0){
//					System.out.println("所有分派的任务都处理回复通过");
//					String value = "";
//					if(main.getPiid().equals(parentProcessId)){
//						value = "true";
//					}else{
//						try{
//							value = service.getProcessInstanceCustomPropertyValue(parentProcessId, "ifAccepted", sessionMap);
//						}catch(Exception e){
//							System.out.println(e.getMessage());
//						}
//					}
//					if(value.equals("true")){
//						request.setAttribute("ifAccepted", "true");
//						request.setAttribute("parentProcessId", parentProcessId);
//						this.showDetailPage(mapping, form, request, response);
//						request.setAttribute("forwardStr", "detail");
//					}
//				}
//			}
//		}
//	}
//	/**
//	 * 申明一个任务
//	 */
//	public void performClaim(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		super.performClaim(mapping, form, request, response);
//		String activeTemplateId = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
//		if(activeTemplateId.equals("ExcuteHumTask")){
//			System.out.println("确认受理");
//			HashMap sessionMap = new HashMap();
//			
//			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//			.getSession().getAttribute("sessionform");
//			sessionMap.put("userId", sessionform.getUserid());
//			sessionMap.put("password", sessionform.getPassword());
//			
//			String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
//			ITask task = this.getTaskService().getSinglePO(taskId);
//			IBusinessDredgebroadFlowManager service = (IBusinessDredgebroadFlowManager)this.getBusinessFlowService();
//			service.setProcessInstanceCustomProperty(task.getProcessId(), "ifAccepted", "true", sessionMap);
//		}
//	}
//	/**
//	 * 工单的详细信息页面
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public void showDetailPage(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String ifAccepted = StaticMethod.nullObject2String(request.getAttribute("ifAccepted"));
//		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"), "");
//		//当从待处理进入detail时
//		if(ifAccepted.equals("")&&(taskName.equals("ExcuteHumTask")||taskName.equals("HoldHumTask"))){
//			IBusinessDredgebroadFlowManager service = (IBusinessDredgebroadFlowManager)this.getBusinessFlowService();
//			HashMap sessionMap = new HashMap();
//			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//			.getSession().getAttribute("sessionform");
//			sessionMap.put("userId", sessionform.getUserid());
//			sessionMap.put("password", sessionform.getPassword());
//			String TKID = StaticMethod.nullObject2String(request.getParameter("TKID"));
//			ITask task = this.getTaskService().getSinglePO(TKID);
//			String parentProcessId = task.getProcessId();
//			try{
//				ifAccepted = service.getProcessInstanceCustomPropertyValue(parentProcessId, "ifAccepted", sessionMap);
//			}catch(Exception e){
//				System.out.println(e.getMessage());
//			}
//			super.showDetailPage(mapping, form, request, response);
//			if(ifAccepted.equals("true")){
//				task.setTaskStatus("8");
//				request.setAttribute("task", task);
//				request.setAttribute("taskStatus", "8");
//			}
//		}else{
//			//提交工单后直接转到detail页面时
//			if(ifAccepted.equals("true")){
//				int selecttimes = 0;
//				while(selecttimes<2){
//					String parentProcessId = StaticMethod.nullObject2String(request.getAttribute("parentProcessId"));
//					if(parentProcessId.equals("")){
//						parentProcessId = StaticMethod.nullObject2String(request.getParameter("piid"), "");
//					}
//					//查询是否产生了task
//					List list = this.getTaskService().getTasksByCondition("processId='"+parentProcessId+"' and (taskName='ExcuteHumTask' or taskName='HoldHumTask') and taskStatus=2");
//					if(list!=null&&list.size()>0){
//						ITask task = (ITask)list.get(0);
//						TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//						.getSession().getAttribute("sessionform");
//						//------------------------------------------------
//						String sheetKey = task.getSheetKey();
//						if (!sheetKey.equals("")) {
//							BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
//							String parentSheetKey = StaticMethod.nullObject2String(mainObject
//									.getParentSheetId());
//							String parentBeanId = StaticMethod.nullObject2String(mainObject
//									.getParentSheetName());
//							System.out.println("parentSheetKey=" + parentSheetKey
//									+ "parentBeanId=" + parentBeanId);
//							if (!parentSheetKey.equals("") && !parentBeanId.equals("")) {
//								IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
//										.getInstance().getBean(parentBeanId);
//								BaseMain parentMain = parentMainSerivce.getSingleMainPO(parentSheetKey);
//								String parentSheetId = parentMain.getSheetId();
//	
//								ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
//										.getInstance().getBean("ITawSystemWorkflowManager");
//								TawSystemWorkflow workflow = mgr
//										.getTawSystemWorkflowByBeanId(parentBeanId);
//								String parentProcessCnName = workflow.getRemark();
//	
//								request.setAttribute("parentSheetId", parentSheetId);
//								request
//										.setAttribute("parentProcessCnName",
//												parentProcessCnName);
//	
//								System.out.println("parentSheetId=" + parentSheetId
//										+ "parentProcessCnName=" + parentProcessCnName);
//							}
//							request.setAttribute("sheetMain", mainObject);
//						}
//	
//						String dealTemplateId = StaticMethod.nullObject2String(request
//								.getParameter("dealTemplateId"));
//						if (dealTemplateId != null && !dealTemplateId.equals("")) {
//							request.setAttribute("dealTemplateId", dealTemplateId);
//							String operateType = StaticMethod.nullObject2String(request
//									.getParameter("operateType"));
//							request.setAttribute("operateType", operateType);
//						}
//	
//						// List list = getLinkService().getLinksByMainId(sheetKey);
//						// add by leo 草稿需要传入mainId，以便于在wps中修改main对象（DB）
//						String taskStatus = task.getTaskStatus();
//						if ((taskStatus.equals(Constants.TASK_STATUS_READY)
//										|| taskStatus.equals(Constants.TASK_STATUS_RUNNING) || taskStatus
//										.equals(Constants.TASK_STATUS_CLAIMED))) {
//							try {
//								String isWaitForSubTask = task.getIfWaitForSubTask();
//								if (isWaitForSubTask.equals("true")) {
//									List subTaskList = this.getTaskService()
//											.getUndealTaskListByParentTaskId(task.getId());
//									if (subTaskList != null && subTaskList.size() > 0) {
//										request.setAttribute("needDealReply", "true");
//									}
//								}
//								task.setTaskStatus("8");
//								request.setAttribute("task", task);
//							} catch (Exception e) {
//								request.setAttribute("task", null);
//							}
//						} else {
//							request.setAttribute("task", null);
//						}
//						
//						String operateRoleId = StaticMethod.nullObject2String(task.getOperateRoleId());
//						if(!operateRoleId.equals("")){
//							ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
//							.getInstance().getBean("ItawSystemSubRoleManager");
//							TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);
//							if (subrole != null) {
//								System.out.println("==roleId==>>" + subrole.getRoleId() + "");
//								request.setAttribute("roleId", subrole.getRoleId() + "");
//							}
//						}
//						//----------------------------------------------------------------
//						request.setAttribute("mainId", task.getSheetKey());
//						request.setAttribute("taskId", task.getId());
//						request.setAttribute("taskName", task.getTaskName());
//						request.setAttribute("operateRoleId", operateRoleId);
//						request.setAttribute("operateUserId", sessionform.getUserid());
//						request.setAttribute("operateDeptId", sessionform.getDeptid());
//						request.setAttribute("operaterContact", sessionform.getContactMobile());
//						request.setAttribute("piid", parentProcessId);
//						request.setAttribute("TKID", task.getId());
//						request.setAttribute("taskStatus", "8");
//						request.setAttribute("preLinkId", task.getPreLinkId());
//						request.setAttribute("methodBeanId", mapping.getAttribute());
//						request.setAttribute("teamFlag", StaticMethod.nullObject2String(request.getParameter("teamFlag")));
//						break;
//					}
//					//延迟两秒
//					Thread.currentThread().sleep(2000);
//					selecttimes++;
//				}
//				request.setAttribute("forwardStr", "detail");
//			}else{
//				super.showDetailPage(mapping, form, request, response);
//			}
//		}
//	}
}
