<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted"));
String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
System.out.println("@@taskNameDetail"+taskName);
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isView = StaticMethod.nullObject2String(request.getParameter("isView"));
String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
%>
<script type="text/javascript">
function modify(){
var urls='./transferspeciallines.do?method=edit&id=${transferspeciallineForm.id}&ordersheetid=${transferspeciallineForm.orderSheet_Id}&sheetType=businessImplement&taskName=HoldTask';
window.showModalDialog(urls,"","dialogWidth=1200px;dialogHeight=100px;help:no;resizable:yes;status:no;dialogWidth=1024px;dialogHeight=700px");
window.location.reload();
}
 function initPage(){
		v = new eoms.form.Validation({form:'transferspeciallineForm'});   		
		var taskName = "<%=taskName%>";	
	  	
	  	if(taskName!=""&&taskName!="AcceptTask"&&taskName!="ImplementDealTask"){
	  		eoms.form.readOnlyArea('BusinessInfo');
	  	}
	  	
	  	if(taskName=="AccessTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  	}else if(taskName=="TransferlTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  		eoms.form.readOnlyArea('interfaceInfo');
	  	}else if(taskName=="TransfereTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  		eoms.form.readOnlyArea('interfaceInfo');
	  		eoms.form.readOnlyArea('transLineInfo');
	  	}else if(taskName=="CityTask"||taskName=="CityNetTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  		eoms.form.readOnlyArea('interfaceInfo');
	  		eoms.form.readOnlyArea('transLineInfo');
	  		eoms.form.readOnlyArea('transCardInfo');
	  	}else if(taskName=="ProjectDealTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  		eoms.form.readOnlyArea('interfaceInfo');
	  		eoms.form.readOnlyArea('transLineInfo');
	  		eoms.form.readOnlyArea('transCardInfo');
	  	}else if(taskName=="TrasitionTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  		eoms.form.readOnlyArea('interfaceInfo');
	  		eoms.form.readOnlyArea('transLineInfo');
	  		eoms.form.readOnlyArea('transCardInfo');
	  		eoms.form.readOnlyArea('lastInfo');
	  	}
    } 
 </script> 
<caption><div class="header center">传输专线详细信息</div></caption>
<html:form action="transferspeciallines.do?method=xsave" method="post" styleId="detail">
<input type="hidden" name="orderSheet_Id" id="orderSheet_Id" value="${transferspeciallineForm.orderSheet_Id}"/>		
   <table class="formTable">
	<caption>业务相关信息</caption> 
	<tbody id='BusinessInfo' > 
   <tr>
      <td class="label">城市A</td>
     <td class="content">
	  	<html:errors property="cityA"/>
        <bean:write name="transferspeciallineForm" property="cityA" scope="request"/>  
     </td>
     <td class="label">城市Z</td>
     <td class="content">
	  	<html:errors property="cityZ"/>
        <bean:write name="transferspeciallineForm" property="cityZ" scope="request"/>  
     </td>
   </tr>
     
     <td class="label">端口A</td>
      <td class="content">
	  	<html:errors property="portA"/>
        <bean:write name="transferspeciallineForm" property="portA" scope="request"/>  
     </td>
          <td class="label">端口Z</td>
        <td class="content">
	  	<html:errors property="portZ"/>
	  	<bean:write name="transferspeciallineForm" property="portZ" scope="request"/>  
      </td>
    </tr>
        
      <tr>
        <td class="label">A接口类型及型号</td>
      <td class="content">
	  	<html:errors property="portAInterfaceType"/>
	  	<eoms:id2nameDB id="${transferspeciallineForm.portAInterfaceType}" beanId="ItawSystemDictTypeDao"/>
     </td>
           <td class="label">Z接口类型及型号</td>
        <td class="content">
	  	<html:errors property="portZInterfaceType"/>
        <eoms:id2nameDB id="${transferspeciallineForm.portZInterfaceType}" beanId="ItawSystemDictTypeDao"/>        
        </td>
	</tr> 
 <tr>
      <td class="label">A端点详细地址</td>
      <td  class="content">
	  	<html:errors property="portADetailAdd"/>
        <bean:write name="transferspeciallineForm" property="portADetailAdd" scope="request"/> 
      </td>
      <td class="label">Z端点详细地址</td>
      <td class="content">
	  	<html:errors property="portZDetailAdd"/>
	  	 <bean:write name="transferspeciallineForm" property="portZDetailAdd" scope="request"/> 
      </td>
	</tr>
<tr>
      <td class="label">A业务设备名称</td>
      <td class="content">
	  	<html:errors property="portABDeviceName"/>
        <bean:write name="transferspeciallineForm" property="portABDeviceName" scope="request"/> 
      </td>
   	  
      <td class="label">Z业务设备名称</td>
      <td class="content">
	  	<html:errors property="portZBDeviceName"/>
	  	 <bean:write name="transferspeciallineForm" property="portZBDeviceName" scope="request"/> 
      </td>
      </tr>
      <tr>
      <td class="label">端点A业务设备端口</td>
     <td class="content">
	  	<html:errors property="portABDevicePort"/>
        <bean:write name="transferspeciallineForm" property="portABDevicePort" scope="request"/>  
     </td>
      
      <td class="label">端点Z业务设备端口</td>
      <td class="content">
	  	<html:errors property="portZBDevicePort"/>
	  	 <bean:write name="transferspeciallineForm" property="portZBDevicePort" scope="request"/> 
     </td> 
     </tr>
     <tr>
         <td class="label">A客户在当地的配合人</td>
      <td class="content">
	  	<html:errors property="apointLocalPerson"/>
        <bean:write name="transferspeciallineForm" property="apointLocalPerson" scope="request"/> 
      </td>
      	      <td class="label">Z客户在当地的配合人</td>
      <td class="content">
	  	<html:errors property="zpointLocalPerson"/>
	  	 <bean:write name="transferspeciallineForm" property="zpointLocalPerson" scope="request"/> 
      </td>
	</tr>
	<tr>
      <td class="label">A客户在当地的配合人的联系电话</td>
      <td class="content">
	  	<html:errors property="portAContactPhone"/>
         <bean:write name="transferspeciallineForm" property="portAContactPhone" scope="request"/> 
      </td>
       

      <td class="label">Z客户在当地的配合人的联系电话</td>
      <td class="content">
	  	<html:errors property="portZContactPhone"/>
	  	 <bean:write name="transferspeciallineForm" property="portZContactPhone" scope="request"/> 
      </td>
      </tr> 
      
   <tr>
      <td class="label">带宽</td>
     <td class="content">
         <html:errors property="bandwidth"/>
         <bean:write name="transferspeciallineForm" property="bandwidth" scope="request"/>   
     </td>
      <td class="label">用户是否用户网站</td>
      <td class="content">
	  	<html:errors property="userIsUserNet"/>
	  	<eoms:id2nameDB id="${transferspeciallineForm.userIsUserNet}" beanId="ItawSystemDictTypeDao"/> 
     </td>  
     <tr>
	      <td class="label">用户个性化设备需求</td>
        <td colspan='3'>
	  	<html:errors property="userSpecifyDevNeed"/>
        <bean:write name="transferspeciallineForm" property="userSpecifyDevNeed" scope="request"/> 
     </td>
    </tr>
    	</tbody>
</table>
  <%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("ExplorateTask")||taskName.equals("UserTask") || taskName.equals("AccessTask") || taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
  
    <table class="formTable">
    <caption>客户端勘查信息</caption> 
	    <tbody id='customInfo' > 
	   <tr>
	   <td class="label">A客户端标准地址</td>
       	<td  colspan='3'>
       	<bean:write name="transferspeciallineForm" property="userStardAddA" scope="request"/>  
	  	</td>
	  	</tr>
	  <tr>
	      <td class="label">A客户位置经度</td>
	      <td class="content">
		  	<html:errors property="userSiteAA"/>
		  	<bean:write name="transferspeciallineForm" property="userSiteAA" scope="request"/>  
	      </td>
	 	<td class="label">A客户位置纬度	</td>
	      <td class="content">
	      	<html:errors property="userSiteHA"/>
	      	<bean:write name="transferspeciallineForm" property="userSiteHA" scope="request"/>
	     </td>
	    </tr>
	 	 <tr>
	      <td class="label">用户端设备端口类型</td>
	      <td class="content">
		  	<html:errors property="portABDeviceType"/>
		  	<eoms:id2nameDB id="${transferspeciallineForm.portABDeviceType}" beanId="ItawSystemDictTypeDao"/> 
	      </td>
	      	 <td class="label">A建设方式</td>
	      <td class="content">
		  	<html:errors property="buildMethodA"/>
		  	<eoms:id2nameDB id="${transferspeciallineForm.buildMethodA}" beanId="ItawSystemDictTypeDao"/>
	      
	      </td>
	    </tr>
	   <tr>
	      <td class="label">A客户端是否具有设备</td>
	      <td class="content">
		  	<html:errors property="userIsHaveDivA"/>
		  	 <eoms:id2nameDB id="${transferspeciallineForm.userIsHaveDivA}" beanId="ItawSystemDictTypeDao"/> 
		   </td>
	 		  <td class="label">A是否需要移动采购</td>
	      <td class="content">
	      	<html:errors property="isNeedBuyA"/>
	      	<eoms:id2nameDB id="${transferspeciallineForm.isNeedBuyA}" beanId="ItawSystemDictTypeDao"/>
	     </td>
	    </tr>
	  </tbody>
	   <tr>	 
	      <td class="label">A需要购买的设备</td>
	      <td colspan='3'>
		  	<html:errors property="theDevNeededA"/>
		  	<bean:write name="transferspeciallineForm" property="theDevNeededA" scope="request"/> 
	      </td>
	      </tr>
	  <tr>
      <td class="label">Z客户端标准地址</td>
       <td  colspan="3">
           <bean:write name="transferspeciallineForm" property="userStardAddZ" scope="request"/> 
		</td>
	</tr>    
	  
	  <tr>
	      <td class="label">Z客户位置经度</td>
	      <td class="content">
		  	<html:errors property="userSiteAZ"/>
		  	 <bean:write name="transferspeciallineForm" property="userSiteAZ" scope="request"/> 
	      </td>
		  <td class="label">Z客户位置纬度	</td>
	      <td class="content">
	      	<html:errors property="userSiteHZ"/>
	      	 <bean:write name="transferspeciallineForm" property="userSiteHZ" scope="request"/> 
	     </td>
	    </tr>
	    
	   <tr>
	      <td class="label">用户端设备端口类型</td>
	      <td class="content">
		  	<html:errors property="portZBDeviceType"/>
		  	<eoms:id2nameDB id="${transferspeciallineForm.portZBDeviceType}" beanId="ItawSystemDictTypeDao"/> 
	      </td>  
	      
	      	  <td class="label">Z建设方式</td>
	      <td class="content">
		  	<html:errors property="buildMethodZ"/>
		  	 <eoms:id2nameDB id="${transferspeciallineForm.buildMethodZ}" beanId="ItawSystemDictTypeDao"/> 
	     
	      </td>
	    </tr>
	    <tr>
	      <td class="label">Z客户端是否具有设备</td>
	      <td class="content">
		  	<html:errors property="userIsHaveDivZ"/>
		  		<eoms:id2nameDB id="${transferspeciallineForm.userIsHaveDivZ}" beanId="ItawSystemDictTypeDao"/>
		  </td>
		  <td class="label">Z是否需要移动采购</td>
	      <td class="content">
	      	<html:errors property="isNeedBuyZ"/>
	      		<eoms:id2nameDB id="${transferspeciallineForm.isNeedBuyZ}" beanId="ItawSystemDictTypeDao"/>
	     </td>
	    </tr>
	    
	     <tr>	 
	      <td class="label">Z需要购买的设备</td>
	      <td colspan='3'>
		  	<html:errors property="theDevNeededZ"/>
		  	<bean:write name="transferspeciallineForm" property="theDevNeededZ" scope="request"/> 
	      </td>
	      </tr>
	  </tbody>
	</table>
		<%} %>
	<br>
<%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("ExplorateTask")||taskName.equals("AccessTask") || taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
	<table class="formTable">
	<caption>接入点勘查信息</caption> 
	<tbody id='interfaceInfo' > 
	    <tr>
      <td class="label">A机房名称</td>
      <td class="content">
	  	<html:errors property="apointComputHouseName"/>
        <bean:write name="transferspeciallineForm" property="apointComputHouseName" scope="request"/> 
      </td>
	   <td class="label">A接入点地址</td>
	      <td class="content">
	      	<html:errors property="interfacePointAddA"/>
	      	 <bean:write name="transferspeciallineForm" property="interfacePointAddA" scope="request"/> 
	     </td>
	    </tr>
	 	 <tr>
      <td class="label">A接入点站点名称</td>
      <td class="content">
	  	<html:errors property="interfaceSiteNameA"/>
         <bean:write name="transferspeciallineForm" property="interfaceSiteNameA" scope="request"/> 
      </td>    
      <td class="label">A接入点设备编码</td>
      <td class="content">
	  	<html:errors property="interfaceEquipCodeA"/>
        <bean:write name="transferspeciallineForm" property="interfaceEquipCodeA" scope="request"/> 
      </td>
	</tr>  
 <tr>
      <td class="label">A光纤设备名称</td>
      <td class="content">
	  	<html:errors property="fiberEquipNameA"/>
  <bean:write name="transferspeciallineForm" property="fiberEquipNameA" scope="request"/> 
      </td>
	      <td class="label">A光纤设备编号</td>
      <td class="content">
	  	<html:errors property="fiberEquipCodeA"/>
       <bean:write name="transferspeciallineForm" property="fiberEquipCodeA" scope="request"/> 
      </td>
	</tr>
	<tr>
	      <td class="label">A纤芯个数</td>
	      <td class="content">
		  	<html:errors property="fiberAcount"/>
		  	 <bean:write name="transferspeciallineForm" property="fiberAcount" scope="request"/> 
	      </td>
	      <td class="label">A接入点类型</td>
	      <td class="content">
		  	<html:errors property="interfacePointTypeA"/>
		  	 <eoms:id2nameDB id="${transferspeciallineForm.interfacePointTypeA}" beanId="ItawSystemDictTypeDao"/> 
	      </td>
	      
		</tr>
	  	<tr>
	      <td class="label">A光缆路由描述</td>
	      <td colspan="3">
		  	<html:errors property="fiberAroute"/>
		  	<bean:write name="transferspeciallineForm" property="fiberAroute" scope="request"/> 
	      </td>
		</tr>
	  <tr>
      <td class="label">Z端机房名称</td>
     <td class="content">
	  	<html:errors property="zpointComputerHorseName"/>
	  	 <bean:write name="transferspeciallineForm" property="zpointComputerHorseName" scope="request"/>
        
     </td>
	
			<td class="label">Z接入点地址</td>
	      <td class="content">
	      	<html:errors property="interfacePointAddZ"/>
	      	  	 <bean:write name="transferspeciallineForm" property="interfacePointAddZ" scope="request"/>	
	     </td>
	    </tr>

	<tr>
      <td class="label">Z接入点站点名称</td>
      <td class="content">
	  	<html:errors property="interfaceSiteNameZ"/>
	  	<bean:write name="transferspeciallineForm" property="interfaceSiteNameZ" scope="request"/>
      </td>
      	  <td class="label">z接入点设备编码</td>
      <td class="content">
      	<html:errors property="interfaceEquipCodeZ"/>
      	<bean:write name="transferspeciallineForm" property="interfaceEquipCodeZ" scope="request"/>
     </td>
    </tr>
    	    <tr>
      <td class="label">Z光纤设备名称</td>
      <td class="content">
	  	<html:errors property="fiberEquipNameZ"/>
	  	 <bean:write name="transferspeciallineForm" property="fiberEquipNameZ" scope="request"/> 
      </td>
     
      <td class="label">Z光纤设备编号</td>
      <td class="content">
	  	<html:errors property="fiberEquipCodeZ"/>
	  	 <bean:write name="transferspeciallineForm" property="fiberEquipCodeZ" scope="request"/> 
      </td>
	</tr>
		<tr>
	      <td class="label">Z纤芯个数</td>
	      <td class="content">
		  	<html:errors property="fiberZcount"/>
		  	<bean:write name="transferspeciallineForm" property="fiberZcount" scope="request"/> 
	      </td>
	      <td class="label">Z接入点类型</td>
	      <td class="content">
		  	<html:errors property="interfacePointTypeZ"/>
		  	 <eoms:id2nameDB id="${transferspeciallineForm.interfacePointTypeZ}" beanId="ItawSystemDictTypeDao"/> 
	      </td>
		</tr>
	 
	 	<tr>	      
	      <td class="label">Z光缆路由描述</td>
	      <td colspan="3">
		  	<html:errors property="fiberZroute"/>
		  	<bean:write name="transferspeciallineForm" property="fiberZroute" scope="request"/> 
	      </td>
		</tr>
		</tbody>
  	</table>
	
	<%} %>
	
	<br>
<%if(sheetType.equals("businessImplement")||taskName.equals("")||taskName.equals("ExplorateTask")||taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
	<table class="formTable">	
	<caption>传输线路勘查信息</caption> 
	<tbody id='transLineInfo' > 
	<tr>
	      <td class="label">A最后一公里光缆长度(单位：皮长)</td>
      <td class="content">
	  	<html:errors property="fiberLengthA"/>
       <bean:write name="transferspeciallineForm" property="fiberLengthA" scope="request"/> 
      </td>
      <td class="label">A光缆产权</td>
      <td class="content">
	  	<html:errors property="fiberOwnerA"/>
      	   <eoms:id2nameDB id="${transferspeciallineForm.fiberOwnerA}" beanId="ItawSystemDictTypeDao"/>
     </td>
	</tr>
	<tr>
			<td class="label">敷设方式</td>
		      <td class="content">
			  	<html:errors property="buildTypeA"/>
			  	<bean:write name="transferspeciallineForm" property="buildTypeA" scope="request"/> 
		      </td>
	      <td class="label">A客户端到接入点能否通达</td>
	      <td class="content">
		  	<html:errors property="isOkBetweenUserA"/>
		  	 <eoms:id2nameDB id="${transferspeciallineForm.isOkBetweenUserA}" beanId="ItawSystemDictTypeDao"/>
	     </td>
	  </tr>
	  <tr>
	   		 <td class="label">A不能接入的原因</td>
		     <td colspan='3'>
		      	<html:errors property="noInputResonA"/>
		      	<bean:write name="transferspeciallineForm" property="noInputResonA" scope="request"/> 
		     </td> 	
		     </tr>	 
		     
		 <tr>
	        <td class="label">Z最后一公里光缆长度</td>
      <td class="content">
	  	<html:errors property="fiberLengthZ"/>
	  	 <bean:write name="transferspeciallineForm" property="fiberLengthZ" scope="request"/> 
      </td>
	
      <td class="label">Z光缆产权</td>
      <td class="content">
	  	<html:errors property="fiberOwnerZ"/>
	  	 <eoms:id2nameDB id="${transferspeciallineForm.fiberOwnerZ}" beanId="ItawSystemDictTypeDao"/>
	  	 
     </td>
     </tr>
	
		 <tr>
			<td class="label">敷设方式</td>
		      <td class="content">
			  	<html:errors property="buildTypeZ"/>
			  	 <bean:write name="transferspeciallineForm" property="buildTypeZ" scope="request"/> 
		      </td>
		      <td class="label">Z客户端到接入点能否通达</td>
	      <td class="content">
		  	<html:errors property="isOkBetweenUserZ"/>
		  	 <eoms:id2nameDB id="${transferspeciallineForm.isOkBetweenUserZ}" beanId="ItawSystemDictTypeDao"/>
	     </td>
	     </tr>
	     <tr>
	     		     <td class="label">Z不能接入的原因</td>
		     <td colspan='3'>
		      	<html:errors property="noInputResonZ"/>
		      		 <bean:write name="transferspeciallineForm" property="noInputResonZ" scope="request"/> 
		     </td>
		     </tr>	 
    </tbody>
    </table>
     <%} %>
    <br>
	<%if(sheetType.equals("businessImplement")||taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")||
	    taskName.equals("ProjectDealTask")||taskName.equals("ExplorateTask")||
	    taskName.equals("CityNetTask")||taskName.equals("")||
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
	<tbody id='transCardInfo' > 
	  	    <tr>
	    	<td class="label">A传输容量是否满足开通</td>
	     	<td class="content">
			  	<html:errors property="isDeviceAllowOpenA"/>
			  	<bean:write name="transferspeciallineForm" property="isDeviceAllowOpenA" scope="request"/> 
	     	</td>
	         <td class="label">A是否需要添加板卡</td>
	          <td class="content">
		  	  <html:errors property="isNeedAddCardA"/>
		  	  <eoms:id2nameDB id="${transferspeciallineForm.isNeedAddCardA}" beanId="ItawSystemDictTypeDao"/>
	         </td>
	    </tr>
	  
	  	      <tr>
				<td class="label">板卡类型</td>
	     		<td class="content">
		  			<html:errors property="cardTypeA"/>
		  			<bean:write name="transferspeciallineForm" property="cardTypeA" scope="request"/> 
	     		</td>
				<td class="label">板卡数量</td>
	     		<td class="content">
		  			<html:errors property="cardNumA"/>
		  			<bean:write name="transferspeciallineForm" property="cardNumA" scope="request"/> 
	     		</td>
			</tr>  
			
			  <tr>
	    	<td class="label">Z传输容量是否满足开通</td>
	     	<td class="content">
			  	<html:errors property="isDeviceAllowOpenZ"/>
			  	<bean:write name="transferspeciallineForm" property="isDeviceAllowOpenZ" scope="request"/> 
	     	</td>
	         <td class="label">Z是否需要添加板卡</td>
	          <td class="content">
		  	  <html:errors property="isNeedAddCardZ"/>
		  	   <eoms:id2nameDB id="${transferspeciallineForm.isNeedAddCardZ}" beanId="ItawSystemDictTypeDao"/>
		  	
	         </td>
	    </tr>   
	    	 <tr>
				<td class="label">Z板卡类型</td>
	     		<td class="content">
		  			<html:errors property="cardTypeZ"/>
		  				<bean:write name="transferspeciallineForm" property="cardTypeZ" scope="request"/> 
	     		</td>
				<td class="label">Z板卡数量</td>
	     		<td class="content">
		  			<html:errors property="cardNumZ"/>
		  				<bean:write name="transferspeciallineForm" property="cardNumZ" scope="request"/> 
	     		</td>
			</tr>
		      
     </tbody>
   <br>
    </table>
     <%} %>
     <%if(sheetType.equals("businessImplement")&&!taskName.equals("ImplementDealTask")){ %> 
    <table class="formTable">
    <caption>最后一公里相关信息</caption> 	
		<tbody id='lastInfo' > 
	    <td class="label">A是否熔接</td>
       <td  colspan='3'>
	  	<html:errors property="isGetInterfaceA"/>
      
	  	   <eoms:id2nameDB id="${transferspeciallineForm.isGetInterfaceA}" beanId="ItawSystemDictTypeDao"/> 
     
      </td>
    </tr>
	<tr>
      <td class="label">A熔接序号</td>
      <td colspan='3'>
	  	<html:errors property="getInterfaceNoA"/>
      <bean:write name="transferspeciallineForm" property="getInterfaceNoA" scope="request"/> 
      </td>

    </tr>
    <tr>
      
      <td class="label">A最后一公里处理意见</td>
       <td   colspan="3">
	   <bean:write name="transferspeciallineForm" property="theLastOpinionA" scope="request"/> 
		</td>
	</tr>
	<tr>
	     <td class="label">Z是否熔接</td>
      <td class="content">
	  	<html:errors property="isGetInterfaceZ"/>
	  	   <eoms:id2nameDB id="${transferspeciallineForm.isGetInterfaceZ}" beanId="ItawSystemDictTypeDao"/> 
       </td>
    <td class="label">Z熔接序号</td>
      <td  class="content">
	  	<html:errors property="getInterfaceNoZ"/>
	  	 <bean:write name="transferspeciallineForm" property="getInterfaceNoZ" scope="request"/> 
      </td>

    </tr>
    <tr>
      <td class="label">Z最后一公里处理意见</td>
       <td colspan="3">
        <bean:write name="transferspeciallineForm" property="theLastOpinionZ" scope="request"/> 
		</td>
	    </tr> 
	     </tbody>
		 </table>
	    <%} %>
  
  <%if(sheetType.equals("businessImplement")&&(
	 	taskName.equals("TrasitionTask")||taskName.equals("")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("HoldTask"))){ %>
   
   <br>
    <table class="formTable">
	<caption>电路信息</caption>    
		<tbody id='circuitInfo' >  
	  	<tr>
	      <td class="label">电路名称</td>
	     <td class="content">
		  	<html:errors property="circuitName"/>
		  	 <bean:write name="transferspeciallineForm" property="circuitName" scope="request"/> 
	     </td>
	      <td class="label">电路编号</td>
	     <td class="content">
		  	<html:errors property="circuitSheetId"/>
		  	 <bean:write name="transferspeciallineForm" property="circuitSheetId" scope="request"/> 
	     </td>
	   </tr>
	   </tbody>

   </table>
 
 <%} %>
 
   
  

</table>

<table>
<input type="button" style="margin-right: 5px" value="编辑" Onclick="modify()">
<input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">

</table>
</html:form>