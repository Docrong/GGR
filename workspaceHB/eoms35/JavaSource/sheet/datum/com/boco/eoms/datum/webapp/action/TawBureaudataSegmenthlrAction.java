package com.boco.eoms.datum.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;

public class TawBureaudataSegmenthlrAction extends BaseAction {


    /***
     * 查询号段归属HLR初始化
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
//		获取所有地市
        ITawBureaudataHlrDAOManager mgr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataHlrDAOManager");
        List list = mgr.getBureaudatedept();
        request.setAttribute("BureaudataHlr", list);
        return mapping.findForward("search");
    }

    /***
     * 查询号段归属HLR查询
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchdo(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String hlrsignalid = StaticMethod.nullObject2String(request
                .getParameter("hlrsignalid"), "");
        String belong = StaticMethod.nullObject2String(request
                .getParameter("belong"), "");
        int beginSegment = StaticMethod.nullObject2int(request
                .getParameter("beginsegment"), 0);
        int endSegment = StaticMethod.nullObject2int(request
                .getParameter("endsegment"), 0);
        String cityId = StaticMethod.nullObject2String(request
                .getParameter("cityId"), "");
        int belongflag = 0;
        if (belong.equals("1")) {
            belongflag = 1;
        }
        ITawBureaudataBasicDAOManager mgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        String where = " and  hlr.hlrsignalid like '%" + hlrsignalid
                + "%' and b.belongflag = " + belongflag
                + " and c.zonenum like '%" + cityId + "%'";
        if (beginSegment > endSegment) {
            if (beginSegment != 0) {
                where += " and b.segmentid <= " + beginSegment;
            }
            if (endSegment != 0) {
                where += "  and b.segmentid >=" + endSegment;
            }

        } else {
            if (beginSegment != 0) {
                where += " and b.segmentid >= " + beginSegment;
            }
            if (endSegment != 0) {
                where += "  and b.segmentid <=" + endSegment;
            }
        }


        List list = mgr.getBureaudataHlrAreaList(where);
        request.setAttribute("bureaudataHlrList", list);
        return mapping.findForward("success");
    }

}
