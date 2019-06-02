package com.boco.eoms.commons.log.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.log.dao.TawCommonLogOperatorDao;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.log.service.ITawCommonLogFileBo;

import com.boco.eoms.commons.log.service.impl.TawCommonLogFileBoImpl;

import com.boco.eoms.commons.log.webapp.bo.ITawCommonLogSearchBo;

import com.boco.eoms.commons.loging.BocoLog;


/**
 * 根据查询条件的不同进行查询控制
 * 
 * @author panlong Apr 10, 2007 10:28:51 AM
 */
public class TawCommonLogConditionAction extends BaseAction {

	private static List listdb = null;

	private static List listfile = null;

	private ITawCommonLogFileBo searchfile;

	// private TawCommonLogOperatorDao tawCommonLogOperatorDao;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			Map formMap = ((DynaActionForm) actionForm).getMap();

			String userid = (String) formMap.get("searchbyuser");
			String modeid = (String) formMap.get("searchbymodel");
			String operid = (String) formMap.get("searchbyoper");
			String starttime = (String) formMap.get("searchbystarttime");
			String endtime = (String) formMap.get("searchbyendtime");
			String issucess = (String) formMap.get("issucess");
			if (!starttime.equals("") && starttime != null) {
				starttime = starttime + " 00:00:00";
			} else {
				starttime = "";
			}
			// !"".equals(endtime)
			if (!endtime.equals("") && endtime != null) {
				endtime = endtime + " 23:59:59";
			} else {
				endtime = "";
			}
           
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					"logList")
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
			// 每页显示条数
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));
			TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
					.getInstance().getBean("tawCommonLogOperatorDao");
			
			Map map = null;
			map = dao.getAllbyUseridModelidOperids(pageIndex, pageSize, userid,
					modeid, operid, issucess, starttime, endtime);
			List list = (List) map.get("result");
			request.setAttribute("logList", list);
			request.setAttribute("resultSize", map.get("total"));
			System.out.println(map.get("total"));
			request.setAttribute("pageSize", pageSize);

		} catch (Exception e) {

			BocoLog.error(this, "查询错误 " + e.getMessage());
			return mapping.findForward("failure");

		}
		return mapping.findForward("success");
	}

	public boolean getFlag(String type) {
		boolean flag = false;
		if (type == null || type.equals("")) {
			flag = true;
		}
		return flag;
	}

	public boolean getFlagtrue(String type) {
		boolean flag = true;
		if (type == null || type.equals("")) {
			flag = false;
		}
		return flag;
	}

	public ActionForward getNoteByUserID(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String userid, String issucess) {

		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllByUserIDs(userid, issucess);
		listfile = searchfile.getLognotebyUserIDs(userid, issucess, "filepath");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward getNoteByUseridAndModelID(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String userid, String modelid,
			String issucess) {

		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllByuseridandModelids(userid, modelid, issucess);
		listfile = searchfile.getLognoteByAllTimes(userid, modelid, issucess,
				"");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward getNoteByUseridAndModelID(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String userid, String modelid,
			String operid, String issucess) {

		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllByuseridandModelids(userid, modelid, issucess);
		listfile = searchfile.getLognoteBymidopids(userid, modelid, operid,
				issucess, "");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward getNoteByUseridAndModelID(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String userid, String modelid,
			String operid, String starttime, String endtime, String issucess)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawcommonlogoperlist")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllBymodelopertiems(request, pageIndex, pageSize,
				userid, modelid, operid, starttime, endtime, issucess);
		listfile = searchfile.getLognoteByAllTimes(userid, modelid, operid,
				starttime, endtime, issucess, "");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward getNoteByModelid(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String modelid, String issucess) {

		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllBymodelids(modelid, issucess);
		listfile = searchfile.getLognoteBymodelids(modelid, issucess, "");
		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);
		return mapping.findForward("success");

	}

	public ActionForward getNoteByModelidAndOperid(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String modelid, String operid,
			String issucess) {

		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllbyModelidAndOperids(modelid, operid, issucess);
		listfile = searchfile.getLognoteBymodelIDandoperIDs(modelid, operid,
				issucess, "");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward getNoteByModelidAndOperidAndTime(
			ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response,
			String modelid, String operid, String starttime, String endtime,
			String issucess) {

		listdb = new ArrayList();
		listfile = new ArrayList();
		ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo) ApplicationContextHolder
				.getInstance().getBean("iTawCommonLogSearchBo");
		searchfile = new TawCommonLogFileBoImpl();
		listdb = searchdb.getAllByMidAndOperidAndTimes(modelid, operid,
				starttime, endtime, issucess);
		listfile = searchfile.getLognoteByModelIdAndOperidAndtimes(modelid,
				operid, starttime, endtime, issucess, "");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward getNoteAll(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, String issucess) throws Exception {

		listdb = new ArrayList();
		listfile = new ArrayList();

		searchfile = new TawCommonLogFileBoImpl();

		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		TawCommonLogOperator oper = new TawCommonLogOperator();
		TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorDao");
		dao.getTawCommonLogOperators(oper);

		searchfile = new TawCommonLogFileBoImpl();
		listdb = dao.getAllbyisSucess(issucess);

		listfile = searchfile.getLognotes(issucess, "");

		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("tawcommonfilelist", listfile);

		return mapping.findForward("success");

	}

	public ActionForward search(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("list");
	}

	/**
	 * 日志查询by何毅
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @param issucess
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQuerydo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, final String userid,
			final String modelid, final String operid, final String starttime,
			final String endtime, final String issucess) throws Exception {

		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawcommonlogoperlist")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
				.getInstance().getBean("tawCommonLogOperatorDao");
		listdb = new ArrayList();
		Map map = null;
		map = dao.getAllbyUseridModelidOperids(pageIndex, pageSize, userid,
				modelid, operid, issucess, starttime, endtime);
		listdb = (List) map.get("result");
		request.setAttribute("resultSize", map.get("total"));
		// ITawCommonLogSearchBo searchdb = (ITawCommonLogSearchBo)
		// ApplicationContextHolder
		// .getInstance().getBean("iTawCommonLogSearchBo");
		// listdb = searchdb.getAllBymodelopertiems( request, pageIndex,
		// pageSize,userid, modelid, operid,
		// starttime, endtime, issucess);
		request.setAttribute("tawcommonlogoperlist", listdb);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("success");

	}
}
