package com.boco.eoms.duty.controller;

import javax.servlet.http.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.bo.TawApparatusroomBO;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;


import org.apache.struts.action.*;

import java.util.*;

public class TawDutyCustAction extends Action {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	private static int PAGE_LENGTH = 10;

	static {
		ResourceBundle prop = ResourceBundle
				.getBundle("resources.application_zh_CN");
		try {
			PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
		} catch (Exception e) {

		}
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		if (isCancelled(request)) {
			return mapping.findForward("cancel");
		}

		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("DEL".equalsIgnoreCase(myaction)) {
			myforward = performDel(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		}else if ("INDEX".equalsIgnoreCase(myaction)) {
			myforward = performIndex(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}

	/**
	 * @see ֵ���Ż�-�Ű���Ϣ����-���
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return null
	 */
	private ActionForward performAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		

		TawRmAssignworkBO tawRmAssignworkBO = null;
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		List dutyRoomList = null;
		try {
			String strRoomId = request.getParameter("strRoomId");
			TawApparatusroomBO tawApparatusroomBO = new TawApparatusroomBO(ds);
			if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
				tawRmAssignworkBO = new TawRmAssignworkBO(ds);
				dutyRoomList = tawRmAssignworkBO.getRoomSelectName();
				

			} else {
				// tawValidatePrivBO = new TawValidatePrivBO(ds);
				// SelectRoom =
				// tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
				// 2051, 2);
				

			}
			request.setAttribute("DUTYROOMLIST", dutyRoomList);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ֵ���Ż�-�Ű���Ϣ����-ɾ��
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return null
	 */
	private ActionForward performDel(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		

		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			TawDutyCustDAO tawDutyCustDAO = new TawDutyCustDAO(ds);
			tawDutyCustDAO.deleted(id);
		} catch (Exception e) {
			generalError(request, e);
			e.printStackTrace();
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ֵ���Ż�-�Ű���Ϣ����-�б�
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return null
	 */
	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		List dutyCustList = null;
		String strRoomId = "";
		try {
			TawDutyCustDAO tawDutyCustDAO = new TawDutyCustDAO(ds);
			dutyCustList = tawDutyCustDAO.list(saveSessionBeanForm.getUserid(), 1);
			if (dutyCustList.size() > 0) {
				strRoomId = tawDutyCustDAO.getSelRoomId(dutyCustList);
			}
			request.setAttribute("DUTYCUSTLIST", dutyCustList);
			request.setAttribute("STRROOMID", strRoomId);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward("success");

	}

	/**
	 * @see ֵ���Ż�-�Ű���Ϣ����-����
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return null
	 */
	private ActionForward performSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
				TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {
			String strRoomId = request.getParameter("id");
			TawDutyCustDAO tawDutyCustDAO = new TawDutyCustDAO(ds);
			tawDutyCustDAO.save(strRoomId, saveSessionBeanForm.getUserid());

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward("success");

	}
	/**
	 * @see ֵ���Ż�-�Ű���Ϣ����-�Ż�
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return null
	 */
	private ActionForward performIndex(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		
		 Vector QueryResult=null;
		 String ret = "success";
		try {
			TawDutyCustDAO tawDutyCustDAO = new TawDutyCustDAO(ds);
			String strRoomId = tawDutyCustDAO.getDuryCustRoomId(saveSessionBeanForm.getUserid(), 1);
			String time_from = StaticMethod.getDateString(0);
			QueryResult = tawDutyCustDAO.getDutyCustVector(strRoomId, time_from.replaceAll("'", "").trim());
			request.setAttribute("QUERYRESULT",QueryResult);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward(ret);

	}
	private void generalError(HttpServletRequest request, Exception e) {
		ActionErrors aes = new ActionErrors();
		aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e
				.getMessage()));
		saveErrors(request, aes);
		e.printStackTrace();
	}
}
