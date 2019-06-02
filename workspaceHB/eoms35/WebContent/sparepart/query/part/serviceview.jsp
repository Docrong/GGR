<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<html>
<head>
<title><bean:message key="service.view"/></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<%
 String path=(String)request.getSession().getAttribute("path");
//
/*
String aa=(String)request.getAttribute("intPage");
int intPage=Integer.parseInt(aa);
String aaa=(String)request.getAttribute("sumPage");
int intPageCount=Integer.parseInt(aaa);
*/
%>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="service.view"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
      <tr class="tr_show">
         <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
        <%! String key;%></td>
      </tr>
      <tr class="td_label">
                      <td align="center"><bean:message key="storage"/></td>
                      <td align="center"><bean:message key="sparepart.department"/></td>
                      <td align="center"><bean:message key="sparepart.necode"/></td>
                      <td align="center"><bean:message key="sparepart.objectname"/></td>
                      <td align="center"><bean:message key="sparepart.sum"/></td>
                      <td align="center"><bean:message key="sparepart.out"/></td>
                      <td align="center"><bean:message key="sparepart.outpercent"/></td>
                      <td align="center"><bean:message key="sparepart.service"/></td>
                      <td align="center"><bean:message key="sparepart.servicepercent"/></td>
        </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show">
           <td align="center"><bean:write name="sparepart" property="storage" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="objecttype" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="nettype" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="necode" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="sum" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="outSum" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="outPercent" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="serSum" scope="page"/></td>
           <td align="center"><bean:write name="sparepart" property="servicePercent" scope="page"/></td>
        </tr>
      </logic:iterate>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<%=path%>">${eoms:a2u('·µ»Ø')}</a></b>
      </td>
    </tr>
</table>
</center>
</div>
</body>
</html>
