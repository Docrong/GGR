package com.boco.eoms.km.statics.webapp.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Feed;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.knowledge.webapp.action.KmContentsAction;
import com.boco.eoms.km.statics.mgr.GeneralStatisticMgr;
import com.boco.eoms.km.statics.mgr.KnowledgeChangesStatisticMgr;
import com.boco.eoms.km.statics.model.KnowledgeChangesStatistic;
import com.boco.eoms.km.statics.util.KnowledgeChangesStatisticConstants;
import com.boco.eoms.km.statics.util.PersonalStatisticConstants;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识库按知识库统计
 * </p>
 * <p>
 * Description:知识库按知识库统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() daizhigang
 * @moudle.getVersion() 0.1
 * 
 */
public final class GeneralStatisticAction  extends BaseAction {


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
	 * 知识库按知识库统计列表
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
		String next = "list";;
		String pageIndexName = new org.displaytag.util.ParamEncoder("generalStatisticList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String startDate = StaticMethod.null2String(request.getParameter("startDate"));
		String endDate = StaticMethod.null2String(request.getParameter("endDate"));		
		//默认统计一周的指标
		if("".equals(endDate)){
			Date endDateTime = new Date(System.currentTimeMillis());			
			java.util.Date startDateTime = StatisticMethod.countDate(endDateTime, -6);

			endDate = StatisticMethod.dateToString(endDateTime, "yyyy-MM-dd");
			startDate = StatisticMethod.dateToString(startDateTime, "yyyy-MM-dd");
		}
		
		GeneralStatisticMgr generalStatisticMgr = (GeneralStatisticMgr) getBean("generalStatisticMgr");
		Map map = (Map) generalStatisticMgr.getGeneralStatistics(startDate, endDate);
		List list = (List) map.get("result");
		request.setAttribute("startDate",startDate);
		request.setAttribute("endDate",endDate);
		request.setAttribute("resultSize",Integer.valueOf(list.size()));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("generalStatisticList", list);
		//导出excel
		String flagexcel = StaticMethod.nullObject2String(request.getParameter("flagexcel"));
		String url = request.getContextPath()+"/kmmanager/generalStatistics.do?method=search&startDate="+startDate+"&endDate="+endDate+"&flagexcel=true";
		request.setAttribute("excelUrl", url);
	    if (flagexcel.equals("true")) {
	    	String dd=  KmContentsAction.class.getResource("/").toString();
			List contentList = (List)map.get("result");
			generalExcelFile(request, "generalStatisticList",
					contentList);
			next = "excelJsp";
		}
		return mapping.findForward(next);
	}
	
	/**
	 * 知识库按知识库统计表列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			KnowledgeChangesStatisticMgr knowledgeChangesStatisticMgr = (KnowledgeChangesStatisticMgr) getBean("knowledgeChangesStatisticMgr");
			Map map = (Map) knowledgeChangesStatisticMgr.getKnowledgeChangesStatistics(pageIndex, pageSize);
			List list = (List) map.get("result");
			KnowledgeChangesStatistic knowledgeChangesStatistic = new KnowledgeChangesStatistic();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
/*			for (int i = 0; i < list.size(); i++) {
				knowledgeChangesStatistic = (KnowledgeChangesStatistic) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/knowledgeChangesStatistic/knowledgeChangesStatistics.do?method=edit&id="
						+ knowledgeChangesStatistic.getId() + "' target='_blank'>"
						+ display name for list + "</a>");
				entry.setSummary(summary);
				entry.setContent(content);
				entry.setLanguage(language);
				entry.setText(text);
				entry.setRights(tights);
				
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
				entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
				Person person = entry.addAuthor(userId);
				person.setName(userName);
			}*/
			
			// 每页显示条数
			feed.setText(map.get("total").toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 实现excel导出
	 * 
	 * @param request
	 * @param excelname
	 * @param list
	 * @return
	 */
	  public void generalExcelFile(HttpServletRequest request, String excelname,
			List list) {
		String configPath = "";
		try{
		configPath =  KmContentsAction.class.getResource("/").toString();
		configPath = configPath.substring(5)+"com/boco/eoms/km/config/";
		}catch(Exception e){
			e.printStackTrace();
		}
		com.boco.eoms.km.excelmanage.PoiExcel poiExcel = new com.boco.eoms.km.excelmanage.PoiExcel(configPath);
		String path = poiExcel.getPoiExcel(excelname, list);
		request.setAttribute("excelfile", path);
		request.setAttribute("excelfilename", path.substring(path
				.lastIndexOf(File.separator) + 1, path.length()));
	}
}
