
package com.boco.eoms.parter.baseinfo.carmgr.webapp.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager;
import com.boco.eoms.parter.baseinfo.carmgr.webapp.form.CarMgrForm;
import com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager;
import com.boco.eoms.parter.baseinfo.metermgr.webapp.form.MetermgrForm;
import com.boco.eoms.workbench.infopub.mgr.IForumsManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ForumsForm;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;
import com.boco.eoms.commons.system.dept.util.DeptMgrLocator;
import com.boco.eoms.commons.ui.util.JSONUtil;


/**
 * Action class to handle CRUD on a CarMgr object
 * @struts.action name="carMgrForm" path="/carMgrs" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/carMgr/carMgrTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/carMgr/carMgrForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/carMgr/carMgrList.jsp" contextRelative="true"
 */
public final class CarMgrAction extends BaseAction {
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
         return mapping.findForward("list");
    }
 	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CarMgrForm carMgrForm = (CarMgrForm) form;
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");
		String newSave = request.getParameter("newSave");
		if("newSave".equals(newSave)){
		Map map = mgr.getCarMgrs(Integer.valueOf(100), Integer.valueOf(100),  this.getSearchSqlNotUniquely(carMgrForm, ""));
			if(!map.get("total").toString().equals("0")){
				return mapping.findForward("notUniquely");
			}
		}
		CarMgr carMgr = (CarMgr) convert(carMgrForm);
		mgr.saveCarMgr(carMgr);
		// JSONUtil.print(response, "操作成功");
		String typeName = request.getParameter("typeName");
		if(typeName != null && typeName.equals("updateEdit")){
			return mapping.findForward("toUpdate");
		}
		 return mapping.findForward("list");
	}
    /**
	 * 根据模块或功能的编码，删除该对象
	 */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CarMgrForm carMgrForm = (CarMgrForm) form;

        ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");
		mgr.removeCarMgr(carMgrForm.getId());
		return mapping.findForward("list");
	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CarMgrForm carMgrForm = (CarMgrForm) form;

		if (carMgrForm.getId() != null) {
			ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");
			CarMgr carMgr = (CarMgr) convert(carMgrForm);

			mgr.saveCarMgr(carMgr);
		   //mgr.updateCarMgr(carMgr);
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
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");
		CarMgr carMgr = mgr.getCarMgr(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(carMgr);

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
		String pageIndexName = new org.displaytag.util.ParamEncoder("carMgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
			Map map = mgr.getCarMgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("carMgrPriv", "carMgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("carMgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("list");
	}
	public ActionForward toDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder("carMgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
			Map map = mgr.getCarMgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("carMgrPriv", "carMgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("carMgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("toDeleteList");
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
	public ActionForward toDeleteList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder("carMgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
			Map map = mgr.getCarMgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("carMgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("toDeleteList");
	}
	
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder("carMgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
			Map map = mgr.getCarMgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("carMgrPriv", "carMgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("carMgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("toUpdate");
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
		CarMgrForm carMgrForm = (CarMgrForm) form;
		String id = request.getParameter("id");
			if (carMgrForm.getId() != null) {
			ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
			CarMgr forums = mgr.getCarMgr(carMgrForm.getId());
			carMgrForm = (CarMgrForm) convert(forums);
			updateFormBean(mapping, request, carMgrForm);
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
		return mapping.findForward("ToSearch");
	}

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}
	
	/**
	 * 转向 修改删除R
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CarMgrForm carMgrForm = (CarMgrForm) form;
		String id = request.getParameter("id");
			if (carMgrForm.getId() != null) {
			ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
			CarMgr forums = mgr.getCarMgr(carMgrForm.getId());
			carMgrForm = (CarMgrForm) convert(forums);
			updateFormBean(mapping, request, carMgrForm);
		}
		return mapping.findForward("toEdit");
	}
	public ActionForward toUpdateEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CarMgrForm carMgrForm = (CarMgrForm) form;
		if (carMgrForm.getId() != null) {
			ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");
			CarMgr forums = mgr.getCarMgr(carMgrForm.getId());
			carMgrForm = (CarMgrForm) convert(forums);
			updateFormBean(mapping, request, carMgrForm);
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
		CarMgrForm carMgrForm = (CarMgrForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				InfopubConstants.THREAD_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		// 信息管理
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
		String deptStr = null;
		List parentDepts = null;

		Map map = mgr.getCarMgrs(pageIndex, pageSize, this.getSearchSql(carMgrForm, deptStr));
		List list = (List) map.get("result");
		request.setAttribute("carMgrPriv", "carMgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("carMgrList", list);
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
		// 要分类的信息
		String ids[] = request.getParameterValues("ids");
		// 未选中则失败
		// TODO 给出提示信息
		if (ids == null) {
			return mapping.findForward("fail");
		}
		ICarMgrManager mgr = (ICarMgrManager) getBean("IcarMgrManager");	
		// 删除信息列表
		mgr.removeCarMgr(ids);
	
	
		// return mapping.findForward("searchList");
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
	public String getSearchSql(CarMgrForm carMgrForm, String deptStr) {
		String sql = "";//"from CarMgr carMgr  where ";
		// 车辆名称
		if (carMgrForm.getCarName() != null&& !"".equals(carMgrForm.getCarName())) {
			sql += " and carName like '%"+carMgrForm.getCarName()+ "%'";
		}
		// 车辆类型
		if (carMgrForm.getCarTypes() != null&& !"".equals(carMgrForm.getCarTypes())) {
			sql += " and carTypes like '%"+carMgrForm.getCarTypes()+ "%'";
		}
		// 车牌号
		if (carMgrForm.getCarNum() != null&& !"".equals(carMgrForm.getCarNum())) {
			sql += " and carNum like '%"+carMgrForm.getCarNum()+ "%'";
		}
		// 代维公司
		if (carMgrForm.getParterCor() != null&& !"".equals(carMgrForm.getParterCor())) {
			sql += " and parterCor like '%"+carMgrForm.getParterCor()+ "%'";
		}
		// 截掉第一个 and
		if (sql.length() > 2) {
			sql = sql.substring(4, sql.length());
			sql = "  where " + sql;
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
	public String getSearchSqlNotUniquely(CarMgrForm carMgrForm,
			String deptStr) {
		String sql = "";
		if (carMgrForm.getParterCor() != null
				&& !"".equals(carMgrForm.getParterCor())) {
			sql += " and parterCor like '%"
					+ carMgrForm.getParterCor() + "%'";
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
