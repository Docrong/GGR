/**
 * @see
 * <p>功能描述：用于排班功能的类。</p>
 * @author 赵川
 * @version 2.0
 */

package com.boco.eoms.duty.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.service.ITawRmAssignworkManager;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import com.boco.eoms.duty.bo.TawModelAssignXLSBO;
import com.boco.eoms.duty.bo.TawModelCopyAssignBO;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.bo.TawRmModelSetBo;
import com.boco.eoms.duty.dao.TawUserRoomDAO;

// import com.boco.eoms.jbzl.dao.*;
// import com.boco.eoms.jbzl.model.*;
// import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
// import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.duty.dao.TawRmSysteminfoDAO;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;
import com.boco.eoms.workbench.contact.util.ContactAttriubuteLocator;
import com.boco.eoms.duty.cache.TawDutyCacheBean;

public class TawRmAssignworkAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		if (isCancelled(request)) {

			return mapping.findForward("cancel");
		}

		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("SELECTROOM".equalsIgnoreCase(myaction)) {
			myforward = performSelectroom(mapping, form, request, response);
		} else if ("SELECTTIME".equalsIgnoreCase(myaction)) {
			myforward = performSelecttime(mapping, form, request, response);
		} else if ("ASSIGN".equalsIgnoreCase(myaction)) {
			myforward = performAssign(mapping, form, request, response);
		} else if ("SAVEASSIGN".equalsIgnoreCase(myaction)) {
			myforward = performSaveassign(mapping, form, request, response);
		} else if ("QUERY".equalsIgnoreCase(myaction)) {
			myforward = performQuery(mapping, form, request, response);
		} else if ("QUERYDUTY".equalsIgnoreCase(myaction)) {
			myforward = performQueryDuty(mapping, form, request, response);
		} else if ("QUERYRESULT".equalsIgnoreCase(myaction)) {
			myforward = performQueryresult(mapping, form, request, response);
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else if ("REMOVE".equalsIgnoreCase(myaction)) {
			myforward = performRemove(mapping, form, request, response);
		} else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("EXPORT".equalsIgnoreCase(myaction)) {
			myforward = performExport(mapping, form, request, response);
		} else if ("MODELCOPY".equalsIgnoreCase(myaction)) {
			myforward = modelCopy(mapping, form, request, response);
		} else if ("MODELSETANDASSIGN".equalsIgnoreCase(myaction)) {
			myforward = modelSetAndAssign(mapping, form, request, response);
		} else if ("XLSSELECT".equalsIgnoreCase(myaction)) {
			myforward = select(mapping, form, request, response);
		} else if ("XLSINPUT".equalsIgnoreCase(myaction)) {
			myforward = xlslCopy(mapping, form, request, response);
		} else if ("XLSOUTPUT".equalsIgnoreCase(myaction)) {
			myforward = xlslOutput(mapping, form, request, response);
		}else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}

	private ActionForward select(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String roomId = request.getParameter("roomId");
		request.setAttribute("roomId", roomId);
		return mapping.findForward("selectxls");
	}

	private ActionForward xlslCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmAssignworkForm tawRmModelCopyForm = (TawRmAssignworkForm) form;

		TawModelAssignXLSBO bo = new TawModelAssignXLSBO();
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		
		String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
		String sysTemPaht = request.getRealPath("/");
		String uploadPath = ContactAttriubuteLocator.getNetDiskAttributes()
				.getContactRootPath();// 取当前系统路径
		String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
		File tempFile = new File(sysTemPaht + uploadPath);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}

		FormFile file = tawRmModelCopyForm.getThisFile();
		try {
			InputStream stream = file.getInputStream(); // 把文件读入
			OutputStream outputStream = new FileOutputStream(filePath); // 建立一个上传文件的输出流

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		
		if(bo.xlsToDate(filePath, roomId)){
			return mapping.findForward("success");
		}else{
			request.setAttribute("failReason", "excel的格式不正确！");
			return mapping.findForward("fail");
		}
		
	}
	private ActionForward xlslOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		
		TawRmModelSetBo modelBo = new TawRmModelSetBo();
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		TawModelCopy model;
		try {
			model = modelBo.getModel(roomId);
			String start = model.getStartDate();
			String end = model.getEndDate();
			TawModelAssignXLSBO xlsBo = new TawModelAssignXLSBO();
			String url = xlsBo.exportUrl(String.valueOf(roomId), start, end);
			File file = new File(url);
			// 读到流中
			InputStream inStream = new FileInputStream(file);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("application/x-msdownload;charset=GBK");
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes("UTF-8"), "GBK"));

			// 循环取出流中的数据
			byte[] b = new byte[128];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			generalError(request, e);
			return mapping.findForward("failure");
		}
		
		return null;
	}
	private ActionForward modelSetAndAssign(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		int roomId = Integer.parseInt(String.valueOf(
				request.getParameter("roomId")).trim());
		TawRmModelSetBo trmsb = new TawRmModelSetBo();
		String workSelect = request.getParameter("workSelect");
		String startDate = (String) request.getSession().getAttribute(
				"startDate_model");

		int date_number = Integer.parseInt(request.getParameter("date_num"));
		int cycle_number = StaticMethod.null2int((request
				.getParameter("s_cycle_num")));
		int day = date_number * cycle_number;
		String temp = startDate;
		for (int i = 1; i < day; i++) {
			temp = TawModelCopyAssignBO.getDate(temp, workSelect);
		}

		String userid = saveSessionBeanForm.getUserid();
		int date_num = Integer.parseInt(request.getParameter("date_num"));
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		int cycle_num = StaticMethod.null2int((request
				.getParameter("s_cycle_num")));
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */String AssignType = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String room_name = null;
		// logBO logbo=null;

		String forward = "";

		try {
			trmsb.deledeModel(roomId);
			trmsb.setModel(roomId, startDate, TawModelCopyAssignBO
					.getAddZero(temp), workSelect);
			// saveSessionBeanForm =
			// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			workSelect = StaticMethod.null2String(request
					.getParameter("workSelect"));
			// 每个if 里面都有的东西提出来
			request.setAttribute("roomId", String.valueOf(
					request.getParameter("roomId")).trim());
			tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
					roomId), 0);
			// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
			room_name = tawApparatusroom.getRoomname();
			request.setAttribute("ROOMNAME", room_name);

			AssignType = request.getParameter("assign_type").trim();
			ITawRmAssignworkManager mgr = (ITawRmAssignworkManager) ApplicationContextHolder
					.getInstance().getBean("ItawRmAssignworkManager");

			// 为避免队列中的request被销毁，将值存入hashmap
			Map map = request.getParameterMap();
			HashMap hashMap = new HashMap();
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				String key = StaticMethod.null2String((String) it.next());
				String value = StaticMethod.null2String(request
						.getParameter(key));
				hashMap.put(key, value);
			}
			String sequenceOpen = StaticMethod
					.null2String(((EOMSAttributes) ApplicationContextHolder
							.getInstance().getBean("eomsAttributes"))
							.getSequenceOpen());
			if ("true".equals(sequenceOpen)) {
				ISequenceFacade sequenceFacade = SequenceLocator
						.getSequenceFacade();
				Sequence dutySequence = null;
				try {
					dutySequence = sequenceFacade.getSequence("duty");
				} catch (SequenceNotFoundException e) {
					e.printStackTrace();
				}
				sequenceFacade.put(mgr, "saveTawRmAssignwork", new Class[] {
						String.class, int.class, int.class, int.class,
						int.class, String.class, int.class, String.class,
						HashMap.class }, new Object[] { AssignType,
						new Integer(date_num), new Integer(team_num),
						new Integer(user_num), new Integer(cycle_num),
						workSelect, new Integer(roomId), userid, hashMap },
						null, dutySequence);
				dutySequence.setChanged();
				sequenceFacade.doJob(dutySequence);
			} else {
				mgr.saveTawRmAssignwork(AssignType, date_num, team_num,
						user_num, cycle_num, workSelect, roomId, userid,
						hashMap);
			}
			try {
				TawRmAssignworkBO tawRmAssignworkBO = new TawRmAssignworkBO(
						com.boco.eoms.db.util.ConnectionPool.getInstance());
				tawRmAssignworkBO.RemindAssignwork(roomId, userid, "success");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("sms");
			}
			// mgr.saveTawRmAssignwork(AssignType, date_num, team_num, user_num,
			// cycle_num, workSelect, roomId, userid, request);

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			AssignType = null;
			cptroomBO = null;
			tawApparatusroom = null;
			room_name = null;
		}
		return mapping.findForward("success");
	}

	private ActionForward modelCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		int roomId = Integer.parseInt(request.getParameter("roomId"));
		TawRmModelSetBo tawRmModelSetBo = new TawRmModelSetBo();
		TawModelCopyAssignBO tawModelCopyAssignBO = new TawModelCopyAssignBO();

		try {
			TawModelCopy tawModelCopy = tawRmModelSetBo.getModel(roomId);
			if (tawModelCopy == null) {
				request.setAttribute("failReason", "没有周期模板，请排班后设置排版模板");
				return mapping.findForward("fail");
			} else {
				String workSelect = tawModelCopy.getWorkSelect();
				String assignDate = tawRmModelSetBo.getAssignDate(roomId,
						workSelect);
				if ("".equals(assignDate) && assignDate == null) {
					request.setAttribute("failReason", "周期模班的排班记录被删除,请重新排版");
					return mapping.findForward("fail");
				}
				String startDate = tawModelCopy.getStartDate();
				String endDate = tawModelCopy.getEndDate();
				boolean bool = tawModelCopyAssignBO.modelCopy(roomId,
						startDate, endDate, assignDate);
				if (bool) {
					return mapping.findForward("success");
				} else {
					request.setAttribute("failReason", "排班没成功");
					return mapping.findForward("fail");
				}
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private ActionForward performExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String roomid = request.getParameter("roomId");
		String timeForm = request.getParameter("timeForm");
		String timeTo = request.getParameter("timeTo");
		TawRmAssignworkBO tawRmAssignworkBO = null;
		Vector QueryResult = new Vector();

		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;
		String room_name = null;
		String week = "星期天,星期一,星期二,星期三,星期四,星期五,星期六";
		try {
			tawRmAssignworkBO = new TawRmAssignworkBO(ds);
			/*
			 * QueryResult =
			 * tawRmAssignworkBO.getQueryResultVector(Integer.parseInt(roomid),
			 * timeForm, timeTo); request.setAttribute("QUERYRESULT",
			 * QueryResult);
			 */
			// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
			/*
			 * tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
			 * Integer.parseInt(roomid)), 0);
			 */
			String url = tawRmAssignworkBO.exportUrl(roomid, timeForm, timeTo);
			// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
			/*
			 * room_name = tawApparatusroom.getRoomname();
			 * request.setAttribute("ROOMNAME", room_name);
			 */
			// logbo = new logBO(ds);
			// boolean bool =
			// logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"查询排班",StaticVariable.OPER,request.getRemoteAddr());
			File file = new File(url);
			// 读到流中
			InputStream inStream = new FileInputStream(file);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("application/x-msdownload;charset=GBK");
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes("UTF-8"), "GBK"));

			// 循环取出流中的数据
			byte[] b = new byte[128];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;

			tawRmAssignworkBO = null;
			QueryResult = null;
			cptroomBO = null;
			// tawApparatusroomDAO=null;
			tawApparatusroom = null;
			room_name = null;
			// logbo=null;

		}

		return null;
	}

	/**
	 * @see 排班机房选择
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSelectroom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		Vector SelectRoom = null;
		Vector SelectRoomName = null;
		TawRmAssignworkBO tawRmAssignworkBO = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;
		// edit by wangheqi
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		// TawValidatePrivBO tawValidatePrivBO=null;
		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String strSelectRoomName = null;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		// TawRmSysteminfoForm form = (TawRmSysteminfoForm) actionForm;
		// form.setStrutsAction(TawRmSysteminfoForm.SELECT_ROOM);
		try {
			/*
			 * SelectRoom = new Vector(); SelectRoomName = new Vector();
			 * if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
			 * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
			 * tawRmAssignworkBO.getRoomSelect(); } else{ //tawValidatePrivBO =
			 * new TawValidatePrivBO(ds); //SelectRoom =
			 * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
			 * 2032, 2); SelectRoom =
			 * StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); }
			 * if (SelectRoom.size() > 0) { //tawApparatusroomDAO = new
			 * TawApparatusroomDAO(ds); tawApparatusroom = null;
			 * strSelectRoomName = ""; Vector removeEle=new Vector(); for (int i =
			 * 0; i < SelectRoom.size(); i++) { tawApparatusroom =
			 * cptroomBO.getTawSystemCptroomById(new Integer(
			 * String.valueOf(SelectRoom.elementAt(i))),0); //tawApparatusroom =
			 * tawApparatusroomDAO.retrieve(Integer.parseInt( //
			 * String.valueOf(SelectRoom.elementAt(i)))); if (tawApparatusroom !=
			 * null) { strSelectRoomName =
			 * StaticMethod.null2String(tawApparatusroom. getRoomname());
			 * SelectRoomName.add(strSelectRoomName); } else {
			 * removeEle.add(SelectRoom.elementAt(i)); } }
			 * SelectRoom.removeAll(removeEle); } else{ return
			 * mapping.findForward("nopriv"); }
			 * request.setAttribute("SelectRoom",SelectRoom);
			 * request.setAttribute("SelectRoomName",SelectRoomName);
			 */
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			SelectRoom = null;
			SelectRoomName = null;
			tawRmAssignworkBO = null;
			privBO = null;
			// tawValidatePrivBO=null;
			cptroomBO = null;
			// tawApparatusroomDAO=null;
			tawApparatusroom = null;
			strSelectRoomName = null;
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 排班日期、初始人员等信息的选择
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSelecttime(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */TawRmSysteminfoDAO tawRmSysteminfoDAO = null;
		TawRmSysteminfo tawRmSysteminfo = null;
		TawUserRoomDAO tawUserRoomDAO = null;
		Vector vector_room_user = null;
		String notsysteminfo = null;
		GregorianCalendar cal_start = null;
		String str_start = null;
		Vector vector_team_num = null;
		Vector vector_user_num = null;
		TawRmExchangeDAO tawRmExchangeDAO = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String room_name = null;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		request.setAttribute("roomId", request.getParameter("roomId"));
		int roomId = Integer.parseInt(request.getParameter("roomId"));

		try {
			tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
			tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
			tawUserRoomDAO = new TawUserRoomDAO(ds);
			vector_room_user = tawUserRoomDAO.getRoomUser(roomId, 0);
			if (tawRmSysteminfo == null) {
				notsysteminfo = "对不起，目前该机房值班参数还没有设定，不能排班,请先设定值班参数。";
				request.setAttribute("notsysteminfo", notsysteminfo);
				return mapping.findForward("notsysteminfo");
			}
			if (vector_room_user.size() == 0) {
				notsysteminfo = "对不起，目前该机房无有效值班人员，不能排班,请先设定机房所属人员。";
				request.setAttribute("notsysteminfo", notsysteminfo);
				return mapping.findForward("notsysteminfo");
			}
			cal_start = new GregorianCalendar();
			cal_start.add(cal_start.DATE, 1);
			str_start = StaticMethod.Cal2String(cal_start);
			str_start = String.valueOf(StaticMethod.getVector(str_start, " ")
					.elementAt(0));
			request.setAttribute("str_start", str_start);

			vector_team_num = new Vector();
			for (int i = 1; i < 11; i++) {
				vector_team_num.insertElementAt(String.valueOf(i), i - 1);
			}
			vector_user_num = new Vector();
			for (int i = 1; i < 21; i++) {
				vector_user_num.insertElementAt(String.valueOf(i), i - 1);
			}
			request.setAttribute("VectorTeamNum", vector_team_num);
			request.setAttribute("VectorUserNum", vector_user_num);

			// TawUserRoomBO tawUserRoomBO = new TawUserRoomBO(ds);
			request.setAttribute("RoomUser", vector_room_user);
			tawRmExchangeDAO = new TawRmExchangeDAO(ds);
			int RoomUserNum = vector_room_user.size();
			request.setAttribute("RoomUserNum", String.valueOf(RoomUserNum));
			int TeamNum = tawRmExchangeDAO.get_team_num(roomId);
			request.setAttribute("TeamNum", String.valueOf(TeamNum));
			int UserNum = tawRmSysteminfo.getMaxdutynum();
			request.setAttribute("UserNum", String.valueOf(UserNum));
			// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
			tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
					roomId), 0);
			// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
			room_name = tawApparatusroom.getRoomname();
			request.setAttribute("ROOMNAME", room_name);
			// 对补排班的限制
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			str_start = dtFormat.format(date);  
			TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
			if (tawRmAssignworkDAO.IsAssignWork(roomId, str_start)) {
				request.setAttribute("haveAssign", "1"); // 已经有排班了,不能进行补排班
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			tawRmSysteminfoDAO = null;
			tawRmSysteminfo = null;
			tawUserRoomDAO = null;
			vector_room_user = null;
			notsysteminfo = null;
			cal_start = null;
			str_start = null;
			vector_team_num = null;
			vector_user_num = null;
			tawRmExchangeDAO = null;
			cptroomBO = null;
			// tawApparatusroomDAO=null;
			tawApparatusroom = null;
			room_name = null;
		}
		/*
		 * String roomIdOther = com.boco.eoms.common.util.StaticMethod
		 * .getNodeName("DUTY.OtherAssign"); if
		 * (roomIdOther.equals(String.valueOf(roomId))) return
		 * mapping.findForward("other");
		 */
		return mapping.findForward("success");
	}

	/**
	 * @see 呈现排班模板，根据assign_type，分为自动排班，自动并覆盖排班，手动排班，补排当日班
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performAssign(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */String AssignType = null;
		String str_start = null;
		String str_end = null;
		GregorianCalendar cal_start = null;
		GregorianCalendar cal_end = null;
		Vector vec_start = null;
		String str_startyear = null;
		String str_startmonth = null;
		String str_startday = null;
		Vector vec_end = null;
		String str_endyear = null;
		String str_endmonth = null;
		String str_endday = null;
		String groupNum = null;
		TawUserRoomDAO tawUserRoomDAO = null;
		TawRmExchangeDAO tawRmExchangeDAO = null;
		Vector vector_exchang_time = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String room_name = null;
		try {
			// saveSessionBeanForm =
			// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			String workSelect = request.getParameter("workSelect");
			request.setAttribute("workSelect", workSelect);
			// get assign_type
			int roomId = Integer.parseInt(String.valueOf(
					request.getParameter("roomId")).trim());
			TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
			TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO
					.retrieve(roomId + "");
			request.getSession().setAttribute("startDate_model",
					request.getParameter("start_"));
			 TawExpertRoomDAO expertDao = new TawExpertRoomDAO(ds);
			 Vector expert = new Vector();
			 /* expertDao.getRoomExpertWithNull(roomId);*/
			 request.setAttribute("EXPERT", expert);
			
			int int_stagger = 0;
			if (tawRmSysteminfo != null) {
				int_stagger = tawRmSysteminfo.getStaggertime();
			}
			request.setAttribute("STAGGER", int_stagger + "");
			AssignType = request.getParameter("assign_type_modify");
			if (AssignType == null) {
				AssignType = request.getParameter("assign_type").trim();
				if (AssignType.equals("auto_cover")
						|| AssignType.equals("auto_daily")) {

					str_start = request.getParameter("start_");

					str_end = request.getParameter("end_");
					groupNum = request.getParameter("groupNum");
					cal_start = StaticMethod.String2Cal(str_start);
					GregorianCalendar cal_temp = new GregorianCalendar();
					cal_temp.setTime(cal_start.getTime());
					if (str_end == null) {
						cal_temp.add(cal_start.DATE,
								Integer.parseInt(groupNum) - 1);
						str_end = StaticMethod.Cal2String(cal_temp);
						str_end = StaticMethod.getDateString(str_end, 0);
					}
					cal_end = StaticMethod.String2Cal(str_end);
					if (cal_end.before(cal_start)) {
						String str_temp = str_start;
						str_start = str_end;
						str_end = str_temp;
					}
					vec_start = StaticMethod.getVector(str_start, "-");
					str_startyear = String.valueOf(vec_start.elementAt(0));
					str_startmonth = String.valueOf(vec_start.elementAt(1));
					str_startday = String.valueOf(vec_start.elementAt(2));
					vec_end = StaticMethod.getVector(str_end, "-");
					str_endyear = String.valueOf(vec_end.elementAt(0));
					str_endmonth = String.valueOf(vec_end.elementAt(1));
					str_endday = String.valueOf(vec_end.elementAt(2));

					request.setAttribute("roomId", String.valueOf(
							request.getParameter("roomId")).trim());
					request.setAttribute("ASSIGNTYPE", request
							.getParameter("assign_type"));
					request.setAttribute("YEARSTART", str_startyear);
					request.setAttribute("MONTHSTART", str_startmonth);
					request.setAttribute("DAYSTART", str_startday);
					request.setAttribute("YEAREND", str_endyear);
					request.setAttribute("MONTHEND", str_endmonth);
					request.setAttribute("DAYEND", str_endday);
					request.setAttribute("SROOMUSER", request
							.getParameter("sRoomUser"));
					request.setAttribute("TEAMNUM", request
							.getParameter("TeamNum"));
					request.setAttribute("USERNUM", request
							.getParameter("UserNum"));

					tawUserRoomDAO = new TawUserRoomDAO(ds);
					Vector vector_room_user = tawUserRoomDAO
							.getRoomUserWithNull(roomId, 0);
					request.setAttribute("RoomUser", vector_room_user);
					tawRmExchangeDAO = new TawRmExchangeDAO(ds);
					vector_exchang_time = tawRmExchangeDAO
							.getVectorExchangTime(roomId);
					if (AssignType.equals("auto_cover")) {
						vector_exchang_time = tawRmExchangeDAO
								.getVectorExchangTime(roomId);
					} else {
						// 调整行政班时间为0点到24点
						vector_exchang_time = new Vector(1);
						vector_exchang_time.add("00:00");
						// vector_exchang_time.add("23:59");
					}
					request.setAttribute("EXCHANGETIME", vector_exchang_time);
					// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(roomId), 0);
					// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
					room_name = tawApparatusroom.getRoomname();
					request.setAttribute("ROOMNAME", room_name);

				} else if (AssignType.equals("auto_new")) {

					str_start = request.getParameter("start_");
					str_end = request.getParameter("end_");
					groupNum = request.getParameter("groupNum");
					cal_start = StaticMethod.String2Cal(str_start);
					GregorianCalendar cal_temp = new GregorianCalendar();
					cal_temp.setTime(cal_start.getTime());
					if (str_end == null) {
						cal_temp.add(cal_start.DATE, -Integer
								.parseInt(groupNum) - 1);
						str_end = StaticMethod.Cal2String(cal_temp);
						str_end = StaticMethod.getDateString(str_end, 0);
					}
					cal_end = StaticMethod.String2Cal(str_end);
					if (cal_end.before(cal_start)) {
						String str_temp = str_start;
						str_start = str_end;
						str_end = str_temp;
					}
					vec_start = StaticMethod.getVector(str_start, "-");
					str_startyear = String.valueOf(vec_start.elementAt(0));
					str_startmonth = String.valueOf(vec_start.elementAt(1));
					str_startday = String.valueOf(vec_start.elementAt(2));
					vec_end = StaticMethod.getVector(str_end, "-");
					str_endyear = String.valueOf(vec_end.elementAt(0));
					str_endmonth = String.valueOf(vec_end.elementAt(1));
					str_endday = String.valueOf(vec_end.elementAt(2));

					request.setAttribute("roomId", String.valueOf(
							request.getParameter("roomId")).trim());
					request.setAttribute("ASSIGNTYPE", request
							.getParameter("assign_type"));
					System.out.println(request.getParameter("assign_type"));
					request.setAttribute("YEARSTART", str_startyear);
					request.setAttribute("MONTHSTART", str_startmonth);
					request.setAttribute("DAYSTART", str_startday);
					request.setAttribute("YEAREND", str_endyear);
					request.setAttribute("MONTHEND", str_endmonth);
					request.setAttribute("DAYEND", str_endday);
					request.setAttribute("SROOMUSER", request
							.getParameter("sRoomUser"));
					request.setAttribute("TEAMNUM", request
							.getParameter("TeamNum"));
					request.setAttribute("USERNUM", request
							.getParameter("UserNum"));

					tawUserRoomDAO = new TawUserRoomDAO(ds);
					Vector vector_room_user = tawUserRoomDAO
							.getRoomUserWithNull(roomId, 0);
					request.setAttribute("RoomUser", vector_room_user);
					tawRmExchangeDAO = new TawRmExchangeDAO(ds);
					vector_exchang_time = tawRmExchangeDAO
							.getVectorExchangTime(roomId);

					request.setAttribute("EXCHANGETIME", vector_exchang_time);
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(roomId), 0);
					room_name = tawApparatusroom.getRoomname();
					request.setAttribute("ROOMNAME", room_name);

				} else if ((AssignType.equals("assign_auto"))) {

					str_start = request.getParameter("start_");
					str_end = request.getParameter("end_");
					groupNum = request.getParameter("groupNum");
					cal_start = StaticMethod.String2Cal(str_start);
					cal_end = StaticMethod.String2Cal(str_end);
					GregorianCalendar cal_temp = new GregorianCalendar();
					cal_temp.setTime(cal_start.getTime());
					if (str_end == null) {
						cal_temp.add(cal_start.DATE,
								Integer.parseInt(groupNum) - 1);
						str_end = StaticMethod.Cal2String(cal_temp);
						str_end = StaticMethod.getDateString(str_end, 0);
					}
					cal_end = StaticMethod.String2Cal(str_end);
					if (cal_end.before(cal_start)) {
						String str_temp = str_start;
						str_start = str_end;
						str_end = str_temp;
					}
					vec_start = StaticMethod.getVector(str_start, "-");
					str_startyear = String.valueOf(vec_start.elementAt(0));
					str_startmonth = String.valueOf(vec_start.elementAt(1));
					str_startday = String.valueOf(vec_start.elementAt(2));
					vec_end = StaticMethod.getVector(str_end, "-");
					str_endyear = String.valueOf(vec_end.elementAt(0));
					str_endmonth = String.valueOf(vec_end.elementAt(1));
					str_endday = String.valueOf(vec_end.elementAt(2));

					request.setAttribute("roomId", String.valueOf(
							request.getParameter("roomId")).trim());
					request.setAttribute("ASSIGNTYPE", request
							.getParameter("assign_type"));
					request.setAttribute("YEARSTART", str_startyear);
					request.setAttribute("MONTHSTART", str_startmonth);
					request.setAttribute("DAYSTART", str_startday);
					request.setAttribute("YEAREND", str_endyear);
					request.setAttribute("MONTHEND", str_endmonth);
					request.setAttribute("DAYEND", str_endday);
					request.setAttribute("TEAMNUM", request
							.getParameter("TeamNum"));
					request.setAttribute("USERNUM", request
							.getParameter("UserNum"));

					tawUserRoomDAO = new TawUserRoomDAO(ds);
					Vector vector_room_user = tawUserRoomDAO
							.getRoomUserWithNull(roomId, 0);
					request.setAttribute("RoomUser", vector_room_user);
					tawRmExchangeDAO = new TawRmExchangeDAO(ds);
					vector_exchang_time = tawRmExchangeDAO
							.getVectorExchangTime(roomId);
					request.setAttribute("EXCHANGETIME", vector_exchang_time);
					// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(roomId), 0);
					// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
					room_name = tawApparatusroom.getRoomname();
					request.setAttribute("ROOMNAME", room_name);
					TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(
							ds);
					Vector vecAssignAuto = tawRmAssignworkDAO.getvecAssignAuto(
							roomId, str_start, str_end);
					request.setAttribute("vecAssignAuto", vecAssignAuto);

				} else if ((AssignType.equals("assign_manu"))) {
					str_start = request.getParameter("start_");
					vec_start = StaticMethod.getVector(str_start, "-");
					str_startyear = String.valueOf(vec_start.elementAt(0));
					str_startmonth = String.valueOf(vec_start.elementAt(1));
					str_startday = String.valueOf(vec_start.elementAt(2));

					request.setAttribute("roomId", String.valueOf(
							request.getParameter("roomId")).trim());
					request.setAttribute("ASSIGNTYPE", request
							.getParameter("assign_type"));
					request.setAttribute("YEARSTART", str_startyear);
					request.setAttribute("MONTHSTART", str_startmonth);
					request.setAttribute("DAYSTART", str_startday);

					request.setAttribute("STEAMNUM", request
							.getParameter("sTeamNum"));
					request.setAttribute("SUSERNUM", request
							.getParameter("sUserNum"));

					tawUserRoomDAO = new TawUserRoomDAO(ds);
					Vector vector_room_user = tawUserRoomDAO
							.getRoomUserWithNull(roomId, 0);
					request.setAttribute("RoomUser", vector_room_user);
					tawRmExchangeDAO = new TawRmExchangeDAO(ds);
					// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
					tawApparatusroom = cptroomBO.getTawSystemCptroomById(
							new Integer(roomId), 0);
					// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
					room_name = tawApparatusroom.getRoomname();
					request.setAttribute("ROOMNAME", room_name);
				}
			}// 补排班
			else {
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				str_start = dtFormat.format(date);
				Vector vec_temp = StaticMethod.getVector(str_start, " ");
				str_start = String.valueOf(vec_temp.elementAt(0));
				vec_start = StaticMethod.getVector(str_start, "-");
				str_startyear = String.valueOf(vec_start.elementAt(0));
				str_startmonth = String.valueOf(vec_start.elementAt(1));
				str_startday = String.valueOf(vec_start.elementAt(2));

				request.setAttribute("roomId", String.valueOf(
						request.getParameter("roomId")).trim());
				request.setAttribute("ASSIGNTYPE", AssignType);
				request.setAttribute("YEARSTART", str_startyear);
				request.setAttribute("MONTHSTART", str_startmonth);
				request.setAttribute("DAYSTART", str_startday);
				request.setAttribute("YEAREND", str_startyear);
				request.setAttribute("MONTHEND", str_startmonth);
				request.setAttribute("DAYEND", str_startday);
				request
						.setAttribute("TEAMNUM", request
								.getParameter("TeamNum"));
				request
						.setAttribute("USERNUM", request
								.getParameter("UserNum"));

				tawUserRoomDAO = new TawUserRoomDAO(ds);
				Vector vector_room_user = tawUserRoomDAO.getRoomUserWithNull(
						roomId, 0);
				request.setAttribute("RoomUser", vector_room_user);
				tawRmExchangeDAO = new TawRmExchangeDAO(ds);
				vector_exchang_time = tawRmExchangeDAO
						.getVectorExchangTime(roomId);
				request.setAttribute("EXCHANGETIME", vector_exchang_time);
				// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
				tawApparatusroom = cptroomBO.getTawSystemCptroomById(
						new Integer(roomId), 0);
				// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
				room_name = tawApparatusroom.getRoomname();
				request.setAttribute("ROOMNAME", room_name);
				TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(
						ds);
				Vector vecAssignAuto = tawRmAssignworkDAO.getvecAssignAuto(
						roomId, str_start, str_end);
				request.setAttribute("vecAssignAuto", vecAssignAuto);
				request.setAttribute("flag","1");
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			AssignType = null;
			str_start = null;
			str_end = null;
			cal_start = null;
			cal_end = null;
			vec_start = null;
			str_startyear = null;
			str_startmonth = null;
			str_startday = null;
			vec_end = null;
			str_endyear = null;
			str_endmonth = null;
			str_endday = null;
			tawUserRoomDAO = null;
			tawRmExchangeDAO = null;
			vector_exchang_time = null;
			cptroomBO = null;
			// tawApparatusroomDAO=null;
			tawApparatusroom = null;
			room_name = null;
		}
		/*
		 * if (groupNum != null) return mapping.findForward("other");
		 */
		return mapping.findForward("success");
	}

	/**
	 * @see 保存排班，根据assign_type，分为自动排班，自动并覆盖排班，手动排班，补排当日班
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSaveassign(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userid = saveSessionBeanForm.getUserid();
		int date_num = Integer.parseInt(request.getParameter("date_num"));
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		int cycle_num = StaticMethod.null2int((request
				.getParameter("s_cycle_num")));
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */String AssignType = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;

		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String room_name = null;
		// logBO logbo=null;
		String workSelect = "";
		String forward = "";

		try {
			// saveSessionBeanForm =
			// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			workSelect = StaticMethod.null2String(request
					.getParameter("workSelect"));
			// 每个if 里面都有的东西提出来
			int roomId = Integer.parseInt(request.getParameter("roomId"));
			request.setAttribute("roomId", String.valueOf(
					request.getParameter("roomId")).trim());
			tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
					roomId), 0);
			// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
			room_name = tawApparatusroom.getRoomname();
			request.setAttribute("ROOMNAME", room_name);

			AssignType = request.getParameter("assign_type").trim();
			ITawRmAssignworkManager mgr = (ITawRmAssignworkManager) ApplicationContextHolder
					.getInstance().getBean("ItawRmAssignworkManager");

			// 为避免队列中的request被销毁，将值存入hashmap
			Map map = request.getParameterMap();
			HashMap hashMap = new HashMap();
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				String key = StaticMethod.null2String((String) it.next());
				String value = StaticMethod.null2String(request
						.getParameter(key));
				hashMap.put(key, value);
			}
			String sequenceOpen = StaticMethod
					.null2String(((EOMSAttributes) ApplicationContextHolder
							.getInstance().getBean("eomsAttributes"))
							.getSequenceOpen());
			if ("true".equals(sequenceOpen)) {
				ISequenceFacade sequenceFacade = SequenceLocator
						.getSequenceFacade();
				Sequence dutySequence = null;
				try {
					dutySequence = sequenceFacade.getSequence("duty");
				} catch (SequenceNotFoundException e) {
					e.printStackTrace();
				}
				sequenceFacade.put(mgr, "saveTawRmAssignwork", new Class[] {
						String.class, int.class, int.class, int.class,
						int.class, String.class, int.class, String.class,
						HashMap.class }, new Object[] { AssignType,
						new Integer(date_num), new Integer(team_num),
						new Integer(user_num), new Integer(cycle_num),
						workSelect, new Integer(roomId), userid, hashMap },
						null, dutySequence);
				dutySequence.setChanged();
				sequenceFacade.doJob(dutySequence);
			} else {
				mgr.saveTawRmAssignwork(AssignType, date_num, team_num,
						user_num, cycle_num, workSelect, roomId, userid,
						hashMap);
				String flag = StaticMethod.nullObject2String(request.getParameter("flag"));
				//如果为补排班，则刷新值班cache
				/*if(!flag.equals("")){
					TawDutyCacheBean tawDutyCacheBean = (TawDutyCacheBean) ApplicationContextHolder
					.getInstance().getBean("TawDutyFlushCacheBean");
					tawDutyCacheBean.addDutyCache();
				}*/
			}
			try {
				TawRmAssignworkBO tawRmAssignworkBO = new TawRmAssignworkBO(
						com.boco.eoms.db.util.ConnectionPool.getInstance());
				tawRmAssignworkBO.RemindAssignwork(roomId, userid, "success");
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("sms");
			}
			// mgr.saveTawRmAssignwork(AssignType, date_num, team_num, user_num,
			// cycle_num, workSelect, roomId, userid, request);

		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			AssignType = null;
			cptroomBO = null;
			tawApparatusroom = null;
			room_name = null;
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 查询排班界面
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performQuery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		// Vector SelectRoom=null;
		// Vector SelectRoomName=null;
		// TawRmAssignworkBO tawRmAssignworkBO=null;
		// edit by wangheqi
		// TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		// TawValidatePrivBO tawValidatePrivBO=null;
		// edit by wangheqi
		// TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		// TawSystemCptroom tawApparatusroom = null;
		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom =null;
		// String strSelectRoomName=null;
		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		// TawRmSysteminfoForm form = (TawRmSysteminfoForm) actionForm;
		// form.setStrutsAction(TawRmSysteminfoForm.SELECT_ROOM);
		try {
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			/*
			 * SelectRoom = new Vector(); SelectRoomName = new Vector();
			 * 
			 * if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
			 * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
			 * tawRmAssignworkBO.getRoomSelect(); } else{ //tawValidatePrivBO =
			 * new TawValidatePrivBO(ds); //SelectRoom =
			 * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
			 * 2033, 2); SelectRoom =
			 * StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); }
			 * //zc 1-6 增加功能，满足本机房人员可以进行查询操作的要求 if (SelectRoom.size()==0){
			 * String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
			 * if (!roomId.equals("0")){ SelectRoom.add(roomId); } } // if
			 * (SelectRoom.size() > 0) { //tawApparatusroomDAO = new
			 * TawApparatusroomDAO(ds); //tawApparatusroom = null;
			 * strSelectRoomName = ""; Vector removeEle=new Vector(); for (int i =
			 * 0; i < SelectRoom.size(); i++) { tawApparatusroom =
			 * cptroomBO.getTawSystemCptroomById(new Integer(
			 * String.valueOf(SelectRoom.elementAt(i))),0); if (tawApparatusroom !=
			 * null) { strSelectRoomName =
			 * StaticMethod.null2String(tawApparatusroom. getRoomname());
			 * SelectRoomName.add(strSelectRoomName); } else {
			 * removeEle.add(SelectRoom.elementAt(i)); } }
			 * SelectRoom.removeAll(removeEle); } else{ return
			 * mapping.findForward("nopriv"); }
			 * request.setAttribute("SelectRoom",SelectRoom);
			 * request.setAttribute("SelectRoomName",SelectRoomName);
			 */
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			// SelectRoom=null;
			// SelectRoomName=null;
			// tawRmAssignworkBO=null;
			// privBO = null;
			// tawValidatePrivBO=null;
			// cptroomBO = null;
			// tawApparatusroomDAO=null;
			// tawApparatusroom =null;
			// strSelectRoomName=null;
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 查询排班结果
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performQueryresult(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		String time_from = null;
		String time_to = null;
		TawRmAssignworkBO tawRmAssignworkBO = null;
		Vector QueryResult = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;
		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom=null;
		String room_name = null;
		// logBO logbo=null;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		// TawRmSysteminfoForm form = (TawRmSysteminfoForm) actionForm;
		// form.setStrutsAction(TawRmSysteminfoForm.SELECT_ROOM);

		int roomId = Integer.parseInt(request.getParameter("roomId"));
		request.setAttribute("roomId", request.getParameter("roomId"));
		// time_from = request.getParameter("time_from");
		String time_fromyear = request.getParameter("time_fromyear");
		String time_frommonth = request.getParameter("time_frommonth");
		String time_fromday = request.getParameter("time_fromday");
		time_from = time_fromyear + "-" + time_frommonth + "-" + time_fromday;
		String time_toyear = request.getParameter("time_toyear");
		String time_tomonth = request.getParameter("time_tomonth");
		String time_today = request.getParameter("time_today");
		time_to = time_toyear + "-" + time_tomonth + "-" + time_today;
		time_from = StaticMethod.getAddZero(time_from);
		time_to = StaticMethod.getAddZero(time_to);
		// time_to = request.getParameter("time_to");
		// request.setAttribute("TIMEFROM",request.getParameter("time_from"));
		// request.setAttribute("TIMETO",request.getParameter("time_to"));
		request.setAttribute("TIMEFROM", time_from);
		request.setAttribute("TIMETO", time_to);
		String week = "星期天,星期一,星期二,星期三,星期四,星期五,星期六";
		request.setAttribute("week", week);
		try {
			tawRmAssignworkBO = new TawRmAssignworkBO(ds);
			QueryResult = tawRmAssignworkBO.getQueryResultVector(roomId,
					time_from, time_to);
			request.setAttribute("QUERYRESULT", QueryResult);
			// tawApparatusroomDAO = new TawApparatusroomDAO(ds);
			tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
					roomId), 0);
			// tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
			room_name = tawApparatusroom.getRoomname();
			request.setAttribute("ROOMNAME", room_name);
			// logbo = new logBO(ds);
			// boolean bool =
			// logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"查询排班",StaticVariable.OPER,request.getRemoteAddr());
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			time_from = null;
			time_to = null;
			tawRmAssignworkBO = null;
			QueryResult = null;
			cptroomBO = null;
			// tawApparatusroomDAO=null;
			tawApparatusroom = null;
			room_name = null;
			// logbo=null;

		}
		return mapping.findForward("success");
	}

	/**
	 * @排班列表，目前不用
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		List tawRmAssignworks = null;
		String objKey = null;
		String url = null;
		String pagerHeader = null;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		try {

			tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

			int offset;
			int length = PAGE_LENGTH;
			String pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}

			tawRmAssignworks = tawRmAssignworkDAO.list(offset, length);

			String[] objKeys = { "TawRmAssignwork", "list" };
			objKey = CacheManager.createKey(objKeys);
			Integer size = (Integer) SizeCacheManager.getCache(objKey);
			if (size == null) {
				size = new Integer(tawRmAssignworkDAO.getSize(
						"taw_rm_assignwork", ""));
				SizeCacheManager.putCache(size, objKey, 0);
			}

			url = request.getContextPath() + "/" + mapping.getPath() + ".do";
			pagerHeader = Pager.generate(offset, size.intValue(), length, url);
			request.setAttribute("pagerHeader", pagerHeader);

			request.setAttribute("TAWRMASSIGNWORKS", tawRmAssignworks);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			tawRmAssignworkDAO = null;
			tawRmAssignworks = null;
			objKey = null;
			url = null;
			pagerHeader = null;

		}

		return mapping.findForward("success");
	}

	/**
	 * @see 排班显示，目前不用
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		TawRmAssignwork tawRmAssignwork = null;

		TawRmAssignworkForm form = (TawRmAssignworkForm) actionForm;
		try {

			tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

			int id = Integer.parseInt(request.getParameter("id"));
			tawRmAssignwork = tawRmAssignworkDAO.retrieve(id);
			if (tawRmAssignwork == null) {
				ActionErrors aes = new ActionErrors();
				aes.add(aes.GLOBAL_ERROR, new ActionError(
						"error.object.notfound", "TawRmAssignwork"));
				saveErrors(request, aes);

			} else {
				org.apache.commons.beanutils.BeanUtils.populate(form,
						org.apache.commons.beanutils.BeanUtils
								.describe(tawRmAssignwork));
				form.setDutydate(tawRmAssignwork.getDutydate().toString());
				form.setStarttimeDefined(tawRmAssignwork.getStarttimeDefined()
						.toString());
				form.setEndtimeDefined(tawRmAssignwork.getEndtimeDefined()
						.toString());
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			tawRmAssignworkDAO = null;
			tawRmAssignwork = null;

		}
		return mapping.findForward("success");
	}

	/**
	 * @see 排班保存，目前不用
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		TawRmAssignwork tawRmAssignwork = null;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		TawRmAssignworkForm form = (TawRmAssignworkForm) actionForm;

		try {

			tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

			tawRmAssignwork = new TawRmAssignwork();
			org.apache.commons.beanutils.BeanUtils.populate(tawRmAssignwork,
					org.apache.commons.beanutils.BeanUtils.describe(form));
			/*
			 * tawRmAssignwork.setDutydate(java.sql.Timestamp.valueOf(form.getDutydate()));
			 * tawRmAssignwork.setStarttimeDefined(java.sql.Timestamp.valueOf(form.getStarttimeDefined()));
			 * tawRmAssignwork.setEndtimeDefined(java.sql.Timestamp.valueOf(form.getEndtimeDefined()));
			 */
			int strutsAction = form.getStrutsAction();
			if (strutsAction == TawRmAssignworkForm.ADD) {
				int id = tawRmAssignwork.getId();
				if (tawRmAssignworkDAO.retrieve(id) == null) {
					tawRmAssignworkDAO.insert(tawRmAssignwork);
				} else {
					sqlDuplicateError(request, "TawRmAssignwork");
					return mapping.findForward("failure");
				}
			} else if (strutsAction == TawRmAssignworkForm.EDIT) {
				tawRmAssignworkDAO.update(tawRmAssignwork);
			}
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			tawRmAssignworkDAO = null;
			tawRmAssignwork = null;

		}
		return mapping.findForward("success");
	}

	/**
	 * @see 排班编辑，目前不用
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		TawRmAssignwork tawRmAssignwork = null;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		TawRmAssignworkForm form = (TawRmAssignworkForm) actionForm;
		form.setStrutsAction(TawRmAssignworkForm.EDIT);
		try {

			tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

			int id = Integer.parseInt(request.getParameter("id"));

			tawRmAssignwork = tawRmAssignworkDAO.retrieve(id);
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawRmAssignwork));
			form.setDutydate(tawRmAssignwork.getDutydate().toString());
			form.setStarttimeDefined(tawRmAssignwork.getStarttimeDefined()
					.toString());
			form.setEndtimeDefined(tawRmAssignwork.getEndtimeDefined()
					.toString());
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			tawRmAssignworkDAO = null;
			tawRmAssignwork = null;

		}
		return mapping.findForward("success");
	}

	/**
	 * @see 排班新增，目前不用
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawRmAssignworkForm form = (TawRmAssignworkForm) actionForm;
		form.setStrutsAction(TawRmAssignworkForm.ADD);
		return mapping.findForward("success");
	}

	/**
	 * @see 排班删除，目前不用
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performRemove(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		return performView(mapping, actionForm, request, response);
	}

	private ActionForward performTrash(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawRmAssignworkForm form = (TawRmAssignworkForm) actionForm;
		try {

			TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);

			int id = Integer.parseInt(request.getParameter("id"));
			tawRmAssignworkDAO.delete(id);
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
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

	/**
	 * @see 查询排班界面
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performQueryDuty(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String roomId = request.getParameter("roomId");
		request.setAttribute("roomId", roomId);
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		// Vector SelectRoom=null;
		// Vector SelectRoomName=null;
		// TawRmAssignworkBO tawRmAssignworkBO=null;
		// edit by wangheqi
		// TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		// TawValidatePrivBO tawValidatePrivBO=null;
		// edit by wangheqi
		// TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		// TawSystemCptroom tawApparatusroom = null;
		// TawApparatusroomDAO tawApparatusroomDAO=null;
		// TawApparatusroom tawApparatusroom =null;
		// String strSelectRoomName=null;
		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		// TawRmSysteminfoForm form = (TawRmSysteminfoForm) actionForm;
		// form.setStrutsAction(TawRmSysteminfoForm.SELECT_ROOM);
		try {
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			/*
			 * SelectRoom = new Vector(); SelectRoomName = new Vector();
			 * 
			 * if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
			 * tawRmAssignworkBO = new TawRmAssignworkBO(ds); SelectRoom =
			 * tawRmAssignworkBO.getRoomSelect(); } else{ //tawValidatePrivBO =
			 * new TawValidatePrivBO(ds); //SelectRoom =
			 * tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
			 * 2033, 2); SelectRoom =
			 * StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM)); }
			 * //zc 1-6 增加功能，满足本机房人员可以进行查询操作的要求 if (SelectRoom.size()==0){
			 * String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
			 * if (!roomId.equals("0")){ SelectRoom.add(roomId); } } // if
			 * (SelectRoom.size() > 0) { //tawApparatusroomDAO = new
			 * TawApparatusroomDAO(ds); //tawApparatusroom = null;
			 * strSelectRoomName = ""; Vector removeEle=new Vector(); for (int i =
			 * 0; i < SelectRoom.size(); i++) { tawApparatusroom =
			 * cptroomBO.getTawSystemCptroomById(new Integer(
			 * String.valueOf(SelectRoom.elementAt(i))),0); if (tawApparatusroom !=
			 * null) { strSelectRoomName =
			 * StaticMethod.null2String(tawApparatusroom. getRoomname());
			 * SelectRoomName.add(strSelectRoomName); } else {
			 * removeEle.add(SelectRoom.elementAt(i)); } }
			 * SelectRoom.removeAll(removeEle); } else{ return
			 * mapping.findForward("nopriv"); }
			 * request.setAttribute("SelectRoom",SelectRoom);
			 * request.setAttribute("SelectRoomName",SelectRoomName);
			 */
		} catch (Exception e) {
			generalError(request, e);
			return mapping.findForward("failure");
		} finally {
			saveSessionBeanForm = null;
			// SelectRoom=null;
			// SelectRoomName=null;
			// tawRmAssignworkBO=null;
			// privBO = null;
			// tawValidatePrivBO=null;
			// cptroomBO = null;
			// tawApparatusroomDAO=null;
			// tawApparatusroom =null;
			// strSelectRoomName=null;
		}
		return mapping.findForward("success");
	}

}
