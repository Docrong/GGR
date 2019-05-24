<%@ include file="../../common/taglibs.jsp"%>
<%@ include file="../../common/header_eoms_form.jsp"%>


<html>
<head>
<title>list</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<body>
<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
	<tr>
		<td class="table_title" align="center"><b>${eoms:a2u('故障记录')}&nbsp;<bean:message key="label.list"/></b></TD>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" border="0" width="95%">
  <tr>
    <td align="center" valign="top">
<!---正文开始------------------------------------------------------------------------------------------>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>		
	<tr class="td_label">
		<td nowrap>${eoms:a2u('创建人')}</td>
		<td nowrap>${eoms:a2u('创建部门')}</td>
		<td nowrap>${eoms:a2u('创建时间')}</td>
		<td nowrap>${eoms:a2u('操作')}</td>
	</tr>

	<bean:write name="pagerHeader" scope="request" filter="false"/>

  <logic:iterate id="baseInfoList" name="BASEINFOLIST" type="com.boco.eoms.duty.faultrecord.vo.FaultrecordVO">
  <tr class="tr_show">
		<td nowrap><bean:write name="baseInfoList" property="userName" scope="page"/></td>
		<td nowrap><bean:write name="baseInfoList" property="deptName" scope="page"/></td>
	  <td nowrap><bean:write name="baseInfoList" property="insertTime" scope="page"/></td>
	  <td nowrap><html:link page="/Faultrecord/view.do" paramId="id" paramName="baseInfoList" paramProperty="id">${eoms:a2u('查看')}</html:link></td>
	  </tr>
  </logic:iterate>
</table>

<BR>

<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
	<TR>
		<TD align="right">
      <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>&nbsp;
		</TD>
	</TR>
</TABLE>

<!---正文开始------------------------------------------------------------------------------------------>
    </td>
	</tr>
</table>
</center>
</body>
</html>
