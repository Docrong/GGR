<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","4401");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/equipmentsecurityresponse/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "equipmentsecurityresponse.do?method=newShowLimit&flowName=EquipmentSecurityResponse",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("sheetAcceptLimit").value = "";
        	   // $('sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
   </script>
	<input type="hidden" name="processTemplateName" value="EquipmentSecurityResponse" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="Audit" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iEquipmentSecurityResponseMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.equipmentsecurityresponse.model.EquipmentSecurityResponseMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.equipmentsecurityresponse.model.EquipmentSecurityResponseLink"/>
    <br>

    <!-- 工单基本信息 --> 
<table id="sheet" class="formTable" >
 
		<tr><td class="label">
				<!-- ISMP工单流水号 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainISMPSheetId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainISMPSheetId" id="mainISMPSheetId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 ISMP工单流水号 信息，最多输入 2000 字符'" value="${sheetMain.mainISMPSheetId}"/>
			</td>
 <td class="label">
				<!-- 用户账号 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainUserId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainUserId" id="mainUserId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 用户账号 信息，最多输入 2000 字符'" value="${sheetMain.mainUserId}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 最长接受时间 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainAcceptTime"/>
			</td>
			<td>					
				<input type="text" class="text" name="mainAcceptTime" readonly="readonly" 
						id="mainAcceptTime" value="${eoms:date2String(sheetMain.mainAcceptTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
			</td>
 <td class="label">
				<!-- 最长处理时间 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainDealTime"/>
			</td>
			<td>					
				<input type="text" class="text" name="mainDealTime" readonly="readonly" 
						id="mainDealTime" value="${eoms:date2String(sheetMain.mainDealTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 省份 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainProvince"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainProvince" id="mainProvince"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 省份 信息，最多输入 2000 字符'" value="${sheetMain.mainProvince}"/>
			</td>
 <td class="label">
				<!-- 地市 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainCity"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainCity" id="mainCity"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 地市 信息，最多输入 2000 字符'" value="${sheetMain.mainCity}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 派单方式 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainSendWay"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainSendWay" id="mainSendWay"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 派单方式 信息，最多输入 2000 字符'" value="${sheetMain.mainSendWay}"/>
			</td>
 <td class="label">
				<!-- 告警标题 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainALarmTitle"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainALarmTitle" id="mainALarmTitle"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 告警标题 信息，最多输入 2000 字符'" value="${sheetMain.mainALarmTitle}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 告警时间 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainALarmTime"/>
			</td>
			<td>					
				<input type="text" class="text" name="mainALarmTime" readonly="readonly" 
						id="mainALarmTime" value="${eoms:date2String(sheetMain.mainALarmTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
			</td>
 <td class="label">
				<!-- 告警类型 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainALarmType"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainALarmType" id="mainALarmType"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 告警类型 信息，最多输入 2000 字符'" value="${sheetMain.mainALarmType}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 告警级别名称 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainALarmName"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainALarmName" id="mainALarmName"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 告警级别名称 信息，最多输入 2000 字符'" value="${sheetMain.mainALarmName}"/>
			</td>
 <td class="label">
				<!-- 告警ID -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainALarmID"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainALarmID" id="mainALarmID"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 告警ID 信息，最多输入 2000 字符'" value="${sheetMain.mainALarmID}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 原来源资产ID -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainOrigSourceID"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainOrigSourceID" id="mainOrigSourceID"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 原来源资产ID 信息，最多输入 2000 字符'" value="${sheetMain.mainOrigSourceID}"/>
			</td>
 <td class="label">
				<!-- 原来目的资产ID -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainOrigPurposeID"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainOrigPurposeID" id="mainOrigPurposeID"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 原来目的资产ID 信息，最多输入 2000 字符'" value="${sheetMain.mainOrigPurposeID}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 现在源资产ID -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainNowSourceID"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainNowSourceID" id="mainNowSourceID"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 现在源资产ID 信息，最多输入 2000 字符'" value="${sheetMain.mainNowSourceID}"/>
			</td>
 <td class="label">
				<!-- 现在目的资产ID -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainNowPurposeID"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainNowPurposeID" id="mainNowPurposeID"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 现在目的资产ID 信息，最多输入 2000 字符'" value="${sheetMain.mainNowPurposeID}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 告警内容 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainALarmContent"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainALarmContent" id="mainALarmContent"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 告警内容 信息，最多输入 2000 字符'" value="${sheetMain.mainALarmContent}"/>
			</td>
 <td class="label">
				<!-- 预留字段1 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainExtend1"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend1" id="mainExtend1"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend1}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 预留字段2 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainExtend2"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend2" id="mainExtend2"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段2 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend2}"/>
			</td>
 <td class="label">
				<!-- 预留字段3 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainExtend3"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend3" id="mainExtend3"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段3 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend3}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 预留字段4 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainExtend4"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend4" id="mainExtend4"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段4 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend4}"/>
			</td>
 <td class="label">
				<!-- 预留字段5 -->
				<bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseMain.mainExtend5"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend5" id="mainExtend5"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段5 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend5}"/>
			</td>
		</tr>
 
</table>

	
<!-- 附件 -->
<table id="sheet" class="formTable">
		<!--附件模板-->
		<tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
			    <eoms:attachment name="tawSheetAccess" property="accesss" 
			            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		</tr>		
	    <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="equipmentsecurityresponse" alt="allowBlank:true"/> 				
		    </td>
	   </tr>			  
</table>


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 审批人
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="4402" flowId="44" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>