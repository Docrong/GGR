//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : DispatchForward.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public final class DispatchForward extends Action {

  /**
   *  Forward request to "cancel", {forward}, or "error" mapping, where
   *  {forward} is an action path given in the parameter mapping or in the
   *  request as "forward=actionPath".
   *
   *@param  mapping   The ActionMapping used to select this instance
   *@param  request   The HTTP request we are processing
   *@param  response  The HTTP response we are creating
   *@param  form      Description of Parameter
   *@return           Description of the Returned Value
   */
  public ActionForward execute(ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) {

    // -- isCancelled?
    if (isCancelled(request)) {
      form.reset(mapping, request);
      return (mapping.findForward("cancel"));
    }

    // -- Locals
    ActionForward thisForward = null;
    String wantForward = null;

    // -- Check internal parameter for forward
    wantForward = mapping.getParameter();

    // -- If not found, check request for forward
    if (wantForward == null) {
      wantForward = request.getParameter("forward");
    }

    // -- If found, consult mappings
    if (wantForward != null) {
      thisForward = mapping.findForward(wantForward);
    }

    // -- If anything not found, dispatch error
    if (thisForward == null) {
      thisForward = mapping.findForward("error");
      ActionErrors errors = new ActionErrors();
      errors.add(ActionErrors.GLOBAL_ERROR,
          new ActionError("action.missing.parameter"));
      saveErrors(request, errors);
    }

    return thisForward;
  }
  // end perform

}
// end Action

