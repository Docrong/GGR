package com.boco.eoms.base.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.model.User;
import com.boco.eoms.base.service.MailEngine;
import com.boco.eoms.base.service.RoleManager;
import com.boco.eoms.base.service.UserExistsException;
import com.boco.eoms.base.service.UserManager;
import com.boco.eoms.base.util.StringUtil;
import com.boco.eoms.base.webapp.form.UserForm;
import com.boco.eoms.base.webapp.util.RequestUtil;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.mail.SimpleMailMessage;

import com.boco.eoms.commons.loging.BocoLog;

/**
 * Action class to allow users to self-register.
 * 
 * <p/> <a href="SignupAction.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * 
 */
public final class SignupAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// if it's an HTTP GET, simply forward to jsp
		if (request.getMethod().equals("GET")) {
			return mapping.findForward("failure");
			// user clicked cancel button
		} else if (isCancelled(request)) {
			return new ActionForward("/");
			// run validation
		} else {
			// run validation rules on this form
			ActionMessages errors = form.validate(mapping, request);
			if (!errors.isEmpty()) {
//				saveErrors(request, errors);
				BocoLog.error(this, errors.toString());
				return mapping.findForward("failure");
			}
		}

//		if (log.isDebugEnabled()) {
		BocoLog.debug(this,"registering user...");
//		}

//		ActionMessages errors = new ActionMessages();
		UserForm userForm = (UserForm) form;
		User user = (User) convert(form);

		// Set the default user role on this new user
		RoleManager roleMgr = (RoleManager) getBean("roleManager");
		user.addRole(roleMgr.getRole(Constants.USER_ROLE));

		try {
			Boolean encrypt = (Boolean) getConfiguration().get(
					Constants.ENCRYPT_PASSWORD);

			if (encrypt != null && encrypt.booleanValue()) {
				String algorithm = (String) getConfiguration().get(
						Constants.ENC_ALGORITHM);
				if (algorithm == null) { // should only happen for test case
					BocoLog.debug(this,"assuming testcase, setting algorigthm to 'SHA'");
					algorithm = "SHA";
				}

				user.setPassword(StringUtil.encodePassword(user.getPassword(),
						algorithm));
			}

			user.setEnabled(true);
			UserManager mgr = (UserManager) getBean("userManager");
			mgr.saveUser(user);
		} catch (UserExistsException e) {
//			log.warn(e.getMessage());
//			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"errors.existing.user", userForm.getUsername(), userForm
//							.getEmail()));
//			saveErrors(request, errors);
			BocoLog.error(this, e.getMessage());
			return mapping.getInputForward();
		}

		ActionMessages messages = new ActionMessages();
		MessageResources resources = getResources(request);

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.registered", userForm.getUsername()));

		saveMessages(request.getSession(), messages);
		request.getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

		// log user in automatically
		Authentication auth = new UsernamePasswordAuthenticationToken(user
				.getUsername(), user.getConfirmPassword());
		try {
			ProviderManager authenticationManager = (ProviderManager) getBean("authenticationManager");
			SecurityContextHolder.getContext().setAuthentication(
					authenticationManager.doAuthentication(auth));
		} catch (NoSuchBeanDefinitionException n) {
			// ignore, should only happen when testing
		}

		// Send user an e-mail
//		if (log.isDebugEnabled()) {
			BocoLog.debug(this,"Sending user '" + userForm.getUsername()
					+ "' an account information e-mail");
//		}

		SimpleMailMessage message = (SimpleMailMessage) getBean("mailMessage");
		message.setTo(user.getFullName() + "<" + user.getEmail() + ">");

		StringBuffer msg = new StringBuffer();
		msg.append(resources.getMessage("signup.email.message"));
		msg.append("\n\n" + resources.getMessage("userForm.username"));
		msg.append(": " + userForm.getUsername() + "\n");
		msg.append(resources.getMessage("userForm.password") + ": ");
		msg.append(userForm.getPassword());
		msg.append("\n\nLogin at: " + RequestUtil.getAppURL(request));
		message.setText(msg.toString());

		message.setSubject(resources.getMessage("signup.email.subject"));

		MailEngine engine = (MailEngine) getBean("mailEngine");
		engine.send(message);

		return mapping.findForward("success");
	}

}
