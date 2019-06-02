<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","146");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/equipmentinstallation/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "equipmentinstallation.do?method=newShowLimit&flowName=EquipmentInstallation",
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
   
   function selectFJ(val){
   		//alert(val.value);
   		if(val.value == '101390301'){// 是
   			eoms.form.disableArea('changeee',true);
   		}else if(val.value == '101390302'){ //否 显示
   			eoms.form.enableArea('changeee');
   		}
   }
   </script>
	<input type="hidden" name="processTemplateName" value="EquipmentInstallation" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="Audit" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iEquipmentInstallationMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.equipmentinstallation.model.EquipmentInstallationMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.equipmentinstallation.model.EquipmentInstallationLink"/>
    <br>

    <!-- 工单基本信息 --> 
<table id="sheet" class="formTable" >
 
		<tr><td class="label">
				<!-- 投诉工单号 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainComplaintSheetId"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainComplaintSheetId" id="mainComplaintSheetId"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 投诉工单号 信息，最多输入 1000 字符'" value="${sheetMain.mainComplaintSheetId}"/>
			</td>
 			<td class="label">
				<!-- 投诉人姓名 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainComplainter"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainComplainter" id="mainComplainter"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 投诉人姓名 信息，最多输入 1000 字符'" value="${sheetMain.mainComplainter}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 投诉号码 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainComplaintNum"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainComplaintNum" id="mainComplaintNum"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 投诉号码 信息，最多输入 1000 字符'" value="${sheetMain.mainComplaintNum}"/>
			</td>
 			<td class="label">
				<!-- 用户星级 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainUserStar"/>*
			</td>
			<td>
				<eoms:comboBox name="mainUserStar" id="mainUserStar" 
			  	       initDicId="1013901" defaultValue="${sheetMain.mainUserStar}" alt="allowBlank:false" />
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 安装设备类型 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainEquipmentType"/>*
			</td>
			<td>
				<eoms:comboBox name="mainEquipmentType" id="mainEquipmentType" 
			  	       initDicId="1013902" defaultValue="${sheetMain.mainEquipmentType}" alt="allowBlank:false" />
			</td>
 			<td class="label">
				<!-- 地市 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainCity"/>*
			</td>
			<td>
					<select name="mainCity" id="mainCity"   alt="allowBlank:false,vtext:'请选择所在地市'">
						<option value="">请选择
						</option>
						<logic:iterate id="cityList" name="cityList">
							<option value="${cityList.keyId}" <c:if test="${sheetMain.mainCity==cityList.keyId}">selected="selected"</c:if>>${cityList.keyName}</option>
						</logic:iterate>
					</select>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 详细地址信息 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainAddress"/>*
			</td>
			<td colspan="3">
				<input type="text"  class="text max" name="mainAddress" id="mainAddress"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 详细地址信息 信息，最多输入 1000 字符'" value="${sheetMain.mainAddress}"/>
			</td>
		</tr>
		
		<tr>
		     <td class="label">
		  	 	<!-- 投诉原因 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainComplaintReasons"/>*
		     </td>
		  	 <td colspan="3">
		  	 <textarea name="mainComplaintReasons" class="textarea max" id="mainComplaintReasons" 
		        alt="allowBlank:false,maxLength:1000,vtext:'请填写投诉原因，最多输入1000字符'">${sheetMain.mainComplaintReasons}</textarea>
		  	 </td>		  
		</tr>
		
		<tr>
		     <td class="label">
		  	 	<!-- 备注说明 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainDescr"/>
		     </td>
		  	 <td colspan="3">
		  	 <textarea name="mainDescr" class="textarea max" id="mainDescr" 
		        alt="allowBlank:true,maxLength:1000,vtext:'请填写备注说明，最多输入1000字符'">${sheetMain.mainDescr}</textarea>
		  	 </td>		  
		</tr>
 		
		<tr>
 			<td class="label">
				<!-- 典型场景 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainTypicalScenario"/>*
			</td>
			<td>
				<eoms:comboBox name="mainTypicalScenario" id="mainTypicalScenario" 
			  	     sub="mainChildScenario"  initDicId="1013904" defaultValue="${sheetMain.mainTypicalScenario}" alt="allowBlank:false" />
			</td>
			
			<td class="label">
				<!-- 子场景 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainChildScenario"/>*
			</td>
			<td>
				<eoms:comboBox name="mainChildScenario" id="mainChildScenario" 
			  	       initDicId="${sheetMain.mainTypicalScenario}" defaultValue="${sheetMain.mainChildScenario}" alt="allowBlank:false" />
			</td>
		</tr>
 
		<tr>
 			<td class="label">
				<!-- 设备是否收取服务费 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainIsEquipmentFee"/>*
			</td>
			<td colspan="3">
				<eoms:comboBox name="mainIsEquipmentFee" id="mainIsEquipmentFee" 
			  	       initDicId="1013903" defaultValue="${sheetMain.mainIsEquipmentFee}" alt="allowBlank:false"  onchange="selectFJ(this)"/>
			</td>
		</tr>
 		<tbody id="changeee" style="">
		<tr><td class="label">
				<!-- 三级经理签字照片 -->
				<bean:message bundle="equipmentinstallation" key="equipmentInstallationMain.mainSignPhoto"/>*
			</td>
			<td colspan="3">
				<eoms:attachment name="sheetMain" property="mainSignPhoto" 
		            scope="request" idField="mainSignPhoto" appCode="equipmentinstallation" alt="allowBlank:false"/> 				
			</td>
		</tr>
 		</tbody>
		
 
</table>

	
<!-- 附件 -->
<!-- 
<table id="sheet" class="formTable">
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
		            scope="request" idField="sheetAccessories" appCode="equipmentinstallation" alt="allowBlank:true"/> 				
		    </td>
	   </tr>			  
</table> -->


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 分公司网优中心主任
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="147" flowId="24" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>