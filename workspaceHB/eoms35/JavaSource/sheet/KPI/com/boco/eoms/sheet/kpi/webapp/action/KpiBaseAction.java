package com.boco.eoms.sheet.kpi.webapp.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.kpi.service.IKpiBaseManager;
import com.boco.eoms.sheet.sheetkpi.service.ISheetKpiBaseManager;

public class KpiBaseAction extends SheetAction {
    public ActionForward init(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileName = StaticMethod.nullObject2String(request
                .getParameter("filename"), "");
        String fileType = StaticMethod.nullObject2String(request
                .getParameter("fileType"), "");
        request.setAttribute("fileName", fileName);
        request.setAttribute("searchInit", "SearchInit");
        request.setAttribute("fileType", fileType);
        if (!"".endsWith(fileName) && fileName != null) {
            return mapping.findForward("init");
        } else {
            return mapping.findForward("groupInit");
        }
    }

    public ActionForward init2(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileName = StaticMethod.nullObject2String(request
                .getParameter("filename"), "");
        request.setAttribute("fileName", fileName);
        request.setAttribute("searchInit", "SearchInit");
        String selectArea = StaticMethod.nullObject2String(XmlManage.getFile("/config/kpi-util.xml").getProperty("selectArea"));
        String selectAreaIndex[] = selectArea.split(",");
        String areaflag = "";
        for (int i = 0; i < selectAreaIndex.length; i++)
            if (fileName.equals(selectAreaIndex[i]))
                areaflag = "1";
        request.setAttribute("areaflag", areaflag);
        return mapping.findForward("init2");
    }


    public ActionForward down(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map actionMap = request.getParameterMap();

        return null;
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map actionMap = request.getParameterMap();
        String fileName = StaticMethod.nullObject2String(request
                .getParameter("filename"), "");
        // 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        // 页数的参数名
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // String pageIndexName = new
        // org.displaytag.util.ParamEncoder("taskList").encodeParameterName(org.displaytag.tags.TableTagParameters.E);
        Integer index = new Integer(GenericValidator.isBlankOrNull(request
                .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
                .getParameter(pageIndexName)) - 1));


        // 当前页数
        final Integer pageIndex = index;

        // 分页取得列表
        // wps端分页取得列表
//		XMLProperties xmlFile = XmlManage
//				.getFile("com/boco/eoms/sheet/kpi/xmlFile/" + fileName + ".xml");
//		String cnname = xmlFile.getProperty("cnname");
//		System.out.println("中文字段:" + cnname);
        String[] aSql = {""};
        int[] aTotal = {0};
        Map condition = new HashMap();
        IKpiBaseManager mgr = (IKpiBaseManager) getBean("iKpiBaseManager");
        List colMap = mgr.getXMLList(fileName);

        List result = mgr.getQuerySheetByCondition(aSql, actionMap, condition,
                pageIndex, pageSize, aTotal, "", fileName);
        Integer total = new Integer(aTotal[0]);

        request.setAttribute("filename", request.getParameter("filename"));
        request.setAttribute("sendTimeStartDate", request.getParameter("sendTimeStartDate"));
        request.setAttribute("sendTimeEndDate", request.getParameter("sendTimeEndDate"));
        request.setAttribute("completeLimitStartDate", request.getParameter("completeLimitStartDate"));
        request.setAttribute("completeLimitEndDate", request.getParameter("completeLimitEndDate"));
        request.setAttribute("operatetype", request.getParameter("operatetype"));
        request.setAttribute("toDeptId", request.getParameter("main.toDeptId"));
        request.setAttribute("status", request.getParameter("status"));
        request.setAttribute("userid", request.getParameter("userid"));
        request.setAttribute("systype", request.getParameter("systype"));
        request.setAttribute("mainNetSortOneChoiceExpression", request.getParameter("mainNetSortOneChoiceExpression"));
        request.setAttribute("mainNetSortTwoChoiceExpression", request.getParameter("mainNetSortTwoChoiceExpression"));
        request.setAttribute("mainNetSortThreeChoiceExpression", request.getParameter("mainNetSortThreeChoiceExpression"));

        request.setAttribute("deptid", request.getParameter("deptid"));
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("colMap", colMap);
//		request.setAttribute("cnname", cnname);
        return mapping.findForward("search");
    }

    public ActionForward groupSearch(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //	java.util.Map actionMap = request.getParameterMap();
        Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
        actionMap = StatUtil.modActionMap(actionMap);
        String fileName = StaticMethod.nullObject2String(request
                .getParameter("filename"), "");
        String deptid = StaticMethod.nullObject2String(request
                .getParameter("deptid"), "");
        String sendTimeStartDate = StaticMethod.nullObject2String(actionMap.get("beginTime"));
        String sendTimeEndDate = StaticMethod.nullObject2String(actionMap.get("endTime"));
        System.out.println("search--sendTimeStartDate-" + sendTimeStartDate + "--sendTimeEndDate--" + sendTimeEndDate);
        actionMap.put("sendTimeStartDate", actionMap.get("beginTime"));
        actionMap.put("sendTimeEndDate", actionMap.get("endTime"));
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList"))
                .encodeParameterName("p");
        Integer index = new Integer(GenericValidator.isBlankOrNull(request
                .getParameter(pageIndexName)) ? 0 : Integer.parseInt(request
                .getParameter(pageIndexName)) - 1);
        Integer pageIndex = index;
        String aSql[] = {""};
        int aTotal[] = new int[1];
        java.util.Map condition = new HashMap();
        IKpiBaseManager mgr = (IKpiBaseManager) getBean("iKpiBaseManager");
        java.util.List colMap = mgr.getXMLList(fileName);
        java.util.List result = new ArrayList();
//		if ("".equals(deptid) || deptid == null) {
        result = mgr.getReportByDept(aSql, actionMap, fileName);
        Integer total = new Integer(result.size());
        request.setAttribute("total", total);
        request.setAttribute("pageSize", "20");
//		} else {
//			result = mgr.getQuerySheetByCondition(aSql, actionMap, condition,
//					pageIndex, pageSize, aTotal, deptid, fileName);
//			Integer total = new Integer(aTotal[0]);
//			request.setAttribute("total", total);
//			request.setAttribute("pageSize", pageSize);
//		}
        request.setAttribute("filename", request.getParameter("filename"));
        request.setAttribute("taskList", result);
        request.setAttribute("sendTimeStartDate", sendTimeStartDate);
        request.setAttribute("sendTimeEndDate", sendTimeEndDate);

        request.setAttribute("colMap", colMap);

        return mapping.findForward("groupSearch");

    }


    public ActionForward excelsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        int total = StaticMethod.nullObject2int(request.getParameter("filename"));
        int page = total / 5000;
        for (int i = 0; i < page; i++) {

        }
        return mapping.findForward("search");
    }


}
