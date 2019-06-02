<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'theform',vbtn:''});
})	
</script>
<div class="list-title">
	${eoms:a2u('评估报表手动补采')}
</div>

<div class="list">
	<html:form action="/tawSuppkpiReportStorageRepair.do?method=reportStorageRepair" method="post" styleId="theform">
		<table style="width:30%;">
			<tr align="center" height="40">
				<td>
				<input type="submit" class="btn" property="method.reportStorageRepair" styleId="method.reportStorageRepair" value="${eoms:a2u('评估报表手动补采')}" />
				</td>				
			</tr>
		</table>
	</html:form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
