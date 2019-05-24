<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<%@ include file="/common/header_eoms.jsp"%>

<div class="errorPage">
	<h1 class="header"><fmt:message key="errorPage.heading" /></h1>
	<div class="content">
		<fmt:message key="errorPage.probable" /><br/>
		<ul>
			<li><fmt:message key="errors.net" /></li>
			<li><fmt:message key="errors.input" /></li>
		</ul>
		<fmt:message key="errorPage.try" />
		<ul>
			<li><fmt:message key="errorPage.try.net" /></li>
			<li><a href="#" onclick="javascript:location.reload();">
				<fmt:message key="errorPage.try.refreshPage" /></a>
			</li>
			<li><fmt:message key="errorPage.try.admin" /></li>
			<li><a href="#" onclick="javascript:$('error-msg').toggle();return false;">
				<fmt:message key="errorPage.moreDetail" />
				</a>
				<div id="error-msg" style="display:none">
	                <% if (exception != null) { %>
	                    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
	                <% } else if ((Exception)request.getAttribute("javax.servlet.error.exception") != null) { %>
	                    <pre><% ((Exception)request.getAttribute("javax.servlet.error.exception"))
	                                           .printStackTrace(new java.io.PrintWriter(out)); %></pre>
	                <% } else if (pageContext.findAttribute("org.apache.struts.action.EXCEPTION") != null) { %>
	                    <bean:define id="exception2" name="org.apache.struts.action.EXCEPTION"
	                     type="java.lang.Exception"/>
	                    <c:if test="${exception2 != null}">
	                        <pre><% exception2.printStackTrace(new java.io.PrintWriter(out));%></pre>
	                    </c:if>
	                    <%-- only show this if no error messages present --%>
	         			  <c:if test="${exception2 == null}">
	                        <fmt:message key="errors.none"/>
	                    </c:if>
	                <% } %>
				</div>
			</li>
		</ul>
	</div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>
