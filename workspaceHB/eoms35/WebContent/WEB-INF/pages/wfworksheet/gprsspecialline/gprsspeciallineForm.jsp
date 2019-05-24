<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.businessupport.product.webapp.form.GprsSpecialLineForm" %>
<%
	String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
	
	if(orderId.equals(""))
		orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));
	System.out.println("orderId====="+orderId);
	String taskName = StaticMethod.nullObject2String(request.getAttribute("taskName"));
		Object gprsspeciallineForm = request.getAttribute("gprsspeciallineForm");
		System.out.println("=====gprsspeciallineForm:"+gprsspeciallineForm);
	if(taskName.equals(""))
		taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
	System.out.println("@@@taskName==form=ProjectDealTask=="+taskName);
	String addr = StaticMethod.nullObject2String(request.getParameter("addr"));
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
 		String userName = sessionform.getUserid();
 	String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
 	
 	String url = "";
	String head = "http://10.25.2.74:8899";
	String host = StaticMethod.nullObject2String(request.getRequestURL());
	if(host.indexOf("10.131.62") <0 ){
		head = "http://10.25.2.74:8899";
	}
 %>
<script type="text/javascript">
 <!--验证-->  
    function initPage(){
		v = new eoms.form.Validation({form:'gprsspeciallineForm'});   		
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
	  		eoms.form.readOnlyArea('cityInfo');
	  	}else if(taskName=="TrasitionTask"){
	  		eoms.form.readOnlyArea('customInfo');
	  		eoms.form.readOnlyArea('interfaceInfo');
	  		eoms.form.readOnlyArea('transLineInfo');
	  		eoms.form.readOnlyArea('transCardInfo');
	  		eoms.form.readOnlyArea('cityInfo');
	  		eoms.form.readOnlyArea('lastInfo');
	  	}
    } 
     Ext.onReady(
       function(){
         initPage();
      }
    );
	function check() {
	    v1 = new eoms.form.Validation({form:'gprsspeciallineForm'});
	    if(v1.check()){
		document.forms[0].submit();		
		window.close();
		}else{
		return false;
		}
	}
	
	
   function needAddCardA(input){
	 	var frm = document.forms[0];
	    var temp = frm.isNeedAddCardA ? frm.isNeedAddCardA.value : '';
		if(temp != ''){
		   	if(input==101231002){
				eoms.form.enableArea('FanKa');
	
				
	       	}else if(input==101231001){
	   
				eoms.form.disableArea('FanKa',true);  
		     
		   	}else {
	
		   		eoms.form.disableArea('FanKa',true);
	 
		   	}	  
		}
   } 
     function needDoubleGGSN(input){
	 	var frm = document.forms[0];
	    var temp = frm.isDoubleGGSN ? frm.isDoubleGGSN.value : '';
		if(temp != ''){
		   	if(input==101231002){
		   		
				eoms.form.enableArea('DoubleGGSN');
	
				
	       	}else{
	   			
				eoms.form.disableArea('DoubleGGSN',true);  
		     
		   	}
		}
   }
	
	  function changeOperate(input){
	 	
	 	var frm = document.forms[0];
	    var temp = frm.userIsRadiusValid ? frm.userIsRadiusValid.value : '';
		if(temp != ''){
		   	if(input==101231002){
				eoms.form.enableArea('yes');
	
				
	       	}else if(input==101231001){
	   
				eoms.form.disableArea('yes',true);  
		     
		   	}else {
	
		   		eoms.form.disableArea('yes',true);
	 
		   	}	  
		}
   }  
   function changeOperate2(input){
	 	
	 	var frm = document.forms[0];
	    var temp = frm.isOkBetweenUserA ? frm.isOkBetweenUserA.value : '';
		if(temp != ''){
		   	if(input==101231001){
				eoms.form.enableArea('YuanYin');
	       	}else if(input==101231002){
	   
				eoms.form.disableArea('YuanYin',true);  
		     
		   	}else {
	
		   		eoms.form.disableArea('YuanYin',true);
	 
		   	}	  
		}
   }  
   function userIsRadiusValidChange(input){
	 	
	 	var frm = document.forms[0];
	    var temp = frm.isNeedBuyA ? frm.isNeedBuyA.value : '';
		if(temp != ''){
		   	if(input==101231002){
				eoms.form.enableArea('NeededA');
	       	}else if(input==101231001){
	   
				eoms.form.disableArea('NeededA',true);  
		     
		   	}else {
	
		   		eoms.form.disableArea('NeededA',true);
	 
		   	}	  
		}
   }  

   
   function popupIrmsPreSurvey(cityCnName){
   			//var addr = "<%=addr%>";
	        var gisUrl=encodeURI("${app}/sheet/resourceconfirm/resourceconfirm.do?method=showTnmsPage&type=1&cityCnName="+cityCnName);	      		        
			var params=window.showModalDialog(gisUrl,"","dialogWidth:"+screen.width*1.0+"px;dialogHeight:"+screen.height*0.9+"px");			
			if(params!=null){					
				var portADetailAdd = $('${sheetPageName}portADetailAdd').value;	
				
					$('${sheetPageName}fiberEquipNameA').value =  params["fiberEquipName"];
					$('${sheetPageName}fiberEquipCodeA').value = params["fiberEquipCode"];
					//$('${sheetPageName}interfaceEquipCodeA').value = params["siteEquipCode"];
					$('${sheetPageName}interfaceSiteNameA').value = params["siteName"];
					//$('${sheetPageName}accessSiteIden').value = params["accessSiteIden"];		

			}
	 
	}
	function popupProjectInfo(){
		var urlstr = "<%=head%>/webmaster/jsp/res/import/custexcelimport/custexcelimport.jsp?includeSpecialties=ProjectResource&userName=admin&prodCode=${gprsspeciallineForm.id}";
		var params=window.showModalDialog(urlstr,"","dialogWidth:"+screen.width*1.0+"px;dialogHeight:"+screen.height*0.9+"px");			
	}
 
	window.name="myname";
 </script> 
<caption><div class="header center">GPRS专线</div></caption>
<html:form action="gprsspeciallines.do?method=xedit" method="post" styleId="gprsspeciallineForm" target="myname">
<br>
<table class="formTable">
	<caption>业务相关信息</caption> 
    <input type="hidden" id="id" name="id" value="${gprsspeciallineForm.id}"/>
    <input type="hidden" id="orderId" name="orderId" value="<%=orderId%>"/>
    <input type="hidden" id="tradeId" name="tradeId" value="${gprsspeciallineForm.tradeId}"/>
    <tbody id='BusinessInfo' > 
    <tr>
	  <td class="label">A站点名称</td>
      <td class="content">
	  	<html:errors property="siteNameA"/>
        <html:text property="siteNameA" styleId="siteNameA"  styleClass="text medium" alt="allowBlank:true"/>
      </td>
      <td class="label">A接口类型及型号</td>
      <td class="content">
	  	<html:errors property="portAInterfaceType"/>
        <eoms:comboBox name="portAInterfaceType" id="portAInterfaceType" defaultValue="${gprsspeciallineForm.portAInterfaceType}" initDicId="1011015" 
	  	       alt="allowBlank:true" styleClass="select-class"/>
     </td>
    </tr>
    <tr>
      <td class="label">A客户端联系人*</td>
      <td colspan="3">
	  	<html:errors property="apointLocalPerson"/>
	  	<html:text property="apointLocalPerson" styleId="apointLocalPerson"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
    </tr>
      
    <tr>
	  <td class="label">A客户端详细地址*</td>
      <td class="content">
      	<html:errors property="portADetailAdd"/>
        <html:text property="portADetailAdd" styleId="portADetailAdd"  styleClass="text medium" alt="allowBlank:false"/>
      </td>
      <td class="label">A客户端联系电话*</td>
      <td class="content">
	  	<html:errors property="portAContactPhone"/>
        <html:text property="portAContactPhone" styleId="portAContactPhone"  styleClass="text medium" alt="allowBlank:false"/>
      </td>
	</tr>
	<tr>
      <td class="label">业务带宽*</td>
      <td colspan="3">
	  	<html:errors property="businessBandwidth"/>
        <html:text property="businessBandwidth" styleId="businessBandwidth"  styleClass="text medium" alt="allowBlank:false"/>
      </td>
    </tr>
   <tr>
      <td class="label">传输接入方式*</td>
      <td class="content">
	  	<html:errors property="transferInputMethod"/>
		<html:text property="transferInputMethod" styleId="transferInputMethod"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
     <td class="label">隧道方式</td>
     <td class="content">
	  	<html:errors property="tunnelType"/>
        <html:text property="tunnelType" styleId="tunnelType"  styleClass="text medium" alt="allowBlank:true"/>
     </td>
   </tr> 
   <tr>
   	 <td class="label">APN名称*</td>
     <td class="content">
	  	<html:errors property="apnName"/>
        <html:text property="apnName" styleId="apnName"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
     <td class="label">APN类型*</td>
     <td class="content">
	  	<html:errors property="apnType"/>
       <eoms:comboBox name="apnType" id="apnType" defaultValue="${gprsspeciallineForm.apnType}" initDicId="1012309" 
	  	       alt="allowBlank:false" styleClass="select-class"/>
     </td>
   </tr> 
   <tr>  
     <td class="label">用户接入路由器IP</td>
     <td class="content">
	  	<html:errors property="userInputRouterIP"/>
        <html:text property="userInputRouterIP" styleId="userInputRouterIP"  styleClass="text medium" alt="allowBlank:true"/>
     </td>
     <td class="label">GRE的企业端IP地址*</td>
     <td class="content">
	  	<html:errors property="greIpAdd"/>
        <html:text property="greIpAdd" styleId="greIpAdd"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
   </tr> 
	<tr>
      <td class="label">集团用户IP地址端需求</td>
        <td  colspan="3">
	  		<textarea name="groupCustomIPDetail" id="groupCustomIPDetail" class="textarea max" alt="allowBlank:true">${gprsspeciallineForm.groupCustomIPDetail}</textarea>			   
		  </td>
   </tr>	
   <tr>
   	 <td class="label">IP地址分配方式*</td>
     <td class="content">
	  	<html:errors property="ipAddressAssign"/>
        <html:text property="ipAddressAssign" styleId="ipAddressAssign"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
     <td class="label">终端IP地址分配方式*</td>
     <td class="content">
	  	<html:errors property="endIDDividType"/>
       <eoms:comboBox name="endIDDividType" id="endIDDividType" defaultValue="${gprsspeciallineForm.endIDDividType}" initDicId="1012311" 
	  	       alt="allowBlank:false" styleClass="select-class"/>
     </td>
   </tr> 
     <tr>
	      <td class="label">是否双GGSN*</td>
	     <td colspan='3'>
		  	<html:errors property="isDoubleGGSN"/>
	       <eoms:comboBox name="isDoubleGGSN" id="isDoubleGGSN" defaultValue="${gprsspeciallineForm.isDoubleGGSN}" initDicId="1011017" 
		  	       alt="allowBlank:false" styleClass="select-class"  />
	     </td>
   </tr>
   </tr> 
	 <td class="label">漫游属性</td>
     <td class="content">
	  	<html:errors property="manYouProperty"/>
       <eoms:comboBox name="manYouProperty" id="manYouProperty" defaultValue="${gprsspeciallineForm.manYouProperty}" initDicId="1012312" 
	  	       alt="allowBlank:true" styleClass="select-class"/>
     </td>      
      <td class="label">计划开通终端数*</td>
     <td class="content">
	  	<html:errors property="planExeEndPointNum"/>
        <html:text property="planExeEndPointNum" styleId="planExeEndPointNum"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
	</tr>
	<tr> 
		<td class="label">终端数量</td>
	     <td class="content">
		  	<html:errors property="endPortNum"/>
	        <html:text property="endPortNum" styleId="endPortNum"  styleClass="text medium" alt="allowBlank:true"/>
	     </td>
	     <td class="label">SIM卡HLR制作范围*</td>
	     <td class="content">
		  	<html:errors property="simAdnHlrScope"/>
	        <html:text property="simAdnHlrScope" styleId="simAdnHlrScope"  styleClass="text medium" alt="allowBlank:false"/>
	     </td>
	 </tr> 
     <tr>
      <td class="label">申请用途*</td>
        <td   colspan="3">
	  		<textarea name="applyPurpose" id="applyPurpose" class="textarea max" alt="allowBlank:false">${gprsspeciallineForm.applyPurpose}</textarea>			   
		  </td>
  	 </tr>
   <tr>
      <td class="label">业务量评估*</td>
     <td colspan='3'>
	  	<html:errors property="businessNumAssessment"/>
        <textarea name="businessNumAssessment" id="businessNumAssessment" class="textarea max" alt="allowBlank:false">${gprsspeciallineForm.businessNumAssessment}</textarea>			   
      
     </td>
     </tr>
   <tr> 
     <td class="label">用户是否用户网站</td>
     <td class="content">
	  	<html:errors property="userIsUserNet"/>
        <eoms:comboBox name="userIsUserNet" id="userIsUserNet" defaultValue="${gprsspeciallineForm.userIsUserNet}" initDicId="1012310" 
	  	       alt="allowBlank:true" styleClass="select-class"/>
     </td>
      <td class="label">用户个性化设备需求</td>
     <td colspan='3'>
	  	<html:errors property="userSpecifyDevNeed"/>
        <textarea name="userSpecifyDevNeed" id="userSpecifyDevNeed" class="textarea max" alt="allowBlank:true">${gprsspeciallineForm.userSpecifyDevNeed}</textarea>			   
     </td>
   </tr>
   <tr> 
   	<td class="label">用户端是否进行RADIUS验证</td>
     <td class="content">
	  	<html:errors property="userIsRadiusValid"/>
       <eoms:comboBox name="userIsRadiusValid" id="userIsRadiusValid" defaultValue="${gprsspeciallineForm.userIsRadiusValid}" initDicId="1012310" 
	  	       alt="allowBlank:true" styleClass="select-class"  onchange="changeOperate(this.value);"/>
     </td>
    </tr> 
  </tbody>
  <tbody id='yes' style="display:none"> 
      <tr>   
      <td class="label">用户端RADIUS服务器IP地址</td>
     <td colspan='3'>
	  	<html:errors property="userRadiusValidateIPAdd"/>
        <html:text property="userRadiusValidateIPAdd" styleId="userRadiusValidateIPAdd"  styleClass="text medium" alt="allowBlank:true"/>
     </td>
   </tr>
   </tbody>
  </table>

    
  
    <br>
   	<%if(sheetType.equals("businessImplement")||taskName.equals("UserTask") || taskName.equals("AccessTask") || taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
    <table class="formTable">
    <caption>客户端勘查信息</caption> 
    <tbody id='customInfo' > 
    <tr>
          <td class="label">A客户端标准地址</td>
       <td  colspan='3'>
       <html:text property="userStardAddA" styleId="userStardAddA"  styleClass="text medium" alt="allowBlank:true"/>
		</td>
    </tr>
	<tr>
      <td class="label">A客户位置经度</td>
      <td class="content">
	  	<html:errors property="userSiteAA"/>
        <html:text property="userSiteAA" styleId="userSiteAA"  styleClass="text medium" alt="allowBlank:true"/>
      </td>
	  <td class="label">A客户位置纬度	</td>
      <td class="content">
      	<html:errors property="userSiteHA"/>
        <html:text property="userSiteHA" styleId="userSiteHA"  styleClass="text medium" alt="allowBlank:true"/>
     </td>
    </tr>
    <tr>
      <td class="label">用户端设备端口类型</td>
      <td class="content">
	  	<html:errors property="portABDeviceType"/>
	  	<eoms:comboBox name="portABDeviceType" id="portABDeviceType" defaultValue="${gprsspeciallineForm.portABDeviceType}" initDicId="1012304" 
		  	       alt="allowBlank:false" styleClass="select-class" />

      </td>
      <td class="label">A建设方式</td>
      <td class="content">
	  	<html:errors property="buildMethodA"/>
           <eoms:comboBox name="buildMethodA" id="buildMethodA" defaultValue="${gprsspeciallineForm.buildMethodA}" initDicId="1012317" 
		  	       alt="allowBlank:false" styleClass="select-class" />
     
      </td>
    </tr>
	<tr>
      <td class="label">A客户端是否具有设备</td>
      <td class="content">
	  	<html:errors property="userIsHaveDivA"/>
	  	 <eoms:comboBox name="userIsHaveDivA" id="userIsHaveDivA" defaultValue="${gprsspeciallineForm.userIsHaveDivA}" initDicId="1012310" 
		  	       alt="allowBlank:false" styleClass="select-class"/>
	  </td>
	  <td class="label">A是否需要移动采购</td>
      <td class="content">
      	<html:errors property="isNeedBuyA"/>
          <eoms:comboBox name="isNeedBuyA" id="isNeedBuyA" defaultValue="${gprsspeciallineForm.isNeedBuyA}" initDicId="1012310" 
		  	       alt="allowBlank:false" styleClass="select-class" onchange="userIsRadiusValidChange(this.value);"/>
     </td>
    </tr>
    </tbody>
    <tbody id="NeededA" style="display:none">
    <tr>	 
      <td class="label">A需要购买的设备</td>
      <td colspan='3'>
	  	<html:errors property="theDevNeededA"/>
        <html:textarea property="theDevNeededA" styleId="theDevNeededA"  styleClass="textarea max" alt="allowBlank:true"/>
      </td>
      </tr>
	</tbody>
	</table>
	<%} %>
	<br>
	<%if(sheetType.equals("businessImplement")||taskName.equals("AccessTask") || taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
	<table class="formTable">
	<caption>接入点勘查信息</caption> 
	<tbody id='interfaceInfo' > 
		<tr>
		<td class="label">接入点机房</td>
	      <td class="content">
		  	<html:errors property="apointComputHouseName"/>
	        <html:text property="apointComputHouseName" styleId="apointComputHouseName"  styleClass="text medium" alt="allowBlank:true"/>
	      </td>
		<td class="label">接入点地址</td>
	      <td class="content">
	      	<html:errors property="interfacePointAddA"/>
	        <html:text property="interfacePointAddA" styleId="interfacePointAddA"  styleClass="text medium" alt="allowBlank:true"/>
	     </td>
    </tr>
	<tr>
      <td class="label">接入点站点名称（接入基站）</td>
      <td colspan="3">
	  	<html:errors property="interfaceSiteNameA"/>
        <html:text property="interfaceSiteNameA" styleId="interfaceSiteNameA"  styleClass="text medium" alt="allowBlank:true"/>
      </td>
	</tr>
	<tr>
      <td class="label">光纤设备名称</td>
      <td class="content">
	  	<html:errors property="fiberEquipNameA"/>
        <html:text property="fiberEquipNameA" styleId="fiberEquipNameA"  styleClass="text medium" alt="allowBlank:true"/>
      </td>
      <td class="label">光纤设备编号</td>
      <td class="content">
	  	<html:errors property="fiberEquipCodeA"/>
        <html:text property="fiberEquipCodeA" styleId="fiberEquipCodeA"  styleClass="text medium" alt="allowBlank:true"/>
      </td>
	</tr>
	<tr>
      <td class="label">纤芯个数*</td>
      <td class="content">
	  	<html:errors property="fiberAcount"/>
        <html:text property="fiberAcount" styleId="fiberAcount"  styleClass="text medium" alt="allowBlank:false"/>
      </td>
      <td class="label">A接入点类型</td>
	      <td class="content">
		  	<html:errors property="interfacePointTypeA"/>
	      <eoms:comboBox name="interfacePointTypeA" id="interfacePointTypeA" defaultValue="${gprsspeciallineForm.interfacePointTypeA}" initDicId="1012316" 
			  	       alt="allowBlank:true" styleClass="select-class" />
	      </td>	 
	</tr>
	<tr>
	      <td class="label">A光缆路由描述</td>
	      <td colspan="3">
		  	<html:errors property="fiberAroute"/>
	        <textarea name="fiberAroute" id="fiberAroute" class="textarea max" alt="allowBlank:true">${gprsspeciallineForm.fiberAroute}</textarea>	
	      </td>
		</tr>
	</tbody>
	<%if(taskName.equals("AccessTask")){%>
	<tr>
		  <td class="label">接入点勘察</td>
		  <td colspan="3">						  	
		  	<a  style="cursor:hand;color:darkbule" onclick="javascript:popupIrmsPreSurvey('${gprsspeciallineForm.portADetailAdd}')">点击进行接入点勘察</a>
		  </td>	
	</tr>
	<%} %>
	</table>
	<%} %>
	
	
	<br>
	<%if(sheetType.equals("businessImplement")||taskName.equals("CityTask")|| taskName.equals("TransfereTask")|| taskName.equals("TransferlTask")|| taskName.equals("MakeTask")|| taskName.equals("AuditTask")|| taskName.equals("HandleTask")|| taskName.equals("HoldTask")){ %>
	<table class="formTable">	
	<caption>传输线路勘查信息</caption> 
	<tbody id='transLineInfo' > 
		<tr>
	      <td class="label">A最后一公里光缆长度(单位：皮长)</td>
	      <td class="content">
		  	<html:errors property="fiberLengthA"/>
	        <html:text property="fiberLengthA" styleId="fiberLengthA"  styleClass="text medium" alt="allowBlank:true"/>
	      </td>
	      <td class="label">A光缆产权</td>
	      <td class="content">
		  	<html:errors property="fiberOwnerA"/>
	       	   <eoms:comboBox name="fiberOwnerA" id="fiberOwnerA" defaultValue="${gprsspeciallineForm.fiberOwnerA}" initDicId="1012315" 
		  	       alt="allowBlank:false" styleClass="select-class"/>
	     </td>
      </tr>
      <tr>
	<tr>
		<td class="label">敷设方式*</td>
	      <td class="content">
		  	<html:errors property="buildTypeA"/>
	        <textarea name="buildTypeA" id="buildTypeA" class="textarea max" alt="allowBlank:true">${gprsspeciallineForm.buildTypeA}</textarea>	
	      </td>
	      <td class="label">A客户端到接入点能否通达*</td>
	      <td class="content">
		  	<html:errors property="isOkBetweenUserA"/>
	        <eoms:comboBox name="isOkBetweenUserA" id="isOkBetweenUserA" defaultValue="${gprsspeciallineForm.isOkBetweenUserA}" initDicId="1012310" 
			  	       alt="allowBlank:false" styleClass="select-class" onchange="changeOperate2(this.value);"/>
	     </td>
     </tr>
     <tr>
	  <tbody id='YuanYin' style="display:none"> 
	     <td class="label">A不能接入的原因*</td>
	     <td colspan='3'>
	      	<html:errors property="noInputResonA"/>
	       	<textarea name="noInputResonA" id="noInputResonA" class="textarea max" alt="allowBlank:true">${gprsspeciallineForm.noInputResonA}</textarea>			   
	     </td>
	     </tbody>
     </tr>
     </tbody>
    </table>
    <%} %>
    <br>
    <table class="formTable">
	<%if(sheetType.equals("businessImplement")||taskName.equals("TransfereTask")||
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
	<tbody id='transCardInfo' > 
    <tr>
    	<td class="label">传输容量是否满足开通×</td>
     	<td class="content">
		  	<html:errors property="isDeviceAllowOpenA"/>
	        <eoms:comboBox name="isDeviceAllowOpenA" id="isDeviceAllowOpenA" defaultValue="${gprsspeciallineForm.isDeviceAllowOpenA}" initDicId="1012310" 
	  	       alt="allowBlank:false" styleClass="select-class" />
         </td>
     	</td>
         <td class="label">是否需要添加板卡</td>
          <td class="content">
	  	  <html:errors property="isNeedAddCardA"/>
          <eoms:comboBox name="isNeedAddCardA" id="isNeedAddCardA" defaultValue="${gprsspeciallineForm.isNeedAddCardA}" initDicId="1012310" 
	  	       alt="allowBlank:true" styleClass="select-class"  onchange="needAddCardA(this.value);"/>
         </td>
    </tr>
     <tbody id='FanKa' style="display:none"> 
      <tr>
			<td class="label">板卡类型</td>
     		<td class="content">
	  			<html:errors property="cardTypeA"/>
        	<html:text property="cardTypeA" styleId="cardTypeA"  styleClass="text medium" alt="allowBlank:true"/>
     		</td>
			<td class="label">板卡数量</td>
     		<td class="content">
	  			<html:errors property="cardNumA"/>
        	<html:text property="cardNumA" styleId="cardNumA"  styleClass="text medium" alt="allowBlank:true"/>
     		</td>
		</tr>
     </tbody>
     </tbody>
   <br>
    </table>
    <%} %>
    
    
	<%if(taskName.equals("ImplementDealTask")||taskName.equals("CityTask")||
	    taskName.equals("ProjectDealTask")||
	    taskName.equals("CityNetTask")||
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HoldTask")
	){ %> 
	<table class="formTable">
	<caption>城域网接入信息</caption> 
	<tbody id='cityInfo' > 
	    <tr>
	    	<td class="label">城域网接入站点名称*</td>
	     	<td class="content">
			  	<html:errors property="siteNameZ"/>
		        <html:text property="siteNameZ" styleId="siteNameZ"  styleClass="text medium" alt="allowBlank:false"/>
	     	</td>
	         <td class="label">城域网接入设备名称</td>
	          <td class="content">
	          <html:errors property="portZBDeviceName"/>
		        <html:text property="portZBDeviceName" styleId="portZBDeviceName"  styleClass="text medium" alt="allowBlank:true"/>
	         </td>
	    </tr>
	    <tr>
	         <td class="label">城域网接入设备端口</td>
	          <td colspan="3">
	          <html:errors property="portZBDevicePort"/>
		        <html:text property="portZBDevicePort" styleId="portZBDevicePort"  styleClass="text medium" alt="allowBlank:true"/>
	         </td>
	    </tr>
    </tbody>
   <!-- @@@ -->
    </table>
    <br>
    <%} %>
    
    <%if(sheetType.equals("businessImplement")&&!taskName.equals("ImplementDealTask")){ %> 
    <table class="formTable">
    <caption>最后一公里相关信息</caption> 	
		<tbody id='lastInfo' > 
	    <tr>	      
	      <td class="label">A是否熔接</td>
	      <td class="content">
		  	<html:errors property="isGetInterfaceA"/>
		  <eoms:comboBox name="isGetInterfaceA" id="isGetInterfaceA" defaultValue="${gprsspeciallineForm.isGetInterfaceA}" initDicId="1012310" 
		  	       alt="allowBlank:false" styleClass="select-class" />
	      </td>
	      <td class="label">A熔接序号</td>
	      <td colspan="3">
		  	<html:errors property="getInterfaceNoA"/>
	        <html:text property="getInterfaceNoA" styleId="getInterfaceNoA"  styleClass="text medium" alt="allowBlank:true"/>
	      </td>		
		</tr>
		<tr>
	      <td class="label">A最后一公里处理意见</td>
	       <td  class="content" colspan="3">
		  		<textarea name="theLastOpinionA" id="theLastOpinionA" class="textarea max" alt="allowBlank:false">${gprsspeciallineForm.theLastOpinionA}</textarea>			   
			</td>
		</tr>
		<tr>
		<td class="label">工程信息</td>
		  <td colspan="3">						  	
		  	<a  style="cursor:hand;color:darkbule" onclick="javascript:popupProjectInfo()">导入工程信息</a>
		  </td>	
		  </tr>
		  </tbody>
	 </table>
	  <br>
    <%} %>
  
  <%if(sheetType.equals("businessImplement")&&(
	 	taskName.equals("TrasitionTask")||
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
        <html:text property="circuitName" styleId="circuitName"  styleClass="text medium" alt="allowBlank:true"/>
     </td>
      <td class="label">电路编号</td>
     <td class="content">
	  	<html:errors property="circuitSheetId"/>
        <html:text property="circuitSheetId" styleId="circuitSheetId"  styleClass="text medium" alt="allowBlank:true"/>
     </td>
   </tr>
   </tbody>
   </table>
   <%} %>
    <br>
    <%if(sheetType.equals("businessImplement")&&(
	 	taskName.equals("GGSNTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("HoldTask"))){ %>
   
    	<table class="formTable">   
    	<caption>GGSN配置</caption>  
	     	<%if(gprsspeciallineForm==null||((GprsSpecialLineForm)gprsspeciallineForm).getId()==null){ %>
		     <tbody id='DoubleGGSN' style="display:none"> 
		     <tr>
		      <td class="label">第一个GGSN的地址池和掩码*</td>
		     <td class="content">
			  	<html:errors property="firstApnAddAndNum"/>
		        <html:text property="firstApnAddAndNum" styleId="firstApnAddAndNum"  styleClass="text medium" alt="allowBlank:false"/>
		     </td>
		      <td class="label">第二个GGSN的地址池和掩码</td>
		     <td class="content">
			  	<html:errors property="secondApnAddAndNum"/>
		        <html:text property="secondApnAddAndNum" styleId="secondApnAddAndNum"  styleClass="text medium" alt="allowBlank:true"/>
		     </td>
			</tr>
			</tbody>
			<%} %>
		     <c:if test="${gprsspeciallineForm.isDoubleGGSN == '101231002'}"> 
		   
		     <tr>
		      <td class="label">第一个GGSN的地址池和掩码*</td>
		     <td class="content">
			  	<html:errors property="firstApnAddAndNum"/>
		        <html:text property="firstApnAddAndNum" styleId="firstApnAddAndNum"  styleClass="text medium" alt="allowBlank:false"/>
		     </td>
		      <td class="label">第二个GGSN的地址池和掩码</td>
		     <td class="content">
			  	<html:errors property="secondApnAddAndNum"/>
		        <html:text property="secondApnAddAndNum" styleId="secondApnAddAndNum"  styleClass="text medium" alt="allowBlank:true"/>
		     </td>
			</tr>
		
			</c:if>
			<tr>	      
		      <td class="label">APN路由方式</td>
		      <td class="content">
			  	<html:errors property="apnRootMethod"/>
		  	       <html:text property="apnRootMethod" styleId="apnRootMethod"  styleClass="text medium" alt="allowBlank:true"/>
		      </td>
		     </tr>
        </table>
   <%} %>
   
   <%if(sheetType.equals("businessImplement")&&(
	 	taskName.equals("TrasitionTask")||
	 	taskName.equals("BusinessTestTask")||
	 	taskName.equals("DredgeAffirmTask")||
	 	taskName.equals("HandleTask")||
	 	taskName.equals("HoldTask"))){ %>
    <br>
    <table class="formTable">
	<caption>APN配置</caption>    
	<tbody id='apnInfo' >  
     <tr>
      <td class="label">APN索引号</td>
     <td colspan="3">
	  	<html:errors property="apnIndex"/>
        <html:text property="apnIndex" styleId="apnIndex"  styleClass="text medium" alt="allowBlank:false"/>
     </td>
   </tr>
   </tbody>
   </table>
   <%} %>
    
  
	<br>
<table  class="formTable">
    <tr>
      <td class="label">备注</td>
        <td  colspan="3">
	  		<textarea name="remark" id="remark" class="textarea max" alt="allowBlank:true">${gprsspeciallineForm.remark}</textarea>			   
		  </td>
   </tr>
 </table>
 <br/>      	
<input type="button"  styleClass="button" onclick="check();" value="保存"/>
<input type="button" style="margin-right: 5px" value="关闭" Onclick="window.close()">
</html:form>
