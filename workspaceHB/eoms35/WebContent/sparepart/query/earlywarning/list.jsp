
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:message key="stat.view"/>
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
         <b>${eoms:a2u('时间点预警')}</b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="td_label" align="center">
        <td>${eoms:a2u('分公司')}</td>
        <td>${eoms:a2u('维修出库预警数')}</td>
        <td>${eoms:a2u('维修入库预警数')}</td>
        <td>${eoms:a2u('归还预警数')}</td>
        <td>${eoms:a2u('库存预警数')}</td>
        <td>${eoms:a2u('维修比例预警数')}</td>
       </tr>
	   <logic:notEmpty name="LIST">
       <logic:iterate id="sparepart" name="LIST" type="com.boco.eoms.sparepart.model.EarlyWarning">
	       <tr class="tr_show" align="center">
	       	 <bean:define id="id" name="sparepart" property="id" type="java.lang.Integer"/>
	         <td><bean:write name="sparepart" property="name" scope="page"/></a></td>
	         <td><a href="<%=request.getContextPath()%>/sparepart/query/earlywarninglist.do?type=1&id=<%=id%>">
	         	<bean:write name="sparepart" property="item1" scope="page"/></a></td>
	         <td><a href="<%=request.getContextPath()%>/sparepart/query/earlywarninglist.do?type=2&id=<%=id%>">
	         	<bean:write name="sparepart" property="item2" scope="page"/></a></td>
	         <td><a href="<%=request.getContextPath()%>/sparepart/query/earlywarninglist.do?type=5&id=<%=id%>">
	         	<bean:write name="sparepart" property="item5" scope="page"/></a></td>
	         <td><a href="<%=request.getContextPath()%>/sparepart/query/earlywarninglist.do?type=3&id=<%=id%>">
	         	<bean:write name="sparepart" property="item3" scope="page"/></a></td>
	         <td><a href="<%=request.getContextPath()%>/sparepart/query/earlywarninglist.do?type=4&id=<%=id%>">
	         	<bean:write name="sparepart" property="item4" scope="page"/></a></td>
	         <%--
	         <td><bean:write name="sparepart" property="name" scope="page"/></td>
	         <td><bean:write name="sparepart" property="item1" scope="page"/></td>
	         <td><bean:write name="sparepart" property="item2" scope="page"/></td>
	         <td><bean:write name="sparepart" property="item5" scope="page"/></td>
	         <td><bean:write name="sparepart" property="item3" scope="page"/></td>
	         <td><bean:write name="sparepart" property="item4" scope="page"/></td>
	         --%>
	        </tr>
	    </logic:iterate>
		</logic:notEmpty>
</table>
<br>
 
</center>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
