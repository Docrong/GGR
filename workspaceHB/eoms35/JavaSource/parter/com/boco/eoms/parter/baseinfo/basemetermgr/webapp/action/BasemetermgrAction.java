
package com.boco.eoms.parter.baseinfo.basemetermgr.webapp.action;

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
import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;
import com.boco.eoms.parter.baseinfo.basemetermgr.service.IBasemetermgrManager;
import com.boco.eoms.parter.baseinfo.basemetermgr.webapp.form.BasemetermgrForm;
import com.boco.eoms.parter.baseinfo.metermgr.webapp.form.MetermgrForm;

import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a Basemetermgr object
 *
 * @struts.action name="basemetermgrForm" path="/basemetermgrs" scope="request"
 * validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="main"
 * path="/WEB-INF/pages/basemetermgr/basemetermgrTree.jsp" contextRelative="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/basemetermgr/basemetermgrForm.jsp" contextRelative="true"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/basemetermgr/basemetermgrList.jsp" contextRelative="true"
 */
public final class BasemetermgrAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        //return mapping.findForward("search");
        return null;
    }

    public ActionForward main(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
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

        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        JSONArray json = mgr.xGetChildNodes(nodeId);

        JSONUtil.print(response, json.toString());
    }

    /**
     * ajax保存
     */
    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;

        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        String newSave = request.getParameter("newSave");
        if ("newSave".equals(newSave)) {
            Map map = mgr.getBasemetermgrs(Integer.valueOf(100), Integer.valueOf(100), this.getSearchSqlNotUniquely(basemetermgrForm, ""));
            if (!map.get("total").toString().equals("0")) {
                return mapping.findForward("notUniquely");
            }
        }
        Basemetermgr basemetermgr = (Basemetermgr) convert(basemetermgrForm);
        mgr.saveBasemetermgr(basemetermgr);
        // JSONUtil.print(response, "操作成功");
        String typeName = request.getParameter("typeName");
        if (typeName != null && typeName.equals("updateEdit")) {
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

        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;

        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        mgr.removeBasemetermgr(basemetermgrForm.getId());
        return mapping.findForward("success");
    }

    /**
     * ajax请求修改某节点的详细信息
     */
    public String xedit(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;

        if (basemetermgrForm.getId() != null) {
            IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
            Basemetermgr basemetermgr = (Basemetermgr) convert(basemetermgrForm);

            mgr.saveBasemetermgr(basemetermgr);
            //mgr.updateBasemetermgr(basemetermgr);
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
        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        Basemetermgr basemetermgr = mgr.getBasemetermgr(_strId);

        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(basemetermgr);

        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 显示某版块下的信息信息列表	 *
     *
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
        String pageIndexName = new org.displaytag.util.ParamEncoder("basemetermgrList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 每页显示条数
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        Map map = mgr.getBasemetermgrs(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute("basemetermgrPriv", "basemetermgrPriv");
        request.setAttribute("Nums", "Nums");
        request.setAttribute("basemetermgrList", list);
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
        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;
        String id = request.getParameter("id");
        if (basemetermgrForm.getId() != null) {
            IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
            Basemetermgr forums = mgr.getBasemetermgr(basemetermgrForm.getId());
            basemetermgrForm = (BasemetermgrForm) convert(forums);
            updateFormBean(mapping, request, basemetermgrForm);
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
                "basemetermgrList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 每页显示条数
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        Map map = mgr.getBasemetermgrs(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute("basemetermgrPrivUpdList", "basemetermgrPrivUpdList");
        request.setAttribute("Nums", "Nums");
        request.setAttribute("basemetermgrList", list);
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
        String pageIndexName = new org.displaytag.util.ParamEncoder("basemetermgrList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 每页显示条数
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        Map map = mgr.getBasemetermgrs(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute("basemetermgrPriv", "basemetermgrPriv");
        request.setAttribute("Nums", "Nums");
        request.setAttribute("basemetermgrList", list);
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
        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;
        String id = request.getParameter("id");
        if (basemetermgrForm.getId() != null) {
            IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
            Basemetermgr forums = mgr.getBasemetermgr(basemetermgrForm.getId());
            basemetermgrForm = (BasemetermgrForm) convert(forums);
            updateFormBean(mapping, request, basemetermgrForm);
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
        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;
        String id = request.getParameter("uid"); //单选框
        if (id != null) {
            basemetermgrForm.setId(id);
        }
        if (basemetermgrForm.getId() != null) {
            IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
            Basemetermgr forums = mgr.getBasemetermgr(basemetermgrForm.getId());
            basemetermgrForm = (BasemetermgrForm) convert(forums);
            updateFormBean(mapping, request, basemetermgrForm);
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
        BasemetermgrForm basemetermgrForm = (BasemetermgrForm) form;
        String pageIndexName = new org.displaytag.util.ParamEncoder("basemetermgrList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 每页显示条数
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        String deptStr = "";
        Map map = mgr.getBasemetermgrs(pageIndex, pageSize, this.getSearchSql(basemetermgrForm, deptStr));
        List list = (List) map.get("result");
        request.setAttribute("basemetermgrPriv", "basemetermgrPriv");
        request.setAttribute("Nums", "Nums");
        request.setAttribute("basemetermgrList", list);
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
        IBasemetermgrManager mgr = (IBasemetermgrManager) getBean("IbasemetermgrManager");
        mgr.removeBasemetermgr(ids);
        return mapping.findForward("success");
    }

    /**
     * 返回查询语句的sql , 在此处加入需要 查询的 字段
     *
     * @param threadForm 信息form
     * @param deptStr    部门列表 信息form
     * @return 返回查询语句的sql
     */
    public String getSearchSql(BasemetermgrForm basemetermgrForm, String deptStr) {
        String sql = "";//"from CarMgr carMgr  where ";
        // 代维单位
        if (basemetermgrForm.getMaintenUnitIdVal() != null && !"".equals(basemetermgrForm.getMaintenUnitIdVal())) {
            sql += " and maintenUnitIdVal like '%" + basemetermgrForm.getMaintenUnitIdVal() + "%'";
        }
        if (basemetermgrForm.getMaintenUnitIdRem() != null && !"".equals(basemetermgrForm.getMaintenUnitIdRem())) {
            sql += " and maintenUnitIdRem like '%" + basemetermgrForm.getMaintenUnitIdRem() + "%'";
        }
        // 台式计算机（台）
        if (basemetermgrForm.getDesktopComputerVal() != null && !"".equals(basemetermgrForm.getDesktopComputerVal())) {
            sql += " and desktopComputerVal like '%" + basemetermgrForm.getDesktopComputerVal() + "%'";
        }
        if (basemetermgrForm.getDesktopComputerRem() != null && !"".equals(basemetermgrForm.getDesktopComputerRem())) {
            sql += " and desktopComputerRem like '%" + basemetermgrForm.getDesktopComputerRem() + "%'";
        }
        // 传真
        if (basemetermgrForm.getFaxVal() != null && !"".equals(basemetermgrForm.getFaxVal())) {
            sql += " and faxVal like '%" + basemetermgrForm.getFaxVal() + "%'";
        }
        if (basemetermgrForm.getFaxRem() != null && !"".equals(basemetermgrForm.getFaxRem())) {
            sql += " and faxRem like '%" + basemetermgrForm.getFaxRem() + "%'";
        }
        // 固定电话（部）
        if (basemetermgrForm.getFixedPhoneVal() != null && !"".equals(basemetermgrForm.getFixedPhoneVal())) {
            sql += " and fixedPhoneVal like '%" + basemetermgrForm.getFixedPhoneVal() + "%'";
        }
        if (basemetermgrForm.getFixedPhoneRem() != null && !"".equals(basemetermgrForm.getFixedPhoneRem())) {
            sql += " and faxRem like '%" + basemetermgrForm.getFixedPhoneRem() + "%'";
        }
        // 移动测试电话(C或G网) （部）
        if (basemetermgrForm.getMoveTestPhoneVal() != null && !"".equals(basemetermgrForm.getMoveTestPhoneVal())) {
            sql += " and moveTestPhoneVal like '%" + basemetermgrForm.getMoveTestPhoneVal() + "%'";
        }
        if (basemetermgrForm.getMoveTestPhoneRem() != null && !"".equals(basemetermgrForm.getMoveTestPhoneRem())) {
            sql += " and moveTestPhoneRem like '%" + basemetermgrForm.getMoveTestPhoneRem() + "%'";
        }
        // 天馈线测试仪（台）
        if (basemetermgrForm.getLineTesterVal() != null && !"".equals(basemetermgrForm.getLineTesterVal())) {
            sql += " and lineTesterVal like '%" + basemetermgrForm.getLineTesterVal() + "%'";
        }
        if (basemetermgrForm.getLineTesterRem() != null && !"".equals(basemetermgrForm.getLineTesterRem())) {
            sql += " and lineTesterRem like '%" + basemetermgrForm.getLineTesterRem() + "%'";
        }
        // 地阻测试仪（台）
        if (basemetermgrForm.getTerraBlockTesterVal() != null && !"".equals(basemetermgrForm.getTerraBlockTesterVal())) {
            sql += " and terraBlockTesterVal like '%" + basemetermgrForm.getTerraBlockTesterVal() + "%'";
        }
        if (basemetermgrForm.getTerraBlockTesterRem() != null && !"".equals(basemetermgrForm.getTerraBlockTesterRem())) {
            sql += " and terraBlockTesterRem like '%" + basemetermgrForm.getTerraBlockTesterRem() + "%'";
        }
        // 电池容量测试仪（台）
        if (basemetermgrForm.getCellTesterVal() != null && !"".equals(basemetermgrForm.getCellTesterVal())) {
            sql += " and cellTesterVal like '%" + basemetermgrForm.getCellTesterVal() + "%'";
        }
        if (basemetermgrForm.getCellTesterRem() != null && !"".equals(basemetermgrForm.getCellTesterRem())) {
            sql += " and cellTesterRem like '%" + basemetermgrForm.getCellTesterRem() + "%'";
        }
        // 日常维护工具（套）
        if (basemetermgrForm.getMaintenanceToolsVal() != null && !"".equals(basemetermgrForm.getMaintenanceToolsVal())) {
            sql += " and maintenanceToolsVal like '%" + basemetermgrForm.getMaintenanceToolsVal() + "%'";
        }
        if (basemetermgrForm.getMaintenanceToolsRem() != null && !"".equals(basemetermgrForm.getMaintenanceToolsRem())) {
            sql += " and maintenanceToolsRem like '%" + basemetermgrForm.getMaintenanceToolsRem() + "%'";
        }
        // 维护车辆（台）
        if (basemetermgrForm.getMaintenanceCarsVal() != null && !"".equals(basemetermgrForm.getMaintenanceCarsVal())) {
            sql += " and maintenanceCarsVal like '%" + basemetermgrForm.getMaintenanceCarsVal() + "%'";
        }
        if (basemetermgrForm.getMaintenanceCarsRem() != null && !"".equals(basemetermgrForm.getMaintenanceCarsRem())) {
            sql += " and maintenanceCarsRem like '%" + basemetermgrForm.getMaintenanceCarsRem() + "%'";
        }
        // 万用表（台）
        if (basemetermgrForm.getMultimeterVal() != null && !"".equals(basemetermgrForm.getMultimeterVal())) {
            sql += " and multimeterVal like '%" + basemetermgrForm.getMultimeterVal() + "%'";
        }
        if (basemetermgrForm.getMultimeterRem() != null && !"".equals(basemetermgrForm.getMultimeterRem())) {
            sql += " and multimeterRem like '%" + basemetermgrForm.getMultimeterRem() + "%'";
        }
        // 交直流钳型表（台）
        if (basemetermgrForm.getACDCclampMeterVal() != null && !"".equals(basemetermgrForm.getACDCclampMeterVal())) {
            sql += " and ACDCclampMeterVal like '%" + basemetermgrForm.getACDCclampMeterVal() + "%'";
        }
        if (basemetermgrForm.getACDCclampMeterRem() != null && !"".equals(basemetermgrForm.getACDCclampMeterRem())) {
            sql += " and ACDCclampMeterRem like '%" + basemetermgrForm.getACDCclampMeterRem() + "%'";
        }
        // 2M误码仪（台）
        if (basemetermgrForm.getTwoMInstrumentVal() != null && !"".equals(basemetermgrForm.getTwoMInstrumentVal())) {
            sql += " and twoMInstrumentVal like '%" + basemetermgrForm.getTwoMInstrumentVal() + "%'";
        }
        if (basemetermgrForm.getTwoMInstrumentRem() != null && !"".equals(basemetermgrForm.getTwoMInstrumentRem())) {
            sql += " and twoMInstrumentRem like '%" + basemetermgrForm.getTwoMInstrumentRem() + "%'";
        }
        // 天线倾角测量仪（台）
        if (basemetermgrForm.getAntennaAngleGaugeVal() != null && !"".equals(basemetermgrForm.getAntennaAngleGaugeVal())) {
            sql += " and antennaAngleGaugeVal like '%" + basemetermgrForm.getAntennaAngleGaugeVal() + "%'";
        }
        if (basemetermgrForm.getAntennaAngleGaugeRem() != null && !"".equals(basemetermgrForm.getAntennaAngleGaugeRem())) {
            sql += " and antennaAngleGaugeRem like '%" + basemetermgrForm.getAntennaAngleGaugeRem() + "%'";
        }
        // 望远镜（台）
        if (basemetermgrForm.getTelescopeVal() != null && !"".equals(basemetermgrForm.getTelescopeVal())) {
            sql += " and telescopeVal like '%" + basemetermgrForm.getTelescopeVal() + "%'";
        }
        if (basemetermgrForm.getTelescopeRem() != null && !"".equals(basemetermgrForm.getTelescopeRem())) {
            sql += " and telescopeRem like '%" + basemetermgrForm.getTelescopeRem() + "%'";
        }
        // 数码相机（台）
        if (basemetermgrForm.getDigitalCameraVal() != null && !"".equals(basemetermgrForm.getDigitalCameraVal())) {
            sql += " and digitalCameraVal like '%" + basemetermgrForm.getDigitalCameraVal() + "%'";
        }
        if (basemetermgrForm.getDigitalCameraRem() != null && !"".equals(basemetermgrForm.getDigitalCameraRem())) {
            sql += " and digitalCameraRem like '%" + basemetermgrForm.getDigitalCameraRem() + "%'";
        }
        // 水平仪（台）
        if (basemetermgrForm.getGradienterVal() != null && !"".equals(basemetermgrForm.getGradienterVal())) {
            sql += " and gradienterVal like '%" + basemetermgrForm.getGradienterVal() + "%'";
        }
        if (basemetermgrForm.getGradienterRem() != null && !"".equals(basemetermgrForm.getGradienterRem())) {
            sql += " and gradienterRem like '%" + basemetermgrForm.getGradienterRem() + "%'";
        }
        // GPS定位仪（台）
        if (basemetermgrForm.getGPSLocatorVal() != null && !"".equals(basemetermgrForm.getGPSLocatorVal())) {
            sql += " and GPSLocatorVal like '%" + basemetermgrForm.getGPSLocatorVal() + "%'";
        }
        if (basemetermgrForm.getGPSLocatorRem() != null && !"".equals(basemetermgrForm.getGPSLocatorRem())) {
            sql += " and GPSLocatorRem like '%" + basemetermgrForm.getGPSLocatorRem() + "%'";
        }
        // 红外测温仪（台）
        if (basemetermgrForm.getInfraredThermoscopeVal() != null && !"".equals(basemetermgrForm.getInfraredThermoscopeVal())) {
            sql += " and infraredThermoscopeVal like '%" + basemetermgrForm.getInfraredThermoscopeVal() + "%'";
        }
        if (basemetermgrForm.getInfraredThermoscopeRem() != null && !"".equals(basemetermgrForm.getInfraredThermoscopeRem())) {
            sql += " and infraredThermoscopeRem like '%" + basemetermgrForm.getInfraredThermoscopeRem() + "%'";
        }
        // 笔记本电脑
        if (basemetermgrForm.getNotebookPCVal() != null && !"".equals(basemetermgrForm.getNotebookPCVal())) {
            sql += " and notebookPCVal like '%" + basemetermgrForm.getNotebookPCVal() + "%'";
        }
        if (basemetermgrForm.getNotebookPCRem() != null && !"".equals(basemetermgrForm.getNotebookPCRem())) {
            sql += " and notebookPCRem like '%" + basemetermgrForm.getNotebookPCRem() + "%'";
        }
        // 天眼巡检终端（定位手机）（部）
        if (basemetermgrForm.getMobileGpsVal() != null && !"".equals(basemetermgrForm.getMobileGpsVal())) {
            sql += " and mobileGpsVal like '%" + basemetermgrForm.getMobileGpsVal() + "%'";
        }
        if (basemetermgrForm.getMobileGps() != null && !"".equals(basemetermgrForm.getMobileGps())) {
            sql += " and mobileGps like '%" + basemetermgrForm.getMobileGps() + "%'";
        }

        // 备注
        if (basemetermgrForm.getRemark() != null && !"".equals(basemetermgrForm.getRemark())) {
            sql += " and remark like '%" + basemetermgrForm.getRemark() + "%'";
        }

        // 截掉第一个 and
        if (sql.length() > 2) {
            sql = sql.substring(4, sql.length());
            sql = "  where " + sql + "  order by id";
        } else {
            sql = "  order by id";
        }
        return sql;
    }

    /**
     * 返回查询语句的sql, 查询 代维单位，确保该专业不出想重复的代维单位
     *
     * @param threadForm 信息form
     * @param deptStr    部门列表 信息form
     * @return 返回查询语句的sql
     */
    public String getSearchSqlNotUniquely(BasemetermgrForm basemetermgrForm,
                                          String deptStr) {
        String sql = "";
        if (basemetermgrForm.getMaintenUnitIdVal() != null
                && !"".equals(basemetermgrForm.getMaintenUnitIdVal())) {
            sql += " and maintenUnitIdVal like '%"
                    + basemetermgrForm.getMaintenUnitIdVal() + "%'";
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
