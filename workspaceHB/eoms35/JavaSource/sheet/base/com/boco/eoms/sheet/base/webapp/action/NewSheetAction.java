/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;

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
 * @author wangjianhua
 * @version 1.0
 */
public class NewSheetAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 需要的参数${module}
        String path = mapping.getPath();
        path = path.substring(1);
        request.setAttribute("module", path);
        return super.execute(mapping, form, request, response);
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
    public ActionForward newShowNewInputSheetPage(ActionMapping mapping,
                                                  ActionForm form, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        String processname = StaticMethod.nullObject2String(request.getParameter("processname"));
        String taskname = StaticMethod.nullObject2String(request.getParameter("taskname"));

        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowInputNewSheetPage(mapping, form, request, response);

        ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder.getInstance().getBean("ItawSheetAccessManager");
        TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processname, taskname);
        request.setAttribute("tawSheetAccess", access);

        return mapping.findForward("newInputNew");
    }

    /**
     * 保存工单主表main
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newSaveMainSheet(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newSaveMainSheet(mapping, form, request, response);
        return mapping.findForward("success");
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
    public ActionForward newShowDetailPage(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowDetailPage(mapping, form, request, response);
        String taskName = request.getParameter("taskName");
        String mainId = request.getParameter("sheetKey");
//		
//		//如果是驳回状态就返回草稿页面在进行修改提交
//		if (taskName.equals(Constants.TASK_NAME_REJECT)) {
//			String module =  mapping.getPath().substring(1);
//			String url = "./" + module + ".do?method=newShowNewInputSheetPage&taskName=" + taskName + "&rejectId=" + mainId ;
//			response.sendRedirect(url);
//			return  mapping.findForward(null);
//		} else {
        return mapping.findForward("newDetail");
//		}

    }


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
    public ActionForward newSaveDraft(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newSaveDraft(mapping, form, request, response);
        return mapping.findForward("success");
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
    public ActionForward newShowDraftList(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowDraftList(mapping, form, request, response);
        return mapping.findForward("newDraftList");
    }

    /**
     * 处理环节中页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowInputDealPage(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowInputDealPage(mapping, form, request, response);
        return mapping.findForward("newShowInputDealPage");
    }

    /**
     * 处理环节中页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowInputConfirmAndCCPage(ActionMapping mapping,
                                                      ActionForm form, HttpServletRequest request,
                                                      HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowInputDealPage(mapping, form, request, response);
        return mapping.findForward("newShowInputConfirmAndCCPage");
    }

    /**
     * 保存模板(main表)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 20008-7-22
     */
    public ActionForward newSaveTemplate(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newSaveTemplate(mapping, form, request, response);
        return mapping.findForward("successMessage");
    }

    /**
     * 根据用户查找所有的模板（工单的main表）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 20008-7-22
     */
    public ActionForward newGetTemplatesByUserId(ActionMapping mapping,
                                                 ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        baseSheet.newGetTemplatesByUserId(mapping, form, request, response);
        return mapping.findForward("newTemplates");
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
    public ActionForward newPerformClaimTask(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        baseSheet.newPerformClaim(mapping, form, request, response);
        String forwardAction = StaticMethod.nullObject2String(request.getAttribute("findForwardAction"));
        response.sendRedirect(forwardAction);
        return mapping.findForward(null);
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
     * 整合中  工单的初始化页面 本流程新增调用 王建华修改 用于新版流程
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showCCPageNew(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowInputDealPage(mapping, form, request, response);
        return mapping.findForward("newCC");
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
    public ActionForward newEditTemplateInfo(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.getTemplate(mapping, form, request, response);
        return mapping.findForward("newInputNew");
    }

    /**
     * 引用模板(main表)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 20008-7-22
     */
    public ActionForward newReferenceTemplate(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        baseSheet.newReferenceTemplate(mapping, form, request, response);
        return mapping.findForward("newInputNew");

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
    public ActionForward newPerformTransferWorkItem(ActionMapping mapping,
                                                    ActionForm form, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        // 转移工单
        baseSheet.performTransferWorkItem(mapping, form, request, response);
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
    public ActionForward newShowTransferWorkItemPage(ActionMapping mapping,
                                                     ActionForm form, HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        boolean invokeFlag = (boolean) baseSheet.performIsInvokeProcess(mapping, form, request, response);
        // 转移工单
        if (invokeFlag == true) {
            return mapping.findForward("invokepage");
        } else {
            baseSheet.showTransferWorkItemPage(mapping, form, request, response);
            return mapping.findForward("newTransfer");
        }

    }

    /**
     * 阶段回复
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowPhaseBackToUpPage(ActionMapping mapping,
                                                  ActionForm form, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        boolean invokeFlag = (boolean) baseSheet.performIsInvokeProcess(mapping, form, request, response);
        if (invokeFlag == true) {
            return mapping.findForward("invokepage");
        } else {
            baseSheet.showPhaseBackToUpPage(mapping, form, request, response);
            return mapping.findForward("newPhaseBackToUp");
        }

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
    public ActionForward newShowInputSplit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        boolean invokeFlag = (boolean) baseSheet.performIsInvokeProcess(mapping, form, request, response);
        if (invokeFlag == true) {
            return mapping.findForward("invokepage");
        } else {
            baseSheet.showInputSplitPage(mapping, form, request, response);
            return mapping.findForward("newSplit");
        }

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
    public ActionForward newShowListsendundoByRole(ActionMapping mapping,
                                                   ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showListUndoByRole(mapping, form, request, response);
        return mapping.findForward("newList");
    }

    /**
     * 根据用户查找所有的模板（工单的link表）
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 20008-7-22
     */
    public ActionForward newGetDealTemplatesByUserId(ActionMapping mapping,
                                                     ActionForm form, HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        baseSheet.getDealTemplatesByUserId(mapping, form, request, response);
        return mapping.findForward("newDealTemplates");

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
    public ActionForward newOpenDealTemplateInfo(ActionMapping mapping,
                                                 ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newGetDealTemplate(mapping, form, request, response);
        return mapping.findForward("newOpenDealTemplate");
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
    public ActionForward newPerformSaveInfo(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newPerformSaveDealInfo(mapping, form, request, response);
        return mapping.findForward("successMessage");
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
    public ActionForward newPerformSaveInfoAndExit(ActionMapping mapping,
                                                   ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newPerformSaveDealInfo(mapping, form, request, response);
        String forwardAction = mapping.getPath().substring(1) + ".do?method=newShowListsendundo";
        response.sendRedirect(forwardAction);
        return mapping.findForward(null);
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
    public ActionForward newShowListsendundo(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showListUndo(mapping, form, request, response);
        return mapping.findForward("newList");
    }

    /**
     * 保存模板(link表)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 20008-7-22
     */
    public ActionForward newSaveDealTemplate(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newSaveDealTemplate(mapping, form, request, response);
        return mapping.findForward("successMessage");
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
    public ActionForward newPerformListQuery(ActionMapping mapping, ActionForm form,
                                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performListQuery(mapping, form, request, response);
        String findForward = StaticMethod.nullObject2String(request.getParameter("findForward"));
        return mapping.findForward(findForward);
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
    public ActionForward newShowSheetDealList(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showSheetDealList(mapping, form, request, response);
        return mapping.findForward("newHistory");
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
    public ActionForward newShowHoldedList(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showHoldedList(mapping, form, request, response);
        return mapping.findForward("newHoldlist");
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
    public ActionForward newShowListsenddoneByRole(ActionMapping mapping,
                                                   ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showListsenddoneByRole(mapping, form, request, response);
        return mapping.findForward("newMainlist");
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
    public ActionForward newShowListsenddone(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showListsenddone(mapping, form, request, response);
        return mapping.findForward("newMainlist");
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
    public ActionForward newShowQueryPage(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showQueryPage(mapping, form, request, response);
        return mapping.findForward("newQuery");
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
    public ActionForward newPerformQuery(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performQuery(mapping, form, request, response);
        return mapping.findForward("newQuerylist");
    }

    /**
     * 显示附件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public ActionForward newShowSheetAccessoriesList(ActionMapping mapping,
                                                     ActionForm form, HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showSheetAccessoriesList(mapping, form, request, response);
        return mapping.findForward("newSheetAccessories");
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
    public ActionForward newShowForceHoldPage(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {

        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        // 转移工单
        baseSheet.showForceHoldPage(mapping, form, request, response);
        return mapping.findForward("newForceHold");
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
    public ActionForward newPerformFroceHold(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        // 转移工单
        baseSheet.performFroceHold(mapping, form, request, response);
        return mapping.findForward("success");
    }


    /**
     * 管理者列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author yangliangliang
     */
    public ActionForward newShowListForAdmin(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showListForAdmin(mapping, form, request, response);
        return mapping.findForward("newAdminlist");
    }

    /**
     * 阶段通知
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowPhaseAdvicePage(ActionMapping mapping,
                                                ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showPhaseAdvicePage(mapping, form, request, response);
        return mapping.findForward("newPhaseAdvice");
    }

    /**
     * 显示流程图
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowWorkFlow(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newShowWorkFlow(mapping, form, request, response);
        return mapping.findForward("newShowWorkFlow");
    }

    /**
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
    public ActionForward newShowWorkFlowInstance(ActionMapping mapping,
                                                 ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showWorkFlowInstance(mapping, form, request, response);
        return mapping.findForward("newShowWorkFlowInstance");
    }

    /**
     * 处理回复通过页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowDealReplyAcceptPage(ActionMapping mapping,
                                                    ActionForm form, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showDealReplyAcceptPage(mapping, form, request, response);
        return mapping.findForward("newDealReplyAccept");
    }

    /**
     * 提交处理回复通过
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newPerformDealReplyAccept(ActionMapping mapping,
                                                   ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newPerformDealReplyAccept(mapping, form, request, response);
        return mapping.findForward("success");
    }

    /**
     * 处理回复不通过页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowDealReplyRejectPage(ActionMapping mapping,
                                                    ActionForm form, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showDealReplyRejectPage(mapping, form, request, response);
        return mapping.findForward("newDealReplyReject");
    }

    /**
     * 提交处理回复不通过
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newPerformDealReplyReject(ActionMapping mapping,
                                                   ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.newPerformDealReplyReject(mapping, form, request, response);
        return mapping.findForward("success");
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
    public ActionForward newShowOwnStarterList(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showOwnStarterList(mapping, form, request, response);
        return mapping.findForward("newStartlist");
    }

    /**
     * 显示作废工单列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowCancelList(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showCancelList(mapping, form, request, response);
        return mapping.findForward("newCancellist");
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
    public ActionForward newShowMainDetailPage(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        baseSheet.showMainDetailPage(mapping, form, request, response);
        return mapping.findForward("newMaindetail");

    }

    /**
     * 阶段通知后的已阅/回复界面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newShowRemarkPage(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IBaseSheet baseSheet = (IBaseSheet) getBean(mapping.getAttribute());
        baseSheet.showRemarkPage(mapping, form, request, response);
        return mapping.findForward("newRemark");

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
    public ActionForward newShowPortal(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("newPortal");
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
    public ActionForward newShowDrawing(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("newDraw");
    }

    /**
     * 查看实例图的详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newGetLinkOperatePage(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.getLinkOperatePage(mapping, form, request, response);
        return mapping.findForward("newGetLinkOperatePage");
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
    public ActionForward performAddNew(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performAddNew(mapping, form, request, response);
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
    public ActionForward performDealNew(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.performDealNew(mapping, form, request, response);
        return mapping.findForward("success");
    }

}
