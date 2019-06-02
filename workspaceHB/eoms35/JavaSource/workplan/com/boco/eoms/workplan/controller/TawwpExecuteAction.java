package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 执行作业计划Action�?/p>
 * <p>Description: 负责页面数据的显示、组织及获取�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.duty.controller.TawRmCycleTableForm;
import com.boco.eoms.workplan.dao.ITawwpModelPlanDao;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.dao.hibernate.TawwpMonthExecuteDaoHibernate;
import com.boco.eoms.workplan.dao.hibernate.TawwpMonthPlanDaoHibernate;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.mgr.ITawwpExecuteCheckMgr;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.mgr.ITawwpMonthMgr;
import com.boco.eoms.workplan.mgr.ITawwpStatMgr;
import com.boco.eoms.workplan.mgr.ITawwpWorkflowMgr;
import com.boco.eoms.workplan.model.TawwpExecuteCheck;
import com.boco.eoms.workplan.model.TawwpExecuteContent;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpExecuteFile;
import com.boco.eoms.workplan.model.TawwpMonthExecute;
import com.boco.eoms.workplan.model.TawwpMonthExecuteUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpWorkflow;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;
import com.boco.eoms.workplan.vo.TawwpExecuteContentUserVO;
import com.boco.eoms.workplan.vo.TawwpExecuteContentVO;
import com.boco.eoms.workplan.vo.TawwpExecuteReportVO;
import com.boco.eoms.workplan.vo.TawwpModelExecuteVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpStubUserVO;

public class TawwpExecuteAction extends BaseAction {

	// 获取属性文�?
	static {
		ResourceBundle prop = ResourceBundle
				.getBundle("resources.application_zh_CN");
	}

	/**
	 * 执行控制方法，以跳转到实际Action
	 * 
	 * @param mapping
	 *            ActionMapping 集合
	 * @param form
	 *            ActionForm 数据模型
	 * @param request
	 *            HttpServletRequest 请求
	 * @param response
	 *            HttpServletResponse 应答
	 * @return ActionForward 转向对象
	 * @throws IOException
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward myforward = null;

		// 获取请求的action属�?
		String myaction = mapping.getParameter();

		// session超时处理
		try {
			/*
			 * TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
			 * request. getSession().getAttribute( "sessionform"); if
			 * (saveSessionBeanForm == null) { return
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
		} else if ("DAILYEXECUTELIST".equalsIgnoreCase(myaction)) {
			myforward = performDailyExecuteList(mapping, form, request,
					response); // 显示日常执行作业计划列表
		} else if ("GXDAILYEXECUTELIST".equalsIgnoreCase(myaction)) {
			myforward = performGXDailyExecuteList(mapping, form, request,
					response); // 显示日常执行作业计划列表(广西合并网元填写版本)
		} else if ("DUTYEXECUTELIST".equalsIgnoreCase(myaction)) {
			myforward = performDutyExecuteList(mapping, form, request, response); // 显示值班执行作业计划列表
		} else if ("GXDUTYEXECUTELIST".equalsIgnoreCase(myaction)) {
			myforward = performGXDutyExecuteList(mapping, form, request,
					response); // 显示值班执行作业计划列表(广西合并网元填写版本)
		} else if ("EXECUTEADDS".equalsIgnoreCase(myaction)) {
			myforward = performExecuteAdds(mapping, form, request, response); // 显示执行内容批量填写页面
		} else if ("GXEXECUTEADDS".equalsIgnoreCase(myaction)) {
			myforward = performGxExecuteAdds(mapping, form, request, response); // 显示执行内容批量填写页面
		} else if ("EXECUTESAVES".equalsIgnoreCase(myaction)) {
			myforward = performExecuteSaves(mapping, form, request, response); // 执行内容批量填写保存
		} else if ("GXEXECUTESAVES".equalsIgnoreCase(myaction)) {
			myforward = performGXExecuteSaves(mapping, form, request, response); // 执行内容批量填写保存
		} else if ("EXECUTEVIEW".equalsIgnoreCase(myaction)) {
			myforward = performExecuteView(mapping, form, request, response); // 显示执行内容查看页面
		} else if ("REPORTADD".equalsIgnoreCase(myaction)) {
			myforward = performReportAdd(mapping, form, request, response); // 显示月、周报添加页�?
		} else if ("REPORTSAVE".equalsIgnoreCase(myaction)) {
			myforward = performReportSave(mapping, form, request, response); // 月、周报填写后保存
		} else if ("REPORTEDIT".equalsIgnoreCase(myaction)) {
			myforward = performReportEdit(mapping, form, request, response); // 显示月、周报编辑页�?
		} else if ("REPORTEDITSAVE".equalsIgnoreCase(myaction)) {
			myforward = performReportEditSave(mapping, form, request, response); // 月、周报编辑后保存
		} else if ("ASSESSLIST".equalsIgnoreCase(myaction)) {
			myforward = performAssessList(mapping, form, request, response); // 待考核执行作业计划列表
		} else if ("ASSESSVIEW".equalsIgnoreCase(myaction)) {
			myforward = performAssessView(mapping, form, request, response); // 显示执行作业计划考核详细信息页面
		} else if ("ASSESSPASS".equalsIgnoreCase(myaction)) {
			myforward = performAssessPass(mapping, form, request, response); // 执行作业计划考核通过
		} else if ("ASSESSREJECT".equalsIgnoreCase(myaction)) {
			myforward = performAssessReject(mapping, form, request, response); // 执行作业计划考核驳回
		} else if ("SAMEVIEW".equalsIgnoreCase(myaction)) {
			myforward = performSameView(mapping, form, request, response); // 显示查看同组执行页面
		} else if ("SAMEVIEW1".equalsIgnoreCase(myaction)) {
			myforward = performSameView1(mapping, form, request, response); // 显示查看同组执行页面
		} else if ("EXECUTEADD".equalsIgnoreCase(myaction)) {
			myforward = performExecuteAdd(mapping, form, request, response); // 显示执行内容添加页面
		} else if ("EXECUTESAVE".equalsIgnoreCase(myaction)) {
			myforward = performExecuteSave(mapping, form, request, response); // 执行内容添加保存
		} else if ("EXECUTEINTERFACESAVE".equalsIgnoreCase(myaction)) {
			myforward = performExecuteInterfaceSave(mapping, form, request,
					response); // 执行内容添加保存
		} else if ("EXECUTEEDIT".equalsIgnoreCase(myaction)) {
			myforward = performExecuteEdit(mapping, form, request, response); // 显示执行内容编辑页面
		} else if ("EXECUTEEDITSAVE".equalsIgnoreCase(myaction)) {
			myforward = performExecuteEditSave(mapping, form, request, response); // 执行内容编辑保存
		} else if ("EXECUTEREFER".equalsIgnoreCase(myaction)) {
			myforward = performExecuteRefer(mapping, form, request, response); // 执行作业计划提交考核
		} else if ("VIEWALL".equalsIgnoreCase(myaction)) {
			myforward = performViewAll(mapping, form, request, response); // 负责人查看执行作业计划详细信�?
		} else if ("VIEWLIST".equalsIgnoreCase(myaction)) {
			myforward = performViewList(mapping, form, request, response); // 负责人查看执行作业计划列�?
		} else if ("VIEWALLLIST".equalsIgnoreCase(myaction)) {
			myforward = performViewALLList(mapping, form, request, response); // 负责人查看执行作业计划列�?
		} else if ("CONTENTVIEW".equalsIgnoreCase(myaction)) {
			myforward = performContentView(mapping, form, request, response); // 负责人查看执行作业计划列�?
		} else if ("COUNTUSER".equalsIgnoreCase(myaction)) {
			myforward = performCountUser(mapping, form, request, response); // 显示当前用户执行数量
		} else if ("CONFIRM".equalsIgnoreCase(myaction)) {
			myforward = performConfirm(mapping, form, request, response); // 确认执行作业计划
		} else if ("FILEUPLOAD".equalsIgnoreCase(myaction)) {
			myforward = performFileUpload(mapping, form, request, response); // 显示当前用户执行数量
		} else if ("SEARCHRESULT".equalsIgnoreCase(myaction)) {
			myforward = performResult(mapping, form, request, response); // 按执行内容查询结果列�?
		} else if ("SEARCHBAKDATA".equalsIgnoreCase(myaction)) {
			myforward = performSearchBakData(mapping, form, request, response);
		} else if ("BAKDATARESULT".equalsIgnoreCase(myaction)) {
			myforward = performBakDataResult(mapping, form, request, response); // add
			// by
			// xudongsuo
			// 2007-07-25
			// for
			// 查询备份数据
		} else if ("EXECUTEVIEWSTAT".equalsIgnoreCase(myaction)) {// add by dende (贵州本地) 目的作业计划查询到用户使�?
			myforward = performExecuteViewStat(mapping, form, request, response); // 显示执行内容查看页面
		} else if ("WOKRFLOWLIST".equalsIgnoreCase(myaction)) {// 工单列表
			myforward = performWorkflowList(mapping, form, request, response); // 显示执行内容查看页面
		} else if ("WOKRFLOWADD".equalsIgnoreCase(myaction)) {// 作业计划工单发起
			myforward = performWorkflowAdd(mapping, form, request, response); // 显示执行内容查看页面
		} else if ("CHECKVIEW".equalsIgnoreCase(myaction)) {
			myforward = performCheckView(mapping, form, request, response);
		} else if ("CHECKAdd".equalsIgnoreCase(myaction)) {
			myforward = performCheckAdd(mapping, form, request, response);
		} else if ("PREVIOUS".equalsIgnoreCase(myaction)) {
			myforward = performPrevious(mapping, form, request, response); // 复制前一工作日执行内容(不支持复制上月最后一天)
		} else if ("QUERYADDONS".equalsIgnoreCase(myaction)) {
			myforward = performQueryAddons(mapping, form, request, response); // 复制前一工作日执行内容(不支持复制上月最后一天)
		} else if ("QUERYADDONSDOWNLOAD".equalsIgnoreCase(myaction)) {
			myforward = performQueryAddonsDownLoad(mapping, form, request,
					response); // 复制前一工作日执行内容(不支持复制上月最后一天)
		} else if ("DAILYEXECUTELISTVIEW".equalsIgnoreCase(myaction)) {
			myforward = performDailyExecuteListView(mapping, form, request,
					response);
		} else if ("DUTYEXECUTELISTVIEW".equalsIgnoreCase(myaction)) {
			myforward = performDutyExecuteListView(mapping, form, request,
					response);
		} else if ("EXECUTEADDSNEW".equalsIgnoreCase(myaction)) {
			myforward = performExecuteAddsNew(mapping, form, request, response);
		} else if ("DAYEXECUTEADDSNEW".equalsIgnoreCase(myaction)) {
			myforward = performDayExecuteAddsNew(mapping, form, request, response);
		} else if ("DAYINSPECTIONnspectionEXECUTEADDSNEW".equalsIgnoreCase(myaction)) {
			myforward = performInspectionDayExecuteAddsNew(mapping, form, request, response);
		} else if ("EXECUTESAVESNEW".equalsIgnoreCase(myaction)) {
			try {
				myforward = performExecuteSavesNew(mapping, form, request,
						response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("PREVIOUSOLD".equalsIgnoreCase(myaction)) {
			// added by xudongsuo 2007-9-21
			myforward = performPreviousold(mapping, form, request, response); // 复制前一工作日执行内容(不支持复制上月最后一天)
		} else if ("DUTYEXECUTELISTADDS".equalsIgnoreCase(myaction)) {
			myforward = performDutyExecuteListAdds(mapping, form, request,
					response);
		} else if ("GETQUALITYLIST".equalsIgnoreCase(myaction)) {
			myforward = performGetQualityList(mapping, form, request, response); // 获取待质检的执行项
		} else if ("QUALITYVIEW".equalsIgnoreCase(myaction)) {
			myforward = performGetQualityView(mapping, form, request, response); // 进入质检页面
		} else if ("QUALITYPASS".equalsIgnoreCase(myaction)) {
			myforward = performGetQualityPass(mapping, form, request, response); // 质检通过
		} else if ("QUALITYREJECT".equalsIgnoreCase(myaction)) {
			myforward = performGetQualityReject(mapping, form, request,
					response); // 质检不通过
		} else if ("QUERYQUALITY".equalsIgnoreCase(myaction)) {
			myforward = performQueryQuality(mapping, form, request, response); // 质检查询
		} else if ("QUERYQUALITYLIST".equalsIgnoreCase(myaction)) {
			myforward = performQueryQualityList(mapping, form, request,
					response); // 质检查询结果
		} else if ("QUERYQUALITYVIEW".equalsIgnoreCase(myaction)) {
			myforward = performQueryQualityView(mapping, form, request,
					response); // 进入质检页面
		} else if ("SEARCHBYNETTYPE".equalsIgnoreCase(myaction)) {
			myforward = performSearchbynettype(mapping, form, request, response);
		} else if ("DEPTVIEW".equalsIgnoreCase(myaction)) {
			myforward = performDEPTVIEW(mapping, form, request, response);
		} else if("EXECUTEINTERFACESAVES".equalsIgnoreCase(myaction)) {
			myforward=performExecuteInterFaceSaves(mapping, form, request, response);  // 批量执行智能巡检
		} else if("INTERFACESAVES".equalsIgnoreCase(myaction)) {
			myforward=performInterFaceSaves(mapping, form, request, response);  // 批量执行智能巡检
		} else if("FILEDOWN".equalsIgnoreCase(myaction)) {
			myforward=performFileDown(mapping, form, request, response);  // 批量执行智能巡检
		} else if ("EXECUTENETLIST".equalsIgnoreCase(myaction)) {
			myforward = performExecuteNetList(mapping, form, request, response);
		} else if ("NOTEXECUTEADDS".equalsIgnoreCase(myaction)) {
			myforward = performNotExecuteListAdds(mapping, form, request, response);
		} else if ("VIEWDAILYEXECUTE".equalsIgnoreCase(myaction)) {
			myforward = performViewDailyExecute(mapping, form, request, response);// add// by// gongyufeng// 手动批量执行
		} else {
			myforward = mapping.findForward("failure"); // 无效的请求，转向错误页面
		}

		return myforward;
	}

	private ActionForward performDEPTVIEW(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO();
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		String stubFlag = "";
		String userId = "";
		String deptId = "";
		String roomId = "";

		// 获取页面传递过来的参数
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?

		try {

			// 设定代理标志
			if (!"".equals(userByStub)) {
				stubFlag = "stub";
				userId = userByStub;
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(userByStub);
				deptId = String.valueOf(userTemp.getDeptid());
				// deptId = String.valueOf(
				// (tawRmUserDAO.retrieve(userByStub)).getDeptId());
			} else {
				stubFlag = "own";
				userId = currUser; // 当前用户�?
				deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
				roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
			}

			// 获取VO对象
			tawwpMonthPlanVO = tawwpExecuteMgr.viewExecutePlan(monthPlanId);
			// tawwpMonthPlanVO =
			// tawwpExecuteMgr.viewExecutePlanExecuter(userId,
			// deptId, roomId, monthPlanId, stubFlag);

			// 为页面显示准备数�?
			request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);
			request.setAttribute("curruser", currUser);

			// 转向执行作业计划详细信息浏览页面
			String id = tawwpMonthPlanVO.getId();
			TawwpMonthExecuteDaoHibernate tawMonth = (TawwpMonthExecuteDaoHibernate) getBean("tawwpMonthExecuteDao");
			List list = tawMonth.getMonthExecutebyMonthPlanId(id);
			String name = "";
			boolean flag = false;
			String userid = saveSessionBeanForm.getUserid();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(((TawwpMonthExecute) list.get(i))
						.getExecuter());
				// if((","+taw.getExecuter()+",").indexOf(","+user[n]+",")>=0)
				if (("," + ((TawwpMonthExecute) list.get(i)).getExecuter() + ",")
						.indexOf("," + userid + ",") >= 0) {
					flag = true;
				}
			}
			if (flag == true)
				return mapping.findForward("success");
			else
				return mapping.findForward("ok");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	private ActionForward performSearchbynettype(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 获取当前用户的session中的信息
		List monthExcutelist = new ArrayList();
		List monthplanlist = new ArrayList();
		String userall = "";
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptId = request.getParameter("deptid"); // 当前部门编号
		ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("itawSystemUserManager");
		List list = userManager.getUserBydeptids(deptId);
		for (int i = 0; i < list.size(); i++) {
			TawSystemUser user = (TawSystemUser) list.get(i);
			userall += user.getUserid() + ",";
		}
		userall = userall.substring(0, userall.length() - 1);
		String[] user = userall.split(",");
		String nettypeid = request.getParameter("nettypeid");
		ITawwpStatMgr mgr = (ITawwpStatMgr) getBean("tawwpStatMgr");
		List mylist = null;
		try {
			mylist = mgr.listidbynettypeid(nettypeid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String year;
		String months;
		year = request.getParameter("year");
		months = request.getParameter("months");
		if (year == null) {
			Date date = new Date();

			year = TawwpUtil.getCurrentDateTime("yyyy");
			int month = date.getMonth() + 1;
			if (month < 10)
				months = "0" + month;
			else
				months = String.valueOf(month);
		}

		TawwpMonthPlanDaoHibernate tawwpMonthPlanDao = (TawwpMonthPlanDaoHibernate) getBean("tawwpMonthPlanDao");
		List monthlist = tawwpMonthPlanDao.getMonthPlanByNet(nettypeid, year,
				months);
		for (int j = 0; j < monthlist.size(); j++) {
			String monthid = ((TawwpMonthPlan) monthlist.get(j)).getId();
			TawwpMonthExecuteDaoHibernate tawMonth = (TawwpMonthExecuteDaoHibernate) getBean("tawwpMonthExecuteDao");
			List listexecuteList = tawMonth
					.getMonthExecutebyMonthPlanId(monthid);
			for (int m = 0; m < listexecuteList.size(); m++) {
				TawwpMonthExecute taw = (TawwpMonthExecute) listexecuteList
						.get(m);
				if (taw.getExecuter() != null) {
					for (int n = 0; n < user.length; n++) {
						if (("," + taw.getExecuter() + ",").indexOf(","
								+ user[n] + ",") >= 0) {
							monthExcutelist.add(taw);
						}
					}
				}
			}

		}

		for (int k = 0; k < monthExcutelist.size(); k++) {
			TawwpMonthPlan monthplan = ((TawwpMonthExecute) monthExcutelist
					.get(k)).getTawwpMonthPlan();
			boolean flag = false;
			for (int i = 0; i < monthplanlist.size(); i++) {
				if (((TawwpMonthPlan) monthplanlist.get(i)).getId() == monthplan
						.getId()) {
					flag = true;
				}
			}
			if (flag == false)
				monthplanlist.add(monthplan);
		}
		request.setAttribute("monthplanlist", monthplanlist);
		List stubUserList = saveSessionBeanForm.getStubUserList();
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();

		// List对象
		List monthExecuteUserList = null;

		// Hashtable对象
		Hashtable monthPlanVOHash = new Hashtable();

		Hashtable tempHash = null;
		Enumeration tempkeys = null;
		String monthPlanId = "";
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		List stubMonthPlanList = new ArrayList();
		// model对象
		TawwpMonthExecuteUser tawwpMonthExecuteUser = null;
		TawwpMonthPlan tawwpMonthPlan = null;

		if (monthExcutelist != null) {
			for (int i = 0; i < monthplanlist.size(); i++) {

				// 取出model对象(modified by lijia 2005-11-28)

				tawwpMonthPlan = (TawwpMonthPlan) monthplanlist.get(i);

				// 如果Hashtable对象中没有包括,以此月度作业计划编号为key值的value,则进行VO对象的封装
				if (monthPlanVOHash.get(tawwpMonthPlan.getId()) == null) {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					// 进行VO对象的封装
					try {
						MyBeanUtils.copyPropertiesFromDBToPage(
								tawwpMonthPlanVO, tawwpMonthPlan);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 获取执行类型名称
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 获取部门名称
					try {
						tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
								.getDeptName(tawwpMonthPlanVO.getDeptId()));
					} catch (DictDAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 获取系统类型名称
					try {
						tawwpMonthPlanVO
								.setSysTypeName(tawwpUtilDAO
										.getSysTypeName(tawwpMonthPlanVO
												.getNetTypeId()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 获取网元类型名称
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 获取创建人姓名
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 获取制定状态名称
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 获取执行状态名称
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 获取执行负责人姓名
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 获取网元名称
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}

					// modified by lijia 2005-11-28

					// 将VO对象添加到Hashtable对象中

					monthPlanVOHash.put(tawwpMonthPlan, tawwpMonthPlanVO);

				}
			}

		}

		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		// 如果代理信息存在
		if (stubUserList != null) {

			for (int i = 0; i < stubUserList.size(); i++) {
				// 获取代理信息VO对象
				tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
				// 获取代理申请人需要执行的月度作业计划
				tempHash = new Hashtable();
				try {
					tempHash = tawwpExecuteMgr.listExecutePlan(tawwpStubUserVO
							.getCruser(), String.valueOf(deptId));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (tempHash.size() != 0) {
					// 取出全部月度作业计划编号
					tempkeys = tempHash.keys();

					while (tempkeys.hasMoreElements()) {
						monthPlanId = (String) tempkeys.nextElement();
						// 获取月度作业计划VO对象,并修改代理标志位
						tawwpMonthPlanVO = (TawwpMonthPlanVO) (tempHash
								.get(monthPlanId));
						tawwpMonthPlanVO.setStubFlag("1");
						tawwpMonthPlanVO.setUserByStub(tawwpStubUserVO
								.getCruser()); // 修改被代理用户名
					}
					stubMonthPlanList.add(tempHash);
				}
			}
		}
		request.setAttribute("m", months);
		request.setAttribute("deptid", deptId);
		request.setAttribute("nettypeid", nettypeid);
		request.setAttribute("monthplanvohash", monthPlanVOHash);
		request.setAttribute("stubmonthplanlist", stubMonthPlanList);

		// // ------------------------------------------------
		// Enumeration hashKeys = null;
		// // List listTemp = new ArrayList();
		// List listKey = new ArrayList();
		// TawwpMonthPlan tawwpMonthPlan = null;
		// if (monthPlanVOHash.size() != 0) {
		// hashKeys = monthPlanVOHash.keys();
		// while (hashKeys.hasMoreElements()) {
		// tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());
		// // if(tawwpMonthPlan.getTypeIndex()==null){
		// // tawwpMonthPlan.setTypeIndex("");
		// // }
		//
		// listKey.add(tawwpMonthPlan);
		// tawwpMonthPlan = null;
		// }
		// Collections.sort(listKey);
		// }
		// // tawwpYearPlan=(TawwpYearPlan)listKey.get(0);
		// // tawwpYearPlan=(TawwpYearPlan)listKey.get(1);
		// // tawwpYearPlan=(TawwpYearPlan)listKey.get(2);
		// request.setAttribute("listKey", listKey);
		// // ----------------------------------------------------
		//
		// System.out.println(monthPlanVOHash);
		// // 为页面显示准备数�?
		// request.setAttribute("monthplanvohash", monthPlanVOHash);
		// request.setAttribute("stubmonthplanlist", stubMonthPlanList);
		// // 若portlet则跳转到日常执行作业计划管理portlet页面
		// if (request.getParameter("portal") != null
		// && "true".equals(request.getParameter("portal"))) {
		// return mapping.findForward("portlet");
		// } else {
		// // 转向日常执行作业计划管理(列表)页面
		// return mapping.findForward("success");
		// }
		// } catch (Exception e) {
		// generalError(request, e); // 将错误信息加入到消息队列、写入日�?
		// ActionMessages messages = new ActionMessages();
		// messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		// "submit.title.failure"));
		// saveMessages(request, messages);
		// return mapping.findForward("failure"); // 转向错误页面
		// } finally {
		// tempHash = null;
		// tempkeys = null;
		// }

		if (request.getParameter("portal") != null
				&& "true".equals(request.getParameter("portal"))) {
			return mapping.findForward("portlet");
		} else {
			// 转向日常执行作业计划管理(列表)页面
			return mapping.findForward("success");
		}
	}

	private ActionForward performDutyExecuteListAdds(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = saveSessionBeanForm.getRoomId(); // 当前机房编号
		// 初始化数据
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List monthPlanVOList = null;
		String dutyStartDate = "";
		String dutyEndDate = "";
		String date = TawwpUtil.getCurrentDateTime().substring(8, 10);
		String flag = "duty";
		// 获取当前年、月
		String yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
		String monthFlag = TawwpUtil.getCurrentDateTime("MM");

		try {

			// 判断值班状态
			if (saveSessionBeanForm.getStartDuty() != null
					&& saveSessionBeanForm.getEndDuty() != null) {
				dutyStartDate = StaticMethod.getCurrentDateTime(
						saveSessionBeanForm.getStartDuty(),
						"yyyy-MM-dd HH:mm:ss")
						+ "";
				dutyEndDate = StaticMethod
						.getCurrentDateTime(saveSessionBeanForm.getEndDuty(),
								"yyyy-MM-dd HH:mm:ss")
						+ "";

			} else {
				// 如果当前用户未处于值班状态
				request.setAttribute("error", "noduty");
				// 转向提示页面
				return mapping.findForward("failure");

			}

			// 获取执行作业计划集合
			// monthPlanVOList = tawwpExecuteMgr.listDutyExecutePlan(String
			// .valueOf(roomId), yearFlag, monthFlag, dutyStartDate,
			// dutyEndDate);
			monthPlanVOList = (ArrayList) request.getSession().getAttribute(
					"monthPlanVOList");

			HashMap monthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
			HashMap monthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
			if (monthPlanVOList != null) {
				String stubFlag = "duty";
				String userId = currUser; // 当前用户�

				for (int i = 0; i < monthPlanVOList.size(); i++) {
					TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) monthPlanVOList
							.get(i);
					String monthplanid = tawwpmonthplanvo.getId();
					try {
						// 获取单个月度作业计划执行内容(整体)VO对象集合
						List executeContentVOList = tawwpExecuteMgr
								.addExecuteContentUsersView(userId, deptId,
										roomId, monthplanid, dutyStartDate,
										dutyEndDate, flag, stubFlag, date);
						HashMap contentHash = new LinkedHashMap();
						contentHash = tawwpExecuteMgr
								.dealList(executeContentVOList);

						monthplanvoHash.put(tawwpmonthplanvo,
								executeContentVOList);
						monthplancontentHash.put(tawwpmonthplanvo, contentHash);
					} catch (Exception e) {
						e.printStackTrace();
						return mapping.findForward("failure"); // 转向错误页面
					}

				}

			}

			request.setAttribute("flag", flag);
			request.setAttribute("curruser", currUser);
			request.setAttribute("date", date);
			request.setAttribute("monthplanvoHash", monthplanvoHash);
			request.setAttribute("monthplancontentHash", monthplancontentHash);

			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * added by xudongsuo 2007-07-19 以前日期工作日执行内容Action
	 * 逻辑：针对周期为日的作业计划执行内容,将上一工作日的执行内容,复制到当日 注意：1、每月第一天不能复制上一月度最后一天的
	 * 2、因为此需求为联通需求开发,不将BO方法并入主版本(基类), 如需要修改,可在子类的ExecuteBO中找到
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
	private ActionForward performPreviousold(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户名

		// 初始化数据
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");

		// 获取页面提交的参数
		String flagStr = request.getParameter("flag"); // 标志日常还是值班的批量填写
		String userByStubStr = TawwpUtil.getRequestValue(request, "userbystub",
				""); // 代理用户名
		String monthPlanIdStr = request.getParameter("monthplanid"); // 执行(单一用户)编号
		String dateStr = TawwpUtil.getRequestValue(request, "date", ""); // 批量填写日期
		String day = TawwpUtil.getRequestValue(request, "day", ""); // 批量填写日期

		String[] flagArray = flagStr.split(";");
		String[] monthPlanIdArray = monthPlanIdStr.split(";");
		String[] dateArray = dateStr.split(";");
		int j = 0; // add by jql
		for (int i = 0; i < dateArray.length; i++) {

			try {
				String flag = flagArray[i];
				String date = dateArray[i];
				String userByStub = "";
				String monthPlanId = monthPlanIdArray[i];

				// 如果填写日期为空则,取当前日期
				if (date == null || "".equals(date)) {
					date = TawwpUtil.getCurrentDateTime("dd");
				}

				// 如果取得的日期小于10且没有经过补0处理,则在日期前加0
				if (date.length() == 1) {
					date = "0" + date;
				}

				String dutyDate = ""; // 值班时段
				String dutyStartDate = ""; // 值班开始时间
				String dutyEndDate = ""; // 值班结束时间
				String stubFlag = ""; // 代理标志:"stub"或者"own"
				String userId = ""; // 执行用户登录名
				String deptId = ""; // 部门编号
				String roomId = ""; // 机房编号

				/*
				 * if (date.equals("01")) { // 如果当前日期为当月一号
				 * request.setAttribute("error", "firstday"); // 转向提示页面 return
				 * mapping.findForward("failure"); }
				 */

				// 设定代理标志
				if (!"".equals(userByStub)) {
					stubFlag = "stub";
					userId = userByStub; // 被代理人用户名
					TawSystemUser userTemp = tawRmUserDAO
							.getUserByuserid(userByStub);
					deptId = String.valueOf(userTemp.getDeptid());
				} else {
					stubFlag = "own";
					userId = currUser; // 当前用户名
					deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
					roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
				}

				// 如果为值班批量填写,获取班次开始、结束时间
				if ("duty".equals(flag)) {
					dutyDate = saveSessionBeanForm.getWorkSerialTime(); // 值班的时间段
					String[] dates = dutyDate.split("-->");
					dutyStartDate = dates[0].trim().substring(0, 19); // 班次开始时间
					dutyEndDate = dates[1].trim().substring(0, 19); // 班次结束时间
				}

				// 执行复制操作
				// Session s = HibernateUtil.currentSession(); //对每个计划操作完成,既提交事务
				j = tawwpExecuteMgr.previousold(userId, currUser, deptId,
						roomId, monthPlanId, dutyStartDate, dutyEndDate, flag,
						stubFlag, date, day, j); // add by jql

			} catch (Exception e) {

				e.printStackTrace();

			}

		}
		// 转向执行作业计划批量填写页面
		return mapping.findForward("success");

	}

	private ActionForward performExecuteSavesNew(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符�?
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String executeType = request.getParameter("executetype"); // 执行类型
		String formindex = request.getParameter("formindex");
		String stubFlag = request.getParameter("stubFlag");

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";

		String content = "";
		String remark = "";
		String formDataId = "";
		String fileName = "";

		String cruser = "";
		String stubUser = "";

		String contentUserId = "";
		String normalFlag = "";
		String writeDate = "";
		String reason = "";

		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 处理执行作业计划执行内容(整体)编号字符�?
		String[] tempStr = contentIdStr.split(",");

		try {
			// 如果为值班批量填写,获取班次开始、结束时�?
			if ("duty".equals(flag)) {

				dutyStartDate = saveSessionBeanForm.getStartDuty() + ""; // 班次开始时�?
				dutyEndDate = saveSessionBeanForm.getEndDuty() + ""; // 班次结束时间
			}

			for (int i = 0; i < tempStr.length; i++) {

				// 获取从页面传递过来的参数

				content = request.getParameter("content" + i + "_" + formindex
						+ "_" + stubFlag);
				remark = request.getParameter("remark" + i + "_" + formindex
						+ "_" + stubFlag);
				formDataId = request.getParameter("formdataid" + i + "_"
						+ formindex + "_" + stubFlag);
				fileName = request.getParameter("filename" + i + "_"
						+ formindex + "_" + stubFlag);
				contentUserId = request.getParameter("executecontentuserid" + i
						+ "_" + formindex + "_" + stubFlag);
				cruser = request.getParameter("userid" + i + "_" + formindex
						+ "_" + stubFlag);
				stubUser = request.getParameter("stubuser" + i + "_"
						+ formindex + "_" + stubFlag);
				writeDate = request.getParameter("writeDate" + i + "_"
						+ formindex + "_" + stubFlag);
				normalFlag = request.getParameter("normalFlag" + i + "_"
						+ formindex + "_" + stubFlag);
				reason = request.getParameter("reason" + i + "_" + formindex
						+ "_" + stubFlag);
				if (normalFlag == null || "".equals(normalFlag)) {
					normalFlag = "1";
				}
				if ((content == null || "".equals(content))
						&& (formDataId == null || "".equals(formDataId))
						&& (fileName == null || "".equals(fileName))
						&& (contentUserId == null || "".equals(contentUserId))) {
				} else {

					tawwpExecuteContentUser = new TawwpExecuteContentUser();
					tawwpExecuteContentUser.setContent(content); // 执行内容
					tawwpExecuteContentUser.setRemark(remark);
					tawwpExecuteContentUser.setFormDataId(formDataId); // 附加表单记录编号
					tawwpExecuteContentUser.setId(contentUserId); // 执行内容(单一用户)编号
					tawwpExecuteContentUser.setCruser(cruser);
					tawwpExecuteContentUser.setCrtime(TawwpUtil
							.getCurrentDateTime());
					tawwpExecuteContentUser.setWriteDate(writeDate);
					tawwpExecuteContentUser.setStubUser(stubUser);
					tawwpExecuteContentUser.setNormalFlag(normalFlag);
					tawwpExecuteContentUser.setReason(reason);
					// 将执行作业计划执行内�?单一用户)对象添加到Hashtable
					executeContentUserHash.put(tempStr[i],
							tawwpExecuteContentUser);

				}
			}

			if ("duty".equals(flag)) {
				// 保存值班批量填写内容
				tawwpExecuteMgr.addDutyExecuteContentUsersSave(
						executeContentUserHash, dutyStartDate, dutyEndDate,
						userId, executeType);
				// 日志
				ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
				tawwpLogMgr
						.addLog(cruser, "addDutyExecuteContentUsersSave", "");

				// 转向值班执行作业计划列表管理页面
				JSONUtil.success(response, "保存成功!");
				return null;
			} else {
				ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
				// 保存日常批量填写内容
				// tawwpExecuteMgr.addExecuteContentUsersSave(
				// executeContentUserHash, userId, executeType);

				mgr.addExecuteContentUsersSave(executeContentUserHash, userId,
						executeType);

				// 转向日常执行作业计划列表管理页面
				JSONUtil.success(response, "保存成功!");
				return null;
			}
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			JSONUtil.fail(response, "保存失败");// 转向错误页面
			return null;
		}

	}

	private ActionForward performExecuteAddsNew(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) getBean("itawSystemUserManager");
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�
		String flag = "daily";
		HttpSession session = request.getSession();
		String year = "";
		String month = "";
		String date = request.getParameter("date");
		if (date != null && !date.equals("")) {
			if (date.length() == 1) {
				date = "0" + date;
			}
			session.setAttribute("date", date);
		} else {
			date = (String) session.getAttribute("date");
			if (date == null || "".equals(date)) {
				date = TawwpUtil.getCurrentDateTime("dd");
				if (date.length() == 1) {
					date = "0" + date;
				}
			}
		}

		List monthPlanVOList = (ArrayList) session
				.getAttribute("monthPlanVOList");
		List stubMonthPlanVOList = (ArrayList) session
				.getAttribute("stubMonthPlanVOList");

		HashMap monthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		HashMap monthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
		if (monthPlanVOList != null) {
			String stubFlag = "own";
			String userId = currUser; // 当前用户�
			String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
			String roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
			String dutyStartDate = "";
			String dutyEndDate = "";

			for (int i = 0; i < monthPlanVOList.size(); i++) {
				TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) monthPlanVOList
						.get(i);
				year = tawwpmonthplanvo.getYearFlag();
				month = tawwpmonthplanvo.getMonthFlag();
				String monthplanid = tawwpmonthplanvo.getId();
				try {
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.addExecuteContentUsersView(userId, deptId, roomId,
									monthplanid, dutyStartDate, dutyEndDate,
									flag, stubFlag, date);
					HashMap contentHash = new LinkedHashMap();
					contentHash = tawwpExecuteMgr
							.dealList(executeContentVOList);

					monthplanvoHash.put(tawwpmonthplanvo, executeContentVOList);
					monthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}

			}

		}

		HashMap stubMonthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		HashMap stubMonthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
		if (stubMonthPlanVOList != null) {
			String stubFlag = "stub";
			String roomId = "";
			String dutyStartDate = "";
			String dutyEndDate = "";
			String reason = "";

			for (int i = 0; i < stubMonthPlanVOList.size(); i++) {
				TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) stubMonthPlanVOList
						.get(i);
				String monthplanid = tawwpmonthplanvo.getId();
				String userbystub = tawwpmonthplanvo.getUserByStub();
				String userId = userbystub;
				TawSystemUser userTemp = tawRmUserDAO.getUserByuserid(userId);
				String deptId = String.valueOf(userTemp.getDeptid());
				try {
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.addExecuteContentUsersView(userId, deptId, roomId,
									monthplanid, dutyStartDate, dutyEndDate,
									flag, stubFlag, date);
					HashMap contentHash = new LinkedHashMap();
					contentHash = tawwpExecuteMgr
							.dealList(executeContentVOList);

					stubMonthplanvoHash.put(tawwpmonthplanvo,
							executeContentVOList);
					stubMonthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}

			}

		}
		request.setAttribute("flag", flag);
		request.setAttribute("curruser", currUser);
		request.setAttribute("date", date);
		request.setAttribute("years", year + "-" + month + "-" + date);
		request.setAttribute("monthplanvoHash", monthplanvoHash);
		request.setAttribute("monthplancontentHash", monthplancontentHash);
		request.setAttribute("stubMonthplanvoHash", stubMonthplanvoHash);
		request.setAttribute("stubMonthplancontentHash",
				stubMonthplancontentHash);
		return mapping.findForward("success");
	}

	private ActionForward performDailyExecuteListView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) getBean("itawSystemUserManager");

		// 处理时间
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null || month == null) {
			String date = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
			year = date.substring(0, 4);
			month = date.substring(5, 7);
		}

		// 获取session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// 获取当前用户信息
		String userId = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
		// 获取当前用户日常执行列表
		List monthPlanVOList = null;
		try {
			monthPlanVOList = tawwpExecuteMgr.listDailyExecuteView(userId,
					deptId, roomId, year, month, "own");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取代理用户信息
		List stubUserList = saveSessionBeanForm.getStubUserList();
		List stubSysUserList = new ArrayList();// 代理用户对象列表
		String stubUserIds = "";
		String[] stubUserId = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		if (stubUserList != null) {
			for (int i = 0; i < stubUserList.size(); i++) {
				// 获取代理用户VO对象
				tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
				if (stubUserIds.indexOf(tawwpStubUserVO.getCruser() + ",") == -1) {
					stubUserIds = stubUserIds + tawwpStubUserVO.getCruser()
							+ ",";
				}
			}
		}
		if (stubUserIds.length() > 0) {
			stubUserId = stubUserIds.split(",");
		}

		if (stubUserId != null) {
			for (int i = 0; i < stubUserId.length; i++) {
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(stubUserId[i]);
				stubSysUserList.add(userTemp);
			}
		}
		// 获取代理用户日常执行列表
		List stubMonthPlanVOList = new ArrayList();
		List temp = null;
		try {
			for (int i = 0; i < stubSysUserList.size(); i++) {
				TawSystemUser userTemp = (TawSystemUser) stubSysUserList.get(i);
				temp = tawwpExecuteMgr.listDailyExecuteView(userTemp
						.getUserid(), userTemp.getDeptid(), "", year, month,
						"stub");
				if (temp != null) {
					for (int j = 0; j < temp.size(); j++) {
						stubMonthPlanVOList.add(temp.get(j));
					
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(monthPlanVOList, new Comparator() {
			public int compare(Object o1, Object o2) {
				TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
				TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
				int k = -1;
				if (p1.getName().compareTo(p2.getName()) > 0) {
					k = 1;
				} else if (p1.getName().compareTo(p2.getName()) == 0) {
					if (p1.getId().compareTo(p2.getId()) > 0) {
						k = 1;
					} else {
						k = -1;
					}
				} else if (p1.getName().compareTo(p2.getName()) < 0) {
					k = -1;
				}
				return k;

			}
		});
		Collections.sort(stubMonthPlanVOList, new Comparator() {
			public int compare(Object o1, Object o2) {
				TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
				TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
				int k = -1;
				if (p1.getName().compareTo(p2.getName()) > 0) {
					k = 1;
				} else if (p1.getName().compareTo(p2.getName()) == 0) {
					if (p1.getId().compareTo(p2.getId()) > 0) {
						k = 1;
					} else {
						k = -1;
					}
				} else if (p1.getName().compareTo(p2.getName()) < 0) {
					k = -1;
				}
				return k;

			}
		});
		request.setAttribute("curruser", userId);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("monthPlanVOList", monthPlanVOList);
		request.setAttribute("stubMonthPlanVOList", stubMonthPlanVOList);
		return mapping.findForward("success");
	}

	private ActionForward performDayExecuteListView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) getBean("itawSystemUserManager");

		// 处理时间
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null || month == null) {
			String date = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
			year = date.substring(0, 4);
			month = date.substring(5, 7);
		}

		// 获取session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// 获取当前用户信息
		String userId = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
		// 获取当前用户日常执行列表
		List monthPlanVOList = null;
		try {
			monthPlanVOList = tawwpExecuteMgr.listDailyExecuteView(userId,
					deptId, roomId, year, month, "own");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取代理用户信息
		List stubUserList = saveSessionBeanForm.getStubUserList();
		List stubSysUserList = new ArrayList();// 代理用户对象列表
		String stubUserIds = "";
		String[] stubUserId = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		if (stubUserList != null) {
			for (int i = 0; i < stubUserList.size(); i++) {
				// 获取代理用户VO对象
				tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
				if (stubUserIds.indexOf(tawwpStubUserVO.getCruser() + ",") == -1) {
					stubUserIds = stubUserIds + tawwpStubUserVO.getCruser()
							+ ",";
				}
			}
		}
		if (stubUserIds.length() > 0) {
			stubUserId = stubUserIds.split(",");
		}

		if (stubUserId != null) {
			for (int i = 0; i < stubUserId.length; i++) {
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(stubUserId[i]);
				stubSysUserList.add(userTemp);
			}
		}
		// 获取代理用户日常执行列表
		List stubMonthPlanVOList = new ArrayList();
		List temp = null;
		try {
			for (int i = 0; i < stubSysUserList.size(); i++) {
				TawSystemUser userTemp = (TawSystemUser) stubSysUserList.get(i);
				temp = tawwpExecuteMgr.listDailyExecuteView(userTemp
						.getUserid(), userTemp.getDeptid(), "", year, month,
						"stub");
				if (temp != null) {
					for (int j = 0; j < temp.size(); j++) {
						stubMonthPlanVOList.add(temp.get(j));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("curruser", userId);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("monthPlanVOList", monthPlanVOList);
		request.setAttribute("stubMonthPlanVOList", stubMonthPlanVOList);
		return mapping.findForward("success");
	}

	private ActionForward performQueryAddonsDownLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String modelplanid = request.getParameter("modelplanid");
		String cruser = request.getParameter("cruser");
		String formid = request.getParameter("formid");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		// 根据条件获取执行记录集合
		List executecontentuserlist = tawwpExecuteMgr.listExeConUserforAddons(
				modelplanid, cruser, formid, startDate, endDate);
		// 附加表文件保存目录
		String oldpath = TawwpStaticVariable.wwwDir
				+ "workplan/tawwpfile/execute";
		// 导出附加表文件保存临时目录
		String temppath = TawwpStaticVariable.wwwDir
				+ "workplan/tawwpfile/temppath";
		// 删除旧的临时目录
		StaticMethod.delFolder(temppath);
		// 删除旧的压缩文件
		StaticMethod.delFile(temppath + ".zip");
		// 建立新的临时目录
		StaticMethod.newFolder(temppath);
		// 获得符合条件的附加表文件并拷贝到临时目录
		if (executecontentuserlist != null
				&& executecontentuserlist.size() != 0) {
			for (int i = 0; i < executecontentuserlist.size(); i++) {
				TawwpExecuteContentUser ecuser = (TawwpExecuteContentUser) executecontentuserlist
						.get(i);
				if (ecuser.getFormDataId() != null
						&& !ecuser.getFormDataId().equals("")) {
					StaticMethod.copyFile(oldpath + "/"
							+ ecuser.getFormDataId() + ".xls", temppath + "/"
							+ ecuser.getFormDataId() + ".xls");
				}
			}
		}
		// 压缩临时目录为.zip文件
		try {
			StaticMethod.CreateZip(temppath, temppath + ".zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			InputStream inStream = new FileInputStream(temppath + ".zip");// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("application/x-msdownload;charset=GBK");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=addonsDownLoad.zip");
			byte[] b = new byte[128];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ActionForward performQueryAddons(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取附加表集合
		ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
		List list = new ArrayList();
		try {
			list = tawwpAddonsMgr.listAddonsByWorkPlan();
		} catch (TawwpException e) {
			e.printStackTrace();
		}
		request.setAttribute("addonslist", list);
		// 获取年计划集合
		ITawwpModelPlanDao twwwpmodelplanDao = (ITawwpModelPlanDao) getBean("tawwpModelPlanDao");
		List modelplanlist = twwwpmodelplanDao.listModelPlan();
		request.setAttribute("modelplanlist", modelplanlist);
		return mapping.findForward("success");
	}

	private ActionForward performPrevious(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户名
		// 初始化数据
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		// 获取页面提交的参数
		String flagStr = request.getParameter("flag"); // 标志日常还是值班的批量填写
		String monthPlanIdStr = request.getParameter("monthplanid"); // 执行(单一用户)编号
		String dateStr = TawwpUtil.getRequestValue(request, "date", ""); // 批量填写日期

		String[] flagArray = flagStr.split(";");
		String[] monthPlanIdArray = monthPlanIdStr.split(";");
		String[] dateArray = dateStr.split(";");
		int j = 0; // add by jql
		for (int i = 0; i < dateArray.length; i++) {

			try {
				String flag = flagArray[i];
				String date = dateArray[i];
				String userByStub = "";
				String monthPlanId = monthPlanIdArray[i];

				// 如果填写日期为空则,取当前日期
				if (date == null || "".equals(date)) {
					date = TawwpUtil.getCurrentDateTime("dd");
				}

				// 如果取得的日期小于10且没有经过补0处理,则在日期前加0
				if (date.length() == 1) {
					date = "0" + date;
				}

				String dutyDate = ""; // 值班时段
				String dutyStartDate = ""; // 值班开始时间
				String dutyEndDate = ""; // 值班结束时间
				String stubFlag = ""; // 代理标志:"stub"或者"own"
				String userId = ""; // 执行用户登录名
				String deptId = ""; // 部门编号
				String roomId = ""; // 机房编号

				if (date.equals("01")) {
					// 如果当前日期为当月一号
					request.setAttribute("error", "firstday");
					// 转向提示页面
					return mapping.findForward("failure");
				}

				// 设定代理标志
				if (!"".equals(userByStub)) {
					stubFlag = "stub";
					userId = userByStub; // 被代理人用户名
					TawSystemUser userTemp = tawRmUserDAO
							.getUserByuserid(userByStub);
					deptId = String.valueOf(userTemp.getDeptid());
				} else {
					stubFlag = "own";
					userId = currUser; // 当前用户名
					deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
					roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
				}

				// 如果为值班批量填写,获取班次开始、结束时间
				if ("duty".equals(flag)) {
					dutyDate = saveSessionBeanForm.getWorkSerialTime(); // 值班的时间段
					String[] dates = dutyDate.split("-->");
					dutyStartDate = dates[0].trim().substring(0, 19); // 班次开始时间
					dutyEndDate = dates[1].trim().substring(0, 19); // 班次结束时间
				}

				// 执行复制操作
				// HibernateUtil.currentSession(); //对每个计划操作完成,既提交事务
				j = tawwpExecuteMgr.previous(userId, currUser, deptId, roomId,
						monthPlanId, dutyStartDate, dutyEndDate, flag,
						stubFlag, date, j); // add by jql

				// HibernateUtil.commitTransaction();
				// HibernateUtil.closeSession(); //结束事务

			} catch (Exception e) {

				HibernateUtil.rollbackTransaction();// 回滚事务
				HibernateUtil.closeSession(); // 结束事务

			}

		}
		// 转向执行作业计划批量填写页面
		return mapping.findForward("success");

	}

	private ActionForward performSearchBakData(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");

	}

	private ActionForward performBakDataResult(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; // 硬盘实际目录
		String rootDir = TawwpStaticVariable.rootDir; // web目录
		String startDate = request.getParameter("startDate") + " 00:00:00";
		String endDate = request.getParameter("endDate") + " 23:59:59";
		String serialno = request.getParameter("serialno");
		String addonstable = request.getParameter("addonstable");
		String timeer = request.getParameter("startDate").replace("-", "")
				+ "_" + request.getParameter("endDate").replace("-", "");

		StringBuffer StrBuffer = new StringBuffer("");
		String url = "";
		try {

			ITawwpNetDao tawwpNetDAO = (ITawwpNetDao) getBean("tawwpNetDao");

			String netid = tawwpNetDAO.loadNetBySerial(serialno);

			try {

				String fileName = rootSaveXMLDir + "50" + "/" + serialno + "_"
						+ timeer + ".xml";
				String strFileName = rootDir + fileName;
				File file = new File(strFileName);
				// 获得SaveSessionBeanForm
				TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
						.getSession().getAttribute("sessionform");
				TawwpUtilDAO tawwputilDAO = new TawwpUtilDAO();

				List list = tawwputilDAO.getformdataid(startDate, endDate,
						netid, addonstable);

				ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr) getBean("tawwpAddonsMgr");
				TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsMgr
						.viewAddonsTableVO(addonstable);
				url = tawwpAddonsTableVO.getUrl();

				StrBuffer = (StringBuffer) tawwpAddonsMgr.getAddonsHtmlBuffer(
						url, saveSessionBeanForm.getUserid(), list, serialno);

				request.setAttribute("strbuffer", StrBuffer);

			} catch (Exception e) {
				generalError(request, e);
				return mapping.findForward("failure");
			}

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("nonet");
		}

		return mapping.findForward("success");

	}

	private ActionForward performResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String content = request.getParameter("content");

			ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
			ArrayList list = tawwpExecuteMgr.searchByContent(content,
					startDate, endDate);
			request.setAttribute("list", list);
			return mapping.findForward("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}

	}

	private ActionForward performCheckAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ITawwpExecuteCheckMgr tawwpExecuteMgr = (ITawwpExecuteCheckMgr) getBean("tawwpExecuteCheckMgr");
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String executeId = request.getParameter("executeId");
		String id = request.getParameter("id");
		String checkContent = request.getParameter("checkContent");

		TawwpExecuteCheck tawwpExecuteCheck = new TawwpExecuteCheck();
		tawwpExecuteCheck.setCheckContent(checkContent);
		tawwpExecuteCheck.setChecker(saveSessionBeanForm.getUsername());
		tawwpExecuteCheck.setCheckTime(StaticMethod.getCurrentDateTime());
		tawwpExecuteCheck.setExecuteId(executeId);

		if (id == null || id.equals("")) {
			tawwpExecuteMgr.saveExecuteCheck(tawwpExecuteCheck);
		} else {
			tawwpExecuteCheck.setId(id);
			tawwpExecuteMgr.updateExecuteCheck(tawwpExecuteCheck);
		}

		ActionForward actionForward = new ActionForward();
		actionForward
				.setPath("/tawwpstat/viewmonthcontent.do?executecontentid="
						+ executeId);
		return actionForward;
	}

	/**
	 * 显示作业计划执行内容整体执行情况
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
	private ActionForward performCheckView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 初始化数�?

		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// 获取页面传递过来的参数
		String executeContentId = request.getParameter("executecontentid"); // 执行作业计划执行内容编号

		try {
			// 获取VO对象
			tawwpExecuteContentVO = tawwpExecuteMgr
					.viewExecuteContent(executeContentId);

			// 为页面显示准备数�?
			request
					.setAttribute("tawwpexecutecontentvo",
							tawwpExecuteContentVO);

			// 转向执行作业计划详细信息浏览页面
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

	private ActionForward performWorkflowAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String workflowId = request.getParameter("workflowId");
		String executeId = request.getParameter("executeId");
		String workflowType = request.getParameter("executeType");
		TawwpWorkflow tawwpWorkflow = new TawwpWorkflow();
		tawwpWorkflow.setExecuteId(executeId);
		tawwpWorkflow.setFlowId(workflowId);
		tawwpWorkflow.setFlowType(workflowType);
		ITawwpWorkflowMgr tawwpWorkflowMgr = (ITawwpWorkflowMgr) getBean("tawwpWorkflowMgr");
		tawwpWorkflowMgr.saveWorkflow(tawwpWorkflow);
		tawwpWorkflowMgr.getListByExecute(executeId);
		request.getSession().getAttribute("executeId");
		return mapping.findForward("success");
	}

	private ActionForward performWorkflowList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String executeId = request.getParameter("executeId");
		List workflowList = new ArrayList();
		if (executeId != null) {

		} else if (request.getSession().getAttribute("executeId") != null) {
			executeId = (String) request.getSession().getAttribute("executeId");
		}
		if (executeId != null) {
			ITawwpWorkflowMgr tawwpWorkflowMgr = (ITawwpWorkflowMgr) getBean("tawwpWorkflowMgr");
			workflowList = tawwpWorkflowMgr.getListByExecute(executeId);
		}
		request.setAttribute("workflowList", workflowList);
		return mapping.findForward("success");
	}

	private ActionForward performFileUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub\
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) ApplicationContextHolder
				.getInstance().getBean("tawwpExecuteMgr");
		String action = request.getParameter("action");
		String resulturl = request.getParameter("resulturl");
		String name = request.getParameter("name");
		String numname = request.getParameter("numname");

		String executeContentUserIdName = request
				.getParameter("executecontentuseridname");
		String executeContentUserId = request
				.getParameter("executecontentuserid");
		String executeContentId = request.getParameter("executecontentid");
		String userId = request.getParameter("userid");
		String stubUser = request.getParameter("stubuser");
		String strShowSpanId = request.getParameter("showspanid");
		String formName = request.getParameter("formname");

		String newExecuteContentUserId = "";

		String delFile = request.getParameter("delFile");
		String myResulturl = "";
		String rootFilePath = "";
		rootFilePath = AccessoriesMgrLocator.getAccessoriesAttributes()
				.getUploadPath()
				+ "accessories/uploadfile/workplan/uploadfile/";
		try {
			resulturl = java.net.URLDecoder.decode(resulturl, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TawRmCycleTableForm tawRmCycleTableForm = (TawRmCycleTableForm) form;
		if (tawRmCycleTableForm.getThisFile() != null) {
			if (resulturl != null) {
				try {
					/*
					 * resulturl = new String(resulturl.getBytes("ISO-8859-1"),
					 * "UTF-8");
					 */
				} catch (Exception e) {

				}
			}
			// 取得所有上传的文件名称
			FormFile myFile = tawRmCycleTableForm.getThisFile();

			// 操作上传
			if (action.equals("add")) {

				// 创建一个file对象 myFile
				if (myFile != null && !myFile.getFileName().equals("")) {
					// 获得时间参数
					Date puredate = null;
					SimpleDateFormat dateFormat = null;
					String date = null;
					puredate = new Date();
					dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					date = dateFormat.format(puredate);
					/** *******************存储****************** */
					// 如果文件存在，则存储
					try {

						// edit by gongyufeng

						/*
						 * String fileName = new String(myFile.getFileName()
						 * .getBytes("ISO-8859-1"), "UTF-8");
						 */
						String fileName = myFile.getFileName();

						String fileExt = "";
						if (fileName.indexOf(".") != -1) {
							fileExt = fileName.substring(fileName.indexOf("."));
						}
						String filePath = rootFilePath + date + "_" + fileExt;

						InputStream stream;

						stream = myFile.getInputStream();
						// 把文件读�?

						OutputStream bos = new FileOutputStream(filePath); // 建立一个上传文件的输出�?

						int bytesRead = 0;
						byte[] buffer = new byte[8192];
						while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
							bos.write(buffer, 0, bytesRead); // 将文件写入服务器
						}
						bos.close();
						stream.close();

						newExecuteContentUserId = tawwpExecuteMgr.uploadFile(
								fileName, date + "_" + fileExt, String
										.valueOf(myFile.getFileSize()), userId,
								executeContentId, executeContentUserId,
								stubUser);
						String[] contentAndFileId = newExecuteContentUserId
								.split(",");
						// 可能为ExecuteContentUserIdnull
						executeContentUserId = contentAndFileId[0];
						// System.out.println("调试信息：executeContentUserId="+executeContentUserId);

						resulturl = resulturl + "," + fileName
								+ "@../tawwpexecute/filedown.do?fileId=" + date
								+ "_" + fileExt;
						myResulturl = resulturl;
						System.out.println("调试信息：resulturl=" + resulturl);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						generalError(request, e); // 将错误信息加入到消息队列、写入日�?
						ActionMessages messages = new ActionMessages();
						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("submit.title.failure"));
						saveMessages(request, messages);
						return mapping.findForward("failure"); // 转向错误页面
					} catch (IOException e) {
						// TODO Auto-generated catch block
						generalError(request, e); // 将错误信息加入到消息队列、写入日�?
						ActionMessages messages = new ActionMessages();
						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("submit.title.failure"));
						saveMessages(request, messages);
						return mapping.findForward("failure"); // 转向错误页面
					} catch (Exception e) {
						// TODO Auto-generated catch block
						generalError(request, e); // 将错误信息加入到消息队列、写入日�?
						ActionMessages messages = new ActionMessages();
						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("submit.title.failure"));
						saveMessages(request, messages);
						return mapping.findForward("failure"); // 转向错误页面
					}

				}

			}
			/** ********************删除**************** */
			if (delFile != null) {
				String[] d = delFile.split(",");
				for (int i = 0; i < d.length; i++) {
					if (!d[i].equals("")) {
						String[] delete = d[i].split("@");

						File myDelFile = new File(rootFilePath
								+ TawwpUtil.getFileNameSub(delete[1]));
						// System.out.println("调试�+TawwpUtil.getFileName(delete[1]));
						try {
							tawwpExecuteMgr.removeFile(TawwpUtil
									.getFileNameSub(delete[1]),
									executeContentId);
							myDelFile.delete();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							generalError(request, e); // 将错误信息加入到消息队列、写入日�?
							ActionMessages messages = new ActionMessages();
							messages.add(ActionMessages.GLOBAL_MESSAGE,
									new ActionMessage("submit.title.failure"));
							saveMessages(request, messages);
							return mapping.findForward("failure"); // 转向错误页面
						}

					}

				}

			}
		} else {
			List list = tawwpExecuteMgr.listExecuteFile(executeContentId);
			resulturl = "";
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					TawwpExecuteFile file = (TawwpExecuteFile) list.get(i);
					resulturl = resulturl + "," + file.getFileName()
							+ "@../tawwpexecute/filedown.do?fileId="
							+ file.getFileCodeName();
				}
			}

		}
		// 获取变量
		request.setAttribute("resulturl", resulturl);
		request.setAttribute("action", action);
		request.setAttribute("name", name);
		request.setAttribute("numname", numname);
		request.setAttribute("delFile", delFile);
		request.setAttribute("showspanid", strShowSpanId);
		request.setAttribute("executecontentuseridname",
				executeContentUserIdName);
		request.setAttribute("executecontentuserid", executeContentUserId);
		request.setAttribute("executecontentid", executeContentId);
		request.setAttribute("userid", userId);
		request.setAttribute("stubuser", stubUser);
		request.setAttribute("formname", formName);

		return mapping.findForward("success");
	}

	private ActionForward performViewALLList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		Hashtable monthPlanVOHash = null;

		String _deptId = "";
		String _yearFlag = "";
		String _monthFlag = "";

		if (request.getParameter("yearFlag") == null
				|| request.getParameter("yearFlag").equals("")) {
			_yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
		} else {
			_yearFlag = request.getParameter("yearFlag");
		}
		if (request.getParameter("monthFlag") == null
				|| request.getParameter("monthFlag").equals("")) {
			_monthFlag = TawwpUtil.getCurrentDateTime("MM");
		} else {
			_monthFlag = request.getParameter("monthFlag");
		}
		if (request.getParameter("deptId") != null
				&& !request.getParameter("deptId").equals("")) {
			_deptId = request.getParameter("deptId");
		}

		try {
			// 获取执行作业计划集合
			monthPlanVOHash = tawwpExecuteMgr.newlistALLExecutePlan(_deptId,
					_yearFlag, _monthFlag);

			// 为页面显示准备数�?

			// ------------------------------------------------
			Enumeration hashKeys = null;
			List listTemp = new ArrayList();
			List listKey = new ArrayList();
			TawwpMonthPlan tawwpMonthPlan = null;
			if (monthPlanVOHash.size() != 0) {
				hashKeys = monthPlanVOHash.keys();
				while (hashKeys.hasMoreElements()) {
					tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());

					listKey.add(tawwpMonthPlan);
					tawwpMonthPlan = null;
				}
				// Collections.sort(listKey);
				Collections.sort(listKey, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthPlan p1 = (TawwpMonthPlan) o1;
						TawwpMonthPlan p2 = (TawwpMonthPlan) o2;
						int k = -1;
						if (p1.getName().compareTo(p2.getName()) > 0) {
							k = 1;
						} else if (p1.getName().compareTo(p2.getName()) == 0) {
							if (p1.getId().compareTo(p2.getId()) > 0) {
								k = 1;
							} else {
								k = -1;
							}
						} else if (p1.getName().compareTo(p2.getName()) < 0) {
							k = -1;
						}
						return k;

					}
				});
			}

			// ----------------------------------------------------
			request.setAttribute("listKey", listKey);
			request.setAttribute("monthplanvohash", monthPlanVOHash);
			request.setAttribute("yearFlag", _yearFlag);
			request.setAttribute("monthFlag", _monthFlag);
			request.setAttribute("deptId", _deptId);
			// 转向当前用户负责的执行作业计划管�?列表)
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
	 * 显示日常执行作业计划管理(列表)首页Action
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
	private ActionForward performDailyExecuteList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String deptId = saveSessionBeanForm.getDeptid(); // 当前部门编号
		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// List stubUserList = saveSessionBeanForm.getStubUserList(); //代理信息集合
		List stubUserList = saveSessionBeanForm.getStubUserList();
		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		Hashtable monthPlanVOHash = null;
		Hashtable tempHash = null;
		Enumeration tempkeys = null;
		String monthPlanId = "";
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		List stubMonthPlanList = new ArrayList();
		String monthflag = StaticMethod.nullObject2String(request
				.getParameter("month"));
		String yearflag = StaticMethod.nullObject2String(request
				.getParameter("year"));
		String dateTime = TawwpUtil.getCurrentDateTime();
		if ("".equals(monthflag)) {
			monthflag = TawwpUtil.getCurrentDateTime("MM");

		}
		if ("".equals(yearflag)) {
			yearflag = TawwpUtil.getCurrentDateTime("yyyy");
		}
	 
		try {
			// 获取执行作业计划集合
			 
			monthPlanVOHash = tawwpExecuteMgr.listExecutePlanNew(userId, String
					.valueOf(deptId),monthflag,yearflag);
			 
			// 如果代理信息存在
			if (stubUserList != null) {

				for (int i = 0; i < stubUserList.size(); i++) {
					// 获取代理信息VO对象
					tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
					// 获取代理申请人需要执行的月度作业计划
					tempHash = new Hashtable();
					tempHash = tawwpExecuteMgr.listExecutePlan(tawwpStubUserVO
							.getCruser(), String.valueOf(deptId), monthflag,
							yearflag);
					if (tempHash.size() != 0) {
						// 取出全部月度作业计划编号
						tempkeys = tempHash.keys();

						while (tempkeys.hasMoreElements()) {
							monthPlanId = (String) tempkeys.nextElement();
							// 获取月度作业计划VO对象,并修改代理标志位
							tawwpMonthPlanVO = (TawwpMonthPlanVO) (tempHash
									.get(monthPlanId));
							tawwpMonthPlanVO.setStubFlag("1");
							tawwpMonthPlanVO.setUserByStub(tawwpStubUserVO
									.getCruser()); // 修改被代理用户名
						}
						stubMonthPlanList.add(tempHash);
						
					}
				}
			}

			// ------------------------------------------------
			 
			Enumeration hashKeys = null;
			List listTemp = new ArrayList();
			Hashtable tawwpExecuteContentHash = new Hashtable();
			List listKey = new ArrayList();
			TawwpMonthPlan tawwpMonthPlan = null;
			if (monthPlanVOHash.size() != 0) {
				hashKeys = monthPlanVOHash.keys();
				while (hashKeys.hasMoreElements()) {
					tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());
					// if(tawwpMonthPlan.getTypeIndex()==null){
					// tawwpMonthPlan.setTypeIndex("");
					// }
					int flag0 = 0;
					int flag1 = 0;
					int flag2 = 0;
					int _executeFlag = 0;
					String condition = " and tawwpexecutecontent.tawwpMonthPlan.id=:workpalnId"
							+ " and tawwpexecutecontent.startDate <=:dateTime"
							+ " and tawwpexecutecontent.endDate >=:dateTime";
					listTemp = tawwpExecuteMgr.listExecuteContent(condition,tawwpMonthPlan.getId());
					for (int i = 0; i < listTemp.size(); i++) {
						TawwpExecuteContent tec = (TawwpExecuteContent) listTemp
								.get(i);
						String temp = tec.getExecuteFlag();
						if ("0".equals(temp)) {// 未填写
							flag0++;
						} else if ("1".equals(temp)) {// 已填写
							flag1++;
						} else {// 填写中
							flag2++;
						}
					}
					if (flag0 == listTemp.size() && flag1 == 0 && flag2 == 0) {
						_executeFlag = 0;
					} else if (flag1 == listTemp.size() && flag0 == 0
							&& flag2 == 0) {
						_executeFlag = 1;
					} else {
						_executeFlag = 2;
					}
					String executeFlag = TawwpExecuteContentVO
							.getDailyExecuteFlagName(_executeFlag);
					tawwpExecuteContentHash.put(tawwpMonthPlan.getId(),
							executeFlag);
					listKey.add(tawwpMonthPlan);
					tawwpMonthPlan = null;
				}
				// Collections.sort(listKey);
			}
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(0);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(1);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(2);
			Collections.sort(listKey, new Comparator() {
				public int compare(Object o1, Object o2) {
					TawwpMonthPlan p1 = (TawwpMonthPlan) o1;
					TawwpMonthPlan p2 = (TawwpMonthPlan) o2;
					int k = -1;
					if (p1.getName().compareTo(p2.getName()) > 0) {
						k = 1;
					} else if (p1.getName().compareTo(p2.getName()) == 0) {
						if (p1.getId().compareTo(p2.getId()) > 0) {
							k = 1;
						} else {
							k = -1;
						}
					} else if (p1.getName().compareTo(p2.getName()) < 0) {
						k = -1;
					}
					return k;

				}
			});
//			Collections.sort(stubMonthPlanList, new Comparator() {
//				public int compare(Object o1, Object o2) {
//					TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
//					TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
//					int k = -1;
//					if (p1.getName().compareTo(p2.getName()) > 0) {
//						k = 1;
//					} else if (p1.getName().compareTo(p2.getName()) == 0) {
//						if (p1.getId().compareTo(p2.getId()) > 0) {
//							k = 1;
//						} else {
//							k = -1;
//						}
//					} else if (p1.getName().compareTo(p2.getName()) < 0) {
//						k = -1;
//					}
//					return k;
//
//				}
//			});
		 
			request.setAttribute("listKey", listKey);
			// ----------------------------------------------------
			request.setAttribute("tawwpExecuteContentHash",
					tawwpExecuteContentHash);
			// 为页面显示准备数�?
			request.setAttribute("year", yearflag);
			request.setAttribute("month", monthflag);
			request.setAttribute("monthplanvohash", monthPlanVOHash);
			request.setAttribute("stubmonthplanlist", stubMonthPlanList);
			// 若portlet则跳转到日常执行作业计划管理portlet页面
			if (request.getParameter("portal") != null
					&& "true".equals(request.getParameter("portal"))) {
				return mapping.findForward("portlet");
			} else {
				// 转向日常执行作业计划管理(列表)页面
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		} finally {
			tempHash = null;
			tempkeys = null;
		}
	}

	/**
	 * 显示日常执行作业计划管理(列表)首页Action
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
	private ActionForward performGXDailyExecuteList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String deptId = saveSessionBeanForm.getDeptid(); // 当前部门编号
		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// List stubUserList = saveSessionBeanForm.getStubUserList(); //代理信息集合
		List stubUserList = saveSessionBeanForm.getStubUserList();
		String monthflag = StaticMethod.nullObject2String(request
				.getParameter("month"));
		String yearflag = StaticMethod.nullObject2String(request
				.getParameter("year"));
		String dateTime = TawwpUtil.getCurrentDateTime();
		if ("".equals(monthflag)) {
			monthflag = TawwpUtil.getCurrentDateTime("MM");

		}
		if ("".equals(yearflag)) {
			yearflag = TawwpUtil.getCurrentDateTime("yyyy");
		}
		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		Hashtable monthPlanVOHash = null;
		Hashtable tempHash = null;
		Enumeration tempkeys = null;
		String monthPlanId = "";
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		List stubMonthPlanList = new ArrayList();
		boolean s = false;

		try {
			// 获取执行作业计划集合
			monthPlanVOHash = tawwpExecuteMgr.listGxExecutePlanNew(userId, String
					.valueOf(deptId), monthflag, yearflag);
			// 如果代理信息存在
			
			
			if (stubUserList != null) {  

				for (int i = 0; i < stubUserList.size(); i++) {
					// 获取代理信息VO对象
					tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
					// 获取代理申请人需要执行的月度作业计划
					tempHash = new Hashtable();
					tempHash = tawwpExecuteMgr.listSubExecutePlan(tawwpStubUserVO
							.getCruser(), String.valueOf(deptId), monthflag,
							yearflag);
					if (tempHash.size() != 0) {
						// 取出全部月度作业计划编号
						tempkeys = tempHash.keys();

						while (tempkeys.hasMoreElements()) {
							monthPlanId = (String) tempkeys.nextElement();
							// 获取月度作业计划VO对象,并修改代理标志位
							tawwpMonthPlanVO = (TawwpMonthPlanVO) (tempHash
									.get(monthPlanId));
							for (int l = 0; l < stubMonthPlanList.size(); l++) {
								Hashtable tempHash1 = (Hashtable) stubMonthPlanList
										.get(l);
								Enumeration hashKeys1 = tempHash1.keys();
								while (hashKeys1.hasMoreElements()) {
									String monthPlanId1 = (String) (hashKeys1
											.nextElement());
									TawwpMonthPlanVO tawwpMonthPlanVO1 = (TawwpMonthPlanVO) tempHash1
											.get(monthPlanId1);
									if (tawwpMonthPlanVO.getName().equals(
											tawwpMonthPlanVO1.getName())) {
										s = true;
									}
								}
							}
							tawwpMonthPlanVO.setStubFlag("1");
							tawwpMonthPlanVO.setUserByStub(tawwpStubUserVO
									.getCruser()); // 修改被代理用户名
						}
						if (s) {
							s = false;
							continue;
						}
						stubMonthPlanList.add(tempHash);
					}
				}
			}

			// ------------------------------------------------
			Enumeration hashKeys = null;
			List listKey = new ArrayList();
			TawwpMonthPlan tawwpMonthPlan = null;
			if (monthPlanVOHash.size() != 0) {
				hashKeys = monthPlanVOHash.keys();
				while (hashKeys.hasMoreElements()) {
					tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());
					for (int j = 0; j < listKey.size(); j++) {
						TawwpMonthPlan tempMonthPlan = (TawwpMonthPlan) listKey
								.get(j);
						if (tawwpMonthPlan.getName().equals(
								tempMonthPlan.getName())) {
							s = true;
						}
					}
					if (s) {
						s = false;
						continue;
					}
					listKey.add(tawwpMonthPlan);
					Collections.sort(listKey, new Comparator() {
						public int compare(Object o1, Object o2) {
							TawwpMonthPlan p1 = (TawwpMonthPlan) o1;
							TawwpMonthPlan p2 = (TawwpMonthPlan) o2;
							int k = -1;
							if (p1.getName().compareTo(p2.getName()) > 0) {
								k = 1;
							} else if (p1.getName().compareTo(p2.getName()) == 0) {
								if (p1.getId().compareTo(p2.getId()) > 0) {
									k = 1;
								} else {
									k = -1;
								}
							} else if (p1.getName().compareTo(p2.getName()) < 0) {
								k = -1;
							}
							return k;

						}
					});
					tawwpMonthPlan = null;
				}
			}
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(0);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(1);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(2);
			request.setAttribute("listKey", listKey);
			// ----------------------------------------------------

			// 为页面显示准备数�?
			request.setAttribute("year", yearflag);
			request.setAttribute("month", monthflag);
			request.setAttribute("monthplanvohash", monthPlanVOHash);
			request.setAttribute("stubmonthplanlist", stubMonthPlanList);
			// 若portlet则跳转到日常执行作业计划管理portlet页面
			if (request.getParameter("portal") != null
					&& "true".equals(request.getParameter("portal"))) {
				return mapping.findForward("portlet");
			} else {
				// 转向日常执行作业计划管理(列表)页面
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		} finally {
			tempHash = null;
			tempkeys = null;
		}
	}

	/**
	 * 显示值班执行作业计划管理(列表)首页Action
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
	private ActionForward performDutyExecuteList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String roomId = saveSessionBeanForm.getRoomId(); // 当前机房编号
		// String dutyDate =
		// saveSessionBeanForm.getStartDuty()+"-->"+saveSessionBeanForm.getEndDuty();
		// //值班的时间段

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List monthPlanVOList = null;
		String dutyStartDate = "";
		String dutyEndDate = "";

		// 获取当前年、月
		String yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
		String monthFlag = TawwpUtil.getCurrentDateTime("MM");

		try {

			// 判断值班状�?
			if (saveSessionBeanForm.getStartDuty() != null
					&& saveSessionBeanForm.getEndDuty() != null) {
				dutyStartDate = StaticMethod.getCurrentDateTime(
						saveSessionBeanForm.getStartDuty(),
						"yyyy-MM-dd HH:mm:ss")
						+ "";
				dutyEndDate = StaticMethod
						.getCurrentDateTime(saveSessionBeanForm.getEndDuty(),
								"yyyy-MM-dd HH:mm:ss")
						+ "";

			} else {
				// 如果当前用户未处于值班状�?
				request.setAttribute("error", "noduty");
				// 转向提示页面
				return mapping.findForward("failure");

			}

			// 获取执行作业计划集合
			monthPlanVOList = tawwpExecuteMgr.listDutyExecutePlan(String
					.valueOf(roomId), yearFlag, monthFlag, dutyStartDate,
					dutyEndDate);

			// 为页面显示准备数�?
			request.setAttribute("monthplanvolist", monthPlanVOList);
			// 转向值班执行作业计划管理(列表)页面
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 显示批量填写执行作业计划执行内容首页Action
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
	private ActionForward performExecuteAdds(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?
		String date = TawwpUtil.getRequestValue(request, "date", ""); // 批量填写日期

		// 如果取得的日期小�?0且没有经过补0处理,则在日期前加0
		if (date.length() == 1) {
			date = "0" + date;
		}

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO();
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List executeContentVOList = null;

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";
		String stubFlag = "";
		String userId = "";
		String deptId = "";
		String roomId = "";

		try {

			// 设定代理标志
			if (!"".equals(userByStub)) {
				stubFlag = "stub";
				userId = userByStub;
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(userByStub);
				deptId = String.valueOf(userTemp.getDeptid());
			} else {
				stubFlag = "own";
				userId = currUser; // 当前用户�?
				deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
				roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
			}

			// 如果为值班批量填写,获取班次开始、结束时�?
			if ("duty".equals(flag)) {

				dutyStartDate = saveSessionBeanForm.getStartDuty() + ""; // 班次开始时�?
				dutyEndDate = saveSessionBeanForm.getEndDuty() + ""; // 班次结束时间
			}

			// 获取执行作业计划执行内容(整体)VO对象集合

			executeContentVOList = tawwpExecuteMgr.addExecuteContentUsersView(
					userId, deptId, roomId, monthPlanId, dutyStartDate,
					dutyEndDate, flag, stubFlag, date);

			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(userId, "addExecuteContentUsersView", "");
			if (executeContentVOList == null
					|| executeContentVOList.size() == 0) {

				// 如果返回结果集为�?
				request.setAttribute("error", "noexecute");
				// 转向提示页面
				if ("duty".equals(flag)) {
					request.setAttribute("error", "noduty");
				}

				return mapping.findForward("failure");
			}

			// 为页面显示准备数�?
			request.setAttribute("executecontentvolist", executeContentVOList);
			request.setAttribute("flag", flag);
			request.setAttribute("curruser", currUser);
			request.setAttribute("monthplanid", monthPlanId);
			request.setAttribute("userbystub", userByStub);
			request.setAttribute("date", date);
			// 转向批量填写执行内容页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			if ("duty".equals(flag)) {
				request.setAttribute("error", "noduty");
			}
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 批量填写执行作业计划执行内容保存Action
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
	private ActionForward performExecuteSaves(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符�?
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String executeType = request.getParameter("executetype"); // 执行类型

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";

		String content = "";
		String formDataId = "";
		String fileName = "";
		String remark = "";
		String cruser = "";
		String stubUser = "";
		String reason = "";

		String contentUserId = "";

		String writeDate = "";
		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 处理执行作业计划执行内容(整体)编号字符�?
		String[] tempStr = contentIdStr.split(",");

		try {
			// 如果为值班批量填写,获取班次开始、结束时�?
			if ("duty".equals(flag)) {

				dutyStartDate = saveSessionBeanForm.getStartDuty() + ""; // 班次开始时�?
				dutyEndDate = saveSessionBeanForm.getEndDuty() + ""; // 班次结束时间
			}

			for (int i = 0; i < tempStr.length; i++) {

				// 获取从页面传递过来的参数
				remark = request.getParameter("remark" + i);
				content = request.getParameter("content" + i);
				formDataId = request.getParameter("formdataid" + i);
				fileName = request.getParameter("filename" + i);
				contentUserId = request
						.getParameter("executecontentuserid" + i);
				cruser = request.getParameter("userid" + i);
				stubUser = request.getParameter("stubuser" + i);
				writeDate = request.getParameter("writeDate" + i);
				reason = request.getParameter("reason" + i);
				String normalFlag = request.getParameter("normalFlag" + i);
				if (normalFlag == null || "".equals(normalFlag)) {
					normalFlag = "1";
				}
				if ((content == null || "".equals(content))
						&& (formDataId == null || "".equals(formDataId))
						&& (fileName == null || "".equals(fileName))
						&& (contentUserId == null || "".equals(contentUserId))) {
					// 都没填就不添加到Hashtable�?
				} else {

					tawwpExecuteContentUser = new TawwpExecuteContentUser();
					tawwpExecuteContentUser.setContent(content); // 执行内容
					tawwpExecuteContentUser.setFormDataId(formDataId); // 附加表单记录编号
					tawwpExecuteContentUser.setId(contentUserId); // 执行内容(单一用户)编号
					tawwpExecuteContentUser.setCruser(cruser);
					tawwpExecuteContentUser.setCrtime(TawwpUtil
							.getCurrentDateTime());
					tawwpExecuteContentUser.setWriteDate(writeDate);
					tawwpExecuteContentUser.setStubUser(stubUser);
					tawwpExecuteContentUser.setNormalFlag(normalFlag);
					tawwpExecuteContentUser.setRemark(remark);
					tawwpExecuteContentUser.setReason(reason);
					// 将执行作业计划执行内�?单一用户)对象添加到Hashtable�?
					executeContentUserHash.put(tempStr[i],
							tawwpExecuteContentUser);

				}
			}

			if ("duty".equals(flag)) {
				// 保存值班批量填写内容
				tawwpExecuteMgr.addDutyExecuteContentUsersSave(
						executeContentUserHash, dutyStartDate, dutyEndDate,
						userId, executeType);
				// 日志
				ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
				tawwpLogMgr
						.addLog(cruser, "addDutyExecuteContentUsersSave", "");

				// 转向值班执行作业计划列表管理页面
				return mapping.findForward("success_duty");
			} else {
				ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
				// 保存日常批量填写内容
				// tawwpExecuteMgr.addExecuteContentUsersSave(
				// executeContentUserHash, userId, executeType);

				mgr.addExecuteContentUsersSave(executeContentUserHash, userId,
						executeType);

				// 转向日常执行作业计划列表管理页面
				return mapping.findForward("success_daily");
			}
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
	 * 浏览执行作业计划管理页面Action
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
	private ActionForward performExecuteView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO();
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		String stubFlag = "";
		String userId = "";
		String deptId = "";
		String roomId = "";

		// 获取页面传递过来的参数
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?

		try {

			// 设定代理标志
			/*
			 * if (!"".equals(userByStub)) { stubFlag = "stub"; userId =
			 * userByStub; TawSystemUser userTemp = tawRmUserDAO
			 * .getUserByuserid(userByStub); deptId =
			 * String.valueOf(userTemp.getDeptid()); // deptId = String.valueOf( //
			 * (tawRmUserDAO.retrieve(userByStub)).getDeptId()); } else {
			 * stubFlag = "own"; userId = currUser; // 当前用户�? deptId =
			 * String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门 roomId =
			 * String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号 }
			 */
			long t1 = System.currentTimeMillis();

			// 获取VO对象
			tawwpMonthPlanVO = tawwpExecuteMgr.viewExecutePlan(monthPlanId);
			// tawwpMonthPlanVO =
			// tawwpExecuteMgr.viewExecutePlanExecuter(userId,
			// deptId, roomId, monthPlanId, stubFlag);
			long t2 = System.currentTimeMillis();
			System.out.println(t2 - t1);
			// 为页面显示准备数�?
			request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);
			request.setAttribute("curruser", currUser);

			// 转向执行作业计划详细信息浏览页面
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
	 * 显示作业计划执行内容整体执行情况
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
	private ActionForward performContentView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// 获取页面传递过来的参数
		String executeContentId = request.getParameter("executecontentid"); // 执行作业计划执行内容编号

		try {
			// 获取VO对象
			tawwpExecuteContentVO = tawwpExecuteMgr
					.viewExecuteContent(executeContentId);

			// 为页面显示准备数�?
			request
					.setAttribute("tawwpexecutecontentvo",
							tawwpExecuteContentVO);

			// 转向执行作业计划详细信息浏览页面
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
	 * 显示周、月报添加页面Action
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
	private ActionForward performReportAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String yearFlag = request.getParameter("yearflag"); // 计划年度
		String monthFlag = request.getParameter("monthflag"); // 计划月度

		int dayCount = 0;

		try {

			// 获取该月天数
			dayCount = TawwpUtil.getDayCountOfMonth(yearFlag, monthFlag);

			// 为页面显示准备数�?
			request.setAttribute("monthplanid", monthPlanId);
			request.setAttribute("yearflag", yearFlag);
			request.setAttribute("monthflag", monthFlag);
			request.setAttribute("daycount", String.valueOf(dayCount));

			// 转向周、月报添加页�?
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
	 * 周、月报填写后保存Action
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
	private ActionForward performReportSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String deptId = saveSessionBeanForm.getDeptid(); // 当前部门编号
		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面提交的参�?
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String startDate = request.getParameter("startdate"); // 开始时�?
		String endDate = request.getParameter("enddate"); // 结束时间
		String reportType = request.getParameter("reporttype"); // 汇报类型(周、月)
		String reportFlag = request.getParameter("reportflag"); // 是否需要注�?
		String content = request.getParameter("content"); // 汇报内容
		String remark = request.getParameter("remark"); // 备注信息
		String advice = request.getParameter("advice"); // 建议

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		try {
			// 周报
			if ("week".equals(reportType)) {
				tawwpExecuteMgr.addExecuteWeekReport(String.valueOf(deptId),
						startDate, endDate, content, userId, reportFlag,
						remark, advice, monthPlanId);
				// 日志
				ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
				tawwpLogMgr.addLog(userId, "addExecuteMonthReport", "");

			}
			// 月报
			else if ("month".equals(reportType)) {
				tawwpExecuteMgr.addExecuteMonthReport(String.valueOf(deptId),
						startDate, endDate, content, userId, reportFlag,
						remark, advice, monthPlanId);
				// 日志
				ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
				tawwpLogMgr.addLog(userId, "addExecuteMonthReport", "");

			}
			// 转向执行作业计划浏览页面
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
	 * 编辑周、月报页面Action
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
	private ActionForward performReportEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpExecuteReportVO tawwpExecuteReportVO = null;

		// 获取页面传递过来的参数
		String executeReportId = request.getParameter("executereportid"); // 周月报编�?
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划

		try {

			// 获取VO对象
			tawwpExecuteReportVO = tawwpExecuteMgr
					.editExecuteReportView(executeReportId);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			// 获取当前用户的session中的信息
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");

			String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
			tawwpLogMgr.addLog(userId, "editExecuteReportView", "");

			// 为页面显示准备数�?
			request.setAttribute("tawwpexecutereportvo", tawwpExecuteReportVO);
			request.setAttribute("monthplanid", monthPlanId);

			// 转向周、月报编辑页�?
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
	 * 周、月报编辑后保存Action
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
	private ActionForward performReportEditSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取页面提交的参�?
		String executeReportId = request.getParameter("executereportid"); // 周月报编�?
		String reportFlag = request.getParameter("reportflag"); // 是否需要注�?
		String content = request.getParameter("content"); // 汇报内容
		String remark = request.getParameter("remark"); // 备注信息
		String advice = request.getParameter("advice"); // 建议

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		try {
			// 更新周、月报对�?
			tawwpExecuteMgr.editExecuteReportSave(content, reportFlag, remark,
					advice, executeReportId);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			// 获取当前用户的session中的信息
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
			tawwpLogMgr.addLog(userId, "editExecuteReportSave", "");

			// 转向执行作业计划浏览页面
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
	 * 执行作业计划归档Action
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
	private ActionForward performExecuteRefer0926(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		// 获取页面传递的参数
		String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
				"");
		String flowSerial = TawwpUtil
				.getRequestValue(request, "flowserial", "");
		String stepSerial = TawwpUtil
				.getRequestValue(request, "stepserial", "");
		String checkDept = String.valueOf(saveSessionBeanForm.getDeptid());
		String userId = saveSessionBeanForm.getUserid();
		String content = TawwpUtil.getRequestValue(request, "content", "同意归档");

		// 初始化数
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		try {
			content = java.net.URLDecoder.decode(content, "UTF-8");
			// 执行作业计划提交考核
			if (!tawwpExecuteMgr.addExecuteAssess(checkDept, userId, content,
					monthPlanId, flowSerial, stepSerial)) {
				return mapping.findForward("fault"); // 转向错误页面
			}
			// 转向执行作业计划浏览页面
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
	 * 执行作业计划提交考核Action
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
	private ActionForward performExecuteRefer(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// 获取页面传递的参数
		String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
				"");
		String flowSerial = TawwpUtil
				.getRequestValue(request, "flowserial", "");
		String stepSerial = TawwpUtil
				.getRequestValue(request, "stepserial", "");
		String checkDept = String.valueOf(saveSessionBeanForm.getDeptid());
		String checkUser = TawwpUtil.getRequestValue(request, "checkuser", "");

		// 初始化数??
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		try {
			// 执行作业计划提交考核
			if (!tawwpExecuteMgr.addExecuteAssess(checkDept, checkUser,
					monthPlanId, flowSerial, stepSerial)) {
				return mapping.findForward("fault"); // 转向错误页面
			}
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(checkUser, "addExecuteAssess", "");

			// 转向执行作业计划浏览页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日??
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}
	/**
	 * 执行作业计划提交考核Action
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
	 * 
	 * private ActionForward performExecuteRefer(ActionMapping mapping,
	 * ActionForm actionForm, HttpServletRequest request, HttpServletResponse
	 * response) { // 获取当前用户的session中的信息 TawSystemSessionForm
	 * saveSessionBeanForm = (TawSystemSessionForm) request
	 * .getSession().getAttribute("sessionform");
	 *  // 获取页面传递的参数 String monthPlanId = TawwpUtil.getRequestValue(request,
	 * "monthplanid", ""); String flowSerial = TawwpUtil
	 * .getRequestValue(request, "flowserial", ""); String stepSerial =
	 * TawwpUtil .getRequestValue(request, "stepserial", ""); String checkDept =
	 * String.valueOf(saveSessionBeanForm.getDeptid()); String checkUser =
	 * TawwpUtil.getRequestValue(request, "checkuser", "");
	 *  // 初始化数�? ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr)
	 * getBean("tawwpExecuteMgr");
	 * 
	 * try { // 执行作业计划提交考核 if (!tawwpExecuteMgr.addExecuteAssess(checkDept,
	 * checkUser, monthPlanId, flowSerial, stepSerial)) { return
	 * mapping.findForward("fault"); // 转向错误页面 } // 日志 ITawwpLogMgr tawwpLogMgr =
	 * (ITawwpLogMgr) getBean("tawwpLogMgr"); tawwpLogMgr.addLog(checkUser,
	 * "addExecuteAssess", "");
	 *  // 转向执行作业计划浏览页面 return mapping.findForward("success"); } catch
	 * (Exception e) { generalError(request, e); // 将错误信息加入到消息队列、写入日�?
	 * ActionMessages messages = new ActionMessages();
	 * messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
	 * "submit.title.failure")); saveMessages(request, messages); return
	 * mapping.findForward("failure"); // 转向错误页面 } }
	 */

	/**
	 * 显示待考核执行作业计划管理(列表)Action
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
	private ActionForward performAssessList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = saveSessionBeanForm.getUserid(); // 当前用户

		// 权限验证
		/*
		 * try { TawSystemAssignBo tawValidatePrivBO
		 * =TawSystemAssignBo.getInstance(); //TawValidatePrivBO
		 * tawValidatePrivBO = new TawValidatePrivBO(); if
		 * (tawValidatePrivBO.hasPermission4region(userId,"") == false) {
		 * //OPE_EXECUTE_CHECK return mapping.findForward("nopriv"); //转向无权限页�?} }
		 * catch (Exception e) { e.printStackTrace(); return
		 * mapping.findForward("failure"); //异常页面 }
		 */

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List list = null;

		try {
			// 获取月度作业计划VO对象集合
			list = tawwpExecuteMgr.listExecuteAssess(userId);

			// 为页面显示准备数�?
			request.setAttribute("monthplanvolist", list);

			// 转向待考核执行作业计划管理(列表)页面
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
	 * 显示执行作业计划考核页面Action
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
	private ActionForward performAssessView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取月度作业计划编号
		String monthPlanId = TawwpUtil.getRequestValue(request, "monthplanid",
				"");

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {

			// 浏览执行作业计划
			tawwpMonthPlanVO = tawwpExecuteMgr
					.viewExecutePlanByCheck(monthPlanId);

			// 为页面显示准备数�?
			request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);

			// 转向执行作业计划考核页面
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
	 * 执行作业计划考核_通过Action
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
	private ActionForward performAssessPass(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户登录�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		// 获取执行作业计划考核信息编号
		String executeAssessId = TawwpUtil.getRequestValue(request,
				"executeassessid", "");

		// 获取考核信息(意见)
		String content = TawwpUtil.getRequestValue(request, "content", "");

		// 获取下一级考核人用户名
		String nextCheckUser = TawwpUtil.getRequestValue(request, "checkuser",
				"");

		try {

			// 通过执行作业计划考核
			tawwpExecuteMgr.passExecuteAssess(executeAssessId, nextCheckUser,
					content, userId);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(userId, "passExecuteAssess", "");
			// 转向待考核执行作业计划管理(列表)页面
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
	 * 执行作业计划考核_驳回Action
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
	private ActionForward performAssessReject(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户登录�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		// 获取执行作业计划考核信息编号
		String executeAssessId = TawwpUtil.getRequestValue(request,
				"executeassessid", "");

		// 获取考核信息(意见)
		String content = TawwpUtil.getRequestValue(request, "content", "");

		try {

			// 驳回月度作业计划审批
			tawwpExecuteMgr.rejectExecuteAssess(executeAssessId, content,
					userId);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(userId, "rejectExecuteAssess", "");

			// 转向待考核执行作业计划管理(列表)页面
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
	 * 显示同组执行页面Action
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
	private ActionForward performSameView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户登录�?

		// 获取页面传递的参数
		String executeContentId = TawwpUtil.getRequestValue(request,
				"executecontentid", ""); // 执行作业计划执行内容(整体)编号

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List executeContentUserVOList = null;

		try {
			// 获取执行作业计划执行内容(单一用户)集合
			executeContentUserVOList = tawwpExecuteMgr.ShowSameExecute(
					executeContentId, userId);

			// 为页面显示准备数�?
			request.setAttribute("executecontentuservolist",
					executeContentUserVOList);

			// 转向查看同组执行页面
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

	private ActionForward performSameView1(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户登录�?

		// 获取页面传递的参数
		String executeContentId = TawwpUtil.getRequestValue(request,
				"executecontentid", ""); // 执行作业计划执行内容(整体)编号

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		List executeContentUserVOList = null;
		List executeContentUser = new ArrayList();
		TawwpExecuteContentUserVO tawwpExecuteContentUserVO = null;
		// TawwpExecuteContentDAO tawwpExecuteContentDAO = new
		// TawwpExecuteContentDAO();
		try {
			// 获取执行作业计划执行内容(单一用户)集合
			String[] executeContentIdArr = null;
			// String[] namecc=null;
			if (!executeContentId.equals("") && executeContentId != null) {
				executeContentIdArr = executeContentId.split(",");
			}

			for (int i = 0; i < executeContentIdArr.length; i++) {
				executeContentUserVOList = tawwpExecuteMgr.ShowSameExecute(
						executeContentIdArr[i], userId);
				List names = tawwpExecuteMgr.getContent(executeContentIdArr[i]);
				for (int j = 0; j < executeContentUserVOList.size(); j++) {
					tawwpExecuteContentUserVO = (TawwpExecuteContentUserVO) executeContentUserVOList
							.get(j);
					tawwpExecuteContentUserVO.setName(new String(names.get(0)
							.toString().getBytes("819"), "gb2312"));
					executeContentUser.add(tawwpExecuteContentUserVO);
				}

				executeContentUserVOList = null;
			}
			// executeContentUserVOList = tawwpExecuteMgr.ShowSameExecute(
			// executeContentId, userId);

			// 为页面显示准备数�?
			request
					.setAttribute("executecontentuservolist",
							executeContentUser);

			// 转向查看同组执行页面
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
	 * 显示执行内容添加页面Action
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
	private ActionForward performExecuteAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// 获取页面传递过来的参数
		String executeContentId = request.getParameter("executecontentid");
		String monthPlanId = request.getParameter("monthplanid");
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?

		try {

			// 获取VO对象
			tawwpExecuteContentVO = tawwpExecuteMgr
					.addExecuteContentUserView(executeContentId);
			// 补填作业计划？
			String reason = tawwpExecuteContentVO.getReason();
			if (reason == null || "".equals(reason)) {
				tawwpExecuteContentVO.setReason("");
			}
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(currUser, "addExecuteContentUserView", "");
			// 为页面显示准备数�?
			request
					.setAttribute("tawwpexecutecontentvo",
							tawwpExecuteContentVO);
			request.setAttribute("monthplanid", monthPlanId);
			request.setAttribute("curruser", currUser);
			request.setAttribute("userbystub", userByStub);

			// 转向执行作业计划执行内容添加页面
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
	 * 显示执行内容编辑页面Action
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
	private ActionForward performExecuteEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户登录�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpExecuteContentVO tawwpExecuteContentVO = null;

		// 获取页面传递过来的参数
		String executeContentId = request.getParameter("executecontentid"); // 执行作业计划执行内容(整体)编号
		String executeType = request.getParameter("executetype");
		String monthPlanId = request.getParameter("monthplanid");
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?
		String userId = "";
		if (!"".equals(userByStub)) {
			userId = userByStub;
		} else {
			userId = currUser;
		}

		try {

			// 获取VO对象
			tawwpExecuteContentVO = tawwpExecuteMgr.editExecuteContentUserView(
					executeContentId, userId, executeType);
			String reason = tawwpExecuteContentVO.getReason();
			if (reason == null || "".equals(reason)) {
				tawwpExecuteContentVO.setReason("");
			}
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(userId, "editExecuteContentUserView", "");

			// 为页面显示准备数�?
			request
					.setAttribute("tawwpexecutecontentvo",
							tawwpExecuteContentVO);
			request.setAttribute("monthplanid", monthPlanId);
			request.setAttribute("curruser", currUser);
			request.setAttribute("userbystub", userByStub);

			// 转向执行作业计划执行内容编辑页面
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
	 * 执行作业计划执行内容添加保存Action
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
	private ActionForward performExecuteSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		// 获取页面提交的参�?
		String remark = request.getParameter("remark"); // 备注信息
		String formDataId = request.getParameter("formdataid"); // 附加表单记录
		String eligibility = request.getParameter("eligibility"); // 合格标志
		String stubUser = TawwpUtil.getRequestValue(request, "stubuser", ""); // 代理�?
		String executeContentUserId = request
				.getParameter("executecontentuserid"); // 执行作业计划执行内容(单一用户)编号
		String executeContentId = request.getParameter("executecontentid"); // 执行作业计划执行内容(整体)编号
		String content = request.getParameter("content"); // 执行内容
		String cruser = TawwpUtil.getRequestValue(request, "cruser", ""); // 执行�?
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String writeDate = request.getParameter("writeDate");
		String reason = request.getParameter("reason");
		String normalFlag = request.getParameter("normalFlag"); // 添加针对网元的正常与否
		if (normalFlag == null || "".equals(normalFlag)) { // 如果省市不需要次功能。可以屏蔽掉
			normalFlag = "1";
		}
		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ActionForward actionForward = null;

		try {
			String _normalFlagName = "";

			_normalFlagName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util
									.constituteDictId("dict-workplan",
											"normalFlag"), normalFlag);
			content = content + "  " + saveSessionBeanForm.getUsername() + " "
					+ _normalFlagName;
			// 保存执行作业计划执行内容
			tawwpExecuteMgr.addExecuteContentUserSaveAs(cruser, stubUser,
					writeDate, content, remark, formDataId, eligibility,
					executeContentUserId, executeContentId, normalFlag, reason);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(cruser, "addExecuteContentUserSave", "");

			if (!"".equals(stubUser)) {
				// 转向执行作业计划浏览页面
				actionForward = new ActionForward(
						"/tawwpexecute/executeview.do?monthplanid="
								+ monthPlanId + "&userbystub=" + cruser);
				actionForward.setRedirect(true);
				return actionForward;
			}

			// 转向执行作业计划浏览页面
			actionForward = new ActionForward(
					"/tawwpexecute/executeview.do?monthplanid=" + monthPlanId);
			return actionForward;
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	private ActionForward performExecuteInterfaceSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取页面提交的参�?
		String remark = request.getParameter("remark"); // 备注信息
		String formDataId = request.getParameter("formdataid"); // 附加表单记录
		String eligibility = request.getParameter("eligibility"); // 合格标志
		String stubUser = TawwpUtil.getRequestValue(request, "stubuser", ""); // 代理�?
		String executeContentUserId = request
				.getParameter("executecontentuserid"); // 执行作业计划执行内容(单一用户)编号
		String executeContentId = request.getParameter("executecontentid"); // 执行作业计划执行内容(整体)编号
		String content = request.getParameter("content"); // 执行内容
		String cruser = TawwpUtil.getRequestValue(request, "cruser", ""); // 执行�?
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String writeDate = request.getParameter("writeDate");
		String reason = request.getParameter("reason");
		String repReason = TawwpUtil.getRequestValue(request, "repReason", ""); // 月度作业计划编号
		String executeFlag2 = TawwpUtil.getRequestValue(request,
				"executeFlag2", ""); // 月度作业计划编号
		String commond = request.getParameter("commondId");

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ActionForward actionForward = null;

		try {

			// 保存执行作业计划执行内容
			/*
			 * tawwpExecuteMgr.addExecuteContentUserSave(cruser, stubUser,
			 * remark, formDataId, eligibility, executeContentUserId,
			 * executeContentId,repReason,executeFlag2);
			 */

			tawwpExecuteMgr.addExecuteContentUserSaveByCommond(cruser,
					stubUser, remark, formDataId, eligibility,
					executeContentUserId, executeContentId, reason, commond);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(cruser, "addExecuteContentUserSave", "");

			if (!"".equals(stubUser)) {
				// 转向执行作业计划浏览页面
				actionForward = new ActionForward(
						"/tawwpexecute/executeview.do?monthplanid="
								+ monthPlanId + "&userbystub=" + cruser);
				actionForward.setRedirect(true);
				return actionForward;
			}

			// 转向执行作业计划浏览页面
			actionForward = new ActionForward(
					"/tawwpexecute/executeview.do?monthplanid=" + monthPlanId);
			return actionForward;
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
	 * 执行作业计划执行内容编辑保存Action
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
	private ActionForward performExecuteEditSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面提交的参�?
		String remark = request.getParameter("remark"); // 备注信息
		String formDataId = request.getParameter("formdataid"); // 附加表单记录
		String eligibility = request.getParameter("eligibility"); // 合格标志
		String stubUser = TawwpUtil.getRequestValue(request, "stubuser", ""); // 代理�?
		String executeContentUserId = request
				.getParameter("executecontentuserid"); // 执行作业计划执行内容(单一用户)编号
		String content = request.getParameter("content"); // 执行内容
		String cruser = TawwpUtil.getRequestValue(request, "cruser", ""); // 执行�?
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		String writeDate = request.getParameter("writeDate");
		String normalFlag = request.getParameter("normalFlag"); // 正常非正常
		String reason = request.getParameter("reason");
		if (normalFlag == null || "".equals(normalFlag)) {
			normalFlag = "1";
		}
		ActionForward actionForward = null;

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		try {

			// 更新执行作业计划执行内容
			tawwpExecuteMgr.editExecuteContentUserSave(cruser, stubUser,
					writeDate, content, remark, formDataId, eligibility,
					executeContentUserId, normalFlag, reason);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			tawwpLogMgr.addLog(userId, "editExecuteContentUserSave", "");

			if (!"".equals(stubUser)) {
				// 转向执行作业计划浏览页面
				actionForward = new ActionForward(
						"/tawwpexecute/executeview.do?monthplanid="
								+ monthPlanId + "&userbystub=" + cruser);
				actionForward.setRedirect(true);
				return actionForward;
			}

			// 转向执行作业计划浏览页面
			actionForward = new ActionForward(
					"/tawwpexecute/executeview.do?monthplanid=" + monthPlanId);
			return actionForward;
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
	 * 负责人浏览执行作业计划详细信息Action
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
	private ActionForward performViewAll(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		// 获取页面传递过来的参数
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号

		try {

			// 获取VO对象
			tawwpMonthPlanVO = tawwpExecuteMgr
					.viewExecutePlanCheck(monthPlanId);

			// 为页面显示准备数�?
			request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);
			request.setAttribute("userid", userId);

			// 转向执行作业计划详细信息浏览页面
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
	 * 显示用户执行数量Action
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
	private ActionForward performCountUser(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门编号
		String roomId=saveSessionBeanForm.getRoomId();

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		Hashtable hashtable = null;
		List monthList = new ArrayList();
		try {
			hashtable = tawwpExecuteMgr.countExecute(userId, deptId,roomId);
			monthList = tawwpExecuteMgr.listMonthPlan(userId, deptId);

			// 为页面显示准备数�?
			request.setAttribute("count", hashtable);
			request.getSession().setAttribute("monthList", monthList);
			// 转向执行作业计划详细信息浏览页面
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
	 * 显示当前用户负责的执行作业计划管�?列表)首页Action
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
	private ActionForward performViewList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		Hashtable monthPlanVOHash = null;

		try {
			// 获取执行作业计划集合
			monthPlanVOHash = tawwpExecuteMgr.newlistExecutePlan(userId);

			// 为页面显示准备数�?

			// ------------------------------------------------
			Enumeration hashKeys = null;
			List listTemp = new ArrayList();
			List listKey = new ArrayList();
			TawwpMonthPlan tawwpMonthPlan = null;
			if (monthPlanVOHash.size() != 0) {
				hashKeys = monthPlanVOHash.keys();
				while (hashKeys.hasMoreElements()) {
					tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());
					// if(tawwpMonthPlan.getTypeIndex()==null){
					// tawwpMonthPlan.setTypeIndex("");
					// }

					listKey.add(tawwpMonthPlan);
					tawwpMonthPlan = null;
				}
				// Collections.sort(listKey);
				// Collections.sort(listKey, new Comparator() {
				// public int compare(Object o1, Object o2) {
				// TawwpMonthPlan p1 = (TawwpMonthPlan) o1;
				// TawwpMonthPlan p2 = (TawwpMonthPlan) o2;
				// int k = -1;
				// if(p1.getCrtime().compareTo(p2.getCrtime())>0){
				// k = 1;
				// }else if(p1.getCrtime().compareTo(p2.getCrtime())==0){
				// if (p1.getId().compareTo(p2.getId()) > 0) {
				// k = 1;
				// }else{
				// k = -1;
				// }
				// }else
				// if(p1.getCrtime().compareTo(p2.getCrtime())<0){
				// k = -1;
				// }
				// return k;
				//
				// }
				// });
				Collections.sort(listKey, new Comparator() {
					public int compare(Object o1, Object o2) {
						TawwpMonthPlan p1 = (TawwpMonthPlan) o1;
						TawwpMonthPlan p2 = (TawwpMonthPlan) o2;
						int k = -1;
						if (p1.getName().compareTo(p2.getName()) > 0) {
							k = 1;
						} else if (p1.getName().compareTo(p2.getName()) == 0) {
							if (p1.getCrtime().compareTo(p2.getCrtime()) > 0) {
								k = 1;
							} else {
								k = -1;
							}
						} else if (p1.getName().compareTo(p2.getName()) < 0) {
							k = -1;
						}
						return k;

					}
				});
			}
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(0);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(1);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(2);

			// ----------------------------------------------------
			request.setAttribute("listKey", listKey);
			request.setAttribute("monthplanvohash", monthPlanVOHash);
			// 转向当前用户负责的执行作业计划管�?列表)
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
	 * added by lijia 2005-11-28 确认执行作业计划Action
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
	private ActionForward performConfirm(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取页面提交的参�?
		String monthExecuteUserId = request.getParameter("monthexecuteuserid"); // 月度作业计划执行人信�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		try {

			tawwpExecuteMgr.confirm(monthExecuteUserId);
			// 日志
			ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
			// 获取当前用户的session中的信息
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
			tawwpLogMgr.addLog(userId, "confirm", "");

			// 转向执行作业计划浏览页面
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
	 * 将错误信息加入到消息队列、写入日�?
	 * 
	 * @param request
	 *            HttpServletRequest 要进行处理的request
	 * @param e
	 *            Exception 异常
	 */
	private void generalError(HttpServletRequest request, Exception e) {
		ActionMessages aes = new ActionMessages();
		aes.add("EOMS_WORKPLAN_ERROR", new ActionMessage("error.general", e
				.getMessage())); // 将错误信息加入到消息队列�?
		saveMessages(request, aes); // 保存消息队列
		BocoLog.error(this, 2, "[WORK_PLAN_EXECUTE] Error -", e); // 将错误信息写入日�?
	}

	/**
	 * 查看作业计划详细执行信息
	 * 
	 * @每个人都可以看自己的计划完成了多�?
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 */
	private ActionForward performExecuteViewStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO();
		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		String stubFlag = "";
		String userId = "";
		String deptId = "";
		String roomId = "";

		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");

		// 获取页面传递过来的参数
		String monthPlanId = request.getParameter("monthplanid"); // 月度作业计划编号
		userId = request.getParameter("userid");
		// 开始时�?
		String startDategz = request.getParameter("startDategz");
		// 结束时间
		String endDategz = request.getParameter("endDategz");

		if (!startDategz.equals("0")) {
			request.setAttribute("startDategz", startDategz);
		} else {
			request.setAttribute("startDategz", "0");
		}
		if (!endDategz.equals("0")) {
			request.setAttribute("endDategz", endDategz);
		} else {
			request.setAttribute("endDategz", "0");
		}
		try {

			// 获取VO对象
			tawwpMonthPlanVO = tawwpExecuteMgr.viewExecutePlan(monthPlanId);

			// 为页面显示准备数�?
			request.setAttribute("tawwpmonthplanvo", tawwpMonthPlanVO);
			request.setAttribute("curruser", currUser);

			// 转向执行作业计划详细信息浏览页面
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

	private ActionForward performExecuteUniteSaves(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户名

		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符串
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填写
		String executeType = request.getParameter("executetype"); // 执行类型

		// 初始化数据
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		/*
		 * TawwpExecuteExtendBO tawwpExecuteExtendBO = new
		 * TawwpExecuteExtendBO();
		 */

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";

		String content = "";
		String formDataId = "";
		String fileName = "";

		String cruser = "";
		String stubUser = "";

		String contentUserId = "";
		String executeStartDate = "";
		String executeEndDate = "";

		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 处理执行作业计划执行内容(整体)编号字符串
		String[] tempStr = contentIdStr.split(",");

		try {
			// 如果为值班批量填写,获取班次开始、结束时间
			if ("duty".equals(flag)) {
				dutyDate = saveSessionBeanForm.getWorkSerialTime(); // 值班的时间段
				String[] dates = dutyDate.split("-->");
				dutyStartDate = dates[0].trim().substring(0, 19); // 班次开始时间
				dutyEndDate = dates[1].trim().substring(0, 19); // 班次结束时间
			}

			for (int i = 0; i < tempStr.length; i++) {

				// 获取从页面传递过来的参数
				content = request.getParameter("content" + tempStr[i]);
				formDataId = request.getParameter("formdataid" + tempStr[i]);
				fileName = request.getParameter("filename" + tempStr[i]);
				contentUserId = request.getParameter("executecontentuserid"
						+ tempStr[i]);
				cruser = request.getParameter("userid" + tempStr[i]);
				stubUser = request.getParameter("stubuser" + tempStr[i]);
				executeStartDate = request.getParameter("executestartdate"
						+ tempStr[i]);
				executeEndDate = request.getParameter("executeenddate"
						+ tempStr[i]);

				if ((content == null || "".equals(content))
						&& (formDataId == null || "".equals(formDataId))
						&& (fileName == null || "".equals(fileName))
						&& (contentUserId == null || "".equals(contentUserId))) {
					// 都没填就不添加到Hashtable中
				} else {
					tawwpExecuteContentUser = new TawwpExecuteContentUser();
					tawwpExecuteContentUser.setContent(content); // 执行内容
					tawwpExecuteContentUser.setFormDataId(formDataId); // 附加表单记录编号
					tawwpExecuteContentUser.setId(contentUserId); // 执行内容(单一用户)编号
					tawwpExecuteContentUser.setCruser(cruser);
					tawwpExecuteContentUser.setStubUser(stubUser);
					/*
					 * tawwpExecuteContentUser
					 * .setExecuteStratDate(executeStartDate);
					 * tawwpExecuteContentUser.setExecuteEndDate(executeEndDate);
					 */

					// 将执行作业计划执行内容(单一用户)对象添加到Hashtable中
					executeContentUserHash.put(tempStr[i],
							tawwpExecuteContentUser);

				}
			}

			if ("duty".equals(flag)) {
				// 保存值班批量填写内容
				mgr.addDutyExecuteContentUsersUniteSave(executeContentUserHash,
						dutyStartDate, dutyEndDate, userId, executeType);

				// 转向值班执行作业计划列表管理页面
				return mapping.findForward("success_duty");
			} else {
				// 保存日常批量填写内容
				mgr.addExecuteContentUsersSave(executeContentUserHash, userId,
						executeType);

				// 转向日常执行作业计划列表管理页面
				return mapping.findForward("success_daily");
			}
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日志
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	// 获取待质检执行项列表
	private ActionForward performGetQualityList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		String allQuality = StaticMethod.nullObject2String(request
				.getParameter("all"));

		// 初始化数据
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List list = mgr.getQualityList(allQuality, currUser);
		request.setAttribute("allQuality", allQuality);
		request.setAttribute("list", list);
		return mapping.findForward("success");
	}

	// 进入质检页面
	private ActionForward performGetQualityView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		String allQuality = StaticMethod.nullObject2String(request
				.getParameter("allQuality"));
		// 初始化数据
		TawwpUtilDAO dao = new TawwpUtilDAO();
		TawwpExecuteContentVO tawwpExecuteContentVO = new TawwpExecuteContentVO();
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		try {
			TawwpExecuteContent tawwpExecuteContent = mgr
					.loadExecuteContent(id);
			TawwpMonthPlan tawwpMonthPlan = tawwpExecuteContent
					.getTawwpMonthPlan();
			TawwpNet tawwpNet = tawwpMonthPlan.getTawwpNet();
			TawwpMonthPlanVO tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 封装执行作业计划执行内容(整体)VO对象
			MyBeanUtils.copyProperties(tawwpExecuteContentVO,
					tawwpExecuteContent);
			MyBeanUtils.copyProperties(tawwpMonthPlanVO, tawwpMonthPlan);
			tawwpExecuteContentVO.setMonthPlanNname(tawwpMonthPlan.getName());
			if (tawwpNet == null) {
				tawwpExecuteContentVO.setNetNname(tawwpMonthPlanVO
						.getNetTypeName());
			} else {
				tawwpExecuteContentVO.setNetNname(tawwpNet.getName());
			}
			tawwpExecuteContentVO.setFileName(this.getFileStrOfContent(tawwpExecuteContent));
			tawwpExecuteContentVO.setUserName(dao
					.getUserNames(tawwpExecuteContent.getCruser()));
			request.setAttribute("tawwpExecuteContent", tawwpExecuteContentVO);
			request.setAttribute("allQuality", allQuality);
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日志
			return mapping.findForward("failure"); // 转向错误页面
		}

		return mapping.findForward("success");
	}

	// 质检通过
	private ActionForward performGetQualityPass(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String forword = "";
		String ids = StaticMethod.null2String(request.getParameter("id"));
		String device = StaticMethod
				.null2String(request.getParameter("device"));
		String allQuality = StaticMethod.null2String(request
				.getParameter("allQuality"));
		if (allQuality.equals("")) {
			forword = "day";
		} else {
			forword = "all";
		}
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		try {
			if (!ids.equals("")) {
				String[] tempIds = ids.split(",");
				mgr.qualityCheckPass(tempIds, device);
			}
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日志
			return mapping.findForward("failure"); // 转向错误页面
		}
		return mapping.findForward(forword);
	}

	// 质检不通过
	private ActionForward performGetQualityReject(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String forword = "";
		String id = StaticMethod.null2String(request.getParameter("id"));
		String device = StaticMethod
				.null2String(request.getParameter("device"));
		String allQuality = StaticMethod.null2String(request
				.getParameter("allQuality"));
		if (allQuality.equals("")) {
			forword = "day";
		} else {
			forword = "all";
		}
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		mgr.qualityCheckReject(id, device);
		return mapping.findForward(forword);
	}

	// 质检查询
	private ActionForward performQueryQuality(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	// 质检查询结果
	private ActionForward performQueryQualityList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String qualityUser = jsonOrg2Orgs(StaticMethod
				.nullObject2String(request.getParameter("recieverOrgId")));
		String qualityState = StaticMethod.nullObject2String(request
				.getParameter("qualitystate"));
		String executeName = StaticMethod.nullObject2String(request
				.getParameter("executeName"));
		String hSql = "";
		if (!qualityUser.equals("")) {
			hSql += " and tawwpexecutecontent.qualityCheckUser in ("
					+ qualityUser + ")";
		}
		if (!qualityState.equals("-1")) {
			hSql += " and tawwpexecutecontent.qualityCheckFlag = '"
					+ qualityState + "'";
		}
		if (!executeName.equals("")) {
			hSql += " and tawwpexecutecontent.name like '%" + executeName
					+ "%'";
		}
		hSql += " and tawwpexecutecontent.qualityCheckFlag is not null order by tawwpexecutecontent.qualityCheckFlag";
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List list = mgr.getQualityListByCondition(hSql);
		request.setAttribute("list", list);
		return mapping.findForward("success");
	}

	// 进入质检查询的详细信息页面
	private ActionForward performQueryQualityView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		// 初始化数据
		TawwpUtilDAO dao = new TawwpUtilDAO();
		TawwpExecuteContentVO tawwpExecuteContentVO = new TawwpExecuteContentVO();
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		try {
			TawwpExecuteContent tawwpExecuteContent = mgr
					.loadExecuteContent(id);
			TawwpMonthPlan tawwpMonthPlan = tawwpExecuteContent
					.getTawwpMonthPlan();
			TawwpNet tawwpNet = tawwpMonthPlan.getTawwpNet();
			TawwpMonthPlanVO tawwpMonthPlanVO = new TawwpMonthPlanVO();
			// 封装执行作业计划执行内容(整体)VO对象
			MyBeanUtils.copyProperties(tawwpExecuteContentVO,
					tawwpExecuteContent);
			MyBeanUtils.copyProperties(tawwpMonthPlanVO, tawwpMonthPlan);
			tawwpExecuteContentVO.setMonthPlanNname(tawwpMonthPlan.getName());
			if (tawwpNet == null) {
				tawwpExecuteContentVO.setNetNname(tawwpMonthPlanVO
						.getNetTypeName());
			} else {
				tawwpExecuteContentVO.setNetNname(tawwpNet.getName());
			}
			tawwpExecuteContentVO.setUserName(dao
					.getUserNames(tawwpExecuteContent.getCruser()));
			tawwpExecuteContentVO.setQualityCheckUserName(dao
					.getUserName(tawwpExecuteContent.getQualityCheckUser()));
			request.setAttribute("tawwpExecuteContent", tawwpExecuteContentVO);
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日志
			return mapping.findForward("failure"); // 转向错误页面
		}
		return mapping.findForward("success");
	}

	/**
	 * 从json中取已经选择的质检人
	 * 
	 * @param orgs
	 *            json串
	 * @return
	 */
	private String jsonOrg2Orgs(String orgs) {
		JSONArray jsonOrgs = JSONArray.fromString(orgs);
		String users = "";
		for (Iterator it = jsonOrgs.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 质检人的userId
			String id = org.getString(UIConstants.JSON_ID);
			users = users + "'" + id + "',";
		}
		if (!users.equals("")) {
			users = users.substring(0, users.length() - 1);
		}
		return users;
	}

	private ActionForward performDutyExecuteListView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = saveSessionBeanForm.getRoomId(); // 当前机房编号
		// 初始化数据
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List monthPlanVOList = null;
		String dutyStartDate = "";
		String dutyEndDate = "";
		String date = TawwpUtil.getCurrentDateTime().substring(8, 10);
		String flag = "duty";
		String yearFlag = request.getParameter("year");
		String monthFlag = request.getParameter("month");
		String dayFlag = "";
		if (yearFlag == null || monthFlag == null) {
			yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
			monthFlag = TawwpUtil.getCurrentDateTime("MM");
			dayFlag = TawwpUtil.getCurrentDateTime("dd");
		}
		// 获取当前年、月

		// 获取当前用户日常执行列表

		try {
			// 获取执行作业计划集合
			monthPlanVOList = tawwpExecuteMgr.listDutyExecuteView(currUser,
					deptId, roomId, yearFlag, monthFlag, "own");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取代理用户信息
		// List stubUserList = saveSessionBeanForm.getStubUserList();
		// List stubSysUserList = new ArrayList();// 代理用户对象列表
		// String stubUserIds = "";
		// String[] stubUserId = null;
		// TawwpStubUserVO tawwpStubUserVO = null;
		// if (stubUserList != null) {
		// for (int i = 0; i < stubUserList.size(); i++) {
		// // 获取代理用户VO对象
		// tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
		// if (stubUserIds.indexOf(tawwpStubUserVO.getCruser() + ",") == -1) {
		// stubUserIds = stubUserIds + tawwpStubUserVO.getCruser()
		// + ",";
		// }
		// }
		// }
		// if (stubUserIds.length() > 0) {
		// stubUserId = stubUserIds.split(",");
		// }
		//
		// if (stubUserId != null) {
		// for (int i = 0; i < stubUserId.length; i++) {
		// TawSystemUser userTemp = tawRmUserDAO
		// .getUserByuserid(stubUserId[i]);
		// stubSysUserList.add(userTemp);
		// }
		// }
		// // 获取代理用户日常执行列表
		// List stubMonthPlanVOList = new ArrayList();
		// List temp = null;
		// try {
		// for (int i = 0; i < stubSysUserList.size(); i++) {
		// TawSystemUser userTemp = (TawSystemUser) stubSysUserList.get(i);
		// temp = tawwpExecuteMgr.listDailyExecuteView(userTemp
		// .getUserid(), userTemp.getDeptid(), "", year, month,
		// "stub");
		// if (temp != null) {
		// for (int j = 0; j < temp.size(); j++) {
		// stubMonthPlanVOList.add(temp.get(j));
		// }
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		request.setAttribute("day", dayFlag);
		request.setAttribute("curruser", currUser);
		request.setAttribute("year", yearFlag);
		request.setAttribute("month", monthFlag);
		request.setAttribute("monthPlanVOList", monthPlanVOList);
		// request.setAttribute("stubMonthPlanVOList", stubMonthPlanVOList);
		return mapping.findForward("success");
	}

	private ActionForward performInspectionDayExecuteAddsNew(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String inspectionFlg = "Inspection";
		long time1 = System.currentTimeMillis();
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) getBean("itawSystemUserManager");
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�
		String flag = "daily";
		HttpSession session = request.getSession();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null || month == null) {
			String date = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
			year = date.substring(0, 4);
			month = date.substring(5, 7);
		}

		// 获取session中的信息

		// 获取当前用户信息
		String userId = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号

		String date = request.getParameter("date");
		if (date != null && !date.equals("")) {
			if (date.length() == 1) {
				date = "0" + date;
			}
			session.setAttribute("date", date);
		} else {
			date = (String) session.getAttribute("date");
			if (date == null || "".equals(date)) {
				date = TawwpUtil.getCurrentDateTime("dd");
				if (date.length() == 1) {
					date = "0" + date;
				}
			}
		}

		List monthPlanVOList = null;
		try {
			monthPlanVOList = tawwpExecuteMgr.listDailyExecuteView(currUser,
					deptId, roomId, year, month, "own");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取代理用户信息
		List stubUserList = saveSessionBeanForm.getStubUserList();
		List stubSysUserList = new ArrayList();// 代理用户对象列表
		String stubUserIds = "";
		String[] stubUserId = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		if (stubUserList != null) {
			for (int i = 0; i < stubUserList.size(); i++) {
				// 获取代理用户VO对象
				tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
				if (stubUserIds.indexOf(tawwpStubUserVO.getCruser() + ",") == -1) {
					stubUserIds = stubUserIds + tawwpStubUserVO.getCruser()
							+ ",";
				}
			}
		}
		if (stubUserIds.length() > 0) {
			stubUserId = stubUserIds.split(",");
		}

		if (stubUserId != null) {
			for (int i = 0; i < stubUserId.length; i++) {
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(stubUserId[i]);
				stubSysUserList.add(userTemp);
			}
		}
		// 获取代理用户日常执行列表
		List stubMonthPlanVOList = new ArrayList();
		List temp = null;
		try {
			for (int i = 0; i < stubSysUserList.size(); i++) {
				TawSystemUser userTemp = (TawSystemUser) stubSysUserList.get(i);
				temp = tawwpExecuteMgr.listDayExecuteView(userTemp.getUserid(),
						userTemp.getDeptid(), "", year, month, "stub",
						inspectionFlg);
				if (temp != null) {
					for (int j = 0; j < temp.size(); j++) {
						stubMonthPlanVOList.add(temp.get(j));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap monthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		HashMap monthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
		if (monthPlanVOList != null) {
			String stubFlag = "own";
			userId = currUser; // 当前用户�
			// 当前用户登录机房编号
			String dutyStartDate = "";
			String dutyEndDate = "";

			for (int i = 0; i < monthPlanVOList.size(); i++) {
				TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) monthPlanVOList
						.get(i);
				year = tawwpmonthplanvo.getYearFlag();
				month = tawwpmonthplanvo.getMonthFlag();
				String monthplanid = tawwpmonthplanvo.getId();
				try {
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.addExecuteContentUsersView(userId, deptId, roomId,
									monthplanid, dutyStartDate, dutyEndDate,
									inspectionFlg, stubFlag, date);
					HashMap contentHash = new LinkedHashMap();
					contentHash = tawwpExecuteMgr
							.dealList(executeContentVOList);

					monthplanvoHash.put(tawwpmonthplanvo, executeContentVOList);
					monthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}

			}

		}

		HashMap stubMonthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		HashMap stubMonthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
		if (stubMonthPlanVOList != null) {
			String stubFlag = "stub";
			String dutyStartDate = "";
			String dutyEndDate = "";
			String reason = "";

			for (int i = 0; i < stubMonthPlanVOList.size(); i++) {
				TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) stubMonthPlanVOList
						.get(i);
				String monthplanid = tawwpmonthplanvo.getId();
				String userbystub = tawwpmonthplanvo.getUserByStub();
				userId = userbystub;
				TawSystemUser userTemp = tawRmUserDAO.getUserByuserid(userId);
				deptId = String.valueOf(userTemp.getDeptid());
				try {
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.addExecuteContentUsersView(userId, deptId, roomId,
									monthplanid, dutyStartDate, dutyEndDate,
									inspectionFlg, stubFlag, date);
					HashMap contentHash = new LinkedHashMap();
					contentHash = tawwpExecuteMgr
							.dealList(executeContentVOList);

					stubMonthplanvoHash.put(tawwpmonthplanvo,
							executeContentVOList);
					stubMonthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}

			}

		}
		long time2 = System.currentTimeMillis();
		System.out.println("time2===" + String.valueOf(time2 - time1));
		request.setAttribute("flag", flag);
		request.setAttribute("curruser", currUser);
		request.setAttribute("date", date);
		request.setAttribute("years", year + "-" + month + "-" + date);
		request.setAttribute("monthplanvoHash", monthplanvoHash);
		request.setAttribute("monthplancontentHash", monthplancontentHash);
		request.setAttribute("stubMonthplanvoHash", stubMonthplanvoHash);
		request.setAttribute("stubMonthplancontentHash",
				stubMonthplancontentHash);
		return mapping.findForward("success");
	}

	private ActionForward performDayExecuteAddsNew(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String inspectionFlg = "notInspection";
		long time1 = System.currentTimeMillis();
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) getBean("itawSystemUserManager");
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�
		String flag = "daily";
		HttpSession session = request.getSession();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null || month == null) {
			String date = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
			year = date.substring(0, 4);
			month = date.substring(5, 7);
		}

		// 获取session中的信息

		// 获取当前用户信息
		String userId = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号

		String date = request.getParameter("date");
		if (date != null && !date.equals("")) {
			if (date.length() == 1) {
				date = "0" + date;
			}
			session.setAttribute("date", date);
		} else {
			date = (String) session.getAttribute("date");
			if (date == null || "".equals(date)) {
				date = TawwpUtil.getCurrentDateTime("dd");
				if (date.length() == 1) {
					date = "0" + date;
				}
			}
		}

		List monthPlanVOList = null;
		try {
			monthPlanVOList = tawwpExecuteMgr.listDayExecuteView(currUser,
					deptId, roomId, year, month, "own", inspectionFlg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取代理用户信息
		List stubUserList = saveSessionBeanForm.getStubUserList();
		List stubSysUserList = new ArrayList();// 代理用户对象列表
		String stubUserIds = "";
		String[] stubUserId = null;
		TawwpStubUserVO tawwpStubUserVO = null;
		if (stubUserList != null) {
			for (int i = 0; i < stubUserList.size(); i++) {
				// 获取代理用户VO对象
				tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
				if (stubUserIds.indexOf(tawwpStubUserVO.getCruser() + ",") == -1) {
					stubUserIds = stubUserIds + tawwpStubUserVO.getCruser()
							+ ",";
				}
			}
		}
		if (stubUserIds.length() > 0) {
			stubUserId = stubUserIds.split(",");
		}

		if (stubUserId != null) {
			for (int i = 0; i < stubUserId.length; i++) {
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(stubUserId[i]);
				stubSysUserList.add(userTemp);
			}
		}
		// 获取代理用户日常执行列表
		List stubMonthPlanVOList = new ArrayList();
		List temp = null;
		try {
			for (int i = 0; i < stubSysUserList.size(); i++) {
				TawSystemUser userTemp = (TawSystemUser) stubSysUserList.get(i);
				temp = tawwpExecuteMgr.listDayExecuteView(userTemp.getUserid(),
						userTemp.getDeptid(), "", year, month, "stub",
						inspectionFlg);
				if (temp != null) {
					for (int j = 0; j < temp.size(); j++) {
						stubMonthPlanVOList.add(temp.get(j));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap monthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		HashMap monthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
		if (monthPlanVOList != null) {
			String stubFlag = "own";
			userId = currUser; // 当前用户�
			// 当前用户登录机房编号
			String dutyStartDate = "";
			String dutyEndDate = "";

			for (int i = 0; i < monthPlanVOList.size(); i++) {
				TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) monthPlanVOList
						.get(i);
				year = tawwpmonthplanvo.getYearFlag();
				month = tawwpmonthplanvo.getMonthFlag();
				String monthplanid = tawwpmonthplanvo.getId();
				try {
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.addExecuteContentUsersView(userId, deptId, roomId,
									monthplanid, dutyStartDate, dutyEndDate,
									inspectionFlg, stubFlag, date);
					HashMap contentHash = new LinkedHashMap();
					contentHash = tawwpExecuteMgr
							.dealList(executeContentVOList);

					monthplanvoHash.put(tawwpmonthplanvo, executeContentVOList);
					monthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}

			}

		}

		HashMap stubMonthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		HashMap stubMonthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
		if (stubMonthPlanVOList != null) {
			String stubFlag = "stub";
			String dutyStartDate = "";
			String dutyEndDate = "";
			String reason = "";

			for (int i = 0; i < stubMonthPlanVOList.size(); i++) {
				TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) stubMonthPlanVOList
						.get(i);
				String monthplanid = tawwpmonthplanvo.getId();
				String userbystub = tawwpmonthplanvo.getUserByStub();
				userId = userbystub;
				TawSystemUser userTemp = tawRmUserDAO.getUserByuserid(userId);
				deptId = String.valueOf(userTemp.getDeptid());
				try {
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.addExecuteContentUsersView(userId, deptId, roomId,
									monthplanid, dutyStartDate, dutyEndDate,
									inspectionFlg, stubFlag, date);
					HashMap contentHash = new LinkedHashMap();
					contentHash = tawwpExecuteMgr
							.dealList(executeContentVOList);

					stubMonthplanvoHash.put(tawwpmonthplanvo,
							executeContentVOList);
					stubMonthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}

			}

		}
		long time2 = System.currentTimeMillis();
		request.setAttribute("flag", flag);
		request.setAttribute("curruser", currUser);
		request.setAttribute("date", date);
		request.setAttribute("years", year + "-" + month + "-" + date);
		request.setAttribute("monthplanvoHash", monthplanvoHash);
		request.setAttribute("monthplancontentHash", monthplancontentHash);
		request.setAttribute("stubMonthplanvoHash", stubMonthplanvoHash);
		request.setAttribute("stubMonthplancontentHash",
				stubMonthplancontentHash);
		System.out.println("运行时间值:" + String.valueOf(time2 - time1));
		return mapping.findForward("success");
	}

	private ActionForward performExecuteInterFaceSaves(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符�?
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String executeType = request.getParameter("executetype"); // 执行类型
		String monthPlanId = request.getParameter("monthplanid");

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";

		String content = "";
		String formDataId = "";
		String fileName = "";
		String remark = "";
		String cruser = "";
		String stubUser = "";
		String reason = "";

		String contentUserId = "";

		String writeDate = "";
		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 处理执行作业计划执行内容(整体)编号字符�?
		String[] tempStr = contentIdStr.split(",");
		try {
			for (int i = 0; i < tempStr.length; i++) {

				// 获取从页面传递过来的参数
				remark = request.getParameter("remark" + i);
				content = request.getParameter("content" + i);
				formDataId = request.getParameter("formdataid" + i);
				fileName = request.getParameter("filename" + i);
				contentUserId = request
						.getParameter("executecontentuserid" + i);
				cruser = request.getParameter("userid" + i);
				stubUser = request.getParameter("stubuser" + i);
				writeDate = request.getParameter("writeDate" + i);
				reason = request.getParameter("reason" + i);
				String normalFlag = request.getParameter("normalFlag" + i);
				if (normalFlag == null || "".equals(normalFlag)) {
					normalFlag = "1";
				}
				tawwpExecuteContentUser = new TawwpExecuteContentUser();
				tawwpExecuteContentUser.setContent(content); // 执行内容
				tawwpExecuteContentUser.setFormDataId(formDataId); // 附加表单记录编号
				tawwpExecuteContentUser.setId(contentUserId); // 执行内容(单一用户)编号
				tawwpExecuteContentUser.setCruser(cruser);
				tawwpExecuteContentUser.setCrtime(TawwpUtil
						.getCurrentDateTime());
				tawwpExecuteContentUser.setWriteDate(writeDate);
				tawwpExecuteContentUser.setStubUser(stubUser);
				tawwpExecuteContentUser.setNormalFlag(normalFlag);
				tawwpExecuteContentUser.setRemark(remark);
				tawwpExecuteContentUser.setReason(reason);
				// 将执行作业计划执行内�?单一用户)对象添加到Hashtable�?
				executeContentUserHash.put(tempStr[i], tawwpExecuteContentUser);

			}
			ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
			// 保存日常批量填写内容
			// tawwpExecuteMgr.addExecuteContentUsersSave(
			// executeContentUserHash, userId, executeType);

			mgr.addExecuteContentInterFaceUsersSave(executeContentUserHash,
					userId, executeType);

			// 转向日常执行作业计划列表管理页面
			return mapping.findForward("success_daily");

		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	private ActionForward performInterFaceSaves(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符�?
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String executeType = request.getParameter("executetype"); // 执行类型
		String monthPlanId = request.getParameter("monthplanid");

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";

		String content = "";
		String formDataId = "";
		String fileName = "";
		String remark = "";
		String cruser = "";
		String stubUser = "";
		String reason = "";

		String contentUserId = "";

		String writeDate = "";
		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		contentIdStr = contentIdStr.substring(0, contentIdStr.length() - 1);
		// 处理执行作业计划执行内容(整体)编号字符�?
		String[] tempStr = contentIdStr.split(",");

		try {
			/*
			 * for (int i = 0; i < tempStr.length; i++) {
			 *  // 获取从页面传递过来的参数 remark = request.getParameter("remark"+i);
			 * content = request.getParameter("content" + i); formDataId =
			 * request.getParameter("formdataid" + i); fileName =
			 * request.getParameter("filename" + i); contentUserId = request
			 * .getParameter("executecontentuserid" + i); cruser =
			 * request.getParameter("userid" + i); stubUser =
			 * request.getParameter("stubuser" + i); writeDate =
			 * request.getParameter("writeDate" + i);
			 * reason=request.getParameter("reason"+i); String normalFlag =
			 * request.getParameter("normalFlag" + i); if (normalFlag == null ||
			 * "".equals(normalFlag)) { normalFlag = "1"; }
			 * tawwpExecuteContentUser = new TawwpExecuteContentUser();
			 * tawwpExecuteContentUser.setContent(content); // 执行内容
			 * tawwpExecuteContentUser.setFormDataId(formDataId); // 附加表单记录编号
			 * tawwpExecuteContentUser.setId(contentUserId); // 执行内容(单一用户)编号
			 * tawwpExecuteContentUser.setCruser(cruser);
			 * tawwpExecuteContentUser.setCrtime(TawwpUtil
			 * .getCurrentDateTime());
			 * tawwpExecuteContentUser.setWriteDate(writeDate);
			 * tawwpExecuteContentUser.setStubUser(stubUser);
			 * tawwpExecuteContentUser.setNormalFlag(normalFlag);
			 * tawwpExecuteContentUser.setRemark(remark);
			 * tawwpExecuteContentUser.setReason(reason); //
			 * 将执行作业计划执行内�?单一用户)对象添加到Hashtable�?
			 * executeContentUserHash.put(tempStr[i], tawwpExecuteContentUser);
			 *  }
			 */
			ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
			// 保存日常批量填写内容
			// tawwpExecuteMgr.addExecuteContentUsersSave(
			// executeContentUserHash, userId, executeType);

			String returnStr = mgr.addExecuteInterFaceUsersSave(tempStr,
					userId, executeType);

			// 转向日常执行作业计划列表管理页面
			JSONUtil.success(response, returnStr);
			return null;

		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);

			return null; // 转向错误页面
		}
	}

	private ActionForward performFileDown(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String fileId = request.getParameter("fileId");
		ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		TawwpExecuteFile executeFile = new TawwpExecuteFile();
		String rootFilePath = "";
		String fileCnName = "";
		try {
			rootFilePath = AccessoriesMgrLocator.getAccessoriesAttributes()
					.getUploadPath();
			executeFile = mgr.loadExecuteFileByCode(fileId);
			String codeName = executeFile.getFileCodeName();
			fileCnName = executeFile.getFileName();
			String filePath = rootFilePath
					+ "accessories/uploadfile/workplan/uploadfile/" + codeName;
			File file = new File(filePath);
			if (!file.exists()) {
				request.setAttribute("noteInfo", "文件系统中的文件不存在，可能已经被删除！");
				String fileIdList = StaticMethod.nullObject2String(request
						.getParameter("filelist"));
				request.setAttribute("fileIdlist", fileIdList);
				return mapping.findForward("view");
			} else {
				request.setAttribute("noteInfo", "");
			}
			InputStream inStream = new FileInputStream(filePath);
			//
			// fileCnName = URLEncoder.encode(fileCnName, "UTF-8");
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("GB2312");

			fileCnName = new String(fileCnName.getBytes("gbk"), "iso8859-1");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileCnName);
			// // 文件的存放路径
			/*
			 * javax.servlet.RequestDispatcher dispatcher = request
			 * .getRequestDispatcher(path); if (dispatcher != null) {
			 * dispatcher.forward(request, response); }
			 */
			// 循环取出流中的数据
			byte[] b = new byte[128];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ActionForward performNotExecuteListAdds(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = saveSessionBeanForm.getRoomId(); // 当前机房编号
		// 初始化数据
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List monthPlanVOList = null;
		String dutyStartDate = "";
		String dutyEndDate = "";
		String date = TawwpUtil.getCurrentDateTime().substring(8, 10);
		// String flag = "duty";
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		// 获取当前年、月
		String yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
		String monthFlag = TawwpUtil.getCurrentDateTime("MM");

		try {

			// 判断值班状态
			if (flag.equals("duty")
					&& saveSessionBeanForm.getStartDuty() != null
					&& saveSessionBeanForm.getEndDuty() != null) {
				dutyStartDate = StaticMethod.getCurrentDateTime(
						saveSessionBeanForm.getStartDuty(),
						"yyyy-MM-dd HH:mm:ss")
						+ "";
				dutyEndDate = StaticMethod
						.getCurrentDateTime(saveSessionBeanForm.getEndDuty(),
								"yyyy-MM-dd HH:mm:ss")
						+ "";

			} else if (flag.equals("daily")) {
				// 如果当前用户未处于值班状态
				dutyStartDate = "";
				dutyEndDate = "";

			} else {
				request.setAttribute("error", "noduty");
				// 转向提示页面
				return mapping.findForward("failure");
			}

			// 获取执行作业计划集合
			// monthPlanVOList = tawwpExecuteMgr.listNotExecuteView(String
			// .valueOf(roomId), yearFlag, monthFlag, dutyStartDate,
			// dutyEndDate,);

			try {
				// monthPlanVOList =
				// tawwpExecuteMgr.llistDailyExecuteView(currUser,
				// deptId, roomId, yearFlag, monthFlag, "own");
			} catch (Exception e) {
				e.printStackTrace();
			}

			HashMap monthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
			HashMap monthplancontentHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容封装信息
			// if (monthPlanVOList != null) {
			// String stubFlag = "duty";
			String userId = currUser; // 当前用户�

			// for (int i = 0; i < monthPlanVOList.size(); i++) {
			// TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO)
			// monthPlanVOList
			// .get(i);
			String monthPlanId = request.getParameter("monthplanid");
			TawwpMonthPlanVO tawwpMonthPlanVO = tawwpExecuteMgr
					.viewExecutePlanExecuter(currUser, deptId, roomId,
							monthPlanId, "owm");
			try {
				// 获取单个月度作业计划执行内容(整体)VO对象集合
				List executeContentVOList = tawwpExecuteMgr
						.addExecuteContentUsersView(userId, deptId, roomId,
								monthPlanId, dutyStartDate, dutyEndDate, flag,
								"owm", date);
				HashMap contentHash = new LinkedHashMap();
				contentHash = tawwpExecuteMgr.dealList(executeContentVOList);

				monthplanvoHash.put(tawwpMonthPlanVO, executeContentVOList);
				monthplancontentHash.put(tawwpMonthPlanVO, contentHash);
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("failure"); // 转向错误页面
			}

			// }
			//
			// }

			request.setAttribute("flag", flag);
			request.setAttribute("curruser", currUser);
			request.setAttribute("date", date);
			request.setAttribute("monthplanvoHash", monthplanvoHash);
			request.setAttribute("monthplancontentHash", monthplancontentHash);

			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			generalError(request, e);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 批量填写执行作业计划执行内容保存Action
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
	private ActionForward performGXExecuteSaves(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		List sublist = new ArrayList();
		sublist = saveSessionBeanForm.getStubUserList();

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?
		String dept = saveSessionBeanForm.getDeptid(); // 当前用户�?
		String roomId = saveSessionBeanForm.getRoomId(); //当前机房
		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符�?
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String executeType = request.getParameter("executetype"); // 执行类型

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		String date = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";

		String content = "";
		String formDataId = "";
		String fileName = "";
		String remark = "";
		String cruser = "";
		String stubUser = "";
		String reason = "";

		String contentUserId = "";

		String writeDate = "";
		List list = new ArrayList();
		String yearPlanId ="";
		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;
		ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) getBean("tawwpMonthMgr");
		// 处理执行作业计划执行内容(整体)编号字符�?
		String[] tempStr = contentIdStr.split(",");

		try {
			// 如果为值班批量填写,获取班次开始、结束时�?
			if ("duty".equals(flag)) {

				dutyStartDate = saveSessionBeanForm.getStartDuty() + ""; // 班次开始时�?
				dutyEndDate = saveSessionBeanForm.getEndDuty() + ""; // 班次结束时间
			}
			TawwpExecuteContent tawwpExecuteContent;
			for (int i = 0; i < tempStr.length; i++) {

				// 获取从页面传递过来的参数
				remark = request.getParameter("remark" + i);
				content = request.getParameter("content" + i);
				formDataId = request.getParameter("formdataid" + i);
				fileName = request.getParameter("filename" + i);
				contentUserId = request
						.getParameter("executecontentuserid" + i);
				cruser = request.getParameter("userid" + i);
				stubUser = request.getParameter("stubuser" + i);
				writeDate = request.getParameter("writeDate" + i);
				reason = request.getParameter("reason" + i);
				String normalFlag = request.getParameter("normalFlag" + i);
				if (normalFlag == null || "".equals(normalFlag)) {
					normalFlag = "1";
				}
				if ((content == null || "".equals(content))
						&& (formDataId == null || "".equals(formDataId))
						&& (fileName == null || "".equals(fileName))
						&& (contentUserId == null || "".equals(contentUserId))) {
					
				} else {
					tawwpExecuteContentUser = new TawwpExecuteContentUser();
					tawwpExecuteContentUser.setContent(content); // 执行内容
					tawwpExecuteContentUser.setFormDataId(formDataId); // 附加表单记录编号
					tawwpExecuteContentUser.setId(contentUserId); // 执行内容(单一用户)编号
					tawwpExecuteContentUser.setCruser(cruser);
					tawwpExecuteContentUser.setCrtime(TawwpUtil
							.getCurrentDateTime());
					tawwpExecuteContentUser.setWriteDate(writeDate);
					tawwpExecuteContentUser.setStubUser(stubUser);
					tawwpExecuteContentUser.setNormalFlag(normalFlag);
					tawwpExecuteContentUser.setRemark(remark);
					tawwpExecuteContentUser.setReason(reason);
					// 将执行作业计划执行内�?单一用户)对象添加到Hashtable�?
					tawwpExecuteContent = tawwpExecuteMgr
							.loadExecuteContent(tempStr[i]);
					String name = tawwpExecuteContent.getName();
					if ("duty".equals(flag)) {
						list = tawwpExecuteMgr.loadExecuteContentList(name,
								saveSessionBeanForm.getUserid(),
								dutyStartDate, dutyEndDate,
								tawwpExecuteContent.getTawwpMonthPlan()
										.getTawwpYearPlan().getId());
					}else{
						list = tawwpExecuteMgr.loadExecuteContentList(name,
								saveSessionBeanForm.getUserid(),
								date + " 00:00:00", date + " 23:59:59",
								tawwpExecuteContent.getTawwpMonthPlan()
										.getTawwpYearPlan().getId());
					}
					System.out.println("list.size()===========》" + list.size());
					System.out
							.println("GXADD==================>" + list.size());
					String monthPlanName = tawwpExecuteContent
							.getTawwpMonthPlan().getName();
					String executer = "";
					if (list.size() > 0) {
						for (int j = 0; j < list.size(); j++) {
							TawwpExecuteContent executeContent = new TawwpExecuteContent();

							TawwpMonthPlan tawwpMonthPlan = new TawwpMonthPlan();
							executeContent = (TawwpExecuteContent) list.get(j);
							System.out
									.println("executeContent.getTawwpMonthPlan().getId()"
											+ executeContent
													.getTawwpMonthPlan()
													.getId());
							yearPlanId = executeContent
							.getTawwpMonthPlan().getYearPlanId();
							if (executeContent.getTawwpMonthPlan().getName()
									.equals(monthPlanName)) {
								executer = "," + executeContent.getExecuter()
										+ ",";
								if (executer.indexOf("," + userId + ",") >= 0
										|| executer.indexOf("," + dept + ",") >= 0
										|| executer.indexOf("," + roomId + ",") >= 0) {
									tawwpMonthPlan = executeContent
											.getTawwpMonthPlan();
									tawwpMonthPlan.setExecuteState("4");
									tawwpMonthMgr
											.updateMonthPlan(tawwpMonthPlan);
									executeContent = (TawwpExecuteContent) list
											.get(j);
									executeContentUserHash.put(executeContent
											.getId(), tawwpExecuteContentUser);

								}
								// if (sublist != null) {
								// for (int l = 0; l < sublist.size(); l++) {
								// // 获取代理用户VO对象
								// TawwpStubUserVO tawwpStubUserVO =
								// (TawwpStubUserVO) sublist.get(j);
								// if(executeContent.getExecuter().indexOf(","+tawwpStubUserVO.getCruser()+",")>=0
								// ||
								// executeContent.getExecuter().indexOf(","+tawwpStubUserVO.getCruser())>=0
								// ||
								// executeContent.getExecuter().indexOf(tawwpStubUserVO.getCruser()+",")>=0||
								// executeContent.getExecuter().equals(tawwpStubUserVO.getCruser())){
								// tawwpMonthPlan=executeContent.getTawwpMonthPlan();
								// tawwpMonthPlan.setExecuteState("4");
								// tawwpMonthMgr.updateMonthPlan(tawwpMonthPlan);
								// executeContent=(TawwpExecuteContent)
								// list.get(j);
								// executeContentUserHash.put(executeContent.getId(),
								// tawwpExecuteContentUser);
								//							
								// }
								// }
								//								
								// }
							}
						}
						System.out.println("GXADD==================>");
					}
					executeContentUserHash.put(tempStr[i],
							tawwpExecuteContentUser);

				}
			}

			if ("duty".equals(flag)) {
				// 保存值班批量填写内容
				tawwpExecuteMgr.addDutyExecuteContentUsersSave(
						executeContentUserHash, dutyStartDate, dutyEndDate,
						userId, executeType,yearPlanId);
				// 日志
				ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
				tawwpLogMgr
						.addLog(cruser, "addDutyExecuteContentUsersSave", "");

				// 转向值班执行作业计划列表管理页面
				JSONUtil.success(response, "保存成功!");
				return null;
				// return mapping.findForward("success_duty");
			} else {
				ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
				// 保存日常批量填写内容
				// tawwpExecuteMgr.addExecuteContentUsersSave(
				// executeContentUserHash, userId, executeType);

				mgr.addExecuteContentUsersSave(executeContentUserHash, userId,
						executeType,yearPlanId);

				// 转向日常执行作业计划列表管理页面
				JSONUtil.success(response, "保存成功!");
				// return mapping.findForward("success_daily");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			try {
				JSONUtil.success(response, "保存失败!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
	}

	private ActionForward performExecuteNetList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		List sublist = new ArrayList();
		TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sublist = TawSystemSessionForm.getStubUserList();
		String date = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		String executeId = request.getParameter("executeId");
		TawwpExecuteContent tawwpExecuteContent = new TawwpExecuteContent();
		List netList = new ArrayList();
		List list = new ArrayList();
		String dept = TawSystemSessionForm.getDeptid();
		String roomId = TawSystemSessionForm.getRoomId();
		try {
			tawwpExecuteContent = tawwpExecuteMgr.loadExecuteContent(executeId);
			String name = tawwpExecuteContent.getName();
			String userId = TawSystemSessionForm.getUserid();
			String monthPlanName = tawwpExecuteContent.getTawwpMonthPlan()
					.getName();
			System.out.println("monthPlanName===:" + monthPlanName);
			System.out.println("monthPlanName===:"
					+ tawwpExecuteContent.getTawwpMonthPlan().getYearPlanId());
			list = tawwpExecuteMgr.loadExecuteContentList(name, userId, date
					+ " 00:00:00", date + " 23:59:59", tawwpExecuteContent
					.getTawwpMonthPlan().getTawwpYearPlan().getId());

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TawwpNet tawwpNet = new TawwpNet();
					TawwpExecuteContent executeContent = new TawwpExecuteContent();
					executeContent = (TawwpExecuteContent) list.get(i);
					System.out
							.println("executeContent.getTawwpMonthPlan().getName().equals(monthPlanName)===>>>:"
									+ executeContent.getTawwpMonthPlan()
											.getName().equals(monthPlanName));
					if (executeContent.getTawwpMonthPlan().getName().equals(
							monthPlanName)) {
						tawwpNet = executeContent.getTawwpMonthPlan()
								.getTawwpNet();
						String executer = "," + executeContent.getExecuter()
								+ ",";
						System.out.println("executer===:" + executer);
						// System.out.println("tawwpNet.getName()===>>>:"+tawwpNet.getName());
						System.out
								.println("executeContent.getTawwpMonthPlan().getId()===>>>:"
										+ executeContent.getTawwpMonthPlan()
												.getId());

						if (executer.indexOf("," + userId + ",") >= 0
								|| executer.indexOf("," + dept + ",") >= 0
								|| executer.indexOf("," + roomId + ",") >= 0){

							if (tawwpNet != null) {
								int flag = 0;
								for (int k = 0; k < netList.size(); k++) {
									if (((TawwpNet) netList.get(k)).getId()
											.equals(tawwpNet.getId())) {
										flag = 1;
									}
								}
								if (flag == 0) {
									netList.add(tawwpNet);
								}
							}

						}
					}
		

				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print("netList.size():" + netList.size());
		request.setAttribute("netList", netList);
		return mapping.findForward("success");
	}

	 /**
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */


	private ActionForward performGxExecuteAdds(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String monthExecuteUserId = StaticMethod.null2String(request
				.getParameter("monthexecuteuserid")); // 月度作业计划执行人信�?
		String monthExecuteUserState = StaticMethod.null2String(request
				.getParameter("monthExecuteUserState"));
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String yearPlanid = request.getParameter("yearPlanid"); // 月度作业计划编号
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?
		String date = TawwpUtil.getRequestValue(request, "date", ""); // 批量填写日期

		// 如果取得的日期小�?0且没有经过补0处理,则在日期前加0
		if (date.length() == 1) {
			date = "0" + date;
		}

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO();
		ITawSystemUserManager tawRmUserDAO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List executeContentVOList = null;

		String dutyDate = "";
		String dutyStartDate = "";
		String dutyEndDate = "";
		String stubFlag = "";
		String userId = "";
		String deptId = "";
		String roomId = "";
		// 日志
		ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
		try {
			if (monthExecuteUserState.equals("0")) {
				tawwpExecuteMgr.confirm(monthExecuteUserId);

				// 获取当前用户的session中的信息

				tawwpLogMgr.addLog(currUser, "confirm", "");
			}
			// 设定代理标志
			if (!"".equals(userByStub)) {
				stubFlag = "stub";
				userId = userByStub;
				TawSystemUser userTemp = tawRmUserDAO
						.getUserByuserid(userByStub);
				deptId = String.valueOf(userTemp.getDeptid());
			} else {
				stubFlag = "own";
				userId = currUser; // 当前用户�?
				deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
				roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号
			}

			// 如果为值班批量填写,获取班次开始、结束时�?
			if ("duty".equals(flag)) {

				dutyStartDate = saveSessionBeanForm.getStartDuty() + ""; // 班次开始时�?
				dutyEndDate = saveSessionBeanForm.getEndDuty() + ""; // 班次结束时间
			}

			// 获取执行作业计划执行内容(整体)VO对象集合

			executeContentVOList = tawwpExecuteMgr
					.addGxExecuteContentUsersView(userId, deptId, roomId,
							yearPlanid, dutyStartDate, dutyEndDate, flag,
							stubFlag, date);

			// 日志

			tawwpLogMgr.addLog(userId, "addExecuteContentUsersView", "");
			if (executeContentVOList == null
					|| executeContentVOList.size() == 0) {

				// 如果返回结果集为�?
				request.setAttribute("error", "noexecute");
				// 转向提示页面
				if ("duty".equals(flag)) {
					request.setAttribute("error", "noduty");
				}

				return mapping.findForward("failure");
			}

			// 为页面显示准备数�?
			request.setAttribute("executecontentvolist", executeContentVOList);
			request.setAttribute("flag", flag);
			request.setAttribute("curruser", currUser);
			request.setAttribute("monthplanid", yearPlanid);
			request.setAttribute("userbystub", userByStub);
			request.setAttribute("date", date);
			// 转向批量填写执行内容页面
			return mapping.findForward("success");
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			if ("duty".equals(flag)) {
				request.setAttribute("error", "noduty");
			}
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 显示所有作业计划的执行内容
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performViewDailyExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String inspectionFlg = "";

		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�
		String flag = "daily";
		// HttpSession session = request.getSession();
		String year = TawwpUtil.getCurrentDateTime("yyyy");
		String month = TawwpUtil.getCurrentDateTime("MM");
		String date = TawwpUtil.getCurrentDateTime("dd");
		List monthList = new ArrayList();

		// 获取session中的信息
		// 获取当前用户信息
		String userId = saveSessionBeanForm.getUserid(); // 当前用户
		String deptId = String.valueOf(saveSessionBeanForm.getDeptid()); // 当前部门
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId()); // 当前用户登录机房编号

		TawwpMonthPlanVO tawwpMonthPlanVO = null;

		try {
			monthList = (List) request.getSession().getAttribute("monthList");
			if (monthList.size() == 0) {
				monthList = tawwpExecuteMgr.listMonthPlan(userId, deptId);
			}
			/*
			 * monthPlanVOList = tawwpExecuteMgr.listDayExecuteView(currUser,
			 * deptId, roomId, year, month, "own", inspectionFlg);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap monthplanvoHash = new LinkedHashMap(); // 按月计划为单位分别保存单个月度作业计划执行内容(整体)VO对象集合
		TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
		if (monthList != null) {
			String stubFlag = "own";
			userId = currUser; // 当前用户�
			// 当前用户登录机房编号
			String dutyStartDate = "";
			String dutyEndDate = "";

			for (int i = 0; i < monthList.size(); i++) {
				TawwpMonthPlan tawwpMonthPlan = (TawwpMonthPlan) monthList
						.get(i);
				try {
					tawwpMonthPlanVO = new TawwpMonthPlanVO();
					MyBeanUtils.copyPropertiesFromDBToPage(tawwpMonthPlanVO,
							tawwpMonthPlan);
					tawwpMonthPlanVO
							.setExecuteTypeName(TawwpMonthPlanVO
									.getExecuteTypeName(Integer
											.parseInt(tawwpMonthPlanVO
													.getExecuteType())));
					// 获取部门名称
					tawwpMonthPlanVO.setDeptName(tawwpUtilDAO
							.getDeptName(tawwpMonthPlanVO.getDeptId()));
					// 获取系统类型名称
					tawwpMonthPlanVO.setSysTypeName(tawwpUtilDAO
							.getSysTypeName(tawwpMonthPlanVO.getSysTypeId()));
					// 获取网元类型名称
					tawwpMonthPlanVO.setNetTypeName(tawwpUtilDAO
							.getNetTypeName(tawwpMonthPlanVO.getNetTypeId()));
					// 获取创建人姓�?
					tawwpMonthPlanVO.setUserName(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getCruser()));
					// 获取制定状态名�?
					tawwpMonthPlanVO.setConstituteStateName(TawwpMonthPlanVO
							.getConstituteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getConstituteState())));
					// 获取执行状态名�?
					tawwpMonthPlanVO.setExecuteStateName(TawwpMonthPlanVO
							.getExecuteStateName(Integer
									.parseInt(tawwpMonthPlanVO
											.getExecuteState())));
					// 获取执行负责人姓�?
					tawwpMonthPlanVO.setPrincipal(tawwpUtilDAO
							.getUserName(tawwpMonthPlanVO.getPrincipal()));
					// 获取网元名称
					if (tawwpMonthPlan.getTawwpNet() != null) {
						tawwpMonthPlanVO.setNetName(StaticMethod
								.null2String(tawwpMonthPlan.getTawwpNet()
										.getName()));
					} else {
						tawwpMonthPlanVO.setNetName("无网元");
					}
					year = tawwpMonthPlanVO.getYearFlag();
					month = tawwpMonthPlanVO.getMonthFlag();
					String monthplanid = tawwpMonthPlanVO.getId();
					// 获取单个月度作业计划执行内容(整体)VO对象集合
					List executeContentVOList = tawwpExecuteMgr
							.loadExecuteContentUsersView(userId, deptId,
									roomId, monthplanid, dutyStartDate,
									dutyEndDate, flag, stubFlag, date, year,
									month);

					monthplanvoHash.put(tawwpMonthPlanVO, executeContentVOList);
					request.setAttribute("flag", flag);
					request.setAttribute("curruser", currUser);
					request.setAttribute("date", date);
					request.setAttribute("years", year + "-" + month + "-"
							+ date);
					request.setAttribute("monthplanvoHash", monthplanvoHash);
					// monthplancontentHash.put(tawwpmonthplanvo, contentHash);
				} catch (Exception e) {
					e.printStackTrace();
					return mapping.findForward("failure"); // 转向错误页面
				}
			}
		}
		return mapping.findForward("success");
	}

	/**
	 * 显示值班执行作业计划管理(列表)首页Action（广西网元合并填写版本）
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
	private ActionForward performGXDutyExecuteList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String roomId = saveSessionBeanForm.getRoomId(); // 当前机房编号
		// String dutyDate =
		// saveSessionBeanForm.getStartDuty()+"-->"+saveSessionBeanForm.getEndDuty();
		// //值班的时间段

		// 初始化数�?
		ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
		List monthPlanVOList = null;
		String dutyStartDate = "";
		String dutyEndDate = "";
		List listKey = new ArrayList();
		String monthflag=StaticMethod.nullObject2String(request.getParameter("month"));
		String yearflag=StaticMethod.nullObject2String(request.getParameter("year"));
		if("".equals(monthflag)){
			monthflag=TawwpUtil.getCurrentDateTime("MM");
			
		}
		if("".equals(yearflag)){
			yearflag=TawwpUtil.getCurrentDateTime("yyyy");
		}

		boolean s = false;
		// 获取当前年、月
		try {

			// 判断值班状�?
			if (saveSessionBeanForm.getStartDuty() != null
					&& saveSessionBeanForm.getEndDuty() != null) {
				dutyStartDate = StaticMethod.getCurrentDateTime(
						saveSessionBeanForm.getStartDuty(),
						"yyyy-MM-dd HH:mm:ss")
						+ "";
				dutyEndDate = StaticMethod
						.getCurrentDateTime(saveSessionBeanForm.getEndDuty(),
								"yyyy-MM-dd HH:mm:ss")
						+ "";

			} else {
				// 如果当前用户未处于值班状�?
				request.setAttribute("error", "noduty");
				// 转向提示页面
				return mapping.findForward("failure");

			}

			// 获取执行作业计划集合
			monthPlanVOList = tawwpExecuteMgr.listDutyExecutePlanNew(String
					.valueOf(roomId), yearflag, monthflag, dutyStartDate,
					dutyEndDate);
			if (monthPlanVOList.size() != 0) {
				for(int i=0;i<monthPlanVOList.size();i++) {
					TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO) (monthPlanVOList.get(i));
					for(int j=0;j<listKey.size();j++){
						TawwpMonthPlanVO tempMonthPlanVO = (TawwpMonthPlanVO)listKey.get(j);
			            if(tawwpMonthPlanVO.getName().equals(tempMonthPlanVO.getName())){
			            		s=true;
				        }
					}
					if(s){
						s=false;
						continue;
					}
					listKey.add(tawwpMonthPlanVO);
					Collections.sort(listKey, new Comparator() {
						public int compare(Object o1, Object o2) {
							TawwpMonthPlanVO p1 = (TawwpMonthPlanVO) o1;
							TawwpMonthPlanVO p2 = (TawwpMonthPlanVO) o2;
							int k = -1;
							if(p1.getName().compareTo(p2.getName())>0){
								k = 1;
							}else if(p1.getName().compareTo(p2.getName())==0){
								if (p1.getId().compareTo(p2.getId()) > 0) {
									k = 1;
								}else{
									k = -1;
								}
							}else 
								if(p1.getName().compareTo(p2.getName())<0){
									k = -1;
							}
							return k;

						}
					});
				}
			}
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(0);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(1);
			// tawwpYearPlan=(TawwpYearPlan)listKey.get(2);
			// 为页面显示准备数�?
			request.setAttribute("year", yearflag);
			request.setAttribute("month", monthflag);
			request.setAttribute("monthplanvolist", listKey);
			// 转向值班执行作业计划管理(列表)页面
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
			return mapping.findForward("failure"); // 转向错误页面
		}
	}

	/**
	 * 获取执行项附件的信息
	 * 
	 * @param _tawwpExecuteContent
	 *            TawwpExecuteContent 鎵ц浣滀笟璁″垝鎵ц鍐呭(鏁翠綋)
	 * @return String 闄勪欢淇℃伅
	 */
	private String getFileStrOfContent(TawwpExecuteContent _tawwpExecuteContent) {
		// 闄勪欢淇℃伅瀛楃涓�
		String fileStr = "";
		// 闄勪欢闆嗗悎
		Iterator tawwpExecuteFlies = null;
		// 闄勪欢瀵硅薄
		TawwpExecuteFile tawwpExecuteFile = null;
		// 鑾峰彇闄勪欢闆嗗悎
		tawwpExecuteFlies = _tawwpExecuteContent.getTawwpExecuteFiles()
				.iterator();
		while (tawwpExecuteFlies.hasNext()) {
			tawwpExecuteFile = (TawwpExecuteFile) tawwpExecuteFlies.next();
			// 缁勫悎闄勪欢淇℃伅瀛楃涓�
			fileStr += (tawwpExecuteFile.getFileName() + "@"
					+ "../tawwpexecute/filedown.do?fileId="
					+ tawwpExecuteFile.getFileCodeName() + ",");
		}
		if (fileStr != null && !"".equals(fileStr)) {
			// 闄勪欢鏁伴噺涓嶄负闆�
			fileStr = fileStr.substring(0, fileStr.length() - 1);
		}
		// 杩斿洖闄勪欢淇℃伅瀛楃涓�
		return fileStr;
	}

}
