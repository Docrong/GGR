package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 年度作业计划struts控制管理action</p>
 * <p>Description: 年度作业计划模板信息各页面的管理控制,用户输入数据收集或bo类数据提�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.workplan.flow.model.Step;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.mgr.ITawwpModelMgr;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.mgr.ITawwpYearPlanMgr;
import com.boco.eoms.workplan.model.TawwpModelPlan;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpNetVO;
import com.boco.eoms.workplan.vo.TawwpYearExecuteVO;
import com.boco.eoms.workplan.vo.TawwpYearPlanVO;
import com.boco.eoms.common.util.StaticMethod;

public class TawwpYearAction extends BaseAction {

	// 获取属性文�?
	static {
		ResourceBundle prop = ResourceBundle
				.getBundle("resources.application_zh_CN");
	}

	/**
	 * 根据用户请求页面请求，进行页面跳�?
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionForward myforward = null;

		// 获取请求的action属�?
		String myaction = mapping.getParameter();
		// System.out.println("调试信息：myaction=" + myaction);
		// session超时处理
		try {
			/*
			 * TawSystemSessionForm TawSystemSessionForm =
			 * (TawSystemSessionForm) request. getSession().getAttribute(
			 * "sessionform"); if (TawSystemSessionForm == null) { return
			 * mapping.findForward("timeout"); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据用户请求页面请求，进行页面跳�?
		if (isCancelled(request)) {
			return mapping.findForward("cancel"); // 无效的请求，转向错误页面
		} else if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure"); // 条件为空，转向空�?
		} else if ("NETSELECT".equalsIgnoreCase(myaction)) {
			myforward = performNetSelect(mapping, form, request, response); // 根据网元类型获取模版信息
		} else if ("TITLESELECT".equalsIgnoreCase(myaction)) {
			myforward = performTitleSelect(mapping, form, request, response); // 根据网元类型获取模版信息
		} else if ("DEPTSELECT".equalsIgnoreCase(myaction)) {
			myforward = performDeptSelect(mapping, form, request, response); // 根据网元类型获取模版信息
		} else if ("YEARLIST".equalsIgnoreCase(myaction)) {
			myforward = performYearList(mapping, form, request, response); // 显示年度作业计划的列�?
		} else if ("YEARSAVE".equalsIgnoreCase(myaction)) {
			myforward = performYearSave(mapping, form, request, response); // 显示年度作业计划的列�?
		} else if ("YEARSAVENOMODEL".equalsIgnoreCase(myaction)) {
			myforward = performYearSaveNoModel(mapping, form, request, response); // 显示年度作业计划的列�?
		} else if ("YEARVIEW".equalsIgnoreCase(myaction)) {
			myforward = performYearView(mapping, form, request, response); // 显示年度作业计划的列�?
		} else if ("YEARREMOVE".equalsIgnoreCase(myaction)) {
			myforward = performYearRemove(mapping, form, request, response); // 年度作业计划的删�?
		} else if ("YEARDEPLOY".equalsIgnoreCase(myaction)) {
			myforward = performYearDeploy(mapping, form, request, response); // 年度作业计划的发�?
		} else if ("YEARREFER".equalsIgnoreCase(myaction)) {
			myforward = performYearRefer(mapping, form, request, response); // 年度作业计划的派�?
		} else if ("ITEMLIST".equalsIgnoreCase(myaction)) {
			myforward = performItemList(mapping, form, request, response); // 年度作业计划执行内容列表
		} else if ("ITEMSAVE".equalsIgnoreCase(myaction)) {
			myforward = performItemSave(mapping, form, request, response); // 保存年度作业计划执行内容
		} else if ("ITEMADD".equalsIgnoreCase(myaction)) {
			myforward = performItemAdd(mapping, form, request, response); // 保存年度作业计划执行内容
		} else if ("ITEMEDIT".equalsIgnoreCase(myaction)) {
			myforward = performItemEdit(mapping, form, request, response); // 编辑年度作业计划执行内容
		} else if ("ITEMMODIFY".equalsIgnoreCase(myaction)) {
			myforward = performItemModify(mapping, form, request, response); // 更新年度作业计划执行内容
		} else if ("ITEMREMOVE".equalsIgnoreCase(myaction)) {
			myforward = performItemRemove(mapping, form, request, response); // 删除作作业计划模版执行内�?
		} else if ("CHECKLIST".equalsIgnoreCase(myaction)) {
			myforward = performCheckList(mapping, form, request, response); // 年度作业计划待审批列�?
		} else if ("CHECKVIEW".equalsIgnoreCase(myaction)) {
			myforward = performCheckView(mapping, form, request, response); // 年度年度作业计划审批页面
		} else if ("CHECKPASS".equalsIgnoreCase(myaction)) {
			myforward = performCheckPass(mapping, form, request, response); // 年度作业计划审批通过
		} else if ("CHECKLISTPASS".equalsIgnoreCase(myaction)) {
			myforward = performCheckListPass(mapping, form, request, response); // 年度作业计划审批通过
		} else if ("CHECKREJECT".equalsIgnoreCase(myaction)) {
			myforward = performCheckReject(mapping, form, request, response); // 年度作业计划审批驳回
		} else if ("YEAREXPORT".equalsIgnoreCase(myaction)) {
			myforward = performYearExport(mapping, form, request, response); // 年度作业计划审批驳回
		}
		/*
		 * else if ("YEARREPORT".equalsIgnoreCase(myaction)) { myforward =
		 * performYearReport(mapping, form, request, response); //年度作业计划审批驳回 }
		 */
		else {
			myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
		}
		return myforward;
	}

	/**
	 * 年度作业计划的List
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performYearList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = TawSystemSessionForm.getUserid(); // 当前用户
		String deptId = TawSystemSessionForm.getDeptid();
		String yearFlag = request.getParameter("yearFlag");
		if (yearFlag == null || yearFlag.equals("")) {
			yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
		    String monthFlag = TawwpUtil.getCurrentDateTime("MM");
		    if ("12".equals(monthFlag)) {
			int yearflag = Integer.parseInt(yearFlag);
			yearflag = yearflag + 1;
			yearFlag = String.valueOf(yearflag);
		    }
		}
		ActionMessages messages = new ActionMessages();
		// 权限验证
		try {
			// TawValidatePrivBO tawValidatePrivBO = new TawValidatePrivBO();

			TawSystemAssignBo tawValidatePrivBO = TawSystemAssignBo
					.getInstance();

		} catch (Exception e) {
			e.printStackTrace();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 异常页面
		}

		List list = null;

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr"); // 初始化作业计划模板管理BO�?
		try {
			list = tawwpYearPlanMgr.listYearPlan(deptId, yearFlag); // 获取作业计划模版集合
			request.setAttribute("yearplanlist", list);
			request.setAttribute("yearFlag", yearFlag);
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}

	}

	/**
	 * 年度作业计划保存
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */

	private ActionForward performYearSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = TawSystemSessionForm.getUserid(); // 当前用户-创建�?

		String sysType = request.getParameter("systype");
		String netTypeId = request.getParameter("nettypeid");
		String planId = request.getParameter("planid");
		String deptId = request.getParameter("deptid"); // 部门ID
		String yearFlag = request.getParameter("yearflag"); // 年度信息
		String content = request.getParameter("content"); // 作业计划内容
		String remark = request.getParameter("remark"); // 备注
		String planname = request.getParameter("planname");//作业计划名称-针对一个模板可以做多个年度计划的需求
		
		String isApp=request.getParameter("isApp");
		// modified by lijia 2005-11-25
		// String netList = request.getParameter("netlist"); //网元ID

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");

		TawwpModelPlan tawwpModelPlan = null;
		// modified by lijia 2005-11-25
		ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) getBean("tawwpNetMgr");
		String netListStr = "";
		String tempStr = "";
		String typeIndex = "";
		
		ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr");

		try {
			tawwpModelPlan = tawwpModelMgr.loadModelPlan(planId);
			if (tawwpModelPlan != null) {
				typeIndex = tawwpModelPlan.getTypeIndex();
			}

			// modified by lijia 2005-11-25
			List netList = tawwpNetMgr.listNetByTypeDept(netTypeId, deptId);
			for (int i = 0; i < netList.size(); i++) {
				tempStr = ((TawwpNetVO) (netList.get(i))).getId();
				netListStr += (tempStr + ",");
			}
			if (!"".equals(netListStr)) {
				netListStr = netListStr.substring(0, netListStr.length() - 1);
			}

			tawwpYearPlanMgr.addYearPlan(sysType, netTypeId, planname, content, userId,
					deptId, planId, netListStr,isApp, remark, yearFlag, typeIndex);

		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");

	}

	private ActionForward performYearSaveNoModel(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = TawSystemSessionForm.getUserid(); // 当前用户-创建�?

		String sysType = request.getParameter("systype");
		String netTypeId = request.getParameter("nettypeid");
		String planName = request.getParameter("planname");
		String deptId = request.getParameter("deptid"); // 部门ID
		String yearFlag = request.getParameter("yearflag"); // 年度信息
		String content = request.getParameter("content"); // 作业计划内容
		String remark = request.getParameter("remark"); // 备注
		String typeIndex = request.getParameter("typeIndex");
		String isApp=request.getParameter("isApp");

		// modified by lijia 2005-11-25
		// String netList = request.getParameter("netlist"); //网元ID

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");

		// modified by lijia 2005-11-25
		ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) getBean("tawwpNetMgr");
		String netListStr = "";
		String tempStr = "";

		try {

			// modified by lijia 2005-11-25
			List netList = tawwpNetMgr.listNetByTypeDept(netTypeId, deptId);
			for (int i = 0; i < netList.size(); i++) {
				tempStr = ((TawwpNetVO) (netList.get(i))).getId();
				netListStr += (tempStr + ",");
			}
			if (!"".equals(netListStr)) {
				netListStr = netListStr.substring(0, netListStr.length() - 1);
			}

			tawwpYearPlanMgr.addYearPlanByNoModel(sysType, netTypeId, content,
					userId, deptId, planName, netListStr,isApp, remark, yearFlag,
					typeIndex);
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");

	}

	/**
	 * 删除年度作业计划
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performYearRemove(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得参数
		String yearplanid = request.getParameter("yearplanid"); // 年度作业计划ID

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		try {
			// 删除年度作业计划
			tawwpYearPlanMgr.removeYearPlan(yearplanid);
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	/**
	 * 浏览年度作业计划
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performYearView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得参数
		String id = request.getParameter("id"); // 年度作业计划ID

		TawwpYearPlanVO tawwpYearPlanVO = null;

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		Step step = tawwpYearPlanVO.getStep();
		try {
			// 获得该年度作业计�?
			tawwpYearPlanVO = tawwpYearPlanMgr.viewYearPlanVO(id);

			// 返回一个tawwpYearPlan
			request.setAttribute("tawwpyearplan", tawwpYearPlanVO);
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	/**
	 * 年度发布
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performYearDeploy(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得参数
		String yearplanid = request.getParameter("yearplanid"); // 年度作业计划ID

		TawwpYearPlanVO tawwpYearPlanVO = null;
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		try {
			// 获得该年度作业计�?
			tawwpYearPlanVO = tawwpYearPlanMgr.viewYearPlanVO(yearplanid);
			tawwpYearPlanVO.setState("1");

			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	/**
	 * 年度作业计划的提�?
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performYearRefer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// 获得参数
		String yearplanid = request.getParameter("yearplanid"); // 年度作业计划ID
		 
		String flowSerial = request.getParameter("flowserial"); // 流程标识
		String stepSerial = request.getParameter("deptserial"); // 步骤标识
		String deptId = TawSystemSessionForm.getDeptid(); // 部门信息
		String checkUsers[]=request.getParameterValues("checkUsers");
		String checkUser =""; // 审批人信�?
		if(checkUsers!=null){
			for(int i=0;i<checkUsers.length;i++){
				if(!"".equals(StaticMethod.nullObject2String(checkUsers[i]))){
					checkUser=checkUser+checkUsers[i]+",";	
				}
				
			}
			if(checkUser.length()>1){
				checkUser=checkUser.substring(0,checkUser.length()-1);
			}
		}
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		ActionMessages messages = new ActionMessages();

		try {

			if (tawwpYearPlanMgr.addYearCheck(checkUser, deptId, yearplanid,
					flowSerial, stepSerial) == null) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"submit.title.failure"));
				saveMessages(request, messages);
				return mapping.findForward("fault"); // 转向错误页面
			}

			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	// *****************网元ACTION*********************//
	/**
	 * 显示编辑网元
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performNetSelect(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String netTypeId = request.getParameter("nettypeid"); // 网元类型
			String deptId = request.getParameter("deptid"); // 网元类型

			ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) getBean("tawwpNetMgr");
			List netlist = tawwpNetMgr.listNetByTypeDept(netTypeId, deptId);

			request.setAttribute("netlist", netlist);

			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	private ActionForward performDeptSelect(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			String netTypeId = request.getParameter("nettypeid"); // 网元类型
			String deptId = request.getParameter("deptid"); // 网元类型

			ITawwpNetMgr tawwpNetMgr = (ITawwpNetMgr) getBean("tawwpNetMgr");
			List netlist = tawwpNetMgr.listNetByTypeDept(netTypeId, deptId);

			request.setAttribute("netlist", netlist);

			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	// *****************作业计划名称ACTION*********************//
	/**
	 * 显示作业计划名称信息
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performTitleSelect(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String netTypeId = request.getParameter("nettypeid"); // 网元类型

			ITawwpModelMgr tawwpModelMgr = (ITawwpModelMgr) getBean("tawwpModelMgr");
			List modellist = tawwpModelMgr.listModeLPlanByNetType(netTypeId);

			request.setAttribute("modellist", modellist);

			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
	}

	// *****************执行内容ACTION*********************//

	/**
	 * 显示详细列表
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */

	private ActionForward performItemList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String yearplanid = request.getParameter("yearplanid");
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		// 初始化年度作业计划管理BO�?
		TawwpYearPlanVO tawwpYearPlanVO = new TawwpYearPlanVO();
		try {
			tawwpYearPlanVO = tawwpYearPlanMgr.viewYearPlanVO(yearplanid);
			request.setAttribute("tawwpYearPlanVO", tawwpYearPlanVO);

			return mapping.findForward("success");
		}

		catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}

	}

	/**
	 * 保存年度作业计划执行内容
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performItemSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = TawSystemSessionForm.getUserid(); // 当前用户

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		; // 初始化作业计划模板管理BO�?

		String yearPlanId = request.getParameter("yearplanid"); // 作业计划模板标识
		String name = request.getParameter("name"); // 执行内容名称
		
		String botype = request.getParameter("botype"); // 业务类型
		String executedeptlevel = request.getParameter("executedeptlevel"); // 执行单位级别
		String appdesc = request.getParameter("appdesc"); // 适用说明
		
		String cycle = request.getParameter("cycle"); // 周期
		String format = request.getParameter("format"); // 格式
		String formId = "";
		if (request.getParameter("formid") != null) {
			formId = request.getParameter("formid"); // 填写表格
		}
		String groupId = request.getParameter("groupid"); // 填写表格
		String command = request.getParameter("command"); // 填写命令
		String monthFlag = request.getParameter("monthflag"); // 填写年度标记
		String netTypeId = request.getParameter("nettypeid"); // 填写表格
		String remark = request.getParameter("remark"); // 填写备注
		String isHoliday = request.getParameter("isHoliday");
		String isWeekend = request.getParameter("isWeekend");
		String accessories = request.getParameter("accessories");
		String note = request.getParameter("note");
		String fileFlag = request.getParameter("fileFlag");// 是否必须上传附件
		String executeDay = StaticMethod.nullObject2String(request.getParameter("executeDay"));// 是否必须上传附件
		ActionForward actionForward = null;	

		try {
			tawwpYearPlanMgr.addYearExecute(command, userId, cycle, null,
					format, formId, groupId, monthFlag, name, botype, executedeptlevel, appdesc, netTypeId,
					remark,note, yearPlanId, isHoliday, isWeekend, fileFlag, "","","","",executeDay,accessories);

			// 返回到年度作业计划管理页�?
			actionForward = new ActionForward(
					"/tawwpyear/itemlist.do?yearplanid=" + yearPlanId);
			actionForward.setRedirect(true);

		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
		return actionForward;
	}

	/**
	 * 修改年度作业计划执行内容
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performItemEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		String yearexecuteid = request.getParameter("yearexecuteid"); // 作业计划模板执行内容标识
		String yearplanid = request.getParameter("yearplanid"); // 作业计划模板执行内容标识
		String flag = request.getParameter("flag"); // 作业计划模板执行内容标识
		TawwpYearExecuteVO tawwpYearExecuteVO = null;
		ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");

		try {
			tawwpYearExecuteVO = tawwpYearPlanMgr
					.viewYearExecute(yearexecuteid); // 显示作业计划模版执行内容
			List list = tawwpAddonsMgr.listAddonsByWorkPlan(); // 获取附加表集�
			request.setAttribute("addonslist", list);
			request.setAttribute("yearplanid", yearplanid);
			request.setAttribute("yearexecute", tawwpYearExecuteVO);
			request.setAttribute("flag", flag);

		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * 修改年度作业计划执行内容
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performItemModify(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		; // 初始化年度作业计划管理BO�?

		String yearExecuteId = request.getParameter("yearexecuteid"); // 年度作业计划执行内容标识
		String name = request.getParameter("name"); // 执行内容名称
		
		String botype = request.getParameter("botype"); // 业务类型
		String executedeptlevel = request.getParameter("executedeptlevel"); // 执行单位级别
		String appdesc = request.getParameter("appdesc"); // 适用说明
		
		String cycle = request.getParameter("cycle"); // 周期
		String format = request.getParameter("format"); // 格式
		String formId = request.getParameter("formid"); // 填写表格
		String command = request.getParameter("command"); // 填写命令
		String monthFlag = request.getParameter("monthflag"); // 年度标记
		String netTypeId = request.getParameter("nettypeid"); // 年度标记
		String remark = request.getParameter("remark"); // 执行帮助	
		String note = request.getParameter("note"); // 备注
		String isHoliday = request.getParameter("isHoliday");// 是否假日排班
		String isWeekend = request.getParameter("isWeekend");// 是否周末排班
		String executer=request.getParameter("executer");
		String executeDuty="";
		String executeRoom="";
		String executerType=request.getParameter("executeType"); 
		String fileFlag = request.getParameter("fileFlag");
		String executeDay = StaticMethod.nullObject2String(request.getParameter("executeDay"));// 是否必须上传附件
		if (executerType != null) {
			if (executerType.equals("user")) {
				executerType = "0";

			} else if (executerType.equals("dept")) {
				executerType = "1";

			} else {
				executerType = "3";

			}
		}else{
			executerType = "";
		}
		if (executer != null) {
			if (!executer.equals("") && executer.endsWith(",")) {
				executer = executer.substring(0, executer.length() - 1);
			}
		} else {
			executer = "";
		}
		try {

			tawwpYearPlanMgr.editYearExecute(cycle, command, yearExecuteId,
			formId, format, monthFlag, name, botype, executedeptlevel, appdesc, netTypeId, remark,note,
			isHoliday, isWeekend, fileFlag, executer,executeDuty,executeRoom,executerType, executeDay); // 修改年度作业计划执行内容


		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * 删除年度作业计划执行内容
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performItemRemove(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		; // 初始化年度作业计划管理BO�?

		String yearPlanId = request.getParameter("yearplanid"); // 年度作业计划标识
		String yearExecuteId = request.getParameter("yearexecuteid"); // 年度作业计划执行内容标识
		try {
			tawwpYearPlanMgr.removeYearExecute(yearExecuteId); // 删除年度作业计划执行内容
			request.setAttribute("yearplanid", yearPlanId);
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * 执行内容增加
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performItemAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String yearPlanId = request.getParameter("yearplanid"); // 年度作业计划标识
		ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
		try {
			List list = tawwpAddonsMgr.listAddonsByWorkPlan(); // 获取附加表集�?
			request.setAttribute("addonslist", list);
			request.setAttribute("yearplanid", yearPlanId);
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure");
		}

	}

	// *****************审批ACTION*********************//
	/**
	 * 显示待审批年度作业计划管�?列表)Action
	 * 
	 * @param mapping
	 *            ActionMapping 集合
	 * @param actionForm
	 *            ActionForm 数据模型
	 * @param request
	 *            HttpServletRequest 请求
	 * @param response
	 *            HttpServletResponse 应答
	 * @return ActionForward 转向对象
	 */
	private ActionForward performCheckList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

		// 权限验证
		/*
		 * try { //TawValidatePrivBO tawValidatePrivBO = new
		 * TawValidatePrivBO(); TawSystemAssignBo tawValidatePrivBO
		 * =TawSystemAssignBo.getInstance(); //ssssssssssssss if
		 * (tawValidatePrivBO.hasPermission4region(userId,"") == false) { return
		 * mapping.findForward("nopriv"); //转向无权限页�?} } catch (Exception e) {
		 * e.printStackTrace(); return mapping.findForward("failure"); //异常页面 }
		 */
		// 初始化数�?
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		List list = null;

		try {
			// 获取年度作业计划VO对象集合
			list = tawwpYearPlanMgr.listYearCheck(userId);

			// 为页面显示准备数�?
			request.setAttribute("yearplanvolist", list);

			// 转向待审批年度作业计划管�?列表)页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 显示年度作业计划审批页面Action
	 * 
	 * @param mapping
	 *            ActionMapping 集合
	 * @param actionForm
	 *            ActionForm 数据模型
	 * @param request
	 *            HttpServletRequest 请求
	 * @param response
	 *            HttpServletResponse 应答
	 * @return ActionForward 转向对象
	 */
	private ActionForward performCheckView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取年度作业计划编号
		String yearPlanId = TawwpUtil
				.getRequestValue(request, "yearplanid", "");

		// 初始化数�?
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		TawwpYearPlanVO tawwpYearPlanVO = null;

		try {

			// 浏览年度作业计划
			tawwpYearPlanVO = tawwpYearPlanMgr
					.viewYearPlanVOByCheck(yearPlanId);

			// 为页面显示准备数�?
			request.setAttribute("tawwpyearplan", tawwpYearPlanVO);

			// 转向年度作业计划审批页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 年度作业计划审批通过
	 * 
	 * @param mapping
	 *            ActionMapping 集合
	 * @param actionForm
	 *            ActionForm 数据模型
	 * @param request
	 *            HttpServletRequest 请求
	 * @param response
	 *            HttpServletResponse 应答
	 * @return ActionForward 转向对象
	 */
	private ActionForward performCheckPass(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

		// 初始化数�?
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");

		// 获取年度作业计划审批信息编号
		String yearCheckId = TawwpUtil.getRequestValue(request, "yearcheckid",
				"");

		// 获取审批信息(意见)
		String content = TawwpUtil.getRequestValue(request, "content", "");

		// 获取下一级审批人用户�?
		String nextCheckUser = TawwpUtil.getRequestValue(request, "checkuser",
				"");

		try {

			// 通过年度作业计划审批
			tawwpYearPlanMgr.passYearCheck(yearCheckId, nextCheckUser, content,
					userId);

			// 转向待审批年度作业计划管�?列表)页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 年度作业计划审批驳回
	 * 
	 * @param mapping
	 *            ActionMapping 集合
	 * @param actionForm
	 *            ActionForm 数据模型
	 * @param request
	 *            HttpServletRequest 请求
	 * @param response
	 *            HttpServletResponse 应答
	 * @return ActionForward 转向对象
	 */
	private ActionForward performCheckReject(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

		// 初始化数�?
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");

		// 获取年度作业计划审批信息编号
		String yearCheckId = TawwpUtil.getRequestValue(request, "yearcheckid",
				"");

		// 获取审批信息(意见)
		String content = TawwpUtil.getRequestValue(request, "content", "");

		try {

			// 驳回年度作业计划审批
			tawwpYearPlanMgr.rejectYearCheck(yearCheckId, content, userId);

			// 转向待审批年度作业计划管�?列表)页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 导出年度作业计划
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performYearExport(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		; // 初始化作业计划模板管理BO�?
		String yearplanid = request.getParameter("yearplanid"); // 年度作业计划标识
		String reaction = request.getParameter("reaction");

		String _exportUrl = "";
		_exportUrl = tawwpYearPlanMgr.exportYearToExcel(yearplanid);

		// 返回页面
		ActionForward actionForward = new ActionForward(reaction + "?url="
				+ _exportUrl);
		actionForward.setRedirect(true);

		return actionForward;

	}

	/**
	 * 年度作业计划审批批量通过
	 * 
	 * @param mapping
	 *            ActionMapping 集合
	 * @param actionForm
	 *            ActionForm 数据模型
	 * @param request
	 *            HttpServletRequest 请求
	 * @param response
	 *            HttpServletResponse 应答
	 * @return ActionForward 转向对象
	 */
	private ActionForward performCheckListPass(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = TawSystemSessionForm.getUserid(); // 当前用户登录�?

		// 初始化数�?
		ITawwpYearPlanMgr tawwpYearPlanMgr = (ITawwpYearPlanMgr) getBean("tawwpYearPlanMgr");
		;

		// 获取年度作业计划审批信息编号
		String yearCheckIdStr = TawwpUtil.getRequestValue(request,
				"yearcheckidstr", "");

		try {

			// 通过年度作业计划审批
			tawwpYearPlanMgr.passYearCheck(yearCheckIdStr, "", userId);

			// 转向待审批年度作业计划管�?列表)页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 通过接口向总部提交年度作业计划
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param actionForm
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 * 
	 * private ActionForward performYearReport(ActionMapping mapping ,
	 * ActionForm actionForm, HttpServletRequest request , HttpServletResponse
	 * response) {
	 * 
	 * ITawwpYearPlanMgr
	 * tawwpYearPlanMgr=(ITawwpYearPlanMgr)getBean("tawwpYearPlanMgr");;
	 * //初始化作业计划模板管理BO�?String yearplanid = request.getParameter("yearplanid");
	 * //年度作业计划标识 tawwpYearPlanMgr.yearReport(yearplanid); return
	 * mapping.findForward("success"); }
	 */

	/**
	 * 管理错误信息，如果有异常出现，则进行处理，将事务回滚
	 * 
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @param e
	 *            Exception 异常对象
	 */
	private void generalError(HttpServletRequest request, Exception e) {
		ActionMessages aes = new ActionMessages();
		aes.add("EOMS_WORKPLAN_ERROR", new ActionMessage("error.general", e
				.getMessage()));
		saveMessages(request, aes);
		BocoLog.error(this, 2, "[WORKPLAN_YEAR] Error -", e);
	}

}
