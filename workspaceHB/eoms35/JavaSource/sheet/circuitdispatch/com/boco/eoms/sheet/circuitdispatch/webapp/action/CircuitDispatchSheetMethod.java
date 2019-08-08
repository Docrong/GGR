/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.circuitdispatch.webapp.action;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.interfaces.EOMSService.bo.IcrmLoad;
import com.boco.eoms.interfaces.EOMSService.util.IcrmUtil;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.circuitdispatch.dao.ICircuitDispatchMainDAO;
import com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

/**
 * @author TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CircuitDispatchSheetMethod extends BaseSheet {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request
                .getParameter("operateName"));
        System.out.println("operateName is -----------------------"
                + operatName);
        try {
            if (operatName.equals("forceHold")) {
                HashMap map = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("id"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("sheetKey"));
                }
                // BaseMain main =
                // this.getMainService().getMainDAO().loadSinglePO(sheetKey,
                // this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                map.put("main", main);
                map.put("link", getLinkService().getLinkObject().getClass()
                        .newInstance());
                map.put("operate", getPageColumnName());
                hashMap.put("selfSheet", map);
            } else if (taskName.equals("")) {
                // 新增工单
                String id = StaticMethod.nullObject2String(request
                        .getParameter("id"));
                BaseMain main = null;
                if (!id.equals("")) {
                    // main =
                    // this.getMainService().getMainDAO().loadSinglePO(id,
                    // this.getMainService().getMainObject());
                    main = this.getMainService().getSingleMainPO(id);
                } else {
                    main = (BaseMain) getMainService().getMainObject()
                            .getClass().newInstance();
                }
                HashMap sheetMap = new HashMap();
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject()
                        .getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("DraftTask")
                    || taskName.equals("RejectTask")) {
                // 草稿状态
                HashMap sheetMap = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("sheetKey"));
                }
                // BaseMain main =
                // this.getMainService().getMainDAO().loadSinglePO(sheetKey,
                // this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject()
                        .getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("ProjectCreateTask")
                    || taskName.equals("PermitTask")
                    || taskName.equals("PlanTask")
                    || taskName.equals("ExecuteTask")
                    || taskName.equals("ValidateTask")
                    || taskName.equals("HoldTask")) {
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("sheetKey"));
                }
                HashMap sheetMap = new HashMap();
                // BaseMain main =
                // this.getMainService().getMainDAO().loadSinglePO(sheetKey,
                // this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject()
                        .getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else {
                // 其他人工处理
                HashMap sheetMap = new HashMap();
                sheetMap.put("link", getLinkService().getLinkObject()
                        .getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String phaseId = StaticMethod.nullObject2String(request
                .getParameter("phaseId"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        HashMap sheetMap = this.getFlowEngineMap();
        Map main = (HashMap) sheetMap.get("main");
        Map link = (HashMap) sheetMap.get("link");
        Map operateMap = (HashMap) sheetMap.get("operate");


        // 阶段回复
        if (taskName.equals("reply") || taskName.equals("advice")) {
            link.put("id", "");
        }
        // 如果是驳回则将驳回次数加1

        if (taskName.equals("PermitTask")
                && (phaseId.equals("DraftTask") || phaseId
                .equals("ProjectCreateTask"))) {

            Integer mainRejectTimes = (Integer) main.get("mainRejectTimes");
            mainRejectTimes = new Integer(mainRejectTimes.intValue() + 1);
            main.put("mainRejectTimes", mainRejectTimes);

        }
        // 将实施完成时限数据保存到main的mainExecuteEndDate中
        if (taskName.equals("ProjectCreateTask")) {
            main.put("mainExecuteEndDate", link.get("linkExecuteEndDate"));
        }
        // 如果调用子流程则为每个子流程单独生成correlationKey
        if (taskName.equals("PermitTask")) {
            String[] dealperformers = ((String) operateMap.get("dealPerformer"))
                    .split(",");
            if (dealperformers.length > 1) {
                // String corrKey = "";
                // String tmp = "";
                // for(int i=0;i<dealperformers.length;i++){
                // tmp = UUIDHexGenerator.getInstance().getID()+",";
                // corrKey += tmp;
                // }
                // link.put("correlationKey", corrKey);
                if (operateType.equals("111")) {
                    String linkId = "";
                    String tmpLink = "";
                    for (int i = 0; i < dealperformers.length; i++) {
                        tmpLink = UUIDHexGenerator.getInstance().getID() + ",";
                        linkId = linkId + tmpLink;
                    }
                    operateMap.put("extendKey1", linkId);
                }
            }
        }

        if (taskName.equalsIgnoreCase("ProjectCreateTask")) { // 方案设计
            String enableService = StaticMethod.nullObject2String(XmlManage
                    .getFile("/config/circuitdispatch-util.xml").getProperty(
                            "EnableService"));
            if (enableService.equalsIgnoreCase("true")) {
                String attach = StaticMethod.nullObject2String(link
                        .get("nodeAccessories"));
                BocoLog.info(this, "design attach=" + attach);
                if (attach.length() > 0) { // 传送url
                    attach = attach.replaceAll("'", "");
                    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                            .getSession().getAttribute("sessionform");

                    String userId = sessionform.getUserid();
                    String icrmUserId = StaticMethod.nullObject2String(request
                            .getSession().getAttribute("irms_" + userId));

                    ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder
                            .getInstance().getBean(
                                    "ItawCommonsAccessoriesManager");
                    TawCommonsAccessories access = mgr.getSystemToOther(attach,
                            "http");
                    String url = access.getAccessoriesPath();

                    List chNameList = new ArrayList();
                    List enName = new ArrayList();
                    List contentList = new ArrayList();

                    chNameList.add("工单id");
                    enName.add("sheetId");
                    contentList.add(main.get("sheetId"));

                    chNameList.add("用户名");
                    enName.add("userId");
                    contentList.add(icrmUserId);

                    chNameList.add("附件地址");
                    enName.add("url");
                    contentList.add(url);

                    String opDetail = CrmLoader.createOpDetailXml(chNameList,
                            enName, contentList);
                    String result = IcrmLoad.putBusinessData("", opDetail);
                    BocoLog.info(this, "result=" + result);
                }
            }
        } else if (taskName.equalsIgnoreCase("PermitTask")) {
            if (operateType.equals("111")) { // 审核通过 获取派单地市
                String enableService = StaticMethod.nullObject2String(XmlManage
                        .getFile("/config/circuitdispatch-util.xml")
                        .getProperty("EnableService"));
                if (enableService.equalsIgnoreCase("true")) {
                    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                            .getSession().getAttribute("sessionform");

                    String userId = sessionform.getUserid();
                    String icrmUserId = StaticMethod.nullObject2String(request
                            .getSession().getAttribute("irms_" + userId));

                    List chNameList = new ArrayList();
                    List enName = new ArrayList();
                    List contentList = new ArrayList();

                    chNameList.add("工单id");
                    enName.add("sheetId");
                    contentList.add((String) main.get("sheetId"));

                    chNameList.add("用户名");
                    enName.add("userId");
                    contentList.add(icrmUserId);

                    chNameList.add("审核结果");
                    enName.add("checkResult");
                    contentList.add("0");

                    String opDetail = CrmLoader.createOpDetailXml(chNameList,
                            enName, contentList);
                    String checkResult = IcrmLoad.setCheck("", opDetail);
                    if (!"0".equalsIgnoreCase(checkResult))
                        throw new Exception("资管系统保存审核结果失败");

                    String cityStr = IcrmLoad.getDeptIds("", opDetail);

                    WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                    String dealperformer = "";

                    String[] cityIds = cityStr.split(",");
                    for (int i = 0; i < cityIds.length; i++) {
                        TawSystemSubRole subRole = sm.getMatchRoles(
                                "CircuitDispatchMainFlowProcess", cityIds[i],
                                "270", main);
                        if (subRole != null && subRole.getId() != null)
                            dealperformer += subRole.getId() + ",";

                    }
                    if (dealperformer.length() > 0)
                        dealperformer = dealperformer.substring(0,
                                dealperformer.length() - 1);
                    else
                        throw new Exception("没找到对应的角色");
                    BocoLog.info(this, "interface dealperformer="
                            + dealperformer);

                    operateMap.put("dealPerformer", dealperformer);
                }
            }
        } else if (taskName.equalsIgnoreCase("ExecuteTask")) { // 施工
            if (!operateType.equals("61")) {
                String enableService = StaticMethod.nullObject2String(XmlManage
                        .getFile("/config/circuitdispatch-util.xml")
                        .getProperty("EnableService"));
                if (enableService.equalsIgnoreCase("true")) {
                    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                            .getSession().getAttribute("sessionform");

                    String userId = sessionform.getUserid();
                    String icrmUserId = StaticMethod.nullObject2String(request
                            .getSession().getAttribute("irms_" + userId));

                    List chNameList = new ArrayList();
                    List enName = new ArrayList();
                    List contentList = new ArrayList();

                    chNameList.add("工单id");
                    enName.add("sheetId");
                    contentList.add((String) main.get("sheetId"));

                    chNameList.add("用户名");
                    enName.add("userId");
                    contentList.add(icrmUserId);

                    String opDetail = CrmLoader.createOpDetailXml(chNameList,
                            enName, contentList);
                    String result = IcrmLoad.submitReplySheet("", opDetail);
                    if (!"1".equals(result) && !"2".equals(result)) {
                        throw new Exception("资源侧不允许提交：有未处理的回单明细 " + result);
                    }
                }
            }

        } else if (taskName.equalsIgnoreCase("ValidateTask")) {
            if (!operateType.equals("61")) {
                String enableService = StaticMethod.nullObject2String(XmlManage
                        .getFile("/config/circuitdispatch-util.xml")
                        .getProperty("EnableService"));
                if (enableService.equalsIgnoreCase("true")) {
                    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                            .getSession().getAttribute("sessionform");

                    String userId = sessionform.getUserid();
                    String icrmUserId = StaticMethod.nullObject2String(request
                            .getSession().getAttribute("irms_" + userId));

                    List chNameList = new ArrayList();
                    List enName = new ArrayList();
                    List contentList = new ArrayList();

                    chNameList.add("工单id");
                    enName.add("sheetId");
                    contentList.add(main.get("sheetId"));

                    chNameList.add("用户名");
                    enName.add("userId");
                    contentList.add(icrmUserId);

                    String opDetail = CrmLoader.createOpDetailXml(chNameList,
                            enName, contentList);
                    String attachUrl = IcrmLoad.getExcelData("", opDetail);
                    if (attachUrl.length() > 0
                            && attachUrl.indexOf("tp://") > 0) {
                        attachUrl = URLDecoder.decode(attachUrl, "UTF-8");
                        String name = XmlManage.getFile(
                                "/config/circuitdispatch-util.xml")
                                .getProperty("AttachCnName");
                        WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                        String attach = sm.getAttachFromOtherSystem(name,
                                attachUrl, "circuitdispatch");
                        BocoLog.info(this, "attach=" + attach);
                        attach = sm.getAttachStr(attach);

                        link.put("nodeAccessories", attach);
                    } else
                        throw new Exception("获取资源测数据文件失败");
                }
            }
        }

        sheetMap.put("main", main);
        sheetMap.put("link", link);
        sheetMap.put("operate", operateMap);
        this.setFlowEngineMap(sheetMap);

    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        // ////////驳回////////////
        // 取上条TASK
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        // 碰到需要驳回操作的节点时，去取上一个Task记录
        if (taskName.equals("ProjectCreateTask")
                || taskName.equals("PermitTask") || taskName.equals("PlanTask")
                || taskName.equals("ExecuteTask")
                || taskName.equals("ValidateTask")
                || taskName.equals("HoldTask")) {
            // 下面这个方法写在base类中，会取出4个值，传递到inputdealpage页面中
            super.setParentTaskOperateWhenRejct(request);
        }
        // ////////驳回////////////

        //taskName.equals("ExecuteTask") && operateType.equals("113")
        //找到ProjectCreateTask环节的电路调度数据制作员，传给页面
        if (taskName.equals("ExecuteTask")) {
            setParentTaskOperateWhenRejct("ProjectCreateTask", request);
        }

        // 调用其他流程
        taskName = StaticMethod.nullObject2String(request
                .getParameter("taskName"));
        if (taskName.equals("ValidateTask")) {
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"));
            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(
                    sheetKey, taskName);
            if (relationAllList != null) {
                for (int i = 0; i < relationAllList.size(); i++) {
                    TawSheetRelation relation = (TawSheetRelation) relationAllList
                            .get(i);
                    int state = relation.getInvokeState();
                    if (state == Constants.INVOKE_STATE_RUNNING) {
                        request.setAttribute("ifInvoke", "no");
                        break;
                    }
                    request.setAttribute("ifInvoke", "yes");
                }
            } else {
                request.setAttribute("ifInvoke", "no");
            }
        }
        // 需要回调外部流程
        if (taskName.equals("HoldTask")) {
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"), "");
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            String parentSheetName = main.getParentSheetName();
            String parentSheetKey = main.getParentSheetId();
            if (parentSheetName != null && !parentSheetName.equals("")) {
                IMainService parentMainService = (IMainService) ApplicationContextHolder
                        .getInstance().getBean(parentSheetName);
                BaseMain parentMain = parentMainService
                        .getSingleMainPO(parentSheetKey);

                String parentPhaseName = main.getParentPhaseName();

                if (parentPhaseName.indexOf("@") != -1) {
                    request.setAttribute("parentPiid", parentPhaseName
                            .substring(parentPhaseName.indexOf("@") + 1));
                    System.out.println("回调：parentProcessId："
                            + parentPhaseName.substring(parentPhaseName
                            .indexOf("@") + 1));
                } else {
                    request.setAttribute("parentPiid", parentMain.getPiid());
                }

                request.setAttribute("parentMain", parentMain);
                request.setAttribute("parentProcessName", parentSheetName);
                System.out.println("circuitdiaptch 执行了回调 ===========");
            }
        }
    }

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }


    /**
     * 新增提交
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void newPerformAdd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.newPerformAdd(mapping, form, request, response);
        // 如果是报备则不进行工单的流转，只保存main数据
        String mainChangeSource = StaticMethod.nullObject2String(request
                .getParameter("mainChangeSource"));
        // 变更来源如果是故障处理或投诉处理，则为报备
        if (mainChangeSource.equals("101090101")
                || mainChangeSource.equals("101090102")) {
            BaseMain mainbean = null;
            mainbean = (BaseMain) this.getMainService().getMainObject()
                    .getClass().newInstance();
            Map map = this.getFlowEngineMap();
            Map main = (Map) map.get("main");
            Iterator names = main.keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                Object value = main.get(name);
                if (value != null) {
                    Class clazz = value.getClass();
                    String className = clazz.getName();
                    if (className.equalsIgnoreCase("java.util.Date")) {
                        DateFormat df = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                        String datestr = df.format((Date) value);
                        main.put(name, datestr);
                    }
                }
            }
            SheetBeanUtils.populateMap2Bean(mainbean, main);
            this.getMainService().addMain(mainbean);
        }
    }

    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();

        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("sheetAccessories");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");

        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
    }

    public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputNewSheetPage(mapping, form, request, response);
        try {
            // 删除今天以前main表中所有的空记录（只有id数据的记录）
            ICircuitDispatchMainDAO mainDAO = (ICircuitDispatchMainDAO) this
                    .getMainService().getMainDAO();

            mainDAO.DeleteEarlyEmptyMain(this.getMainService().getMainObject());
            // 新增一条记录，该记录中只有id和sendtime字段的数据
            BaseMain main = (BaseMain) request.getAttribute("sheetMain");
            main.setId(UUIDHexGenerator.getInstance().getID());
            main.setSendTime(new Date());
            mainDAO.saveObject(main);
            request.setAttribute("sheetMain", main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 工单强制归档、作废页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void showForceHoldPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showForceHoldPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"));
        // 需要回调外部流程
        BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
        String parentSheetName = main.getParentSheetName();
        String parentSheetKey = main.getParentSheetId();
        if (parentSheetName != null && !parentSheetName.equals("")) {
            IMainService parentMainService = (IMainService) ApplicationContextHolder
                    .getInstance().getBean(parentSheetName);
            BaseMain parentMain = parentMainService
                    .getSingleMainPO(parentSheetKey);
            String parentPhaseName = main.getParentPhaseName();

            if (parentPhaseName.indexOf("@") != -1) {
                request.setAttribute("parentPiid", parentPhaseName
                        .substring(parentPhaseName.indexOf("@") + 1));
                System.out.println("回调：parentProcessId："
                        + parentPhaseName.substring(parentPhaseName
                        .indexOf("@") + 1));
            } else {
                request.setAttribute("parentPiid", parentMain.getPiid());
            }
            request.setAttribute("parentMain", parentMain);
            request.setAttribute("parentProcessName", parentSheetName);
        }
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        // map = this.loadDefaultMap(map, "config/complaint-crm.xml", type);
        if (type.equals(InterfaceConstants.WORKSHEET_NEWINSTANCE)) {
            String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
            if (sheetId == null || sheetId.equals(""))
                throw new Exception("sheetId为空");

            BaseMain main = this.getMainService().getMainBySheetId(sheetId);
            if (main == null)
                throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");

            String operateType = StaticMethod.nullObject2String(map
                    .get("operateType"));
            if (operateType.equals("4"))
                map.put("phaseId", "ProjectCreateTask");
            else {
                map.put("phaseId", "PermitTask");

                String cityId = StaticMethod.nullObject2String(map
                        .get("cityId"));
                // cityId = "北京";
                if (cityId.equals(""))
                    throw new Exception("cityId为空");
                IcrmUtil util = new IcrmUtil();
                Map mainMap = SheetBeanUtils.bean2Map(main);
                String dealPerformer = util.getDealPerformerByCityId(cityId,
                        "270", mainMap);
                WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
                sm.setAcceptRole(dealPerformer, map);
            }

            map.put("sheetKey", main.getId());
            map.put("ifSaveLink", "false");
        }
        return map;
    }

    public void setParentTaskOperateWhenRejct(String taskName, HttpServletRequest request)
            throws Exception {
        String prelinkid = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        BaseLink preLink = getLinkService().getSingleLinkPO(prelinkid);
        String sheetKey = preLink.getMainId();
        String fOperateroleid = "";
        String ftaskOwner = "";
        String fOperateroleidType = "";
        String fPreTaskName = "";
        if (preLink != null) {
            String parentTaskId = StaticMethod.nullObject2String(preLink.getAiid());
            if (!parentTaskId.equals("")) {
                ITask task = getTaskService().getSinglePO(parentTaskId);
                fOperateroleid = task.getOperateRoleId();
                ftaskOwner = task.getTaskOwner();
                fOperateroleidType = task.getOperateType();
                fPreTaskName = task.getTaskName();
            } else {
                BaseMain main = getMainService().getSingleMainPO(sheetKey);
                fOperateroleid = main.getSendRoleId();
                ftaskOwner = main.getSendUserId();
                fOperateroleidType = "subrole";
            }
        }
        request.setAttribute("fOperateroleid", fOperateroleid);
        request.setAttribute("ftaskOwner", ftaskOwner);
        request.setAttribute("fOperateroleidType", fOperateroleidType);
        request.setAttribute("fPreTaskName", fPreTaskName);
        if (taskName != null)
            while (!taskName.equals(StaticMethod.nullObject2String(fPreTaskName))) {
                prelinkid = StaticMethod.nullObject2String(preLink.getPreLinkId());
                if (!prelinkid.equals(""))
                    preLink = getLinkService().getSingleLinkPO(prelinkid);
                else
                    preLink = null;
                fOperateroleid = "";
                ftaskOwner = "";
                fOperateroleidType = "";
                fPreTaskName = "";
                if (preLink == null) {
                    BaseMain main = getMainService().getSingleMainPO(sheetKey);
                    fOperateroleid = main.getSendRoleId();
                    ftaskOwner = main.getSendUserId();
                    fOperateroleidType = "subrole";
                    fPreTaskName = "";
                } else {
                    String parentTaskId = StaticMethod.nullObject2String(preLink.getAiid());
                    if (!parentTaskId.equals("")) {
                        ITask task = getTaskService().getSinglePO(parentTaskId);
                        fOperateroleid = task.getOperateRoleId();
                        ftaskOwner = task.getTaskOwner();
                        fOperateroleidType = task.getOperateType();
                        fPreTaskName = task.getTaskName();
                    } else {
                        BaseMain main = getMainService().getSingleMainPO(sheetKey);
                        fOperateroleid = main.getSendRoleId();
                        ftaskOwner = main.getSendUserId();
                        fOperateroleidType = "subrole";
                        fPreTaskName = "";
                    }
                }
                request.setAttribute(fPreTaskName + "Operateroleid", fOperateroleid);
                request.setAttribute(fPreTaskName + "TaskOwner", ftaskOwner);
                request.setAttribute(fPreTaskName + "OperateroleidType", fOperateroleidType);
                if (fPreTaskName.equals(""))
                    break;
            }
    }


}
