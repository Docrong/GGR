package com.boco.eoms.sheet.mofficedata.service.sync;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.mofficedata.util.MofficeUtil;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataLink;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataMain;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataSubLink;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataTask;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataLinkManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataMainManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataProMatchManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataSubLinkManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataTaskManager;
import com.boco.eoms.util.StaxParser;

/**
 * 用于局数据同步局数据系统工单状态接口服务
 *
 * @author weichao
 * @date 2016-3-23下午01:39:40
 */
public class MoSyncServices {

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
     * 判断当前工单是否已经接单
     *
     * @param serSupplier
     * @param callTime
     * @return
     */
    public String isConfirmSheet(String serialNo, String serSupplier, String serCaller, String callerPwd,
                                 String callTime, String opDetail) {
        BocoLog.info(this, "==局数据工单同步服务开始（SYNC SERVICES START!）==");
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
            MofficeDataMain baseMain = (MofficeDataMain) mainMgr.getMainBySheetId(serialNo);
            if (null != baseMain && !"".equals(baseMain.getId())) {
                HashMap sheetMap = new HashMap();
                sheetMap = StaxParser.getInstance().getOpdetail4HashMap(opDetail);
                // 将接口传过来的数据转和eoms对应字段一一对应
                String filePath = "classpath:config/mofficedata-util.xml";
                InterfaceUtilProperties properties = new InterfaceUtilProperties();
                filePath = StaticMethod.getFilePathForUrl(filePath);
                Map map = properties.getMapFromXml(sheetMap, filePath, "isConfirmWorkSheet");
                String tmppreLinkId = StaticMethod.nullObject2String(map.get("preLinkId"));
                if (!"".equals(tmppreLinkId)) {
                    // 查找待办的task
                    String condition = " sheetKey = '" + baseMain.getId() + "'"
                            + " and taskName='OfficeMadeTask' and taskStatus='8' and preLinkId='" + tmppreLinkId
                            + "'";
                    List baseTasks = taskMgr.getTasksByCondition(condition);
                    if (baseTasks != null && baseTasks.size() > 0) {
                        return "0";
                    } else {
                        condition = " sheetKey = '" + baseMain.getId() + "'"
                                + " and taskName='OfficeMadeTask' and taskStatus='8' and correlationKey='" + tmppreLinkId + "'";
                        baseTasks = taskMgr.getTasksByCondition(condition);
                        if (baseTasks != null && baseTasks.size() > 0) {
                            return "0";
                        } else {
                            BocoLog.info(this, "EOMS没有对应的任务信息，sheetID==" + baseMain.getSheetId());
                            return "1";
                        }

                    }
                } else {
                    BocoLog.info(this, "the column preLinkId=" + tmppreLinkId);
                    result = "1";
                }
            } else {
                BocoLog.info(this, "can't find the eoms's sheet and sheetid=" + serialNo);
                result = "1";
            }

        } catch (Exception e) {
            result = "1";
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 同步局数据工单状态 关联到工单流转信息对应的局数据制作步骤下面
     *
     * @param serialNo
     * @param serSupplier
     * @param serCaller
     * @param callerPwd
     * @param callTime
     * @param opDetail
     * @return
     * @date 2016-3-23下午01:44:30
     * @author weichao
     */
    public String syncWorkSheet(String serialNo, String serSupplier, String serCaller, String callerPwd,
                                String callTime, String opDetail) {
        BocoLog.info(this, "==局数据工单同步服务开始（SYNC SERVICES START!）==");
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
            MofficeDataMain baseMain = (MofficeDataMain) mainMgr.getMainBySheetId(serialNo);
            if (null != baseMain && !"".equals(baseMain.getId())) {
                HashMap sheetMap = new HashMap();
                sheetMap = StaxParser.getInstance().getOpdetail4HashMap(opDetail);
                // 将接口传过来的数据转和eoms对应字段一一对应
                String filePath = "classpath:config/mofficedata-util.xml";
                InterfaceUtilProperties properties = new InterfaceUtilProperties();
                filePath = StaticMethod.getFilePathForUrl(filePath);
                Map map = properties.getMapFromXml(sheetMap, filePath, "syncJWorkSheet");
                String sheetStatus = StaticMethod.nullObject2String(map.get("sheetStatus"));
                String tmppreLinkId = StaticMethod.nullObject2String(map.get("preLinkId"));
                String loglog = StaticMethod.nullObject2String(map.get("resultDe"));
                BocoLog.info(this, "resultDe=" + loglog);
                if (!"".equals(sheetStatus) && !"".equals(tmppreLinkId)) {
                    // 查找待办的task
                    String condition = " sheetKey = '" + baseMain.getId() + "'"
                            + " and taskName='OfficeMadeTask' and taskStatus='8' and preLinkId='" + tmppreLinkId + "'";
                    List baseTasks = taskMgr.getTasksByCondition(condition);

                    MofficeDataTask baseTask = null;
                    if (baseTasks != null && baseTasks.size() > 0) {
                        baseTask = (MofficeDataTask) baseTasks.get(0);
                    } else {
                        condition = " sheetKey = '" + baseMain.getId() + "'"
                                + " and taskName='OfficeMadeTask' and taskStatus='8' and correlationKey='" + tmppreLinkId + "'";
                        baseTasks = taskMgr.getTasksByCondition(condition);
                        if (baseTasks != null && baseTasks.size() > 0) {
                            baseTask = (MofficeDataTask) baseTasks.get(0);
                        } else {
                            BocoLog.info(this, "EOMS没有对应的任务信息，sheetID==" + baseMain.getSheetId());
                            return "EOMS没有对应的任务信息";
                        }

                    }

                    if (sheetStatus.equals("101400109") || sheetStatus.equals("101400110")) {// 工单完成或者撤销工单，需要驱动流程流转到下一个环节
                        result = this.transferTask2Next(baseTask, baseMain, result, map);
                    } else {// 保存link信息
                        this.saveSubLink(map, baseMain.getSheetId(), baseTask.getPreLinkId());
                        if ("101400102".equals(sheetStatus)) {//核查完成
                            baseMain.setMainStyle6(loglog);
                        } else if ("101400103".equals(sheetStatus)) {//脚本制作完成
                            baseMain.setMainStyle7(loglog);
                        } else if ("101400105".equals(sheetStatus)) {//数据拨测完成
                            baseMain.setMainStyle8(loglog);
                        } else if ("101400107".equals(sheetStatus)) {//话单测试完成
                            baseMain.setMainStyle10(loglog);
                        } else if ("101400108".equals(sheetStatus)) {//信令测试完成
                            baseMain.setMainStyle9(loglog);
                        }
                        mainMgr.saveOrUpdateMain(baseMain);
                    }
                } else {
                    BocoLog.info(this, "the column sheetStatus=" + sheetStatus);
                    BocoLog.info(this, "the column preLinkId=" + tmppreLinkId);
                    result = "传递字段为空，请检查!";
                }
            } else {
                BocoLog.info(this, "can't find the eoms's sheet and sheetid=" + serialNo);
                result = "找不到对应的EOMS工单!";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 插入局数据的操作环节
     *
     * @param map
     * @param sheetId
     * @param preLinkId
     * @throws Exception
     * @date 2016-3-28上午08:56:18
     * @author weichao
     */
    private void saveSubLink(Map map, String sheetId, String preLinkId) throws Exception {
        IMofficeDataSubLinkManager subLinkMgr = (IMofficeDataSubLinkManager) ApplicationContextHolder.getInstance()
                .getBean("iMofficeDataSubLinkManager");
        MofficeDataSubLink newSubLink = (MofficeDataSubLink) subLinkMgr.getSubLinkObject().getClass().newInstance();
        SheetBeanUtils.populateMap2Bean(newSubLink, map);
        newSubLink.setId(UUIDHexGenerator.getInstance().getID());
        newSubLink.setCreateTime(new Date());
        newSubLink.setSheetId(sheetId);
        newSubLink.setPreLinkId(preLinkId);
        subLinkMgr.saveOrUpdate(newSubLink);
    }

    /**
     * 更新其他的subLink使之与linb表的记录关联起来
     *
     * @param liId
     * @param preLinkId
     * @param map
     * @param sheetId
     * @throws Exception
     * @date 2016-3-28上午08:55:38
     * @author weichao
     */
    private void updateSubLinks(String liId, String preLinkId, Map map, String sheetId) throws Exception {
        IMofficeDataSubLinkManager subLinkMgr = (IMofficeDataSubLinkManager) ApplicationContextHolder.getInstance()
                .getBean("iMofficeDataSubLinkManager");
        MofficeDataSubLink newSubLink = (MofficeDataSubLink) subLinkMgr.getSubLinkObject().getClass().newInstance();
        SheetBeanUtils.populateMap2Bean(newSubLink, map);
        newSubLink.setId(UUIDHexGenerator.getInstance().getID());
        newSubLink.setCreateTime(new Date());
        newSubLink.setSheetId(sheetId);
        newSubLink.setParentLinkId(liId);
        subLinkMgr.saveOrUpdate(newSubLink);
        subLinkMgr.updateOthers(liId, preLinkId);


    }

    /**
     * 将当前环节流转到质检环节
     *
     * @param baseMain
     * @param operateRoleId
     * @param result
     * @return
     * @throws Exception
     * @date 2016-3-25下午03:09:43
     * @author weichao
     */
    private String transferTask2Next(MofficeDataTask baseTask, MofficeDataMain baseMain,
                                     String result, Map map) throws Exception {
        IMofficeDataLinkManager linkMgr = (IMofficeDataLinkManager) ApplicationContextHolder.getInstance().getBean(
                "iMofficeDataLinkManager");
        IBusinessFlowService businessFlowService = (IBusinessFlowService) ApplicationContextHolder.getInstance()
                .getBean("iMofficeDataFlowManager");
        IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager) ApplicationContextHolder.getInstance()
                .getBean("iMofficeDataProMatchManager");
        // 查找确认受理的link
        String linkCondition = " from MofficeDataLink where mainId= '" + baseMain.getId()
                + "' and activeTemplateId='OfficeMadeTask' " + "and operateType='61' and operateUserId='"
                + baseTask.getTaskOwner() + "' and preLinkId='" + baseTask.getPreLinkId() + "' ";
        List baseLinks = linkMgr.getLinksBySql(linkCondition);
        MofficeDataLink baseLink = null;
        if (baseLinks != null && baseLinks.size() > 0) {
            baseLink = (MofficeDataLink) baseLinks.get(0);
        } else {
            BocoLog.info(this, "EOMS没有对应的环节信息，sheetID==" + baseMain.getSheetId());
            return "EOMS没有对应的环节信息";
        }
        String correKey = StaticMethod.nullObject2String(baseTask.getCorrelationKey());
        String prelinkId = StaticMethod.nullObject2String(baseTask.getPreLinkId());
        MofficeDataProMatch mat = null;
        if (!"".equals(prelinkId)) {/*派单的时候选的制作人是人员*/
            mat = proMgr.getProMatchObjectByPreLinkId(prelinkId);
        }
        if (null == mat && !"".equals(correKey)) {/*派单的时候选的制作人是角色*/
            mat = proMgr.getProMatchObjectByCorreKey(correKey);
        }

        MofficeDataLink newLink = (MofficeDataLink) linkMgr.getLinkObject().getClass().newInstance();
        String accessories = getaccessories(baseMain);

        // 构造基础的link信息
        Date now = new Date();// 现在时间
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);// 获取年份
        int month = ca.get(Calendar.MONTH);// 获取月份
        int day = ca.get(Calendar.DATE);// 获取日
        if (accessories.length() > 0) {
            newLink.setNodeAccessories(accessories);
        }
        newLink.setMainId(baseMain.getId());
        newLink.setOperateType(Integer.valueOf("103"));
        newLink.setOperateTime(now);
        newLink.setOperateUserId(baseTask.getTaskOwner());
        newLink.setPreLinkId(baseTask.getPreLinkId());
        newLink.setPiid(baseMain.getPiid());
        newLink.setAiid(baseTask.getId());
        if (null != mat) {
            newLink.setLinkStyle1(mat.getDeviceName());/*设备厂家信息*/
            newLink.setLinkStyle2(mat.getBuissType());/*业务信息*/
        } else {
            BocoLog.info(this, "=未找到mat对象=");
            newLink.setLinkStyle1("oldSheet");/*未找到mat对象*/
        }

        newLink.setActiveTemplateId("OfficeMadeTask");
        newLink.setOperateRoleId(baseTask.getOperateRoleId());
        newLink.setCorrelationKey(baseMain.getCorrelationKey());
        newLink.setOperateDeptId(baseLink.getOperateDeptId());
        newLink.setOperaterContact(baseLink.getOperaterContact());
        newLink.setOperateYear(year);
        newLink.setOperateMonth(month);
        newLink.setOperateDay(day);
        newLink.setPreLinkId(baseTask.getPreLinkId());
        baseTask.setTaskStatus("5");

        try {
            String dealPerformer = baseMain.getSendRoleId();
            String dealPerformerLeader = baseMain.getSendUserId();
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
            String liId = StaticMethod.nullObject2String(liMap.get("id"));
            BocoLog.info(this, "liId=" + liId);
            this.updateSubLinks(liId, baseTask.getPreLinkId(), map, baseMain.getSheetId());
            opMap.put("phaseId", "DataCheckTask");
            opMap.put("dealPerformer", dealPerformer);
            opMap.put("dealPerformerLeader", dealPerformerLeader);
            opMap.put("dealPerformerType", "user");
            opMap.put("hasNextTaskFlag", "com.boco.eoms.sheet.mofficedata.model.MofficeDataMain");
            WpsMap.put("operate", opMap);

            HashMap sessionMap = new HashMap();
            sessionMap.put("userId", baseTask.getTaskOwner());
            sessionMap.put("password", "111");
            businessFlowService.completeHumanTask(baseTask.getId(), WpsMap, sessionMap);
            BocoLog.info(this, "sheetid is " + baseMain.getSheetId() + " 进入质检成功，处理人===" + dealPerformer);
        } catch (Exception e) {
            e.printStackTrace();
            BocoLog.info(this, "进入质检环节失败！" + baseMain.getSheetId());
            result = "EOMS工单处理异常，请联系管理员";
        }
        return result;

    }

    private String getaccessories(MofficeDataMain baseMain) {
        String accessories = "";
        String syslog;
        if ((syslog = baseMain.getMainStyle6()) != null) {
            String result = MofficeUtil.DownFile(syslog, "mofficedata");
            if (result != null) {
                if (accessories.length() > 0) {
                    accessories += ",";
                }
                accessories += "'" + result + "'";
            }
        }

        if ((syslog = baseMain.getMainStyle7()) != null) {
            String result = MofficeUtil.DownFile(syslog, "mofficedata");
            if (result != null) {
                if (accessories.length() > 0) {
                    accessories += ",";
                }
                accessories += "'" + result + "'";
            }
        }

        if ((syslog = baseMain.getMainStyle8()) != null) {
            String result = MofficeUtil.DownFile(syslog, "mofficedata");
            if (result != null) {
                if (accessories.length() > 0) {
                    accessories += ",";
                }
                accessories += "'" + result + "'";
            }
        }

        if ((syslog = baseMain.getMainStyle9()) != null) {
            String result = MofficeUtil.DownFile(syslog, "mofficedata");
            if (result != null) {
                if (accessories.length() > 0) {
                    accessories += ",";
                }
                accessories += "'" + result + "'";
            }
        }

        if ((syslog = baseMain.getMainStyle10()) != null) {
            String result = MofficeUtil.DownFile(syslog, "mofficedata");
            if (result != null) {
                if (accessories.length() > 0) {
                    accessories += ",";
                }
                accessories += "'" + result + "'";
            }
        }

        return accessories;
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