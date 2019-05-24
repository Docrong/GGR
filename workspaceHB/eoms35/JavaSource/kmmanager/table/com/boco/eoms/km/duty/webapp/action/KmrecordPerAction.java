//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordSubAction.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.km.duty.webapp.action;

import javax.sql.*;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.LogFactory;

import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;

//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
//import com.boco.eoms.jbzl.dao.TawRmUserDAO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
//import com.boco.eoms.jbzl.model.TawRmUser;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.duty.bo.KmassignworkBO;

//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;

public class KmrecordPerAction extends Action {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	private static int PAGE_LENGTH = 20;
	// 整合调整关于国际化
	static {
		ResourceBundle prop = ResourceBundle
				.getBundle("resources.application_zh_CN");
		try {
			PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
		} catch (Exception e) {
		}
	}

	/*
	 * static { ResourceBundle prop =
	 * ResourceBundle.getBundle("resources.application"); try { PAGE_LENGTH =
	 * Integer.parseInt(prop.getString("list.page.length")); } catch (Exception
	 * e) { } }
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		if (isCancelled(request)) {
			return mapping.findForward("cancel");
		}
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("SHOWCONFIG".equalsIgnoreCase(myaction)) {
			myforward = performShowConfig(mapping, form, request, response);
		} else if ("SHOWRESULT".equalsIgnoreCase(myaction)) {
			myforward = performShowResult(mapping, form, request, response);
		} else if ("SELECTROOM".equalsIgnoreCase(myaction)) {
			myforward = performSelectRoom(mapping, form, request, response);
		} else if ("SELECTDATE".equalsIgnoreCase(myaction)) {
			myforward = performSelectDate(mapping, form, request, response);
		}
		 else if ("GETROOM".equalsIgnoreCase(myaction)) {
				myforward = performGetRoom(mapping, form, request, response);
			} else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}
	
	/**
	 * @see 值班统计条件
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performShowConfig(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		TawRmRecordPerDAO tawRmRecordPerDAO = null;
		TawUserRoomDAO tawUserRoomDAO = null;
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		String starttime = "";
		String endtime = "";
		Vector vectorRoomUser = null;
		Vector vectorWorkseiral = null;
		// saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
		// getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		try {
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			tawUserRoomDAO = new TawUserRoomDAO(ds);
			vectorRoomUser = tawUserRoomDAO.getRoomUser(roomId, 0); // 得到该机房下用户的id
																	// 和name
			request.setAttribute("RoomUser", vectorRoomUser);

			starttime = request.getParameter("starttime");
			endtime = request.getParameter("endtime");
			request.setAttribute("roomId", String.valueOf(roomId));
			request.setAttribute("starttime", starttime);
			request.setAttribute("endtime", endtime);

			// 得到班次
			if (starttime.equals(endtime)) // 只有选择某一天时,才可以选择查询的班次
			{
				tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
				vectorWorkseiral = tawRmAssignworkDAO.getWorkSerialofDay(
						starttime, roomId);
				request.setAttribute("vectorWorkseiral", vectorWorkseiral);
			}

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 值班统计结果
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performShowResult(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		String starttime = null;
		String endtime = null;
		TawRmRecordPerDAO tawRmRecordPerDAO = null;
		Vector vecPerRecords = null;

		// saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
		// getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {
			starttime = request.getParameter("starttime");
			endtime = request.getParameter("endtime");
			starttime = starttime + " 00:00:00";
			endtime = endtime + " 23:59:59";
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			String typename = ("".equals(request.getParameter("typename")) ? ""
					: " and  typename='" + request.getParameter("typename")
							+ "'");
			String flag = "".equals(request.getParameter("flag")) ? ""
					: " and  complete_flag=" + request.getParameter("flag");
			String userId = "".equals(request.getParameter("userId")) ? ""
					: " and dutyman='" + request.getParameter("userId") + "'";
			String Workseiral = request.getParameter("Workseiral") != null
					&& !"".equals(request.getParameter("Workseiral")) ? " and workserial="
					+ request.getParameter("Workseiral")
					: "";
			String where = "recordtime>='" + starttime + "' and recordtime<='"
					+ endtime + "'" + typename + flag + userId + Workseiral;
			tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
			vecPerRecords = tawRmRecordPerDAO.getSearchResult(where);
			request.setAttribute("vecPerRecords", vecPerRecords);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			starttime = null;
			endtime = null;
			vecPerRecords = null;
		}

		return mapping.findForward("success");

	}

	/**
	 * @see 值班统计条件
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSelectRoom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		 
		// saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
		// getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {
			// 权限验证
			 
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			 
		}
		return mapping.findForward("success");
	}
	
	//选择日期
	private ActionForward performSelectDate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		 
		// saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
		// getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {
			String roomId = request.getParameter("typeId");
			request.setAttribute("typeId", roomId);
			 
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			 
		}
		return mapping.findForward("success");
	}
	private ActionForward performGetRoom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		Vector SelectRoom = null;
		Vector SelectRoomId = null;
		Vector SelectRoomName = null;
		KmassignworkBO tawRmAssignworkBO = null;
		TawSystemPrivRegion tawSystemPrivRegion = null;
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
		KmsystemCptroom tawApparatusroom = null;
		JSONArray json = new JSONArray();
		String strSelectRoomName = null;
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		try {
			SelectRoom = new Vector();
			SelectRoomName = new Vector();
			SelectRoomId = new Vector();
			
		//	if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
				tawRmAssignworkBO = new KmassignworkBO(ds);
				SelectRoom = tawRmAssignworkBO.getRoomSelect();
				tawApparatusroom = null;
				strSelectRoomName = "";
				Vector removeEle = new Vector();
				if (SelectRoom.size() > 0) {
					for (int i = 0; i < SelectRoom.size(); i++) {
						JSONObject jitem = new JSONObject();
						tawApparatusroom = cptroomBO.getKmsystemCptroomById(
								new Integer((String) SelectRoom.elementAt(i)),
								0);
						if (tawApparatusroom != null) {
							strSelectRoomName = StaticMethod
									.null2String(tawApparatusroom.getRoomname());
							SelectRoomName.add(strSelectRoomName);
							SelectRoomId.add((String) SelectRoom.elementAt(i));
							jitem.put("id", (String) SelectRoom.elementAt(i));
							jitem.put("text", strSelectRoomName);
							jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
							jitem.put("allowChild", true); 
							jitem.put("allowDelete", true);
							jitem.put("allowList", true);
							jitem.put("leaf", tawApparatusroom.getLeaf());
							json.put(jitem);
						} else {
							removeEle.add(SelectRoom.elementAt(i));
						}
					}
					SelectRoom.removeAll(removeEle);
				} else {
					return mapping.findForward("nopriv");
				}
			/*} else {
				SelectRoom = StaticMethod
						.list2vector(privBO
								.getPermissions(
										saveSessionBeanForm.getUserid(),
										com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
										com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

				if (SelectRoom.size() > 0) {
					tawApparatusroom = null;
					strSelectRoomName = "";
					Vector removeEle = new Vector();
					for (int i = 0; i < SelectRoom.size(); i++) {
						JSONObject jitem = new JSONObject();
						tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
								.elementAt(i);
						tawApparatusroom = cptroomBO.getKmsystemCptroomById(
								new Integer(tawSystemPrivRegion.getRegionid()),
								0);
						if (tawApparatusroom != null) {
							strSelectRoomName = StaticMethod
									.null2String(tawApparatusroom.getRoomname());
							SelectRoomName.add(strSelectRoomName);
							SelectRoomId.add(tawSystemPrivRegion.getRegionid());
							jitem.put("id", tawSystemPrivRegion.getRegionid());
							jitem.put("text", strSelectRoomName);
							jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
							jitem.put("allowChild", true);
							jitem.put("allowDelete", true);
							jitem.put("allowList", true);
							jitem.put("leaf", tawApparatusroom.getLeaf());
							json.put(jitem);
						} else {
							removeEle.add(SelectRoom.elementAt(i));
						}
					}
					SelectRoom.removeAll(removeEle);
				} else {
					return mapping.findForward("nopriv");
				}
			}*/
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().print(json.toString());
			request.setAttribute("SelectRoom", SelectRoomId);
			request.setAttribute("SelectRoomName", SelectRoomName);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			SelectRoom = null;
			SelectRoomName = null;
			tawRmAssignworkBO = null;
			privBO = null;
			// tawValidatePrivBO = null;
			cptroomBO = null;
			// tawApparatusroomDAO = null;
			tawApparatusroom = null;
			strSelectRoomName = null;
		}
		return null;
	}
	private void sqlDuplicateError(HttpServletRequest request, String objName) {
		ActionErrors aes = new ActionErrors();
		aes.add(aes.GLOBAL_ERROR, new ActionError("errors.database.duplicate",
				objName));
		saveErrors(request, aes);
	}

	private void generalError(HttpServletRequest request, Exception e) {
		ActionErrors aes = new ActionErrors();
		aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e
				.getMessage()));
		saveErrors(request, aes);
		e.printStackTrace();
	}
}
