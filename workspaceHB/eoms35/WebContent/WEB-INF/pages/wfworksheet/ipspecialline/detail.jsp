<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted"));
String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
System.out.println("@@taskNameDetail="+taskName);
String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
System.out.println("@@sheetType="+taskName);
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isView = StaticMethod.nullObject2String(request.getParameter("isView"));
System.out.println("isView="+isView);
%>
<script type="text/javascript">
function modify(){
 window.location.href='./ipspeciallines.do?method=edit&id=${ipspeciallineForm.id}&ordersheetid=${ipspeciallineForm.orderSheet_Id}&sheetType=<%=sheetType%>&taskName=<%=taskName%>';
 }
 </script> 
<caption><div class="header center">IP数据专线详细信息</div></caption>
<html:form action="ipspeciallines.do?method=xsave" method="post" styleId="detail">
<input type="hidden" name="orderSheet_Id" id="orderSheet_Id" value="${ipspeciallineForm.orderSheet_Id}"/>			
     <table class="formTable">
     <caption>业务相关信息</caption> 
	    <tr>
      		<td class="label">接入点类型</td>
       		<td colspan="3">
      		<html:errors property="interfacePointTypeA"/>
      		<eoms:id2nameDB id="${ipspeciallineForm.portAInterfaceType}" beanId="ItawSystemDictTypeDao"/>
     		</td>
     	</tr>
     	<tr>
     		<td class="label">客户端联系人</td>
      		<td class="content">
	  		<html:errors property="apointLocalPerson"/>
       		<bean:write name="ipspeciallineForm" property="apointLocalPerson" scope="request"/>  
      		</td>
     		<td class="label">客户端详细地址</td>
      		<td class="content">
		  		<html:errors property="portADetailAdd"/>
	       		<bean:write name="ipspeciallineForm" property="portADetailAdd" scope="request"/>  
      		</td>
		</tr>
	   <tr>
	      <td class="label">业务带宽</td>
	      <td class="content">
		  	<html:errors property="businessBandwidth"/>
	        <bean:write name="ipspeciallineForm" property="businessBandwidth" scope="request"/>  
	      </td>
	      <td class="label">业务数量(传输条数)</td>
	      <td class="content">
		  	<html:errors property="businessAmount"/>
	        <bean:write name="ipspeciallineForm" property="businessAmount" scope="request"/>  
	      </td>
	   </tr>
   <tr>
   	  <td class="label">IP地址-客户应用服务需求数量（个）</td>
      <td class="content">
	  	<html:errors property="ipServerNeedNum"/>
         <bean:write name="ipspeciallineForm" property="ipServerNeedNum" scope="request"/>  
      </td>
      <td class="label">IP地址-互联需求数量(个)</td>
      <td class="content">
	  	<html:errors property="ipNeedNum"/>
       <bean:write name="ipspeciallineForm" property="ipNeedNum" scope="request"/>  
      </td>
      </td>
    </tr>
    <tr>
     <td class="label">子网掩码</td>
     <td class="content">
	  	<html:errors property="cnetCode"/>
        <bean:write name="ipspeciallineForm" property="cnetCode" scope="request"/>  
     </td>
      <td class="label">网关</td>
     <td class="content">
	  	<html:errors property="gateway"/>
        <bean:write name="ipspeciallineForm" property="gateway" scope="request"/>  
     </td>
   </tr>
   <tr>
       <td class="label">业务需求描述</td>
       <td colspan="3">
        <html:errors property="businessRequireDesc"/>
        <bean:write name="ipspeciallineForm" property="businessRequireDesc" scope="request"/> 
	   </td>
    </tr>
    <tr>
      <td class="label">用户个性化设备需求</td>
      <td class="content">
	  	<html:errors property="userSpecifyDevNeed"/>
       <bean:write name="ipspeciallineForm" property="userSpecifyDevNeed" scope="request"/>  
      </td>
      <td class="label">用户是否有用户网站</td>
      <td class="content">
	  	<html:errors property="userIsUserNet"/>
	  	<eoms:id2nameDB id="${ipspeciallineForm.userIsUserNet}" beanId="ItawSystemDictTypeDao"/>
      </td>      
	</tr>  
	<tr>
      <td class="label">端口数量</td>
      <td colspan="3">
	  	<html:errors property="portNum"/>
       <bean:write name="ipspeciallineForm" property="portNum" scope="request"/>  
      </td>
   </tr>
	<tr>
       <td class="label">业务需求区域</td>
       <td colspan="3">
        <html:errors property="businessRequireDesc"/>
        <bean:write name="ipspeciallineForm" property="requirmentArea" scope="request"/> 
	   </td>
    </tr>
    
    </table>
   
   <br>
   	<%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("ExplorateTask")||taskName.equals("UserTask") || taskName.equals("AccessTask") || taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
   <table class="formTable">    
   <caption>客户端勘查信息</caption> 
	<tr>
	    <td class="label">客户端标准地址</td>
       	<td  colspan='3'>
       	<bean:write name="ipspeciallineForm" property="userStardAddA" scope="request"/>  
	  	</td>
	</tr>
	<tr>
      <td class="label">客户位置经度</td>
      <td class="content">
	  	<html:errors property="userSiteAA"/>
       <bean:write name="ipspeciallineForm" property="userSiteAA" scope="request"/>  
      </td>
	  <td class="label">客户位置纬度	</td>
      <td class="content">
      	<html:errors property="userSiteHA"/>
       <bean:write name="ipspeciallineForm" property="userSiteHA" scope="request"/>  
     </td>
    </tr>
	<tr>
      <td class="label">客户端是否具有设备</td>
      <td class="content">
	  	<html:errors property="userIsHaveDivA"/>
      <eoms:id2nameDB id="${ipspeciallineForm.userIsHaveDivA}" beanId="ItawSystemDictTypeDao"/>
      </td>
	  <td class="label">是否需要移动采购</td>
      <td class="content">
      	<html:errors property="isNeedBuyA"/>
       	<eoms:id2nameDB id="${ipspeciallineForm.isNeedBuyA}" beanId="ItawSystemDictTypeDao"/>
     </td>
    </tr>
	<tr>
		<td class="label">需要购买的设备</td>
       <td colspan='3'>
	  	<html:errors property="theDevNeededA"/>
       <bean:write name="ipspeciallineForm" property="theDevNeededA" scope="request"/>  
     	</td>
     </tr>
	
	</table>
	<br>
	<%} %>
	
	<%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("ExplorateTask")||taskName.equals("AccessTask") || taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
    <table class="formTable"> 
    <caption>接入点勘查信息</caption> 
    <tr>
       <td class="label">接入点地址</td>
      <td colspan="3">
	  	<html:errors property="interfacePointAddA"/>
      <bean:write name="ipspeciallineForm" property="interfacePointAddA" scope="request"/>  
      </td>
	</tr>
	<tr>
      <td class="label">接入点站点名称（接入基站）</td>
      <td colspan="3">
	  	<html:errors property="interfaceSiteNameA"/>
        <bean:write name="ipspeciallineForm" property="interfaceSiteNameA" scope="request"/>  
      </td>
	</tr>
    <tr>
      <td class="label">光纤设备名称</td>
      <td class="content">
	  	<html:errors property="fiberEquipNameA"/>
 		<bean:write name="ipspeciallineForm" property="fiberEquipNameA" scope="request"/>  
      </td>
      <td class="label">光纤设备编号</td>
      <td class="content">
	  	<html:errors property="fiberEquipCodeA"/>
     	<bean:write name="ipspeciallineForm" property="fiberEquipCodeA" scope="request"/>  
      </td>
	</tr>
	<tr>
      <td class="label">纤芯个数</td>
      <td class="content">
	  	<html:errors property="fiberAcount"/>
 		<bean:write name="ipspeciallineForm" property="fiberAcount" scope="request"/>  
      </td>
      <td class="label">接入点类型</td>
      <td class="content">
	  	<html:errors property="interfacePointTypeA"/>
     	<bean:write name="ipspeciallineForm" property="interfacePointTypeA" scope="request"/>  
      </td>
	</tr>
	<tr>
		<td class="label">光缆路由描述</td>
		<td colspan="3">
	  	<html:errors property="fiberAroute"/>
     	<bean:write name="ipspeciallineForm" property="fiberAroute" scope="request"/>  
      </td>
	</tr>
	</table>
	<br>
		<%} %>
	
	<%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("ExplorateTask")||taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
    <table class="formTable">
    <caption>传输线路勘查信息</caption> 
    <tr>	
	      <td class="label">最后一公里光缆长度(单位：皮长)</td>
	      <td class="content">
		  	<html:errors property="fiberLengthA"/>
	         <bean:write name="ipspeciallineForm" property="fiberLengthA" scope="request"/>  
	      </td>
	      <td class="label">光缆产权</td>
	      <td class="content">
		  	<html:errors property="fiberOwnerA"/>
		    <eoms:id2nameDB id="${ipspeciallineForm.fiberOwnerA}" beanId="ItawSystemDictTypeDao"/>
	      </td>
      </tr>
      <tr>
      	  <td class="label">敷设方式</td>
	      <td class="content">
		  	<html:errors property="buildTypeA"/>
	         <bean:write name="ipspeciallineForm" property="buildTypeA" scope="request"/>  
	      </td>
	      
	      <td class="label">客户端到接入点能否通达</td>
	      <td class="content">
		  	<html:errors property="isOkBetweenUserA"/>
		  	<eoms:id2nameDB id="${ipspeciallineForm.isOkBetweenUserA}" beanId="ItawSystemDictTypeDao"/>
	      </td>
	  </tr>
	  <c:if test="${ipspeciallineForm.isOkBetweenUserA == '101231001'}"> 
	  <tr>
		  <td class="label">不能接入的原因</td>
	      <td class="content">
	      	<html:errors property="noInputResonA"/>
	         <bean:write name="ipspeciallineForm" property="noInputResonA" scope="request"/>  
	      </td>
	  </tr>
	  </c:if>
	 </table>
	 <br>
	  <%} %>
	  
	  <%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("TransfereTask")||taskName.equals("ExplorateTask")||
	    taskName.equals("ProjectDealTask")||
	    taskName.equals("CityNetTask")||
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("MakeTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("AuditTask")||
	 	taskName.equals("HoldTask"))
	{ %> 
		<table class="formTable">
		<caption>传输容量勘查信息</caption> 
		    <tr>
		    	<td class="label">传输容量是否满足开通</td>
		     	<td class="content">
				  	<html:errors property="isDeviceAllowOpenA"/>
			        <bean:write name="ipspeciallineForm" property="isDeviceAllowOpenA" scope="request"/>  
		     	</td>
		         <td class="label">是否需要添加板卡</td>
		          <td class="content">
			  	  <html:errors property="isNeedAddCardA"/>
			  	  <eoms:id2nameDB id="${ipspeciallineForm.isNeedAddCardA}" beanId="ItawSystemDictTypeDao"/>
		         </td>
		    </tr>
		      <tr>
					<td class="label">板卡类型</td>
		     		<td class="content">
			  			<html:errors property="cardTypeA"/>
		        		<bean:write name="ipspeciallineForm" property="cardTypeA" scope="request"/>  
		     		</td>
					<td class="label">板卡数量</td>
		     		<td class="content">
			  			<html:errors property="cardNumA"/>
		        	<bean:write name="ipspeciallineForm" property="cardNumA" scope="request"/>  
		     		</td>
				</tr>
		   <br>
	    </table>
	    <br>
	    <%} %>
	    
	  	<%if(taskName.equals("ImplementDealTask")||taskName.equals("")||taskName.equals("CityTask")||taskName.equals("ExplorateTask")||
	    taskName.equals("ProjectDealTask")||
	    taskName.equals("CityNetTask")||
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HoldTask")
		){ %> 
			<table class="formTable">
			<caption>城域网接入信息</caption>
			    <tr>
			    	<td class="label">城域网接入站点名称</td>
			     	<td class="content">
					  	<html:errors property="siteNameZ"/>
				        <bean:write name="ipspeciallineForm" property="siteNameZ" scope="request"/>  
			     	</td>
			         <td class="label">城域网接入设备名称</td>
			          <td class="content">
			          <html:errors property="portZBDeviceName"/>
				        <bean:write name="ipspeciallineForm" property="portZBDeviceName" scope="request"/>  
			         </td>
			    </tr>
			    <tr>
			         <td class="label">城域网接入设备端口</td>
			          <td class="content">
			          <html:errors property="portZBDevicePort"/>
				        <bean:write name="ipspeciallineForm" property="portZBDevicePort" scope="request"/>  
			         </td>
			         <td class="label">城域网接入详细地址</td>
			          <td class="content">
			          <html:errors property="portZDetailAdd"/>
				        <bean:write name="ipspeciallineForm" property="portZDetailAdd" scope="request"/>  
			         </td>
			    </tr>
		    </table>
		    <br>
		<%} %>
     
     	<%if(sheetType.equals("businessImplement")&&!taskName.equals("ImplementDealTask")){ %> 
	<table class="formTable">
	<caption>最后一公里相关信息</caption> 
  	<tr>
      <td class="label">是否熔接</td>
      <td class="content">
	  	<html:errors property="isGetInterfaceA"/>
	  	      <eoms:id2nameDB id="${ipspeciallineForm.isGetInterfaceA}" beanId="ItawSystemDictTypeDao"/>
      </td>
	<td class="label">熔接序号</td>
      <td class="content">
	  	<html:errors property="getInterfaceNoA"/>
          <bean:write name="ipspeciallineForm" property="getInterfaceNoA" scope="request"/>  
      </td>
    </tr>
	</tr>
	<tr>
       <td class="label">最后一公里处理意见</td>
       <td  class="content" colspan="3">
	  	  <bean:write name="ipspeciallineForm" property="theLastOpinionA" scope="request"/>  
		</td>
	</tr>
	</table>
	<%} %>
	
    <br>
    <table class="formTable">
        <%if(sheetType.equals("businessImplement")&&(
	 	taskName.equals("TrasitionTask")||taskName.equals("")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("HoldTask"))){ %>
	 	<caption>电路信息</caption> 
	<tr>
      <td class="label">电路名称</td>
      <td class="content">
	  	<html:errors property="circuitName"/>
	  	 <bean:write name="ipspeciallineForm" property="circuitName" scope="request"/>  
      </td>
      <td class="label">电路编号</td>
      <td class="content">
	  	<html:errors property="circuitSheetId"/>
	  	 <bean:write name="ipspeciallineForm" property="circuitSheetId" scope="request"/>  
     </td>
   </tr>
   <%} %>
    
    <tr>
      <td class="label">备注</td>
        <td  colspan="3">
        <html:errors property="remark"/>
        <bean:write name="ipspeciallineForm" property="remark" scope="request"/> 
	  </td>
   </tr>
</table>
<%if(!isView.equals("1")||taskName.equals("AcceptTask")||taskName.equals("ImplementDealTask")) {%>
<input type="button"  value="修改" onclick="modify();"> 
<%} %>
<input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
</html:form>

<!-- footer_eoms.jsp end-->