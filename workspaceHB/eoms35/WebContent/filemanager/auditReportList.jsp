<%--
  Created by IntelliJ IDEA.
  User: wangsixuan
  Date: 2008-11-22
  Time: 10:44:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/red/theme.css" />
<%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/default.css" />--%>
<%----%>

</head>
<html>
<body>
<html:form action="/ReportMgrAction.do">
<br/><br/>
<table width="100%">
  <tr>
<td>
     <font size="2"><b>&nbsp;&nbsp;汇总列表</b></font></td>
</tr>
  <tr>
    <td width="100%" align="right" colspan="8" nowrap>
      <FONT size="2"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></FONT>
    </td>
  </tr>
</table>
<table width="100%">
<tr>
<td>
      <table class="formTable" id="list-table" width="100%">
        <thead>
         <tr>
         <td nowrap width="5%" class="label">
            <b>序号</b>
          </td>
          <td width="20%" height="30" nowrap class="label">
            <b>报表名称</b>
          </td>
          <td width="10%" height="30" nowrap class="label">
           <b>报表专题名称</b>
          </td>
          <td width="20%" height="30" nowrap class="label">
           <b>派发部门</b>
          </td>
          <td width="10%" height="30" nowrap class="label">
            <b>派发时间</b>
          </td>
          <td width="10%" height="30" nowrap class="label">
            <b>派发时限</b>
          </td>
          <%--<td width="15%" height="30" nowrap class="label">
           <b> 报表合格状态</b>
          </td>
          <td width="10%" height="30" nowrap class="label">
            <b>超时信息</b>
          </td>
        --%></tr>
        </thead>
        <tbody>
    <%
    int i=0;
    %>
        <logic:iterate id="reportList" name="ReportList" type="com.boco.eoms.filemanager.form.ReportMgrBean">
        <tr bgcolor="<%if(i%2==0) out.print("#f5fafa"); else out.print("#ffffff");%>">
        <td width="39">
        <%=++i%>
        </td>
          <%
          java.util.HashMap map = new java.util.HashMap();
          map.put("reportId", reportList.getReportId());
          pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
          %>
         <td nowrap height="30">
            <html:link href="ReportMgrAction.do?act=auditFileView" name="map"  title="">
              <bean:write name="reportList" property="reportName"/>
            </html:link>
          </td>
          <td nowrap height="30">
            <bean:write name="reportList" property="topicName"/>
          </td>
          <td nowrap height="30">
            <bean:write name="reportList" property="mgrDeptName"/>
          </td>
            <td nowrap height="30">
            <bean:write name="reportList" property="createTime"/>
          </td>
            <td nowrap height="30">
            <bean:write name="reportList" property="deadline"/>
          </td>
          <%--<td nowrap height="30">
               <bean:write name="reportList" property="reportStatusName"/>
          </td>
          <td nowrap height="30">
               <bean:write name="reportList" property="overtimeName"/>
          </td>
        --%></tr>
       </logic:iterate>
       </tbody>
      </table>
</td>
</tr>
</table>
</html:form>
</body>
</html>