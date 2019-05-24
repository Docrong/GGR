package com.boco.eoms.sheet.circuitdispatch.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.sheet.interfaceBase.service.IAuthentication;

public class IrmsAuthenticationImpl implements IAuthentication{

	public String getAuthenticationResult(String userId, String password) {

		boolean result = false;
		if(result)
			return "irms";
		return null;
	}

	public ActionForward authenticationCommon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return null;
	}
}
