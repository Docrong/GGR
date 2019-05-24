<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<%@ include file="/common/header_eoms.jsp"%>
<div class="failurePage">
	<h1 class="header">删除失败！请重新删除</h1>
	<div class="content">
		最可能的原因是：<br/>
		<ul>
		<li>未连接到网络</li>
		<li>删除的数据中可能存在错误</li>
		</ul>
		您可以尝试以下操作：
		<ul>
		<li>检查您的网络连接，确认连接到本系统。</li>
		<li><a href="#" onclick="javascript:history.back();return false;">
			返回到上一页重新操作</a>
		</li>
		<li><a href="#" onclick="javascript:$('error-msg').toggle();return false;">
			更多信息
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