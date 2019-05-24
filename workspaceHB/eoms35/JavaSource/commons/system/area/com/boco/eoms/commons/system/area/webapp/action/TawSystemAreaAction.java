package com.boco.eoms.commons.system.area.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.area.webapp.form.TawSystemAreaForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.JSONUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Action class to handle CRUD on a TawSystemArea object
 * 
 * @struts.action name="tawSystemAreaForm" path="/tawSystemAreas"
 *                scope="request" validate="false" parameter="method"
 *                input="mainMenu"
 * @struts.action name="tawSystemAreaForm" path="/editTawSystemArea"
 *                scope="request" validate="false" parameter="method"
 *                input="list"
 * @struts.action name="tawSystemAreaForm" path="/saveTawSystemArea"
 *                scope="request" validate="true" parameter="method"
 *                input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawSystemArea/tawSystemAreaForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawSystemArea/tawSystemAreaList.jsp"
 * @struts.action-forward name="search" path="/tawSystemAreas.html"
 *                        redirect="true"
 */
public final class TawSystemAreaAction extends BaseAction {

	
	/**
	 * 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();

		String areaid = request.getParameter("id");
	
		ITawSystemAreaManager mgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");

		TawSystemArea tawSystemArea = (TawSystemArea) mgr
				.getAreaByAreaId(areaid);
		List sameLevelarea = (ArrayList) mgr.getSameLevelArea(tawSystemArea
				.getParentAreaid(), tawSystemArea.getOrdercode());
		
		if (sameLevelarea == null || sameLevelarea.size() <= 0) {
			TawSystemArea parArea = (TawSystemArea) mgr
					.getAreaByAreaId(tawSystemArea.getParentAreaid());
			parArea.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
			mgr.saveTawSystemArea(parArea);
		}
		if (sameLevelarea != null && sameLevelarea.size() > 0) {
			List sonAreaList = (ArrayList) mgr.getAllSonAreaByAreaid(areaid);
			for (Iterator prowIt = sonAreaList.iterator(); prowIt.hasNext();) {
				TawSystemArea sysarea = (TawSystemArea) prowIt.next();
				mgr.removeTawSystemArea(String.valueOf(sysarea.getId()));
			}
		} else {
			mgr.removeTawSystemArea(String.valueOf(tawSystemArea.getId()));
		}
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawSystemArea.deleted"));
		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);
		return null;
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
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemAreaForm tawSystemAreaForm = (TawSystemAreaForm) form;
		MessageResources mr = this.getResources(request);
		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (tawSystemAreaForm.getId() != null) {
			ITawSystemAreaManager mgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
			TawSystemArea tawSystemArea = (TawSystemArea) convert(tawSystemAreaForm);
			if(!tawSystemArea.getAreacode().equals(tawSystemArea.getOldAreaName())){
				if(mgr.getAreaNameByName(tawSystemArea.getAreaname(),tawSystemArea.getParentAreaid())){
					String msg = mr.getMessage("tawsystemarea.false");
					JSONUtil.fail(response, msg);
					return null;
				}else{
					mgr.saveTawSystemArea(tawSystemArea);
				}
			}
			
			updateFormBean(mapping, request, tawSystemAreaForm);
		}

		return null;
	}

	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		MessageResources mr = this.getResources(request);
		ActionMessages messages = new ActionMessages();
		TawSystemAreaForm tawSystemAreaForm = (TawSystemAreaForm) form;
		boolean isNew = ("".equals(tawSystemAreaForm.getId()) || tawSystemAreaForm
				.getId() == null);

		ITawSystemAreaManager mgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
		TawSystemArea tawSystemArea = (TawSystemArea) convert(tawSystemAreaForm);
		  
		String pareareaid = tawSystemArea.getParentAreaid();
		if (pareareaid == null
				|| pareareaid.equals(TawSystemAreaUtil.AREA_DEFAULT_STRVAL)
				|| pareareaid.equals(TawSystemAreaUtil.AREA_DEFAULT_PARENTVAL)) {
			tawSystemArea
					.setParentAreaid(TawSystemAreaUtil.AREA_DEFAULT_PARENTVAL);
			tawSystemArea.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
			tawSystemArea
					.setOrdercode(TawSystemAreaUtil.AREA_DEFAULT_ORDERCODEVAL);
			tawSystemArea.setAreaid(mgr
					.getNewAreaid(TawSystemAreaUtil.AREA_DEFAULT_CODE));
			tawSystemArea.setAreacode(TawSystemAreaUtil.AREA_DEFAULT_CODE);
			
		} else {
			String newareaid = mgr.getNewAreaid(pareareaid);
			TawSystemArea parArea = new TawSystemArea();
			parArea = mgr.getAreaByAreaId(pareareaid);
			if (tawSystemArea.getAreaid() == null
					|| tawSystemArea.getAreaid().equals("")) {
				tawSystemArea.setAreaid(newareaid);
			}
			
			tawSystemArea.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
			tawSystemArea.setOrdercode(new Integer(parArea.getOrdercode().intValue()
					+ TawSystemAreaUtil.AREA_DEFAULT_ORDERCODEVAL.intValue()));
			tawSystemArea.setAreacode(TawSystemAreaUtil.AREA_DEFAULT_CODE);
			TawSystemArea parSysarea = (TawSystemArea)mgr.getAreaByAreaId(pareareaid);
			parSysarea.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFZERO);
            mgr.saveTawSystemArea(parSysarea);
		}
		if(mgr.getAreaNameByName(tawSystemArea.getAreaname(),tawSystemArea.getParentAreaid())){
			String msg = mr.getMessage("tawsystemarea.false");
			JSONUtil.fail(response, msg);
			return null;
		}else{
			mgr.saveTawSystemArea(tawSystemArea);
		}
		

		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSystemArea.added"));

			saveMessages(request.getSession(), messages);
			request.setAttribute("lastNewId", pareareaid);
			return null;
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"tawSystemArea.updated"));
			saveMessages(request, messages);
			request.setAttribute("lastEditId", tawSystemArea.getAreaid());
			return null;
		}
	}

	
	/**
	 * 得到某个节点的信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nodeId = request.getParameter("node");
		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		JSONArray jsonRoot = treebo.getAreaTree(nodeId, "");

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 查询所有地域
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String areaid = request.getParameter("id");
		ITawSystemAreaManager mgr = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
		TawSystemArea _objOneOpt = (TawSystemArea)mgr.getAreaByAreaId(areaid);
		_objOneOpt.setOldAreaName(_objOneOpt.getAreaname());
		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(_objOneOpt);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

}
