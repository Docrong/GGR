package com.boco.eoms.commonfault.importexcel.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commonfault.importexcel.mgr.CommonfaultImportExcelMgr;
import com.boco.eoms.commonfault.importexcel.model.CommonfaultImportExcel;
import com.boco.eoms.commonfault.importexcel.model.CommonfaultImportExcels;
import com.boco.eoms.commonfault.importexcel.util.CommonfaultImportExcelConstants;
import com.boco.eoms.commonfault.importexcel.webapp.form.CommonfaultImportExcelForm;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
//import com.boco.eoms.duty.workimport.mgr.TawRmDutyImportMgr;
//import com.boco.eoms.duty.workimport.model.TawRmDutyImport;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:使用表格导入撤销工单
 * </p>
 * <p>
 * Description:使用表格导入撤销工单
 * </p>
 * <p>
 * Tue Oct 26 10:55:09 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() liulei
 * @moudle.getVersion() 3.5
 * 
 */
public final class CommonfaultImportExcelAction extends BaseAction {
 
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
	 * 新增使用表格导入撤销工单
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
	 * 修改使用表格导入撤销工单
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
		CommonfaultImportExcelMgr commonfaultImportExcelMgr = (CommonfaultImportExcelMgr) getBean("commonfaultImportExcelMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		CommonfaultImportExcel commonfaultImportExcel = commonfaultImportExcelMgr.getCommonfaultImportExcel(id);
		CommonfaultImportExcelForm commonfaultImportExcelForm = (CommonfaultImportExcelForm) convert(commonfaultImportExcel);
		updateFormBean(mapping, request, commonfaultImportExcelForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存使用表格导入撤销工单
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
		CommonfaultImportExcelMgr commonfaultImportExcelMgr = (CommonfaultImportExcelMgr) getBean("commonfaultImportExcelMgr");
		CommonfaultImportExcelForm commonfaultImportExcelForm = (CommonfaultImportExcelForm) form;
		boolean isNew = (null == commonfaultImportExcelForm.getId() || "".equals(commonfaultImportExcelForm.getId()));
		CommonfaultImportExcel commonfaultImportExcel = (CommonfaultImportExcel) convert(commonfaultImportExcelForm);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		commonfaultImportExcel.setCreateTime(df.format(date));
		String userid = ((TawSystemSessionForm)request.getSession().getAttribute("sessionform")).getUserid();
		commonfaultImportExcel.setCreateUser(userid);
		String deptid = ((TawSystemSessionForm)request.getSession().getAttribute("sessionform")).getDeptid();
		commonfaultImportExcel.setCreateDept(deptid);
		if (isNew) {
			commonfaultImportExcelMgr.saveCommonfaultImportExcel(commonfaultImportExcel);
		} else {
			commonfaultImportExcelMgr.saveCommonfaultImportExcel(commonfaultImportExcel);
		}
		return search(mapping, commonfaultImportExcelForm, request, response);
	}
	
	/**
	 * 删除使用表格导入撤销工单
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
		CommonfaultImportExcelMgr commonfaultImportExcelMgr = (CommonfaultImportExcelMgr) getBean("commonfaultImportExcelMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		commonfaultImportExcelMgr.removeCommonfaultImportExcel(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示使用表格导入撤销工单列表
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
				CommonfaultImportExcelConstants.COMMONFAULTIMPORTEXCEL_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		CommonfaultImportExcelMgr commonfaultImportExcelMgr = (CommonfaultImportExcelMgr) getBean("commonfaultImportExcelMgr");
		Map map = (Map) commonfaultImportExcelMgr.getCommonfaultImportExcels(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(CommonfaultImportExcelConstants.COMMONFAULTIMPORTEXCEL_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 导入版本
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward importCommonfault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer sheet = new Integer(0);
		CommonfaultImportExcelMgr mgr = (CommonfaultImportExcelMgr) getBean("commonfaultImportExcelMgr");
		CommonfaultImportExcel commonfaultImportExcel = mgr.getCommonfaultImportExcel(request.getParameter("id"));

		String filePath = AccessoriesMgrLocator
				.getTawCommonsAccessoriesManagerCOS().getFilePath("commonfualtimport");
		// 符件保存格式为'1234535.xls'，去掉单引号
		String fileName = commonfaultImportExcel.getAccessoriesId().replace("'",
				"");
		Map map = mgr.mappingCommonfaultExcel(filePath + fileName);
		List list=(List)map.get(sheet);
		Iterator its = list.iterator();
		while(its.hasNext()){
			CommonfaultImportExcels commonfaultImportExcels = (CommonfaultImportExcels)its.next();
			System.out.println(commonfaultImportExcels.getSheetId());
			System.out.println(commonfaultImportExcels.getTitle());
		}
		if (map == null || !map.containsKey(sheet) || list==null || list.isEmpty()) {
			// 导入失败
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"Commonfault.import.error"));
			saveMessages(request, messages);
			return mapping.findForward("fail");
		}
		

		return mapping.findForward("success");
	}
	
	/**
	 * 分页显示使用表格导入撤销工单列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			CommonfaultImportExcelMgr commonfaultImportExcelMgr = (CommonfaultImportExcelMgr) getBean("commonfaultImportExcelMgr");
//			Map map = (Map) commonfaultImportExcelMgr.getCommonfaultImportExcels(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			CommonfaultImportExcel commonfaultImportExcel = new CommonfaultImportExcel();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				commonfaultImportExcel = (CommonfaultImportExcel) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/commonfaultImportExcel/commonfaultImportExcels.do?method=edit&id="
//						+ commonfaultImportExcel.getId() + "' target='_blank'>"
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
//			
//			// 每页显示条数
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}