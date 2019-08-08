package com.boco.eoms.interSwitchAlarm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoaderHB;
import com.boco.eoms.handheldoperation.HandheldOperationUtil;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.commonfault.interfaces.Service_PortType;
import com.boco.eoms.sheet.commonfault.interfaces.Service_ServiceLocator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.model.CommonFaultViSheetInfo;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultViSheetInfoManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commonfault.util.GeneralUtil;
import com.boco.eoms.sheet.commonfault.webapp.action.CommonFaultSheetMethod;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.StaxParser;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;

/**
 * 2014/9/9 add by dengpengfei
 *
 * @author dpf
 */
public class EOMSCommonfaultSheet {


    /*
     * 2014/9/16 begin: modify by dengpengfei
     */
    // 告警核实接口
    public String checkfaultWorkSheet(int sheetType, String serialNo, String serSupplier,
                                      String serCaller, String callerPwd, String callTime,
                                      String opPerson, String opCorp, String opDepart,
                                      String opContact, String opTime, String opDetail) {
        System.out.println("dpf----------begin:--------checkfaultWorkSheet----------------------");
        System.out.println("告警核实接口");
        System.out.println("sheetType=" + sheetType);// 工单类别
        System.out.println("serialNo=" + serialNo); // 掌上运维工单编号
        System.out.println("opDetail=" + opDetail); // 详细信息，参见“详细信息约定

        System.out.println("serSupplier=" + serSupplier); // 服务提供方：（HB_EOMS）
        System.out.println("serCaller=" + serCaller); // 服务调用方，格式为：省份编码_系统名称
        System.out.println("callerPwd=" + callerPwd); // 服务调用方密码(可选)
        System.out.println("callTime=" + callTime); // 服务调用时间
        System.out.println("opPerson=" + opPerson); // 操作人
        System.out.println("opCorp=" + opCorp); // 操作人单位
        System.out.println("opDepart=" + opDepart); // 操作人所属部门
        System.out.println("opContact=" + opContact); // 操作人联系电话
        System.out.println("opTime=" + opTime); // 操作时间

        HashMap sheetMap = new HashMap();
        sheetMap.put("serialNo", serialNo);
        sheetMap.put("callTime", callTime);
        sheetMap.put("opPerson", opPerson);
        sheetMap.put("opCorp", opCorp);
        sheetMap.put("opDepart", opDepart);
        sheetMap.put("opContact", opContact);
        sheetMap.put("opTime", opTime);

        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            if (opDetail != null && !opDetail.equals("")) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap,
                        "FieldContent");
            }
            String result = CommonFaultBO.updateMainCheckcount(sheetMap);
            System.out.println("dpf----------end:--------checkfaultWorkSheet----------------------");
            return result;
        } catch (Exception err) {
            err.printStackTrace();
            return "工单未能找到";
        }

    }

    /**
     * 2014/9/16 end: modify by dengpengfei
     */
    public String notifyWorkSheet(int sheetType, String serialNo, String serSupplier,
                                  String serCaller, String callerPwd, String callTime,
                                  String opPerson, String opCorp, String opDepart,
                                  String opContact, String opTime, String opDetail) {


        String remark = "";
        System.out.println("dpf----------begin:--------notifyWorkSheet----------------------");
        String result = "0";
        Map sheetMap = new HashMap();
        // 工单号
        String sheetId = StaticMethod.nullObject2String(serialNo);
        // 延期申请内容
        String linkTransmitContent = "";
        try {
            if (opDetail != null && !opDetail.equals("")) {
                sheetMap = StaxParser.getInstance().getOpdetail(opDetail);
            }

            // 阶段回复内容
            remark = StaticMethod.nullObject2String(sheetMap.get("opDesc"));


            // 调用时间
            callTime = StaticMethod.nullObject2String(callTime);
            Date callTimeDate = SheetUtils.stringToDate(callTime);

            // 操作时间
            opTime = StaticMethod.nullObject2String(opTime);
            Date opTimeDate = SheetUtils.stringToDate(opTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"".equals(sheetId)) {
            ICommonFaultMainManager mainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            ICommonFaultLinkManager linkManager = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            ICommonFaultTaskManager taskManager = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");

            CommonFaultMain baseMain = (CommonFaultMain) mainManager.getMainBySheetId(sheetId);

            if (null != baseMain || !"".equals(baseMain.getSheetId())) {
                try {
                    String mainid = baseMain.getId();
                    List tasks = taskManager.getTasksBySheetKey(mainid, "'SecondExcuteHumTask'");
                    if (tasks.size() > 0) {

                        CommonFaultTask baseTask = (CommonFaultTask) tasks.get(0);

                        Date now = new Date();
                        Calendar ca = Calendar.getInstance();
                        int year = ca.get(Calendar.YEAR);// 获取年份
                        int month = ca.get(Calendar.MONTH);// 获取月份
                        int day = ca.get(Calendar.DATE);// 获取日
                        CommonFaultLink newLink = (CommonFaultLink) linkManager.getLinkObject().getClass().newInstance();
                        // 操作人Id
                        opDepart = StaticMethod.nullObject2String(opDepart);
                        opContact = StaticMethod.nullObject2String(opContact);
                        opPerson = StaticMethod.nullObject2String(opPerson);

                        //阶段回复
                        newLink.setRemark(remark);


                        //阶段回复
                        newLink.setOperateType(Integer.valueOf(9));
                        newLink.setOperateTime(now);


                        // 构造基础的link信息
                        newLink.setId(UUIDHexGenerator.getInstance().getID());
                        newLink.setMainId(baseMain.getId());
                        //	newLink.setOperateType(Integer.valueOf(9));
                        //	newLink.setOperateTime(now);
                        newLink.setOperateUserId(opPerson);

                        newLink.setPreLinkId(baseTask.getPreLinkId());
                        newLink.setPiid(baseMain.getPiid());
                        newLink.setAiid(baseTask.getId());
                        newLink.setActiveTemplateId("SecondExcuteHumTask");
                        newLink.setNodeAcceptLimit(baseMain.getSheetAcceptLimit());
                        newLink.setNodeCompleteLimit(baseMain.getSheetCompleteLimit());
                        String subRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("notifyDefaultSubRole"));
                        newLink.setToOrgRoleId(subRole);
                        newLink.setCorrelationKey(baseMain.getCorrelationKey());
                        newLink.setOperaterContact(opContact);
                        newLink.setOperateDeptId(opDepart);
                        newLink.setOperateYear(year);
                        newLink.setOperateMonth(month);
                        newLink.setOperateDay(day);
                        linkManager.addLink(newLink);
                        CommonFaultTask newTask = (CommonFaultTask) taskManager.getTaskModelObject().getClass().newInstance();
                        newTask.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
                        newTask.setTaskName("reply");
                        newTask.setTaskDisplayName("阶段回复");
                        newTask.setCreateTime(now);
                        newTask.setTaskStatus("2");
                        newTask.setProcessId(baseMain.getPiid());
                        newTask.setSheetKey(baseMain.getId());
                        newTask.setSheetId(baseMain.getSheetId());
                        newTask.setTitle(baseMain.getTitle());
                        newTask.setPreLinkId(newLink.getId());
                        newTask.setFlowName(baseMain.getPiid());
                        newTask.setTaskOwner(subRole);
                        newTask.setSubTaskFlag("false");
                        newTask.setIfWaitForSubTask("false");
                        newTask.setOperateType("subrole");
                        newTask.setIfWaitForSubTask("false");
                        newTask.setSendTime(baseMain.getSendTime());
                        newTask.setCreateYear(year);
                        newTask.setCreateDay(day);
                        newTask.setCreateMonth(month);
                        newTask.setPreDealUserId(baseTask.getTaskOwner());
                        newTask.setAcceptTimeLimit(baseMain.getSheetAcceptLimit());
                        newTask.setCompleteTimeLimit(baseMain.getSheetCompleteLimit());
                        taskManager.addTask(newTask);

                        System.out.println("sheetid is " + sheetId + " 阶段回复成功!");

                    } else {
                        result = "sheetid is " + sheetId + " 该工单不是T2环节的工单!";

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                result = "不存在对应的eoms工单";
            }
        }
        System.out.println("dpf----------end:--------notifyWorkSheet----------------------");
        return result;
    }


    /**
     * 2014/9/16 begin:add by dengpengfei
     */
    public String delayWorkSheet(int sheetType, String serialNo, String serSupplier,
                                 String serCaller, String callerPwd, String callTime,
                                 String opPerson, String opCorp, String opDepart,
                                 String opContact, String opTime, String opDetail) {

        System.out.println("dpf----------begin:--------delayWorkSheet----------------------");
        String result = "0";
        Map sheetMap = new HashMap();
        // 工单号
        String sheetId = StaticMethod.nullObject2String(serialNo);
        // 延期申请内容
        String linkTransmitContent = "";
        try {
            if (opDetail != null && !opDetail.equals("")) {
                sheetMap = StaxParser.getInstance().getOpdetail(opDetail);
            }

            // 从接口中取出对应的字段 判段是否为空
            linkTransmitContent = StaticMethod.nullObject2String(sheetMap.get("opDesc"));

            // 调用时间
            callTime = StaticMethod.nullObject2String(callTime);
            Date callTimeDate = SheetUtils.stringToDate(callTime);

            // 操作时间
            opTime = StaticMethod.nullObject2String(opTime);
            Date opTimeDate = SheetUtils.stringToDate(opTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"".equals(sheetId)) {
            ICommonFaultMainManager mainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            ICommonFaultLinkManager linkManager = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            ICommonFaultTaskManager taskManager = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");

            CommonFaultMain baseMain = (CommonFaultMain) mainManager.getMainBySheetId(sheetId);

            if (null != baseMain || !"".equals(baseMain.getSheetId())) {
                try {
                    String mainid = baseMain.getId();
                    List tasks = taskManager.getTasksBySheetKey(mainid, "'SecondExcuteHumTask'");
                    if (tasks.size() > 0) {

                        CommonFaultTask baseTask = (CommonFaultTask) tasks.get(0);
                        CommonFaultLink baseLink = (CommonFaultLink) linkManager.getSingleLinkPO(baseTask.getPreLinkId());

                        IBusinessFlowService businessFlowService = (IBusinessFlowService) ApplicationContextHolder.getInstance().getBean("businessFlowService");

                        // 现在时间
                        Date now = new Date();
                        Calendar ca = Calendar.getInstance();
                        int year = ca.get(Calendar.YEAR);// 获取年份
                        int month = ca.get(Calendar.MONTH);// 获取月份
                        int day = ca.get(Calendar.DATE);// 获取日
                        CommonFaultLink newLink = (CommonFaultLink) linkManager.getLinkObject().getClass().newInstance();
                        // 操作人Id
                        opDepart = StaticMethod.nullObject2String(opDepart);
                        opContact = StaticMethod.nullObject2String(opContact);
                        opPerson = StaticMethod.nullObject2String(opPerson);

                        // 延期申请理由
                        newLink.setLinkTransmitContent(linkTransmitContent);

                        // 构造基础的link信息
                        newLink.setId(UUIDHexGenerator.getInstance().getID());
                        newLink.setMainId(baseMain.getId());
                        newLink.setOperateType(Integer.valueOf(5));
                        newLink.setOperateTime(now);
                        newLink.setOperateUserId(opPerson);

                        newLink.setPreLinkId(baseTask.getPreLinkId());
                        newLink.setPiid(baseMain.getPiid());
                        newLink.setAiid(baseTask.getId());
                        newLink.setActiveTemplateId("SecondExcuteHumTask");
                        newLink.setNodeAcceptLimit(baseMain.getSheetAcceptLimit());
                        newLink.setNodeCompleteLimit(baseMain.getSheetCompleteLimit());

                        // 要到那个子角色去，从配置文件读取
                        String subRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceDefaultSubRole"));
                        newLink.setToOrgRoleId(subRole);
                        newLink.setCorrelationKey(baseMain.getCorrelationKey());
                        newLink.setOperaterContact(opContact);
                        newLink.setOperateDeptId(opDepart);
                        newLink.setOperateYear(year);
                        newLink.setOperateMonth(month);
                        newLink.setOperateDay(day);
                        Map linkMap = SheetBeanUtils.bean2Map(newLink);

                        // 保存task对象
                        baseTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
                        baseTask.setCurrentLinkId(newLink.getId());


                        // 构造新的task对象
                        CommonFaultTask newTask = (CommonFaultTask) taskManager.getTaskModelObject().getClass().newInstance();
                        newTask.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
                        newTask.setTaskName("ExamineTask");
                        newTask.setTaskDisplayName("延期审批");
                        newTask.setCreateTime(now);
                        newTask.setTaskStatus(Constants.TASK_STATUS_READY);

                        newTask.setProcessId(baseMain.getPiid());
                        newTask.setSheetKey(baseMain.getId());
                        newTask.setSheetId(baseMain.getSheetId());
                        newTask.setTitle(baseMain.getTitle());
                        newTask.setTaskOwner(baseMain.getSendUserId());
                        newTask.setPreLinkId(newLink.getId());
                        newTask.setFlowName(baseMain.getPiid());
                        newTask.setTaskOwner(baseLink.getOperateUserId());

                        //操作角色Id
                        newTask.setOperateRoleId(subRole);
                        newTask.setPreDealRoleId(baseTask.getOperateRoleId());


                        newTask.setSubTaskFlag("false");
                        newTask.setIfWaitForSubTask("false");
                        newTask.setSendTime(baseMain.getSendTime());
                        newTask.setOperateType("subrole");
                        newTask.setIfWaitForSubTask("false");
                        newTask.setSendTime(baseMain.getSendTime());
                        newTask.setCreateYear(year);
                        newTask.setCreateDay(day);
                        newTask.setCreateMonth(month);
                        newTask.setPreDealUserId(baseTask.getTaskOwner());
                        newTask.setAcceptTimeLimit(baseMain.getSheetAcceptLimit());
                        newTask.setCompleteTimeLimit(baseMain.getSheetCompleteLimit());


                        //操作角色Id
                        //	newTask.setOperateRoleId(subRole);
                        //	newTask.setPreDealRoleId(baseTask.getOperateRoleId());

                        //	newTask.setSubTaskFlag("false");
                        //	newTask.setIfWaitForSubTask("false");

                        //	newTask.setTaskOwner(baseLink.getOperateUserId());
                        //	newTask.setPreDealRoleId(baseTask.getOperateRoleId());
                        // 更新main


                        mainManager.saveOrUpdateMain(baseMain);
                        Map mainMap = SheetBeanUtils.bean2Map(baseMain);
                        // 结束流程实例
                        HashMap map2 = new HashMap();
                        Map operateMap = new HashMap();
                        //		Map columnMap = new HashMap();
                        operateMap.put("phaseId", "ExamineTask");
                        //		operateMap.put("phaseId", "ExamineHumTask");
                        operateMap.put("dealPerformer", subRole);
                        operateMap.put("beanId", "iCommonFaultMainManager");
                        operateMap.put("linkBeanId", "iCommonFaultLinkManager");
                        operateMap.put("linkClassName", "com.boco.eoms.sheet.commonfault.model.CommonFaultLink");
                        operateMap.put("mainClassName", "com.boco.eoms.sheet.commonfault.model.CommonFaultMain");
                        operateMap.put("dealPerformerLeader", subRole);
                        //		operateMap.put("dealPerformerType", "subrole");
                        //		operateMap.put("sheetId", StaticMethod.nullObject2String(baseMain.getSheetId()));
                        //		operateMap.put("correlationKey", StaticMethod.nullObject2String(baseMain.getCorrelationKey()));
                        //		operateMap.put("mainId", StaticMethod.nullObject2String(baseMain.getId()));
                        //		operateMap.put("operateType", "5");


                        map2.put("main", mainMap);
                        map2.put("link", linkMap);
                        map2.put("operate", operateMap);

                        //	map2.put("operate", Constants.pageColumnName);
                        //	columnMap.put("selfSheet", map2);

                        HashMap sessionMap = new HashMap();

                        String defaultUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("DefaultUser"));
                        if (defaultUser == null || defaultUser.equals("")) {
                            defaultUser = "EOMS_admin";
                        }
                        String DefaultPassword = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("DefaultPassword"));
                        if (DefaultPassword == null || DefaultPassword.equals("")) {
                            defaultUser = "EOMS_password";
                        }

                        sessionMap.put("userId", defaultUser);
                        sessionMap.put("password", DefaultPassword);

                        //	  WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                        //		sm.dealSheet(mainid, operateMap, columnMap, defaultUser, taskManager);
                        //businessFlowService.completeHumanTask(baseTask.getId(),map2,sessionMap);
                        businessFlowService.completeHumanTask(baseTask.getId(), map2, sessionMap);

                        System.out.println("sheetid is " + sheetId + " 需求申请成功!");

                    } else {
                        result = "sheetid is " + sheetId + " 该工单不是t2环节的工单!";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                result = "不存在对应的eoms工单";
            }
        }
        System.out.println("dpf----------end:--------delayWorkSheet----------------------");
        return result;
    }

    public String combineSheetsList(String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            String nodeName = "combineSheetsList";
            System.out.println("CommonfaultSheetService内对应的nodeName======" + nodeName);
            HandheldOperationUtil hoUtil = new HandheldOperationUtil();
            if ((opDetail != null) && (!"".equals(opDetail))) {
                //	  String taskName = "SecondExcuteHumTask";
                String keyHql = "batchDeal.T2CombineSQL";
                //		String keyListsize =  "batchDeal.T2CombineSQLSize";
                String hql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(keyHql));
                //  		String listSize = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(keyListsize));
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                String userId = StaticMethod.nullObject2String(sheetMap.get("user_id"));
                int start_records = StaticMethod.nullObject2int(sheetMap.get("Start_records"));
                int end_records = StaticMethod.nullObject2int(sheetMap.get("End_records"));
                if (end_records == 0) {
                    end_records = 15;
                }
                ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                TawSystemUser user = userMgr.getUserByuserid(userId);
                String deptId = user.getDeptid();
                if (("".equals(userId)) || (userId == null) || (deptId == null) || ("".equals(deptId))) {
                    result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！用户名在EOMS中不存在。";
                    return result;
                }
                hql = ExcelConverterUtil.replaceAll(hql, "$userId$", userId);
                hql = ExcelConverterUtil.replaceAll(hql, "$deptId$", deptId);
                ICommonFaultPackMainManager packMainSerive = (ICommonFaultPackMainManager) ApplicationContextHolder.getInstance().getBean(
                        "iCommonFaultPackMainManager");
                IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                String sheet_totals = "";

                String countSql = "select count(distinct main.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
                System.out.println(countSql);
                List countList = services.getSheetAccessoriesList(countSql);
                Map countMap = (Map) countList.get(0);
                sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
                int totals = Integer.parseInt(sheet_totals);
                String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
                List taskList = services.getSheetAccessoriesList(sql);
                String opDetail1 = "";
                if (null != taskList) {
                    for (int i = 0; i < taskList.size(); i++) {
                        Map main = (Map) taskList.get(i);
                        if ("1030101".equals((main.get("mainIsPack").toString())) && (main.get("mainIsPack") != null)) {//如果是打包工单则检查打包的工单是否有告警清除时间
                            boolean re = packMainSerive.isAllHaveClearTime((main.get("id").toString()));
                            if (re) {
                                taskList.remove(main);
                                totals--;
                            }
                        }
                    }
                    opDetail1 = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), nodeName, String.valueOf(totals));
                }
                result = "Status=0;sheetDetail=" + opDetail1 + ";Errlist=";
                System.out.println("--combineSheetsList--opDetail--" + opDetail1);
                return result;

            } else {
                result = "Status=-1;Errlist=工单获取合并工单列表接口没有传入opDetail参数,请查证！";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;Errlist=工单获取合并工单列表失败！详细信息为" + e.getMessage();
        }
        return result;
    }


    public String combineSheets(String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            String taskName = "SecondExcuteHumTask";
            String nodeName = "combineSheets";
            System.out.println("CommonfaultSheetService.combineSheets内对应的nodeName======" + nodeName);
            if ((opDetail != null) && (!"".equals(opDetail))) {
                ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
                ICommonFaultViSheetInfoManager infoMgr = (ICommonFaultViSheetInfoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultViSheetInfoManager");
                ICommonFaultTaskManager taskMgr = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                //	        String userId = StaticMethod.nullObject2String(sheetMap.get("user_id"));
                String mainId = StaticMethod.nullObject2String(sheetMap.get("main_sheet_id"));
                String viss = StaticMethod.nullObject2String(sheetMap.get("sub_sheet_id"));
                //	        String operateTime = StaticMethod.nullObject2String(sheetMap.get("oper_time"));
                //	        viss = viss.substring(0, viss.lastIndexOf(","));
                BocoLog.info(this, "要合并的主单信息main sheet id===" + mainId);
                BocoLog.info(this, "要合并的副单信息viss sheet ids===" + viss);
                //1、更新主单标题
                if (null != mainId && !"".equals(mainId) && null != viss && !"".equals(viss)) {
                    CommonFaultMain main = (CommonFaultMain) mainMgr.getCommonFaultMainBySheetId(mainId);
                    //		Integer mainlevel = GeneralUtil.getLevel(main.getMainFaultResponseLevel());
                    String mid = main.getId();
                    String title = main.getTitle();
                    if (!title.contains("【合（主）】")) {
                        title = "【合（主）】 " + title;
                        main.setTitle(title);//保存
                    }
                    main.setMainIfCombine("combine");
                    mainMgr.saveOrUpdateMain(main);
                    //2、查找当前步骤的task对象
                    String condition = " sheetKey = '" + mid + "'" +
                            " and taskName='" + taskName + "' and taskStatus='8' and (subTaskFlag is null or subTaskFlag = 'false' )";
                    List baseTasks = taskMgr.getTasksByCondition(condition);
                    CommonFaultTask baseTask = (CommonFaultTask) baseTasks.get(0);
                    Date mainTaskSheetAccept = baseTask.getAcceptTimeLimit();
                    Date mainTaskSheetComplete = baseTask.getCompleteTimeLimit();
                    Date mainMainSheetAccept = main.getSheetAcceptLimit();
                    Date mainMainSheetComplete = main.getSheetCompleteLimit();
                    //3、for循环更新子单的main表 标题、受理时限、完成时限、以及task环节的受理时限、完成时限
                    List list = GeneralUtil.convertString2List(viss);
                    int vlevel = 1;
                    for (int i = 0; i < list.size(); i++) {
                        String visId = StaticMethod.nullObject2String(list.get(i));
                        CommonFaultMain vism = (CommonFaultMain) mainMgr.getCommonFaultMainBySheetId(visId);
                        Integer vislevel = GeneralUtil.getLevel(vism.getMainFaultResponseLevel());
                        if (vislevel.intValue() > vlevel) {
                            vlevel = vislevel.intValue();
                        }
                        String vistitle = vism.getTitle();
                        String vid = vism.getId();
                        vistitle = "【合（副）】 " + vistitle;
                        vism.setTitle(vistitle);
                        vism.setSheetAcceptLimit(mainMainSheetAccept);
                        vism.setSheetCompleteLimit(mainMainSheetComplete);
                        //				String ifCombine = vism.getMainIfCombine();
                        vism.setMainIfCombine(mid);

                        //to-do 保存
                        String conditionVis = " sheetKey = '" + vid + "'" +
                                " and taskName='" + taskName + "' and taskStatus='8' and (subTaskFlag is null or subTaskFlag = 'false' )";
                        List bs = taskMgr.getTasksByCondition(conditionVis);
                        CommonFaultTask visTask = null;
                        if (null != bs && bs.size() > 0) {
                            visTask = (CommonFaultTask) bs.get(0);
                        } else {
                            BocoLog.info(this, "副单没有符合条件的task记录==" + vism.getSheetId());
                            continue;
                        }
                        visTask.setAcceptTimeLimit(mainTaskSheetAccept);
                        visTask.setCompleteTimeLimit(mainTaskSheetComplete);
                        visTask.setTaskStatus("9");//随便改个数字，使之不出现在待办工单列表

                        //to-do 保存
                        CommonFaultViSheetInfo info = new CommonFaultViSheetInfo();
                        info.setId(UUIDHexGenerator.getInstance().getID());
                        info.setMainId(mid);
                        info.setVisId(vid);
                        info.setDeleted(Integer.valueOf(0));
                        info.setTaskId(visTask.getId());
                        info.setMainSheetAcceptLimit(mainMainSheetAccept);
                        info.setMainSheetCompleteLimit(mainMainSheetComplete);
                        info.setTaskSheetAcceptLimit(mainTaskSheetAccept);
                        info.setTaskSheetCompleteLimit(mainTaskSheetComplete);
                        //to-do 保存
                        mainMgr.saveOrUpdateMain(vism);
                        infoMgr.saveOrUpdate(info);
                        taskMgr.addTask(visTask);
                    }
                    result = "Status=0;Errlist=";

                } else {
                    result = "Status=-1;Errlist=工单合并工单接口没有传入工单号参数,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=工单合并工单接口没有传入opDetail参数,请查证！";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;Errlist=工单合并失败！详细信息为" + e.getMessage();
        }
        return result;
    }


    public String visSheets(String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            String nodeName = "visSheets";
            System.out.println("CommonfaultSheetService.combineSheets内对应的nodeName======" + nodeName);
            HandheldOperationUtil hoUtil = new HandheldOperationUtil();
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                //       String userId = StaticMethod.nullObject2String(sheetMap.get("user_id"));
                String id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
                int start_records = StaticMethod.nullObject2int(sheetMap.get("Start_records"));
                int end_records = StaticMethod.nullObject2int(sheetMap.get("End_records"));
                if (end_records == 0) {
                    end_records = 15;
                }
                IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                String sheet_totals = "";
                ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
                //	String id = request.getParameter("id");
                CommonFaultMain main = (CommonFaultMain) mainMgr.getCommonFaultMainBySheetId(id);
                if (null != main) {
                    String combineStatus = main.getMainIfCombine();
                    String mid = main.getId();
                    if (null != combineStatus && "combine".equals(combineStatus)) {
                        String sql = "select main.* from CommonFault_Main main where  main.deleted <> '1' and main.status=0 and main.mainIfCombine='" + mid + "'";
                        String countSql = "select count(distinct main.id) num from CommonFault_Main main where main.deleted <> '1' and main.status=0 and main.mainIfCombine='" + mid + "'";
                        System.out.println(countSql);
                        List countList = services.getSheetAccessoriesList(countSql);
                        Map countMap = (Map) countList.get(0);
                        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
                        String resultsql = "select * from (select a.*,rownum row_ from (" + sql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
                        List taskList = services.getSheetAccessoriesList(resultsql);
                        //	Map map = mainMgr.getQueryListByCondition(sql, new Integer(0),new Integer(-1));
                        //	List list = (List)map.get("sheetList");
                        //	Integer total = StaticMethod.nullObject2Integer(map.get("sheetTotal"));
                        String opDetail1 = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml"), "combineSheetsList", sheet_totals);
                        result = "Status=0;sheetDetail=" + opDetail1 + ";Errlist=";
                        return result;
                    } else {
                        result = "Status=-1;Errlist=此工单不是合并工单的主工单,请查证！";
                        return result;
                    }
                } else {
                    result = "Status=-1;Errlist=此工单号无对应工单,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Errlist=副工单查询接口没有传入opDetail参数,请查证！";
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;Errlist=获取副工单列表失败！详细信息为" + e.getMessage();
        }
        return result;

    }

    public String cancelVisSheet(String opDetail) {
        String result = "";
        ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ICommonFaultViSheetInfoManager infoMgr = (ICommonFaultViSheetInfoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultViSheetInfoManager");
        ICommonFaultTaskManager taskMgr = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");

        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            String nodeName = "cancelVisSheet";
            System.out.println("CommonfaultSheetService.combineSheets内对应的nodeName======" + nodeName);
            //  HandheldOperationUtil hoUtil = new HandheldOperationUtil();
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                String mainId = StaticMethod.nullObject2String(sheetMap.get("main_sheet_id"));
                String visId = StaticMethod.nullObject2String(sheetMap.get("sub_sheet_id"));
                if (null != mainId && !"".equals(mainId) && null != visId && !"".equals(visId)) {
                    CommonFaultMain vmain = (CommonFaultMain) mainMgr.getCommonFaultMainBySheetId(visId);
                    CommonFaultMain main = (CommonFaultMain) mainMgr.getCommonFaultMainBySheetId(mainId);
                    String mid = main.getId();
                    String vid = vmain.getId();
                    CommonFaultViSheetInfo info = infoMgr.getCommonFaultViSheetInfoByVisId(mid, vid);
                    if (null != info) {
                        if (null != main.getMainIfReply() && !"".equals(main.getMainIfReply())) {
                            result = "Status=-1;Errlist=主单已回单，副单不能撤单!";
                            return result;
                        } else {
                            info.setDeleted(Integer.valueOf(1));
                            infoMgr.saveOrUpdate(info);
                            String title = vmain.getTitle();
                            title = title.replace("【合（副）】 ", "");
                            vmain.setTitle(title);
                            vmain.setSheetAcceptLimit(info.getMainSheetAcceptLimit());
                            vmain.setSheetCompleteLimit(info.getMainSheetCompleteLimit());
                            vmain.setMainIfCombine("");

                            ITask task = taskMgr.getSinglePO(info.getTaskId());
                            task.setTaskStatus("8");
                            task.setAcceptTimeLimit(info.getTaskSheetAcceptLimit());
                            task.setCompleteTimeLimit(info.getTaskSheetCompleteLimit());

                            CommonFaultViSheetInfo infoM = infoMgr.getCommonFaultViSheetInfoBymainId(mid);
                            if (null == infoM) {
                                String tt = main.getTitle();
                                tt = tt.replace("【合（主）】 ", "");
                                main.setMainIfCombine("");
                                main.setTitle(tt);//保存
                                mainMgr.saveOrUpdateMain(main);
                            }
                            mainMgr.saveOrUpdateMain(vmain);

                            taskMgr.addTask(task);

                            result = "Status=0;sheetDetail=撤单成功;Errlist=";
                            return result;
                        }
                    } else {
                        result = "Status=-1;sheetDetail=;Errlist=未找到副单信息,请查证！";
                        return result;
                    }
                } else {
                    result = "Status=-1;sheetDetail=;Errlist=副工单查询接口没有传入工单号参数,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;sheetDetail=;Errlist=副工单查询接口没有传入opDetail参数,请查证！";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;sheetDetail=;Errlist=撤单失败，请联系管理员！详细信息为" + e.getMessage();
        }
        return result;
    }

    public String getCheckTime(String opDetail) {
        String result = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap sheetMap = new HashMap();
            ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                String sheetId = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
//		       String userId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));	 
                if (!"".equals(sheetId) && sheetId != null) {
                    CommonFaultMain main = (CommonFaultMain) mainMgr.getCommonFaultMainBySheetId(sheetId);
                    String alarmTime = StaticMethod.date2String(main.getMainAlarmSolveDate());
                    String checkTime = StaticMethod.date2String(main.getMainCheckTime());
                    String alarmCheckTime = "";
                    if (!"".equals(alarmTime) && alarmTime != null) {
                        alarmCheckTime = alarmTime;
                    } else {
                        alarmCheckTime = checkTime;
                    }
                    result = "Status=0;Cleartime=" + alarmCheckTime;
                    return result;
                } else {
                    result = "Status=-1;Cleartime=;Errlist=获取告警清除时间接口没有传入sheetId,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;Cleartime=;Errlist=获取告警清除时间接口没有传入opDetail参数,请查证！";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;Cleartime=;Errlist=获取告警核实时间失败，请联系管理员！详细信息为" + e.getMessage();
        }

        return result;
    }


    public String rejectAcceptSheet(String opDetail) {
        String result = "";
        try {
            ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
            ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
            ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            HashMap sheetMap = new HashMap();
            HashMap columnMap = new HashMap();
            HashMap sheetMap1 = new HashMap();
            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            String nodeName = "rejectSheet";
            System.out.println("HandheldOperation2EomsService.dealSheet内对应的nodeName======" + nodeName);
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/handheldoperation.xml");
            if ((opDetail != null) && (!"".equals(opDetail))) {
                sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
                Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
                String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
                String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
                String operate_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));
                String remark = StaticMethod.nullObject2String(sheetMap.get("Reject_desc"));
                if ((sheet_id != null) && (!"".equals(sheet_id))) {
                    CommonFaultMain main = (CommonFaultMain) mainservice.getMainBySheetId(sheet_id);
                    int status = StaticMethod.nullObject2int(main.getStatus());

                    if (status != 0) {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
                        return result;
                    }
                    String sheetKey = StaticMethod.nullObject2String(main.getId());
                    String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='SecondExcuteHumTask'";

                    List taskList = taskservice.getTasksByCondition(condition);
                    if ((taskList != null) && (taskList.size() > 0)) {
                        CommonFaultTask task = (CommonFaultTask) taskList.get(0);
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
                        valueMap.put("remark", remark);
                        valueMap.put("operateType", "4");
                        valueMap.put("hasNextTaskFlag", "true");

                        Map linkMap = new HashMap();
                        if ("SecondExcuteHumTask".equals(task.getTaskName())) {
                            valueMap.put("phaseId", "FirstExcuteTask");
                            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                            String sql = "select * from commonfault_link link where link.mainid='" +
                                    sheetKey + "' and link.operatetype='1' and link.activetemplateid='FirstExcuteHumTask' " +
                                    "order by link.operatetime desc ";
                            System.out.println("驳回操作查询T1操作人sql====" + sql);
                            List linkList = services.getSheetAccessoriesList(sql);
                            if ((linkList != null) && (linkList.size() > 0)) {
                                linkMap = (Map) linkList.get(0);
                                valueMap.put("dealPerformer", "8aa0813b1c6f2386011c6f39c8350027");
                                valueMap.put("dealPerformerLeader", "8aa0813b1c6f2386011c6f39c8350027");
                                valueMap.put("dealPerformerType", "subrole");
                                valueMap.put("toOrgRoleId", StaticMethod.nullObject2String(linkMap.get("operateRoleId")));
                                valueMap.put("nodeAcceptLimit", StaticMethod.nullObject2String(linkMap.get("nodeAcceptLimit")));
                                valueMap.put("nodeCompleteLimit", StaticMethod.nullObject2String(linkMap.get("nodeCompleteLimit")));
                            }
                        }


//	          记录驳回次数 by lyg
                        String mainT2RejectionNum = StaticMethod.nullObject2String(main.getMainT2RejectionNum());
                        System.out.println("mainT2RejectionNum==liu==liu==" + mainT2RejectionNum);


//				自能派单驳回，无线专业 mainNetSortOne（101010401）start 
                        String mainNetSortOne = StaticMethod.nullObject2String(main.getMainNetSortOne());
                        System.out.println("=qian===sheetId=" + task.getSheetId() + "==mainNetSortOne====" + mainNetSortOne);
                        String linkCiytSubrole = StaticMethod.nullObject2String(valueMap.get("linkCiytSubrole"));
//				第3次驳回和跨地市驳回不走自能派单驳回
                        String mainIsOther = StaticMethod.nullObject2String(valueMap.get("mainIsOther"));
                        if ("1".equals(mainIsOther)) {
                            valueMap.put("mainOtherSubrole", linkCiytSubrole);
                            valueMap.put("mainThisSubrole", task.getOperateRoleId());
                        }
                        if (!"3".equals(mainT2RejectionNum) && "".equals(mainIsOther)) {
                            String subroleid = "";//t1自动派给t2的班组
                            if ("101010401".equals(mainNetSortOne)) {
                                //查询网元对应班组表commonfault_net_team_wx
                                String operateRoleId1 = StaticMethod.nullObject2String(task.getOperateRoleId());
                                String mainNetName = StaticMethod.nullObject2String(main.getMainNetName());
                                System.out.println("operateRoleId1 === " + operateRoleId1 + "====mainNetName=" + mainNetName);

                                String sheetId = StaticMethod.nullObject2String(main.getSheetId());
                                String mainCitySubrole = StaticMethod.nullObject2String(main.getMainCitySubrole());
                                String mainFaultGenerantCity = StaticMethod.nullObject2String(main.getToDeptId());
                                System.out.println("sheetId=" + sheetId + "=mainCitySubrole=" + mainCitySubrole + "mainNetName=" + mainNetName + "=mainFaultGenerantCity=" + mainFaultGenerantCity + "=" + linkCiytSubrole);
                                Map getRuleMap = new HashMap();
                                getRuleMap.put("sheetId", sheetId);
                                getRuleMap.put("mainCitySubrole", mainCitySubrole);
                                getRuleMap.put("mainNetName", mainNetName);
                                getRuleMap.put("mainFaultGenerantCity", mainFaultGenerantCity);
                                getRuleMap.put("linkCiytSubrole", linkCiytSubrole);
                                getRuleMap.put("operateRoleId", operateRoleId1);
                                CommonFaultSheetMethod sheet = new CommonFaultSheetMethod();
                                Map ruleMap = sheet.getRule(getRuleMap);
                                mainCitySubrole = StaticMethod.nullObject2String(ruleMap.get("mainCitySubrole"));
                                valueMap.put("mainCitySubrole", mainCitySubrole);
                                subroleid = StaticMethod.nullObject2String(ruleMap.get("nextSubroleId"));

                                System.out.println("subroleid====1" + subroleid);

                            } else {
                                subroleid = linkCiytSubrole;
                                System.out.println("subroleid====2" + subroleid);
                            }
                            System.out.println("subroleid====" + subroleid);
                            if (!"".equals(subroleid)) {
//						调用铁塔接口
                                //1.判断该工单是否被铁塔驳回  条件还需要加入mainIFTowner=3，表示是铁塔驳回的，之后在派到T2时就部给铁塔平台推送了
                                //2.调用铁塔接口 id sheetId mainTownerName mainTownerDeviceId mainTownerRoomId
//						title mainAlarmDesc sendTime mainFaultGenerantTime
                                String mainIFTowner = StaticMethod.nullObject2String(main.getMainIFTowner());
                                System.out.println("mainIFTowner====" + mainIFTowner);
                                if (!"3".equals(mainIFTowner)) {
                                    Map mainMap = new HashMap();
                                    mainMap.put("id", main.getId());
                                    mainMap.put("sheetId", main.getSheetId());
                                    mainMap.put("mainTownerName", main.getMainTownerName());
                                    mainMap.put("mainTownerDeviceId", main.getMainTownerDeviceId());
                                    mainMap.put("mainTownerRoomId", main.getMainTownerRoomId());
                                    mainMap.put("title", main.getTitle());
                                    mainMap.put("mainAlarmDesc", main.getMainAlarmDesc());
                                    mainMap.put("sendTime", main.getSendTime());
                                    mainMap.put("mainFaultGenerantTime", main.getMainFaultGenerantTime());
                                    Map operateMap = new HashMap();
//							phaseId  dealPerformer
                                    operateMap.put("phaseId", "SecondExcuteTask");
                                    operateMap.put("dealPerformer", subroleid);
                                    CommonFaultBO bo = new CommonFaultBO();
                                    System.out.println("handheld after1=" + main.getSheetId());
                                    try {
                                        Map retMap = bo.townerSend(mainMap, operateMap);
                                        valueMap.put("mainIFTowner", StaticMethod.nullObject2String(retMap.get("mainIFTowner")));
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        System.out.println("handheld after=" + main.getSheetId());
                                    }
                                }

//						 1.添加link表驳回信息（T2驳回到T1）
                                String deptId = "";
                                if (user != null) {
                                    deptId = user.getDeptid();
                                }

                                GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();

                                CommonFaultLink Relink = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
                                String linkId = UUIDHexGenerator.getInstance().getID();
                                Relink.setId(linkId);
                                Relink.setMainId(StaticMethod.nullObject2String(main.getId()));
                                Relink.setOperateType(new Integer(4));
                                Relink.setOperateTime(calendar.getTime());
                                Relink.setOperateDay(calendar.get(5));
                                Relink.setOperateMonth(calendar.get(2) + 1);
                                Relink.setOperateYear(calendar.get(1));
                                Relink.setAcceptFlag(new Integer(0));
                                Relink.setPreLinkId(StaticMethod.nullObject2String(linkMap.get("id")));
                                Relink.setActiveTemplateId("SecondExcuteHumTask");
                                Relink.setToOrgType(new Integer(0));
                                Relink.setCompleteFlag(new Integer(0));
                                Relink.setOperateUserId(StaticMethod.nullObject2String(operateUserId));
                                Relink.setOperateRoleId(StaticMethod.nullObject2String(task.getOperateRoleId()));
                                Relink.setOperateDeptId(deptId);
                                String correlationKey = UUIDHexGenerator.getInstance().getID();
                                Relink.setCorrelationKey(correlationKey);
                                Relink.setTemplateFlag(0);
                                Relink.setOperaterContact(StaticMethod.nullObject2String(user.getMobile()));
                                Relink.setPiid(StaticMethod.nullObject2String(main.getPiid()));
                                Relink.setToOrgRoleId("8aa0813b1c6f2386011c6f39c8350027");
                                Relink.setRemark(remark);
                                Relink.setLinkCiytSubrole(linkCiytSubrole);
                                Relink.setLinkIfMobile("mobileReject");
                                linkservice.addLink(Relink);
                                // 2.T1确认受理
                                calendar.add(13, 5);
                                CommonFaultLink T1link61 = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
                                T1link61.setId(UUIDHexGenerator.getInstance().getID());
                                T1link61.setOperateType(new Integer("61"));
                                T1link61.setActiveTemplateId("FirstExcuteHumTask");
                                T1link61.setOperateTime(calendar.getTime());
                                T1link61.setOperateDay(calendar.get(5));
                                T1link61.setOperateMonth(calendar.get(2) + 1);
                                T1link61.setOperateYear(calendar.get(1));
                                T1link61.setMainId(StaticMethod.nullObject2String(main.getId()));
                                T1link61.setToOrgRoleId(subroleid);// 综合班组
                                T1link61.setPreLinkId(linkId);
                                T1link61.setNodeAccessories("");
                                T1link61.setToOrgType(new Integer(0));
                                T1link61.setCompleteFlag(new Integer(0));
                                T1link61.setOperateUserId("fangmin");
                                T1link61.setOperateRoleId("8aa0813b1c6f2386011c6f39c8350027");
                                T1link61.setOperateDeptId("12201");
                                T1link61.setTemplateFlag(0);
                                T1link61.setLinkIfMobile("mobileAccept");
                                linkservice.addLink(T1link61);

                                // 3.T1增加task记录
                                // ICommonFaultTaskManager taskservice =
                                // (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
                                CommonFaultTask T1Task = (CommonFaultTask) taskservice.getTaskModelObject().getClass().newInstance();
                                try {
                                    T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                T1Task.setTaskName("FirstExcuteHumTask");
                                T1Task.setTaskDisplayName("T1处理");
                                T1Task.setFlowName("CommonFaultMainFlowProcess");
                                T1Task.setSendTime((Date) main.getSendTime());
                                T1Task.setSheetKey(StaticMethod.nullObject2String(main.getId()));
                                T1Task.setTaskStatus("5");
                                T1Task.setSheetId(StaticMethod.nullObject2String(main.getSheetId()));
                                T1Task.setTitle(StaticMethod.nullObject2String(main.getTitle()));
                                T1Task.setOperateType("subrole");
                                T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
                                T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
                                T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
                                T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
                                T1Task.setOperateRoleId(StaticMethod.nullObject2String("8aa0813b1c6f2386011c6f39c8350027"));
                                T1Task.setTaskOwner(StaticMethod.nullObject2String("fangmin"));
                                T1Task.setIfWaitForSubTask("false");
                                T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
                                T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
                                taskservice.addTask(T1Task);

                                // 4.派单到T2
                                calendar.add(13, 10);

                                valueMap.put("operateDeptId", "12201");
                                valueMap.put("operaterContact", "");

                                valueMap.put("operateUserId", "fangmin");
                                valueMap.put("operateRoleId", "8aa0813b1c6f2386011c6f39c8350027");
                                valueMap.put("mainId", main.getId());
                                valueMap.put("aiid", T1Task.getId());
                                valueMap.put("piid", T1Task.getProcessId());
                                valueMap.put("operateTime", calendar.getTime());
                                valueMap.put("operateType", "1");
                                valueMap.put("hasNextTaskFlag", "true");

                                valueMap.put("activeTemplateId", "FirstExcuteHumTask");

                                valueMap.put("phaseId", "SecondExcuteTask");

                                valueMap.put("dealPerformer", subroleid);// 综合班组
                                valueMap.put("dealPerformerLeader", subroleid);// 综合班组
                                valueMap.put("dealPerformerType", "subrole");
                                valueMap.put("toOrgRoleId", subroleid);// 综合班组

                                operateUserId = "fangmin";
                            }

                        }

                        //自能派单驳回，无线专业 mainNetSortOne（101010401）start


                        if ("".equals(mainT2RejectionNum)) {
                            mainT2RejectionNum = "1";
                            System.out.println("mainT2RejectionNum==if1==liu==" + mainT2RejectionNum);
                        } else if ("1".equals(mainT2RejectionNum) || "2".equals(mainT2RejectionNum)) {
                            mainT2RejectionNum = String.valueOf(Integer.parseInt(mainT2RejectionNum) + 1);
                            System.out.println("mainT2RejectionNum==if2==liu==" + mainT2RejectionNum);
                        } else if ("3".equals(mainT2RejectionNum)) {
//					表示以前驳回了3次，这是最后一次驳回（第4次），此次驳回后，流程自动流转到T2
                            mainT2RejectionNum = "4";
                            //故障地市不为空，自动流转T2getMainFaultGenerantCity
                            System.out.println("mainT2RejectionNum==if3==liu==" + mainT2RejectionNum);
                            System.out.println("mainT2RejectionNum==if3==main.getToDeptId()==" + main.getToDeptId());
                            if (!"".equals(StaticMethod.nullObject2String(main.getToDeptId()))) {
//						根据故障城市查询对应的subroleid
                                INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
                                String subroleid = netOwnershipwirelessManager.getSubroleId(StaticMethod.nullObject2String(main.getToDeptId()));
//						流程自动流转到T2
//						1.添加link表驳回信息（T2驳回到T1）
                                String deptId = "";
                                if (user != null) {
                                    deptId = user.getDeptid();
                                }

                                GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();

//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						Date thisdate = dateFormat.parse(operate_time);
//						
//						calendar.setTime(thisdate);
                                CommonFaultLink Relink = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
                                String linkId = UUIDHexGenerator.getInstance().getID();
                                Relink.setId(linkId);
                                Relink.setMainId(StaticMethod.nullObject2String(main.getId()));
                                Relink.setOperateType(new Integer(4));
                                Relink.setOperateTime(calendar.getTime());
                                Relink.setOperateDay(calendar.get(5));
                                Relink.setOperateMonth(calendar.get(2) + 1);
                                Relink.setOperateYear(calendar.get(1));
                                Relink.setAcceptFlag(new Integer(0));
                                Relink.setPreLinkId(StaticMethod.nullObject2String(linkMap.get("id")));
                                Relink.setActiveTemplateId("SecondExcuteHumTask");
                                Relink.setToOrgType(new Integer(0));
                                Relink.setCompleteFlag(new Integer(0));
                                Relink.setOperateUserId(StaticMethod.nullObject2String(operateUserId));
//						Relink.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
                                Relink.setOperateDeptId(deptId);
                                String correlationKey = UUIDHexGenerator.getInstance().getID();
                                Relink.setCorrelationKey(correlationKey);
                                Relink.setTemplateFlag(0);
                                //T1link.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
                                Relink.setPiid(StaticMethod.nullObject2String(main.getPiid()));
                                Relink.setToOrgRoleId("8aa0813b1c6f2386011c6f39c8350027");
                                linkservice.addLink(Relink);
                                //2.T1确认受理
//						calendar.add(13, 30);
                                CommonFaultLink T1link61 = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
                                T1link61.setId(UUIDHexGenerator.getInstance().getID());
                                T1link61.setOperateType(new Integer("61"));
                                T1link61.setActiveTemplateId("FirstExcuteHumTask");
                                T1link61.setOperateTime(calendar.getTime());
                                T1link61.setOperateDay(calendar.get(5));
                                T1link61.setOperateMonth(calendar.get(2) + 1);
                                T1link61.setOperateYear(calendar.get(1));
                                T1link61.setMainId(StaticMethod.nullObject2String(main.getId()));
                                T1link61.setToOrgRoleId(subroleid);//综合班组
                                T1link61.setPreLinkId(linkId);
                                T1link61.setNodeAccessories("");
                                T1link61.setToOrgType(new Integer(0));
                                T1link61.setCompleteFlag(new Integer(0));
                                T1link61.setOperateUserId("fangmin");
                                T1link61.setOperateRoleId("8aa0813b1c6f2386011c6f39c8350027");
                                T1link61.setOperateDeptId("12201");
                                T1link61.setTemplateFlag(0);
                                linkservice.addLink(T1link61);

                                //3.T1增加task记录
//						ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
                                CommonFaultTask T1Task = (CommonFaultTask) taskservice.getTaskModelObject().getClass().newInstance();
                                try {
                                    T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                T1Task.setTaskName("FirstExcuteHumTask");
                                T1Task.setTaskDisplayName("T1处理");
                                T1Task.setFlowName("CommonFaultMainFlowProcess");
                                T1Task.setSendTime((Date) main.getSendTime());
                                T1Task.setSheetKey(StaticMethod.nullObject2String(main.getId()));
                                T1Task.setTaskStatus("5");
                                T1Task.setSheetId(StaticMethod.nullObject2String(main.getSheetId()));
                                T1Task.setTitle(StaticMethod.nullObject2String(main.getTitle()));
                                T1Task.setOperateType("subrole");
                                T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
                                T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
                                T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
                                T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
                                T1Task.setOperateRoleId(StaticMethod.nullObject2String("8aa0813b1c6f2386011c6f39c8350027"));
                                T1Task.setTaskOwner(StaticMethod.nullObject2String("fangmin"));
                                T1Task.setIfWaitForSubTask("false");
                                T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
                                T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
                                taskservice.addTask(T1Task);


//						4.派单到T2
//						calendar.add(13, 30);
                                String operateTime = StaticMethod.Cal2String(calendar);
                                valueMap.put("operateDeptId", "12201");
                                valueMap.put("operaterContact", "");

                                valueMap.put("operateUserId", "fangmin");
                                valueMap.put("operateRoleId", "8aa0813b1c6f2386011c6f39c8350027");
                                valueMap.put("mainId", main.getId());
                                valueMap.put("aiid", T1Task.getId());
                                valueMap.put("piid", T1Task.getProcessId());
                                valueMap.put("operateTime", operateTime);
//						valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
//						valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());
                                valueMap.put("operateType", "1");
                                valueMap.put("hasNextTaskFlag", "true");

                                valueMap.put("activeTemplateId", "FirstExcuteHumTask");

                                valueMap.put("phaseId", "SecondExcuteTask");

                                valueMap.put("dealPerformer", subroleid);//综合班组
                                valueMap.put("dealPerformerLeader", subroleid);//综合班组
                                valueMap.put("dealPerformerType", "subrole");
                                valueMap.put("toOrgRoleId", subroleid);//综合班组

                                operateUserId = "fangmin";

                            }

                        }

                        main.setMainT2RejectionNum(mainT2RejectionNum);
                        sheetMap1.put("main", main);
                        sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
                        sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
                        columnMap.put("selfSheet", sheetMap1);
                        valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
                        sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);

                        Date date = new Date();
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String currentDate = sf.format(date);
                        String typeID = "02";
                        String type = "驳回";
                        String json = "{'data':" + "{'type':'" + typeID + "','"
                                + "name':['工单号','处理类型', '处理时间'],'" + "values':" + "[ "
                                + "['" + StaticMethod.nullObject2String(main.getSheetId()) + "','" + type + "','" + currentDate + "']" + "]"
                                + "}" + "}";
                        System.out.println("cxfivrcj:json=" + json);
                        boolean cxfivrcjresult = false;
                        try {
                            Service_ServiceLocator service = new Service_ServiceLocator();
                            Service_PortType binding = (Service_PortType) service.getJSONServicePort();
                            cxfivrcjresult = binding.insert(json);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println("cxfivrcj:result=" + cxfivrcjresult);

                        result = "Status=0;Errlist=";
                    } else {
                        result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于已接单未处理中状态,无法进行工单驳回操作！";
                        return result;
                    }
                } else {
                    result = "Status=-1;sheetDetail=;Errlist=工单驳回接口传入参数不正确,请查证！";
                    return result;
                }
            } else {
                result = "Status=-1;sheetDetail=;Errlist=工单驳回接口没有传入opDetail参数,请查证！";
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "Status=-1;sheetDetail=;Errlist=工单驳回接口出错！详细信息为" + e.getMessage();
        }
        return result;
    }
}
