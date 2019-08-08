package com.boco.eoms.sheet.commonfault.service.bo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commonfaultfccos.GroupFaultServiceLocator;
import com.boco.eoms.commonfaultfccos.GroupFault_PortType;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
//import com.boco.eoms.sheet.commonfault.interfaces.GroupFaultServiceLocator;
//import com.boco.eoms.sheet.commonfault.interfaces.GroupFault_PortType;
import com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkLocator;
import com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_PortType;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commonfault.util.InterfaceAutoDistribute;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.netownership.model.NetOwnership;
import com.boco.eoms.sheet.netownership.service.INetOwnershipManager;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;
import com.boco.eoms.sheetflowline.mgr.IAutoDealSOPMgr;
import com.boco.eoms.sheetflowline.mgr.IPreAllocatedMgr;
import com.boco.eoms.sheetflowline.mgr.IPreAllocatedNewMgr;
import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.model.AutoDealSopSheet;
import com.boco.eoms.sheetflowline.model.PreAllocated;
import com.boco.eoms.sheetflowline.model.PreAllocatedNew;
import com.boco.eoms.sheetflowline.model.PreAllocatedSepcail;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class CommonFaultBO {
    private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";

    public static String performAdd(Map map)
            throws Exception {
        System.out.println("start performAdd.");
        String serCaller = StaticMethod.nullObject2String(map.get("serCaller"));
        System.out.println("serCaller:" + serCaller);
        String userId = StaticMethod.nullObject2String(map.get("sender"));
        if (userId.equals(""))
            map.put("sender", serCaller);
        map.put("interfaceSender", serCaller);
        map.put("mainIfAlarmSend", serCaller);
        InterfaceUtilProperties properties = new InterfaceUtilProperties();
        String filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfault-util.xml");
        map = properties.getMapFromXml(map, filePath, "newWorkSheet");
        String time_stamp = StaticMethod.nullObject2String(map.get("time_stamp"));
        /**设备名称*/
//    String equipmentName = StaticMethod.nullObject2String(map.get("equipmentName"));
//    System.out.println("equipmentName=****************"+map.get("equipmentName"));
        /**设备名称*/
        System.out.println("lizhi:time_stamph=" + time_stamp);
        if ((!"".equals(time_stamp)) && (time_stamp != null)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = "1970-01-01 08:00:00";
            Date timeDate = format.parse(timeFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(timeDate);
            cal.add(13, Integer.parseInt(time_stamp));
            timeDate = cal.getTime();
            System.out.println("front:" + timeDate);
            String time_stamp2 = format.format(timeDate);
            System.out.println("lizhi:time_stamp2=" + time_stamp2);
            map.put("mainDetectFaultTime", time_stamp2);
        }
        String mainIdInfo = StaticMethod.nullObject2String(map.get("alarmStaId"));
        if ((!"".equals(mainIdInfo)) && (mainIdInfo != null)) {
            INetOwnershipManager netOwnershipManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
            ArrayList noInterPreSolveList = new ArrayList();
            String tableName = "commonfault_predeal_alarmid";
            noInterPreSolveList = (ArrayList) netOwnershipManager.getListByAlarmId(tableName, mainIdInfo);
            if (noInterPreSolveList.size() == 0)
                map.put("intelligentPreSolve", "1030102");
            else
                map.put("intelligentPreSolve", "1030101");
        } else {
            map.put("intelligentPreSolve", "1030102");
        }
        System.out.println("ph----itelligentPreSolve:mainId:" + mainIdInfo);
        System.out.println("full map finish");

        if ((!"".equals(mainIdInfo)) && (mainIdInfo != null)) {
            if ("900-900-00-000001".equals(mainIdInfo)) {
                map.put("netOptimization", "1");
            } else {
                INetOwnershipManager processManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
                String alarmTableName = "commonfault_alarmId_process";
                ArrayList noProcess = new ArrayList();
                noProcess = (ArrayList) processManager.getListByAlarmId(alarmTableName, mainIdInfo);
                System.out.println("--------netOptimization-----------listsize" + noProcess.size());
                if (noProcess.size() > 0)
                    map.put("netOptimization", "2");
                else
                    map.put("netOptimization", "3");
            }
        } else map.put("netOptimization", "3");

        System.out.println("00000000000netOptimization00000000" + map.get("netOptimization"));

        ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
        ITawSystemAreaManager areaMgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
        ICommonFaultMainManager iCommonFaultMainManager = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ICommonFaultLinkManager iCommonFaultLinkManager = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        String pageColumnName = "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String";
        HashMap columnMap = new HashMap();
        HashMap sheetMap = new HashMap();
        sheetMap.put("main", iCommonFaultMainManager.getMainObject().getClass().newInstance());
        sheetMap.put("link", iCommonFaultLinkManager.getLinkObject().getClass().newInstance());
        sheetMap.put("operate", pageColumnName);
        columnMap.put("selfSheet", sheetMap);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String processTemplateName = "CommonFaultMainFlowProcess";
        String operateName = "newWorksheet";
        map.put("sendTime", new String[]{
                dateFormat.format(date)});

        map.put("correlationKey", new String[]{
                UUIDHexGenerator.getInstance().getID()});

        String NeTypeCode = StaticMethod.nullObject2String(map.get("NeType"));
        String mainNetSortOne = "";
        String mainNetSortTwo = "";
        String mainNetSortThree = "";
        if (NeTypeCode.length() > 0) {
            String rootId = XmlManage.getFile("/config/commonfault-util.xml").getProperty("rootNeTypeId");
            mainNetSortThree = dictMgr.getDictIdByDictCode(rootId, NeTypeCode);
            if ((mainNetSortThree == null) || (mainNetSortThree.length() == 0))
                System.out.println("没有找到映射的网络分类");
            else
                try {
                    System.out.println("mainNetSortThree=" + mainNetSortThree);
                    TawSystemDictType dict3 = dictMgr.getDictByDictId(mainNetSortThree);
                    if (dict3 != null) {
                        map.put("mainNetSortThree", mainNetSortThree);
                        String pId = dict3.getParentDictId();
                        TawSystemDictType dict2 = dictMgr.getDictByDictId(pId);
                        if (dict2 != null) {
                            mainNetSortTwo = dict2.getDictId();
                            map.put("mainNetSortTwo", mainNetSortTwo);
                            pId = dict2.getParentDictId();
                            if (!pId.equals("-1")) {
                                TawSystemDictType dict1 = dictMgr.getDictByDictId(pId);
                                if (dict1 != null)
                                    mainNetSortOne = dict1.getDictCode();
                                map.put("mainNetSortOne", mainNetSortOne);
                            }
                        }
                    } else {
                        System.out.println("dict3 is null");
                    }
                } catch (Exception err) {
                    System.out.println("没有找到映射的网络分类");
                }
        }
        System.out.println("**********************mainNetSortOne=" + mainNetSortOne);
        System.out.println("**********************mainNetSortTwo=" + mainNetSortTwo);
        System.out.println("**********************mainNetSortThree=" + mainNetSortThree);
        String mainEquipmentFactoryCode = StaticMethod.nullObject2String(map.get("alarmVendor"));
        if (mainEquipmentFactoryCode.length() > 0) {
            List list = null;
            try {
                list = dictMgr.getDictByCode(mainEquipmentFactoryCode);
            } catch (Exception localException1) {
            }
            if ((list == null) || (list.size() == 0)) {
                System.out.println("没有找到映射的厂家类型");
            } else {
                TawSystemDictType d = (TawSystemDictType) list.get(0);
                map.put("mainEquipmentFactory", d.getDictId());
            }
        }
        String mainFaultGenerantPrivCode = StaticMethod.nullObject2String(map.get("alarmProvince"));
        if (mainFaultGenerantPrivCode.length() > 0)
            try {
                TawSystemArea area = areaMgr.getAreaByCode(mainFaultGenerantPrivCode);
                if (area != null)
                    map.put("mainFaultGenerantPriv", area.getAreaname());
                else
                    System.out.println("没有找到映射的告警省份");
            } catch (Exception err) {
                System.out.println("没有找到映射的告警省份");
            }
        String mainFaultGenerantCityCode = StaticMethod.nullObject2String(map.get("alarmRegion"));
        System.out.println("告警地市:" + mainFaultGenerantCityCode);
        if (mainFaultGenerantCityCode.length() > 0)
            try {
                TawSystemArea area = areaMgr.getAreaByCode(mainFaultGenerantCityCode);
                if (area != null)
                    map.put("toDeptId", area.getAreaid());
                else
                    System.out.println("没有找到映射的告警地市");
            } catch (Exception err) {
                System.out.println("没有找到映射的告警地市");
            }
        String mainFaultResponseLevel = StaticMethod.nullObject2String(map.get("mainFaultResponseLevel"));
        String mainEquipmentFactory = StaticMethod.nullObject2String(map.get("mainEquipmentFactory"));
        setLimitByLevel(mainFaultResponseLevel, mainNetSortOne, mainNetSortTwo, mainNetSortThree, map);
        try {
            setPreAllocatedUserNew(mainFaultResponseLevel, mainNetSortOne, mainNetSortTwo, map);
        } catch (Exception e) {
            System.out.println("预分配发生异常" + e.getMessage());
        }
        String sheetStatus = "";
        if ("0".equals(map.get("createType"))) {
            String phaseId = StaticMethod.nullObject2String(map.get("phaseId"));
            if (phaseId.equals(""))
                phaseId = "FirstExcuteTask";
            map.put("phaseId", phaseId);
            HashMap classMap = RoleMapSchema.getInstance().getStyleIDsBySheet("CommonFaultMainFlowProcess");
            Hashtable hash = new Hashtable();
            hash.put("deptId", StaticMethod.nullObject2String(map.get("toDeptId")));
            Set filterSet = classMap.keySet();
            for (Iterator filterIt = filterSet.iterator(); filterIt.hasNext(); ) {
                String hashName = StaticMethod.nullObject2String(filterIt.next());
                String name = StaticMethod.nullObject2String(classMap.get(hashName));
                if ((!name.equals("")) && (map.get(name) != null)) {
                    hash.put(hashName, map.get(name));
                }
            }
            String bigRole = StaticMethod.nullObject2String(map.get("bigRole"));
            if (bigRole.equals(""))
                bigRole = XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceRole");
            List perform = RoleManage.getInstance().getSubRoles(bigRole, hash);
            TawSystemSubRole tawSystemSubRole = SheetUtils.getMaxFilterSubRole(perform, null);
            String subRoleId = "";
            if ((tawSystemSubRole != null) && (tawSystemSubRole.getId() != null))
                subRoleId = tawSystemSubRole.getId();
            else
                subRoleId = XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceSubRole");
            Map roleMap = SheetUtils.getUserNameForSubRole(subRoleId);
            String leader = StaticMethod.nullObject2String(roleMap.get("leaderId"));
            if (leader.equals(""))
                map.put("dealPerformerLeader", subRoleId);
            else
                map.put("dealPerformerLeader", leader);
            map.put("dealPerformer", subRoleId);
            map.put("dealPerformerType", "subrole");
            map.put("sendRoleId", StaticMethod.nullObject2String(map.get("operateRoleId")));
            map.put("toOrgRoleId", subRoleId);
            sheetStatus = "待受理";
            String dealPerformerLeader = InterfaceAutoDistribute.getAutoDistributeUser(processTemplateName, (String) map.get("dealPerformer"));
            if ((dealPerformerLeader != null) && (!"".equals(dealPerformerLeader))) {
                map.put("dealPerformerLeader", dealPerformerLeader);
            }
            System.out.println("lizhi:dealPerformerLeader=" + StaticMethod.nullObject2String(map.get("dealPerformerLeader")));
            try {
                String mainSendMode = XmlManage.getFile("/config/commonfault-util.xml").getProperty("dict.autosheet");
                System.out.println("配置文件定义mainSendMode=" + mainSendMode);
                map.put("mainSendMode", mainSendMode);
            } catch (Exception er) {
                System.out.println("配置文件没有定义autosheet");
            }
        } else {
            map.put("phaseId", new String[]{
                    "DraftTask"});

            map.put("sendRoleId", StaticMethod.nullObject2String(map.get("operateRoleId")));
            sheetStatus = "草稿";
            try {
                String mainSendMode = XmlManage.getFile("/config/commonfault-util.xml").getProperty("dict.personsheet");
                System.out.println("配置文件定义mainSendMode=" + mainSendMode);
                map.put("mainSendMode", mainSendMode);
            } catch (Exception er) {
                System.out.println("配置文件没有定义personsheet");
            }
        }
        if (userId.equals(""))
            userId = StaticMethod.nullObject2String(map.get("sendUserId"));
        if (userId.equals(""))
            userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceUser"));
        System.out.println("InterfaceUser:" + userId);
        map.put("sendUserId", userId);
        map.put("operateUserId", userId);
        TawSystemUser user = userMgr.getUserByuserid(userId);
        if (user != null) {
            map.put("sendDeptId", user.getDeptid());
            map.put("sendContact", user.getMobile());
            map.put("operateDeptId", user.getDeptid());
            map.put("operaterContact", user.getMobile());
        }
        map = getAttach(map);
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        HashMap tempWpsMap;
        for (; it.hasNext(); WpsMap.putAll(tempWpsMap)) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
        }

        HashMap sessionMap = new HashMap();
        sessionMap.put("userId", userId);
        sessionMap.put("password", "111");
        Map mainrule = (HashMap) WpsMap.get("main");
        Map operate = (HashMap) WpsMap.get("operate");
        Map linkrule = (HashMap) WpsMap.get("link");
        Date operateTime = (Date) linkrule.get("operateTime");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(operateTime);
        String dealPerformer = StaticMethod.nullObject2String(operate.get("dealPerformer"));
        String phaseId = StaticMethod.nullObject2String(operate.get("phaseId"));
        boolean flag = true;
        IAutoDealSOPMgr autoDealSopMgr = (IAutoDealSOPMgr) ApplicationContextHolder.getInstance().getBean("autoDealSopMgr");
        if (phaseId.equals("FirstExcuteTask")) {
            Map map2 = new HashMap();
            map2.put("autoDealTask", "T1");
            map2.put("alarmId", StaticMethod.nullObject2String(mainrule.get("mainAlarmId")));
            map2.put("autoRule", "0");
            List list = autoDealSopMgr.searchSOP(map2);
            if ((list != null) && (list.size() > 0)) {
                AutoDealSOP autoDealSop = (AutoDealSOP) list.get(0);
                if ("1".equals(autoDealSop.getAutoDealMode())) {
                    operate.put("phaseId", "SecondExcuteTask");
                    createT1Task(mainrule, dealPerformer, autoDealSop.getOperateUserId(), StaticMethod.nullObject2String(linkrule.get("id")));
                    operate.put("dealPerformer", autoDealSop.getTranferObject());
                    operate.put("dealPerformerLeader", autoDealSop.getTranferObject());
                    operate.put("dealPerformerType", "subrole");
                    calendar1.add(13, 20);
                    CommonFaultLink t1Link = createT1Link(mainrule, autoDealSop, "1", calendar1.getTime(), StaticMethod.nullObject2String(linkrule.get("id")));
                    Map map1 = new HashMap();
                    map1.put("autoDealTask", "T2");
                    map1.put("alarmId", StaticMethod.nullObject2String(mainrule.get("mainAlarmId")));
                    map1.put("autoRule", "0");
                    List list1 = autoDealSopMgr.searchSOP(map1);
                    if ((list1 != null) && (list1.size() > 0)) {
                        AutoDealSOP autoDealSopT2 = (AutoDealSOP) list1.get(0);
                        operate.put("phaseId", "");
                        operate.put("hasNextTaskFlag", "true");
                        operate.put("dealPerformer", "");
                        operate.put("dealPerformerLeader", "");
                        operate.put("dealPerformerType", "user");
                        CommonFaultMain mainObject = createMain(mainrule);
                        createLink0(mainrule);
                        calendar1.add(13, 20);
                        createT2Task(mainrule, autoDealSop.getTranferObject(), autoDealSopT2.getOperateUserId(), t1Link.getId());
                        CommonFaultLink t2Link = createT2Link(mainrule, autoDealSopT2, calendar1.getTime(), StaticMethod.nullObject2String(t1Link.getId()));
                        calendar1.add(13, 20);
                        createHoldMessage(mainrule, calendar1.getTime(), StaticMethod.nullObject2String(t2Link.getId()));
                        AutoDealSopSheet SOPsheet1 = new AutoDealSopSheet(autoDealSopT2.getId(), StaticMethod.nullObject2String(mainrule.get("sheetId")), new Date());
                        autoDealSopMgr.saveSopSheet(SOPsheet1);
                        flag = false;
                    }
                } else {
                    operate.put("hasNextTaskFlag", "true");
                    operate.put("phaseId", "");
                    CommonFaultMain mainObject = createMain(mainrule);
                    createLink0(mainrule);
                    createT1Task(mainrule, dealPerformer, autoDealSop.getOperateUserId(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    CommonFaultLink t1Link = createT1Link(mainrule, autoDealSop, "46", calendar1.getTime(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    createHoldMessage(mainrule, calendar1.getTime(), StaticMethod.nullObject2String(t1Link.getId()));
                    operate.put("dealPerformer", "");
                    operate.put("dealPerformerLeader", "");
                    operate.put("dealPerformerType", "user");
                    flag = false;
                }
                AutoDealSopSheet SOPsheet2 = new AutoDealSopSheet(autoDealSop.getId(), StaticMethod.nullObject2String(mainrule.get("sheetId")), new Date());
                autoDealSopMgr.saveSopSheet(SOPsheet2);
            }
        }
        if (phaseId.equals("SecondExcuteTask")) {
            Map map3 = new HashMap();
            map3.put("autoDealTask", "T2");
            map3.put("alarmId", StaticMethod.nullObject2String(mainrule.get("mainAlarmId")));
            map3.put("autoRule", "0");
            List list = autoDealSopMgr.searchSOP(map3);
            if ((list != null) && (list.size() > 0)) {
                AutoDealSOP autoDealSop = (AutoDealSOP) list.get(0);
                if ("2".equals(autoDealSop.getAutoDealMode())) {
                    operate.put("phaseId", "");
                    operate.put("hasNextTaskFlag", "true");
                    CommonFaultMain mainObject = createMain(mainrule);
                    createLink0(mainrule);
                    createT2Task(mainrule, autoDealSop.getTranferObject(), autoDealSop.getOperateUserId(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    CommonFaultLink t2Link = createT2Link(mainrule, autoDealSop, calendar1.getTime(), StaticMethod.nullObject2String(linkrule.get("id")));
                    calendar1.add(13, 20);
                    createHoldMessage(mainrule, calendar1.getTime(), StaticMethod.nullObject2String(t2Link.getId()));
                    operate.put("dealPerformer", "");
                    operate.put("dealPerformerLeader", "");
                    operate.put("dealPerformerType", "user");
                    AutoDealSopSheet SOPsheet = new AutoDealSopSheet(autoDealSop.getId(), StaticMethod.nullObject2String(mainrule.get("sheetId")), new Date());
                    autoDealSopMgr.saveSopSheet(SOPsheet);
                    flag = false;
                }
            }
        }
//判断环节
        if (phaseId.equals("FirstExcuteTask")) {
            System.out.println("进入*************FirstExcuteTask");
            String toDeptId = StaticMethod.nullObject2String(mainrule.get("toDeptId"));
            String pilotcity = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("pilotcity"));
            System.out.println("lizhi:toDeptId=" + toDeptId + "pilotcity=" + pilotcity);
            String[] pilotcityIndex = pilotcity.split(",");
            boolean pilotcityflag = false;
            for (int i = 0; i < pilotcityIndex.length; i++) {
                if ((toDeptId.equals(pilotcityIndex[i])) || ("".equals(toDeptId)))
                    pilotcityflag = true;
            }
            System.out.println("lizhi:pilotcityflag=" + pilotcityflag);
            System.out.println("mainNetSortOne=****************" + mainNetSortOne);


            //根据工单中“网元名称”和“告警ID”两个字段信息匹配派往班组信息 by lyg 171228
            String mainNetNameNew = StaticMethod.nullObject2String(map.get("mainNetName"));//网元名称
            String mainAlarmIdNew = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));//告警ID
            String subroleidNew = "";
            String ccSubroleidNew = "";
            String flagNew = "";
            String newSql = "SELECT * FROM COMMONFAULT_NEW_ROLE t WHERE netname = '" + mainNetNameNew + "' and AlarmId = '" + mainAlarmIdNew + "'";
            List newList = getResult(newSql);

            if (newList != null && newList.size() > 0) {
                //得到派单角色
                subroleidNew = StaticMethod.nullObject2String(((Map) newList.get(0)).get("subroleid"));
                ccSubroleidNew = StaticMethod.nullObject2String(((Map) newList.get(0)).get("ccsubroleid"));
                if (!"".equals(subroleidNew)) {
                    flagNew = "1";
                }
            }

            //end by lyg 171228
            //***************************集团工单*************************************

            if (pilotcityflag) {
                System.out.println("进入pilotcityflag判断**************************");
                String subroleid = "";
                String ifAutotran = "";
                String ccObject = "";
                String eomsCityId = "";
                String eomsCountyId = "";
                String mainNeId = StaticMethod.nullObject2String(map.get("mainNeId"));
                String mainNetName = StaticMethod.nullObject2String(map.get("mainNetName"));
                System.out.println("lizhi:mainNeId=" + mainNeId + "mainNetName=" + mainNetName);
                INetOwnershipManager netOwnershipManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
                INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mainrule.put("mainIfCenterMonitor", "1");
                mainrule.put("mainManualPreSolve", "1030101");
                operate.put("ifAuto2T2", "false");
                String faultResponseLevel = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("faultResponseLevel"));
                String mainAlarmId = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));

                System.out.println("lizhi:mainAlarmId=" + mainAlarmId + "mainFaultResponseLevel=" + mainFaultResponseLevel);
                ArrayList noNeedAutoTranList = new ArrayList();
                System.out.println("lyg123sheetId=" + mainrule.get("sheetId"));
                if (!faultResponseLevel.equals(mainFaultResponseLevel)) {
                    System.out.println("进入faultResponseLevel判断**************************");
                    if (!"".equals(mainAlarmId)) {
                        noNeedAutoTranList = (ArrayList) netOwnershipManager.ifNoneedAutotran(mainAlarmId);
                    }
                    if ("1".equals(flagNew)) {
                        subroleid = subroleidNew;
                        ifAutotran = "1";
                        if (!"".equals(ccSubroleidNew)) {
                            ccObject = ccSubroleidNew;
                        }
                        System.out.println("111=" + subroleid);
                    } else if (noNeedAutoTranList.size() == 0) {
                        if ((!"".equals(toDeptId)) && (!"".equals(mainAlarmId))) {
                            subroleid = netOwnershipManager.getPerformanceAlarm(toDeptId, mainAlarmId);
                            if (!"".equals(subroleid))
                                ifAutotran = "1";
                            System.out.println("222=" + subroleid);
                        }

                        if (mainNetSortOne.equals("101010417")) {//集团客服 101010417
                            //更具集中故障传过来的  区县 信息，匹配eoms班组，匹配表commonfult_jtkh_subrole
                            IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                            String mainReserved1 = StaticMethod.nullObject2String(map.get("mainReserved1")).trim();
                            System.out.println("sheetId=" + map.get("sheetId") + "====mainReserved1=" + mainReserved1);
                            String jtkhSql = "SELECT * FROM commonfult_jtkh_subrole WHERE alarmcity = '" + mainReserved1 + "'";
                            List jtkhList = sqlMgr.getSheetAccessoriesList(jtkhSql);
                            if (jtkhList != null && jtkhList.size() > 0) {
                                subroleid = StaticMethod.nullObject2String(((Map) jtkhList.get(0)).get("subroleid"));
                                ifAutotran = "1";
                            }
                            System.out.println("333=" + subroleid);
                        }


                        if ("".equals(ifAutotran))
                            //  	System.out.println("ifAutotran**************************");
                            if (!"".equals(mainNetName)) {
                                System.out.println("mainNetName**************************");
                                NetOwnership netownership = netOwnershipManager.getNetOwnershipByNetName(mainNetName);
                                NetOwnershipwireless netownershipwireless = netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);

                                //add by zhangjiang start 传输专业加入新的规则
                                String subRoleIdByMainTowerId = "";
                                String ccObjectByMainTowerId = "";
                                Map performerMap = findT2DealPerformerByMainTowerId(mainrule);
                                if (performerMap != null && performerMap.size() > 0) {
                                    if (performerMap.containsKey("subRoleId")) {
                                        subRoleIdByMainTowerId = StaticMethod.nullObject2String(performerMap.get("subRoleId"));
                                    }
                                    if (performerMap.containsKey("copyPerformer")) {
                                        ccObjectByMainTowerId = StaticMethod.nullObject2String(performerMap.get("copyPerformer"));
                                    }
                                }
                                System.out.println("lyg=subRoleIdByMainTowerId=" + subRoleIdByMainTowerId);
                                String subroleidwuhan = "";
                                IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                                if ("".equals(subRoleIdByMainTowerId)) {
                                    String mainNetNamewuhan = StaticMethod.nullObject2String(map.get("mainNetName"));//网元名称
                                    String mainAlarmIdwuhan = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));//告警ID
                                    System.out.println("lyg===mainNetNamewuhan=" + mainNetNamewuhan + "===mainAlarmIdwuhan=" + mainAlarmIdwuhan);
                                    String querySql = "SELECT * FROM COMMONFAULT_wuhan_ROLE t WHERE netname = '" + mainNetNamewuhan + "'";
                                    List list = sqlMgr.getSheetAccessoriesList(querySql);
                                    if (list != null && list.size() > 0 && !"".equals(mainAlarmIdwuhan)) {
                                        String alarmId = StaticMethod.nullObject2String(((Map) list.get(0)).get("alarmid"));
                                        System.out.println("lyg=alarmId==" + alarmId);
                                        if (!"".equals(alarmId) && alarmId.indexOf(mainAlarmIdwuhan) >= 0 && mainAlarmIdwuhan.length() == 17) {
                                            subroleidwuhan = StaticMethod.nullObject2String(((Map) list.get(0)).get("subroleid"));
                                        } else {
                                            subroleidwuhan = StaticMethod.nullObject2String(((Map) list.get(0)).get("othersubroleid"));
                                        }
                                        System.out.println("lyg=subroleidwuhan=" + subroleidwuhan);
                                    }
                                }
//    	      专业是 传输 时end

                                if ((mainNetSortOne.equals("101010401")) && (netownershipwireless != null))//无线专业
                                {
                                    System.out.println("The mainNetName is =====" + mainNetName);
                                    subroleid = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
                                    ccObject = StaticMethod.nullObject2String(netownershipwireless.getCcObject());
                                    ifAutotran = StaticMethod.nullObject2String(netownershipwireless.getIfAutotran());
                                    eomsCityId = StaticMethod.nullObject2String(netownershipwireless.getEomsCityId());
                                    if (("".equals(subroleid)) && (!"".equals(eomsCityId))) {
                                        subroleid = netOwnershipwirelessManager.getSubroleId(eomsCityId);
                                        System.out.println("fengmin:subroleid==20151116==" + subroleid);
                                    }
                                    System.out.println("444=" + subroleid);
                                } else if (mainNetSortOne.equals("101010405") && !"".equals(subRoleIdByMainTowerId)) {//传输专业
                                    System.out.println("进入传输工单**********tieta****************");
                                    subroleid = subRoleIdByMainTowerId;
                                    ccObject = ccObjectByMainTowerId;
                                    ifAutotran = "1";
                                    System.out.println("555=" + subroleid);
                                } else if (mainNetSortOne.equals("101010405") && !"".equals(subroleidwuhan)) {//传输工单  网元名称
                                    System.out.println("进入传输工单********wuhan******************");
                                    subroleid = subroleidwuhan;
                                    ifAutotran = "1";
                                    System.out.println("666=" + subroleid);
                                } else if (netownership != null)//网元名称
                                {
                                    System.out.println("The mainNetName is =====" + mainNetName);
                                    subroleid = StaticMethod.nullObject2String(netownership.getTeamRoleId());
                                    ccObject = StaticMethod.nullObject2String(netownership.getCcObject());
                                    ifAutotran = StaticMethod.nullObject2String(netownership.getIfAutotran());
                                    eomsCityId = StaticMethod.nullObject2String(netownership.getEomsCityId());
                                    eomsCountyId = StaticMethod.nullObject2String(netownership.getEomsCountyId());
                                    if (("".equals(subroleid)) && (!"".equals(eomsCityId))) {
                                        subroleid = netOwnershipManager.getSubroleId(eomsCityId);
                                        System.out.println("lizhi:subroleid=" + subroleid);
                                    }
                                    System.out.println("777=" + subroleid);
                                }
                                //权重表查询start
                                else if ("".equals(subroleid)) {//网元名称
//                  判断dealPerformer是否有值（智能派单 start）
                                    IDownLoadSheetAccessoriesService servicesql = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                                    //查询权重表
                                    System.out.println("mainAlarmId===" + mainAlarmId + "====mainNetName=" + mainNetName);
                                    String subRoleSql = "SELECT * FROM commonfault_level WHERE mainAlarmId = '" + mainAlarmId + "' AND mainNetName = '" + mainNetName + "' ORDER BY level1 DESC";
                                    List subRoleList = (List) servicesql.getSheetAccessoriesList(subRoleSql);
                                    if (subRoleList != null && subRoleList.size() > 0) {
                                        subroleid = StaticMethod.nullObject2String(((Map) subRoleList.get(0)).get("subroleid"));
                                        System.out.println("commonfault_level====" + subroleid);
                                        ifAutotran = "1";
                                    }
                                    System.out.println("8888=" + subroleid);
//                  判断dealPerformer是否有值（智能派单 end）
                                }
                                //权重表查询END
                                else if ((!"".equals(toDeptId)) && (toDeptId != null)) {
                                    subroleid = netOwnershipManager.getSubroleId(toDeptId);
                                    ifAutotran = "1";
                                    System.out.println("999=" + subroleid);
                                } else {
                                    subroleid = "8aa0813b1c6f2386011c6f39c8350027";
                                    System.out.println("101010=" + subroleid);
                                }
                            } else if ((!"".equals(toDeptId)) && (toDeptId != null)) {
                                subroleid = netOwnershipManager.getSubroleId(toDeptId);
                                ifAutotran = "1";
                                System.out.println("1010101111=" + subroleid);
                            } else {
                                subroleid = "8aa0813b1c6f2386011c6f39c8350027";
                                System.out.println("1010101212=" + subroleid);
                            }
                        System.out.println("lizhi:subroleid=" + subroleid + "ifAutotran=" + ifAutotran + "ccObject=" + ccObject + "eomsCityId=" + eomsCityId);
                    }
                }

                System.out.println("lizhi:subroleid=" + subroleid + "ifAutotran=" + ifAutotran + "ccObject=" + ccObject + "eomsCityId=" + eomsCityId);
                String subroleidwire = "";
                String ifAutotranwire = "";
                String ccObjectwire = "";
                String ccObjectwire1 = "";
                String eomsCityIdwire = "";
                boolean weathersend = true;
                String mainNetNamewire = StaticMethod.nullObject2String(map.get("mainNetName"));
                String mainAlarmIdwire = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));
                String relationDH = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("relationDH"));
                String relationCS = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("relationCS"));
                //第二次判断无线专业
                if (mainNetSortOne.equals("101010401") && !"1".equals(flagNew)) {
                    if ((!"".equals(mainIdInfo)) && (mainIdInfo != null)) {
                        if ("900-900-00-000001".equals(mainIdInfo)) {
                            map.put("netOptimization", "1");
                        } else {
                            INetOwnershipManager processManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");
                            String alarmTableName = "commonfault_alarmId_process";
                            ArrayList noProcess = new ArrayList();
                            noProcess = (ArrayList) processManager.getListByAlarmId(alarmTableName, mainIdInfo);
                            if (noProcess.size() > 0)
                                map.put("netOptimization", "2");
                            else
                                map.put("netOptimization", "3");
                        }
                    } else {
                        map.put("netOptimization", "3");
                    }
                    System.out.println("====fengmin===netOptimization====" + map.get("netOptimization"));
                    if (!"".equals(mainNetNamewire)) {
                        String isNetownershipwireless = "false";
                        NetOwnershipwireless netownershipwireless = new NetOwnershipwireless();

                        String where = " and netname ='" + mainNetNamewire + "' and tttype !='101031903' and tttype is not null";
                        List netownershipwirelesss = netOwnershipwirelessManager.getObjectByCondition(where);
                        if ((netownershipwirelesss != null) && (netownershipwirelesss.size() > 0)) {
                            netownershipwireless = (NetOwnershipwireless) netownershipwirelesss.get(0);
                            isNetownershipwireless = "true";
                        }
                        List tuifualarm = netOwnershipwirelessManager.getTuifuAlarmBynetId(" and netid='" + mainAlarmIdwire + "'");

                        if ("true".equals(isNetownershipwireless)) {
                            System.out.println("The information is tuifualarm.size():" + tuifualarm.size() + "; relationDH:" + relationDH + "  ;relationCS:" + relationCS);
                            System.out.println("The information is relationDH:" + relationDH + "; relationCS: " + relationCS);

                            if ((tuifualarm.size() > 0) && (relationDH.indexOf(mainNetSortThree) == -1) && (relationCS.indexOf(mainNetSortThree) == -1)) {
                                ifAutotranwire = "";
                                subroleidwire = "";
                                weathersend = false;
                                System.out.println("The information weathersend is:" + weathersend);
                            } else if ((tuifualarm.size() > 0) && (relationDH.indexOf(mainNetSortThree) != -1)) {
                                ifAutotranwire = "1";
                                subroleidwire = StaticMethod.nullObject2String(netownershipwireless.getTtRoleId());
                                ccObjectwire = StaticMethod.nullObject2String(netownershipwireless.getCcObject());
                                ccObjectwire1 = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
                                weathersend = true;
                            } else if ((tuifualarm.size() > 0) && (relationCS.indexOf(mainNetSortThree) != -1)) {
                                ifAutotranwire = "1";
                                subroleidwire = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
                                ccObjectwire = StaticMethod.nullObject2String(netownershipwireless.getCcObject());
                                weathersend = true;
                            }

                            if (("".equals(subroleidwire)) && ("1".equals(ifAutotranwire))) {
                                ifAutotranwire = "";
                                subroleidwire = "";
                                weathersend = false;
                            }

                            System.out.println("The information weathersend is:" + weathersend);
                        }

                    } else {
                        weathersend = false;
                    }
                    System.out.println("fengmin:subroleid=" + subroleidwire + "ifAutotran=" + ifAutotranwire + "ccObject=" + ccObjectwire + "eomsCityId=" + eomsCityIdwire);
                    if ((!"".equals(subroleidwire)) && ("1".equals(ifAutotranwire)) && (weathersend)) {
                        operate.put("phaseId", "SecondExcuteTask");
                        operate.put("dealPerformer", subroleidwire);
                        operate.put("dealPerformerLeader", subroleidwire);
                        operate.put("dealPerformerType", "subrole");
                        Date opeTime = (Date) linkrule.get("operateTime");
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String prelinkId = StaticMethod.nullObject2String(linkrule.get("id"));
                        String mainCompleteLimitT1 = StaticMethod.nullObject2String(map.get("mainCompleteLimitT1"));
                        Date mainCompleteLimitT1Date = new Date();
                        if (!"".equals(mainCompleteLimitT1))
                            mainCompleteLimitT1Date = sd.parse(mainCompleteLimitT1);
                        String mainCompleteLimitT2 = StaticMethod.nullObject2String(map.get("mainCompleteLimitT2"));
                        Date mainCompleteLimitT2Date = new Date();
                        if (!"".equals(mainCompleteLimitT2))
                            mainCompleteLimitT2Date = sd.parse(mainCompleteLimitT2);
                        createT1Link(mainrule, opeTime, prelinkId, subroleidwire, mainCompleteLimitT2Date);

                        createT1Task(mainrule, prelinkId, mainCompleteLimitT1Date);
                        linkrule.put("nodeAcceptLimit", mainrule.get("mainCompleteLimitT2"));
                        linkrule.put("nodeCompleteLimit", mainrule.get("mainCompleteLimitT2"));
                        if ((!"".equals(ccObjectwire)) || (!"".equals(ccObjectwire1))) {
                            String copyPerformer = "";
                            String copyPerformerLeader = "";
                            String copyPerformerType = "";
                            if ((!"".equals(ccObjectwire)) && (!"".equals(ccObjectwire1))) {
                                copyPerformer = ccObjectwire + "," + ccObjectwire1;
                                copyPerformerLeader = ccObjectwire + "," + ccObjectwire1;
                                copyPerformerType = "subrole,subrole";
                            } else if ((!"".equals(ccObjectwire)) && ("".equals(ccObjectwire1))) {
                                copyPerformer = ccObjectwire;
                                copyPerformerLeader = ccObjectwire;
                                copyPerformerType = "subrole";
                            } else if ((!"".equals(ccObjectwire1)) && ("".equals(ccObjectwire))) {
                                copyPerformer = ccObjectwire1;
                                copyPerformerLeader = ccObjectwire1;
                                copyPerformerType = "subrole";
                            }
                            operate.put("copyPerformer", copyPerformer);
                            operate.put("copyPerformerLeader", copyPerformerLeader);
                            operate.put("copyPerformerType", copyPerformerType);
                        }

                        WpsMap.put("main", mainrule);
                        WpsMap.put("link", linkrule);
                        WpsMap.put("operate", operate);
                    }
                }
                //无线专业结束

                /**
                 * subroleidwire 是第二次 无线专业判断 中在无线网元对应班组中的子角色id，
                 * ifAutotranwire 是第二次 无线专业判断 等于1表示subroleidwire有值，等于空标识subroleidwire没有值
                 * weathersend 和 ifAutotranwire 判断一样
                 * 满足subroleidwire，ifAutotranwire为空，weathersend=true,有两种情况
                 * 1.专业不是无线专业
                 * 2.在无线专业网元对应班组中没有找到对应的subroleid
                 *
                 * 满足第一个if条件有两种可能
                 * 1.专业既不是 无线也不是传输
                 * 2.专业是其中之一，但是没有找到对应的subroleid
                 */
                if (("".equals(subroleidwire)) && ("".equals(ifAutotranwire)) && (weathersend)) {
                    //subroleid 分两种情况 无线专业 从
                    //ifAutotran 是第一次 无线专业判断 的中班组表中的  ifAutotran 目前还不知道表示什么
                    //满足条件是：在第一次判断 无线 专业中 得到了 subroleid
                    /**
                     * subroleid
                     * 1.更具toDeptId, mainAlarmId 得到subroleid,并标记ifAutotran = 1，如果没有subroleid进入2
                     * 2.无线专业更具mainNetName查询commonfault_net_team_wx表得到subroleid，并得到ifAutotran的值，如果没有得到在更具eomsCityId得到subroleid
                     *   非无线专业更具mainNetName查询commonfault_net_team表得到subroleid，并得到ifAutotran的值，如果没有得到在更具eomsCityId得到subroleid
                     * 3.如果没有查到commonfault_net_team_wx和commonfault_net_team 表，并且toDeptId有值，更具toDeptId查询subroleid，
                     *   并标记ifAutotran = 1，如果没有得到subroleid进入4
                     * 4.将subroleid赋值
                     */
                    if ((!"".equals(subroleid)) && ("1".equals(ifAutotran))) {
                        operate.put("phaseId", "SecondExcuteTask");
                        operate.put("dealPerformer", subroleid);
                        operate.put("dealPerformerLeader", subroleid);
                        operate.put("dealPerformerType", "subrole");
                        Date opeTime = (Date) linkrule.get("operateTime");
                        String prelinkId = StaticMethod.nullObject2String(linkrule.get("id"));
                        String mainCompleteLimitT1 = StaticMethod.nullObject2String(map.get("mainCompleteLimitT1"));
                        Date mainCompleteLimitT1Date = new Date();
                        if (!"".equals(mainCompleteLimitT1))
                            mainCompleteLimitT1Date = sdf.parse(mainCompleteLimitT1);
                        String mainCompleteLimitT2 = StaticMethod.nullObject2String(map.get("mainCompleteLimitT2"));
                        Date mainCompleteLimitT2Date = new Date();
                        if (!"".equals(mainCompleteLimitT2))
                            mainCompleteLimitT2Date = sdf.parse(mainCompleteLimitT2);
                        mainrule.put("mainManualPreSolve", "1030102");
                        createT1Link(mainrule, opeTime, prelinkId, subroleid, mainCompleteLimitT2Date);
                        createT1Task(mainrule, prelinkId, mainCompleteLimitT1Date);
                        linkrule.put("nodeAcceptLimit", mainrule.get("mainCompleteLimitT2"));
                        linkrule.put("nodeCompleteLimit", mainrule.get("mainCompleteLimitT2"));
                        linkrule.put("manualPreSolve", "1030102");
                        String copyPerformer = "";
                        String copyPerformerLeader = "";
                        String copyPerformerType = "";
                        if ((mainNetSortOne.equals("101010420")) && (!"824-121-01-817001".equals(mainIdInfo)) && (!"808-121-01-817001".equals(mainIdInfo)) &&
                                (!"808-121-01-817002".equals(mainIdInfo)) && (!"807-121-01-817001".equals(mainIdInfo))) {
                            String ccsubroleid = "";
                            System.out.println("lizhi:eomsCountyId=" + eomsCountyId);
                            if (!"".equals(eomsCountyId)) {
                                String sql = "select subroleid from CommonfaultCountyId where CountyId = '" + eomsCountyId + "'";
                                IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                                List taskList = services.getSheetAccessoriesList(sql);
                                if (taskList.size() > 0) {
                                    Map subroleidMap = (Map) taskList.get(0);
                                    ccsubroleid = StaticMethod.nullObject2String(subroleidMap.get("subroleid"));
                                }
                            }
                            System.out.println("lizhi:ccsubroleid=" + ccsubroleid);

                            if (!"".equals(ccsubroleid)) {
                                copyPerformer = ccsubroleid;
                                copyPerformerLeader = ccsubroleid;
                                copyPerformerType = "subrole";
                            }
                            String todeptsubroleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("cc" + toDeptId));
                            System.out.println("lizhi:todeptsubroleid=" + todeptsubroleid + "toDeptId=" + toDeptId);
                            if ((!"".equals(todeptsubroleid)) && (!"".equals(copyPerformer))) {
                                copyPerformer = copyPerformer + "," + todeptsubroleid;
                                copyPerformerLeader = copyPerformerLeader + "," + todeptsubroleid;
                                copyPerformerType = copyPerformerType + ",subrole";
                            } else if ((!"".equals(todeptsubroleid)) && ("".equals(copyPerformer))) {
                                copyPerformer = todeptsubroleid;
                                copyPerformerLeader = todeptsubroleid;
                                copyPerformerType = "subrole";
                            }
                        }
                        System.out.println("lizhi:ccObject=" + ccObject);
                        if ((!"".equals(ccObject)) && (!"".equals(copyPerformer))) {
                            copyPerformer = copyPerformer + "," + ccObject;
                            copyPerformerLeader = copyPerformerLeader + "," + ccObject;
                            copyPerformerType = copyPerformerType + ",subrole";
                        } else if ((!"".equals(ccObject)) && ("".equals(copyPerformer))) {
                            if ((mainNetSortOne.equals("101010420")) && (("824-121-01-817001".equals(mainIdInfo)) || ("808-121-01-817001".equals(mainIdInfo)) || ("808-121-01-817002".equals(mainIdInfo)) || ("807-121-01-817001".equals(mainIdInfo)))) {
                                copyPerformer = "";
                                copyPerformerLeader = "";
                                copyPerformerType = "";
                            } else {
                                copyPerformer = ccObject;
                                copyPerformerLeader = ccObject;
                                copyPerformerType = "subrole";
                            }
                        }

                        operate.put("copyPerformer", copyPerformer);
                        operate.put("copyPerformerLeader", copyPerformerLeader);
                        operate.put("copyPerformerType", copyPerformerType);
                    } else {
                        String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoSubRole"));
                        linkrule.put("manualPreSolve", "1030101");
                        operate.put("dealPerformer", autoSubRole);
                        operate.put("dealPerformerLeader", autoSubRole);
                        operate.put("dealPerformerType", "subrole");
                    }
                    WpsMap.put("main", mainrule);
                    WpsMap.put("link", linkrule);
                    WpsMap.put("operate", operate);

                }
            }
        }

        String mainAlarmId = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));
        String hql = "select * from commonfaultjkzc where alarmId = '" + mainAlarmId + "'";
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List alarmIdList = services.getSheetAccessoriesList(hql);
        if (alarmIdList.size() > 0) {
            mainrule.put("mainInterfaceCaller", "jkzc");
            String mainAlarmNum = StaticMethod.nullObject2String(mainrule.get("mainAlarmNum"));//告警流水号
            String sheetId = StaticMethod.nullObject2String(mainrule.get("sheetId"));
            String eomsSheetId = sheetId + "/" + mainAlarmNum;
            System.out.println("eomsSheetId====" + eomsSheetId);
            mainrule.put("eomsSheetId", eomsSheetId);
            String supplier = "HB_JKZC";
            String caller = "HB_EOMS";
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String callTime = sd.format(new Date());
            String nodeName = "newWorkSheet";
            String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(mainrule, StaticMethod.getFilePathForUrl("classpath:config/commonfault-crm.xml"), nodeName);
            System.out.println("lizhi:jkzcBasicInfo=" + opDetail);
            String result = "";
            try {
                GroupFaultServiceLocator service = new GroupFaultServiceLocator();
                GroupFault_PortType binding = service.getGroupFault();
                result = binding.getBasicInfo(supplier, caller, "", callTime, opDetail);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("lizhi:jkzcBasicInforesult=" + result);
            }
        }

        String netOptimization = StaticMethod.nullObject2String(mainrule.get("netOptimization"));
        if ("2".equals(netOptimization)) {
            mainrule.put("mainInterfaceCallerGZ", "HB_JZXN");
            String serSupplier = "HB_EOMS";
            String caller = "HB_JZXN";
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String calltime = sd.format(new Date());
            String nodeName = "newWorkSheetJZXN";
            String opDetail = "<?xml version=\"1.0\" encoding=\"gbk\"?>" + InterfaceUtilProperties.getInstance().getXmlFromMap(mainrule, StaticMethod.getFilePathForUrl("classpath:config/commonfault-crm.xml"), nodeName);
            opDetail = opDetail.replaceAll("\r|\n", "");
            System.out.println("lizhi:JZXNBasicInfo=" + opDetail);
            String result = "";
            try {
                ReplaySheetWorkLocator service = new ReplaySheetWorkLocator();
                ReplaySheetWorkSoap_PortType binding = service.getReplaySheetWorkSoap();
                result = binding.newWorkSheet(serSupplier, caller, "", calltime, opDetail);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("lizhi:JZXNBasicInforesult=" + result);
            }
        }

        if (flag) {
            Map tmplink = (Map) WpsMap.get("link");
            Map tmpoperate = (Map) WpsMap.get("operate");
            try {
                String copyPerformer = StaticMethod.nullObject2String(tmpoperate.get("copyPerformer"));
                Integer operateType = (Integer) tmplink.get("operateType");
                if ((!copyPerformer.equals("")) ||
                        (operateType.intValue() == -10) ||
                        (operateType.intValue() == 9) ||
                        (operateType.intValue() == -11)) {
                    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("CommonFault");
                    baseSheet.newSaveNonFlowData("", WpsMap);
                    String sheetId = StaticMethod.nullObject2String(mainrule.get("sheetId"));
                    String title = StaticMethod.nullObject2String(mainrule.get("title"));
                    workSM_NON_T(sheetId, copyPerformer, title);
                    tmpoperate.put("copyPerformer", "");
                    tmpoperate.put("copyPerformerLeader", "");
                    tmpoperate.put("copyPerformerType", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//      判断铁塔工单taw_system_sub_role中type7 = '3'
            operate = (Map) WpsMap.get("operate");
            mainrule = (Map) WpsMap.get("main");
            mainrule = townerSend(mainrule, operate);
            WpsMap.put("main", mainrule);


            IBusinessFlowService businessFlowService = (IBusinessFlowService) ApplicationContextHolder.getInstance().getBean("businessFlowService");
            businessFlowService.initProcess(processTemplateName, operateName, WpsMap, sessionMap);
        }
        HashMap main = (HashMap) WpsMap.get("main");
        String sheetId = main.get("sheetId").toString();
        String sheetKey = main.get("id").toString();


        String enableService = XmlManage.getFile("/config/commonfault-util.xml").getProperty("EnableService");
        if ("true".equals(enableService)) {
            String limit = "";
            if (main.get("sheetCompleteLimit") != null)
                limit = dateFormat.format((Date) main.get("sheetCompleteLimit"));
            System.out.println("mainAlarmNum=" + map.get("mainAlarmNum").toString() + "   sheetId=" + sheetId + "   sheetStatus=" + sheetStatus + "   mainIfAlarmSend=" + StaticMethod.nullObject2String(main.get("mainIfAlarmSend")));
            sendAlarmInfo(map.get("mainAlarmNum").toString(), sheetId, sheetStatus, StaticMethod.nullObject2String(main.get("mainIfAlarmSend")), limit);
        }
        return sheetId + ";" + sheetKey;
    }

    private static void setPreAllocatedUser(String mainFaultResponseLevel, String mainNetSortOne, String mainNetSortTwo, String mainNetSortThree, String mainEquipmentFactory, Map parammap)
            throws Exception {
        IPreAllocatedMgr mgr = (IPreAllocatedMgr) ApplicationContextHolder.getInstance().getBean("preAllocatedNMgr");
        List list = mgr.search(mainNetSortOne, mainNetSortTwo, mainNetSortThree, mainEquipmentFactory, mainFaultResponseLevel, StaticMethod.getCurrentDateTime());
        String dutyUser = "";
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        if ((list != null) && (list.size() > 0)) {
            for (int i = 0; i < list.size(); i++) {
                PreAllocated object = (PreAllocated) list.get(i);
                if (object.getCount() > 0) {
                    if (object.getCount() == object.getAlreadyCount()) {
                        list1.add(object);
                    } else if ((object.getAlreadyCount() < object.getCount()) && (object.getAlreadyCount() > 0)) {
                        list2.add(object);
                    } else if (object.getAlreadyCount() == 0)
                        list3.add(object);
                }
            }
            PreAllocated pre = null;
            if (list2.size() > 0) {
                pre = (PreAllocated) list2.get(0);
                dutyUser = pre.getDutyUserId();
                pre.setAlreadyCount(pre.getAlreadyCount() + 1);
                mgr.updateObject(pre);
            } else if (list3.size() > 0) {
                pre = (PreAllocated) list3.get(0);
                dutyUser = pre.getDutyUserId();
                pre.setAlreadyCount(1);
                mgr.updateObject(pre);
            } else if (list1.size() > 0) {
                pre = (PreAllocated) list1.get(0);
                dutyUser = pre.getDutyUserId();
                for (int j = 0; j < list1.size(); j++) {
                    PreAllocated object2 = (PreAllocated) list1.get(j);
                    if (object2.getId().equals(pre.getId()))
                        object2.setAlreadyCount(1);
                    else
                        object2.setAlreadyCount(0);
                    mgr.updateObject(object2);
                }
            }
        }

        if ((dutyUser == null) || ("".equals(dutyUser))) {
            List leaderlist = mgr.search("", "", "", "", "", StaticMethod.getCurrentDateTime());
            if ((leaderlist != null) && (leaderlist.size() > 0)) {
                PreAllocated object1 = (PreAllocated) leaderlist.get(0);
                dutyUser = object1.getDutyUserId();
            }
        }
        parammap.put("perAllocatedUser", dutyUser);
    }

    public static void setPreAllocatedUserNew(String mainFaultResponseLevel, String mainNetSortOne, String mainNetSortTwo, Map parammap)
            throws Exception {
        IPreAllocatedNewMgr mgr = (IPreAllocatedNewMgr) ApplicationContextHolder.getInstance().getBean("preAllocatedNewMgr");
        HashMap resultMap = mgr.searchPreUser(mainNetSortOne, mainNetSortTwo, mainFaultResponseLevel, StaticMethod.getCurrentDateTime());
        String dutyUser = "";

        boolean specailJHFlag = false;
        boolean specailCSFlag = false;
        boolean specailSJFlag = false;
        boolean specailOtherFlag = false;

        List specailList = (List) resultMap.get("specailList");
        for (int num = 0; (specailList != null) && (specailList.size() > 0) && (num < specailList.size()); num++) {
            PreAllocatedSepcail specail = (PreAllocatedSepcail) specailList.get(num);
            String specailName = StaticMethod.nullObject2String(specail.getSpecailType());
            if ("JH".equals(specailName))
                specailJHFlag = true;
            else if ("CS".equals(specailName))
                specailCSFlag = true;
            else if ("SJ".equals(specailName))
                specailSJFlag = true;
            else if ("Other".equals(specailName)) {
                specailOtherFlag = true;
            }

        }

        List preUserList = (List) resultMap.get("preResultList");
        for (int i = 0; (preUserList != null) && (preUserList.size() > 0) && (i < preUserList.size()); i++) {
            PreAllocatedNew object = (PreAllocatedNew) preUserList.get(i);
            if (specailJHFlag) {
                int specailJHAlreadyCount = object.getSpecailJHAlreadyCount();
                int monitorJHAlreadyCount = object.getMonitorJHAlreadyCount();

                int specailJHAlreadyUserCount = object.getSpecailJHAlreadyUserCount();
                int monitorJHAlreadyUserCount = object.getMonitorJHAlreadyUserCount();

                String specailJHAlreadyUserId = StaticMethod.nullObject2String(object.getSpecailJHAlreadyUserId());
                String monitorJHAlreadyUserId = StaticMethod.nullObject2String(object.getMonitorJHAlreadyUserId());

                int specailJHCount = object.getSpecailJHcount();

                String specailJHUserId = StaticMethod.nullObject2String(object.getSpecailJHUserId());
                String monitorJHUserId = StaticMethod.nullObject2String(object.getMonitorJHUserId());

                BocoLog.info(CommonFaultBO.class, "specailJHAlreadyCount==" + specailJHAlreadyCount + "monitorJHAlreadyCount" + monitorJHAlreadyCount +
                        "specailJHAlreadyUserCount=" + specailJHAlreadyUserCount + "monitorJHAlreadyUserCount=" + monitorJHAlreadyUserCount +
                        "specailJHAlreadyUserId=" + specailJHAlreadyUserId + "monitorJHAlreadyUserId=" + monitorJHAlreadyUserId +
                        "specailJHCount=" + specailJHCount +
                        "specailJHUserId=" + specailJHUserId + "monitorJHUserId=" + monitorJHUserId);

                String[] specailJHUserIds = specailJHUserId.split(",");
                String[] monitorJHUserIds = monitorJHUserId.split(",");

                if (specailJHUserIds.length > 0) {
                    if (specailJHAlreadyUserCount < specailJHUserIds.length) {
                        if (specailJHAlreadyCount < specailJHCount) {
                            if ("".equals(specailJHAlreadyUserId)) {
                                specailJHAlreadyUserId = specailJHUserIds[0];
                                dutyUser = specailJHAlreadyUserId;
                                object.setSpecailJHAlreadyUserId(specailJHAlreadyUserId);
                                object.setSpecailJHAlreadyCount(1);
                                object.setSpecailJHAlreadyUserCount(specailJHAlreadyUserCount + 1);
                                mgr.saveObject(object);
                                break;
                            }
                            dutyUser = specailJHAlreadyUserId;
                            object.setSpecailJHAlreadyCount(specailJHAlreadyCount + 1);
                            mgr.saveObject(object);
                            break;
                        }

                        String temp = specailJHUserId.substring(specailJHUserId.indexOf(specailJHAlreadyUserId) + specailJHAlreadyUserId.length() + 1);
                        String lastUserId = specailJHUserId.substring(specailJHUserId.lastIndexOf(",") + 1, specailJHUserId.length());
                        if (!temp.equals(lastUserId)) {
                            dutyUser = temp.substring(0, temp.indexOf(","));
                            object.setSpecailJHAlreadyUserId(dutyUser);
                            object.setSpecailJHAlreadyCount(1);
                            object.setSpecailJHAlreadyUserCount(specailJHAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = lastUserId;
                        object.setSpecailJHAlreadyUserId(dutyUser);
                        object.setSpecailJHAlreadyCount(1);
                        object.setSpecailJHAlreadyUserCount(specailJHAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    if ((specailJHAlreadyUserCount == specailJHUserIds.length) &&
                            (specailJHAlreadyCount < specailJHCount)) {
                        dutyUser = specailJHAlreadyUserId;
                        object.setSpecailJHAlreadyCount(specailJHAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                }

                if (monitorJHUserIds.length <= 0)
                    continue;
                if (monitorJHAlreadyUserCount < monitorJHUserIds.length) {
                    if (monitorJHAlreadyCount < specailJHCount) {
                        if ("".equals(monitorJHAlreadyUserId)) {
                            monitorJHAlreadyUserId = monitorJHUserIds[0];
                            dutyUser = monitorJHAlreadyUserId;
                            object.setMonitorJHAlreadyUserId(monitorJHAlreadyUserId);
                            object.setMonitorJHAlreadyCount(1);
                            object.setMonitorJHAlreadyUserCount(monitorJHAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = monitorJHAlreadyUserId;
                        object.setMonitorJHAlreadyCount(monitorJHAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    String temp = monitorJHUserId.substring(monitorJHUserId.indexOf(monitorJHAlreadyUserId) + monitorJHAlreadyUserId.length() + 1);
                    String lastUserId = monitorJHUserId.substring(monitorJHUserId.lastIndexOf(",") + 1, monitorJHUserId.length());
                    if (!temp.equals(lastUserId)) {
                        dutyUser = temp.substring(0, temp.indexOf(","));
                        object.setMonitorJHAlreadyUserId(dutyUser);
                        object.setMonitorJHAlreadyCount(1);
                        object.setMonitorJHAlreadyUserCount(monitorJHAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = lastUserId;
                    object.setMonitorJHAlreadyUserId(dutyUser);
                    object.setMonitorJHAlreadyCount(1);
                    object.setMonitorJHAlreadyUserCount(monitorJHAlreadyUserCount + 1);
                    mgr.saveObject(object);
                    break;
                }

                if (monitorJHAlreadyUserCount == monitorJHUserIds.length) {
                    if (monitorJHAlreadyCount < specailJHCount) {
                        dutyUser = monitorJHAlreadyUserId;
                        object.setMonitorJHAlreadyCount(monitorJHAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = specailJHUserIds[0];
                    object.setMonitorJHAlreadyCount(0);
                    object.setMonitorJHAlreadyUserCount(0);
                    object.setMonitorJHAlreadyUserId("");
                    object.setSpecailJHAlreadyCount(1);
                    object.setSpecailJHAlreadyUserCount(1);
                    object.setSpecailJHAlreadyUserId(dutyUser);
                    mgr.saveObject(object);
                    break;
                }

            } else if (specailCSFlag) {
                int specailCSAlreadyCount = object.getSpecailCSAlreadyCount();
                int monitorCSAlreadyCount = object.getMonitorCSAlreadyCount();

                int specailCSAlreadyUserCount = object.getSpecailCSAlreadyUserCount();
                int monitorCSAlreadyUserCount = object.getMonitorCSAlreadyUserCount();

                String specailCSAlreadyUserId = StaticMethod.nullObject2String(object.getSpecailCSAlreadyUserId());
                String monitorCSAlreadyUserId = StaticMethod.nullObject2String(object.getMonitorCSAlreadyUserId());

                int specailCSCount = object.getSpecailCScount();

                String specailCSUserId = StaticMethod.nullObject2String(object.getSpecailCSUserId());
                String monitorCSUserId = StaticMethod.nullObject2String(object.getMonitorCSUserId());

                BocoLog.info(CommonFaultBO.class, "specailCSAlreadyCount==" + specailCSAlreadyCount + "monitorCSAlreadyCount" + monitorCSAlreadyCount +
                        "specailCSAlreadyUserCount=" + specailCSAlreadyUserCount + "monitorCSAlreadyUserCount=" + monitorCSAlreadyUserCount +
                        "specailCSAlreadyUserId=" + specailCSAlreadyUserId + "monitorCSAlreadyUserId=" + monitorCSAlreadyUserId +
                        "specailCSCount=" + specailCSCount +
                        "specailCSUserId=" + specailCSUserId + "monitorCSUserId=" + monitorCSUserId);

                String[] specailCSUserIds = specailCSUserId.split(",");
                String[] monitorCSUserIds = monitorCSUserId.split(",");

                if (specailCSUserIds.length > 0) {
                    if (specailCSAlreadyUserCount < specailCSUserIds.length) {
                        if (specailCSAlreadyCount < specailCSCount) {
                            if ("".equals(specailCSAlreadyUserId)) {
                                specailCSAlreadyUserId = specailCSUserIds[0];
                                dutyUser = specailCSAlreadyUserId;
                                object.setSpecailCSAlreadyUserId(specailCSAlreadyUserId);
                                object.setSpecailCSAlreadyCount(1);
                                object.setSpecailCSAlreadyUserCount(specailCSAlreadyUserCount + 1);
                                mgr.saveObject(object);
                                break;
                            }
                            dutyUser = specailCSAlreadyUserId;
                            object.setSpecailCSAlreadyCount(specailCSAlreadyCount + 1);
                            mgr.saveObject(object);
                            break;
                        }

                        String temp = specailCSUserId.substring(specailCSUserId.indexOf(specailCSAlreadyUserId) + specailCSAlreadyUserId.length() + 1);
                        String lastUserId = specailCSUserId.substring(specailCSUserId.lastIndexOf(",") + 1, specailCSUserId.length());
                        if (!temp.equals(lastUserId)) {
                            dutyUser = temp.substring(0, temp.indexOf(","));
                            object.setSpecailCSAlreadyUserId(dutyUser);
                            object.setSpecailCSAlreadyCount(1);
                            object.setSpecailCSAlreadyUserCount(specailCSAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = lastUserId;
                        object.setSpecailCSAlreadyUserId(dutyUser);
                        object.setSpecailCSAlreadyCount(1);
                        object.setSpecailCSAlreadyUserCount(specailCSAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    if ((specailCSAlreadyUserCount == specailCSUserIds.length) &&
                            (specailCSAlreadyCount < specailCSCount)) {
                        dutyUser = specailCSAlreadyUserId;
                        object.setSpecailCSAlreadyCount(specailCSAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                }

                if (monitorCSUserIds.length <= 0)
                    continue;
                if (monitorCSAlreadyUserCount < monitorCSUserIds.length) {
                    if (monitorCSAlreadyCount < specailCSCount) {
                        if ("".equals(monitorCSAlreadyUserId)) {
                            monitorCSAlreadyUserId = monitorCSUserIds[0];
                            dutyUser = monitorCSAlreadyUserId;
                            object.setMonitorCSAlreadyUserId(monitorCSAlreadyUserId);
                            object.setMonitorCSAlreadyCount(1);
                            object.setMonitorCSAlreadyUserCount(monitorCSAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = monitorCSAlreadyUserId;
                        object.setMonitorCSAlreadyCount(monitorCSAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    String temp = monitorCSUserId.substring(monitorCSUserId.indexOf(monitorCSAlreadyUserId) + monitorCSAlreadyUserId.length() + 1);
                    String lastUserId = monitorCSUserId.substring(monitorCSUserId.lastIndexOf(",") + 1, monitorCSUserId.length());
                    if (!temp.equals(lastUserId)) {
                        dutyUser = temp.substring(0, temp.indexOf(","));
                        object.setMonitorCSAlreadyUserId(dutyUser);
                        object.setMonitorCSAlreadyCount(1);
                        object.setMonitorCSAlreadyUserCount(monitorCSAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = lastUserId;
                    object.setMonitorCSAlreadyUserId(dutyUser);
                    object.setMonitorCSAlreadyCount(1);
                    object.setMonitorCSAlreadyUserCount(monitorCSAlreadyUserCount + 1);
                    mgr.saveObject(object);
                    break;
                }

                if (monitorCSAlreadyUserCount == monitorCSUserIds.length) {
                    if (monitorCSAlreadyCount < specailCSCount) {
                        dutyUser = monitorCSAlreadyUserId;
                        object.setMonitorCSAlreadyCount(monitorCSAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = specailCSUserIds[0];
                    object.setMonitorCSAlreadyCount(0);
                    object.setMonitorCSAlreadyUserCount(0);
                    object.setMonitorCSAlreadyUserId("");
                    object.setSpecailCSAlreadyCount(1);
                    object.setSpecailCSAlreadyUserCount(1);
                    object.setSpecailCSAlreadyUserId(dutyUser);
                    mgr.saveObject(object);
                    break;
                }

            } else if (specailSJFlag) {
                int specailSJAlreadyCount = object.getSpecailSJAlreadyCount();
                int monitorSJAlreadyCount = object.getMonitorSJAlreadyCount();

                int specailSJAlreadyUserCount = object.getSpecailSJAlreadyUserCount();
                int monitorSJAlreadyUserCount = object.getMonitorSJAlreadyUserCount();

                String specailSJAlreadyUserId = StaticMethod.nullObject2String(object.getSpecailSJAlreadyUserId());
                String monitorSJAlreadyUserId = StaticMethod.nullObject2String(object.getMonitorSJAlreadyUserId());

                int specailSJCount = object.getSpecailSJcount();

                String specailSJUserId = StaticMethod.nullObject2String(object.getSpecailSJUserId());
                String monitorSJUserId = StaticMethod.nullObject2String(object.getMonitorSJUserId());

                BocoLog.info(CommonFaultBO.class, "specailSJAlreadyCount==" + specailSJAlreadyCount + "monitorSJAlreadyCount" + monitorSJAlreadyCount +
                        "specailSJAlreadyUserCount=" + specailSJAlreadyUserCount + "monitorSJAlreadyUserCount=" + monitorSJAlreadyUserCount +
                        "specailSJAlreadyUserId=" + specailSJAlreadyUserId + "monitorSJAlreadyUserId=" + monitorSJAlreadyUserId +
                        "specailSJCount=" + specailSJCount +
                        "specailSJUserId=" + specailSJUserId + "monitorSJUserId=" + monitorSJUserId);

                String[] specailSJUserIds = specailSJUserId.split(",");
                String[] monitorSJUserIds = monitorSJUserId.split(",");

                if (specailSJUserIds.length > 0) {
                    if (specailSJAlreadyUserCount < specailSJUserIds.length) {
                        if (specailSJAlreadyCount < specailSJCount) {
                            if ("".equals(specailSJAlreadyUserId)) {
                                specailSJAlreadyUserId = specailSJUserIds[0];
                                dutyUser = specailSJAlreadyUserId;
                                object.setSpecailSJAlreadyUserId(specailSJAlreadyUserId);
                                object.setSpecailSJAlreadyCount(1);
                                object.setSpecailSJAlreadyUserCount(specailSJAlreadyUserCount + 1);
                                mgr.saveObject(object);
                                break;
                            }
                            dutyUser = specailSJAlreadyUserId;
                            object.setSpecailSJAlreadyCount(specailSJAlreadyCount + 1);
                            mgr.saveObject(object);
                            break;
                        }

                        String temp = specailSJUserId.substring(specailSJUserId.indexOf(specailSJAlreadyUserId) + specailSJAlreadyUserId.length() + 1);
                        String lastUserId = specailSJUserId.substring(specailSJUserId.lastIndexOf(",") + 1, specailSJUserId.length());
                        if (!temp.equals(lastUserId)) {
                            dutyUser = temp.substring(0, temp.indexOf(","));
                            object.setSpecailSJAlreadyUserId(dutyUser);
                            object.setSpecailSJAlreadyCount(1);
                            object.setSpecailSJAlreadyUserCount(specailSJAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = lastUserId;
                        object.setSpecailSJAlreadyUserId(dutyUser);
                        object.setSpecailSJAlreadyCount(1);
                        object.setSpecailSJAlreadyUserCount(specailSJAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    if ((specailSJAlreadyUserCount == specailSJUserIds.length) &&
                            (specailSJAlreadyCount < specailSJCount)) {
                        dutyUser = specailSJAlreadyUserId;
                        object.setSpecailSJAlreadyCount(specailSJAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                }

                if (monitorSJUserIds.length <= 0)
                    continue;
                if (monitorSJAlreadyUserCount < monitorSJUserIds.length) {
                    if (monitorSJAlreadyCount < specailSJCount) {
                        if ("".equals(monitorSJAlreadyUserId)) {
                            monitorSJAlreadyUserId = monitorSJUserIds[0];
                            dutyUser = monitorSJAlreadyUserId;
                            object.setMonitorSJAlreadyUserId(monitorSJAlreadyUserId);
                            object.setMonitorSJAlreadyCount(1);
                            object.setMonitorSJAlreadyUserCount(monitorSJAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = monitorSJAlreadyUserId;
                        object.setMonitorSJAlreadyCount(monitorSJAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    String temp = monitorSJUserId.substring(monitorSJUserId.indexOf(monitorSJAlreadyUserId) + monitorSJAlreadyUserId.length() + 1);
                    String lastUserId = monitorSJUserId.substring(monitorSJUserId.lastIndexOf(",") + 1, monitorSJUserId.length());
                    if (!temp.equals(lastUserId)) {
                        dutyUser = temp.substring(0, temp.indexOf(","));
                        object.setMonitorSJAlreadyUserId(dutyUser);
                        object.setMonitorSJAlreadyCount(1);
                        object.setMonitorSJAlreadyUserCount(monitorSJAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = lastUserId;
                    object.setMonitorSJAlreadyUserId(dutyUser);
                    object.setMonitorSJAlreadyCount(1);
                    object.setMonitorSJAlreadyUserCount(monitorSJAlreadyUserCount + 1);
                    mgr.saveObject(object);
                    break;
                }

                if (monitorSJAlreadyUserCount == monitorSJUserIds.length) {
                    if (monitorSJAlreadyCount < specailSJCount) {
                        dutyUser = monitorSJAlreadyUserId;
                        object.setMonitorSJAlreadyCount(monitorSJAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = specailSJUserIds[0];
                    object.setMonitorSJAlreadyCount(0);
                    object.setMonitorSJAlreadyUserCount(0);
                    object.setMonitorSJAlreadyUserId("");
                    object.setSpecailSJAlreadyCount(1);
                    object.setSpecailSJAlreadyUserCount(1);
                    object.setSpecailSJAlreadyUserId(dutyUser);
                    mgr.saveObject(object);
                    break;
                }
            } else {
                if (!specailOtherFlag)
                    continue;
                int specailOtherAlreadyCount = object.getSpecailOtherAlreadyCount();
                int monitorOtherAlreadyCount = object.getMonitorOtherAlreadyCount();

                int specailOtherAlreadyUserCount = object.getSpecailOtherAlreadyUserCount();
                int monitorOtherAlreadyUserCount = object.getMonitorOtherAlreadyUserCount();

                String specailOtherAlreadyUserId = StaticMethod.nullObject2String(object.getSpecailOtherAlreadyUserId());
                String monitorOtherAlreadyUserId = StaticMethod.nullObject2String(object.getMonitorOtherAlreadyUserId());

                int specailOtherCount = object.getSpecailOthercount();

                String specailOtherUserId = StaticMethod.nullObject2String(object.getSpecailOtherUserId());
                String monitorOtherUserId = StaticMethod.nullObject2String(object.getMonitorOtherUserId());

                BocoLog.info(CommonFaultBO.class, "specailOtherAlreadyCount==" + specailOtherAlreadyCount + "monitorOtherAlreadyCount" + monitorOtherAlreadyCount +
                        "specailOtherAlreadyUserCount=" + specailOtherAlreadyUserCount + "monitorOtherAlreadyUserCount=" + monitorOtherAlreadyUserCount +
                        "specailOtherAlreadyUserId=" + specailOtherAlreadyUserId + "monitorOtherAlreadyUserId=" + monitorOtherAlreadyUserId +
                        "specailOtherCount=" + specailOtherCount +
                        "specailOtherUserId=" + specailOtherUserId + "monitorOtherUserId=" + monitorOtherUserId);

                String[] specailOtherUserIds = specailOtherUserId.split(",");
                String[] monitorOtherUserIds = monitorOtherUserId.split(",");

                if (specailOtherUserIds.length > 0) {
                    if (specailOtherAlreadyUserCount < specailOtherUserIds.length) {
                        if (specailOtherAlreadyCount < specailOtherCount) {
                            if ("".equals(specailOtherAlreadyUserId)) {
                                specailOtherAlreadyUserId = specailOtherUserIds[0];
                                dutyUser = specailOtherAlreadyUserId;
                                object.setSpecailOtherAlreadyUserId(specailOtherAlreadyUserId);
                                object.setSpecailOtherAlreadyCount(1);
                                object.setSpecailOtherAlreadyUserCount(specailOtherAlreadyUserCount + 1);
                                mgr.saveObject(object);
                                break;
                            }
                            dutyUser = specailOtherAlreadyUserId;
                            object.setSpecailOtherAlreadyCount(specailOtherAlreadyCount + 1);
                            mgr.saveObject(object);
                            break;
                        }

                        String temp = specailOtherUserId.substring(specailOtherUserId.indexOf(specailOtherAlreadyUserId) + specailOtherAlreadyUserId.length() + 1);
                        String lastUserId = specailOtherUserId.substring(specailOtherUserId.lastIndexOf(",") + 1, specailOtherUserId.length());
                        if (!temp.equals(lastUserId)) {
                            dutyUser = temp.substring(0, temp.indexOf(","));
                            object.setSpecailOtherAlreadyUserId(dutyUser);
                            object.setSpecailOtherAlreadyCount(1);
                            object.setSpecailOtherAlreadyUserCount(specailOtherAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = lastUserId;
                        object.setSpecailOtherAlreadyUserId(dutyUser);
                        object.setSpecailOtherAlreadyCount(1);
                        object.setSpecailOtherAlreadyUserCount(specailOtherAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    if ((specailOtherAlreadyUserCount == specailOtherUserIds.length) &&
                            (specailOtherAlreadyCount < specailOtherCount)) {
                        dutyUser = specailOtherAlreadyUserId;
                        object.setSpecailOtherAlreadyCount(specailOtherAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                }

                if (monitorOtherUserIds.length <= 0)
                    continue;
                if (monitorOtherAlreadyUserCount < monitorOtherUserIds.length) {
                    if (monitorOtherAlreadyCount < specailOtherCount) {
                        if ("".equals(monitorOtherAlreadyUserId)) {
                            monitorOtherAlreadyUserId = monitorOtherUserIds[0];
                            dutyUser = monitorOtherAlreadyUserId;
                            object.setMonitorOtherAlreadyUserId(monitorOtherAlreadyUserId);
                            object.setMonitorOtherAlreadyCount(1);
                            object.setMonitorOtherAlreadyUserCount(monitorOtherAlreadyUserCount + 1);
                            mgr.saveObject(object);
                            break;
                        }
                        dutyUser = monitorOtherAlreadyUserId;
                        object.setMonitorOtherAlreadyCount(monitorOtherAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }

                    String temp = monitorOtherUserId.substring(monitorOtherUserId.indexOf(monitorOtherAlreadyUserId) + monitorOtherAlreadyUserId.length() + 1);
                    String lastUserId = monitorOtherUserId.substring(monitorOtherUserId.lastIndexOf(",") + 1, monitorOtherUserId.length());
                    if (!temp.equals(lastUserId)) {
                        dutyUser = temp.substring(0, temp.indexOf(","));
                        object.setMonitorOtherAlreadyUserId(dutyUser);
                        object.setMonitorOtherAlreadyCount(1);
                        object.setMonitorOtherAlreadyUserCount(monitorOtherAlreadyUserCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = lastUserId;
                    object.setMonitorOtherAlreadyUserId(dutyUser);
                    object.setMonitorOtherAlreadyCount(1);
                    object.setMonitorOtherAlreadyUserCount(monitorOtherAlreadyUserCount + 1);
                    mgr.saveObject(object);
                    break;
                }

                if (monitorOtherAlreadyUserCount == monitorOtherUserIds.length) {
                    if (monitorOtherAlreadyCount < specailOtherCount) {
                        dutyUser = monitorOtherAlreadyUserId;
                        object.setMonitorOtherAlreadyCount(monitorOtherAlreadyCount + 1);
                        mgr.saveObject(object);
                        break;
                    }
                    dutyUser = specailOtherUserIds[0];
                    object.setMonitorOtherAlreadyCount(0);
                    object.setMonitorOtherAlreadyUserCount(0);
                    object.setMonitorOtherAlreadyUserId("");
                    object.setSpecailOtherAlreadyCount(1);
                    object.setSpecailOtherAlreadyUserCount(1);
                    object.setSpecailOtherAlreadyUserId(dutyUser);
                    mgr.saveObject(object);
                    break;
                }

            }

        }

        System.out.println("dutyUser=" + dutyUser);
        parammap.put("perAllocatedUser", dutyUser);
    }

    private static Map getAttach(Map sheetMap) {
        String attachUrl = StaticMethod.nullObject2String(sheetMap.get("attachUrl"));
        String attachName = StaticMethod.nullObject2String(sheetMap.get("attachName"));
        if (attachUrl.length() > 0) {
            WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
            String str = wm.getAttachFromOtherSystem(attachName, attachUrl, "commonfault");
            str = wm.getAttachStr(str);
            System.out.println("attach=" + str);
            sheetMap.put("sheetAccessories", str);
        }
        return sheetMap;
    }

    private static String addMinute(Date date, int minute) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(12, minute);
        String datestr = dateFormat.format(c.getTime());
        return datestr;
    }

    private static Map setLimitByLevel(String levelCode, String mainNetSortOne, String mainNetSortTwo, String mainNetSortThree, Map map)
            throws Exception {
        System.out.println("mainFaultResponseLevel=" + levelCode);
        System.out.println("mainNetSortThree=" + mainNetSortThree);
        String limitDictPath = "";
        if ("101030401".equals(levelCode)) {
            limitDictPath = "dict.levelone";
            String copyRoleId = XmlManage.getFile("/config/commonfault-util.xml").getProperty("copyRoleId");
            System.out.println("一级处理抄送角色:" + copyRoleId);
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
            TawSystemSubRole subRole = sm.getMatchRoles("CommonFaultMainFlowProcess", toDeptId, copyRoleId, map);
            if ((subRole == null) || (subRole.getId() == null) || (subRole.getId().length() == 0)) {
                System.out.println("未找到抄送角色");
            } else {
                map.put("copyPerformer", subRole.getId());
                map.put("copyPerformerLeader", subRole.getId());
                map.put("copyPerformerType", "subrole");
            }
        } else if ("101030402".equals(levelCode)) {
            limitDictPath = "dict.leveltwo";
        } else {
            limitDictPath = "dict.levelthree";
        }
        try {
            int t1 = 0;
            int t2 = 0;
            int t3 = 0;
            int dealtime = 0;
            int accepttime = 0;
            Date date = new Date();
            if ((levelCode.length() > 0) && (mainNetSortThree.length() > 0)) {
                ISheetLimitManager iSheetLimitManager = (ISheetLimitManager) ApplicationContextHolder.getInstance().getBean("IsheetLimitManager");
                SheetLimit limit = iSheetLimitManager.getSheetLimitBySpecial(mainNetSortOne, levelCode, mainNetSortTwo, mainNetSortThree);
                if ((limit != null) && (limit.getId() != null)) {
                    System.out.println("limit!=null");
                    t1 = Integer.parseInt(limit.getT1Limit());
                    t2 = Integer.parseInt(limit.getT2Limit());
                    t3 = Integer.parseInt(limit.getT3Limit());
                } else {
                    System.out.println("limit=null，使用默认时限");
                    String completeLimitT1 = XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT1");
                    System.out.println("limitDictPath.completeLimitT1=" + completeLimitT1);
                    t1 = Integer.parseInt(completeLimitT1);
                    String completeLimitT2 = XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT2");
                    System.out.println("limitDictPath.completeLimitT2=" + completeLimitT2);
                    t2 = Integer.parseInt(completeLimitT2);
                    String completeLimitT3 = XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT3");
                    System.out.println("limitDictPath.completeLimitT3=" + completeLimitT3);
                    t3 = Integer.parseInt(completeLimitT3);
                }
                SheetAttributes sheetAttributes = (SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes");
                String regionId = StaticMethod.nullObject2String(sheetAttributes.getRegionId());
                SheetLimit acceptLimit = null;
                if ((!regionId.equals("")) && (!regionId.equals("JL")))
                    acceptLimit = iSheetLimitManager.getSheetLimitBySpecial(levelCode);
                if (acceptLimit != null) {
                    System.out.println("acceptLimit!=null");
                    if (acceptLimit.getAcceptLimit() != null) {
                        System.out.println("accepttime!=null");
                        accepttime = Integer.parseInt(acceptLimit.getAcceptLimit());
                    } else {
                        System.out.println("accepttime=null");
                        accepttime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".acceptLimit"));
                    }
                    if (acceptLimit.getReplyLimit() != null) {
                        System.out.println("dealtime!=null");
                        dealtime = Integer.parseInt(acceptLimit.getReplyLimit());
                    } else {
                        System.out.println("dealtime=null");
                        dealtime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".replyLimit"));
                    }
                } else {
                    System.out.println("acceptLimit=null");
                    dealtime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".replyLimit"));
                    accepttime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".acceptLimit"));
                }
            } else {
                System.out.println("mainFaultResponseLevel为空或netTypeCode为空，使用默认时限");
                t1 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT1"));
                t2 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT2"));
                t3 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT3"));
                dealtime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".replyLimit"));
                accepttime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".acceptLimit"));
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("nowdate = " + dateFormat.format(new Date()));
            System.out.println("date=" + dateFormat.format(date) + ";t1=" + t1);
            String mainCompleteLimitT1 = addMinute(date, t1);
            String mainCompleteLimitT2 = addMinute(date, t1 + t2);
            String mainCompleteLimitT3 = addMinute(date, t1 + t2 + t3);
            String sheetCompleteLimit = addMinute(date, dealtime);
            String sheetAcceptLimit = addMinute(date, accepttime);
            System.out.println("mainCompleteLimitT1=" + mainCompleteLimitT1);
            System.out.println("mainCompleteLimitT2=" + mainCompleteLimitT2);
            System.out.println("mainCompleteLimitT3=" + mainCompleteLimitT3);
            System.out.println("sheetCompleteLimit=" + sheetCompleteLimit);
            System.out.println("sheetAcceptLimit=" + sheetAcceptLimit);
            map.put("mainCompleteLimitT1", mainCompleteLimitT1);
            map.put("mainCompleteLimitT2", mainCompleteLimitT2);
            map.put("mainCompleteLimitT3", mainCompleteLimitT3);
            map.put("sheetCompleteLimit", sheetCompleteLimit);
            map.put("sheetAcceptLimit", sheetAcceptLimit);
            map.put("nodeAcceptLimit", addMinute(new Date(), accepttime));
            map.put("nodeCompleteLimit", addMinute(new Date(), t1));
        } catch (Exception err) {
            System.out.println("告警派单：读取时限失败");
            err.printStackTrace();
        }
        return map;
    }

    public static String updateMain(HashMap map)
            throws Exception {
        String sheetId = "";
        ICommonFaultMainManager mgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ICommonFaultPackMainManager packMgr = (ICommonFaultPackMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultPackMainManager");
        if (map.get("alarmId") == null)
            throw new Exception("参数alarmId＝null");
        if (map.get("clearTime") == null)
            throw new Exception("参数clearTime＝null");
        CommonFaultMain main = (CommonFaultMain) mgr.getMainByAlarmId(map.get("alarmId").toString());
        CommonFaultPackMain packMain = null;
        if (main == null) {
            packMain = packMgr.getMainByAlarmId(map.get("alarmId").toString());
            if (packMain == null)
                throw new Exception("没找到alarmId＝'" + map.get("alarmId").toString() + "'对应的工单");
        }
        if ((main != null) && (main.getMainAlarmSolveDate() != null) && (!main.getMainAlarmSolveDate().equals(""))) {
            BocoLog.info(CommonFaultBO.class, "告警清除时间已存在，不更新");
            return main.getSheetId();
        }
        String datestr = map.get("clearTime").toString();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date todate = dateformat.parse(datestr);
        if (main != null) {
            main.setMainAlarmSolveDate(todate);
            if (map.get("alarmStatus") != null)
                main.setMainAlarmState(StaticMethod.nullObject2String(map.get("alarmStatus")));
            main.setMainAlarmSolveDate(todate);
            mgr.saveOrUpdateMain(main);
            sheetId = main.getSheetId();
            //得到网络一级分类、网络二级分类
            String mainNetSortOne = StaticMethod.nullObject2String(main.getMainNetSortOne());
            String mainNetSortTwo = StaticMethod.nullObject2String(main.getMainNetSortTwo());
            //网络一级分类为：数据网(101010403)，网络二级分类为：LTE(10101040313) 的工单
            if ("101010403".equals(mainNetSortOne) && "10101040313".equals(mainNetSortTwo)) {
                //调用集中故障接口
                String mainAlarmNum = StaticMethod.nullObject2String(main.getMainAlarmNum());
                System.out.println("调用集中故障接口 start get jzgz port by lyg=" + main.getSheetId() + "===" + mainAlarmNum);
                String endpoint = XmlManage.getFile("/config/commonfaultsheet.xml").getProperty("operate.ipPort");
                System.out.println("endpoint====" + endpoint);
//  		String endpoint = "http://10.30.172.40:58999/CentralizedFaultHandlingService?wsdl";
                Service service = new Service();
                Call call = (Call) service.createCall();
                call.setTargetEndpointAddress(endpoint);
                // WSDL里面描述的接口名称(要调用的方法)
                call.setOperationName(new javax.xml.namespace.QName("http://services.boco.com/", "run"));
                // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
                call.addParameter("standardAlarmFpId", XMLType.XSD_STRING, ParameterMode.IN);

                // 设置被调用方法的返回值类型
                call.setReturnType(XMLType.XSD_STRING);

                call.setTimeout(new Integer(500));
                //设置方法中参数的值
                Object[] paramValues = new Object[]{mainAlarmNum};
                // 给方法传递参数，并且调用方法
                try {
                    call.invoke(paramValues);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

                try {
                    if ("jkzc".equals(main.getMainInterfaceCaller())) {
                        sheetId = StaticMethod.nullObject2String(main.getSheetId());
                        String supplier = "HB_JKZC";
                        String caller = "HB_EOMS";
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String callTime = sd.format(new Date());
                        mainAlarmNum = StaticMethod.nullObject2String(main.getMainAlarmNum());//告警流水号
                        String emosSheetId = sheetId + "/" + mainAlarmNum;
                        String linkFaultAvoidTime = datestr;
                        System.out.println("updatemain emosSheetId=" + emosSheetId + "====linkFaultAvoidTime=" + linkFaultAvoidTime);
                        GroupFaultServiceLocator locator = new GroupFaultServiceLocator();
                        GroupFault_PortType bing = locator.getGroupFault();
                        String opDetail1 = "<root><request><emosSheetId>" + emosSheetId + "</emosSheetId><coveryTime>" + linkFaultAvoidTime + "</coveryTime></request></root>";
                        System.out.println("updatemain inpt getCoveryInfo opDetail1=" + opDetail1);
                        bing.getCoveryInfo(supplier, caller, "", callTime, opDetail1);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("updatemain getCoveryInfo error sheetId=" + sheetId);
                    e.getStackTrace();
                }


            }
        } else {
            packMain.setMainAlarmState(StaticMethod.nullObject2String(map.get("alarmStatus")));
            packMain.setMainAlarmSolveDate(todate.toString());
            packMgr.saveOrUpdateMain(packMain);
            main = (CommonFaultMain) mgr.getSingleMainPO(packMain.getMainId());
            sheetId = main.getSheetId();

            try {
                if ("jkzc".equals(main.getMainInterfaceCaller())) {
                    sheetId = StaticMethod.nullObject2String(main.getSheetId());
                    String supplier = "HB_JKZC";
                    String caller = "HB_EOMS";
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String callTime = sd.format(new Date());
                    String mainAlarmNum = StaticMethod.nullObject2String(map.get("alarmId"));
                    String emosSheetId = sheetId + "/" + mainAlarmNum;
                    String linkFaultAvoidTime = datestr;
                    System.out.println("updatemain emosSheetId=" + emosSheetId + "====linkFaultAvoidTime=" + linkFaultAvoidTime);
                    GroupFaultServiceLocator locator = new GroupFaultServiceLocator();
                    GroupFault_PortType bing = locator.getGroupFault();
                    String opDetail1 = "<root><request><emosSheetId>" + emosSheetId + "</emosSheetId><coveryTime>" + linkFaultAvoidTime + "</coveryTime></request></root>";
                    System.out.println("packMain updatemain inpt getCoveryInfo opDetail1=" + opDetail1);
                    bing.getCoveryInfo(supplier, caller, "", callTime, opDetail1);
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("packMain updatemain getCoveryInfo error sheetId=" + sheetId);
                e.getStackTrace();
            }

        }
        return sheetId;
    }

    public static void updateAlarm(String sheetkey, String cnStatus)
            throws Exception {
        updateAlarm(sheetkey, cnStatus, "");
    }

    public static void updateAlarm(String sheetkey, String cnStatus, String limit)
            throws Exception {
        ICommonFaultMainManager mgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        System.out.println("接口id：" + sheetkey);
        CommonFaultMain main = (CommonFaultMain) mgr.loadSinglePO(sheetkey);
        if (main == null) {
            System.out.println("没找到" + sheetkey + "对应的工单");
            return;
        }

        updateAlarm(main, cnStatus, limit);
    }

    public static void updateAlarm(CommonFaultMain main, String cnStatus)
            throws Exception {
        updateAlarm(main, cnStatus, "");
    }

    public static void updateAlarm(CommonFaultMain main, String cnStatus, String limit)
            throws Exception {
        System.out.println("limit=" + limit);
        String mainSendMode = main.getMainSendMode();
        String autoMode = XmlManage.getFile("/config/commonfault-util.xml").getProperty("dict.autosheet");
        String personMode = XmlManage.getFile("/config/commonfault-util.xml").getProperty("dict.personsheet");
        if ((!autoMode.equals(mainSendMode)) && (!personMode.equals(mainSendMode))) {
            System.out.println("不是接口派单，退出告警同步");
            System.out.println("autoMode=" + autoMode);
            System.out.println("personMode=" + personMode);
            System.out.println("mainSendMode=" + mainSendMode);
            return;
        }
        String serSupplier = main.getMainIfAlarmSend();
        String dealTimeLimit = "";
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!limit.equals("")) {
            dealTimeLimit = limit;
        } else if (main.getSheetCompleteLimit() != null)
            dealTimeLimit = dateformat.format(main.getSheetCompleteLimit());
        sendAlarmInfo(main.getMainAlarmNum(), main.getSheetId(), cnStatus, serSupplier, dealTimeLimit);
    }

    public static void sendAlarmInfo(String alarmId, String sheetId, String cnStatus, String serSupplier, String completeLimit) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateformat.format(new Date());
        String xml = "<opDetail><recordInfo><fieldInfo><fieldChName>网管告警ID</fieldChName><fieldEnName>alarmId</fieldEnName><fieldContent>" + alarmId + "</fieldContent>" + "</fieldInfo>" + "<fieldInfo>" + "<fieldChName>EOMS工单ID</fieldChName>" + "<fieldEnName>sheetNo</fieldEnName>" + "<fieldContent>" + sheetId + "</fieldContent>" + "</fieldInfo>" + "<fieldInfo>" + "<fieldChName>工单状态</fieldChName>" + "<fieldEnName>sheetStatus</fieldEnName>" + "<fieldContent>" + cnStatus + "</fieldContent>" + "</fieldInfo>" + "<fieldInfo>" + "<fieldChName>状态时间</fieldChName>" + "<fieldEnName>statusTime</fieldEnName>" + "<fieldContent>" + date + "</fieldContent>" + "</fieldInfo>" + "<fieldInfo>" + "<fieldChName>工单处理时限</fieldChName>" + "<fieldEnName>dealTimeLimit</fieldEnName>" + "<fieldContent>" + completeLimit + "</fieldContent>" + "</fieldInfo></recordInfo>" + "</opDetail>";
        System.out.println("告警同步opDetail=" + xml);
        try {
            alarmId = AlarmClient.syncSheetState(serSupplier, xml);
            System.out.println("告警同步完成，syncSheetState:" + alarmId);
        } catch (Exception err) {
            System.out.println("调用告警同步接口失败,alarmId:" + alarmId);
            err.printStackTrace();
        }
    }

    private static String splitString(String str, int len, String elide) {
        if (str == null)
            return "";
        byte[] strByte = str.getBytes();
        int strLen = strByte.length;
        int elideLen = elide.trim().length() != 0 ? elide.getBytes().length : 0;
        if ((len >= strLen) || (len < 1))
            return str;
        if (len - elideLen > 0)
            len -= elideLen;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int value = strByte[i];
            if (value < 0) {
                count++;
            }
        }
        if (count % 2 != 0)
            len = len != 1 ? len - 1 : len + 1;
        return new String(strByte, 0, len) + elide.trim();
    }

    public static HashMap parseAlarmInfo(HashMap map) {
        ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
        ITawSystemAreaManager areaMgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
        StringBuffer compareStr = new StringBuffer();
        map.put("mainAlarmNum", map.get("alarmId"));
        map.put("mainAlarmId", map.get("alarmStaId"));
        map.put("mainIsPack", map.get("mainIsPack"));
        //增加打包位置信息 by lyg
        map.put("mainLocatinf", map.get("locatinginformation"));

        System.out.println(StaticMethod.nullObject2String(map.get("alarmTitle")));
        String title = splitString(StaticMethod.nullObject2String(map.get("alarmTitle")), 2000, "...");
        System.out.println("title=" + title);
        map.put("title", title);
        map.put("mainFaultGenerantTime", map.get("alarmCreateTime"));
        map.put("mainNetName", map.get("neName"));
        map.put("mainFaultResponseLevel", map.get("alarmLevel"));
        map.put("mainAlarmLogicSort", map.get("alarmType"));
        map.put("mainAlarmLogicSortSub", map.get("alarmSubType"));
        map.put("mainAlarmSource", map.get("alarmLocation"));
        String alarmDetail = splitString(StaticMethod.nullObject2String(map.get("alarmDetail")), 2000, "...");
        map.put("mainAlarmDesc", alarmDetail);
        map.put("mainEquipmentModel", map.get("equipType"));
        String NeTypeCode = StaticMethod.nullObject2String(map.get("NeType"));
        String mainNetSortThree = "";
        if (NeTypeCode.length() > 0) {
            List list = dictMgr.getDictByCode(NeTypeCode);
            if ((list == null) || (list.size() == 0))
                System.out.println("没有找到映射的网络分类");
            else
                try {
                    TawSystemDictType dict3 = (TawSystemDictType) list.get(0);
                    if (dict3 != null) {
                        mainNetSortThree = dict3.getDictId();
                        map.put("mainNetSortThree", mainNetSortThree);
                        compareStr.append(mainNetSortThree + ";");
                        String pId = dict3.getParentDictId();
                        TawSystemDictType dict2 = dictMgr.getDictByDictId(pId);
                        if (dict2 != null) {
                            map.put("mainNetSortTwo", dict2.getDictId());
                            compareStr.append(dict2.getDictId() + ";");
                            pId = dict2.getParentDictId();
                            if (!pId.equals("-1")) {
                                TawSystemDictType dict1 = dictMgr.getDictByDictId(pId);
                                if (dict1 != null)
                                    map.put("mainNetSortOne", dict1.getDictCode());
                                compareStr.append(dict1.getDictId() + ";");
                            } else {
                                compareStr.append(";");
                            }
                        } else {
                            compareStr.append(";");
                        }
                    } else {
                        compareStr.append(";");
                    }
                } catch (Exception err) {
                    System.out.println("没有找到映射的网络分类");
                }
        }
        String mainEquipmentFactoryCode = StaticMethod.nullObject2String(map.get("alarmVendor"));
        if (mainEquipmentFactoryCode.length() > 0) {
            List list = null;
            try {
                list = dictMgr.getDictByCode(mainEquipmentFactoryCode);
            } catch (Exception localException1) {
            }
            if ((list == null) || (list.size() == 0)) {
                System.out.println("没有找到映射的厂家类型");
                compareStr.append(";");
            } else {
                TawSystemDictType d = (TawSystemDictType) list.get(0);
                map.put("mainEquipmentFactory", d.getDictId());
                compareStr.append(d.getDictId() + ";");
            }
        }
        String mainFaultGenerantPrivCode = StaticMethod.nullObject2String(map.get("alarmProvince"));
        if (mainFaultGenerantPrivCode.length() > 0)
            try {
                TawSystemArea area = areaMgr.getAreaByCode(mainFaultGenerantPrivCode);
                if (area != null)
                    map.put("mainFaultGenerantPriv", area.getAreaname());
                else
                    System.out.println("没有找到映射的告警省份");
            } catch (Exception err) {
                System.out.println("没有找到映射的告警省份");
            }
        String mainFaultGenerantCityCode = StaticMethod.nullObject2String(map.get("alarmRegion"));
        System.out.println("告警地市:" + mainFaultGenerantCityCode);
        if (mainFaultGenerantCityCode.length() > 0)
            try {
                TawSystemArea area = areaMgr.getAreaByCode(mainFaultGenerantCityCode);
                if (area != null) {
                    System.out.println("告警地市Areaid:" + area.getAreaid());
                    map.put("toDeptId", area.getAreaid());
                    compareStr.append(area.getAreaid() + ";");
                } else {
                    System.out.println("没有找到映射的告警地市");
                    compareStr.append(";");
                }
            } catch (Exception err) {
                System.out.println("没有找到映射的告警地市");
            }
        String mainFaultResponseLevel = StaticMethod.nullObject2String(map.get("alarmLevel"));
        try {
            setLimitByLevel(mainFaultResponseLevel, mainNetSortThree, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("compareStr:" + compareStr);
        map.put("compareStr", compareStr);
        return map;
    }

    private static Map setLimitByLevel(String levelCode, String mainNetSortThree, Map map)
            throws Exception {
        System.out.println("mainFaultResponseLevel=" + levelCode);
        System.out.println("mainNetSortThree=" + mainNetSortThree);
        String limitDictPath = "";
        if ("101030401".equals(levelCode)) {
            limitDictPath = "dict.levelone";
            String copyRoleId = XmlManage.getFile("/config/commonfault-util.xml").getProperty("copyRoleId");
            System.out.println("一级处理抄送角色:" + copyRoleId);
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
            TawSystemSubRole subRole = sm.getMatchRoles("CommonFaultMainFlowProcess", toDeptId, copyRoleId, map);
            if ((subRole == null) || (subRole.getId() == null) || (subRole.getId().length() == 0)) {
                System.out.println("未找到抄送角色");
            } else {
                map.put("copyPerformer", subRole.getId());
                map.put("copyPerformerLeader", subRole.getId());
                map.put("copyPerformerType", "subrole");
            }
        } else {
            limitDictPath = "dict.leveltwo";
        }
        try {
            int t1 = 0;
            int t2 = 0;
            int t3 = 0;
            int dealtime = 0;
            int accepttime = 0;
            Date date = new Date();
            if ((levelCode.length() > 0) && (mainNetSortThree.length() > 0)) {
                ISheetLimitManager iSheetLimitManager = (ISheetLimitManager) ApplicationContextHolder.getInstance().getBean("IsheetLimitManager");
                SheetLimit limit = iSheetLimitManager.getSheetLimitByLastSpecial(mainNetSortThree, levelCode);
                if (limit != null) {
                    System.out.println("limit!=null");
                    t1 = Integer.parseInt(limit.getT1Limit());
                    t2 = Integer.parseInt(limit.getT2Limit());
                    t3 = Integer.parseInt(limit.getT3Limit());
                } else {
                    System.out.println("limit=null，使用默认时限");
                    t1 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT1"));
                    t2 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT2"));
                    t3 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT3"));
                }
                SheetLimit acceptLimit = iSheetLimitManager.getSheetLimitBySpecial(levelCode);
                if (acceptLimit != null) {
                    System.out.println("acceptLimit!=null");
                    if (acceptLimit.getAcceptLimit() != null) {
                        System.out.println("accepttime!=null");
                        accepttime = Integer.parseInt(acceptLimit.getAcceptLimit());
                    } else {
                        System.out.println("accepttime=null");
                        accepttime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".acceptLimit"));
                    }
                    if (acceptLimit.getReplyLimit() != null) {
                        System.out.println("dealtime!=null");
                        dealtime = Integer.parseInt(acceptLimit.getReplyLimit());
                    } else {
                        System.out.println("dealtime=null");
                        dealtime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".replyLimit"));
                    }
                } else {
                    System.out.println("acceptLimit=null");
                    dealtime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".replyLimit"));
                    accepttime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".acceptLimit"));
                }
            } else {
                System.out.println("mainFaultResponseLevel为空或netTypeCode为空，使用默认时限");
                t1 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT1"));
                t2 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT2"));
                t3 = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".completeLimitT3"));
                dealtime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".replyLimit"));
                accepttime = Integer.parseInt(XmlManage.getFile("/config/commonfault-util.xml").getProperty(limitDictPath + ".acceptLimit"));
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("nowdate = " + dateFormat.format(new Date()));
            System.out.println("date=" + dateFormat.format(date) + ";t1=" + t1);
            String mainCompleteLimitT1 = addMinute(date, t1);
            String mainCompleteLimitT2 = addMinute(date, t1 + t2);
            String mainCompleteLimitT3 = addMinute(date, t1 + t2 + t3);
            String sheetCompleteLimit = addMinute(date, dealtime);
            String sheetAcceptLimit = addMinute(date, accepttime);
            System.out.println("mainCompleteLimitT1=" + mainCompleteLimitT1);
            System.out.println("mainCompleteLimitT2=" + mainCompleteLimitT2);
            System.out.println("mainCompleteLimitT3=" + mainCompleteLimitT3);
            System.out.println("sheetCompleteLimit=" + sheetCompleteLimit);
            System.out.println("sheetAcceptLimit=" + sheetAcceptLimit);
            map.put("mainCompleteLimitT1", mainCompleteLimitT1);
            map.put("mainCompleteLimitT2", mainCompleteLimitT2);
            map.put("mainCompleteLimitT3", mainCompleteLimitT3);
            map.put("sheetCompleteLimit", sheetCompleteLimit);
            map.put("sheetAcceptLimit", sheetAcceptLimit);
            map.put("nodeAcceptLimit", addMinute(new Date(), accepttime));
            map.put("nodeCompleteLimit", addMinute(new Date(), t1));
        } catch (Exception err) {
            System.out.println("告警派单：读取时限失败");
            err.printStackTrace();
        }
        return map;
    }

    public static void createT1Task(Map mainrule, String T1subRoleId, String T1operateUserId, String preLinkId)
            throws Exception {
        ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
        CommonFaultTask T1Task = (CommonFaultTask) taskservice.getTaskModelObject().getClass().newInstance();
        try {
            T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        T1Task.setTaskName("FirstExcuteHumTask");
        T1Task.setTaskDisplayName("T1处理");
        T1Task.setFlowName("CommonFaultMainFlowProcess");
        T1Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.get("sendTime")));
        T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
        T1Task.setTaskStatus("5");
        T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
        T1Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
        T1Task.setOperateType("subrole");
        T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
        T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
        T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
        T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
        T1Task.setOperateRoleId(T1subRoleId);
        T1Task.setTaskOwner(T1operateUserId);
        T1Task.setIfWaitForSubTask("false");
        T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
        T1Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
        taskservice.addTask(T1Task);
    }

    public static CommonFaultLink createT1Link(Map mainrule, AutoDealSOP autoDealSop, String operateType, Date operateTime, String prelinkId)
            throws Exception {
        ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(operateTime);
        calendar.add(13, -10);
        CommonFaultLink T1link61 = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T1link61.setId(UUIDHexGenerator.getInstance().getID());
        T1link61.setOperateType(new Integer("61"));
        T1link61.setActiveTemplateId("FirstExcuteHumTask");
        T1link61.setOperateTime(calendar.getTime());
        T1link61.setOperateDay(calendar.get(5));
        T1link61.setOperateMonth(calendar.get(2) + 1);
        T1link61.setOperateYear(calendar.get(1));
        T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        T1link61.setToOrgRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T1link61.setPreLinkId(prelinkId);
        T1link61.setNodeAccessories("");
        T1link61.setToOrgType(new Integer(0));
        T1link61.setCompleteFlag(new Integer(0));
        T1link61.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T1link61.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
        T1link61.setTemplateFlag(0);
        T1link61.setOperateUserId(StaticMethod.nullObject2String(autoDealSop.getOperateUserId()));
        linkservice.addLink(T1link61);
        CommonFaultLink T1link = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T1link.setId(UUIDHexGenerator.getInstance().getID());
        T1link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        T1link.setOperateType(new Integer(operateType));
        T1link.setOperateTime(operateTime);
        T1link.setOperateDay(calendar.get(5));
        T1link.setOperateMonth(calendar.get(2) + 1);
        T1link.setOperateYear(calendar.get(1));
        T1link.setOperateUserId(StaticMethod.nullObject2String(autoDealSop.getOperateUserId()));
        T1link.setAcceptFlag(new Integer(0));
        T1link.setPreLinkId(prelinkId);
        T1link.setActiveTemplateId("FirstExcuteHumTask");
        T1link.setToOrgType(new Integer(0));
        T1link.setCompleteFlag(new Integer(0));
        T1link.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T1link.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
        String correlationKey = UUIDHexGenerator.getInstance().getID();
        T1link.setCorrelationKey(correlationKey);
        T1link.setTemplateFlag(0);
        if ("1".equals(operateType)) {
            T1link.setLinkIfMutualCommunication(autoDealSop.getIfMutualCommunication());
            T1link.setLinkIfGreatFault(autoDealSop.getIfGreatFault());
            T1link.setLinkIfSafe(autoDealSop.getIfSafe());
            T1link.setLinkFaultDesc(autoDealSop.getTranferReason());
            T1link.setLinkFaultFirstDealDesc(autoDealSop.getFaultFirstDealDesc());
        }
        if ("46".equals(operateType)) {
            T1link.setLinkFaultDealResult(autoDealSop.getFaultDealResult());
            T1link.setLinkIfGreatFault(autoDealSop.getIfBigFault());
            T1link.setLinkFaultReasonSort(autoDealSop.getFaultReasonSort());
            T1link.setLinkFaultReasonSubsection(autoDealSop.getFaultReasonSubsection());
            T1link.setLinkIfExcuteNetChange(autoDealSop.getIfCarryNetChange());
            T1link.setLinkIfFinallySolveProject(autoDealSop.getIfLastPlan());
            T1link.setLinkIfAddCaseDataBase(autoDealSop.getIfAddCaseDataBase());
            T1link.setLinkFaultAvoidTime(autoDealSop.getFaultAvoidTime());
            T1link.setLinkOperRenewTime(autoDealSop.getOperRenewTime());
            T1link.setLinkAffectTimeLength(autoDealSop.getAffectTimeLength());
            T1link.setFaultdealdesc(autoDealSop.getFaultReasonInfo());
            T1link.setLinkDealdesc(autoDealSop.getDealStep());
            T1link.setRemark(autoDealSop.getRemark());
            T1link.setFaultdealTime(autoDealSop.getFaultdealTime());
            T1link.setFaultTreatment(autoDealSop.getFaultTreatment());
            T1link.setLinkDealStep(autoDealSop.getDealStep());
        }
        T1link.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
        T1link.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
        T1link.setToOrgRoleId(autoDealSop.getTranferObject());
        linkservice.addLink(T1link);
        return T1link;
    }

    public static void createT2Task(Map mainrule, String T2subRoleId, String T2operateUserId, String preLinkId)
            throws Exception {
        ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
        CommonFaultTask T2Task = (CommonFaultTask) taskservice.getTaskModelObject().getClass().newInstance();
        try {
            T2Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        T2Task.setTaskName("SecondExcuteHumTask");
        T2Task.setTaskDisplayName("T2处理");
        T2Task.setFlowName("CommonFaultMainFlowProcess");
        T2Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.get("sendTime")));
        T2Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
        T2Task.setTaskStatus("5");
        T2Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
        T2Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
        T2Task.setOperateType("subrole");
        T2Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
        T2Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
        T2Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
        T2Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
        T2Task.setOperateRoleId(T2subRoleId);
        T2Task.setTaskOwner(T2operateUserId);
        T2Task.setIfWaitForSubTask("false");
        T2Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
        T2Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
        taskservice.addTask(T2Task);
    }

    public static CommonFaultLink createT2Link(Map mainrule, AutoDealSOP autoDealSop, Date operateTime, String prelinkId)
            throws Exception {
        ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(operateTime);
        calendar.add(13, -10);
        CommonFaultLink T1link61 = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T1link61.setId(UUIDHexGenerator.getInstance().getID());
        T1link61.setOperateType(new Integer("61"));
        T1link61.setOperateUserId(autoDealSop.getOperateUserId());
        T1link61.setActiveTemplateId("SecondExcuteHumTask");
        T1link61.setOperateTime(calendar.getTime());
        T1link61.setOperateDay(calendar.get(5));
        T1link61.setOperateMonth(calendar.get(2) + 1);
        T1link61.setOperateYear(calendar.get(1));
        T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        T1link61.setToOrgRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T1link61.setPreLinkId(prelinkId);
        T1link61.setNodeAccessories("");
        T1link61.setToOrgType(new Integer(0));
        T1link61.setCompleteFlag(new Integer(0));
        T1link61.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T1link61.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
        T1link61.setTemplateFlag(0);
        linkservice.addLink(T1link61);
        CommonFaultLink T2link = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T2link.setId(UUIDHexGenerator.getInstance().getID());
        T2link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        T2link.setOperateType(new Integer(46));
        T2link.setOperateTime(operateTime);
        T2link.setOperateDay(calendar.get(5));
        T2link.setOperateMonth(calendar.get(2) + 1);
        T2link.setOperateYear(calendar.get(1));
        T2link.setOperateUserId(autoDealSop.getOperateUserId());
        T2link.setAcceptFlag(new Integer(0));
        T2link.setPreLinkId(prelinkId);
        T2link.setActiveTemplateId("SecondExcuteHumTask");
        T2link.setToOrgType(new Integer(0));
        T2link.setCompleteFlag(new Integer(0));
        T2link.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T2link.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
        String correlationKey = UUIDHexGenerator.getInstance().getID();
        T2link.setCorrelationKey(correlationKey);
        T2link.setTemplateFlag(0);
        T2link.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
        T2link.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
        T2link.setToOrgRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T2link.setLinkFaultDealResult(autoDealSop.getFaultDealResult());
        T2link.setLinkIfGreatFault(autoDealSop.getIfBigFault());
        T2link.setLinkFaultReasonSort(autoDealSop.getFaultReasonSort());
        T2link.setLinkFaultReasonSubsection(autoDealSop.getFaultReasonSubsection());
        T2link.setLinkIfExcuteNetChange(autoDealSop.getIfCarryNetChange());
        T2link.setLinkIfFinallySolveProject(autoDealSop.getIfLastPlan());
        T2link.setLinkIfAddCaseDataBase(autoDealSop.getIfAddCaseDataBase());
        T2link.setLinkFaultAvoidTime(autoDealSop.getFaultAvoidTime());
        T2link.setLinkOperRenewTime(autoDealSop.getOperRenewTime());
        T2link.setLinkAffectTimeLength(autoDealSop.getAffectTimeLength());
        T2link.setFaultdealdesc(autoDealSop.getFaultReasonInfo());
        T2link.setLinkDealdesc(autoDealSop.getDealStep());
        T2link.setRemark(autoDealSop.getRemark());
        T2link.setFaultdealTime(autoDealSop.getFaultdealTime());
        T2link.setFaultTreatment(autoDealSop.getFaultTreatment());
        T2link.setLinkDealStep(autoDealSop.getDealStep());
        linkservice.addLink(T2link);
        return T2link;
    }

    public static void createHoldMessage(Map mainrule, Date operateTime, String prelink)
            throws Exception {
        ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
        ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        Calendar calendar = Calendar.getInstance();
        CommonFaultLink linkbean = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        linkbean.setId(UUIDHexGenerator.getInstance().getID());
        linkbean.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        linkbean.setOperateTime(operateTime);
        linkbean.setOperateType(new Integer(18));
        linkbean.setOperateDay(calendar.get(5));
        linkbean.setOperateMonth(calendar.get(2) + 1);
        linkbean.setOperateYear(calendar.get(1));
        linkbean.setOperateUserId(StaticMethod.nullObject2String(mainrule.get("sendUserId")));
        linkbean.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
        linkbean.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        linkbean.setOperaterContact(StaticMethod.nullObject2String(mainrule.get("sendContact")));
        linkbean.setToOrgRoleId("");
        linkbean.setToOrgType(new Integer(0));
        linkbean.setAcceptFlag(new Integer(2));
        linkbean.setCompleteFlag(new Integer(2));
        linkbean.setActiveTemplateId("HoldHumTask");
        linkbean.setPreLinkId(prelink);
        linkservice.addLink(linkbean);
        CommonFaultTask task = new CommonFaultTask();
        try {
            task.setId(UUIDHexGenerator.getInstance().getID());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        task.setTaskName("HoldHumTask");
        task.setTaskDisplayName("待归档");
        task.setFlowName("CommonFaultMainFlowProcess");
        task.setSendTime(new Date());
        task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
        task.setTaskStatus("5");
        task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
        task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
        task.setOperateType("subrole");
        task.setCreateTime(new Date());
        task.setCreateYear(calendar.get(1));
        task.setCreateMonth(calendar.get(2) + 1);
        task.setCreateDay(calendar.get(5));
        task.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        task.setTaskOwner(StaticMethod.nullObject2String(mainrule.get("sendUserId")));
        task.setOperateType("subrole");
        task.setIfWaitForSubTask("false");
        task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
        task.setPreLinkId(linkbean.getId());
        taskservice.addTask(task);
    }

    public static CommonFaultMain createMain(Map mainrule)
            throws Exception {
        ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        CommonFaultMain mainObject = (CommonFaultMain) mainservice.getMainObject();
        SheetBeanUtils.populateMap2Bean(mainObject, mainrule);
        mainObject.setEndResult("满意");
        mainObject.setStatus(new Integer(1));
        mainObject.setHoldStatisfied(Integer.valueOf(1030301));
        mainservice.addMain(mainObject);
        return mainObject;
    }

    public static void createLink0(Map mainrule)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        CommonFaultLink T0link = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T0link.setId(UUIDHexGenerator.getInstance().getID());
        T0link.setOperateType(new Integer("0"));
        T0link.setOperateUserId(StaticMethod.nullObject2String(mainrule.get("sendUserId")));
        T0link.setActiveTemplateId("");
        T0link.setOperateTime(new Date());
        T0link.setOperateDay(calendar.get(5));
        T0link.setOperateMonth(calendar.get(2) + 1);
        T0link.setOperateYear(calendar.get(1));
        T0link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        T0link.setToOrgRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T0link.setPreLinkId("");
        T0link.setNodeAccessories("");
        T0link.setToOrgType(new Integer(0));
        T0link.setCompleteFlag(new Integer(0));
        T0link.setOperateRoleId(StaticMethod.nullObject2String(mainrule.get("sendRoleId")));
        T0link.setOperateDeptId(StaticMethod.nullObject2String(mainrule.get("sendDeptId")));
        T0link.setTemplateFlag(0);
        linkservice.addLink(T0link);
    }

    public static CommonFaultLink createT1Link(Map mainrule, Date operateTime, String prelinkId, String subroleid, Date mainCompleteLimitT2Date)
            throws Exception {
        String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoUser"));
        String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoSubRole"));
        ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(operateTime);
        calendar.add(13, 30);
        CommonFaultLink T1link61 = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T1link61.setId(UUIDHexGenerator.getInstance().getID());
        T1link61.setOperateType(new Integer("61"));
        T1link61.setActiveTemplateId("FirstExcuteHumTask");
        T1link61.setOperateTime(calendar.getTime());
        T1link61.setOperateDay(calendar.get(5));
        T1link61.setOperateMonth(calendar.get(2) + 1);
        T1link61.setOperateYear(calendar.get(1));
        T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
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
        CommonFaultLink T1link = (CommonFaultLink) linkservice.getLinkObject().getClass().newInstance();
        T1link.setId(UUIDHexGenerator.getInstance().getID());
        T1link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
        T1link.setOperateType(new Integer(1));
        T1link.setOperateTime(calendar.getTime());
        T1link.setOperateDay(calendar.get(5));
        T1link.setOperateMonth(calendar.get(2) + 1);
        T1link.setOperateYear(calendar.get(1));
        T1link.setAcceptFlag(new Integer(0));
        T1link.setPreLinkId(prelinkId);
        T1link.setActiveTemplateId("FirstExcuteHumTask");
        T1link.setToOrgType(new Integer(0));
        T1link.setCompleteFlag(new Integer(0));
        T1link.setOperateUserId(StaticMethod.nullObject2String(autoUser));
        T1link.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
        T1link.setOperateDeptId("12201");
        String correlationKey = UUIDHexGenerator.getInstance().getID();
        T1link.setCorrelationKey(correlationKey);
        T1link.setTemplateFlag(0);

        T1link.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
        T1link.setToOrgRoleId(subroleid);
        T1link.setNodeAcceptLimit(mainCompleteLimitT2Date);
        T1link.setNodeCompleteLimit(mainCompleteLimitT2Date);
        linkservice.addLink(T1link);
        return T1link;
    }

    public static void createT1Task(Map mainrule, String preLinkId, Date mainCompleteLimitT1Date)
            throws Exception {
        String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoUser"));
        String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoSubRole"));
        ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
        CommonFaultTask T1Task = (CommonFaultTask) taskservice.getTaskModelObject().getClass().newInstance();
        try {
            T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        T1Task.setTaskName("FirstExcuteHumTask");
        T1Task.setTaskDisplayName("T1处理");
        T1Task.setFlowName("CommonFaultMainFlowProcess");
        T1Task.setSendTime((Date) mainrule.get("sendTime"));
        T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
        T1Task.setTaskStatus("5");
        T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
        T1Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
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
        T1Task.setAcceptTimeLimit(mainCompleteLimitT1Date);
        T1Task.setCompleteTimeLimit(mainCompleteLimitT1Date);
        taskservice.addTask(T1Task);
    }

    public static boolean isT2(String sheetId)
            throws Exception {
        ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");

        System.out.println("dpf-----query sheet isT2 start------");
        CommonFaultMain baseMain = (CommonFaultMain) mainservice.getMainBySheetId(sheetId);
        System.out.println("dpf-----query sheet isT2 end------");

        String mainid = baseMain.getId();
        List tasks = taskservice.getTasksBySheetKey(mainid, "'SecondExcuteHumTask'");

        return tasks.size() > 0;
    }

    public static String updateMainCheckcount(Map sheetMap)
            throws Exception {
        ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        String sheetId = (String) sheetMap.get("serialNo");
        String opDesc = (String) sheetMap.get("opDesc");

        if (!isT2(sheetId)) {
            return "该工单不属于T2环节";
        }

        System.out.println("dpf-----query sheet  start------");
        CommonFaultMain cfm = (CommonFaultMain) mainservice.getMainBySheetId(sheetId);
        System.out.println("dpf-----query sheet  end------");

        int checkcount = cfm.getCheckCount();
        System.out.println("dpf------------" + checkcount);
        System.out.println("");
        if (checkcount >= 3) {
            return "已经超过最大核实次数3";
        }
        checkcount++;

        cfm.setCheckCount(checkcount);
        cfm.setMainApplyCheckTime(new Date());
        cfm.setMainCheckStatus("1");
        if ((!"".equals(opDesc)) && (opDesc != null)) {
            cfm.setMainApplyReason(opDesc);
        }

        mainservice.saveOrUpdateMain(cfm);
        System.out.println("---------------dpf-updateMainCheckcount end------------");
        return "0";
    }

    public static void workSM_NON_T(String sheetId, String receiverId, String title) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            String nodeInstantName = "worksheet.CommonFaultMainFlowProcess.smsServiceId.instant";
            System.out.println("====" + nodeInstantName);
            String filePath = SheetStaticMethod.getFilePathForUrl("classpath:config/worksheet-sms-service-info.xml");
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String receivers = "";

            String[] receiverIds = receiverId.split(",");
            for (int i = 0; i < receiverIds.length; i++) {
                receivers = receivers + 3 + "," + receiverIds[i] + "#";
            }
            System.out.println("receivers=" + receivers);

            String sendContent = "提醒您收取故障工单:" + sheetId + ",主题名:" + title + ",请查阅!";

            TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
            Calendar c = Calendar.getInstance(TimeZone.getDefault());
            Date localTime = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String messageSendTime = formatter.format(localTime);
            System.out.println("lizhi:instantServiceId" + instantServiceId + "sendContent" + sendContent + "sheetKey" + sheetId + "receivers" + receivers + "messageSendTime" + messageSendTime);
            msgService.sendMsg(instantServiceId, sendContent,
                    sheetId, receivers, messageSendTime);
        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }
    }
//  private static Map ruleforChuanShuAutoT12T2(Map WpsMap){
//	  //add new rule zhangjiang --传输专业新增规则，根据告警映射表和铁塔ID派发工单，如不满足按照原来规则派单
//	  Map mainrule = (Map)WpsMap.get("main");
//	  Map operate = (Map)WpsMap.get("operate");
//	  Map linkrule = (Map)WpsMap.get("link");
//	  Map mChuanshu = new HashMap();
//     mChuanshu = findT2DealPerformerByMainTowerId(mainrule);
//     if(mChuanshu.size()>0){
//	  if(mChuanshu.containsKey("subRoleId")&&mChuanshu.containsKey("copyPerformer")){
//	System.out.println("--zhangjiang--"+"传输专业:匹配告警ID，匹配到铁塔编号，--匹配对象--"+mChuanshu.get("subRoleId")+"--抄送对象--"+mChuanshu.get("copyPerformer"));
//    operate.put("dealPerformer", mChuanshu.get("subRoleId"));
//    operate.put("dealPerformerLeader", mChuanshu.get("subRoleId"));
//    operate.put("dealPerformerType", "subrole");
//    operate.put("copyPerformer", mChuanshu.get("copyPerformer"));
//    operate.put("copyPerformerLeader", mChuanshu.get("copyPerformer"));
//    operate.put("copyPerformerType", "subrole");
//    linkrule.put("toOrgRoleId", mChuanshu.get("subRoleId"));
//    WpsMap.put("link", linkrule);
//    WpsMap.put("operate", operate);
//    return WpsMap;
//	  }else{
//		  System.out.println("--zhangjiang--"+"传输专业:Map 存放key value 不匹配");
//		  return WpsMap;
//	  }
//}else{
//	  System.out.println("--zhangjiang--"+"传输专业:匹配告警ID，匹配到铁塔编号，按照原来规则派单");
//	  return WpsMap;
//}
//  }
//  private static Map findT2DealPerformerByMainTowerId(Map mainrule){
//		//获取传输dictId并判断mainNetSortOne为传输专业
//		String mainNetSortOne =StaticMethod.nullObject2String(mainrule.get("mainNetSortOne"));
//		String netSortOneChuanShu = getSqlTemplate("/config/commonfault-util.xml","mainNetSortOne").trim();
//		if(netSortOneChuanShu.equals(mainNetSortOne)){
////		if(true){
//			//获取告警Id，查找告警id是否在告警整合表中
//			String mainAlarmId = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));
//			String sqlForAlarmId = getSqlTemplate("/config/commonfault-util.xml","isCommonfaultAlarmInfoContainsAlarmId")
//								.replace("@alarmId@", mainAlarmId);
//			List list = getResult(sqlForAlarmId);
//			if(list!=null&&list.size()>0){
//				//获取铁塔编号
//				String mainTowerId=StaticMethod.nullObject2String(mainrule.get("mainTowerId"));
//				if(!"".equals(mainTowerId)){
//					String sql = getSqlTemplate("/config/commonfault-util.xml","getRoleIdByTowerId")
//								.replace("@towerId@", mainTowerId);
//					List listSubRoleId = getResult(sql);
//					if(list!=null&&list.size()>0){
//						return (Map)listSubRoleId.get(0);
//					}
//
//				}else{
//					System.out.println("--mainTowerId has no value--");
//				}
//			}
//		}
//		return new HashMap();
//	}
//
//	/**
//	 * 根据指定的文件从xml文件中获取相应字符串(sql模板)
//	 * @param String FilePath -文件路径
//	 * @param String templateName -xml标签名称
//	 * */
//	public static String getSqlTemplate(String filePath,String templateName){
//		return StaticMethod.nullObject2String(XmlManage.getFile(filePath).getProperty(templateName));
//	}
///**
//* queryList 根据制定sql获取list列表
//* @param String 可执行sql
//* @throws Exception
//* */
//	public static List getResult(String sqlStatement) {
//		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
//		try {
//			return sqlMgr.getSheetAccessoriesList(sqlStatement);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

    /**
     * add by zhangjiang
     * 新增T1到T2的派单规则（原有规则：根据工单基本信息中的“网元名称”字段值，通过映射表，匹配到派往的T2班组）
     * <p>
     * 本次改造新增规则：T1自动移交T2的时候，根据基本信息中“告警ID”匹配数据库中的“告警ID整合表”
     * （这张表需要进行新建，包括以下四个字段：id、告警ID、修改人、修改时间，现场自行维护表中数据），
     * 若“告警ID”在此表中，则根据基本信息中“铁塔编码”信息，通过映射表，匹配到派往的T2班组
     * 若以上没匹配到，按照原来规则派单
     * if(findT2DealPerformerByMainTowerId(mainrule).size()>0){
     * Map m = findT2DealPerformerByMainTowerId(mainrule);
     * if(m.containsKes("subRoleId")){
     * String subrole = m.get("subRoleId");
     * }
     * if(m.containsKey("copyPerformer"){
     * String CopyPerformer = m.get("copyPerformer");
     * }
     * <p>
     * }
     */
    private static Map findT2DealPerformerByMainTowerId(Map mainrule) {
        //获取传输dictId并判断mainNetSortOne为传输专业
        String mainNetSortOne = StaticMethod.nullObject2String(mainrule.get("mainNetSortOne"));
        String netSortOneChuanShu = getSqlTemplate("/config/commonfault-util.xml", "mainNetSortOne").trim();
        System.out.println("netSortOneChuanShu=" + netSortOneChuanShu + "====mainNetSortOne=" + mainNetSortOne);
        if (netSortOneChuanShu.equals(mainNetSortOne)) {
//		if(true){
            //获取告警Id，查找告警id是否在告警整合表中
            String mainAlarmId = StaticMethod.nullObject2String(mainrule.get("mainAlarmId"));
            String sqlForAlarmId = getSqlTemplate("/config/commonfault-util.xml", "isCommonfaultAlarmInfoContainsAlarmId")
                    .replace("@alarmId@", mainAlarmId);
            List list = getResult(sqlForAlarmId);
            if (list != null && list.size() > 0) {
                //获取铁塔编号
                String mainTowerId = StaticMethod.nullObject2String(mainrule.get("mainTowerId"));
                if (!"".equals(mainTowerId)) {
                    String sql = getSqlTemplate("/config/commonfault-util.xml", "getSubRoleIdByTowerId")
                            .replace("@towerId@", mainTowerId);
                    List listSubRoleId = getResult(sql);
                    if (listSubRoleId != null && listSubRoleId.size() > 0) {
                        return (Map) listSubRoleId.get(0);
                    }

                }
            }
        }
        return null;
    }

    /**
     * 根据指定的文件从xml文件中获取相应字符串(sql模板)
     *
     * @param String FilePath -文件路径
     * @param String templateName -xml标签名称
     */
    public static String getSqlTemplate(String filePath, String templateName) {
        return StaticMethod.nullObject2String(XmlManage.getFile(filePath).getProperty(templateName));
    }

    /**
     * queryList 根据制定sql获取list列表
     *
     * @param String 可执行sql
     * @throws Exception
     */
    public static List getResult(String sqlStatement) {
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        try {
            return sqlMgr.getSheetAccessoriesList(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用铁塔简单接口
     *
     * @param mainMap
     * @param operateMap
     * @return
     * @throws Exception
     */
    public static Map townerSend(Map mainMap, Map operateMap) {

        String temPhaseId = StaticMethod.nullObject2String(operateMap.get("phaseId"));
        String temSubRoleId = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
        System.out.println("temPhaseId==towner=lyg=" + temPhaseId + "===temSubRoleId=" + temSubRoleId);

        String sheetKey = StaticMethod.nullObject2String(mainMap.get("id"));
        String sheetId = StaticMethod.nullObject2String(mainMap.get("sheetId"));

        System.out.println("sheetKey==lyg==" + sheetKey + "====sheetId==lyg==" + sheetId);

        if (!"".equals(temSubRoleId)) {
            IDownLoadSheetAccessoriesService sheetServices = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            String townerSql = "SELECT * FROM taw_system_sub_role WHERE id = '" + temSubRoleId + "'";
            List townerList = new ArrayList();
            try {
                townerList = sheetServices.getSheetAccessoriesList(townerSql);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("err==townerSend====townerSql===");
                e.printStackTrace();
            }
            String type7 = "";
            if (townerList != null && townerList.size() > 0) {
                type7 = StaticMethod.nullObject2String(((Map) townerList.get(0)).get("type7"));
            }
            if ("SecondExcuteTask".equals(temPhaseId) && "3".equals(type7)) {
                mainMap.put("mainIFTowner", "1");//是铁塔工单并且调用接口返回值为N或者返回超时
//	        	  IrontowerUtil townerUtil = new IrontowerUtil();
                String filePath = "";
                try {
                    filePath = StaticMethod.getFilePathForUrl("classpath:config/commonfaulttowner-util.xml");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    System.out.println("err==townerSend====filePath===");
                    e.printStackTrace();
                }
                InterfaceUtilProperties properties = new InterfaceUtilProperties();
                String opDetail = "";
                try {
                    opDetail = properties.getXmlFromMap(mainMap, filePath, "newWorkSheet");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("err==townerSend====opDetail===");
                    e.printStackTrace();
                }
                System.out.println("newWork map to xml =" + opDetail);
                String mainTownerDeviceId = StaticMethod.nullObject2String(mainMap.get("mainTownerDeviceId"));
                //调用接口

                CommonFaultBOThread t1 = new CommonFaultBOThread(opDetail, mainTownerDeviceId);
                t1.start();
            }
        }
        return mainMap;
    }

    public static String getDateByType(String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        String dateStr1 = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        return dateStr1;
    }

}