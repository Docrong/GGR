
package com.boco.eoms.sheet.bureaudataSave.webapp.action;

import java.util.ArrayList;
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
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataCityzoneManager;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;
import com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import com.boco.eoms.util.InterfaceUtil;

public class BureaudataSaveMethod extends BaseSheet {
    public String getPageColumnName() {

        return super.getPageColumnName() + ",toMorePhaseId@java.lang.String,supplier1Performer@java.lang.String,supplier1PerformerLeader@java.lang.String,"
                + "supplier1PerformerType@java.lang.String,supplier2Performer@java.lang.String,supplier2PerformerLeader@java.lang.String,supplier2PerformerType@java.lang.String,"
                + "supplier1CorrKey@java.lang.String,supplier2CorrKey@java.lang.String";

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));


        HashMap sheetMap = new HashMap();
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("mainId"));
        BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
        if (!sheetKey.equals("")) {
            sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

        }
        if (!sheetKey.equals("")) {
            main = this.getMainService().getSingleMainPO(sheetKey);
        }
        sheetMap.put("main", main);
        sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
        sheetMap.put("operate", getPageColumnName());
        hashMap.put("selfSheet", sheetMap);

        return hashMap;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#dealFlowEngineMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getParameterMap() {
        // TODO Auto-generated method stub
        return this.getParameterMap();
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

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"), "");
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"), "");
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        if (taskName.equals("RejectTask") || taskName.equals("Assessor") || taskName.equals("HoldTask")) {
            super.setParentTaskOperateWhenRejct(request);
            ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataBasicDAOManager");
            List hlrlist = hlrmgr.getRelatedBelongsegment(sheetKey, "新增");
            request.setAttribute("BureaudataBasicdeal", hlrlist);
        }
//		 if(operateType.equals("18")){
//				//需要回调外部流程
//				BaseMain main=this.getMainService().getSingleMainPO(sheetKey);
//				String parentSheetName=main.getParentSheetName();
//				String parentSheetKey=main.getParentSheetId();											
//				IMainService parentMainService = (IMainService) ApplicationContextHolder
//					.getInstance().getBean(parentSheetName);													
//				BaseMain parentMain=parentMainService.getSingleMainPO(parentSheetKey);
//				request.setAttribute("parentMain", parentMain);
//				request.setAttribute("parentProcessName", parentSheetName);
//				}

    }

    public void performAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
                response);// 获取main link operate对象
        Map map = request.getParameterMap();
        System.out.println("=====request Map parentPhaseName:" + StaticMethod.nullObject2String(request.getParameter("parentPhaseName"), ""));
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        while (it.hasNext()) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
                    tempColumnMap);
            WpsMap.putAll(tempWpsMap);
        }

        String processTemplateName = StaticMethod.nullObject2String(request
                .getParameter("processTemplateName"));
        String operateName = StaticMethod.nullObject2String(request
                .getParameter("operateName"));

        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);


        Map tmpmain = (Map) WpsMap.get("main");
        String tmpparentPhaseName = (String) tmpmain.get("parentPhaseName");
        System.out.println("==========basesheet:parentPhaseName:"
                + tmpparentPhaseName);
        /** 获取登陆信息，add by qinmin* */
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        // Subject subject = (Subject) request.getSession().getAttribute(
        // "wpsSubject");
        // sessionMap.put("wpsSubject", subject);
        /** add by qinmin* */
        businessFlowService.initProcess(processTemplateName, operateName,
                getFlowEngineMap(), sessionMap);


        String[] hlrSignalidArr = (String[]) map.get("hlrSignalid");
        String[] beginsegmentArr = (String[]) map.get("beginsegment");
        ;
        String[] endsegmentArr = (String[]) map.get("endsegment");
        ;
        List list = new ArrayList();
        for (int i = 0; i < beginsegmentArr.length; i++) {
            TawBureaudataSegmenthlrVO segVO = new TawBureaudataSegmenthlrVO();
            segVO.setBeginSegment(Integer.parseInt(beginsegmentArr[i]));
            segVO.setEndSegment(Integer.parseInt(endsegmentArr[i]));
            segVO.setHlrSignalId(hlrSignalidArr[i]);
            segVO.setBureaudataSheet(tmpmain.get("id").toString());
            list.add(segVO);
        }


        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");

        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        if (taskName.equals("RejectTask")) {
            hlrmgr.updateBase(list, 2);
        } else {
            hlrmgr.updateBase(list, 1);
        }

    }

    public void performDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        /**
         * 由于改为调用wps提供的humantaskManagerAPI，故taskid的取值由原来的aiid改为tkid，modify by
         * qinmin*
         */
        String taskId = StaticMethod.nullObject2String(request
                .getParameter("TKID"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        System.out.println("basesheet operateType is -----------------------" + operateType);
        // 获取页面输入信息
        HashMap columnMap = this.getInterfaceObjMap(mapping, form, request,
                response);
        Map map = request.getParameterMap();
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = serializableMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        while (it.hasNext()) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            if (taskId.equals("")) {
                Object obj = tempMap.get("aiid");
                if (obj.getClass().isArray()) {
                    Object[] obja = (Object[]) obj;
                    obj = obja[0];
                }
                taskId = StaticMethod.nullObject2String(obj);
            }
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
                    tempColumnMap);
            WpsMap.putAll(tempWpsMap);
        }
        setFlowEngineMap(WpsMap);
        dealFlowEngineMap(mapping, form, request, response);

        /** 获取登陆信息，add by qinmin* */
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());
        /** add by qinmin* */

        // 是否调用外部流程，若调用的话，将调用外部流程的个数放置到operate中
        ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder
                .getInstance().getBean("ITawSheetRelationManager");
        String mainId = StaticMethod.nullObject2String(request
                .getParameter("mainId"));
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("taskName"));
        //List list = mgr.getRunningSheetByParentId(mainId);
        String tempUserId = "";
        if (sessionform != null) {
            tempUserId = sessionform.getUserid();
        }
        //查询工单互调表

        List relationAllList = mgr.getRunningSheetByPidAndPhaseIdAndUserId(mainId, taskName, tempUserId);
        HashMap operate = (HashMap) this.getFlowEngineMap().get("operate");

        if (relationAllList != null && relationAllList.size() > 0) {
            operate.put("reInvokeCount", new Integer(relationAllList.size()));
            System.out.println("=======hasNextTaskFlag=======is==invokeProcess");
            operate.put("hasNextTaskFlag", "invokeProcess");
        }

        this.getFlowEngineMap().put("operate", operate);

        // 是否需要回调外部流程
        String ifReplyInvoke = StaticMethod.nullObject2String(request
                .getParameter("ifReplyInvoke"));
        if (ifReplyInvoke.equals("true")) {
            String invokePiid = StaticMethod.nullObject2String(request
                    .getParameter("invokePiid"));
            String invokeOperateName = StaticMethod.nullObject2String(request
                    .getParameter("invokeOperateName"));
            String invokeProcessBeanId = StaticMethod.nullObject2String(request
                    .getParameter("invokeProcessBeanId"));
            String parentSheetKey = StaticMethod.nullObject2String(request
                    .getParameter("invokeSheetId"));
            String toPhaseId = StaticMethod.nullObject2String(request
                    .getParameter("invokePhaseId"));
            IBaseSheet parentSheet = (IBaseSheet) ApplicationContextHolder
                    .getInstance().getBean(invokeProcessBeanId);

            if (parentSheetKey != null) {

                HashMap sheetMap = new HashMap();
                HashMap mainMap = new HashMap();
                HashMap linkMap = new HashMap();
                HashMap operateMap = new HashMap();

                BaseMain parentMain = parentSheet.getMainService().getSingleMainPO(parentSheetKey);
                BaseLink parentLink = (BaseLink) parentSheet.getLinkService()
                        .getLinkObject().getClass().newInstance();
                mainMap = SheetBeanUtils.bean2MapWithNull(parentMain);
                linkMap = SheetBeanUtils.bean2MapWithNull(parentLink);
                operateMap.put("phaseId", toPhaseId);
                operateMap.put("hasNextTaskFlag", "invokeProcess");

                sheetMap.put("main", mainMap);
                sheetMap.put("link", linkMap);
                sheetMap.put("operate", operateMap);

                parentSheet.getBusinessFlowService().reInvokeProcess(invokePiid,
                        invokeOperateName, sheetMap, sessionMap);
            }
        }
        /**判断是否是同组处理模式，若是的话，则先取消之前的claim操作。add by 秦敏**/
        String teamFlag = StaticMethod.nullObject2String(request.getParameter("teamFlag"));
        if (teamFlag.equals("true")) {
            businessFlowService.cancelClaimTask(taskId, getFlowEngineMap(),
                    sessionMap);
        }
        // finish task
        businessFlowService.completeHumanTask(taskId, getFlowEngineMap(),
                sessionMap);


        if ("RejectTask".equals(taskName) || "DraftTask".equals(taskName)) {
            String[] hlrSignalidArr = (String[]) map.get("hlrSignalid");
            String[] beginsegmentArr = (String[]) map.get("beginsegment");
            String[] endsegmentArr = (String[]) map.get("endsegment");
            List list = new ArrayList();
            for (int i = 0; i < beginsegmentArr.length; i++) {
                if (beginsegmentArr[i] != null && endsegmentArr[i] != null
                        && hlrSignalidArr[i] != null) {
                    TawBureaudataSegmenthlrVO segVO = new TawBureaudataSegmenthlrVO();
                    segVO
                            .setBeginSegment(StaticMethod
                                    .null2int(beginsegmentArr[i]));
                    segVO.setEndSegment(StaticMethod.null2int(endsegmentArr[i]));
                    segVO.setHlrSignalId(hlrSignalidArr[i]);
                    segVO.setBureaudataSheet(mainId);
                    list.add(segVO);
                }

            }

            ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataBasicDAOManager");
            if (taskName.equals("")) {
                taskName = StaticMethod.nullObject2String(request
                        .getParameter("taskName"));
            }
            if (list.size() > 0) {
                hlrmgr.updateBase(list, 1);
            }
        }


    }

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "BureaudataSaveProcesses";
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return "bureaudataSave";
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        return null;
    }

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String phaseId = StaticMethod.nullObject2String(request
                .getParameter("phaseId"));
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));

        HashMap sheetMap = this.getFlowEngineMap();
        Map operate = (HashMap) sheetMap.get("operate");
        HashMap sessionMap = new HashMap();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        sessionMap.put("userId", sessionform.getUserid());
        sessionMap.put("password", sessionform.getPassword());

        Map mainMap = (HashMap) sheetMap.get("main");
        Map linkMap = (HashMap) sheetMap.get("link");
        String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");
        String[] toMorePhaseIds = (StaticMethod.nullObject2String(operate.get("toMorePhaseId"))).split(",");
        if (mainMap.get("title") != null && "".equals(mainMap.get("title"))) {
            ITawBureaudataCityzoneManager tbr = (ITawBureaudataCityzoneManager) ApplicationContextHolder.getInstance().getBean("iTawBureaudataCityzoneManager");
            String title = tbr.getTawSystemApplyID(sessionform.getUserid(), "新增");
            mainMap.put("title", title);
            sheetMap.put("main", mainMap);
        }

        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }

        if (dealperformers.length > 1) {

            String corrKey = "";
            String tmp = "";
            for (int i = 0; i < dealperformers.length; i++) {
                tmp = UUIDHexGenerator.getInstance().getID();
                if (dealperformers.length == 1) {
                    corrKey = tmp;
                } else {
                    if (corrKey.equals("")) {
                        corrKey = tmp;
                    } else {
                        corrKey = corrKey + "," + tmp;
                    }

                }
            }
            System.out.println("corrKey" + corrKey);
            operate.put("extendKey1", corrKey);
            sheetMap.put("operate", operate);

        }

        System.out.println("main=" + mainMap);
        //==============================
        if (operateType.equals("-12")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            System.out.println("========================" + sheetKey);
            ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataBasicDAOManager");
            hlrmgr.updatenew(sheetKey, "新增");
        }
        //==============================
        this.setFlowEngineMap(sheetMap);
    }

    public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputNewSheetPage(mapping, form, request, response);
        TawSystemSessionForm session = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        //获取所有地市
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        List list = mgr.getBureaudatedept();
        request.setAttribute("BureaudataHlr", list);
        //获取该用户可访问号段信息
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List hlrlist = hlrmgr.getBelongSegment(session.getUserid());
        request.setAttribute("BureaudataBasic", hlrlist);
        request.setAttribute("belongflag", "0");


    }


    public void showDetailPage(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDetailPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List hlrlist = hlrmgr.getRelatedBelongsegment(sheetKey, "新增");
        request.setAttribute("BureaudataBasic", hlrlist);
    }

    public void showSheetDealList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showSheetDealList(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List hlrlist = hlrmgr.getRelatedBelongsegment(sheetKey, "新增");
        request.setAttribute("BureaudataBasicdeal", hlrlist);
    }

    public void showDealPage(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDealPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List hlrlist = hlrmgr.getRelatedBelongsegment(sheetKey, "新增");
        request.setAttribute("BureaudataBasicdeal", hlrlist);

    }


    public void showDraftPage(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showDraftPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        //获取所有地市
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        List list = mgr.getBureaudatedept();
        request.setAttribute("BureaudataHlr", list);
        //获取该用户可访问号段信息
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List hlrlist = hlrmgr.getRelatedBelongsegment(sheetKey, "新增");
        request.setAttribute("BureaudataBasic", hlrlist);
        request.setAttribute("belongflag", "1");
    }


    public void showMainDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        super.showMainDetailPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"));
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List hlrlist = hlrmgr.getRelatedBelongsegment(sheetKey, "新增");
        request.setAttribute("BureaudataBasic", hlrlist);

    }
}
