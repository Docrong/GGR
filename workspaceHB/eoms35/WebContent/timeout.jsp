<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<SCRIPT LANGUAGE="JavaScript">
function on_history()
{
    var url="sysuser/tawSystemUsers.do?method=saveUserLogOut";
    window.open(url, "_self");
}
// -->
</SCRIPT>
<html:html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center"><table width="743" height="440" background="<%=request.getContextPath()%>/images/bg001.jpg">
			<tr>
				<td align="center">
		        <font style="font-size:14px;color:#CC0000;"><strong>${eoms:a2u('您没有这个权限 ，请联系管理员')}</strong></font>
				<br>
				<input type = "button" value='${eoms:a2u('返回')}' class="button"  onclick="javascript:on_history();">
				</td>
			</tr>
		</table> </td>
    </tr>
</table>    
</body>
</html:html>