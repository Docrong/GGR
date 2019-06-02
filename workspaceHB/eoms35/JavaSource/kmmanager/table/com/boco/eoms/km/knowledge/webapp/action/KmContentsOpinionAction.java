package com.boco.eoms.km.knowledge.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
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
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsOpinionMgr;
import com.boco.eoms.km.knowledge.model.KmContentsOpinion;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.knowledge.webapp.form.KmContentsOpinionForm;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.util.KmOperateDefine;
import com.boco.eoms.km.log.util.KmOperateLogConstants;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 11:36:49 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmContentsOpinionAction extends BaseAction {
 
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
	 * 新增知识管理
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
	 * 修改知识管理
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
		KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmContentsOpinion kmContentsOpinion = kmContentsOpinionMgr.getKmContentsOpinion(id);
		KmContentsOpinionForm kmContentsOpinionForm = (KmContentsOpinionForm) convert(kmContentsOpinion);
		updateFormBean(mapping, request, kmContentsOpinionForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存知识管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
		KmContentsOpinionForm kmContentsOpinionForm = (KmContentsOpinionForm) form;
		boolean isNew = (null == kmContentsOpinionForm.getId() || "".equals(kmContentsOpinionForm.getId()));
		KmContentsOpinion kmContentsOpinion = (KmContentsOpinion) convert(kmContentsOpinionForm);
		if (isNew) {
			kmContentsOpinionMgr.saveKmContentsOpinion(kmContentsOpinion);
		} else {
			kmContentsOpinionMgr.saveKmContentsOpinion(kmContentsOpinion);
		}
		return search(mapping, kmContentsOpinionForm, request, response);
	}

	/**
	 * 评论知识内容
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
		// 读取：操作人信息
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String operateDeptId = sessionform.getDeptid();
		String operateUserId = sessionform.getUserid();
		
		KmContentsOpinionForm kmContentsOpinionForm = (KmContentsOpinionForm) form;
		KmContentsOpinion kmContentsOpinion = (KmContentsOpinion) convert(kmContentsOpinionForm);
		kmContentsOpinion.setCreateDept(operateDeptId);
		kmContentsOpinion.setCreateTime(new Date());
		kmContentsOpinion.setCreateUser(operateUserId);

		// 保存：评论信息
		KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
		kmContentsOpinionMgr.saveKmContentsOpinion(kmContentsOpinion);
		
		//更新知识对应的等级
		int grade = StaticMethod.null2int(kmContentsOpinion.getOpinionGrade());
		if(grade >0){
			KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
			kmContentsMgr.updateKmContentsGrade(kmContentsOpinion.getTableId(), kmContentsOpinion.getContentId(), grade);
		}

		// 记录:操作记录表-评论操作
		KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
		kmOperateLogMgr.saveKmOperateLog(kmContentsOpinion.getThemeId(), 
				kmContentsOpinion.getTableId(), 
				kmContentsOpinion.getContentId(),
				KmOperateDefine.KM_OPERATE_NAME_CONTENTS_OPINION,
				operateUserId, operateDeptId, operateUserId);
		// 记录：日操作记录表-评论操作
		KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
		kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
				KmOperateDefine.KM_OPERATE_NAME_CONTENTS_OPINION,
				operateUserId, operateDeptId);

		// 判断用户是否提出修改意见
		if(kmContentsOpinion.getIsEdit() != null && kmContentsOpinion.getIsEdit().equals("1")){
			// 记录:操作记录表-提出修改见议
			kmOperateLogMgr.saveKmOperateLog(kmContentsOpinion.getThemeId(), 
					kmContentsOpinion.getTableId(), 
					kmContentsOpinion.getContentId(),
					KmOperateDefine.KM_OPERATE_NAME_CONTENTS_ADVICE,
					operateUserId, operateDeptId, operateUserId);
			// 记录：日操作记录表-提出修改见议
			kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
					KmOperateDefine.KM_OPERATE_NAME_CONTENTS_ADVICE,
					operateUserId, operateDeptId);			
		}

		return mapping.findForward("success");
	}

	/**
	 * 删除知识管理
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
		KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmContentsOpinionMgr.removeKmContentsOpinion(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示知识管理列表
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
				KmContentsConstants.KMCONTENTSOPINION_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
		Map map = (Map) kmContentsOpinionMgr.getKmContentsOpinions(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmContentsConstants.KMCONTENTSOPINION_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示知识管理列表，支持Atom方式接入Portal
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
			KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
			Map map = (Map) kmContentsOpinionMgr.getKmContentsOpinions(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmContentsOpinion kmContentsOpinion = new KmContentsOpinion();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmContentsOpinion = (KmContentsOpinion) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmContentsOpinion/kmContentsOpinions.do?method=edit&id="
//						+ kmContentsOpinion.getId() + "' target='_blank'>"
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
}