package com.boco.eoms.check.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jdom.Document;
import org.jdom.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.boco.eoms.check.bo.TawRoomDutyCheckBO;
import com.boco.eoms.check.model.TawRoomdutyCheck;
import com.boco.eoms.check.model.TawRoomdutycAddonstable;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.interfaceMonitoring.mgr.InterfaceMonitoringMgr;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.bo.TawRmAbnormalRecordBO;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.dao.TawRmAbnormalRecordDAO;
import com.boco.eoms.duty.model.TawRmAddonsTable;
import com.boco.eoms.duty.service.ITawRmPlanContentManager; //import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
//import com.boco.eoms.workplan.bo.ext.TawwpAddonsExtendBO;
//import com.boco.eoms.workplan.bo.ext.TawwpLogExtendBO;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsElementVO;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;

public class TawRoomDutyCheckAction extends Action {

	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, IOException, Exception {
		ActionForward myforward = null;

		// ��ȡ�����action����
		String myaction = mapping.getParameter();
		try {
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ����û�����ҳ�����󣬽���ҳ����ת
		if (isCancelled(request)) {
			return mapping.findForward("cancel"); // ��Ч������ת�����ҳ��
		} else if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure"); // ���Ϊ�գ�ת���ҳ
		} else if ("CONFIGROOM".equalsIgnoreCase(myaction)) {
			myforward = performConfigRoom(mapping, form, request, response);
		} else if ("CONFIGROOMNEXT".equalsIgnoreCase(myaction)) {
			myforward = performConfigRoomNext(mapping, form, request, response);
		} else if ("CONFIGROOMSAVE".equalsIgnoreCase(myaction)) {
			myforward = performConfigRoomSave(mapping, form, request, response);
		} else if ("DUTYCHECK".equalsIgnoreCase(myaction)) {
			myforward = performDutyCheck(mapping, form, request, response);
		} else if ("ROOMCHECK".equalsIgnoreCase(myaction)) {
			myforward = performRoomCheck(mapping, form, request, response);
		} else if ("FORMREAD".equalsIgnoreCase(myaction)) {
			myforward = performFormRead(mapping, form, request, response);
		} else if ("SEARCH".equalsIgnoreCase(myaction)) {
			myforward = performSearch(mapping, form, request, response);
		} else if ("SEARCHRESULT".equalsIgnoreCase(myaction)) {
			myforward = performSearchResult(mapping, form, request, response);
		} else if ("FORMSAVE".equalsIgnoreCase(myaction)) {
			myforward = performFormSave(mapping, form, request, response);
		} else if ("ARCHIVE".equalsIgnoreCase(myaction)) {
			myforward = performArchive(mapping, form, request, response);
		}

		else {
			myforward = mapping.findForward("failure"); // ��Ч������ת�����ҳ��
		}
		return myforward;

	}

	private ActionForward performArchive(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String tawRoomDutyCheckId = request.getParameter("tawRoomDutyCheckId");
		TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();
		tawRoomDutyCheckBO.archiveTawRommDutyCheck(tawRoomDutyCheckId);

		return mapping.findForward("success");
	}

	private ActionForward performSearchResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		List l = null;
		List list=new ArrayList();
		String roomId = request.getParameter("roomId");
		String checktype = request.getParameter("checktype");
		String formname = request.getParameter("formname").trim();
		// String state = request.getParameter("state");
		// TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();
		ITawRmPlanContentManager ITawRmPlanContentManager = (ITawRmPlanContentManager) getBean("ITawRmPlanContentManager");
		l = ITawRmPlanContentManager.searchTawRoomDutyCheck(roomId, checktype,
				StaticMethod.getPageString(formname), "");
		TawRoomdutycAddonstable rel = null;
		for (int i = 0; i < l.size(); i++) {
			rel = (TawRoomdutycAddonstable) l.get(i);
			// dataUrl = rel.getDataurl();
			TawRmAddonsTable tawRmAddonsTable = new TawRmAddonsTable();

			System.out.println(rel.getTawRmAddonsTable().getId());
			tawRmAddonsTable = ITawRmPlanContentManager.getTawRmAddonsTable(rel
					.getTawRmAddonsTable().getId());
			list.add(tawRmAddonsTable);
		
		}
		request.setAttribute("relList", list);

		return mapping.findForward("success");
	}

	private ActionForward performFormSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		// ����б�Ԫ�صĴ�С
		String action = request.getParameter("action");
		String myid = request.getParameter("myid");
		String url = request.getParameter("url");
		String reaction = request.getParameter("reaction");
		String model = request.getParameter("model");
		// String window = request.getParameter("window");
		String addonsid = request.getParameter("addonsid");
		String checkid = request.getParameter("checkid");
		String checkType = "";
		// ���ļ�����һ��Document
		try {
			Document doc = null;// TawwpAddonsExtendBO.getSAXBDoc(url);
			// �õ���Ԫ��
			Element root = doc.getRootElement();
			// ���elements����
			Element body = root.getChild("body").getChild("elements");
			// emterms
			List emtermsList = body.getChildren();
			// start
			String value_temp = "";
			//
			Vector v = new Vector();

			for (int e = 0; e < emtermsList.size(); e++) {
				Element emterm = (Element) emtermsList.get(e);
				java.util.List emtermLists = emterm.getChildren();
				for (int i = 0; i < emtermLists.size(); i++) {
					TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
					Element element = (Element) emtermLists.get(i);
					eleForm = eleForm.getElement(element);
					// �޸Ļ�õ����
					// informix
					value_temp = StaticMethod.strFromBeanToPage(request
							.getParameter(eleForm.getName() + "_" + e));
					v.add(value_temp);
				}
			}
			// StaticMethod.strFromBeanToPage(
			// ����url
			// url = TawwpAddonsExtendBO.saveAddons(action, model, url, v,
			// myid);
			// ��־
			// TawwpLogExtendBO tawwpLogExtendBO = new TawwpLogExtendBO();
			// ��ȡ��ǰ�û���session�е���Ϣ
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = saveSessionBeanForm.getUserid(); // ��ǰ�û���
			// tawwpLogExtendBO.addLog(userId, "saveAddons", "");
			// TawRmAbnormalRecordBO abnormalRecordBO=new
			// TawRmAbnormalRecordBO(ds);
			// add by lgs begin 2008-01-14
			TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();

			checkType = tawRoomDutyCheckBO.getTableMapByRoomdutyDataurl(myid);
			// if(checkType.equals("1")){
			// abnormalRecordBO.insertAbnormalRecord(2, checkid, addonsid);
			// }
			// else if(checkType.equals("2")){
			// abnormalRecordBO.insertAbnormalRecord(3, checkid, addonsid);
			// }
			// add by lgs end 2008-01-14
			request.setAttribute("url", url);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ���¶���
		if (reaction != null) {
			reaction = java.net.URLDecoder.decode(reaction);
			// ���ص��ƶ�ҳ��ҳ��
			ActionForward actionForward = new ActionForward(reaction);
			return actionForward;
		} else {
			return mapping.findForward("success");
		}
	}

	private ActionForward performSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();
		Vector SelectRoom = null;
		Vector SelectRoomName = null;
		Map map = null;
		String roomId = "";
		roomId = request.getParameter("roomId");
		map = tawRoomDutyCheckBO.getAllRoom(ds);

		SelectRoom = (Vector) map.get("SelectRoom");
		SelectRoomName = (Vector) map.get("SelectRoomName");
		request.setAttribute("roomId", roomId);
		request.setAttribute("SelectRoom", SelectRoom);
		request.setAttribute("SelectRoomName", SelectRoomName);
		return mapping.findForward("success");

	}

	private ActionForward performFormRead(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; // Ӳ��ʵ��Ŀ¼
		String rootDir = TawwpStaticVariable.rootDir; // webĿ¼
		// ��ò���
		String model = "check"; // ������ļ���EOMS_J2EE\WEB-INF\saveXML\check
								// Ŀ¼��
		String reaction = request.getParameter("reaction");
		String myid = request.getParameter("myid");
		String addonsid = request.getParameter("addonsid");
		String action = request.getParameter("action");
		String window = request.getParameter("window");
		String checkid = request.getParameter("checkid");
		StringBuffer StrBuffer = new StringBuffer("");
		String url = "";
		try {
			String fileName = rootDir + rootSaveXMLDir + model + "/" + myid
					+ ".xml";
			File file = new File(fileName);
			// �ж��ļ��Ƿ����
			if (file.exists()) { // �޸�
				url = rootSaveXMLDir + model + "/" + myid + ".xml";
				if (action.equals("edit")) {
					action = "edit";
				}
			} else { // ���
			// TawwpAddonsExtendBO tawwpAddonsExtendBO = new
			// TawwpAddonsExtendBO();
			// TawwpAddonsTableVO tawwpAddonsTableVO = tawwpAddonsExtendBO
			// .viewAddonsTableVO(addonsid);
			// url = tawwpAddonsTableVO.getUrl();
				if (action.equals("edit")) {
					action = "add";
				}
			}
			// String xmltype = TawwpAddonsExtendBO.getAddonsType(url);

			// ��ȡ���ӱ��ҳ��
			// StrBuffer = TawwpAddonsExtendBO.getAddonsHtmlBuffer(url,
			// saveSessionBeanForm.getWrf_UserID(), action);

			request.setAttribute("strbuffer", StrBuffer);
			request.setAttribute("model", model);
			request.setAttribute("myid", myid);
			// request.setAttribute("xmltype", xmltype);
			request.setAttribute("reaction", reaction);
			request.setAttribute("action", action);
			request.setAttribute("url", url);
			request.setAttribute("window", window);
			request.setAttribute("checkid", checkid);
			request.setAttribute("addonsid", addonsid);

			return mapping.findForward("success");

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	private ActionForward performRoomCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();
		ITawRmPlanContentManager ITawRmPlanContentManager = (ITawRmPlanContentManager) getBean("ITawRmPlanContentManager");
		// �ж��Ƿ���ֵ��״̬�����begin
		TawRmRecordDAO tawRmRecordDAO = null;
		int dutyid = java.lang.Integer
				.parseInt(saveSessionBeanForm.getRoomId());
		String userId = StaticMethod.null2String(saveSessionBeanForm
				.getUserid());
		tawRmRecordDAO = new TawRmRecordDAO(ds);
		try {
			if (dutyid == 0
					|| (saveSessionBeanForm.getWorkSerial().equals("0") && !tawRmRecordDAO
							.if_tempManager(dutyid, userId))) {
				return mapping.findForward("notonduty");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �ж��Ƿ���ֵ��״̬�����end

		Map map = null;
		Map tableURLMap = null;
		List addonsTableOfRoom = null;
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
		String roomName = saveSessionBeanForm.getRoomname();
		// String roomId = "250";
		String checktype = request.getParameter("checktype");
		map = ITawRmPlanContentManager.getTawRoomdutyCheckInfo(roomId,
				checktype);
		if (map.isEmpty()) {
			return mapping.findForward("noform");
		} else {
			String tawRoomdutyCheckId = ((TawRoomdutyCheck) map
					.get("tawRoomdutyCheck")).getId();
			addonsTableOfRoom = (List) map.get("tableList");
			tableURLMap = ITawRmPlanContentManager
					.getTableURLMap(tawRoomdutyCheckId);

			request.setAttribute("roomName", roomName);
			request.setAttribute("tawRoomdutyCheck", (TawRoomdutyCheck) map
					.get("tawRoomdutyCheck"));
			request.setAttribute("addonsTableOfRoom", addonsTableOfRoom);
			request.setAttribute("tableURLMap", tableURLMap);

			return mapping.findForward("success");
		}

	}

	private ActionForward performDutyCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();

		// �ж��Ƿ���ֵ��״̬�����begin
		TawRmRecordDAO tawRmRecordDAO = null;
		int dutyid = java.lang.Integer
				.parseInt(saveSessionBeanForm.getRoomId());
		String userId = StaticMethod.null2String(saveSessionBeanForm
				.getUserid());
		tawRmRecordDAO = new TawRmRecordDAO(ds);
		try {
			if (dutyid == 0
					|| (saveSessionBeanForm.getWorkSerial().equals("0") && !tawRmRecordDAO
							.if_tempManager(dutyid, userId))) {
				return mapping.findForward("notonduty");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �ж��Ƿ���ֵ��״̬�����end

		Map map = null;
		Map tableURLMap = null;
		List addonsTableOfRoom = null;
		String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
		String roomName = saveSessionBeanForm.getRoomname();
		ITawRmPlanContentManager ITawRmPlanContentManager = (ITawRmPlanContentManager) getBean("ITawRmPlanContentManager");
		String checktype = request.getParameter("checktype");
		map = ITawRmPlanContentManager.getTawRoomdutyCheckInfo(roomId,
				checktype);
		if (map.isEmpty()) {
			return mapping.findForward("noform");
		} else {
			String tawRoomdutyCheckId = ((TawRoomdutyCheck) map
					.get("tawRoomdutyCheck")).getId();
			addonsTableOfRoom = (List) map.get("tableList");
			tableURLMap = ITawRmPlanContentManager
					.getTableURLMap(tawRoomdutyCheckId);

			request.setAttribute("roomName", roomName);
			request.setAttribute("tawRoomdutyCheck", (TawRoomdutyCheck) map
					.get("tawRoomdutyCheck"));
			request.setAttribute("addonsTableOfRoom", addonsTableOfRoom);
			request.setAttribute("tableURLMap", tableURLMap);

			return mapping.findForward("success");
		}
	}

	private ActionForward performConfigRoomSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();
		String addonsTableIDS = request.getParameter("addonsTableIDS");
		String roomId = request.getParameter("roomId");
		String checktype = request.getParameter("checktype");
		String tawRoomDutyCheckId = request.getParameter("tawRoomDutyCheckId");
		ITawRmPlanContentManager ITawRmPlanContentManager = (ITawRmPlanContentManager) getBean("ITawRmPlanContentManager");
		if (tawRoomDutyCheckId == null) {
			// �� ��һ���j
			ITawRmPlanContentManager.insertTawRoomdutyCheck(roomId,
					addonsTableIDS, checktype);
		} else {
			// �޸Ķ�Զ��j
			tawRoomDutyCheckBO.updateTawRoomDutyCheck(tawRoomDutyCheckId,
					addonsTableIDS);
		}
		Map map = ITawRmPlanContentManager.getTawRoomdutyCheckInfo(roomId,
				checktype);
		if (tawRoomDutyCheckId == null)
			tawRoomDutyCheckId = map.get("tawRoomdutyCheckId").toString();
		TawRmAbnormalRecordBO abnormalRecordBO = new TawRmAbnormalRecordBO(ds);
		abnormalRecordBO.insertAbnormalRecord(2, tawRoomDutyCheckId,
				addonsTableIDS);
		return mapping.findForward("success");
	}

	private ActionForward performConfigRoomNext(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		List addonsList = null;
		List addonsTableOfRoom = null;
		Map map = null;
		TawRmRecordDAO tawRmRecordDAO = null;
		TawRoomDutyCheckBO tawRoomDutyCheckBO = new TawRoomDutyCheckBO();
		String roomId = "";
		roomId = request.getParameter("roomId");
		String checktype = "";
		checktype = request.getParameter("checktype");
		if (roomId == null) {
			roomId = (String) request.getAttribute("roomId");
		}
		tawRmRecordDAO = new TawRmRecordDAO(ds);

		try {
			addonsTableOfRoom = tawRmRecordDAO.getAddons(roomId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("roomId", roomId);
		request.setAttribute("checktype", checktype);
		request.setAttribute("addonsList", addonsTableOfRoom);
		request.setAttribute("addonsTableOfRoom", addonsList);
		// request.setAttribute("tawRoomDutyCheckId", (String) map
		// .get("tawRoomdutyCheckId"));
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		return mapping.findForward("success");
	}

	private ActionForward performConfigRoom(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		Vector SelectRoom = null;
		Vector SelectRoomTemp = null;
		Vector SelectRoomName = null;
		TawRmAssignworkBO tawRmAssignworkBO = null;
		// TawApparatusroomDAO tawApparatusroomDAO = null;
		// TawApparatusroom tawApparatusroom = null;
		String strSelectRoomName = null;

		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO = null;
		// TawApparatusroom tawApparatusroom = null;
		// edit by wangheqi
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		;
		// TawValidatePrivBO tawValidatePrivBO = null;

		try {
			String roomId = request.getParameter("typeId");
			request.setAttribute("roomId", roomId);
			SelectRoom = new Vector();
			SelectRoomName = new Vector();
			// SelectRoomId = new Vector();

			if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
				tawRmAssignworkBO = new TawRmAssignworkBO(ds);
				SelectRoom = tawRmAssignworkBO.getRoomSelect();
				tawApparatusroom = null;
				strSelectRoomName = "";
				Vector removeEle = new Vector();

			} else {
				// tawValidatePrivBO = new TawValidatePrivBO(ds);
				// SelectRoom =
				// tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
				// 2051, 2);
//				SelectRoom = StaticMethod
//						.list2vector(privBO
//								.getPermissions(
//										saveSessionBeanForm.getUserid(),
//										com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
//										com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

			}
			if (SelectRoom.size() > 0) {
				tawApparatusroom = null;
				strSelectRoomName = "";
				Vector removeEle = new Vector();
				for (int i = 0; i < SelectRoom.size(); i++) {
					strSelectRoomName = tawRmAssignworkBO.retrieve(Integer
							.parseInt(String.valueOf(SelectRoom.elementAt(i))));
					if (!"".equals(strSelectRoomName)
							&& strSelectRoomName != null) {

						SelectRoomName.add(strSelectRoomName);
					} else {
						removeEle.add(SelectRoom.elementAt(i));
					}
				}
				SelectRoom.removeAll(removeEle);
			} else {
				return mapping.findForward("nopriv");
			}
			request.setAttribute("SelectRoom", SelectRoom);
			request.setAttribute("SelectRoomName", SelectRoomName);
			/*
			 * SelectRoom = new Vector(); SelectRoomName = new Vector();
			 * 
			 * if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
			 * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
			 * tawRmAssignworkBO.getRoomSelect(); } else { // tawValidatePrivBO =
			 * new TawValidatePrivBO(ds); // SelectRoom = //
			 * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(), //
			 * 2003, 2); SelectRoom = StaticMethod .list2vector(privBO
			 * .getPermissions( saveSessionBeanForm.getUserid(),
			 * com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
			 * com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); } //
			 * zc 3-5 增加功能，满足本机房人员可以进行查询操作的要求 if (SelectRoom.size() == 0) {
			 * String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
			 * if (!roomId.equals("0")) { SelectRoom.add(roomId); } } // if
			 * (SelectRoom.size() > 0) { // tawApparatusroomDAO = new
			 * TawApparatusroomDAO(ds); tawApparatusroom = null;
			 * strSelectRoomName = ""; Vector removeEle = new Vector(); for (int
			 * i = 0; i < SelectRoom.size(); i++) { tawApparatusroom = cptroomBO
			 * .getTawSystemCptroomById(new Integer(String
			 * .valueOf(SelectRoom.elementAt(i))), 0); if (tawApparatusroom !=
			 * null) { strSelectRoomName = StaticMethod
			 * .null2String(tawApparatusroom.getRoomname());
			 * SelectRoomName.add(strSelectRoomName); } else {
			 * removeEle.add(SelectRoom.elementAt(i)); } }
			 * SelectRoom.removeAll(removeEle); } else { return
			 * mapping.findForward("nopriv"); }
			 * request.setAttribute("SelectRoom", SelectRoom);
			 * request.setAttribute("SelectRoomName", SelectRoomName);
			 */
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			SelectRoom = null;
			SelectRoomName = null;
			tawRmAssignworkBO = null;
			cptroomBO = null;
			// tawApparatusroomDAO = null;
			tawApparatusroom = null;
			strSelectRoomName = null;
			privBO = null;
			// tawValidatePrivBO = null;
		}
		return mapping.findForward("success");
	}

	/**
	 * ���������Ϣ��������쳣���֣�����д��?������ع�
	 * 
	 * @param request
	 *            HttpServletRequest �������
	 * @param e
	 *            Exception �쳣����
	 */
	private void generalError(HttpServletRequest request, Exception e) {
		ActionMessages aes = new ActionMessages();
		aes.add("EOMS_WORKPLAN_ERROR", new ActionMessage("error.general", e
				.getMessage()));
		saveMessages(request, aes);
		BocoLog.error(this, 2, "[WORKPLAN_ADDONS] Error -", e);
	}

	public Object getBean(String name) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servlet.getServletContext());
		return ctx.getBean(name);
	}

}
