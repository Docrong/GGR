package com.boco.eoms.commons.mms.statreport.webapp.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.mms.statreport.mgr.StatreportMgr;
import com.boco.eoms.commons.mms.statreport.model.Statreport;
import com.boco.eoms.commons.mms.statreport.util.StatreportConstants;
import com.boco.eoms.commons.mms.statreport.webapp.form.StatreportForm;
import com.boco.eoms.commons.statistic.base.util.FileUtil;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:报表实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 11:35:28 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李振友
 * @moudle.getVersion() 3.5
 * 
 */
public final class StatreportAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
 	
 	/**
	 * 新增报表实例
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改报表实例
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StatreportMgr statreportMgr = (StatreportMgr) getBean("statreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Statreport statreport = statreportMgr.getStatreport(id);
		StatreportForm statreportForm = (StatreportForm) convert(statreport);
		updateFormBean(mapping, request, statreportForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存报表实例
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StatreportMgr statreportMgr = (StatreportMgr) getBean("statreportMgr");
		StatreportForm statreportForm = (StatreportForm) form;
		boolean isNew = (null == statreportForm.getId() || "".equals(statreportForm.getId()));
		Statreport statreport = (Statreport) convert(statreportForm);
		if (isNew) {
			statreportMgr.saveStatreport(statreport);
		} else {
			statreportMgr.saveStatreport(statreport);
		}
		return search(mapping, statreportForm, request, response);
	}
    /**
     * 根据id列出这个彩信报的所有的信息
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StatreportMgr statreportMgr = (StatreportMgr) getBean("statreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Statreport statreport = statreportMgr.getStatreport(id);
		StatreportForm statreportForm = (StatreportForm) convert(statreport);
		updateFormBean(mapping, request, statreportForm);
		return mapping.findForward("detail");
	}
	/**
	 * 删除报表实例
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StatreportMgr statreportMgr = (StatreportMgr) getBean("statreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		statreportMgr.removeStatreport(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 显示查询报表条件页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
		request.setAttribute("userid", userid);
		return mapping.findForward("showPage");
	}
	
	/**
	 * 分页显示报表实例列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				StatreportConstants.STATREPORT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		//查询条件
		String seachReportType = request.getParameter("seachReportType");
		String statName = request.getParameter("statName");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String seachuserid = request.getParameter("userid");
		
		String where = " where 1=1 ";
		if(!"".equalsIgnoreCase(seachReportType) && seachReportType != null)
		{
			where += " and statreport.reportType='" + seachReportType + "'";
		}
		if((!"".equalsIgnoreCase(beginTime) && beginTime != null)
				&&(!"".equalsIgnoreCase(endTime) && endTime != null))
		{
			where += " and statreport.createTime>='" + beginTime + "' and statreport.createTime<='" + endTime + "'";
		}
		if(!"".equalsIgnoreCase(statName) && statName != null)
		{
			where += " and statreport.statName='" + statName.trim() + "'";
		}
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
		if(!"admin".equalsIgnoreCase(userid))
		{
			where += " and statreport.userid='" + userid + "'";
		}
		else
		{
			if(!"".equalsIgnoreCase(seachuserid) && seachuserid != null)
			{
				where += " and statreport.userid='" + seachuserid + "'";
			}
		}
		StatreportMgr statreportMgr = (StatreportMgr) getBean("statreportMgr");
		Map map = (Map) statreportMgr.getStatreports(pageIndex, pageSize, where);
		List list = (List) map.get("result");
		request.setAttribute(StatreportConstants.STATREPORT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("上传文件");
		
		StatreportMgr statreportMgr = (StatreportMgr) getBean("statreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Statreport statreport = statreportMgr.getStatreport(id);
		
		InputStream fileSource= request.getInputStream();
		String excelUrl = statreport.getExcelID();
		String picUrl = statreport.getPicID();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte b[]=new byte[1000];
	    int n;
	    while((n=fileSource.read(b))!=-1)
	    	baos.write(b,0,n);
	    baos.close();
	    baos.close();

		FileUtil.writeFile(baos, excelUrl);
		StatreportForm statreportForm = (StatreportForm) convert(statreport);
		updateFormBean(mapping, request, statreportForm);
		return mapping.findForward("detail");
	}
	
}