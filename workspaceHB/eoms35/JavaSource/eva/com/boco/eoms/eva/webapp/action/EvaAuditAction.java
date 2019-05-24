package com.boco.eoms.eva.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.eva.webapp.form.EvaAuditForm;
import com.boco.eoms.eva.mgr.IEvaAuditInfoMgr;
import com.boco.eoms.eva.mgr.IEvaKpiInstanceMgr;
import com.boco.eoms.eva.mgr.IEvaOrgMgr;
import com.boco.eoms.eva.model.EvaOrg;
import com.boco.eoms.eva.util.DateTimeUtil;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.eva.util.EvaStaticMethod;
import com.boco.eoms.eva.model.EvaAuditInfo;
import com.boco.eoms.eva.mgr.IEvaTemplateMgr;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.eva.model.EvaTemplate;

public final class EvaAuditAction extends BaseAction {

	/**
	 * 初始化审核页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward auditPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("templateId"));
		request.setAttribute("templateId", templateId);

		// 审核页面加入模板和考核结果
		IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");

		IEvaOrgMgr orgMgr = (IEvaOrgMgr) getBean("IevaOrgMgr");
		EvaOrg orgOld = orgMgr.getEvaOrg(templateId);
		// 获取下发的任务
		EvaOrg orgTask = orgMgr.getEvaOrg(orgOld.getTemplateId());
		// 模板
		EvaTemplate template = templateMgr.getTemplate(orgTask.getTemplateId());
		orgTask.setOrgName(EvaStaticMethod.getOrgName(orgTask.getOrgId(),
				orgTask.getOrgType()));
		request.setAttribute("org", orgTask);
		request.setAttribute("template", template);

		// 任务当前处于激活状态的记录
		EvaOrg activeOrg = new EvaOrg();
		activeOrg = orgMgr.getLatestTaskByTaskId(orgTask.getId());
		request.setAttribute("activeOrg", activeOrg);

		// 考核实例信息
		IEvaKpiInstanceMgr instanceMgr = (IEvaKpiInstanceMgr) getBean("IevaKpiInstanceMgr");
		List kpiList = instanceMgr.listKpiOfTemplateWithScore(orgTask.getId(),
				DateTimeUtil.getCurrentDateTime(EvaConstants.DATE_FORMAT));
		request.setAttribute("kpiList", kpiList);

		return mapping.findForward("auditPage");
	}

	/**
	 * 审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		SimpleDateFormat simpleDFmt = new SimpleDateFormat("yyyy-MM-dd");
		EvaAuditForm evaAuditForm = (EvaAuditForm) form;
		IEvaAuditInfoMgr auditInfoMgr = (IEvaAuditInfoMgr) getBean("IevaAuditInfoMgr");
		IEvaOrgMgr orgMgr = (IEvaOrgMgr) getBean("IevaOrgMgr");
		IEvaTemplateMgr templateMgr = (IEvaTemplateMgr) getBean("IevaTemplateMgr");
		// 更改原来任务的状态为已执行
		EvaOrg orgOld = orgMgr.getEvaOrg(evaAuditForm.getId());
		orgOld.setStatus(EvaConstants.TASK_STSTUS_INACTIVE);
		orgMgr.saveEvaOrg(orgOld);
		// 获取处理下发的组织
		EvaOrg orgUp = orgMgr.getEvaOrg(orgOld.getTemplateId());
		// 获取模板信息
		EvaTemplate template = templateMgr.getTemplate(orgUp.getTemplateId());
		EvaOrg orgNew = new EvaOrg();
		if (evaAuditForm.getAuditFlag().equals("1")) { // 驳回
			// 增加驳回任务
			orgNew.setOrgId(orgUp.getOrgId());
			orgNew.setOrgType(orgUp.getOrgType());
			orgNew.setTemplateId(orgUp.getId());
			orgNew.setActionType(EvaConstants.TEMPLATE_AUDIT_REJECT);
			orgNew.setStatus(EvaConstants.TASK_STSTUS_ACTIVITIES);
			orgNew.setOperateTime(new Date());
			orgNew.setEvaStartTime(template.getStartTime());
			orgNew.setEvaEndTime(template.getEndTime());
			orgMgr.saveEvaOrg(orgNew);
		} else { // 审核通过
			// 增加上报任务
			orgNew.setOrgId(template.getCreator());
			orgNew.setOrgType(StaticVariable.PRIV_ASSIGNTYPE_USER);
			orgNew.setTemplateId(orgUp.getId());
			orgNew.setStatus(EvaConstants.TASK_STSTUS_ACTIVITIES);
			orgNew.setActionType(EvaConstants.TEMPLATE_REPORTED);
			orgNew.setOperateTime(new Date());
			orgNew.setEvaStartTime(template.getStartTime());
			orgNew.setEvaEndTime(template.getEndTime());
			orgMgr.saveEvaOrg(orgNew);
		}
		// 增加审批信息
		EvaAuditInfo evaAuditInfo = new EvaAuditInfo();
		evaAuditInfo.setAuditInfo(evaAuditForm.getAuditInfo());
		evaAuditInfo.setTemplateId(orgUp.getId());
		evaAuditInfo.setAuditTime(new Date());
		evaAuditInfo.setAuditUser(sessionform.getUserid());
		evaAuditInfo.setStatus(orgNew.getStatus());
		auditInfoMgr.saveEvaAuditInfo(evaAuditInfo);
		return mapping.findForward("success");
	}

	/**
	 * 审核意见列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward auditInfoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String templateId = StaticMethod.nullObject2String(request
				.getParameter("nodeId"));
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				EvaConstants.AUDITINFO_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IEvaAuditInfoMgr auditInfoMgr = (IEvaAuditInfoMgr) getBean("IevaAuditInfoMgr");
		String hSql = " where templateId='" + templateId + "'";
		Map map = (Map) auditInfoMgr.getAuditInfoByTempateId(pageIndex,
				pageSize, hSql);
		List list = (List) map.get("result");
		request.setAttribute(EvaConstants.AUDITINFO_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("auditInfoList");
	}
}
