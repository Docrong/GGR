package com.boco.eoms.commonfaulthj.webapp.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commonfaulthj.util.CommonfaulthjExcelImportUtil;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;


public class ExcelimportAction extends BaseAction {

    public ActionForward messageimport(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("commonfaultimport");
    }

    public ActionForward importSave(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//		 获取当前用户的session中的信息
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid();
        String userName = TawSystemSessionForm.getUsername();
        String deptName = TawSystemSessionForm.getDeptname();
        try {
            //使用工具类
            CommonfaulthjExcelImportUtil importUtil = new CommonfaulthjExcelImportUtil();
            //将文件上传到相应位置，并返回上传路径
            File newExcelFile = importUtil.pushExcelFile(request, userName, deptName);
            // 将Excel里面的数据导入到数据库
            List errorList = importUtil.processExcel(newExcelFile, 0, 0, importUtil.getSheetType(), importUtil.getColseSwitch(), userId);
            if (errorList.size() > 0) {
                request.setAttribute("err", errorList.get(0));
                return mapping.findForward("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("importsuccess");
    }
}
