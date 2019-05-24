<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<% 
String listType=StaticMethod.nullObject2String(request.getAttribute("listType"));
String succesReturn=StaticMethod.nullObject2String(request.getAttribute("succesReturn"));
String faultReturn=StaticMethod.nullObject2String(request.getAttribute("faultReturn"));
String flagExecl = StaticMethod.nullObject2String(request.getAttribute("flagExecl"));
System.out.println("===listType===="+listType);
System.out.println("===succesReturn===="+succesReturn+"===faultReturn====="+faultReturn);




if(listType.equals("ownerList")){
%>
<script type="text/javascript">
	function intoOwner(){
	 var undo=document.location.href;
	 undo = undo.substring(0,undo.indexOf("?")+1)+"method=showOwnStarterList";
	 location.href = undo;
	}
	window.setTimeout(intoOwner, 3000);
</script>
<%}else if(listType.equals("flagExecl")){%>
	<script type="text/javascript">
	function intoOwner(){
	 window.close();

	}
	window.setTimeout(intoOwner, 3000);
   </script>


<%} else{ %>
<script type="text/javascript">
	function intoUndo(){
	 var undo=document.location.href;
	 undo = undo.substring(0,undo.indexOf("?")+1)+"method=showListsendundo&flag=0";
	 location.href = undo;
	}
	
	function intoDone(){
	 var done= document.location.href;
	 done = done.substring(0,done.indexOf("?")+1)+"method=showListsenddone";
	 location.href = done;
	 
	}
	<% if(faultReturn.equals("")&&succesReturn.equals("")){ %>
	     window.setTimeout(intoUndo, 3000);
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
 	  <h1 class="header">工单数据已经成功提交!</h1>
	<%}else{
	     if(!succesReturn.equals("")){%>
	       <h1 class="header">本次批量处理成功的工单号为:</h1><br/><span class="data"><%=succesReturn%></span><br/>
	<%} if(!faultReturn.equals("")){ %>
	      <h1 class="header">本次批量处理失败的工单号或者任务号为:</h1><br/><span class="data"><%=faultReturn%></span><br/>
	<%}%>
	 <div class="content">
	   <ul>
	      <li><html:link href="#" onclick="intoUndo();">返回待处理工单列表</html:link></li>
          <li><html:link href="#" onclick="intoDone();">返回已处理工单列表</html:link></li>
       </ul>
	</div> 
	<%} %>
</div>
<%@ include file="/common/footer_eoms.jsp"%>