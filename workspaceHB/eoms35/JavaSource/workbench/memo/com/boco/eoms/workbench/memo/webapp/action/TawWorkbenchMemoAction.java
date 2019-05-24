package com.boco.eoms.workbench.memo.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoManager;
import com.boco.eoms.workbench.memo.util.MemoPage;

import com.boco.eoms.workbench.memo.webapp.form.TawWorkbenchMemoForm;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.workbench.memo.bo.TawWorkbenchMemoBO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-24 10:04:17
 * </p>
 * 
 * @author 龚玉峰
 * @version 1.0
 */
public final class TawWorkbenchMemoAction extends BaseAction {

	// 定义页数长度
	private static int PAGE_LENGTH = 15;

	TawWorkbenchMemoBO memobo = TawWorkbenchMemoBO.getInstance();

	// 撤销
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawWorkbenchMemoForm tawWorkbenchMemoForm = (TawWorkbenchMemoForm) form;
		try {
			// Exceptions are caught by ActionExceptionHandler
			ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
			mgr.removeTawWorkbenchMemo(tawWorkbenchMemoForm.getId());

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.deleted"));

			// save messages in session, so they'll survive the redirect
			saveMessages(request.getSession(), messages);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return search(mapping, form, request, response);
	}

	/**
	 * 
	 * @see 修改派发便签
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
		TawWorkbenchMemoForm tawWorkbenchMemoForm = (TawWorkbenchMemoForm) form;
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
			String id = StaticMethod.nullObject2String(request
					.getParameter("id"));
			String str = request.getParameter("folderPath");

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String workSerial = sessionform.getWorkSerial();
			request.setAttribute("folderPath", str);

			if (tawWorkbenchMemoForm.getId() != null) {
				ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
				if (id != null && !id.equals("")) {
					TawWorkbenchMemo tawWorkbenchMemo = mgr
							.getTawWorkbenchMemo(id);

					if (tawWorkbenchMemo != null) {
						if (workSerial.equals("0") && (str.length() < 2)) {
							request.setAttribute("tawWorkbenchMemo",
									tawWorkbenchMemo);
							return mapping.findForward("detailContent");
						}
						tawWorkbenchMemoForm = (TawWorkbenchMemoForm) convert(tawWorkbenchMemo);
					}
				}
				String sendStr_ = tawWorkbenchMemoForm.getSendflag();
				request.setAttribute("s", sendStr_);
				request.setAttribute("memoId", id);
				/*
				 * tawWorkbenchMemoForm = (TawWorkbenchMemoForm)
				 * convert(tawWorkbenchMemo);
				 */
				updateFormBean(mapping, request, tawWorkbenchMemoForm);
			} else {

				String strSub = str.substring(1, 2);
				tawWorkbenchMemoForm.setLevel(strSub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("edit");
	}

	/**
	 * 
	 * @see 保存派发便签
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

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();

		TawWorkbenchMemoForm tawWorkbenchMemoForm = (TawWorkbenchMemoForm) form;
		boolean isNew = ("".equals(tawWorkbenchMemoForm.getId()) || tawWorkbenchMemoForm
				.getId() == null);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String str = request.getParameter("folderPath");
		final String userId = sessionform.getUserid();
		ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
		TawWorkbenchMemo tawWorkbenchMemo = (TawWorkbenchMemo) convert(tawWorkbenchMemoForm);
		// 判断是否发送 如果是就先发总在入库 如果不是则直接入库
		if (tawWorkbenchMemoForm.getSendflag() != null
				&& tawWorkbenchMemoForm.getSendflag().equals("1")) {
			String creattime = StaticMethod.getLocalString(); // dangqianshijian
			tawWorkbenchMemo.setCreattime(creattime);
			tawWorkbenchMemo.setUserid(sessionform.getUserid());
			tawWorkbenchMemo.setDeleted("0");
			tawWorkbenchMemo.setSendtime(StaticMethod.getLocalString());
			String recieverOld = request.getParameter("recievers");
			String sendManner = tawWorkbenchMemo.getSendmanner();
			String recievers = tawWorkbenchMemoForm.getReciever();
			if (recieverOld.equals(recievers)) {
				request.setAttribute("falseMessage", "便签发送错误,请选择发送人");
				return mapping.findForward("false");
			}
			// 通过id得到用户的名称
			String reciever = memobo.getUserStrByIds(tawWorkbenchMemo
					.getReciever());
			String[] user = reciever.split(",");
			for (int i = 0; i < user.length; i++) {
				if (!mgr.ifSystemUser(user[i]) && sendManner.equals("3")) {
					request.setAttribute("falseMessage", "便签发送错误,您发送的'"
							+ user[i] + "'不为系统内部人员。不能通过【系统】发送此便签");
					return mapping.findForward("false");
				}
			}
			if (recieverOld != null && !"".equals(recieverOld)) {

				tawWorkbenchMemo.setReciever(recieverOld + "," + reciever);

			} else {
				tawWorkbenchMemo.setReciever(reciever);
			}

			try {

				mgr.sendMemo(tawWorkbenchMemo, recievers, sendManner,
						sessionform.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("falseMessage", "便签发送错误");
				return mapping.findForward("false");
			}
			try {
				mgr.saveTawWorkbenchMemo(tawWorkbenchMemo);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("falseMessage", "便签保存错误");
				return mapping.findForward("false");
			}

		} else {
			String creattime = StaticMethod.getLocalString(); // dangqianshijian
			tawWorkbenchMemo.setCreattime(creattime);
			tawWorkbenchMemo.setUserid(userId);
			tawWorkbenchMemo.setDeleted("0");
			tawWorkbenchMemo.setSendflag("0");
			mgr.saveTawWorkbenchMemo(tawWorkbenchMemo);
		}
		String level = "";
		level = tawWorkbenchMemo.getLevel();
		request.setAttribute("level", level);
		request.setAttribute("folderPath", str);
		// add success messages
		if (isNew) {

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("success");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.updated"));
			saveMessages(request, messages);

			return mapping.findForward("success");
		}

	}

	/**
	 * 
	 * @see 保存派发便签
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawWorkbenchMemoForm tawWorkbenchMemoForm = (TawWorkbenchMemoForm) form;
		boolean isNew = ("".equals(tawWorkbenchMemoForm.getId()) || tawWorkbenchMemoForm
				.getId() == null);
		String str = request.getParameter("folderPath");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		request.setAttribute("folderPath", str);
		ITawWorkbenchMemoManager mgrr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
		TawWorkbenchMemo tawWorkbenchMemo = (TawWorkbenchMemo) convert(tawWorkbenchMemoForm);
		String creattime = StaticMethod.getLocalString(); // 当前时间
		tawWorkbenchMemo.setCreattime(creattime);
		tawWorkbenchMemo.setUserid(sessionform.getUserid());
		tawWorkbenchMemo.setSendflag("1");
		tawWorkbenchMemo.setDeleted("0");
		tawWorkbenchMemo.setSendtime(StaticMethod.getLocalString());
		String level = "";
		level = tawWorkbenchMemo.getLevel();
		request.setAttribute("level", level);

		String sendManner = tawWorkbenchMemo.getSendmanner();
		String recievers = tawWorkbenchMemoForm.getReciever();
		String reciever = "";
		if (str.length() < 2) {
			reciever = memobo.getUserStrByUserIds(tawWorkbenchMemo
					.getReciever());
		} else {
			reciever = memobo.getUserStrByIds(tawWorkbenchMemo.getReciever());
		}
		System.out.println("reciever============" + reciever);
		// 增加判断派发人是否为系统内的人员
		String[] user = reciever.split(",");
		for (int i = 0; i < user.length; i++) {
			if (!mgrr.ifSystemUser(user[i]) && sendManner.equals("3")) {
				request.setAttribute("falseMessage", "便签发送错误,您发送的'" + user[i]
						+ "'不为系统内部人员。不能通过【系统】发送此便签");
				return mapping.findForward("false");
			}
		}
		tawWorkbenchMemo.setReciever(reciever);

		// 保存到tawWorkbenchMemo表
		String id = mgrr.saveTawWorkbenchMemoReturnId(tawWorkbenchMemo);
		tawWorkbenchMemo.setId(id);

		try {
			if (str.length() >= 2) {
				mgrr.sendMemo(tawWorkbenchMemo, recievers, sendManner,
						sessionform.getUsername());
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", "便签发送错误");
			return mapping.findForward("false");
		}
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("success");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawWorkbenchMemo.updated"));
			saveMessages(request, messages);

			return mapping.findForward("success");
		}

		// add success messages

	}

	/**
	 * 
	 * @see 根据条件查询便签结果集
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
		// --------------用于分页，得到当前页号-------------
		int offset;
		int length = PAGE_LENGTH;
		try {
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_WORKBENCH_MEMOLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			TawWorkbenchMemo tawWorkbenchMemo = null;
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String str = "";
			if (request.getParameter("folderPath").length() < 2) {
				str = request.getAttribute("level").toString();
			} else {
				str = request.getParameter("folderPath").substring(1, 2);
			}
			final String userId = sessionform.getUserid();
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			// 得到查询的条件
			String whereStr = " where userid = '" + userId + "' and level = '"
					+ str + "'";
			List tawWorkbenchMemoList = new ArrayList();
			ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
			Map map = (Map) mgr.getTawWorkbenchMemos(pageIndex, pageSize,
					whereStr);

			List list = (List) map.get("result");
			// 分页
			for (int i = offset; i < (length + offset) && i < list.size(); i++) {
				tawWorkbenchMemo = (TawWorkbenchMemo) list.get(i);

				tawWorkbenchMemoList.add(tawWorkbenchMemo);
			}

			// 每页显示条数

			request.setAttribute(DutyConstacts.TAW_WORKBENCH_MEMOLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("folderPathStr", "0" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 根据条件查询便签结果集
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward searchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int offset;
		int length = PAGE_LENGTH;
		try {
			String whereStr = "";
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}

			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_WORKBENCH_MEMOLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = sessionform.getUserid();
			String str = request.getParameter("folderPath");
			whereStr = request.getParameter("whereStr");
			request.setAttribute("folderPath", str);
			// TawWorkbenchMemo tawWorkbenchMemo = null;
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			// List tawWorkbenchMemoList = new ArrayList();
			buffer.append(" where 1=1 and userid = '" + userId + "'");
			bufferpage.append("method=searchList");
			String title = StaticMethod.null2String(request
					.getParameter("title"));
			bufferpage.append("&title=" + title + "");
			if (!title.equals("")) {
				buffer.append(" and title like '%" + title + "%'");

			}
			String content = StaticMethod.null2String(request
					.getParameter("content"));
			bufferpage.append("&content=" + content + "");
			if (!content.equals("")) {
				buffer.append(" and content like '%" + content + "%'");
			}
			String creatBeginTime = StaticMethod.null2String(request
					.getParameter("creatBeginTime"));
			bufferpage.append("&creatBeginTime=" + creatBeginTime + "");
			if (!creatBeginTime.equals("")) {
				buffer.append(" and creattime >= '" + creatBeginTime + "'");
			}
			String creatEndTime = StaticMethod.null2String(request
					.getParameter("creatEndTime"));
			bufferpage.append("&creatEndTime=" + creatEndTime + "");
			if (!creatEndTime.equals("")) {
				buffer.append(" and creattime <= '" + creatEndTime + "'");
			}
			String level = StaticMethod.null2String(request
					.getParameter("level"));
			bufferpage.append("&level=" + level + "");
			if (level.equals("1") || level.equals("2") || level.equals("3")
					|| level.equals("4")) {
				buffer.append(" and level = " + level + "");
			}
			String sendflag = StaticMethod.null2String(request
					.getParameter("sendflag"));
			bufferpage.append("&sendflag=" + sendflag + "");
			if (sendflag.equals("0") || sendflag.equals("1")) {
				buffer.append(" and sendflag = " + sendflag + "");
			}
			if (sendflag.equals("1")) {
				String reciever = StaticMethod.null2String(request
						.getParameter("reciever"));
				bufferpage.append("&reciever=" + reciever + "");
				if (!reciever.equals("")) {
					String[] recieverAry = reciever.split(",");
					for (int i = 0; i < recieverAry.length; i++) {
						buffer.append(" and reciever like '" + recieverAry[i]
								+ "'");
					}
				}
				String sendBeginTime = StaticMethod.null2String(request
						.getParameter("sendBeginTime"));
				bufferpage.append("&sendBeginTime=" + sendBeginTime + "");
				if (!sendBeginTime.equals("")) {
					buffer.append(" and sendtime >= '" + sendBeginTime + "'");

				}
				String sendEndTime = StaticMethod.null2String(request
						.getParameter("sendEndTime"));
				bufferpage.append("&sendEndTime=" + sendEndTime + "");
				if (!sendEndTime.equals("")) {
					buffer.append(" and sendtime <= '" + sendEndTime + "'");
				}
			}
			bufferpage.append("&folderPath=" + str);
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
			Map map = (Map) mgr.getTawWorkbenchMemos(pageIndex, pageSize,
					whereStr);
			List list = (List) map.get("result");
			// 分叶
			/**
			 * for (int i = offset; i < (length + offset) && i < list.size();
			 * i++) { tawWorkbenchMemo = (TawWorkbenchMemo) list.get(i); if
			 * (tawWorkbenchMemo.getLevel() == null) {
			 * tawWorkbenchMemo.setLevel(""); } if
			 * (tawWorkbenchMemo.getSendflag() == null) {
			 * tawWorkbenchMemo.setSendflag(""); } if
			 * (tawWorkbenchMemo.getSendmanner() == null) {
			 * tawWorkbenchMemo.setSendmanner(""); }
			 * tawWorkbenchMemoList.add(tawWorkbenchMemo); }
			 */

			String url = request.getContextPath() + "/workbench/memo"
					+ mapping.getPath() + ".do";
			int size = ((Integer) map.get("total")).intValue();
			// 每页显示条数

			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size,
					length, url, pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			// Iterator listIt = tawWorkbenchMemoList.iterator();
			// request.setAttribute("listIt", listIt);
			request.setAttribute(DutyConstacts.TAW_WORKBENCH_MEMOLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("searchList");
	}

	/**
	 * 
	 * @see 发送查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward xSearchRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int offset;
		int length = PAGE_LENGTH;
		try {
			String whereStr = "";
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}

			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_WORKBENCH_MEMOLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = sessionform.getUserid();
			String str = request.getParameter("folderPath");
			whereStr = request.getParameter("whereStr");
			request.setAttribute("folderPath", str);
			TawWorkbenchMemo tawWorkbenchMemo = null;
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			List tawWorkbenchMemoList = new ArrayList();
			buffer.append(" where 1= 1 and userid ='"+userId+"'   and sendflag = 1");
			bufferpage.append("method=xSearchRecord");

			String sendBeginTime = StaticMethod.null2String(request
					.getParameter("sendBeginTime"));
			bufferpage.append("&sendBeginTime=" + sendBeginTime + "");
			if (!sendBeginTime.equals("")) {
				buffer.append(" and sendtime >= '" + sendBeginTime + "'");

			}
			String sendEndTime = StaticMethod.null2String(request
					.getParameter("sendEndTime"));
			bufferpage.append("&sendEndTime=" + sendEndTime + "");
			if (!sendEndTime.equals("")) {
				buffer.append(" and sendtime <= '" + sendEndTime + "'");
			}

			bufferpage.append("&folderPath=" + str);
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			System.out.println(whereStr);
			String pageStr = bufferpage.toString();
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
			Map map = (Map) mgr.getTawWorkbenchMemos(pageIndex, pageSize,
					whereStr);
			List list = (List) map.get("result");

			// 分叶
			/*
			 * for (int i = offset; i < (length + offset) && i < list.size();
			 * i++) { tawWorkbenchMemo = (TawWorkbenchMemo) list.get(i); if
			 * (tawWorkbenchMemo.getLevel() == null) {
			 * tawWorkbenchMemo.setLevel(""); } if
			 * (tawWorkbenchMemo.getSendflag() == null) {
			 * tawWorkbenchMemo.setSendflag(""); } if
			 * (tawWorkbenchMemo.getSendmanner() == null) {
			 * tawWorkbenchMemo.setSendmanner(""); }
			 * tawWorkbenchMemoList.add(tawWorkbenchMemo); }
			 */

			String url = request.getContextPath() + "/workbench/memo"
					+ mapping.getPath() + ".do";
			int size = ((Integer) map.get("total")).intValue();
			// final Integer pageSize =
			// UtilMgrLocator.getEOMSAttributes().getPageSize();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size,
					length, url, pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			// Iterator listIt = tawWorkbenchMemoList.iterator();
			// request.setAttribute("listIt", listIt);
			request.setAttribute(DutyConstacts.TAW_WORKBENCH_MEMOLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("xSearchList");
	}

	/**
	 * 
	 * @see 我收到的便签查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward xSearchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int offset;
		int length = PAGE_LENGTH;
		try {
			String whereStr = "";
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_WORKBENCH_MEMOLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = sessionform.getUsername();
			String str = request.getParameter("folderPath");
			whereStr = request.getParameter("whereStr");
			request.setAttribute("folderPath", str);
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();

			buffer.append(" where 1= 1 and sendmanner = '3' and reciever like '%"
					+ userId + "%'");
			bufferpage.append("method=xSearchList");
			String title = StaticMethod.null2String(request
					.getParameter("title"));
			bufferpage.append("&title=" + title + "");
			if (title != "") {
				buffer.append(" and title like '%" + title + "%'");
			}
			String content = StaticMethod.null2String(request
					.getParameter("content"));
			bufferpage.append("&content=" + content + "");
			if (!content.equals("")) {
				buffer.append(" and content like '%" + content + "%'");
			}
			String creatBeginTime = StaticMethod.null2String(request
					.getParameter("creatBeginTime"));
			bufferpage.append("&creatBeginTime=" + creatBeginTime + "");
			if (!creatBeginTime.equals("")) {
				buffer.append(" and creattime >= '" + creatBeginTime + "'");
			}
			String creatEndTime = StaticMethod.null2String(request
					.getParameter("creatEndTime"));
			bufferpage.append("&creatEndTime=" + creatEndTime + "");
			if (!creatEndTime.equals("")) {
				buffer.append(" and creattime <= '" + creatEndTime + "'");
			}
			String creatUserId = StaticMethod.null2String(request
					.getParameter("userid"));
			bufferpage.append("&userid=" + creatUserId + "");
			if (!creatUserId.equals("")) {
				buffer.append(" and userid = '" + creatUserId + "'");
			}
			String level = StaticMethod.null2String(request
					.getParameter("level"));
			bufferpage.append("&level=" + level + "");
			if (level.equals("1") || level.equals("2") || level.equals("3")
					|| level.equals("4")) {
				buffer.append(" and level = " + level + "");
			}

			String sendBeginTime = StaticMethod.null2String(request
					.getParameter("sendBeginTime"));
			bufferpage.append("&sendBeginTime=" + sendBeginTime + "");
			if (!sendBeginTime.equals("")) {
				buffer.append(" and sendtime >= '" + sendBeginTime + "'");

			}
			String sendEndTime = StaticMethod.null2String(request
					.getParameter("sendEndTime"));
			bufferpage.append("&sendEndTime=" + sendEndTime + "");
			if (!sendEndTime.equals("")) {
				buffer.append(" and sendtime <= '" + sendEndTime + "'");
			}

			bufferpage.append("&folderPath=" + str);
			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);

			String pageStr = bufferpage.toString();
			ITawWorkbenchMemoManager mgr = (ITawWorkbenchMemoManager) getBean("ItawWorkbenchMemoManager");
			Map map = (Map) mgr.getTawWorkbenchMemos(pageIndex, pageSize,
					whereStr);
			List list = (List) map.get("result");
			List memoList = new ArrayList();
			TawWorkbenchMemo tawWorkbenchMemo = null;
			for (Iterator it = list.iterator(); it.hasNext();) {
				tawWorkbenchMemo = (TawWorkbenchMemo) it.next();
				if (tawWorkbenchMemo.getReciever() != null) {
					String[] userArray = tawWorkbenchMemo.getReciever().split(
							",");
					for (int i = 0; i < userArray.length; i++) {
						if (userId.equals(userArray[i])) {
							memoList.add(tawWorkbenchMemo);
						}
					}
				}
			}
			// 分叶
			/*
			 * for (int i = offset; i < (length + offset) && i < list.size();
			 * i++) { tawWorkbenchMemo = (TawWorkbenchMemo) list.get(i); if
			 * (tawWorkbenchMemo.getLevel() == null) {
			 * tawWorkbenchMemo.setLevel(""); } if
			 * (tawWorkbenchMemo.getSendflag() == null) {
			 * tawWorkbenchMemo.setSendflag(""); } if
			 * (tawWorkbenchMemo.getSendmanner() == null) {
			 * tawWorkbenchMemo.setSendmanner(""); }
			 * tawWorkbenchMemoList.add(tawWorkbenchMemo); }
			 */
			String url = request.getContextPath() + "/workbench/memo"
					+ mapping.getPath() + ".do";
			int size = ((Integer) map.get("total")).intValue();

			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size,
					length, url, pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			// Iterator listIt = tawWorkbenchMemoList.iterator();
			// request.setAttribute("listIt", listIt);
			request
					.setAttribute(DutyConstacts.TAW_WORKBENCH_MEMOLIST,
							memoList);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("xSearchreciver");
	}

	public ActionForward memoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("memoList");
	}

	public ActionForward xSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String findForward = "";

		String nodeid = request.getParameter("folderPath");
		if (nodeid.equals("3")) {
			findForward = "xSearch";
		} else if (nodeid.equals("4")) {
			findForward = "recordSearch";
		}
		return mapping.findForward(findForward);
	}

	public ActionForward memoSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("memoSearch");
	}

	public ActionForward memoMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("memoMain");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return memoMain(mapping, form, request, response);
	}

	/**
	 * 生成树JSON数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = FilePathProcessor.recoverPath(request
				.getParameter("node"));

		JSONArray jsonRoot = new JSONArray();
		if ("-1".equals(nodeId)) { // 初始化三个节点
			jsonRoot = initNodes();
		} else {
			String nodeType = request.getParameter(UIConstants.JSON_NODETYPE);
			if ("1".equals(nodeType)) {
				jsonRoot = getMyFolderNodes(nodeId);
			}
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private JSONArray initNodes() {
		JSONArray jsonRoot = new JSONArray();
		JSONObject myFolder = new JSONObject();
		myFolder.put("id", "1");
		myFolder.put("text", "我的便签");
		myFolder.put(UIConstants.JSON_NODETYPE, "1");
		myFolder.put("allowChild", false);
		myFolder.put("allowEdit", false);
		myFolder.put("allowDelete", false);
		myFolder.put("allowList", false);
		myFolder.put("allowShare", false);
		myFolder.put("leaf", false);
		myFolder.put("iconCls", "folder");
		JSONObject myShare = new JSONObject();
		myShare.put("id", "2");
		myShare.put("text", "便签查询");
		myShare.put(UIConstants.JSON_NODETYPE, "2");
		myShare.put("allowChild", false);
		myShare.put("allowEdit", false);
		myShare.put("allowDelete", false);
		myShare.put("allowList", true);
		myShare.put("allowShare", false);
		myShare.put("leaf", false);
		myShare.put("iconCls", "folder");
		JSONObject goMemo = new JSONObject();
		goMemo.put("id", "3");
		goMemo.put("text", "我接收到的便签");
		goMemo.put(UIConstants.JSON_NODETYPE, "3");
		goMemo.put("allowChild", false);
		goMemo.put("allowEdit", false);
		goMemo.put("allowDelete", false);
		goMemo.put("allowList", false);
		goMemo.put("allowShare", true);
		goMemo.put("leaf", false);
		goMemo.put("iconCls", "folder");
		JSONObject record = new JSONObject();
		record.put("id", "4");
		record.put("text", "发送记录");
		record.put(UIConstants.JSON_NODETYPE, "4");
		record.put("allowChild", false);
		record.put("allowEdit", false);
		record.put("allowDelete", false);
		record.put("allowList", false);
		record.put("allowShare", true);
		record.put("leaf", false);
		record.put("iconCls", "folder");
		jsonRoot.put(myFolder);
		jsonRoot.put(myShare);
		jsonRoot.put(goMemo);
		jsonRoot.put(record);
		return jsonRoot;
	}

	/**
	 * 
	 * 
	 * @param nodeId
	 * @return
	 */
	private JSONArray getMyFolderNodes(String nodeId) {
		JSONArray jsonRoot = new JSONArray();
		JSONObject myFolder = new JSONObject();
		myFolder.put("id", "01");
		myFolder.put("text", "重要紧急");
		myFolder.put(UIConstants.JSON_NODETYPE, "01");
		myFolder.put("allowChild", true);
		myFolder.put("allowEdit", true);
		myFolder.put("allowDelete", true);
		myFolder.put("allowList", false);
		myFolder.put("allowShare", false);
		myFolder.put("leaf", true);
		myFolder.put("iconCls", "file");
		JSONObject myShare = new JSONObject();
		myShare.put("id", "02");
		myShare.put("text", "重要不紧急");
		myShare.put(UIConstants.JSON_NODETYPE, "02");
		myShare.put("allowChild", true);
		myShare.put("allowEdit", true);
		myShare.put("allowDelete", true);
		myShare.put("allowList", false);
		myShare.put("allowShare", false);
		myShare.put("leaf", true);
		myShare.put("iconCls", "file");
		JSONObject shareToMe = new JSONObject();
		shareToMe.put("id", "03");
		shareToMe.put("text", "紧急不重要");
		shareToMe.put(UIConstants.JSON_NODETYPE, "03");
		shareToMe.put("allowChild", true);
		shareToMe.put("allowEdit", true);
		shareToMe.put("allowDelete", true);
		shareToMe.put("allowList", false);
		shareToMe.put("allowShare", false);
		shareToMe.put("leaf", true);
		shareToMe.put("iconCls", "file");
		JSONObject shareToMe1 = new JSONObject();
		shareToMe1.put("id", "04");
		shareToMe1.put("text", "不重要不紧急");
		shareToMe1.put(UIConstants.JSON_NODETYPE, "04");
		shareToMe1.put("allowChild", true);
		shareToMe1.put("allowEdit", true);
		shareToMe1.put("allowDelete", true);
		shareToMe1.put("allowList", false);
		shareToMe1.put("allowShare", false);
		shareToMe1.put("leaf", true);
		shareToMe1.put("iconCls", "file");
		jsonRoot.put(myFolder);
		jsonRoot.put(myShare);
		jsonRoot.put(shareToMe);
		jsonRoot.put(shareToMe1);

		return jsonRoot;
	}

}
