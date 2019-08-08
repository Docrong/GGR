package com.boco.eoms.businessupport.product.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.util.GprsSpecialLineConstants;
import com.boco.eoms.businessupport.product.webapp.form.GprsSpecialLineForm;


public final class GprsSpecialLineAction extends BaseAction {

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IGprsSpecialLineManager iGprsSpecialLineManager = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        GprsSpecialLineForm gprsSpecialLineForm = (GprsSpecialLineForm) form;
        boolean isNew = (null == gprsSpecialLineForm.getId() || "".equals(gprsSpecialLineForm.getId()));
//		form转Object
        GprsSpecialLine gprsSpecialLine = new GprsSpecialLine();
        HashMap gprsSpecialLineFormMap = SheetBeanUtils.bean2MapWithNull(gprsSpecialLine);
        SheetBeanUtils.populateDataObj2ReqMap(gprsSpecialLineFormMap, gprsSpecialLineForm);
        SheetBeanUtils.populate(gprsSpecialLine, gprsSpecialLineFormMap);

        updateFormBean(mapping, request, gprsSpecialLineForm);
        gprsSpecialLine.setDeleted(new Integer(0));
        iGprsSpecialLineManager.saveGprsSpecialLine(gprsSpecialLine);
        if (gprsSpecialLine.getId() != null) {//save sucess
            return mapping.findForward("savesuccessjsp");
        } else {//save  failure
            return mapping.findForward("savefailurejsp");
        }
    }

    /**
     * 根据模块或功能的编码，删除该对象
     */
    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        request.setAttribute("ifReference", ifReference);
        String id = StaticMethod.nullObject2String(request.getParameter("gprsspeciallineid"));
        mgr.removeGprsSpecialLine(id);
        if (id != null) {//delete sucess
            return mapping.findForward("deletesuccessjsp");
        } else {//delete  failure
            return mapping.findForward("deletefailurejsp");
        }
    }

    /**
     * 根据模块或功能的编码，恢复该对象
     */
    public ActionForward xrestore(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        request.setAttribute("ifReference", ifReference);
        String id = StaticMethod.nullObject2String(request.getParameter("gprsspeciallineid"));
        mgr.restoreGprsSpecialLine(id);
        if (id != null) {//restore sucess
            return mapping.findForward("savesuccessjsp");
        } else {//restore failure
            return mapping.findForward("savefailurejsp");
        }

    }

    /**
     * 保存修改信息
     */
    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;

        //TawSystemSessionForm sessionForm = this.getUser(request);
        //String curUserId = sessionForm.getUserid();
//		String id = gprsspeciallineForm.getId();
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        String type = StaticMethod.nullObject2String(request.getParameter("type"));
        String orderSheet_Id = StaticMethod.nullObject2String(request.getParameter("orderId"));

        request.setAttribute("ifReference", ifReference);
        if (type.equals("add")) {
            return mapping.findForward("formjsp");
        } else {
            IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
            GprsSpecialLine gprsspecialline = (GprsSpecialLine) convert(gprsspeciallineForm);
            if (orderSheet_Id.equals(""))
                throw new Exception("orderId is null");
            gprsspecialline.setOrderSheet_Id(orderSheet_Id);

            mgr.saveGprsSpecialLine(gprsspecialline);

            return mapping.findForward("savesuccessjsp");
        }
    }

    /**
     * 进入订单目录的查询未删除的对象
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xquery(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        List gprsspeciallineList = mgr.getGprsSpecialLines();
        if (!ifReference.equals("")) {

            request.setAttribute("ifReference", "true");
        }
        request.setAttribute("gprsspeciallineList", gprsspeciallineList);
        return mapping.findForward("listjsp");
    }

    /**
     * 进入查询页面
     */
    public ActionForward showQueryPage(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        List gprsspeciallineList = mgr.getGprsSpecialLines();
        request.setAttribute("gprsspeciallineList", gprsspeciallineList);
        return mapping.findForward("querypage");
    }

    /**
     * 根据组合条件查询已删除的对象
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xqueryDeleted(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        request.setAttribute("ifReference", ifReference);
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        List gprsspeciallineList = mgr.getGprsSpecialLinesDeleted();
        request.setAttribute("gprsspeciallineList", gprsspeciallineList);
        return mapping.findForward("deletedlistjsp");
    }

    /**
     *
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;
        String id = gprsspeciallineForm.getId();
        if (id == null) {
            id = (String) request.getParameter("id");
        }
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        GprsSpecialLine gprsspecialline = (GprsSpecialLine) mgr.getGprsSpecialLine(id);
        String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        String orderId = StaticMethod.nullObject2String(request.getParameter("ordersheetid"));

        request.setAttribute("taskName", taskName);
        request.setAttribute("sheetType", sheetType);
        request.setAttribute("orderId", orderId);
        request.setAttribute("gprsspeciallineForm", gprsspecialline);
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        if (!ifReference.equals("")) {

            request.setAttribute("ifReference", "true");
        }
        String flag = StaticMethod
                .null2String(request.getParameter("flag"), "");
        request.setAttribute("flag", flag);
        return mapping.findForward("formjsp");
    }


    /**
     * 显示已删除产品详细信息
     */
    public ActionForward showDeletedDetail(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;
        String id = gprsspeciallineForm.getId();
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        GprsSpecialLine gprsspecialline = (GprsSpecialLine) mgr.getGprsSpecialLine(id);
        request.setAttribute("gprsspeciallineForm", gprsspecialline);
        String flag = StaticMethod
                .null2String(request.getParameter("flag"), "");
        request.setAttribute("flag", flag);
        return mapping.findForward("deleteddetailjsp");
    }

    /**
     * 显示未删除产品详细信息
     */
    public ActionForward showDetail(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;
        String id = gprsspeciallineForm.getId();
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        GprsSpecialLine gprsspecialline = (GprsSpecialLine) mgr.getGprsSpecialLine(id);
        request.setAttribute("gprsspeciallineForm", gprsspecialline);
        String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
        String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));

        request.setAttribute("sheetType", sheetType);
        request.setAttribute("taskName", taskName);
        request.setAttribute("orderId", orderId);
        String ifReference = StaticMethod.nullObject2String(request.getParameter("ifReference"));
        if (!ifReference.equals("")) {

            request.setAttribute("ifReference", "true");
        }
        String flag = StaticMethod
                .null2String(request.getParameter("flag"), "");
        request.setAttribute("flag", flag);

        return mapping.findForward("detail");
    }


    //根据主表中的主键去查找服务存量表 modify by shichuangke
    public ActionForward getSpecialLines(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        GprsSpecialLineForm gprsspeciallineForm = (GprsSpecialLineForm) form;
        String orderid = gprsspeciallineForm.getId();
        //String specialtyType = gprsspeciallineForm.getSpecialtyType();
        IGprsSpecialLineManager mgr = (IGprsSpecialLineManager) ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
        List list = mgr.getSpecialLines(orderid);
        List specialList = new ArrayList();
        for (int i = 0; list != null && i < list.size(); i++) {
            Map rasMap = new HashMap();
            GprsSpecialLine gprsSpecialLine = (GprsSpecialLine) list.get(i);
            rasMap.putAll(SheetBeanUtils.bean2Map(gprsSpecialLine));
            specialList.add(rasMap);
        }
        request.setAttribute("list", specialList);
        request.setAttribute("total", new Integer(list.size()));
        request.setAttribute("pageSize", new Integer(list.size()));
        request.setAttribute("orderid", orderid);
        if (!orderid.equals("")) {
            return mapping.findForward("specialLineList");
        } else {
            return null;
        }
    }

    /*
     * 查询结果列表展现方法，根据所选条件组合查询未删除的定单记录信息  modify by shichuangke
     */
    public ActionForward showListQuery(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //生成时间
        String creatTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("creatTimeStartDate"));
        String creatTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("creatTimeEndDate"));
        //完成时限
        String completeLimitStartDateExpression = StaticMethod.nullObject2String(request.getParameter("completeLimitStartDate"));
        String completeLimitEndDateExpression = StaticMethod.nullObject2String(request.getParameter("completeLimitEndDate"));
        //变更时间
        String changeTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("changeTimeStartDate"));
        String changeTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("changeTimeEndDate"));
        //撤销时间
        String cancelTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("cancelTimeStartDate"));
        String cancelTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("cancelTimeEndDate"));
        //竣工时间
        String endTimeStartDateExpression = StaticMethod.nullObject2String(request.getParameter("endTimeStartDate"));
        String endTimeEndDateExpression = StaticMethod.nullObject2String(request.getParameter("endTimeEndDate"));

        String orderType = StaticMethod.nullObject2String(request.getParameter("orderTypeChoiceExpression"));
        String urgentDegree = StaticMethod.nullObject2String(request.getParameter("urgentDegreeChoiceExpression"));
        String orderBuisnessType = StaticMethod.nullObject2String(request.getParameter("orderBuisnessTypeChoiceExpression"));
        String orderNumber = StaticMethod.nullObject2String(request.getParameter("orderNumber"));
        String isCompleted = StaticMethod.nullObject2String(request.getParameter("isCompletedChoiceExpression"));
        String mainProductInstanceCode = StaticMethod.nullObject2String(request.getParameter("mainProductInstanceCode"));
        Map queryMap = new HashMap();

        queryMap.put("creatTimeStartDateExpression", creatTimeStartDateExpression);
        queryMap.put("creatTimeEndDateExpression", creatTimeEndDateExpression);
        queryMap.put("changeTimeStartDateExpression", changeTimeStartDateExpression);
        queryMap.put("changeTimeEndDateExpression", changeTimeEndDateExpression);
        queryMap.put("completeLimitEndDateExpression", completeLimitEndDateExpression);
        queryMap.put("completeLimitStartDateExpression", completeLimitStartDateExpression);
        queryMap.put("cancelTimeStartDateExpression", cancelTimeStartDateExpression);
        queryMap.put("cancelTimeEndDateExpression", cancelTimeEndDateExpression);
        queryMap.put("endTimeStartDateExpression", endTimeStartDateExpression);
        queryMap.put("endTimeEndDateExpression", endTimeEndDateExpression);
        queryMap.put("orderType", orderType);
        queryMap.put("urgentDegree", urgentDegree);
        queryMap.put("orderBuisnessType", orderBuisnessType);
        queryMap.put("orderNumber", orderNumber);
        queryMap.put("isCompleted", isCompleted);
        queryMap.put("mainProductInstanceCode", mainProductInstanceCode);

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                GprsSpecialLineConstants.gprsSpecialLine_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        IGprsSpecialLineManager iGprsSpecialLineManager = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        Map map = (Map) iGprsSpecialLineManager.getQueryGprsSpecialLines(queryMap, pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(GprsSpecialLineConstants.gprsSpecialLine_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("querylist");
    }

}
