<%@page pageEncoding="UTF-8"%>
<%@page import="com.boco.eoms.workbench.contact.model.TawWorkbenchContact"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ include file="/wap/common/taglibs.jsp"%>
	<head>
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><bean:message key="wap.platform.contact.index.title" /></title>
	</head>
	<body>
	 
		<html:form action="contactAction.do?method=query">
				<jsp:include page="../../head.jsp" flush="true"/>
			<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg" >
				<tr>
					<td class="title_bg_01"><img src="images/s_ico_04.gif" />
						<bean:message key="wap.platform.contact.index.query" />
					</td>
					<td class="title_bg_01">
						<input type="text" name="name" />
					</td>

				</tr>
				<tr>
					<td align="center" colspan="2" class="table_td">
						<input type="submit" class="btn_02"
							value="<bean:message key="wap.platform.contact.index.button.query"/>" />
					</td>
				</tr>
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
				<logic:notEmpty name="contants">
				<logic:iterate id="item" name="contants">
				<%
					TawWorkbenchContact contact = (TawWorkbenchContact) item;
				%>
				<tr class="fb_xx_head_tr">
					<td class="tt_head_bg">
						<bean:message key="wap.platform.contact.list.contactName" />
				    </td>
				    <td class="fb_add_content">
						<a
							href="wtai://wp/ap;<bean:write name='item' property='tele'/>;<bean:write name='item' property='contactName' />"><bean:write
								name="item" property="contactName" />
						</a>
					</td>
				</tr>
				<tr class="fb_xx_head_tr">
					<td class="tt_head_bg">
						<bean:message key="wap.platform.contact.list.mobile" />
					</td>
					<td class="fb_add_content">
						<a href="wtai://wp/mc;<bean:write name='item' property='tele' />"><bean:write
								name="item" property="tele" />
						</a>
					</td>
				</tr>
				<tr class="fb_xx_head_tr">
					<td class="tt_head_bg">
						<bean:message key="wap.platform.contact.list.email" />
					</td>
					<td class="fb_add_content">
						<a href="mailto:<bean:write name='item' property='email' />"><bean:write
								name="item" property="email" />
						</a>
					</td>
				</tr>
				<tr class="fb_xx_head_tr">
					<td class="tt_head_bg">
						<bean:message key="wap.platform.contact.list.deptName" />
					</td>
					<td class="fb_add_content">
						<eoms:id2nameDB id="<%=contact.getDeptId()%>"
							beanId="tawSystemDeptDao" />
					</td>
				</tr>
						<tr>
							<td class="content_text_03"><br></td>
							<td class="content_text_03"><br></td>
						  </tr>
			</logic:iterate>
			</logic:notEmpty>
				</table>
				</table>
			</html:form>
		
	</body>
</html>