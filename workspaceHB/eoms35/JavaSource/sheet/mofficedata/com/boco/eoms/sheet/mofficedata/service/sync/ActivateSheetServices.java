package com.boco.eoms.sheet.mofficedata.service.sync;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataLink;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataMain;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataTask;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataLinkManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataMainManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataProMatchManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataTaskManager;
import com.boco.eoms.util.StaxParser;

public class ActivateSheetServices {

    /**
     * 收到局数据系统握手信号
     *
     * @param serSupplier
     * @param callTime
     * @return
     */
    public String isAlive(String serSupplier, String callTime) {
        BocoLog.info(this, "收到局数据系统握手信号");
        String isAliveResult = "0";
        return isAliveResult;
    }

    /**
     * 激活当前处于自动质检状态的工单
     */
    public String activateWorkSheet(String serialNo, String serSupplier, String serCaller, String callerPwd,
                                    String callTime, String opDetail) {
        BocoLog.info(this, "==局数据工单激活服务开始（ACTIVATE SERVICES START!）==");
        BocoLog.info(this, "serialNo=" + serialNo);
        BocoLog.info(this, "opDetail=" + opDetail);
        BocoLog.info(this, "serSupplier=" + serSupplier);
        BocoLog.info(this, "serCaller=" + serCaller);
        BocoLog.info(this, "callerPwd=" + callerPwd);
        BocoLog.info(this, "callTime=" + callTime);

        String result = "0";
        try {
            IMofficeDataMainManager mainMgr = (IMofficeDataMainManager) ApplicationContextHolder.getInstance().getBean(
                    "iMofficeDataMainManager");
            IMofficeDataTaskManager taskMgr = (IMofficeDataTaskManager) ApplicationContextHolder.getInstance().getBean(
                    "iMofficeDataTaskManager");
            IMofficeDataLinkManager linkMgr = (IMofficeDataLinkManager) ApplicationContextHolder.getInstance().getBean(
                    "iMofficeDataLinkManager");
            IBusinessFlowService businessFlowService = (IBusinessFlowService) ApplicationContextHolder.getInstance()
                    .getBean("iMofficeDataFlowManager");
            IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager) ApplicationContextHolder.getInstance()
                    .getBean("iMofficeDataProMatchManager");
            String userId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.userId");
            String deptId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.deptId");
            String subRoleId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.subRoleId");
            String contact = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.contact");
            MofficeDataMain baseMain = (MofficeDataMain) mainMgr.getMainBySheetId(serialNo);
            if (null != baseMain && !"".equals(baseMain.getId())) {
                HashMap sheetMap = new HashMap();
                sheetMap = StaxParser.getInstance().getOpdetail4HashMap(opDetail);
                // 将接口传过来的数据转和eoms对应字段一一对应
                String filePath = "classpath:config/mofficedata-util.xml";
                InterfaceUtilProperties properties = new InterfaceUtilProperties();
                filePath = StaticMethod.getFilePathForUrl(filePath);
                Map map = properties.getMapFromXml(sheetMap, filePath, "activateSheet");
                String tkid = StaticMethod.nullObject2String(map.get("preLinkId"));//此id为task表审批或者质检环节的task记录的id
                if (!"".equals(tkid)) {
                    MofficeDataTask baseTask = null;
                    String newtaskname = null;
                    String dealPerformer = null;
                    String dealPerformerType = null;
                    MofficeDataProMatch mat = null;
                    if (tkid.indexOf("_TK") > -1) {
                        MofficeDataTask ftask = (MofficeDataTask) taskMgr.getSinglePO(tkid);
                        /*如果是老的一派一的任务，则根据link存储的相关信息找对应的task待办 add by weichao 20161207*/
                        MofficeDataLink baseLink = (MofficeDataLink) linkMgr.getSingleLinkPO(ftask.getCurrentLinkId());
                        newtaskname = baseLink.getLinkRejectHt();
                        String condition = " sheetKey = '" + baseMain.getId() + "' and taskName='"
                                + newtaskname + "' and taskStatus='2' and preLinkId='" + ftask.getCurrentLinkId() + "'";
                        BocoLog.info(ActivateSheetServices.class, "tmpsql4check==" + condition);
                        List baseTasks = taskMgr.getTasksByCondition(condition);

                        if (baseTasks != null && baseTasks.size() > 0) {
                            baseTask = (MofficeDataTask) baseTasks.get(0);
                        } else {
                            BocoLog.info(this, "EOMS没有对应的任务信息，sheetID==" + baseMain.getSheetId());
                            return "EOMS没有对应的任务信息";
                        }
                        dealPerformer = baseLink.getLinkDealDesc();
                        dealPerformerType = "user";
                    } else {/*如果是新的任务，则根据MofficeDataProMatch的correKey找对应的task待办 add by weichao 20161207*/
                        mat = proMgr.getProMatchObjectByCorreKey(tkid);
                        newtaskname = mat.getPhaseName();
                        if (null != mat && !"".equals(mat.getId())) {
                            String condition = " sheetKey = '" + baseMain.getId() + "'" + " and taskName='"
                                    + mat.getPhaseName() + "' and taskStatus='2' and correlationKey='" + tkid + "'";
                            BocoLog.info(ActivateSheetServices.class, "tmpsql4check==" + condition);
                            List baseTasks = taskMgr.getTasksByCondition(condition);
                            if (baseTasks != null && baseTasks.size() > 0) {
                                baseTask = (MofficeDataTask) baseTasks.get(0);
                            } else {
                                BocoLog.info(this, "EOMS没有对应的任务信息，sheetID==" + baseMain.getSheetId());
                                return "EOMS没有对应的任务信息";
                            }
                            dealPerformer = mat.getProducerId();
                            dealPerformerType = mat.getProducerType();
                        } else {
                            BocoLog.info(this, "EOMS没有对应的业务信息，sheetID==" + baseMain.getSheetId());
                            return "EOMS没有对应的业务信息";
                        }

                    }
                    String isCreated = StaticMethod.nullObject2String(map.get("isCreated"));
                    BocoLog.info(ActivateSheetServices.class, "isCreated==" + isCreated);

                    MofficeDataLink newLink = (MofficeDataLink) linkMgr.getLinkObject().getClass().newInstance();

                    // 构造基础的link信息
                    Date now = new Date();// 现在时间
                    Calendar ca = Calendar.getInstance();
                    int year = ca.get(Calendar.YEAR);// 获取年份
                    int month = ca.get(Calendar.MONTH);// 获取月份
                    int day = ca.get(Calendar.DATE);// 获取日
                    newLink.setMainId(baseMain.getId());
                    if ("1030101".equals(isCreated)) {
                        if ("AutoCheckTask".equals(newtaskname)) {
                            newLink.setOperateType(Integer.valueOf("114"));
                        } else if ("AutoACheckTask".equals(newtaskname)) {
                            newLink.setOperateType(Integer.valueOf("113"));
                        }
                    } else {
                        if ("AutoCheckTask".equals(newtaskname)) {
                            newLink.setOperateType(Integer.valueOf("110"));
                        } else if ("AutoACheckTask".equals(newtaskname)) {
                            newLink.setOperateType(Integer.valueOf("112"));
                        }
                    }
                    newLink.setOperateTime(now);
                    newLink.setOperateUserId(userId);
                    newLink.setPreLinkId(baseTask.getPreLinkId());
                    newLink.setPiid(baseMain.getPiid());
                    newLink.setAiid(baseTask.getId());
                    newLink.setActiveTemplateId(newtaskname);
                    newLink.setOperateRoleId(subRoleId);
                    newLink.setCorrelationKey(baseMain.getCorrelationKey());
                    newLink.setOperateDeptId(deptId);
                    newLink.setOperaterContact(contact);
                    newLink.setOperateYear(year);
                    newLink.setOperateMonth(month);
                    newLink.setOperateDay(day);
                    newLink.setLinkAuditRe(StaticMethod.nullObject2String(map.get("isCreated")));//是否新建成功
                    newLink.setLinkAuditSu(StaticMethod.nullObject2String(map.get("backReason")));//退回原因
                    newLink.setPreLinkId(baseTask.getPreLinkId());
                    baseTask.setTaskStatus("5");

                    try {
                        BocoLog.info(ActivateSheetServices.class, "dealPerformer==" + dealPerformer);
                        HashMap columnMap = new HashMap();
                        columnMap.put("selfSheet", this.setNewInterfacePara());
                        newLink.setToOrgRoleId(dealPerformer);// 派往对象
                        Map linkMap = SheetBeanUtils.bean2Map(newLink);
                        Map mainMap = SheetBeanUtils.bean2Map(baseMain);

                        HashMap parameters = new HashMap();
                        parameters.putAll(mainMap);
                        parameters.putAll(linkMap);
                        this.setBaseMap(parameters);

                        String da = StaticMethod.date2String(now);
                        parameters.put("operateTime", da);

                        WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                        HashMap WpsMap = sm.prepareMap(parameters, columnMap);
                        Map opMap = (HashMap) WpsMap.get("operate");
                        Map liMap = (HashMap) WpsMap.get("link");
                        String liid = StaticMethod.nullObject2String(liMap.get("id"));
                        if ("1030101".equals(isCreated)) {
                            opMap.put("phaseId", "OfficeMadeTask");
                        } else {
                            if ("AutoCheckTask".equals(newtaskname)) {
                                opMap.put("phaseId", "RejectTask");
                            } else if ("AutoACheckTask".equals(newtaskname)) {
                                opMap.put("phaseId", "DataCheckTask");
                            }
                        }

                        opMap.put("dealPerformer", dealPerformer);
                        opMap.put("dealPerformerLeader", dealPerformer);
                        opMap.put("dealPerformerType", dealPerformerType);
                        opMap.put("hasNextTaskFlag", "com.boco.eoms.sheet.mofficedata.model.MofficeDataMain");
                        WpsMap.put("operate", opMap);

                        HashMap sessionMap = new HashMap();
                        sessionMap.put("userId", baseTask.getTaskOwner());
                        sessionMap.put("password", "111");
                        businessFlowService.completeHumanTask(baseTask.getId(), WpsMap, sessionMap);
                        BocoLog.info(this, "sheetid is " + baseMain.getSheetId() + " 处理完成，处理人===" + dealPerformer);
                        if ("1030101".equals(isCreated)) {
                            mat.setLinkId(liid);
                            proMgr.saveOrUpdate(mat);
                            result = "0," + liid;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        BocoLog.info(this, "进入质检环节失败！" + baseMain.getSheetId());
                        result = "1,EOMS工单处理异常，请联系管理员";
                    }
                    return result;

                } else {
                    BocoLog.info(this, "the column preLinkId=" + tkid);
                    result = "1,传递字段为空，请检查!";
                }

            } else {
                BocoLog.info(this, "can't find the eoms's sheet and sheetid=" + serialNo);
                result = "1,找不到对应的EOMS工单!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result====" + "result");
        BocoLog.info(this, "result" + result);
        return result;
    }

    private Object getPageColumnName() {
        return Constants.pageColumnName;
    }

    private Map setNewInterfacePara() throws Exception {
        IMofficeDataMainManager mainMgr = (IMofficeDataMainManager) ApplicationContextHolder.getInstance().getBean(
                "iMofficeDataMainManager");
        IMofficeDataLinkManager linkMgr = (IMofficeDataLinkManager) ApplicationContextHolder.getInstance().getBean(
                "iMofficeDataLinkManager");

        HashMap sheetMap = new HashMap();
        sheetMap.put("main", mainMgr.getMainObject().getClass().newInstance());
        sheetMap.put("link", linkMgr.getLinkObject().getClass().newInstance());
        sheetMap.put("operate", this.getPageColumnName());

        return sheetMap;
    }

    private String getMainBeanId() {
        return "iMofficeDataMainManager";
    }

    private Map setBaseMap(Map map) {
        try {
            IMofficeDataMainManager mainMgr = (IMofficeDataMainManager) ApplicationContextHolder.getInstance().getBean(
                    "iMofficeDataMainManager");
            IMofficeDataLinkManager linkMgr = (IMofficeDataLinkManager) ApplicationContextHolder.getInstance().getBean(
                    "iMofficeDataLinkManager");
            String mainBeanId = this.getMainBeanId();
            map.put("beanId", new String[]{mainBeanId});
            BocoLog.info(this, "mainClassName=" + mainMgr.getMainObject().getClass().getName());
            BocoLog.info(this, "linkClassName=" + linkMgr.getLinkObject().getClass().getName());
            map.put("mainClassName", new String[]{mainMgr.getMainObject().getClass().getName()});
            map.put("linkClassName", new String[]{linkMgr.getLinkObject().getClass().getName()});

        } catch (Exception err) {
            err.printStackTrace();
        }
        return map;
    }

}
