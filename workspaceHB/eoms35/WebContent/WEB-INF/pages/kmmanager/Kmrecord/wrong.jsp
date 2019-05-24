<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<html:html>

<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center"><table width="743" height="440" background="<%=request.getContextPath()%>/images/bg001.jpg">
			<tr>
				<td align="center">
		        <font style="font-size:14px;color:#CC0000;"><strong>${eoms:a2u('密码错误')}</strong></font>
				<br>
				<input type = "button" value='${eoms:a2u('返回')}' class="button"  onclick="javascript:history.back();">
				</td>
			</tr>
		</table> </td>
    </tr>
</table>    
</body>
</html:html>