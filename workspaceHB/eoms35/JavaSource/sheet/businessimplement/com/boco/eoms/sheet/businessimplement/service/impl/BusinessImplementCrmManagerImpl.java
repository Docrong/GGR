package com.boco.eoms.sheet.businessimplement.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.Irms.bo.IrmsResLoader;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.interfaces.transfer.client.TransferLoader;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementTask;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementLinkManager;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementTaskManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.InterfaceUtil;


public class BusinessImplementCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage {

    public String getLinkBeanId() {
        // TODO Auto-generated method stub
        return "iBusinessImplementLinkManager";
    }

    public String getMainBeanId() {
        // TODO Auto-generated method stub
        return "iBusinessImplementMainManager";
    }

    public String getOperateName() {
        // TODO Auto-generated method stub
        return "newWorkSheet";
    }

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "BusinessImplementProcess";
    }

    public String getSendUser(Map map) {
        String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplement-crm.xml").getProperty("base.InterfaceUser"));
        return userId;
    }

    public String getTaskBeanId() {
        // TODO Auto-generated method stub
        return "iBusinessImplementTaskManager";
    }

    public HashMap addPara(HashMap hashMap) {
        try {
            System.out.println("star corrKey...");
            hashMap.put("corrKey", UUIDHexGenerator.getInstance().getID());
            System.out.println("corrKey=" + hashMap.get("corrKey").toString());
            HashMap main = (HashMap) hashMap.get("main");
            HashMap operate = (HashMap) hashMap.get("operate");
            String mainSpecifyType = (String) main.get("mainSpecifyType");
            String orderSheetId = (String) main.get("orderSheetId");
            Object objectName = "";
            String cityA = "";
            String cityZ = "";
            IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
                    .getInstance().getBean("IOrderSheetManager");
            if (mainSpecifyType != null
                    && !mainSpecifyType.equals("")
                    && (mainSpecifyType.equals("101230101")
                    || mainSpecifyType.equals("101100101") || mainSpecifyType
                    .equals("101220101"))) {// GPRS专线
                objectName = new GprsSpecialLine();
            } else if (mainSpecifyType != null
                    && !mainSpecifyType.equals("")
                    && (mainSpecifyType.equals("101230102")
                    || mainSpecifyType.equals("101100102") || mainSpecifyType
                    .equals("101220102"))) {// IP专线
                objectName = new IPSpecialLine();
            } else if (mainSpecifyType != null
                    && !mainSpecifyType.equals("")
                    && (mainSpecifyType.equals("101230103")
                    || mainSpecifyType.equals("101100103") || mainSpecifyType
                    .equals("101220103"))) {// 传输专线
                objectName = new TransferSpecialLine();
            } else if (mainSpecifyType != null
                    && !mainSpecifyType.equals("")
                    && (mainSpecifyType.equals("101230104")
                    || mainSpecifyType.equals("101100104") || mainSpecifyType
                    .equals("101220104"))) {//
                objectName = new LanguageSpecialLine();
            } else if (mainSpecifyType != null
                    && !mainSpecifyType.equals("")
                    && (mainSpecifyType.equals("101230105")
                    || mainSpecifyType.equals("101230105") || mainSpecifyType
                    .equals("101230106"))) {//
                objectName = new Smsspecialline();
            }
            List list = mgr.getSpecialLinesByType(orderSheetId, objectName);
            for (int i = 0; list != null && i < list.size(); i++) {
                Map dataMap = SheetBeanUtils.bean2Map(list.get(i));
                cityA = StaticMethod.nullObject2String(dataMap.get("cityA"));
                cityZ = StaticMethod.nullObject2String(dataMap.get("cityZ"));
            }
            if (mainSpecifyType != null
                    && !mainSpecifyType.equals("")
                    && (mainSpecifyType.equals("101230103")
                    || mainSpecifyType.equals("101100103") || mainSpecifyType
                    .equals("101220103")) && !"".equals(cityA) && cityA.equals(cityZ)) {
                String subroleid = "";
                String subrolename = "";
                ITawSystemSubRoleManager itawsystemsubrolemanager = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
                List listrole = itawsystemsubrolemanager.getTawSystemSubRoles(917);
                for (int i = 0; i < listrole.size(); i++) {
                    TawSystemSubRole tawsystemsubrole = (TawSystemSubRole) listrole.get(i);
                    subrolename = tawsystemsubrole.getSubRoleName();
                    if (subrolename.indexOf(cityA) != -1) {
                        subroleid = tawsystemsubrole.getId();
                    }
                }
                if (null != subroleid && !"".equals(subroleid)) {
                    operate.put("phaseId", "ProjectDealTask");
                    operate.put("dealPerformer", StaticMethod.nullObject2String(subroleid));
                    operate.put("dealPerformerLeader", StaticMethod.nullObject2String(subroleid));
                    operate.put("dealPerformerType", "subrole");
                    this.createTaskAndLink(main);
                }
            }
            hashMap.put("main", main);
            hashMap.put("operate", operate);
        } catch (Exception err) {
            err.printStackTrace();
        }
        return hashMap;
    }

    public void createTaskAndLink(Map main) {
        IBusinessImplementLinkManager linkservice = (IBusinessImplementLinkManager) ApplicationContextHolder.getInstance().getBean("iBusinessImplementLinkManager");
        IBusinessImplementTaskManager taskservice = (IBusinessImplementTaskManager) ApplicationContextHolder.getInstance().getBean("iBusinessImplementTaskManager");
        Calendar calendar = Calendar.getInstance();

        BusinessImplementLink linkbean = new BusinessImplementLink();
        try {
            linkbean.setId(UUIDHexGenerator.getInstance().getID());
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        linkbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
        calendar.add(Calendar.SECOND, 5);
        linkbean.setOperateTime(calendar.getTime());
        linkbean.setOperateUserId("fangmin");
        linkbean.setToOrgType(new Integer(0));
        linkbean.setAcceptFlag(new Integer(1));
        linkbean.setCompleteFlag(new Integer(1));
        linkbean.setOperateType(new Integer(61));
        linkbean.setOperateDay(calendar.get(5));
        linkbean.setOperateMonth(calendar.get(2) + 1);
        linkbean.setOperateYear(calendar.get(1));
        linkbean.setOperateDeptId("12201");
        linkbean.setOperateRoleId("8a9982f22ccfa1c9012cf216f94939e9");
        linkbean.setOperaterContact(StaticMethod.nullObject2String(main.get("sendContact")));
        linkbean.setToOrgRoleId(StaticMethod.nullObject2String(main.get("sendUserId")));
        linkbean.setActiveTemplateId("ImplementDealTask");
        try {
            linkservice.addLink(linkbean);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        BusinessImplementLink commitbean = new BusinessImplementLink();
        try {
            commitbean.setId(UUIDHexGenerator.getInstance().getID());
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        commitbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
        calendar.add(Calendar.SECOND, 10);
        commitbean.setOperateTime(calendar.getTime());
        commitbean.setOperateType(new Integer(91));
        commitbean.setOperateDay(calendar.get(5));
        commitbean.setOperateMonth(calendar.get(2) + 1);
        commitbean.setOperateYear(calendar.get(1));
        commitbean.setOperateUserId("fangmin");
        commitbean.setOperateDeptId("12201");
        commitbean.setOperateRoleId("8a9982f22ccfa1c9012cf216f94939e9");
        commitbean.setOperaterContact(StaticMethod.nullObject2String(main.get("sendContact")));
        commitbean.setToOrgRoleId(StaticMethod.nullObject2String(main.get("sendUserId")));
        commitbean.setToOrgType(new Integer(0));
        commitbean.setAcceptFlag(new Integer(1));
        commitbean.setCompleteFlag(new Integer(1));
        commitbean.setActiveTemplateId("ImplementDealTask");
        commitbean.setLinkArugmentlevel("101010202");
        commitbean.setLinkNeedFinishTime(calendar.getTime());
        commitbean.setLinkSenderOpinition("请处理");
        try {
            linkservice.addLink(commitbean);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        BusinessImplementTask task = new BusinessImplementTask();
        try {
            task.setId(UUIDHexGenerator.getInstance().getID());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        task.setTaskName("ImplementDealTask");
        task.setTaskDisplayName("开通工单受理");
        task.setFlowName("BusinessImplementProcess");
        task.setSendTime(new Date());
        task.setSheetKey(StaticMethod.nullObject2String(main.get("id")));
        task.setTaskStatus("5");
        task.setSheetId(StaticMethod.nullObject2String(main.get("sheetId")));
        task.setTitle(StaticMethod.nullObject2String(main.get("title")));
        task.setOperateType("subrole");
        task.setCreateTime(new Date());
        task.setCreateYear(calendar.get(1));
        task.setCreateMonth(calendar.get(2) + 1);
        task.setCreateDay(calendar.get(5));
        task.setOperateRoleId("8a9982f22ccfa1c9012cf216f94939e9");
        task.setTaskOwner(StaticMethod.nullObject2String(main.get("sendUserId")));
        task.setIfWaitForSubTask("false");
        task.setParentTaskId("_AI:" + (new Date()).getTime());
        task.setPreLinkId(linkbean.getId());
        try {
            taskservice.addTask(task);
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        try {
            map = this.loadDefaultMap(map, "config/businessimplement-crm.xml", type);
            if (type.equals(InterfaceConstants.WORKSHEET_NEW) || type.equals(InterfaceConstants.WORKSHEET_RENEW)) {

                String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplement-crm.xml").getProperty("base.SendRoleId"));
                map.put("sendRoleId", sendRoleId);

                String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplement-crm.xml").getProperty("base.AcceptGroupId"));
                String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));

                WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                String subRoleId = "";
                TawSystemSubRole subRole = sm.getMatchRoles("BusinessImplementProcess", toDeptId, bigRole, map);
                if (subRole == null || subRole.getId() == null || subRole.getId().length() == 0) {
                    System.out.println("未找到匹配角色，使用默认角色");
                    subRoleId = XmlManage.getFile("/config/businessimplement-crm.xml").getProperty("base.AcceptRoleId");
                } else {
                    subRoleId = subRole.getId();
                }
                map = sm.setAcceptRole(subRoleId, map);


                String orderSheetId = StaticMethod.nullObject2String(map.get("orderSheetId"));
                if (orderSheetId.equals("")) {
                    OrderSheet order = new OrderSheet();
                    SheetBeanUtils.populateMap2Bean(order, map);

                    orderSheetId = UUIDHexGenerator.getInstance().getID();
                    order.setId(orderSheetId);
                    IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
                    mgr.saveOrUpdate(order);
                }

                String attachId = this.getAttach(attach);
                System.out.println("#####################businessimplement#######attachId=" + attachId);
                if (attachId != null && attachId.length() > 0) {
                    map.put("businessimplement", attachId);
                    map.put("sheetAccessories", attachId);
                }
            }
            if (type.equals(InterfaceConstants.WORKSHEET_RENEW) || type.equals(InterfaceConstants.WORKSHEET_UNTREAD)) {
                String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
                if (sheetId == null || sheetId.equals(""))
                    throw new Exception("sheetId为空");

                BaseMain main = null;
                IMainService iMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
                ITaskService iTaskService = (ITaskService) ApplicationContextHolder.getInstance().getBean(this.getTaskBeanId());

                List list = iMainService.getMainListByParentSheetId(sheetId);
                if (list.size() > 0) {
                    boolean b = false;
                    main = (BaseMain) list.get(0);
                    System.out.println("SendRoleId=" + main.getSendRoleId());
                    List taskList = iTaskService.getCurrentUndoTask(main.getId());
                    if (taskList != null) {
                        for (int i = 0; i < taskList.size(); i++) {
                            ITask task = (ITask) taskList.get(i);
                            System.out.println("TaskOwner=" + task.getTaskOwner());
                            if (task.getTaskOwner().equals(main.getSendRoleId()) || task.getTaskOwner().equals(main.getSendUserId()) || task.getTaskOwner().equals(main.getSendDeptId())) {
                                b = true;
                                break;
                            }
                        }
                    }
                    System.out.println("allow renew:" + b);
                    if (!b) {
                        if (type.equals(InterfaceConstants.WORKSHEET_RENEW))
                            throw new Exception("工单未被驳回，不能重派");
                        else if (type.equals(InterfaceConstants.WORKSHEET_UNTREAD))
                            throw new Exception("工单未回复，不能退回");
                    }

                } else
                    throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
                System.out.println("attach size==" + attach.size());
                String attachId = this.getAttach(attach);
                System.out.println("#####################businessimplement#######attachId=" + attachId);
                if (attachId != null && attachId.length() > 0) {
                    map.put("businessimplement", attachId);
                    map.put("sheetAccessories", attachId);
                }
            }
            if (type.equals(InterfaceConstants.WORKSHEET_HOLD)) {    //归档
                String endResult = StaticMethod.nullObject2String(map.get("endResult"));
                BocoLog.info(this, "endResult=" + endResult);
                BusinessImplementMain main = null;
                IMainService iMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());

                String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
                List list = iMainService.getMainListByParentSheetId(sheetId);
                if (list.size() > 0) {
                    main = (BusinessImplementMain) list.get(0);
                    HashMap orderMap = SheetBeanUtils.bean2Map(main);

                    String enable = XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.enable");
                    if (enable.equalsIgnoreCase("true")) {
                        IrmsResourceBo.occupyServiceRes(orderMap, null);    //资源实占
                    }

                    String orderSheetId = StaticMethod.nullObject2String(main.getOrderSheetId());
                    List productList = OrderSheetMethod.getSpecialLineList(orderSheetId, main.getMainSpecifyType());

                    enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml")
                            .getProperty("enable");

                    if (enable.equalsIgnoreCase("true")) {
                        //传输归档
                        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(orderMap, productList, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "irmsAttempArchive");
                        String result = TransferLoader.irmsAttempArchive(main.getSendUserId(), "", opDetail);

                        InterfaceUtil interfaceUtil = new InterfaceUtil();
                        HashMap sheetMap = new HashMap();
                        sheetMap = interfaceUtil.xmlElements(result, sheetMap, "FieldContent");
                        String isSuccess = StaticMethod.nullObject2String(sheetMap.get("isSuccess"));
                        if (isSuccess.equals("0"))
                            throw new Exception("传输归档失败：" + StaticMethod.nullObject2String(sheetMap.get("errorInfo")));
                    }
                    enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/Irms/config/irmsres-config.xml").getProperty("enable");
                    if (enable.equalsIgnoreCase("true")) {
                        //将电路名称传给IRMS
                        IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
                        OrderSheet ordersheet = (OrderSheet) mgr.getOrderSheet(orderSheetId);
                        orderMap.putAll(SheetBeanUtils.bean2Map(ordersheet));
                        IrmsResLoader.addEomsResByProdTypeBO(orderMap, productList);
                    }
                } else {
                    throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
                }
            }
            return map;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public String getSheetAttachCode() {
        return "businessimplement";
    }

}
