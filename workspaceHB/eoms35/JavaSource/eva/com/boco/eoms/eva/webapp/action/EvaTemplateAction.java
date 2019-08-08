package com.boco.eoms.eva.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.eva.mgr.IEvaKpiMgr;
import com.boco.eoms.eva.mgr.IEvaOrgMgr;
import com.boco.eoms.eva.mgr.IEvaTaskMgr;
import com.boco.eoms.eva.mgr.IEvaTemplateMgr;
import com.boco.eoms.eva.mgr.IEvaTreeMgr;
import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.model.EvaOrg;
import com.boco.eoms.eva.model.EvaTask;
import com.boco.eoms.eva.model.EvaTemplate;
import com.boco.eoms.eva.model.EvaTree;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.eva.util.EvaStaticMethod;
import com.boco.eoms.eva.webapp.form.EvaTemplateForm;
import com.boco.eoms.log4j.Logger;

public final class EvaTemplateAction extends BaseAction {

    /**
     * 未指定方法
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return templateTree(mapping, form, request, response);
    }

    /**
     * 考核模板树
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward templateTree(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("templateTree");
    }

    /**
     * 新建考核模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newTemplate(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String parentNodeId = StaticMethod.null2String(request
                .getParameter("nodeId"));
        if (parentNodeId == null || "".equals(parentNodeId)) {
            parentNodeId = StaticMethod.null2String(request
                    .getParameter("parentNodeId"));
        }
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        // 所属地市
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem = new JSONObject();
        jitem.put(UIConstants.JSON_ID, sessionform.getDeptid());
        jitem.put(UIConstants.JSON_NAME, sessionform.getDeptname());
        jsonRoot.put(jitem);

        // 复制需要的信息 add:wangsixuan 2009-2-5
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
        List ttList = treeMgr.listNextLevelNode("1",
                EvaConstants.NODETYPE_TEMPLATETYPE);
        request.setAttribute("ttList", ttList);
        String templateTypeId = StaticMethod.null2String(request
                .getParameter("templateTypeId"));
        List tList = new ArrayList();
        if (templateTypeId != null && !"".equals(templateTypeId)) {
            tList = treeMgr.listNextLevelNode(templateTypeId,
                    EvaConstants.NODETYPE_TEMPLATE);
        }
        request.setAttribute("tList", tList);
        request.setAttribute("templateTypeId", templateTypeId);

        request.setAttribute("orgIds", jsonRoot.toString());
        request.setAttribute("parentNodeId", parentNodeId);
        return mapping.findForward("newTemplate");
    }

    /**
     * 修改考核模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editTemplate(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EvaTemplate template = new EvaTemplate();
        String nodeId = StaticMethod
                .null2String(request.getParameter("nodeId"));
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        if ("".equals(nodeId)) { // 没有取到nodeId说明不是以右键菜单操作为入口
            nodeId = StaticMethod.nullObject2String(request
                    .getAttribute("nodeId"));
            EvaTemplateForm templateForm = (EvaTemplateForm) form;
            updateFormBean(mapping, request, templateForm);
            template = (EvaTemplate) convert(templateForm);
        } else {
            IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
            EvaTree tplNode = treeMgr.getTreeNodeByNodeId(nodeId);
            template = templateMgr.getTemplate(tplNode.getObjectId());
            EvaTemplateForm templateForm = (EvaTemplateForm) convert(template);
            updateFormBean(mapping, request, templateForm);
        }

        // 所属地市，目前仅为部门，可扩展为角色和用户
        IEvaTaskMgr taskMgr = (IEvaTaskMgr) getBean("IevaTaskMgr");
        List taskList = taskMgr.listTaskOfTpl(template.getId());
        JSONArray jsonRoot = new JSONArray();
        for (Iterator taskIt = taskList.iterator(); taskIt.hasNext(); ) {
            EvaTask task = (EvaTask) taskIt.next();
            JSONObject jitem = new JSONObject();
            jitem.put(UIConstants.JSON_ID, task.getOrgId());
            jitem.put(UIConstants.JSON_NAME, EvaStaticMethod.orgId2Name(task
                    .getOrgId(), task.getOrgType()));
            jsonRoot.put(jitem);
        }
        request.setAttribute("orgIds", jsonRoot.toString());
        request.setAttribute("parentNodeId", nodeId);

        //页面控制需要的模板对应权值
//		HashMap evaTWHashMap = new HashMap();
//		evaTWHashMap.put(nodeId, templateMgr.getTotalWtOfTemplate(nodeId).floatValue()+"");
//		request.getSession().setAttribute("evaTWHashMap", evaTWHashMap);

        request.getSession().setAttribute("evaTW", templateMgr.getTotalWtOfTemplate(nodeId).floatValue() + "");
        return mapping.findForward("editTemplate");
    }

    /**
     * 保存考核模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveTemplate(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        EvaTemplate template = new EvaTemplate();
        EvaTemplateForm templateForm = (EvaTemplateForm) form;
        String parentNodeId = StaticMethod.null2String(request
                .getParameter("parentNodeId"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (null == templateForm.getId() || "".equals(templateForm.getId())) {// 新增模板
            template = (EvaTemplate) convert(templateForm);
            template.setCreator(sessionform.getUserid());
            template.setCreateTime(StaticMethod.getCurrentDateTime());
            template.setCreatorOrgId(sessionform.getDeptid());
            // template.setStartTime(EvaStaticMethod.getStartTimeByCycle(template
            // .getCycle(), DateTimeUtil
            // .getCurrentDateTime(EvaConstants.DATE_FORMAT)));
            // template.setEndTime(EvaStaticMethod.getEndTimeByCycle(template
            // .getCycle(), DateTimeUtil
            // .getCurrentDateTime(EvaConstants.DATE_FORMAT)));
            template.setOrgType(EvaConstants.ORG_DEPT); // orgType暂时作为预留字段，全部设置为dept
            template.setActivated(EvaConstants.TEMPLATE_NOTACTIVATED);
            template.setDeleted(EvaConstants.UNDELETED);

            // 所属地市
            String orgIds = StaticMethod.null2String(request
                    .getParameter("orgIds"));
            if ("".equals(orgIds)) {
                // 页面上有选择树是否选择的判断，为防止页面控制失效，此处多判断一次
                return mapping.findForward("fail");
            }
            String[] ids = orgIds.split(",");
            templateMgr
                    .saveTemplateWithNodeAndTask(template, parentNodeId, ids);
            IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
            request.setAttribute("nodeId", treeMgr.getNodeByObjId(parentNodeId,
                    template.getId()).getNodeId());
        } else { // 修改模板
            template = templateMgr.getTemplate(templateForm.getId());
            template.setTemplateName(templateForm.getTemplateName());
            // template.setCycle(templateForm.getCycle());
            // template.setStartTime(EvaStaticMethod.getStartTimeByCycle(
            // templateForm.getCycle(), DateTimeUtil
            // .getCurrentDateTime(EvaConstants.DATE_FORMAT)));
            // template.setEndTime(EvaStaticMethod.getEndTimeByCycle(templateForm
            // .getCycle(), DateTimeUtil
            // .getCurrentDateTime(EvaConstants.DATE_FORMAT)));
            template.setRemark(templateForm.getRemark());
            // template.setOrgType(templateForm.getOrgType());
            template.setOrgType(EvaConstants.ORG_DEPT); // orgType暂时作为预留字段，全部设置为dept

            // 所属地市
            String orgIds = StaticMethod.null2String(request
                    .getParameter("orgIds"));
            if ("".equals(orgIds)) {
                // 页面上有选择树是否选择的判断，为防止页面控制失效，此处多判断一次
                return mapping.findForward("fail");
            }
            String[] ids = orgIds.split(",");
            templateMgr.updateTemplate(template, parentNodeId, ids);
            request.setAttribute("nodeId", parentNodeId);
        }
        form = (EvaTemplateForm) convert(template);

        // 复制模板指标 add:wangsixuan 2009-2-5
        // 先找出tree中以templateId开头的nodeID集合list
        // 根据每个list对应objId在kpi表中查到相关记录，修改用户名和时间并保存
        // 根据新的kpi主键修改tree的list并保存
        String templateId = StaticMethod.null2String(request
                .getParameter("templateId"));
        if (templateId != null && !"".equals(templateId)) {
            IEvaTreeMgr treeMgr2 = (IEvaTreeMgr) getBean("IevaTreeMgr");
            IEvaKpiMgr kpiMgr = (IEvaKpiMgr) getBean("IevaKpiMgr");

            // 找到新增的节点ID-nodeIdNewSave
            EvaTree etree = treeMgr2.getNodeByObjId(parentNodeId, template.getId());
            String nodeIdNewSave = etree.getNodeId();

            // 找到要复制的信息list
            List treeKpiList = treeMgr2.listChildNodes(templateId,
                    EvaConstants.NODETYPE_KPI, "");
            for (int i = 0; i < treeKpiList.size(); i++) {
                EvaTree et = (EvaTree) treeKpiList.get(i);
                EvaKpi ek = kpiMgr.getKpi(et.getObjectId());

                // 更新指标
                Logger logger = Logger.getLogger(this.getClass());

                ek.setWeight(et.getWeight());
                ek.setCreator(sessionform.getUserid());
                ek.setDeleted(EvaConstants.UNDELETED);
                ek.setCreateTime(StaticMethod.getCurrentDateTime());

                EvaTree evaTree = new EvaTree();
                // 生成新节点ID，用nodeIdNewSave替换对应节点ID的前部分
                int nodeLength = nodeIdNewSave.length();
                String newNodeId = nodeIdNewSave
                        + et.getNodeId().substring(nodeLength);
                String newParentNodeId = nodeIdNewSave
                        + et.getParentNodeId().substring(nodeLength);

                // 保存树节点
                evaTree.setNodeId(newNodeId);
                evaTree.setParentNodeId(newParentNodeId);
                evaTree.setNodeName(ek.getKpiName());
                evaTree.setNodeType(EvaConstants.NODETYPE_KPI);
                evaTree.setLeaf(EvaConstants.NODE_LEAF);
                evaTree.setObjectId(ek.getId());
                evaTree.setWeight(ek.getWeight());
                treeMgr2.saveTreeNode(evaTree);

                // 更新父节点leaf
                treeMgr2.updateParentNodeLeaf(newParentNodeId,
                        EvaConstants.NODE_NOTLEAF);
                logger.info("\n进入kpiMgr.saveKpiAndNode");
                kpiMgr.saveKpiAndNode(ek, newParentNodeId);
            }
        }

        // 保存完成后返回模板编辑页面
        return editTemplate(mapping, form, request, response);
    }

    /**
     * 删除考核模板（已激活则逻辑删除，仅从树图上删除；未激活则物理删除）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeTemplate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IEvaTemplateMgr tplMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
        String nodeId = request.getParameter("nodeId");
        EvaTree tplNode = treeMgr.getTreeNodeByNodeId(nodeId);
        EvaTemplate tpl = tplMgr.getTemplate(tplNode.getObjectId());
        if (EvaConstants.TEMPLATE_ACTIVATED.equals(tpl.getActivated())) {// 如果模板已激活则逻辑删除
            tplMgr.removeTplLogical(nodeId);
        } else {// 未激活则物理删除
            tplMgr.removeTplPhysical(nodeId);
        }
        return mapping.findForward("success");
    }

    /**
     * 查看模板详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewTemplate(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String templateId = StaticMethod.null2String(request
                .getParameter("nodeId"));
        // 如果是action内部调用
        if (null == templateId || "".equals(templateId)) {
            templateId = ((EvaTemplateForm) form).getId();
        }
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        EvaTemplate template = templateMgr.getTemplate(templateId);
        EvaTemplateForm templateForm = (EvaTemplateForm) convert(template);
        updateFormBean(mapping, request, templateForm);
        return mapping.findForward("viewTemplate");
    }

    /**
     * 模板查询页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward query(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("query");
    }

    /**
     * 模板查询
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
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        IEvaOrgMgr orgMgr = (IEvaOrgMgr) getBean("IevaOrgMgr");
        // EvaTemplateForm templateForm = (EvaTemplateForm) form;
        // String orgType = templateForm.getOrgType();
        List recieverOrgList = EvaStaticMethod.jsonOrg2Orgs(request
                .getParameter("recieverOrgId"));
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String actionType = request.getParameter("actionType");
        // 只查任务
        String conditions = " where org.actionType='"
                + EvaConstants.TEMPLATE_DISTRIBUTED + "' ";
        // 如果有选择组织
        if (recieverOrgList.iterator().hasNext()) {
            conditions += " and org.orgId in(";
            for (Iterator orgIdIter = recieverOrgList.iterator(); orgIdIter
                    .hasNext(); ) {
                EvaOrg recieverOrg = (EvaOrg) orgIdIter.next();
                conditions += "'" + recieverOrg.getOrgId() + "',";
            }
            if (conditions.endsWith(",")) {
                conditions = conditions.substring(0, conditions.length() - 1);
            }
            conditions += ")";
        }
        if (null != month && !"".equals(month)) {
            String date = year + "-" + month + "-" + "01";
            String startDateStr = EvaStaticMethod.getStartTimeByCycle(
                    EvaConstants.CYCLE_MONTH, date);
            String endDateStr = EvaStaticMethod.getEndTimeByCycle(
                    EvaConstants.CYCLE_MONTH, date);
            conditions += " and org.evaStartTime='" + startDateStr + "'";
            conditions += " and org.evaEndTime='" + endDateStr + "'";
        }
        if (null != actionType && !"".equals(actionType)) {
            conditions += " and org.id in(select neworg.templateId from EvaOrg neworg where neworg.actionType='"
                    + actionType
                    + "' and neworg.status='"
                    + EvaConstants.TASK_STSTUS_ACTIVITIES + "')";
        }

        List orgList = new ArrayList();
        List list = orgMgr.getTaskByConditions(conditions);
        for (Iterator orgIter = list.iterator(); orgIter.hasNext(); ) {
            EvaOrg org = (EvaOrg) orgIter.next();
            EvaTemplate template = templateMgr.getTemplate(org.getTemplateId());
            org.setOrgName(EvaStaticMethod.getOrgName(org.getOrgId(), org
                    .getOrgType()));
            // 从XML字典中取状态名称
            String statusName = DictMgrLocator
                    .getDictService()
                    .itemId2name(
                            "dict-eva"
                                    + com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
                                    + "templateStatus",
                            orgMgr.getLatestTaskByTaskId(org.getId())
                                    .getActionType()).toString();
            org.setTemplateName(template.getTemplateName() + "(" + statusName
                    + ")");
            orgList.add(org);
        }
        request.setAttribute("orgIter", orgList.iterator());
        return mapping.findForward("list");
    }

    /**
     * 从树图中删除模板（非物理删除，仅去掉树图中的显示）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delTemplateFromTree(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        IEvaTreeMgr treeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
        String nodeId = StaticMethod
                .null2String(request.getParameter("nodeId"));
        treeMgr.removeTreeNodeByNodeId(nodeId);
        return mapping.findForward("success");
    }

    /**
     * 激活模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward activeTemplate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String templateId = StaticMethod.null2String(request
                .getParameter("templateId"));
        String tplNodeId = StaticMethod.null2String(request
                .getParameter("nodeId"));
        IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
        EvaTemplate tpl = templateMgr.getTemplate(templateId);
        if ("".equals(templateId) || "".equals(tplNodeId)) {
            String failInfo = "获取模板信息错误！";
            request.setAttribute("failInfo", failInfo);
        } else if (EvaConstants.TEMPLATE_ACTIVATED.equals(tpl.getActivated())) {
            String failInfo = "模板已经激活！";
            request.setAttribute("failInfo", failInfo);
        }
//去掉满分100的限制，富强提出的需求，修改王思轩 20009-2-9
//		else if (EvaConstants.DEFAULT_MAX_WT.floatValue() != templateMgr
//				.getTotalWtOfTemplate(tplNodeId).floatValue()) {
//			String failInfo = "模板下属指标权重和不等于" + EvaConstants.DEFAULT_MAX_WT
//					+ "，请调整后再激活模板！";
//			request.setAttribute("failInfo", failInfo);
//		} 
        else {
            templateMgr.activeTemplate(templateId, tplNodeId);
        }
        form = (EvaTemplateForm) convert(tpl);
        // 操作完成后返回模板编辑页面
        return editTemplate(mapping, form, request, response);
    }
}
