package com.boco.eoms.commons.statistic.base.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.reference.BaseAction;
import com.boco.eoms.commons.statistic.base.util.StatUtil;


public class StatAction extends BaseAction {
	/**
	 * 显示kpi结果页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{		
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
//		String servletPath = getServlet().getServletContext().getRealPath("");//WebServer path		
		statMethod.performStatistic(mapping, form, request, response);
		
		String reportFromType = String.valueOf(request.getAttribute("reportFromType"));
		ActionForward actionForward = null;
		if("StatFrom".equalsIgnoreCase(reportFromType))
		{
			//StatFrom 统计报表
			actionForward = mapping.findForward("statisticresult");
		}
		else if("graphicsFrom".equalsIgnoreCase(reportFromType))
		{
			//graphicsFrom 图形报表
			actionForward = mapping.findForward("showGraphicsStatisticPage");
		}
		else if("StatFrom_graphicsFrom".equalsIgnoreCase(reportFromType))
		{
			//同时显示统计和图形报表
			actionForward = mapping.findForward("showStatAndGraphicsStatisticPage");
		}
        return actionForward;
		
	}
	
	/**
	 * 显示kpi查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String findForward = request.getParameter("findForward");
		String excelConfigURL =request.getParameter("excelConfigURL");
		String findListForward =request.getParameter("findListForward");
		StatUtil.validataParameter(findForward,excelConfigURL,findListForward);
		
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.showStatisticPage(mapping, form, request, response);
		
        return mapping.findForward(findForward);
		
	}
	
	/**
	 * kpi页面点击数字产生工单列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showStatisticSheetList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.showStatisticSheetList(mapping, form, request, response);
		
		String findListForward =request.getParameter("findListForward");
		
        return  mapping.findForward(findListForward);//mapping.findForward("statisticsheetlist");
	}
	
	/**
	 * 显示图形报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showGraphicsStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.showGraphicsStatisticPage(mapping, form, request, response);
		return mapping.findForward("showGraphicsStatisticPage");
	}
	
	/**
	 * 按照统计接入规范 接入门户系统
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward buildStatObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.buildStatObject(mapping, form, request, response);
		
		return null;
	}
	
	
	
//	//*************************定制统计*************************************
//	
//	/**
//	 * 定制统计选择页面
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward showCustomSatatistPage(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{
//		
//		String findForward = request.getParameter("findForward");
//		String excelConfigURL =request.getParameter("excelConfigURL");
//		String findListForward = request.getParameter("findListForward");
//		
//		StatUtil.validataParameter(findForward,excelConfigURL,findListForward);
//		
//		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
//		statMethod.showCustomSatatistPage(mapping, form, request, response);
//		
//		//customstatisticpage 显示定制统计页面
//        return mapping.findForward(findForward);
//	}
//	
//	/**
//	 * 
//	 * 保存订制信息
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward saveCustomSatatistInfo(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{		
//		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
//		statMethod.saveCustomStatisticInfo(mapping, form, request, response);
//		
//		//查询数据库，返回已经定制的所有信息 List，保存到 request中
//		
//		//保存定制信息后跳转已订制的页面
//		String url = "/stat.do?method=alreadyCustomSatatistInfo";
//        return new ActionForward(url);//mapping.findForward("alreadycustomstatisticpage");
//	}
//	
//	/**
//	 * 已经订制的统计
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward alreadyCustomSatatistInfo(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{
//		
//		String findForward = request.getParameter("findForward");
//		String excelConfigURL =request.getParameter("excelConfigURL");
//		String findListForward =request.getParameter("findListForward");
//		StatUtil.validataParameter(findForward,excelConfigURL,findListForward);
//
//		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
//		statMethod.alreadyCustomSatatistInfo(mapping, form, request, response);
//		
//		return mapping.findForward("alreadycustomstatisticpage");
//	}
//	
//	//*******************************执行订制统计******************************************
//	
//	/**
//	 * 已经统计出来文件的信息
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	public ActionForward statistFileInfo(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{
//		return null;
//	}
//	
//	
//	/**
//	 * 查看统计出来所有文件的信息(报表)
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	public ActionForward showSatatistFileResult(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{
//		return null;
//	}
//	
//	/**
//	 * 查看统计出来的文件信息
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward showAlreadySatatistFileInfo(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{
//		return null;
//	}
//	
//	
//	/**
//	 * 按照已经定制的统计进行统计
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	public ActionForward runCustomStatistic(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		return null;
//	}
}
