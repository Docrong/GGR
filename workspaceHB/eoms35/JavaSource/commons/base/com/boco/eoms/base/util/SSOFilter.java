package com.boco.eoms.base.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.security.auth.Subject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.boco.eoms.commons.log.service.logSave;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.session.bo.TawSystemSessionBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;

import edu.yale.its.tp.cas.client.ProxyTicketValidator;
import edu.yale.its.tp.cas.client.Util;
import edu.yale.its.tp.cas.client.filter.CASFilter;
import edu.yale.its.tp.cas.client.filter.CASFilterRequestWrapper;

public class SSOFilter extends CASFilter {
    // *********************************************************************
    // Constants

    /**
     * Session attribute in which the username is stored
     */
    public final static String CAS_FILTER_USER = "edu.yale.its.tp.cas.client.filter.user";

    // *********************************************************************
    // Configuration state

    private String casLogin, casValidate, casAuthorizedProxy, casServiceUrl,
            casRenew, casServerName;

    private boolean wrapRequest;

    // *********************************************************************
    // Initialization

    public void init(FilterConfig config) throws ServletException {
        casLogin = config
                .getInitParameter("edu.yale.its.tp.cas.client.filter.loginUrl");
        casValidate = config
                .getInitParameter("edu.yale.its.tp.cas.client.filter.validateUrl");
        casServiceUrl = config
                .getInitParameter("edu.yale.its.tp.cas.client.filter.serviceUrl");
        casAuthorizedProxy = config
                .getInitParameter("edu.yale.its.tp.cas.client.filter.authorizedProxy");
        casRenew = config
                .getInitParameter("edu.yale.its.tp.cas.client.filter.renew");
        casServerName = config
                .getInitParameter("edu.yale.its.tp.cas.client.filter.serverName");
        wrapRequest = Boolean
                .valueOf(
                        config
                                .getInitParameter("edu.yale.its.tp.cas.client.filter.wrapRequest"))
                .booleanValue();

    }

    // *********************************************************************
    // Filter processing

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain fc) throws ServletException, IOException {
        EOMSAttributes attributes = (EOMSAttributes) ApplicationContextHolder
                .getInstance().getBean("eomsAttributes");

        // 判断是否调用service接口
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        if (null != requestURI
                && isInterfaceCall(requestURI, attributes.getInterfaceList())) {
            // 请求链接中包含service直接转至chain的下一个filter
            fc.doFilter(request, response);
            return;
        }
        // 根据访问的server端IP地址从配置文件中读取对应的CAS服务器的IP地址
        String serverName = request.getServerName();
        String casAddr = ((String) attributes.getRegister().get(serverName))
                .trim();
        casLogin = casAddr + "/login";
        System.out.println("===================liulei=================");
        // make sure we've got an HTTP request
        if (!(request instanceof HttpServletRequest)
                || !(response instanceof HttpServletResponse))
            throw new ServletException("CASFilter protects only HTTP resources");

        // Wrap the request if desired
        if (wrapRequest) {
            request = new CASFilterRequestWrapper((HttpServletRequest) request);
        }

        HttpSession session = ((HttpServletRequest) request).getSession();

        // if our attribute's already present, don't do anything
        if (session != null && session.getAttribute(CAS_FILTER_USER) != null) {
            fc.doFilter(request, response);
            return;
        }

        // otherwise, we need to authenticate via CAS
        String ticket = request.getParameter("ticket");

        // no ticket? abort request processing and redirect
        if (ticket == null || ticket.equals("")) {
            if (casLogin == null) {
                throw new ServletException(
                        "When CASFilter protects pages that do not receive a 'ticket' "
                                + "parameter, it needs a edu.yale.its.tp.cas.client.filter.loginUrl "
                                + "filter parameter");
            }
            ((HttpServletResponse) response).sendRedirect(casLogin
                    + "?service="
                    + getService((HttpServletRequest) request)
                    + ((casRenew != null && !casRenew.equals("")) ? "&renew="
                    + casRenew : ""));
            // abort chain
            return;
        }

        // Yay, ticket! Validate it.
        String user = getAuthenticatedUser((HttpServletRequest) request);
        if (user == null)
            throw new ServletException("Unexpected CAS authentication error");

        // Store the authenticated user in the session
        if (session != null) // probably unncessary
        {
            session.setAttribute(CAS_FILTER_USER, user);


//				 加载eoms用户,by leo
            String userId = user;
            if (userId != null) {
                // 获取用户session信息
                TawSystemSessionForm tsf = (TawSystemSessionForm) ((HttpServletRequest) request)
                        .getSession().getAttribute("sessionform");
                if (tsf == null || !userId.equals(tsf.getUserid())) {

                    TawSystemSessionForm sessionform = new TawSystemSessionForm();
                    sessionform.setUserid(userId);
                    ITawwpStubUserMgr tawwpStubUserMgr = (ITawwpStubUserMgr) ApplicationContextHolder
                            .getInstance().getBean("tawwpStubUserMgr");

                    sessionform = TawSystemSessionBo.getSessionForm(userId);

                    TawSystemPrivAssignOut privassimgr = TawSystemPrivAssignOut.getInstance();
                    String modeName = privassimgr.getNameBycode(request.getParameter("id"));

                    logSave log = logSave.getInstance(
                            modeName, userId, "0001", request
                                    .getRemoteAddr(), userId + " 于:"
                                    + StaticMethod.getCurrentDateTime() + " 登录系统.",
                            "111");

                    log.info();
                    sessionform.setRomteaddr(request.getRemoteAddr());

                    List stubUserVOList = new ArrayList();

                    try {
                        // 获取代理人信息VO对象集合

                        stubUserVOList = tawwpStubUserMgr
                                .listStubUserByStubuser(userId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sessionform.setStubUserList(stubUserVOList);

                    // 在session中保存流程引擎登陆信息，add by qinmin

                    try {
                        String password = sessionform.getPassword();
                        IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
                                .getInstance().getBean(
                                        "WorkflowSecutiryService");
                        Subject subject = safeService.logIn(userId,
                                password);
                        ((HttpServletRequest) request).getSession()
                                .setAttribute("wpsSubject", subject);
                    } catch (Exception e) {
                        BocoLog.error(this, "保存流程登陆信息报错：" + e.getMessage());
                    }

                    TawSystemTreeBo usertree = TawSystemTreeBo
                            .getInstance();

                    // 添加项目所在物理路径
                    try {
                        sessionform.setRealPath(StaticMethod.getWebPath());
                    } catch (FileNotFoundException e) {

                    }

                    if ("admin".equals(sessionform.getUserid())) {
                        ((HttpServletRequest) request).getSession()
                                .setAttribute("menu",
                                        usertree.getPrivAdminMenu(userId));
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

                    List list = TawSystemSessionBo.getRoomInfo(userId);
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

                    } else {

                        sessionform.setRoomId(StaticMethod
                                .null2String((String) vecId.elementAt(0)));
                        sessionform
                                .setRoomname(StaticMethod
                                        .null2String((String) vecName
                                                .elementAt(0)));
                    }
                    ((HttpServletRequest) request).getSession()
                            .setAttribute("sessionform", sessionform);

                }
            }
        }

        // end


        // continue processing the request
        fc.doFilter(request, response);
    }

    // *********************************************************************
    // Destruction

    public void destroy() {
    }

    // *********************************************************************
    // Utility methods

    /**
     * Converts a ticket parameter to a username, taking into account an
     * optionally configured trusted proxy in the tier immediately in front of
     * us.
     */
    private String getAuthenticatedUser(HttpServletRequest request)
            throws ServletException {
        ProxyTicketValidator pv = null;
        try {
            pv = new ProxyTicketValidator();
            pv.setCasValidateUrl(casValidate);
            pv.setServiceTicket(request.getParameter("ticket"));
            pv.setService(getService(request));
            pv.setRenew(Boolean.valueOf(casRenew).booleanValue());
            pv.validate();
            if (!pv.isAuthenticationSuccesful())
                throw new ServletException("CAS authentication error: "
                        + pv.getErrorCode() + ": " + pv.getErrorMessage());
            if (pv.getProxyList().size() != 0) {
                // ticket was proxied
                if (casAuthorizedProxy == null) {
                    throw new ServletException(
                            "this page does not accept proxied tickets");
                } else {
                    boolean authorized = false;
                    String proxy = (String) pv.getProxyList().get(0);
                    StringTokenizer casProxies = new StringTokenizer(
                            casAuthorizedProxy);
                    while (casProxies.hasMoreTokens()) {
                        if (proxy.equals(casProxies.nextToken())) {
                            authorized = true;
                            break;
                        }
                    }
                    if (!authorized) {
                        throw new ServletException(
                                "unauthorized top-level proxy: '"
                                        + pv.getProxyList().get(0) + "'");
                    }
                }
            }
            return pv.getUser();
        } catch (SAXException ex) {
            String xmlResponse = "";
            if (pv != null)
                xmlResponse = pv.getResponse();
            throw new ServletException(ex + " " + xmlResponse);
        } catch (ParserConfigurationException ex) {
            throw new ServletException(ex);
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Returns either the configured service or figures it out for the current
     * request. The returned service is URL-encoded.
     */
    private String getService(HttpServletRequest request)
            throws ServletException {
        // ensure we have a server name or service name
        if (casServerName == null && casServiceUrl == null)
            throw new ServletException(
                    "need one of the following configuration "
                            + "parameters: edu.yale.its.tp.cas.client.filter.serviceUrl or "
                            + "edu.yale.its.tp.cas.client.filter.serverName");

        // use the given string if it's provided
        if (casServiceUrl != null)
            return URLEncoder.encode(casServiceUrl);
        else
            // otherwise, return our best guess at the service
            return Util.getService(request, casServerName);
    }

    private boolean isInterfaceCall(String requestURI, List interfaceList) {
        for (Iterator it = interfaceList.iterator(); it.hasNext(); ) {
            String interfaceKey = StaticMethod.nullObject2String(it.next())
                    .trim();
            if (requestURI.indexOf(interfaceKey) > 0) {
                return true;
            }
        }
        return false;
    }


}
