<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"  %>
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
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<c:set var="scheme" scope="page" value="${pageContext.request.scheme}"/>
<c:set var="serverName" scope="page" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" scope="page" value="${pageContext.request.serverPort}"/>
<c:if test="${empty sessionScope.theme}">
  <c:set var="theme" scope="session" value="default"/>
</c:if>
<c:if test="${!empty param.theme}">
  <c:set var="theme" scope="session" value="${param.theme}"/>
</c:if>
<%-- Set all pages that include this page to use XHTML --%>
<html:xhtml />

