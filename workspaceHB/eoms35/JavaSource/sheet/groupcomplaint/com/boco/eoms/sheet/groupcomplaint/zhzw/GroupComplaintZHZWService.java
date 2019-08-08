package com.boco.eoms.sheet.groupcomplaint.zhzw;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintLinkManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintTaskManager;
import com.boco.eoms.sheet.groupcomplaint.task.impl.GroupComplaintTask;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.interfaceBase.util.XmlUtil;
import com.boco.eoms.util.InterfaceUtil;

/**
 * EOMS集客投诉工单与有线业务智慧装维支撑系统接口
 * 流程英文名：GroupComplaint
 * 流程图：groupcomplaint-config.xml
 * 在server-config.wsdd中添加
 *
 * @author LiKuan
 * 2019-1-18
 */
public class GroupComplaintZHZWService {


    /**
     * 1.4工单驳回接口
     * 参照的HandGroupComplaint2EomsService的rejectSheet
     * ！Sheet_id	工单编号	举例：HB-051-130816-1218
     * ！System_code	请求来源	zhzw
     * Operate_task	操作类型	7驳回
     * ！Operate_userid	处理人账号	当前处理人
     * ！Reject_time	驳回时间	举例：2013-08-16 13:15:26
     * Reject_desc	驳回原因
     */
    public String rejectSheet(String opDetail) {
        String errorLog = "";
        try {
            HashMap sheetMap = new HashMap();
            if (opDetail == null || "".equals(opDetail)) {
                errorLog += "lk,error1，工单驳回接口rejectSheet的入参为空！";
                return this.publicReturnStr(errorLog);
            }

            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/GroupComplaintZHZWService.xml");
            String nodeName = "rejectSheet";
            this.sysoutNodeName("rejectSheet", nodeName);
            Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

            String System_code = StaticMethod.nullObject2String(sheetMap.get("System_code"));//请求来源	zhzw
            if (!"zhzw".equals(System_code)) {
                errorLog += "lk,error2，工单驳回接口rejectSheet的入参的System_code的值不正确！";
                return this.publicReturnStr(errorLog);
            }

            String Reject_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));//驳回时间	举例：2013-08-16 13:15:26
            if (Reject_time == null || "".equals(Reject_time)) {
                Reject_time = StaticMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
            }


            ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            String Operate_userid = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("interfaceType.operateUserId");//处理人账号
            TawSystemUser user = userMgr.getUserByuserid(Operate_userid);
            if (user == null) {
                errorLog += "lk,error3，工单驳回接口rejectSheet的入参的Operate_userid为" + Operate_userid + "，但在EOMS中未查询到！";
                return this.publicReturnStr(errorLog);
            }

            String Sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));//工单编号	举例：HB-051-130816-1218
            if (Sheet_id == null || "".equals(Sheet_id)) {
                errorLog += "lk,error4，工单驳回接口rejectSheet的入参的Sheet_id为空！";
                return this.publicReturnStr(errorLog);
            }

            IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
            GroupComplaintMain main = (GroupComplaintMain) mainservice.getMainBySheetId(Sheet_id);
            try {
                System.out.println("lk,根据Sheet_id" + Sheet_id + "查询main表的ID为：" + main.getId());
            } catch (Exception e) {
                errorLog += "lk,error5，根据Sheet_id" + Sheet_id + "未查询到main表记录！";
                return this.publicReturnStr(errorLog);
            }
            int status = StaticMethod.nullObject2int(main.getStatus());
            if (status != 0) {
                errorLog += "lk,error6，工单流水号为" + Sheet_id + "的工单未处于运行状态,无法进行工单驳回操作！";
                return this.publicReturnStr(errorLog);
            }


            String sheetKey = StaticMethod.nullObject2String(main.getId());
            String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask'";
            IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
            List taskList = taskservice.getTasksByCondition(condition);
            if (taskList == null || taskList.size() == 0) {
                String condition1 = condition.replace("taskstatus ='2'", "taskstatus ='8'");
                List taskList1 = taskservice.getTasksByCondition(condition1);
                if (taskList1 != null && taskList1.size() > 0) {//已接单
                    errorLog += "lk,error7，工单流水号为" + Sheet_id + "的工单已接单,无法进行工单驳回操作！";
                } else {//未接单
                    errorLog += "lk,error8，工单流水号为" + Sheet_id + "的工单未接单,可以进行工单驳回操作！";
                }
                return this.publicReturnStr(errorLog);
            }

            GroupComplaintTask task = (GroupComplaintTask) taskList.get(0);
            if (!"SecondExcuteHumTask".equals(task.getTaskName())) {//此工单处于‘二级处理’环节
                errorLog += "lk,error9，工单流水号为" + Sheet_id + "的工单不处于二级处理环节！";
                return this.publicReturnStr(errorLog);
            }

            String sql = "select * from GroupComplaint_link link where link.mainid='" +
                    sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " +
                    "order by link.operatetime desc ";
            System.out.println("lk,工单驳回操作查询T1操作人sql====" + sql);
            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            List linkList = services.getSheetAccessoriesList(sql);
            if (linkList == null || linkList.size() == 0) {
                errorLog += "lk,error10，工单流水号为" + Sheet_id + "的工单未查询到一级处理环节的操作人！";
                return this.publicReturnStr(errorLog);
            }
            Map linkMap = (Map) linkList.get(0);

            String subroleid = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("subrole.t1subroleid");

            valueMap.put("operateDeptId", user.getDeptid());
            valueMap.put("operaterContact", user.getMobile());

            valueMap.put("operateUserId", Operate_userid);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("aiid", task.getId());
            valueMap.put("piid", task.getProcessId());
            valueMap.put("operateTime", Reject_time);
            valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
            valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
            valueMap.put("operateType", "4");
            valueMap.put("hasNextTaskFlag", "true");

            valueMap.put("phaseId", "FirstExcuteHumTask");

            valueMap.put("dealPerformer", subroleid);
            valueMap.put("dealPerformerLeader", subroleid);
            valueMap.put("dealPerformerType", "subrole");

            valueMap.put("toOrgRoleId", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
            valueMap.put("nodeAcceptLimit", StaticMethod.nullObject2String(linkMap.get("nodeAcceptLimit")));
            valueMap.put("nodeCompleteLimit", StaticMethod.nullObject2String(linkMap.get("nodeCompleteLimit")));

            valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));

            IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
            HashMap sheetMap1 = new HashMap();
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");

            HashMap columnMap = new HashMap();
            columnMap.put("selfSheet", sheetMap1);

            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            sm.dealSheet(sheetKey, valueMap, columnMap, Operate_userid, taskservice);

        } catch (Exception e) {
            e.printStackTrace();
            errorLog += "lk,error11，rejectSheet方法报错" + e.getMessage();
            return this.publicReturnStr(errorLog);
        }
        return this.publicReturnStr(errorLog);
    }

    /**
     * 1.2接单接口（确认受理）接口
     * 参照的HandGroupComplaint2EomsService的acceptSheet
     * Sheet_id	工单编号	EOMS工单号
     * System_code	请求来源	zhzw
     * Sheet_status	工单状态	3：已接单未处理
     * Operate_userid	处理人账号	当前处理人
     * Accept_time	故障受理时间	当前操作时间
     */
    public String acceptSheet(String opDetail) {
        String errorLog = "";
        try {
            HashMap sheetMap = new HashMap();
            if (opDetail == null || "".equals(opDetail)) {
                errorLog += "lk,error12，接单接口acceptSheet的入参为空！";
                return this.publicReturnStr(errorLog);
            }

            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/GroupComplaintZHZWService.xml");
            String nodeName = "acceptSheet";
            this.sysoutNodeName("acceptSheet", nodeName);
            Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

            String System_code = StaticMethod.nullObject2String(sheetMap.get("System_code"));//请求来源	zhzw
            if (!"zhzw".equals(System_code)) {
                errorLog += "lk,error13，接单接口acceptSheet的入参的System_code的值不正确！";
                return this.publicReturnStr(errorLog);
            }

            String Accept_time = StaticMethod.nullObject2String(sheetMap.get("Accept_time"));//故障受理时间	当前操作时间
            if (Accept_time == null || "".equals(Accept_time)) {
                Accept_time = StaticMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
            }


            ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            String Operate_userid = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("interfaceType.operateUserId");//处理人账号
            TawSystemUser user = userMgr.getUserByuserid(Operate_userid);
            if (user == null) {
                errorLog += "lk,error14，接单接口acceptSheet的入参的Operate_userid为" + Operate_userid + "，但在EOMS中未查询到！";
                return this.publicReturnStr(errorLog);
            }

            String Sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));//工单编号	举例：HB-051-130816-1218
            if (Sheet_id == null || "".equals(Sheet_id)) {
                errorLog += "lk,error15，接单接口acceptSheet的入参的Sheet_id为空！";
                return this.publicReturnStr(errorLog);
            }

            IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
            GroupComplaintMain main = (GroupComplaintMain) mainservice.getMainBySheetId(Sheet_id);
            try {
                System.out.println("lk,根据Sheet_id" + Sheet_id + "查询main表的ID为：" + main.getId());
            } catch (Exception e) {
                errorLog += "lk,error16，根据Sheet_id" + Sheet_id + "未查询到main表记录！";
                return this.publicReturnStr(errorLog);
            }
            int status = StaticMethod.nullObject2int(main.getStatus());
            if (status != 0) {
                errorLog += "lk,error17，工单流水号为" + Sheet_id + "的工单未处于运行状态,无法进行接单（确认受理）操作！";
                return this.publicReturnStr(errorLog);
            }


            String sheetKey = StaticMethod.nullObject2String(main.getId());
            String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='SecondExcuteHumTask'";
            IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
            List taskList = taskservice.getTasksByCondition(condition);
            if (taskList == null || taskList.size() == 0) {
                String condition1 = condition.replace("taskstatus ='2'", "taskstatus ='8'");
                List taskList1 = taskservice.getTasksByCondition(condition1);
                if (taskList1 != null && taskList1.size() > 0) {//已接单
                    errorLog += "lk,error18，工单流水号为" + Sheet_id + "的工单已接单（已确认受理）！";
                } else {//未接单
                    errorLog += "lk,error19，工单流水号为" + Sheet_id + "的工单未接单（未确认受理）！";
                }
                return this.publicReturnStr(errorLog);
            }

            GroupComplaintTask task = (GroupComplaintTask) taskList.get(0);

            valueMap.put("operateDeptId", user.getDeptid());
            valueMap.put("operaterContact", user.getMobile());

            valueMap.put("operateUserId", Operate_userid);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("aiid", task.getId());
            valueMap.put("operateTime", Accept_time);
            valueMap.put("toOrgRoleId", task.getOperateRoleId());


            IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
            HashMap sheetMap1 = new HashMap();
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");

            HashMap columnMap = new HashMap();
            columnMap.put("selfSheet", sheetMap1);

            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            sm.claimTask(task.getId(), valueMap, columnMap, Operate_userid);

        } catch (Exception e) {
            e.printStackTrace();
            errorLog += "lk,error20，acceptSheet方法报错" + e.getMessage();
            return this.publicReturnStr(errorLog);
        }
        return this.publicReturnStr(errorLog);
    }

    /**
     * 1.3回单接口(服务端)
     * 参照的参照的HandGroupComplaint2EomsService的dealSheet
     * Sheet_id	工单编号	EOMS工单编号
     * System_code	请求来源	zhzw
     * Operate_userid	处理人账号	当前处理人
     * Operate_time	处理完成时间	当前操作时间
     * Operate_contact	操作人联系方式
     * Linkman	联系人
     * Linkman_phone	联系人电话
     * Complaint_nature	投诉性质	有理由/无理由 101100601/101100602
     * Is_answered	是否答复客户	是/否 101105001/101105002
     * Is_right	工单派单是否准确	是/否 101105001/101105002
     * 故障处理范围	本地/全网/本地+全网 101100301/101100302/101100303
     * Eliminate_time	问题消除时间	2017-04-19 14:49:08
     * Dispose_result	处理结果	解决/未解决 101100701/101100702
     * Reason_solution	问题原因
     * Cause_classification	故障分类	传输设备故障/局端设备原因/客户端我方设备原因/客户自身设备原因 101031001/101031002/101031003/101031004/
     * Fault_text	故障原因	客户/线路/局端设备/传输/割接101031801/101031802/101031803/101031804
     * SLAcode	SLA保障等级	普通/A/AA/AAA
     * City	地市	武汉市/宜昌市/恩施州/荆州市/荆门市/黄石市/鄂州市/咸宁市/黄冈市/襄阳市/随州市/十堰市/孝感市/天门市/潜江市/仙桃市/ 字典值
     * County	区县	根据地市菜单选择. 字典值
     * if Line_num	是否存在产品实例标识
     * Line_num	产品实例标识
     * Solution	解决措施
     * Sign_information	人员签到信息	人员位置的经度和纬度2个值
     */
    public String dealSheet(String opDetail) {
        String errorLog = "";
        try {
            HashMap sheetMap = new HashMap();
            if (opDetail == null || "".equals(opDetail)) {
                errorLog += "lk,error21，回单接口dealSheet的入参为空！";
                return this.publicReturnStr(errorLog);
            }

            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/GroupComplaintZHZWService.xml");
            String nodeName = "dealSheet";
            this.sysoutNodeName("dealSheet", nodeName);
            Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

            String System_code = StaticMethod.nullObject2String(sheetMap.get("System_code"));//请求来源	zhzw
            if (!"zhzw".equals(System_code)) {
                errorLog += "lk,error22，回单接口dealSheet的入参的System_code的值不正确！";
                return this.publicReturnStr(errorLog);
            }

            String Operate_time = StaticMethod.nullObject2String(sheetMap.get("Operate_time"));//处理完成时间	当前操作时间
            if (Operate_time == null || "".equals(Operate_time)) {
                Operate_time = StaticMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
            }


            ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            String Operate_userid = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("interfaceType.operateUserId");//处理人账号
            TawSystemUser user = userMgr.getUserByuserid(Operate_userid);
            if (user == null) {
                errorLog += "lk,error23，回单接口dealSheet的入参的Operate_userid为" + Operate_userid + "，但在EOMS中未查询到！";
                return this.publicReturnStr(errorLog);
            }

            String Sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));//工单编号	举例：HB-051-130816-1218
            if (Sheet_id == null || "".equals(Sheet_id)) {
                errorLog += "lk,error24，回单接口dealSheet的入参的Sheet_id为空！";
                return this.publicReturnStr(errorLog);
            }

            IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
            GroupComplaintMain main = (GroupComplaintMain) mainservice.getMainBySheetId(Sheet_id);
            try {
                System.out.println("lk,根据Sheet_id" + Sheet_id + "查询main表的ID为：" + main.getId());
            } catch (Exception e) {
                errorLog += "lk,error25，根据Sheet_id" + Sheet_id + "未查询到main表记录！";
                return this.publicReturnStr(errorLog);
            }
            int status = StaticMethod.nullObject2int(main.getStatus());
            if (status != 0) {
                errorLog += "lk,error26，工单流水号为" + Sheet_id + "的工单未处于运行状态,无法进行回单操作！";
                return this.publicReturnStr(errorLog);
            }


            String sheetKey = StaticMethod.nullObject2String(main.getId());
            String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='SecondExcuteHumTask'";
            IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
            List taskList = taskservice.getTasksByCondition(condition);
            if (taskList == null || taskList.size() == 0) {
                String condition1 = condition.replace("taskstatus ='8'", "taskstatus ='2'");
                List taskList1 = taskservice.getTasksByCondition(condition1);
                if (taskList1 != null && taskList1.size() > 0) {//未接单
                    errorLog += "lk,error27，工单流水号为" + Sheet_id + "的工单未接单,先接单，再进行回单操作！";
                } else {//已接单
                    errorLog += "lk,error28，工单流水号为" + Sheet_id + "的工单已接单,直接进行回单操作！";
                }
                return this.publicReturnStr(errorLog);
            }

            GroupComplaintTask task = (GroupComplaintTask) taskList.get(0);


            valueMap.put("operateDeptId", user.getDeptid());
            valueMap.put("operaterContact", user.getMobile());

            valueMap.put("operateUserId", Operate_userid);
            valueMap.put("operateRoleId", task.getOperateRoleId());
            valueMap.put("mainId", main.getId());
            valueMap.put("operateTime", Operate_time);
            valueMap.put("aiid", task.getId());

            String subroleid = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("subrole.zjsubroleid");

            valueMap.put("dealPerformer", subroleid);
            valueMap.put("dealPerformerType", "subrole");
            valueMap.put("dealPerformerLeader", subroleid);

            String linkAffectedAreas = StaticMethod.nullObject2String(valueMap.get("affectedAreas"));
            main.setAffectedAreas(linkAffectedAreas);
            Map sheetMainMap = SheetBeanUtils.bean2Map(main);
            valueMap.putAll(sheetMainMap);

            IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
            HashMap sheetMap1 = new HashMap();
            sheetMap1.put("main", main);
            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");

            HashMap columnMap = new HashMap();
            columnMap.put("selfSheet", sheetMap1);

            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            sm.dealSheet(sheetKey, valueMap, columnMap, Operate_userid, taskservice);

        } catch (Exception e) {
            e.printStackTrace();
            errorLog += "lk,error29，dealSheet方法报错" + e.getMessage();
            return this.publicReturnStr(errorLog);
        }
        return this.publicReturnStr(errorLog);
    }

    /**
     * 1.8阶段回复接口(服务端)
     * 参照的WidenComplainSheetService的replySheet
     * Sheet_id	工单编号	EOMS工单号
     * System_code	请求来源	zhzw
     * remark	关联阶段回复描述	中文描述
     */
    public String replySheet(String opDetail) {
        String errorLog = "";
        try {
            HashMap sheetMap = new HashMap();
            if (opDetail == null || "".equals(opDetail)) {
                errorLog += "lk,error30，阶段回复接口replySheet的入参为空！";
                return this.publicReturnStr(errorLog);
            }

            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/GroupComplaintZHZWService.xml");
            String nodeName = "replySheet";
            this.sysoutNodeName("replySheet", nodeName);
            Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

            String System_code = StaticMethod.nullObject2String(sheetMap.get("System_code"));//请求来源	zhzw
            if (!"zhzw".equals(System_code)) {
                errorLog += "lk,error31，阶段回复接口replySheet的入参的System_code的值不正确！";
                return this.publicReturnStr(errorLog);
            }

            ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            String Operate_userid = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("interfaceType.operateUserId");//处理人账号
            TawSystemUser user = userMgr.getUserByuserid(Operate_userid);
            if (user == null) {
                errorLog += "lk,error32，阶段回复接口replySheet的Operate_userid为" + Operate_userid + "，但在EOMS中未查询到！";
                return this.publicReturnStr(errorLog);
            }

            String Sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));//工单编号	举例：HB-051-130816-1218
            if (Sheet_id == null || "".equals(Sheet_id)) {
                errorLog += "lk,error33，阶段回复接口replySheet的入参的Sheet_id为空！";
                return this.publicReturnStr(errorLog);
            }

            IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
            GroupComplaintMain main = (GroupComplaintMain) mainservice.getMainBySheetId(Sheet_id);
            try {
                System.out.println("lk,根据Sheet_id" + Sheet_id + "查询main表的ID为：" + main.getId());
            } catch (Exception e) {
                errorLog += "lk,error34，根据Sheet_id" + Sheet_id + "未查询到main表记录！";
                return this.publicReturnStr(errorLog);
            }
            int status = StaticMethod.nullObject2int(main.getStatus());
            if (status != 0) {
                errorLog += "lk,error35，工单流水号为" + Sheet_id + "的工单未处于运行状态,无法进行阶段回复接口操作！";
                return this.publicReturnStr(errorLog);
            }


            String sheetKey = StaticMethod.nullObject2String(main.getId());
            String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='SecondExcuteHumTask'";
            IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
            List taskList = taskservice.getTasksByCondition(condition);
            if (taskList == null || taskList.size() == 0) {
                String condition1 = condition.replace("taskstatus ='8'", "taskstatus ='2'");
                List taskList1 = taskservice.getTasksByCondition(condition1);
                if (taskList1 != null && taskList1.size() > 0) {//未接单
                    errorLog += "lk,error36，工单流水号为" + Sheet_id + "的工单未接单,先接单，再进行阶段回复接口操作！";
                } else {//已接单
                    errorLog += "lk,error37，工单流水号为" + Sheet_id + "的工单已接单,直接进行阶段回复接口操作！";
                }
                return this.publicReturnStr(errorLog);
            }
            GroupComplaintTask task = (GroupComplaintTask) taskList.get(0);


//			调用客服接口start
            String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iGroupComplaintMainManager");
            IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager) ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
            WfInterfaceInfo info = new WfInterfaceInfo();

            info.setInterfaceType("notifyWorkSheet");
            info.setSheetKey(sheetKey);
            info.setLinkId(task.getId());
            info.setIsSended("0");
            info.setMainBeanId("iGroupComplaintMainManager");
            info.setLinkBeanId("iGroupComplaintTaskManager");//此处传的是task
            info.setMethodType("notifyWorkSheet");

            GroupComplaintLink link = new GroupComplaintLink();
            link.setActiveTemplateId("SecondExcuteHumTask");
            link.setOperateType(new Integer(9));
            link.setOperateUserId(Operate_userid);
            System.out.println("lk,diaoyongkefu==replySheet=" + main.getSheetId());
            try {
                boolean returnType = operateMgr.sendData(info, link);
                if (!returnType) {
                    errorLog += "lk,error38，阶段回复接口replySheet中调用客服接口失败！";
                    return this.publicReturnStr(errorLog);
                }
            } catch (Exception e) {
                errorLog += "lk,error39，阶段回复接口replySheet中调用客服接口报错,详细内容为" + e.getMessage();
                return this.publicReturnStr(errorLog);
            }
//			调用客服接口end

//			开始
            IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
            GroupComplaintLink linkbean = (GroupComplaintLink) linkservice.getLinkObject().getClass().newInstance();

            Calendar calendar = Calendar.getInstance();
            SheetBeanUtils.populateEngineMap2Bean(linkbean, valueMap);


            linkbean.setOperateDeptId(user.getDeptid());
            linkbean.setOperaterContact(user.getMobile());
            linkbean.setOperateUserId(user.getUserid());


            linkbean.setActiveTemplateId("reply");
            linkbean.setId(UUIDHexGenerator.getInstance().getID());
            linkbean.setMainId(main.getId());
            linkbean.setOperateType(new Integer(9));
            linkbean.setOperateTime(new Date());
            linkbean.setToOrgRoleId(main.getSendRoleId());
            linkbean.setOperateDay(calendar.get(Calendar.DATE));
            linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
            linkbean.setOperateYear(calendar.get(Calendar.YEAR));
            // 保存task数据
            GroupComplaintTask task1 = (GroupComplaintTask) taskservice.getTaskModelObject().getClass().newInstance();
            task1.setId(UUIDHexGenerator.getInstance().getID());
            task1.setTaskName("reply");
            task1.setTaskDisplayName("阶段回复");
            task1.setOperateRoleId(main.getSendRoleId());
            task1.setTaskOwner(main.getSendRoleId());
            task1.setFlowName("GroupComplaint");
            task1.setOperateType("subrole");
            this.newSaveTask(main, linkbean, task1);
            linkbean.setAiid(task1.getId());
            linkservice.addLink(linkbean);
            //结束

        } catch (Exception e) {
            e.printStackTrace();
            errorLog += "lk,error40，rejectSheet方法报错" + e.getMessage();
            return this.publicReturnStr(errorLog);
        }
        return this.publicReturnStr(errorLog);
    }
//****************************************************************************分割线，上端为服务端，下端为  客户端

    /**
     * 1.1投诉派单接口（T1移交T2）
     */
    public void sendSheet(Map map) {
        try {
            int type = 2;
            String opDetail = this.getOpDetail(map, type);
            String eomsSheetid = StaticMethod.nullObject2String(map.get("sheetId"));
            String serialNo = StaticMethod.nullObject2String(map.get("parentCorrelation"));
            System.out.println("lk,sendSheet-serialNo=" + serialNo);
            System.out.println("lk,sendSheet-eomsSheetid=" + eomsSheetid);
            this.publicClient(opDetail, type, serialNo, eomsSheetid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lk,error41，调用智慧装维投诉派单接口（T1移交T2）sendSheet报错" + e.getMessage());
        }
    }

    /**
     * 1.6质检通过/不通过接口
     */
    public void inspectionSheet(Map map) {
        try {
            int type = 5;
            String opDetail = this.getOpDetail(map, type);
            String eomsSheetid = StaticMethod.nullObject2String(map.get("sheetId"));
            String serialNo = StaticMethod.nullObject2String(map.get("PARENTCORRELATION"));
            this.publicClient(opDetail, type, serialNo, eomsSheetid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lk,error42，调用质检inspectionSheet报错" + e.getMessage());
        }
    }

    /**
     * 1.7催单接口（阶段通知）
     */
    public void informSheet(String sheetType, String serviceType, String serialNo, String eomsSheetid, String serSupplier, String serCaller, String callerPwd, String callTime, String attachRef, String opPerson, String opCorp, String opDepart, String opContact, String opTime, String opDetail) {
        try {
            EomsServiceLocator service = new EomsServiceLocator();
            Eoms2IomService bing = service.getEoms2IomServicePort();
            String str = bing.informSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
            System.out.println("lk,调用智慧装维催单接口（阶段通知）informSheet的返回值str=" + str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lk,error43，调用智慧装维催单接口（阶段通知）informSheet报错" + e.getMessage());
        }
    }


    /**
     * 1.5归档接口
     */
    public void endSheet(String sheetType, String serviceType, String serialNo, String eomsSheetid, String serSupplier, String serCaller, String callerPwd, String callTime, String attachRef, String opPerson, String opCorp, String opDepart, String opContact, String opTime, String opDetail) {
        try {
            EomsServiceLocator service = new EomsServiceLocator();
            Eoms2IomService bing = service.getEoms2IomServicePort();
            String str = bing.endSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
            System.out.println("lk,调用智慧装维归档接口（阶段通知）endSheet的返回值str=" + str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lk,error44，调用智慧装维归档接口endSheet报错" + e.getMessage());
        }
    }

    /**
     * 1.9重派接口
     */
    public void repeatSendSheet(Map map) {
        try {
            int type = 4;
            String opDetail = this.getOpDetail(map, type);
            String eomsSheetid = StaticMethod.nullObject2String(map.get("sheetId"));
            String serialNo = StaticMethod.nullObject2String(map.get("PARENTCORRELATION"));
            this.publicClient(opDetail, type, serialNo, eomsSheetid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lk,error45，调用智慧装维投诉派单接口（T1移交T2）sendSheet报错" + e.getMessage());
        }
    }

    /**
     * sheetType	String(10)	工单类别（集客投诉为57）
     * serviceType	String(10)	无意义。 1
     * serialNo	String(40)	CRM工单编号,main.PARENTCORRELATION
     * eomsSheetid	String(50)	EOMS工单号
     */
    public void publicClient(String opDetail, int type, String serialNo, String eomsSheetid) {
        System.out.println("lk,publicClient-serialNo=" + serialNo);
        System.out.println("lk,publicClient-eomsSheetid=" + eomsSheetid);

        String methodName = this.getMethodNameByType(type);
        try {
            EomsServiceLocator service = new EomsServiceLocator();
            Eoms2IomService bing = service.getEoms2IomServicePort();


            String sheetType = "57";
            String serviceType = "1";
            String serSupplier = "";
            String serCaller = "";
            String callerPwd = "";
            String callTime = "";
            String attachRef = "";
            String opPerson = "";
            String opCorp = "";
            String opDepart = "";
            String opContact = "";
            String opTime = "";

            String str = "";
            switch (type) {
                case 1:
                    str = bing.endSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
                    break;

                case 2:
                    str = bing.sendSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
                    break;

                case 3:
                    str = bing.informSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
                    break;

                case 4:
                    str = bing.repeatSendSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
                    break;

                case 5:
                    str = bing.inspectionSheet(sheetType, serviceType, serialNo, eomsSheetid, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
                    break;
            }

            System.out.println("lk,调用智慧装维客户端接口" + methodName + "的返回值：" + str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lk,error46，调用智慧装维客户端接口" + methodName + "报错" + e.getMessage());
        }
    }

    public String getMethodNameByType(int type) {
        String methodName = "";
        switch (type) {
            case 1:
                methodName = "endSheet";
                break;

            case 2:
                methodName = "sendSheet";
                break;

            case 3:
                methodName = "informSheet";
                break;

            case 4:
                methodName = "repeatSendSheet";
                break;

            case 5:
                methodName = "inspectionSheet";
                break;
        }

        return methodName;
    }

    public String getOpDetail(Map map, int type) {
        String opDetail = "";
        try {
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/GroupComplaintZHZWService.xml");
            String methodName = this.getMethodNameByType(type);
            opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, filePath, methodName);
            System.out.println("lk,getOpDetail-opDetail=" + opDetail);
        } catch (Exception e) {
            opDetail = "";
            e.printStackTrace();
            System.out.println("lk,error47，得到opDetail报错" + e.getMessage());
        }
        return opDetail;
    }


    //****************************************************************************分割线，上端为接口，下端为 公共方法
    public void sysoutNodeName(String methodName, String nodeName) {
        System.out.println("lk,GroupComplaintZHZWService." + methodName + ".nodeName==" + nodeName);
    }

    /**
     * 输出为一个字符串
     * 格式为“Status=状态（0-成功；-1-失败）;Errlist=错误描述”。
     * 如果成功： “错误描述”为空串；
     * 如果失败， “错误列表”为错误描述。
     * 约定“Status=”和”Errlist=”不管成功与否都必须有，只是值可以为空串。
     */
    public String publicReturnStr(String errorLog) {
        String returnStr = "";
        try {
            if (errorLog != null && !"".equals(errorLog)) {//有错误-->失败
                returnStr = "Status=-1;Errlist=" + errorLog;
            } else {//没有错误-->成功
                returnStr = "Status=0;Errlist=";
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnStr = "Status=-1;Errlist=" + e.getMessage();
        }
        System.out.println(returnStr);
        return returnStr;
    }

    /**
     * 整合用 保存task数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void newSaveTask(BaseMain main, BaseLink link, ITask task)
            throws Exception {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        if (task.getId() == null)
            task.setId(UUIDHexGenerator.getInstance().getID());
        if (task.getCreateTime() == null)
            task.setCreateTime(nowDate);
        if (task.getTaskStatus() == null)
            task.setTaskStatus(Constants.TASK_STATUS_READY);
        if (task.getProcessId() == null && main.getPiid() != null)
            task.setProcessId(main.getPiid());
        if (task.getSheetKey() == null)
            task.setSheetKey(main.getId());
        if (task.getSheetId() == null)
            task.setSheetId(main.getSheetId());
        if (task.getTitle() == null)
            task.setTitle(main.getTitle());
        if (task.getAcceptTimeLimit() == null)
            task.setAcceptTimeLimit(link.getNodeAcceptLimit());
        if (task.getCompleteTimeLimit() == null)
            task.setCompleteTimeLimit(link.getNodeCompleteLimit());
        if (task.getPreLinkId() == null)
            task.setPreLinkId(link.getId());
        if (task.getIfWaitForSubTask() == null)
            task.setIfWaitForSubTask("false");

        task.setCreateDay(calendar.get(Calendar.DATE));
        task.setCreateMonth(calendar.get(Calendar.MONTH) + 1);
        task.setCreateYear(calendar.get(Calendar.YEAR));
        IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager) ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
        taskservice.addTask(task);
    }


}
