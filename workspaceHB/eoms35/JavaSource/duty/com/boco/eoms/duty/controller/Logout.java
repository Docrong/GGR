//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : Logout.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.controller;

import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.struts.action.*;

public class Logout extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.invalidate();

        // uncomment if use resin
        // com.caucho.http.security.JdbcAuthenticator auth;
        // auth = (com.caucho.http.security.JdbcAuthenticator) application.getAttribute("caucho.authenticator");
        // if (auth != null) auth.logout(request, response, application, auth.getUserPrincipal(request, response, application));

        return mapping.findForward("success");
    }

}
