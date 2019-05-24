<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style type="text/css">
	td.mylabel{
		vertical-align: top;
		background-color: #f7f7f7;
		width: 10%;
	}
</style>
<script type="text/javascript">
	var v;
	Ext.onReady(function(){
		v = new eoms.form.Validation({form:'theform'});
	});
	
	function checkRule(){
		var alatmID = document.getElementById('alatmID').value;
		var faultDealDesc = document.getElementById('faultDealDesc').value;
		var faultReasonSort1 = document.getElementById('faultReasonSort1').value;
		var faultReasonSort2 = document.getElementById('faultReasonSort2').value;
		var faultReasonSort3 = document.getElementById('faultReasonSort3').value;
		var preDealRelation = document.getElementById('preDealRelation').value;
		if(alatmID == ''){
			alert('请填入 告警ID 信息。');
			return;
		}
		if(faultDealDesc == ''){
			alert('请填入 故障原因及处理措施 信息。');
			return;
		}
		if(faultReasonSort1 == ''){
			alert('请填入 归因一级 信息。');
			return;
		}
		if(faultReasonSort2 == ''){
			alert('请填入 归因二级 信息。');
			return;
		}
		if(faultReasonSort3 == ''){
			alert('请填入 归因三级 信息。');
			return;
		}
		if(preDealRelation == ''){
			alert('请填入 预处理对应关系 信息。');
			return;
		}
		var form = document.getElementById("theform");
    	var ajaxForm = Ext.getDom(form);
		Ext.Ajax.request({
			method:'post',
			form: ajaxForm,
			url:'ptnpretreatmentrule.do?method=performPreCommit&type=addOrEdit',
			success:function(res){
				var data = eoms.JSONDecode(res.responseText);
				if(data.status == '2'){
					alert(data.text);
				}else{
					form.submit();
				}
			}
		});
	}
</script>
<div style="width:100%">
	<html:form styleId="theform" method="post" action="ptnpretreatmentrule.do?method=save">
		<table class="formTable">
			<tr>
				<td class="mylabel">厂家</td>
				<td class="content">
					<eoms:comboBox name="factory" id="factory" initDicId="1010322"
						defaultValue="${ptnPretreatmentRule.factory }" 
						alt="allowBlank:true,vtext:'请填入 厂家 信息'"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">设备类型</td>
				<td class="content">
					<input type="text" class="text" id="equipmentType" name="equipmentType" 
						alt="allowBlank:true,vtext:'请填入 设备类型 信息'" 
						value="${ptnPretreatmentRule.equipmentType }"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">告警名称</td>
				<td class="content">
					<input type="text" class="text" id="alarmName" name="alarmName" 
						alt="allowBlank:true,vtext:'请填入 告警名称 信息'" 
						value="${ptnPretreatmentRule.alarmName }"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">告警ID*</td>
				<td class="content">
					<input type="text" class="text" id="alatmID" name="alatmID" 
						alt="allowBlank:false,vtext:'请填入 告警ID 信息'" 
						value="${ptnPretreatmentRule.alatmID }"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">故障原因及处理措施*</td>
				<td class="content">
					<eoms:comboBox name="faultDealDesc" id="faultDealDesc" initDicId="1010323"
						defaultValue="${ptnPretreatmentRule.faultDealDesc }" 
						alt="allowBlank:false,vtext:'请填入 故障原因及处理措施 信息'"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">归因一级*</td>
				<td class="content">
					<eoms:comboBox name="faultReasonSort1" id="faultReasonSort1" initDicId="1010303"
						defaultValue="${ptnPretreatmentRule.faultReasonSort1 }" sub="faultReasonSort2" 
						alt="allowBlank:false,vtext:'请填入 归因一级 信息'"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">归因二级*</td>
				<td class="content">
					<eoms:comboBox name="faultReasonSort2" id="faultReasonSort2"
						defaultValue="${ptnPretreatmentRule.faultReasonSort2 }"
						initDicId="${ptnPretreatmentRule.faultReasonSort1 }"
						sub="faultReasonSort3" alt="allowBlank:false,vtext:'请填入 归因二级 信息'"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">归因三级*</td>
				<td class="content">
					<eoms:comboBox name="faultReasonSort3" id="faultReasonSort3"
						defaultValue="${ptnPretreatmentRule.faultReasonSort3 }"
						initDicId="${ptnPretreatmentRule.faultReasonSort2 }"
						alt="allowBlank:false,vtext:'请填入 归因三级 信息'"/>
				</td>
			</tr>
			<tr>
				<td class="mylabel">预处理对应关系*</td>
				<td class="content">
					<eoms:comboBox name="preDealRelation" id="preDealRelation" initDicId="1010321"
						defaultValue="${ptnPretreatmentRule.preDealRelation }"
						alt="allowBlank:false,vtext:'请填入 预处理对应关系 信息'"/>
				</td>
			</tr>
		</table>
		<br/>
		<c:choose>
			<c:when test="${!empty ptnPretreatmentRule.id }">
				<input type="hidden" id="id" name="id" value="${ptnPretreatmentRule.id }"/>
				<input type="button" class="btn" value="保存" onclick="javascript:checkRule();"/>
			</c:when>
			<c:otherwise>
				<input type="button" class="btn" value="新增" onclick="javascript:checkRule();"/>
			</c:otherwise>
		</c:choose>
	</html:form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>