package com.boco.eoms.partdata.webapp.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partdata.mgr.TawpartLacBscMgr;
import com.boco.eoms.partdata.mgr.TawpartlacRangeMgr;
import com.boco.eoms.partdata.model.TawpartlacRange;
import com.boco.eoms.partdata.util.TawpartlacRangeConstants;
import com.boco.eoms.partdata.webapp.form.TawpartlacRangeForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:LAC码号地市分配
 * </p>
 * <p>
 * Description:LAC码号地市分配
 * </p>
 * <p>
 * Mon Jul 12 09:21:06 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.6
 * 
 */
public final class TawpartlacRangeAction extends BaseAction {

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
	 * 新增LAC码号地市分配
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
	 * 修改LAC码号地市分配
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
		TawpartlacRangeMgr tawpartlacRangeMgr = (TawpartlacRangeMgr) getBean("tawpartlacRangeMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawpartlacRange tawpartlacRange = tawpartlacRangeMgr
				.getTawpartlacRange(id);
		TawpartlacRangeForm tawpartlacRangeForm = (TawpartlacRangeForm) convert(tawpartlacRange);
		updateFormBean(mapping, request, tawpartlacRangeForm);
		String l1l2s = tawpartlacRangeForm.getLoneLtwo() + "00";
		String l1l2e = tawpartlacRangeForm.getLoneLtwo() + "ff";
		request.setAttribute("l1l2s", Integer.parseInt(l1l2s, 16) + "");
		request.setAttribute("l1l2e", Integer.parseInt(l1l2e, 16) + "");
		List list = tawpartlacRangeMgr.getTawpartlacRangebyL1L2(tawpartlacRangeForm.getLoneLtwo());
		String xml = getXml(list, Integer.parseInt(l1l2s, 16));
		request.setAttribute("bingxml", xml);
		return mapping.findForward("edit");
	}

	/**
	 * 保存LAC码号地市分配
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
		TawpartlacRangeMgr tawpartlacRangeMgr = (TawpartlacRangeMgr) getBean("tawpartlacRangeMgr");
		TawpartlacRangeForm tawpartlacRangeForm = (TawpartlacRangeForm) form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		boolean isNew = (null == tawpartlacRangeForm.getId() || ""
				.equals(tawpartlacRangeForm.getId()));
		TawpartlacRange tawpartlacRange = (TawpartlacRange) convert(tawpartlacRangeForm);
		if (isNew) {
			if (!tawpartlacRangeMgr.isavailable(tawpartlacRange.getTenStart(),
					tawpartlacRange.getTenEnd())) {
				request.setAttribute("message", "所填范围与已经存在的范围有交集！");
				return mapping.findForward("fail");
			}
			tawpartlacRange.setCreateTime(StaticMethod.getLocalString());
			tawpartlacRange.setCreator(sessionform.getUsername());
			tawpartlacRangeMgr.saveTawpartlacRange(tawpartlacRange);

		} else {
			if (!tawpartlacRangeMgr.isavailablenotself(tawpartlacRange
					.getTenStart(), tawpartlacRange.getTenEnd(),
					tawpartlacRangeForm.getId())) {
				request.setAttribute("message", "所填范围与已经存在的范围有交集！");
				return mapping.findForward("fail");
			}
			tawpartlacRangeMgr.saveTawpartlacRange(tawpartlacRange);
		}
		return search(mapping, tawpartlacRangeForm, request, response);

	}

	/**
	 * 删除LAC码号地市分配
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
		TawpartlacRangeMgr tawpartlacRangeMgr = (TawpartlacRangeMgr) getBean("tawpartlacRangeMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
		tawpartLacBscMgr.removeTawpartLacBscbyRangeid(id);
		tawpartlacRangeMgr.removeTawpartlacRange(id);
		return search(mapping, form, request, response);
	}

	/**
	 * 分页显示LAC码号地市分配列表
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
				TawpartlacRangeConstants.TAWPARTLACRANGE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawpartlacRangeMgr tawpartlacRangeMgr = (TawpartlacRangeMgr) getBean("tawpartlacRangeMgr");
		Map map = (Map) tawpartlacRangeMgr.getTawpartlacRanges(pageIndex,
				pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(TawpartlacRangeConstants.TAWPARTLACRANGE_LIST,
				list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	public ActionForward toInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("init");
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawpartlacRangeMgr tawpartlacRangeMgr = (TawpartlacRangeMgr) getBean("tawpartlacRangeMgr");
		TawpartlacRangeForm tawpartlacRangeForm = (TawpartlacRangeForm) form;
		String l1l2 = tawpartlacRangeForm.getLoneLtwo();
		String l1l2s = l1l2 + "00";
		String l1l2e = l1l2 + "ff";
		request.setAttribute("l1l2s", Integer.parseInt(l1l2s, 16) + "");
		request.setAttribute("l1l2e", Integer.parseInt(l1l2e, 16) + "");
		List list = tawpartlacRangeMgr.getTawpartlacRangebyL1L2(l1l2);
		String xml = getXml(list, Integer.parseInt(l1l2s, 16));
		request.setAttribute("bingxml", xml);
		return mapping.findForward("edit");
	}

	private String getXml(List list, int initStart) {
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				TawpartlacRange p1 = (TawpartlacRange) o1;
				TawpartlacRange p2 = (TawpartlacRange) o2;
				int k = -1;
				if (p1.getTenStart().compareTo(p2.getTenStart()) > 0) {
					k = 1;
				} else if (p1.getTenStart().compareTo(p2.getTenStart()) < 0) {
					k = -1;
				}
				return k;

			}
		});

		String xml = "<chart palette='2' caption='LAC ASSIGN' shownames='1' showvalues='0'  numberPrefix='' showSum='1' decimals='0' useRoundEdges='1'>";
		xml += "<categories><category label='' /></categories>";
		int nextStart = initStart;
		int tenStart = 0;
		int tenEnd = 0;
		int substrac = 0;
		for (int i = 0; i < list.size(); i++) {
			TawpartlacRange tawpartlacRange = (TawpartlacRange) list.get(i);
			tenStart = Integer.parseInt(tawpartlacRange.getTenStart());
			tenEnd = Integer.parseInt(tawpartlacRange.getTenEnd());
			if (nextStart < tenStart) {
				substrac = tenStart - nextStart;
				xml += "<dataset seriesName='空闲(" + nextStart + "-"
						+ (tenStart - 1)
						+ ")' color='8BBA00' showValues='0'><set value='"
						+ substrac + "'/></dataset>";
				substrac = tenEnd - tenStart + 1;
				xml += "<dataset seriesName='" + tawpartlacRange.getAreaName()
						+ "(" + tawpartlacRange.getTenStart() + "-"
						+ tawpartlacRange.getTenEnd()
						+ ")' color='F6BD0F' showValues='0'><set value='"
						+ substrac + "'/></dataset>";
				nextStart = tenEnd + 1;
			} else {
				substrac = tenEnd - tenStart + 1;
				xml += "<dataset seriesName='" + tawpartlacRange.getAreaName()
						+ "(" + tawpartlacRange.getTenStart() + "-"
						+ tawpartlacRange.getTenEnd()
						+ ")' color='F6BD0F' showValues='0'><set value='"
						+ substrac + "'/></dataset>";
				nextStart = tenEnd + 1;
			}
		}
		if (nextStart < initStart + 255) {
			substrac = initStart + 255 - nextStart;
			xml += "<dataset seriesName='空闲(" + nextStart + "-"
					+ (initStart + 255)
					+ ")' color='8BBA00' showValues='0'><set value='"
					+ substrac + "'/></dataset>";
		}
		xml += "</chart>";
		System.out.println(xml);
		return xml;
	}

	public ActionForward assign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawpartlacRangeMgr tawpartlacRangeMgr = (TawpartlacRangeMgr) getBean("tawpartlacRangeMgr");
		TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
		TawpartlacRangeForm tawpartlacRangeForm = (TawpartlacRangeForm) form;
		String id = tawpartlacRangeForm.getId();
		Map tawpartLacBscmap = tawpartLacBscMgr.getTawpartLacBscList(id);
		TawpartlacRange tawpartlacRange = tawpartlacRangeMgr
				.getTawpartlacRange(id);
		String tenStart = tawpartlacRange.getTenStart();
		String tenEnd = tawpartlacRange.getTenEnd();
		String areaId = tawpartlacRange.getAreaId();
		String areaName = tawpartlacRange.getAreaName();
		request.setAttribute("tawpartLacBscmap", tawpartLacBscmap);
		request.setAttribute("rangeId", id);
		request.setAttribute("areaId", areaId);
		request.setAttribute("areaName", areaName);
		request.setAttribute("tenStart", tenStart);
		request.setAttribute("tenEnd", tenEnd);
		return mapping.findForward("assign");
	}
}