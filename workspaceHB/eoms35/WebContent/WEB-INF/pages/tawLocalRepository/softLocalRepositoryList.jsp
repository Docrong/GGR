<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<%@ taglib uri="/WEB-INF/tlds/priv.tld" prefix="priv" %>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}"/>
<c:set var="scheme" scope="page" value="${pageContext.request.scheme}"/>
<c:set var="serverName" scope="page" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" scope="page" value="${pageContext.request.serverPort}"/>
<c:set var="theme" scope="session" value="default"/>
<c:if test="${!empty param.theme}">
    <c:set var="theme" scope="session" value="${param.theme}"/>
</c:if>
<%-- Set all pages that include this page to use XHTML --%>
<html>
<head>
    <base target="netname">
    <title><fmt:message key="webapp.name"/></title>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="${app}/scripts/local/zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="${app}/scripts/base/eoms.js"></script>
    <script type="text/javascript">eoms.appPath = "${app}";</script>
    <link rel="stylesheet" type="text/css" href="${app}/styles/${theme}/theme.css"/>
    <!--[if IE]><link rel="stylesheet" type="text/css" href="${app}/styles/common/ie.css" /><![endif]-->
    <!-- EXT LIBS verson 1.1 -->
    <script type="text/javascript" src="${app}/scripts/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="${app}/scripts/ext/ext-all.js"></script>
    <script type="text/javascript" src="${app}/scripts/adapter/ext-ext.js"></script>
    <script type="text/javascript" src="${app}/scripts/ext/source/locale/ext-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="${app}/scripts/ext/resources/css/ext-all.css"/>
    <c:if test="${theme ne 'default'}">
        <link rel="stylesheet" type="text/css" href="${app}/scripts/ext/resources/css/xtheme-gray.css"/>
    </c:if>
    <link rel="stylesheet" type="text/css" href="${app}/styles/${theme}/ext-adpter.css"/>
    <!--[if IE]><link rel="stylesheet" type="text/css" href="${app}/styles/common/ie.css" /><![endif]-->
</head>

<body>
<div id="page">
    <%-- Put constants into request scope --%>
    <eoms:constants scope="request"/>
    <div id="content" class="clearfix">
        <div id="main"><br/><br/>
            <script type="text/javascript">
                var checkflag = "false";
                var myObject = window.dialogArguments;
                var softwareRepository = myObject.softwareRepository;
                var mainCellInfo = myObject.mainCellInfo;
                //var softwareRepositoryName = myObject.softwareRepositoryName;
                window.name = 'netname';

                function choose() {

                    var objs = document.getElementsByName("id");
                    if (checkflag == "false") {
                        for (var i = 0; i < objs.length; i++) {
                            if (objs[i].type.toLowerCase() == "checkbox")
                                objs[i].checked = true;
                            checkflag = "true";
                        }
                    } else if (checkflag == "true") {
                        for (var i = 0; i < objs.length; i++) {
                            if (objs[i].type.toLowerCase() == "checkbox")
                                objs[i].checked = false;
                            checkflag = "false";
                        }
                    }
                }

                function selectNet(obj) {
                    var returnValue = "";
                    var objs = document.getElementsByName("id");
                    for (var i = 0; i < objs.length; i++) {
                        var repository = objs[i].value.split(",");
                        if (objs[i].type.toLowerCase() == "checkbox" && objs[i].checked == true) {
                            if (softwareRepository == '' || softwareRepository == null) {
                                softwareRepository = repository[1];
                                //softwareRepositoryName = repository[2];
                                if (returnValue != '') {
                                    returnValue += "," + repository[0];
                                } else {
                                    returnValue += repository[0];
                                }
                            } else if (softwareRepository == repository[1]) {
                                if (returnValue != '') {
                                    returnValue += "," + repository[0];
                                } else {
                                    returnValue += repository[0];
                                }
                            } else {
                                alert(repository[0] + "与" + mainCellInfo + "," + returnValue + "不是同一软件版本");
                                return null;
                            }
                        }
                    }
                    window.returnValue = returnValue + "&" + softwareRepository;//+","+softwareRepositoryName;
                    window.close();
                }
            </script>
            <jsp:include page="/WEB-INF/pages/tawLocalRepository/listsendUndoJS.jsp"/>
            <fmt:bundle basename="config/applicationResource-tawlocalrepository">
                <c:set var="buttons">
                    <input type="button" class="btn" onclick="selectNet()" value="确认"/>
                </c:set>
                <display:table name="tawLocalRepositoryList" cellspacing="0" cellpadding="0" id="tawLocalRepositoryList"
                               pagesize="${pageSize}" class="table tawLocalRepositoryList"
                               export="false" requestURI="" sort="list" partialList="true" size="resultSize"
                               decorator="com.boco.eoms.repository.webapp.action.SoftRepDisplaytagDecoratorHelper">
                    <display:column property="id"
                                    title="<input type='checkbox' onclick='javascript:choose();'>"/>
                    <display:column property="net" sortable="true"
                                    headerClass="sortable" titleKey="tawLocalRepository.net"
                                    href="${app}/repository/tawLocalRepositorys.do?method=edit" paramId="id"
                                    paramProperty="id"/>
                    <display:column sortable="true" headerClass="sortable" titleKey="tawLocalRepository.netType">
                        <eoms:id2nameDB id="${tawLocalRepositoryList.netType}" beanId="ItawSystemDictTypeDao"/>
                    </display:column>
                    <display:column sortable="true" headerClass="sortable" titleKey="tawLocalRepository.driverTpye">
                        <eoms:id2nameDB id="${tawLocalRepositoryList.driverTpye}" beanId="ItawSystemDictTypeDao"/>
                    </display:column>
                    <display:column sortable="true" headerClass="sortable" titleKey="tawLocalRepository.company">
                        <eoms:id2nameDB id="${tawLocalRepositoryList.company}" beanId="ItawSystemDictTypeDao"/>
                    </display:column>
                    <display:column sortable="true" headerClass="sortable" titleKey="tawLocalRepository.netModale">
                        <eoms:id2nameDB id="${tawLocalRepositoryList.netModale}" beanId="ItawSystemDictTypeDao"/>
                    </display:column>
                    <display:column sortable="true" headerClass="sortable"
                                    titleKey="tawLocalRepository.hardwareRepository">
                        <eoms:id2nameDB id="${tawLocalRepositoryList.hardwareRepository}"
                                        beanId="ItawSystemDictTypeDao"/>
                    </display:column>
                    <display:column sortable="true" headerClass="sortable"
                                    titleKey="tawLocalRepository.softwareRepository">
                        <eoms:id2nameDB id="${tawLocalRepositoryList.softwareRepository}"
                                        beanId="ItawSystemDictTypeDao"/>
                    </display:column>
                    <display:column property="patch" sortable="true" headerClass="sortable"
                                    titleKey="tawLocalRepository.patch" paramId="id" paramProperty="id"/>
                    <display:footer>
                        <c:out value="${buttons}" escapeXml="false"/>
                    </display:footer>
                </display:table>
            </fmt:bundle>
            <br/>

        </div>
    </div>
</div>
</body>
</html>
