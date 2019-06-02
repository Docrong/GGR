package com.boco.eoms.commons.statistic.customstat.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.webapp.action.StatAction;

public class CustomStatAction extends StatAction {
	//*******************************执行订制统计******************************************
	
	/**
	 * 已经统计出来文件的信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward statistFileInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.statistFileInfo(mapping, form, request, response);
		
		return mapping.findForward("statisticfilepage");
	}
	
	
	/**
	 * 查看统计出来报表文件的信息(报表)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward showSatatistFileResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.showSatatistFileResult(mapping, form, request, response);
		
		//结果页面
		return mapping.findForward("statisticresult");
	}
	
	/**
	 * 删除统计文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSatatistFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.deleteSatatistFile(mapping, form, request, response);
		
		//结果页面
		statMethod.showAlreadySatatistFileInfo(mapping, form, request, response);
		return mapping.findForward("statisticfilepage");//mapping.findForward("success");
	}
	
	/**
	 * 查看统计出来的文件信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateSatatistFileInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.updateSatatistFileInfo(mapping, form, request, response);
		
		//结果页面
		statMethod.showAlreadySatatistFileInfo(mapping, form, request, response);
		return mapping.findForward("statisticfilepage");//mapping.findForward("success");
	}
	
	/**
	 * 更新统计出来的报表（修改是否经过审批）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAlreadySatatistFileInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.showAlreadySatatistFileInfo(mapping, form, request, response);
		
		//结果页面
		return mapping.findForward("statisticfilepage");
	}
	
	public ActionForward seachSatatistFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.seachSatatistFile(mapping, form, request, response);
		
		//结果页面
		return mapping.findForward("seachstatisticfilepage");
	}
	
	
	/**
	 * 按照已经定制的统计进行统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward runCustomStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.runCustomStatistic(mapping, form, request, response);
        return null;
	}
	
	/**
	 * 刷新内存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward reLoadCustomConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ICustomStatMethod statMethod = (ICustomStatMethod) getBean(mapping.getAttribute());
		statMethod.reLoadCustomConfig(mapping, form, request, response);
		//成功页面
        return mapping.findForward("success");
	}
}
