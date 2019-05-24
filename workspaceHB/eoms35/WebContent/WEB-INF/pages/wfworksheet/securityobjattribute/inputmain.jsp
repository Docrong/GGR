<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","4201");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/securityobjattribute/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "securityobjattribute.do?method=newShowLimit&flowName=SecurityObjAttribute",
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
	<input type="hidden" name="processTemplateName" value="SecurityObjAttribute" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="Audit" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iSecurityObjAttributeMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.securityobjattribute.model.SecurityObjAttributeMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.securityobjattribute.model.SecurityObjAttributeLink"/>
    <br>

    <!-- 工单基本信息 --> 
<table id="sheet" class="formTable" >
 
		<tr><td class="label">
				<!-- ISMP工单流水号 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainISMPSheetId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainISMPSheetId" id="mainISMPSheetId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 ISMP工单流水号 信息，最多输入 2000 字符'" value="${sheetMain.mainISMPSheetId}"/>
			</td>
 <td class="label">
				<!-- 用户账号 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainUserId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainUserId" id="mainUserId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 用户账号 信息，最多输入 2000 字符'" value="${sheetMain.mainUserId}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 最长接受时间 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainAcceptTime"/>
			</td>
			<td>					
				<input type="text" class="text" name="mainAcceptTime" readonly="readonly" 
						id="mainAcceptTime" value="${eoms:date2String(sheetMain.mainAcceptTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
			</td>
 <td class="label">
				<!-- 最长处理时间 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainDealTime"/>
			</td>
			<td>					
				<input type="text" class="text" name="mainDealTime" readonly="readonly" 
						id="mainDealTime" value="${eoms:date2String(sheetMain.mainDealTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 省份 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainProvince"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainProvince" id="mainProvince"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 省份 信息，最多输入 2000 字符'" value="${sheetMain.mainProvince}"/>
			</td>
 <td class="label">
				<!-- 地市 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainCity"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainCity" id="mainCity"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 地市 信息，最多输入 2000 字符'" value="${sheetMain.mainCity}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 派单方式 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainSendWay"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainSendWay" id="mainSendWay"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 派单方式 信息，最多输入 2000 字符'" value="${sheetMain.mainSendWay}"/>
			</td>
 <td class="label">
				<!-- 流程类型 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainProcessType"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainProcessType" id="mainProcessType"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 流程类型 信息，最多输入 2000 字符'" value="${sheetMain.mainProcessType}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 变更时间 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainChangeTime"/>
			</td>
			<td>					
				<input type="text" class="text" name="mainChangeTime" readonly="readonly" 
						id="mainChangeTime" value="${eoms:date2String(sheetMain.mainChangeTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
			</td>
 <td class="label">
				<!-- 操作者账号 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainOperateId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainOperateId" id="mainOperateId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 操作者账号 信息，最多输入 2000 字符'" value="${sheetMain.mainOperateId}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 操作者名称 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainOperateName"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainOperateName" id="mainOperateName"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 操作者名称 信息，最多输入 2000 字符'" value="${sheetMain.mainOperateName}"/>
			</td>
 <td class="label">
				<!-- 变更对象ID -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainObjectId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainObjectId" id="mainObjectId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 变更对象ID 信息，最多输入 2000 字符'" value="${sheetMain.mainObjectId}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 变更对象名称 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainObjectName"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainObjectName" id="mainObjectName"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 变更对象名称 信息，最多输入 2000 字符'" value="${sheetMain.mainObjectName}"/>
			</td>
 <td class="label">
				<!-- 变更属性中文名称 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainAttrName"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainAttrName" id="mainAttrName"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 变更属性中文名称 信息，最多输入 2000 字符'" value="${sheetMain.mainAttrName}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 变更属性字段名称 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainAttrId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainAttrId" id="mainAttrId"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 变更属性字段名称 信息，最多输入 2000 字符'" value="${sheetMain.mainAttrId}"/>
			</td>
 <td class="label">
				<!-- 变更属性之前值 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainAttrBefore"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainAttrBefore" id="mainAttrBefore"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 变更属性之前值 信息，最多输入 2000 字符'" value="${sheetMain.mainAttrBefore}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 变更属性之后值 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainAttrAfter"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainAttrAfter" id="mainAttrAfter"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 变更属性之后值 信息，最多输入 2000 字符'" value="${sheetMain.mainAttrAfter}"/>
			</td>
 <td class="label">
				<!-- 预留字段1 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainExtend1"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend1" id="mainExtend1"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend1}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 预留字段2 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainExtend2"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend2" id="mainExtend2"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段2 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend2}"/>
			</td>
 <td class="label">
				<!-- 预留字段3 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainExtend3"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend3" id="mainExtend3"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段3 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend3}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 预留字段4 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainExtend4"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainExtend4" id="mainExtend4"  alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段4 信息，最多输入 2000 字符'" value="${sheetMain.mainExtend4}"/>
			</td>
 <td class="label">
				<!-- 预留字段5 -->
				<bean:message bundle="securityobjattribute" key="securityObjAttributeMain.mainExtend5"/>
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
		            scope="request" idField="sheetAccessories" appCode="securityobjattribute" alt="allowBlank:true"/> 				
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
			<eoms:chooser id="sendObject"  type="role" roleId="4202" flowId="42" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>