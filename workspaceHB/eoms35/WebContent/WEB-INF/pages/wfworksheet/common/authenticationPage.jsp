<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<script type="text/javascript" charset="utf-8" src="${app}/scripts/ajaxRequestDomain.js"></script>
<%
	String authenticationBeanId = StaticMethod.nullObject2String(request.getParameter("authenticationBeanId"));
	String forwardUrl = StaticMethod.nullObject2String(request.getAttribute("forwardUrl"));
	String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
	String type = StaticMethod.nullObject2String(request.getParameter("type"));
	String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetId"));
	String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));
	String completeTime = StaticMethod.nullObject2String(request.getParameter("completeTime"));
	System.out.println("=========="+authenticationBeanId);
	System.out.println("forwardUrl:"+forwardUrl);
	System.out.println("sheetId:"+sheetId);
	System.out.println("operateType:"+operateType);
 %>
<script type="text/javascript">
window.name = "mywin";

function authention(){
	try{
		var userId = $('userId').value;
		var password = $('password').value;

	    Ext.Ajax.request({
	        url : "http://10.32.1.26:9006/webattemp/security/loginservlet?actionType=loginWorkflowSys&userName="+userId+"&password="+password+"&isRedirect=false",
			scriptTag: true,
	        failure: function(form, action){
	            alert('failure');
	        },
	        success: function(result,request){
                    var txt = Ext.util.JSON.decode(result.responseText); 
                    if(txt.success){			  				
			  				window.location="${app}/sheet/circuitdispatch/circuitdispatch.do?method=getIrmsUrl&sheetId=<%=sheetId%>&userId="+userId+"&operateType=<%=operateType%>&taskId=<%=taskId%>&completeTime=<%=completeTime%>";
                    }else{
                        alert(txt.message);                       
                    }	            
	        }

	    });
	  
	}catch(ex){
		alert(ex.message);
	}
}
</script>
<form target="mywin" method="post" id="loginForm" action="${app}/sheet/workflow/authentication.do?method=authentication">
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
        		<input type="button" name="login" class="button"
					value=" ${eoms:a2u("提交")}" onclick="authention();">
				<input type="button" name="login" class="button"
					value=" ${eoms:a2u("关闭")}" onclick="javascript:window.close();">				
			</td>
        </tr>
        <input type="hidden" id="authenticationBeanId" name="authenticationBeanId" value="<%=authenticationBeanId%>"/> 
        <input type="hidden" id="forwardUrl" name="forwardUrl" value="<%=forwardUrl%>"/> 
	</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>