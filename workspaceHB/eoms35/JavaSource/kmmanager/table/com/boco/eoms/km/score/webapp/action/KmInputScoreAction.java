package com.boco.eoms.km.score.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.km.score.mgr.KmInputScoreMgr;
import com.boco.eoms.km.score.util.KmScoreConstants;


/**
 * <p>
 * Title:知识管理导入积分管理
 * </p>
 * <p>
 * Description:知识管理导入积分管理
 * </p>
 * <p>
 * 2009-08-04
 * </p>
 * 
 * @moudle.getAuthor() daizhigang
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmInputScoreAction extends BaseAction {

	/**
	 * 未指定方法时默认调用的方法
	 * 
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
	 * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
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
		String next = "list";
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmScoreConstants.KMINPUTSCORE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		KmInputScoreMgr kmInputScoreMgr = (KmInputScoreMgr) getBean("kmInputScoreMgr");
		List column = kmInputScoreMgr.getKmInputScoreColumns();
		Map map = kmInputScoreMgr.getKmInputScores(pageIndex, pageSize, "", column);
		request.setAttribute("column", column);
		request.setAttribute(KmScoreConstants.KMINPUTSCORE_LIST, map.get("result"));
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		//导出excel
		return mapping.findForward(next);
	}
	/**
	 *导入积分的历史列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inputScoreHistoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String next = "historyList";
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmScoreConstants.KMINPUTSCORE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		KmInputScoreMgr kmInputScoreMgr = (KmInputScoreMgr) getBean("kmInputScoreMgr");
		List column = kmInputScoreMgr.getKmInputScoreColumns();
		Map map = kmInputScoreMgr.getKmInputScoresHistory(pageIndex, pageSize, "", column);
		request.setAttribute("column", column);
		request.setAttribute(KmScoreConstants.KMINPUTSCORE_LIST, map.get("result"));
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		//导出excel
		return mapping.findForward(next);
	}

}