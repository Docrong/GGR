<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
 <tr>
    <td class="btn_bg">
<table>
  <tr>
  <input class="btn_02" name="url" type="hidden" value='<%=(String) request.getAttribute("url")%>'/>
  <input class="btn_02" name="hiddenStr" type="hidden" value='<%=hiddenStr.toString() %>' />
  <input class="btn_02" name="taskName" type="hidden" value="<%=(String) request.getAttribute("taskName") %>"/>
  <%
  //根据任务名taskName判断环节
  if ("FirstExcuteHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
 	 //根据taskStatus判断任务状态
  		if ("2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))) {%>
	   	 	 <td><input class="btn_02" name="accept" type="submit" value="接单" /></td>
	   		 <td><input class="btn_02" name="reject" type="submit" value="驳回" /></td>
	 	 <%}else if ("8".equalsIgnoreCase(request.getAttribute("taskStatus").toString())) {%>
	    	 <tr>
	    	 	<td><input class="btn_02" name="toMove" type="submit" value="移交" /></td>
	    	  <td><input class="btn_02" name="toMoveT2" type="submit" value="移交T2" /></td>
	    	  <td><input class="btn_02" name="toReply" type="submit" value="处理完成" /></td>
	    	 </tr>
	    	 <tr>
	    	 	<td><input class="btn_02" name="toDelayTransmit" type="submit" value="延期申请" /></td>
	    	  <td><input class="btn_02" name="toPhaseReply" type="submit" value="阶段回复" /></td>
	    	 </tr> 
	  	<% }
  } 
 
  //第二环节
  if ("SecondExcuteHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
  		//根据taskStatus判断任务状态
  		if ("2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))) {%>
	   	 	 <td><input class="btn_02" name="accept" type="submit" value="接单" /></td>
	   		 <td><input class="btn_02" name="reject" type="submit" value="驳回" /></td>
	 	 <%}else if ("8".equalsIgnoreCase(request.getAttribute("taskStatus").toString())) {%>
	    	 <tr>
	    	 	<td><input class="btn_02" name="toMove" type="submit" value="移交" /></td>
	    	  <td><input class="btn_02" name="toMoveT3" type="submit" value="移交T3" /></td>
	    	  <td><input class="btn_02" name="toReply" type="submit" value="处理完成" /></td>
	    	</tr>
	    	<tr> 
	    		<td><input class="btn_02" name="toDelayTransmit" type="submit" value="延期申请" /></td>
	    	  <td><input class="btn_02" name="toPhaseReply" type="submit" value="阶段回复" /></td>
				</tr>	  	
	  	<% }
  }
  
  
  //质检环节
  if ("CheckingHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
 	 //根据taskStatus判断任务状态
  		if ("2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))) {%>
	   	 	 <td><input class="btn_02" name="accept" type="submit" value="接单" /></td>
	   		 <td><input class="btn_02" name="reject" type="submit" value="驳回" /></td>
	 	 <%}else if ("8".equalsIgnoreCase(request.getAttribute("taskStatus").toString())) {%>
	    	 <tr>
	    	 	<td><input class="btn_02" name="toMove" type="submit" value="移交" /></td>
	    	  <td><input class="btn_02" name="toMoveT2" type="submit" value="移交T2" /></td>
	    	  <td><input class="btn_02" name="toReply" type="submit" value="处理完成" /></td>
	    	 </tr>
	    	 <tr>
	    	 	<td><input class="btn_02" name="toDelayTransmit" type="submit" value="延期申请" /></td>
	    	  <td><input class="btn_02" name="toPhaseReply" type="submit" value="阶段回复" /></td>
	    	 </tr> 
	  	<% }
  } 
  
  //归档
  if ("HoldHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {%>
	   	 	 <td><input class="btn_02" name="toHoldHumTask" type="submit" value="归档" /></td>
	   	 	 <td><input class="btn_02" name="toSendBack" type="submit" value="退回" /></td>
<% 
  }
  //延期审批
  if ("ExamineHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {%>
  			<input class="btn_02" name="toExamineHumTask" type="hidden" value="toExamineHumTask"/>
	   	 	 <td><input class="btn_02" name="pass" type="submit" value="审批通过" /></td>
	   	 	 <td><input class="btn_02" name="delayReject" type="submit" value="延期驳回" /></td>
	   	 	 <td><input class="btn_02" name="toMove" type="submit" value="转审" /></td>
<% 
  }
  %>
  </tr>
</table>
</td>
</tr>

