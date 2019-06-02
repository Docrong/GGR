package com.boco.eoms.pq.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.pq.mgr.IHoldPQMgr;
import com.boco.eoms.pq.model.HoldPQ;
import com.boco.eoms.pq.model.HoldPQVO;
import com.boco.eoms.pq.util.Constants;
import com.boco.eoms.pq.util.PQConfigLocator;

public final class HoldPQAction extends BaseAction {

	/**
	 * 默认方法
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return listErrorJobs(mapping, form, request, response);
	}

	/**
	 * 列出所有执行出错的任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listErrorJobs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int offset;
		int length = PQConfigLocator.getPQConfig().getPageSize().intValue();
		String pageOffset = request.getParameter("pager.offset");
		if (pageOffset == null || pageOffset.equals("")) {
			offset = 0;
		} else {
			offset = Integer.parseInt(pageOffset);
		}
		String pageIndexName = new org.displaytag.util.ParamEncoder("jobList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		final Integer pageSize = PQConfigLocator.getPQConfig().getPageSize();

		IHoldPQMgr holdPQMgr = (IHoldPQMgr) getBean("holdPQMgr");
		Map map = (Map) holdPQMgr.listHoldPQs(pageIndex, pageSize,
				Constants.Q_STATUS_ERROR, Constants.UNDELETED);

		List list = (List) map.get("result");

		List jobList = new ArrayList();

		// 分页
		for (int i = offset; i < (length + offset) && i < list.size(); i++) {
			HoldPQ holdPQ = (HoldPQ) list.get(i);
			HoldPQVO holdPQVO = new HoldPQVO(holdPQ);
			jobList.add(holdPQVO);
		}
		request.setAttribute("jobList", jobList);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("listErrorJobs");
	}

	/**
	 * 手动执行出错任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doJobManually(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jobId = StaticMethod.null2String(request.getParameter("jobId"));
		IHoldPQMgr holdPQMgr = (IHoldPQMgr) getBean("holdPQMgr");
		holdPQMgr.doHoldPQ(jobId);
		request.setAttribute("note", "ID为：" + jobId
				+ "的任务正在后台执行，若执行成功系统将会删除此任务！");
		return listErrorJobs(mapping, form, request, response);
	}

	/**
	 * 删除执行出错任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteErrorJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jobId = StaticMethod.null2String(request.getParameter("jobId"));
		IHoldPQMgr holdPQMgr = (IHoldPQMgr) getBean("holdPQMgr");
		try {
			holdPQMgr.delHoldPQ(jobId);
		} catch (Exception e) {
			request.setAttribute("note", "ID为：" + jobId + "的任务删除失败！");
			e.printStackTrace();
		} finally {
			return listErrorJobs(mapping, form, request, response);
		}
	}

}
