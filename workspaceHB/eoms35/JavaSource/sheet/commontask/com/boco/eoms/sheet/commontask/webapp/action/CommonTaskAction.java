// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonTaskAction.java

package com.boco.eoms.sheet.commontask.webapp.action;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

// Referenced classes of package com.boco.eoms.sheet.commontask.webapp.action:
//			CommonTaskHBMethod

public class CommonTaskAction extends SheetAction
{

	public CommonTaskAction()
	{
	}

	public ActionForward showDrawing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("draw");
	}

	public ActionForward showPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("pic");
	}

	public ActionForward showKPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("kpi");
	}

	public ActionForward showTabundo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("tab");
	}

	public ActionForward showListAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		CommonTaskHBMethod baseSheet = new CommonTaskHBMethod();
		String findForward = "";
		String ifAgent = StaticMethod.nullObject2String(request.getParameter("ifGent"));
		baseSheet.showListUndoAll(mapping, form, request, response);
		if (!"".equals(ifAgent))
			if (ifAgent.equals("0"))
				findForward = "agentlist";
			else
			if (ifAgent.equals("1"))
				findForward = "noagentlist";
		return mapping.findForward(findForward);
	}
        
        public ActionForward showListPersonal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		CommonTaskHBMethod baseSheet = new CommonTaskHBMethod();
		baseSheet.showListUndoPersonal(mapping, form, request, response);
		return mapping.findForward("listall");
	}
}
