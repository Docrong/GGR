/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.circuitdispatch.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.interfaces.EOMSService.bo.IcrmLoad;
import com.boco.eoms.sheet.base.util.Constants;
//import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink;
import com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchMain;
//import com.boco.eoms.sheet.circuitdispatch.service.ICircuitDispatchMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.service.IModifyTimeManager;

/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CircuitDispatchAction extends SheetAction {

	/**
	 * 显示草图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("draw");
	}

	/**
	 * 显示流程VISO图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("pic");
	}

	/**
	 * 显示KPI
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showKPI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("kpi");
	}
	
//	public ActionForward newPerformAdd(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String outMainClassName = StaticMethod.nullObject2String(request
//				.getParameter("outMainClassName"));
//		if(outMainClassName.length()>0){
//			Object outMain = Class.forName(outMainClassName).newInstance();
//			SheetBeanUtils.populateMap2Bean(outMain, request.getParameterMap());
//			ICircuitDispatchMainManager mgr = (ICircuitDispatchMainManager) ApplicationContextHolder
//			.getInstance().getBean("iCircuitDispatchMainManager");
//			mgr.save(outMain);
//		}
//		
//		String beanName = mapping.getAttribute();
//		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
////		baseSheet.newPerformAdd(mapping, form, request, response);
//		return mapping.findForward("success");
//	}

	public ActionForward newPerformDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");

		BocoLog.info(this, "taskName=" + taskName);
		BocoLog.info(this, "operateType=" + operateType);
		BocoLog.info(this, "sheetKey=" + sheetKey);

		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.newPerformDeal(mapping, form, request, response);

		CircuitDispatchMain main = (CircuitDispatchMain) baseSheet
				.getMainService().getSingleMainPO(sheetKey);
		try {
			if (taskName.equals("PlanTask")) {
				List links = (List) baseSheet.getLinkService()
						.getLinksByMainId(sheetKey);
				String local = "";
				for (int i = 0; i < links.size(); i++) {
					CircuitDispatchLink link = (CircuitDispatchLink) links
							.get(i);
					if (link.getOperateType().intValue() == 110) {
						local = link.getLinkInvolvedCity();
						break;
					}
				}
				IModifyTimeManager mgr = (IModifyTimeManager) ApplicationContextHolder
						.getInstance().getBean("ImodifytimeManager");
				ModifyTime modifytime = new ModifyTime();
				modifytime.setBeginTime(StaticMethod.nullObject2String(request
						.getParameter("linkPlanStartDate")));
				modifytime.setDeleted("0");
				modifytime.setEndTime(StaticMethod.nullObject2String(request
						.getParameter("linkPlanEndDate")));
				modifytime.setFunctionary(StaticMethod
						.nullObject2String(request
								.getParameter("linkExcutePrincipal")));
				modifytime.setIntroduction("");
				modifytime.setLocal(local);
				modifytime.setMetNet("");
				modifytime.setRemarks("");
				modifytime.setSpecialtyOne(main.getMainNetSortOne());
				modifytime.setSpecialtyTwo(main.getMainNetSortTwo());
				modifytime.setSpecialtyThree(main.getMainNetSortThree());
				modifytime.setTitle(main.getTitle());
				modifytime.setType("电路调度");
				mgr.saveModifyTime(modifytime);
			} else if (taskName.equalsIgnoreCase("PermitTask")) {
				if (operateType.equals("121") || operateType.equals("122")) { // 审批不通过

					String enableService = StaticMethod
							.nullObject2String(XmlManage.getFile(
									"/config/circuitdispatch-util.xml")
									.getProperty("EnableService"));
					if (enableService.equalsIgnoreCase("true")) {
						TawSystemSessionForm sessionform = (TawSystemSessionForm) request
								.getSession().getAttribute("sessionform");

						String userId = sessionform.getUserid();
						String icrmUserId = StaticMethod
								.nullObject2String(request.getSession()
										.getAttribute("irms_" + userId));

						List chNameList = new ArrayList();
						List enName = new ArrayList();
						List contentList = new ArrayList();

						chNameList.add("工单id");
						enName.add("sheetId");
						contentList.add(main.getSheetId());

						chNameList.add("用户名");
						enName.add("userId");
						contentList.add(icrmUserId);

						chNameList.add("审核结果");
						enName.add("checkResult");
						contentList.add("1");

						String opDetail = CrmLoader.createOpDetailXml(
								chNameList, enName, contentList);
						IcrmLoad.setCheck("", opDetail);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * 工单作废
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performFroceHold(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		try {
			// 转移工单
			baseSheet.performFroceHold(mapping, form, request, response);
			String operateType = StaticMethod.nullObject2String(request
					.getParameter("operateType"));
			String sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"), "");
			if (String.valueOf(Constants.ACTION_NULLITY).equals(operateType)) {
				String status = "作废";
			}
		} catch (Exception e) {
			// 失败，有可能现有activity状态不正确
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	

	public ActionForward getIrmsUrl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String eomsUserId = "";
		if (sessionform != null)
			eomsUserId = sessionform.getUserid();

		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String sheetId = StaticMethod.nullObject2String(request
				.getParameter("sheetId"));
		String completeTime = StaticMethod.nullObject2String(request
				.getParameter("completeTime"));
		String userId = StaticMethod.nullObject2String(request
				.getParameter("userId"));
		String taskId = StaticMethod.nullObject2String(request
				.getParameter("taskId"));
		String filePath = StaticMethod
				.getFilePathForUrl("classpath:config/circuitdispatch-util.xml");

		String forwardUrl = InterfaceUtilProperties.getInstance().getXmlValue(
				filePath, "dict", "operateType", "url", operateType);
		if (forwardUrl.indexOf("?") <= 0)
			forwardUrl += "?";
		else
			forwardUrl += "&";

		forwardUrl += "taskId=" + taskId ;
		if (operateType.equalsIgnoreCase("design")) {
			forwardUrl += "&eomsSheetId=" + sheetId
					+ "&activityId=TraphDesign&isModify=true&completeTime="
					+ completeTime;
		} else if (operateType.equalsIgnoreCase("designinfo")) {
			forwardUrl += "&eomsSheetId=" + sheetId
					+ "&activityId=TraphDesign&isModify=false";
		} else if (operateType.equalsIgnoreCase("execute")) {
			forwardUrl += "&eomsSheetId=" + sheetId
					+ "&activityId=TraphReply&isModify=true";
		} else if (operateType.equalsIgnoreCase("selectdesign")) {
			forwardUrl += "&eomsSheetId=" + sheetId;
		} else if (operateType.equalsIgnoreCase("hold")) {
			 forwardUrl = forwardUrl + "&eomsSheetId=" + sheetId + "&activityId=TraphArchive&isModify=true";
		        BocoLog.info(this, "forwardUrl:" + forwardUrl);
		}

		if (userId.length() > 0)
			request.getSession().setAttribute("irms_" + eomsUserId, userId);// 标识为已鉴权,将对端的userId保存到session
		String ifAuthentication = StaticMethod.nullObject2String(request
				.getSession().getAttribute("irms_" + eomsUserId));

		if (ifAuthentication.length() > 0) {// 已鉴权
			forwardUrl += "&userId=" + ifAuthentication;
			BocoLog.info(this, "forwardUrl:" + forwardUrl);
			return new ActionForward(forwardUrl, true);
		} else {
			BocoLog.info(this, "forwardUrl:" + forwardUrl);
			request.setAttribute("forwardUrl", forwardUrl);
			request.setAttribute("authenticationBeanId", "IrmsAuthentication");
			request.setAttribute("sheetId", sheetId);
			request.setAttribute("operateType", operateType);
			ActionForward actionForward = mapping
					.findForward("authenticationPage");
			String url = actionForward.getPath();
			url += "&operateType=" + operateType + "&sheetId=" + sheetId
					+ "&taskId=" + taskId + "&completeTime=" + completeTime;

			ActionForward forward = new ActionForward();
			forward.setPath(url);
			forward.setRedirect(true);
			forward.setContextRelative(true);
			return forward;
		}
	}
}
