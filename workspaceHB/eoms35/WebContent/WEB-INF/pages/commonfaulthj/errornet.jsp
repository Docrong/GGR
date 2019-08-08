<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">

    body {
        font-size: 24px
    }
</style>

<div>
    <b><font size=5> 导入数据有误，请检查模板数据是否正确填入！</font></b>

</div>

<table id="sheet" class="formTable">
    <%List errorList = (List) request.getAttribute("errorList"); %>

    <%
        for (int i = 0; errorList != null && i < errorList.size(); i++) {
            String error = (String) errorList.get(i);

    %>
    <tr>
        <td>
            <%=i + 1 %>
        </td>

        <td>
            <%=error%>
        </td>
    </tr>
    <%} %>


</table>


<%@ include file="/common/footer_eoms.jsp" %>