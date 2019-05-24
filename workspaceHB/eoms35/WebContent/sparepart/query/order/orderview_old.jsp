<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<title>
<bean:message key="order.query.view"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="order.query.view"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
         <tr class="tr_show">
            <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
            <%! String key;%></td>
        </tr>
        <tr class="td_label">
                      <td width="8%"><bean:message key="order.operater"/></td>
                      <td width="8%"><bean:message key="order.proposer"/></td>
                      <td ><bean:message key="order.prop_dept"/></td>
                      <td ><bean:message key="order.prop_tel"/></td>
                      <td width="8%"><bean:message key="order.type"/></td>
                      <td width="10%">${eoms:a2u('发单时间')}</td>
                      <td width="8%"><bean:message key="order.state"/></td>
                      <td><bean:message key="sparepart.msg"/></td>
        </tr>
    <logic:iterate id="tawOrder" name="tawOrder" type="com.boco.eoms.sparepart.model.TawOrder">
        <tr class="tr_show" align="center">
                      <td ><bean:write name="tawOrder" property="operater" scope="page"/></td>
                      <td ><bean:write name="tawOrder" property="proposer" scope="page"/></td>
                      <td ><bean:write name="tawOrder" property="propDept" scope="page"/></td>
                      <td ><bean:write name="tawOrder" property="propTel" scope="page"/></td>
                      <td ><bean:write name="tawOrder" property="orderType" scope="page"/></td>
                      <td ><bean:write name="tawOrder" property="startdate" scope="page"/></td>
                      <td><bean:write name="tawOrder" property="orderState" scope="page"/></td>
                      <td>
                             <html:link page="/query/orderpart.do" paramId="id" paramName="tawOrder" paramProperty="id">
                         <bean:message key="sparepart.msg"/></html:link>
                      </td>
        </tr>
      </logic:iterate>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<bean:write name='path' scope='session'/>">${eoms:a2u('返回')}</a></b>
      </td>
    </tr>
</table>
</center>
</div>
</body>
</html>
