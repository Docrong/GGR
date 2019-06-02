<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<SCRIPT language="JavaScript" src="/pageComponent/resources/scripts/openWin.js" type="text/javascript"></SCRIPT>
<%
 String value = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("vml"));
%>

<HTML xmlns:v xmlns:BOCO="BOCO Inter-Telecom">
<html>
<head>
<title>流程图</title>
<STYLE>
v\:*{behavior:url(#default#VML);}

#display{
	position:absolute;
	left:10px;
	top:140px;
}
#sample{
	position:absolute;
	left:200px;
	top:30px;
}
</STYLE>

</head>
<body>
<div id="introduction" valign>
<table class="table_show" cellspacing="1">
    <tr class="tr_show">
	<td>${eoms:a2u('图 例 :')}</td>
	<td></td>
   </tr>
   <tr>	
	<td bgColor=#3399FF width="8%">&nbsp;</td>
	<td nowrap="nowrap">${eoms:a2u('此操作已完成 :')}</td>
	</tr>
	<tr>
	<td bgColor=#FF6633 width="8%">&nbsp;</td>
	<td nowrap="nowrap">${eoms:a2u('此操作未完成或在等待下级处理 :')}</td>
	</tr>
</table>

<div id="flow">
<%=value%>
</div>
</div>
</body>