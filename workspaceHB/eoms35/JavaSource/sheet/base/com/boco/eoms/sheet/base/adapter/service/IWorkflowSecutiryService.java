/*
 * Created on 2007-9-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service;

import javax.security.auth.Subject;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IWorkflowSecutiryService {
    public Subject logIn(String userId, String password) throws Exception;

    public void logOut() throws Exception;
}
