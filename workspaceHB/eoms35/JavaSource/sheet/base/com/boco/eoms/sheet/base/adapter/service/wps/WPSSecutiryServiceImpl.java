/*
 * Created on 2007-9-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service.wps;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.ibm.websphere.security.auth.callback.WSCallbackHandlerImpl;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WPSSecutiryServiceImpl implements IWorkflowSecutiryService {
   private LoginContext lc;
	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService#logIn(java.lang.String, java.lang.String)
	 */
	public Subject logIn(String userId, String password) throws Exception {
	  CallbackHandler loginHandler = new WSCallbackHandlerImpl(userId, password);
      lc = new LoginContext("WSLogin", loginHandler);
      lc.login();
      Subject subject = lc.getSubject();
	  return subject;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService#logOut()
	 */
	public void logOut() throws Exception {
		// TODO Auto-generated method stub
      lc.logout();
	}

}
