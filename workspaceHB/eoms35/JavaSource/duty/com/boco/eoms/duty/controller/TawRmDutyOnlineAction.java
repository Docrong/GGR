package com.boco.eoms.duty.controller;

import javax.sql.*;
import java.util.*;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.*;
import javax.servlet.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.*;
//import com.boco.eoms.jbzl.dao.*;
//import com.boco.eoms.jbzl.model.*;
//import com.boco.eoms.jbzl.bo.*;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.bo.TawRmRecordBO;
import com.boco.eoms.duty.util.DutyMgrLocator;

import java.io.*;

//import com.boco.eoms.common.controller.SaveSessionBeanForm;

public class TawRmDutyOnlineAction extends Action {
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
		} else if ("ENTER".equalsIgnoreCase(myaction)) {
			myforward = performEnter(mapping, form, request, response);
		} else if ("FILE".equalsIgnoreCase(myaction)) {
			myforward = performFile(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure");
		}
		return myforward;
	}

	private ActionForward performSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawRmDutyOnlineForm tawRmDutyOnlineForm = (TawRmDutyOnlineForm) form;
		FormFile file = tawRmDutyOnlineForm.getThisFile();
		response.setContentType("text/html; charset=GBK");
		String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
		String fileName = timeTag + file.getFileName().substring(file.getFileName().length()-4,file.getFileName().length());
		String uploadPath = DutyMgrLocator.getAttributes().getDutyRootPath()
				+ "/";
		String dbPath = uploadPath + fileName;
		String sysTemPaht = request.getRealPath("/"); // 取当前系统路径
		String path = sysTemPaht + uploadPath;
		HttpSession session = request.getSession();
		session.setAttribute("path", dbPath);
		request.setAttribute("url", dbPath);
		request.setAttribute("SAY", "发送了一张图片");

		String filePath = sysTemPaht + dbPath;
		File tempFile = new File(path);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
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
		return mapping.findForward("success");
	}

	private ActionForward performFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("success");
	}

	/**
	 * @see 根据roomId取出所有defineTable信息
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward performEnter(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// edit by wangheqi 2.7 to 3.5
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		/*
		 * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
		 * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
		 */
		// HttpSession session=null;
		HashMap DeptHash = null;
		// HashMap sessionMap=null;
		Vector UserInfo = null; // 名称
		int userNumbers = 0;

		// saveSessionBeanForm =
		// (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// 判断是否值班
		String swithduty = saveSessionBeanForm.getWorkSerial();
		// add by wangheqi temp
		saveSessionBeanForm.setWorkSerial("1");
		if (Integer.parseInt(saveSessionBeanForm.getWorkSerial()) == 0
				&& !"0".equals(swithduty)) {
			return mapping.findForward("notonduty");
		}
		try {
			ServletContext application = request.getSession()
					.getServletContext();
			synchronized (application) {
				DeptHash = (HashMap) application.getAttribute("DeptHash");
				userNumbers = (application.getAttribute("userNumbers") == null ? 0
						: ((Integer) application.getAttribute("userNumbers"))
								.intValue());
				if (DeptHash == null)
					DeptHash = new HashMap();
				/*
				 * sessionMap=(HashMap)application.getAttribute("sessionMap");
				 * if(sessionMap==null) sessionMap=new HashMap();
				 */
				String deptname = saveSessionBeanForm.getDeptname().trim();
				String UserName = saveSessionBeanForm.getUsername().trim();
				if (DeptHash.containsKey(deptname)) // 按部门分类
				{
					UserInfo = (Vector) DeptHash.get(deptname);
					if (!UserInfo.contains(UserName)) {
						UserInfo.add(UserName);
						userNumbers++;
						// sessionMap.put(UserName,request.getSession());
					}
					request.getSession().setAttribute("UserName", UserName);
					/*
					 * else { session=(HttpSession)sessionMap.get(UserName);
					 * if(session!=null ) session.invalidate();
					 * request.getSession().setAttribute("UserName", UserName);
					 * sessionMap.put(UserName,request.getSession()); }
					 */
				} else {
					UserInfo = new Vector();
					UserInfo.add(UserName);
					userNumbers++;
					request.getSession().setAttribute("UserName", UserName);
					// sessionMap.put(UserName,request.getSession());
					DeptHash.put(deptname, UserInfo);
				}
				application.setAttribute("DeptHash", DeptHash);
				// application.setAttribute("sessionMap",sessionMap);
				application.setAttribute("userNumbers",
						new Integer(userNumbers));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");

		} finally {
			saveSessionBeanForm = null;
		}
		return mapping.findForward("success");
	}

}
