package com.boco.eoms.commons.statistic.flowcontrol.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.webapp.action.IStatMethod;
import com.boco.eoms.commons.statistic.base.webapp.action.StatAction;

public class FlowcontrolAction extends StatAction {
    public ActionForward performStatistic(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
//			String servletPath = getServlet().getServletContext().getRealPath("");//WebServer path		
        statMethod.performStatistic(mapping, form, request, response);

        String reportFromType = String.valueOf(request.getAttribute("reportFromType"));
        //liquan add
        String reportIndex = String.valueOf(request.getAttribute("reportIndex"));
        String excelConfigURL = String.valueOf(request.getAttribute("excelConfigURL"));
        //end
        ActionForward actionForward = null;
        if ("StatFrom".equalsIgnoreCase(reportFromType)) {
            if ("flowcontrol_oracle".equals(excelConfigURL)) {
                if ("0".equals(reportIndex)) {
                    actionForward = mapping.findForward("result_first");
                } else if ("1".equals(reportIndex)) {
                    actionForward = mapping.findForward("result_sheetsecond");
                } else if ("2".equals(reportIndex)) {
                    actionForward = mapping.findForward("result_deptsecond");
                } else if ("3".equals(reportIndex)) {
                    actionForward = mapping.findForward("result_deptthird");
                } else if ("4".equals(reportIndex)) {
                    actionForward = mapping.findForward("result_sheetthird");
                } else {
                    actionForward = mapping.findForward("statisticresult");
                }
            } else {

                actionForward = mapping.findForward("statisticresult");
            }


        } else if ("graphicsFrom".equalsIgnoreCase(reportFromType)) {
            //graphicsFrom
            actionForward = mapping.findForward("showGraphicsStatisticPage");
        } else if ("StatFrom_graphicsFrom".equalsIgnoreCase(reportFromType)) {

            actionForward = mapping.findForward("showStatAndGraphicsStatisticPage");
        }
        return actionForward;

    }
}
