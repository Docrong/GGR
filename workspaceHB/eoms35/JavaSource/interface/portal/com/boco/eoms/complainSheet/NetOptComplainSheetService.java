package com.boco.eoms.complainSheet;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.handheldoperation.HandheldOperationUtil;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;

import com.boco.eoms.sheet.complaint.model.ComplaintLink;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintLinkManager;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;
import com.boco.eoms.sheet.complaint.service.IComplaintTaskManager;
import com.boco.eoms.sheet.complaint.task.impl.ComplaintTask;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.StaxParser;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetOptComplainSheetService {


    public String acceptSheet(String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            HashMap columnMap = new HashMap();
            HashMap sheetMap1 = new HashMap();
            Map valueMap = new HashMap();
            IComplaintMainManager mainservice = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
            IComplaintTaskManager taskservice = (IComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
            IComplaintLinkManager linkservice = (IComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            String operateUserId = "";
            InterfaceUtilProperties properties = new InterfaceUtilProperties();

            String nodeName = "acceptService";
            System.out.println("CompiainSheetService.acceptSheet内对应的nodeName======" + nodeName);

            if ((opDetail != null) && (!"".equals(opDetail))) {
                String filePath = StaticMethod.getFilePathForUrl("classpath:config/netOptComplainSheet.xml");
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

                valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
                String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
                String sheet_status = StaticMethod.nullObject2String(sheetMap.get("Sheet_status"));
                String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
                String accept_time = StaticMethod.nullObject2String(sheetMap.get("Accept_time"));
                if ((sheet_id != null) && (!"".equals(sheet_id))) {
                    ComplaintMain main = (ComplaintMain) mainservice.getMainBySheetId(sheet_id);
                    int status = StaticMethod.nullObject2int(main.getStatus());

                    if (status != 0) {
                        result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
                        return result;
                    }
                    String sheetKey = StaticMethod.nullObject2String(main.getId());
                    String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask' ";
                    List taskList = taskservice.getTasksByCondition(condition);

                    if ((taskList != null) && (taskList.size() > 0)) {
                        ComplaintTask task = (ComplaintTask) taskList.get(0);
                        String operateType = StaticMethod.nullObject2String(task.getOperateType());
                        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                        TawSystemUser user = userMgr.getUserByuserid(operate_userid);
                        if ((operate_userid != null) && (!"".equals(operate_userid))) {
                            if ("user".equals(operateType)) {
                                operateUserId = operate_userid;
                            } else if ("subrole".equals(operateType)) {
                                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
                                List userList = usermgr.getRoleidByuserid(operate_userid);
                                if (userList.contains(task.getTaskOwner())) {
                                    operateUserId = operate_userid;
                                } else {
                                    if (operate_userid.equals(task.getTaskOwner())) {
                                        operateUserId = operate_userid;
                                    } else {
                                        result = "Status=-1;Errlist=工单受理状态更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                                        return result;
                                    }
                                }
                            } else if ("dept".equals(operateType)) {
                                if (task.getTaskOwner().equals(user.getDeptid())) {
                                    operateUserId = operate_userid;
                                } else {
                                    result = "Status=-1;Errlist=工单受理状态更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                                    return result;
                                }
                            }
                        } else {
                            result = "Status=-1;Errlist=工单受理状态更新请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                            return result;
                        }

                        if (user != null) {
                            valueMap.put("operateDeptId", user.getDeptid());
                            valueMap.put("operaterContact", user.getMobile());
                        }

                        valueMap.put("operateUserId", operateUserId);
                        valueMap.put("operateRoleId", task.getOperateRoleId());
                        valueMap.put("mainId", main.getId());
                        valueMap.put("aiid", task.getId());
                        valueMap.put("toOrgRoleId", task.getOperateRoleId());
                        valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
                        valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
                        if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm")) {
                            valueMap.put("interfaceType", "confirmWorkSheet");
                            valueMap.put("methodType", "confirmWorkSheet");
                        }
                        try {
                            sheetMap1.put("main", main);
                            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
                            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
                            columnMap.put("selfSheet", sheetMap1);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                            return result;
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                            return result;
                        }
                        try {
                            sm.claimTask(task.getId(), valueMap, columnMap, operateUserId);
                        } catch (Exception e) {
                            e.printStackTrace();
                            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                            return result;
                        }
                        result = "sheetNo=0;Errlist=";
                    } else {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于待受理状态,无法进行受理状态更新操作！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }

    public String dealSheet(String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            IComplaintMainManager mainservice = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
            IComplaintTaskManager taskservice = (IComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
            IComplaintLinkManager linkservice = (IComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            HashMap sheetMap = new HashMap();
            HashMap columnMap = new HashMap();
            HashMap sheetMap1 = new HashMap();
            String operateUserId = "";
            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String nodeName = "dealSheet";
            System.out.println("CompiainSheetService.dealSheet内对应的nodeName======" + nodeName);
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/netOptComplainSheet.xml");
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

                String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
                String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
                String operate_time = StaticMethod.nullObject2String(sheetMap.get("Operate_time"));

                if ((sheet_id != null) && (!"".equals(sheet_id))) {
                    ComplaintMain main = (ComplaintMain) mainservice.getMainBySheetId(sheet_id);
                    int status = StaticMethod.nullObject2int(main.getStatus());

                    if (status != 0) {
                        result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
                        return result;
                    }
                    String sheetKey = StaticMethod.nullObject2String(main.getId());
                    String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='SecondExcuteHumTask' ";
                    List taskList = taskservice.getTasksByCondition(condition);

                    if ((taskList != null) && (taskList.size() > 0)) {
                        ComplaintTask task = (ComplaintTask) taskList.get(0);
                        String operateType = StaticMethod.nullObject2String(task.getOperateType());
                        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                        TawSystemUser user = userMgr.getUserByuserid(operate_userid);

                        if ((operate_userid != null) && (!"".equals(operate_userid))) {
                            if (task.getTaskOwner().equals(operate_userid)) {
                                operateUserId = operate_userid;
                            } else if ("user".equals(operateType)) {
                                operateUserId = operate_userid;
                            } else if ("subrole".equals(operateType)) {
                                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
                                List userList = usermgr.getRoleidByuserid(operate_userid);
                                if (userList.contains(task.getTaskOwner())) {
                                    operateUserId = operate_userid;
                                } else {
                                    result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                                    return result;
                                }
                            } else if ("dept".equals(operateType)) {
                                if (task.getTaskOwner().equals(user.getDeptid())) {
                                    operateUserId = operate_userid;
                                } else {
                                    result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                                    return result;
                                }
                            }
                        } else {
                            result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
                            return result;
                        }
                        if (user != null) {
                            valueMap.put("operaterContact", user.getMobile());
                        }
                        valueMap.put("operateUserId", operateUserId);
                        valueMap.put("operateRoleId", task.getOperateRoleId());
                        valueMap.put("mainId", main.getId());
                        valueMap.put("operateTime", operate_time);
                        valueMap.put("aiid", task.getId());
                        Map sheetMainMap = SheetBeanUtils.bean2Map(main);
                        valueMap.putAll(sheetMainMap);
                        BaseLink linkObject = (BaseLink) linkservice.getLinkObject().getClass().newInstance();
                        String linkId = UUIDHexGenerator.getInstance().getID();
                        linkObject.setId(linkId);
                        sheetMap1.put("main", main);
                        sheetMap1.put("link", linkObject);
                        sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
                        columnMap.put("selfSheet", sheetMap1);
                        String zjSubroleid = "8a9982f2222d20300122311a9071016b";
                        String customattribution = main.getCustomAttribution();
                        String complaintType1 = main.getComplaintType1();
                        String areaid = StaticMethod.nullObject2String(InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("customAttribution", StaticMethod.nullObject2String(customattribution)));
                        System.out.println("--------质检 areaid-----------" + areaid);
                        String roleid = String.valueOf(task.getOperateRoleId());
                        if (!areaid.equals("")) {
                            ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
                            ArrayList rolelist = (ArrayList) mgr.getSubRolesByDeptId(areaid, "52", "217");
                            System.out.println("--质检-----deptid-------" + areaid);
                            System.out.println("----质检----roleid-----------" + roleid);
                            System.out.println("------------质检---------zjSubroleid----" + zjSubroleid);
                            if ((rolelist.size() > 0) && ("101062502".equals(complaintType1))) {
                                TawSystemSubRole sysrole = (TawSystemSubRole) rolelist.get(0);
                                zjSubroleid = sysrole.getId();
                            }
                        }
                        System.out.println("------------质检---------zjSubroleid12----" + zjSubroleid);
                        valueMap.put("dealPerformer", zjSubroleid);

                        valueMap.put("dealPerformerType", "subrole");
                        valueMap.put("dealPerformerLeader", zjSubroleid);

                        IWfInterfaceInfoManager wfInterfaceInfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
                                .getInstance().getBean("iWfInterfaceInfoManager");
                        try {
                            wfInterfaceInfoManager.saveWfInterfaceInto("iComplaintMainManager",
                                    "iComplaintLinkManager", sheetKey, linkId, "replyWorkSheet",
                                    "replyWorkSheet", "2");
                            BocoLog.info(this, "invokeWfInterface end!!!!!!!!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //质检开始
                        String customPhone = StaticMethod.nullObject2String(main.getCustomPhone());
                        Date complaintTime = StaticMethod.nullObject2Timestamp(main.getComplaintTime());
                        System.out.println("lizhi:customPhone=" + customPhone + "complaintTime=" + complaintTime);
                        int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(complaintTime);
                        cal.add(5, time);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String beforedate = sdf.format(cal.getTime());
                        String afterdate = sdf.format(complaintTime);
                        System.out.println("lizhi:beforedate=" + beforedate + "afterdate=" + afterdate + "customPhone=" + customPhone);
                        int repeatNum = mainservice.getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
                        System.out.println("lizhi:repeatNum=" + repeatNum);
                        List needAutoTranList = null;
                        if (repeatNum == 0 || repeatNum == 1) {
                            String complaintType2 = StaticMethod.nullObject2String(main.getComplaintType2());
                            String complaintType = StaticMethod.nullObject2String(main.getComplaintType());
                            String complaintType4 = StaticMethod.nullObject2String(main.getComplaintType4());
                            String complaintType5 = StaticMethod.nullObject2String(main.getComplaintType5());
                            String complaintType6 = StaticMethod.nullObject2String(main.getComplaintType6());
                            String complaintType7 = StaticMethod.nullObject2String(main.getComplaintType7());
                            System.out.println("lizhi:complaintType1=" + complaintType1 + "complaintType2=" + complaintType2 + "complaintType=" + complaintType + "complaintType4=" + complaintType4 + "complaintType5=" + complaintType5 + "complaintType6=" + complaintType6 + "complaintType7=" + complaintType7);
                            needAutoTranList = (List) mainservice.ifneedAutotran(complaintType1, complaintType2, complaintType, complaintType4, complaintType5, complaintType6, complaintType7);

                            //System.out.println("lizhi:needAutoTranList.size=" + needAutoTranList.size());

                            if (needAutoTranList != null && needAutoTranList.size() > 0) {
                                System.out.println("---------自动质检开始--------");
                                Date opeTime = new Date();
                                String prelinkId = StaticMethod.nullObject2String(linkId);
                                String subroleid = StaticMethod.nullObject2String(main.getSendRoleId());
                                System.out.println("lizhi:opeTime=" + opeTime + "prelinkId=" + prelinkId + "subroleid=" + subroleid);
                                valueMap.put("phaseId", "HoldHumTask");
                                valueMap.put("dealPerformer", subroleid);
                                valueMap.put("dealPerformerLeader", subroleid);
                                valueMap.put("dealPerformerType", "subrole");

                                if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm")) {
                                    String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendImmediately"));
                                    if (!sendImmediately.equalsIgnoreCase("true")) {
                                        try {
                                            wfInterfaceInfoManager.updateInfoForSend(sheetKey, "replyWorkSheet", "replyWorkSheet");
                                            BocoLog.info(this, "zhijian  invokeWfInterface end!!!!!!!!");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                createCheckingLink(main, opeTime, prelinkId, subroleid);
                                createCheckingTask(main, prelinkId);
                            }
                        }
                        sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);

                        //质检结束

                        result = "sheetNo=0;Errlist=";
                    } else {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单处理完成提交操作！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }

    public String rejectSheet(String opDetail) {
        String result = "";
        try {
            IComplaintMainManager mainservice = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
            IComplaintTaskManager taskservice = (IComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
            IComplaintLinkManager linkservice = (IComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            HashMap sheetMap = new HashMap();
            HashMap columnMap = new HashMap();
            HashMap sheetMap1 = new HashMap();
            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String nodeName = "rejectSheet";
            System.out.println("ComplainSheetService.dealSheet内对应的nodeName======" + nodeName);
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/netOptComplainSheet.xml");
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
                String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
                String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
                String operate_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));
                if ((sheet_id != null) && (!"".equals(sheet_id))) {
                    ComplaintMain main = (ComplaintMain) mainservice.getMainBySheetId(sheet_id);
                    int status = StaticMethod.nullObject2int(main.getStatus());

                    if (status != 0) {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
                        return result;
                    }
                    String sheetKey = StaticMethod.nullObject2String(main.getId());
                    String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask'";

                    List taskList = taskservice.getTasksByCondition(condition);
                    if ((taskList != null) && (taskList.size() > 0)) {
                        ComplaintTask task = (ComplaintTask) taskList.get(0);
                        String operateType = StaticMethod.nullObject2String(task.getOperateType());
                        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                        TawSystemUser user = userMgr.getUserByuserid(operateUserId);
                        if (user != null) {
                            valueMap.put("operateDeptId", user.getDeptid());
                            valueMap.put("operaterContact", user.getMobile());
                        }
                        valueMap.put("operateUserId", operateUserId);
                        valueMap.put("operateRoleId", task.getOperateRoleId());
                        valueMap.put("mainId", main.getId());
                        valueMap.put("aiid", task.getId());
                        valueMap.put("piid", task.getProcessId());
                        valueMap.put("operateTime", operate_time);
                        valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
                        valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
                        valueMap.put("operateType", "4");
                        valueMap.put("hasNextTaskFlag", "true");

                        if ("SecondExcuteHumTask".equals(task.getTaskName())) {
                            valueMap.put("phaseId", "FirstExcuteHumTask");
                            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                            String sql = "select * from complaint_link link where link.mainid='" +
                                    sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " +
                                    "order by link.operatetime desc ";
                            System.out.println("驳回操作查询T1操作人sql====" + sql);
                            List linkList = services.getSheetAccessoriesList(sql);
                            String firstOperateroleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("firstOperateroleid"));
                            if ((linkList != null) && (linkList.size() > 0)) {
                                Map linkMap = (Map) linkList.get(0);
                                String pretaskid = StaticMethod.nullObject2String(linkMap.get("aiid"));
                                if (!"".equals(pretaskid)) {
                                    valueMap.put("dealPerformer", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
                                    valueMap.put("dealPerformerLeader", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
                                    valueMap.put("dealPerformerType", "subrole");
                                    valueMap.put("toOrgRoleId", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
                                } else {
                                    valueMap.put("dealPerformer", firstOperateroleid);
                                    valueMap.put("dealPerformerLeader", firstOperateroleid);
                                    valueMap.put("dealPerformerType", "subrole");
                                    valueMap.put("toOrgRoleId", firstOperateroleid);
                                }
                                valueMap.put("nodeAcceptLimit", StaticMethod.nullObject2String(linkMap.get("nodeAcceptLimit")));
                                valueMap.put("nodeCompleteLimit", StaticMethod.nullObject2String(linkMap.get("nodeCompleteLimit")));
                            }
                        }
                        BaseLink linkObject = (BaseLink) linkservice.getLinkObject().getClass().newInstance();
                        String linkId = UUIDHexGenerator.getInstance().getID();
                        linkObject.setId(linkId);
                        if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm")) {
                            IWfInterfaceInfoManager wfInterfaceInfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
                                    .getInstance().getBean("iWfInterfaceInfoManager");
                            try {
                                wfInterfaceInfoManager.saveWfInterfaceInto("iComplaintMainManager",
                                        "iComplaintLinkManager", sheetKey, linkId, "withdrawWorkSheet",
                                        "withdrawWorkSheet", "0");
                                BocoLog.info(this, "invokeWfInterface end!!!!!!!!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        sheetMap1.put("main", main);
                        sheetMap1.put("link", linkObject);
                        sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
                        columnMap.put("selfSheet", sheetMap1);
                        valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
                        sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
                        result = "sheetNo=0;Errlist=";
                    } else {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单驳回操作！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=工单驳回接口传入参数不正确,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单驳回接口没有传入opDetail参数,请查证！";
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;Errlist=工单驳回接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }


    public String notifyWorkSheet(String opDetail) {

        String result = "";
        try {
            IComplaintMainManager mainservice = (IComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
            IComplaintTaskManager taskservice = (IComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
            IComplaintLinkManager linkservice = (IComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            String nodeName = "notifyWorkSheet";
            System.out.println("CompiainSheetService.dealSheet内对应的nodeName======" + nodeName);
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                String sheetId = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
                String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
                String operate_time = StaticMethod.nullObject2String(sheetMap.get("notify_time"));
                String isDeferReploy = StaticMethod.nullObject2String(sheetMap.get("isDeferReploy"));
                String remark = StaticMethod.nullObject2String(sheetMap.get("opDesc"));
                ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                TawSystemUser user = userMgr.getUserByuserid(operateUserId);
                if ((sheetId != null) && (!"".equals(sheetId))) {
                    ComplaintMain baseMain = (ComplaintMain) mainservice.getMainBySheetId(sheetId);
                    if (null != baseMain || !"".equals(baseMain.getSheetId())) {
                        String mainid = baseMain.getId();
                        List tasks = taskservice.getTasksBySheetKey(mainid, "'SecondExcuteHumTask'");
                        if (tasks.size() > 0) {
                            ComplaintTask baseTask = (ComplaintTask) tasks.get(0);
                            Date now = new Date();
                            Calendar ca = Calendar.getInstance();
                            int year = ca.get(Calendar.YEAR);// 获取年份
                            int month = ca.get(Calendar.MONTH);// 获取月份
                            int day = ca.get(Calendar.DATE);// 获取日
                            ComplaintLink newLink = (ComplaintLink) linkservice.getLinkObject().getClass().newInstance();
                            // 操作人Id
                            newLink.setRemark(remark);
                            newLink.setOperateType(Integer.valueOf(9));
                            newLink.setOperateTime(now);


                            // 构造基础的link信息
                            newLink.setId(UUIDHexGenerator.getInstance().getID());
                            newLink.setMainId(baseMain.getId());
                            newLink.setOperateType(Integer.valueOf(9));
                            newLink.setOperateTime(now);
                            newLink.setOperateUserId(operateUserId);
                            newLink.setOperateRoleId(baseTask.getOperateRoleId());

                            newLink.setPreLinkId(baseTask.getPreLinkId());
                            newLink.setPiid(baseMain.getPiid());
                            newLink.setAiid(baseTask.getId());
                            newLink.setActiveTemplateId("SecondExcuteHumTask");
                            newLink.setNodeAcceptLimit(baseMain.getSheetAcceptLimit());
                            newLink.setNodeCompleteLimit(baseMain.getSheetCompleteLimit());
                            newLink.setCorrelationKey(baseMain.getCorrelationKey());
                            newLink.setOperaterContact(user.getMobile());
                            newLink.setOperateDeptId(user.getDeptid());
                            newLink.setOperateYear(year);
                            newLink.setOperateMonth(month);
                            newLink.setOperateDay(day);
                            newLink.setIsDeferReploy(isDeferReploy);
                            newLink.setToOrgRoleId(baseMain.getSendRoleId());
                            linkservice.addLink(newLink);
                            if (StaticMethod.nullObject2String(baseMain.getMainInterfaceSheetType()).equalsIgnoreCase("crm")) {
                                IWfInterfaceInfoManager wfInterfaceInfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
                                        .getInstance().getBean("iWfInterfaceInfoManager");
                                try {
                                    wfInterfaceInfoManager.saveWfInterfaceInto("iComplaintMainManager",
                                            "iComplaintLinkManager", mainid, newLink.getId(), "notifyWorkSheet",
                                            "notifyWorkSheet", "0");
                                    BocoLog.info(this, "invokeWfInterface end!!!!!!!!");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            result = "sheetNo==0;Errlist=";
                            System.out.println("sheetid is " + sheetId + " 阶段回复成功!");
                        } else {
                            result = "Status=-1;Errlist=工单不在T2环节，无法进行阶段回复,请查证";
                            return result;
                        }
                    } else {
                        result = "Status=-1;Errlist=工单阶段回复接口没有对应的EOMS工单,请查证！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=工单阶段回复接口没有传入sheetid参数,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单阶段回复接口没有传入opDesc参数,请查证！";
                return result;
            }
            System.out.println("-----工单阶段回复服务调用结束----------");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;Errlist=工单阶段回复接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }


    public static ComplaintLink createCheckingLink(ComplaintMain mainrule, Date operateTime, String prelinkId, String subroleid)
            throws Exception {
        String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoUser"));
        String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoSubRole"));
        System.out.println("lizhi:autoUser=" + autoUser + "autoSubRole=" + autoSubRole);
        IComplaintLinkManager linkservice = (IComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(operateTime);
        calendar.add(13, 30);
        ComplaintLink T1link61 = (ComplaintLink) linkservice.getLinkObject().getClass().newInstance();
        T1link61.setId(UUIDHexGenerator.getInstance().getID());
        T1link61.setOperateType(new Integer("61"));
        T1link61.setActiveTemplateId("CheckingHumTask");
        T1link61.setOperateTime(calendar.getTime());
        T1link61.setOperateDay(calendar.get(5));
        T1link61.setOperateMonth(calendar.get(2) + 1);
        T1link61.setOperateYear(calendar.get(1));
        T1link61.setMainId(StaticMethod.nullObject2String(mainrule.getId()));
        T1link61.setToOrgRoleId(subroleid);
        T1link61.setPreLinkId(prelinkId);
        T1link61.setNodeAccessories("");
        T1link61.setToOrgType(new Integer(0));
        T1link61.setCompleteFlag(new Integer(0));
        T1link61.setOperateUserId(StaticMethod.nullObject2String(autoUser));
        T1link61.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
        T1link61.setOperateDeptId("12201");
        T1link61.setTemplateFlag(0);
        linkservice.addLink(T1link61);
        calendar.add(13, 30);
        ComplaintLink T1link = (ComplaintLink) linkservice.getLinkObject().getClass().newInstance();
        T1link.setId(UUIDHexGenerator.getInstance().getID());
        T1link.setMainId(StaticMethod.nullObject2String(mainrule.getId()));
        T1link.setOperateType(new Integer(56));
        T1link.setOperateTime(calendar.getTime());
        T1link.setOperateDay(calendar.get(5));
        T1link.setOperateMonth(calendar.get(2) + 1);
        T1link.setOperateYear(calendar.get(1));
        T1link.setAcceptFlag(new Integer(0));
        T1link.setPreLinkId(prelinkId);
        T1link.setActiveTemplateId("CheckingHumTask");
        T1link.setToOrgType(new Integer(0));
        T1link.setCompleteFlag(new Integer(0));
        T1link.setOperateUserId(StaticMethod.nullObject2String(autoUser));
        T1link.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
        T1link.setOperateDeptId("12201");
        String correlationKey = UUIDHexGenerator.getInstance().getID();
        T1link.setCorrelationKey(correlationKey);
        T1link.setTemplateFlag(0);
        T1link.setPiid(StaticMethod.nullObject2String(mainrule.getPiid()));
        T1link.setToOrgRoleId(subroleid);
        T1link.setNodeAcceptLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetAcceptLimit()));
        T1link.setNodeCompleteLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetCompleteLimit()));
        T1link.setLinkCheckResult("1030101");
        T1link.setLinkCheckIdea("ok");
        linkservice.addLink(T1link);
        return T1link;
    }

    public static void createCheckingTask(ComplaintMain mainrule, String preLinkId)
            throws Exception {
        String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoUser"));
        String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoSubRole"));
        IComplaintTaskManager taskservice = (IComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
        ComplaintTask T1Task = (ComplaintTask) taskservice.getTaskModelObject().getClass().newInstance();
        try {
            T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        T1Task.setTaskName("CheckingHumTask");
        T1Task.setTaskDisplayName("质检");
        T1Task.setFlowName("ComplaintProcess");
        T1Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.getSendTime()));
        T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.getId()));
        T1Task.setTaskStatus("5");
        T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.getSheetId()));
        T1Task.setTitle(StaticMethod.nullObject2String(mainrule.getTitle()));
        T1Task.setOperateType("subrole");
        T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
        T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
        T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
        T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
        T1Task.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
        T1Task.setTaskOwner(StaticMethod.nullObject2String(autoUser));
        T1Task.setIfWaitForSubTask("false");
        T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
        T1Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
        T1Task.setAcceptTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetAcceptLimit()));
        T1Task.setCompleteTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetCompleteLimit()));
        taskservice.addTask(T1Task);
    }


}