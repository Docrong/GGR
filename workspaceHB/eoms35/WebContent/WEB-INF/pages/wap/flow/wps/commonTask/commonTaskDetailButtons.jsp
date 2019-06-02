<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
 <tr>
    <td class="btn_bg">
<table>
  <tr>
  <input class="btn_02" name="url" type="hidden" value='<%=(String) request.getAttribute("url")%>'/>
  <input class="btn_02" name="hiddenStr" type="hidden" value='<%=hiddenStr.toString() %>' />
  <input class="btn_02" name="taskName" type="hidden" value="<%=(String) request.getAttribute("taskName") %>"/>
  <%
  //派发审批环节
  if ("TaskCreateAuditHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
 	 //根据taskStatus判断任务状态
  		if ("2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))) {%>
	   	 	 <td><input class="btn_02" name="accept" type="submit" value="接单" /></td>
	   		 <td><input class="btn_02" name="reject" type="submit" value="驳回" /></td>
	 	 <%}else if ("8".equalsIgnoreCase(request.getAttribute("taskStatus").toString())) {%>
	    	 <tr>
	    	  <td><input class="btn_02" name="toCreatPass" type="submit" value="任务创建审批通过" /></td>
	    	  <td><input class="btn_02" name="toCreatNotPass" type="submit" value="任务创建审批不通过" /></td>
	    	 </tr>
	    	 <%--
	    	 <tr>
	    	  <td><input class="btn_02" name="toPhaseReply" type="submit" value="阶段回复" /></td>
	    	 </tr> 
	    	 --%>
	  	<% }
  } 
  //任务执行环节
  if ("ExcuteHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
  		//根据taskStatus判断任务状态
  		if ("2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))) {%>
	   	 	 <td><input class="btn_02" name="accept" type="submit" value="接单" /></td>
	   		 <td><input class="btn_02" name="reject" type="submit" value="驳回" /></td>
	 	 <%}else if ("8".equalsIgnoreCase(request.getAttribute("taskStatus").toString())) {%>
	    	 <tr>
	    	  <td><input class="btn_02" name="toReplyed" type="submit" value="回复" /></td>
	    	 <%-- <td><input class="btn_02" name="toReplySend" type="submit" value="回复送审" /></td>--%>
	    	</tr>
	    	<%-- 
	    	<tr> 	    		
	    	  <td><input class="btn_02" name="toPhaseReply" type="submit" value="阶段回复" /></td>
	    	  <td><input class="btn_02" name="toSend" type="submit" value="分派" /></td>
				</tr>
			--%>	  	
	  	<% }
  }
  //完成审批环节
  if ("TaskCompleteAuditHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
  		//根据taskStatus判断任务状态
  		if ("2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))) {%>
	   	 	 <td><input class="btn_02" name="accept" type="submit" value="接单" /></td>
	   		 <td><input class="btn_02" name="reject" type="submit" value="驳回" /></td>
	 	 <%}else if ("8".equalsIgnoreCase(request.getAttribute("taskStatus").toString())) {%>
	 	 	<tr>
		 	 	<td><input class="btn_02" name="toCompletePass" type="submit" value="任务完成审批通过" /></td>
		 	 	<td><input class="btn_02" name="toCompleteNotPass" type="submit" value="任务完成审批不通过" /></td>
		 	</tr>
		 	<%-- 
		 	 <tr>	
		 	 	<td><input class="btn_02" name="toPhaseReply" type="submit" value="阶段回复" /></td>
		 	 </tr>
		 	--%> 
	  	<% }
  }
    //处理回复环节
 // if ("AffirmHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {%>
	<%-- 	 	
	<td><input class="btn_02" name="toReplyPass" type="submit" value="处理回复通过" /></td>
		 <td><input class="btn_02" name="toReplyNotPass" type="submit" value="处理回复不通过" /></td>--%>
     
	<%   
 // }
  //归档
  if ("HoldHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {%>
	   	 	 <td><input class="btn_02" name="toHoldHumTask" type="submit" value="归档" /></td>
<% 
  }
  %>
  </tr>
</table>
</td>
</tr>

