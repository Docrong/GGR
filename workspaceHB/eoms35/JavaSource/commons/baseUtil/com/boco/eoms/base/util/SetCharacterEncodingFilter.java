package com.boco.eoms.base.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.security.auth.Subject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;

import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.action.TawSystemSessionAction;
import com.boco.eoms.commons.system.session.bo.TawSystemSessionBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;

public class SetCharacterEncodingFilter implements Filter {

	/**
	 * The default character encoding to set for requests that pass through this
	 * filter.
	 */
	protected String encoding = null;

	/**
	 * The filter configuration object we are associated with. If this value is
	 * null, this filter instance is not currently configured.
	 */
	protected FilterConfig filterConfig = null;

	/**
	 * Should a character encoding specified by the client be ignored?
	 */
	protected boolean ignore = true;

	// 没有权限时，跳转的页面
	protected String privpage = "";

	// session超时时，跳转的页面
	protected String timeoutpage = "";
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	 
	 
		try {
			if (ignore || (request.getCharacterEncoding() == null)) {
				String encoding = selectEncoding(request);
				if (encoding != null)
					request.setCharacterEncoding(encoding);
				 
			}
		HttpServletRequest req = (HttpServletRequest)request;
		String  url = req.getRequestURI();
		
		if(url.indexOf("wap")>0){
			timeoutpage = "waptimeout.jsp";
		}
			boolean isTure=true;
//			 Pass control on to the next filter
			if(((HttpServletRequest) request).getSession().getAttribute("sessionform")!=null&&true){
				chain.doFilter(request, response);
				isTure=false; 
			}
			// 获取用户session信息
			if (request.getParameter("type") != null
					&& request.getParameter("type").equalsIgnoreCase(
							"interface")) {
			    
				String userId =request.getParameter("userName");
				if (userId != null) {
					TawSystemSessionForm sessionform = new TawSystemSessionForm();
					sessionform.setUserid(userId);
					TawSystemSessionAction sessionaction = new TawSystemSessionAction();
					ITawwpStubUserMgr tawwpStubUserMgr = (ITawwpStubUserMgr) ApplicationContextHolder
							.getInstance().getBean("tawwpStubUserMgr");

					sessionform = TawSystemSessionBo.getSessionForm(userId);
					
					TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
					String modeName = privassimgr.getNameBycode(request.getParameter("id"));
					logSave log = logSave.getInstance(
							modeName, userId, "0001", request
									.getRemoteAddr(), userId + " 于:"
									+ StaticMethod.getCurrentDateTime()
									+ " 登录系统.", "111");
					
					log.info();
					sessionform.setRomteaddr(request.getRemoteAddr());
					
					List stubUserVOList = new ArrayList();

					try {
						// 获取代理人信息VO对象集合

						stubUserVOList = tawwpStubUserMgr
								.listStubUserByStubuser(userId);
					} catch (Exception e) {
						System.out.print(e);
					}
					sessionform.setStubUserList(stubUserVOList);

				 	// 在session中保存流程引擎登陆信息，add by qinmin

					 try {
						String password = sessionform.getPassword();
						IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
								.getInstance().getBean(
										"WorkflowSecutiryService");
						Subject subject = safeService.logIn(userId, password);
						((HttpServletRequest) request).getSession()
								.setAttribute("wpsSubject", subject);
					} catch (Exception e) {
						BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
					} 
 
					TawSystemTreeBo usertree = TawSystemTreeBo.getInstance();

					// 添加项目所在物理路径
					try {
						sessionform.setRealPath(StaticMethod.getWebPath());
					} catch (FileNotFoundException e) {

					}

					if ("admin".equals(sessionform.getUserid())) {
						((HttpServletRequest) request).getSession()
								.setAttribute("menu",
										usertree.getPrivAdminMenu(userId));
					} else {
						((HttpServletRequest) request)
								.getSession()
								.setAttribute(
										"menu",
										PrivMgrLocator
												.getPrivMgr()
												.operations2json(
														PrivMgrLocator
																.getPrivMgr()
																.listOpertion(
																		sessionform
																				.getUserid(),
																		sessionform
																				.getDeptid(),
																		sessionform
																				.getRolelist(),
																		PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
																		StaticVariable.ROOT_NODE)));

					}

					((HttpServletRequest) request).getSession()
							.setMaxInactiveInterval(-1);

					Vector vecName = new Vector();
					TawSystemCptroom tawApparatusroom = null;
					Vector vecId = new Vector();

					// 得到机房id 机房名称
					// 所属机房
					String room_id = sessionform.getRoomId();
					String room_Name = sessionform.getRoomname();
					if (room_id == null || room_id.equals("")) {
						room_id = "";
						room_Name = "";
					} else {
						request.setAttribute("userid", userId);
						String[] roomNameArray = room_Name.split(",");
						for (int i = 0; i < roomNameArray.length; i++) {
							vecName.add(roomNameArray[i]);

						}
						String[] roomIdArray = room_id.split(",");
						for (int j = 0; j < roomIdArray.length; j++) {
							vecId.add(roomIdArray[j]);
						}
					}
					// 机房域
					String roomid = "";
					String roomName = "";

					List list = TawSystemSessionBo.getRoomInfo(userId);
					for (Iterator it = list.iterator(); it.hasNext();) {
						tawApparatusroom = (TawSystemCptroom) it.next();
						roomid = tawApparatusroom.getId() + "";
						roomName = tawApparatusroom.getRoomname() + "（已排班）";
						if (!vecName.contains(tawApparatusroom.getRoomname())) {
							vecId.add(roomid);
							vecName.add(roomName);
						}
					}

					int size = vecId.size();
					if (size == 0) {
						sessionform.setRoomId("0");
						sessionform.setRoomname("");
						sessionform.setWorkSerial("0");

					} else {

						sessionform.setRoomId(StaticMethod
								.null2String((String) vecId.elementAt(0)));
						sessionform.setRoomname(StaticMethod
								.null2String((String) vecName.elementAt(0)));
					}
					((HttpServletRequest) request).getSession().setAttribute(
							"sessionform", sessionform);
					((HttpServletRequest) request).getSession().setAttribute(
							"type", "interface");
					if(isTure){ 
						try{
						   chain.doFilter(request, response);
						   isTure=false;
						}catch(Exception e){
							
						}
					}
				}
			} 
			if(isTure){
				chain.doFilter(request, response);
			}
			 
			

		} catch (Exception ee) {
			ee.printStackTrace();
			BocoLog.error(this, ee.getMessage());
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = arg0;
		this.privpage = filterConfig.getInitParameter("privpage");
		this.timeoutpage = filterConfig.getInitParameter("timeoutpage");
		

		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");

		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {

		return (this.encoding);

	}

}
