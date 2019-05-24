package com.boco.eoms.datum.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.datum.model.TawBureaudataCityzone;
import com.boco.eoms.datum.service.ITawBureaudataCityzoneManager;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public class TawBureaudataCityzoneAction extends BaseAction {
	/**
	 * 获取地市归属信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward seach(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
//		 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();

		// 分页取得列表
		int[] aTotal = { 0 };

		String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("list")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		ITawBureaudataCityzoneManager base = (ITawBureaudataCityzoneManager) getBean("iTawBureaudataCityzoneManager");
		List list  = base.findByAll();
		request.setAttribute("list", list);
		request.setAttribute("total", new Integer(aTotal[0]));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 显示地市信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findByid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String id =StaticMethod.nullObject2String(request.getParameter("cityid"),"");
		String type = StaticMethod.nullObject2String(request.getParameter("type"),"");
		ITawBureaudataCityzoneManager base = (ITawBureaudataCityzoneManager) getBean("iTawBureaudataCityzoneManager");
		TawBureaudataCityzone obj  = (TawBureaudataCityzone) base.findById(id);
		request.setAttribute("TawBureaudataCityzone", obj);
		request.setAttribute("type", type);
		return mapping.findForward("findByid");
	}
	
	/**
	 * 修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		Map map = request.getParameterMap();
		TawBureaudataCityzone obj = new TawBureaudataCityzone();
		SheetBeanUtils.populateMap2Bean(obj, map);
		ITawBureaudataCityzoneManager base = (ITawBureaudataCityzoneManager) getBean("iTawBureaudataCityzoneManager");
		base.saveorUpdate(obj);
		return mapping.findForward("success");
	}
	
	
	
	
}
