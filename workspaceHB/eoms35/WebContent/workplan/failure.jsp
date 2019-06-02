<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<head>
	<title>Error!</title>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/table_style.css"
		type="text/css">
</head>
<body>
	<table width="100%" height="100%" border="0" cellspacing="0"
		cellpadding="0"
		background="<%=request.getContextPath()%>/template/img/bg001.gif">
		<tr>
			<td width="100%" height="100%" align="center">
				<table width="743" height="440"
					background="<%=request.getContextPath()%>/images/bg001.jpg">
					<tr>
						<td align="center">
							<font style="font-size:14px;color:#CC0000;"><strong>
									<logic:messagesPresent message="true">
										<html:messages id="message" message="true">
											<%=message%>
										</html:messages>
									</logic:messagesPresent> </strong> </font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

