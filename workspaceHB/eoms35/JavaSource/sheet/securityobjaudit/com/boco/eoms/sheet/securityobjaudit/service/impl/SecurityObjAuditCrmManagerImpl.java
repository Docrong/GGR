package com.boco.eoms.sheet.securityobjaudit.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
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
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmLink;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmTask;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmLinkManager;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmTaskManager;

public class SecurityObjAuditCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage {

    public String getLinkBeanId() {
        return "iSecurityObjAuditLinkManager";
    }

    public String getMainBeanId() {
        return "iSecurityObjAuditMainManager";
    }

    public String getOperateName() {
        return "newWorkSheet";
    }

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "SecurityObjAudit";
    }

    public String getSendUser(Map map) {
        String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/securityobjaudit-crm.xml").getProperty("base.InterfaceUser"));
        return userId;
    }

    public String getTaskBeanId() {
        // TODO Auto-generated method stub
        return "iSecurityObjAuditTaskManager";
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

    public Map initMap(Map map, List attach, String type) throws Exception {
        try {
            map = this.loadDefaultMap(map, "config/securityobjaudit-crm.xml", type);
            map.put("corrKey", UUIDHexGenerator.getInstance().getID());


            if (type.equals(InterfaceConstants.WORKSHEET_NEW) || type.equals(InterfaceConstants.WORKSHEET_RENEW)) {

                String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/securityobjaudit-crm.xml").getProperty("base.SendRoleId"));
                map.put("sendRoleId", sendRoleId);

                String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/securityobjaudit-crm.xml").getProperty("base.AcceptGroupId"));
                String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));

                WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                String subRoleId = "";
                TawSystemSubRole subRole = sm.getMatchRoles("SecurityObjAuditProcess", toDeptId, bigRole, map);
                if (subRole == null || subRole.getId() == null || subRole.getId().length() == 0) {
                    System.out.println("未找到匹配角色，使用默认角色");
                    subRoleId = XmlManage.getFile("/config/securityobjaudit-crm.xml").getProperty("base.AcceptRoleId");
                } else {
                    subRoleId = subRole.getId();
                }
                map = sm.setAcceptRole(subRoleId, map);

                String attachId = this.getAttach(attach);
                System.out.println("#####################resourceconfirm#######attachId=" + attachId);
                if (attachId != null && attachId.length() > 0) {
                    map.put("sheetAccessories", attachId);
                }

                String sheetKey = UUIDHexGenerator.getInstance().getID();
                map.put("id", sheetKey);

            }


            return map;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public String getSheetAttachCode() {
        return "securityobjaudit";
    }

    public String untreadWorkSheet(HashMap interfaceMap, List attach)
            throws Exception {
        IMainService iMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(getMainBeanId());
        Map map = initMap(interfaceMap, attach, "untreadWorkSheet");
        map = setBaseMap(map);
        String sheetNo = StaticMethod.nullObject2String(map.get("serialNo"));
        BaseMain main = null;
        main = iMainService.getMainBySheetId(sheetNo);
        if (main == null) {
            throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
        } else {
            map.put("id", main.getId());
            map.put("operateRoleId", main.getSendRoleId());
            return dealSheet(main, map, attach);
        }
    }

    public String checkinWorkSheet(HashMap interfaceMap, List attach)
            throws Exception {
        Map map = initMap(interfaceMap, attach, "checkinWorkSheet");
        if (map.get("phaseId") == null)
            throw new Exception("phaseId为空");
        IMainService iMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(getMainBeanId());
        map = setBaseMap(map);
        String sheetNo = StaticMethod.nullObject2String(map.get("serialNo"));
        System.out.println("serialNo=" + sheetNo);
        BaseMain main = iMainService.getMainBySheetId(sheetNo);
        if (null != main) {
            map.put("id", main.getId());
            map.put("operateUserId", main.getSendUserId());
            map.put("endUserId", main.getSendUserId());
            map.put("endDeptId", main.getSendDeptId());
            map.put("endRoleId", main.getSendRoleId());
            map.put("status", Constants.SHEET_HOLD);
            return dealSheet(main, map, attach);
        } else {
            throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
        }
    }

}
