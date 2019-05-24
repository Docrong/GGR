package com.boco.eoms.km.knowledge.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.knowledge.mgr.KmContentsCollectMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.knowledge.model.KmContentsCollect;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;

public class KmContentsCollectAction extends BaseAction {
	
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
	 * 根据用户名字查询 该用户所收藏的知识列表
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
		//获得当前登录用户信息（收藏人）
		String collectUser = this.getUser(request).getUserid();
		//根据收藏人分页查询收藏信息
		KmContentsCollectMgr kmContentsCollectMgr =(KmContentsCollectMgr)getBean("kmContentsCollectMgr");
		KmContentsMgr kmContentsMgr = (KmContentsMgr)getBean("kmContentsMgr");
		List list = kmContentsCollectMgr.getKmContentsCollectList(collectUser);
		List kmContentsList = new ArrayList();
		for(int i=0;i<list.size();i++){
			KmContentsCollect kmContentsCollect = (KmContentsCollect)list.get(i);
			KmContents kmContents = kmContentsMgr.getKmContentsByContentId(kmContentsCollect.getContentId());
			if(kmContents!=null && kmContents.getId()!=null && !kmContents.getId().equals("")){
				kmContentsList.add(kmContents);
			}			
		}
		request.setAttribute(KmContentsConstants.KMCONTENTSCOLLECT_LIST, kmContentsList);
		request.setAttribute("pageSize", pageSize);
    	
		return mapping.findForward("listCollect");
	}
	
	/**
	 * 根据订阅信息的id删除订阅信息
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
		String id = request.getParameter("ID");
		KmContentsCollectMgr kmContentsCollectMgr = (KmContentsCollectMgr)getBean("kmContentsCollectMgr");
		kmContentsCollectMgr.removeKmContentsCollect(id);
		return mapping.findForward("success");
	}
	
	/**
	 * 用户根据知识id收藏知识
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward collect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmContentsCollectMgr kmContentsCollectMgr =(KmContentsCollectMgr)getBean("kmContentsCollectMgr");
		//知识ID
		String ID = StaticMethod.null2String(request.getParameter("ID"));
		//知识所属模型ID
		String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
		//知识模型所属分类ID
		String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));
		String collectUser = this.getUser(request).getUserid();
		//收藏前先判断该知识是不是已经被收藏了
		//根据知识知识id查询 该知识是否已经被收藏了
		List list = kmContentsCollectMgr.getKmContentsCollectList(collectUser);
		for(int i=0;i<list.size();i++){
			KmContentsCollect kmContentsCollect = (KmContentsCollect)list.get(i);
			String contentId = kmContentsCollect.getContentId();
			if(ID.equals(contentId)){
				System.out.println("123----------------------------");
				return mapping.findForward("collectFail");
			};
		}
		
		KmContentsCollect kmContentsCollect = new KmContentsCollect();
		kmContentsCollect.setCollectUser(collectUser);
		kmContentsCollect.setCollectTime(new Date());
		kmContentsCollect.setTableId(TABLE_ID);
		kmContentsCollect.setThemeId(THEME_ID);
		kmContentsCollect.setContentId(ID);
		kmContentsCollectMgr.savaKmContentsCollect(kmContentsCollect);
		
		return mapping.findForward("success");
	}
}
