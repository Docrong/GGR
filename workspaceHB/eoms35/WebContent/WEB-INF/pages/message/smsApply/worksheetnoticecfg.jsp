<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@page import="java.util.*"%>

<html:form action="/smsApplys.do?method=saveWorkSheetNotice" method="post" styleId="theform"> 
	<table class="formTable" name="table1" id="table1">
	<caption>工单通知方式配置</caption>
		<tr>
			<td class="label">
				流程名：
			</td>
			<td>
 			<eoms:comboBox name="flowName" id="flowName" 
        	      initDicId="1017702"  alt="allowBlank:false" defaultValue="" styleClass="select-class" />				
			</td>
			<td class="label">
				提醒类型：
			</td>
			<td>
 			<eoms:comboBox name="noticeType" id="noticeType" 
        	      initDicId="1017703"  alt="allowBlank:false" defaultValue="" styleClass="select-class" />				
			</td>
		</tr>
		<tr>
    		<td class="label">
    			<bean:message key='smsService.send'/>
    		</td>
    		<td class="content">    			
				<input type="text" size="3" name="sendDay" id="sendDay" />天
				<input type="text" size="3" name="sendHour" id="sendHour"/>时
				<input type="text" size="3" name="sendMin" id="sendMin" />分
    		</td> 
    		<td colspan="2"> 
    			<html:button styleClass="btn" property="method.save"styleId="method.save" onclick="check()">
         		新增
				</html:button>
			</td> 
    	</tr>
	</table>    	
</html:form>
<script type="text/javascript">
<!--
	function check(){
		var flowName = document.getElementById("flowName").value;
		var noticeType = document.getElementById("noticeType").value;
		if(null==flowName || ""==flowName ){
			alert("请先选择流程名");
			return;
		}else if(null==noticeType || ""==noticeType){
			alert("请先选择提醒类型");
			return;
		}
		var sendday = document.getElementById("sendDay").value;
		var sendhour = document.getElementById("sendHour").value;
		var sendmin = document.getElementById("sendMin").value;
		if(sendday.indexOf(".")>0 || sendmin.indexOf(".")>0 || sendhour.indexOf(".")>0 || ""==sendday || 
			""==sendhour || ""==sendmin || isNaN(sendday) || isNaN(sendhour) || isNaN(sendmin)){
			alert("提示：天、时、分必须输入整数！");
			return;
		}
		document.forms[0].action="smsApplys.do?method=saveWorkSheetNotice";
		document.forms[0].submit();	
	
	}
//-->
</script>
<br>
<%
String source = (String)request.getAttribute("source");
if(null!=source){%>
	<div><font color="red">注：当删除某工单某种服务的最后一条记录时，会自动将该服务的初始时间添加至以下记录。</font></div>
<br>
<html:form action="/smsApplys.do?method=updateWorkSheetNotice" method="post" styleId="">
	<table class="formTable" id="table2">
		<tr>
			<td class="label">
				流程名称
			</td>
			<td class="label">
				提醒类型
			</td>
			<td class="label">
				工单短信服务名称
			</td>
			<td class="label">
				提前派发时间
			</td>
			<td class="label" colspan="2">
				操作
			</td>
		</tr>
	  <%List list = (List)request.getAttribute("list");
	  	for(int i=0;i<list.size();i++){
	  	List subList = (List)list.get(i);
	  %>
	  <tr>
		<td>
			<%=subList.get(0)%>
		</td>
		<td>
			<%=subList.get(1)%>
		</td>
		<td>
			<%=subList.get(2)%>
		</td>
		<td>
	  		<input type="text" size="3" name="sendday<%=(String)subList.get(8)%>" id="sendday<%=(String)subList.get(8)%>" value="<%=(String)subList.get(5)%>"/>天
			<input type="text" size="3" name="sendhour<%=(String)subList.get(8)%>" id="sendhour<%=(String)subList.get(8)%>" value="<%=(String)subList.get(6)%>"/>时
			<input type="text" size="3" name="sendmin<%=(String)subList.get(8)%>" id="sendmin<%=(String)subList.get(8)%>" value="<%=(String)subList.get(7)%>"/>分
		</td>
		<td>
			<input type="button" value="修改" name="<%=(String)subList.get(8)%>" class="btn" onclick="updateva(this)"/>
		</td>
		<td>
			<input type="button" value="删除" name="del<%=(String)subList.get(8)%>" class="btn" onclick="deleva(this)"/>
		</td>
	  </tr>
	  <%}%>
	</table>
	<br>
	<input type="hidden" name="workflowName" id="workflowName" value="${workflowName}"/>
	<input type="hidden" name="flowDictName" id="flowDictName" value="${flowDictName}"/>
	<input type="hidden" name="serviceId" id="serviceId" value="${serviceId}"/>
	<input type="hidden" name="type" id="type" value="${type}"/>
</html:form>

<script type="text/javascript">
<!--
	function updateva(obj){
		var sdayid = "sendday"+obj.name;
		var sday = document.getElementById("sendday"+obj.name).value;
		var shour = document.getElementById("sendhour"+obj.name).value;
		var smin = document.getElementById("sendmin"+obj.name).value;
		if(sday.indexOf(".")>0 || smin.indexOf(".")>0 || shour.indexOf(".")>0 || ""==sday || 
			""==shour || ""==smin || isNaN(sday) || isNaN(shour) || isNaN(smin)){
			alert("提示：天、时、分必须输入整数！");
			return;
		}
		
		document.forms[1].action="smsApplys.do?method=updateWorkSheetNotice&id="+obj.name+"&sday="+sday+"&shour="+shour+"&smin="+smin+"";
		document.forms[1].submit();	
	}
	
	function deleva(obj){
		var sid = obj.name;
		document.forms[1].action="smsApplys.do?method=updateWorkSheetNotice&id="+obj.name+"&opertype=del";
		document.forms[1].submit();	
	}
//-->
</script>
<%} %>  
<%@ include file="/common/footer_eoms.jsp"%>