<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.List,java.util.ArrayList,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.sparepart.model.TawOrderDetail,com.boco.eoms.common.util.StaticMethod"%>
<%
	List orderList = new ArrayList();
	orderList = (ArrayList) request.getAttribute("tawOrder");
%>
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
                      <%--<td width="8%"><bean:message key="order.operater"/></td>--%>
                      <td width="8%"><bean:message key="order.proposer"/></td>
                      <%--<td ><bean:message key="order.prop_dept"/></td> 
                      <td ><bean:message key="order.prop_tel"/></td>--%>
                      <td width="8%"><bean:message key="order.type"/></td>
                      <td width="10%">${eoms:a2u('操作时间')}</td>
                      <td width="8%">${eoms:a2u('状态')}</td>
                      <td>${eoms:a2u('序列号')}</td>
                      <td>${eoms:a2u('实物条形码')}</td>
							 <td>${eoms:a2u('备件信息查看')}</td>
        </tr>
        <%for (int i=0;i<orderList.size();i++){
        	TawOrderDetail TO = (TawOrderDetail) orderList.get(i);
	%>
        	<tr class="tr_show" align="center">
                       <%--<td ><%out.print(TO.getOperater());%></td>--%>
                      <td><%out.print(TO.getProposer());%></td>
                     <%-- <td><%out.print(TO.getPropDept());%></td>
                      <td><%out.print(TO.getPropTel());%></td>--%>
                      <td><%out.print(TO.getOrderName());%></td>
                      <td><%out.print(TO.getStartdate());%></td>
                      <td><%out.print(TO.getOrderPartStateName());%></td>
                      <%--
                      <td><a href="/EOMS_J2EE/sparepart/query/orderpart.do?id=<%=TO.getSparepartId()%>">备件信息</a></td>
                      --%>
                      <td><%out.print(TO.getSerialno());%></td>
                       <td><%out.print(TO.getManagecode());%></td>
							  <td><a href="/EOMS_J2EE/sparepart/part/amplyview.do?id=<%=TO.getSparepartId()%>" target="_blank">${eoms:a2u('查看详细')}</a></td>
		</tr>
        <%}%>
</table>
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><a href="<bean:write name='path' scope='session'/>">${eoms:a2u('返回')}</a></b>
      </td>
      <td class="table_title" align="center">
         <b><a href="../query/orderviewtoexp.do" target="_blank">${eoms:a2u('导出数据')}</a></b>
      </td>		
    </tr>
</table>
</center>
</div>
</body>
</html>
