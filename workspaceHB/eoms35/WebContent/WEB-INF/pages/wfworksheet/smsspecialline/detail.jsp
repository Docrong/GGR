<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.businessupport.product.webapp.form.SmsspeciallineForm" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted")); 
String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
System.out.println("@@taskNameDetail"+taskName);
System.out.println("@@sheetTypeDetail"+sheetType);
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isView = StaticMethod.nullObject2String(request.getParameter("isView"));
    String isShowSms = StaticMethod.nullObject2String(request.getParameter("isShowSms"));
    System.out.println("@isShowSms"+isShowSms);
    	String	taskType = StaticMethod.nullObject2String(request.getParameter("taskType"));
    	String duanXinCaiXinType = StaticMethod.nullObject2String(request.getAttribute("duanXinCaiXinType"));
      	String	specialtyType = StaticMethod.nullObject2String(request.getAttribute("specialtyType"));
      System.out.println("@detailspecialtyType"+specialtyType);    	
%>
<script type="text/javascript">
function modify(){
 window.location.href='./smsspeciallines.do?method=edit&id=${smsspeciallineForm.id}&ordersheetid=${smsspeciallineForm.orderSheet_Id}&sheetType=${sheetType}&taskName=${taskName}&isEdit=isEdit&isShowSms=<%=isShowSms%>&specialtyType=<%=specialtyType %>';
 }
 
   function initPage(){
		v = new eoms.form.Validation({form:'smsspeciallineForm'});   		
		var taskName = "<%=taskName%>";	
	  
    } 
 </script> 
<caption><div class="header center"><%if(specialtyType.equals("101230105")){ %>
								彩信信息
		<%}else if(specialtyType.equals("101230106")){%>短信信息<%} else{ %>
		1111111111111
		<%} %>	
</div></caption>
<html:form action="smsspeciallines.do?method=xsave" method="post" styleId="detail">
<input type="hidden" name="orderSheet_Id" id="orderSheet_Id" value="${smsspeciallineForm.orderSheet_Id}"/>	

<br>
 <table class="formTable">  
  <tbody id='BusinessInfo' > 
   <%if(specialtyType.equals("101230105")){ %>
     <tr>
    	<td class="label">A端点详细地址</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="portADetailAdd" scope="request"/>  
		</td>
 		<td class="label">
			产品编号
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="productNum" scope="request"/>  
		</td>
    </tr>
   	<tr>
      <td class="label">业务售后SLA维护登记</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="businessSLABook" scope="request"/>  
      </td>
	  <td class="label">是否要支持上行彩信流量</td>
 <td class="content">
   <eoms:id2nameDB id="${smsspeciallineForm.isSupportUpCaiXinStream}" beanId="ItawSystemDictTypeDao"/>
         </td>

    </tr>
	<tr>


		<td class="label">
			业务范围
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="scopeOfBusiness" scope="request"/>  
		</td>
		<td class="label">
			使用协议
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.useAccord}" beanId="ItawSystemDictTypeDao"/>
      </td>
	</tr>
	<tr>
		<td class="label">
			终端IP地址类型
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.ipAdressType}" beanId="ItawSystemDictTypeDao"/>
		</td>
	
		<td class="label">
			终端IP地址
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="ipAdress" scope="request"/>  
		</td>
	</tr>
	<tr>	
	<td class="label">
			接入的方式
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.cableModem}" beanId="ItawSystemDictTypeDao"/>
		</td>

	
		<td class="label">
			业务流向限制
		</td>
		 <td class="content">
		   <eoms:id2nameDB id="${smsspeciallineForm.businessLimit}" beanId="ItawSystemDictTypeDao"/>
				  </td>	
	</tr>
	<tr>	
	<td class="label">
			限制发送和接收范围
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="limitSendAndRecScope" scope="request"/>  
		</td>

	<!--  
		<td class="label">
			主机地址
		</td>
		 <td class="content">
				  	 <eoms:comboBox name="businessLimit" id="businessLimit" 
				  	       initDicId="1013502" defaultValue="${smsspeciallineForm.businessLimit}" alt="allowBlank:false" />
				  </td>	-->
	</tr>
	<tr>
		<td class="label">
			主机地址
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="hostAddress" scope="request"/>  
		</td>
	<td class="label">
			是否接入MISC/DSMP
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.ifCable}" beanId="ItawSystemDictTypeDao"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			计费类型
		</td>
		 <td class="content">
		 <eoms:id2nameDB id="${smsspeciallineForm.billingType}" beanId="ItawSystemDictTypeDao"/>
		</td>	
		<td class="label">
			信息费
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="informationFeeds" scope="request"/>  
		</td>
	</tr>
	<tr>		
		<td class="label">
			地域制作范围
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="scopeMakeScope" scope="request"/>  
		</td>

		<td class="label">
			时间制作范围
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="timeMakeScope" scope="request"/>  
		</td>
	</tr>	
	<tr>		
		<td class="label">
			接入网关带宽
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="inputNetWayBandWidth" scope="request"/>  
		</td>

		<td class="label">
			最大链接数
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="computerAdd" scope="request"/>  
		</td>
	</tr>
	<tr>		
		<td class="label">
			最大下发流量
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="lagerDownStream" scope="request"/>  
		</td>

		<td class="label">
			最大上行流量
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="lagerUptStream" scope="request"/>  
		</td>
	</tr>	
	<tr>
		<td class="label">
			企业流控优先级
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="rightModule" scope="request"/>  
		</td>
	<td class="label">
			端口速率是否自适应下调
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.nameSheetMethod}" beanId="ItawSystemDictTypeDao"/>
       
	
		</td>
		
		</tr>	
			<!-- 
	<tr>		
		
		<td class="label">
			上行URL
		</td>
		<td class="content">
			<html:text property="upUrl" styleId="upUrl"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.upUrl}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			ProvisionURL
		</td>
		<td class="content">
			<html:text property="provisionURL" styleId="provisionURL"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.provisionURL}" />
		</td>
	

	</tr>



	<tr>
		<td class="label">
			业务名称
		</td>
		<td class="content">
			<html:text property="businessName" styleId="businessName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.businessName}" />
		</td>
	
		<td class="label">
			业务代码
		</td>
		<td class="content">
			<html:text property="businessCode" styleId="businessCode"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.businessCode}" />
		</td>
	</tr>
 -->



	<%} else if(specialtyType.equals("101230106") ){%>
		     <tr>
    	<td class="label">A端点详细地址</td>
		<td colspan='3'>
		   	<bean:write name="smsspeciallineForm" property="portADetailAdd" scope="request"/>  
		</td>
		<!-- 
 		<td class="label">
			产品编号
		</td>
		<td class="content">
			<html:text property="productNum" styleId="productNum"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${smsspeciallineForm.productNum}" />
		</td> -->
    </tr>
<tr>
    	<td class="label">产品类型</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="productType" scope="request"/>  
		</td>
 		<td class="label">
			产品实例名称
		</td>
		<td class="content">
		   	<bean:write name="smsspeciallineForm" property="productInstanceName" scope="request"/>  
		</td>
    </tr>
    
    <tr>
      <td class="label">业务售后SLA维护登记</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="businessSLABook" scope="request"/>  
      </td>
      <td class="label">接入设备编号或名称</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="inputDevNoAndName" scope="request"/>  
      </td>
	</tr>   
     <tr>
      <td class="label">客户占用接入设备端口</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="userUseDevPort" scope="request"/>  
      </td>
      <td class="label">接入设备端口状态</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="inputDevPortStatue" scope="request"/>  
      </td>
	</tr>      

   	<tr>
      <td class="label">终端IP地址</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="ipAdress" scope="request"/>  
      </td>
	  <td class="label">终端IP地址类型</td>
 		<td>
 		  <eoms:id2nameDB id="${smsspeciallineForm.ipAdressType}" beanId="ItawSystemDictTypeDao"/>
         </td>

    </tr>
   	<tr>
	  <td class="label">协议</td>
 		<td>
 		  <eoms:id2nameDB id="${smsspeciallineForm.protocolDuanXin}" beanId="ItawSystemDictTypeDao"/>
         </td>
	  <td class="label">品牌范围</td>
 		<td>
 		  <eoms:id2nameDB id="${smsspeciallineForm.brandScope}" beanId="ItawSystemDictTypeDao"/>
         </td>

    </tr>
    <tr>
    	<td class="label">
			是否接入DSMP
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.isinputDSMP}" beanId="ItawSystemDictTypeDao"/>
		</td>
    	<td class="label">
			接入方式
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.inputMethodDuanXin}" beanId="ItawSystemDictTypeDao"/>
		</td>		
		</tr>		
    <tr>
      <td class="label">限制发送和接收范围</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="limitSendAndRecScope" scope="request"/>  
      </td>
    	<td class="label">
			接入等级号
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.inputLevelNumber}" beanId="ItawSystemDictTypeDao"/>
		</td>		
		</tr>
    <tr>

    	<td class="label">
			品牌范围
		</td>
		<td class="content">
		  <eoms:id2nameDB id="${smsspeciallineForm.brandScope}" beanId="ItawSystemDictTypeDao"/>
		</td>		
    
      <td class="label">短信签名</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="duanxinSing" scope="request"/>  
      </td>
		</tr>
     <tr>
      <td class="label">企业中文实名</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="enterpriseCNName" scope="request"/>  
      </td>
      <td class="label">企业英文实名</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="enterpriseENname" scope="request"/>  
      </td>
	</tr> 
     <tr>
      <td class="label">地域制作范围</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="scopeMakeScope" scope="request"/>  
      </td>
      <td class="label">时间制作范围</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="timeMakeScope" scope="request"/>  
      </td>
	</tr> 
	</tr> 
     <tr>
      <td class="label">接入点</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="inputDot" scope="request"/>  
      </td>
      <td class="label">主机IP地址</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="computerIpAdd" scope="request"/>  
      </td>
	</tr>
     <tr>
      <td class="label">接入网关带宽</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="inputNetWayBandWidth" scope="request"/>  
      </td>
      <td class="label">端口速率自适应下调</td>
	   	<td class="content">
	   	  <eoms:id2nameDB id="${smsspeciallineForm.portSpeed}" beanId="ItawSystemDictTypeDao"/>
      </td>
	</tr>
     <tr>
      <td class="label">最大下发流量</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="lagerDownStream" scope="request"/>  
      </td>
      <td class="label">最大上行流量</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="lagerUptStream" scope="request"/>  
      </td>
	</tr>
     <tr>
      <td class="label">鉴权模式</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="rightModule" scope="request"/>  
      </td>
      <td class="label">话单位长</td>
      <td class="content">
	     	<bean:write name="smsspeciallineForm" property="feeBit" scope="request"/>  
      </td>
	</tr>
	     <tr>
      <td class="label">黑白名单设置方式</td>
      <td colspan="3">
	     	<bean:write name="smsspeciallineForm" property="nameSheetMethod" scope="request"/>  
      </td>

	</tr>
	<%} %>	
	</tbody>
  </table>
	
	
	  	<%if(sheetType.equals("businessImplement")
	  	||sheetType.equals("businessImplementSms")
	  	||taskName.equals("UserTask") 
	  	|| taskName.equals("AccessTask")
	  	 || taskName.equals("CityTask")
	  	  || taskName.equals("CaiXinTask")
	  	 || taskName.equals("TransfereTask")
	  	 || taskName.equals("TransferlTask")
	  	 || taskName.equals("MakeTask")
	  	 || taskName.equals("AuditTask")
	  	 || taskName.equals("HandleTask")
	  	 
	  	  || taskName.equals("ExplorateTask")
	  	 || taskName.equals("HoldTask")){ %>
   <table class="formTable">    
   <caption>客户端勘查信息</caption> 
	<tr>
	    <td class="label">客户端标准地址</td>
       	<td  colspan='3'>
       	<bean:write name="smsspeciallineForm" property="userStardAddA" scope="request"/>  
	  	</td>
	</tr>
	<tr>
      <td class="label">客户位置经度</td>
      <td class="content">
	  	<html:errors property="userSiteAA"/>
       <bean:write name="smsspeciallineForm" property="userSiteAA" scope="request"/>  
      </td>
	  <td class="label">客户位置纬度	</td>
      <td class="content">
      	<html:errors property="userSiteHA"/>
       <bean:write name="smsspeciallineForm" property="userSiteHA" scope="request"/>  
     </td>
    </tr>
	<tr>
      <td class="label">客户端是否具有设备</td>
      <td class="content">
	  	<html:errors property="userIsHaveDivA"/>
      <eoms:id2nameDB id="${smsspeciallineForm.userIsHaveDivA}" beanId="ItawSystemDictTypeDao"/>
      </td>
	  <td class="label">是否需要移动采购</td>
      <td class="content">
      	<html:errors property="isNeedBuyA"/>
       	<eoms:id2nameDB id="${smsspeciallineForm.isNeedBuyA}" beanId="ItawSystemDictTypeDao"/>
     </td>
    </tr>
	<tr>
		<td class="label">需要购买的设备</td>
       <td colspan='3'>
	  	<html:errors property="theDevNeededA"/>
       <bean:write name="smsspeciallineForm" property="theDevNeededA" scope="request"/>  
     	</td>
     </tr>
	
	</table>
	<br>
	<%} %>
	
	<%if(sheetType.equals("businessImplement")
	||sheetType.equals("businessImplementSms")
	||taskName.equals("ExplorateTask") 
	||taskName.equals("AccessTask") 
	|| taskName.equals("CityTask")
	 || taskName.equals("CaiXinTask")
	|| taskName.equals("TransfereTask")
	|| taskName.equals("TransferlTask")
	|| taskName.equals("MakeTask")
	|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
    <table class="formTable"> 
    <caption>接入点勘查信息</caption> 
    <tr>
       <td class="label">接入点地址</td>
      <td colspan="3">
	  	<html:errors property="interfacePointAddA"/>
      <bean:write name="smsspeciallineForm" property="interfacePointAddA" scope="request"/>  
      </td>
	</tr>
	<tr>
      <td class="label">接入点站点名称（接入基站）</td>
      <td colspan="3">
	  	<html:errors property="interfaceSiteNameA"/>
        <bean:write name="smsspeciallineForm" property="interfaceSiteNameA" scope="request"/>  
      </td>
	</tr>
    <tr>
      <td class="label">光纤设备名称</td>
      <td class="content">
	  	<html:errors property="fiberEquipNameA"/>
 		<bean:write name="smsspeciallineForm" property="fiberEquipNameA" scope="request"/>  
      </td>
      <td class="label">光纤设备编号</td>
      <td class="content">
	  	<html:errors property="fiberEquipCodeA"/>
     	<bean:write name="smsspeciallineForm" property="fiberEquipCodeA" scope="request"/>  
      </td>
	</tr>
	<tr>
      <td class="label">纤芯个数</td>
      <td class="content">
	  	<html:errors property="fiberAcount"/>
 		<bean:write name="smsspeciallineForm" property="fiberAcount" scope="request"/>  
      </td>
      <td class="label">接入点类型</td>
      <td class="content">
	  	<html:errors property="interfacePointTypeA"/>
	  	<eoms:id2nameDB id="${smsspeciallineForm.interfacePointTypeA}" beanId="ItawSystemDictTypeDao"/>
      </td>
	</tr>
	<tr>
		<td class="label">光缆路由描述</td>
		<td colspan="3">
	  	<html:errors property="fiberAroute"/>
     	<bean:write name="smsspeciallineForm" property="fiberAroute" scope="request"/>  
      </td>
	</tr>
	</table>
	<br>
		<%} %>
	
	<%if(sheetType.equals("businessImplement")
	||sheetType.equals("businessImplementSms")
	||taskName.equals("CityTask")
	 || taskName.equals("CaiXinTask")
	|| taskName.equals("TransfereTask")
	|| taskName.equals("TransferlTask")
	|| taskName.equals("MakeTask")
	|| taskName.equals("AuditTask")
	|| taskName.equals("HandleTask")
	
	|| taskName.equals("ExplorateTask")
	
	|| taskName.equals("HoldTask")){ %>
    <table class="formTable">
    <caption>传输线路勘查信息</caption> 
    <tr>	
	      <td class="label">最后一公里光缆长度(单位：皮长)</td>
	      <td class="content">
		  	<html:errors property="fiberLengthA"/>
	         <bean:write name="smsspeciallineForm" property="fiberLengthA" scope="request"/>  
	      </td>
	      <td class="label">光缆产权</td>
	      <td class="content">
		  	<html:errors property="fiberOwnerA"/>
		    <eoms:id2nameDB id="${smsspeciallineForm.fiberOwnerA}" beanId="ItawSystemDictTypeDao"/>
	      </td>
      </tr>
      <tr>
      	  <td class="label">敷设方式</td>
	      <td class="content">
		  	<html:errors property="buildTypeA"/>
	         <bean:write name="smsspeciallineForm" property="buildTypeA" scope="request"/>  
	      </td>
	      
	      <td class="label">客户端到接入点能否通达</td>
	      <td class="content">
		  	<html:errors property="isOkBetweenUserA"/>
		  	<eoms:id2nameDB id="${smsspeciallineForm.isOkBetweenUserA}" beanId="ItawSystemDictTypeDao"/>
	      </td>
	  </tr>
	  <c:if test="${smsspeciallineForm.isOkBetweenUserA == '101231001'}"> 
	  <tr>
		  <td class="label">不能接入的原因</td>
	      <td colspan="3">
	      	<html:errors property="noInputResonA"/>
	         <bean:write name="smsspeciallineForm" property="noInputResonA" scope="request"/>  
	      </td>
	  </tr>
	  </c:if>
	 </table>
	 <br>
	  <%} %>
	  
	  <%if(sheetType.equals("businessImplement")
	  ||sheetType.equals("businessImplementSms")
	  ||taskName.equals("TransfereTask")||
	    taskName.equals("ProjectDealTask")||
	    taskName.equals("CityNetTask")||
	    
	   taskName.equals("CaiXinTask")||
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("MakeTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("AuditTask")||
	 	
	 	taskName.equals("ExplorateTask")||
	 		
	 	taskName.equals("HoldTask"))
	{ %> 
		<table class="formTable">
		<caption>传输容量勘查信息</caption> 
		    <tr>
		    	<td class="label">传输容量是否满足开通</td>
		     	<td class="content">
				  	<html:errors property="isDeviceAllowOpenA"/>
			        <bean:write name="smsspeciallineForm" property="isDeviceAllowOpenA" scope="request"/>  
		     	</td>
		         <td class="label">是否需要添加板卡</td>
		          <td class="content">
			  	  <html:errors property="isNeedAddCardA"/>
			  	  <eoms:id2nameDB id="${smsspeciallineForm.isNeedAddCardA}" beanId="ItawSystemDictTypeDao"/>
		         </td>
		    </tr>
		      <tr>
					<td class="label">板卡类型</td>
		     		<td class="content">
			  			<html:errors property="cardTypeA"/>
		        		<bean:write name="smsspeciallineForm" property="cardTypeA" scope="request"/>  
		     		</td>
					<td class="label">板卡数量</td>
		     		<td class="content">
			  			<html:errors property="cardNumA"/>
		        	<bean:write name="smsspeciallineForm" property="cardNumA" scope="request"/>  
		     		</td>
				</tr>
		   <br>
	    </table>
	    <br>
	    <%} %>
	    
	  	<%if(taskName.equals("ImplementDealTask")
	  	||sheetType.equals("businessImplementSms")
	  	||taskName.equals("CityTask")||
	  
	  	taskName.equals("CaiXinTask")||
	    taskName.equals("ProjectDealTask")||
	    taskName.equals("CityNetTask")||
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	
	 	taskName.equals("ExplorateTask")||
	 	taskName.equals("MakeTask")||
	 	taskName.equals("AuditTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("HoldTask")
		){ %> 
			<table class="formTable">
			<caption>行业网关接入勘察信息</caption>
			    <tr>
			    	<td class="label">短彩信接入站点名称</td>
			     	<td class="content">
					  	<html:errors property="siteNameZ"/>
				        <bean:write name="smsspeciallineForm" property="siteNameZ" scope="request"/>  
			     	</td>
			         <td class="label">短彩信接入设备名称</td>
			          <td class="content">
			          <html:errors property="portZBDeviceName"/>
				        <bean:write name="smsspeciallineForm" property="portZBDeviceName" scope="request"/>  
			         </td>
			    </tr>
			    <tr>
			         <td class="label">短彩信接入设备端口</td>
			          <td class="content">
			          <html:errors property="portZBDevicePort"/>
				        <bean:write name="smsspeciallineForm" property="portZBDevicePort" scope="request"/>  
			         </td>
			         <td class="label">短彩信接入详细地址</td>
			          <td class="content">
			          <html:errors property="portZDetailAdd"/>
				        <bean:write name="smsspeciallineForm" property="portZDetailAdd" scope="request"/>  
			         </td>
			    </tr>
		    </table>
		    <br>
		<%} %>
     
     	<%if(
     	taskName.equals("TranferTask")||
        taskName.equals("MakeTask")||
		taskName.equals("NetMakeTask")||
		taskName.equals("SupportMakeTask")||
		taskName.equals("BossMakeTask")||
		taskName.equals("DataAcceptTask")||
		taskName.equals("DevelopTask")||
		taskName.equals("InstallTask")||
		taskName.equals("TestTask")||
		taskName.equals("ImplementAcceptTask")||
		taskName.equals("HoldTask")||
		
		taskName.equals("")||
		
	    taskName.equals("ProjectDealTask")||
	    taskName.equals("CityNetTask")||
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")
	){ %> 
	<table class="formTable">
	<caption>最后一公里相关信息</caption> 
  	<tr>
      <td class="label">是否熔接</td>
      <td class="content">
	  	<html:errors property="isGetInterfaceA"/>
	  	      <eoms:id2nameDB id="${smsspeciallineForm.isGetInterfaceA}" beanId="ItawSystemDictTypeDao"/>
      </td>
	<td class="label">熔接序号</td>
      <td class="content">
	  	<html:errors property="getInterfaceNoA"/>
          <bean:write name="smsspeciallineForm" property="getInterfaceNoA" scope="request"/>  
      </td>
    </tr>
	</tr>
	<tr>
       <td class="label">最后一公里处理意见</td>
       <td  class="content" colspan="3">
	  	  <bean:write name="smsspeciallineForm" property="theLastOpinionA" scope="request"/>  
		</td>
	</tr>
	</table>
	<%} %>
	
    <br>
   
        <%if(taskName.equals("TranferTask")||
        taskName.equals("MakeTask")||
		taskName.equals("NetMakeTask")||
		taskName.equals("SupportMakeTask")||
		taskName.equals("BossMakeTask")||
		taskName.equals("DataAcceptTask")||
		taskName.equals("DevelopTask")||
		taskName.equals("InstallTask")||
		taskName.equals("TestTask")||
		taskName.equals("ImplementAcceptTask")||
		
		taskName.equals("HoldTask")||
		taskName.equals("")||
		
        sheetType.equals("businessImplement")&&(
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("HoldTask"))){ %>
	 	 <table class="formTable">
	 	<caption>电路信息</caption> 
	<tr>
      <td class="label">电路名称</td>
      <td class="content">
	  	<html:errors property="circuitName"/>
	  	 <bean:write name="smsspeciallineForm" property="circuitName" scope="request"/>  
      </td>
      <td class="label">电路编号</td>
      <td class="content">
	  	<html:errors property="circuitSheetId"/>
	  	 <bean:write name="smsspeciallineForm" property="circuitSheetId" scope="request"/>  
     </td>
   </tr>

   <%} %>
</table>
<table class="formTable">
<tbody id='BusinessInfo2' > 
	<tr>
		<td class="label">
			说明
		</td>
	   <td colspan="3">
	     <bean:write name="smsspeciallineForm" property="illustrate" scope="request"/>  
	      </td>
	</tr>
	</tbody>
</table>
<%if(isView.equals("1")||taskName.equals("AcceptTask")||taskName.equals("ImplementDealTask")||taskName.equals("TrasitionTask")||(isShowSms.equals("yes")&&taskName.equals("LauguageTask")) ||taskName.equals("ProjectDealTask")) {%>
<input type="button"  value="修改" onclick="modify();"> 
<%} %>
<input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">

</html:form>

