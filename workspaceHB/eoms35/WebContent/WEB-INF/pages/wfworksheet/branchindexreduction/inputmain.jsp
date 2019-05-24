<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
	request.setAttribute("roleId", "99501");

	long localTimes = com.boco.eoms.base.util.StaticMethod
			.getLocalTime().getTime();
%>
<%@ include
	file="/WEB-INF/pages/wfworksheet/branchindexreduction/baseinputmainhtmlnew.jsp"%>  
<script type="text/javascript">
     	//selectLimit(); 
     	//限制 受理时限 和 完成时限 


	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "branchindexreduction.do?method=newShowLimit&flowName=BranchIndexReduction",
		success: function(x){  
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("sheetAcceptLimit").value = "";
        	   // $('sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,4));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,6));
	           	$("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
   var v1 = eoms.form; 

   function selectRoleId(val){ // 选择子角色 
   		var url = "${app}/sheet/branchindexreduction/branchindexreduction.do?method=getSubRole&mainSubtractProfessional="+val;
		//alert(url);
		//alert(mainSubtractProfessional);
		Ext.Ajax.request({    
		       url: url,    
		       method: "post",  
		       success: function (x) {    
		            var result = eoms.JSONDecode(x.responseText);
			        //alert(!result.subRoleId);
			        if(result.subRoleId !=''){
			        	//alert(subRoleId);
				        v1.enableArea('div2');//生效
						v1.disableArea('div1',true);//失效
				        document.getElementById("dealPerformerLeader1").value = result.subRoleId;
				        document.getElementById("dealPerformer1").value = result.subRoleId;
				        document.getElementById("dealPerformerType1").value = result.type;
				        document.getElementById("subRoleName").innerHTML = result.subRoleName;
			        }else{
			        	v1.enableArea('div1');//生效
						v1.disableArea('div2',true);//失效
			        }
		       }
	    });
   
   }
   // add by 20180127
   function selectSheet(sheet){
	    var mainSubtractIndexName = document.getElementById("mainSubtractIndexName").value;
	    var fm = eoms.form;
	    //alert(mainSubtractIndexName);   
		//根据 核减指标类型来选择附件是否为必填
		
		if((mainSubtractIndexName =='101060202') || (mainSubtractIndexName =='101060203') ){
			fm.enableArea("ss");
		    fm.disableArea("cc",true);
		}else{
			fm.enableArea("cc");
		    fm.disableArea("ss",true);
		}  
	}
	
	//add by lyg
	 function selectSubRole(val){ // 选择子角色 
	 	if(val != null && val != null){
	 		var url = "${app}/sheet/branchindexreduction/branchindexreduction.do?method=getSubRoleId&mainSubtractReason="+val;
			Ext.Ajax.request({    
		       url: url,    
		       method: "post",  
		       success: function (x) {    
		            var result = eoms.JSONDecode(x.responseText);
			        if(result.subRoleId !=''){
				        v1.enableArea('div2');//生效
						v1.disableArea('div1',true);//失效
				        document.getElementById("dealPerformerLeader1").value = result.subRoleId;
				        document.getElementById("dealPerformer1").value = result.subRoleId;
				        document.getElementById("dealPerformerType1").value = result.type;
				        document.getElementById("subRoleName").innerHTML = result.subRoleName;
			        }else{
			        	v1.enableArea('div1');//生效
						v1.disableArea('div2',true);//失效
			        }
		       }
	    	});	
	 	}
   		
   
   }
</script>
   
<input type="hidden" name="processTemplateName"
	value="BranchIndexReduction" />
<input type="hidden" name="operateName" value="newWorkSheet" />
<c:if test="${status!=1}">
	<input type="hidden" name="phaseId" id="phaseId" value="TrialTask" />

	<input type="hidden" id="operateType" name="operateType" value="0" />
	<input type="hidden" name="gatherPhaseId" id="gatherPhaseId"
		value="HoldTask" />
</c:if>

<c:if test="${status==1}">
	<input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
</c:if>
<input type="hidden" name="beanId"
	value="iBranchIndexReductionMainManager" />  
<input type="hidden" name="mainClassName"
	value="com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionMain" />
<input type="hidden" name="linkClassName"
	value="com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionLink" />
<input type="hidden" id="listsize" name="listsize" />                                          <!-- 隐藏 listsize -->
<input type="hidden" id="noPass" name="noPass" />
<br>

<!-- 工单基本信息 -->     
<input type="hidden" id="mainReserveA" name="mainReserveA" value="${sheetMain.mainReserveA }"/> 
<table id="sheet" class="formTable">
  <tr>
  	<td>mainReserveA</td>
  	<td>${sheetMain.mainReserveA }</td>
  </tr>
	
	
	<c:choose>
		<c:when test="${newFlag == 'newFlag' || sheetMain.newFlag == 'newFlag'}">
			<tr>
					
			    <td class="label">核减专业 *</td>
				<td class="content"><eoms:comboBox  
					name="mainSubtractProfessional" id="mainSubtractProfessional" defaultValue="${sheetMain.mainSubtractProfessional}"
					sub="mainSubtractIndexName"
					initDicId="10143" alt="allowBlank:false"/>
				</td>
				<td class="label">核减指标类型*</td>   
				<td class="content">
					<eoms:comboBox 
					name="mainSubtractIndexName" id="mainSubtractIndexName"  defaultValue="${sheetMain.mainSubtractIndexName}" 
					sub="mainSubtractReason"
					 alt="allowBlank:false"/>
				</td>
			</tr>
			<tr>
				<td class="label">核减原因模板*</td> 
				<input type="hidden" id="newFlag" name="newFlag" value="${newFlag }"/>  
				<td class="content">
					<eoms:comboBox  onchange="selectSubRole(this.value);"
					name="mainSubtractReason" id="mainSubtractReason" 
					defaultValue="${sheetMain.mainSubtractReason}" 
					 alt="allowBlank:false"/>
				</td>
				
				<td class="label">地市*</span></td>
				<td class="content">
					<select name="mainAreaId" id="mainAreaId" class="input select"  alt="allowBlank:false,vtext:'请选择所在地市'">
						<option value="">
							
						</option>
						<logic:iterate id="cityList" name="cityList">
							<option value="${cityList.keyId}" <c:if test="${sheetMain.mainAreaId==cityList.keyId}">selected="selected"</c:if>>${cityList.keyName}</option>
						</logic:iterate>
					</select>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="label">核减指标类型*</td>   
				<td class="content">
					<eoms:comboBox  onchange="selectSheet(this.value);"
					name="mainSubtractIndexName" id="mainSubtractIndexName" 
					defaultValue="${sheetMain.mainSubtractIndexName}" 
					initDicId="1014103" alt="allowBlank:false"/></td>
				<td class="label"><!-- 核减专业 --> <bean:message
					bundle="branchindexreduction"
					key="branchIndexReductionMain.mainSubtractProfessional" />*</td>
				<td class="content"><eoms:comboBox   onchange="selectRoleId(this.value)"
					name="mainSubtractProfessional" id="mainSubtractProfessional" defaultValue="${sheetMain.mainSubtractProfessional}"
					initDicId="1014101" alt="allowBlank:false"/></td>
			</tr>
		</c:otherwise>
	
	</c:choose>

	
	<tr>
		<td class="label">申请核减时间*</td>
		<td class="content">
			<input type="text" class="text" name="mainSubtractTime" readonly="readonly"
						id="mainSubtractTime" value="${eoms:date2String(sheetMain.mainSubtractTime)}" 
						 alt="allowBlank:false"/> </td>
		<td class="label">受理时限*</td>
		<td class="content">
			<input type="text" class="text" name="sheetAcceptLimit" readonly="readonly" 
						id="sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
						 alt="allowBlank:false"/> </td>
		
	</tr>
	<tr>
		<td class="label">处理时限*</td>
		<td class="content">
			<input type="text" class="text" name="sheetCompleteLimit" readonly="readonly"
						id="sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
						 alt="allowBlank:false"/> </td>
		<td class="label">初审处理时限*</td>
		<td class="content">  
			<input type="text" class="text" name="mainProcessTime" readonly="readonly" 
						id="mainProcessTime" value="${eoms:date2String(sheetMain.mainProcessTime)}" 
						 alt="allowBlank:false"/> </td>
		
	</tr>
	<tr>
		<td class="label"><!-- 核减信息确认人 --> <bean:message
			bundle="branchindexreduction"
			key="branchIndexReductionMain.mainConfirmingPerson" /></td>
		<td><input type="text" class="text" name="mainConfirmingPerson"
			id="mainConfirmingPerson"
			alt="allowBlank:true,maxLength:20,vtext:'请填入 核减信息确认人 信息，最多输入 20 字符'"
			value="${sheetMain.mainConfirmingPerson}" />
		</td>
		<td class="label"><!-- 核减信息确认人电话 --> <bean:message
			bundle="branchindexreduction"
			key="branchIndexReductionMain.mainConfirmingTelephone" /></td>
		<td><input type="text" class="text"
			name="mainConfirmingTelephone" id="mainConfirmingTelephone"
			alt="allowBlank:true,maxLength:20,vtext:'请填入 核减信息确认人电话 信息，最多输入 20 字符'"
			value="${sheetMain.mainConfirmingTelephone}" />
		</td>
	</tr>
	
	
  
  
  <c:choose>
  		<c:when test="${newFlag == 'newFlag'  || sheetMain.newFlag == 'newFlag'}">
  			<tr style="height:35%;">
				<td class="label">核减内容</td>
				<td colspan="3">
						<eoms:attachment name="sheetMain" alt="allowBlank:false" 
							property="" scope="request"
							idField="mainReserveD" appCode="branchindexreduction"/>
			  	</td>
			  </tr>
  		</c:when>
  		<c:otherwise>
  			<!-- 核减内容Excel表 -->
  			<c:choose>
  				<c:when test="${operateType == '54' }">
  					<tr style="height:35%;">
						<td class="label">核减内容*</td>
						<td style="height:25%;" colspan="3"><eoms:attachmentWlf scope="request"
							idField="sheetAccessories1" name="sheetMain" 
							property="" appCode="branchindexreduction" sheetFlag="renew"
							alt="allowBlank:true" /></td>
					</tr>
  		   
  				</c:when>
  				<c:otherwise>
  					<tr style="height:35%;">
						<td class="label">核减内容</td>
						<td colspan="3"><eoms:attachmentWlf scope="request"
							idField="sheetAccessories1" name="sheetMain"
							property="" appCode="branchindexreduction" sheetFlag="new"
							alt="allowBlank:true" /></td> 
					</tr> 
  				</c:otherwise>
  			</c:choose> 
  			<!-- 核减内容Excel表 end -->
  		</c:otherwise>
  </c:choose>
  
	
	<!-- 隐藏一个iframe的 入数据信息列表 -->
	<c:if test="${operateType == null || operateType == '' }">
		<tr id="importInforList" style="display:none;">
			<td colspan="4"><iframe src="" scrolling="auto" width="100%"
				id="iframepage"></iframe></td>
		</tr>
	</c:if>
	
	<tr>
		<td class="label"><!-- 说明 --> <bean:message
			bundle="branchindexreduction"
			key="branchIndexReductionMain.mainIllustrate" /></td>
		<td colspan="3"><textarea class="textarea max"
			name="mainIllustrate" id="mainIllustrate"
			alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetMain.mainIllustrate}</textarea>
		</td>
	</tr>

</table>
	<!-- 附件 -->
<table id="sheet" class="formTable">
	<tbody id="ss" style="display:none">	
		<tr>
				<td class="label">核减依据*</td>
			<td colspan="3"><eoms:attachment name="sheetMains" alt="allowBlank:false" 
				property="" scope="request"
				idField="sheetAccessories" appCode="branchindexreduction"
				/></td>  
		</tr>			 
	</tbody>
	<tbody id="cc"> 
		<tr>
			<td class="label">核减依据*</td>
			<td colspan="3"><eoms:attachment name="sheetMain" alt="allowBlank:true"
				property="" scope="request"
				idField="sheetAccessories" appCode="branchindexreduction"
				/></td>
		</tr>

	</tbody> 
</table>

	
<c:if test="${operateType == '54' && sheetMain.newFlag !='newFlag'}">
	<table class="formTable">
		<tr id="importInforList">
			<td colspan="4"><iframe src="branchindexreduction.do?method=search&ifShowOther=no&ifSend=no&refSheetId=${sheetMain.mainReserveA }&scrolling="auto" width="100%"
				id="iframepage"></iframe></td>
		</tr>
	</table>
	<br/>
	<div id="btn" align="right">
		<input type="button" class="btn"  onclick="exportList();" value="导出未通过核减列表" />
	</div>
	<script type="text/javascript">
		function exportList(){
			var url = 'branchindexreduction.do?method=exportList&refSheetId=${sheetMain.mainReserveA }';
			window.location.href = url;
		}
	</script>
</c:if>
	

<!--派单树-->
<br />
<div id="div1">
	<fieldset><legend> <bean:message bundle="sheet"
		key="mainForm.toOperateRoleName" />： <span id="roleName"> 初审人 </span> </legend>
	<div class="x-form-item"><eoms:chooser id="sendObject"
		type="role" roleId="99502" flowId="995"
		config="{returnJSON:true,showLeader:true}"
		category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
		data="${sheetMain.sendObject}" /></div>
	</fieldset>
</div>
<div id="div2">
	<fieldset>
	<legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>: 初审人 </legend>
	<input name="dealPerformerLeader" id="dealPerformerLeader1" type="hidden" value="" alt="allowBlank:false,vtext:'未匹配到派往对象'"/>
	<input name="dealPerformer" id="dealPerformer1" value="" type="hidden"/>
	<input name="dealPerformerType" id="dealPerformerType1" value="" type="hidden"/>
	<span id="subRoleName"></span>
	</fieldset>
</div>  
 <script type="text/javascript">
	
     
   Ext.onReady(function(){
   		var val = document.getElementById("mainSubtractProfessional").value;
   		selectRoleId(val);
   });
   
</script>   
