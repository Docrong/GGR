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
import com.boco.eoms.km.expert.mgr.KmExpertBasicMgr;
import com.boco.eoms.km.expert.model.KmExpertBasic;
import com.boco.eoms.km.expert.util.KmExpertBasicConstants;
import com.boco.eoms.km.expert.webapp.form.KmExpertBasicForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmExpertBasicAction extends BaseAction {
 
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
		return edit(mapping, form, request, response);
	}

 	/**
	 * 新增运维专家
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward newExpert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("newExpert");
	}
    
    public ActionForward detailExpert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String userId = StaticMethod.null2String(request.getParameter("userId"));
		if(!"".equals(userId)){
			KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
			KmExpertBasic kmExpertBasic = kmExpertBasicMgr.getKmExpertBasicByUserId(userId);
			request.setAttribute("createUser", kmExpertBasic.getCreateUser());
		}
		return mapping.findForward("detailExpert");
	}

 	/**
	 * 新增基本信息
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
	 * 修改基本信息
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
		String userId = StaticMethod.null2String(request.getParameter("userId"));
		if(!"".equals(userId)){
			KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
			KmExpertBasic kmExpertBasic = kmExpertBasicMgr.getKmExpertBasicByUserId(userId);

			KmExpertBasicForm kmExpertBasicForm = (KmExpertBasicForm) convert(kmExpertBasic);
			updateFormBean(mapping, request, kmExpertBasicForm);
		}
		return mapping.findForward("edit");
	}

    public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	edit(mapping, form, request, response);
		return mapping.findForward("detail");
	}

	/**
	 * 保存基本信息
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
		KmExpertBasicForm kmExpertBasicForm = (KmExpertBasicForm) form;		
		KmExpertBasic kmExpertBasic = (KmExpertBasic) convert(kmExpertBasicForm);
		kmExpertBasic.setCreateTime(new java.util.Date()); //创建时间
		kmExpertBasic.setCreateUser(this.getUserId(request)); //创建人
		
		boolean isNew = (null == kmExpertBasicForm.getId() || "".equals(kmExpertBasicForm.getId()));
		KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
		if (isNew) {
			ITawSystemUserManager iTawSystemUserManager = (ITawSystemUserManager) getBean("itawSystemUserManager");
			TawSystemUser tawSystemUser = iTawSystemUserManager.getUserByuserid(kmExpertBasic.getUserId());
			kmExpertBasic.setDeptId(tawSystemUser.getDeptid());
			kmExpertBasicMgr.saveKmExpertBasic(kmExpertBasic);
		} else {
			kmExpertBasicMgr.saveKmExpertBasic(kmExpertBasic);
		}
		
		request.setAttribute("operType", "save");
		return edit(mapping, kmExpertBasicForm, request, response);
	}
	
	/**
	 * 删除基本信息
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
		String id = StaticMethod.null2String(request.getParameter("id"));
		String userId = StaticMethod.null2String(request.getParameter("userId"));
		
		KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
		kmExpertBasicMgr.removeKmExpertBasic(id, userId);
		
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示基本信息列表
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
				KmExpertBasicConstants.KMEXPERTBASIC_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		KmExpertBasicForm kmExpertBasicForm = (KmExpertBasicForm) form;
		String deptId = StaticMethod.null2String(kmExpertBasicForm.getDeptId()); //部门
		String specialty = StaticMethod.null2String(kmExpertBasicForm.getSpecialty()); //专业
		
		boolean first = true;
		StringBuffer whereStr = new StringBuffer();
		
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
		String fieldName = StaticMethod.null2String(request.getParameter("fieldName"));
		if(!fieldName.equals("")){
			whereStr.append(" where kmExpertBasic.");
			whereStr.append(fieldName);
			whereStr.append(" like '");
			whereStr.append(nodeId);
			whereStr.append("%' ");
		}
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("fieldName", fieldName);
		
		if(deptId.length() >0){
			whereStr.append(" and kmExpertBasic.deptId=" + deptId);
			first = false;
		}

		if(!fieldName.equals("specialty") && specialty.length() >0){
			if(first){
				whereStr.append(" and kmExpertBasic.specialty like '" + specialty + "%' ");
			}else{
			    whereStr.append(" and kmExpertBasic.specialty like '" + specialty + "%' ");
			}
		}
		
		KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
		Map map = (Map) kmExpertBasicMgr.getKmExpertBasics(pageIndex, pageSize, whereStr.toString());
		List list = (List) map.get("result");
		
		request.setAttribute(KmExpertBasicConstants.KMEXPERTBASIC_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExpertBasicConstants.KMEXPERTBASIC_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		String fieldName = StaticMethod.null2String(request.getParameter("fieldName"));
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		//传递nodeId的值 以便页面查询用 
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("fieldName", fieldName);
		
		StringBuffer whereStr = new StringBuffer();
		if(!fieldName.equals("")){
			whereStr.append(" where kmExpertBasic.");
			whereStr.append(fieldName);
			whereStr.append(" like '");
			whereStr.append(nodeId);
			whereStr.append("%' ");
		}
		
		KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
		Map map = (Map) kmExpertBasicMgr.getKmExpertBasics(pageIndex, pageSize, whereStr.toString());
		List list = (List) map.get("result");
		
		request.setAttribute(KmExpertBasicConstants.KMEXPERTBASIC_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	/**
	 * 分页显示基本信息列表，支持Atom方式接入Portal
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
//			KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
//			Map map = (Map) kmExpertBasicMgr.getKmExpertBasics(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmExpertBasic kmExpertBasic = new KmExpertBasic();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmExpertBasic = (KmExpertBasic) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmExpertBasic/kmExpertBasics.do?method=edit&id="
//						+ kmExpertBasic.getId() + "' target='_blank'>"
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