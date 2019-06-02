<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<script type="text/javascript">
//function selectLimit(obj){
  
//    if($("${sheetPageName}mainSafeAuditType1").value == null ||$("${sheetPageName}mainSafeAuditType1").value ==""){
//        return false;
//    }

//    var temp1=$("${sheetPageName}mainSafeAuditType1").value;
//    var temp2=$("${sheetPageName}mainSafeAuditType2").value;
//    var temp3=$("${sheetPageName}mainSafeAuditType3").value;
//}
function selectLimit(obj){
    if($("${sheetPageName}mainSafeAuditType1").value == null ||$("${sheetPageName}mainSafeAuditType1").value ==""){
       // alert("请选择故障专业！");
        return false;
    }

    var temp1=$("${sheetPageName}mainSafeAuditType1").value;
    var temp2=$("${sheetPageName}mainSafeAuditType2").value;
    var temp3=$("${sheetPageName}mainSafeAuditType3").value;
          
    Ext.Ajax.request({
		method:"get",
		url: "safeaudit.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&flowName=SafeAuditProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	  //  $("${sheetPageName}sheetAcceptLimit").value = "";
        	   // $('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
 </script>
<%
request.setAttribute("roleId", "332");
%>
<%@ include
	file="/WEB-INF/pages/wfworksheet/safeaudit/baseinputmainhtmlnew.jsp"%>

<input type="hidden" name="${sheetPageName}processTemplateName"
	value="SafeAuditProcess" />
<input type="hidden" name="${sheetPageName}operateName"
	value="newWorkSheet" />
<input type="hidden" name="${sheetPageName}operateType"
	id="${sheetPageName}operateType"  value="0" />
<c:if test="${status==0}">
	<input type="hidden" name="${sheetPageName}phaseId" value="EvaluateMaterialHumTask"
		id="${sheetPageName}phaseId" />
</c:if>
<c:if test="${status==1}">
	<input type="hidden" name="${sheetPageName}phaseId"
		id="${sheetPageName}phaseId" value="" />
</c:if>
<input type="hidden" name="${sheetPageName}beanId"
	value="iSafeAuditMainManager" />
<input type="hidden" name="${sheetPageName}mainClassName"
	value="com.boco.eoms.sheet.safeaudit.model.SafeAuditMain" />
<input type="hidden" name="${sheetPageName}linkClassName"
	value="com.boco.eoms.sheet.safeaudit.model.SafeAuditLink" />
<!-- sheet info -->
<br />
<table class="formTable">
	<tr>
		<td class="label"><bean:message bundle="safeaudit"
			key="safeaudit.sheetAcceptLimit" />*</td>
		<td class="content"><input type="text" class="text"
			name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
			id="${sheetPageName}sheetAcceptLimit"
			value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
			onclick="popUpCalendar(this, this)"
			alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:" 受理时限不能晚于处理时限",allowBlank:false"/>

		</td>
		<td class="label"><bean:message bundle="safeaudit"
			key="safeaudit.sheetCompleteLimit" />*</td>
		<td class="content"><input type="text" class="text"
			name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
			id="${sheetPageName}sheetCompleteLimit"
			value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
			onclick="popUpCalendar(this, this)"
			alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:" 完成时限不能早于受理时限",allowBlank:false"/>

		</td>
	</tr>
	<c:if test="${status==1}">
		<!-- 归档时显示已经选择的字典值 -->

	</c:if>
	<c:if test="${status!=1}">
		<tr>
			<td class="label"><bean:message bundle="safeaudit"
				key="safeaudit.mainSafeAuditType1" />*</td>
			<td><eoms:comboBox name="${sheetPageName}mainSafeAuditType1"
				id="${sheetPageName}mainSafeAuditType1"
				sub="${sheetPageName}mainSafeAuditType2" initDicId="1010104"
				defaultValue="${sheetMain.mainSafeAuditType1}" alt="allowBlank:false"
				onchange="selectLimit(this.value);" /></td>
			<td class="label"><bean:message bundle="safeaudit"
				key="safeaudit.mainSafeAuditType2" />*</td>
			<td><eoms:comboBox name="${sheetPageName}mainSafeAuditType2"
				id="${sheetPageName}mainSafeAuditType2"
				sub="${sheetPageName}mainSafeAuditType3"
				initDicId="${sheetMain.mainSafeAuditType1}"
				defaultValue="${sheetMain.mainSafeAuditType2}" alt="allowBlank:false"
				onchange="selectLimit(this.value);" /></td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="safeaudit"
				key="safeaudit.mainSafeAuditType3" />*</td>
			<td><eoms:comboBox name="${sheetPageName}mainSafeAuditType3"
				id="${sheetPageName}mainSafeAuditType3"
				initDicId="${sheetMain.mainSafeAuditType2}"
				defaultValue="${sheetMain.mainSafeAuditType3}" alt="allowBlank:false"
				onchange="selectLimit(this.value);" /></td>
			<td class="label"></td>
			<td></td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="safeaudit"
				key="safeaudit.mainSafeAuditRequest" />*</td>
			<td colspan="3"><textarea class="textarea max"
				name="${sheetPageName}mainSafeAuditRequest"
				id="${sheetPageName}mainSafeAuditRequest"
				alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入安全审计要求，最多输入125字'">${sheetMain.mainSafeAuditRequest}</textarea>
			</td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="tawSheetAccessForm.access" /></td>
			<td colspan="3"><eoms:attachment name="tawSheetAccess"
				property="accesss" scope="request" idField="accesss"
				appCode="toolaccess" viewFlag="Y" /></td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="mainForm.accessories" /></td>
			<td colspan="3"><eoms:attachment name="sheetMain"
				property="sheetAccessories" scope="request"
				idField="${sheetPageName}sheetAccessories" appCode="safeaudit"
				alt="allowBlank:true" /></td>
		</tr>
	</c:if>
</table>
<br />
<c:if test="${status!=1}">
	<fieldset>
	<legend> 
	  <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> :
	  <bean:message bundle="safeaudit" key="role.secmaintenance" />
	</legend> 
	<eoms:chooser
		id="test" type="role" roleId="335" flowId="72"
		config="{returnJSON:true,showLeader:true}" 
        category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
		/>
	</fieldset>
		

</c:if>
