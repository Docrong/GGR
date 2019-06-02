<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String jsonString = request.getAttribute("jsonString").toString();
%>
<title></title> 

<script language="javascript">	
	function displaySend() {
		var iscyclesend = document.getElementsByName("isCycleSend");
		var cycle = document.getElementsByName("cycleStatus");
		for(var i=0;i<iscyclesend.length;i++) {
			if(iscyclesend[i].checked) {
				document.getElementById("issend").style.display="";	
			} else {
				document.getElementById("issend").style.display="none";
			}
		}
	}
	function modifyTitle() {
		if(document.forms[0].selStatus.checked) {
			document.getElementById("customService").style.display="";
			document.getElementById("chooseService").style.display="none";
		} else {
			document.getElementById("chooseService").style.display="";
			document.getElementById("customService").style.display="none";
		}		
	}
	function setValue(el) {
		if(el.checked) {
			el.value="1";
		} else {
			el.value="0";
		}
	}
	function controlSoap() {
		if(document.forms[0].regetData.checked) {
			document.getElementById("soap").style.display="";
			document.getElementById("soapname").style.display="";
		} else {
			document.getElementById("soap").style.display="none";
			document.getElementById("soapname").style.display="none";
		}
	} 
	function control() {
	   for(i=0;i<document.getElementsByName("cycleStatus").length;i++)   
	   if(document.getElementsByName("cycleStatus")[i].checked) {
	    if(document.getElementsByName("cycleStatus")[i].value=="1"){
	     document.getElementById("cycleMonth").disabled=false;   
	     document.getElementById("cycleDay").disabled=false;
	     document.getElementById("cycleHour").disabled=false; 
	   } else if(document.getElementsByName("cycleStatus")[i].value=="2"){
	     document.getElementById("cycleMonth").disabled=true;   
	     document.getElementById("cycleDay").disabled=false;
	     document.getElementById("cycleHour").disabled=false; 
	   } else if(document.getElementsByName("cycleStatus")[i].value=="3"){
	     document.getElementById("cycleMonth").disabled=true;   
	     document.getElementById("cycleDay").disabled=true;
	     document.getElementById("cycleHour").disabled=false; 
	   } 
	  }   
 	}
 
	function setSendValue() {
		var frm = document.forms[0];
		for(var i=0;i<document.getElementsByName("sendStatus").length;i++){
			if(document.getElementsByName("sendStatus")[i].checked) {
				if(document.getElementsByName("sendStatus")[i].value=="1"){
					frm.sendDay.readOnly=true;
					frm.sendHour.readOnly=true;
					frm.sendMin.readOnly=true;
					frm.sendDay.value = "0";
					frm.sendHour.value = "0";
					frm.sendMin.value = "0";
				} else {
					frm.sendDay.readOnly=false;
					frm.sendHour.readOnly=false;
					frm.sendMin.readOnly=false;
					frm.sendDay.value = "0";
					frm.sendHour.value = "1";
					frm.sendMin.value = "30";
				} 
			}
		}   
	}
	function displaySendValue() {
		var frm = document.forms[0];
		for(var i=0;i<document.getElementsByName("sendStatus").length;i++){
			if(document.getElementsByName("sendStatus")[i].checked) {
				if(document.getElementsByName("sendStatus")[i].value=="1"){
					frm.sendDay.readOnly=true;
					frm.sendHour.readOnly=true;
					frm.sendMin.readOnly=true;
				} else {
					frm.sendDay.readOnly=false;
					frm.sendHour.readOnly=false;
					frm.sendMin.readOnly=false;
				} 
			}
		}   
	}
</script>
<script language="javascript">

	Ext.onReady(function(){
	control();displaySend();displaySendValue();modifyTitle();controlSoap();
	v = new eoms.form.Validation({form:'smsServiceForm'});
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div><bean:message key="smsApply.chooseservice"/></div>'								
		}
	);
	var s = '<%=jsonString%>';
	userViewer.jsonData = eoms.JSONDecode(s);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'<bean:message key="smsService.servicename"/>',treeChkMode:'',treeChkType:'user,dept',
		viewer:userViewer,saveChkFldId:'usersId',returnJSON:true  
	});
})
</script>

<html:form action="/smsServices.do?method=xsave" method="post" styleId="smsServiceForm"> 
<%
	String parentid = request.getParameter("parentid"); 
	String status = request.getParameter("status");
%>

    <table class="formTable">    	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.name'/>
    		</td>
    		<td class="content">
        		<html:text property="name" styleClass="text" alt="allowBlank:false,vtext:'${eoms:a2u('请输入服务名称')}'"></html:text>
			</td>
		</tr>
    	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.userid'/>
    		</td>
    		<td class="content">
        		<html:text property="userId" styleClass="text" readonly="true"></html:text>
			</td>
		</tr>
    	<tr>
    		<td class="label" id="chooseService" style="">
    			<bean:message key="smsApply.chooseservice"/>
    		</td>
    		<td class="label" id="customService" style="display:none">
    			<bean:message key="smsApply.customservice"/>
    		</td>
    		<td class="label"><div id="user-list" class="viewer-list"></div>
    		<input type="button" value="<bean:message key='smsService.modifyuserlist'/>" id="userTreeBtn" class="btn"/>
    		<html:checkbox styleId="selStatus" property="selStatus" onclick="modifyTitle()">&nbsp;<bean:message key='smsService.selstatus'/></html:checkbox>
			</td>
		</tr>
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.msgtype'/>
    		</td>
    		<td class="content">
    			<eoms:dict key="dict-msg" dictId="msgType" isQuery="false" defaultId="${smsServiceForm.msgType}" selectId="msgType" beanId="selectXML"/>
    			
    		</td>
    	</tr>
    	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.count'/>
    		</td>
    		<td class="content">
    			<html:text property="count" styleClass="text" alt="vtype:'number',vtext:'${eoms:a2u('必须输入数字')}',allowBlank:false"></html:text>
    		</td>
    	</tr>
    	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.interval'/>
    		</td>
    		<td class="content">
    			<html:text property="interval" styleClass="text" alt="vtype:'number',vtext:'${eoms:a2u('必须输入数字')}',allowBlank:false"></html:text>
    			<bean:message key='smsService.min'/>
    		</td>
    	</tr>
    	<tr>
    		<td class="label">
    			<html:checkbox styleId="isSendNight" property="isSendNight" onclick="setValue(this)"><bean:message key='smsService.issendnight'/></html:checkbox>
    		</td>
    		<td class="content">
    			<html:checkbox styleId="isSendImediat" property="isSendImediat" onclick="setValue(this)"><bean:message key='smsService.issendimediat'/></html:checkbox>
    		</td>
    	</tr>
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.isSendUnDuty'/>
    		</td>
    		    <c:if test='${smsServiceForm.isSendUnDuty==null||empty smsServiceForm.isSendUnDuty}'>
    		         　<c:set value="0" target="${smsServiceForm}" property="isSendUnDuty"></c:set>
    		    </c:if>
    		<td class="content">

    		    <html:radio property="isSendUnDuty" styleId="isSendUnDuty" value="1">${eoms:a2u('是')}</html:radio>&nbsp;&nbsp;
    		    <html:radio property="isSendUnDuty" styleId="isSendUnDuty" value="0">${eoms:a2u('否')}</html:radio>
    		</td>
    	</tr>
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.starttime'/>
    		</td>
    		<td class="content">
    			<input type="text" name="startTime" class="text" value="<bean:write format='yyyy-MM-dd hh:mm:ss' name='smsServiceForm' property='startTime'/>" onclick="popUpCalendar(this, this);" readonly="true"/>
    		</td>
    	</tr>
    	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.endtime'/>
    		</td>
    		<td class="content">
    			<input type="text" name="endTime" class="text" value="<bean:write format='yyyy-MM-dd hh:mm:ss' name='smsServiceForm' property='endTime'/>" onclick="popUpCalendar(this, this);" readonly="true"/>
    			
    		</td>
    	</tr>
    	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.send'/>
    		</td>
    		<td class="content">    			
    			<table class="formtable">
    				<tr>
    					<td>
    						<html:text property="sendDay" size="3" alt="vtype:'number',vtext:'${eoms:a2u('必须输入数字')}',allowBlank:false"></html:text>
    					</td>
    					<td>
    						<bean:message key='smsService.sendday'/>
    					</td>
    					<td>
    						<html:text property="sendHour" size="3" alt="vtype:'number',vtext:'${eoms:a2u('必须输入数字')}',allowBlank:false"></html:text>
    					</td>
    					<td>
    						<bean:message key='smsService.sendhour'/>
    					</td>
    					<td>
    						<html:text property="sendMin" size="3" alt="vtype:'number',vtext:'${eoms:a2u('必须输入数字')}',allowBlank:false"></html:text>
    					</td>
    					<td>
    						<bean:message key='smsService.sendmin'/>
    					</td>
    					<td class="label">    						
    						<html:radio property="sendStatus" value="1" onclick="setSendValue(this)"><bean:message key='smsService.sendimediat'/></html:radio>
    					</td>
    					<td class="label">    						
    						<html:radio property="sendStatus" value="2" onclick="setSendValue(this)"><bean:message key='smsService.sendforward'/></html:radio>
    					</td>
    					<td class="label">
    						<html:radio property="sendStatus" value="3" onclick="setSendValue(this)"><bean:message key='smsService.senddelay'/></html:radio>
    					</td>
    				</tr>
    			</table>
    		</td>    		
    	</tr>
    </table>
    <table class="formTable">
    	<tr>
    		<td colspan="3">
    			<html:checkbox property="isCycleSend" onclick="displaySend()"><bean:message key='smsService.sendcycle'/></html:checkbox>
    		</td>
    	</tr>
    	<tr id="issend" style="display:none">
    		<td class="label" colspan="3">    			
    			<table>
    				<tr>
    					<td class="label">
    						<html:radio property="cycleStatus" value="1" onclick="control(this)">&nbsp;<bean:message key='smsService.yearcycle'/></html:radio>
    					</td>
    					<td class="label">    						
    						<html:radio property="cycleStatus" value="2" onclick="control(this)">&nbsp;<bean:message key='smsService.monthcycle'/></html:radio>
			    		</td>
			    		<td class="label">
			    			<html:radio property="cycleStatus" value="3" onclick="control(this)">&nbsp;<bean:message key='smsService.daycycle'/></html:radio>
			    		</td>		
    				</tr>
    				<tr>
			    		<td class="label">
			    			<eoms:dict key="dict-msg" dictId="month" isQuery="false" defaultId="${smsServiceForm.cycleMonth}" selectId="cycleMonth" beanId="selectXML"/>
			    			&nbsp;<bean:message key='smsService.month'/>
			    		</td>
			    		<td class="label">
			    			<eoms:dict key="dict-msg" dictId="day" isQuery="false" defaultId="${smsServiceForm.cycleDay}" selectId="cycleDay" beanId="selectXML"/>
			    			&nbsp;<bean:message key='smsService.day'/>
			    		</td>
			    		<td class="label">
			    			<eoms:dict key="dict-msg" dictId="hour" isQuery="false" defaultId="${smsServiceForm.cycleHour}" selectId="cycleHour" beanId="selectXML"/>
			    			&nbsp;<bean:message key='smsService.hour'/>
			    		</td>
			    	</tr>
			    	<tr colspan="3">    		
			    		<td>
			    			<html:checkbox styleId="regetData" property="regetData" value="0" onclick="controlSoap()">&nbsp;<bean:message key='smsService.regetdata'/></html:checkbox>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td colspan="3">
			    			<table>
			    				<tr>
			    					<td class="label">
			    						<bean:message key="smsService.regetaddr"/>
			    					</td>
			    					<td class="label">
			    						<html:text property="regetAddr" size="56"></html:text>
			    					</td>
			    					<td class="label" id="soapname" style="display:none">
			    						<bean:message key='smsService.soaptype'/>
			    					</td>
			    					<td class="label" id="soap" style="display:none">
			    						<eoms:dict key="dict-msg" dictId="soapType" isQuery="false" defaultId="${smsServiceForm.regetProtocol}" selectId="regetProtocol" beanId="selectXML"/>
			    					</td>
			    				</tr>
			    			</table>
			    		</td>
			    	</tr>
    			</table>
    		</td>
    	</tr>
    </table>
    <table>  	
    	<tr>
    		<td class="label">
    			<bean:message key='smsService.remark'/>
    		</td>
    		<td>
    			<html:textarea property="remark"></html:textarea>
    		</td>
    	</tr>
    </table>
        
        

    
    <input type="hidden" id="usersId" name="usersId"/>  	
    <html:hidden property="id"/>  
    <html:hidden property="parentId" value="<%=parentid%>"/>
    <html:hidden property="status" value="<%=status%>"/>
    <html:hidden property="leaf"/>
    <html:hidden property="deleted"/>
    
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <bean:message key="button.save"/>
        </html:submit>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>