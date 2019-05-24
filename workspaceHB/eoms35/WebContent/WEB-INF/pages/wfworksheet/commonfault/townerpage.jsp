<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*;" %>


<table class="formTable"> 
	<% List townerList = (List) request.getAttribute("townerList");
	   if(townerList != null && townerList.size()>0){
	   		Map townerMap = (Map) townerList.get(0);
	   		%>
	   		<tr>
        		<td class="label">运营商故障工单流水号</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("TASKID")) %></td>
				
				<td class="label">工单状态</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("TASK_STATUS")) %></td>
			</tr>
			
			<tr>
        		<td class="label">处理结果描述</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("TASK_DEAL_DESC")) %></td>
				
				<td class="label">回单人</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("TASK_PERSON")) %></td>
			</tr>
			
			<tr>
        		<td class="label">回单人联系电话</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("PERSON_TELPHONE")) %></td>
				
				<td class="label">回单时间 </td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("TASK_TIME")) %></td>
			</tr>
						
			<tr>
        		<td class="label">实时直流电压值</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("ELECTRIC_CURRENT")) %></td>
				
				<td class="label">铁塔站址资源编码</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("TOWNER_DEVICE_ID")) %></td>
			</tr>
			
			<tr>
        		<td class="label">站址运维ID</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("STATION_CODE")) %></td>
				
				<td class="label">站址名称</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("STATION_NAME")) %></td>
			</tr>
			
			<tr>
        		<td class="label">运营商是否购买发电服务</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("IS_BUY_ELETRIC")) %></td>
				
				<td class="label">是否是发电工单</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("IS_GENELEC_BILL")) %></td>
			</tr>
			
			<tr>
        		<td class="label">发电开始时间</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("ELETRIC_BEGINTIME")) %></td>
				
				<td class="label">发电结束时间</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("ELETRIC_ENDTIME")) %></td>
			</tr>
			
			<tr>
        		<td class="label">接单时间</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("ACCEPT_TIME")) %></td>
				
				<td class="label">所属地市</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("CITY_NAME")) %></td>
			</tr>
			
			<tr>
        		<td class="label">所属区县</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("COUNTRY_NAME")) %></td>
				
				<td class="label">运营商共享情况</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("OPERATORS_SHARE")) %></td>
			</tr>
			
			<tr>
        		<td class="label">站址服务等级</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("STATION_LEVEL")) %></td>
				
				<td class="label">是否为免责站址</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("IS_RELIEF")) %></td>
			</tr>
			
			<tr>
        		<td class="label"></td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("townerMap")) %></td>
				
				<td class="label"></td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("townerMap")) %></td>
			</tr>
			
			<tr>
        		<td class="label">附件上传的ftp地址</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("FTP_PATH")) %></td>
				
				<td class="label">申告工单是否铁塔原因</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("CAUSE_TYPE")) %></td>
			</tr>
			
			<tr>
        		<td class="label">申告工单故障分类</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("FAULT_TYPE")) %></td>
				
				<td class="label">是否申请减免</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("IS_DERATE")) %></td>
			</tr>
			
			<tr>
        		<td class="label">申请减免原因</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("DERATE_MESSAGE")) %></td>
				
				<td class="label">申请减免分钟数</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("DERATE_MINUTE")) %></td>
			</tr>
			
			<tr>
        		<td class="label">处理措施</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("DEALSTEP")) %></td>
				
				<td class="label">故障清除时间</td>
				<td class="content"><%= StaticMethod.nullObject2String(townerMap.get("CLEARTIME")) %></td>
			</tr>
			
			<tr>
        		<td class="label">故障归因</td>
				<td colspan="3"><%= StaticMethod.nullObject2String(townerMap.get("FUALTREASON")) %></td>
				
			</tr>
	   		
	  <% }else{%>	
	  		<div class="content">
	   			<ul>
	      			<li>工单还没有回单，没有任何内容</li>
       			</ul>
			</div>
	  <%} %>
	 
</table>
  

	
<%@ include file="/common/footer_eoms.jsp"%>