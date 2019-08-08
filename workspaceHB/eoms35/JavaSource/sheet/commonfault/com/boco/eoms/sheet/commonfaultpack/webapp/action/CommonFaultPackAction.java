package com.boco.eoms.sheet.commonfaultpack.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfaultpack.dao.ICommonFaultPackMainDAO;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonFaultPackAction extends SheetAction {
    /**
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
     * 新增提交
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performAdd(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        // 将数据保存到数据库，不走流程
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("mainId"));
        String mainAlarmSolveDate = StaticMethod.nullObject2String(request.getParameter("mainAlarmSolveDate"));
        CommonFaultPackMain mainObject = (CommonFaultPackMain) baseSheet
                .getMainService().getMainObject().getClass().newInstance();
        SheetBeanUtils.populate(mainObject, request.getParameterMap());
        mainObject.setMainAlarmSolveDate(mainAlarmSolveDate);
        mainObject.setMainId(sheetKey);
        mainObject.setMainAlarmState("0");
        mainObject.setMainFaultGenerantCity(StaticMethod.nullObject2String(request.getParameter("toDeptId")));
        baseSheet.getMainService().saveOrUpdateMain(mainObject);
        request.setAttribute("alarmMethod", "new");
        return mapping.findForward("showOwnStarterList");
    }

    /**
     * 显示由我启动列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showOwnStarterList(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultPackMainManager commonfaultPackMainManager = (ICommonFaultPackMainManager) baseSheet
                .getMainService();
        String alarmMethod = StaticMethod.nullObject2String(request.getParameter("alarmMethod"));
        if ("".equals(alarmMethod))
            alarmMethod = StaticMethod.nullObject2String(request.getAttribute("alarmMethod"));
        String mainId = StaticMethod.nullObject2String(request
                .getParameter("mainId"));
        if ("".equals(mainId))
            mainId = StaticMethod.nullObject2String(request
                    .getAttribute("mainId"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        System.out.println("---------show pack list  alarmMethod:" + alarmMethod + "-----------");
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();


        String pageIndexName = new org.displaytag.util.ParamEncoder("mainList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 当前页数
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        // 分页取得列表
        Integer total = commonfaultPackMainManager.getCountByMainId(mainId);
        // wps端分页取得列表

        List result = commonfaultPackMainManager.getListByMainId(pageIndex,
                new Integer(pageSize.intValue()), mainId);

        System.out.println("pack-----pageSize:" + pageSize + "--pageIndexName:" + pageIndexName + "--pageIndex:" + pageIndex + "--total:" + total + "--result:" + result.size());
        request.setAttribute("taskName", taskName);
        request.setAttribute("mainId", mainId);
        request.setAttribute("alarmMethod", alarmMethod);
        request.setAttribute("mainList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("mainlist");
    }

    /**
     * 告警信息的修改，删除
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performDeal(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        String alarmMethod = StaticMethod.nullObject2String(request
                .getParameter("alarmMethod"));
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("Id"));
        String mainId = StaticMethod.nullObject2String(request.getParameter("mainId"));
        String forward = "";
        if ("update".equals(alarmMethod)) {
            //CommonFaultPackMain mainObject = (CommonFaultPackMain) mainDAO.loadSinglePO(sheetKey);  edited by zenghankai
            CommonFaultPackMain mainObject = (CommonFaultPackMain) baseSheet.getMainService().getSingleMainPO(sheetKey);
            String replyDealStep = StaticMethod.nullObject2String(mainObject.getReplyDealStep()).trim();
            String replyFaultDealResult = StaticMethod.nullObject2String(mainObject.getReplyFaultDealResult()).trim();
            SheetBeanUtils.populate(mainObject, request.getParameterMap());
            mainObject.setReplyDealStep(replyDealStep + "  " + StaticMethod.nullObject2String(request.getParameter("replyDealStep")));
            mainObject.setReplyFaultDealResult(replyFaultDealResult + " " + StaticMethod.nullObject2String(request.getParameter("replyFaultDealResult")));
            mainObject.setMainAlarmState("1");
            baseSheet.getMainService().saveOrUpdateMain(mainObject);
            forward = "event";
        } else if ("delete".equals(alarmMethod)) {
            baseSheet.getMainService().removeMain(baseSheet.getMainService().getSingleMainPO(sheetKey));
            forward = "event";
        } else if ("edit".equals(alarmMethod)) {

        }
        request.setAttribute("mainId", mainId);
        return mapping.findForward(forward);
    }


    public void getIfAlarmSolve(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JSONArray json = new JSONArray();
        JSONObject jitem = new JSONObject();
        String ifAlarmSolve = "";
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        ICommonFaultPackMainManager commonfaultPackMainManager = (ICommonFaultPackMainManager) baseSheet
                .getMainService();
        String alarmId = StaticMethod.nullObject2String(request.getParameter("alarmId"));
        System.out.println("-------***追单告警时间信息***--alarmId----：" + alarmId);
        ifAlarmSolve = commonfaultPackMainManager.getIfAlarmSolve(alarmId);
        System.out.println("-------***追单告警时间信息***-ifAlarmSolve-----：" + ifAlarmSolve);
        jitem.put("ifAlarmSolve", ifAlarmSolve);
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
    }
}
