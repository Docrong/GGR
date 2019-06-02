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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/default.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>
<html>
<body leftmargin="0" topmargin="0" class="clssclbar" topmargin=0 marginheight=0 leftmargin=0 marginwidth=0 >

<br> <br> <br> <br>
<br>
<table cellpadding="0" cellspacing="0" width="90%" align="center">
  <tr>
<td width="100%" height="18"  colspan="8" align="left" background="<%=request.getContextPath()%>/images/img/bg_title_01.jpg">
     <font color="#FFFFFF"><strong>未上传报表列表</strong></font></td>
</tr>
  <tr>
    <td width="100%" align="right" colspan="8" nowrap>
      <bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%>
    </td>
  </tr>
</table>
<table cellpadding="0" cellspacing="0" width="80%" align="center">
  <tr>
    <td>
      <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
        <tr class="td_label">
         <td nowrap width="5%">
            序号
          </td>
          <td width="20%" height="30" nowrap>
            报表名称
          </td>
          <td width="10%" height="30" nowrap>
           报表专题名称
          </td>
          <td width="20%" height="30" nowrap>
           派发部门
          </td>
          <td width="10%" height="30" nowrap>
            制作报表部门
          </td>
          <td width="10%" height="30" nowrap>
            派发时间
          </td>
          <td width="15%" height="30" nowrap>
            处理时限
          </td>
          <td width="10%" height="30" nowrap>
            处理状态
          </td>
        </tr>
    <%
    int i=0;
    %>
        <logic:iterate id="reportList" name="ReportList" type="com.boco.eoms.filemanager.form.ReportListBean">
        
        <tr class="tr_show" bgcolor="<%if(i%2==0) out.print("#eeeeee"); else out.print("#F7FBFF");%>">
        <td width="39">
        <%=++i%>
        </td>
          <%
          java.util.HashMap map = new java.util.HashMap();
          map.put("reportId", reportList.getReportId());
          pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
          %>
         <td nowrap height="30">
            <html:link href="../filemanager/ReportMgrAction.do?act=add" name="map"  title="">
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

</body>
</html>