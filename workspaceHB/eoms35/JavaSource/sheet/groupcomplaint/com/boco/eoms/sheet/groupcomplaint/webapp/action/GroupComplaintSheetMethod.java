/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangLocator;
import com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_PortType;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintLinkManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintTaskManager;
import com.boco.eoms.sheet.groupcomplaint.task.impl.GroupComplaintTask;
import com.boco.eoms.sheet.groupcomplaint.zhzw.GroupComplaintZHZWService;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.XmlUtil;

/**
 * @author yymk
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintSheetMethod extends BaseSheet {

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
        String operatName = StaticMethod.nullObject2String(request
                .getParameter("operateName"));
        System.out.println("operateName is -----------------------"
                + operatName);
        if (operatName.equals("forceHold")) {
            HashMap map = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
            if (sheetKey == null || sheetKey.equals("")) {
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            }
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
            // 新增工单
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
        } else if (taskName.equals("DraftHumTask") || taskName.equals("ByRejectHumTask")) {
            // 草稿状态/ 被驳回
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null || sheetKey.equals("")) {
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            }
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            //sheetMap.put("link", getLinkService().getLinkObject());
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e1) {

            } catch (InstantiationException e1) {
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("HoldHumTask")) {
            // 待归档
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            // System.out.println("==$$$$==sheetKey==="+sheetKey);
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
        } else if (taskName.equals("FirstExcuteHumTask")
                || taskName.equals("SecondExcuteHumTask")
                || taskName.equals("CheckingHumTask")) {

            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            System.out.println("==$$$$一级处理、二级处理、质检==sheetKey===" + sheetKey);
            HashMap sheetMap = new HashMap();
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            System.out.println("==$$$$一级处理、二级处理、质检==main===" + main);
            sheetMap.put("main", main);
            //sheetMap.put("link", getLinkService().getLinkObject());
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e1) {

            } catch (InstantiationException e1) {
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else if (taskName.equals("advice") || taskName.equals("reply") || taskName.equals("cc")) {
            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null && sheetKey.equals("")) {
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            }
            System.out.println("非流程动作=====sheetKey is" + sheetKey);
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        } else {
            // 其他人工处理

            HashMap sheetMap = new HashMap();
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
            if (sheetKey == null && sheetKey.equals("")) {
                sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            }
            //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            sheetMap.put("main", main);
            //sheetMap.put("link", getLinkService().getLinkObject());
            try {
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
            } catch (IllegalAccessException e1) {

            } catch (InstantiationException e1) {
            }
            sheetMap.put("operate", getPageColumnName());
            hashMap.put("selfSheet", sheetMap);
        }
        return hashMap;
    }

    /**
     * 主要是实现角色的自动匹配。
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

        Map operateMap = (HashMap) sheetMap.get("operate");
        Map main = (HashMap) sheetMap.get("main");

        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));

        String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendImmediately"));

        if (StaticMethod.nullObject2String(main.get("mainInterfaceSheetType")).equalsIgnoreCase("crm")) {

            if (operateType.equals("8") && taskName.equals("SecondExcuteHumTask")) {
                java.util.Date newCompleteLimitT1 = StaticMethod.nullObject2Timestamp(main.get("sendTime"));
                if (newCompleteLimitT1 != null) {
                    Calendar calendarlimit = Calendar.getInstance();
                    calendarlimit.setTime(newCompleteLimitT1);
                    calendarlimit.add(10, 20);
                    main.put("mainCompleteLimitT1", calendarlimit.getTime());
                    main.put("cancelReason", "1");
                    sheetMap.put("main", main);
                }
            }

            if (!sendImmediately.equalsIgnoreCase("true")) {
                if (operateType.equals("4")) {// 驳回
                    String dealPerformer = StaticMethod.nullObject2String(request
                            .getParameter("dealPerformer"));

                    if ("SecondExcuteHumTask".equals(taskName)) {
                        dealPerformer = "8a9982f222885c36012298ed35bf38de";
                        operateMap.put("dealPerformer", dealPerformer);
                        operateMap.put("dealPerformerLeader", dealPerformer);
                        operateMap.put("dealPerformerType", "subrole");
                        operateMap.put("phaseId", "FirstExcuteHumTask");
                        Map link = (HashMap) sheetMap.get("link");
                        link.put("toOrgRoleId", dealPerformer);
                        sheetMap.put("link", link);
                    }

                    String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.InterfaceUser"));
                    String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendRoleId"));
                    System.out.println("集团投诉驳回:dealPerformer=" + dealPerformer);
                    if (dealPerformer.equals(userId) || dealPerformer.equals(sendRoleId)) {
                        operateMap.put("interfaceType", "withdrawWorkSheet");
                        operateMap.put("methodType", "withdrawWorkSheet");
                    }
                } else if (operateType.equals("9")) {// 阶段回复
                    operateMap.put("interfaceType", "notifyWorkSheet");
                    operateMap.put("methodType", "notifyWorkSheet");
                } else if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61")) {// 受理
                    String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
                    ITaskService iTaskService = (ITaskService) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
                    ITask task = iTaskService.getSinglePO(taskId);
                    if (task != null && !StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true")) {//不是子任务
                        operateMap.put("interfaceType", "confirmWorkSheet");
                        operateMap.put("methodType", "confirmWorkSheet");
                    }
                } else if (taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66")) {//同意延期
                    if (StaticMethod.nullObject2String(request.getParameter("mainDelayFlag")).equals("1")) {
                        operateMap.put("interfaceType", "notifyWorkSheet");
                        operateMap.put("methodType", "notifyWorkSheet");
                    }

                }
            }
            if (operateType.equals("46")) {// 回复
                operateMap.put("interfaceType", "replyWorkSheet");
                operateMap.put("methodType", "replyWorkSheet");
                operateMap.put("sendType", new Integer(InterfaceConstants.IS_UNREADY));
            }
            if (operateType.equals("56")) {// 质检合格
//				operateMap.put("interfaceType", "replyWorkSheet");
//				operateMap.put("methodType", "replyWorkSheet");
//				operateMap.put("sendType", new Integer(InterfaceConstants.UN_SEND));
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                System.out.println("------质检环节------sheetkey-" + sheetKey);
                IWfInterfaceInfoManager iwfinterfaceinfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder.getInstance().getBean("iWfInterfaceInfoManager");
                iwfinterfaceinfoManager.updateInfoForSend(sheetKey, "replyWorkSheet", "replyWorkSheet");
            }
        }

        String title = StaticMethod.nullObject2String(main.get("title"));
        String users = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.copyUserId"));
        String userType = "";

        if (taskName.equals("FirstExcuteHumTask") && "SecondExcuteHumTask".equals(StaticMethod.nullObject2String(operateMap.get("phaseId")))) {
            if (title.length() > 9 && "（省直管集团客户）".equals(title.substring(0, 9))) {
                if (!"".equals(users)) {
                    String userarr[] = users.split(",");
                    for (int i = 0; i < userarr.length; i++) {
                        userType = userType + "user,";
                    }
                    if (!"".equals(userType)) {
                        userType = userType.substring(0, userType.lastIndexOf(","));
                    }
                }

                operateMap.put("copyPerformer", users);
                operateMap.put("copyPerformerLeader", users);
                operateMap.put("copyPerformerType", userType);


                /**
                 * 当带有“（省直管集团客户）”标识的工单由T1环节派发到T2环节的时候，eoms需要将投诉工单对应的工单流水号、集团名称、工单派单时间、T2处理时限、所属地市等字段通过webservice传给智远的自动传报平台
                 */

                String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
                Date sendTime = SheetUtils.stringToDate(StaticMethod.nullObject2String(main.get("sendTime")));
                Date dealTime2 = SheetUtils.stringToDate(StaticMethod.nullObject2String(main.get("dealTime2")));
                String sendTimeStr = "";
                String dealTime2Str = "";
                if (sendTime != null) {
                    sendTimeStr = StaticMethod.date2String(sendTime);
                }
                if (dealTime2 != null) {
                    dealTime2Str = StaticMethod.date2String(dealTime2);
                }
                String customName = StaticMethod.nullObject2String(main.get("customName"));
                String cityName = StaticMethod.nullObject2String(main.get("cityName"));
                TSfaultInfo_yiyangLocator service = new TSfaultInfo_yiyangLocator();
                TSfaultInfo_yiyangSoap_PortType bing = service.getTSfaultInfo_yiyangSoap();
//				String ret = bing.TS_ConveyMessage(customerName, sendtime, receipetime, city, jobNo, uname, upwd);
                String ret = bing.TS_ConveyMessage(customName, sendTimeStr, dealTime2Str, cityName, sheetId, "webs_yiyang", "sBIQCUoBGyIlo6dAxcQdtQ==");
                System.out.println("123TSfaultInfo sheetid=" + sheetId + "===ret=" + ret);
            }

//			T1自动处理，调用智慧专维派单接口
            GroupComplaintZHZWService groupComplaintZHZWService = new GroupComplaintZHZWService();
            System.out.println("T1toT2==newsheet==" + main.get("sheetId"));
            groupComplaintZHZWService.sendSheet(main);


        }

        //当带有“（省直管集团客户）”标识的集团投诉处理工单在T2回单的时后，需要短信通知，短信发送到投诉手机和值班长手机上。
        if (operateType.equals("46") && taskName.equals("SecondExcuteHumTask") && title.length() > 9 && "（省直管集团客户）".equals(title.substring(0, 9))) {
            String smsMobile = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.smsMobile"));
            IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            if (!"".equals(smsMobile)) {
                String smsMobilearr[] = smsMobile.split(",");
                String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
                String alarmName = "";
                String coutent = "工单标题：" + title + "，工单号：" + sheetId + "，告警名称：" + alarmName + "，已回单。";
                for (int i = 0; i < smsMobilearr.length; i++) {
                    String uuid = UUIDHexGenerator.getInstance().getID();
                    String insertSql = "INSERT INTO sms_monitor VALUES ('" + uuid + "','jkszg','','" + sheetId + "','','" + smsMobilearr[i] + "',Sysdate,'" + coutent + "','true','false','0')";
                    sqlMgr.updateTasks(insertSql);
                }
            }
        }

        //质检时调用智慧专维接口
        if ("CheckingHumTask".equals(taskName) && ("56".equals(operateType) || "54".equals(operateType))) {
            String linkCheckResult = "";
            if ("56".equals(operateType)) {//质检通过
                linkCheckResult = "0";
            }

            if ("54".equals(operateType)) {//质检不通过
                linkCheckResult = "1";
            }

            String linkCheckIdea = StaticMethod.nullObject2String(request.getParameter("linkCheckIdea"));
            Map reMap = new HashMap();
            reMap.put("linkCheckResult", linkCheckResult);
            reMap.put("linkCheckIdea", linkCheckIdea);

            reMap.put("sheetId", main.get("sheetId"));
            reMap.put("PARENTCORRELATION", main.get("PARENTCORRELATION"));
            GroupComplaintZHZWService groupComplaintZHZWService = new GroupComplaintZHZWService();
            System.out.println("T1toT2==newsheet==" + main.get("sheetId"));
            groupComplaintZHZWService.inspectionSheet(reMap);
        }

        sheetMap.put("operate", operateMap);
        this.setFlowEngineMap(sheetMap);
    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);

        // add by yangyankang
        String operateRoleId = StaticMethod.nullObject2String(request
                .getParameter("operateRoleId"));

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemSubRoleManager");
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

        /*
         * add by panlong
         */
        // 取上条TASK
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask")) {
            super.setParentTaskOperateWhenRejct(request);
        }
    }

    /**
     * 工单的详细信息页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void showDetailPage(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDetailPage(mapping, form, request, response);
//		String sheetKey = StaticMethod.nullObject2String(request
//				.getParameter("sheetKey"), "");
//		String taskName = StaticMethod.nullObject2String(request
//				.getParameter("taskName"), "");
//		String taskStatus = StaticMethod.nullObject2String(request
//				.getParameter("taskStatus"), "");

//		if ((taskName.equals("FirstExcuteHumTask")
//				|| taskName.equals("SecondExcuteHumTask") )
//				&& (!taskStatus.equals("") && !taskStatus.equals(Constants.TASK_STATUS_FINISHED))) {
//
//			String beanName = mapping.getAttribute();
//			IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder
//					.getInstance().getBean(beanName);
//			IGroupComplaintMainManager groupcomplaintMainManager = (IGroupComplaintMainManager) baseSheet
//					.getMainService();
//			List showInvokeRelationShipList = groupcomplaintMainManager
//					.showInvokeRelationShipList(sheetKey);
//
//			if (showInvokeRelationShipList != null
//					&& showInvokeRelationShipList.size() > 0) {
//				request.setAttribute("ifInvokeUrgentFault", "yes");
//			} else {
//				request.setAttribute("ifInvokeUrgentFault", "no");
//			}
//		}
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
        IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
        IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));

        GroupComplaintMain main = (GroupComplaintMain) getMainService().getSingleMainPO(sheetKey);
        String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('8','2') and taskName in ('FirstExcuteHumTask','SecondExcuteHumTask','CheckingHumTask')  ORDER BY createtime DESC ";
        List taskList = taskservice.getTasksByCondition(condition);

        GroupComplaintLink linkbean = new GroupComplaintLink();

        if (operateType.equals("56") && "CheckingHumTask".equals(taskName)) {
//        	 List linkList = linkservice.getLinksBySql(" SELECT * FROM groupcomplaint_link WHERE mainid = '"+sheetKey+"' AND operatetype = '46' ORDER BY operatetime DESC");
            List linkList = linkservice.getLinksBycondition("mainid = '" + sheetKey + "' AND operatetype = '46' ORDER BY operatetime DESC", "GroupComplaintLink");

            if (linkList != null && linkList.size() > 0) {
                linkbean = (GroupComplaintLink) linkList.get(0);
            }
        } else {
            String remark = StaticMethod.nullObject2String(request.getParameter("remark"));
            String ndeptContact = StaticMethod.nullObject2String(request.getParameter("ndeptContact"));
            String ndeptContactPhone = StaticMethod.nullObject2String(request.getParameter("ndeptContactPhone"));
            String compProp = StaticMethod.nullObject2String(request.getParameter("compProp"));
            String isReplied = StaticMethod.nullObject2String(request.getParameter("isReplied"));
            String issueEliminatTimeStr = StaticMethod.nullObject2String(request.getParameter("issueEliminatTime"));
            String issueEliminatReason = StaticMethod.nullObject2String(request.getParameter("issueEliminatReason"));
            String dealResult = StaticMethod.nullObject2String(request.getParameter("dealResult"));
            String linkIsNeedBomcAssist = StaticMethod.nullObject2String(request.getParameter("linkIsNeedBomcAssist"));

            linkbean.setActiveTemplateId(taskName);
            linkbean.setOperateType(new Integer(operateType));
            linkbean.setOperateUserId(sessionform.getUserid());

            Date issus = SheetUtils.stringToDate(issueEliminatTimeStr);
            if (!"".equals(issueEliminatTimeStr) && issus != null) {
                linkbean.setIssueEliminatTime(issus);
            }
            linkbean.setRemark(remark);
            linkbean.setNdeptContact(ndeptContact);
            linkbean.setNdeptContactPhone(ndeptContactPhone);
            linkbean.setCompProp(compProp);
            linkbean.setIsReplied(isReplied);
            linkbean.setIssueEliminatReason(issueEliminatReason);
            linkbean.setDealResult(dealResult);
            linkbean.setLinkIsNeedBomcAssist(linkIsNeedBomcAssist);

        }


        String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iGroupComplaintMainManager");
        IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager) ApplicationContextHolder.getInstance().getBean(interfaceBeanId);

        String status = "0";
        String text = "";

        System.out.println("groupsheet===" + main.getSheetId());

        GroupComplaintTask task = new GroupComplaintTask();
        if ((taskList != null) && (taskList.size() > 0)) {
            task = (GroupComplaintTask) taskList.get(0);
        }

        boolean returnType = true;
        WfInterfaceInfo info = new WfInterfaceInfo();
        //T1驳回给建单人 调用接口，返回成功就流转、不成功不流转
        String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendImmediately"));
        String linkId = StaticMethod.nullObject2String(task.getId());
        String isSended = "0";
        String mainBeanId = "iGroupComplaintMainManager";
        String linkBeanId = "iGroupComplaintTaskManager";
        String interfaceType = "";
        String methodType = "";
        if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm")) {
            if (!sendImmediately.equalsIgnoreCase("true")) {
                if (operateType.equals("4") && taskName.equals("FirstExcuteHumTask")) { //驳回
//					String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.InterfaceUser"));
//					String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendRoleId"));
//					String dealPerformer = StaticMethod.nullObject2String(request.getParameter("dealPerformer"));
//					System.out.println("grouplyg:dealPerformer=" + dealPerformer);
//					if (dealPerformer.equals(userId) || dealPerformer.equals(sendRoleId))
//					{	
                    interfaceType = "withdrawWorkSheet";
                    methodType = "withdrawWorkSheet";

                    info.setInterfaceType(interfaceType);
                    info.setSheetKey(sheetKey);
                    info.setLinkId(linkId);
                    info.setIsSended(isSended);
                    info.setMainBeanId(mainBeanId);
                    info.setLinkBeanId(linkBeanId);
                    info.setMethodType(methodType);

                    System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=4=befoer==" + returnType);
                    returnType = operateMgr.sendData(info, linkbean);
                    System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=4=after==" + returnType);

                    if (!returnType) {//返回的结果是false
                        text = "调用客服接口错误！";
                        status = "2";
                    }
//					}
                } else if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61")) {//T1确认受理
                    if (task != null && !StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true")) {

                        interfaceType = "confirmWorkSheet";
                        methodType = "confirmWorkSheet";

                        info.setInterfaceType(interfaceType);
                        info.setSheetKey(sheetKey);
                        info.setLinkId(linkId);
                        info.setIsSended(isSended);
                        info.setMainBeanId(mainBeanId);
                        info.setLinkBeanId(linkBeanId);
                        info.setMethodType(methodType);

                        System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=61=befoer==" + returnType);
                        returnType = operateMgr.sendData(info, linkbean);
                        System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=61=after==" + returnType);
                    }
                }
            }

        }

        if (operateType.equals("56") && taskName.equals("CheckingHumTask")) {
            System.out.println("------质检环节------sheetkey-" + sheetKey);

            interfaceType = "replyWorkSheet";
            methodType = "replyWorkSheet";

            info.setInterfaceType(interfaceType);
            info.setSheetKey(sheetKey);
            info.setLinkId(linkId);
            info.setIsSended(isSended);
            info.setMainBeanId(mainBeanId);
            info.setLinkBeanId(linkBeanId);
            info.setMethodType(methodType);

            System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=56=befoer==" + returnType);
            returnType = operateMgr.sendData(info, linkbean);
            System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=56=after==" + returnType);

            if (!returnType) {//返回的结果是false
                text = "调用客服接口错误！";
                status = "2";
            }

        }

        //T2回单时判断是否自动质检，是则调用接口，返回成功就流转、不成功不流转，否流程往下走
        //T2确认受理调用接口，此时不需要确认
        //回单时限-当前时间<30
        Date sheetCompleteLimit = SheetUtils.stringToDate(StaticMethod.nullObject2String(main.getSheetCompleteLimit()));
        if (operateType.equals("46")) {
            //调用中资接口start
//			String linkIfElectricCode = StaticMethod.nullObject2String(link.get("linkIfElectricCode"));
//			String electricCode = StaticMethod.nullObject2String(link.get("electricCode"));
//			if("0".equals(linkIfElectricCode)){
//				
//			}
            //调用中资接口end


            if (sheetCompleteLimit.getTime() - new Date().getTime() < 30 * 60 * 1000) {
                //调用接口
                interfaceType = "replyWorkSheet";
                methodType = "replyWorkSheet";

                info.setInterfaceType(interfaceType);
                info.setSheetKey(sheetKey);
                info.setLinkId(linkId);
                info.setIsSended(isSended);
                info.setMainBeanId(mainBeanId);
                info.setLinkBeanId(linkBeanId);
                info.setMethodType(methodType);

                System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=46=befoer==" + returnType);
                returnType = operateMgr.sendData(info, linkbean);
                System.out.println("sheet===" + main.getSheetId() + "====grouplyg:returnType=46=after==" + returnType);

                if (!returnType) {//返回的结果是false
                    text = "调用客服接口错误！";
                    status = "2";
                }
            }
        }

        JSONArray data = new JSONArray();
        JSONObject o = new JSONObject();
        o.put("text", text);
        data.put(o);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot.put("data", data);
        jsonRoot.put("status", status);
        JSONUtil.print(response, jsonRoot.toString());

    }


}
