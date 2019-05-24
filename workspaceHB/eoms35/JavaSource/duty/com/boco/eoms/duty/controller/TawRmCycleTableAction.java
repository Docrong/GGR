package com.boco.eoms.duty.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.bo.TawRmCycleTableBO;
import com.boco.eoms.duty.bo.TawRmRecordBO;
import com.boco.eoms.duty.dao.TawRmCycleTableDAO;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.test.dao.GuiCamera;
import com.boco.eoms.duty.util.DutyMgrLocator;

public class TawRmCycleTableAction extends Action {
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

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		if (isCancelled(request)) {

			return mapping.findForward("cancel");
		}

		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			try {
				myforward = performSave(mapping, form, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("REMOVE".equalsIgnoreCase(myaction)) {
			myforward = performRemove(mapping, form, request, response);
		} else if ("TRASH".equalsIgnoreCase(myaction)) {
			myforward = performTrash(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else if ("SUBMIT".equalsIgnoreCase(myaction)) {
			myforward = performSubmit(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		try {
			String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
			String uploadPath = DutyMgrLocator.getAttributes()
					.getDutyRootPath()
					+ "/";
			String sysTemPaht = request.getRealPath("/"); // 取当前系统路径
			String path = sysTemPaht + uploadPath + timeTag;

			GuiCamera cam = new GuiCamera(path, "png");//

			cam.snapShot();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");

	}

	/**
	 * 列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		List list = new ArrayList();
		TawRmCycleTableDAO tawRmCycleTableDAO = null;
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String roomId = request.getParameter("roomId");
		if (roomId == null) {
			roomId = (String) request.getAttribute("roomId");
		}
		request.setAttribute("roomId", roomId);
		try {
			tawRmCycleTableDAO = new TawRmCycleTableDAO(ds);

			list = tawRmCycleTableDAO.getCycleTable(roomId);
			request.setAttribute("AddonsList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward performTrash(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performRemove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		String id = request.getParameter("id");
		TawRmCycleTableDAO tawRmCycleTableDAO = null;
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String roomId = request.getParameter("roomId");
		request.setAttribute("roomId", roomId);

		try {
			tawRmCycleTableDAO = new TawRmCycleTableDAO(ds);
			tawRmCycleTableDAO.deleteTable(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * 保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward performSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		TawRmCycleTableBO tawRmCycleTableBO = null;
		TawRmCycleTableForm tawRmCycleTableForm = (TawRmCycleTableForm) form;
		// request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("utf-8");
		// response.setContentType("text/html; charset=GBK");
		String roomid = request.getParameter("roomId");
		System.out.println(request.getParameter("name"));
		String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
		String timeDate = StaticMethod
				.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
		String uploadPath = DutyMgrLocator.getAttributes().getDutyRootPath()
				+ "/" + roomid + "/";
		String dbPath = uploadPath + timeTag + ".xls";
		String sysTemPaht = request.getRealPath("/"); // 取当前系统路径
		String path = sysTemPaht + uploadPath;
		String filePath = sysTemPaht + dbPath;
		File tempFile = new File(path);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		request.setAttribute("roomId", roomid);
		FormFile file = tawRmCycleTableForm.getThisFile();

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
			//

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String loginType = UtilMgrLocator.getEOMSAttributes()
					.getLoginType();
			if ("sso".equals(loginType)) {
				tawRmCycleTableForm.setRemark(tawRmCycleTableForm.getRemark());
				tawRmCycleTableForm.setName(tawRmCycleTableForm.getName());
			 } else {
				tawRmCycleTableForm.setRemark(new String(tawRmCycleTableForm
						.getRemark().getBytes("ISO-8859-1"), "UTF-8"));
				tawRmCycleTableForm.setName(new String(tawRmCycleTableForm
						.getName().getBytes("ISO-8859-1"), "UTF-8"));
			} 

			// 内蒙修改
			tawRmCycleTableBO = new TawRmCycleTableBO(ds);
			tawRmCycleTableForm.setCreatTime(timeDate);
			tawRmCycleTableForm.setCreatUser(userId);
			tawRmCycleTableForm.setUrl(dbPath);
			tawRmCycleTableForm.setRoomId(roomid);

			tawRmCycleTableBO.insert(tawRmCycleTableForm);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	private ActionForward performAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String roomId = request.getParameter("roomId");

		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		try {
			request.setAttribute("roomId", roomId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return mapping.findForward("success");
	}

	private ActionForward performEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		TawRmCycleTableDAO tawRmCycleTableDAO = null;
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		String id = request.getParameter("id");
		String roomId = request.getParameter("roomId");
		request.setAttribute("typeId", roomId);

		try {
			tawRmCycleTableDAO = new TawRmCycleTableDAO(ds);
			String url = tawRmCycleTableDAO.getAddonsUrl(id);
			String systemPath = request.getRealPath("/");
			systemPath = StaticMethod.getWebPath();

			String path = systemPath + "/" + url;
			File file = new File(path);
			// 读到流中
			InputStream inStream = new FileInputStream("/" + file);// 文件的存放路径
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
			e.printStackTrace();
		}
		return null;
	}
}
