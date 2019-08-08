<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.boco.eoms.duty.mgr.*" %>
<%@ page import="com.boco.eoms.base.util.*" %>
<%@ page import="com.boco.eoms.commons.system.session.form.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.boco.eoms.commons.system.user.model.*" %>
<jsp:directive.page import="com.boco.eoms.base.webapp.listener.UserCounterListener"/>

<html>
<head>
    <title><fmt:message key="webapp.name"/></title>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${app}/scripts/local/zh_CN.js"></script>
    <script type="text/javascript" src="${app}/scripts/base/eoms.js"></script>
    <script type="text/javascript">eoms.appPath = "${app}";</script>
    <link rel="stylesheet" type="text/css" href="${app}/styles/${theme}/index.css"/>
    <link rel="stylesheet" type="text/css" href="./jwchat/dhtmlxWindows/codebase/dhtmlxwindows.css">
    <link rel="stylesheet" type="text/css" href="./jwchat/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">

    <script src="./jwchat/dhtmlxWindows/codebase/dhtmlxcommon.js"></script>
    <script src="./jwchat/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
    <script src="./jwchat/dhtmlxWindows/codebase/dhtmlxcontainer.js"></script>

    <%
        PapersMgr papersMgr = (PapersMgr) ApplicationContextHolder.getInstance().getBean("papersMgr");
        TawSystemSessionForm sessionForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionForm.getUserid();
        Map map = (Map) papersMgr.getPerson();
        List list = (List) map.get("result");
        TawSystemUser tawSystemUser = new TawSystemUser();
        boolean a = false;
        for (int i = 0; i < list.size(); i++) {
            tawSystemUser = (TawSystemUser) list.get(i);
            String person = tawSystemUser.getUserid();
            if (person != null && person.equals(userId)) {
                a = true;
                break;
            }
        }
    %>
    script language="JavaScript">
    var jwchats = null;
    function loginIM() {
    if (jwchats && jwchats.isHidden) {
    jwchats.show();
    return false;
    }
    if (jwchats)
    return false;
    var jid = '<bean:write name='sessionform' property='userid'/>';
    var URL1 = './jwchat/jwchat.html?server=10.30.227.11&btype=binding&base=/jwchat/JHB/&pass=<bean:write
        name='sessionform' property='originPassword'/>&jid=<bean:write name='sessionform' property='userid'/>';

    // var URL1 =
    './jwchat/jwchat.html?server=10.30.227.11&btype=binding&base=/JHB/&pass=Boco1234&jid=yinyong&prio=medium';
    // var URL1 = 'http://10.30.227.11:8080/jwchat/jwchat.html?server=10.30.227.11&btype=binding&base=/jwchat/JHB/&pass=<bean:write
        name='sessionform' property='originPassword'/>&jid=<bean:write name='sessionform' property='userid'/>@10.30.227.11/jwchat';
    // jwchats[jid]=
    window.open('http://10.30.227.11:8080/jwchat/jwchat.html?server=10.30.227.11&btype=binding&base=/jwchat/JHB/&pass=<bean:write
        name='sessionform' property='originPassword'/>&jid=<bean:write name='sessionform' property='userid'/>@10.30.227.11/jwchat','<bean:write
        name='sessionform' property='username'/>','width=180,height=390,resizable=yes');

    //jwchats = window.open(URL1,makeWindowName(jid),'width=180,height=390,resizable=yes');
    dhxWins = new dhtmlXWindows();
    dhxWins.setImagePath("./jwchat/dhtmlxWindows/codebase/imgs/");
    jwchats = dhxWins.createWindow("wchat", 1000, 100, 250, 300);
    jwchats.setText("运维通");
    jwchats.id = 'wchat';
    jwchats.button("close").attachEvent("onClick", function() {
    jwchats.hide();
    });

    jwchats.button("stick").hide();
    jwchats.button("minmax1").hide();
    jwchats.button("minmax2").hide();
    jwchats.attachURL(URL1);
    }

    </script>
    <
    script
    type = "text/javascript" >
    if (<%=a%>==true
    )
    {
        window.open("${app}/duty/papers/papers.do?method=searchTixing");
    }

    //以下代码用来捕获窗口关闭事件（IE6有效,IE7关闭标签页无效,FireFox无效）
    if (eoms.isIE) { //firefox中没找到区分刷新和关闭的代码，不做判断
        window.attachEvent("onunload", f_bofore, false);  // IE使用这个
    }

    function openUser() {
        window.open('${app}/sysuser/tawSystemUsers.do?method=editUserInfo', '', 'width=600,height=600,scrollbars=yes');
    }

    function f_bofore(event) {
        var e = window.event || event;

        //ie6中工作正常
        var ie6_closetest = (window.screenLeft >= 10000);
        //ie7会把关闭标签页认为是刷新。并且鼠标放在关闭图标上按f5，也会判断为关闭。
        var ie7_closetest = (e.clientX > document.body.clientWidth && e.clientY < 0 || e.altKey);

        var url = '<c:url value="/sysuser/tawSystemUsers.do?method=saveUserLogOutClose"/>';

        if (eoms.isIE6 && ie6_closetest) {
            window.location.href = url;
        } else if (eoms.isIE7 && ie7_closetest) {
            window.location.href = url;
        } else {
        }
    }

    //打开在线用户列表窗口
    function openOnlineUserWindow() {
        var url = '${app}/workbench/onlineuser/tawWorkbenchOnlineUsers.do';
        window.open(url, '', 'channelMode, menubar=no, toolbar=no, location=no, directories=no, status=yes, resizable=yes, scrollbars=yes, width=600, height=400, fullscreen=no');
    }

    </script>
</head>

<body scroll="no">

<!-- loading -->
<div id="loading-mask">&#160;</div>
<div id="loading">
    <div class="loading-indicator">数据装载中,请稍候...</div>
</div>

<%@ include file="/common/extlibs.jsp" %>
<!-- HEADER -->
<div id="header">
    <div id="top">
        <div id="logo">
            <div id="functions">支持人员邓先晖：13765150083 |
                <a style="text-decoration: none;" href="#" onclick="javascript:loginIM();"><img width="18" height="18"
                                                                                                alt="登录运维通"
                                                                                                src="${app}/images/spark.png">登录运维通</a>
                |
                <a href="javascript:openUser()">你好,${sessionScope.sessionform.username}</a> |
                <a href="${app}/roomSelectArea.do?method=roomSelectArea" target="_top">切换机房</a> |
                <a href="javascript:openOnlineUserWindow();">在线用户：<%=UserCounterListener.getOnlineUserCounter()%>人</a> |
                <a href="javascript:if(confirm('确认退出系统么？')){location.href='${app}/sysuser/tawSystemUsers.do?method=saveUserLogOut';}">退出系统</a>
            </div>
        </div>
    </div>

    <div id="menubar">
        <%@ include file="/common/menu.jsp" %>
    </div>
</div>
<%--
<iframe id="portal-north" name="portal-north" frameborder="no" width="0" height="0"
  src="${scheme}://${serverName}:${serverPort}/fbrole.proper.loginSystem.do?EOSOperator/userID=<bean:write name='sessionform' property='userid'/>&EOSOperator/password=<bean:write name='sessionform' property='password'/>" ></iframe>

<iframe id="bbs-north" name="flow-north" frameborder="no" width="0" height="0"
  src="http://10.32.2.16:9080/eoms35/j_security_check?j_username=<bean:write name='sessionform' property='id'/>&j_password=<bean:write name='sessionform' property='password'/>" ></iframe>
 --%>
<!-- iframe -->
<iframe id="mainFrame" name="mainFrame" frameborder="0" width="0" height="0"
        src="<c:url value="${welcomePage}"/>"></iframe>
<script type="text/javascript" src="${app}/scripts/layout/index.js"></script>
</body>
</html>