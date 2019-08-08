package com.boco.eoms.duty.controller;

import java.util.*;

import javax.servlet.http.*;

import net.sf.json.JSONArray;

import org.apache.struts.action.*;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
//import com.boco.eoms.jbzl.dao.*;
//import com.boco.eoms.jbzl.model.*;
//import com.boco.eoms.jbzl.bo.*;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.duty.bo.*;
import com.boco.eoms.duty.model.*;

import java.text.*;

import org.apache.struts.util.LabelValueBean;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.base.util.StaticMethod;

public class TawRmDefineTreeAction extends Action {
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
            .getInstance();

    private static int PAGE_LENGTH = 20;

    // 整合调整关于国际化
    static {
        ResourceBundle prop = ResourceBundle
                .getBundle("resources.application_zh_CN");
        try {
            PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
        } catch (Exception e) {
        }
    }

    /*
     * static { ResourceBundle prop =
     * ResourceBundle.getBundle("resources.application"); try { PAGE_LENGTH =
     * Integer.parseInt(prop.getString("list.page.length")); } catch (Exception
     * e) { } }
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionForward myforward = null;
        String myaction = mapping.getParameter();

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }

        if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure");
        } else if ("DESIGNTREE".equalsIgnoreCase(myaction)) {
            myforward = performDesignTree(mapping, form, request, response);
        } else if ("SAVE".equalsIgnoreCase(myaction)) {
            myforward = performSave(mapping, form, request, response);
        } else if ("PREVIEW".equalsIgnoreCase(myaction)) {
            myforward = performPreview(mapping, form, request, response);
        } else if ("SELECTROOM".equalsIgnoreCase(myaction)) {
            myforward = performSelectroom(mapping, form, request, response);
        } else if ("SELECTCRPROOM".equalsIgnoreCase(myaction)) {
            myforward = performSelectCrproom(mapping, form, request, response);
        } else if ("SELECTPLAN".equalsIgnoreCase(myaction)) {
            myforward = performSelectPlan(mapping, form, request, response);
        } else if ("ADDDEFRECORD".equalsIgnoreCase(myaction)) {
            myforward = performAddDefRecord(mapping, form, request, response);
        } else if ("EDITRECORD".equalsIgnoreCase(myaction)) {
            myforward = performEditRecord(mapping, form, request, response);
        } else if ("SAVEREC".equalsIgnoreCase(myaction)) {
            myforward = performSaveRec(mapping, form, request, response);
        } else if ("QUERY".equalsIgnoreCase(myaction)) {
            myforward = performQuery(mapping, form, request, response);
        } else if ("LISTVIEW".equalsIgnoreCase(myaction)) {
            myforward = performListView(mapping, form, request, response);
        } else if ("DESIGN".equalsIgnoreCase(myaction)) {
            myforward = performDesign(mapping, form, request, response);
        } else if ("SUMMARY".equalsIgnoreCase(myaction)) {
            myforward = performSummary(mapping, form, request, response);
        } else if ("RECORDSUMMARY".equalsIgnoreCase(myaction)) {
            myforward = performRecordSummary(mapping, form, request, response);
        } else {
            myforward = mapping.findForward("failure");
        }
        // request.setAttribute("roomName",StaticMethod.dbNStrRev(request.getParameter("roomName")));
        try {
            String GBStr = com.boco.eoms.common.util.StaticMethod
                    .PageNStrRev(request.getParameter("roomName"));
            request.setAttribute("roomName", GBStr);
        } catch (Exception e) {
        }
        return myforward;
    }

    private ActionForward performSummary(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // SaveSessionBeanForm saveSessionBeanForm=null;

        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        // edit by wangheqi 2.7 to 3.5
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        // TawDeptDAO tawDeptDAO=new TawDeptDAO(ds);
        try {
            String[] deptIdsArr = request.getParameterValues("deptId");
            String deptIds = "";
            String[] topDeptArr = new String[deptIdsArr.length];
            HashMap topDeptMapSecDept = new HashMap();
            for (int i = 0; i < deptIdsArr.length; i++) {
                if (i == 0) {
                    deptIds += deptbo.getChilddepts((deptIdsArr[i]), "0");
                    // deptIds +=
                    // tawDeptDAO.getChildDept(Integer.parseInt(deptIdsArr[i]),
                    // 0);
                } else {
                    deptIds += "," + deptbo.getChilddepts((deptIdsArr[i]), "0");
                    // deptIds += ","
                    // +tawDeptDAO.getChildDept(Integer.parseInt(deptIdsArr[i]),
                    // 0);
                }
                topDeptArr[i] = deptbo.getChilddepts((deptIdsArr[i]), "0")
                        .toString();
                // topDeptArr[i]=tawDeptDAO.getChildDept(Integer.parseInt(deptIdsArr[i]),0);
            }
            String deptNames = request.getParameter("deptName");
            String YearMonth = request.getParameter("YearMonth");
            Calendar cal = new GregorianCalendar();
            String[] yearmonth = YearMonth.split("-");
            String starttime = null;
            String endtime = null;
            starttime = YearMonth + "-01";
            String monTmp = String.valueOf(cal.get(cal.MONTH) + 1);
            if (monTmp.length() == 1)
                monTmp = "0" + monTmp;
            if (yearmonth[0].equals(String.valueOf(cal.get(cal.YEAR)))
                    && yearmonth[1].equals(monTmp)) {
                if ((cal.get(cal.DATE) - 1) < 10)
                    endtime = YearMonth + "-0" + (cal.get(cal.DATE) - 1);
                else
                    endtime = YearMonth + "-" + (cal.get(cal.DATE) - 1);
            } else
                endtime = YearMonth + "-31";
            // edit by wangheqi
            TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
            // TawApparatusroomDAO tawApparatusroomDAO =new
            // TawApparatusroomDAO(ds);
            List roomList = cptroomBO.getTawSystemCptroomList(deptIds, 0);
            // Filter Room
            roomList = tawRmDefineTreeBO.isHavePlanOfRoom(roomList, starttime,
                    endtime);
            HashMap roomMapPlanNums = tawRmDefineTreeBO.roomMapPlanNums(
                    roomList, starttime, endtime);
            HashMap roomMapRealPlanNums = tawRmDefineTreeBO
                    .roomMapRealPlanNums(roomList, starttime, endtime);

            HashMap deptMapPlanNums = new HashMap();
            HashMap deptMapRealPlanNums = new HashMap();
            HashMap deptMaproom = new HashMap();
            String[] deptNames2 = deptNames.split(" ");
            for (int i = 0; i < deptIdsArr.length; i++) {
                List secondDept = deptbo.getSamelevelDeptbyDeptids(
                        deptIdsArr[i], "0");
                // List
                // secondDept=tawDeptDAO.getSameLevelList(Integer.parseInt(deptIdsArr[i]));
                List tmpSecond = new ArrayList();
                if (secondDept != null && secondDept.size() > 0) {
                    for (int j = 0; j < secondDept.size(); j++) {
                        // edit by wangheqi 2.7 to 3.5
                        TawSystemDept tawDept = (TawSystemDept) secondDept
                                .get(j);
                        int tmpId = Integer.parseInt(tawDept.getDeptId());
                        String tmpchildDetp = deptbo.getChilddepts(
                                String.valueOf(tmpId), "0").toString();
                        // TawDept tawDept=(TawDept)secondDept.get(j);
                        // int tmpId=tawDept.getDeptId();
                        // String tmpchildDetp=tawDeptDAO.getChildDept(tmpId,0);
                        List roomids = cptroomBO.getTawSystemCptroomList(
                                tmpchildDetp, 0);
                        if (roomids == null || roomids.size() == 0) // 机房不存在
                            continue;
                        roomids = tawRmDefineTreeBO.isHavePlanOfRoom(roomids,
                                starttime, endtime);
                        if (roomids.size() == 0) // 值班作业不存在
                            continue;
                        int total = 0;
                        int real = 0;
                        String realrooms = "";
                        for (Iterator itr = roomids.iterator(); itr.hasNext(); ) {
                            LabelValueBean lb = (LabelValueBean) itr.next();
                            total += Integer.parseInt(roomMapPlanNums.get(
                                    lb.getValue()).toString());
                            real += Integer.parseInt(roomMapRealPlanNums.get(
                                    lb.getValue()).toString());
                            realrooms += lb.getValue() + ",";
                        }
                        deptMapPlanNums.put(String.valueOf(tmpId), String
                                .valueOf(total));
                        deptMapRealPlanNums.put(String.valueOf(tmpId), String
                                .valueOf(real));
                        if (realrooms.endsWith(","))
                            realrooms = realrooms.substring(0, realrooms
                                    .length() - 1);
                        deptMaproom.put(String.valueOf(tmpId), realrooms);
                        tmpSecond.add(secondDept.get(j));
                    }
                    topDeptMapSecDept.put(String.valueOf(deptNames2[i]),
                            tmpSecond);
                }
            }
            // sHashMap
            // deptMapRooms=tawRmDefineTreeBO.deptMapRooms(topDeptArr,deptNames);
            request.setAttribute("deptMapsecond", topDeptMapSecDept);
            request.setAttribute("deptMapPlanNums", deptMapPlanNums);
            request.setAttribute("deptMapRealPlanNums", deptMapRealPlanNums);
            request.setAttribute("deptMaproom", deptMaproom);
            request.setAttribute("YearMonth", YearMonth);
            return mapping.findForward("success");
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        } finally {
            // saveSessionBeanForm=null;
        }
    }

    private ActionForward performSelectPlan(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            request.setAttribute("Action", request.getParameter("Action"));
            Vector planVect = tawRmDefineTreeBO.getNodes(roomId, "0", 0);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("planVect", planVect);
            return mapping.findForward("success");
        } catch (Exception e) {
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performListView(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            HashMap planMapCicyle = tawRmDefineTreeBO.getPlanMapCicle(roomId);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("planMapCicyle", planMapCicyle);
            return mapping.findForward("success");
        } catch (Exception e) {
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performAddDefRecord(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String action = request.getParameter("Action");
        // 判断是否值班
        if (Integer.parseInt(saveSessionBeanForm.getWorkSerial()) == 0) {
            // return mapping.findForward("notonduty");
            action = "View";
        }
        int roomId = Integer.parseInt(saveSessionBeanForm.getRoomId());
        /*
         * if(request.getParameter("roomId")!=null)
         * roomId=Integer.parseInt(request.getParameter("roomId"));
         */
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            request.setAttribute("Action", action);
            request.setAttribute("History", request.getParameter("History"));
            Vector planVect = tawRmDefineTreeBO.getNodes(roomId, "0", 0);
            String month = request.getParameter("month");
            if (month == null) {
                Calendar calendar = new GregorianCalendar();
                String bits = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                if (bits.length() < 2)
                    bits = "0" + bits;
                month = String.valueOf(calendar.get(Calendar.YEAR)) + "-"
                        + bits;
            }
            HashMap recordOfMonth = tawRmDefineTreeBO.getRecordOfMonth(roomId,
                    "0", month);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("planVect", planVect);
            request.setAttribute("recordOfMonth", recordOfMonth);
            request.setAttribute("month", month);
            request.setAttribute("Summary", request.getParameter("Summary"));
            request.setAttribute("deptId", request.getParameter("deptId"));
            request.setAttribute("deptName", request.getParameter("deptName"));
            return mapping.findForward("success");
        } catch (Exception e) {

            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performRecordSummary(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String roomIds = request.getParameter("roomId");
        String month = request.getParameter("month");
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        // TawApparatusroomDAO tawApparatusroomDAO=new TawApparatusroomDAO(ds);
        try {
            HashMap roomMapPlan = new HashMap();
            HashMap roomMapRecord = new HashMap();
            String[] roomId = roomIds.split(",");
            for (int i = 0; i < roomId.length; i++) {
                Vector planVect = tawRmDefineTreeBO.getNodes(Integer
                        .parseInt(roomId[i]), "0", 0);
                HashMap recordOfMonth = tawRmDefineTreeBO.getRecordOfMonth(
                        Integer.parseInt(roomId[i]), "0", month);
                roomMapPlan.put(cptroomBO.getTawSystemCptroomName(new Integer(
                        roomId[i])), planVect);
                roomMapRecord.put(cptroomBO
                                .getTawSystemCptroomName(new Integer(roomId[i])),
                        recordOfMonth);
            }
            request.setAttribute("roomMapPlan", roomMapPlan);
            request.setAttribute("roomMapRecord", roomMapRecord);
            request.setAttribute("month", month);
            return mapping.findForward("success");
        } catch (Exception e) {

            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performSaveRec(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            String parent_id = request.getParameter("parent_id");
            String action = request.getParameter("Action");
            String dutydate = request.getParameter("dutydate");
            String cycle = request.getParameter("Cycle");
            String dutyman = saveSessionBeanForm.getUserid();
            // int workserial=saveSessionBeanForm.getWorkSerial();
            int workserial = Integer.parseInt(request
                    .getParameter("workserial"));
            String[] values = request.getParameterValues("dutyrecord");
            boolean ishave = tawRmDefineTreeBO.IsExistINRec(parent_id, roomId,
                    dutydate, workserial, cycle);
            if ("workserial".equals(cycle)) {
                TawRmAssignworkDAO tawRmAssignWorkDAO = new TawRmAssignworkDAO(
                        ds);
                TawRmAssignwork tawRmAssignwork = tawRmAssignWorkDAO
                        .retrieve(workserial);
                dutydate = tawRmAssignwork.getDutydate();
            }

            if (ishave) { // upt
                String[] ids = request.getParameterValues("Id");
                tawRmDefineTreeBO.UptNodeRec(ids, values, dutydate, dutyman);
            } else { // add
                String[] nodeIds = request.getParameterValues("nodeId");
                tawRmDefineTreeBO.insertRec(nodeIds, values, roomId, dutydate,
                        workserial, dutyman);
            }
            tawRmDefineTreeBO.IsIdentify();
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("parent_id", parent_id);
            request.setAttribute("Action", action);
            request.setAttribute("History", request.getParameter("History"));
            return mapping.findForward("success");
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performEditRecord(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        /*
         * int roomId = -1; if (request.getParameter("roomId")!=null){ roomId =
         * Integer.parseInt(String.valueOf(request.getParameter("roomId")));
         * }else{ roomId =
         * Integer.parseInt(String.valueOf(request.getAttribute("roomId"))); }
         */
        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            String parent_id = request.getParameter("parent_id");
            String action = request.getParameter("Action");
            String Cycle = request.getParameter("Cycle");
            String dutydate = request.getParameter("dutydate");
            String workserail = request.getParameter("workserial");
            if (dutydate == null) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dutydate = formatter.format(new Date());
            }
            if (workserail == null) {
                workserail = String
                        .valueOf(saveSessionBeanForm.getWorkSerial());
            }
            String strTable = tawRmDefineTreeBO.GenTableRec(roomId, parent_id,
                    null, action, null, null, null, dutydate, Integer
                            .parseInt(workserail), Cycle);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("parent_id", parent_id);
            request.setAttribute("dutydate", dutydate);
            request.setAttribute("strTable", strTable);
            request.setAttribute("Action", action);
            request.setAttribute("Cycle", Cycle);
            request.setAttribute("History", request.getParameter("History"));
            request.setAttribute("Summary", request.getParameter("Summary"));
            request.setAttribute("deptId", request.getParameter("deptId"));
            request.setAttribute("deptName", request.getParameter("deptName"));
            request.setAttribute("month", request.getParameter("month"));
            request.setAttribute("workserial", workserail);
            return mapping.findForward("success");
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performPreview(ActionMapping mapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            String parent_id = request.getParameter("parent_id");
            String nodeName = request.getParameter("Name");
            String specility = request.getParameter("specility");
            String cycle = request.getParameter("cycle");

            String action = request.getParameter("Action");
            String strTable = tawRmDefineTreeBO.GenTable(roomId, parent_id,
                    null, action);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("strTable", strTable);
            request.setAttribute("Action", action);
            request.setAttribute("nodeId", parent_id);
            request.setAttribute("Name", nodeName);
            request.setAttribute("specility", specility);
            request.setAttribute("cycle", cycle);

            return mapping.findForward("success");
        } catch (Exception e) {
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performDesign(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        try {
            String nodeId = request.getParameter("nodeId");
            String nodeName = request.getParameter("Name");
            String specility = request.getParameter("specility");
            String cycle = request.getParameter("cycle");
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("Name", nodeName);
            request.setAttribute("nodeId", nodeId);
            request.setAttribute("specility", specility);
            request.setAttribute("cycle", cycle);
            return mapping.findForward("success");
        } catch (Exception e) {
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performDesignTree(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
        try {
            String nodeId = request.getParameter("nodeId");
            String treeStr = tawRmDefineTreeBO.GenTree(roomId, nodeId, null,
                    null);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("treeStr", treeStr);
            request.setAttribute("nodeId", nodeId);
            return mapping.findForward("success");
        } catch (Exception e) {
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 选择机房
     */
    private ActionForward performSelectroom(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;
        Vector SelectRoomId = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        TawSystemPrivRegion tawSystemPrivRegion = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO=null;
        // TawApparatusroom tawApparatusroom=null;
        String strSelectRoomName = null;
        // edit by wangheqi 权限验证
        TawSystemAssignBo privBO = null;
        // TawValidatePrivBO tawValidatePrivBO=null;
        /*
         * saveSessionBeanForm =
         * (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            //String is_Admin = "0";
            SelectRoom = new Vector();
            SelectRoomName = new Vector();
            SelectRoomId = new Vector();
            if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
                tawRmAssignworkBO = new TawRmAssignworkBO(ds);
                SelectRoom = tawRmAssignworkBO.getRoomSelect();
                request.setAttribute("SelectRoom", SelectRoom);
                strSelectRoomName = "";
                Vector removeEle = new Vector();
                for (int i = 0; i < SelectRoom.size(); i++) {

                    tawApparatusroom = cptroomBO.getTawSystemCptroomById(
                            new Integer((String) SelectRoom
                                    .elementAt(i)), 0);
                    if (tawApparatusroom != null) {
                        strSelectRoomName = StaticMethod
                                .null2String(tawApparatusroom.getRoomname());
                        SelectRoomName.add(strSelectRoomName);
                        SelectRoomId.add((String) SelectRoom
                                .elementAt(i));
                    } else {
                        removeEle.add(SelectRoom.elementAt(i));
                    }
                }
                SelectRoom.removeAll(removeEle);
                request.setAttribute("SelectRoom", SelectRoomId);
                //is_Admin = "1";
            } else {
                // edit by wangheqi
                privBO = TawSystemAssignBo.getInstance();
                SelectRoom = StaticMethod
                        .list2vector(privBO
                                .getPermissions(
                                        saveSessionBeanForm.getUserid(),
                                        com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
                                        com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));

                strSelectRoomName = "";
                Vector removeEle = new Vector();
                for (int i = 0; i < SelectRoom.size(); i++) {
                    tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
                            .elementAt(i);
                    tawApparatusroom = cptroomBO.getTawSystemCptroomById(
                            new Integer(tawSystemPrivRegion.getRegionid()), 0);
                    if (tawApparatusroom != null) {
                        strSelectRoomName = StaticMethod
                                .null2String(tawApparatusroom.getRoomname());
                        SelectRoomName.add(strSelectRoomName);
                        SelectRoomId.add(tawSystemPrivRegion.getRegionid());
                    } else {
                        removeEle.add(SelectRoom.elementAt(i));
                    }
                }
                SelectRoom.removeAll(removeEle);
                request.setAttribute("SelectRoom", SelectRoomId);
            }

            request.setAttribute("SelectRoomName", SelectRoomName);
            //request.setAttribute("is_Admin", is_Admin);
            if ("true".equals(request.getParameter("query"))) {
                return mapping.findForward("query");
            }
            // roomId=146,tableId=3,nodeID=004,startTime=2004-08-31,endTime=2004-11-13
            // TawRmDefineTreeBO tawRmDefineTreeBO=new TawRmDefineTreeBO(ds);
            // tawRmDefineTreeBO.ExportData(146,3,"2004-08-31","2004-11-13","004");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO=null;
            tawApparatusroom = null;
            strSelectRoomName = null;
            privBO = null;
            // tawValidatePrivBO=null;

        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 选择机房
     */
    private ActionForward performSelectCrproom(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {

        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        Vector SelectRoom = null;
        Vector SelectRoomId = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        TawSystemPrivRegion tawSystemPrivRegion = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;
        JSONArray json = new JSONArray();
        // TawApparatusroomDAO tawApparatusroomDAO=null;
        // TawApparatusroom tawApparatusroom=null;
        String strSelectRoomName = null;
        // edit by wangheqi 权限验证
        TawSystemAssignBo privBO = null;
        // TawValidatePrivBO tawValidatePrivBO=null;
        /*
         * saveSessionBeanForm =
         * (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            String is_Admin = "0";
            SelectRoom = new Vector();
            SelectRoomName = new Vector();
            SelectRoomId = new Vector();
            if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
                tawRmAssignworkBO = new TawRmAssignworkBO(ds);
                SelectRoom = tawRmAssignworkBO.getRoomSelect();
                is_Admin = "1";
            } else {
                // edit by wangheqi
                privBO = TawSystemAssignBo.getInstance();
                SelectRoom = StaticMethod
                        .list2vector(privBO
                                .getPermissions(
                                        saveSessionBeanForm.getUserid(),
                                        com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
                                        com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
                /*
                 * tawValidatePrivBO = new TawValidatePrivBO(ds); SelectRoom =
                 * tawValidatePrivBO.validatePriv(saveSessionBeanForm.
                 * getUserid(),mapping.getPath());
                 */
            }

            if (SelectRoom.size() > 0) {
                // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
                // tawApparatusroom = null;
                strSelectRoomName = "";
                Vector removeEle = new Vector();
                for (int i = 0; i < SelectRoom.size(); i++) {
                    tawSystemPrivRegion = (TawSystemPrivRegion) SelectRoom
                            .elementAt(i);
                    tawApparatusroom = cptroomBO.getTawSystemCptroomById(
                            new Integer(tawSystemPrivRegion.getRegionid()), 0);
                    if (tawApparatusroom != null) {
                        strSelectRoomName = StaticMethod
                                .null2String(tawApparatusroom.getRoomname());
                        SelectRoomName.add(strSelectRoomName);
                        SelectRoomId.add(tawSystemPrivRegion.getRegionid());
                    } else {
                        removeEle.add(SelectRoom.elementAt(i));
                    }
                }
                SelectRoom.removeAll(removeEle);
            } else {
                return mapping.findForward("nopriv");
            }
            request.setAttribute("SelectRoom", SelectRoomId);
            request.setAttribute("SelectRoomName", SelectRoomName);
            request.setAttribute("is_Admin", is_Admin);
            if ("true".equals(request.getParameter("query"))) {
                return mapping.findForward("query");
            }
            // roomId=146,tableId=3,nodeID=004,startTime=2004-08-31,endTime=2004-11-13
            // TawRmDefineTreeBO tawRmDefineTreeBO=new TawRmDefineTreeBO(ds);
            // tawRmDefineTreeBO.ExportData(146,3,"2004-08-31","2004-11-13","004");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            cptroomBO = null;
            // tawApparatusroomDAO=null;
            tawApparatusroom = null;
            strSelectRoomName = null;
            privBO = null;
            // tawValidatePrivBO=null;

        }
        return mapping.findForward("success");
    }

    private ActionForward performSave(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        String next = "success";
        try {
            String NodeName = request.getParameter("Name");
            String parentID = request.getParameter("Parent");
            String nodeId = request.getParameter("nodeId");
            String NodeID = request.getParameter("Node");
            String Type = request.getParameter("Type");
            String action = request.getParameter("Action");
            String Default = request.getParameter("Default");
            int LineNum = request.getParameter("LineNum") == null ? 0 : Integer
                    .parseInt(request.getParameter("LineNum")); // 以后加上
            String cycle = request.getParameter("cycle");
            String specility = request.getParameter("specility");
            TawRmDefineTree tawRmDefineTree = null;
            TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
            if ("ADD".equals(action)) {
                if ("Folder".equals(Type)) {
                    tawRmDefineTree = new TawRmDefineTree();
                    tawRmDefineTree.setName(NodeName);
                    tawRmDefineTree.setParentId(parentID);
                    tawRmDefineTree.setRoomId(roomId);
                    tawRmDefineTree.setIsLeaf(0);
                    tawRmDefineTree.setCycles(cycle);
                    tawRmDefineTree.setSpecility(specility);
                    nodeId = tawRmDefineTreeBO.insert(tawRmDefineTree);
                    if ("0".equals(parentID))
                        next = "listview";
                } else if ("Item".equals(Type)) {
                    tawRmDefineTree = new TawRmDefineTree();
                    tawRmDefineTree.setName(NodeName);
                    tawRmDefineTree.setParentId(parentID);
                    tawRmDefineTree.setRoomId(roomId);
                    tawRmDefineTree.setDefalut(Default);
                    tawRmDefineTree.setIsLeaf(1);
                    tawRmDefineTree.setLines(LineNum);
                    tawRmDefineTreeBO.insert(tawRmDefineTree);
                }
            } else if ("MODI".equals(action)) {
                if ("Folder".equals(Type)) {
                    tawRmDefineTreeBO.UptNodeName(parentID, NodeName, roomId,
                            cycle, specility);
                    if (nodeId.equals(parentID))
                        next = "design";
                } else if ("Item".equals(Type)) {
                    tawRmDefineTreeBO.UptNode(parentID, NodeName, Default,
                            LineNum, roomId);
                }
            } else if ("DEL".equals(action)) {
                tawRmDefineTreeBO.delete(NodeID, roomId);
                if ("0".equals(parentID))
                    next = "listview";
            }
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("parentID", parentID);
            request.setAttribute("nodeId", nodeId);
            request.setAttribute("nodeName", NodeName);
            request.setAttribute("specility", specility);
            return mapping.findForward(next);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
    }

    private ActionForward performQuery(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int roomId = -1;
        if (request.getParameter("roomId") != null) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        }
        try {
            TawRmDefineTreeBO tawRmDefineTreeBO = new TawRmDefineTreeBO(ds);
            Vector monthVect = tawRmDefineTreeBO.getHisMonth(roomId);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("monthVect", monthVect);
            request.setAttribute("Action", "View");
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
        }
        return mapping.findForward("success");
    }

    private void sqlDuplicateError(HttpServletRequest request, String objName) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("errors.database.duplicate",
                objName));
        saveErrors(request, aes);
    }

    private void generalError(HttpServletRequest request, Exception e) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e
                .getMessage()));
        saveErrors(request, aes);
        e.printStackTrace();
    }

}
