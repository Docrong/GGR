<%@ page language="java" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<%@ include file="/common/header_eoms.jsp"%>
<div class="sheet-failed">
	<div class="header"><h1>${eoms:a2u('您没有任何权限进行操作！')}</h1></div>
	<div class="content">
		${eoms:a2u('您已成功登录，但没有任何已分配的权限，请联系管理员为您分配权限，并重新登录，谢谢。')}<br/>
	</div>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
