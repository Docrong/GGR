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
function selectTree(){
  dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:450px;dialogWidth:480px;";
  dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox_zyjh/listbox_depttoinput.jsp?selectself=yes', window, dWinOrnaments);
}

Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",	
			'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
			{ 
				multiSelect: true,		
				emptyText : '<div></div>'								
			}
		);
		userViewer.refresh();
		
		userTree = new xbox({
			btnId:'userTreeBtn',dlgId:'dlg-user',	
			treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u('部门')}',treeChkMode:'',treeChkType:'dept',
			viewer:userViewer,saveChkFldId:'deptid' 
		});
	})
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



<%
String usereId =(String)request.getAttribute("usereId");
		String roomname=(String)request.getAttribute("roomname");
		String workSerial=(String)request.getAttribute("workSerial");
%>
<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">
	<div id="form" class="tab-content">
		<html:form action="/failureRecord.do?method=saveFailureRecord">
			<table border="0" width="100%" cellspacing="1" class="formTable">
				<tr class="tr_show">
					<td class="label">
						<bean:message key='Failure.title' />
					</td>
					<td colspan="3">
						<input type="text" name="title" size="80"
							alt="allowBlank:false,vtext:'dsa'" />


					</td>
				<tr class="tr_show">
					<td class="label">
						<bean:message key='Failure.Faultstarttime' />
					</td>
					<td>

						<input type="text" name="faultstarttime" size="30" readonly="true"
							class="text" onclick="popUpCalendar(this, this);">

					</td>
					<td class="label">
						<bean:message key='Failure.Faultendtime' />
					</td>

					<td>
						<input type="text" name="faultendtime" size="30" readonly="true"
							class="text" onclick="popUpCalendar(this, this);">
					</td>
				</tr>

							<tr class="tr_show">
					<td class="label">
						<bean:message key='Failure.faulttype1' />
					</td>
					<td>
					<eoms:comboBox name="faulttype1" id="a1" sub="a2" initDicId="120"/>
											</td>
					<td class="label">
						<bean:message key='Failure.faulttype2' />
					</td>
					<td>
					<eoms:comboBox name="faulttype2" id="a2" sub="a3" styleClass="border"/><br/>
						</td>


				</tr>


				<tr class="tr_show">

					<td class="label">
						<bean:message key='Failure.faulttype3' />
					</td>
					<td>
					<eoms:comboBox name="faulttype3" id="a3" sub="a4" styleClass="border"/><br/>
						
					</td>
					<td class="label">

						<bean:message key='Failure.faulttype4' />
					</td>
					<td>
						<eoms:comboBox name="faulttype4" id="a4"  styleClass="border"/><br/>
						
					</td>

				</tr>
				<tr class="tr_show">

					<td class="label">

						<bean:message key='Failure.faultregion' />
					</td>
					<td>
					<eoms:comboBox name="faultregion" id="a5"  initDicId="122"/>
					
					</td>
					<td class="label">

						<bean:message key='Failure.faultjudge' />
					</td>
					<td>
                         <eoms:comboBox name="faultjudge" id="a5"  initDicId="10302"/>
						

					</td>

				</tr>
				<tr class="tr_show">

					<td class="label">

						<bean:message key='Failure.sheettemplatename' />
					</td>
					<td>
					<eoms:comboBox name="faultregion" id="a6"  initDicId="123"/>
						
					</td>
					<td class="label">

						<bean:message key='Failure.faulttype5' />
					</td>
					<td>
						<input type="text" name="faulttype5"
							alt="allowBlank:false,vtext:'dsa'" />


					</td>


				</tr>
				<tr class="tr_show">

					<td class="label">

						<bean:message key='Failure.faultdetail' />
					</td>
					<td colspan="3">


						<textarea rows="1" cols="50" wrap="hard" name="faultdetail"></textarea>

					</td>

				</tr>
				<tr class="tr_show">

					<td class="label">

						<bean:message key='Failure.todeptid' />
					</td>
					<td>
						<div id="user-list" class="viewer-list">
						</div>
						<input type="button" value="${eoms:a2u('选择部门')}" id="userTreeBtn"
							class="btn" />
						<INPUT TYPE="hidden" name="todeptid" id="todeptid" value="">

					</td>

					<td class="label">

						<bean:message key='Failure.todutyroom' />
					</td>
					<td>
						<input type="text" name="todutyroom"
							alt="allowBlank:false,vtext:'dsa'" value="<%=roomname%>"
							readonly="true" />


					</td>

				</tr>

				<tr class="tr_show">

					<td class="label">

						<bean:message key='Failure.todutyroomid' />
					</td>
					<td>

						<input type="text" name="odutyroomid"
							alt="allowBlank:false,vtext:'dsa'" value="<%=workSerial%>"
							readonly="true" />

					</td>
					<td class="label">

						<bean:message key='Failure.todutyroomen' />
					</td>
					<td>

						<input type="text" name="todutyroomen"
							alt="allowBlank:false,vtext:'dsa'" value="<%=usereId%>"
							readonly="true" />


					</td>

				</tr>

				<tr class="tr_show">


					<td class="label">

						<bean:message key='Failure.faultstatus' />
					</td>
					<td colspan="3">
						<eoms:comboBox name="faultstatus" id="a7"  initDicId="124"/>

					</td>

				</tr>

				<tr class="tr_show">

					<td class="label">

						<bean:message key='Failure.faultsource' />
					</td>
					<td colspan="3">


						<textarea rows="1" cols="50" wrap="hard" name="faultsource"></textarea>

					</td>

				</tr>


			</table>
			<table border="0" width="100%" cellspacing="0">
				<tr>
					<td width="100%" height="32" align="right">
						<html:submit property="strutsButton" styleClass="clsbtn2">
							<bean:message key='Failure.BtnSave' />
						</html:submit>
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</html:form>
	</div>
	<div id="info">
  	<dl>
  		<dt>${eoms:a2u('功能说明')}</dt>
        <dd>${eoms:a2u('当值班人员发现产生故障时，新增故障记录，如果需要其他部门处理则进行派发。监控人员在派发故障工单时，系统提供内部接口，自动把相关记录信息发送到故障记录表中，如果该工单在流程中被撤销，则相应的故障记录也要删除。')}</dd>
    </dl>
  </div>
</div>


<%@ include file="/common/footer_eoms.jsp"%>