
package com.boco.eoms.parter.baseinfo.pnrcompact.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager;
import com.boco.eoms.parter.baseinfo.mainmetermgr.webapp.form.MainmetermgrForm;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;
import com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager;
import com.boco.eoms.parter.baseinfo.pnrcompact.webapp.form.PnrcompactForm;

import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a Pnrcompact object
 * @struts.action name="pnrcompactForm" path="/pnrcompacts" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/pnrcompact/pnrcompactTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/pnrcompact/pnrcompactForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/pnrcompact/pnrcompactList.jsp" contextRelative="true"
 */
public final class PnrcompactAction extends BaseAction {
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
         return mapping.findForward("main");
    }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;

		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		Pnrcompact pnrcompact = (Pnrcompact) convert(pnrcompactForm);
		mgr.savePnrcompact(pnrcompact);
		// JSONUtil.print(response, "操作成功");
		String typeName = request.getParameter("typeName");
		if(typeName != null && typeName.equals("updateEdit")){
			return mapping.findForward("toUpdate");
		}
		// JSONUtil.print(response, "操作成功");
		return mapping.findForward("success");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;

        IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		mgr.removePnrcompact(pnrcompactForm.getId());
		return mapping.findForward("list");
	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;

		if (pnrcompactForm.getId() != null) {
			IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
			Pnrcompact pnrcompact = (Pnrcompact) convert(pnrcompactForm);

			mgr.savePnrcompact(pnrcompact);
		   //mgr.updatePnrcompact(pnrcompact);
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
		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		Pnrcompact pnrcompact = mgr.getPnrcompact(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(pnrcompact);

		JSONUtil.print(response, jsonRoot.toString());
	}
	
	/**
	 * 显示某版块下的信息信息列表	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder("pnrcompactList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		Map map = mgr.getPnrcompacts(pageIndex, pageSize, "");
		List list = (List) map.get("result");	
		request.setAttribute("pnrcompactListPriv", "pnrcompactListPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("pnrcompactList", list);		
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("list");
	}
	
	/**
	 * 转向新增、修改
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
		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;
		String id = request.getParameter("id");
		if (pnrcompactForm.getId() != null) {
			IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
			Pnrcompact forums = mgr.getPnrcompact(pnrcompactForm.getId());
			pnrcompactForm = (PnrcompactForm) convert(forums);
			updateFormBean(mapping, request, pnrcompactForm);
		}
		return mapping.findForward("edit");
	}
	/**
	 * 转向 查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		return mapping.findForward("toSearch");
	}
	/**
	 * 转向 修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"pnrcompactList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		Map map = mgr.getPnrcompacts(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("pnrcompactListPriv", "pnrcompactListPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("pnrcompactList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("toUpdate");
	}

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}
	/**
	 * 转向 删除页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder("pnrcompactList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		Map map = mgr.getPnrcompacts(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("pnrcompactListPriv", "pnrcompactListPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("pnrcompactList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("toDeleteList");
	}
	
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;
		String id = request.getParameter("id");
		if (pnrcompactForm.getId() != null) {
			IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
			Pnrcompact forums = mgr.getPnrcompact(pnrcompactForm.getId());
			pnrcompactForm = (PnrcompactForm) convert(forums);
			updateFormBean(mapping, request, pnrcompactForm);
		}
		return mapping.findForward("toEdit");
	}
	/**
	 * 通过修改列表列表进入修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toUpdateEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;
		if (pnrcompactForm.getId() != null) {
			IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
			Pnrcompact forums = mgr.getPnrcompact(pnrcompactForm.getId());
			pnrcompactForm = (PnrcompactForm) convert(forums);
			updateFormBean(mapping, request, pnrcompactForm);
		}
		return mapping.findForward("toUpdateEdit");
	}
	
	/**
	 * 查询信息列表
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
		PnrcompactForm pnrcompactForm = (PnrcompactForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder("pnrcompactList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		String deptStr = "";
		Map map = mgr.getPnrcompacts(pageIndex, pageSize, this.getSearchSql(pnrcompactForm, deptStr));
		List list = (List) map.get("result");
		request.setAttribute("pnrcompactList", list);
		request.setAttribute("pnrcompactPriv", "pnrcompactPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("searchList");
	}
	/**
	 * 删除所选信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids[] = request.getParameterValues("ids");
		// 未选中则失败
		// TODO 给出提示信息
		if (ids == null) {
			return mapping.findForward("fail");
		}
		// 信息管理
		IPnrcompactManager mgr = (IPnrcompactManager) getBean("IpnrcompactManager");
		mgr.removePnrcompact(ids);	
		return mapping.findForward("success");
	}
	/**
	 * 返回查询语句的sql
	 * 
	 * @param threadForm
	 *            信息form
	 * @param deptStr
	 *            部门列表 信息form
	 * @return 返回查询语句的sql
	 */
	public String getSearchSql(PnrcompactForm pnrcompactForm, String deptStr) {
		String sql = "";//"from CarMgr carMgr  where ";
		// 合同号
		if (pnrcompactForm.getCompactNumVal() != null&& !"".equals(pnrcompactForm.getCompactNumVal())) {
			sql += " and compactNumVal like '%"+pnrcompactForm.getCompactNumVal()+ "%'";
		}
		if (pnrcompactForm.getConmpactUnitVal() != null&& !"".equals(pnrcompactForm.getConmpactUnitVal())) {
			sql += " and conmpactUnitVal like '%"+pnrcompactForm.getConmpactUnitVal()+ "%'";
		}
		if (pnrcompactForm.getCompactSignedDateVal() != null&& !"".equals(pnrcompactForm.getCompactSignedDateVal())) {
			sql += " and compactSignedDateVal=to_date('"+pnrcompactForm.getCompactSignedDateVal()+ "','yyyy-mm-dd')";
		}

		// 截掉第一个 and
		if (sql.length() > 2) {
			sql = sql.substring(4, sql.length());
			sql = "  where " + sql+"  order by id";
		}else{
			sql="  order by id";
		}
		return sql;
	}
}
