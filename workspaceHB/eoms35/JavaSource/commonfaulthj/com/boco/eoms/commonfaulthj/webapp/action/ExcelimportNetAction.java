package com.boco.eoms.commonfaulthj.webapp.action;

import java.io.File;
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
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commonfaulthj.mgr.CommonfaulthjMgr;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.commonfaulthj.util.TawExcelImportUtil;

public class ExcelimportNetAction extends BaseAction {

    public ActionForward messageimport(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("messageimport");
    }

    public ActionForward importSave(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String flag = StaticMethod.nullObject2String(request.getParameter("flag"));
        String theFile = StaticMethod.nullObject2String(request.getParameter("theFile"));
//		 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid();
        String userName = TawSystemSessionForm.getUsername();
        String deptName = TawSystemSessionForm.getDeptname();
        try {
            //使用工具类
            TawExcelImportUtil importUtil = new TawExcelImportUtil();
            //将文件上传到相应位置，并返回上传路径
            File newExcelFile = importUtil.pushExcelFile(request, userName, deptName);
            // 将Excel里面的数据导入到数据库
            List errorList = importUtil.processExcel(newExcelFile, 0, 0, importUtil.getSheetType(), importUtil.getColseSwitch(), userId, flag);
            if (errorList.size() > 0) {
                request.setAttribute("errorList", errorList);
                return mapping.findForward("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("listType", "flagExecl");
        return mapping.findForward("success");
    }

    public ActionForward showList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

//    	 获取每页显示条数
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder
                .getInstance().getBean("SheetAttributes")).getPageSize();
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) getBean("commonfaulthjMgr");
        Map map = (Map) commonfaulthjMgr.getMapList(pageIndex, pageSize, "");
        List list = (List) map.get("result");

        request.setAttribute("taskList", list);
        request.setAttribute("total", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("findForward", "list");

        return mapping.findForward("showlist");
    }
}
