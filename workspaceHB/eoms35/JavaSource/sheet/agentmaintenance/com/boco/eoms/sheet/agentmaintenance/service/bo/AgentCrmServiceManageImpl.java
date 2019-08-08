package com.boco.eoms.sheet.agentmaintenance.service.bo;

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
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;

public class AgentCrmServiceManageImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage {

    public String getLinkBeanId() {
        // TODO Auto-generated method stub
        return "iAgentMaintenanceLinkManager";
    }

    public String getMainBeanId() {
        // TODO Auto-generated method stub
        return "iAgentMaintenanceMainManager";
    }

    public String getOperateName() {
        // TODO Auto-generated method stub
        return "newWorksheet";
    }

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "AgentMaintenanceMainFlowProcess";
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
        String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.InterfaceUser"));
        return userId;
    }

    public String getTaskBeanId() {
        // TODO Auto-generated method stub
        return "iagentmaintenanceTaskManager";
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        try {
            map = this.loadDefaultMap(map, "config/agentmaintenance-crm.xml", type);
            if (type.equals("newWorkSheet") || type.equals("renewWorkSheet")) {
                String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.SendRoleId"));
                map.put("sendRoleId", sendRoleId);
                WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                if (type.equals("newWorkSheet")) {
                    String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.AcceptGroupId"));
                    String toDeptId = StaticMethod.nullObject2String(map.get("mainComstartDealCity"));

                    String subRoleId = "";
                    String Tooperateroleid = StaticMethod.nullObject2String(map.get("Tooperateroleid"));
                    //			TawSystemSubRole subRole = sm.getMatchRoles("AgentMaintenanceMainFlowProcess", toDeptId, bigRole, map);
                    ITawSystemSubRoleManager tawsubrole = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
                    TawSystemSubRole subRole = tawsubrole.getTawSystemSubRole(Tooperateroleid);
                    if (!"".equals(Tooperateroleid) && Tooperateroleid != null && subRole != null && (subRole.getId()) != null && !"".equals(subRole.getId())) {
                        subRoleId = subRole.getId();

                    } else {
                        System.out.println("newWorkSheet未找到匹配角色,工单生成失败");
                        throw new Exception("工单生成失败，Tooperateroleid无效");
                    }
                    String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
                    map = setComplaintType(map, complaintType);
                    map = sm.setAcceptRole(subRoleId, map);

                    //保存附件
                    String attachId = getAttach(attach);
                    System.out.println("attachId=" + attachId);
                    if (attachId != null && attachId.length() > 0) {
                        map.put("sheetAccessories", attachId);
                    }
                } else {
                    map = sm.setAcceptRole("", map);
                    String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
                    map = setComplaintType(map, complaintType);
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
            } else if (type.equals("untreadWorkSheet")) {
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


    public Map setComplaintType(Map map, String code) {
        try {
            System.out.println("complaintType=" + code);
            ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
            String parentDictId = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.ComplaintTypeDictId"));
            for (int i = 1; i * 2 <= (code.length() - 4); i++) {
                System.out.println("parentDictId" + i + "=" + parentDictId);
                String subcode = code.substring(0, i * 2 + 4);
                System.out.println("subcode" + i + "=" + subcode);
                TawSystemDictType dict = dictMgr.getDictByDictType(subcode, parentDictId);
                if (dict != null && dict.getDictId() != null) {
                    String dictId = dict.getDictId();
                    System.out.println("mainComType" + i + "=" + dictId);
                    if (i == 3)
                        map.put("mainComType", dictId);
                    else
                        map.put("mainComType" + i, dictId);
                    parentDictId = dictId;
                    continue;
                }
                if (i != 1)
                    continue;
                String complaintType1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.ComplaintType1"));
                String complaintType2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.ComplaintType2"));
                map.put("mainComType1", complaintType1);
                map.put("mainComType2", complaintType2);
                break;
            }

        } catch (Exception err) {
            System.out.println("没有找到映射的投诉分类");
            err.printStackTrace();
        }
        return map;
    }


    public String getSheetAttachCode() {
        return "agentmaintenance";
    }

    public void finishNew(BaseMain main, Map WpsMap) {
        super.finishNew(main, WpsMap);

    }
}
