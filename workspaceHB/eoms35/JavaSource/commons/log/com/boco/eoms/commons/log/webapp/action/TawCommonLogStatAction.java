package com.boco.eoms.commons.log.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;

import com.boco.eoms.commons.log.dao.TawCommonLogOperatorDao;
import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonLogStatAction extends BaseAction {

    // private TawCommonLogOperatorDao tawCommonLogOperatorDao;

    public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward myforward = null;
        String myaction = mapping.getParameter();

        if ("LOGSTAT".equalsIgnoreCase(myaction)) { // 所有未处理工单
            myforward = performListStat(mapping, actionForm, request, response);
        } else if ("LOGSTATDEPT".equalsIgnoreCase(myaction)) {
            myforward = performListDeptStat(mapping, actionForm, request, response);
        } else if ("LOGSTATUSER".equalsIgnoreCase(myaction)) {
            myforward = performListUserStat(mapping, actionForm, request, response);
        } else if ("LOGSTATALL".equalsIgnoreCase(myaction)) {
            myforward = performListAllStat(mapping, actionForm, request, response);
        }

        return myforward;

    }

    private ActionForward performListStat(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {

        String[] deptarray = {"1", "14", "15", "16", "17", "18", "19", "20",
                "21", "22"};
        String[] deptname = {"省公司", "贵阳", "遵义", "安顺", "黔南分公司", "黔东南分公司",
                "铜仁分公司", "毕节分公司", "六盘水分公司", "黔西南分公司"};
        String depts = "";
        String deptcount = "";
        String usercount = "";
        String allconunt = "";
        List list = new ArrayList();
        try {
            Map formMap = ((DynaActionForm) actionForm).getMap();

            String starttime = (String) formMap.get("searchbystarttime");
            String endtime = (String) formMap.get("searchbyendtime");

            if (!starttime.equals("") && starttime != null) {
                starttime = starttime + " 00:00:00";
            } else {
                starttime = "";
            }
            // !"".equals(endtime)
            if (!endtime.equals("") && endtime != null) {
                endtime = endtime + " 23:59:59";
            } else {
                endtime = "";
            }
            TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
                    .getInstance().getBean("tawCommonLogOperatorDao");
            for (int i = 0; i < deptarray.length; i++) {
                List listTemp = new ArrayList();
                depts = dao.getAllwelcomebyone(deptarray[i]);
                // 部门登录数
                deptcount = dao.getAlldeptcount(depts, starttime, endtime);
                // 用户登录数
                usercount = dao.getAllusercount(depts, starttime, endtime);
                // 总登录数
                allconunt = dao.getAllcount(depts, starttime, endtime);

                listTemp.add(deptname[i]);
                listTemp.add(deptcount);
                listTemp.add(usercount);
                listTemp.add(allconunt);
                list.add(listTemp);
            }

            request.setAttribute("list", list);
            request.setAttribute("starttime", starttime);
            request.setAttribute("endtime", endtime);

        } catch (Exception e) {

            BocoLog.error(this, "查询错误 " + e.getMessage());
            return mapping.findForward("failure");

        }
        return mapping.findForward("success");

    }

    private ActionForward performListDeptStat(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {

        String[] deptarray = {"1", "14", "15", "16", "17", "18", "19", "20",
                "21", "22"};
        String[] deptname = {"省公司", "贵阳", "遵义", "安顺", "黔南分公司", "黔东南分公司",
                "铜仁分公司", "毕节分公司", "六盘水分公司", "黔西南分公司"};
        String depts = "";
        String deptcount = "";
        String usercount = "";
        String allconunt = "";
        List list = new ArrayList();
        try {
            Map formMap = ((DynaActionForm) actionForm).getMap();

            String starttime = (String) request.getParameter("searchbystarttime");
            String endtime = (String) request.getParameter("searchbyendtime");
            String flag = (String) request.getParameter("flag");

            if (!starttime.equals("") && starttime != null) {
                starttime = starttime + " 00:00:00";
            } else {
                starttime = "";
            }
            // !"".equals(endtime)
            if (!endtime.equals("") && endtime != null) {
                endtime = endtime + " 23:59:59";
            } else {
                endtime = "";
            }

            TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
                    .getInstance().getBean("tawCommonLogOperatorDao");

            depts = dao.getAllwelcomebyone(deptarray[Integer.parseInt(flag)]);
            list = dao.getListdeptStat(depts, starttime, endtime);

            request.setAttribute("list", list);
            request.setAttribute("starttime", starttime);
            request.setAttribute("endtime", endtime);

        } catch (Exception e) {

            BocoLog.error(this, "查询错误 " + e.getMessage());
            return mapping.findForward("failure");

        }
        return mapping.findForward("success");

    }

    private ActionForward performListUserStat(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {

        String[] deptarray = {"1", "14", "15", "16", "17", "18", "19", "20",
                "21", "22"};
        String[] deptname = {"省公司", "贵阳", "遵义", "安顺", "黔南分公司", "黔东南分公司",
                "铜仁分公司", "毕节分公司", "六盘水分公司", "黔西南分公司"};
        String depts = "";
        String deptcount = "";
        String usercount = "";
        String allconunt = "";
        List list = new ArrayList();
        try {
            Map formMap = ((DynaActionForm) actionForm).getMap();

            String starttime = (String) request.getParameter("searchbystarttime");
            String endtime = (String) request.getParameter("searchbyendtime");
            String flag = (String) request.getParameter("flag");

            if (!starttime.equals("") && starttime != null) {
                starttime = starttime + " 00:00:00";
            } else {
                starttime = "";
            }
            // !"".equals(endtime)
            if (!endtime.equals("") && endtime != null) {
                endtime = endtime + " 23:59:59";
            } else {
                endtime = "";
            }

            TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
                    .getInstance().getBean("tawCommonLogOperatorDao");

            depts = dao.getAllwelcomebyone(deptarray[Integer.parseInt(flag)]);
            list = dao.getListuserStat(depts, starttime, endtime);

            request.setAttribute("list", list);
            request.setAttribute("starttime", starttime);
            request.setAttribute("endtime", endtime);

        } catch (Exception e) {

            BocoLog.error(this, "查询错误 " + e.getMessage());
            return mapping.findForward("failure");

        }
        return mapping.findForward("success");

    }

    private ActionForward performListAllStat(ActionMapping mapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) {

        String[] deptarray = {"1", "14", "15", "16", "17", "18", "19", "20",
                "21", "22"};
        String[] deptname = {"省公司", "贵阳", "遵义", "安顺", "黔南分公司", "黔东南分公司",
                "铜仁分公司", "毕节分公司", "六盘水分公司", "黔西南分公司"};
        String depts = "";
        String deptcount = "";
        String usercount = "";
        String allconunt = "";

        try {
            Map formMap = ((DynaActionForm) actionForm).getMap();

            String starttime = (String) request.getParameter("searchbystarttime");
            String endtime = (String) request.getParameter("searchbyendtime");
            String flag = (String) request.getParameter("flag");

            if (!starttime.equals("") && starttime != null) {
                starttime = starttime + " 00:00:00";
            } else {
                starttime = "";
            }
            // !"".equals(endtime)
            if (!endtime.equals("") && endtime != null) {
                endtime = endtime + " 23:59:59";
            } else {
                endtime = "";
            }
            String pageIndexName = new org.displaytag.util.ParamEncoder(
                    "logList")
                    .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
            // 每页显示条数
            final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                    .getPageSize();
            final Integer pageIndex = new Integer(
                    GenericValidator.isBlankOrNull(request
                            .getParameter(pageIndexName)) ? 0 : (Integer
                            .parseInt(request.getParameter(pageIndexName)) - 1));
            TawCommonLogOperatorDao dao = (TawCommonLogOperatorDao) ApplicationContextHolder
                    .getInstance().getBean("tawCommonLogOperatorDao");

            depts = dao.getAllwelcomebyone(deptarray[Integer.parseInt(flag)]);

            Map map = null;
            map = dao.getAllStat(pageIndex, pageSize, depts, starttime, endtime);
            List list = (List) map.get("result");
            request.setAttribute("logList", list);
            request.setAttribute("resultSize", map.get("total"));
            System.out.println(map.get("total"));
            request.setAttribute("pageSize", pageSize);

        } catch (Exception e) {

            BocoLog.error(this, "查询错误 " + e.getMessage());
            return mapping.findForward("failure");

        }
        return mapping.findForward("success");

    }

    public ActionForward search(ActionMapping mapping, ActionForm actionForm,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("list");
    }

}