<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.* , com.boco.eoms.common.controller.*,com.boco.eoms.duty.util.StaticDutycheck,com.boco.eoms.common.util.*, com.boco.eoms.duty.util.DutyConstacts,com.boco.eoms.commons.system.session.form.TawSystemSessionForm,com.boco.eoms.duty.model.TawRmRecord,com.boco.eoms.duty.model.TawRmDutyEvent,com.boco.eoms.workplan.vo.TawwpMonthPlanVO"%>
<%
String roomName = request.getAttribute("roomName").toString();
String NO = request.getAttribute("NO").toString();

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

function fun_refresh()
{
        document.all.tawRmRecordForm.action="ynRecordmain.do?WORKSERIAL="+document.forms[0].statement.value;
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

// 自动扩展textArea
function fun_onExtend(textAreaObj, value) {
   // 第一种模式 用于onclick
   if (value == 1) {
       var textAreaString = textAreaObj.innerText;
       var pos = 0
       var count = 0;
       var b_extend = false;
       while(true){
          pos = textAreaString.indexOf('\r\n', 0);
          if ( pos >= 0 ) {
             textAreaString = textAreaString.substring(pos + 1, textAreaString.length - 1);
             count = count + 1;
             if ( count >= textAreaObj.rows ) {
                b_extend = true;
                break;
             }
          } else {
            break;
          }
       }
    // 超过textArea限定的行数或者字数则扩展
    if (b_extend || textAreaObj.innerText.length >  textAreaObj.rows * textAreaObj.cols) {
        textAreaObj.style.overflowY='visible';
    } else {
        textAreaObj.style.overflowY='scroll';
    }
   }
   // 第2种模式 用于on key press
   if (value == 2) {
       if (textAreaObj.innerText.length >  textAreaObj.rows * textAreaObj.cols) {
           textAreaObj.style.overflowY='visible';
        }  else {
           textAreaObj.style.overflowY='scroll';
       }
    }
    //  第3种模式 离开时回复原状
    if (value == 3) {
       textAreaObj.style.overflowY='scroll';
    }
}

var xmlHttp;

// 创建XMLHttpRequest对象
function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

// ajax doGet获取response
function doRequestUsingGET() {
    $('extract').disabled = true; //// 置灰提取按钮
    createXMLHttpRequest();
    var queryString = '${app}/duty/yndutyevent.do?method=faultExtract';
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.open("GET", queryString, true);
    xmlHttp.send(null);
}
    
function handleStateChange() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            parseResults();
        }
    }
}

// 结果展示
function parseResults() {
    var xmlDoc = xmlHttp.responseText; //// 故障事件和灾害事件以</fault>分割
    var pos = xmlDoc.indexOf('</fault>',0);
    var major_fault = xmlDoc.substring(0, pos);
    $('netfault').value = $('netfault').value + major_fault;
    $('netfault').focus();
    var importantaffair = $('importantaffair').value;
    var disaster_events = xmlDoc.substring(pos + 8);
    $('importantaffair').value = $('importantaffair').value + disaster_events; 
    $('extract').disabled = false; //// 恢复提取按钮  
}	
//-->
</SCRIPT>
</head>
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar" >
<html:form method="post" action="/TawRmRecord/ynsavemain">
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

<%
String typemode = request.getAttribute("typemode").toString();
TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
if (saveSessionBeanForm.getIsDutyMaster())
{
%>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="listTable" align=center>
<TR class="tr_show">
	<TD class="label" align="left"><bean:message key="TawRmRecord.duty.conditioner"/></TD>
	<TD class="label" align="left"><bean:message key="TawRmRecord.clean"/></TD>
	<TD colspan="1">
		<input name="clean" id="clean" type="text" class="clstext" size="10" value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>" />
	</TD>
    <TD class="label"><bean:message key="TawRmRecord.conditioner"/></TD>
   	<TD >
        <input name="conditioner" id="conditioner" type="text" class="clstext" size="10" value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD rowSpan="4" align="left" class="label"><bean:message key="TawRmRecord.baseinfo"/></TD>
	<TD class="label"><bean:message key="TawRmSysteminfo.roomName"/></TD>
	<TD align="left">
		<input name="RoomName" id="RoomName" type="text" style="BACKGROUND-COLOR: lightgrey " readOnly="" maxlength="150" size="20" value="<%=roomName%>" class="clstext"/>
    </TD>
    <TD class="label"><bean:message key="TawRmRecord.receivertime"/></TD>
	<TD >
        <html:hidden property="starttime" />
		<input name="starttime" id="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
	</TD>
    <html:hidden property="flag" />
</TR>
<TR class="tr_show">
	<TD class="label" align="left">
        <bean:message key="TawRmRecord.hander"/>
    </TD>
	<TD colSpan="3">
        <html:hidden property="hander"  />
        <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey"  class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD class="label" align="left"><bean:message key="TawRmRecord.dutyman"/></TD>
	<TD colSpan="3">
	<html:hidden property="dutyman" />
	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
</TR>
<TR class="tr_show">
	<TD class="label" align="left"><bean:message key="TawRmRecord.receiver"/></TD>
	<TD colSpan="3">
	<html:hidden property="receiver" />
	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD rowSpan="4" align="left" class="label">${eoms:a2u('监控纪要')}</TD>
	<TD align="left" class="label">${eoms:a2u('网络重要故障')}</TD>
	<TD colSpan="3">
		<html:textarea rows="4" cols="60%" style="width:100%" name="tawRmRecordForm" property="netfault" styleClass="clstext" onclick="fun_onExtend(this,1)" onblur="fun_onExtend(this,3)"></html:textarea>
	</TD>
</TR>
<TR class="tr_show">
	<TD align="left" class="label">${eoms:a2u('重要社会或灾害事件')}</TD>
	<TD colSpan="3">
		<html:textarea rows="4" cols="60%" style="width:100%" name="tawRmRecordForm" property="importantaffair" styleClass="clstext" onclick="fun_onExtend(this,1)" onblur="fun_onExtend(this,3)"></html:textarea>
	</TD>
</TR>
<TR class="tr_show">
	<TD align="left" class="label">${eoms:a2u('需要省公司协调事项')}</TD>
	<TD colSpan="3">
		<html:textarea rows="4" cols="60%" style="width:100%" name="tawRmRecordForm" property="harmony" styleClass="clstext" onclick="fun_onExtend(this,1)" onblur="fun_onExtend(this,3)"></html:textarea>
	</TD>
</TR>
<TR class="tr_show">
	<TD align="left" class="label">${eoms:a2u('交班事项')}</TD>
	<TD colSpan="3">
		<html:textarea rows="4" cols="60%" style="width:100%" name="tawRmRecordForm" property="otheraffair" styleClass="clstext" onclick="fun_onExtend(this,1)" onblur="fun_onExtend(this,3)"></html:textarea>
	</TD>
</TR>
<TR class="tr_show">
        <TD align="left" class="label"><bean:message key="TawRmRecord.accessories"/></TD>
        <TD colSpan="4">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height=60 SCROLLING=YES SRC='../TawRmDutyfile/dutyfile.do?WORKSERIAL=<bean:write name="tawRmRecordForm" property="id"/>'>
        </IFRAME>
        </TD>
</TR>
 </TABLE>
 <br>
<table border="0" width="95%" cellspacing="1"  cellpadding="1" align=center>
<TR >
        <TD align="label" colSpan="6" >
		<input type=submit Class="button" name="load" value='<bean:message key="label.rush"/>' onclick="javascript:fun_refresh();">
        <input type=submit Class="button" name="load" value='<bean:message key="label.save"/>' onclick="return checkform();">
        <html:button property="extract" value="${eoms:a2u('提取')}" styleClass="button" onclick="doRequestUsingGET();"></html:button>
        </TD>
</TR>
</TABLE>
<% } else { %>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="listTable" align=center>
<TR class="tr_show">
	<TD class="label" align="left"><bean:message key="TawRmRecord.duty.conditioner"/></TD>
	<TD class="label" align="left"><bean:message key="TawRmRecord.clean"/></TD>
	<TD colspan="1">
		<input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="10" value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>" />
	</TD>
    <TD class="label"><bean:message key="TawRmRecord.conditioner"/></TD>
   	<TD ><html:hidden property="conditioner" />
        <input name="tempStr" id="tempStr" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="10" value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD rowSpan="4" align="left" class="label"><bean:message key="TawRmRecord.baseinfo"/></TD>
	<TD class="label"><bean:message key="TawRmSysteminfo.roomName"/></TD>
	<TD align="left">
		<input name="RoomName" id="RoomName" type="text" style="BACKGROUND-COLOR: lightgrey " readOnly="" maxlength="150" size="20" value="<%=roomName%>" class="clstext"/>
    </TD>
    <TD class="label"><bean:message key="TawRmRecord.receivertime"/></TD>
	<TD >
        <html:hidden property="starttime" />
		<input name="starttime" id="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
	</TD>
    <html:hidden property="flag" />
</TR>
<TR class="tr_show">
	<TD class="label" align="left">
        <bean:message key="TawRmRecord.hander"/>
    </TD>
	<TD colSpan="3">
        <html:hidden property="hander"  />
        <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey"  class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD class="label" align="left"><bean:message key="TawRmRecord.dutyman"/></TD>
	<TD colSpan="3">
	<html:hidden property="dutyman" />
	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
</TR>
<TR class="tr_show">
	<TD class="label" align="left"><bean:message key="TawRmRecord.receiver"/></TD>
	<TD colSpan="3">
	<html:hidden property="receiver" />
	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
    </TD>
</TR>
<TR class="tr_show">
	<TD rowSpan="4" align="left" class="label">${eoms:a2u('监控纪要')}</TD>
	<TD align="left" class="label">${eoms:a2u('网络重要故障')}</TD>
	<TD colSpan="3">
		<TEXTAREA name=netfault rows=4 style="width:100%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="netfault" /></TEXTAREA>
	</TD>
</TR>
<TR class="tr_show">
	<TD align="left" class="label">${eoms:a2u('重要社会或灾害事件')}</TD>
	<TD colSpan="3">
		<TEXTAREA name=importantaffair rows=4 style="width:100%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="importantaffair" /></TEXTAREA>
	</TD>
</TR>
<TR class="tr_show">
	<TD align="left" class="label">${eoms:a2u('需要省公司协调事项')}</TD>
	<TD colSpan="3">
		<TEXTAREA name=harmony rows=4 style="width:100%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="harmony" /></TEXTAREA>
	</TD>
</TR>
<TR class="tr_show">
	<TD align="left" class="label">${eoms:a2u('交班事项')}</TD>
	<TD colSpan="3">
		<TEXTAREA name=otheraffair rows=4 style="height:150;width:100%;BACKGROUND-COLOR: lightgrey" readonly="readonly"><bean:write name="tawRmRecordForm" property="otheraffair" /></TEXTAREA>
	</TD>
</TR>
<TR class="tr_show" height="80">
        <TD align="left" class="label"><bean:message key="TawRmRecord.accessories"/></TD>
        <TD colSpan="4">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height=80 SCROLLING=YES SRC='../TawRmDutyfile/dutyfile.do?WORKSERIAL=<bean:write name="tawRmRecordForm" property="id"/>'>
        </IFRAME>
        </TD>
</TR>
</TABLE>
<br>
<table border="0" width="95%" cellspacing="1"  cellpadding="1" align=center>
<TR >
    <TD align="label" colSpan="6" >
		<input type=submit Class="button" name="load" value='<bean:message key="label.rush"/>' onclick="javascript:fun_refresh();">
    </TD>
</TR>
</TABLE>
<%}%>
<input type="hidden" name="typeMode" value="<%=typemode %>" />
</html:form>


</body>
</html>

