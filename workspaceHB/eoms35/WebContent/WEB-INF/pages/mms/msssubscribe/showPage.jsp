<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List mmsreportTemplateList = (List)request.getAttribute("mmsreportTemplateList");
String userid = String.valueOf(request.getAttribute("userid"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订阅查询页面</title>

  </head>
  
  <body>
    <html:form action="/msssubscribes.do?method=search" styleId="theform">
	 <table class="formTable">
	 
	 
	 
	 
	 	<!-- 
	 	<tr>
	 		<td noWrap class="label">	 
	 			 按订阅人查询：
	 		</td>
	 		
	 		<td>	
				<input type="text" id="createPerson" name="createPerson"/>
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
 					按订阅人查询：
 				</td>
 			
		 		<td><!-- alt="allowBlank:false" -->
		  		  	<input type="text" id="createPerson" name="createPerson"/>
	  		  	</td>
		 	</tr>
	 	<%		
	 		}
	 		else
	 		{
	 	 %>
	 	 	<input type="hidden" name="createPerson" value="<%=userid %>"/>
	 	 <%
			}	 	 
	 	  %>
	 	
	 	<tr>
	 		<td noWrap class="label">	 
	 			按彩信报发送时间查询：´</td>	 		
	 		<td>
              	从：
            		<input type="text" name="beginTime" id="beginTime" alt="allowBlank:true,vtype:'lessThen',link:'endTime',vtext:'开始时间不能为空或晚于结束时间´'" readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            	到：
            		<input type="text" name="endTime" id="endTime" alt="allowBlank:true,vtype:'moreThen',link:'beginTime',vtext:'结束时间不能为空或早于开始时间'" readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            </td>
	 	</tr>
	 	
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
