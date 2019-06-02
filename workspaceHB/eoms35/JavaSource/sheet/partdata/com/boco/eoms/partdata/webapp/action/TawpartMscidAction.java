package com.boco.eoms.partdata.webapp.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partdata.mgr.TawpartMscidMgr;
import com.boco.eoms.partdata.model.TawpartMscid;
import com.boco.eoms.partdata.util.TawpartMscidConstants;
import com.boco.eoms.partdata.webapp.form.TawpartMscidForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:MSCID码号管理
 * </p>
 * <p>
 * Description:MSCID码号管理
 * </p>
 * <p>
 * Mon Jul 05 09:11:48 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.6
 * 
 */
public final class TawpartMscidAction extends BaseAction {

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
	 * 新增MSCID码号管理
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
	 * 修改MSCID码号管理
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
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawpartMscid tawpartMscid = tawpartMscidMgr.getTawpartMscid(id);
		TawpartMscidForm tawpartMscidForm = (TawpartMscidForm) convert(tawpartMscid);
		updateFormBean(mapping, request, tawpartMscidForm);
		return mapping.findForward("edit");
	}

	/**
	 * 保存MSCID码号管理
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
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		TawpartMscidForm tawpartMscidForm = (TawpartMscidForm) form;
		boolean isNew = (null == tawpartMscidForm.getId() || ""
				.equals(tawpartMscidForm.getId()));
		TawpartMscid tawpartMscid = (TawpartMscid) convert(tawpartMscidForm);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		if (isNew) {
			tawpartMscid.setCreator(sessionform.getUsername());
			tawpartMscid.setCreateTime(StaticMethod.getLocalString());
			tawpartMscidMgr.saveTawpartMscid(tawpartMscid);
		} else {
			tawpartMscidMgr.saveTawpartMscid(tawpartMscid);
		}
		return mapping.findForward("success");
		// return search(mapping, tawpartMscidForm, request, response);
	}

	/**
	 * 保存单个码号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
			TawpartMscidForm tawpartMscidForm = (TawpartMscidForm) form;
			TawpartMscid tawpartMscid = (TawpartMscid) convert(tawpartMscidForm);
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (sessionform == null) {
				return mapping.findForward("timeout");
			}
			tawpartMscid.setAssigner(sessionform.getUsername());
			tawpartMscid.setAssignTime(StaticMethod.getLocalString());
			tawpartMscid.setDistributed("1");
			tawpartMscidMgr.saveOneTawpartMscid(tawpartMscid);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	/**
	 * 删除MSCID码号管理
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
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		TawpartMscidForm tawpartMscidForm = (TawpartMscidForm) form;
		tawpartMscidMgr.removeTawpartMscid(tawpartMscidForm.getHeadNumber(),
				StaticMethod.null2String(tawpartMscidForm.getNumberM0()),
				StaticMethod.null2String(tawpartMscidForm.getNumberM1()),
				StaticMethod.null2String(tawpartMscidForm.getNumberM2()));
		return mapping.findForward("success");
	}

	public ActionForward toRemove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("remove");
	}

	/**
	 * 分页显示MSCID码号管理列表
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
				TawpartMscidConstants.TAWPARTMSCID_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		Map map = (Map) tawpartMscidMgr.getTawpartMscids(pageIndex, pageSize,
				"");
		List list = (List) map.get("result");
		request.setAttribute(TawpartMscidConstants.TAWPARTMSCID_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	public ActionForward assignInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*
		 * TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr)
		 * getBean("tawpartMscidMgr"); String id =
		 * StaticMethod.null2String(request.getParameter("id")); TawpartMscid
		 * tawpartMscid = tawpartMscidMgr.getTawpartMscid(id); TawpartMscidForm
		 * tawpartMscidForm = (TawpartMscidForm) convert(tawpartMscid);
		 * updateFormBean(mapping, request, tawpartMscidForm);
		 */
		return mapping.findForward("assign");
	}

	public ActionForward toAssign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		TawpartMscidForm tawpartMscidForm = (TawpartMscidForm) form;
		String headNumber = tawpartMscidForm.getHeadNumber();
		List m0List = tawpartMscidMgr.getM0byHeadNumber(headNumber);
		List m1List = tawpartMscidMgr.getM1byHeadNumber(headNumber);
		List m2List = tawpartMscidMgr.getM2byHeadNumber(headNumber);
		List iDList = tawpartMscidMgr.getIDbyHeadNumber(headNumber);
		List tawpartMscidList = tawpartMscidMgr
				.getTawpartMscidsbyHeadnumber(headNumber);
		Map mscidMap = new HashMap();
		for (int i = 0; i < tawpartMscidList.size(); i++) {
			if (tawpartMscidList.get(i) != null
					&& !mscidMap.containsKey(((TawpartMscid) tawpartMscidList
							.get(i)).getNumberFree()))
				;
			mscidMap.put(((TawpartMscid) tawpartMscidList.get(i))
					.getNumberFree(), (TawpartMscid) tawpartMscidList.get(i));
		}
		request.setAttribute("mscidMap", mscidMap);
		request.setAttribute("headNumber", headNumber);
		request.setAttribute("m0List", m0List);
		request.setAttribute("m1List", m1List);
		request.setAttribute("m2List", m2List);
		request.setAttribute("iDList", iDList);
		request.setAttribute("tawpartMscidList", tawpartMscidList);
		return mapping.findForward("assign");
	}

	public ActionForward assignSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		Map mscidMap = (Map) request.getSession().getAttribute("mscidMap1");
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		if (mscidMap != null) {
			Set mscidSet = mscidMap.entrySet();
			Iterator iter = mscidSet.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				TawpartMscid tawpartMscid = (TawpartMscid) entry.getValue();
				String areaName = request.getParameter(key);
				tawpartMscid.setAreaName(areaName);
				tawpartMscid.setDistributed("1");
				tawpartMscid.setAssigner(sessionform.getUsername());
				tawpartMscid.setAssignTime(StaticMethod.getLocalString());
				tawpartMscid
						.setCityname(request.getParameter("cityName" + key));
				tawpartMscid.setSignalname(request.getParameter("singalNum"
						+ key));
				tawpartMscid.setEquipmentfactory(request
						.getParameter("equFactory" + key));
				tawpartMscidMgr.updateTawpartMscid(tawpartMscid);
			}
		}
		return mapping.findForward("success");
	}

	public ActionForward assignOneSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		Map mscidMap = (Map) request.getSession().getAttribute("mscidMap1");
		TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
		if (mscidMap != null) {
			Set mscidSet = mscidMap.entrySet();
			Iterator iter = mscidSet.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				TawpartMscid tawpartMscid = (TawpartMscid) entry.getValue();
				String areaName = request.getParameter(key);
				tawpartMscid.setAreaName(areaName);
				tawpartMscid.setDistributed("1");
				tawpartMscid.setAssigner(sessionform.getUserid());
				tawpartMscid.setAssignTime(StaticMethod.getLocalString());
				tawpartMscid
						.setCityname(request.getParameter("cityName" + key));
				tawpartMscid.setSignalname(request.getParameter("singalNum"
						+ key));
				tawpartMscid.setEquipmentfactory(request
						.getParameter("equFactory" + key));
				tawpartMscidMgr.updateTawpartMscid(tawpartMscid);
			}
		}
		return mapping.findForward("success");
	}

	/**
	 * 分配页面编辑单个码号进行管理（弹出新的页面进行管理）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editSignleNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getParameter("id") != null) {
			request.setAttribute("id", request.getParameter("id").toString());
		}
		if (request.getParameter("value") != null) {
			String idt = request.getParameter("value");
			idt = java.net.URLDecoder.decode(idt, "UTF-8");
			request.setAttribute("value", idt);
		}
		if (request.getParameter("idt") != null) {
			try {
				String idt = request.getParameter("idt");
				TawpartMscidMgr tawpartMscidMgr = (TawpartMscidMgr) getBean("tawpartMscidMgr");
				TawpartMscid tawpartMscid = tawpartMscidMgr
						.getTawpartMscid(idt);
				request
						.setAttribute("signalname", tawpartMscid
								.getSignalname());
				request.setAttribute("cityname", tawpartMscid.getCityname());
				request.setAttribute("equipmentfactory", tawpartMscid
						.getEquipmentfactory());
				request.setAttribute("idt", idt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapping.findForward("editonenum");
	}
}