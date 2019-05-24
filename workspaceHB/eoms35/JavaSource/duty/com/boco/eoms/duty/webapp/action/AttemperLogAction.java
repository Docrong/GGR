package com.boco.eoms.duty.webapp.action;

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

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.mgr.AttemperLogMgr;
import com.boco.eoms.duty.model.AttemperLog;
import com.boco.eoms.duty.util.AttemperLogConstants;
import com.boco.eoms.duty.webapp.form.AttemperLogForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:网调日志记录
 * </p>
 * <p>
 * Description:网调日志记录
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class AttemperLogAction extends BaseAction {
 
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
	 * 新增网调日志记录
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
	 * 修改网调日志记录
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
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		AttemperLog attemperLog = attemperLogMgr.getAttemperLog(id);
		AttemperLogForm attemperLogForm = (AttemperLogForm) convert(attemperLog);
		updateFormBean(mapping, request, attemperLogForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存网调日志记录
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
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		AttemperLogForm attemperLogForm = (AttemperLogForm) form;
		boolean isNew = (null == attemperLogForm.getId() || "".equals(attemperLogForm.getId()));
		AttemperLog attemperLog = (AttemperLog) convert(attemperLogForm);
		if (isNew) {
			attemperLogMgr.saveAttemperLog(attemperLog);
		} else {
			attemperLogMgr.saveAttemperLog(attemperLog);
		}
		return search(mapping, attemperLogForm, request, response);
	}
	
	/**
	 * 删除网调日志记录
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
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		attemperLogMgr.removeAttemperLog(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示网调日志记录列表
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
				AttemperLogConstants.ATTEMPERLOG_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
		Map map = (Map) attemperLogMgr.getAttemperLogs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(AttemperLogConstants.ATTEMPERLOG_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示网调日志记录列表，支持Atom方式接入Portal
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
			AttemperLogMgr attemperLogMgr = (AttemperLogMgr) getBean("attemperLogMgr");
			Map map = (Map) attemperLogMgr.getAttemperLogs(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			AttemperLog attemperLog = new AttemperLog();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				attemperLog = (AttemperLog) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				/*---- 云南 先注释------------------------------------------
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/attemperLog/attemperLogs.do?method=edit&id="
						+ attemperLog.getId() + "' target='_blank'>"
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
				----------------------------------------------*/
			}
			
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
}