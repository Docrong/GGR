package com.boco.eoms.base.webapp.action;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationTrustResolver;
import org.acegisecurity.AuthenticationTrustResolverImpl;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.springframework.mail.SimpleMailMessage;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.model.Role;
import com.boco.eoms.base.model.User;
import com.boco.eoms.base.service.MailEngine;
import com.boco.eoms.base.service.RoleManager;
import com.boco.eoms.base.service.UserExistsException;
import com.boco.eoms.base.service.UserManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.base.util.StringUtil;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.form.UserForm;
import com.boco.eoms.base.webapp.util.RequestUtil;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.action.TawSystemSessionAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;
import com.boco.eoms.portal.sso.client.SSOConstants;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.commontask.model.CommonTaskTask;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.task.impl.ComplaintTask;
import com.boco.eoms.wap.util.WAPConstants;
import com.boco.eoms.wap.util.WapUtil;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;
import com.boco.eoms.workplan.util.TawwpUtil;

/**
 * Implementation of <strong>Action</strong> that interacts with the {@link
 * UserForm} and retrieves values. It interacts with the {@link UserManager} to
 * retrieve/persist values to the database.
 * 
 * <p>
 * <a href="UserAction.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 * 
 */
public final class UserAction extends BaseAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Entering 'add' method");
		// }

		User user = new User();
		user.addRole(new Role(Constants.USER_ROLE));
		UserForm userForm = (UserForm) convert(user);
		updateFormBean(mapping, request, userForm);

		checkForRememberMeLogin(request);

		return mapping.findForward("edit");
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Entering 'cancel' method");
		// }

		if (!StringUtils.equals(request.getParameter("from"), "list")) {
			return mapping.findForward("mainMenu");
		} else {
			return mapping.findForward("viewUsers");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Entering 'delete' method");
		// }

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		UserForm userForm = (UserForm) form;

		// Exceptions are caught by ActionExceptionHandler
		UserManager mgr = (UserManager) getBean("userManager");
		mgr.removeUser(userForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.deleted", userForm.getFirstName() + ' '
						+ userForm.getLastName()));

		saveMessages(request.getSession(), messages);

		// return a forward to searching users
		return mapping.findForward("viewUsers");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Entering 'edit' method");
		// }

		UserForm userForm = (UserForm) form;

		// if URL is "editProfile" - make sure it's the current user
		if (request.getRequestURI().indexOf("editProfile") > -1) {
			// reject if username passed in or "list" parameter passed in
			// someone that is trying this probably knows the AppFuse code
			// but it's a legitimate bug, so I'll fix it. ;-)
			if ((request.getParameter("username") != null)
					|| (request.getParameter("from") != null)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				BocoLog.warn(this, "User '" + request.getRemoteUser()
						+ "' is trying to edit user '"
						+ request.getParameter("username") + "'");

				return null;
			}
		}

		// Exceptions are caught by ActionExceptionHandler
		UserManager mgr = (UserManager) getBean("userManager");
		User user = null;

		// if a user's username is passed in
		if (request.getParameter("username") != null) {
			// lookup the user using that id
			user = mgr.getUserByUsername(userForm.getUsername());
		} else {
			// look it up based on the current user's id
			user = mgr.getUserByUsername(request.getRemoteUser());
		}

		BeanUtils.copyProperties(userForm, convert(user));
		userForm.setConfirmPassword(userForm.getPassword());
		updateFormBean(mapping, request, userForm);

		checkForRememberMeLogin(request);

		// return a forward to edit forward
		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Entering 'save' method");
		// }

		// run validation rules on this form
		// See https://appfuse.dev.java.net/issues/show_bug.cgi?id=128
		ActionMessages errors = form.validate(mapping, request);

		if (!errors.isEmpty()) {
			// saveErrors(request, errors);
			BocoLog.error(this, errors.toString());
			return mapping.findForward("edit");
		}

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		UserForm userForm = (UserForm) form;
		User user = new User();

		// Exceptions are caught by ActionExceptionHandler
		// all we need to persist is the parent object
		BeanUtils.copyProperties(user, userForm);

		Boolean encrypt = (Boolean) getConfiguration().get(
				Constants.ENCRYPT_PASSWORD);

		if (StringUtils.equals(request.getParameter("encryptPass"), "true")
				&& (encrypt != null && encrypt.booleanValue())) {
			String algorithm = (String) getConfiguration().get(
					Constants.ENC_ALGORITHM);

			if (algorithm == null) { // should only happen for test case
				BocoLog.debug(this,
						"assuming testcase, setting algorithm to 'SHA'");
				algorithm = "SHA";
			}

			user.setPassword(StringUtil.encodePassword(user.getPassword(),
					algorithm));
		}

		UserManager mgr = (UserManager) getBean("userManager");
		RoleManager roleMgr = (RoleManager) getBean("roleManager");
		String[] userRoles = request.getParameterValues("userRoles");

		for (int i = 0; userRoles != null && i < userRoles.length; i++) {
			String roleName = userRoles[i];
			user.addRole(roleMgr.getRole(roleName));
		}

		try {
			mgr.saveUser(user);
		} catch (UserExistsException e) {
			// errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
			// "errors.existing.user", userForm.getUsername(), userForm
			// .getEmail()));
			// saveErrors(request, errors);
			BocoLog.error(this, "errors.existing.user "
					+ userForm.getUsername());

			BeanUtils.copyProperties(userForm, convert(user));
			userForm.setConfirmPassword(userForm.getPassword());
			// reset the version # to what was passed in
			userForm.setVersion(request.getParameter("version"));
			updateFormBean(mapping, request, userForm);

			return mapping.findForward("edit");
		}

		BeanUtils.copyProperties(userForm, convert(user));
		userForm.setConfirmPassword(userForm.getPassword());
		updateFormBean(mapping, request, userForm);

		if (!StringUtils.equals(request.getParameter("from"), "list")) {
			// add success messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"user.saved"));
			saveMessages(request.getSession(), messages);

			// return a forward to main Menu
			return mapping.findForward("mainMenu");
		} else {
			// add success messages
			if ("".equals(request.getParameter("version"))) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"user.added", user.getFullName()));
				saveMessages(request.getSession(), messages);
				sendNewUserEmail(request, userForm);

				return mapping.findForward("addUser");
			} else {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"user.updated.byAdmin", user.getFullName()));
				saveMessages(request, messages);

				return mapping.findForward("edit");
			}
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Entering 'search' method");
		// }

		UserForm userForm = (UserForm) form;

		// Exceptions are caught by ActionExceptionHandler
		UserManager mgr = (UserManager) getBean("userManager");
		User user = (User) convert(userForm);
		List users = mgr.getUsers(user);
		request.setAttribute(Constants.USER_LIST, users);

		// return a forward to the user list definition
		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return search(mapping, form, request, response);
	}

	private void sendNewUserEmail(HttpServletRequest request, UserForm userForm)
			throws Exception {
		MessageResources resources = getResources(request);

		// Send user an e-mail
		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "Sending user '" + userForm.getUsername()
				+ "' an account information e-mail");
		// }

		SimpleMailMessage message = (SimpleMailMessage) getBean("mailMessage");
		message.setTo(userForm.getFullName() + "<" + userForm.getEmail() + ">");

		StringBuffer msg = new StringBuffer();
		msg.append(resources.getMessage("newuser.email.message", userForm
				.getFullName()));
		msg.append("\n\n" + resources.getMessage("userForm.username"));
		msg.append(": " + userForm.getUsername() + "\n");
		msg.append(resources.getMessage("userForm.password") + ": ");
		msg.append(userForm.getPassword());
		msg.append("\n\nLogin at: " + RequestUtil.getAppURL(request));
		message.setText(msg.toString());

		message.setSubject(resources.getMessage("signup.email.subject"));

		MailEngine engine = (MailEngine) getBean("mailEngine");
		engine.send(message);
	}

	private void checkForRememberMeLogin(HttpServletRequest request) {
		// if user logged in with remember me, display a warning that they can't
		// change passwords
		BocoLog.debug(this, "checking for remember me login...");

		AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		SecurityContext ctx = SecurityContextHolder.getContext();

		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();

			if (resolver.isRememberMe(auth)) {
				request.getSession().setAttribute("cookieLogin", "true");

				// add warning message
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"userProfile.cookieLogin"));
				saveMessages(request, messages);
			}
		}
	}

	public ActionForward saveSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("liu====");
		//String originPassword = null;
		String userId = null;
		ActionForward forword = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
		TawSystemSessionForm users = (TawSystemSessionForm) request.getSession(
				true).getAttribute("sessionform");
		String token = (String) request.getSession().getAttribute(
				SSOConstants.SSO_TOKEN);
		String onLineUser = (String) request.getSession().getAttribute(
				SSOConstants.SSO_ACCOUNT);
//		if (users != null && onLineUser != null && token != null) {
//			userId = onLineUser;
//
//		} else {
//			// wap登陆方式
//			if (request.getParameter("wapLogin") != null) {
//				String j_username = request.getParameter(StringEscapeUtils.escapeSql("j_username"));
//			//	System.out.println("--20150608---WAP-------j_username--------------" + j_username);
//				String j_password = request.getParameter("j_password");
//				// cookie中保存用户名
//				WapUtil.saveValue4Cookie(WAPConstants.WAP_COOKIE_LOGIN_NAME,
//						j_username, response);
//				// 用户名/手机号 校验
//				String returnValue = "";
//				if (j_username != null) {
//
//					returnValue = userManager.getUserByUserIdOrMobile(
//							j_username, j_password);
//					if ("".equalsIgnoreCase(returnValue)) {
//						return new ActionForward("/wap/login.jsp?error=error");
//
//					}
//				}
//				userId = returnValue;
//			}
//			// 单点登陆方式
//			else if (Constants.LOGIN_SSO.equals(UtilMgrLocator
//					.getEOMSAttributes().getLoginType())) {
//				userId = (String) request.getSession().getAttribute(
//						"edu.yale.its.tp.cas.client.filter.user");
//			}
//			// 以acegi方式登陆
//			else if (Constants.LOGIN_ACEGI.equals(UtilMgrLocator
//					.getEOMSAttributes().getLoginType())) {
//				SecurityContext securityContext = SecurityContextHolder
//						.getContext();
//				TawSystemUser acegiUser = (TawSystemUser) securityContext
//						.getAuthentication().getPrincipal();
//				userId = acegiUser.getUserid();
//			//	originPassword = (String)securityContext.getAuthentication().getCredentials();
////				SecurityContext securityContext = SecurityContextHolder
////				.getContext();
////				TawSystemUser acegiUser = (TawSystemUser) securityContext
////						.getAuthentication().getPrincipal();
////				userId = acegiUser.getUserid();
//		//		System.out.println("---登陆方式acegi-----------originPassword--------------" + originPassword);
//
//			}
//			
//			// 以eoms自带方式登陆
//			else if (Constants.LOGIN_EOMS.equals(UtilMgrLocator
//					.getEOMSAttributes().getLoginType())) {
//		//         userId = request.getParameter("j_username");
				userId = request.getParameter(StringEscapeUtils.escapeSql("j_username"));
			//	System.out.println("--20150608---eoms-------j_username--------------" + userId);
				String userPWD = request.getParameter("j_password");
//		/*		if(userId!=null&&!userId.equals("")){
//					 userId=new String(decode(userId));
//					 userPWD=new String(decode(userPWD));
//				}
//				  
//				//System.out.println("userId,userPwd----->"+userId+"/"+userPWD);
//				if (userId == null || "".equals(userId))
//				{
//					TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
//					if (sessionform != null)
//					{
//						userId = StaticMethod.nullObject2String(StringEscapeUtils.escapeSql(sessionform.getUserid()));
//						userPWD = StaticMethod.nullObject2String(StringEscapeUtils.escapeSql(sessionform.getOriginPassword()));
//						//System.out.println("userId2,userPwd2----->"+userId+"/"+userPWD);
//					}
//				}
//				originPassword = userPWD;
//*/
//				TawSystemUser user = null;
//				//System.out.println("LoginType()==eoms,userId===="+userId+",userPWD==="+userPWD);
//				// SOX，连续登陆n（6）次将锁定用户不得登陆
//				try {
//
//					// 验证用户
//					user = (TawSystemUser) userManager.getUserByuserid(userId);
//
//					if (userId.indexOf("'") != -1) {
//						 throw new BadCredentialsException(messages.getMessage(
//								 "UserAction.badCredentials", "Bad credentials"));
//					}else if (user == null)// 用户不存在，抛异常
//						throw new BadCredentialsException(messages.getMessage(
//								"UserAction.badCredentials", "Bad credentials"));
//					// TODO ldap中验证通过后,不再验证EOMS系统
//					 else if (!user.getPassword().equals(// 密码错误，抛异常
//					 new Md5PasswordEncoder().encodePassword(userPWD,
//					 new String()))) {
//					 throw new BadCredentialsException(messages.getMessage(
//					 "UserAction.badCredentials", "Bad credentials"));
//					 }
//					else if (user.isAccountLocked()) {// 用户被锁定
//						loginErrorReturn(request, response, "LockedException");
//						return mapping.findForward("relogin");
//					} else if (!user.isEnabled()) {// 用户不可用
//						loginErrorReturn(request, response, "DisabledException");
//						return mapping.findForward("relogin");
//					}
//				} catch (Exception e) {
//					// 若登陆失败，则将失败次数累加，到达一定次数则锁住用户
//					user
//							.setFailCount(user.getFailCount() != null ? new Integer(
//									user.getFailCount().intValue() + 1)
//									: new Integer(0));
//					if (user.getFailCount().intValue() >= UserMgrLocator
//							.getUserAttributes().getPasswdRepeatNum()
//							.intValue()) {
//						// 锁定用户
//						user.setAccountLocked(true);
//						userManager.saveTawSystemUser(user);
//						loginErrorReturn(request, response, "LockedException");
//						return mapping.findForward("relogin");
//					}
//					if (user.getUserid()==null||user.getUserid().equals("")){
//					
//						loginErrorReturn(request, response, "DisabledException");
//					 return mapping.findForward("relogin");
//					 }else{
//						 userManager.saveTawSystemUser(user);
//					 }
//					loginErrorReturn(request, response,
//							"BadCredentialsException");
//					return mapping.findForward("relogin");
//				}
//
//				// SOX，判断用户有效期为n(90)天
//				if (new Date().compareTo(DateUtil.addDate(user.getSavetime(),
//						UserMgrLocator.getUserAttributes()
//								.getPasswdAvailableDay().intValue())) > 0) {
//					// 设置user用户不可用
//					user.setEnabled(false);
//					userManager.saveTawSystemUser(user);
//					loginErrorReturn(request, response, "DisabledException");
//					return mapping.findForward("relogin");
//				}
//
//				// 登陆成功则置零
//				if (user.getFailCount() != null
//						&& user.getFailCount().intValue() != 0) {
//					user.setFailCount(new Integer(0));
//					userManager.saveTawSystemUser(user);
//				}
//				request.getSession().setAttribute(SSOConstants.SSO_TOKEN,
//						"notoken");
//
//			}
//		}

		TawSystemSessionForm sessionform = new TawSystemSessionForm();
		sessionform.setUserid(userId);
	/*	TawSystemUser tsu = userManager.getTawSystemUserByuserid(userId) ;
		//2015/04/20 add by lijin
		sessionform.setAttribute1(StaticMethod.nullObject2String(tsu.getAttribute1()));
		sessionform.setAttribute2(StaticMethod.nullObject2String(tsu.getAttribute2()));
		System.out.println("attribute1    =======>>>"+StaticMethod.nullObject2String(tsu.getAttribute1()));
		System.out.println("originPassword    =======>>>"+originPassword);
		sessionform.setOriginPassword(originPassword);*/
		TawSystemSessionAction sessionaction = new TawSystemSessionAction();
		ITawwpStubUserMgr tawwpStubUserMgr = (ITawwpStubUserMgr) getBean("tawwpStubUserMgr");
		request.setAttribute("tawwpStubUserMgr", tawwpStubUserMgr);
		forword = sessionaction.performInit(mapping, sessionform, request,
				response);
 //2014/9/26 begin:add by dengpengfei
			
	/*	
		request.getSession().setAttribute("showornot", "yes");
		try {
			
			String deptId = sessionform.getDeptid();
			String flowName = "";
			Integer startIndex = new Integer(0);
			Integer length = new Integer(0);
			Map condition = new HashMap();
			condition.put("orderCondition", "");
			condition.put("ifAgent", "");
			Map result = null;
				
			//故障工单
			condition.put("mainObject", new CommonFaultMain());
			condition.put("taskObject", new CommonFaultTask());
			flowName = "CommonFaultMainFlowProcess";
			ITaskService commonFaultMgr = (ITaskService)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
			result = commonFaultMgr.getUndoTaskByOverTime(condition, userId, deptId, flowName, startIndex, length);
			String commonfaultCount = "0";
			if(result != null && result.size() > 0) {
				commonfaultCount = ((Integer) result.get("taskTotal")).toString();
			}
			request.getSession().setAttribute("commonfaultCount", commonfaultCount);
			result = null;
			
			//投诉工单
			condition.put("mainObject", new ComplaintMain());
			condition.put("taskObject", new ComplaintTask());
			flowName = "ComplaintProcess";
			ITaskService complaintMgr = (ITaskService)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
			result = complaintMgr.getUndoTaskByOverTime(condition, userId, deptId, flowName, startIndex, length);
			String complaintCount = "0";
			if(result != null && result.size() > 0) {
				complaintCount = ((Integer) result.get("taskTotal")).toString();
			}
			
			request.getSession().setAttribute("complaintCount", complaintCount);
			
			//任务工单CommonTaskMain.java
			condition.put("mainObject", new CommonTaskMain());
			condition.put("taskObject", new CommonTaskTask());
			flowName = "CommonTaskMainFlowProcess";
			ITaskService commontaskMgr = (ITaskService)ApplicationContextHolder.getInstance().getBean("icommontaskTaskManager");
			result = commontaskMgr.getUndoTaskByOverTime(condition, userId, deptId, flowName, startIndex, length);
			String commontaskCount = "0";
			if(result != null && result.size() > 0) {
				commontaskCount = ((Integer) result.get("taskTotal")).toString();
			}
			
			request.getSession().setAttribute("commontaskCount", commontaskCount);
			result = null;
			
			//作业计划 日常执行内容
			ITawwpExecuteMgr tawwpExecuteMgr = (ITawwpExecuteMgr)ApplicationContextHolder.getInstance().getBean("tawwpExecuteMgr");
		
			String monthflag = TawwpUtil.getCurrentDateTime("MM");
            String yearflag = TawwpUtil.getCurrentDateTime("yyyy");
            Map monthPlanVOHash = tawwpExecuteMgr.listExecutePlanNew(userId, deptId,monthflag,yearflag);
            String monthPlanCount = "0"; 
            if(monthPlanVOHash != null && monthPlanVOHash.size() > 0) {
            	monthPlanCount = monthPlanVOHash.size() + "";
			}
        	request.getSession().setAttribute("monthPlanCount", monthPlanCount);
         
		} catch (Exception e) {
			e.printStackTrace();
		}
        //2014/9/26 end:add by dengpengfei
*/
		return forword;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 分离wap登陆方式
	 */
	public ActionForward wapSaveSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String userId = null;
		ActionForward forword = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");

		// wap登陆方式
		if (request.getParameter("wapLogin") != null) {
			String j_username = request.getParameter(StringEscapeUtils.escapeSql("j_username"));
			String j_password = request.getParameter("j_password");
			// cookie中保存用户名
			WapUtil.saveValue4Cookie(WAPConstants.WAP_COOKIE_LOGIN_NAME,
					j_username, response);
			// 用户名/手机号 校验
			String returnValue = "";
			if (j_username != null) {

				returnValue = userManager.getUserByUserIdOrMobile(j_username,
						j_password);
				if ("".equalsIgnoreCase(returnValue)) {
					return new ActionForward("/wap/login.jsp?error=error");

				}
			}
			userId = returnValue;

			TawSystemUser user = null;

			// SOX，连续登陆n（6）次将锁定用户不得登陆
			try {
				// 验证用户
				user = (TawSystemUser) userManager.getUserByuserid(userId);

				if (user == null)// 用户不存在，抛异常
					throw new BadCredentialsException(messages.getMessage(
							"UserAction.badCredentials", "Bad credentials"));
				// TODO ldap中验证通过后,不再验证EOMS系统
				// else if (!user.getPassword().equals(// 密码错误，抛异常
				// new Md5PasswordEncoder().encodePassword(userPWD,
				// new String()))) {
				// throw new BadCredentialsException(messages.getMessage(
				// "UserAction.badCredentials", "Bad credentials"));
				// }
				else if (user.isAccountLocked()) {// 用户被锁定
					loginErrorReturn(request, response, "LockedException");
					return mapping.findForward("relogin");
				} else if (!user.isEnabled()) {// 用户不可用
					loginErrorReturn(request, response, "DisabledException");
					return mapping.findForward("relogin");
				}
			} catch (Exception e) {
				// 若登陆失败，则将失败次数累加，到达一定次数则锁住用户
				user.setFailCount(user.getFailCount() != null ? new Integer(
						user.getFailCount().intValue() + 1) : new Integer(0));
				if (user.getFailCount().intValue() >= UserMgrLocator
						.getUserAttributes().getPasswdRepeatNum().intValue()) {
					// 锁定用户
					user.setAccountLocked(true);
					userManager.saveTawSystemUser(user);
					loginErrorReturn(request, response, "LockedException");
					return mapping.findForward("relogin");
				}
				userManager.saveTawSystemUser(user);
				loginErrorReturn(request, response, "BadCredentialsException");
				return mapping.findForward("relogin");
			}

			// SOX，判断用户有效期为n(90)天
			if (new Date().compareTo(DateUtil.addDate(user.getSavetime(),
					UserMgrLocator.getUserAttributes().getPasswdAvailableDay()
							.intValue())) > 0) {
				// 设置user用户不可用
				user.setEnabled(false);
				userManager.saveTawSystemUser(user);
				loginErrorReturn(request, response, "DisabledException");
				return mapping.findForward("relogin");
			}

			// 登陆成功则置零
			user.setFailCount(new Integer(0));
			userManager.saveTawSystemUser(user);

		}

		TawSystemSessionForm sessionform = new TawSystemSessionForm();
		sessionform.setUserid(userId);

		TawSystemSessionAction sessionaction = new TawSystemSessionAction();
		ITawwpStubUserMgr tawwpStubUserMgr = (ITawwpStubUserMgr) getBean("tawwpStubUserMgr");
		request.setAttribute("tawwpStubUserMgr", tawwpStubUserMgr);
		request.setAttribute("wapLogin", "wap");
		forword = sessionaction.performLogin(mapping, sessionform, request,
				response);

		return forword;
	}

	private void loginErrorReturn(HttpServletRequest request,
			HttpServletResponse response, String strError) {
		request.setAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY, strError);
	}
	
/*	public ActionForward performIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	return mapping.findForward("newIndex");
}

	 *//**
	  *跟新user表的attribute字段 
	  *add by lijin 2015-04-20
	  *//* 
	 
	 public ActionForward updateSequenceOfBlock(ActionMapping mapping, ActionForm form, 
	    		HttpServletRequest request, HttpServletResponse response) throws Exception{
		 
		 IDownLoadSheetAccessoriesService sqlMgr=
			    (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		                                                                                          
		 String sequenceOfBlock=StaticMethod.nullObject2String(request.getParameter("sequenceOfBlock"));
		 String userid=StaticMethod.nullObject2String(request.getParameter("userid"));
		 
		 
		 String sql="update taw_system_user set attribute1='"+sequenceOfBlock+"' where userid='"+userid+"'";
		 System.out.println("updateSequenceOfBlock -----sql -----"+sql);
		 
		 sqlMgr.updateTasks(sql);

		 return null;
	 }
	 
	 *//**
	  *跟新user表的attribute字段 
	  *add by lijin 2015-04-20
	  *//* 
public ActionForward updateSequenceOfEditBlock(ActionMapping mapping, ActionForm form, 
	    		HttpServletRequest request, HttpServletResponse response) throws Exception{
		 
		 IDownLoadSheetAccessoriesService sqlMgr=
			    (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		 
		 String sequenceOfBlock=StaticMethod.nullObject2String(request.getParameter("sequenceOfBlock"));
		 String userid=StaticMethod.nullObject2String(request.getParameter("userid"));
		 
		 String sql="update taw_system_user set attribute2='"+sequenceOfBlock+"' where userid='"+userid+"'";
		 
		 sqlMgr.updateTasks(sql);

		 return null;
	 }


*//**
* 解密
* @param str
* @return
*//*
public static byte[] decode(String str) {
        byte[] data = str.getBytes();
        int len = data.length;
        ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
        int i = 0;
        int b1, b2, b3, b4;

        while (i < len) {
                do {
                        b1 = base64DecodeChars[data[i++]];
                } while (i < len && b1 == -1);
                if (b1 == -1) {
                        break;
                }

                do {
                        b2 = base64DecodeChars[data[i++]];
                } while (i < len && b2 == -1);
                if (b2 == -1) {
                        break;
                }
                buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

                do {
                        b3 = data[i++];
                        if (b3 == 61) {
                                return buf.toByteArray();
                        }
                        b3 = base64DecodeChars[b3];
                } while (i < len && b3 == -1);
                if (b3 == -1) {
                        break;
                }
                buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

                do {
                        b4 = data[i++];
                        if (b4 == 61) {
                                return buf.toByteArray();
                        }
                        b4 = base64DecodeChars[b4];
                } while (i < len && b4 == -1);
                if (b4 == -1) {
                        break;
                }
                buf.write((int) (((b3 & 0x03) << 6) | b4));
        }
        return buf.toByteArray();
}*/

}
