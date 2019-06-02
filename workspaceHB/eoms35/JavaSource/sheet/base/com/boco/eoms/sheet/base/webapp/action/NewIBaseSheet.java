package com.boco.eoms.sheet.base.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;

/**
 * @author wangjianhua
 * 
 * 
 */
public interface NewIBaseSheet {
	public abstract ITaskService getTaskService();

	/**
	 * @param taskService
	 *            The taskService to set.
	 */
	public abstract void setTaskService(ITaskService taskService);
	/**
     * 获取link的service
     * 
     * @return
     */
    public abstract ILinkService getLinkService();

    /**
     * 设置link的service
     * 
     * @param linkService
     */
    public abstract void setLinkService(ILinkService linkService);

    /**
     * 获取main的service
     * 
     * @return
     */
    public abstract IMainService getMainService();

    /**
     * 设置main的service、
     * 
     * @param mainService
     */
    public abstract void setMainService(IMainService mainService);

    /**
     * 获取业务流程service
     * 
     * @return
     */
    public abstract IBusinessFlowService getBusinessFlowService();

    /**
     * 设置业务流程service
     * 
     * @param businessFlowService
     */
    public abstract void setBusinessFlowService(
            IBusinessFlowService businessFlowService);
    
    /**
     * 获取工单对象的operate对象的字段名称和类型，比如dealPerformer@java.lang.String
     * 
     * @return String
     */
    public abstract String getPageColumnName();

    /**
     * 获取任务列表所要呈现的流程模板的名称
     * 
     * @return
     */
    public abstract String getFlowTemplateName();

    /**
     * 设置任务列表所要呈现的流程模板的名称
     * 
     * @param flowTemplateName
     */
    public abstract void setFlowTemplateName(String flowTemplateName);

	/**
	 * 新建工单页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newShowInputNewSheetPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 保存草稿
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveDraft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 草稿列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newShowDraftList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 保存main表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveMainSheet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 工单的详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public void newShowDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 新保存模板(main表)
	 * 
	 * @author wangjianhua
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newSaveTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 根据用户查找所有的模板(main表)
	 * 
	 * @author wangjianhua
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newGetTemplatesByUserId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 呈现工单处理子界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newShowInputDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 申明任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newPerformClaim(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * 获取流程中所定义的接口对象的类型和名称的map map.put(name,type);
	 * 基类和子类（BaseSheet）都要有getInterfaceObjMap所以把此方法提到基类的接口里
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract HashMap getInterfaceObjMap(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 对流程引擎所使用的map进行处理 基类和子类（BaseSheet）都要有dealFlowEngineMap所以把此方法提到基类的接口里
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public abstract void dealFlowEngineMap(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
    /**
     * 引用模板
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void newReferenceTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
	/**
	 * 根据用户查找所有的模板(link表)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newGetDealTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 工单处理信息的保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newPerformSaveDealInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
    /**
     * 保存模板(link表)
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void newSaveDealTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;
    
	/**
	 * 取main表所要保存派发对象的域名
	 * @return
	 */
	public Map getMainSendObject();
	
	/**
	 * 取link表所要保存派发对象的域名
	 * @return
	 */
	public Map getLinkSendObject();
	
	 /**
     * 显示流程图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void newShowWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 执行处理回复通过
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newPerformDealReplyAccept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	/**
	 * 执行处理回复不通过
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newPerformDealReplyReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	
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
	public void performAddNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
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
	public void performDealNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	/**
	 * 非流程动作：目前包括抄送、阶段回复、阶段通知中进行link和task的保存
	 * @param taskId 任务id
	 * @param dataMap 输入参数为Map类型，其中包括：Map类型的main、Map类型的link、Map类型的operate
	 * @return
	 * @throws Exception
	 */
	public void newSaveNonFlowData(String taskId,Map dataMap) throws Exception;
	/**
	 * 非流程动作：阶段通知中进行link和task的保存
	 * @param taskId 任务id
	 * @param dataMap 输入参数为Map类型，其中包括：Map类型的main、Map类型的link、Map类型的operate
	 * @return
	 * @throws Exception
	 */
	public void newSaveNonFlowData(Map dataMap) throws Exception;

	/**
	 * 派发
	 * @param interfaceMap 接口参数
	 * @param attach 附件信息
	 * @return
	 */
	public String newWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 重派
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String renewWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 阶段通知
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String suggestWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 退回
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String untreadWorkSheet(HashMap interfaceMap,List attach) throws Exception;
	/**
	 * 归档
	 * @param interfaceMap
	 * @param attach
	 * @return
	 */
	public String checkinWorkSheet(HashMap interfaceMap,List attach) throws Exception;

	public String cancelWorkSheet(HashMap interfaceMap) throws Exception;
	public String getSheetAttachCode();
	public void createNewSheetInstance(HashMap interfaceMap,List attach) throws Exception;
}
