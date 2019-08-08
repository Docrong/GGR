package com.boco.eoms.sheet.equipmentunsubscribe.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;

/**
 * <p>
 * Title:设备退订流程
 * </p>
 * <p>
 * Description:设备退订流程
 * </p>
 * <p>
 * Tue Oct 09 14:24:25 GMT+08:00 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public class EquipmentUnsubscribeAction extends SheetAction {

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

    public ActionForward showDraftPage(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String sql = "select areaid keyId,areaname keyName from taw_system_area where parentareaid='18' AND areaid NOT IN ('1817','1818') order by areaid";
        List cityList = sqlMgr.getSheetAccessoriesList(sql);
        request.setAttribute("cityList", cityList);
        return super.showDraftPage(mapping, form, request, response);
    }

}
 



