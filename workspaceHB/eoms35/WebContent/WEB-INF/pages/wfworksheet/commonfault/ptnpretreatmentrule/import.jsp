<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
	
	function checkRule(){
		var form = document.getElementById("theform");
    	var ajaxForm = Ext.getDom(form);
		var attachment = document.getElementById('attachment').value;
		if(attachment == ''){
			alert('请先上传附件');
			return;
		}
		Ext.Ajax.request({
			method:'post',
			form: ajaxForm,
			url:'ptnpretreatmentrule.do?method=performPreCommit&type=import',
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
	
	function downloadTemplate(){
		var url = 'ptnpretreatmentrule.do?method=downloadTemplate';
		window.location.href = url;
	}
</script>

<div style="width:100%">
	<html:form styleId="theform" method="post" action="ptnpretreatmentrule.do?method=importSave">
		<table class="formTable">
			<tr>
				<td class="label">模板下载</td>
				<td class="content" colspan="3">
					<a href="javascript:void(0)" onclick="downloadTemplate();">传输自动归档模板.xls</a>
				</td>
			</tr>
			<tr>
				<td class="label">导入</td>
				<td colspan="3">
					<eoms:attachment name="attachment" property="attachment"
              			scope="request" idField="attachment" appCode="commonfault" />	
				</td>
			</tr>
		</table>
		<br/>
		<input type="button" class="btn" value="新增" onclick="javascript:checkRule();"/>
	</html:form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>