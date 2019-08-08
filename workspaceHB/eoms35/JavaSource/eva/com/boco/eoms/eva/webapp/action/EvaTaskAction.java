package com.boco.eoms.eva.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.eva.mgr.IEvaKpiInstanceMgr;
import com.boco.eoms.eva.mgr.IEvaKpiMgr;
import com.boco.eoms.eva.mgr.IEvaReportInfoMgr;
import com.boco.eoms.eva.mgr.IEvaTaskDetailMgr;
import com.boco.eoms.eva.mgr.IEvaTaskMgr;
import com.boco.eoms.eva.mgr.IEvaTemplateMgr;
import com.boco.eoms.eva.mgr.IEvaTreeMgr;
import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.model.EvaKpiInstance;
import com.boco.eoms.eva.model.EvaReportInfo;
import com.boco.eoms.eva.model.EvaTask;
import com.boco.eoms.eva.model.EvaTaskDetail;
import com.boco.eoms.eva.model.EvaTree;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.eva.webapp.form.EvaKpiInstanceForm;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;

import java.net.URLDecoder;

public final class EvaTaskAction extends BaseAction {

    /**
     * 未指定方法
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return taskView(mapping, form, request, response);
    }

    /**
     * 打开考核任务页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward taskView(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) getBean("IevaTaskMgr");
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
        // // 已激活的任务列表
        // List taskList = taskMgr.listTaskOfOrg(sessionform.getDeptid(),
        // EvaConstants.TEMPLATE_ACTIVATED);
        // request.setAttribute("taskList", taskList);

        // 改为2级联动，第一级为模板分类，第二级为模板，初始化的时候先显示第1级-王思轩
        List list = treeMgr.listChildNodes("", "TEMPLATETYPE", "0");
        List templateType = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            EvaTree et = (EvaTree) list.get(i);
            templateType.add(et);
        }

        // 合作伙伴信息,先通过部门NAME找到nodeid，然后找到对应的厂商
        List factoryList = new ArrayList();
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        String deptName = sessionform.getDeptname();
        String sql = "from AreaDeptTree tree where 1=1 and tree.nodeName = '"
                + deptName + "' and tree.nodeType in ('province','area')";
        List areaDeptList = areaDeptTreeMgr.getInfoByCondition(sql);
        if (!areaDeptList.isEmpty()) {
            AreaDeptTree adt = (AreaDeptTree) areaDeptList.get(0);
            String hql = "select distinct(tree.nodeName) from AreaDeptTree tree where 1=1 and tree.parentNodeId like '"
                    + adt.getNodeId() + "%' and tree.nodeType ='factory'";
            factoryList = areaDeptTreeMgr.getInfoByCondition(hql);
        }
//		for (int i = 0; i < nodeNameList.size(); i++) {
//			String nodeName = nodeNameList.get(i).toString();
//			String sql1 = "from AreaDeptTree tree where 1=1 and tree.nodeName = '"
//					+ nodeName + "' and tree.nodeType ='factory'";
//			List nodeIdList = areaDeptTreeMgr.getInfoByCondition(sql1);
//			AreaDeptTree adt1 = (AreaDeptTree) nodeIdList.get(0);
//			factoryList.add(adt1.getNodeId());
//		}
        request.setAttribute("factoryList", factoryList);

        request.setAttribute("templateType", templateType);
        return mapping.findForward("taskView");
    }

    /**
     * 页面二级联动，已知模板分类，返回对应模板任务list
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward changeTemplateType(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        String templateTypeId = request.getParameter("templateTypeId");
        // 已激活的任务列表
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) getBean("IevaTaskMgr");
        IEvaTemplateMgr tempMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");

        List list = taskMgr.listTaskOfOrg(sessionform.getDeptid(),
                EvaConstants.TEMPLATE_ACTIVATED, templateTypeId);
        StringBuffer d_list = new StringBuffer();
        d_list.append("," + "");
        d_list.append("," + "--请选择模板--");
        for (int i = 0; i < list.size(); i++) {
            EvaTask et = (EvaTask) list.get(i);
            d_list.append("," + et.getId());
            d_list.append("," + tempMgr.id2Name(et.getTemplateId()));
        }
        String taskBuffer = d_list.toString();
        taskBuffer = taskBuffer.substring(1, taskBuffer.length());
        response.setContentType("text/html; charset=GBK");
        response.getWriter().print(taskBuffer);
        response.getWriter().flush();
        return null;
    }

    /**
     * 打开考核任务页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward taskDetailList(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IEvaTaskDetailMgr taskDetailMgr = (IEvaTaskDetailMgr) getBean("IevaTaskDetailMgr");
        IEvaKpiInstanceMgr evaKpiInstanceMgr = (IEvaKpiInstanceMgr) getBean("IevaKpiInstanceMgr");
        IEvaKpiMgr evaKpiMgr = (IEvaKpiMgr) getBean("IevaKpiMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        List tableList = new ArrayList();
        String taskId = StaticMethod
                .null2String(request.getParameter("taskId"));
        String partner = StaticMethod.null2String(request
                .getParameter("partner"));
        String partnerId = StaticMethod.null2String(request
                .getParameter("partnerId"));
        if (partner == null || "".equals(partner)) {
            partner = areaDeptTreeMgr.id2Name(partnerId);
        }

        String year = StaticMethod.null2String(request.getParameter("year"));
        String month = StaticMethod.null2String(request.getParameter("month"));

        String queryType = StaticMethod.null2String(request
                .getParameter("queryType"));
        if (queryType == null || "".equals(queryType))
            queryType = "run";
        String timeType = "月度";// 后续开发
        String time = year + month;
        if (time == null || "".equals(time)) {
            time = StaticMethod.null2String(request.getParameter("time"));
        }
        String isPublishButton = "";
        int maxLevel = 0;
        int maxListNo = taskDetailMgr.getMaxListNoOfTask(taskId);
        for (int i = 1; i <= maxListNo; i++) {
            List rowList = taskDetailMgr.listDetailOfTaskByListNo(taskId,
                    String.valueOf(i));

            List rowListShow = new ArrayList();
            for (int j = 0; j < rowList.size(); j++) {
                EvaTaskDetail etd = (EvaTaskDetail) rowList.get(j);
                EvaKpiInstanceForm ekif = new EvaKpiInstanceForm();
                ekif.setId(etd.getId());
                ekif.setKpiId(etd.getKpiId());
                ekif.setLeaf(etd.getLeaf());
                ekif.setListNo(etd.getListNo());
                ekif.setNodeId(etd.getNodeId());
                ekif.setParentNodeId(etd.getParentNodeId());
                ekif.setRowspan(etd.getRowspan());
                ekif.setTaskId(etd.getTaskId());
                ekif.setWeight(etd.getWeight());
                ekif.setColspan(etd.getColspan());
                if (EvaConstants.NODE_LEAF.equals(etd.getLeaf())) {
                    EvaKpiInstance ekis = evaKpiInstanceMgr
                            .getKpiInstanceByTimeAndPartner(etd.getId(),
                                    timeType, time, partner);
                    ekif.setRealScore(ekis.getRealScore());
                    ekif.setReduceReason(ekis.getReduceReason());
                    ekif.setRemark(ekis.getRemark());
                    if (ekis.getIsPublish() != null
                            && !"".equals(ekis.getIsPublish())) {
                        ekif.setIsPublish(ekis.getIsPublish());
                    } else {
                        ekif.setIsPublish(EvaConstants.TASK_NOT_PUBLISHED);
                    }
                    if ("1".equals(ekis.getIsPublish())) {
                        isPublishButton = "display:none;";
                    }
                }

                // 算法加入
                EvaKpi ek = evaKpiMgr.getKpi(ekif.getKpiId());
                String algorithm = ek.getAlgorithm();
                if (algorithm == null || "".equals(algorithm)) {
                    algorithm = "无";
                }
                ekif.setAlgorithm(algorithm);
                rowListShow.add(ekif);
            }
            tableList.add(rowListShow);
            if (rowList.size() > maxLevel) {
                maxLevel = rowList.size();
            }
        }

        // 找到当前taskId对应的模板name
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) getBean("IevaTaskMgr");
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        EvaTask et = taskMgr.getTaskById(taskId);
        String taskName = templateMgr.id2Name(et.getTemplateId());
        request.setAttribute("taskName", taskName); // 任务名称

        request.setAttribute("tableList", tableList); // 详细列表数据
        request.setAttribute("maxLevel", String.valueOf(maxLevel));
        request.setAttribute("taskId", taskId); // 任务ID
        request.setAttribute("partner", partner); // 合作伙伴信息
        request.setAttribute("timeType", timeType); // 时间类型
        request.setAttribute("time", time); // 时间
        request.setAttribute("isPublishButton", isPublishButton); // 是否发布(控制按钮)
        request.setAttribute("queryType", queryType); // 查询类型，为返回制定
        return mapping.findForward("taskDetailList");
    }

    // 保存任务详细信息 王思轩 09-1-21
    public ActionForward saveTaskDetail(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (xsave(mapping, form, request, response,
                EvaConstants.TASK_NOT_PUBLISHED))
            return mapping.findForward("success");
        else
            return mapping.findForward("fail");
    }

    // 上报任务详细信息 王思轩 09-1-21
    public ActionForward commitTaskDetail(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        if (xsave(mapping, form, request, response, EvaConstants.TASK_PUBLISHED))
            return mapping.findForward("success");
        else
            return mapping.findForward("fail");
    }

    /**
     * 保存任务细节
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public boolean xsave(ActionMapping mapping, ActionForm form,
                         HttpServletRequest request, HttpServletResponse response,
                         String saveType) throws Exception {
        try {
            String taskId = StaticMethod.null2String(request
                    .getParameter("taskId"));
            String partner = StaticMethod.null2String(request
                    .getParameter("partner"));
            String timeType = StaticMethod.null2String(request
                    .getParameter("timeType"));
            String time = StaticMethod
                    .null2String(request.getParameter("time"));

            IEvaTaskDetailMgr taskDetailMgr = (IEvaTaskDetailMgr) getBean("IevaTaskDetailMgr");
            IEvaKpiInstanceMgr evaKpiInstanceMgr = (IEvaKpiInstanceMgr) getBean("IevaKpiInstanceMgr");
            IEvaReportInfoMgr evaReportInfoMgr = (IEvaReportInfoMgr) getBean("IevaReportInfoMgr");
            AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");

            int maxLevel = 0;
            int maxListNo = taskDetailMgr.getMaxListNoOfTask(taskId);

            // 指标实际总分
            float totalScore = 0;
            // 指标权重总分
            float totalWeight = 0;
            for (int i = 1; i <= maxListNo; i++) {
                List rowList = taskDetailMgr.listDetailOfTaskByListNo(taskId,
                        String.valueOf(i));
                for (int j = 0; j < rowList.size(); j++) {
                    EvaTaskDetail etd = (EvaTaskDetail) rowList.get(j);
                    if (EvaConstants.NODE_LEAF.equals(etd.getLeaf())) {
                        String nodeId = etd.getNodeId();
                        String realScore = StaticMethod.null2String(request
                                .getParameter(nodeId + "_sc"));
                        String reduceReason = StaticMethod.null2String(request
                                .getParameter(nodeId + "_rs"));
                        String remark = StaticMethod.null2String(request
                                .getParameter(nodeId + "_rm"));
                        if (realScore != null && !"".equals(realScore)) {
                            realScore = Float.valueOf(realScore).toString();
                            // 加入总分
                            totalScore = totalScore
                                    + Float.valueOf(realScore).floatValue();
                        }
                        totalWeight = totalWeight
                                + etd.getWeight().floatValue();

                        EvaKpiInstance ekis = evaKpiInstanceMgr
                                .getKpiInstanceByTimeAndPartner(etd.getId(),
                                        timeType, time, partner);
                        if (ekis.getId() == null || "".equals(ekis.getId())) {
                            DateFormat format = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            ekis.setCreateTime(format.format(new Date()));
                        }
                        ekis.setIsPublish(saveType);
                        ekis.setPartnerId(partner);
                        ekis.setPartnerName(partner);
                        ekis.setRealScore(realScore);
                        ekis.setReduceReason(reduceReason);
                        ekis.setRemark(remark);
                        ekis.setTaskDetailId(etd.getId());
                        ekis.setTime(time);
                        ekis.setTimeType(timeType);

                        evaKpiInstanceMgr.saveKpiInstance(ekis);
                    }
                }
                if (rowList.size() > maxLevel) {
                    maxLevel = rowList.size();
                }
            }

            // 如果发布，将总分信息存入ReportInfo表中
            if (saveType.equals(EvaConstants.TASK_PUBLISHED)) {
                EvaReportInfo eri = new EvaReportInfo();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                eri.setCreateTime(format.format(new Date()));
                eri.setIsPublish(saveType);
                eri.setPartnerId(partner);
                eri.setPartnerName(partner);
                eri.setTaskId(taskId);
                eri.setTime(time);
                eri.setTimeType(timeType);
                eri.setTotalScore(Float.valueOf(totalScore).toString());
                eri.setTotalWeight(Float.valueOf(totalWeight).toString());
                evaReportInfoMgr.saveEvaReportInfo(eri);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // 考核结果查询初始 王思轩 09-1-22
    public ActionForward queryInit(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) getBean("IevaTaskMgr");
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
        // // 已激活的任务列表
        // List taskList = taskMgr.listTaskOfOrg(sessionform.getDeptid(),
        // EvaConstants.TEMPLATE_ACTIVATED);
        // request.setAttribute("taskList", taskList);

        // 改为2级联动，第一级为模板分类，第二级为模板，初始化的时候先显示第1级-王思轩
        List list = treeMgr.listChildNodes("", "TEMPLATETYPE", "0");
        List templateType = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            EvaTree et = (EvaTree) list.get(i);
            templateType.add(et);
        }

        // 合作伙伴信息,先通过部门NAME找到nodeid，然后找到对应的厂商
        List factoryList = new ArrayList();
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        String deptName = sessionform.getDeptname();
        String sql = "from AreaDeptTree tree where 1=1 and tree.nodeName = '"
                + deptName + "' and tree.nodeType in ('province','area')";
        List areaDeptList = areaDeptTreeMgr.getInfoByCondition(sql);
        if (!areaDeptList.isEmpty()) {
            AreaDeptTree adt = (AreaDeptTree) areaDeptList.get(0);
            String hql = "select distinct(tree.nodeName) from AreaDeptTree tree where 1=1 and tree.parentNodeId like '"
                    + adt.getNodeId() + "%' and tree.nodeType ='factory'";
            factoryList = areaDeptTreeMgr.getInfoByCondition(hql);
        }
        request.setAttribute("factoryList", factoryList);

        request.setAttribute("templateType", templateType);
        return mapping.findForward("query");
    }

    // 考核结果查询 王思轩 09-1-22
    public ActionForward xquery(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String deptId = sessionform.getDeptid();
        IEvaTaskDetailMgr taskDetailMgr = (IEvaTaskDetailMgr) getBean("IevaTaskDetailMgr");
        IEvaKpiInstanceMgr evaKpiInstanceMgr = (IEvaKpiInstanceMgr) getBean("IevaKpiInstanceMgr");
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) getBean("IevaTaskMgr");
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");

        String searchType = StaticMethod.null2String(request
                .getParameter("searchType"));
        String taskId = StaticMethod
                .null2String(request.getParameter("taskId"));
        String partner = StaticMethod.null2String(request
                .getParameter("partner"));
        String year1 = StaticMethod.null2String(request.getParameter("year1"));
        String month1 = StaticMethod
                .null2String(request.getParameter("month1"));
        String year2 = StaticMethod.null2String(request.getParameter("year2"));
        String month2 = StaticMethod
                .null2String(request.getParameter("month2"));
        String timeType = "月度";// 后续开发
        String startTime = year1 + month1;
        String endTime = year2 + month2;
        if (searchType == null || "".equals(searchType)) {
            searchType = EvaConstants.TASK_PUBLISHED;
        }

        List rowList = taskDetailMgr.listDetailOfTaskByListNo(taskId, String
                .valueOf(1));
        List taskDetailList = new ArrayList();
        List taskList = new ArrayList();
        for (int i = 0; i < rowList.size(); i++) {
            EvaTaskDetail etd = (EvaTaskDetail) rowList.get(i);
            if (EvaConstants.NODE_LEAF.equals(etd.getLeaf())) {
                taskList = evaKpiInstanceMgr
                        .getKpiInstanceListByTimeAndPartner(etd.getId(),
                                partner, timeType, startTime, endTime,
                                searchType);
                for (int j = 0; j < taskList.size(); j++) {
                    EvaKpiInstance edi = (EvaKpiInstance) taskList.get(j);
                    taskDetailList.add(edi);
                }
            }
        }

        List taskDetailListShow = new ArrayList();
        // 找到当前taskId对应的模板name
        for (int i = 0; i < taskDetailList.size(); i++) {
            EvaKpiInstance eki = (EvaKpiInstance) taskDetailList.get(i);
            EvaKpiInstanceForm ekif = (EvaKpiInstanceForm) convert(eki);
            EvaTaskDetail etd = taskDetailMgr.getTaskDetailById(eki
                    .getTaskDetailId());
            EvaTask et = taskMgr.getTaskById(etd.getTaskId());
            String taskName = templateMgr.id2Name(et.getTemplateId());
            ekif.setTaskId(etd.getTaskId());
            ekif.setTaskName(taskName);

            //找到对应的地域ID,前台传参用
            String sql1 = "from AreaDeptTree tree where 1=1 and tree.nodeName = '"
                    + ekif.getPartnerId() + "' and tree.nodeType ='factory'";
            List nodeIdList = areaDeptTreeMgr.getInfoByCondition(sql1);
            AreaDeptTree adt1 = (AreaDeptTree) nodeIdList.get(0);
            ekif.setPartnerId(adt1.getNodeId());
            //对当前任务进行判断，如果orgId与deptId一致则要，否者不要
            if (deptId.equals(et.getOrgId())) {
                taskDetailListShow.add(ekif);
            }
        }

        if (partner == null || "".equals(partner)) {
            partner = "所有合作伙伴";
        }
        if (startTime == null || "".equals(startTime)) {
            startTime = "最早时间";
        }
        if (endTime == null || "".equals(endTime)) {
            endTime = "最晚时间";
        }

        // 找到当前taskId对应的模板name
        String taskName = "所有任务";
        if (taskId != null && !"".equals(taskId)) {
            EvaTask et = taskMgr.getTaskById(taskId);
            taskName = templateMgr.id2Name(et.getTemplateId());
        }
        request.setAttribute("taskName", taskName); // 任务名称

        request.setAttribute("taskDetailList", taskDetailListShow); // 详细列表数据
        request.setAttribute("partner", partner); // 合作伙伴信息
        request.setAttribute("timeType", timeType); // 时间类型
        request.setAttribute("startTime", startTime); // 时间1
        request.setAttribute("endTime", endTime); // 时间2
        return mapping.findForward("queryList");
    }
}
