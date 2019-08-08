package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.extra.supplierkpi.schedule.TawSuppkpiReportStorageSchedule;
import com.boco.eoms.extra.supplierkpi.schedule.TawSupplierkpiSchedule;
import com.boco.eoms.extra.supplierkpi.service.ITawSuppKpiInstReportOrderManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;


public class TawSuppKpiInstRepairAction extends BaseAction {

    public ActionForward instRepair(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /**
         ArrayList list = instSearch();
         if(list!=null&&list.size()>0){
         return mapping.findForward("finished");
         }else{
         TawSupplierkpiSchedule kpiSchedule = new TawSupplierkpiSchedule();
         kpiSchedule.repair();
         ArrayList instList = instSearch();
         if(instList!=null&&instList.size()>0){
         return mapping.findForward("succeed");
         }else{
         return mapping.findForward("failure");
         }
         }
         */
        TawSupplierkpiSchedule kpiSchedule = new TawSupplierkpiSchedule();
        int num = kpiSchedule.repair();
        if (num < 0) {
            return mapping.findForward("failure");
        } else if (num == 0) {
            return mapping.findForward("finished");
        } else {
            String numStr = String.valueOf(num);
            request.setAttribute("num", numStr);
            return mapping.findForward("succeed");
        }
    }

    public ActionForward reportStorageRepair(ActionMapping mapping, ActionForm form,
                                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /**
         ArrayList list = reportStorageSearch();
         if(list!=null&&list.size()>0){
         return mapping.findForward("finished");
         }else{
         TawSuppkpiReportStorageSchedule reportStorageSchedule = new TawSuppkpiReportStorageSchedule();
         reportStorageSchedule.createStorage();
         ArrayList reportStorageList = reportStorageSearch();
         if(reportStorageList!=null&&reportStorageList.size()>0){
         return mapping.findForward("succeed");
         }else{
         return mapping.findForward("failure");
         }
         }*/
        TawSuppkpiReportStorageSchedule reportStorageSchedule = new TawSuppkpiReportStorageSchedule();
        int num = reportStorageSchedule.repair();
        if (num < 0) {
            return mapping.findForward("failure");
        } else if (num == 0) {
            return mapping.findForward("finished");
        } else {
            String numStr = String.valueOf(num);
            request.setAttribute("num", numStr);
            return mapping.findForward("succeed");
        }
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return instRepair(mapping, form, request, response);
    }

    //查询本月KPI指是否生成
    public ArrayList instSearch() {
        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
        String currentTime = StaticMethod.getCurrentDateTime();
        int date = Integer.parseInt(currentTime.substring(8, 10));
        String py = SuppStaticVariable.getLocalString(date, 0);
        String year = py.substring(0, 4);
        String fy = py.substring(5, 7);
        String timeLatitude = "";
        String timeLatitude1 = "";
        if ("03".equals(fy)) {
            timeLatitude = "one";
        } else if ("06".equals(fy)) {
            timeLatitude = "two";
        } else if ("09".equals(fy)) {
            timeLatitude = "three";
        } else if ("12".equals(fy)) {
            timeLatitude = "four";
            timeLatitude1 = "year";
        }

        String hql = "select distinct(specialType) from TawSupplierkpiInstance where (timeLatitude='"
                + fy
                + "'"
                + " or timeLatitude='"
                + timeLatitude
                + "' or timeLatitude='"
                + timeLatitude1
                + "') and year='"
                + year + "'";
        ArrayList list = (ArrayList) mgr.getTawSupplierkpiInstances(hql);
        return list;
    }

    //查询报表是否生成
    public ArrayList reportStorageSearch() {

        ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
                .getInstance().getBean("ItawSuppKpiInstReportOrderManager");
        String currentTime = StaticMethod.getCurrentDateTime();
        int date = Integer.parseInt(currentTime.substring(8, 10));
        String py = SuppStaticVariable.getLocalString(date, 0);
        String year = py.substring(0, 4);
        String fy = py.substring(5, 7);
        String timeLatitude = "";
        String timeLatitude1 = "";
        if ("03".equals(fy)) {
            timeLatitude = "one";
        } else if ("06".equals(fy)) {
            timeLatitude = "two";
        } else if ("09".equals(fy)) {
            timeLatitude = "three";
        } else if ("12".equals(fy)) {
            timeLatitude = "four";
            timeLatitude1 = "year";
        }
        String hql = " from TawSuppkpiReportStorage storage where (storage.reportTime ='"
                + fy
                + "' or storage.reportTime ='"
                + timeLatitude
                + "' or storage.reportTime ='"
                + timeLatitude1
                + "') and storage.reportBelongTime ='" + year + "'";
        ArrayList list = (ArrayList) mgr.getTawSuppkpiReportStorages(hql);
        return list;
    }

}
