package com.boco.eoms.filemanager.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.filemanager.form.StatForm;
import com.boco.eoms.filemanager.ReportMgrStat;
import com.boco.eoms.filemanager.SchemeMgrDAO;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;


import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-26
 * Time: 14:56:55
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class StatReportMgrAction extends Action {
    public static int PAGE_LENGTH = 10;

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        ActionForward myforward = null;
        TawSystemAssignBo privBO = null;
          //Ȩ���ж�
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
        request.getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return new ActionForward("/timeout.jsp");
        }
        String userId = saveSessionBeanForm.getUserid();
        
        privBO = TawSystemAssignBo.getInstance();
        //StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.OPERATOR_REPORT_STAT_ID,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
//        if (!privBO.hasPermission4region(userId, SchemeMgrDAO.OPERATOR_REPORT_STAT_ID)) {
//          return new ActionForward("/nopriv.jsp");
//        }
        String myaction = request.getParameter("act");
        if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure");
        } else if ("prepare".equalsIgnoreCase(myaction)) {
            myforward = performPrepare(mapping, form, request, response);
        } else if ("stat".equalsIgnoreCase(myaction)) {
            myforward = performStat(mapping, form, request, response);
        } else
            myforward = mapping.findForward("failure");

        return myforward;
    }

    private ActionForward performPrepare(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
         StatForm myForm = (StatForm) form;
        try {
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
            request.getSession().getAttribute("sessionform");
            if (saveSessionBeanForm == null) {
                return mapping.findForward("timeout");
            }
        myForm.setAct("stat");
        myForm.setStatType(ReportMgrStat.STAT_TYPE_DEPT);
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }
        return mapping.findForward("input");
    }

    private ActionForward performStat(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        StatForm myForm = (StatForm) form;
        try {
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
            request.getSession().getAttribute("sessionform");
            if (saveSessionBeanForm == null) {
                return mapping.findForward("timeout");
            }
            ReportMgrStat stat=new ReportMgrStat(myForm);
            Vector result=stat.getStatReulst();
            stat.release();
            request.setAttribute("StatType",myForm.getStatType()+"");
            request.setAttribute("StatResult",result);
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }
        return mapping.findForward("result");
    }
}
