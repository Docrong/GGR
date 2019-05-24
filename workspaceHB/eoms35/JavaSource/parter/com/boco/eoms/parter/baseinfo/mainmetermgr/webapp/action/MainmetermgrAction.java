
package com.boco.eoms.parter.baseinfo.mainmetermgr.webapp.action;

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
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.lanmetermgr.service.ILanmetermgrManager;
import com.boco.eoms.parter.baseinfo.lanmetermgr.webapp.form.LanmetermgrForm;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.service.IMainmetermgrManager;
import com.boco.eoms.parter.baseinfo.mainmetermgr.webapp.form.MainmetermgrForm;
import com.boco.eoms.parter.baseinfo.metermgr.webapp.form.MetermgrForm;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;

import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a Mainmetermgr object
 * @struts.action name="mainmetermgrForm" path="/mainmetermgrs" scope="request"
 *  validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main" 
 *  path="/WEB-INF/pages/mainmetermgr/mainmetermgrTree.jsp" contextRelative="true"
 *
 * @struts.action-forward name="edit" 
 *  path="/WEB-INF/pages/mainmetermgr/mainmetermgrForm.jsp" contextRelative="true"
 *
 * @struts.action-forward name="list" 
 *  path="/WEB-INF/pages/mainmetermgr/mainmetermgrList.jsp" contextRelative="true"
 */
public final class MainmetermgrAction extends BaseAction {
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

		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

    /**
	 * ajax保存
	 */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;		
		
		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		String newSave = request.getParameter("newSave");
		if("newSave".equals(newSave)){
		Map map = mgr.getMainmetermgrs(Integer.valueOf(100), Integer.valueOf(100),  this.getSearchSqlNotUniquely(mainmetermgrForm, ""));
			if(!map.get("total").toString().equals("0")){
				return mapping.findForward("notUniquely");
			}
		}
		Mainmetermgr mainmetermgr = (Mainmetermgr) convert(mainmetermgrForm);
		mgr.saveMainmetermgr(mainmetermgr);
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

		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;

        IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		mgr.removeMainmetermgr(mainmetermgrForm.getId());
		return mapping.findForward("list");

	}
    /**
	 * ajax请求修改某节点的详细信息
	 */
    public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;

		if (mainmetermgrForm.getId() != null) {
			IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
			Mainmetermgr mainmetermgr = (Mainmetermgr) convert(mainmetermgrForm);

			mgr.saveMainmetermgr(mainmetermgr);
		   //mgr.updateMainmetermgr(mainmetermgr);
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
		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		Mainmetermgr mainmetermgr = mgr.getMainmetermgr(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(mainmetermgr);

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
		String pageIndexName = new org.displaytag.util.ParamEncoder("mainmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		Map map = mgr.getMainmetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");	
		request.setAttribute("mainmetermgrPriv", "mainmetermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("mainmetermgrList", list);		
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
		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;
		String id = request.getParameter("id");
		if (mainmetermgrForm.getId() != null) {
			IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
			Mainmetermgr forums = mgr.getMainmetermgr(mainmetermgrForm.getId());
			mainmetermgrForm = (MainmetermgrForm) convert(forums);
			updateFormBean(mapping, request, mainmetermgrForm);
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
				"mainmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		Map map = mgr.getMainmetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("mainmetermgrPrivUpdList", "mainmetermgrPrivUpdList");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("mainmetermgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("toUpdate");
	}

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}
	public ActionForward toDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder("mainmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数		
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		Map map = mgr.getMainmetermgrs(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute("mainmetermgrPriv", "mainmetermgrPriv");
		request.setAttribute("Nums", "Nums");
		request.setAttribute("mainmetermgrList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize); 
			
		return mapping.findForward("toDeleteList");
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
		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;
		String id = request.getParameter("id");
		if (mainmetermgrForm.getId() != null) {
			IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
			Mainmetermgr forums = mgr.getMainmetermgr(mainmetermgrForm.getId());
			mainmetermgrForm = (MainmetermgrForm) convert(forums);
			updateFormBean(mapping, request, mainmetermgrForm);
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
		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;
		String id = request.getParameter("uid"); //单选框
		if(id!=null){mainmetermgrForm.setId(id);}
		if (mainmetermgrForm.getId() != null) {
			IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
			Mainmetermgr forums = mgr.getMainmetermgr(mainmetermgrForm.getId());
			mainmetermgrForm = (MainmetermgrForm) convert(forums);
			updateFormBean(mapping, request, mainmetermgrForm);
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
		MainmetermgrForm mainmetermgrForm = (MainmetermgrForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder("mainmetermgrList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示条数
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		String deptStr = "";
		Map map = mgr.getMainmetermgrs(pageIndex, pageSize, this.getSearchSql(mainmetermgrForm, deptStr));
		List list = (List) map.get("result");
		request.setAttribute("mainmetermgrList", list);
		request.setAttribute("mainmetermgrPriv", "mainmetermgrPriv");
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
		IMainmetermgrManager mgr = (IMainmetermgrManager) getBean("ImainmetermgrManager");
		mgr.removeMainmetermgr(ids);	
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
	public String getSearchSql(MainmetermgrForm mainmetermgrForm, String deptStr) {
		String sql = "";//"from CarMgr carMgr  where ";
		// 代维单位
		if (mainmetermgrForm.getMaintenUnitId() != null&& !"".equals(mainmetermgrForm.getMaintenUnitId())) {
			sql += " and maintenUnitId like '%"+mainmetermgrForm.getMaintenUnitId()+ "%'";
		}

		if (mainmetermgrForm.getOTDRCou() != null&& !"".equals(mainmetermgrForm.getOTDRCou())) {
			sql += " and OTDRCou like '%"+mainmetermgrForm.getOTDRCou()+ "%'";
		}if (mainmetermgrForm.getOTDRRem() != null&& !"".equals(mainmetermgrForm.getOTDRRem())) {
			sql += " and OTDRRem like '%"+mainmetermgrForm.getOTDRRem()+ "%'";
		}if (mainmetermgrForm.getLighCableFinderCou() != null&& !"".equals(mainmetermgrForm.getLighCableFinderCou())) {
			sql += " and lighCableFinderCou like '%"+mainmetermgrForm.getLighCableFinderCou()+ "%'";
		}if (mainmetermgrForm.getLighCableFinderRem() != null&& !"".equals(mainmetermgrForm.getLighCableFinderRem())) {
			sql += " and lighCableFinderRem like '%"+mainmetermgrForm.getLighCableFinderRem()+ "%'";
		}if (mainmetermgrForm.getGroundFinderCou() != null&& !"".equals(mainmetermgrForm.getGroundFinderCou())) {
			sql += " and groundFinderCou like '%"+mainmetermgrForm.getGroundFinderCou()+ "%'";
		}if (mainmetermgrForm.getGroundFinderRem() != null&& !"".equals(mainmetermgrForm.getGroundFinderRem())) {
			sql += " and groundFinderRem like '%"+mainmetermgrForm.getGroundFinderRem()+ "%'";
		}if (mainmetermgrForm.getPortableGPSCou() != null&& !"".equals(mainmetermgrForm.getPortableGPSCou())) {
			sql += " and portableGPSCou like '%"+mainmetermgrForm.getPortableGPSCou()+ "%'";
		}if (mainmetermgrForm.getPortableGPSRem() != null&& !"".equals(mainmetermgrForm.getPortableGPSRem())) {
			sql += " and portableGPSRem like '%"+mainmetermgrForm.getPortableGPSRem()+ "%'";
		}if (mainmetermgrForm.getAutoFiberWeldCou() != null&& !"".equals(mainmetermgrForm.getAutoFiberWeldCou())) {
			sql += " and autoFiberWeldCou like '%"+mainmetermgrForm.getAutoFiberWeldCou()+ "%'";
		}if (mainmetermgrForm.getAutoFiberWeldRem() != null&& !"".equals(mainmetermgrForm.getAutoFiberWeldRem())) {
			sql += " and autoFiberWeldRem like '%"+mainmetermgrForm.getAutoFiberWeldRem()+ "%'";
		}if (mainmetermgrForm.getFiberStripCou() != null&& !"".equals(mainmetermgrForm.getFiberStripCou())) {
			sql += " and fiberStripCou like '%"+mainmetermgrForm.getFiberStripCou()+ "%'";
		}if (mainmetermgrForm.getFiberStripRem() != null&& !"".equals(mainmetermgrForm.getFiberStripRem())) {
			sql += " and fiberStripRem like '%"+mainmetermgrForm.getFiberStripRem()+ "%'";
		}if (mainmetermgrForm.getLosetubeStripCou() != null&& !"".equals(mainmetermgrForm.getLosetubeStripCou())) {
			sql += " and losetubeStripCou like '%"+mainmetermgrForm.getLosetubeStripCou()+ "%'";
		}if (mainmetermgrForm.getLosetubeStripRem() != null&& !"".equals(mainmetermgrForm.getLosetubeStripRem())) {
			sql += " and losetubeStripRem like '%"+mainmetermgrForm.getLosetubeStripRem()+ "%'";
		}if (mainmetermgrForm.getPspStripCou() != null&& !"".equals(mainmetermgrForm.getPspStripCou())) {
			sql += " and pspStripCou like '%"+mainmetermgrForm.getPspStripCou()+ "%'";
		}if (mainmetermgrForm.getPspStripRem() != null&& !"".equals(mainmetermgrForm.getPspStripRem())) {
			sql += " and pspStripRem like '%"+mainmetermgrForm.getPspStripRem()+ "%'";
		}if (mainmetermgrForm.getLighCableReameCou() != null&& !"".equals(mainmetermgrForm.getLighCableReameCou())) {
			sql += " and lighCableReameCou like '%"+mainmetermgrForm.getLighCableReameCou()+ "%'";
		}if (mainmetermgrForm.getLighCableReameRem() != null&& !"".equals(mainmetermgrForm.getLighCableReameRem())) {
			sql += " and lighCableReameRem like '%"+mainmetermgrForm.getLighCableReameRem()+ "%'";
		}if (mainmetermgrForm.getBeamTubeFindeCou() != null&& !"".equals(mainmetermgrForm.getBeamTubeFindeCou())) {
			sql += " and beamTubeFindeCou like '%"+mainmetermgrForm.getBeamTubeFindeCou()+ "%'";
		}if (mainmetermgrForm.getBeamTubeFindeRem() != null&& !"".equals(mainmetermgrForm.getBeamTubeFindeRem())) {
			sql += " and beamTubeFindeRem like '%"+mainmetermgrForm.getBeamTubeFindeRem()+ "%'";
		}if (mainmetermgrForm.getFiberAmputatCou() != null&& !"".equals(mainmetermgrForm.getFiberAmputatCou())) {
			sql += " and fiberAmputatCou like '%"+mainmetermgrForm.getFiberAmputatCou()+ "%'";
		}if (mainmetermgrForm.getFiberAmputatRem() != null&& !"".equals(mainmetermgrForm.getFiberAmputatRem())) {
			sql += " and fiberAmputatRem like '%"+mainmetermgrForm.getFiberAmputatRem()+ "%'";
		}
		if (mainmetermgrForm.getVLinkerCou() != null&& !"".equals(mainmetermgrForm.getVLinkerCou())) {
			sql += " and VLinkerCou like '%"+mainmetermgrForm.getVLinkerCou()+ "%'";
		}if (mainmetermgrForm.getVLinkerRem() != null&& !"".equals(mainmetermgrForm.getVLinkerRem())) {
			sql += " and VLinkerRem like '%"+mainmetermgrForm.getVLinkerRem()+ "%'";
		}if (mainmetermgrForm.getScavPumpCou() != null&& !"".equals(mainmetermgrForm.getScavPumpCou())) {
			sql += " and scavPumpCou like '%"+mainmetermgrForm.getScavPumpCou()+ "%'";
		}if (mainmetermgrForm.getScavPumpRem() != null&& !"".equals(mainmetermgrForm.getScavPumpRem())) {
			sql += " and scavPumpRem like '%"+mainmetermgrForm.getScavPumpRem()+ "%'";
		}if (mainmetermgrForm.getFiberRecognizerCou() != null&& !"".equals(mainmetermgrForm.getFiberRecognizerCou())) {
			sql += " and fiberRecognizerCou like '%"+mainmetermgrForm.getFiberRecognizerCou()+ "%'";
		}if (mainmetermgrForm.getFiberRecognizerRem() != null&& !"".equals(mainmetermgrForm.getFiberRecognizerRem())) {
			sql += " and fiberRecognizerRem like '%"+mainmetermgrForm.getFiberRecognizerRem()+ "%'";
		}if (mainmetermgrForm.getMechSpecToolCou() != null&& !"".equals(mainmetermgrForm.getMechSpecToolCou())) {
			sql += " and mechSpecToolCou like '%"+mainmetermgrForm.getMechSpecToolCou()+ "%'";
		}if (mainmetermgrForm.getMechSpecToolRem() != null&& !"".equals(mainmetermgrForm.getMechSpecToolRem())) {
			sql += " and mechSpecToolRem like '%"+mainmetermgrForm.getMechSpecToolRem()+ "%'";
		}if (mainmetermgrForm.getDynamoCou() != null&& !"".equals(mainmetermgrForm.getDynamoCou())) {
			sql += " and dynamoCou like '%"+mainmetermgrForm.getDynamoCou()+ "%'";
		}if (mainmetermgrForm.getDynamoRem() != null&& !"".equals(mainmetermgrForm.getDynamoRem())) {
			sql += " and dynamoRem like '%"+mainmetermgrForm.getDynamoRem()+ "%'";
		}if (mainmetermgrForm.getMainterCarCou() != null&& !"".equals(mainmetermgrForm.getMainterCarCou())) {
			sql += " and mainterCarCou like '%"+mainmetermgrForm.getMainterCarCou()+ "%'";
		}if (mainmetermgrForm.getMainterCarRem() != null&& !"".equals(mainmetermgrForm.getMainterCarRem())) {
			sql += " and mainterCarRem like '%"+mainmetermgrForm.getMainterCarRem()+ "%'";
		}if (mainmetermgrForm.getWaterPumpCou() != null&& !"".equals(mainmetermgrForm.getWaterPumpCou())) {
			sql += " and waterPumpCou like '%"+mainmetermgrForm.getWaterPumpCou()+ "%'";
		}if (mainmetermgrForm.getWaterPumpRem() != null&& !"".equals(mainmetermgrForm.getWaterPumpRem())) {
			sql += " and waterPumpRem like '%"+mainmetermgrForm.getWaterPumpRem()+ "%'";
		}if (mainmetermgrForm.getMobileGpsCou() != null&& !"".equals(mainmetermgrForm.getMobileGpsCou())) {
			sql += " and mobileGpsCou like '%"+mainmetermgrForm.getMobileGpsCou()+ "%'";
		}if (mainmetermgrForm.getMobileGpsRem() != null&& !"".equals(mainmetermgrForm.getMobileGpsRem())) {
			sql += " and mobileGpsRem like '%"+mainmetermgrForm.getMobileGpsRem()+ "%'";
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
	public String getSearchSqlNotUniquely(MainmetermgrForm mainmetermgrForm,
			String deptStr) {
		String sql = "";
		if (mainmetermgrForm.getMaintenUnitId() != null
				&& !"".equals(mainmetermgrForm.getMaintenUnitId())) {
			sql += " and maintenUnitId like '%"
					+ mainmetermgrForm.getMaintenUnitId() + "%'";
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
