
package com.boco.eoms.commons.system.code.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.code.model.TawSystemCode;
import com.boco.eoms.commons.system.code.service.ITawSystemCodeManager;
import com.boco.eoms.commons.system.code.webapp.form.TawSystemCodeForm;

import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.duty.util.DutyConstacts;

/**
 * Action class to handle CRUD on a TawSystemCode object
 * @struts.action name="tawSystemCodeForm" path="/tawSystemCodes" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/tawSystemCode/tawSystemCodeTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/tawSystemCode/tawSystemCodeForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/tawSystemCode/tawSystemCodeList.jsp" contextRelative="true"
 */
public final class TawSystemCodeAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
         //return mapping.findForward("search");
           return null;
    }
     public ActionForward main(ActionMapping mapping, ActionForm form,
     	HttpServletRequest request,HttpServletResponse response)
    	throws Exception {
         return mapping.findForward("search");
    }
     public ActionForward add(ActionMapping mapping, ActionForm form,
    	     	HttpServletRequest request,HttpServletResponse response)
    	    	throws Exception {
    	         return mapping.findForward("add");
    	    }
     public ActionForward edit (ActionMapping mapping, ActionForm form,
    		 HttpServletRequest request,HttpServletResponse response)
     		 throws Exception {
    	 String id = request.getParameter("id");
    	 ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
    	 TawSystemCode tawSystemCode = mgr.getTawSystemCode(id);
    	 request.setAttribute("TawSystemCodeForm", tawSystemCode);
    	 return mapping.findForward("add");
     }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemCodeForm tawSystemCodeForm = (TawSystemCodeForm) form;

		ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
		TawSystemCode tawSystemCode = (TawSystemCode) convert(tawSystemCodeForm);
		mgr.saveTawSystemCode(tawSystemCode);
		 return mapping.findForward("success");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSystemCodeForm tawSystemCodeForm = (TawSystemCodeForm) form;

        ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
		mgr.removeTawSystemCode(tawSystemCodeForm.getId());
		return xquery(mapping, form, request, response);
	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemCodeForm tawSystemCodeForm = (TawSystemCodeForm) form;

		if (tawSystemCodeForm.getId() != null) {
			ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
			TawSystemCode tawSystemCode = (TawSystemCode) convert(tawSystemCodeForm);

			mgr.saveTawSystemCode(tawSystemCode);
		   //mgr.updateTawSystemCode(tawSystemCode);
		}

		return null;
	}

     /**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
		TawSystemCode tawSystemCode = mgr.getTawSystemCode(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawSystemCode);

		JSONUtil.print(response, jsonRoot.toString());
	}
	/**
	 * 根据组合条件查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemCodeForm tawSystemCodeForm = (TawSystemCodeForm) form;
		ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
		// String lanStartIp = supportForm.getLanStartIp();
		// StringBuffer condition = new StringBuffer("from Support where 1=1");
		StringBuffer condition = new StringBuffer(" where 1=1");
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				DutyConstacts.TAW_WORKBENCH_MEMOLIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
		String name = tawSystemCodeForm.getName();
		String code = tawSystemCodeForm.getCode();
		HttpSession session = request.getSession();
		if(name==null&&code==null){
			name = (String)session.getAttribute("name");
			code = (String)session.getAttribute("code");
		}
		if (name != null && !name.equals("")) {
			condition.append(" and name like '%" + name + "%'");
			String s = condition.toString();
		}
		if (code != null && !code.equals("")) {
			condition.append(" and code like '%" + code + "%'");
		}
		String where = condition.toString();
		Map map = (Map)mgr.getTawSystemCodes(pageIndex, pageSize, condition.toString());
		List safeServiceNotesList = (List) map.get("result");
		List tawSystemCodeList = mgr.getTawSystemCodeByCondition(where);
		request.setAttribute("tawSystemCodeList",tawSystemCodeList);
		session.setAttribute("name", name);
		session.setAttribute("code", code);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	/**
	 * 查看页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemCodeForm tawSystemCodeForm = (TawSystemCodeForm) form;
		//tawSystemCodeForm sessionForm = this.getUser(request);
		//String UserId = sessionForm.getUserid();
		
		String id = tawSystemCodeForm.getId();
		if (id != null && !id.equals("")) {
			ITawSystemCodeManager mgr = (ITawSystemCodeManager) getBean("ItawSystemCodeManager");
			//TawSystemCode tawSystemCode = (TawSystemCode) convert(tawSystemCodeForm);
			TawSystemCode tawSystemCode = mgr.getTawSystemCode(id);
			//safeServiceNotesForm.setNoticeTime(safeServiceNotes.getNoticeTime());
			tawSystemCodeForm.setName(tawSystemCode.getName());
			tawSystemCodeForm.setCode(tawSystemCode.getCode());
			//safeServiceNotesForm = (SafeServiceNotesForm)convert(safeServiceNotes);
				request.setAttribute("tawSystemCodeForm", tawSystemCodeForm);
				//跳转到显示页面
				return mapping.findForward("view");
			}		
		return mapping.findForward("error");
	}
}
