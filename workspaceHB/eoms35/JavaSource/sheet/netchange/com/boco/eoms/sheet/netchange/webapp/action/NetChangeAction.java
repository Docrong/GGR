/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netchange.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.netchange.service.INetChangeMainManager;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetChangeAction extends SheetAction {

    /**
     * 显示草图
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("draw");
    }

    /**
     * 显示流程VISO图
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showPic(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("pic");
    }

    /**
     * 显示KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showKPI(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("kpi");
    }

    /**
     * 调用关系列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public ActionForward showInvokeRelationShipList(ActionMapping mapping,
                                                    ActionForm form, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        String mainId = StaticMethod.null2String(request.getParameter("id"));
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        INetChangeMainManager netChangeMainManager = (INetChangeMainManager) baseSheet.getMainService();
        List showInvokeRelationShipList = netChangeMainManager.showInvokeRelationShipList(mainId);

        //得到处理环节的工单
        BaseLink baseLink = netChangeMainManager.getHasInvokeBaseLink(mainId);
        String activeTemplateId = (baseLink == null ? "" : baseLink.getActiveTemplateId());

        request.setAttribute("proccessName", "故障处理工单");
        request.setAttribute("invokedproccessName", "紧急故障工单");
        request.setAttribute("showInvokeRelationShipList", showInvokeRelationShipList);
        request.setAttribute("activeTemplateId", activeTemplateId);
        return mapping.findForward("showInvokeRelationShipList");
    }

    /**
     * 根据专业，查询时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public void showLimit(ActionMapping mapping,
                          ActionForm form, HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        String specialty1 = StaticMethod.null2String(request.getParameter("faultSpecialty"));
        String specialty2 = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
        String specialty3 = StaticMethod.null2String(request.getParameter("faultSpecialty3"));
        String specialty4 = StaticMethod.null2String(request.getParameter("faultSpecialty4"));
        ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
        SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty1, specialty2, specialty3, specialty4);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(sheetLimit);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 根据专业，查询时限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public void showDealLimit(ActionMapping mapping,
                              ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String specialty = StaticMethod.null2String(request.getParameter("faultResponseLevel"));
        ISheetLimitManager mgr = (ISheetLimitManager) getBean("IsheetLimitManager");
        SheetLimit sheetLimit = mgr.getSheetLimitBySpecial(specialty);
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(sheetLimit);
        JSONUtil.print(response, jsonRoot.toString());
    }

}
