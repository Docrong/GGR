package com.boco.eoms.duty.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
 

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;

import com.boco.eoms.duty.model.TawRmReplace;
import com.boco.eoms.duty.service.ITawRmReplaceManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmReplaceForm;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawRmReplace object
 * 
 * @struts.action name="tawRmReplaceForm" path="/tawRmReplaces" scope="request"
 *                validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main"
 *                        path="/WEB-INF/pages/tawRmReplace/tawRmReplaceTree.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawRmReplace/tawRmReplaceForm.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawRmReplace/tawRmReplaceList.jsp"
 *                        contextRelative="true"
 */
public final class TawRmReplaceAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

 

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

	/**
	 * ajax保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String user_id = saveSessionBeanForm.getUserid();

		Date date = null;
		GregorianCalendar cal = null;
		String inpudate = null;
		int roomId = Integer.parseInt(String.valueOf(
				request.getParameter("roomId")).trim());

		date = new Date();
		cal = new GregorianCalendar();
		cal.setTime(date);
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;
		int day = cal.get(cal.DATE);
		inpudate = String.valueOf(year) + "-" + String.valueOf(month) + "-"
				+ String.valueOf(day);

		Vector apply_from = null;

		try {
			String reason = StaticMethod.nullObject2String(request
					.getParameter("reason"));
			String remark = StaticMethod.nullObject2String(request
					.getParameter("remark"));
			String workapplyfrom = request.getParameter("workapplyfrom");

			workapplyfrom = workapplyfrom.substring(1, workapplyfrom.length());
			String recevier = StaticMethod.nullObject2String(request
					.getParameter("recever"));

			try {
				apply_from = StaticMethod.getVector(workapplyfrom, "@");

				for (int i = 0; i < apply_from.size(); i++) {
					String applyfrom = (String) apply_from.elementAt(i);
					mgr.replaceDuty(roomId, inpudate, user_id, applyfrom,
							recevier, reason,remark);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("failure");
			}

		} catch (Exception e) {

			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			date = null;
			cal = null;
			inpudate = null;

			apply_from = null;

		} 
		return mapping.findForward("success");
	}

	/**
	 * 根据模块或功能的编码，删除该对象
	 */
	public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmReplaceForm tawRmReplaceForm = (TawRmReplaceForm) form;

		ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
		mgr.removeTawRmReplace(tawRmReplaceForm.getId());

	}

	/**
	 * ajax请求修改某节点的详细信息
	 */
	public ActionForward xgetshow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int length = 12;// PAGE_LENGTH;
		try {
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_RM_WORKORDERRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
			ITawSystemUserManager mgrUser = (ITawSystemUserManager) getBean("itawSystemUserManager");
			ITawSystemCptroomManager mgrRoom = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");

			String roomid = StaticMethod.nullObject2String(request
					.getParameter("roomId"));
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			String flag = StaticMethod.nullObject2String(request
					.getParameter("flag"));

			String whereStr = "";
			String userid = saveSessionBeanForm.getUserid();
			if (flag == null || flag.equals("")) {
				if (saveSessionBeanForm.isAdmin()) {
					whereStr = " where roomid = '" + roomid
							+ "' and flag in (0,1,2)";
				} else {
					if (PrivMgrLocator.getPrivMgr().hasPriv(userid,
							DutyConstacts.DUTY_TAW_RM_REPLACE)) {
						whereStr = " where roomid = '" + roomid
								+ "' and flag in (0,1,2)";
					} else {
						whereStr = " where (hander='" + userid
								+ "' or receiver = '" + userid
								+ "') and roomid = '" + roomid
								+ "' and flag in (0,1,2)";
					}
				}
			} else {
				if (saveSessionBeanForm.isAdmin()) {
					whereStr = " where roomid = '" + roomid + "' and flag ='"
							+ flag + "'";
				} else {
					if (PrivMgrLocator.getPrivMgr().hasPriv(userid,
							DutyConstacts.DUTY_TAW_RM_REPLACE)) {
						whereStr = " where roomid = '" + roomid
								+ "' and flag ='" + flag + "'";
					} else {
						whereStr = " where (hander='" + userid
								+ "' or receiver = '" + userid
								+ "') and roomid = '" + roomid
								+ "' and flag ='" + flag + "'";
					}
				}
			}
			Map map = (Map) mgr.getTawRmReplaces(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			List replace = new ArrayList();
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawRmReplace tawRmReplace = (TawRmReplace) it.next();
				tawRmReplace.setHanderName(mgrUser.id2Name(tawRmReplace
						.getHander()));
				tawRmReplace.setReceiverName(mgrUser.id2Name(tawRmReplace
						.getReceiver()));
				tawRmReplace.setRemark(StaticMethod.null2String(tawRmReplace.getRemark()));
				if (tawRmReplace.getFlag().equals("0"))
					tawRmReplace.setFlagName("管理员审核中");
				if (tawRmReplace.getFlag().equals("1"))
					tawRmReplace.setFlagName("管理员审核通过");
				if (tawRmReplace.getFlag().equals("2"))
					tawRmReplace.setFlagName("管理员审核驳回");

				tawRmReplace.setRoomName(mgrRoom
						.getTawSystemCptroomName(new Integer(tawRmReplace
								.getRoomId().trim())));
				replace.add(tawRmReplace);
			}
			request.setAttribute(DutyConstacts.TAW_RM_REPLACELIST, replace);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));
			request.setAttribute("roomId", roomid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("xgetshow");
	}

	/**
	 * ajax请求获取某节点的详细信息
	 */
	public ActionForward xgetAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int length = 12;// PAGE_LENGTH;
		try {
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.TAW_RM_WORKORDERRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
			ITawSystemUserManager mgrUser = (ITawSystemUserManager) getBean("itawSystemUserManager");
			ITawSystemCptroomManager mgrRoom = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");

			String roomid = StaticMethod.nullObject2String(request
					.getParameter("roomId"));
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			
			String whereStr = "";
			whereStr = " where roomid = '" + roomid + "' and flag  =0";
			Map map = (Map) mgr.getTawRmReplaces(pageIndex,
					new Integer(length), whereStr);
			List list = (List) map.get("result");
			List replace = new ArrayList();
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawRmReplace tawRmReplace = (TawRmReplace) it.next();
				tawRmReplace.setHanderName(mgrUser.id2Name(tawRmReplace
						.getHander()));
				tawRmReplace.setReceiverName(mgrUser.id2Name(tawRmReplace
						.getReceiver()));
				if (tawRmReplace.getFlag().equals("0"))
					tawRmReplace.setFlagName("管理员审核中");
				if (tawRmReplace.getFlag().equals("1"))
					tawRmReplace.setFlagName("管理员审核通过");
				if (tawRmReplace.getFlag().equals("2"))
					tawRmReplace.setFlagName("管理员审核驳回");
				tawRmReplace.setRemark(StaticMethod.null2String(tawRmReplace.getRemark()));
				tawRmReplace.setRoomName(mgrRoom
						.getTawSystemCptroomName(new Integer(tawRmReplace
								.getRoomId().trim())));
				replace.add(tawRmReplace);
			}
			request.setAttribute(DutyConstacts.TAW_RM_REPLACELIST, replace);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));
			request.setAttribute("roomId", roomid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("xgetaudit");
	}

	public ActionForward selectInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmReplaceForm tawRmReplaceForm = (TawRmReplaceForm) form;
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
		String time_from = "";
		String time_to = "";
		String receiver = tawRmReplaceForm.getReceiver();
		String user_id = "";
		Vector vector_from = new Vector();
		Vector vector_to = null;
		time_from = request.getParameter("time_from");
		time_to = request.getParameter("time_to");
		time_from = StaticMethod.getAddZero(time_from);
		time_to = StaticMethod.getAddZero(time_to);
		List receverList = new ArrayList();
		if (receiver == null || receiver.equals("")) {
			receiver = request.getParameter("receiver");
		}

		int roomId = 0;
		roomId = Integer.parseInt(String
				.valueOf(request.getParameter("roomId")));
		user_id = saveSessionBeanForm.getUserid();
		try {
			vector_from = new Vector();
			vector_to = new Vector();

			receverList = mgr.getreplaceApplyVector(roomId, user_id, receiver,
					time_from, time_to, "1");
			if (receverList.size() > 0) {
				request.setAttribute("isexit", "1");
				return mapping.findForward("apply");

			} else {
				vector_from = mgr.getreplaceApplyVector(roomId, user_id,
						time_from, time_to, "0");
				if (vector_from.size() < 0) {
					request.setAttribute("isexit", "2");
					return mapping.findForward("apply");
				}
			}

			request.setAttribute("receiver", receiver);
			request.setAttribute("VECTORFROM", vector_from);
			request.setAttribute("VECTORTO", vector_to);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			request.setAttribute("roomId", String.valueOf(roomId));
		}
		return mapping.findForward("selectInfo");
	}

	public ActionForward apply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("apply");
	}

	public ActionForward xAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawRmReplaceForm tawRmReplaceForm = (TawRmReplaceForm) form;
		String flag = tawRmReplaceForm.getFlag();
		String id = "";
		TawRmReplace tawRmReplace = null;
		String[] arrId = request.getParameterValues("id");
		String roomId = request.getParameter("roomId");
		request.setAttribute("roomId", roomId);
		ITawRmReplaceManager mgr = (ITawRmReplaceManager) getBean("ItawRmReplaceManager");
		if (arrId !=null) {
			for (int i = 0; i < arrId.length; i++) {
				id = arrId[i];
				tawRmReplace = mgr.getTawRmReplace(id);
				tawRmReplace.setFlag(flag);
				if(tawRmReplace.getRemark()==null){
					tawRmReplace.setRemark("");
				}
				mgr.saveTawRmReplace(tawRmReplace);
				if (flag.equals("1")) {
					mgr.replaceDutyMain(tawRmReplace.getHander(), tawRmReplace
							.getWorkserial(), tawRmReplace.getReceiver());
					 
				} 
				mgr.replaceSendMsg(tawRmReplace); 
			}
		}
		request.setAttribute("roomId", roomId);
		return xgetAudit(mapping, form, request, response);
	}

}
