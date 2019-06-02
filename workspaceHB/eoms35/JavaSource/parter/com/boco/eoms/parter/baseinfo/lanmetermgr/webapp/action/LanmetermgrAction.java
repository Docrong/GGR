
package com.boco.eoms.parter.baseinfo.lanmetermgr.webapp.action;

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
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.service.ICarMgrManager;
import com.boco.eoms.parter.baseinfo.carmgr.webapp.form.CarMgrForm;
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager;
import com.boco.eoms.parter.baseinfo.lanmetermgr.webapp.form.LanmetermgrForm;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager;
import com.boco.eoms.parter.baseinfo.mainmetermgr.webapp.form.MainmetermgrForm;
import com.boco.eoms.parter.baseinfo.metermgr.webapp.form.MetermgrForm;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;

import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a Lanmetermgr object
 * @struts.action name="lanmetermgrForm" path="/lanmetermgrs" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/lanmetermgr/lanmetermgrTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/lanmetermgr/lanmetermgrForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/lanmetermgr/lanmetermgrList.jsp" contextRelative="true"
 */
public final class LanmetermgrAction extends BaseAction {
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

		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;

		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		String newSave = request.getParameter("newSave");
		if("newSave".equals(newSave)){
		Map map = mgr.getLanmetermgrs(Integer.valueOf(100), Integer.valueOf(100),  this.getSearchSqlNotUniquely(lanmetermgrForm, ""));
			if(!map.get("total").toString().equals("0")){
				return mapping.findForward("notUniquely");
			}
		}
		Lanmetermgr lanmetermgr = (Lanmetermgr) convert(lanmetermgrForm);
		mgr.saveLanmetermgr(lanmetermgr);
		String typeName = request.getParameter("typeName");
		if(typeName != null && typeName.equals("updateEdit")){
			return mapping.findForward("success");
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

		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;

        ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		mgr.removeLanmetermgr(lanmetermgrForm.getId());
		return mapping.findForward("list");
	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;

		if (lanmetermgrForm.getId() != null) {
			ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
			Lanmetermgr lanmetermgr = (Lanmetermgr) convert(lanmetermgrForm);

			mgr.saveLanmetermgr(lanmetermgr);
		   //mgr.updateLanmetermgr(lanmetermgr);
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
		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		Lanmetermgr lanmetermgr = mgr.getLanmetermgr(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(lanmetermgr);

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
		String pageIndexName = new org.displaytag.util.ParamEncoder("lanmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		Map map = mgr.getLanmetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("lanmetermgrPriv", "lanmetermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("lanmetermgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("list");
	}
	
	public ActionForward toDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"lanmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		Map map = mgr.getLanmetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("lanmetermgrPriv", "lanmetermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("lanmetermgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		return mapping.findForward("toDeleteList");
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
		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;
		String id = request.getParameter("id");
			if (lanmetermgrForm.getId() != null) {
			ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
			Lanmetermgr forums = mgr.getLanmetermgr(lanmetermgrForm.getId());
			lanmetermgrForm = (LanmetermgrForm) convert(forums);
			updateFormBean(mapping, request, lanmetermgrForm);
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
	 * 转向 修改删除
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
		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;
		String id = request.getParameter("id");
			if (lanmetermgrForm.getId() != null) {
			ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
			Lanmetermgr forums = mgr.getLanmetermgr(lanmetermgrForm.getId());
			lanmetermgrForm = (LanmetermgrForm) convert(forums);
			updateFormBean(mapping, request, lanmetermgrForm);
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
		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;
		String id = request.getParameter("uid");
		if(id!=null){lanmetermgrForm.setId(id);}
		if (lanmetermgrForm.getId() != null) {
			ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
			Lanmetermgr forums = mgr.getLanmetermgr(lanmetermgrForm.getId());
			lanmetermgrForm = (LanmetermgrForm) convert(forums);
			updateFormBean(mapping, request, lanmetermgrForm);
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
		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder("lanmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		// 信息管理
		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		String deptStr = null;
		Map map = mgr.getLanmetermgrs(pageIndex, pageSize, this.getSearchSql(lanmetermgrForm, deptStr));
		List list = (List) map.get("result");
		request.setAttribute("lanmetermgrList", list);
		request.setAttribute("lanmetermgrPriv", "lanmetermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		return mapping.findForward("searchList");
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
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LanmetermgrForm lanmetermgrForm = (LanmetermgrForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder("lanmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		// 信息管理
		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		String deptStr = null;
		Map map = mgr.getLanmetermgrs(pageIndex, pageSize, this.getSearchSql(lanmetermgrForm, deptStr));
		List list = (List) map.get("result");
		request.setAttribute("lanmetermgrList", list);
		request.setAttribute("Nums", "Nums");
		request.setAttribute("uid", "uid");
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);

		return mapping.findForward("toUpdate");
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
		ILanmetermgrManager mgr = (ILanmetermgrManager) getBean("IlanmetermgrManager");
		// 删除信息列表
		mgr.removeLanmetermgr(ids);
	
	
		// return mapping.findForward("searchList");
		return mapping.findForward("success");
	}
	/**
	 * 
	 */
	/**
	 * 返回查询语句的sql
	 * 
	 * @param threadForm
	 *            信息form
	 * @param deptStr
	 *            部门列表 信息form
	 * @return 返回查询语句的sql
	 */
	public String getSearchSql(LanmetermgrForm lanmetermgrForm, String deptStr) {
		String sql = "";//"from CarMgr carMgr  where ";
		// 车辆名称
		if (lanmetermgrForm.getMaintenUnitId() != null&& !"".equals(lanmetermgrForm.getMaintenUnitId())) {
			sql += " and maintenUnitId like '%"+lanmetermgrForm.getMaintenUnitId()+ "%'";
		}
		if (lanmetermgrForm.getOTDRCou() != null&& !"".equals(lanmetermgrForm.getOTDRCou())) {
			sql += " and OTDRCou like '%"+lanmetermgrForm.getOTDRCou()+ "%'";
		}
		if (lanmetermgrForm.getOTDRRem() != null&& !"".equals(lanmetermgrForm.getOTDRRem())) {
			sql += " and OTDRRem like '%"+lanmetermgrForm.getOTDRRem()+ "%'";
		}
		if (lanmetermgrForm.getLighCableFinderCou() != null&& !"".equals(lanmetermgrForm.getLighCableFinderCou())) {
			sql += " and lighCableFinderCou like '%"+lanmetermgrForm.getLighCableFinderCou()+ "%'";
		}if (lanmetermgrForm.getLighCableFinderRem() != null&& !"".equals(lanmetermgrForm.getLighCableFinderRem())) {
			sql += " and lighCableFinderRem like '%"+lanmetermgrForm.getLighCableFinderRem()+ "%'";
		}if (lanmetermgrForm.getGroundFinderCou() != null&& !"".equals(lanmetermgrForm.getGroundFinderCou())) {
			sql += " and groundFinderCou like '%"+lanmetermgrForm.getGroundFinderCou()+ "%'";
		}if (lanmetermgrForm.getGroundFinderRem() != null&& !"".equals(lanmetermgrForm.getGroundFinderRem())) {
			sql += " and groundFinderRem like '%"+lanmetermgrForm.getGroundFinderRem()+ "%'";
		}if (lanmetermgrForm.getPortableGPSCou() != null&& !"".equals(lanmetermgrForm.getPortableGPSCou())) {
			sql += " and portableGPSCou like '%"+lanmetermgrForm.getPortableGPSCou()+ "%'";
		}if (lanmetermgrForm.getPortableGPSRem() != null&& !"".equals(lanmetermgrForm.getPortableGPSRem())) {
			sql += " and portableGPSRem like '%"+lanmetermgrForm.getPortableGPSRem()+ "%'";
		}if (lanmetermgrForm.getAutoFiberWeldCou() != null&& !"".equals(lanmetermgrForm.getAutoFiberWeldCou())) {
			sql += " and autoFiberWeldCou like '%"+lanmetermgrForm.getAutoFiberWeldCou()+ "%'";
		}if (lanmetermgrForm.getAutoFiberWeldRem() != null&& !"".equals(lanmetermgrForm.getAutoFiberWeldRem())) {
			sql += " and autoFiberWeldRem like '%"+lanmetermgrForm.getAutoFiberWeldRem()+ "%'";
		}if (lanmetermgrForm.getFiberStripCou() != null&& !"".equals(lanmetermgrForm.getFiberStripCou())) {
			sql += " and fiberStripCou like '%"+lanmetermgrForm.getFiberStripCou()+ "%'";
		}if (lanmetermgrForm.getFiberStripRem() != null&& !"".equals(lanmetermgrForm.getFiberStripRem())) {
			sql += " and fiberStripRem like '%"+lanmetermgrForm.getFiberStripRem()+ "%'";
		}if (lanmetermgrForm.getLosetubeStripCou() != null&& !"".equals(lanmetermgrForm.getLosetubeStripCou())) {
			sql += " and losetubeStripCou like '%"+lanmetermgrForm.getLosetubeStripCou()+ "%'";
		}if (lanmetermgrForm.getLosetubeStripRem() != null&& !"".equals(lanmetermgrForm.getLosetubeStripRem())) {
			sql += " and losetubeStripRem like '%"+lanmetermgrForm.getLosetubeStripRem()+ "%'";
		}if (lanmetermgrForm.getPspStripCou() != null&& !"".equals(lanmetermgrForm.getPspStripCou())) {
			sql += " and pspStripCou like '%"+lanmetermgrForm.getPspStripCou()+ "%'";
		}if (lanmetermgrForm.getPspStripRem() != null&& !"".equals(lanmetermgrForm.getPspStripRem())) {
			sql += " and pspStripRem like '%"+lanmetermgrForm.getPspStripRem()+ "%'";
		}if (lanmetermgrForm.getLighCableReameCou() != null&& !"".equals(lanmetermgrForm.getLighCableReameCou())) {
			sql += " and lighCableReameCou like '%"+lanmetermgrForm.getLighCableReameCou()+ "%'";
		}if (lanmetermgrForm.getLighCableReameRem() != null&& !"".equals(lanmetermgrForm.getLighCableReameRem())) {
			sql += " and lighCableReameRem like '%"+lanmetermgrForm.getLighCableReameRem()+ "%'";
		}if (lanmetermgrForm.getBeamTubeFindeCou() != null&& !"".equals(lanmetermgrForm.getBeamTubeFindeCou())) {
			sql += " and beamTubeFindeCou like '%"+lanmetermgrForm.getBeamTubeFindeCou()+ "%'";
		}if (lanmetermgrForm.getBeamTubeFindeRem() != null&& !"".equals(lanmetermgrForm.getBeamTubeFindeRem())) {
			sql += " and beamTubeFindeRem like '%"+lanmetermgrForm.getBeamTubeFindeRem()+ "%'";
		}if (lanmetermgrForm.getFiberAmputatCou() != null&& !"".equals(lanmetermgrForm.getFiberAmputatCou())) {
			sql += " and fiberAmputatCou like '%"+lanmetermgrForm.getFiberAmputatCou()+ "%'";
		}if (lanmetermgrForm.getFiberAmputatRem() != null&& !"".equals(lanmetermgrForm.getFiberAmputatRem())) {
			sql += " and fiberAmputatRem like '%"+lanmetermgrForm.getFiberAmputatRem()+ "%'";
		}if (lanmetermgrForm.getVLinkerCou() != null&& !"".equals(lanmetermgrForm.getVLinkerCou())) {
			sql += " and VLinkerCou like '%"+lanmetermgrForm.getVLinkerCou()+ "%'";
		}if (lanmetermgrForm.getVLinkerRem() != null&& !"".equals(lanmetermgrForm.getVLinkerRem())) {
			sql += " and VLinkerRem like '%"+lanmetermgrForm.getVLinkerRem()+ "%'";
		}if (lanmetermgrForm.getScavPumpCou() != null&& !"".equals(lanmetermgrForm.getScavPumpCou())) {
			sql += " and scavPumpCou like '%"+lanmetermgrForm.getScavPumpCou()+ "%'";
		}if (lanmetermgrForm.getScavPumpRem() != null&& !"".equals(lanmetermgrForm.getScavPumpRem())) {
			sql += " and scavPumpRem like '%"+lanmetermgrForm.getScavPumpRem()+ "%'";
		}if (lanmetermgrForm.getFiberRecognizerCou() != null&& !"".equals(lanmetermgrForm.getFiberRecognizerCou())) {
			sql += " and fiberRecognizerCou like '%"+lanmetermgrForm.getFiberRecognizerCou()+ "%'";
		}if (lanmetermgrForm.getFiberRecognizerRem() != null&& !"".equals(lanmetermgrForm.getFiberRecognizerRem())) {
			sql += " and fiberRecognizerRem like '%"+lanmetermgrForm.getFiberRecognizerRem()+ "%'";
		}if (lanmetermgrForm.getMechSpecToolCou() != null&& !"".equals(lanmetermgrForm.getMechSpecToolCou())) {
			sql += " and mechSpecToolCou like '%"+lanmetermgrForm.getMechSpecToolCou()+ "%'";
		}if (lanmetermgrForm.getMechSpecToolRem() != null&& !"".equals(lanmetermgrForm.getMechSpecToolRem())) {
			sql += " and mechSpecToolRem like '%"+lanmetermgrForm.getMechSpecToolRem()+ "%'";
		}if (lanmetermgrForm.getDynamoCou() != null&& !"".equals(lanmetermgrForm.getDynamoCou())) {
			sql += " and dynamoCou like '%"+lanmetermgrForm.getDynamoCou()+ "%'";
		}if (lanmetermgrForm.getDynamoRem() != null&& !"".equals(lanmetermgrForm.getDynamoRem())) {
			sql += " and dynamoRem like '%"+lanmetermgrForm.getDynamoRem()+ "%'";
		}if (lanmetermgrForm.getMainterCarCou() != null&& !"".equals(lanmetermgrForm.getMainterCarCou())) {
			sql += " and mainterCarCou like '%"+lanmetermgrForm.getMainterCarCou()+ "%'";
		}if (lanmetermgrForm.getMainterCarRem() != null&& !"".equals(lanmetermgrForm.getMainterCarRem())) {
			sql += " and mainterCarRem like '%"+lanmetermgrForm.getMainterCarRem()+ "%'";
		}if (lanmetermgrForm.getWaterPumpCou() != null&& !"".equals(lanmetermgrForm.getWaterPumpCou())) {
			sql += " and waterPumpCou like '%"+lanmetermgrForm.getWaterPumpCou()+ "%'";
		}if (lanmetermgrForm.getWaterPumpRem() != null&& !"".equals(lanmetermgrForm.getWaterPumpRem())) {
			sql += " and waterPumpRem like '%"+lanmetermgrForm.getWaterPumpRem()+ "%'";
		}if (lanmetermgrForm.getMobileGpsCou() != null&& !"".equals(lanmetermgrForm.getMobileGpsCou())) {
			sql += " and mobileGpsCou like '%"+lanmetermgrForm.getMobileGpsCou()+ "%'";
		}if (lanmetermgrForm.getMobileGpsRem() != null&& !"".equals(lanmetermgrForm.getMobileGpsRem())) {
			sql += " and mobileGpsRem like '%"+lanmetermgrForm.getMobileGpsRem()+ "%'";
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
	public String getSearchSqlNotUniquely(LanmetermgrForm lanmetermgrForm,
			String deptStr) {
		String sql = "";
		if (lanmetermgrForm.getMaintenUnitId() != null
				&& !"".equals(lanmetermgrForm.getMaintenUnitId())) {
			sql += " and maintenUnitId like '%"
					+ lanmetermgrForm.getMaintenUnitId() + "%'";
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
