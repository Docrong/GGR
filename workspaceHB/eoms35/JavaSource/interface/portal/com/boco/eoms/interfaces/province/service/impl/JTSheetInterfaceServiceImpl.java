package com.boco.eoms.interfaces.province.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.interfaces.province.service.IJTSheetInterfaceService;
import com.boco.eoms.interfaces.province.util.JTSheetInterfaceUtils;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
//import com.boco.eoms.sheet.centralcommonfault.service.bo.CentralCommonfaultBO;
import com.boco.eoms.sheet.commontask.service.ICommonTaskLinkManager;
import com.boco.eoms.sheet.commontask.service.ICommonTaskMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class JTSheetInterfaceServiceImpl implements IJTSheetInterfaceService {

    //	@Override
    public String getSheetIdByGroupSheetId(String groupSheetId, String sheetType) {
        String eomsSheetId = "";

        try {
            String querySheetIdSql = "select province_sheet_id provinceSheetId from  group_provice_sheet_id_mapping " +
                    " where group_sheet_id='" + groupSheetId + "' and sheet_type='" + sheetType + "'";
            IDownLoadSheetAccessoriesService service =
                    (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            List sheetList = service.getSheetAccessoriesList(querySheetIdSql);
            if (sheetList != null && sheetList.size() > 0) {
                Map provinceSheetMap = (Map) sheetList.get(0);
                eomsSheetId = StaticMethod.nullObject2String(provinceSheetMap.get("provinceSheetId"));
            }
        } catch (Exception e) {
            return "";
        }
        return eomsSheetId;
    }

    //	@Override
    public String newCentralCommonfaultSheet(Map opDetailMap) throws Exception {
        opDetailMap.put("createType", "1");//半自动草稿派单
//	    return CentralCommonfaultBO.performAdd(opDetailMap);
        return null;
    }

    //	@Override
    public String newCommonTaskSheet(Map opDetailMap, List attachList) throws Exception {
        ICommonTaskMainManager iCommonTaskMainManager = (ICommonTaskMainManager) ApplicationContextHolder.getInstance().getBean("iCommonTaskMainManager");
        ICommonTaskLinkManager iCommonTaskLinkManager = (ICommonTaskLinkManager) ApplicationContextHolder.getInstance().getBean("iCommonTaskLinkManager");
        ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        String pageColumnName = "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String";
        String userId = StaticMethod.nullObject2String(opDetailMap.get("sender"));//建单用户
        String passwd = StaticMethod.nullObject2String(opDetailMap.get("callerPwd"));//建单用户密码
        //将接口传递过来的参数映射到工单对应字段
        InterfaceUtilProperties properties = new InterfaceUtilProperties();
        String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/province/config/JISheetService-util.xml");
        opDetailMap = properties.getMapFromXml(opDetailMap, filePath, "newCommonTaskSheet");
        //构建工单所需的其他字段
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        opDetailMap.put("sendTime", new String[]{dateFormat.format(date)});
        opDetailMap.put("correlationKey", new String[]{UUIDHexGenerator.getInstance().getID()});
        //构建用户信息
        opDetailMap.put("sendUserId", userId);
        opDetailMap.put("operateUserId", userId);
        TawSystemUser user = userMgr.getUserByuserid(userId);
        if (user != null) {
            opDetailMap.put("sendDeptId", user.getDeptid());
            opDetailMap.put("sendContact", user.getMobile());
            opDetailMap.put("operateDeptId", user.getDeptid());
            opDetailMap.put("operaterContact", user.getMobile());
        }
        //网络分类的映射
        JTSheetInterfaceUtils.netTypeConvert(opDetailMap);
        //获取接口附件
        String attachId = getCommonTaskAttach(attachList);
        if (attachId != null && attachId.length() > 0)
            opDetailMap.put("sheetAccessories", attachId);

        //格式化流程数据
        HashMap columnMap = new HashMap();
        HashMap sheetMap = new HashMap();
        sheetMap.put("main", iCommonTaskMainManager.getMainObject().getClass().newInstance());
        sheetMap.put("link", iCommonTaskLinkManager.getLinkObject().getClass().newInstance());
        sheetMap.put("operate", pageColumnName);
        columnMap.put("selfSheet", sheetMap);

        Map serializableMap = SheetUtils.serializableParemeterMap(opDetailMap);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        HashMap tempWpsMap;
        for (; it.hasNext(); WpsMap.putAll(tempWpsMap)) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
        }

        //新增任务工单实例
        String processTemplateName = "CommonTaskMainFlowProcess";
        String operateName = "newWorksheet";
        HashMap sessionMap = new HashMap();
        sessionMap.put("userId", userId);
        sessionMap.put("password", "111");
        IBusinessFlowService businessFlowService =
                (IBusinessFlowService) ApplicationContextHolder.getInstance().getBean("businessFlowService");
        businessFlowService.initProcess(processTemplateName, operateName, WpsMap, sessionMap);
        HashMap main = (HashMap) WpsMap.get("main");
        String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
        String sheetKey = StaticMethod.nullObject2String(main.get("id"));
        return sheetId + ";" + sheetKey;
    }

    /**
     * 获取接口传递过来的附件信息
     *
     * @param attachList
     * @return
     */
    private String getCommonTaskAttach(List attachList) {
        WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
        return wm.getAttach(attachList, "commontask");
    }

    //	@Override
    public void saveSheetIdByGroupSheetId(String groupSheetId, String provinceSheetId, String sheetType) {
        try {
            String id = UUIDHexGenerator.getInstance().getID();// uuid唯一随机数生成id
            String insertSql = "insert into group_provice_sheet_id_mapping(id,group_sheet_id,province_sheet_id,sheet_type,deleted)values" +
                    " ('" + id + "','" + groupSheetId + "','" + provinceSheetId + "','" + sheetType + "','0')";
            IDownLoadSheetAccessoriesService service =
                    (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            service.updateTasks(insertSql);
        } catch (Exception e) {

        }
    }
}
