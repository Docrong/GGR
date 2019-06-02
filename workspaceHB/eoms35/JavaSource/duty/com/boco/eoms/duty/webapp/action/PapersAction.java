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
import com.boco.eoms.duty.mgr.PapersMgr;
import com.boco.eoms.duty.model.Papers;
import com.boco.eoms.duty.model.PapersPart;
import com.boco.eoms.duty.util.PapersConstants;
import com.boco.eoms.duty.webapp.form.PapersForm;
import com.boco.eoms.duty.webapp.form.PapersPartForm;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

/**
 * <p>
 * Title:资料
 * </p>
 * <p>
 * Description:资料
 * </p>
 * <p>
 * Tue Apr 07 10:05:44 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lixiaoming
 * @moudle.getVersion() 3.5
 * 
 */
public final class PapersAction extends BaseAction {
 
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
	 * 新增资料
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
	 * 修改资料
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
		PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		Papers papers = papersMgr.getPapers(id);
		PapersForm papersForm = (PapersForm) convert(papers);
		updateFormBean(mapping, request, papersForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存资料
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
		PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		String time = StaticMethod.getLocalString();
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		String deptId = sessionForm.getDeptid();
		PapersForm papersForm = (PapersForm) form;
		boolean isNew = (null == papersForm.getId() || "".equals(papersForm.getId()));
		Papers papers = (Papers) convert(papersForm);
		papers.setInsertTime(time);
		papers.setInsertUserId(userId);
		papers.setDeptId(deptId);
		papers.setDeleted("0");
		
		if (isNew) {
			papersMgr.savePapers(papers);
		} else {
			papersMgr.savePapers(papers);
		}
		Map map = (Map) papersMgr.getPapersId();
		List list = (List) map.get("result");
		if(list!=null&&list.size()>0){
		PapersPart papersPart = new PapersPart();
		String id = papersForm.getId();
		for(int i = 0;i<list.size();i++){
			papersPart = (PapersPart)list.get(i);
			String papersId = papersPart.getPapersId();
			if(id!=null&&!id.equals("")&&id.equals(papersId)){
				papersMgr.removePapersPart(id);
				break;
				}
			}
		}
		return search(mapping, papersForm, request, response);
	}
	/**
	 * 确认查看，点击之后向PAPERS_PART添加记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward selectView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		String deptId = sessionForm.getDeptid();
		String time = StaticMethod.getLocalString();
		String id = StaticMethod.null2String(request.getParameter("id"));
		Papers papers = papersMgr.getPapers(id);
		//PapersPartForm paperspartForm = (PapersPartForm) convert(papers);
		PapersPart paperspart = new PapersPart();
		paperspart.setInsertTime(time);
		paperspart.setInsertUserId(userId);
		paperspart.setDeptId(deptId);
		paperspart.setDeleted("0");
		paperspart.setPapersId(id);
		paperspart.setTitle(papers.getTitle());
		papersMgr.savePapersPart(paperspart);
		return mapping.findForward("selectview");
	}
	
	/**
	 * 删除资料
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
		PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		papersMgr.removePapers(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示资料列表
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
				PapersConstants.PAPERS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		Map map = (Map) papersMgr.getPaperss(pageIndex, pageSize, "",userId);
		List list = (List) map.get("result");
		request.setAttribute(PapersConstants.PAPERS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 跳转页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchTixing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				PapersConstants.PAPERS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		Map map = (Map) papersMgr.searchTixing(pageIndex, pageSize, "",userId);
		List list = (List) map.get("result");
		request.setAttribute(PapersConstants.PAPERS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("tixinglist");
	}
	/**
	 * 查询当前登陆人是否属于网络监控部门，如果属于，才执行跳转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean choosePerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//PapersMgr papersMgr = (PapersMgr) getBean("papersMgr");
		PapersMgr papersMgr = (PapersMgr) ApplicationContextHolder.getInstance().getBean("papersMgr");
		TawSystemSessionForm sessionForm = this.getUser(request);
		String userId = sessionForm.getUserid();
		Map map = (Map) papersMgr.getPerson();
		List list = (List) map.get("result");
		TawSystemUser tawSystemUser = new TawSystemUser();
		boolean a = false;
		for(int i = 0;i < list.size();i++){
			tawSystemUser = (TawSystemUser)list.get(i);
			String person = tawSystemUser.getUserid();
			if(person!=null&&person.equals(userId)){
				a = true;
				break;
			}
		}
		return a;
	}
}