<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style>
#tabs {
	width: 100%;
}

#tabs .x-tabs-item-body {
	display: none;
	padding: 10px;
}
</style>
<script type="text/javascript">
Ext.onReady(function(){
	var tabs = new Ext.TabPanel('tabs');
    var formTab = tabs.addTab('form', "<bean:message key='Failure.RecordAdd'/>");
    var infoTab = tabs.addTab('info', "<bean:message key='Failure.Help'/>");
    formTab.on('activate',function(){
    	
    });
    infoTab.on('activate',function(){
    	
    });
    tabs.activate('form');	
});
</script>




<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">
	<div id="form" class="tab-content">

		<table border="0" width="100%" cellspacing="1" class="formTable">

			<tr class="tr_show">
				<td class="label">
					<bean:message key='Failure.title' />
				</td>
				<td colspan="3">
					<bean:write name="FailureRecord" property="title" />


				</td>
			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.Faultstarttime' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faultstarttime" />


				</td>
				<td class="label">
					<bean:message key='Failure.Faultendtime' />
				</td>

				<td>
					<bean:write name="FailureRecord" property="faultendtime" />

				</td>
			</tr>

			</tr>
			<tr class="tr_show">
				<td class="label">
					<bean:message key='Failure.faulttype1' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faulttype1Name" />

				</td>
				<td class="label">
					<bean:message key='Failure.faulttype2' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faulttype2Name" />

				</td>


			</tr>


			<tr class="tr_show">

				<td class="label">
					<bean:message key='Failure.faulttype3' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faulttype3Name" />
				</td>
				<td class="label">

					<bean:message key='Failure.faulttype4' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faulttype4Name" />
				</td>

			</tr>
			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.faultregion' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faultregionName" />
				</td>
				<td class="label">

					<bean:message key='Failure.faultjudge' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faultjudgeName" />
				</td>
			</tr>
			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.sheettemplatename' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="sheettemplatename" />

				</td>
				<td class="label">

					<bean:message key='Failure.faulttype5' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faulttype5" />


				</td>


			</tr>
			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.faultdetail' />
				</td>
				<td colspan="3">
					<textarea rows="12" cols="50" readonly="true" wrap="hard">${FailureRecord.faultdetail}</textarea>

				</td>

			</tr>
			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.todeptid' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="todeptid" />



				</td>

				<td class="label">

					<bean:message key='Failure.todutyroom' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="todutyroom" />
				</td>

			</tr>

			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.todutyroomid' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="odutyroomid" />


				</td>
				<td class="label">

					<bean:message key='Failure.todutyroomid' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="odutyroomid" />

				</td>

			</tr>

			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.todutyroomen' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="todutyroomen" />
				</td>
				<td class="label">

					<bean:message key='Failure.faultstatus' />
				</td>
				<td>
					<bean:write name="FailureRecord" property="faultstatusName" />
				</td>

			</tr>

			<tr class="tr_show">

				<td class="label">

					<bean:message key='Failure.faultsource' />
				</td>
				<td colspan="3">

                    <bean:write name="FailureRecord" property="faultsource" />
					

				</td>

			</tr>


		</table>


	</div>
</div>


<%@ include file="/common/footer_eoms.jsp"%>