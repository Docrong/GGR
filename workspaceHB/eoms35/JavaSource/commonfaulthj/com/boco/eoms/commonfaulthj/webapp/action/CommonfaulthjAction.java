package com.boco.eoms.commonfaulthj.webapp.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.boco.eoms.commonfaulthj.mgr.CommonfaulthjMgr;
import com.boco.eoms.commonfaulthj.model.Commonfaulthj;
import com.boco.eoms.commonfaulthj.util.CommonfaulthjConstants;
import com.boco.eoms.commonfaulthj.util.CommonfaulthjExcelImportUtil;
import com.boco.eoms.commonfaulthj.webapp.form.CommonfaulthjForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:commonfaulthj
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 * 
 * @moudle.getAuthor() zhoupan
 * @moudle.getVersion() 3.5
 * 
 */
public final class CommonfaulthjAction extends BaseAction {
 
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
	 * 新增commonfaulthj
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
	 * 修改commonfaulthj
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
		CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) getBean("commonfaulthjMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Commonfaulthj commonfaulthj = commonfaulthjMgr.getCommonfaulthj(id);
		CommonfaulthjForm commonfaulthjForm = (CommonfaulthjForm) convert(commonfaulthj);
		updateFormBean(mapping, request, commonfaulthjForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存commonfaulthj
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
		CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) getBean("commonfaulthjMgr");
		CommonfaulthjForm commonfaulthjForm = (CommonfaulthjForm) form;
		//判断当前操作是新增还是修改
		boolean isNew = (null == commonfaulthjForm.getId() || "".equals(commonfaulthjForm.getId()));
		Commonfaulthj commonfaulthj = (Commonfaulthj) convert(commonfaulthjForm);
		if (isNew) {
			commonfaulthj.setSavetime(new Date());
			commonfaulthjMgr.saveCommonfaulthj(commonfaulthj);
		} else {
			commonfaulthj.setUpdatetime(new Date());
			commonfaulthjMgr.saveCommonfaulthj(commonfaulthj);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除commonfaulthj
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
		CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) getBean("commonfaulthjMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		commonfaulthjMgr.removeCommonfaulthj(id);
		return mapping.findForward("success");
	}
	
	/**
	 * 分页显示commonfaulthj列表
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
				CommonfaulthjConstants.COMMONFAULTHJ_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) getBean("commonfaulthjMgr");
		Map map = (Map) commonfaulthjMgr.getCommonfaulthjs(pageIndex, pageSize, " order by savetime desc");
		List list = (List) map.get("result");
		request.setAttribute(CommonfaulthjConstants.COMMONFAULTHJ_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示commonfaulthj列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) getBean("commonfaulthjMgr");
			Map map = (Map) commonfaulthjMgr.getCommonfaulthjs(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			Commonfaulthj commonfaulthj = new Commonfaulthj();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				commonfaulthj = (Commonfaulthj) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/commonfaulthj/commonfaulthjs.do?method=edit&id="
						+ commonfaulthj.getId() + "' target='_blank'>"
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
	}*/
}