<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>



<form method="post" id="theform" action="commonfaultauto.do?method=save">
<table class="formTable">
			<caption>规则配置</caption>
			<logic:equal name="ruleType" value="autoHold">
					<tr>
					  	<td class="label">措施</td>
					  	<td>
					  	 	  <textarea class="textarea max"  name="commonFaultDesc" id="commonFaultDesc">${commonfaultAuto.commonFaultDesc}</textarea>
					  	</td> 
					</tr>
					<tr>
					  	<td class="label">网络告警ID</td>
					  	<td>
					  	 	 <textarea class="textarea max"  name="remark1" id="remark1">${commonfaultAuto.remark1}</textarea>
					  	</td> 
					</tr>
					
					<tr>
					  	<td class="label">归档内容</td>
					  	<td>
					  	 	 <textarea class="textarea max"  name="remark2" id="remark2">${commonfaultAuto.remark2}</textarea>
					  	</td> 
					</tr>
			</logic:equal>
			
		    			
		 <tr>
			  		<td class="label">是否自动归档</td>
			  		<td width="100%">
			  	 		<select name="sheetType" ${type == 'open' ? 'disabled="disabled"': ''}>
			  	 			<option value="auto" ${commonfaultAuto.sheetType == 'no' ? 'auto' : ''}>自动</option>
			  	 			<option value="handler" ${commonfaultAuto.sheetType == 'no' ? 'handler' : ''}>手动</option>
			  	 		</select>
			  		</td>					  
		   	</tr>
		   	<tr>
			  		<td class="label">功能开/关</td>
			  		<td width="100%">
			  	 		<select name="colseSwitch" ${type == 'open' ? 'disabled="disabled"': ''}>
			  	 			
			  	 			<option value="yes" ${commonfaultAuto.colseSwitch == 'yes' ? 'selected' : ''}>开</option>
			  	 			<option value="no" ${commonfaultAuto.colseSwitch == 'no' ? 'selected' : ''}>关</option>
			  	 		</select> 
			  		</td>					  
		   	</tr>
		<logic:notEqual name="type" value="open">
			<tr>
				<td colspan="2">
					<input type="hidden" name="id" value="${commonfaultAuto.id}">
					<input type="hidden" name="ruleType" value="${ruleType}">
					<input type="submit" value="提交" class="btn" >
				</td>
			</tr>
		</logic:notEqual>
		<logic:equal name="type" value="open">
			<tr>
				<td colspan="2">
					<input type="button" value="编辑" class="btn" onclick="toedit();">
					<input type="button" value="删除" class="btn" onclick="todelete();">
				</td>
			</tr>
		</logic:equal>
        
</table>
<%@ include file="gscommonqueryJs.jsp"%>
</form>
 <script type="text/javascript">

 
 	function tosubmit(e) {
	 		
	 		var commonFaultDesc = document.getElementById("commonFaultDesc");
	 		var remark1 = document.getElementById("remark1");
	 		var remark2 = document.getElementById("remark2");
	 		
			if (commonFaultDesc.value == "") {
	 			alert("请填写措施");
	 			return false;
	 		}
	 		if (remark1.value == "") {
	 			alert("请填写网络告警ID");
	 			return false;
	 		}
	 		if (remark2.value == "") {
	 			alert("请填写归档描述");
	 			return false;
	 		}
			
			
	}
	
	eoms.$("theform").onsubmit = tosubmit;

 	function toedit(){
		location.href = "./commonfaultauto.do?method=edit&ruleType=${ruleType}&type=edit&id=${commonfaultAuto.id}";
	}
	
	function todelete(){
		location.href = "./commonfaultauto.do?method=remove&id=${commonfaultAuto.id}";
	}
 </script>