// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BusinessImplementAction.java

package com.boco.eoms.sheet.businessimplement.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.interfaces.transfer.service.ITransferInterfaceManager;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementMainManager;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BusinessImplementAction extends SheetAction
{

	public BusinessImplementAction()
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

	public ActionForward newPerformDeal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		baseSheet.newPerformDeal(mapping, form, request, response);
		String forwardStr = StaticMethod.nullObject2String(request.getAttribute("forwardStr"));
		if (forwardStr.equals("detail"))
			return mapping.findForward(forwardStr);
		else
			return mapping.findForward("success");
	}

	public void performPreValidateStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		String status = "0";
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFn = new StringBuffer();
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		String operateType = StaticMethod.null2String(request.getParameter("operateType"));
		String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
		String taskName = StaticMethod.null2String(request.getParameter("taskName"));
		BusinessImplementMain businessImplementMain = (BusinessImplementMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
		Integer sheetStatus = businessImplementMain.getStatus();
//		if (sheetStatus.equals(Constants.SHEET_HANG))
//		{
//			status = "2";
//			sb.append("您好，此工单出目前处于挂起状态，不允许您提交!");
//		} else
		if (operateType.equals("92") || operateType.equals("95") || operateType.equals("96"))
		{
			String enable = XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.enable");
			if (enable.equalsIgnoreCase("true"))
			{
				String mainSpecifyType = StaticMethod.nullObject2String(businessImplementMain.getMainSpecifyType());
				String orderSheetId = StaticMethod.nullObject2String(businessImplementMain.getOrderSheetId());
				List list = OrderSheetMethod.getSpecialLineList(orderSheetId, mainSpecifyType);
				Map orderMap = new HashMap();
				orderMap.putAll(SheetBeanUtils.bean2Map(businessImplementMain));
				BocoLog.info(this, "taskName:" + taskName + mainSpecifyType);
				orderMap.put("serviceType", taskName + mainSpecifyType);
				for (int i = 0; i < list.size(); i++)
				{
					List tempList = new ArrayList();
					tempList.add(list.get(i));
					IrmsResourceBo.preOccupyResFinish(orderMap, tempList);
				}

			}
		}
		o.put("text", sb.toString());
		o.put("fn", sbFn.toString());
		data.put(o);
		jsonRoot.put("data", data);
		jsonRoot.put("status", String.valueOf(status));
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void validateData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		String status = "0";
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFn = new StringBuffer();
		try
		{
			String taskName = StaticMethod.null2String(request.getParameter("taskName"));
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			String operateType = StaticMethod.null2String(request.getParameter("operateType"));
			IBusinessImplementMainManager mgr = (IBusinessImplementMainManager)ApplicationContextHolder.getInstance().getBean("iBusinessImplementMainManager");
			BusinessImplementMain main = (BusinessImplementMain)mgr.getSingleMainPO(sheetKey);
			if (operateType.equals("97") && (main.getMainIsCircuitComplete() == null || main.getMainIsCircuitComplete().equals("")))
			{
				sb.append("传输调度还没有完成施工，不能提交！");
				status = "2";
			}
			if (!main.getMainSpecifyType().equals(com.boco.eoms.businessupport.util.Constants._TRANSFER_LINE))
			{
				String orderSheetId = main.getOrderSheetId();
				IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
				boolean b = iOrderSheetManager.validateProductData(orderSheetId, taskName);
				if (!b)
				{
					sb.append("不能提交,请将专线数据填写完整！");
					status = "2";
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb.append("数据校验失败：" + e.getMessage());
			status = "2";
		}
		o.put("text", sb.toString());
		o.put("fn", sbFn.toString());
		data.put(o);
		jsonRoot.put("data", data);
		jsonRoot.put("status", String.valueOf(status));
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void getTraphInfos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		IBaseSheet baseSheet = (IBaseSheet)getBean(mapping.getAttribute());
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		String orderSheetId = StaticMethod.nullObject2String(request.getParameter("orderSheetId"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String mainSpecifyType = StaticMethod.nullObject2String(request.getParameter("mainSpecifyType"));
		ITransferInterfaceManager transferInterfaceManagerImpl = (ITransferInterfaceManager)ApplicationContextHolder.getInstance().getBean("ITransferInterfaceManager");
		BusinessImplementMain businessImplementMain = (BusinessImplementMain)baseSheet.getMainService().getSingleMainPO(sheetKey);
		Map map = SheetBeanUtils.bean2Map(businessImplementMain);
		String result = transferInterfaceManagerImpl.dealTraphInfosResult(map, orderSheetId, mainSpecifyType, sessionform.getUserid(), sessionform.getPassword());
		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().print(result);
	}

	public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
		String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		IBusinessImplementMainManager mgr = (IBusinessImplementMainManager)baseSheet.getMainService();
		List list = mgr.getMainListByParentSheetId(sheetId);
		if (list != null && list.size() > 0)
		{
			BaseMain main = (BaseMain)list.get(0);
			String sheetKey = main.getId();
			if (!sheetKey.equals(""))
			{
				if ("".equals(userid))
					userid = "admin";
				try
				{
					IWorkflowSecutiryService safeService = (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
					javax.security.auth.Subject subject = safeService.logIn(userid, "");
					request.getSession().setAttribute("wpsSubject", subject);
				}
				catch (Exception e)
				{
					BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
				}
				ActionForward forward = mapping.findForward("showInterfaceDraftPage");
				String path = forward.getPath() + "&sheetKey=" + sheetKey;
				System.out.println("path=" + path);
				return new ActionForward(path, false);
			} else
			{
				throw new Exception("sheetNo不能为空");
			}
		} else
		{
			throw new Exception("没找到对应工单，请确认编号正确");
		}
	}

	public ActionForward showNewInputSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		String processname = StaticMethod.nullObject2String(request.getParameter("processname"));
		String taskname = StaticMethod.nullObject2String(request.getParameter("taskname"));
		String orderSheetId = StaticMethod.nullObject2String(request.getParameter("orderSheetId"));
		String title = StaticMethod.nullObject2String(request.getParameter("title"));
		String isShowLanguage = StaticMethod.nullObject2String(request.getParameter("isShowLanguage"));
		String isShowSms = StaticMethod.nullObject2String(request.getParameter("isShowSms"));
		String startType = StaticMethod.nullObject2String(request.getParameter("startType"));
		String mainBelongPronice = StaticMethod.nullObject2String(request.getParameter("isCalledByLangugage"));
		String specialtyType = StaticMethod.nullObject2String(request.getParameter("specialtyType"));
		request.setAttribute("specialtyType", specialtyType);
		if (orderSheetId != null && !orderSheetId.equals(""))
		{
			IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
			com.boco.eoms.businessupport.order.model.OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
			request.setAttribute("orderSheetId", orderSheetId);
			request.setAttribute("title", title);
			request.setAttribute("orderSheet", orderSheet);
			request.setAttribute("isShowLanguage", isShowLanguage);
			request.setAttribute("isShowSms", isShowSms);
			request.setAttribute("startType", startType);
		}
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		baseSheet.showInputNewSheetPage(mapping, form, request, response);
		ITawSheetAccessManager mgr = (ITawSheetAccessManager)ApplicationContextHolder.getInstance().getBean("ItawSheetAccessManager");
		com.boco.eoms.sheet.tool.access.model.TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processname, taskname);
		request.setAttribute("tawSheetAccess", access);
		String flag = (String)request.getSession().getAttribute("flag");
		if ("1".equals(flag))
		{
			request.getSession().removeAttribute("flag");
			return mapping.findForward("newInit");
		} else
		{
			return mapping.findForward("inputNew");
		}
	}

	public ActionForward showNewSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
		String actionForward = baseSheet.showNewSheetPage(mapping, form, request, response);
		String title = StaticMethod.nullObject2String(request.getParameter("title"), "");
		String type = StaticMethod.nullObject2String(request.getParameter("type"), "");
		String specialtyType = "";
		if (type.equals("gprs"))
			specialtyType = com.boco.eoms.businessupport.util.Constants._GPRS_LINE;
		else
		if (type.equals("ip"))
			specialtyType = com.boco.eoms.businessupport.util.Constants._IP_LINE;
		else
		if (type.equals("trans"))
			specialtyType = com.boco.eoms.businessupport.util.Constants._TRANSFER_LINE;
		request.setAttribute("title", title);
		request.setAttribute("specialtyType", specialtyType);
		return mapping.findForward(actionForward);
	}
}
