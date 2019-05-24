<%--
  Created by IntelliJ IDEA.
  User: liqifei
  Date: 2005-9-20
  Time: 15:44:19
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
<br/>
<br/>
<table width="100%">
  <tr>
<td>
     <font size="2"><strong>&nbsp;&nbsp;报表列表</strong></font></td>
</tr>
  <tr>
    <td width="100%" align="right" colspan="8" nowrap>
      <font size="2"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></font>
    </td>
  </tr>
<br/>
</table>
<table width="100%">
  <tr>
    <td>
      <table width="100%" class="formTable">
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
           <b>制作报表部门</b>
          </td>
          <td width="10%" height="30" nowrap class="label">
            <b>派发时间</b>
          </td>
          <td width="15%" height="30" nowrap class="label">
            <b>处理时限</b>
          </td>
          <td width="10%" height="30" nowrap class="label">
            <b>处理状态</b>
          </td>
        </tr>
    <%
    int i=0;
    %>
        <logic:iterate id="reportList" name="ReportList" type="com.boco.eoms.filemanager.form.ReportListBean">
        <tr bgcolor="<%if(i%2==0) out.print("#eeeeee"); else out.print("#F7FBFF");%>">
        <td width="39">
        <%=++i%>
        </td>
          <%
          java.util.HashMap map = new java.util.HashMap();
          map.put("reportId", reportList.getReportId());
          pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
          %>
         <td nowrap height="30">
            <html:link href="ReportMgrAction.do?act=add" name="map" title="">
              <bean:write name="reportList" property="reportName"/>
            </html:link>
          </td>
          <td nowrap height="30">
            <bean:write name="reportList" property="topicName"/>
          </td>
          <td nowrap height="30">
            <bean:write name="reportList" property="sendDeptName"/>
          </td>
            <td nowrap height="30">
            <bean:write name="reportList" property="acceptDeptName"/>
          </td>
            <td nowrap height="30">
            <bean:write name="reportList" property="sendTime"/>
          </td>
          <td nowrap height="30">
               <bean:write name="reportList" property="deadline"/>
          </td>
          <td nowrap height="30">
               <bean:write name="reportList" property="statusName"/>
          </td>
        </tr>
       </logic:iterate>
      </table>
    </td>
  </tr>
</table>
</html:form>
</body>
</html>