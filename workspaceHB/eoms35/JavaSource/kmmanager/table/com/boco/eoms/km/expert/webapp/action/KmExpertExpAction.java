package com.boco.eoms.km.expert.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.expert.mgr.KmExpertExpMgr;
import com.boco.eoms.km.expert.model.KmExpertExp;
import com.boco.eoms.km.expert.util.KmExpertExpConstants;
import com.boco.eoms.km.expert.webapp.form.KmExpertExpForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:工作经验
 * </p>
 * <p>
 * Description:工作经验
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmExpertExpAction extends BaseAction {
 
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
	 * 新增工作经验
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
	 * 修改工作经验
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
		KmExpertExpMgr kmExpertExpMgr = (KmExpertExpMgr) getBean("kmExpertExpMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		if(!id.equals("")){
			KmExpertExp kmExpertExp = kmExpertExpMgr.getKmExpertExp(id);
			KmExpertExpForm kmExpertExpForm = (KmExpertExpForm) convert(kmExpertExp);
			updateFormBean(mapping, request, kmExpertExpForm);
		}
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存工作经验
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
		KmExpertExpMgr kmExpertExpMgr = (KmExpertExpMgr) getBean("kmExpertExpMgr");
		KmExpertExpForm kmExpertExpForm = (KmExpertExpForm) form;
		boolean isNew = (null == kmExpertExpForm.getId() || "".equals(kmExpertExpForm.getId()));
		KmExpertExp kmExpertExp = (KmExpertExp) convert(kmExpertExpForm);
		if (isNew) {
			kmExpertExpMgr.saveKmExpertExp(kmExpertExp);
		} else {
			kmExpertExpMgr.saveKmExpertExp(kmExpertExp);
		}		
		
		request.setAttribute("operType", "save");
		
		return mapping.findForward("edit");
	}
	
	/**
	 * 删除工作经验
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
		KmExpertExpMgr kmExpertExpMgr = (KmExpertExpMgr) getBean("kmExpertExpMgr");
		String[] ids = request.getParameterValues("ids");
//		for(int i =0;i<ids.length;i++){
//			kmExpertExpMgr.removeKmExpertExp(ids[i]);
//		}
		kmExpertExpMgr.removeKmExpertExps(ids);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示工作经验列表
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
		String userId = StaticMethod.null2String(request.getParameter("userId"));
		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExpertExpConstants.KMEXPERTEXP_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		KmExpertExpMgr kmExpertExpMgr = (KmExpertExpMgr) getBean("kmExpertExpMgr");
		Map map = (Map) kmExpertExpMgr.getKmExpertExpsByUserId(pageIndex, pageSize, userId);
		List list = (List) map.get("result");
		
		request.setAttribute(KmExpertExpConstants.KMEXPERTEXP_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("userId", userId);
		
		return mapping.findForward("list");
	}

	public ActionForward listDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		search(mapping, form, request, response);
		return mapping.findForward("listDetail");
	}

	/**
	 * 分页显示工作经验列表，支持Atom方式接入Portal
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
//			KmExpertExpMgr kmExpertExpMgr = (KmExpertExpMgr) getBean("kmExpertExpMgr");
//			Map map = (Map) kmExpertExpMgr.getKmExpertExps(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmExpertExp kmExpertExp = new KmExpertExp();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmExpertExp = (KmExpertExp) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmExpertExp/kmExpertExps.do?method=edit&id="
//						+ kmExpertExp.getId() + "' target='_blank'>"
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