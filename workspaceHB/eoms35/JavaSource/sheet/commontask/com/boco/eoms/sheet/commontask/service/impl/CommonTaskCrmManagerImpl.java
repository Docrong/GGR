package com.boco.eoms.sheet.commontask.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;

public class CommonTaskCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage {

    public String getLinkBeanId() {
        // TODO Auto-generated method stub
        return "iCommonTaskLinkManager";
    }

    public String getMainBeanId() {
        // TODO Auto-generated method stub
        return "iCommonTaskMainManager";
    }

    public String getOperateName() {
        // TODO Auto-generated method stub
        return "newWorksheet";
    }

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "CommonTaskMainFlowProcess";
    }

    public HashMap addPara(HashMap hashMap) {
        try {
            System.out.println("star corrKey...");
            hashMap.put("corrKey", UUIDHexGenerator.getInstance().getID());
            System.out.println("corrKey=" + hashMap.get("corrKey").toString());
        } catch (Exception err) {
            err.printStackTrace();
        }
        return hashMap;
    }

    public String getSendUser(Map map) {
        String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.InterfaceUser"));
        return userId;
    }

    public String getTaskBeanId() {
        // TODO Auto-generated method stub
        return "icommontaskTaskManager";
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        try {
            map = this.loadDefaultMap(map, "config/CommonTask-crm.xml", type);
            if (type.equals(InterfaceConstants.WORKSHEET_NEW) || type.equals(InterfaceConstants.WORKSHEET_RENEW)) {
                String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.SendRoleId"));
                map.put("sendRoleId", sendRoleId);

                WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                if (type.equals(InterfaceConstants.WORKSHEET_NEW)) {
                    String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.AcceptGroupId"));
                    String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));

                    String subRoleId = "";
                    TawSystemSubRole subRole = sm.getMatchRoles("CommonTaskMainFlowProcess", toDeptId, bigRole, map);
                    if (subRole == null || subRole.getId() == null || subRole.getId().length() == 0) {
                        System.out.println("未找到匹配角色，使用默认角色");
                        subRoleId = XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.AcceptRoleId");
                    } else {
                        subRoleId = subRole.getId();
                    }
                    String mainTaskDeptId = StaticMethod.nullObject2String(map.get("mainTaskDeptId"));
                    System.out.println("lizhi:mainTaskDeptId=" + mainTaskDeptId);
                    if (null != mainTaskDeptId && !"".equals(mainTaskDeptId)) {
                        ITawSystemDeptManager itawsystemdeptmanager = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
                        TawSystemDept TawSystemDept = (TawSystemDept) itawsystemdeptmanager.getDeptinfobydeptid(mainTaskDeptId, "0");
                        String deptid = StaticMethod.nullObject2String(TawSystemDept.getDeptId());
                        System.out.println("lizhi:deptid=" + deptid);
                        if (null != deptid && !"".equals(deptid)) {
                            subRoleId = mainTaskDeptId;
                        }
                    }
                    map = sm.setAcceptRole(subRoleId, map);
                } else {
                    map = sm.setAcceptRole("", map);

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
                        List taskList = iTaskService.getCurrentUndoTask(main.getId());
                        if (taskList != null) {
                            for (int i = 0; i < taskList.size(); i++) {
                                ITask task = (ITask) taskList.get(i);
                                if (task.getTaskOwner().equals(main.getSendRoleId()) || task.getTaskOwner().equals(main.getSendUserId()) || task.getTaskOwner().equals(main.getSendDeptId())) {
                                    b = true;
                                    break;
                                }
                            }
                        }
                        if (!b) {
                            throw new Exception("工单未被驳回，不能重派");
                        }

                    } else
                        throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");

                }
            } else if (type.equals(InterfaceConstants.WORKSHEET_UNTREAD)) {
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
                    List taskList = iTaskService.getCurrentUndoTask(main.getId());
                    if (taskList != null) {
                        for (int i = 0; i < taskList.size(); i++) {
                            ITask task = (ITask) taskList.get(i);
                            if (task.getTaskOwner().equals(main.getSendRoleId()) || task.getTaskOwner().equals(main.getSendUserId()) || task.getTaskOwner().equals(main.getSendDeptId())) {
                                b = true;
                                break;
                            }
                        }
                    }
                    if (!b) {
                        throw new Exception("工单未回复，不能退回");
                    }

                } else
                    throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
            }
            return map;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public String getSheetAttachCode() {
        return "commontask";
    }
}
