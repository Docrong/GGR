<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import ="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String aa= (String)request.getAttribute("path");
%>
<logic:redirect page="<%=aa%>"/>

