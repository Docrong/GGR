
package com.boco.eoms.duty.webapp.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.service.ITawRmArticleManager;
import com.boco.eoms.duty.webapp.form.TawRmArticleForm;


public final class TawRmArticleAction extends BaseAction {
//	 定义页数长度
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
    	TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
	    ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		
		TawRmArticle tawRmArticle = (TawRmArticle) convert(tawRmArticleForm);
		tawRmArticle.setOnlineNum(tawRmArticle.getAllNum());
		tawRmArticle.setLoanNum(0);
		String articleName = tawRmArticle.getArticleName();
		String articletype = tawRmArticle.getArticleType();
		StringBuffer condition =new StringBuffer();
		if(!"".equals(articleName)&&articleName!=null){
		condition.append(" and articleName= '"+articleName+"'");
		}
		if(!"".equals(articletype)&&articletype!=null){
			condition.append(" and articleType= '"+articletype+"'");
		}
		List old = mgr.getTawRmArticleByCondition(condition.toString());
		if(old.isEmpty()){
		mgr.saveTawRmArticle(tawRmArticle);
		}else{
			TawRmArticle preArticle = (TawRmArticle)old.get(0);
			int preOn = preArticle.getOnlineNum();
			int preAll = preArticle.getAllNum();
			preOn+=tawRmArticle.getAllNum();
			preAll+=tawRmArticle.getAllNum();
			preArticle.setOnlineNum(preOn);
			preArticle.setAllNum(preAll);
			mgr.saveTawRmArticle(preArticle);
		}
		request.setAttribute("congratuation","");
		return mapping.findForward("succ");
	}
	
	public ActionForward toform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		request.setAttribute("tawRmArticleForm", tawRmArticleForm);
		return mapping.findForward("edit");
	}
	
	public ActionForward toquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		request.setAttribute("tawRmArticleForm", tawRmArticleForm);
		return mapping.findForward("query");
	}
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		
		String articleName = tawRmArticleForm.getArticleName();
		String articleType = tawRmArticleForm.getArticleType();
		StringBuffer condition =new StringBuffer();
		if(!"".equals(articleName)&&articleName!=null){
			condition.append(" and articleName like '%"+articleName+"%'");
			}
			if(!"".equals(articleType)&&articleType!=null){
				condition.append("and articleType like '%"+articleType+"%'");
			}
			List tawRmArticleList = mgr.getTawRmArticleByCondition(condition.toString());	
		request.setAttribute("tawRmArticleList", tawRmArticleList);
		return mapping.findForward("list");
	}
	
	public ActionForward xchange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		TawRmArticle tawRmArticle = mgr.getTawRmArticle(id);
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm)convert(tawRmArticle);
		tawRmArticleForm.setAllNum(1);
		request.setAttribute("tawRmArticleForm", tawRmArticleForm);
		return mapping.findForward("change");
	}
	
	public ActionForward xadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		String id = tawRmArticleForm.getId();
		int addNum = tawRmArticleForm.getAllNum();
		TawRmArticle tawRmArticle = mgr.getTawRmArticle(id);
		tawRmArticle.setAllNum(tawRmArticle.getAllNum()+addNum);
		tawRmArticle.setOnlineNum(tawRmArticle.getOnlineNum()+addNum);
		mgr.saveTawRmArticle(tawRmArticle);
		request.setAttribute("congratuation", "成功添加"+tawRmArticle.getArticleName()+addNum+"件");
		return mapping.findForward("succ");
	}
	public ActionForward xminus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		String id = tawRmArticleForm.getId();
		int minusNum = tawRmArticleForm.getAllNum();
		TawRmArticle tawRmArticle = mgr.getTawRmArticle(id);
		int onlineNum = tawRmArticle.getOnlineNum();
		if(minusNum>onlineNum){
			request.setAttribute("failReason", "减少的物品数量不能小于在线的物品数量");
			return mapping.findForward("fail");
		}
		tawRmArticle.setAllNum(tawRmArticle.getAllNum()-minusNum);
		tawRmArticle.setOnlineNum(tawRmArticle.getOnlineNum()-minusNum);
		mgr.saveTawRmArticle(tawRmArticle);
		request.setAttribute("congratuation", "成功减少"+tawRmArticle.getArticleName()+minusNum+"件");
		return mapping.findForward("succ");
	}
	

	public ActionForward xdetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		String id = tawRmArticleForm.getId();
		TawRmArticle tawRmArticle = mgr.getTawRmArticle(id);
		tawRmArticleForm = (TawRmArticleForm)convert(tawRmArticle);
		request.setAttribute("tawRmArticleForm", tawRmArticleForm);
		return mapping.findForward("detail");
	}
	

	public ActionForward xloan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawRmArticleManager mgr = (ITawRmArticleManager) getBean("ITawRmArticleManager");
		TawRmArticleForm tawRmArticleForm = (TawRmArticleForm) form;
		String id = tawRmArticleForm.getId();
		TawRmArticle tawRmArticle = mgr.getTawRmArticle(id);
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String workSerial=sessionform.getWorkSerial();
		
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}
			// 判断是否值班
			if (workSerial.equals("0")) {
				return mapping.findForward("notonduty1");
			}
		
		request.setAttribute("articleId", id);
		request.setAttribute("articleName", tawRmArticle.getArticleName());
		request.setAttribute("articleType", tawRmArticle.getArticleType());
		return mapping.findForward("loan");
	}
}
