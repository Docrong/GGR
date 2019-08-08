package com.boco.eoms.sheet.plannadjust.webapp.action;

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
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.plannadjust.service.IPlannAdjustMainManager;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:10 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannAdjustAction extends SheetAction {

    /**
     * showDrawing
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
     * showPic
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
     * showKPI
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

    public ActionForward showNumber(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("shownumber");
    }

    public ActionForward showDetail(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("showdetail");
    }

    public ActionForward searchNumber(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sendTimeStartDate = StaticMethod.nullObject2String(request.getParameter("sendTimeStartDate"));
        String sendTimeEndDate = StaticMethod.nullObject2String(request.getParameter("sendTimeEndDate"));
        String queryType = StaticMethod.nullObject2String(request.getParameter("queryType"));
        IPlannAdjustMainManager mainService = (IPlannAdjustMainManager) ApplicationContextHolder.getInstance().getBean("iPlannAdjustMainManager");
        List taskMapList = mainService.getNumber(sendTimeStartDate, sendTimeEndDate, queryType);
        request.setAttribute("queryType", queryType);
        request.setAttribute("taskList", taskMapList);
        request.setAttribute("total", new Integer(taskMapList.size()));
        request.setAttribute("pageSize", new Integer(taskMapList.size()));
        return mapping.findForward("searchnumber");
    }

    public ActionForward searchDetail(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        String sendTimeStartDate = StaticMethod.nullObject2String(request.getParameter("sendTimeStartDate"));
        String sendTimeEndDate = StaticMethod.nullObject2String(request.getParameter("sendTimeEndDate"));
        IPlannAdjustMainManager mainService = (IPlannAdjustMainManager) ApplicationContextHolder.getInstance().getBean("iPlannAdjustMainManager");
        Map map = mainService.getDetail(pageIndex, pageSize, sendTimeStartDate, sendTimeEndDate);
        List taskMapList = (List) map.get("result");
        request.setAttribute("taskList", taskMapList);
        request.setAttribute("total", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("searchdetail");
    }
}
 



