<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>

<script>
setTimeout("",3000);
//setTimeout("location.href='http://sports.cn.yahoo.com/'",3000);
</script>
<% 



String listType=StaticMethod.nullObject2String(request.getAttribute("listType"));
String succesReturn=StaticMethod.nullObject2String(request.getAttribute("succesReturn"));
String faultReturn=StaticMethod.nullObject2String(request.getAttribute("faultReturn"));

if(listType.equals("ownerList")){
%>
<script type="text/javascript">
</script>
<%}else{ %>
<script type="text/javascript">
	function intoServiceConfiguration(){
	 var undo=document.location.href;
	 undo = undo.substring(0,undo.indexOf("?")+1)+"method=showServiceConfigurationPage";
	 location.href = undo;
	}
	
	<% if(faultReturn.equals("")&&succesReturn.equals("")){ %>
	     window.setTimeout(intoServiceConfiguration, 3000);
	<%}%>
</script>
<%} %>
<style type="text/css">
.successPage span.data{
	color:#1465B7;
	margin-left:65px;
}
</style>
<div class="successPage">
    <%  if(faultReturn.equals("")&&succesReturn.equals("")){ %>
 	  <h1 class="header">数据已经成功提交!</h1>
	<%}else{%>
	 <div class="content">
	   <ul>
	      <li><html:link href="#" onclick="intoServiceConfiguration();">返回服务配置列表</html:link></li>
       </ul>
	</div> 
	<%} %>
</div>
<%@ include file="/common/footer_eoms.jsp"%>