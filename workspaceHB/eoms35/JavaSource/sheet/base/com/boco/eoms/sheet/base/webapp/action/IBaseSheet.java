/*
 * Created on 2007-8-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IHumanTaskService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface IBaseSheet extends NewIBaseSheet {

    

    public abstract IHumanTaskService getHumanTaskService();

    public abstract void setHumanTaskService(IHumanTaskService humanTaskService);

    /**
     * 设置任务列表每页显示的长度
     * 
     * @param pageLenth
     */
    public abstract void setPageLength(int pageLenth);

    /**
     * 获取任务列表每页显示的长度
     * 
     * @return
     */
    public abstract int getPageLength();

    /**
     * 获取工单任务对象
     * 
     * @return
     */
    public abstract ITask getSheetTask();

    /**
     * 设置工单任务对象
     * 
     * @param sheetTask
     */
    public abstract void setSheetTask(ITask sheetTask);

    /**
     * 设置流程引擎所要使用的map
     * 
     * @param flowEngineMap
     */
    public abstract void setFlowEngineMap(HashMap flowEngineMap);

    /**
     * 获取流程引擎所要使用的map
     * 
     * @return
     */
    public abstract HashMap getFlowEngineMap();

    /**
     * 显示工单新增页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract String showNewSheetPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * 显示工单新增子页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showInputNewSheetPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * 工单main新增提交方法
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performAdd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    
   
    /**
     * 显示工单详细界面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 更新main对象的提交
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performUpdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示撤销界面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showCancelPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示删除界面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showDeletePage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示归档界面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showHoldPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示查询界面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showQueryPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    
    /**
     * 执行查询方法
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performQuery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    
    
    /**
     * 执行列表查询方法
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performListQuery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;


    /**
     * 工单处理提交
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performDeal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示待处理工单列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showListUndo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示已处理列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showListsenddone(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * 显示草稿列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showDraftList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 显示已归档工单列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showHoldedList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    /**
     * 显示已撤销工单列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showCancelList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;


    /**
     * 获取任务对象结构的map
     * 
     * @return
     */
    public abstract HashMap getTaskBOMap();

    /**
     * 呈现工单草稿
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showDraftPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 呈现工单处理界面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showDealPage(ActionMapping mapping, ActionForm form,
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
    public abstract void showInputDealPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * 工单详细处理记录列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showSheetDealList(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * 呈现工单驳回界面
     * 
     * @param form
     * @param request
     * @param mapping
     * @param response
     * 
     * @return
     * @throws Exception
     */
    public void showRejectPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 呈现工单驳回子界面
     * 
     * @param form
     * @param request
     * @param mapping
     * @param response
     * 
     * @return
     * @throws Exception
     */
    public void showInputRejectPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

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
    public void showOwnStarterList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    
    
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
    public void showMainDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception ;
    /**
     * 撤消的框架页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void showCancelInputPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception ;
 
    /**
     * main工单的撤销
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public void performCancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception ;
    

	

	public Map getUndoList(HttpServletRequest request) throws Exception;

	
	/**
     * 工单提交预处理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public void performPreCommit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception ;

    /**
     * 解析流程配置文件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public HashMap analyseFlowDefine(HttpServletRequest request,String sheetPageName)
            throws Exception;
	

	/**
	 * 申明一个任务
	 * @param request
	 * @throws Exception
	 */
	public void performClaim(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 创建一个子任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void performCreateSubTask(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 执行流程的事件页面
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showEventPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	/**
	 * 执行流程的事件输入页面
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	public void showInputEventPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	/**
	 * 执行流程的事件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void performProcessEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;

    /**
     * 工单移交 
     * @param form
     * @param request
     * @param mapping
     * @param response
     * @return
     * @throws Exception
     */
    public void performTransferWorkItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 工单移交输入页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void showTransferWorkItemPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
	/**
	 * 工单处理信息的保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void performSaveDealInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	
	
	/**
	 * 根据用户查找所有的模板(main表)
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getTemplatesByUserId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 根据用户查找所有的模板(link表)
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getDealTemplatesByUserId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	
    /**
     * 显示模板页面(main表)
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void showInputTemplateSheetPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
	
    /**
     * 显示模板页面(link表)
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void showDealInputTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 保存模板(main表)
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void saveTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
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
    public void saveDealTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 引用模板
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void referenceTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 查看main模板
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 查看link模板
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getDealTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 删除main模板
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void removeTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 删除link模板
     * @author wangjianhua
	 * @date 2008-07-22
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void removeDealTemplate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    
    /**
     * 
     * @author wangjianhua
	 * @date 2008-08-02
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void showSheetAccessoriesList(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    
    /**
     * @author 张影
	 * 处理工单强制归档、作废
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void performFroceHold(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	/**
	 * @author 张影
	 * 显示工单强制归档、作废页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showForceHoldPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	
    
	
    /**
     * 显示未归档工单列表
     * 
     *  @author yangliangliang
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showListForAdmin(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    
	public void getAllDoRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	public void getNowDoRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	public void getPreRoleNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception; 
	
	/**
	 * 获取流程定义文件的路径 格式为classpath:config/business-config.xml
	 * @return roleConfigPath
	 */
	public abstract String getRoleConfigPath();
	
	
	/**
	 * 设置流程定义文件的路径 格式为classpath:config/business-config.xml
	 * @return roleConfigPath
	 */
	public abstract void setRoleConfigPath(String roleConfigPath);

	/**
	 * 呈现任务分派页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showInputSplitPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	  
	public abstract void showRemarkPage(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception;
	
	
	public abstract void showPhaseBackToUpPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
	
	public abstract void showPhaseAdvicePage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;

	/**
	 * 当驳回的时候查询上一条任务的执行者对象
	 *   从request中通过request.getAttribute(fOperateroleid/ftaskOwner/fOperateroleidType)
	 *   取得上一条任务的Operateroleid taskOwner OperateroleidType
	 * @param request
	 * @throws Exception
	 */
	public void setParentTaskOperateWhenRejct( HttpServletRequest request)
	throws Exception;
	
	

	
	/**
	 * 取流程对象的属性
	 * @return
	 */
	public Map getProcessOjbectAttribute();
	
	/**
	 * 取得保存附件字段属性
	 * @return
	 */
	public Map getAttachmentAttributeOfOjbect();
	
	/**
	 * 呈现处理回复通过界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDealReplyAcceptPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/**
	 * 呈现处理回复不通过界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showDealReplyRejectPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	
	/**
	 * 执行处理回复通过
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performDealReplyAccept(ActionMapping mapping,
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
	public void performDealReplyReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	
	 /**
     * 显示流程图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void showWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;

	 /**
     * 显示流程实例图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb  
	 * @since 2008-09-09 
	 */ 
	public void showWorkFlowInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 呈现接口回调回复页面(接口互调)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showInvokeReplyPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
	
	/**
	 * 执行接口回调动作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performInvokeReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception ;
	
	/**
	 * 显示流程实例图
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 * @since 2008-09-09
	 */
	public void getLinkOperatePage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/**
     * 是否调用了其他工单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract boolean performIsInvokeProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
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
	public void newPerformAdd(ActionMapping mapping, ActionForm form,
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
	public void newPerformDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	/**
	 * 整合用   非流程动作的处理：目前包括抄送、阶段回复、阶段通知
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newPerformNonFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	/**
	 * 整合用   非流程动作：目前包括抄送、阶段回复、阶段通知中进行link和task的保存
	 * @param taskId 任务id
	 * @param dataMap 输入参数为Map类型，其中包括：Map类型的main、Map类型的link、Map类型的operate
	 * @return
	 * @throws Exception
	 */
	public void newSaveNonFlowData(String taskId,Map dataMap) throws Exception;
	/**
	 * 提交批量回复处理
	 * @param taskId
	 * @param dataMap
	 * @throws Exception
	 */
	public void performBatchDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception;

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
	public void showListUndoByRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
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
	public void showListsenddoneByRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
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
	public void showQueryHidePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
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
	public void performQueryHide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	/**
	 * 工单隐藏
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void performHide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	
    /**
     * @author yyk
     * @see 根据历史工单生成新的工单新增页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showNewSendPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;	
	
    /**
     * 显示待处理工单列表(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void getAtomLists(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    /**
     * 显示详细信息页面(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showAtomDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;   
    /**
     * 申请任务(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performClaimTaskAtom(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;      
    
    /**
     * 任务的处理(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performDealAtom(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;      
    
    /**
     * 移交(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performTransferAtom(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;    
    /**
     * 阶段回复(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void showPhaseReplyPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;    
    /**
     * 阶段回复(Atom系统)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void performNonFlowAtom(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;   
    /**
     * 显示待处理工单列表(portal)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void getUndoListsForPortal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;
    /**
     * 显示已处理工单列表(portal)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public abstract void getDoneListsForPortal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception;  
    /**
	 * 同组未处理列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void showUndoListForSameTeam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
    /**
	 * 获取待归档工单任务列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void showUnHoldList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

    /**
	 * 获取待处理工单任务列表（不含过滤步骤的任务）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void showUndoListByFilter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;	
	
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
	public void getUndoAllListsForPortal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	
	
	/**
	 * 显示所有待处理任务列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getUndoAllLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	

	
	 /**
    * 显示全流程图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response 
	 * @throws Exception
	 */
	public void shoWholeWorkFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 全流程步骤的详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAllWorkflowStepInfoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	
	/**
	 * 延期申请列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deferAppList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * 批理处理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
   public abstract void showBatchDealPage(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws Exception; 
   
   /**
	 * 同组处理模式已处理列表（本角色其他人员已经处理完成工单）
	 * add by 秦敏 20090907
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void showDoneListForSameTeam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
   /**
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @throws Exception
    */
   public abstract void showHoldedListForUser(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws Exception;   
}
