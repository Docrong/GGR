<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
	String authenticationBeanId = StaticMethod.nullObject2String(request.getParameter("authenticationBeanId"));
	String type = StaticMethod.nullObject2String(request.getParameter("type"));
 %>
<script type="text/javascript">
window.name = "mywin";

</script>
<form target="mywin" method="post" id="loginForm" action="${app}/sheet/workflow/authentication.do?method=authenticationCommon">
	<table class="listTable" id="list-table" width="400px">
		<tr>
			<td class="label" align="center">
				${eoms:a2u("用户鉴权")}
			</td>
		</tr>
		<tr>
			<td class="label" align="center">
				${eoms:a2u("用户名")}
				<input type='text' name="userId" id="userId" size='20'>
				<% if(type.equals("error")){%>
					<div>
						<font color="red">${eoms:a2u("输入的验证码不正确")}</font>
					</div>
				<% }%>
			</td>
		</tr>
		<tr>
            <td class="label" align="center">
				${eoms:a2u("密    码")}
				<input type='password' name="password" id="password" size='20' >
			</td>
		</tr>
        <tr>
        	<td class="label" align="center">
        		<input type="submit" name="login" class="button"
					value=" ${eoms:a2u("提交")}" >
				<input type="button" name="login" class="button"
					value=" ${eoms:a2u("关闭")}" onclick="javascript:window.close();">				
			</td>
        </tr>
        <input type="hidden" id="authenticationBeanId" name="authenticationBeanId" value="<%=authenticationBeanId%>"/> 
	</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>