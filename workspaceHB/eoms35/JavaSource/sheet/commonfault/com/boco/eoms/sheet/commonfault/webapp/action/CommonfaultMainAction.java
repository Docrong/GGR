package com.boco.eoms.sheet.commonfault.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;

public class CommonfaultMainAction extends SheetAction {

    public ActionForward init(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("newmain");
    }

    public ActionForward getMainInfo(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        try {
            baseSheet.getInterfaceObjMap(mapping, form, request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mapping.findForward("sql");
    }
}
