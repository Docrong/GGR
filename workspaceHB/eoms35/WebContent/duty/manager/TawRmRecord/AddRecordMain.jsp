<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.* , com.boco.eoms.common.controller.*,com.boco.eoms.duty.util.StaticDutycheck,com.boco.eoms.common.util.*, com.boco.eoms.duty.util.DutyConstacts,com.boco.eoms.commons.system.session.form.TawSystemSessionForm,com.boco.eoms.duty.model.TawRmRecord,com.boco.eoms.duty.model.TawRmDutyEvent,com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%
String roomName = request.getAttribute("roomName").toString();
String NO = request.getAttribute("NO").toString();
	List cutoversheetlist = (List) request
			.getAttribute("cutoversheetlist");
	List faultreportsheetlist = (List) request
			.getAttribute("faultreportsheetlist");
	List faultsheetlist = (List) request.getAttribute("faultsheetlist");
List monthPlanVOList = (List) request.getAttribute("monthPlanVOList");
List eventList = (List)request.getAttribute("eventList");
Iterator it = eventList.iterator();
String needcorrespond = (String)request.getAttribute("needcorrespond");
String handoverproceeding = (String)request.getAttribute("handoverproceeding");

%>
<%
			String separator = System.getProperty("line.separator");
			String impNetFault = "";
			String importantSocietyDisaster = "";
			int j = 1;
			int m = 1;
			while(it.hasNext()) {
				
				TawRmDutyEvent dutyEvent = (TawRmDutyEvent)it.next();
				if(dutyEvent.getFaultType().equals(DutyConstacts.IMP_NET_FAULT)) {
					impNetFault = impNetFault.trim() + separator + j +":"+dutyEvent.getEventtitle().trim();
					j++;
				} else if (dutyEvent.getFaultType().equals(DutyConstacts.IMP_SOCIETY) || dutyEvent.getFaultType().equals(DutyConstacts.IMP_DISASTER)) {
					importantSocietyDisaster = importantSocietyDisaster.trim() + separator + m +":"+ dutyEvent.getEventtitle().trim();
					m++;
				}				
			}
		 %>
<html>
<head>

<SCRIPT LANGUAGE=javascript>
var trans1="1";
var swtch1="2";
var data1="3";
var system1="4";
var other1="5";
var dutySummary1="6";
var importRecord1="7";
var importFault1="8";
var netCut1="9";
var netKPI1="0";
<%
String trans =StaticMethod.null2String(request.getAttribute("trans").toString());

if(trans.equals("")){ trans="'"+NO+"'";}
else{ trans ="'"+ trans+"'";}
String swtch =StaticMethod.null2String(request.getAttribute("swtch").toString());
if(swtch.equals("")){ swtch="'"+NO+"'";}
else{ swtch = "'"+swtch+"'";}
String data = StaticMethod.null2String(request.getAttribute("data").toString());
if(data.equals("")){ data="'"+NO+"'";}
else{ data = "'"+data+"'";} //StaticMethod.getGBString(data)
String system=StaticMethod.null2String(request.getAttribute("system").toString());
if(system.equals("")){ system="'"+NO+"'";}
else{ system = "'"+system+"'";}
String other =StaticMethod.null2String(request.getAttribute("other").toString());
if(other.equals("")){ other="'"+NO+"'";}
else{ other = "'"+other+"'";}

String dutySummary =StaticMethod.null2String(request.getAttribute("dutySummary").toString());
if(dutySummary.equals("0")){ dutySummary="'"+NO+"'";}
else{ dutySummary ="'"+ dutySummary+"'";}
String importRecord =StaticMethod.null2String(request.getAttribute("importRecord").toString());
if(importRecord.equals("0")){ importRecord="'"+NO+"'";}
else{ importRecord = "'"+importRecord+"'";}
String importFault = StaticMethod.null2String(request.getAttribute("importFault").toString());
if(importFault.equals("0")){ importFault="'"+NO+"'";}
else{ importFault = "'"+importFault+"'";} 
String netCut=StaticMethod.null2String(request.getAttribute("netCut").toString());
if(netCut.equals("0")){ netCut="'"+NO+"'";}
else{ netCut = "'"+netCut+"'";}
String netKPI =StaticMethod.null2String(request.getAttribute("netKPI").toString());
if(netKPI.equals("0")){ netKPI="'"+NO+"'";}
else{ netKPI = "'"+netKPI+"'";}
%>
   
trans1=<%=trans%>;
swtch1=<%=swtch%>;
data1=<%=data%>;
system1=<%=system%>;
other1=<%=other%>;
dutySummary1=<%=dutySummary%>;
importRecord1=<%=importRecord%>;
importFault1=<%=importFault%>;
 
netKPI1=<%=netKPI%>;

function fun_refresh()
{
    document.all.tawRmRecordForm.action="recordmain.do";
    document.all.tawRmRecordForm.submit;
}

function checkform(){
  var str = "";
  var form = document.forms[0];
  for (var i = 0; i < form.elements.length; i++){
     var obj = form.elements[i];
     if ( obj.type == 'checkbox' ){
     if ( obj.checked ){
       str = str + ";" + obj.name;
      }
    }
  }
  form.dutycheck.value = str.substring(1);
}
function show(id){
 
  if( id=="1"){
 
  document.forms[0].statement.value =trans1;
  }else if(id=="2"){
 
  document.forms[0].statement.value=swtch1;
  }
  else if(id=="3"){
 
  document.forms[0].statement.value=data1;
  }
  else if(id=="4"){
 
  document.forms[0].statement.value=system1;
  }
  else if(id=="5") {
  
  document.forms[0].statement.value=other1;
  
  }
  else if( id=="6"){
 
  document.forms[0].statement.value =dutySummary1;
  }else if(id=="7"){
 
  document.forms[0].statement.value=importRecord1;
  }
  else if(id=="8"){
 
  document.forms[0].statement.value=importFault1;
  }
  else if(id=="9"){
 
  document.forms[0].statement.value=netCut1;
  }
  else if(id=="0") {
  
  document.forms[0].statement.value=netKPI1;
  
  }
}
//-->
</SCRIPT>
</head>
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar" onload="tawRmRecordForm.statement.focus();" >
<html:form method="post" action="/TawRmRecord/savemain">
<html:hidden property="strutsAction" value="1"/>
<html:hidden property="strutsAction" value="1"/>
<html:hidden property="id" />
<html:hidden property="roomId" />
<html:hidden property="flag" />
<html:hidden property="dutydate" />
<html:hidden property="dutyman" />
<html:hidden property="hander" />
<html:hidden property="receiver" />
<html:hidden property="starttimeDefined" />
<html:hidden property="endtimeDefined" />
<html:hidden property="starttime" />
<html:hidden property="endtime" />
<html:hidden property="dutyman" />
<html:hidden property="hander" />
<html:hidden property="receiver" />
<html:hidden property="dutycheck" />
<%
String dutycheck = request.getAttribute("dutycheck").toString();
String dutychecksub = request.getAttribute("dutychecksub").toString();
String typemode = request.getAttribute("typemode").toString();

//System.out.println("trans"+trans);
%>
<br>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="listTable" align=center>
<%
	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
if (saveSessionBeanForm.getIsDutyMaster())
{
%>
<TR class="tr_show">
	<TD width="16%" noWrap align="center" rowSpan="2" class="label"><bean:message key="TawRmRecord.circumstance"/></TD>
	<TD class="label"><bean:message key="TawRmRecord.weather"/></TD>
	
	<TD> 
	<eoms:dict key="dict-duty" dictId="weather" beanId="selectXML"  defaultId="${tawRmRecordForm.weather}" isQuery="false"  selectId="weather"/>
          <!--<html:text property="weather" size="10" styleClass="clstext" />**-->
	</TD>
	<TD class="label"><bean:message key="TawRmRecord.temperature"/></TD>
	<TD   >
         <html:select property="temperature" styleClass="clstext">
         <html:options collection="LISTTEMPERATURE" property="value"  labelProperty="label"/>
         </html:select>C
         </TD>
        <TD class="label"><bean:message key="TawRmRecord.wet"/></TD>
        <TD>
         <html:select property="wet" styleClass="clstext">
         <html:options collection="LISTWET" property="value"  labelProperty="label"/>
         </html:select>
         &nbsp;%
        </TD>
</TR>
<TR class="tr_show">
        
	<TD class="label"><bean:message key="TawRmRecord.clean"/></TD>
	<TD>
	<eoms:dict key="dict-duty" dictId="clean" beanId="selectXML"  defaultId="${tawRmRecordForm.clean}" isQuery="false"  selectId="clean"/>
	 

          <!--<html:text property="clean" size="10" styleClass="clstext"/>**-->
	</TD>
        <html:hidden property="clean1" />
        <TD class="label"><bean:message key="TawRmRecord.conditioner"/></TD>
        <TD colSpan="3">
        <eoms:dict key="dict-duty" dictId="conditioner" beanId="selectXML" defaultId="${tawRmRecordForm.conditioner}" isQuery="false"  selectId="conditioner"/>
       

          <!--<html:text property="conditioner" size="10" styleClass="clstext"/>**-->
          </TD>
</TR>
<TR class="tr_show">
	<TD noWrap rowSpan="4" align="center" class="label"><bean:message key="TawRmRecord.baseinfo"/></TD>
	<TD noWrap class="label"><bean:message key="TawRmSysteminfo.roomName"/></TD>
	<TD colSpan="3">
	<input name="RoomName" id="RoomName" type="text" style="BACKGROUND-COLOR: lightgrey " readOnly="" maxlength="150" size="20" value="<%=roomName%>" class="clstext"/>
        </TD>
        <TD class="label"><bean:message key="TawRmRecord.receivertime"/></TD>
	<TD colSpan="1">
        <html:hidden property="starttime" />
	<input name="starttime" id="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
	</TD>
        <html:hidden property="flag" />
</TR>
<TR class="tr_show">
	<TD class="label">
            <bean:message key="TawRmRecord.hander"/>
        </TD>
	<TD colSpan="5">
        <html:hidden property="hander"  />
        <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey"  class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

        </TD>
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.dutyman"/></TD>
	<TD colSpan="5">
	<html:hidden property="dutyman" />
	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.receiver"/></TD>
	<TD colSpan="5">
	<html:hidden property="receiver" />
	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD noWrap align="center" class="label"><bean:message key="TawRmHangover.prehangquestion"/></TD>
	<TD colSpan="8">
        <TEXTAREA id=hangQuestion0 name=hangQuestion0 rows=4 cols=70 style="BACKGROUND-COLOR: lightgrey" readOnly=""><%=String.valueOf(request.getAttribute("strHangQuestion"))%></TEXTAREA>
        </TD>
</TR>
<tr class="tr_show">
  <td class="label">
  <bean:message key="TawRmRecord.remark1"/>
     
  </td>
  <td colspan="6" >
<%for(int i = 1; i <= StaticDutycheck.dutycheckcount; i++ ){%>
    <font title="<bean:write name='<%=StaticDutycheck.getDutycheckId(i)%>'/>" color='<%out.print(dutycheck.lastIndexOf(StaticDutycheck.getDutycheckId(i))!=-1?"black":"red");%>'><%=StaticDutycheck.getDutycheckName( StaticDutycheck.getDutycheckId(i) )%></font>&nbsp;
<%}%>
  </td>
</tr>

<TR class="tr_show">
        <TD align="center" class="label"><bean:message key="TawRmRecord.accessories"/></TD>
        <TD colSpan="6">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height=60 SCROLLING=YES SRC='../TawRmDutyfile/dutyfile.do?WORKSERIAL=<bean:write name="tawRmRecordForm" property="id"/>'>
        </IFRAME>
        </TD>
</TR>
<TR class="tr_show">
    <TD align="center" class="label"><bean:message key="TawRmRecord.personrecord"/></TD>
    <TD colSpan="6">&nbsp;
      <SPAN id=spanSubReocrd>
        <logic:iterate id="tawRmRecordSub" name="TAWRMRECORD_DUTYMAN" type="com.boco.eoms.duty.model.TawRmRecordSub">
            <html:link href="../TawRmRecordSub/view.do" target="blank"  paramId="id" paramName="tawRmRecordSub" paramProperty="id">
                <bean:write name="tawRmRecordSub" property="dutyman" scope="page"/>
            </html:link>
        </logic:iterate>&nbsp;
      </SPAN></TD>
  </TR>
<%
}
else
{
%>
<TR class="tr_show">
	<TD width="16%" noWrap align="middle" align="center" rowSpan="2" class="clsfth"><bean:message key="TawRmRecord.circumstance"/></TD>
	<TD class="label"><bean:message key="TawRmRecord.weather"/></TD>
	<TD><html:hidden property="weather" /><input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordForm" property="weather" scope="request"/>" />**
	</TD>
	<TD class="label"><bean:message key="TawRmRecord.temperature"/></TD>
        <TD><html:hidden property="temperature" /><input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="5" value="<bean:write name="tawRmRecordForm" property="temperature" scope="request"/>" />&nbsp;C
	</TD>

        <TD class="label"><bean:message key="TawRmRecord.wet"/></TD>
        <TD><html:hidden property="wet" /><input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="5" value="<bean:write name="tawRmRecordForm" property="wet" scope="request"/>" />&nbsp;%
	</TD>
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.clean"/></TD>
	<TD colspan="1"><html:hidden property="clean" /><input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="10" value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>" />**
	</TD>

        <html:hidden property="clean1" />

        <TD class="label"><bean:message key="TawRmRecord.conditioner"/></TD>
        <TD colspan="3"><html:hidden property="conditioner" /><input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="10" value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>" />**</TD>

</TR>
<TR class="tr_show">
	<TD noWrap rowSpan="4" align="center" class="label"><bean:message key="TawRmRecord.baseinfo"/></TD>
	<TD noWrap class="label"><bean:message key="TawRmSysteminfo.roomName"/></TD>
	<TD colSpan="3">
	<input name="RoomName" id="RoomName" type="text" style="BACKGROUND-COLOR: lightgrey " readOnly="" maxlength="150" size="20" value="<%=roomName%>" class="clstext"/>
        </TD>
	<TD class="label"><bean:message key="TawRmRecord.receivertime"/></TD>
	<TD colSpan="1">
        <html:hidden property="starttime" />
	<input name="starttime" id="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
	</TD>
        <html:hidden property="flag" />

</TR>
<TR class="tr_show">
	<TD class="label">
            <bean:message key="TawRmRecord.hander"/>
        </TD>
	<TD colSpan="5">
        <html:hidden property="hander"  />
        <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey"  class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

        </TD>
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.dutyman"/></TD>
	<TD colSpan="5">
	<html:hidden property="dutyman" />
	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.receiver"/></TD>
	<TD colSpan="5">
	<html:hidden property="receiver" />
	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
        </TD>
</TR>
<TR class="tr_show">
	<TD noWrap align="center" class="label"><bean:message key="TawRmHangover.prehangquestion"/></TD>
	<TD colSpan="8">
        <TEXTAREA id=hangQuestion0 name=hangQuestion0 rows=4 cols=70 style="BACKGROUND-COLOR: lightgrey" readOnly=""><%=String.valueOf(request.getAttribute("strHangQuestion"))%></TEXTAREA>
        </TD>
</TR>
<tr class="tr_show">
  <td class="label">
  <bean:message key="TawRmRecord.remark1"/>
  </td>
  <td colspan="6" >

<%for(int i = 1; i <= StaticDutycheck.dutycheckcount; i++ ){%>
    <font title="<bean:write name='<%=StaticDutycheck.getDutycheckId(i)%>'/>" color='<%out.print(dutycheck.lastIndexOf(StaticDutycheck.getDutycheckId(i))!=-1?"black":"red");%>'><%=StaticDutycheck.getDutycheckName( StaticDutycheck.getDutycheckId(i) )%></font>&nbsp;
<%}%>

  </td>
</tr>
<TR class="tr_show">
        <TD align="middle" class="label"><bean:message key="TawRmRecord.accessories"/></TD>
        <TD colSpan="6">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height=60 SCROLLING=YES SRC='../TawRmDutyfile/dutyfile.do?WORKSERIAL=<bean:write name="tawRmRecordForm" property="id"/>'>
        </IFRAME>
        </TD>
</TR>
<%}%>
</TABLE>
<br>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="listTable" align=center>
<TR class="tr_show">
        <TD width="16%" noWrap class="label"><bean:message key="TawRmRecordSub.dutyman"/></TD>
        <TD>

            <input name="dutymansub" id="dutymansub" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" maxlength="150" size="20" value="<%=saveSessionBeanForm.getUsername()%>" />
        </TD>
        <TD noWrap class="label"><bean:message key="TawRmRecordSub.starttime"/></TD>
        <TD>
        <%String starttimesub = String.valueOf(request.getAttribute("STARTTIME"));%>
        <input name="starttimesub" id="starttimesub" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=starttimesub%>" />
        </TD>
</TR>
<TR class="tr_show">
        <TD noWrap class="label"><bean:message key="TawRmRecordSub.starttimeDefined"/></TD>
        <TD>
        <%String Starttime_Defined = String.valueOf(request.getAttribute("STARTTIMEDEFINED"));%>
        <input name="starttimeDefined" id="starttimeDefined" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=Starttime_Defined%>" />
        </TD>
        <TD noWrap class="label"><bean:message key="TawRmRecordSub.endtimeDefined"/></TD>
        <TD>
        <%String Endtime_Defined = String.valueOf(request.getAttribute("ENDTIMEDEFINED"));%>
        <input name="endtimeDefined" id="endtimeDefined" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=Endtime_Defined%>" />
        </TD>
</TR>
<TR class="tr_show">
        <TD noWrap class="label"><bean:message key="TawRmRecordSub.workflag"/></TD>
        <TD>
        <%
          
         String WorkFlagStr = String.valueOf(request.getAttribute("WORKFALG"));
        
        %>
        <input name="workflag" id="workflag" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=WorkFlagStr%>" />
        </TD>
        <TD noWrap class="label"><bean:message key="TawRmRecordSub.status"/></TD>
        <TD>
        <%
        
         int StatusInt = Integer.parseInt(String.valueOf(request.getAttribute("StatusInt")));
        String StatusStr = String.valueOf(request.getAttribute("STATUS"));
        %>
        <input name="status" id="status" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=StatusStr%>" />
        </TD>
</TR>
<tr class="tr_show">
  <td class="label">
   <bean:message key="TawRmRecord.dayLook"/>
 
  </td>
  <td colspan="3" title='<bean:message key="TawRmRecord.remark2"/>'>
    <%for(int i = 1; i <= StaticDutycheck.dutycheckcount; i++ ){%>
    <%=StaticDutycheck.getDutycheckName( StaticDutycheck.getDutycheckId(i) )%><input type="checkbox" name="<%=StaticDutycheck.getDutycheckId(i)%>" <%if (dutychecksub.lastIndexOf(StaticDutycheck.getDutycheckId(i))!=-1) out.print("checked");%>/>&nbsp;
    <%}%>
  </td>
</tr>
<tr class="tr_show">
  <td class="label">
  <bean:message key="TawRmRecord.dictType"/>
   
  </td>
  <td colspan="3" title="TawRmRecord.remark3">
    <%if("1".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="1" checked="checked" onclick="javascript:show(1);"><bean:message key="TawRmRecord.trans"/>
    <%}else{%>
  <input type="radio" name="typeMode" value="1" onclick="javascript:show(1);"><bean:message key="TawRmRecord.trans"/>
    <%}%>
       <%if("2".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="2" checked="checked" onclick="javascript:show(2);"><bean:message key="TawRmRecord.change"/>
    <%}else{%>
  <input type="radio" name="typeMode" value="2" onclick="javascript:show(2);"><bean:message key="TawRmRecord.change"/>
    <%}%>
      <%if("3".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="3" checked="checked" onclick="javascript:show(3);"> <bean:message key="TawRmRecord.date"/>
    <%}else{%>
  <input type="radio" name="typeMode" value="3" onclick="javascript:show(3);"><bean:message key="TawRmRecord.date"/>
    <%}%>
    <%if("4".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="4" checked="checked" onclick="javascript:show(4);"><bean:message key="TawRmRecord.system"/>
    <%}else{%>
  <input type="radio" name="typeMode" value="4" onclick="javascript:show(4);"><bean:message key="TawRmRecord.system"/>
    <%}%>
     <%if("5".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="5" checked="checked" onclick="javascript:show(5);"><bean:message key="TawRmRecord.other"/>
    <%}else{%>
  <input type="radio" name="typeMode" value="5" onclick="javascript:show(5);"><bean:message key="TawRmRecord.other"/>
    <%}%>
      <%if("6".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="6" checked="checked" onclick="javascript:show(6);">${eoms:a2u('值班综述')}
    <%}else{%>
  <input type="radio" name="typeMode" value="6" onclick="javascript:show(6);">${eoms:a2u('值班综述')}
    <%}%>
       <%if("7".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="7" checked="checked" onclick="javascript:show(7);">${eoms:a2u('重要纪事')}
    <%}else{%>
  <input type="radio" name="typeMode" value="7" onclick="javascript:show(7);">${eoms:a2u('重要纪事')}
    <%}%>
      <%if("8".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="8" checked="checked" onclick="javascript:show(8);"> ${eoms:a2u('重大故障')}
    <%}else{%>
  <input type="radio" name="typeMode" value="8" onclick="javascript:show(8);">${eoms:a2u('重大故障')}
    <%}%>
    <%if("9".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="9" checked="checked" onclick="javascript:show(9);">${eoms:a2u('网络割接')}
    <%}else{%>
  <input type="radio" name="typeMode" value="9" onclick="javascript:show(9);">${eoms:a2u('网络割接')}
    <%}%>
     <%if("0".equals(typemode)){ %>
  <input type="radio" name="typeMode" value="0" checked="checked" onclick="javascript:show(0);">${eoms:a2u('网络KPI')}
    <%}else{%>
  <input type="radio" name="typeMode" value="0" onclick="javascript:show(0);">${eoms:a2u('网络KPI')}
    <%}%>
  </td>
</tr>
<TR class="tr_show">
        <TD class="label"><bean:message key="TawRmRecord.remark4"/></TD>
<bean:message key="label.zhuanye"/>
          <TD colSpan="3"><textarea name="logtype" id="logtype" readonly="" style="BACKGROUND-COLOR: lightgrey" rows="9" cols="90" Class="clstext" type="_moz">
1.<bean:message key="TawRmRecord.trans"/><bean:message key="label.zhuanye"/>:<%=trans.substring(1,trans.length()-1)%>
2.<bean:message key="TawRmRecord.change"/><bean:message key="label.zhuanye"/>:<%=swtch.substring(1,swtch.length()-1)%>
3.<bean:message key="TawRmRecord.date"/><bean:message key="label.zhuanye"/> :<%=data.substring(1,data.length()-1)%>
4.<bean:message key="TawRmRecord.system"/><bean:message key="label.zhuanye"/> :<%=system.substring(1,system.length()-1)%>
5.<bean:message key="TawRmRecord.other"/><bean:message key="label.zhuanye"/>:<%=other.substring(1,other.length()-1)%>
6.${eoms:a2u('值班综述')}:<%=dutySummary.substring(1,dutySummary.length()-1)%>
7.${eoms:a2u('重要纪事')}:<%=importRecord.substring(1,importRecord.length()-1)%>
8.${eoms:a2u('重大故障')} :<%=importFault.substring(1,importFault.length()-1)%>
9.${eoms:a2u('网络割接')}:<%=netCut.substring(1,netCut.length()-1)%>
10.${eoms:a2u('网络KPI')}:<%=netKPI.substring(1,netKPI.length()-1)%>    
          </textarea>  
            </TD>
</TR>

<TR class="tr_show">
        <TD class="label"><bean:message key="TawRmRecordSub.statement"/></TD>
        <%if (StatusInt ==0){%>
          <TD colSpan="3"><textarea name="statement" id="statement" rows="4" cols="90" Class="clstext"><%=request.getAttribute("STATEMENT")%></textarea>
        <%}else{%>
          <TD colSpan="3"><textarea name="statement" id="statement" readonly="" style="BACKGROUND-COLOR: lightgrey" rows="4" cols="60" Class="clstext"><%=request.getAttribute("STATEMENT")%></textarea>
        <%}%>
            <span id="Customvalidator2" controltovalidate="Statement" errormessage="<BR> " display="Dynamic" evaluationfunction="CustomValidatorEvaluateIsValid" clientvalidationfunction="CheckStatement" style="color:Red;display:none;"><BR>
             </span></TD>
</TR>
<!-- add by sunshengtai start 
<!--
<tr class="tr_show">
	<td class="label"  rowspan="4" valign="middle">
		<bean:message key="TawRmRecordSub.watchSummary"/>
	</td>
	<td class="label">
		<bean:message key="TawRmRecordSub.importantNetFault"/>
	</td>
	<td colspan="2">
		
		<textarea id="importantnetfault" name="importantnetfault" rows="3" cols="71" type="_moz"><%=impNetFault%></textarea>
	</td>
</tr>
	
<tr>
	<td class="label">
		<bean:message key="TawRmRecordSub.importantSocietyDisaster"/>
	</td>
	<td colspan="2">
		<textarea id="importantsocietydisaster" name="importantsocietydisaster" rows="3" cols="71" value="${tawRmRecordForm.importantsocietydisaster}" type="_moz"><%=importantSocietyDisaster%></textarea>
	</td>
</tr>

<tr>
	<td class="label">
		<bean:message key="TawRmRecordSub.needCorrespond"/>
	</td>
	<td colspan="2">
		<textarea id="needcorrespond" name="needcorrespond" rows="3" cols="71"><%=needcorrespond%></textarea>
	</td>
</tr>

<tr>
	<td class="label">
		<bean:message key="TawRmRecordSub.handOverProceeding"/>
	</td>
	<td colspan="2">
		<textarea id="handoverproceeding" name="handoverproceeding" rows="3" cols="71"><%=handoverproceeding%></textarea>
	</td>
</tr>
 
  add by sunshengtai end -->
 
<!--TR class="tr_show">
					<TD  class="label" colSpan="7" align="center">
						${eoms:a2u('值班期间处理的工单及作业计划')}
					</TD></TR>
 <TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('故障')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(faultsheetlist!=null&&faultsheetlist.size()>0){
					for(int i=0;i<faultsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('作业计划记录')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(monthPlanVOList!=null&&monthPlanVOList.size()>0){
					for(int i=0;i<monthPlanVOList.size();i++){
					TawwpMonthPlanVO tawwpMonthPlanVO =new TawwpMonthPlanVO();
					tawwpMonthPlanVO=(TawwpMonthPlanVO)monthPlanVOList.get(i);
					
									%>
					
					[ <a href="../tawwpexecute/executeadds.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>&flag=duty"><%=tawwpMonthPlanVO.getName()%></a>]<br>
										<%}}%>
					</TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
					${eoms:a2u('重大故障上报')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(faultreportsheetlist!=null&&faultreportsheetlist.size()>0){
					for(int i=0;i<faultreportsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('割接')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(cutoversheetlist!=null&&cutoversheetlist.size()>0){
					for(int i=0;i<faultsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR-->
</TABLE>
<br>
<table border="0" width="95%" cellspacing="1"  cellpadding="1" align=center>
<TR >
        <TD align="label" colSpan="6" >
        <%--<html:submit styleClass="clsbtn2" onclick="return checkform();">
        <bean:message key="label.save"/>
        </html:submit>&nbsp;&nbsp;
        --%><input type=submit Class="button" name="load" value='<bean:message key="label.rush"/>' onclick="javascript:fun_refresh();">
        <input type=submit Class="button" name="load" value='<bean:message key="label.save"/>' onclick="return checkform();">
        </TD>
</TR>
</TABLE>
</html:form>


</body>
</html>

