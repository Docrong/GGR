package com.boco.eoms.sheet.limit.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import com.boco.eoms.sheet.limit.util.SheetLimitUtil;
import com.boco.eoms.sheet.limit.webapp.form.StepLimitForm;

public class SheetLimitAction extends BaseAction {

    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return this.getLevelLimitList(mapping, form, request, response);
    }

    /**
     * 删除环节时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        StepLimitForm stepLimitForm = (StepLimitForm) form;

        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        mgr.removeStepLimit(stepLimitForm.getId());

        return mapping.findForward("searchStep");
    }

    /**
     * 环节时限编辑
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

        String levelId = StaticMethod.nullObject2String(request.getParameter("levelId"), "");
        if (levelId.equals("")) {
            levelId = StaticMethod.nullObject2String(request.getAttribute("levelId"));
        }
        String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"), "");
        List stepLimits = null;
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        if (!levelId.equals("")) {
            stepLimits = mgr.getStepLimitByLevel(levelId);
        }

        HashMap stepDefaultValue = new HashMap();
        if (stepLimits != null && stepLimits.size() > 0) {
            for (int i = 0; i < stepLimits.size(); i++) {
                StepLimit tmpStepLimit = (StepLimit) stepLimits.get(i);
                stepDefaultValue.put(tmpStepLimit.getTaskName(), tmpStepLimit);
            }
        }
        HashMap taskNameMap = getPhaseidArray(flowName);
        String exceptTasks = StaticMethod.nullObject2String(SheetLimitUtil.getExceptTask(flowName));
        List phaseIdList = (List) taskNameMap.get("phaseIdList");
        HashMap phaseIdMap = (HashMap) taskNameMap.get("phaseIdMap");
        for (int i = 0; i < phaseIdList.size(); i++) {
            String tmpTaskName = (String) phaseIdList.get(i);
            if (exceptTasks.indexOf(tmpTaskName) != -1) {
                phaseIdList.remove(i);
            } else {
                if (stepDefaultValue.get(tmpTaskName) == null) {
                    StepLimit tmpStepLimit = new StepLimit();
                    tmpStepLimit.setId(UUIDHexGenerator.getInstance().getID());
                    tmpStepLimit.setLevelId(levelId);
                    tmpStepLimit.setStepId("" + i);
                    tmpStepLimit.setTaskName(tmpTaskName);
                    tmpStepLimit.setTaskCnName((String) phaseIdMap.get(tmpTaskName));
                    tmpStepLimit.setCompleteLimit("0");
                    tmpStepLimit.setAcceptLimit("0");
                    stepDefaultValue.put(tmpTaskName, tmpStepLimit);
                }
            }
        }
        request.setAttribute("phaseIdList", phaseIdList);
        request.setAttribute("phaseIdMap", phaseIdMap);
        request.setAttribute("stepDefaultValue", stepDefaultValue);
        request.setAttribute("flowName", flowName);
        return null;
    }

    /**
     * 环节时限保存
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        String id = StaticMethod.nullObject2String(request.getParameter("id"), "");

        String ids = StaticMethod.nullObject2String(request.getParameter("ids"), "");
        String stepIds = StaticMethod.nullObject2String(request.getParameter("stepIds"), "");
        String taskNames = StaticMethod.nullObject2String(request.getParameter("taskNames"), "");
        String taskCnNames = StaticMethod.nullObject2String(request.getParameter("taskCnNames"), "");
        String completeLimits = StaticMethod.nullObject2String(request.getParameter("completeLimits"), "");
        String acceptLimits = StaticMethod.nullObject2String(request.getParameter("sacceptLimits"), "");
        String[] idArr = ids.split(",");
        String[] stepIdArr = stepIds.split(",");
        String[] taskNameArr = taskNames.split(",");
        String[] taskCnNameArr = taskCnNames.split(",");
        String[] completeLimitArr = completeLimits.split(",");
        String[] acceptLimitArr = acceptLimits.split(",");

        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        for (int i = 0; i < idArr.length; i++) {
            String tmpId = idArr[i];
            StepLimit stepLimit = new StepLimit();
            stepLimit.setId(tmpId);
            stepLimit.setLevelId(id);
            stepLimit.setStepId(stepIdArr[i]);
            stepLimit.setTaskName(taskNameArr[i]);
            stepLimit.setTaskCnName(taskCnNameArr[i]);
            stepLimit.setCompleteLimit(completeLimitArr[i]);
            stepLimit.setAcceptLimit(acceptLimitArr[i]);
            mgr.saveStepLimit(stepLimit);
        }

        return null;
    }

    /**
     * 环节时限查询
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }
        String levelId = StaticMethod.nullObject2String(request
                .getParameter("levelId"), "");
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        List stepList = mgr.getStepLimitByLevel(levelId);
        request.setAttribute("stepLimitList", stepList);
        request.setAttribute("timeTotal", "" + stepList.size());
        LevelLimit level = mgr.getLevelLimit(levelId);
        String flowName = level.getFlowName();
        request.setAttribute("flowName", flowName);
//		HashMap taskNameMap = getPhaseidArray(flowName);
//		request.setAttribute("phaseIdList", taskNameMap.get("phaseIdList"));
//		request.setAttribute("phaseIdMap", taskNameMap.get("phaseIdMap"));
        request.setAttribute("levelId", levelId);
        return mapping.findForward("listStep");
    }

    /**
     * 默认执行方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
        request.setAttribute("flowName", flowName);
        return getLevelLimitList(mapping, form, request, response);
    }

    /**
     * 工单分级别时限查询
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getLevelLimitList(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }
        String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
        if (flowName.equals("")) {
            flowName = StaticMethod.nullObject2String(request.getAttribute("flowName"));
        }
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        List levelList = mgr.getLevelLimitList(flowName);
        request.setAttribute("levelLimitList", levelList);
        request.setAttribute("flowName", flowName);
        //根据配置文件显示页面
        HashMap map = SheetLimitUtil.getColumnByMapping(flowName);
        if (map.size() > 0) {
            request.setAttribute("columnMap", map);
        }
        return mapping.findForward("list");
    }

    /**
     * 工单分级别时限编辑
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editLevelLimit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }
        String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));

        if (flowName.equals("")) {
            flowName = StaticMethod.nullObject2String(request.getAttribute("flowName"));
        }
        String id = StaticMethod.nullObject2String(request.getParameter("id"), "");
        LevelLimit sheetLimit = null;
        if (!id.equals("")) {
            ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
            sheetLimit = mgr.getLevelLimit(id);
            if (flowName.equals("")) {
                flowName = sheetLimit.getFlowName();
            }
        } else {
            sheetLimit = new LevelLimit();
            sheetLimit.setFlowName(flowName);
            sheetLimit.setId(UUIDHexGenerator.getInstance().getID());
            sheetLimit.setAcceptLimit("0");
            sheetLimit.setReplyLimit("0");
        }
        request.setAttribute("levelLimit", sheetLimit);
        request.setAttribute("flowName", flowName);
        //配置默认值
        HashMap defaultValueMap = new HashMap();
        defaultValueMap.put("level1", sheetLimit.getLevel1());
        defaultValueMap.put("level2", sheetLimit.getLevel1());
        defaultValueMap.put("level3", sheetLimit.getLevel1());
        defaultValueMap.put("specialty1", sheetLimit.getSpecialty1());
        defaultValueMap.put("specialty2", sheetLimit.getSpecialty2());
        defaultValueMap.put("specialty3", sheetLimit.getSpecialty3());
        defaultValueMap.put("specialty4", sheetLimit.getSpecialty4());
        defaultValueMap.put("specialty5", sheetLimit.getSpecialty5());
        defaultValueMap.put("specialty6", sheetLimit.getSpecialty6());
        defaultValueMap.put("specialty7", sheetLimit.getSpecialty7());
        defaultValueMap.put("specialty8", sheetLimit.getSpecialty8());

        HashMap map = SheetLimitUtil.getHtmlByMapping(flowName, sheetLimit);

        if (map.size() > 0) {
            List list = (List) map.get("columnList");
            HashMap columnMap = (HashMap) map.get("columnMap");
            HashMap htmlMap = (HashMap) map.get("htmlMap");
            request.setAttribute("columnList", list);
            request.setAttribute("columnMap", columnMap);
            request.setAttribute("htmlMap", htmlMap);
            request.setAttribute("defaultValue", defaultValueMap);
        }
        String type = StaticMethod.nullObject2String(request.getParameter("type"));
        if (type.equals("add")) {
            request.setAttribute("type", "add");
        } else {
            request.setAttribute("type", "modify");
        }
        if (type.equals("addStep")) {
            request.setAttribute("addStep", "true");
        }
        request.setAttribute("levelId", sheetLimit.getId());
        this.edit(mapping, form, request, response);
        return mapping.findForward("edit");
    }

    /**
     * 工单处理时限保存
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveLevelLimit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        String type = StaticMethod.nullObject2String(request.getParameter("type"));
        LevelLimit levelLimit = SheetLimitUtil.makeLevelLimit(request);
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        if (type.equals("add")) {
            HashMap specialMap = SheetLimitUtil.makeSpecialsMap(levelLimit);
            specialMap.put("flowName", levelLimit.getFlowName());
            List levelLimitList = mgr.getLevelLimitByCondition(specialMap);
            if (levelLimitList == null || levelLimitList.size() == 0) {
                mgr.saveLevelLimit(levelLimit);
                this.save(mapping, form, request, response);
            } else {
                request.setAttribute("errorMessage", "该分类的时限已经配置！");
                return mapping.findForward("saveFailure");
            }
        } else {
            mgr.saveLevelLimit(levelLimit);
            this.save(mapping, form, request, response);
        }
        return this.getLevelLimitList(mapping, form, request, response);
    }

    /**
     * 删除时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteLevelLimit(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }
        String id = StaticMethod.nullObject2String(request.getParameter("id"), "");
        ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
        mgr.removeLevelLimit(id);

        return this.getLevelLimitList(mapping, form, request, response);
    }

    /**
     * 查询phasId
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public HashMap getPhaseidArray(String processName) throws Exception {
        //找出该流程中的节点
        ITawSystemWorkflowManager workflowmgr = (ITawSystemWorkflowManager) getBean("ITawSystemWorkflowManager");
        TawSystemWorkflow workflow = workflowmgr.getTawSystemWorkflowByName(processName);
        String mainbeanid = workflow.getMainServiceBeanId();
        IMainService mainService = (IMainService) getBean(mainbeanid);
        HashMap phaseIdListMap = new HashMap();

        ArrayList phaseIdList = new ArrayList();
        Map phaseIdMap = new HashMap();
        FlowDefineExplain flowDefineExplain = new FlowDefineExplain(processName, mainService.getRoleConfigPath());
        FlowDefine flowDefine = flowDefineExplain.getFlowDefine();

        if (flowDefine != null) {
            PhaseId phaseIds[] = flowDefine.getPhaseId();
            for (int i = 0; i < phaseIds.length; i++) {
                PhaseId phaseId = phaseIds[i];
                if (!phaseId.getId().equals("receive")) {
                    phaseIdMap.put(phaseId.getId(), phaseId.getName());
                    phaseIdList.add(phaseId.getId());
                }
            }
        }
        phaseIdListMap.put("phaseIdMap", phaseIdMap);
        phaseIdListMap.put("phaseIdList", phaseIdList);
        return phaseIdListMap;
    }

}
