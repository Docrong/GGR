package com.boco.eoms.km.statics.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.statics.mgr.DeptContributeStatisticMgr;
import com.boco.eoms.km.statics.model.DeptContributeStatistic;
import com.boco.eoms.km.statics.util.DeptContributeStatisticConstants;
import com.boco.eoms.km.statics.util.StatisticMethod;
import com.boco.eoms.km.statics.webapp.form.DeptContributeStatisticForm;

/**
 * <p>
 * Title:部门知识贡献情况
 * </p>
 * <p>
 * Description:部门知识贡献情况
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public final class DeptContributeStatisticAction extends BaseAction {
 
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
	 * 分页显示部门知识贡献情况列表
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
				DeptContributeStatisticConstants.DEPTCONTRIBUTESTATISTIC_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		String startDate = StaticMethod.null2String(request.getParameter("startDate"));
		String endDate = StaticMethod.null2String(request.getParameter("endDate"));		
		
		java.util.Date startDateTime = null;
		java.util.Date endDateTime = null;

		//默认统计一周的指标
		if("".equals(endDate)){
			endDate = StaticMethod.getCurrentDateTime("yyyy-MM-dd");
			endDateTime = StatisticMethod.stringToDate(endDate + " 23:59:59" , "yyyy-MM-dd HH:mm:ss");

			java.util.Date newDate = StatisticMethod.countDate(endDateTime, -6);
			startDate = StatisticMethod.dateToString(newDate, "yyyy-MM-dd");
			startDateTime = StatisticMethod.stringToDate(startDate, "yyyy-MM-dd");
		}
		else {
			endDateTime = StatisticMethod.stringToDate(endDate + " 23:59:59" , "yyyy-MM-dd HH:mm:ss");
			startDateTime = StatisticMethod.stringToDate(startDate, "yyyy-MM-dd");
		}
		
		DeptContributeStatisticMgr deptContributeStatisticMgr = (DeptContributeStatisticMgr) getBean("deptContributeStatisticMgr");
		Map map = (Map) deptContributeStatisticMgr.getDeptContributeStatistics(pageIndex, pageSize, startDateTime, endDateTime);
		List list = (List) map.get("result");
		request.setAttribute(DeptContributeStatisticConstants.DEPTCONTRIBUTESTATISTIC_LIST, list);
		
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示部门知识贡献情况列表，支持Atom方式接入Portal
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
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			DeptContributeStatisticMgr deptContributeStatisticMgr = (DeptContributeStatisticMgr) getBean("deptContributeStatisticMgr");
//			Map map = (Map) deptContributeStatisticMgr.getDeptContributeStatistics(pageIndex, pageSize);
//			List list = (List) map.get("result");
//			DeptContributeStatistic deptContributeStatistic = new DeptContributeStatistic();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
/*			for (int i = 0; i < list.size(); i++) {
				deptContributeStatistic = (DeptContributeStatistic) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/deptContributeStatistic/deptContributeStatistics.do?method=edit&id="
						+ deptContributeStatistic.getId() + "' target='_blank'>"
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
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}