package com.boco.eoms.wap.platform.workplan.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.util.DeptMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.model.TawwpExecuteContentUser;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;
import com.boco.eoms.workplan.vo.TawwpStubUserVO;

/**
 * 作业计划action
 * 
 * @author lizhigang
 * 
 */
public class WorkPlanAction extends BaseAction {

	/**
	 * 返回查询发布类型为部门的,发给当前部门所有父部门的sql;以及发布类型为用户的,发给当前用户的sql.(以后如果有发布信息给角色的，可以在这里添加相应的处理)
	 * 
	 * @param deptIds
	 * @param userid
	 * @return 返回查询发布类型为部门的,发给当前部门所有父部门的sql;以及发布类型为用户的,发给当前用户的sql.(以后如果有发布信息给角色的，可以在这里添加相应的处理)
	 */
	private String getPublishRangeQueryStr(String deptIds, String userid) {
		// 若无deptIds则代表查询全部
		if (deptIds == null || "".equals(deptIds)) {
			return "";
		}
		return " and ((threadPermissionOrg.orgId in " + deptIds
				+ " and threadPermissionOrg.orgType='"
				+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "')"
				+ "or(threadPermissionOrg.orgId ='" + userid
				+ "' and threadPermissionOrg.orgType='"
				+ StaticVariable.PRIV_ASSIGNTYPE_USER + "'))"
				+ " and thread.id=threadPermissionOrg.threadId";
	}

	/**
	 * 将部门列表转换为(deptId1,dpetId2,...)形式
	 * 
	 * @param depts
	 * @return
	 */
	private String depts2str(List depts) {
		String result = "(";
		if (depts != null) {
			for (Iterator it = depts.iterator(); it.hasNext();) {
				TawSystemDept dept = (TawSystemDept) it.next();
				result += ("'" + dept.getDeptId() + "',");
			}
		}
		if (result.indexOf(",") > -1) {
			result = result.substring(0, result.length() - 1);
		}
		return result + ")";
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
	 * 显示某版块下的信息信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward atomlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 版块id
		String forumsId = request.getParameter("forumsId");
		final Integer pageIndex = new Integer(request.getParameter("curPage"));
		final Integer pageSize = new Integer(request.getParameter("pageSize"));
		Hashtable monthPlanVOHash = performDailyExecuteList(mapping, form, request,
				response);
		Enumeration hashKeys = null;

		List workPlanList = new ArrayList();
		TawwpMonthPlan tawwpMonthPlan = null;
		if (monthPlanVOHash.size() != 0) {
			hashKeys = monthPlanVOHash.keys();
			while (hashKeys.hasMoreElements()) {
				tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());

				workPlanList.add(tawwpMonthPlan);
				tawwpMonthPlan = null;
			}
			Collections.sort(workPlanList);
		}

		TawwpMonthPlanVO tawwpMonthPlanVO = null;
		// 加上时间段限制过滤(开始)
		Factory factory = Abdera.getNewFactory();
		Feed feed = factory.newFeed();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		TawwpMonthPlan tawwpMonthPlan2 = null;
		if (pageSize.intValue() > 0) {
			for(int i=0;i<workPlanList.size()&&i<= pageSize.intValue();i++){
				tawwpMonthPlan2 = (TawwpMonthPlan)workPlanList.get(i);
				tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOHash.get(tawwpMonthPlan2);
				Entry entry = feed.insertEntry();
				
				entry.setTitle(tawwpMonthPlanVO.getName());
				String url = request.getRequestURI() + "?id=" + tawwpMonthPlanVO.getId()
				+ "&method=executeAdds&flag=daily&monthexecuteuserid=" + tawwpMonthPlanVO.getMonthExecuteUserId();
				entry.setPublished(dateFormat.parse(tawwpMonthPlanVO.getCrtime()));
				entry.setLanguage("infopub");
				entry.setContent(url);
				
			}
		} else {
			for(int i=0;i<workPlanList.size();i++){
				tawwpMonthPlan2 = (TawwpMonthPlan)workPlanList.get(i);
				tawwpMonthPlanVO = (TawwpMonthPlanVO)monthPlanVOHash.get(tawwpMonthPlan2);
				Entry entry = feed.insertEntry();
				entry.setTitle(tawwpMonthPlanVO.getName());
				String url = request.getRequestURI() + "?id=" + tawwpMonthPlanVO.getId()
				+ "&method=executeAdds&flag=daily&monthexecuteuserid=" + tawwpMonthPlanVO.getMonthExecuteUserId();
				entry.setPublished(dateFormat.parse(tawwpMonthPlanVO.getCrtime()));
				entry.setLanguage("infopub");
				entry.setContent(url);
			}
		}
		request.setAttribute(InfopubConstants.THREAD_LIST, workPlanList);
		// 加上时间段限制过滤(结束)
		// request.setAttribute(InfopubConstants.THREAD_LIST, list);
		// request.setAttribute("resultSize", map.get("total"));
		String resultSize = "0";
		if (workPlanList.size() != 0) {
			resultSize = workPlanList.size() + "";
		}
		request.setAttribute("resultSize", new Integer(resultSize));
		request.setAttribute("pageSize", pageSize);

		OutputStream os = response.getOutputStream();
		PrintStream ps = new PrintStream(os);
		feed.getDocument().writeTo(ps);

		return null;
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
	private Hashtable performDailyExecuteList(ActionMapping mapping,
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
		List listKey = null;
		// 获取执行作业计划集合
		try {
			monthPlanVOHash = tawwpExecuteMgr.listExecutePlanNew(userId, String
					.valueOf(deptId));

			// 如果代理信息存在
			if (stubUserList != null) {

				for (int i = 0; i < stubUserList.size(); i++) {
					// 获取代理信息VO对象
					tawwpStubUserVO = (TawwpStubUserVO) stubUserList.get(i);
					// 获取代理申请人需要执行的月度作业计划
					tempHash = new Hashtable();
					tempHash = tawwpExecuteMgr.listExecutePlan(tawwpStubUserVO
							.getCruser(), String.valueOf(deptId));
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

			Enumeration hashKeys = null;

			listKey = new ArrayList();
			TawwpMonthPlan tawwpMonthPlan = null;
			if (monthPlanVOHash.size() != 0) {
				hashKeys = monthPlanVOHash.keys();
				while (hashKeys.hasMoreElements()) {
					tawwpMonthPlan = (TawwpMonthPlan) (hashKeys.nextElement());

					listKey.add(tawwpMonthPlan);
					tawwpMonthPlan = null;
				}
				Collections.sort(listKey);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monthPlanVOHash;
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
	public ActionForward executeAdds(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String currUser = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String monthPlanId = request.getParameter("id"); // 月度作业计划编号
		String flag = request.getParameter("flag"); // 标志日常还是值班的批量填�?
		String userByStub = TawwpUtil
				.getRequestValue(request, "userbystub", ""); // 代理用户�?
		String date = TawwpUtil.getRequestValue(request, "date", ""); // 批量填写日期

		//如果作业计划没确认，确认操作
		this.performConfirm(mapping, actionForm, request, response);
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
			return mapping.findForward("showDetail");
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
	public ActionForward executeSaves(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取当前用户的session中的信息
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String userId = saveSessionBeanForm.getUserid(); // 当前用户�?

		// 获取页面传递过来的参数
		String contentIdStr = request.getParameter("contentidstr"); // 执行作业计划执行内容(整体)编号字符�?
		String executeType = request.getParameter("executetype"); // 执行类型

		// 初始化数�?

		String content = "";
		String formDataId = "";
		String fileName = "";

		String cruser = "";
		String stubUser = "";

		String contentUserId = "";

		String writeDate = "";
		Hashtable executeContentUserHash = new Hashtable();
		TawwpExecuteContentUser tawwpExecuteContentUser = null;

		// 处理执行作业计划执行内容(整体)编号字符�?
		String[] tempStr = contentIdStr.split(",");

		try {
			for (int i = 0; i < tempStr.length; i++) {

				// 获取从页面传递过来的参数
				content = request.getParameter("content" + i);
				formDataId = request.getParameter("formdataid" + i);
				fileName = request.getParameter("filename" + i);
				contentUserId = request
						.getParameter("executecontentuserid" + i);
				cruser = request.getParameter("userid" + i);
				stubUser = request.getParameter("stubuser" + i);
				writeDate = request.getParameter("writeDate" + i);
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
					// 将执行作业计划执行内�?单一用户)对象添加到Hashtable�?
					executeContentUserHash.put(tempStr[i],
							tawwpExecuteContentUser);

				}
			}

				ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) getBean("tawwpExecuteMgr");
				// 保存日常批量填写内容
				// tawwpExecuteMgr.addExecuteContentUsersSave(
				// executeContentUserHash, userId, executeType);

				mgr.addExecuteContentUsersSave(executeContentUserHash, userId,
						executeType);

				// 转向日常执行作业计划列表管理页面
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
	 * 确认作业计划 lizhigang
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private void performConfirm(ActionMapping mapping,
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
		} catch (Exception e) {
			generalError(request, e); // 将错误信息加入到消息队列、写入日�?
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"submit.title.failure"));
			saveMessages(request, messages);
		}
	}

}
