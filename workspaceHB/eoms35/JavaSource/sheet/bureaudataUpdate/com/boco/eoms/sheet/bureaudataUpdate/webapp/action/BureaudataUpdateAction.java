
package com.boco.eoms.sheet.bureaudataUpdate.webapp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;


public class BureaudataUpdateAction extends SheetAction {
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
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showHlrId(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        TawSystemSessionForm session = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        int belongflag = StaticMethod.nullObject2int(request.getParameter("belongflag"), 0);
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
        //获取该用户可访问号段信息
        ITawBureaudataBasicDAOManager hlrmgr = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                .getInstance().getBean("iTawBureaudataBasicDAOManager");
        List uSList = new ArrayList();
        if (!"".equals(sheetKey)) {
            uSList = hlrmgr.getDepBureaudateInfoSheet(sheetKey);
        } else {
            uSList = hlrmgr.getDepBureaudateInfo(session.getDeptid(), belongflag);
        }
//		java.util.ArrayList uSList = null;
        int size = uSList.size();
        StringBuffer returnValue = new StringBuffer();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                int[] arr = (int[]) uSList.get(i);
                returnValue.append(arr[0] + "," + arr[1] + "|");
            }
        }

        response.setContentType("text/xml;charset=GBK");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        out.write(returnValue.toString());
        out.close();
        return null;
    }
}
