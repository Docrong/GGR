<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ page language="java" import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.duty.controller.TawRmRecordForm,com.boco.eoms.duty.model.TawRmDutyfile,com.boco.eoms.duty.model.*,com.boco.eoms.duty.model.TawRmAddonsTable,com.boco.eoms.duty.util.StaticDutycheck"%>
<%
	String roomName = request.getAttribute("roomName").toString();
	String workserial = (String) request.getAttribute("workserial");
	String exchangeFlag = request.getAttribute("EXCHANGEFLAG").toString();
%>
<fmt:bundle basename="com/boco/eoms/duty/config/ApplicationResources-duty">
<script type="text/javascript" src="<%=request.getContextPath()%>/duty/js/faultCommontJs.js"></script>
<script>	
	function CheckDutyRecord(oSrc, args){
		args.IsValid = (window.Form1.Dutyrecord.value.length <= 2000);
	}
	function CheckNotes(oSrc, args){
		args.IsValid = (window.Form1.Notes.value.length <= 120);
	}
	function save_merge(){
	        tawRmRecordForm.action = "savemerge.do";
	        tawRmRecordForm.submit();
	}
	
	function __doPostBack(eventTarget, eventArgument) {
			var theform = document.Form1;
			theform.__EVENTTARGET.value = eventTarget;
			theform.__EVENTARGUMENT.value = eventArgument;
			theform.submit();
	}
	
	function ValidatorOnSubmit() {
	    if (Page_ValidationActive) {
	        ValidatorCommonOnSubmit();
	    }
	}
	
	function showSubList3(thingId, curObj) {
		if (document.getElementById("subListViewThing" + thingId).innerHTML == "") {
				document.getElementById("subListIframe").src ="${app}/duty/TawRmThing.do?method=listThingNote&thingId=" + thingId;
				curObj.firstChild.src = "${app}/duty/images/nofollow.gif";
		} else {
				document.getElementById("subListViewThing" + thingId).innerHTML = "";
				curObj.firstChild.src = "${app}/duty/images/plus.gif";
		}
 	} 
</SCRIPT>

<html:form method="post" action="/TawRmRecord/ynexchange" >
			<center>
				<br>
				<TABLE cellSpacing="1" cellPadding="1" width="600" align="center"
					border="0" class="formTable">
					<TBODY>
						<html:hidden property="id" />
						<html:hidden property="roomId" />
						<TR class="tr_show">
							<TD noWrap rowSpan="4" align="center" class="label">&nbsp;<bean:message key="TawRmRecord.baseinfo" /></TD>
							<TD noWrap class="label"><bean:message key="TawRmSysteminfo.roomName" /></TD>
							<TD noWrap colSpan="1">
								<INPUT id="RoomName" style="BACKGROUND-COLOR: lightgrey"
									readOnly maxLength=150 value="<%=roomName%>" name="RoomName">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.receivertime" />
							</TD>
							<TD noWrap >
								<input name="starttime" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="18"
									value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
							</TD>
							<html:hidden property="flag" />
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.hander" />
							</TD>
							<TD noWrap colSpan="3">
								<html:hidden property="hander" />
								<input name="hander" id="hander" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.dutyman" />
							</TD>
							<TD noWrap colSpan="3">
								<html:hidden property="dutyman" />
								<input name="dutyman" id="dutyman" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
						</TR>
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.receiver" />
							</TD>
							<TD noWrap colSpan="3">
								<html:hidden property="receiver" />
								<input name="receiver" id="receiver" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.clean" />
							</TD>
							<TD noWrap colSpan="1">
								<html:hidden property="clean" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="10"
									value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>" />
								**
							</TD>
							<html:hidden property="clean1" />
						 
							<TD class="label">
								<bean:message key="TawRmRecord.conditioner" />
							</TD>
							<TD colSpan="2">
								<html:hidden property="conditioner" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="10"
									value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>" />
								**
							</TD>
						</TR>

						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.accessories" />
							</TD>
							<%
									Vector vecDutyFile = (Vector) request
									.getAttribute("vecDutyFile");
							%>
							<TD noWrap colSpan=6>
								&nbsp;
								<%
											for (int i = 0; i < vecDutyFile.size(); i++) {
											TawRmDutyfile tawRmDutyfile = (TawRmDutyfile) vecDutyFile
											.elementAt(i);
											if (StaticMethod.getUploadType().equals("UpE")) {
										out.print("<a href='../upload/"
												+ java.net.URLEncoder.encode(tawRmDutyfile
												.getEncodename()) + "'>"
												+ tawRmDutyfile.getFilename() + "</a>");
											} else {
										out.print("<a href='../upload/"
												+ tawRmDutyfile.getEncodename() + "'>"
												+ tawRmDutyfile.getFilename() + "</a>");
											}
											out.print("&nbsp;");
										}
								%>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD rowSpan="4" align="left" class="label">${eoms:a2u('监控纪要')}</TD>
							<TD align="left" class="label">${eoms:a2u('网络重要故障')}</TD>
							<TD colSpan="3">
								<TEXTAREA name=netfault rows=6 style="width:95%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="netfault" /></TEXTAREA>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD align="left" class="label">${eoms:a2u('重要社会或灾害事件')}</TD>
							<TD colSpan="3">
								<TEXTAREA name=importantaffair rows=6 style="width:95%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="importantaffair" /></TEXTAREA>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD align="left" class="label">${eoms:a2u('需要省公司协调事项')}</TD>
							<TD colSpan="3">
								<TEXTAREA name=harmony rows=6 style="width:95%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="harmony" /></TEXTAREA>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD align="left" class="label">${eoms:a2u('交班事项')}</TD>
							<TD colSpan="3">
								<TEXTAREA name=otheraffair rows=6 style="height:150;width:95%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="otheraffair" /></TEXTAREA>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.personrecord" />
							</TD>
							<TD colSpan=6>
								&nbsp;
								<SPAN id=spanSubReocrd> <logic:iterate
										id="tawRmRecordSub" name="TAWRMRECORD_DUTYMAN"
										type="com.boco.eoms.duty.model.TawRmRecordSub">
										<html:link href="../TawRmRecordSub/view.do" target="blank"
											paramId="id" paramName="tawRmRecordSub" paramProperty="id">
											<bean:write name="tawRmRecordSub" property="dutyman"
												scope="page" />
										</html:link>
									</logic:iterate>&nbsp; </SPAN>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.workrecord" />
								<BR>
								<BR>
								<INPUT id=btnGetSub
									onclick="window.location.href='oldcoalition.do'" type=button
									class="button"
									value="<bean:message key="TawRmRecord.mergerecord"/>"
									name=btnGetSub>
							</TD>
							<%
									String strDutyrecord = "";
									String strTemprecord = "";
									if (request.getAttribute("COALITIONRECORD") != null) {
										strDutyrecord = StaticMethod.nullObject2String(request
										.getAttribute("COALITIONRECORD"));
										strTemprecord = StaticMethod.nullObject2String(request
										.getAttribute("COALITIONTEMPRECORD"));
									} else {
										//strDutyrecord =  tawRmRecordForm.getDutyrecord();
									}
							%>
							<TD noWrap align=left colSpan=6>
								<TEXTAREA id="dutyrecord" name="dutyrecord" rows=6 cols=80 type="_moz"><%=strDutyrecord%></TEXTAREA>
								<SPAN id=Customvalidator1 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckDutyRecord"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>"
									controltovalidate="Dutyrecord"><BR>
								</SPAN>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.notes" />
							</TD>
							<TD colSpan=6>
								<TEXTAREA id=notes name=notes rows=4 cols=80 type="_moz"><bean:write name="tawRmRecordForm" property="notes"  scope="request" /></TEXTAREA>
								<SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckNotes"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>" controltovalidate="Notes"><BR>
								</SPAN>
							</TD>
						</TR>
					</TBODY>
				</TABLE>

				<TABLE cellSpacing="0" cellPadding="0" width="600" align="center"
					border="0">
					<TR>
						<%
						if (exchangeFlag.equals("1")) {
						%>
						<TD align=right height="32" colSpan=7>
							<input type="button" name="button" class="clsbtn2"
								value=<bean:message key="label.save"/> Onclick="save_merge();">
						</TD>
						<%
						} else {
						%>
						<TD align=right height="32" colSpan=7>
							<html:submit styleClass="button">
								<bean:message key="label.nextp" />
							</html:submit>
						</TD>
						<%
						}
						%>
					</TR>
				</TABLE>
<SCRIPT language=javascript>
	var Page_Validators =  new Array(document.all["Customvalidator1"], document.all["Customvalidator2"]);
</SCRIPT>
</html:form>

<table cellpadding="0" class="table CURRENTEVENTTLIST" cellspacing="0"
	<tr>
		<td align="left" class="content">${eoms:a2u('本班次监控事件')}</td>
	</tr>
</table>
<display:table name="TawRmDutyEventList" cellspacing="0" cellpadding="0"
    id="TawRmDutyEventList" class="table TawRmDutyEventList"
    sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/yndutyevent.do?method=add"  >

    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.faultType" >
    	<span onclick="showSubList2('<bean:write name="TawRmDutyEventList" property="id"/>', this, 'TawRmDutyEventList');">
			<img src="${app}/duty/images/plus.gif">
		</span>
    	<eoms:dict key="dict-duty" dictId="faultType" itemId="${TawRmDutyEventList.faultType}" beanId="id2nameXML" />
    </display:column>
    
    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.flag" >
    	<eoms:dict key="dict-duty" dictId="faultflag" itemId="${TawRmDutyEventList.flag}" beanId="id2nameXML" />
    </display:column>
  
    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.deptid" >
  		<eoms:id2nameDB id="${TawRmDutyEventList.deptid}" beanId="tawSystemDeptDao" />
    </display:column>
    
    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.inputuser" >
    	<eoms:id2nameDB id="${TawRmDutyEventList.inputuser}" beanId="tawSystemUserDao" />
    </display:column>
    
    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.eventtitle" >
     	<bean:write name="TawRmDutyEventList" property="eventtitle"/>
    </display:column>  
    
    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.sheetid" >
    	<bean:write name="TawRmDutyEventList" property="sheetid"/>
    </display:column>        
						
    <display:column property="beginTime" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.beginTime"/>
    
    <display:column sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.complateFlag" >
  		<eoms:dict key="dict-duty" dictId="complateFlag" itemId="${TawRmDutyEventList.complateFlag}" beanId="id2nameXML" />
    </display:column>
    
    <display:column property="inputdate" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.inputdate"/>
    
    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
<div style="display: none">
 	<iframe name="subListIframe" id="subListIframe" src="" width="400" height="300"></iframe>
</div>
<table cellpadding="0" class="table CURRENTEVENTTLIST" cellspacing="0"
			<caption>
				<td align="left" class="content" colspan="5">${eoms:a2u('物件列表')}</td>
			</caption>
			<TR class="label">
				<TD align="center" class="label">
					<fmt:message key="tawRmThingForm.thingName"/>
				</TD>
				<TD align="center" class="label">
					<fmt:message key="tawRmThingForm.isForUse"/>
				</TD>
				<TD align="center" class="label">
					<fmt:message key="tawRmThingForm.estate"/>
				</TD>
				<TD align="center" class="label">
					<fmt:message key="tawRmThingForm.thingComment"/>
				</TD>
			</TR>
			<logic:iterate id="Thinglist" name="Thinglist">
				 <tr class="tr_show">
	    			<td>
	    				<span onclick="showSubList3('<bean:write name="Thinglist" property="id"/>',this);">
							<img src="${app}/duty/images/plus.gif">
							<bean:write name="Thinglist" property="thingName" scope="page"/>
						</span>
	    			</td>
     				<td>
     					<logic:equal value="1" name="Thinglist" property="isForUse" scope="page">
     						<fmt:message key="tawRmThingForm.isForUse_yes"/>
     					</logic:equal>
     					<logic:equal value="0" name="Thinglist" property="isForUse" scope="page">
     						<fmt:message key="tawRmThingForm.isForUse_no"/>
     					</logic:equal>			
     				</td>
     				<td>
     					<logic:equal value="0" name="Thinglist" property="estate" scope="page">
     						<fmt:message key="tawRmThingForm.noEstate"/>
     					</logic:equal>
     					<logic:equal value="1" name="Thinglist" property="estate" scope="page">
     						<fmt:message key="tawRmThingForm.isEstate"/>
     					</logic:equal>	
     				</td>
     				<td>
     					<bean:write name="Thinglist" property="thingComment" scope="page"/>
     				</td>
     			</tr>
     			<tr>
					<td colspan="5" id="subListViewThing<bean:write name="Thinglist" property="id" />">
					</td>
				</tr>
			</logic:iterate>
		</table>
		<div style="display: none">
			<iframe name="subListIframe" id="subListIframe" src="" width="400" height="300"></iframe>
		</div>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>
