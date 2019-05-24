<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod"%>
<%@page import="com.boco.eoms.sheet.base.task.ITask"%>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain"%>
<%@page
	import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<% 
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform"); 
	String userId = sessionform.getUserid();
	BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
	System.out.println(basemain.getId());
	String sendUserId = basemain.getSendUserId();
%>
<base target="_self">
<script type="text/javascript" src="${app}/scripts/form/validation.js"></script>
<script type="text/javascript" src="${app}/scripts/soprule.js"></script>
<script type="text/javascript">
	var roleTree;
	var v; 
	function initPage(){
		 v = new eoms.form.Validation({form:'theform'});
		 $('btns').show();   
	} 
</script>
<h3 class="sheet-title"></h3>
<html:form
	action="/commonfaultpack.do?method=performDeal&alarmMethod=update&Id=${sheetMain.id}"
	styleId="theform" >
<body >
	<!-- Sheet Tabs Start -->
	<div id="sheet-detail-page"><!-- Sheet Detail Tab Start -->
	<div id="sheetinfo"><logic:present name="sheetMain"
		scope="request">
		<table class="formTable">
			<caption></caption>
			<tr>
				<td class="label">${eoms:a2u('告警标题')}</td>
				<td colspan='3'><bean:write name="sheetMain" property="title"
					scope="request" /></td>
			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmId" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmId" scope="request" /><INPUT id="btn"  type="button" value="sop" onclick="go('002-016-00-009047')"></td>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmNum" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmNum" scope="request" /></td>
			</tr>

			<tr> 
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainFaultGenerantTime" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainFaultGenerantTime" formatKey="date.formatDateTimeAll"
					bundle="sheet" scope="request" /></td>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainApplySheetId" /></td>
				<td colspan='3' class="content"><bean:write name="sheetMain"
					property="mainApplySheetId" scope="request" /></td>

			</tr>
			<tr> 
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmLevel" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmLevel" scope="request" /></td> 
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmSource" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmSource" scope="request" /></td>

			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmLogicSort" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmLogicSort" scope="request" /></td>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmLogicSortSub" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmLogicSortSub" scope="request" /></td>
			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmDesc" /></td>
				<td class="content" colspan='3'><pre><bean:write
					name="sheetMain" property="mainAlarmDesc" scope="request" /></pre></td>
			</tr> 
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainFaultDiscoverableMode" /></td>
				<td class="content"><eoms:id2nameDB
					id="${sheetMain.mainFaultDiscoverableMode}"
					beanId="ItawSystemDictTypeDao" /></td>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainEquipmentModel" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainEquipmentModel" scope="request" /></td>
			</tr> 
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmSolveDate" /></td>
				<td class="content"><bean:write name="sheetMain"
					property="mainAlarmSolveDate" formatKey="date.formatDateTimeAll"
					bundle="sheet" scope="request" /></td>
			</tr>
		</table>
		<c:if test="${isSender=='true'}">
			<div id="btns" class="form-btns" style="display:block"><input
				type="button" onclick="javascript:deleteAlarm();"
				value="<bean:message bundle='commonfaultpack' key='commonfaultpack.delete'/>">
			</div>
		</c:if>
		<c:if test="${alarmMethod==''}">
			<c:if test="${sheetMain.mainAlarmState=='1'}"> 
			&nbsp;&nbsp;<div class='sheet-deal-header'><bean:message
					bundle="commonfaultpack" key="commonfaultpack.replyInfo" /></div>
				<table class="formTable">
					<caption></caption>
					<tr>
						<td class="label"><bean:message bundle="commonfaultpack"
							key="commonfaultpack.replyFaultReasonSort" /></td>
						<td class="content"><eoms:id2nameDB
							id="${sheetMain.replyFaultReasonSort}"
							beanId="ItawSystemDictTypeDao" /></td>
						<td class="label"><bean:message bundle="commonfaultpack"
							key="commonfaultpack.replyFaultReasonSubsection" /></td>
						<td class="content"><eoms:id2nameDB
							id="${sheetMain.replyFaultReasonSubsection}"
							beanId="ItawSystemDictTypeDao" /></td>
					</tr>
					<tr>
							<td class="label"><bean:message bundle="commonfault"
				            	key="commonfault.mainAlarmSolveDate" /></td>
				           <td class="content"><bean:write name="sheetMain"
				            	property="mainAlarmSolveDate" formatKey="date.formatDateTimeAll"
					            bundle="sheet" scope="request" /></td>
					    	<td class="label"><bean:message bundle="commonfaultpack"
							   key="commonfaultpack.replyOperRenewTime" /></td>
					    	<td class="content"><bean:write name="sheetMain"
						    	property="replyOperRenewTime" formatKey="date.formatDateTimeAll"
						    	bundle="sheet" scope="request" /></td>

					</tr>
					<tr>
						<td class="label"><bean:message bundle="commonfaultpack"
							key="commonfaultpack.replyAffectTimeLength" /></td>
						<td class="content"><bean:write name="sheetMain"
							property="replyAffectTimeLength" scope="request" /></td>
						<td class="label"><bean:message bundle="commonfaultpack"
							key="commonfaultpack.replyFaultDealResult" /></td>
						<td class="content" colspan='3'><eoms:id2nameDB
							id="${sheetMain.replyFaultDealResult}"
							beanId="ItawSystemDictTypeDao" /></td>
						</td>
					</tr>
					<tr>
						<td class="label"><bean:message bundle="commonfault"
							key="commonfault.linkIfGreatFault" /></td>
						<td class="content"><eoms:id2nameDB
							id="${sheetMain.replyIfGreatFault}"
							beanId="ItawSystemDictTypeDao" /></td>
						<td class="label"><bean:message bundle="commonfault"
							key="commonfault.linkIfExcuteNetChange" /></td>
						<td class="content"><eoms:id2nameDB
							id="${sheetMain.replyIfExcuteNetChange}"
							beanId="ItawSystemDictTypeDao" /></td>
					</tr>
					<td class="label"><bean:message bundle="commonfault"
						key="commonfault.linkIfFinallySolveProject" />*</td>
					<td colspan="3"><eoms:id2nameDB
						id="${sheetMain.replyIfFinallySolveProject}"
						beanId="ItawSystemDictTypeDao" /></td>
					<tr>
						<td class="label"><bean:message bundle="commonfaultpack"
							key="commonfaultpack.replyDealStep" /></td>
						<td class="content" colspan='3'><bean:write name="sheetMain"
							property="replyDealStep" scope="request" /></td>
					</tr>
				</table>
			</c:if>
		</c:if>
	</logic:present></div>
	<!-- Sheet Detail Tab End --></div>
	<!-- Sheet Tabs End -->


	<c:if test="${alarmMethod=='update'}">
		<div class="sheet-deal">
		<div class='sheet-deal-header'>
		<div class='sheet-deal-header'><bean:message
			bundle="commonfaultpack" key="commonfaultpack.replyInfo" /></div>
		<table id="sheet" class="formTable">
			<tr>
				<td class="label"><bean:message bundle="commonfaultpack"
					key="commonfaultpack.replyFaultReasonSort" /></td>
				<td><eoms:comboBox name="${sheetPageName}replyFaultReasonSort"
					id="${sheetPageName}replyFaultReasonSort" initDicId="1010303"
					sub="${sheetPageName}replyFaultReasonSubsection"
					defaultValue="${sheetMain.replyFaultReasonSort}"
					alt="allowBlank:false" /></td>
				<td class="label"><bean:message bundle="commonfaultpack"
					key="commonfaultpack.replyFaultReasonSubsection" /></td>
				<td><eoms:comboBox
					name="${sheetPageName}replyFaultReasonSubsection"
					id="${sheetPageName}replyFaultReasonSubsection"
					initDicId="${sheetMain.replyFaultReasonSort}"
					defaultValue="${sheetMain.replyFaultReasonSubsection}"
					alt="allowBlank:false" /></td>
			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.linkIfExcuteNetChange" />*</td>
				<td><eoms:comboBox
					name="${sheetPageName}replyIfExcuteNetChange"
					id="${sheetPageName}replyIfExcuteNetChange" initDicId="10301"
					defaultValue="${sheetMain.replyIfExcuteNetChange}"
					alt="allowBlank:false" /></td>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.linkIfFinallySolveProject" />*</td>
				<td><eoms:comboBox
					name="${sheetPageName}replyIfFinallySolveProject"
					id="${sheetPageName}replyIfFinallySolveProject" initDicId="10301"
					defaultValue="${sheetMain.replyIfFinallySolveProject}"
					alt="allowBlank:false" /></td>
			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.mainAlarmSolveDate" /></td>
				<td><input type="text" onclick="popUpCalendar(this, this,null,null,null,true,-1)"
					name="${sheetPageName}mainAlarmSolveDate" readonly="readonly"
					id="${sheetPageName}mainAlarmSolveDate"
					value="${sheetMain.mainAlarmSolveDate}" alt="timer:true" /></td>
				<td class="label"><bean:message bundle="commonfaultpack"
					key="commonfaultpack.replyOperRenewTime" /></td>
				<td><input type="text" onclick="popUpCalendar(this, this)"
					name="${sheetPageName}replyOperRenewTime" readonly="readonly"
					id="${sheetPageName}replyOperRenewTime"
					value="${sheetMain.replyOperRenewTime}" alt="timer:true" /></td>
			</tr>

			<tr>
				<td class="label"><bean:message bundle="commonfaultpack"
					key="commonfaultpack.replyAffectTimeLength" /></td>
				<td><input type="text" class="text"
					name="${sheetPageName}replyAffectTimeLength"
					id="${sheetPageName}replyAffectTimeLength"
					value="${sheetMain.replyAffectTimeLength}" alt="allowBlank:true" /></td>
				<td class="label"><bean:message bundle="commonfaultpack"
					key="commonfaultpack.replyFaultDealResult" /></td>
				<td colspan="3"><eoms:comboBox
					name="${sheetPageName}replyFaultDealResult"
					id="${sheetPageName}replyFaultDealResult" initDicId="1010306"
					defaultValue="${sheetMain.replyFaultDealResult}"
					alt="allowBlank:false" /> </textarea></td>
			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfault"
					key="commonfault.linkIfGreatFault" /></td>
				<td class="content"><eoms:comboBox
					name="${sheetPageName}replyIfGreatFault"
					id="${sheetPageName}replyIfGreatFault" initDicId="10301"
					defaultValue="${sheetMain.replyIfGreatFault}" /></td>
				 <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>*</td>
				  <td>
				    <input type="text" onclick="popUpCalendar(this, this)"
					name="${sheetPageName}replyFaultAvoidTime" readonly="readonly"
					id="${sheetPageName}replyFaultAvoidTime"
					value="${sheetMain.replyFaultAvoidTime}" alt="timer:true" />
				  </td>
			</tr>
			<tr>
				<td class="label"><bean:message bundle="commonfaultpack"
					key="commonfaultpack.replyDealStep" /></td>
				<td colspan="3"><textarea class="textarea max"
					name="${sheetPageName}replyDealStep"
					id="${sheetPageName}replyDealStep" alt="width:500,allowBlank:true">${sheetMain.replyDealStep}</textarea>
				</td>
			</tr>
		</table>
		<div id="btns" class="form-btns" style="display:block"><html:submit
			styleClass="btn" property="method.send" styleId="method.send">
			<bean:message bundle="sheet" key="button.save" />
		</html:submit></div>
		</div>

		<!-- Sheet Deal Content End -->


		<div class="sheet-deal-content" id="sheet-deal-content"></div>
	</c:if>
	</body>
</html:form>
<script language="javascript"> 
 function deleteAlarm()
 {
   if(window.confirm("${eoms:a2u('是否确认删除此告警')}")){
     var frm=document.getElementById("theform"); 
     frm.action="../commonfault/commonfaultpack.do?method=performDeal&alarmMethod=delete&Id=${sheetMain.id}&mainId=${sheetMain.mainId}"; 
     frm.submit();
     }
 }
 function reply()
 { 
   var frm=document.getElementById("theform"); 
   frm.submit();
 }
 </script>
<%@ include file="/common/footer_eoms.jsp"%>
