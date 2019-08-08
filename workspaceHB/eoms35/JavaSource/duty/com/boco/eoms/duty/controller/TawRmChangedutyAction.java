/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：用于交换班等功能的类。</p>
 */

package com.boco.eoms.duty.controller;

import javax.sql.*;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.LogFactory;

import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
// import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
// import com.boco.eoms.jbzl.model.TawApparatusroom;

import com.boco.eoms.duty.bo.*;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
// import com.boco.eoms.log.bo.logBO;
// import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.UIConstants;

public class TawRmChangedutyAction extends Action {
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

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionForward myforward = null;
        String myaction = mapping.getParameter();

        if (isCancelled(request)) {

            return mapping.findForward("cancel");
        }

        if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure");
        } else if ("VIEW".equalsIgnoreCase(myaction)) {
            myforward = performView(mapping, form, request, response);
        } else if ("EDIT".equalsIgnoreCase(myaction)) {
            myforward = performEdit(mapping, form, request, response);
        } else if ("ADD".equalsIgnoreCase(myaction)) {
            myforward = performAdd(mapping, form, request, response);
        } else if ("SAVE".equalsIgnoreCase(myaction)) {
            myforward = performSave(mapping, form, request, response);
        } else if ("REMOVE".equalsIgnoreCase(myaction)) {
            myforward = performRemove(mapping, form, request, response);
        } else if ("TRASH".equalsIgnoreCase(myaction)) {
            myforward = performTrash(mapping, form, request, response);
        } else if ("LIST".equalsIgnoreCase(myaction)) {
            myforward = performList(mapping, form, request, response);
        } else if ("SELECTTIME".equalsIgnoreCase(myaction)) {
            myforward = performSelecttime(mapping, form, request, response);
        } else if ("APPLY".equalsIgnoreCase(myaction)) {
            myforward = performApply(mapping, form, request, response);
        } else if ("SAVEAPPLY".equalsIgnoreCase(myaction)) {
            myforward = performSaveApply(mapping, form, request, response);
        } else if ("QUERYAPPLY".equalsIgnoreCase(myaction)) {
            myforward = performQueryapply(mapping, form, request, response);
        } else if ("TRANSACTAPPLY".equalsIgnoreCase(myaction)) {
            myforward = performTransactapply(mapping, form, request, response);
        } else if ("TRANSAUDIT".equalsIgnoreCase(myaction)) {
            myforward = performTransactAudit(mapping, form, request, response);
        } else if ("SELECTROOM".equalsIgnoreCase(myaction)) {
            myforward = performselectRoom(mapping, form, request, response);
        } else if ("QUERYAUDIT".equalsIgnoreCase(myaction)) {

            myforward = performQueryAudit(mapping, form, request, response);
        } else if ("SELECTDATE".equalsIgnoreCase(myaction)) {

            myforward = performSelectDate(mapping, form, request, response);
        } else if ("SELECTTREE".equalsIgnoreCase(myaction)) {
            myforward = performSelectTree(mapping, form, request, response);
        } else if ("SELECTHISTORY".equalsIgnoreCase(myaction)) {
            myforward = performSelecthistory(mapping, form, request, response);
        } else if ("BATCHSELECT".equalsIgnoreCase(myaction)) {
            myforward = performBatchSelect(mapping, form, request, response);
        } else if ("BATCHAPPLY".equalsIgnoreCase(myaction)) {
            myforward = performBatchApply(mapping, form, request, response);
        } else if ("SAVEBATCHAPPLY".equalsIgnoreCase(myaction)) {
            myforward = performSaveBatchApply(mapping, form, request, response);
        } else {
            myforward = mapping.findForward("failure");
        }
        return myforward;
    }

    //保存批量排班
    private ActionForward performSaveBatchApply(ActionMapping mapping,
                                                ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        Date date = null;
        GregorianCalendar cal = null;
        String inpudate = null;
        TawRmChangedutyBO tawRmChangedutyBO = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = Integer.parseInt(String.valueOf(
                request.getParameter("roomId")).trim());


        date = new Date();
        cal = new GregorianCalendar();
        cal.setTime(date);
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int day = cal.get(cal.DATE);
        inpudate = String.valueOf(year) + "-" + String.valueOf(month) + "-"
                + String.valueOf(day);


        Vector apply_from = null;
        Vector apply_to = null;

        try {
            tawRmChangedutyBO = new TawRmChangedutyBO(ds);

            String reason = StaticMethod.nullObject2String(request
                    .getParameter("reason"));
            String workapplyfrom = request.getParameter("workapplyfrom");
            String workapplyto = request.getParameter("workapplyto");
            workapplyfrom = workapplyfrom.substring(1, workapplyfrom.length());
            workapplyto = workapplyto.substring(1, workapplyto.length());

            try {
                apply_from = StaticMethod.getVector(workapplyfrom, "@");
                apply_to = StaticMethod.getVector(workapplyto, "@");
                for (int i = 0; i < apply_from.size(); i++) {
                    String applyfrom = (String) apply_from.elementAt(i);
                    String applyto = (String) apply_to.elementAt(i);
                    tawRmChangedutyBO.batchapply(roomId, inpudate, applyfrom,
                            applyto, reason);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return mapping.findForward("sms");
            }


        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            date = null;
            cal = null;
            inpudate = null;
            tawRmChangedutyBO = null;
            apply_from = null;
            apply_to = null;
        }
        return mapping.findForward("success");
    }

    // 批量换班

    private ActionForward performBatchApply(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) {

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        String time_from_b = "";
        String time_from_e = "";
        String time_to_b = "";
        String time_to_e = "";
        String strSAVEFLAG = "";
        String user_id = "";
        Vector vector_from = null;
        Vector vector_to = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;
        time_from_b = request.getParameter("time_from_b");
        time_from_e = request.getParameter("time_from_e");
        time_to_b = request.getParameter("time_to_b");
        time_to_e = request.getParameter("time_to_e");
        time_from_b = StaticMethod.getAddZero(time_from_b);
        time_from_e = StaticMethod.getAddZero(time_from_e);
        time_to_b = StaticMethod.getAddZero(time_to_b);
        time_to_e = StaticMethod.getAddZero(time_to_e);
        int roomId = 0;

        strSAVEFLAG = String.valueOf(request.getParameter("SAVEFLAG"));
        if (strSAVEFLAG.equals("true")) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("room_id")));
        }
        user_id = saveSessionBeanForm.getUserid();
        try {
            vector_from = new Vector();
            vector_to = new Vector();
            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            vector_from = tawRmChangedutyDAO.getApplyVector(roomId, user_id,
                    time_from_b, time_from_e, "0");
            vector_to = tawRmChangedutyDAO.getApplyVector(roomId, user_id,
                    time_to_b, time_to_e, "1");
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("VECTORFROM", vector_from);
            request.setAttribute("VECTORTO", vector_to);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return mapping.findForward("success");
    }

    //

    private ActionForward performBatchSelect(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 选择要换班的源时间和目的时间
     */
    private ActionForward performSelectTree(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return mapping.findForward("success");
    }

    private ActionForward performSelecttime(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        ;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {
            TawSystemUser systemuser = new TawSystemUser();
            TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
            systemuser = rolebo
                    .getUserByuserid(saveSessionBeanForm.getUserid());

            String roomid = systemuser.getCptroomid();

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return mapping.findForward("success");
    }

    private ActionForward performQueryAudit(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String user_id = null;
        Date date = null;
        GregorianCalendar cal = null;
        String today = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;
        Vector receiveQueryVector = null;
        Vector sentQueryVector = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String isManager = "0";
        Vector managerQueryVector = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int roomId;

        // xiugaicheng jifangyu
        String roomid = request.getParameter("roomId");
        if (roomid == null) {
            roomId = Integer.parseInt((String) request.getAttribute("roomId"));
        } else {
            roomId = Integer.parseInt(roomid);
        }
        user_id = saveSessionBeanForm.getUserid();
        request.setAttribute("roomId", roomId + "");
        // should get from select_time
        date = new Date();
        cal = new GregorianCalendar();
        cal.setTime(date);
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int day = cal.get(cal.DATE);
        today = String.valueOf(year) + "-" + String.valueOf(month) + "-"
                + String.valueOf(day);
        TawSystemUser tawSystemUser = null;
        try {

            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            // tawRmChangedutyDAO.delete(today);

            receiveQueryVector = new Vector();
            sentQueryVector = new Vector();

            receiveQueryVector = tawRmChangedutyDAO.receiveQueryVector(roomId,
                    user_id);
            sentQueryVector = tawRmChangedutyDAO.sentQueryVector(roomId,
                    user_id);
            request.setAttribute("RECEIVEQUERYVECTOR", receiveQueryVector);
            request.setAttribute("SENTQUERYVECTOR", sentQueryVector);

            // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
            tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
                    roomId), 0);

            managerQueryVector = new Vector();
            managerQueryVector = tawRmChangedutyDAO.managerQueryVector(roomId);
            request.setAttribute("MANAGERQUERYVECTOR", managerQueryVector);

            // }
            request.setAttribute("ISMANAGER", isManager);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            user_id = null;
            date = null;
            cal = null;
            today = null;
            tawRmChangedutyDAO = null;
            receiveQueryVector = null;
            sentQueryVector = null;
            // tawApparatusroomDAO = null;
            cptroomBO = null;
            tawApparatusroom = null;
            isManager = null;
            managerQueryVector = null;
        }
        return mapping.findForward("success");
    }

    private ActionForward performSelectDate(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {
            String roomId = request.getParameter("roomId");
            request.setAttribute("roomId", roomId);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return mapping.findForward("success");
    }

    private ActionForward performselectRoom(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        try {

            TawSystemUser systemuser = new TawSystemUser();
            TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
            systemuser = rolebo
                    .getUserByuserid(saveSessionBeanForm.getUserid());
            // vectorUserRoom
            // =tawUserRoomDAO.getUserRoom(saveSessionBeanForm.getUserid());
            JSONArray json = new JSONArray();
            String roomid = systemuser.getCptroomid();
            String roomName = systemuser.getCptroomname();
            if (roomid != null && roomName != null) {
                String[] roomArrayId = roomid.split(",");
                String[] roomArrayName = roomName.split(",");
                for (int i = 0; i < roomArrayId.length; i++) {
                    JSONObject jitem = new JSONObject();

                    jitem.put("id", roomArrayId[i]);
                    jitem.put("text", roomArrayName[i]);
                    jitem.put(UIConstants.JSON_NODETYPE, "cptroom");

                    jitem.put("leaf", 1);
                    jitem.put("allowChild", true);
                    jitem.put("allowDelete", true);
                    json.put(jitem);
                }

                response.setContentType("text/xml;charset=UTF-8");
                response.getWriter().print(json.toString());
                // vectorRoomId = (Vector)vectorUserRoom.elementAt(0);
            } else {

                // if (vectorRoomId.size() < 1){
                return mapping.findForward("noroom");
            }
            // }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;

        }
        return null;
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 显示换班的历史记录
     */
    private ActionForward performQueryhistory(ActionMapping mapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String user_id = null;
        Date date = null;
        String today = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;
        Vector allQueryVector = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String inputdate_from = null;
        String inputdate_to = null;

        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = Integer.parseInt(request.getParameter("roomId"));
        inputdate_from = request.getParameter("inputdate_from") + " 00:00:00";
        inputdate_to = request.getParameter("inputdate_to") + " 23:59:59";
        try {
            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            allQueryVector = new Vector();

            allQueryVector = tawRmChangedutyDAO.allQueryVector(roomId,
                    inputdate_from, inputdate_to);
            request.setAttribute("ALLQUERYVECTOR", allQueryVector);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            user_id = null;
            date = null;
            today = null;
            tawRmChangedutyDAO = null;
            allQueryVector = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            inputdate_from = null;
            inputdate_to = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 选择要显示的换班历史记录的起止时间
     */
    private ActionForward performSelecthistory(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        Vector SelectRoom = null;
        Vector SelectRoomName = null;
        TawRmAssignworkBO tawRmAssignworkBO = null;
        // edit by wangheqi
        TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
        // TawValidatePrivBO tawValidatePrivBO=null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO=null;
        // TawApparatusroom tawApparatusroom=null;
        String strSelectRoomName = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            SelectRoom = new Vector();
            SelectRoomName = new Vector();

            if (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)) {
                tawRmAssignworkBO = new TawRmAssignworkBO(ds);
                SelectRoom = tawRmAssignworkBO.getRoomSelect();
            } else {
                // tawValidatePrivBO = new TawValidatePrivBO(ds);
                // SelectRoom =
                // tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(),
                // 2061, 2);
                SelectRoom = StaticMethod
                        .list2vector(privBO
                                .getPermissions(
                                        saveSessionBeanForm.getUserid(),
                                        com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
                                        com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
            }
            if (SelectRoom.size() > 0) {
                // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
                // tawApparatusroom = new TawApparatusroom();
                strSelectRoomName = "";

                for (int i = 0; i < SelectRoom.size(); i++) {
                    tawApparatusroom = cptroomBO
                            .getTawSystemCptroomById(new Integer(String
                                    .valueOf(SelectRoom.elementAt(i))), 0);
                    if (tawApparatusroom != null) {
                        strSelectRoomName = StaticMethod
                                .null2String(tawApparatusroom.getRoomname());
                        SelectRoomName.add(strSelectRoomName);
                    } else {
                        SelectRoomName.add(" ");
                    }
                }
            } else {
                return mapping.findForward("nopriv");
            }
            request.setAttribute("SelectRoom", SelectRoom);
            request.setAttribute("SelectRoomName", SelectRoomName);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            SelectRoom = null;
            SelectRoomName = null;
            tawRmAssignworkBO = null;
            privBO = null;
            // tawValidatePrivBO=null;
            cptroomBO = null;
            // tawApparatusroomDAO=null;
            tawApparatusroom = null;
            strSelectRoomName = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 显示申请换班的可选人
     */
    private ActionForward performApply(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String strSAVEFLAG = null;
        String user_id = null;
        String time_from = null;
        String time_to = null;
        Vector vector_from = null;
        Vector vector_to = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;
        // logBO logbo=null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        // TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        // roomId,user_id should get from session
        int roomId = 0;
        strSAVEFLAG = String.valueOf(request.getParameter("SAVEFLAG"));
        if (strSAVEFLAG.equals("true")) {
            roomId = Integer.parseInt(String.valueOf(request
                    .getAttribute("roomId")));
        } else {
            roomId = Integer.parseInt(String.valueOf(request
                    .getParameter("room_id")));
        }
        user_id = saveSessionBeanForm.getUserid();

        time_from = request.getParameter("time_from");
        time_to = request.getParameter("time_to");
        time_from = StaticMethod.getAddZero(time_from);
        time_to = StaticMethod.getAddZero(time_to);
        request.setAttribute("USERID", user_id);
        request.setAttribute("TIMEFROM", time_from);
        request.setAttribute("TIMETO", time_to);

        try {

            vector_from = new Vector();
            vector_to = new Vector();
            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            vector_from = tawRmChangedutyDAO.getApplyVector(roomId, user_id,
                    time_from, 0);
            vector_to = tawRmChangedutyDAO.getApplyVector(roomId, user_id,
                    time_to, 1);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("VECTORFROM", vector_from);
            request.setAttribute("VECTORTO", vector_to);
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"申请换班",StaticVariable.OPER,request.getRemoteAddr());

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            strSAVEFLAG = null;
            user_id = null;
            time_from = null;
            time_to = null;
            vector_from = null;
            vector_to = null;
            tawRmChangedutyDAO = null;
            // logbo=null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 保存换班申请
     */
    private ActionForward performSaveApply(ActionMapping mapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String string_radio_from = null;
        String string_radio_to = null;
        Date date = null;
        GregorianCalendar cal = null;
        String inpudate = null;
        String time_from = null;
        String time_to = null;
        String user_id = null;
        TawRmChangedutyBO tawRmChangedutyBO = null;
        Vector vector_from = null;
        Vector vector_to = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        // TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        int roomId = Integer.parseInt(String.valueOf(
                request.getParameter("roomId")).trim());
        string_radio_from = String.valueOf(request.getParameter("radio_from"));
        string_radio_to = String.valueOf(request.getParameter("radio_to"));

        date = new Date();
        cal = new GregorianCalendar();
        cal.setTime(date);
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int day = cal.get(cal.DATE);
        inpudate = String.valueOf(year) + "-" + String.valueOf(month) + "-"
                + String.valueOf(day);

        time_from = request.getParameter("time_from");
        time_to = request.getParameter("time_to");
        user_id = request.getParameter("user_id");

        try {
            tawRmChangedutyBO = new TawRmChangedutyBO(ds);
            // String reason =
            // StaticMethod.dbNStrRev(request.getParameter("reason"));
            // 这个reason是要插入到数据库中的，不需要经过字符转换
            String reason = StaticMethod.nullObject2String(request
                    .getParameter("reason"));
            try {
                tawRmChangedutyBO.apply(roomId, inpudate, string_radio_from,
                        string_radio_to, reason);
            } catch (Exception e) {
                e.printStackTrace();
                return mapping.findForward("sms");
            }
            vector_from = new Vector();
            vector_to = new Vector();
            // TawRmChangedutyBO tawRmChangedutyBO = new TawRmChangedutyBO(ds);
            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            vector_from = tawRmChangedutyDAO.getApplyVector(roomId, user_id,
                    time_from, 0);
            vector_to = tawRmChangedutyDAO.getApplyVector(roomId, user_id,
                    time_to, 1);
            request.setAttribute("roomId", String.valueOf(roomId));
            request.setAttribute("VECTORFROM", vector_from);
            request.setAttribute("VECTORTO", vector_to);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            string_radio_from = null;
            string_radio_to = null;
            date = null;
            cal = null;
            inpudate = null;
            time_from = null;
            time_to = null;
            user_id = null;
            tawRmChangedutyBO = null;
            vector_from = null;
            vector_to = null;
            tawRmChangedutyDAO = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 显示已提交的换班申请
     */
    private ActionForward performQueryapply(ActionMapping mapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String user_id = null;
        Date date = null;
        GregorianCalendar cal = null;
        String today = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;
        Vector receiveQueryVector = null;
        Vector sentQueryVector = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        // TawApparatusroomDAO tawApparatusroomDAO = null;
        // TawApparatusroom tawApparatusroom = null;
        String isManager = "0";
        Vector managerQueryVector = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }
        int roomId;

        // xiugaicheng jifangyu
        String roomid = request.getParameter("roomId");
        if (roomid == null) {
            roomId = Integer.parseInt((String) request.getAttribute("roomId"));
        } else {
            roomId = Integer.parseInt(roomid);
        }
        user_id = saveSessionBeanForm.getUserid();
        request.setAttribute("roomId", roomId + "");
        // should get from select_time
        date = new Date();
        cal = new GregorianCalendar();
        cal.setTime(date);
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int day = cal.get(cal.DATE);
        today = String.valueOf(year) + "-" + String.valueOf(month) + "-"
                + String.valueOf(day);
        TawSystemUser tawSystemUser = null;
        try {

            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            // tawRmChangedutyDAO.delete(today);

            receiveQueryVector = new Vector();
            sentQueryVector = new Vector();

            receiveQueryVector = tawRmChangedutyDAO.receiveQueryVector(roomId,
                    user_id);
            sentQueryVector = tawRmChangedutyDAO.sentQueryVector(roomId,
                    user_id);
            request.setAttribute("RECEIVEQUERYVECTOR", receiveQueryVector);
            request.setAttribute("SENTQUERYVECTOR", sentQueryVector);

            // tawApparatusroomDAO = new TawApparatusroomDAO(ds);
            tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
                    roomId), 0);
            /*
             * List assigns = PrivMgrLocator.geTawSystemPrivAssignManager()
             * .getAssignsByUrl(DutyConstacts.DUTY_TAW_RMCHANGE_DUTY);
             *
             * List users = new ArrayList(); List roles = new ArrayList();
             *
             * List userManager = new ArrayList(); // 既没信息管理员权限又不是本人创建的信息
             *
             * ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager)
             * ApplicationContextHolder
             * .getInstance().getBean("ItawSystemSubRoleManager");
             * ITawSystemUserManager userMgr = (ITawSystemUserManager)
             * ApplicationContextHolder
             * .getInstance().getBean("itawSystemUserManager");
             * ITawSystemUserRefRoleManager roleuser =
             * (ITawSystemUserRefRoleManager) ApplicationContextHolder
             * .getInstance().getBean("itawSystemUserRefRoleManager");
             *
             * for (Iterator it = assigns.iterator(); it.hasNext();) {
             * TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next(); //
             * 若为角色 if
             * (com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_ROLE
             * .equals(assign.getAssigntype())) { //去所有角色的人 userManager =
             * roleuser .getUserbyroleid(assign.getObjectid()); for (Iterator
             * iterator = userManager.iterator(); iterator .hasNext();) {
             * tawSystemUser = (TawSystemUser) iterator.next(); if
             * (user_id.equals(tawSystemUser.getUserid())) { isManager = "1"; } }
             *
             * roles .add(roleMgr.getTawSystemSubRole(assign .getObjectid())); } //
             * 若为用户 else if
             * (com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER
             * .equals(assign.getAssigntype())) {
             * users.add(userMgr.getUserByuserid(assign.getObjectid())); if
             * (user_id.equals(assign.getObjectid())) { isManager = "1"; } } }
             */
            if (user_id.equals("admin")) {
                isManager = "1";
            }

            // tawApparatusroom = tawApparatusroomDAO.retrieve(roomId);
            // if (isManager.equals("1")) {
            managerQueryVector = new Vector();
            managerQueryVector = tawRmChangedutyDAO.managerQueryVector(roomId);
            request.setAttribute("MANAGERQUERYVECTOR", managerQueryVector);

            // }
            request.setAttribute("ISMANAGER", isManager);

        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            user_id = null;
            date = null;
            cal = null;
            today = null;
            tawRmChangedutyDAO = null;
            receiveQueryVector = null;
            sentQueryVector = null;
            // tawApparatusroomDAO = null;
            cptroomBO = null;
            tawApparatusroom = null;
            isManager = null;
            managerQueryVector = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 处理换班申请
     */
    private ActionForward performTransactapply(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        // roomId,user_id should get from session
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String user_id = null;
        TawRmChangedutyBO tawRmChangedutyBO = null;
        // logBO logbo=null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        String roomId = request.getParameter("roomId");
        request.setAttribute("roomId", roomId);
        user_id = saveSessionBeanForm.getUserid();

        int transact_id = Integer.parseInt(request.getParameter("transact_id"));
        int transact_flag = Integer.parseInt(request
                .getParameter("transact_flag"));

        try {
            tawRmChangedutyBO = new TawRmChangedutyBO(ds);
            tawRmChangedutyBO.transactApplly(transact_id, transact_flag);
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"处理交换班",StaticVariable.OPER,request.getRemoteAddr());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            user_id = null;
            tawRmChangedutyBO = null;
            // logbo=null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 处理换班申请
     */
    private ActionForward performTransactAudit(ActionMapping mapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) {
        // TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        // roomId,user_id should get from session
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String user_id = null;
        TawRmChangedutyBO tawRmChangedutyBO = null;
        // logBO logbo=null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        String roomId = request.getParameter("roomId");
        request.setAttribute("roomId", roomId);
        user_id = saveSessionBeanForm.getUserid();

        int transact_id = Integer.parseInt(request.getParameter("transact_id"));
        int transact_flag = Integer.parseInt(request
                .getParameter("transact_flag"));

        try {
            tawRmChangedutyBO = new TawRmChangedutyBO(ds);
            tawRmChangedutyBO.transactApplly(transact_id, transact_flag);
            // logbo = new logBO(ds);
            // boolean bool =
            // logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"处理交换班",StaticVariable.OPER,request.getRemoteAddr());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            user_id = null;
            tawRmChangedutyBO = null;
            // logbo=null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 换班申请列表
     */
    private ActionForward performList(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        // edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        /*
         * SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         * httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");
         */
        // edit end
        String user_id = null;
        Date date = null;
        String today = null;
        TawRmChangedutyDAO tawRmChangedutyDAO = null;
        Vector allQueryVector = null;
        // edit by wangheqi
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;

        String inputdate_from = null;
        String inputdate_to = null;

        // saveSessionBeanForm =
        // (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        int roomId = Integer.parseInt(request.getParameter("roomId"));
        // inputdate_from = request.getParameter("inputdate_from")+" 00:00:00";
        // inputdate_to = request.getParameter("inputdate_to")+" 23:59:59";
        inputdate_from = request.getParameter("inputdate_from");
        inputdate_to = request.getParameter("inputdate_to");
        try {
            tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);
            allQueryVector = new Vector();

            allQueryVector = tawRmChangedutyDAO.allQueryVector(roomId,
                    inputdate_from, inputdate_to);
            request.setAttribute("ALLQUERYVECTOR", allQueryVector);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        } finally {
            saveSessionBeanForm = null;
            user_id = null;
            date = null;
            today = null;
            tawRmChangedutyDAO = null;
            allQueryVector = null;
            cptroomBO = null;
            // tawApparatusroomDAO = null;
            tawApparatusroom = null;
            inputdate_from = null;
            inputdate_to = null;
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 换班申请查看
     */
    private ActionForward performView(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        try {

            TawRmChangedutyDAO tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);

            int id = Integer.parseInt(request.getParameter("id"));
            TawRmChangeduty tawRmChangeduty = tawRmChangedutyDAO.retrieve(id);
            if (tawRmChangeduty == null) {
                ActionErrors aes = new ActionErrors();
                aes.add(aes.GLOBAL_ERROR, new ActionError(
                        "error.object.notfound", "TawRmChangeduty"));
                saveErrors(request, aes);

            } else {
                org.apache.commons.beanutils.BeanUtils.populate(form,
                        org.apache.commons.beanutils.BeanUtils
                                .describe(tawRmChangeduty));
                form.setInputdate(tawRmChangeduty.getInputdate().toString());
                form
                        .setExpireddate(tawRmChangeduty.getExpireddate()
                                .toString());
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 换班申请保存
     */
    private ActionForward performSave(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;

        try {

            TawRmChangedutyDAO tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);

            TawRmChangeduty tawRmChangeduty = new TawRmChangeduty();
            org.apache.commons.beanutils.BeanUtils.populate(tawRmChangeduty,
                    org.apache.commons.beanutils.BeanUtils.describe(form));
            /*
             * tawRmChangeduty.setInputdate(java.sql.Timestamp.valueOf(form.getInputdate()));
             * tawRmChangeduty.setExpireddate(java.sql.Timestamp.valueOf(form.getExpireddate()));
             */
            int strutsAction = form.getStrutsAction();
            if (strutsAction == TawRmChangedutyForm.ADD) {
                int id = tawRmChangeduty.getId();
                if (tawRmChangedutyDAO.retrieve(id) == null) {
                    tawRmChangedutyDAO.insert(tawRmChangeduty);
                } else {
                    sqlDuplicateError(request, "TawRmChangeduty");
                    return mapping.findForward("failure");
                }
            } else if (strutsAction == TawRmChangedutyForm.EDIT) {
                tawRmChangedutyDAO.update(tawRmChangeduty);
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 换班申请编辑
     */
    private ActionForward performEdit(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {
        TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        form.setStrutsAction(TawRmChangedutyForm.EDIT);
        try {

            TawRmChangedutyDAO tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);

            int id = Integer.parseInt(request.getParameter("id"));

            TawRmChangeduty tawRmChangeduty = tawRmChangedutyDAO.retrieve(id);
            org.apache.commons.beanutils.BeanUtils.populate(form,
                    org.apache.commons.beanutils.BeanUtils
                            .describe(tawRmChangeduty));
            form.setInputdate(tawRmChangeduty.getInputdate().toString());
            form.setExpireddate(tawRmChangeduty.getExpireddate().toString());
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    private ActionForward performAdd(ActionMapping mapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) {
        TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        form.setStrutsAction(TawRmChangedutyForm.ADD);
        return mapping.findForward("success");
    }

    private ActionForward performRemove(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {
        return performView(mapping, actionForm, request, response);
    }

    private ActionForward performTrash(ActionMapping mapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        TawRmChangedutyForm form = (TawRmChangedutyForm) actionForm;
        try {

            TawRmChangedutyDAO tawRmChangedutyDAO = new TawRmChangedutyDAO(ds);

            int id = Integer.parseInt(request.getParameter("id"));
            tawRmChangedutyDAO.delete(id);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
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
