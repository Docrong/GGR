package com.boco.eoms.commons.statistic.base.anychart.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.dom4j.Document;

import com.boco.eoms.commons.statistic.base.anychart.bean.ReportBean;
import com.boco.eoms.commons.statistic.base.anychart.bo.XmlSet;
import com.boco.eoms.commons.statistic.base.anychart.util.ReportTool;
import com.boco.eoms.commons.statistic.base.anychart.util.TimeTool;

public class ReportAction extends DispatchAction{

	/**
	 * 报表
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward Report(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//读取页面参数
		String requestPath = request.getContextPath();//URL		
		String contextPath = getServlet().getServletContext().getRealPath("");//WebServer path		
		String reportid = request.getParameter("reportid");
		
		//read xmlconifg-create sqls
		ReportBean report = new ReportBean(reportid,contextPath,request);
		
		//read xmltemplate
		String templatePath = contextPath + report.getPath();
		String swfPath = requestPath + report.getSwfpath();
		Document doc = ReportTool.readDocument(templatePath);
		
		TimeTool tt = new TimeTool();
		String[] times = tt.getC_time().split(":");
		//标识更改为完整的时间戳
		String time = tt.getC_date("_") + times[0]+times[1]+times[2];
		
		//输出路径
		String outpath = contextPath + report.getOutpath() + reportid + time + "_data.xml";//xml输出页面		
		String pagepath = requestPath + report.getOutpath() + reportid + time + "_data.xml";//JSP 参数页面
		
		//css set with module of xml
		
		//getResult - 逐一解析BLOCKS
		XmlSet.CreateData(doc, report,request);

		//生成XML
		ReportTool.write(doc,outpath);
			
		//设置页面必要参数
		request.setAttribute("title", report.getName());//报表名称
		request.setAttribute("swf", swfPath);
		request.setAttribute("xmlpath", pagepath);
		
		//非自动图例
		if(!report.isAuto()){
			List lineSample = report.getLinesample();
			List columnSample = report.getColumnsample();
			request.setAttribute("linesample", lineSample);
			request.setAttribute("columnsample", columnSample);
		}
		
		return mapping.findForward("reportpage");
	}
	
}
