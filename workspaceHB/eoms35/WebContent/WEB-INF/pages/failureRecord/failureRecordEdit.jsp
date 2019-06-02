<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style>
#tabs {
	width: 80%;
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
					<html:text property="title" styleId="title"/>
						


					</td>
				<tr class="tr_show">
					<td class="label">
						<bean:message key='Failure.Faultstarttime' />
					</td>
					<td>
                      <html:text property="faultstarttime" styleId="faultstarttime" readonly="true" onclick="popUpCalendar(this, this);"/>
						

					</td>
					<td class="label">
						<bean:message key='Failure.Faultendtime' />
					</td>

					<td>
						 <html:text property="faultendtime" styleId="faultendtime" readonly="true" onclick="popUpCalendar(this, this);"/>
					</td>
				</tr>

				</tr>
				<tr class="tr_show">
					<td class="label">
						<bean:message key='Failure.faulttype1' />
					</td>
					<td>
						<eoms:comboBox name="${failureRecordForm.faulttype1}holdStatisfied" id="${failureRecordForm.faulttype1}holdStatisfied" initDicId="120"/>
				
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
						<eoms:dict key="dict-failureRecord" dictId="sheettemplatename"
							isQuery="false" selectId="sheettemplatename" beanId="selectXML" />
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


						<textarea rows="1" cols="50" wrap="hard" name="faultdetail">${failureRecordForm.faultdetail}</textarea>

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
						<eoms:dict key="dict-failureRecord" dictId="faultstatus"
							isQuery="false" selectId="faultstatus" beanId="selectXML" />


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
					<td width="100%" height="32" align="left">
						<html:submit property="strutsButton" styleClass="clsbtn2">
							<bean:message key='Failure.BtnSave' />
						</html:submit>
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</html:form>
	</div>
</div>


<%@ include file="/common/footer_eoms.jsp"%>