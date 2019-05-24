<%@ page language="java" import="com.boco.eoms.base.util.StaticMethod" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%
String num = StaticMethod.null2String(request.getAttribute("num").toString());
%>
<div class="successPage">
	<h1 class="header">${eoms:a2u('操作成功！')}</h1>
	<div class="content">
		${eoms:a2u('您本月的评估报表成功生成!本次生成评估报表数量:')}<%=num%>
	</div>
	<div class="content">
</div>
<%@ include file="/common/footer_eoms.jsp"%>