<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<center>
<table>

<%
    String error=(String)request.getAttribute("error");
    out.println(error);
%>
      <html:submit styleClass="clsbtn2" onclick="window.history.back(-1)">
        返回
      </html:submit>

</table>
</center>