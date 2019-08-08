// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SSOFilter.java

package com.boco.eoms.portal.sso.client;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.service.IPrivMgr;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.bo.TawSystemSessionBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;
import com.boco.iam.sso.client.LoginUtil;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Referenced classes of package com.boco.eoms.portal.sso.client:
//			ClientUtil, SSOConstants

public class SSOFilter
        implements Filter {

    private String encoding;

    public SSOFilter() {
        encoding = "";
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        response.addHeader("P3P", "CP=CAO PSA OUR");
        setEncoding(request, response);
        String token = request.getParameter("ticket");
        System.out.println("doFilter.token===" + token);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        String sessionToken = (String) request.getSession().getAttribute("ticket");
        String userid_test = (String) request.getSession().getAttribute("sso_account");
        String requestPage = request.getRequestURI();
        if (sessionToken == null) {
            System.out.println("+++++++SESSION=null+++++++");
            boolean checkURL = ClientUtil.checkURL(requestPage);
            System.out.println("requestPageURL=" + requestPage + "checkURL=" + checkURL);
            if (checkURL && token != null && !token.equals("")) {
                String onLineUser = ClientUtil.checkOnLineNew(request);
                System.out.println(onLineUser);
                ITawSystemUserManager iTawSystemUserManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
                TawSystemUser tawSystemUser = iTawSystemUserManager.getUserByuserid(onLineUser);
                String useridexist = StaticMethod.null2String(tawSystemUser.getUserid());
                if (onLineUser == null || "".equals(onLineUser) || useridexist == null || "".equals(useridexist))
                    response.sendRedirect(basePath + SSOConstants.AC_FAILURE + "?" + "sysmsg" + "=" + "USER_UNLOGIN");
                else if (onLineUser.equals("UNAUTHORIZED_IP_ACCESS") || onLineUser.equals("USER_UNLOGIN") || onLineUser.equals("SYS_ERROR") || onLineUser.equals("SYS_ERROR_DB") || onLineUser.equals("SYS_ERROR_NO_PARAMETER")) {
                    response.sendRedirect(basePath + SSOConstants.AC_FAILURE + "?" + "sysmsg" + "=" + onLineUser);
                } else {
                    request.getSession().setAttribute("ticket", token);
                    request.getSession().setAttribute("sso_account", onLineUser);
                    String userid = onLineUser;
                    TawSystemSessionForm sessionform = TawSystemSessionBo.getSessionForm(userid);
                    TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
                    String modeName = privassimgr.getNameBycode(request.getParameter("id"));
                    logSave log = logSave.getInstance(modeName, userid, "0001", request.getRemoteAddr(), userid + " 于:" + StaticMethod.getCurrentDateTime() + " 登录系统.", "111");
                    log.info();
                    sessionform.setRomteaddr(request.getRemoteAddr());
                    request.getSession().setAttribute("sessionform", sessionform);
                    ITawwpStubUserMgr tawwpStubUserMgr = (ITawwpStubUserMgr) request.getAttribute("tawwpStubUserMgr");
                    List stubUserVOList = new ArrayList();
                    try {
                        stubUserVOList = tawwpStubUserMgr.listStubUserByStubuser(userid);
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                    sessionform.setStubUserList(stubUserVOList);
                    TawSystemTreeBo usertree = TawSystemTreeBo.getInstance();
                    try {
                        sessionform.setRealPath(StaticMethod.getWebPath());
                    } catch (FileNotFoundException filenotfoundexception) {
                    }
                    if ("admin".equals(sessionform.getUserid()))
                        request.getSession().setAttribute("menu", usertree.getPrivAdminMenu(userid));
                    else if (request.getParameter("wapLogin") == null) {
                        request.getSession().setAttribute("menu", PrivMgrLocator.getPrivMgr().operations2json(PrivMgrLocator.getPrivMgr().listOpertion(sessionform.getUserid(), sessionform.getDeptid(), sessionform.getRolelist(), "MOUDLE_FUNCTION", "-1")));
                    } else {
                        List wapMainList = PrivMgrLocator.getPrivMgr().listOpertion(sessionform.getUserid(), sessionform.getDeptid(), sessionform.getRolelist(), "MOUDLE_FUNCTION", "-1", "0");
                        request.getSession().setAttribute("menuMain", wapMainList);
                        HashMap map = new HashMap();
                        HashMap mapNext = new HashMap();
                        String flagNull = "0";
                        TawSystemPrivOperation operation;
                        List wapLeafList;
                        for (Iterator it = wapMainList.iterator(); it.hasNext(); map.put(operation.getId(), wapLeafList)) {
                            operation = (TawSystemPrivOperation) it.next();
                            wapLeafList = PrivMgrLocator.getPrivMgr().listOpertionwap(sessionform.getUserid(), sessionform.getDeptid(), sessionform.getRolelist(), "MOUDLE_FUNCTION", operation.getCode());
                            TawSystemPrivOperation operationNext;
                            List wapLeafListNext;
                            for (Iterator itNext = wapLeafList.iterator(); itNext.hasNext(); mapNext.put(operationNext.getId(), wapLeafListNext)) {
                                operationNext = (TawSystemPrivOperation) itNext.next();
                                wapLeafListNext = PrivMgrLocator.getPrivMgr().listOpertionwap(sessionform.getUserid(), sessionform.getDeptid(), sessionform.getRolelist(), "MOUDLE_FUNCTION", operationNext.getCode());
                                if (!wapLeafListNext.isEmpty())
                                    flagNull = "1";
                            }

                        }

                        request.getSession().setAttribute("menuLeaf", map);
                        request.getSession().setAttribute("menuLeafNext", mapNext);
                        request.getSession().setAttribute("flagNull", flagNull);
                    }
                    request.getSession().setMaxInactiveInterval(-1);
                    Vector vecName = new Vector();
                    TawSystemCptroom tawApparatusroom = null;
                    Vector vecId = new Vector();
                    String room_id = sessionform.getRoomId();
                    String room_Name = sessionform.getRoomname();
                    if (room_id == null || room_id.equals("")) {
                        room_id = "";
                        room_Name = "";
                    } else {
                        String userId = sessionform.getUserid();
                        request.setAttribute("userid", userId);
                        String roomNameArray[] = room_Name.split(",");
                        for (int i = 0; i < roomNameArray.length; i++)
                            vecName.add(roomNameArray[i]);

                        String roomIdArray[] = room_id.split(",");
                        for (int j = 0; j < roomIdArray.length; j++)
                            vecId.add(roomIdArray[j]);

                    }
                    Integer roomid = new Integer("");
                    String roomName = "";
                    List list = TawSystemSessionBo.getRoomInfo(userid);
                    for (Iterator it = list.iterator(); it.hasNext(); ) {
                        tawApparatusroom = (TawSystemCptroom) it.next();
                        roomid = tawApparatusroom.getId();
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
                    } else if (size == 1) {
                        sessionform.setRoomId(StaticMethod.null2String((String) vecId.elementAt(0)));
                        sessionform.setRoomname(StaticMethod.null2String((String) vecName.elementAt(0)));
                    } else {
                        request.setAttribute("vecId", vecId);
                        request.setAttribute("vecName", vecName);
                    }
                }
            }
        }
        chain.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0)
            throws ServletException {
        try {
            encoding = arg0.getInitParameter("encoding");
            ClientUtil.initAppConfig("/appconfig.properties");
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/config.properties");
            BocoLog.info(this, "初始化配置文件路径为：" + filePath);
            LoginUtil.getInstance().init(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }

    private void setEncoding(ServletRequest request, ServletResponse response)
            throws UnsupportedEncodingException {
        HttpServletRequest Request = (HttpServletRequest) request;
        if (encoding != null && !"".equals(encoding))
            Request.setCharacterEncoding(encoding);
        if (encoding != null && !encoding.equals(""))
            Request.setCharacterEncoding(encoding);
    }

    public static void main(String args[]) {
        try {
            String filePath = StaticMethod.getFilePathForUrl("classpath:config/config.properties");
            LoginUtil.getInstance().init(filePath);
        } catch (Exception exception) {
        }
    }
}
