package com.boco.eoms.duty.webapp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.model.TawRmVisitRecord;
import com.boco.eoms.duty.service.ITawRmVisitRecordManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmVisitRecordForm;
import com.boco.eoms.workbench.memo.util.MemoPage;

import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.duty.dao.TawRmVisitStatDAO;

/**
 * Action class to handle CRUD on a TawRmVisitRecord object
 * 
 * @struts.action name="tawRmVisitRecordForm" path="/tawRmVisitRecords"
 *                scope="request" validate="false" parameter="method"
 *                input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main"
 *                        path="/WEB-INF/pages/tawRmVisitRecord/tawRmVisitRecordTree.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawRmVisitRecord/tawRmVisitRecordForm.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawRmVisitRecord/tawRmVisitRecordList.jsp"
 *                        contextRelative="true"
 */
public final class TawRmVisitRecordAction extends BaseAction {
	// 定义页数长度
	private static int PAGE_LENGTH = 15;

	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("main");
	}

	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ItawRmVisitRecordManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

	/**
	 * ajax保存
	 */
	public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;

		ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ItawRmVisitRecordManager");
		TawRmVisitRecord tawRmVisitRecord = (TawRmVisitRecord) convert(tawRmVisitRecordForm);
		mgr.saveTawRmVisitRecord(tawRmVisitRecord);
		// JSONUtil.print(response, "操作成功");
	}

	/**
	 * 根据模块或功能的编码，删除该对象
	 */
	public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;

		ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ItawRmVisitRecordManager");
		mgr.removeTawRmVisitRecord(tawRmVisitRecordForm.getId());

	}

	/**
	 * ajax请求修改某节点的详细信息
	 */
	public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;

		if (tawRmVisitRecordForm.getId() != null) {
			ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ItawRmVisitRecordManager");
			TawRmVisitRecord tawRmVisitRecord = (TawRmVisitRecord) convert(tawRmVisitRecordForm);

			mgr.saveTawRmVisitRecord(tawRmVisitRecord);
			// mgr.updateTawRmVisitRecord(tawRmVisitRecord);
		}

		return null;
	}

	/**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ItawRmVisitRecordManager");
		TawRmVisitRecord tawRmVisitRecord = mgr.getTawRmVisitRecord(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawRmVisitRecord);

		JSONUtil.print(response, jsonRoot.toString());
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;
		try {
			// if an id is passed in, look up the user - otherwise
			// don't do anything - user is doing an add
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String workSerial = sessionform.getWorkSerial();
			String id = StaticMethod.nullObject2String(request
					.getParameter("id"));
			if (id == null || id.trim().equals("")) {
				if (sessionform == null) {
					return mapping.findForward("timeout");
				}
				// 判断是否值班
				if (workSerial.equals("0")) {
					return mapping.findForward("notonduty1");
				}
			}
			HttpSession session = request.getSession();
			String tmpVisitorName = StaticMethod.nullObject2String(session
					.getAttribute("myTmpVisitorName"));
			String tmpCompany = StaticMethod.nullObject2String(session
					.getAttribute("myTmpCompany"));
			String tmpVisitTime = StaticMethod.nullObject2String(session
					.getAttribute("myTmpVisitTime"));
			String tmpLeftTime = StaticMethod.nullObject2String(session
					.getAttribute("myTmpLeftTime"));
			String tmpReceiver = StaticMethod.nullObject2String(session
					.getAttribute("myTmpReceiver"));
			String roomId = StaticMethod.nullObject2String(session
					.getAttribute("myRoomId"));
			String startTime = StaticMethod.nullObject2String(session
					.getAttribute("myStartTime"));
			String endTime = StaticMethod.nullObject2String(session
					.getAttribute("myEndTime"));
			tawRmVisitRecordForm.setTmpVisitorName(tmpVisitorName);
			tawRmVisitRecordForm.setTmpCompany(tmpCompany);
			tawRmVisitRecordForm.setTmpVisitTime(tmpVisitTime);
			tawRmVisitRecordForm.setTmpLeftTime(tmpLeftTime);
			tawRmVisitRecordForm.setTmpReceiver(tmpReceiver);
			tawRmVisitRecordForm.setRoomId(roomId);
			tawRmVisitRecordForm.setStartTime(startTime);
			tawRmVisitRecordForm.setEndTime(endTime);
			session.removeAttribute("myTmpVisitorName");
			session.removeAttribute("myTmpCompany");
			session.removeAttribute("myTmpVisitTime");
			session.removeAttribute("myTmpLeftTime");
			session.removeAttribute("myTmpReceiver");
			session.removeAttribute("myRoomId");
			session.removeAttribute("myStartTime");
			session.removeAttribute("myEndTime");
			if (tawRmVisitRecordForm.getId() != null) {
				ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ITawRmVisitRecordManager");
				if (id != null && !id.equals("")) {
					TawRmVisitRecord tawRmVisitRecord = mgr
							.getTawRmVisitRecord(id);
					if (tawRmVisitRecord != null) {
						if (workSerial.equals("0")) {
							request.setAttribute("tawRmVisitRecord",
									tawRmVisitRecord);
							return mapping.findForward("detailContent");
						}
						tawRmVisitRecordForm = (TawRmVisitRecordForm) convert(tawRmVisitRecord);
					}
					request.setAttribute("leftTime", tawRmVisitRecord
							.getLeftTime());
				}
				request.setAttribute("recordId", id);
				/*
				 * tawWorkbenchMemoForm = (TawWorkbenchMemoForm)
				 * convert(tawWorkbenchMemo);
				 */
				updateFormBean(mapping, request, tawRmVisitRecordForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("edit");
	}

	/**
	 * 
	 * @see 保存外借记录
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
		ActionMessages messages = new ActionMessages();
		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;
		boolean isNew = (tawRmVisitRecordForm.getId().equals("") || tawRmVisitRecordForm
				.getId() == null);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		final String userId = sessionform.getUserid();
		final String workSerial = sessionform.getWorkSerial();
		final String roomId = sessionform.getRoomId();
		ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ITawRmVisitRecordManager");
		TawRmVisitRecord tawRmVisitRecord = (TawRmVisitRecord) convert(tawRmVisitRecordForm);
		tawRmVisitRecord.setUserId(userId);
		tawRmVisitRecord.setWorkSerial(workSerial);
		tawRmVisitRecord.setRoomId(roomId);
		String createTime = StaticMethod.getLocalString();
		tawRmVisitRecord.setCreateTime(createTime);
		mgr.saveTawRmVisitRecord(tawRmVisitRecord);
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"TawRmVisitRecord.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return searchList2(mapping, form, request, response);
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"TawRmVisitRecord.updated"));
			saveMessages(request, messages);

			return searchList2(mapping, form, request, response);
		}
	}

	/**
	 * 
	 * @see 根据条件查询外借记录
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
		int offset;
		int length = PAGE_LENGTH;
		try {
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			TawRmVisitRecord tawRmVisitRecord = null;
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			final String workSerial = sessionform.getWorkSerial();
			// 得到查询的条件
			String whereStr = " where workSerial = '" + workSerial + "'";
			List tawRmVisitRecordList = new ArrayList();
			ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ITawRmVisitRecordManager");
			Map map = (Map) mgr.getTawRmVisitRecords(new Integer(offset),
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			// 分页
			for (int i = offset; i < (length + offset) && i < list.size(); i++) {
				tawRmVisitRecord = (TawRmVisitRecord) list.get(i);
				tawRmVisitRecordList.add(tawRmVisitRecord);
			}
			String url = request.getContextPath() + "/duty" + mapping.getPath()
					+ ".do";
			int size = Integer.parseInt((StaticMethod.null2String((String) map
					.get("total"))));
			String pagerHeader = Pager.generate(offset, size, length, url,
					"method=search");
			request.setAttribute("pagerHeader", pagerHeader);
			Iterator listIt = tawRmVisitRecordList.iterator();
			request.setAttribute("listIt", listIt);
			request.setAttribute("resultSize", map.get("total"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 根据条件查询外借记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward searchList2(ActionMapping mapping, ActionForm form,
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
					DutyConstacts.TAW_RM_VISITRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;
			String visitorName = StaticMethod.null2String(tawRmVisitRecordForm
					.getTmpVisitorName());
			String company = StaticMethod.null2String(tawRmVisitRecordForm
					.getTmpCompany());
			String visitTime = StaticMethod.null2String(tawRmVisitRecordForm
					.getTmpVisitTime());
			String leftTime = StaticMethod.null2String(tawRmVisitRecordForm
					.getTmpLeftTime());
			String receiver = StaticMethod.null2String(tawRmVisitRecordForm
					.getTmpReceiver());
			String roomId = StaticMethod.null2String(tawRmVisitRecordForm
					.getRoomId());
			String startTime = StaticMethod.null2String(tawRmVisitRecordForm
					.getStartTime());
			String endTime = StaticMethod.null2String(tawRmVisitRecordForm
					.getEndTime());
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			final String userId = sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");

			bufferpage.append("&visitorName=" + visitorName + "");
			if (!visitorName.equals("")) {
				buffer.append(" and visitorName like '%" + visitorName + "%'");

			}

			if (!userId.equals(StaticVariable.ADMIN)) {
				if (!roomId.equals("")) {
					buffer.append(" and roomId ='" + roomId + "'");
					bufferpage.append("&roomId=" + roomId + "");
				} else {
					List roomList = this.getRoomList(request);
					buffer.append(" and roomId in (");
					for (int i = 0; i < roomList.size() - 1; i++) {
						TawSystemCptroom tawSystemCptroom = (TawSystemCptroom) roomList
								.get(i);
						String id = tawSystemCptroom.getId().toString();
						buffer.append(id + ",");
					}
					TawSystemCptroom tawSystemCptroom = (TawSystemCptroom) roomList
							.get(roomList.size() - 1);
					String id = tawSystemCptroom.getId().toString();
					buffer.append(id + ")");
				}
			}

			bufferpage.append("&startTime=" + startTime + "");
			if (!startTime.equals("")) {
				buffer.append(" and createTime>= '" + startTime + "'");

			}

			bufferpage.append("&endTime=" + endTime + "");
			if (!endTime.equals("")) {
				buffer.append(" and createTime<= '" + endTime + "'");
			}

			bufferpage.append("&company=" + company + "");
			if (!company.equals("")) {
				buffer.append(" and company like '%" + company + "%'");
			}

			bufferpage.append("&visitTime=" + visitTime + "");
			if (!visitTime.equals("")) {
				buffer.append(" and visitTime='" + visitTime + "'");
			}

			bufferpage.append("&leftTime=" + leftTime + "");
			if (!leftTime.equals("")) {
				buffer.append(" and leftTime='" + leftTime + "'");
			}

			bufferpage.append("&receiver=" + receiver + "");
			if (!receiver.equals("")) {
				buffer.append(" and receiver like '%" + receiver + "%'");
			}

			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ITawRmVisitRecordManager");
			Map map = (Map) mgr.getTawRmVisitRecords(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");
			String url = request.getContextPath() + "/duty" + mapping.getPath()
					+ ".do";
			// 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			int size = ((Integer) map.get("total")).intValue();
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size,
					length, url, pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_VISITRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 根据条件查询外借记录
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
					DutyConstacts.TAW_RM_VISITRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			final String userId = sessionform.getUserid();
			whereStr = request.getParameter("whereStr");
			StringBuffer buffer = new StringBuffer();
			StringBuffer bufferpage = new StringBuffer();
			buffer.append(" where 1=1");
			bufferpage.append("method=searchList");

			String visitorName = StaticMethod.null2String(request
					.getParameter("tmpVisitorName"));
			if (!visitorName.equals("")) {
				buffer.append(" and visitorName like '%" + visitorName + "%'");
				bufferpage.append("&visitorName=" + visitorName + "");
			}

			String roomId = StaticMethod.null2String(request
					.getParameter("roomId"));
			if (!userId.equals(StaticVariable.ADMIN)) {
				if (!roomId.equals("")) {
					buffer.append(" and roomId ='" + roomId + "'");
					bufferpage.append("&roomId=" + roomId + "");

				} else {
					List roomList = this.getRoomList(request);
					buffer.append(" and roomId in (");
					for (int i = 0; i < roomList.size() - 1; i++) {
						TawSystemCptroom tawSystemCptroom = (TawSystemCptroom) roomList
								.get(i);
						String id = tawSystemCptroom.getId().toString();
						buffer.append(id + ",");
					}
					TawSystemCptroom tawSystemCptroom = (TawSystemCptroom) roomList
							.get(roomList.size() - 1);
					String id = tawSystemCptroom.getId().toString();
					buffer.append(id + ")");
				}
			}

			String startTime = StaticMethod.null2String(request
					.getParameter("startTime"));
			if (!startTime.equals("")) {
				bufferpage.append("&startTime=" + startTime + "");
				buffer.append(" and createTime>= '" + startTime + "'");
			}

			String endTime = StaticMethod.null2String(request
					.getParameter("endTime"));
			if (!endTime.equals("")) {
				bufferpage.append("&endTime=" + endTime + "");
				buffer.append(" and createTime<= '" + endTime + "'");
			}

			String company = StaticMethod.null2String(request
					.getParameter("tmpCompany"));
			if (!company.equals("")) {
				bufferpage.append("&company=" + company + "");
				buffer.append(" and company like '%" + company + "%'");
			}

			String visitTime = StaticMethod.null2String(request
					.getParameter("tmpVisitTime"));
			if (!visitTime.equals("")) {
				bufferpage.append("&visitTime=" + visitTime + "");
				buffer.append(" and visitTime='" + visitTime + "'");
			}

			String leftTime = StaticMethod.null2String(request
					.getParameter("tmpLeftTime"));
			if (!leftTime.equals("")) {
				bufferpage.append("&leftTime=" + leftTime + "");
				buffer.append(" and leftTime='" + leftTime + "'");
			}

			String receiver = StaticMethod.null2String(request
					.getParameter("tmpReceiver"));
			if (!receiver.equals("")) {
				bufferpage.append("&receiver=" + receiver + "");
				buffer.append(" and receiver like '%" + receiver + "%'");
			}

			whereStr = buffer.toString();
			request.setAttribute("whereStr", whereStr);
			String pageStr = bufferpage.toString();
			ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ITawRmVisitRecordManager");
			Map map = (Map) mgr.getTawRmVisitRecords(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");
			// 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			int size = ((Integer) map.get("total")).intValue();
			String url = request.getContextPath() + "/duty" + mapping.getPath()
					+ ".do";
			String pagerHeader = MemoPage.generate(pageIndex.intValue(), size,
					length, url, pageStr);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute(DutyConstacts.TAW_RM_VISITRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	public ActionForward visitRecordSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List roomList = this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("visitRecordSearch");
	}

	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;
		try {
			// Exceptions are caught by ActionExceptionHandler
			ITawRmVisitRecordManager mgr = (ITawRmVisitRecordManager) getBean("ITawRmVisitRecordManager");
			mgr.removeTawRmVisitRecord(tawRmVisitRecordForm.getId());

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawRmVisitRecordForm.deleted"));

			// save messages in session, so they'll survive the redirect
			saveMessages(request.getSession(), messages);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchList2(mapping, form, request, response);
	}

	public ActionForward visitRecordStat(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List roomList = this.getRoomList(request);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("stat");
	}

	public ActionForward visitRecordStatResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawRmVisitRecordForm tawRmVisitRecordForm = (TawRmVisitRecordForm) form;
		TawRmVisitStatDAO statDAO = new TawRmVisitStatDAO();

		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
		.getPageSize();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				DutyConstacts.TAW_RM_VISITSTATLIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		try {
			String where = "select count(*),a.room_id,a.work_serial,a.company,b.dutydate,c.username,b.starttime_defined,b.endtime_defined from taw_rm_visitrecord a,taw_rm_assignwork b,taw_system_user c where a.work_serial=b.id and b.dutymaster=c.userid";
			if(!tawRmVisitRecordForm.getRoomId().equals("")){
				where += " and a.room_id = '" + tawRmVisitRecordForm.getRoomId() + "'";
			}
			if(!tawRmVisitRecordForm.getStartTime().equals("")){
				where += " and a.create_time >= '" + tawRmVisitRecordForm.getStartTime() + "'";
			}
			if(!tawRmVisitRecordForm.getEndTime().equals("")){
				where += " and a.create_time <= '" + tawRmVisitRecordForm.getEndTime() + "'";
			}
			where += " group by a.company,b.starttime_defined,b.endtime_defined,c.username,a.work_serial,b.dutydate,a.room_id";
			where += " order by b.starttime_defined desc";
			Map map = statDAO.getStatResult(where,pageIndex,pageSize);
			List result = (List)map.get("result");
			request.setAttribute(DutyConstacts.TAW_RM_VISITSTATLIST,result);
			request.setAttribute("resultSize",map.get("total"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("statResult");
	}

	private List getRoomList(HttpServletRequest request) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawRmAssignworkBO tawRmAssignworkBO = null;
		Vector SelectRoom = new Vector();
		TawSystemCptroom tawApparatusroom = null;
		TawSystemPrivRegion tawSystemPrivRegion = null;
		List roomList = new ArrayList();
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
			tawRmAssignworkBO = new TawRmAssignworkBO(ds);
			try {
				SelectRoom = tawRmAssignworkBO.getRoomSelect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tawApparatusroom = null;
			if (SelectRoom.size() > 0) {
				for (int i = 0; i < SelectRoom.size(); i++) {
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer((String) SelectRoom.elementAt(i)), 0);
					if (tawApparatusroom != null) {
						roomList.add(tawApparatusroom);
					}
				}
			}
		} else {
			SelectRoom = StaticMethod
					.list2vector(privBO
							.getPermissions(
									saveSessionBeanForm.getUserid(),
									com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
									com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

			if (SelectRoom.size() > 0) {
				tawApparatusroom = null;
				for (int i = 0; i < SelectRoom.size(); i++) {
					tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
							.elementAt(i);
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(tawSystemPrivRegion.getRegionid()), 0);
					if (tawApparatusroom != null) {
						roomList.add(tawApparatusroom);
					}
				}
			}
		}
		return roomList;
	}
}
