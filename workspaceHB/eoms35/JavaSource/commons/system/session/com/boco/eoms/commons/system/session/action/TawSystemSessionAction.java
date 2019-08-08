package com.boco.eoms.commons.system.session.action;

//import javax.security.auth.Subject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Map;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.*;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;

import com.boco.eoms.commons.system.priv.a_fsh.Ip_Double_choice;
import com.boco.eoms.commons.system.priv.a_fsh.Ipconfig;
import com.boco.eoms.commons.system.priv.a_fsh.Ipconfig_creat;
import com.boco.eoms.commons.system.priv.a_fsh.XmlPath;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.bo.TawSystemSessionBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;

import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.dao.TawRmSysteminfoDAO;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmRecord;
import com.boco.eoms.duty.model.TawRmSysteminfo;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;

public class TawSystemSessionAction extends BaseAction {
    // log4j
    private static Logger logger = Logger
            .getLogger(TawSystemSessionAction.class);

    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
            .getInstance();

    /*
     * public ActionForward execute(ActionMapping mapping, ActionForm form,
     * HttpServletRequest request, HttpServletResponse response) {
     *
     * ActionForward forword = null; // String currentaction = ""; //
     * currentaction = mapping.getParameter();
     *
     * forword = performInit(mapping, form, request, response);
     *
     * return forword; }
     */

    public ActionForward performInit(ActionMapping mapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) {

        TawSystemSessionForm form = (TawSystemSessionForm) actionForm;
        String userid = "";
        userid = form.getUserid();
        TawSystemSessionForm sessionform = TawSystemSessionBo
                .getSessionForm(userid);
        // TawCommonLog.saveLog(this, userid, request.getRemoteAddr(), "0001",
        // userid + " 于:" + StaticMethod.getCurrentDateTime() + " 登陆系统.");
        TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
        String modeName = privassimgr.getNameBycode(request.getParameter("id"));
        sessionform.setOriginPassword(form.getOriginPassword());
        logSave log = logSave.getInstance(modeName, userid,
                "0001", request.getRemoteAddr(), userid + " 于:"
                        + StaticMethod.getCurrentDateTime() + " 登录系统.", "111");
        log.info();
        sessionform.setRomteaddr(request.getRemoteAddr());
        request.getSession().setAttribute("sessionform", sessionform);

        // FIXME 初使化值班、机房信息，模拟
        // sessionform.setStartDuty(new Date());
        // sessionform.setEndDuty(new Date());
        // 初始化数据
        // ITawwpStubUserMgr
        // tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

        ITawwpStubUserMgr tawwpStubUserMgr = (ITawwpStubUserMgr) request
                .getAttribute("tawwpStubUserMgr");
        List stubUserVOList = new ArrayList();

        try {
            // 获取代理人信息VO对象集合

            stubUserVOList = tawwpStubUserMgr.listStubUserByStubuser(userid);
        } catch (Exception e) {
            System.out.print(e);

        }
        sessionform.setStubUserList(stubUserVOList);
        // int roomId = Integer.parseInt(sessionform.getRoomId());

        // TODO 初始化值班号，0为未值班
        /*
         * sessionform.setWorkSerial("1"); try { String workSerial =
         * tawRmAssignworkDAO.getAssignWork(roomid, userid, date);
         * sessionform.setWorkSerial(workSerial); } catch (Exception e) {
         * e.printStackTrace(); }
         */
        // 在session中保存流程引擎登陆信息，add by qinmin
		/*
		  try{ String password=sessionform.getPassword();
		  IWorkflowSecutiryService safeService=
		  (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
		  Subject subject = safeService.logIn(userid, password);
		  request.getSession().setAttribute("wpsSubject",subject); }
		  catch(Exception e){ BocoLog.error(this,"保存流程登陆信息报错："+e.getMessage()); }*/


        TawSystemTreeBo usertree = TawSystemTreeBo.getInstance();

        // FIXME 项目所在物理路径所有人都用一个路径，
        // FIXME 已经统一，并不用每个session中都放一个，需要修改,目前仅是针对王老资料管理上传所用
        // 添加项目所在物理路径
        try {
            sessionform.setRealPath(StaticMethod.getWebPath());
        } catch (FileNotFoundException e) {
            logger.error(e);
        }
        // if (sessionform.isAdmin() && !sessionform.isHavePriv()) {
        EOMSAttributes attr = (EOMSAttributes) ApplicationContextHolder.getInstance().getBean("eomsAttributes");
        String menu_ip = attr.getMenu_ipOpen();
        String ip = request.getServerName();
        int port = request.getServerPort();
        if ("".equals(XmlPath.getXmlpath())) {
            String doubleIp = request.getRealPath("");
            XmlPath.setXmlpath(doubleIp);
        }
        if ("admin".equals(sessionform.getUserid())) {
            if (menu_ip.equals("on")) {
                System.out.println(ip + ":" + port);
                String type = Ip_Double_choice.checkIp(ip + ":" + port);
                Ipconfig ipconfig = Ipconfig_creat.getIpconfig();
                request.getSession().setAttribute("menu",
                        Ip_Double_choice.ip2DoubleIpFromUI(usertree.getPrivAdminMenu(userid), ipconfig, type));
            } else {

                request.getSession().setAttribute("menu",
                        usertree.getPrivAdminMenu(userid));
            }
            //request.getSession().setAttribute("menu",
            //usertree.getPrivAdminMenu(userid));
        } else if (request.getParameter("wapLogin") == null) { // 表示eoms登陆
            if (menu_ip.equals("on")) {
                String type = Ip_Double_choice.checkIp(ip + ":" + port);
                Ipconfig ipconfig = Ipconfig_creat.getIpconfig();
                request.getSession().setAttribute(
                        "menu",
                        Ip_Double_choice.ip2DoubleIpFromUI(
                                PrivMgrLocator.getPrivMgr().operations2json(
                                        PrivMgrLocator.getPrivMgr().listOpertion(
                                                sessionform.getUserid(), sessionform.getDeptid(),
                                                sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                                                StaticVariable.ROOT_NODE)), ipconfig, type)
                );
            } else {
                request.getSession().setAttribute(
                        "menu",
                        PrivMgrLocator.getPrivMgr().operations2json(
                                PrivMgrLocator.getPrivMgr().listOpertion(
                                        sessionform.getUserid(), sessionform.getDeptid(),
                                        sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                                        StaticVariable.ROOT_NODE)));

                // request.getSession().setAttribute("menu",
                // usertree.getPrivUserMenu(userid));
            }
			/*request.getSession().setAttribute(
					"menu",
					PrivMgrLocator.getPrivMgr().operations2json(
							PrivMgrLocator.getPrivMgr().listOpertion(
									sessionform.getUserid(), sessionform.getDeptid(),
									sessionform.getRolelist(),PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
									StaticVariable.ROOT_NODE)));
			List afds = new ArrayList();
			afds = PrivMgrLocator.getPrivMgr().listOpertion(
			sessionform.getUserid(), sessionform.getDeptid(),
			sessionform.getRolelist(),PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
			StaticVariable.ROOT_NODE);
			  List b = afds;*/

            // request.getSession().setAttribute("menu",
            // usertree.getPrivUserMenu(userid));

        } else { // 表示wap登陆
            List wapMainList = PrivMgrLocator.getPrivMgr().listOpertion(
                    sessionform.getUserid(), sessionform.getDeptid(),
                    sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                    StaticVariable.ROOT_NODE, StaticVariable.NATURE_WAP);
            request.getSession().setAttribute(
                    "menuMain", wapMainList);
            HashMap map = new HashMap();
            HashMap mapNext = new HashMap();
            String flagNull = "0";
            for (Iterator it = wapMainList.iterator(); it.hasNext(); ) {
                TawSystemPrivOperation operation = (TawSystemPrivOperation) it.next();
                List wapLeafList = PrivMgrLocator.getPrivMgr().listOpertionwap(
                        sessionform.getUserid(), sessionform.getDeptid(),
                        sessionform.getRolelist(),
                        PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operation.getCode());

                for (Iterator itNext = wapLeafList.iterator(); itNext.hasNext(); ) {
                    TawSystemPrivOperation operationNext = (TawSystemPrivOperation) itNext.next();
                    List wapLeafListNext = PrivMgrLocator.getPrivMgr().listOpertionwap(
                            sessionform.getUserid(), sessionform.getDeptid(),
                            sessionform.getRolelist(),
                            PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operationNext.getCode());
                    if (!wapLeafListNext.isEmpty()) {
                        flagNull = "1";
                    }
                    mapNext.put(operationNext.getId(), wapLeafListNext);
                }
                map.put(operation.getId(), wapLeafList);
            }
            request.getSession().setAttribute("menuLeaf", map);
            request.getSession().setAttribute("menuLeafNext", mapNext);
            request.getSession().setAttribute("flagNull", flagNull);


            return mapping.findForward("wapIndex");
        }


        request.getSession().setMaxInactiveInterval(-1);

        Vector vecName = new Vector();
        TawSystemCptroom tawApparatusroom = null;
        Vector vecId = new Vector();

        // 得到机房id 机房名称
        // 所属机房
        String room_id = sessionform.getRoomId();
        String room_Name = sessionform.getRoomname();
        if (room_id == null || room_id.equals("")) {
            room_id = "";
            room_Name = "";
        } else {
            String userId = sessionform.getUserid();
            request.setAttribute("userid", userId);
            String[] roomNameArray = room_Name.split(",");
            for (int i = 0; i < roomNameArray.length; i++) {
                vecName.add(roomNameArray[i]);

            }
            String[] roomIdArray = room_id.split(",");
            for (int j = 0; j < roomIdArray.length; j++) {
                vecId.add(roomIdArray[j]);
            }
        }
        // 机房域
        String roomid = "";
        String roomName = "";

        List list = TawSystemSessionBo.getRoomInfo(userid);
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            tawApparatusroom = (TawSystemCptroom) it.next();
            roomid = tawApparatusroom.getId() + "";
            roomName = tawApparatusroom.getRoomname() + "（已排班）";
            if (!vecName.contains(tawApparatusroom.getRoomname())) {
                vecId.add(roomid);
                vecName.add(roomName);
            }
        }

        int size = vecId.size();
        if (size == 0) {


            sessionform.setRoomId("0");
            sessionform.setRoomname("");
            sessionform.setWorkSerial("0");
            //add by lijin 2015/04/22
            sessionform.setAttribute1(form.getAttribute1());
            sessionform.setAttribute2(form.getAttribute2());
            if ("old".equals(request.getParameter("old")))
                return mapping.findForward("success1");
            else
                return mapping.findForward("success");


        } else if (size == 1) {

            sessionform.setRoomId(StaticMethod.null2String((String) vecId
                    .elementAt(0)));
            sessionform.setRoomname(StaticMethod.null2String((String) vecName
                    .elementAt(0)));
            return mapping.findForward("workserialselect");
        } else {

            request.setAttribute("vecId", vecId);
            request.setAttribute("vecName", vecName);
            return mapping.findForward("roomSelect");
        }

    }

    /**
     * add by gongyufeng at 2008-6-6 为了页面选择机房
     */
    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward roomSelect(ActionMapping mapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) {
        return mapping.findForward("success");
    }

    public ActionForward roomSelectArea(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        // String roomID = sessionform.getRoomId();
        String userid = sessionform.getUserid();
        Vector vecName = new Vector();
        TawSystemCptroom tawApparatusroom = null;
        Vector vecId = new Vector();
        try {

            String roomid = "";
            String roomName = "";

            List list = TawSystemSessionBo.getRoomInfo(userid);
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                tawApparatusroom = (TawSystemCptroom) it.next();
                roomid = tawApparatusroom.getId() + "";
                roomName = tawApparatusroom.getRoomname() + "（已排班）";
                if (!vecName.contains(tawApparatusroom.getRoomname())) {
                    vecId.add(roomid);
                    vecName.add(roomName);
                }
            }

            int size = vecId.size();
            if (size == 0) {
                sessionform.setRoomId("0");
                sessionform.setRoomname("");
                sessionform.setWorkSerial("0");
                return mapping.findForward("success");

            } else if (size == 1) {

                sessionform.setRoomId(roomid);
                sessionform.setRoomname(roomName);
                return mapping.findForward("workserialselect");
            } else {

                request.setAttribute("vecId", vecId);
                request.setAttribute("vecName", vecName);
                return mapping.findForward("roomSelect");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("false");
        }

    }

    /*
     * add by gongyufeng at 2008-6-6 为了页面选择班次
     */
    public ActionForward workserialselect(ActionMapping mapping,
                                          ActionForm actionForm, HttpServletRequest request,
                                          HttpServletResponse response) {
        String forward = "success";
        String roomName = "";
        try {
            ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String roomID = saveSessionBeanForm.getRoomId();
            if (roomID.indexOf(",") > 0) {
                roomID = request.getParameter("Wrf_RoomID");
                roomName = mgr.getTawSystemCptroomName(new Integer(Integer
                        .parseInt(roomID)));
                saveSessionBeanForm.setRoomId(roomID);
                saveSessionBeanForm.setRoomname(roomName);
            }
            String userid = saveSessionBeanForm.getUserid();
            TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);
            // SaveSessionBeanDAO saveSessionBeanDAO = new
            // SaveSessionBeanDAO(ds);
            // saveSessionBeanDAO.alterDateFormat();
            int workserial = 0;
            workserial = tawRmRecordDAO.receiverWorkSerial(roomID);
            if (workserial > 0) {
                TawRmRecord tawRmRecord = tawRmRecordDAO.retrieve(workserial);
                int intFlag = 0;
                if (tawRmRecord != null) {
                    intFlag = tawRmRecord.getFlag();
                }
                if (!(tawRmRecordDAO.receiverIsDutyMan(workserial,
                        saveSessionBeanForm.getUserid()) && intFlag == 0)) {
                    workserial = 0;
                }
                tawRmRecord = null;
            }

            // 取得最大误差时间
            TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO
                    .retrieve(roomID);
            int maxerrortime = 0;
            if (tawRmSysteminfo != null) {
                maxerrortime = tawRmSysteminfo.getMaxerrortime();
            }
            // 当前时间+最大误差时间，是否属于下班次
            GregorianCalendar cal_start = new GregorianCalendar();
            cal_start.add(cal_start.MINUTE, maxerrortime);
            String time_start = String.valueOf(cal_start.get(cal_start.YEAR));
            time_start = time_start + "-"
                    + String.valueOf(cal_start.get(cal_start.MONTH) + 1);
            time_start = time_start + "-"
                    + String.valueOf(cal_start.get(cal_start.DATE));
            time_start = time_start + " "
                    + String.valueOf(cal_start.get(cal_start.HOUR_OF_DAY));
            time_start = time_start + ":"
                    + String.valueOf(cal_start.get(cal_start.MINUTE));
            time_start = time_start + ":"
                    + String.valueOf(cal_start.get(cal_start.SECOND));
            int workserial2 = tawRmRecordDAO.receiverWorkSerial(roomID,
                    StaticMethod.getLocalString(time_start), userid);
            if (workserial2 > 0) {
                TawRmRecord tawRmRecord = tawRmRecordDAO.retrieve(workserial2);
                int intFlag = 0;
                if (tawRmRecord != null) {
                    intFlag = tawRmRecord.getFlag();
                }
                if (!(tawRmRecordDAO.receiverIsDutyMan(workserial2,
                        saveSessionBeanForm.getUserid()) && intFlag == 0)) {
                    workserial2 = 0;
                    // null
                }
                tawRmRecord = null;
            }

            // 当前时间-最大误差时间，是否属于上班次

            GregorianCalendar cal_end = new GregorianCalendar();
            maxerrortime = -maxerrortime;
            cal_end.add(cal_end.MINUTE, maxerrortime);
            String time_end = String.valueOf(cal_end.get(cal_end.YEAR));
            time_end = time_end + "-"
                    + String.valueOf(cal_end.get(cal_end.MONTH) + 1);
            time_end = time_end + "-"
                    + String.valueOf(cal_end.get(cal_end.DATE));
            time_end = time_end + " "
                    + String.valueOf(cal_end.get(cal_end.HOUR_OF_DAY));
            time_end = time_end + ":"
                    + String.valueOf(cal_end.get(cal_end.MINUTE));
            time_end = time_end + ":"
                    + String.valueOf(cal_end.get(cal_end.SECOND));
            int workserial1 = tawRmRecordDAO.receiverWorkSerial(roomID,
                    StaticMethod.getLocalString(time_end), userid);
            if (workserial1 > 0) {
                TawRmRecord tawRmRecord = tawRmRecordDAO.retrieve(workserial1);
                int intFlag = 0;
                if (tawRmRecord != null) {
                    intFlag = tawRmRecord.getFlag();
                }
                if (!(tawRmRecordDAO.receiverIsDutyMan(workserial1,
                        saveSessionBeanForm.getUserid()) && intFlag == 0)) {
                    workserial1 = 0;
                }
                tawRmRecord = null;
            }

            // 班次的向量
            Vector vec_workserial_id = new Vector();
            Vector vec_workserial_name = new Vector();
            // 取起止时间
            TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
            TawRmAssignwork tawRmAssignwork = new TawRmAssignwork();
            if (workserial1 > 0 && workserial1 != workserial) {
                vec_workserial_id.add(String.valueOf(workserial1));
                tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
                tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial1);
                if (tawRmAssignwork != null) {
                    String str_workserial_name = StaticMethod
                            .null2String(String.valueOf(tawRmAssignwork
                                    .getStarttimeDefined()))
                            + "  -->  "
                            + StaticMethod.null2String(String
                            .valueOf(tawRmAssignwork
                                    .getEndtimeDefined()));
                    vec_workserial_name.add(str_workserial_name);
                }
            }

            if (workserial > 0) {
                vec_workserial_id.add(String.valueOf(workserial));
                tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
                tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial);
                if (tawRmAssignwork != null) {
                    String str_workserial_name = StaticMethod
                            .null2String(String.valueOf(tawRmAssignwork
                                    .getStarttimeDefined()))
                            + "  -->  "
                            + StaticMethod.null2String(String
                            .valueOf(tawRmAssignwork
                                    .getEndtimeDefined()));
                    vec_workserial_name.add(str_workserial_name);
                }
            }
            if ((workserial2 > 0) && (workserial2 != workserial)) {
                if ((workserial2 + "").equals(workserial1 + "")) {
                    System.out.println((workserial2 + "").equals(workserial1 + ""));
                } else {

                    vec_workserial_id.add(String.valueOf(workserial2));
                    tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
                    tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial2);
                    if (tawRmAssignwork != null) {
                        String str_workserial_name = StaticMethod
                                .null2String(String.valueOf(tawRmAssignwork
                                        .getStarttimeDefined()))
                                + "  -->  "
                                + StaticMethod.null2String(String
                                .valueOf(tawRmAssignwork
                                        .getEndtimeDefined()));
                        vec_workserial_name.add(str_workserial_name);
                    }
                }
            }
            // null
            tawRmRecordDAO = null;
            // saveSessionBeanDAO = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_start = null;
            time_start = null;
            time_end = null;
            tawRmAssignworkDAO = null;
            tawRmAssignwork = null;

            // 如果属于多班次，让用户选择
            if (vec_workserial_id.size() > 1) {
                request.setAttribute("VECWORKSERIALID", vec_workserial_id);
                request.setAttribute("VECWORKSERIALNAME", vec_workserial_name);
                // -1说明有多班次，0说明无班次，>0说明有一个班次
                saveSessionBeanForm.setWorkSerial("-1");

                // null
                vec_workserial_id = null;
                vec_workserial_name = null;
                return mapping.findForward("workserialselect");
            } else {
                // null
                vec_workserial_id = null;
                vec_workserial_name = null;
                if (workserial > 0) {
                    saveSessionBeanForm.setWorkSerial(workserial + "");
                    request.setAttribute("str_workserial", workserial + "");
                    return mapping.findForward("saveworkserial");
                } else if (workserial1 > 0) {
                    workserial = workserial1;
                    saveSessionBeanForm.setWorkSerial(workserial + "");
                    request.setAttribute("str_workserial", workserial + "");
                    return mapping.findForward("saveworkserial");
                } else if (workserial2 > 0) {
                    workserial = workserial2;
                    saveSessionBeanForm.setWorkSerial(workserial + "");
                    request.setAttribute("str_workserial", workserial + "");
                    return mapping.findForward("saveworkserial");
                } else {
                    return mapping.findForward(forward);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure");
        }

    }

    /*
     * add by mios at 2008-6-24 选择机房后以ajax方式取得班次信息
     */
    public void xOnSelectRoom(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request, HttpServletResponse response) {
        String roomName = "";
        try {
            ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            String roomID = request.getParameter("Wrf_RoomID");
            if (roomID != null) {
                roomName = mgr.getTawSystemCptroomName(new Integer(Integer
                        .parseInt(roomID)));
                saveSessionBeanForm.setRoomname(roomName);
            }
            saveSessionBeanForm.setRoomId(roomID);

            TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);
            String userid = saveSessionBeanForm.getUserid();
            int workserial = 0;
            workserial = tawRmRecordDAO.receiverWorkSerial(roomID);
            if (workserial > 0) {
                TawRmRecord tawRmRecord = tawRmRecordDAO.retrieve(workserial);
                int intFlag = 0;
                if (tawRmRecord != null) {
                    intFlag = tawRmRecord.getFlag();
                }
                if (!(tawRmRecordDAO.receiverIsDutyMan(workserial,
                        saveSessionBeanForm.getUserid()) && intFlag == 0)) {
                    workserial = 0;
                }
                tawRmRecord = null;
            }

            // 取得最大误差时间
            TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
            TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO
                    .retrieve(roomID);
            int maxerrortime = 0;
            if (tawRmSysteminfo != null) {
                maxerrortime = tawRmSysteminfo.getMaxerrortime();
            }
            // 当前时间+最大误差时间，是否属于下班次
            GregorianCalendar cal_start = new GregorianCalendar();
            cal_start.add(cal_start.MINUTE, maxerrortime);
            String time_start = String.valueOf(cal_start.get(cal_start.YEAR));
            time_start = time_start + "-"
                    + String.valueOf(cal_start.get(cal_start.MONTH) + 1);
            time_start = time_start + "-"
                    + String.valueOf(cal_start.get(cal_start.DATE));
            time_start = time_start + " "
                    + String.valueOf(cal_start.get(cal_start.HOUR_OF_DAY));
            time_start = time_start + ":"
                    + String.valueOf(cal_start.get(cal_start.MINUTE));
            time_start = time_start + ":"
                    + String.valueOf(cal_start.get(cal_start.SECOND));
            int workserial2 = tawRmRecordDAO.receiverWorkSerial(roomID,
                    StaticMethod.getLocalString(time_start), userid);
            if (workserial2 > 0) {
                TawRmRecord tawRmRecord = tawRmRecordDAO.retrieve(workserial2);
                int intFlag = 0;
                if (tawRmRecord != null) {
                    intFlag = tawRmRecord.getFlag();
                }
                if (!(tawRmRecordDAO.receiverIsDutyMan(workserial2,
                        saveSessionBeanForm.getUserid()) && intFlag == 0)) {
                    workserial2 = 0;
                    // null
                }
                tawRmRecord = null;
            }

            // 当前时间-最大误差时间，是否属于上班次
            GregorianCalendar cal_end = new GregorianCalendar();
            maxerrortime = -maxerrortime;
            cal_end.add(cal_end.MINUTE, maxerrortime);
            String time_end = String.valueOf(cal_end.get(cal_end.YEAR));
            time_end = time_end + "-"
                    + String.valueOf(cal_end.get(cal_end.MONTH) + 1);
            time_end = time_end + "-"
                    + String.valueOf(cal_end.get(cal_end.DATE));
            time_end = time_end + " "
                    + String.valueOf(cal_end.get(cal_end.HOUR_OF_DAY));
            time_end = time_end + ":"
                    + String.valueOf(cal_end.get(cal_end.MINUTE));
            time_end = time_end + ":"
                    + String.valueOf(cal_end.get(cal_end.SECOND));
            int workserial1 = tawRmRecordDAO.receiverWorkSerial(roomID,
                    StaticMethod.getLocalString(time_end), userid);
            if (workserial1 > 0) {
                TawRmRecord tawRmRecord = tawRmRecordDAO.retrieve(workserial1);
                int intFlag = 0;
                if (tawRmRecord != null) {
                    intFlag = tawRmRecord.getFlag();
                }
                if (!(tawRmRecordDAO.receiverIsDutyMan(workserial1,
                        saveSessionBeanForm.getUserid()) && intFlag == 0)) {
                    workserial1 = 0;
                }
                tawRmRecord = null;
            }

            JSONArray arrJson = new JSONArray();
            // 取起止时间
            TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
            TawRmAssignwork tawRmAssignwork = new TawRmAssignwork();
            if (workserial1 > 0 && workserial1 != workserial) {
                tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
                tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial1);
                if (tawRmAssignwork != null) {
                    String str_workserial_name = StaticMethod
                            .null2String(String.valueOf(tawRmAssignwork
                                    .getStarttimeDefined()))
                            + "  -->  "
                            + StaticMethod.null2String(String
                            .valueOf(tawRmAssignwork
                                    .getEndtimeDefined()));

                    JSONObject obj = new JSONObject();
                    obj.put(UIConstants.JSON_ID, String.valueOf(workserial1));
                    obj.put(UIConstants.JSON_NAME, str_workserial_name);
                    arrJson.put(obj);
                }
            }

            if (workserial > 0) {
                tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
                tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial);
                if (tawRmAssignwork != null) {
                    String str_workserial_name = StaticMethod
                            .null2String(String.valueOf(tawRmAssignwork
                                    .getStarttimeDefined()))
                            + "  -->  "
                            + StaticMethod.null2String(String
                            .valueOf(tawRmAssignwork
                                    .getEndtimeDefined()));

                    JSONObject obj = new JSONObject();
                    obj.put(UIConstants.JSON_ID, String.valueOf(workserial));
                    obj.put(UIConstants.JSON_NAME, str_workserial_name);
                    arrJson.put(obj);
                }
            }
            if (workserial2 > 0 && workserial2 != workserial && workserial2 != workserial1) {
                tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
                tawRmAssignwork = tawRmAssignworkDAO.retrieve(workserial2);
                if (tawRmAssignwork != null) {
                    String str_workserial_name = StaticMethod
                            .null2String(String.valueOf(tawRmAssignwork
                                    .getStarttimeDefined()))
                            + "  -->  "
                            + StaticMethod.null2String(String
                            .valueOf(tawRmAssignwork
                                    .getEndtimeDefined()));

                    JSONObject obj = new JSONObject();
                    obj.put(UIConstants.JSON_ID, String.valueOf(workserial2));
                    obj.put(UIConstants.JSON_NAME, str_workserial_name);
                    arrJson.put(obj);
                }
            }
            // null
            tawRmRecordDAO = null;
            // saveSessionBeanDAO = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_start = null;
            time_start = null;
            time_end = null;
            tawRmAssignworkDAO = null;
            tawRmAssignwork = null;

            // 如果属于多班次，让用户选择
            if (arrJson.length() > 1) {
                // -1说明有多班次，0说明无班次，>0说明有一个班次
                saveSessionBeanForm.setWorkSerial("-1");
            } else {
                if (workserial > 0) {
                    saveSessionBeanForm.setWorkSerial(workserial + "");
                } else if (workserial1 > 0) {
                    workserial = workserial1;
                    saveSessionBeanForm.setWorkSerial(workserial + "");
                } else if (workserial2 > 0) {
                    workserial = workserial2;
                    saveSessionBeanForm.setWorkSerial(workserial + "");
                } else {
                    saveSessionBeanForm.setWorkSerial("0");
                }
            }

            JSONUtil.print(response, arrJson.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * add by gongyufeng at 2008-6-6 保存页面内选择的东西
     */
    public ActionForward saveWorkSerial(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {

        String forward = "success";

        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        int workserial = 0;
        String workid = request.getParameter("str_workserial");
        if (workid == null) {
            workid = (String) request.getAttribute("str_workserial");
            if (workid == null) {
                saveSessionBeanForm.setWorkSerial("0");
                return mapping.findForward(forward);
            }
        } else {
            workserial = Integer.parseInt(String.valueOf(
                    request.getParameter("str_workserial")).trim());
            saveSessionBeanForm.setWorkSerial(workserial + "");
            if (workserial == 0) {
                return mapping.findForward(forward);
            }
        }
        if (workserial == 0) {
            workserial = Integer.parseInt(String.valueOf(saveSessionBeanForm
                    .getWorkSerial().trim()));
        }
        if (workserial > 0) {
            // 应该是大于0的
            try {
                request.getSession().setAttribute("sessionform",
                        saveSessionBeanForm);
                TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(
                        ds);
                TawRmAssignwork tawRmAssignwork = tawRmAssignworkDAO
                        .retrieve(workserial);
                if (tawRmAssignwork != null) {
                    saveSessionBeanForm.setWorkSerialTime(StaticMethod
                            .null2String(String.valueOf(tawRmAssignwork
                                    .getStarttimeDefined()))
                            + "  -->  "
                            + StaticMethod.null2String(String
                            .valueOf(tawRmAssignwork
                                    .getEndtimeDefined())));
                }
                saveSessionBeanForm.setStartDuty(tawRmAssignwork
                        .getStarttimeDefined());
                saveSessionBeanForm.setEndDuty(tawRmAssignwork
                        .getEndtimeDefined());
                String userId = StaticMethod.null2String(saveSessionBeanForm
                        .getUserid());
                TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);
                // 值班班长
                String DutyMaster = tawRmRecordDAO
                        .receiverDutyMaster(workserial);
                System.out.println("dutymaster=" + DutyMaster);
                // 是否是值班班长
                boolean IsDutyMaster;
                if (userId.equals(DutyMaster)) {
                    IsDutyMaster = true;
                } else {
                    IsDutyMaster = false;
                }
                saveSessionBeanForm.setIsDutyMaster(IsDutyMaster);

                // 判断值班住记录是否存在'
                boolean CheckMainRecordExist = tawRmRecordDAO
                        .receiverCheckMainRecordExist(workserial);
                if (CheckMainRecordExist == false) {
                    // 如果不存在创建值班主记录
                    String roomid = saveSessionBeanForm.getRoomId();
                    tawRmRecordDAO.getNewManiRecord(workserial, roomid);
                }
                // 判断值班子记录是否存在
                boolean CheckSubRecordExist = tawRmRecordDAO
                        .receiverCheckSubRecordExist(workserial, userId);
                // 如果不存在生成值班子记录
                if (CheckSubRecordExist == false) {
                    tawRmRecordDAO.getSaveSubRecord(workserial, userId);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return mapping.findForward("failure");
            }
        } else {
            return mapping.findForward("failure");
        }
        return mapping.findForward(forward);
    }

    /**
     * wap登陆
     *
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     */
    public ActionForward performLogin(ActionMapping mapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) {

        TawSystemSessionForm form = (TawSystemSessionForm) actionForm;
        String userid = "";
        // 取用户名称
        userid = form.getUserid();
        TawSystemSessionForm sessionform = TawSystemSessionBo
                .getSessionForm(userid);
        TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
        String modeName = privassimgr.getNameBycode(request.getParameter("id"));
        logSave log = logSave.getInstance(modeName, userid,
                "0001", request.getRemoteAddr(), userid + " 于:"
                        + StaticMethod.getCurrentDateTime() + " 登录wap系统.", "111");
        log.info();
        sessionform.setRomteaddr(request.getRemoteAddr());
        request.getSession().setAttribute("sessionform", sessionform);
        //
        TawSystemTreeBo usertree = TawSystemTreeBo.getInstance();
        try {
            sessionform.setRealPath(StaticMethod.getWebPath());

            // if (sessionform.isAdmin() && !sessionform.isHavePriv()) {
            if ("admin".equals(sessionform.getUserid())) {
                request.getSession().setAttribute("menu",
                        usertree.getPrivAdminMenu(userid));
            } else if (request.getParameter("wapLogin") == null) { // 表示eoms登陆
                request.getSession().setAttribute(
                        "menu",
                        PrivMgrLocator.getPrivMgr().operations2json(
                                PrivMgrLocator.getPrivMgr().listOpertion(
                                        sessionform.getUserid(), sessionform.getDeptid(),
                                        sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                                        StaticVariable.ROOT_NODE)));

                // request.getSession().setAttribute("menu",
                // usertree.getPrivUserMenu(userid));

            } else { // 表示wap登陆
                // 取用户权限下的菜单,为前台显示
                List wapMainList = PrivMgrLocator.getPrivMgr().listOpertion(
                        sessionform.getUserid(), sessionform.getDeptid(),
                        sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                        StaticVariable.ROOT_NODE, StaticVariable.NATURE_WAP);
                request.getSession().setAttribute(
                        "menuMain", wapMainList);
                HashMap map = new HashMap();
                HashMap mapNext = new HashMap();
                String flagNull = "0";
                for (Iterator it = wapMainList.iterator(); it.hasNext(); ) {
                    TawSystemPrivOperation operation = (TawSystemPrivOperation) it.next();
                    List wapLeafList = PrivMgrLocator.getPrivMgr().listOpertionwap(
                            sessionform.getUserid(), sessionform.getDeptid(),
                            sessionform.getRolelist(),
                            PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operation.getCode());
                    // 逐级向下取.
                    for (Iterator itNext = wapLeafList.iterator(); itNext.hasNext(); ) {
                        TawSystemPrivOperation operationNext = (TawSystemPrivOperation) itNext.next();
                        List wapLeafListNext = PrivMgrLocator.getPrivMgr().listOpertionwap(
                                sessionform.getUserid(), sessionform.getDeptid(),
                                sessionform.getRolelist(),
                                PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operationNext.getCode());
                        if (!wapLeafListNext.isEmpty()) {
                            flagNull = "1";
                        }
                        mapNext.put(operationNext.getId(), wapLeafListNext);
                    }
                    map.put(operation.getId(), wapLeafList);
                }
                //为前台准备数据
                request.getSession().setAttribute("menuLeaf", map);
                request.getSession().setAttribute("menuLeafNext", mapNext);
                request.getSession().setAttribute("flagNull", flagNull);


                return mapping.findForward("wapIndex");
            }


            request.getSession().setMaxInactiveInterval(-1);
        } catch (FileNotFoundException e) {
            logger.error(e);
            return mapping.findForward("relogin");
        }

        return null;
    }

    /**
     * add by gongyufeng
     */
    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 返回时候使用
     */
    public ActionForward performWapBack(ActionMapping mapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) {

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userid = sessionform.getUserid();
        TawSystemTreeBo usertree = TawSystemTreeBo.getInstance();
        try {
            sessionform.setRealPath(StaticMethod.getWebPath());

            // if (sessionform.isAdmin() && !sessionform.isHavePriv()) {
            if ("admin".equals(sessionform.getUserid())) {
                request.getSession().setAttribute("menu",
                        usertree.getPrivAdminMenu(userid));
            } else if (request.getParameter("wapLogin") == null) { // 表示eoms登陆
                request.getSession().setAttribute(
                        "menu",
                        PrivMgrLocator.getPrivMgr().operations2json(
                                PrivMgrLocator.getPrivMgr().listOpertion(
                                        sessionform.getUserid(), sessionform.getDeptid(),
                                        sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                                        StaticVariable.ROOT_NODE)));

                // request.getSession().setAttribute("menu",
                // usertree.getPrivUserMenu(userid));

            } else { // 表示wap登陆
                List wapMainList = PrivMgrLocator.getPrivMgr().listOpertion(
                        sessionform.getUserid(), sessionform.getDeptid(),
                        sessionform.getRolelist(), PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                        StaticVariable.ROOT_NODE, StaticVariable.NATURE_WAP);
                request.getSession().setAttribute(
                        "menuMain", wapMainList);
                HashMap map = new HashMap();
                HashMap mapNext = new HashMap();
                String flagNull = "0";
                for (Iterator it = wapMainList.iterator(); it.hasNext(); ) {
                    TawSystemPrivOperation operation = (TawSystemPrivOperation) it.next();
                    List wapLeafList = PrivMgrLocator.getPrivMgr().listOpertionwap(
                            sessionform.getUserid(), sessionform.getDeptid(),
                            sessionform.getRolelist(),
                            PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operation.getCode());

                    for (Iterator itNext = wapLeafList.iterator(); itNext.hasNext(); ) {
                        TawSystemPrivOperation operationNext = (TawSystemPrivOperation) itNext.next();
                        List wapLeafListNext = PrivMgrLocator.getPrivMgr().listOpertionwap(
                                sessionform.getUserid(), sessionform.getDeptid(),
                                sessionform.getRolelist(),
                                PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION, operationNext.getCode());
                        if (!wapLeafListNext.isEmpty()) {
                            flagNull = "1";
                        }
                        mapNext.put(operationNext.getId(), wapLeafListNext);
                    }
                    map.put(operation.getId(), wapLeafList);
                }
                request.getSession().setAttribute("menuLeaf", map);
                request.getSession().setAttribute("menuLeafNext", mapNext);
                request.getSession().setAttribute("flagNull", flagNull);


                return mapping.findForward("wapIndex");
            }
            request.getSession().setMaxInactiveInterval(-1);
        } catch (FileNotFoundException e) {
            logger.error(e);
            return mapping.findForward("relogin");
        }

        return null;
    }


}
