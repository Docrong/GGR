/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.accessories.util.CompressBook;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.qo.IWorkSheetQO;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import com.boco.eoms.sheet.limit.util.SheetLimitUtil;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 15:02:49
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class SheetAction extends NewSheetAction {

	/**
	 * 工单的初始化页面 本流程新增 调用 可实现本流程的特殊处理需求
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showNewSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		String actionForward=baseSheet.showNewSheetPage(mapping, form, request, response);// 实现各自流程的特殊处理
		System.out.println("====="+request.getParameter("templateId"));
		return mapping.findForward(actionForward);

	}

	/**
	 * 工单的初始化页面 流程互调时 调用的新增
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showNewInputSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		String processname = StaticMethod.nullObject2String(request
				.getParameter("processname"));
		String taskname = StaticMethod.nullObject2String(request
				.getParameter("taskname"));

		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showInputNewSheetPage(mapping, form, request, response);

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processname,taskname);
		request.setAttribute("tawSheetAccess", access);
		return mapping.findForward("inputNew");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		boolean invokeFlag = (boolean)baseSheet.performIsInvokeProcess(mapping, form, request, response);
		System.out.println("invokeFlag is ======="+invokeFlag);
		if(invokeFlag == true){
			return mapping.findForward("invokepage");
		}else{
		    baseSheet.showDealPage(mapping, form, request, response);

			// String sendRoleId =
			// baseSheet.getMainService().getMainObject().getSendRoleId();
			// ITawSystemSubRoleManager ITawSystemSubRoleManager =
			// (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
			// TawSystemSubRole sendRole =
			// ITawSystemSubRoleManager.getTawSystemSubRole(Long.parseLong(sendRoleId));
			// request.setAttribute("sendRole",sendRole);
		    return mapping.findForward("dealpage");
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInputDealPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showInputDealPage(mapping, form, request, response);
		// String forwardPath =
		// servletPath.substring(servletPath.lastIndexOf("/")+1,servletPath.indexOf(".html"));
		return mapping.findForward("inputDealpage");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showRejectPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showRejectPage(mapping, form, request, response);
		return mapping.findForward("reject");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInputRejectPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showInputRejectPage(mapping, form, request, response);
		// String forwardPath =
		// servletPath.substring(servletPath.lastIndexOf("/")+1,servletPath.indexOf(".html"));
		return mapping.findForward("inputReject");
	}

	/**
	 * 工单的详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDetailPage(mapping, form, request, response);
		return mapping.findForward("detail");
	}

	/**
	 * 显示归档页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showHoldPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showHoldPage(mapping, form, request, response);
		return mapping.findForward("hold");
	}

	/**
	 * 显示撤销页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCancelPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showCancelPage(mapping, form, request, response);
		return mapping.findForward("cancel");
	}

	/**
	 * 显示撤销框架页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCancelInputPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showCancelInputPage(mapping, form, request, response);
		return mapping.findForward("inputcancel");
	}

	/**
	 * 显示删除页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDeletePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDeletePage(mapping, form, request, response);
		return mapping.findForward("delete");
	}

	/**
	 * 显示工单查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showQueryPage(mapping, form, request, response);
		return mapping.findForward("query");
	}

	/**
	 * 新增提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performAdd(mapping, form, request, response);
		request.setAttribute("listType", "ownerList");
		return mapping.findForward("success");
	}

	/**
	 * 草稿
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDraftPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String processname = StaticMethod.nullObject2String(request
				.getParameter("processname"));
		String taskname = StaticMethod.nullObject2String(request
				.getParameter("taskname"));
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDraftPage(mapping, form, request, response);
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
		.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processname,
				taskname);
		request.setAttribute("tawSheetAccess", access);
		return mapping.findForward("draft");
	}

	/**
	 * 修改工单后提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performUpdate(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performQuery(mapping, form, request, response);
		return mapping.findForward("querylist");
	}
	
	/**
	 * 列表查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performListQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performListQuery(mapping, form, request, response);
		String findForward = StaticMethod.nullObject2String(request.getParameter("findForward"));
		return mapping.findForward(findForward);
	}

	/**
	 * LINK的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performDeal(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 显示未处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListsendundo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showListUndo(mapping, form, request, response);		
		return mapping.findForward("list");
	}

	
	/**
	 * 显示已处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListsenddone(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showListsenddone(mapping, form, request, response);
		return mapping.findForward("mainlist");
	}

	/**
	 * 显示草稿列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDraftList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDraftList(mapping, form, request, response);
		return mapping.findForward("draftList");
	}

	/**
	 * 显示归档工单列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showHoldedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showHoldedList(mapping, form, request, response);
		return mapping.findForward("holdlist");
	}

	/**
	 * 显示撤销页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCancelList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("showCancelList sheetaction");
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showCancelList(mapping, form, request, response);
		return mapping.findForward("cancellist");
	}

	/**
	 * 工单的详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSheetDealList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showSheetDealList(mapping, form, request, response);
		return mapping.findForward("history");
	}

	/**
	 * 工作页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPortal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("portal");
	}

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
	 * 显示草图
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
	 * 显示由我启动列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showOwnStarterList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showOwnStarterList(mapping, form, request, response);
		return mapping.findForward("startlist");
	}

	/**
	 * main工单的详细信息页面，如归档，由我启动
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showMainDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.showMainDetailPage(mapping, form, request, response);
		String forwardstr = "maindetail";
		if(request.getAttribute("distributeForward")!=null&&!request.getAttribute("distributeForward").equals("")){
			forwardstr = (String)request.getAttribute("distributeForward");
		}
		return mapping.findForward(forwardstr);

	}

	/**
	 * main工单的撤消
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @throws Exception
	 */
	public ActionForward performCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.performCancel(mapping, form, request, response);
		return mapping.findForward("operatesuccess");
	}

	/**
	 * 工单预提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.performPreCommit(mapping, form, request, response);
	}

	/**
	 * 申明任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performClaimTask(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.performClaim(mapping, form, request, response);
		return mapping.findForward("detail");
	}

	/**
	 * 创建子任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performCreateSubTask(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.performCreateSubTask(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 呈现创建子任务的界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInputSplit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		boolean invokeFlag = (boolean)baseSheet.performIsInvokeProcess(mapping, form, request, response);
		if(invokeFlag == true){
			return mapping.findForward("invokepage");
		}else{
			baseSheet.showInputSplitPage(mapping, form, request, response);
			return mapping.findForward("split");
		}
		
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showEventPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showEventPage(mapping, form, request, response);
		return mapping.findForward("event");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInputEventPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showInputEventPage(mapping, form, request, response);
		return mapping.findForward("inputEvent");
	}

	/**
	 * 执行流程Event
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performProcessEvent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.performProcessEvent(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 工单移交输入页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTransferWorkItemPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		boolean invokeFlag = (boolean)baseSheet.performIsInvokeProcess(mapping, form, request, response);
			// 转移工单
			if(invokeFlag == true){
				return mapping.findForward("invokepage");
			}else{
				baseSheet.showTransferWorkItemPage(mapping, form, request, response);
				return mapping.findForward("transfer");
			}
	
	}

	/**
	 * 工单移交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performTransferWorkItem(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		// 转移工单
		baseSheet.performTransferWorkItem(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 根据用户查找所有的模板（工单的main表）
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTemplatesByUserId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.getTemplatesByUserId(mapping, form, request, response);
		return mapping.findForward("templates");
	}

	/**
	 * 根据用户查找所有的模板（工单的link表）
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDealTemplatesByUserId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.getDealTemplatesByUserId(mapping, form, request, response);
		String draft = request.getParameter("draft");
		if (draft != null && draft.equalsIgnoreCase("DraftHumTask")) {
			return mapping.findForward("templates");
		} else {
			return mapping.findForward("dealTemplates");
		}

	}

	/**
	 * 引用模板(main表)
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward referenceTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String templateId = request.getParameter("templateId");
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.referenceTemplate(mapping, form, request, response);
		if (templateId != null && !templateId.equals("")) {
			return mapping.findForward("new");
		} else {
			return mapping.findForward("dealpage");
		}

	}

	/**
	 * 模板页面(main表)
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTemplateInputSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showInputTemplateSheetPage(mapping, form, request, response);
		return mapping.findForward("inputNew");
	}

	/**
	 * 模板页面（link表）
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTemplateDealInputSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDealInputTemplate(mapping, form, request, response);
		return mapping.findForward("inputDealpage");
	}

	/**
	 * 保存模板(main表)
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.saveTemplate(mapping, form, request, response);
		return mapping.findForward("successMessage");
	}

	/**
	 * 保存模板(link表)
	 * 
	 * @author wangjianhua
	 * @date 20008-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDealTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.saveDealTemplate(mapping, form, request, response);
		return mapping.findForward("successMessage");
	}

	/**
	 * 工单处理信息的保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performSaveInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performSaveDealInfo(mapping, form, request, response);// 实现各自流程的特殊处理
		return mapping.findForward("saveInfo");
	}

	/**
	 * 工单处理信息的保存并退出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performSaveInfoAndExit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performSaveDealInfo(mapping, form, request, response);// 实现各自流程的特殊处理
		String draft = request.getParameter("draft");
		if (draft != null && !draft.equals("")) {
			return mapping.findForward("showDraftList");
		}
		return mapping.findForward("saveInfoAndExit");
	}

	/**
	 * 查看main工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward openTemplateInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getTemplate(mapping, form, request, response);
		return mapping.findForward("templateDetail");
	}

	/**
	 * 查看link工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward openDealTemplateInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getDealTemplate(mapping, form, request, response);
		return mapping.findForward("dealTemplateDetail");
	}

	/**
	 * 编辑main工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editTemplateInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getTemplate(mapping, form, request, response);
		return mapping.findForward("new");
	}

	/**
	 * 编辑link工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editDealTemplateInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getDealTemplate(mapping, form, request, response);
		return mapping.findForward("editDealTemplate");
	}

	/**
	 * 删除main工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removeTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.removeTemplate(mapping, form, request, response);
		return mapping.findForward("successMessage");
	}

	/**
	 * 删除link工单模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removeDealTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.removeDealTemplate(mapping, form, request, response);
		return mapping.findForward("successMessage");
	}

	/**
	 * display attachments
	 * 
	 * @author wangjianhua
	 * @date 2008-08-02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSheetAccessoriesList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showSheetAccessoriesList(mapping, form, request, response);
		return mapping.findForward("sheetAccessories");
	}

	/**
	 * 工单强制归档
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showForceHoldPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws Exception  {

		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		// 转移工单
		baseSheet.showForceHoldPage(mapping, form, request, response);
		return mapping.findForward("forceHold");
	}

	/**
	 * 工单强制归档
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
		// 转移工单
		baseSheet.performFroceHold(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * 显示流程图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showWorkFlow(mapping, form, request, response);
		return mapping.findForward("showWorkFlow");
	}

	/**
	 * 
	 * 显示流程实例图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangxb
	 * @since 2008-09-09
	 */
	public ActionForward showWorkFlowInstance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showWorkFlowInstance(mapping, form, request, response);
		return mapping.findForward("showWorkFlowInstance");
	}
	
	/**
	 * 查看实例图的详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLinkOperatePage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getLinkOperatePage(mapping, form, request, response);
		return mapping.findForward("getLinkOperatePage");
	}
	
	

	/**
	 * 管理者列表
	 * 
	 * @author yangliangliang
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListForAdmin(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showListForAdmin(mapping, form, request, response);
		return mapping.findForward("adminlist");
	}

	public void getAllDoRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getAllDoRoleNodes(mapping, form, request, response);

	}

	public void getNowDoRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getNowDoRoleNodes(mapping, form, request, response);

	}

	public void getPreRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getPreRoleNodes(mapping, form, request, response);
	}

	public ActionForward showRemarkPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
		baseSheet.showRemarkPage(mapping, form, request, response);
		return mapping.findForward("remark");

	}

	public ActionForward showPhaseBackToUpPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		boolean invokeFlag = (boolean)baseSheet.performIsInvokeProcess(mapping, form, request, response);
		if(invokeFlag == true){
			return mapping.findForward("invokepage");
		}else{
			baseSheet.showPhaseBackToUpPage(mapping, form, request, response);
			return mapping.findForward("phaseBackToUp");
		}
		
	}

	public ActionForward showPhaseAdvicePage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showPhaseAdvicePage(mapping, form, request, response);
		return mapping.findForward("phaseAdvice");
	}

	public ActionForward showDealReplyAcceptPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDealReplyAcceptPage(mapping, form, request, response);
		return mapping.findForward("dealReplyAccept");
	}

	public ActionForward performDealReplyAccept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performDealReplyAccept(mapping, form, request, response);
		return mapping.findForward("success");
	}

	public ActionForward showDealReplyRejectPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showDealReplyRejectPage(mapping, form, request, response);
		return mapping.findForward("dealReplyReject");
	}

	public ActionForward performDealReplyReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performDealReplyReject(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	public ActionForward showInvokeReplyPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");//被调流程mainId
		//String correlationKey="";//待回调流程关系id
		String parentMainId="";//主调流程mainId
		if (!sheetKey.equals("")) {
//			从main表里查询出调用我的工单
//			BaseMain mainObject = getMainService().getSingleMainPO(sheetKey);
//			correlationKey=StaticMethod.nullObject2String(mainObject.getCorrelationKey());
			
            //从SHEET_TOOL_RELATION表里查询调用我的工单
			ITawSheetRelationManager mgr = (ITawSheetRelationManager) getBean("ITawSheetRelationManager");	        
//			List toList = mgr.getRelationSheetByCurrentId(sheetKey);
//			if(!toList.isEmpty()){
//				parentMainId=((TawSheetRelation) toList.get(0)).getParentId();
//			}
		}
		//request.setAttribute("correlationKey", correlationKey);	
		request.setAttribute("sheetKey", parentMainId);	
		baseSheet.showInvokeReplyPage(mapping, form, request, response);
		return mapping.findForward("reply");
	}
	public ActionForward performInvokeReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performInvokeReply(mapping, form, request, response);
		return mapping.findForward("successMessage");
	}
	/**
	 * 整合中   新增提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newPerformAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.newPerformAdd(mapping, form, request, response);
		return mapping.findForward("success");
	}
	/**
	 * 整合中   LINK的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newPerformDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.newPerformDeal(mapping, form, request, response);
		return mapping.findForward("success");
	}
	/**
	 * 整合中   非流程动作的处理：目前包括抄送、阶段回复、阶段通知
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newPerformNonFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.newPerformNonFlow(mapping, form, request, response);
		return mapping.findForward("success");
	}

	
	/**
	 * 批量处理界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performBatchDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		try{
		baseSheet.performBatchDeal(mapping, form, request, response);
		}catch(Exception e){
		   System.out.println("Batch Deal has error!");
		}
		return mapping.findForward("success");
	}

	/**
	 * 显示本角色未处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListsendundoByRole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showListUndoByRole(mapping, form, request, response);
		/**增加属性，区别是否是同组查看模式列表。add by 秦敏 20090902**/
		request.setAttribute("listType", "teamList");
		return mapping.findForward("list");
	}

	/**
	 * 显示本角色已处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showListsenddoneByRole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showListsenddoneByRole(mapping, form, request, response);
		return mapping.findForward("mainlist");
	}
	/**
	 * 显示工单隐藏的查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showQueryHidePage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showQueryHidePage(mapping, form, request, response);
		return mapping.findForward("queryhide");
	}
	/**
	 * 工单隐藏查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performQueryHide(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performQueryHide(mapping, form, request, response);
		return mapping.findForward("queryhidelist");
	}

	/**
	 * 工单隐藏查询提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performHide(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performHide(mapping, form, request, response);
		return mapping.findForward("successMessage");
	}
	
	/**
	 * @author yyk
	 * @see 根据历史工单生成新的工单新增页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showNewSendPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String processname = StaticMethod.nullObject2String(request
				.getParameter("processname"));
		String taskname = StaticMethod.nullObject2String(request
				.getParameter("taskname"));
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showNewSendPage(mapping, form, request, response);
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
		.getInstance().getBean("ItawSheetAccessManager");
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processname,
				taskname);
		request.setAttribute("tawSheetAccess", access);
		return mapping.findForward("newSheet");
	}	
	/**
	 * 根据专业，查询时限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newShowLimit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String flowTemplateName = StaticMethod.nullObject2String(request
				.getParameter("flowName"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		LevelLimit levelLimit = SheetLimitUtil.makeLevelLimit(request);
		ISheetDealLimitManager mgr = (ISheetDealLimitManager) getBean("iSheetDealLimitManager");
		HashMap specialsMap = SheetLimitUtil.makeSpecialsMap(levelLimit);
		specialsMap.put("flowName", flowTemplateName);
		List sheetLimitList = mgr.getlevelLimitBySpecials(specialsMap);
		LevelLimit limit = null;
		if (sheetLimitList != null && sheetLimitList.size() > 0) {
			limit = (LevelLimit) sheetLimitList.get(0);
			if (!taskName.equals("")) {
				String levelId = limit.getId();
				List stepList = mgr.getStepLimitByLevel(levelId);
				limit = new LevelLimit();
				if (stepList != null && stepList.size() > 0) {
					for (int i = 0; i < stepList.size(); i++) {
						StepLimit step = (StepLimit) stepList.get(i);
						if (step.getTaskName().equals(taskName)) {
							limit.setAcceptLimit(step.getAcceptLimit());
							limit.setReplyLimit(step.getCompleteLimit());
							break;
						}
					}
				}
			}
		} else {
			limit = new LevelLimit();
		}
		if(limit.getAcceptLimit()==null)limit.setAcceptLimit("0");
		if(limit.getReplyLimit()==null)limit.setReplyLimit("0");
		Date currentDate = new Date();
		//将工作时间和休息时间计算进来
		int acceptLimit = SheetLimitUtil.getTimeLimitByConfig(currentDate,0,Integer.parseInt(limit.getAcceptLimit()),flowTemplateName);
		int completeLimit = SheetLimitUtil.getTimeLimitByConfig(currentDate,acceptLimit,Integer.parseInt(limit.getReplyLimit()),flowTemplateName);
		limit.setAcceptLimit(""+acceptLimit);
		limit.setReplyLimit(""+completeLimit);
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(limit);
		JSONUtil.print(response, jsonRoot.toString());
	}
	
	/**
	 * 根据专业，查询时限
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newShowDealLimit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	     String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
		 ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
		 SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
		 JSONObject jsonRoot = new JSONObject();
		 jsonRoot = JSONObject.fromObject(sheetLimit);
		 JSONUtil.print(response, jsonRoot.toString());
	}
	
	
	/**
	 * 显示未处理列表(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAtomLists(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getAtomLists(mapping, form, request, response);		
		return null;
	}
	/**
	 * 显示详细信息页面(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAtomDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showAtomDetailPage(mapping, form, request, response);
		return null;
	}	
	
	
	
	/**
	 * 申明任务(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performClaimTaskAtom(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performClaimTaskAtom(mapping, form, request, response);
		return null;
	}		
	
	
	/**
	 * 任务的处理(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performDealAtom(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performDealAtom(mapping, form, request, response);
		return null;
	}	
	
	
	/**
	 * 进入阶段回复(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPhaseReplyPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showPhaseReplyPage(mapping, form, request, response);		
		return null;
	}	
	
	
	/**
	 * 阶段回复(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performNonFlowAtom(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.performNonFlowAtom(mapping, form, request, response);		
		return null;
	}	
	
	
	/**
	 * 显示未处理列表(portal)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUndoListsForPortal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getUndoListsForPortal(mapping, form, request, response);		
		return null;
	}	
	/**
	 * 显示已处理列表(portal)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDoneListsForPortal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getDoneListsForPortal(mapping, form, request, response);		
		return null;
	}

	/**
	 * 同组模式待处理列表（本角色已接单未处理工单）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward showUndoListForSameTeam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showUndoListForSameTeam(mapping, form, request, response);
		return mapping.findForward("list");
	}
    /**
     * 分派回复内容的复用（将分派回复的link对象转换成JSON对象）
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */	
	public void getJsonLink(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			System.out.println("id======"+id);
			String beanName = StaticMethod.nullObject2String(request.getParameter("beanName"));
			IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
			BaseLink baselink = baseSheet.getLinkService().getSingleLinkPO(id);
			 JSONObject jsonRoot = new JSONObject();
			 jsonRoot = JSONObject.fromObject(baselink);
			 JSONUtil.print(response, jsonRoot.toString());
		}
	/**
	 * 工单的初始化页面 本流程新增调用 贾雷优化，用于老版工单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newShowCCPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showInputDealPage(mapping, form, request, response);
		return mapping.findForward("cc");
	}
	
	/**
	 * 获取待归档工单任务列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	public ActionForward showUnHoldList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showUnHoldList(mapping, form, request, response);
		return mapping.findForward("list");
	}

	/**
	 * 获取待处理工单任务列表（不含过滤步骤的任务）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	public ActionForward showUndoListByFilter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showUndoListByFilter(mapping, form, request, response);
		return mapping.findForward("list");
	}	
	
	/**
	 * 获取界面列表中的所有附件压缩包 由于目前有技术难度，暂时不采用该方法 难度为，如果在打包时，将文件名修改成正确的中文名称
	 * 由于不知道如何将中文文件传递到服务器上，在打包时附带这些
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public HashMap downLoadAttacthOfSheetList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);

		// 从连接中获取导出类型，目前默认为查询界面
		String exportType = StaticMethod.nullObject2String(request
				.getParameter("exportType"));
		// 从连接中获取appCode
		String appCode = StaticMethod.nullObject2String(request
				.getParameter("appCode"));

		Map requesetMap = request.getParameterMap();
		HashMap condition = new HashMap();
		IWorkSheetQO qo = baseSheet.getMainService().getWorkSheetQO();
		String sql = qo.getClauseSql(requesetMap, condition);
		if (!sql.equals("")) {

			String aliasMain = qo.getAliasMain();
			String aliasLink = qo.getAliasLink();
			String poMain = qo.getPoMain();
			String poLink = qo.getPoLink();
			String poTask = qo.getPoTask();

			String linkTable = HibernateConfigurationHelper
					.getTableName(baseSheet.getLinkService().getLinkObject()
							.getClass());
			// 替换poMain为main表名
			if (sql.indexOf(poMain) != -1) {
				String mainTable = HibernateConfigurationHelper
						.getTableName(baseSheet.getMainService()
								.getMainObject().getClass());
				if (poMain.equals(aliasMain)) {
					aliasMain = mainTable;
				}
				sql = sql.replaceAll(poMain, mainTable);
				sql = sql.replaceAll(" as ", " ");
			}
			// 替换poLink为link表名
			if (sql.indexOf(poLink) != -1) {
				sql = sql.replaceAll(poLink, linkTable);
				if (poLink.equals(aliasLink)) {
					aliasMain = linkTable;
				}
			}
			// 替换poTask为link表名
			if (sql.indexOf(poTask) != -1) {
				String taskTable = HibernateConfigurationHelper
						.getTableName(baseSheet.getTaskService()
								.getTaskModelObject().getClass());
				sql = sql.replaceAll(poTask, taskTable);
			}
			// 开始拼接查询满足条件的工单的所有附件
			String atachSql = "";
			String selectSql = "select " + aliasMain + ".sheetId ";

			String fromSql = sql.substring(sql.indexOf(" from "), sql
					.indexOf(" where "));
			String whereSql = sql.substring(sql.indexOf(" where "), sql
					.length());
			String orderSql = aliasMain + ".sheetId ";
			if (whereSql.indexOf(" order by") != -1) {
				whereSql = whereSql + "," + orderSql;
			} else {
				whereSql = " order by " + orderSql;
			}
			String attLinkAliasName = " att" + aliasLink;
			String joinSql = " right join " + linkTable + attLinkAliasName
					+ " on " + attLinkAliasName + ".MAINID=" + aliasMain
					+ ".ID ";
			Map attColumnmap = baseSheet.getAttachmentAttributeOfOjbect();
			List mainAttachList = (List) attColumnmap.get("mainObject");
			List linkAttributeList = (List) attColumnmap.get("linkObject");
			List attCoulumList = new ArrayList();
			String mainJoinSql = "";
			// 得到main里的所有附件字段
			for (int i = 0; mainAttachList != null && i < mainAttachList.size(); i++) {
				String attName = StaticMethod.nullObject2String(mainAttachList
						.get(i));
				selectSql = selectSql + "," + aliasMain + "." + attName;
				if(!mainJoinSql.equals("")){
					mainJoinSql = mainJoinSql + " or ";
				}
				mainJoinSql = mainJoinSql + "  (" + aliasMain + "." + attName
				+ " is not null or " + aliasMain + "." + attName
				+ "!='')";
				attCoulumList.add(attName);
			}
			// 得到link里的所有附件字段
			String linkJoinSql = "";
			for (int i = 0; linkAttributeList != null
					&& i < linkAttributeList.size(); i++) {
				String attName = StaticMethod
						.nullObject2String(linkAttributeList.get(i));
				selectSql = selectSql + "," + attLinkAliasName + "." + attName;
				if(!linkJoinSql.equals("")){
					linkJoinSql = linkJoinSql + " or ";
				}
				linkJoinSql = linkJoinSql + "  (" + attLinkAliasName + "." + attName
						+ " is not null or " + attLinkAliasName + "." + attName
						+ "!='')";
				attCoulumList.add(attName);
			}
			joinSql = joinSql + " and (" + mainJoinSql + " or " + linkJoinSql + ")";
			atachSql = selectSql + fromSql + joinSql + whereSql;
			// 根据sql，查询所有满足条件的附件集合
			IDownLoadSheetAccessoriesService jdbcManager = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
					.getInstance().getBean("IDownLoadSheetAccessoriesService");

			List list = jdbcManager.getSheetAccessoriesList(atachSql);
			String zipName = "";
			String uploadPath = "";
			String newFilePath = "";
			String errString = "";
			// 根据是否有文件需要打包，确认是否打包和提供下载
			boolean ifZipAndDownload = false;
			if (list != null && list.size() > 0) {
				// 循环计算，由于采用了右联，所以查询结果中实际会包含重复的连接
				HashMap attMapBySheet = new HashMap();
				Iterator _objIterator = list.iterator();
				while (_objIterator.hasNext()) {
					List sheetList = new ArrayList();
					Map _objMap = (Map) _objIterator.next();
					String sheetId = StaticMethod.nullObject2String(_objMap
							.get("SHEETID"));
					if (attMapBySheet.containsKey(sheetId)) {
						sheetList = (List) attMapBySheet.get(sheetId);
					}
					for (int j = 0; attCoulumList != null
							&& j < attCoulumList.size(); j++) {
						String attValues = StaticMethod
								.nullObject2String(attCoulumList.get(j));
						String fileid = StaticMethod.nullObject2String(_objMap
								.get(attValues.toUpperCase()));
//						if (!fileid.equals("")) {
//							fileid = fileid.replaceAll("'", "");
//						}
						if (!fileid.equals("") && !sheetList.contains(fileid)) {
							sheetList.add(fileid);
						}
					}
					attMapBySheet.put(sheetId, sheetList);
				}

				// String path = ((AccessoriesAttributes)
				// ApplicationContextHolder
				// .getInstance().getBean("accessoriesAttributes"))
				// .getUploadPath();
				TawSystemSessionForm sessionform = (TawSystemSessionForm) request
						.getSession().getAttribute("sessionform");
				Date date = new Date();
				SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
				String result = f.format(date);
				String newForder = result + sessionform.getUserid();
				String separator = "/";
				// 默认分隔符为/
				// newForder = separator + newForder;

				/**
				 * 开始建目录和复制文件到指定目录，目录格式为年月日时分秒+用户userid
				 */
				Iterator itAttach = attMapBySheet.keySet().iterator();
				
				// 最终生成的文件的路径
				zipName = result + sessionform.getUserid();

				while (itAttach.hasNext()) {
					String sheetId = StaticMethod.nullObject2String(itAttach
							.next());
					List singlsheet = (List) attMapBySheet.get(sheetId);
					if (singlsheet != null && singlsheet.size() > 0) {
						String newFileFullPath = "";
						if (!uploadPath.equals("")) {
							newFileFullPath = uploadPath + newForder
									+ separator + sheetId + separator;
						}
						String[] attids = new String[singlsheet.size()];
						for (int i = 0; singlsheet != null
								&& i < singlsheet.size(); i++) {
							String attId = StaticMethod
									.nullObject2String(singlsheet.get(i));
//							if (!attId.equals("")) {
//								attId.replace("'", "");
//							}
							attids[i] = attId;
							// zip.ZipUtil(attids, subForder);

						}
						String attidCorrlection = "";
						// 循环拷贝文件到流水号目录下
						for (int i = 0; attids != null && attids.length > 0
								&& i < attids.length; i++) {
							if (!attidCorrlection.equals("")) {
								attidCorrlection = attidCorrlection + ",";
							}
							attidCorrlection = attidCorrlection + attids[i];
						}
						if (!attidCorrlection.equals("")) {
							List attTemList = baseSheet.getMainService()
									.getMainDAO().getAllAttachmentsBySheet(
											"" + attidCorrlection + "");
							String newfileFullPathWithCNName = "";
							for (int i = 0; attTemList != null
									&& i < attTemList.size(); i++) {
								TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories) attTemList
										.get(i);
								/**
								 * 获取原始文件路径以及目录
								 */
								if (uploadPath.equals("")) {
									String configPath = AccessoriesMgrLocator
											.getTawCommonsAccessoriesManagerCOS()
											.getFilePath(
													tawCommonsAccessories
															.getAppCode());
									uploadPath = configPath;
									if (configPath.indexOf(separator) > 0) {
										configPath = configPath.replaceAll(
												"\\", separator);
										if (configPath.lastIndexOf(separator) == configPath
												.length()) {
											configPath = configPath
													.substring(
															configPath
																	.lastIndexOf(separator) - 1,
															configPath
																	.lastIndexOf(separator));
										}
									}
									newFilePath = uploadPath + newForder
											+ separator;
									com.boco.eoms.common.util.StaticMethod
											.newFolder(newFilePath);
									BocoLog.info(this, "新建打包目录" + newFilePath
											+ "成功");
									newFileFullPath = uploadPath + newForder
											+ separator + sheetId + separator;

								}
								String fileFullPath = uploadPath
										+ tawCommonsAccessories
												.getAccessoriesName();
								newfileFullPathWithCNName = newFileFullPath
										+ tawCommonsAccessories
												.getAccessoriesCnName();
								try {
									BocoLog.info(this, "正在将" + fileFullPath);
									BocoLog.info(this, "拷贝到"
											+ newfileFullPathWithCNName);
									java.io.File file = new java.io.File(
											fileFullPath);
									if (file.exists()) {
										ifZipAndDownload = true;
										com.boco.eoms.common.util.StaticMethod
												.newFolder(newFileFullPath);
										BocoLog.info(this, "新建工单目录"
												+ newFileFullPath + "成功");
										com.boco.eoms.common.util.StaticMethod
												.copyFile(fileFullPath,
														newfileFullPathWithCNName);
										BocoLog
												.info(this, "已经将"
														+ fileFullPath);
										BocoLog.info(this, "拷贝到"
												+ newfileFullPathWithCNName);
									} else {
										if (errString.equals("")) {
											errString = "以下文件:\\n";
										} else {
											errString = errString + "\\n";
										}
										errString = errString
												+ tawCommonsAccessories
														.getAccessoriesCnName();
										BocoLog.info(this, "文件"
												+ newfileFullPathWithCNName
												+ "不存在");
									}
								} catch (Exception ex) {
									BocoLog
											.info(this, ex.getMessage() + "文件"
													+ newfileFullPathWithCNName
													+ "不存在");
									continue;
								}
							}
						}
					}
				}
				
			}
			if (ifZipAndDownload == true) {
				// 开始打包下载
				CompressBook book = new CompressBook();
				String zipNameWithPath = uploadPath + zipName + ".zip";
				zipName = zipName + ".zip";
				BocoLog.info(this, "---------文件打包路径:" + newFilePath);
				BocoLog.info(this, "需要打包的包名及路径为:" + zipNameWithPath);
				book.zip(zipNameWithPath, new java.io.File(newFilePath));

				BocoLog.info(this, "---------文件下载路径:" + zipNameWithPath);
				InputStream inStream = new FileInputStream(zipNameWithPath);// 文件的存放路径
				// 设置输出的格式
				response.reset();
				response
						.setContentType("application/x-msdownload;charset=GBK");
				response.setCharacterEncoding("UTF-8");

				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ new String(zipName.getBytes("UTF-8"),
										"GBK"));

				byte[] b = new byte[128];
				int len;
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();

				StringBuffer sb = new StringBuffer();
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<title>附件打包下载</title>");
				sb
						.append("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>");
				sb
						.append("	  <meta http-equiv=\"Pragma\" content=\"no-cache\"/>");

				sb.append("<meta http-equiv=\"Expires\" content=\"0\"/>");
				sb
						.append(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
				sb.append("</head>");
				sb.append("<body>");
				sb.append("附件下载成功");
				if (!errString.equals("")) {
					sb.append("但是:" + errString + "\\n在文件服务器中不存在，请联系管理员！");
				}
				sb.append("</body>");
				sb.append("</html>");
				//response.getOutputStream().write(sb.);
				//response.getWriter()write(sb.toString());

			} else {
				StringBuffer sb = new StringBuffer();

				response.setCharacterEncoding("UTF-8");
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<title>附件打包下载</title>");
				sb
						.append("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>");
				sb
						.append("	  <meta http-equiv=\"Pragma\" content=\"no-cache\"/>");

				sb.append("<meta http-equiv=\"Expires\" content=\"0\"/>");
				sb
						.append(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
				sb.append("</head>");

				sb.append("<script type=\"text/javascript\">");
				if (!errString.equals("")) {
					errString = errString + "\\n在文件服务器中不存在，请联系管理员！";
					sb.append("alert('" + errString + "');");
				} else {
					sb.append("alert('下载失败!\\n查询结果集中的所有工单都无附件！');");
				}
				sb.append("</script>");
				sb.append("</html>");
				response.getWriter().write(sb.toString());
			}
		}
		return null;
	}

	/**
	 * 显示所有待处理任务列表(portal)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getUndoAllListsForPortal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getUndoAllListsForPortal(mapping, form, request, response);
	}
	
	/**
	 * 显示所有待处理任务列表(portal)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUndoAllLists(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getUndoAllLists(mapping, form, request, response);
		String s = request.getContextPath();
		return mapping.findForward("undoAllList");
	}
	
	
	
	
	
	/**
	 * 
	 * 显示全流程图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shoWholeWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.shoWholeWorkFlow(mapping, form, request, response);
		return mapping.findForward("shoWholeWorkFlow");
	}
	
	/** 
	 * 
	 * 显示全流程图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAllWorkflowStepInfoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.getAllWorkflowStepInfoPage(mapping, form, request, response);
		return mapping.findForward("showSetpInfoPage");
	}
	
	/**
	 * 批量处理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showBatchDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.showBatchDealPage(mapping, form, request, response);
		return mapping.findForward("batchDealPage");
	}
	/**
	 * 延期申请列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward deferAppList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.deferAppList(mapping, form, request, response);
		return mapping.findForward("deferApp");
	}

	

	

}
