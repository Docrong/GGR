<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid = String.valueOf(request.getAttribute("userid"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>定制彩信报查询页面</title>
    
  </head>
  
  <body>
    <html:form action="/mmsreportTemplates.do?method=search" styleId="theform">
	 <table class="formTable">
	 
	 	<tr>
	 		<td noWrap class="label">	 
	 			按彩信报类型
	 		</td>
	 		<td>
	 		<select	name="executeCycle">
	 			<option value="">全部</option>
	 			<option value="monthReport">月</option>
	 			<option value="weekReport">周</option>
	 			<option value="dailyReport">日</option>
	 		</select>
	 		</td>
	 	</tr>
	 	
	 	<tr>
	 		<td noWrap class="label">	 
	 			 按彩信报名称
	 		</td> 		
	 		<td>	
				<input type="text" id="mmsName" name="mmsName" class="content"/>
	 		</td>
	 	</tr>
	 
	 <!-- 
	 	<tr>
	 		<td noWrap class="label">	 
	 			按定制人查询
	 		</td>
	 		<td>
	 		<input type="text" id="userid" name="userid" class="content"/>
	 		</td>
	 	</tr>
	 	 -->
	 	 
	 	<%
	 		//只有超级用户才可以查阅其他人订阅的报表
	 		if("admin".equalsIgnoreCase(userid))
	 		{
	 	%>		
 			<tr>
 				<td class="label">
 					按定制人查询：
 				</td>
 			
		 		<td><!-- alt="allowBlank:false" -->
		  		  	<input type="text" id="userid" name="userid" class="content"/>
	  		  	</td>
		 	</tr>
	 	<%		
	 		}
	 		else
	 		{
	 	 %>
	 	 	<input type="hidden" name="userid" value="<%=userid %>"/>
	 	 <%
			}	 	 
	 	  %>
	 	
	 	<tr>
	 		<td colspan="2">	
				<!-- buttons -->
			     <html:submit styleClass="btn" property="method.save" styleId="method.save">
					查询
			     </html:submit>
	 		</td>
	 	</tr>
	 </table>
	 </html:form>
  </body>
</html>
