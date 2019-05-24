<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">

var frmReg;
Ext.onReady(function(){
	 frmReg = new eoms.form.Validation({form:'newFormPage'});
});

function onBack()
{
  window.close();
}

</script>

<form name="newFormPage" method="POST" id="newFormPage" action="numberapply.do?method=performSignalSave">
<table class="formTable">
	<input type="hidden"   name="sheetKey" id="sheetKey"   value="${sheetKey}"/>
	<input type="hidden"   name="type" id="type" />
	<script type="text/javascript">
		if('${type}' =='new' ){
			$('type').value = 'new';
		}else{
			$('type').value = 'edit';
		}
	</script>
	<c:if test="${!empty numberApplyHlrid.id }">
		<input type="hidden"   name="id" id="id"   value="${numberApplyHlrid.id }"/>
	</c:if>
	<input type="hidden"   name="actionForword" id="actionForword"   value="hlrlist"/>
	<tr>
		<td class="label">网元名称*</td>
		<td class="content">
			<input type="text"  class="text" name="netName" id="netName"   value="${numberApplyHlrid.netName }" alt="allowBlank:false,vtext:'请填写网元名称'"/>
		</td>
		<td class="label">网元代号*</td>
		<td class="content">
			<input type="text"  class="text" name="netId" id="netId"   value="${numberApplyHlrid.netId }" alt="allowBlank:false,vtext:'请填写网元代号'"/>
		</td>
	</tr>
	<tr>
		<td class="label">网元属性*</td>
		<td class="content">
			<input type="text"  class="text" name="netProp" id="netProp"   value="${numberApplyHlrid.netProp }" alt="allowBlank:false,vtext:'请填写网元属性'"/>
		</td>
		<td class="label">建设地点*</td>
		<td class="content">
			<input type="text"  class="text" name="buildAddress" id="buildAddress"   value="${numberApplyHlrid.buildAddress }" alt="allowBlank:false,vtext:'请填写建设地点'"/>
		</td>
	</tr>
	<tr>
		<td class="label">供应商*</td>
		<td class="content"><!-- 供应商的字典值 -->
			<eoms:comboBox name="supplier" id="supplier"  initDicId="1013404"  alt="allowBlank:false" 
			styleClass="select-class" defaultValue="${numberApplyHlrid.supplier}" />
		</td>
		<td class="label">信令点编码(24位)</td>
		<td class="content">
			<input type="text" class="text"  name="commandCode24" id="commandCode24" value=""  />
		</td>
	</tr>
	<tr>
		<td class="label">LLRID</td>
		<td class="content">
			<input type="text"  class="text"   name="hlrId" id="hlrId"   value=""/>
		</td>
		<td class="label">信令点编码(14位)</td>
		<td class="content">
			<input type="text"  class="text"   name="commandCode14" id="commandCode14"   value=""/>
		</td>
	</tr>
	<tr>
		<td class="label">硬件平台</td>
		<td class="content">
			<input type="text"  class="text" name="hardwareFlatRoof" id="hardwareFlatRoof"   value="${numberApplyHlrid.hardwareFlatRoof }"/>
		</td>
		<td class="label">软件版本</td>
		<td class="content">
			<input type="text"  class="text" name="softwareVersion" id="softwareVersion"   value="${numberApplyHlrid.softwareVersion }"/>
		</td>
	</tr>
	<tr>
		<td class="label">容量（万）</td>
		<td class="content">
			<input type="text"  class="text" name="capability" id="capability"   value="${numberApplyHlrid.capability }"/>
		</td>
		<td class="label">信令链路数（2MB）</td>
		<td class="content">
			<input type="text"  class="text" name="commondLink" id="commondLink"   value="${numberApplyHlrid.commondLink }"/>
		</td>
	</tr>
	<tr>
		<td class="label">E1端口总数（承载窄带)</td>
		<td class="content">
			<input type="text"  class="text" name="portCount" id="portCount"   value="${numberApplyHlrid.portCount }"/>
		</td>
		<td class="label">覆盖地区</td>
		<td class="content">
			<input type="text"  class="text" name="coverArea" id="coverArea"   value="${numberApplyHlrid.coverArea }"/>
		</td>
	</tr>
	<tr>
		<td class="label">覆盖地区长途区号</td>
		<td class="content">
			<input type="text"  class="text" name="areaNumber" id="areaNumber"   value="${numberApplyHlrid.areaNumber }"/>
		</td>
		<td class="label">城市</td>
		<td class="content">
			<input type="text"  class="text" name="city" id="city"  value="${numberApplyHlrid.city }"/>
		</td>
	</tr>
	
	<tr>
		<td class="label">设备所在本地网名称</td>
		<td class="content">
			<input type="text"  class="text" name="deviceName" id="deviceName"  value="${numberApplyHlrid.deviceName }"/>
		</td>
		<td class="label">归属区域</td>
		<td class="content">
			<input type="text"  class="text" name="attachArea" id="attachArea"   value="${numberApplyHlrid.attachArea }"/>
		</td>
	</tr>
	<tr>
		<td class="label">批复文件号</td>
		<td class="content"  colspan="3">
			<input type="text"  class="text" name="fileNumber" id="fileNumber"  value="${numberApplyHlrid.fileNumber }"/>
		</td>
	</tr>
</table>
<script type="text/javascript">
if('${numberApplyHlrid.commandCode24}' == ''){
	$('commandCode24').value = '等待管理员分配';
	var element = document.getElementById("commandCode24");
	element.setAttribute("readonly","true",0); 
}else{
	$('commandCode24').value = '${numberApplyHlrid.commandCode24}';
}

if('${numberApplyHlrid.commandCode14}' == ''){
	$('commandCode14').value = '等待管理员分配';
	var element = document.getElementById("commandCode14");
	element.setAttribute("readonly","true",0); 
}else{
	$('commandCode14').value = '${numberApplyHlrid.commandCode14}';
}

if('${numberApplyHlrid.hlrId}' == ''){
	$('hlrId').value = '等待管理员分配';
	var element = document.getElementById("hlrId");
	element.setAttribute("readonly","true",0); 
}else{
	$('hlrId').value = '${numberApplyHlrid.hlrId}';
}
</script>


<input type="submit"  value="保存" class="submit">
<input type="button" value="关闭"  Onclick="onBack();" class="button">
</form>

<%@ include file="/common/footer_eoms.jsp"%>
