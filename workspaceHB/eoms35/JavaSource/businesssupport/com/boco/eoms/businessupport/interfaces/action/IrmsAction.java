package com.boco.eoms.businessupport.interfaces.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

public class IrmsAction {
    public void showUrlPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                            HttpServletResponse response) throws Exception {

        String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));

        List list = new ArrayList();
        String opdetail = "";
        //String result = IrmsResourceBo.createProService(opdetail);
        response.setContentType("text/xml; charset=UTF-8");
        // response.getWriter().print(result);
    }
}
