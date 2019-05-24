<%@page
	import="java.util.List,com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation,com.boco.eoms.wap.util.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm,com.boco.eoms.commons.system.priv.util.PrivMgrLocator,com.boco.eoms.commons.system.priv.util.PrivConstants,com.boco.eoms.base.util.StaticVariable"
	pageEncoding="UTF-8"%>


<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ include file="/wap/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
		<title>电子运维wap系统</title>
	</head>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ico_bg">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_width" >

					<%TawSystemSessionForm sessionform = (TawSystemSessionForm) request
							.getSession().getAttribute("sessionform");
					List wapMainList = PrivMgrLocator.getPrivMgr().listOpertion(
							sessionform.getUserid(), sessionform.getDeptid(),
							sessionform.getRolelist(),
							PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
							StaticVariable.ROOT_NODE, StaticVariable.NATURE_WAP);
					for (int i = 0; i < wapMainList.size(); i++) {
						com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation privOper = (com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation) wapMainList
								.get(i);

						%>
					<%if ((i + 1) % 3 == 1) {%>
					<tr class="nav_text">
						<%}%>
						<td><a href="${app}<%=privOper.getUrl()%>" accesskey="<%=i+1%>"><img src="${app}/wap/images/ico0<%=i+1%>.gif"/></a><br /> 
							<a href="${app}<%=privOper.getUrl()%>" accesskey="<%=i+1%>"> <%=AtomUtil.substring(privOper.getName(), 0, 4)%> </a>
						</td>
						<%if ((i + 1) % 3 == 0 || (i + 1) == wapMainList.size()) {%>
					</tr>
						<%}%>

					<%
					}
				%>

				</table>
			</td>
		</tr>
	</table>
	</body>
</html>