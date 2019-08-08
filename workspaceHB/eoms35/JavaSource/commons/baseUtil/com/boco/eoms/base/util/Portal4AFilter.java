package com.boco.eoms.base.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.security.auth.Subject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.action.TawSystemSessionAction;
import com.boco.eoms.commons.system.session.bo.TawSystemSessionBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;

/**
 * <p>
 * Title:eoms接入门户及4a时用的filter。
 * </p>
 * <p>
 * Description:获取用户名，初始化用户信息到session中。
 * </p>
 * <p>
 * 通过从header和Parameter和session三种方式获取用户名进行初始化。配置方法是在web.xml里添加Portal4AFilter，并且配置ft_paramtype和ft_paramname。
 * </p>
 * <p>
 * Date:Mar 17, 2009 11:47:13 AM
 * </p>
 *
 * @author wangbeiying
 * @version 3.5.0
 */
public class Portal4AFilter implements Filter {

    private static Logger logger = Logger
            .getLogger(TawSystemSessionAction.class);

    /*
     * 用户名参数的获取方式，"filter_param"：通过request.getParameter获取 or
     * "filter_header"：通过((HttpServletRequest)
     * request).getHeader(paramName)获取；or
     * "filter_session"：通过((HttpServletRequest)
     * request).getSession().getAttribute(paramName)获取；
     */
    private String paramType;

    private String paramName;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            boolean isTure = true;

            Object objeser = null;
            if (paramType.equals("filter_param")) {
                objeser = request.getParameter(paramName);
            } else if (paramType.equals("filter_session")) {
                objeser = ((HttpServletRequest) request).getSession()
                        .getAttribute(paramName);
            } else if (paramType.equals("filter_header")) {
                objeser = ((HttpServletRequest) request).getHeader(paramName);
            } else {
                logger
                        .info("didn't get into filter. filter_param:"
                                + paramType);
                chain.doFilter(request, response);
                isTure = false;
            }

            if (isTure) {

                String ssoUser = objeser == null ? "" : ((String) objeser)
                        .trim();
                logger.info("ssoUser:\"" + ssoUser + "\"");
                if (!ssoUser.equals("")) {
                    logger.info("in_ssoUser:\"" + ssoUser + "\"");
                    // TawSystemSessionForm form = (TawSystemSessionForm)
                    // actionForm;
                    // String userid = "";
                    TawSystemSessionForm sessionform = null;
                    String userid = ssoUser;

                    Object osessionfrom = ((HttpServletRequest) request)
                            .getSession().getAttribute("sessionform");
                    if (osessionfrom != null)
                        sessionform = (TawSystemSessionForm) osessionfrom;

                    if (osessionfrom == null
                            || !(sessionform.getUserid().equals(userid))) {//当session中没有初始化或者初始化话的用户与请求的用户不一致是，重新获取用户信息。
                        sessionform = TawSystemSessionBo.getSessionForm(userid);
                        // TawCommonLog.saveLog(this, userid,
                        // request.getRemoteAddr(), "0001",
                        // userid + " 于:" + StaticMethod.getCurrentDateTime() +
                        // "
                        // 登陆系统.");
                        logSave log = logSave.getInstance(this.getClass()
                                .getName(), userid, "0001", request
                                .getRemoteAddr(), userid + " 于:"
                                + StaticMethod.getCurrentDateTime()
                                + " 那个过SSO登录系统.", "111");
                        log.info();
                        sessionform.setRomteaddr(request.getRemoteAddr());
                        ((HttpServletRequest) request).getSession()
                                .setAttribute("sessionform", sessionform);

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

                            stubUserVOList = tawwpStubUserMgr
                                    .listStubUserByStubuser(userid);
                        } catch (Exception e) {
                            System.out.print(e);

                        }
                        sessionform.setStubUserList(stubUserVOList);
                        // int roomId =
                        // Integer.parseInt(sessionform.getRoomId());

                        // TODO 初始化值班号，0为未值班
                        /*
                         * sessionform.setWorkSerial("1"); try { String
                         * workSerial = tawRmAssignworkDAO.getAssignWork(roomid,
                         * userid, date); sessionform.setWorkSerial(workSerial); }
                         * catch (Exception e) { e.printStackTrace(); }
                         */
                        // 在session中保存流程引擎登陆信息，add by qinmin
                        /**/
                        try {
                            String password = sessionform.getPassword();
                            IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
                                    .getInstance().getBean(
                                            "WorkflowSecutiryService");
                            Subject subject = safeService.logIn(userid,
                                    password);
                            ((HttpServletRequest) request).getSession()
                                    .setAttribute("wpsSubject", subject);
                        } catch (Exception e) {
                            BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
                        }

                        TawSystemTreeBo usertree = TawSystemTreeBo
                                .getInstance();

                        // FIXME 项目所在物理路径所有人都用一个路径，
                        // FIXME 已经统一，并不用每个session中都放一个，需要修改,目前仅是针对王老资料管理上传所用
                        // 添加项目所在物理路径
                        try {
                            sessionform.setRealPath(StaticMethod.getWebPath());
                        } catch (FileNotFoundException e) {
                            logger.error(e);
                        }

                        // if (sessionform.isAdmin() &&
                        // !sessionform.isHavePriv()) {
                        if ("admin".equals(sessionform.getUserid())) {
                            ((HttpServletRequest) request).getSession()
                                    .setAttribute("menu",
                                            usertree.getPrivAdminMenu(userid));
                        } else {
                            ((HttpServletRequest) request)
                                    .getSession()
                                    .setAttribute(
                                            "menu",
                                            PrivMgrLocator
                                                    .getPrivMgr()
                                                    .operations2json(
                                                            PrivMgrLocator
                                                                    .getPrivMgr()
                                                                    .listOpertion(
                                                                            sessionform
                                                                                    .getUserid(),
                                                                            sessionform
                                                                                    .getDeptid(),
                                                                            sessionform
                                                                                    .getRolelist(),
                                                                            PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
                                                                            StaticVariable.ROOT_NODE)));

                            // request.getSession().setAttribute("menu",
                            // usertree.getPrivUserMenu(userid));
                        }

                        ((HttpServletRequest) request).getSession()
                                .setMaxInactiveInterval(-1);

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
                            if (!vecName.contains(tawApparatusroom
                                    .getRoomname())) {
                                vecId.add(roomid);
                                vecName.add(roomName);
                            }
                        }

                        int size = vecId.size();
                        if (size == 0) {
                            sessionform.setRoomId("0");
                            sessionform.setRoomname("");
                            sessionform.setWorkSerial("0");
                            // return mapping.findForward("success");

                        } else if (size >= 1) {

                            sessionform.setRoomId(StaticMethod
                                    .null2String((String) vecId.elementAt(0)));
                            sessionform
                                    .setRoomname(StaticMethod
                                            .null2String((String) vecName
                                                    .elementAt(0)));
                            // return mapping.findForward("workserialselect");
                            // } else {

                            // request.setAttribute("vecId", vecId);
                            // request.setAttribute("vecName", vecName);
                            // return mapping.findForward("roomSelect");
                        }
                    }
                }
                chain.doFilter(request, response);

            }

        } catch (Exception ee) {
            ee.printStackTrace();
            BocoLog.error(this, ee.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig fc) throws ServletException {
        // TODO Auto-generated method stub

        this.paramType = fc.getInitParameter("ft_paramtype").trim()
                .toLowerCase();

        this.paramName = fc.getInitParameter("ft_paramname").trim()
                .toLowerCase();

    }

}
