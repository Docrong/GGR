<%@ page language="java" import="java.util.*,com.boco.local.model.*,com.boco.eoms.base.util.StaticMethod"
         pageEncoding="UTF-8" %>
\
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
<%@ page contentType="text/html; charset=gb2312" %>
<% response.setContentType("application/vnd.ms-excel;charset=gb2312"); %>
<HTML>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <%
		List listExcel = (ArrayList)request.getAttribute("listExcel");
%>
    <%
	 String text="eoms_excel.xls";
	 response.setHeader("Content-Disposition: ","attachment; filename="+text);
%>
<form action="" method="post">
    <table cellpadding="1" cellspacing="1" border="1" width="1800">
        <tr>
            <td>工单流水号</td>
            <td>工单主题</td>
            <td>派往对象</td>
            <td>派单时间</td>
            <td>工单内容</td>
            <td>回复内容</td>
            <td>回复时间</td>

        </tr>
        <%
            for (int i = 0; i < listExcel.size(); i++) {
                LocalBusinessDredgebroad businessDredgebroad = (LocalBusinessDredgebroad) listExcel.get(i);
                String sendtime = StaticMethod.date2String(businessDredgebroad.getSendtime());
                String operatetime = StaticMethod.date2String(businessDredgebroad.getOperatetime());
        %>
        <tr>
            <td>
                <%=businessDredgebroad.getSheetid() %>
            </td>
            <td>
                &nbsp;<%=businessDredgebroad.getTitle() %>
            </td>
            <td>
                <%=businessDredgebroad.getTaskowner() %>
            </td>
            <td>
                <%=sendtime %>
            </td>
            <td>
                <%=businessDredgebroad.getBrequirementdesc() %>
            </td>
            <td>
                <%=businessDredgebroad.getDealdesc() %>
            </td>

            <td>
                <%=operatetime %>
            </td>


        </tr>
        <%}%>
    </table>
</form>

<%@ include file="/common/footer_eoms.jsp" %>