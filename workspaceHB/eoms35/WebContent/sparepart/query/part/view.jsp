<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body>
<center>
<br>
  <%
  int i=0;
  %>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="106%" class="table_title" align="center">${eoms:a2u('备件查询结果显示')}<bean:message key="label.list"/></td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="106%" colspan="12" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%></td>
  </tr>
   <tr class="td_label" align="center">
     <td>${eoms:a2u('序号')}</td>
     <td nowrap="nowrap">${eoms:a2u('分公司')}</td>
     <td nowrap="nowrap"><bean:message key="storage"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.department"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.necode"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.objecttype"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.serialno"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.supplier"/></td>
     <td nowrap="nowrap"><bean:message key="sparepart.state"/></td>

     <td nowrap="nowrap"><bean:message key="sparepart.intime"/></td>
    </tr>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
    <bean:define id="stateId" name="sparepart" property="stateid" type="Integer"/>
    <a href="../part/amplyview.do?id=<bean:write name="sparepart" property="id" scope="page"/>">
    <tr  <%if(stateId.intValue()==12){%>class="tr_show_fg_red"<%}else{%>class="tr_show"<%}%> align="center" style="cursor:hand" onmousemove="this.style.backgroundColor='#87CEEB'" onmouseout="this.style.backgroundColor=''">
      <%i=i+1;%>
      <td><%=i%></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="deptName" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="storage" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="nettype" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="serialno" scope="page"/></td>
       <td nowrap="nowrap"><bean:write name="sparepart" property="supplier" scope="page"/></td>
       <td nowrap="nowrap">
         <bean:write name="sparepart" property="state" scope="page"/>
       </td>

       <td nowrap="nowrap"><bean:write name="sparepart" property="intime" scope="page"/></td>
    </tr>
    </a>
    </logic:iterate>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<bean:write name='path' scope='session'/>">${eoms:a2u('返回')}</a></b>
      </td>
      <td class="table_title" align="center">
         <b><a href="../query/viewtoexp.do" target="_blank">${eoms:a2u('导出查询结果')}</a></b>
      </td>		
    </tr>
</table>
  </center>
</body>
</html>
