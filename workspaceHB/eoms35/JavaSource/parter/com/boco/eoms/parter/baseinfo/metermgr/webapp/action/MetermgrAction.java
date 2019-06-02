
package com.boco.eoms.parter.baseinfo.metermgr.webapp.action;

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
import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;
import com.boco.eoms.parter.baseinfo.metermgr.service.IMetermgrManager;
import com.boco.eoms.parter.baseinfo.metermgr.webapp.form.MetermgrForm;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;
import com.boco.eoms.parter.baseinfo.pnrcompact.service.IPnrcompactManager;
import com.boco.eoms.parter.baseinfo.pnrcompact.webapp.form.PnrcompactForm;

import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a Metermgr object
 * @struts.action name="metermgrForm" path="/metermgrs" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/metermgr/metermgrTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/metermgr/metermgrForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/metermgr/metermgrList.jsp" contextRelative="true"
 */
public final class MetermgrAction extends BaseAction {
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

		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MetermgrForm metermgrForm = (MetermgrForm) form;

		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		String newSave = request.getParameter("newSave");
		if("newSave".equals(newSave)){
		Map map = mgr.getMetermgrs(Integer.valueOf(100), Integer.valueOf(100),  this.getSearchSqlNotUniquely(metermgrForm, ""));
			if(!map.get("total").toString().equals("0")){
				return mapping.findForward("notUniquely");
			}
		}
		Metermgr metermgr = (Metermgr) convert(metermgrForm);
		mgr.saveMetermgr(metermgr);
		// JSONUtil.print(response, "操作成功");
		String typeName = request.getParameter("typeName");
		if(typeName != null && typeName.equals("updateEdit")){
			return mapping.findForward("toUpdate");
		}
		// JSONUtil.print(response, "操作成功");
		return mapping.findForward("list");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MetermgrForm metermgrForm = (MetermgrForm) form;

        IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		mgr.removeMetermgr(metermgrForm.getId());
		return mapping.findForward("list");
	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MetermgrForm metermgrForm = (MetermgrForm) form;

		if (metermgrForm.getId() != null) {
			IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
			Metermgr metermgr = (Metermgr) convert(metermgrForm);

			mgr.saveMetermgr(metermgr);
		   //mgr.updateMetermgr(metermgr);
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
		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		Metermgr metermgr = mgr.getMetermgr(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(metermgr);

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
		String pageIndexName = new org.displaytag.util.ParamEncoder("metermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		Map map = mgr.getMetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");	
		request.setAttribute("metermgrPriv", "metermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("metermgrList", list);		
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
		MetermgrForm metermgrForm = (MetermgrForm) form;
		if (metermgrForm.getId() != null) {
			IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
			Metermgr forums = mgr.getMetermgr(metermgrForm.getId());
			metermgrForm = (MetermgrForm) convert(forums);
			updateFormBean(mapping, request, metermgrForm);
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
		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		Map map = mgr.getMetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("metermgrListPriv", "metermgrListPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("metermgrList", list);
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
		String pageIndexName = new org.displaytag.util.ParamEncoder("metermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		Map map = mgr.getMetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("metermgrPriv", "metermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("metermgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 			
		return mapping.findForward("toDeleteList");
	}
	
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MetermgrForm metermgrForm = (MetermgrForm) form;
		String id = request.getParameter("id");
		if (metermgrForm.getId() != null) {
			IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
			Metermgr forums = mgr.getMetermgr(metermgrForm.getId());
			metermgrForm = (MetermgrForm) convert(forums);
			updateFormBean(mapping, request, metermgrForm);
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
		MetermgrForm metermgrForm = (MetermgrForm) form;
		String id = request.getParameter("id");
		if (metermgrForm.getId() != null) {
			IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
			Metermgr forums = mgr.getMetermgr(metermgrForm.getId());
			metermgrForm = (MetermgrForm) convert(forums);
			updateFormBean(mapping, request, metermgrForm);
		}
		return mapping.findForward("toUpdateEdit");
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
		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		mgr.removeMetermgr(ids);	
		return mapping.findForward("success");
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
		MetermgrForm metermgrForm = (MetermgrForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder("metermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IMetermgrManager mgr = (IMetermgrManager) getBean("ImetermgrManager");
		String deptStr = "";
		Map map = mgr.getMetermgrs(pageIndex, pageSize, this.getSearchSql(metermgrForm, deptStr));
		List list = (List) map.get("result");
		request.setAttribute("metermgrList", list);
		request.setAttribute("metermgrPriv", "metermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("searchList");
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
	public String getSearchSql(MetermgrForm metermgrForm, String deptStr) {
		String sql = "";//"from CarMgr carMgr  where ";
		// 代维公司
		if (metermgrForm.getMaintenUnitId() != null&& !"".equals(metermgrForm.getMaintenUnitId())) {
			sql += " and maintenUnitId like '%"+metermgrForm.getMaintenUnitId()+ "%'";
		}
		//仪表名称
		if (metermgrForm.getMeterName() != null&& !"".equals(metermgrForm.getMeterName())) {
			sql += " and meterName like '%"+metermgrForm.getMeterName()+ "%'";
		}
		// 仪表使用分类（字典）
		if (metermgrForm.getMeterSort() != null&& !"".equals(metermgrForm.getMeterSort())) {
			sql += " and meterSort like '%"+metermgrForm.getMeterSort()+ "%'";
		}
		// 仪表使用分类（字典）
		if (metermgrForm.getMeterNumber() != null&& !"".equals(metermgrForm.getMeterNumber())) {
			sql += " and meterNumber like '%"+metermgrForm.getMeterNumber()+ "%'";
		}
		// 仪表使用分类（字典）
		if (metermgrForm.getUseSummarize() != null&& !"".equals(metermgrForm.getUseSummarize())) {
			sql += " and useSummarize like '%"+metermgrForm.getUseSummarize()+ "%'";
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
	/**
	 * 返回查询语句的sql, 查询 代维单位，确保该专业不出想重复的代维单位
	 * 
	 * @param threadForm
	 *            信息form
	 * @param deptStr
	 *            部门列表 信息form
	 * @return 返回查询语句的sql
	 */
	public String getSearchSqlNotUniquely(MetermgrForm metermgrForm,
			String deptStr) {
		String sql = "";
		if (metermgrForm.getMaintenUnitId() != null
				&& !"".equals(metermgrForm.getMaintenUnitId())) {
			sql += " and maintenUnitId like '%"
					+ metermgrForm.getMaintenUnitId() + "%'";
		}
		if (sql.length() > 2) {
			sql = sql.substring(4, sql.length());
			sql = "  where " + sql + "  order by id";
		} else {
			sql = "  order by id";
		}
		return sql;
	}

	
}
