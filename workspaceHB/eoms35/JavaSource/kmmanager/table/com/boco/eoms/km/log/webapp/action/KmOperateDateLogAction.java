package com.boco.eoms.km.log.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
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
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.model.KmOperateDateLog;
import com.boco.eoms.km.log.util.KmOperateDateLogConstants;
import com.boco.eoms.km.log.webapp.form.KmOperateDateLogForm;

/**
 * <p>
 * Title:知识操作日统计表
 * </p>
 * <p>
 * Description:知识操作日统计表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public final class KmOperateDateLogAction extends BaseAction {
 
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
	 * 新增知识操作日统计表
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
	 * 修改知识操作日统计表
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
		KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmOperateDateLog kmOperateDateLog = kmOperateDateLogMgr.getKmOperateDateLog(id);
		KmOperateDateLogForm kmOperateDateLogForm = (KmOperateDateLogForm) convert(kmOperateDateLog);
		updateFormBean(mapping, request, kmOperateDateLogForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存知识操作日统计表
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
		KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
		KmOperateDateLogForm kmOperateDateLogForm = (KmOperateDateLogForm) form;
		boolean isNew = (null == kmOperateDateLogForm.getId() || "".equals(kmOperateDateLogForm.getId()));
		KmOperateDateLog kmOperateDateLog = (KmOperateDateLog) convert(kmOperateDateLogForm);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		kmOperateDateLog.setOperateDate(dateFormat.parse(dateFormat.format(new Date())));
		if (isNew) {
			kmOperateDateLogMgr.saveKmOperateDateLog(kmOperateDateLog);
		} else {
			kmOperateDateLogMgr.saveKmOperateDateLog(kmOperateDateLog);
		}
		return search(mapping, kmOperateDateLogForm, request, response);
	}
	
	/**
	 * 删除知识操作日统计表
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
		KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmOperateDateLogMgr.removeKmOperateDateLog(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示知识操作日统计表列表
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
				KmOperateDateLogConstants.KMOPERATEDATELOG_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
		Map map = (Map) kmOperateDateLogMgr.getKmOperateDateLogs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmOperateDateLogConstants.KMOPERATEDATELOG_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示知识操作日统计表列表，支持Atom方式接入Portal
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
			KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
			Map map = (Map) kmOperateDateLogMgr.getKmOperateDateLogs(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmOperateDateLog kmOperateDateLog = new KmOperateDateLog();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmOperateDateLog = (KmOperateDateLog) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmOperateDateLog/kmOperateDateLogs.do?method=edit&id="
//						+ kmOperateDateLog.getId() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}
			
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
	
	public static void main(String[]args)throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(dateFormat.parse(dateFormat.format(new Date())));
		
	}
}