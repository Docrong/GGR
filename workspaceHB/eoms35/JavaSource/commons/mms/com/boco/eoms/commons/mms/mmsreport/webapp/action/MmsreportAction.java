package com.boco.eoms.commons.mms.mmsreport.webapp.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.boco.eoms.commons.mms.base.config.Reports;
import com.boco.eoms.commons.mms.base.config.Sheet;
import com.boco.eoms.commons.mms.base.util.MMSConstants;
import com.boco.eoms.commons.mms.base.util.MMSUtil;
import com.boco.eoms.commons.mms.mmsreport.mgr.MmsreportMgr;
import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;
import com.boco.eoms.commons.mms.mmsreport.util.MmsreportConstants;
import com.boco.eoms.commons.mms.mmsreport.webapp.form.MmsreportForm;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.cmcc.mm7.vasp.common.MMConstants;
import com.cmcc.mm7.vasp.common.MMContent;
import com.cmcc.mm7.vasp.conf.MM7Config;
import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitReq;
import com.cmcc.mm7.vasp.service.MM7Sender;

/**
 * <p>
 * Title:彩信报实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 18:16:20 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李振友
 * @moudle.getVersion() 3.5
 * 
 */
public final class MmsreportAction extends BaseAction {
 
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
	 * 新增彩信报实例
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
	 * 修改彩信报实例
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
		MmsreportMgr mmsreportMgr = (MmsreportMgr) getBean("mmsreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Mmsreport mmsreport = mmsreportMgr.getMmsreport(id);
		MmsreportForm mmsreportForm = (MmsreportForm) convert(mmsreport);
		updateFormBean(mapping, request, mmsreportForm);
		return mapping.findForward("edit");
	}
    /**
     * 根据id列出这个彩信报的所有的信息
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MmsreportMgr mmsreportMgr = (MmsreportMgr) getBean("mmsreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Mmsreport mmsreport = mmsreportMgr.getMmsreport(id);
		MmsreportForm mmsreportForm = (MmsreportForm) convert(mmsreport);
		
		//设置彩信报类型
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(mmsreportForm.getMmsReportCreateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		String displayStr = MMSUtil.getDateString(calendar,mmsreportForm.getMmsreportType());
		mmsreportForm.setMmsreportType(displayStr);
		
		//设置彩信报包含所有报表的名称
		String[] statid = mmsreportForm.getStatreport_id().split(",");
		String statString = "";
		Reports reports = (Reports) ParseXmlService.create().xml2object(
				Reports.class, StaticMethod.getFilePathForUrl(MMSConstants.REPORT_CONFIG));
		for(int i=0;i<statid.length;i++)
		{
			Sheet sheet = reports.getSheetById(statid[i]);
			if(sheet != null)
			{
				if(!"".equals(statString))
				{
					statString += ",";
				}
				statString += sheet.getName();
			}
		}
		mmsreportForm.setStatreport_id(statString);
		
		updateFormBean(mapping, request, mmsreportForm);
		return mapping.findForward("detail");
	}
	
	/**
	 * 保存彩信报实例
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
		MmsreportMgr mmsreportMgr = (MmsreportMgr) getBean("mmsreportMgr");
		MmsreportForm mmsreportForm = (MmsreportForm) form;
		boolean isNew = (null == mmsreportForm.getId() || "".equals(mmsreportForm.getId()));
		Mmsreport mmsreport = (Mmsreport) convert(mmsreportForm);
		if (isNew) {
			mmsreportMgr.saveMmsreport(mmsreport);
		} else {
			mmsreportMgr.saveMmsreport(mmsreport);
		}
		return search(mapping, mmsreportForm, request, response);
	}
	
	/**
	 * 删除彩信报实例
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
		MmsreportMgr mmsreportMgr = (MmsreportMgr) getBean("mmsreportMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		mmsreportMgr.removeMmsreport(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 彩信报查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
		request.setAttribute("userid", userid);
		return mapping.findForward("showPage");
	}
	
	/**
	 * 分页显示彩信报实例列表
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
				MmsreportConstants.MMSREPORT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		/**
		 * 查询条件
		 *
		 */
		String mmsReportName = request.getParameter("mmsReportName");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String seachReportType = request.getParameter("seachReportType");
		String seachuserid = request.getParameter("userid");

//		查询条件
		String where = "where 1=1 ";
		if(!"".equalsIgnoreCase(seachReportType) && seachReportType != null)
		{
			where += " and mmsreport.mmsreportType='" + seachReportType + "'";
		}
		if((!"".equalsIgnoreCase(beginTime) && beginTime != null)
				&&(!"".equalsIgnoreCase(endTime) && endTime != null))
		{
			where += " and mmsreport.mmsreportcreatedate>='" + beginTime + "' and mmsreport.mmsreportcreatedate<='" + endTime + "'";
		}
		if(!"".equalsIgnoreCase(mmsReportName) && mmsReportName != null)
		{
			where += " and mmsreport.mmsReportName='" + mmsReportName + "'";
		}
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
		if(!"admin".equalsIgnoreCase(userid))
		{
			where += " and mmsreport.userid='" + userid + "'";
		}
		else
		{
			if(!"".equalsIgnoreCase(seachuserid) && seachuserid != null)
			{
				where += " and mmsreport.userid='" + seachuserid + "'";
			}
		}
		
		MmsreportMgr mmsreportMgr = (MmsreportMgr) getBean("mmsreportMgr");
		Map map = (Map) mmsreportMgr.getMmsreports(pageIndex, pageSize, where);
		List list = (List) map.get("result");
		request.setAttribute(MmsreportConstants.MMSREPORT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 测试彩信发送程序
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("测试连接彩信报服务器");
		String mm7c = StaticMethod.getFilePathForUrl("classpath:config/mm7Config.xml");
		String connc = StaticMethod.getFilePathForUrl("classpath:config/ConnConfig.xml");
		System.out.println("mm7Config.xml path: " + mm7c);
		System.out.println("ConnConfig.xml path: " + connc);
		
		MM7Config mm7Config = new MM7Config(mm7c);
		mm7Config. setConnConfigName(connc);
		MM7Sender mm7Sender = null;
		try {
			mm7Sender = new MM7Sender(mm7Config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MM7SubmitReq submit = new MM7SubmitReq();
		submit.setTransactionID("1000000");//11111111
		
	    submit.addTo("15829550058");//13915002000
	    //submit.addTo("15011321994");
	    
	    submit.setVASID("10658218");
	    submit.setVASPID("826211");
	    submit.setSenderAddress("10658218000");
	    submit.setChargedPartyID("1");
	    submit.setChargedParty((byte) 1);
		submit.setDeliveryReport(true);
	    submit.setServiceCode("000");
	    submit.setSubject("测试");
		MMContent content = new MMContent();
	    content.setContentType(MMConstants.ContentType. MULTIPART_MIXED);
	    String picpath = StaticMethod.getFilePathForUrl("classpath:config/aoyunbiaoti.gif");
		MMContent sub1 = MMContent.createFromFile(picpath);
	    sub1.setContentID("1.gif");
	    sub1.setContentType(MMConstants.ContentType.GIF);
	    content.addSubContent(sub1);
		MMContent sub2 = MMContent.createFromString("This is a Test2!");
		sub2.setContentID("2.txt");
		sub2.setContentType(MMConstants.ContentType. TEXT);
		content.addSubContent(sub2);
		submit.setContent(content);
		MM7RSRes res = mm7Sender.send(submit);
		System.out.println("res.statuscode=" + res.getStatusCode() +
	                           ";res.statusText=" + res.getStatusText());
		
		System.out.println("彩信发送成功 哇哈哈!!");
		
		return mapping.findForward("success");
	}
}