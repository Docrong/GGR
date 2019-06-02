<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%
Vector vecId = (Vector)request.getAttribute("vecId");
Vector vecName = (Vector)request.getAttribute("vecName");
%>
<script type="text/javascript" src="${app}/scripts/form/Options.js"></script>
<style type="text/css">
body{
  background-image:none;
  background-color:#c3daf9;
}
#border{
	position:absolute;
	left:30%;
	top:20%;
	border:1px solid #6593cf;
	padding:2px;
	background:#c3daf9;
	width:400px;
	z-index:20001;
}

.room {
	background-color:#F3FAFC;
	border:0pt none;
	color:#003366;
	font-size: 12px;
	padding:10px;
	margin:0;
}
#room-text, #room-select, #workserial, #workserial-text, #btn{
	margin-bottom:8px;
}
</style>

<script type="text/javascript">
function fun_confirm()                            {
	var i = document.forms[0].Wrf_RoomID.selectedIndex;
	if (i==-1){
		alert ("${eoms:a2u("请选择机房")}");
		return false;
	}
	if(eoms.$('str_workserial').length>0 && eoms.$('str_workserial').selectedIndex==-1){
		alert ('${eoms:a2u("请选择班次")}');
		return false;
	}
	return true;
}
function disables(b){
	eoms.$('str_workserial').disabled = eoms.$('submit').disabled = b;
}
function onRoomSel(value){
	disables(true);
	Ext.get('workserial').slideOut('b', {duration:.2,useDisplay:true});
	$('str_workserial').length = 0;
	
	var url = "workserialselect.do?method=xOnSelectRoom&Wrf_RoomID="+value;
	var cfg = {
		method:'get',
		onComplete:function(x){
			var arr = eoms.JSONDecode(x.responseText);
			try{
				if(arr.length>0){
					$('str_workserial').size = arr.length;
					arr.each(function(o,i){
						eoms.form.Options.add('str_workserial',o.name,o.id);
					});
					Ext.get('workserial').slideIn('t', {duration:.2,useDisplay:true});
				}
			}catch(e){};		
			disables(false);
		},
		onError:function(){
			disables(false);
		}
	}
	Ajax.Request(url,cfg);
}
</script>

<html:form method="post" action="/workserialselect.do?method=saveWorkSerial"  styleId="tawSystemSessionForm">

<div id="border">

<div class="room">
  <div id="room-text"><img src="${app}/images/icons/computer.gif" align="absmiddle">&nbsp;<fmt:message key="SaveSessionBean.selectRoom"/></div>
  <div id="room-select">
    <select size="<%=vecId.size()%>" style="width:350px" name="Wrf_RoomID" onchange="onRoomSel(this.value);">
      <%for(int i=0;i<vecId.size();i++) {%>
      <option value='<%=vecId.get(i).toString()%>'><%=vecName.get(i).toString()%></option>
      <%}%>
    </select>
  </div>
  <div id="workserial" class="hide">
    <div id="workserial-text"><img src="${app}/images/icons/date.gif" align="absmiddle">&nbsp;<fmt:message key="SaveSessionBean.selectWorkserial"/></div>
	<select id="str_workserial" name="str_workserial" style="width:350px"></select>
  </div>
  
  <div id="btn">
    <input type="submit" name="submit" id="submit" class="submit" value='<fmt:message key="label.ok" />' onclick="return fun_confirm();">
  <div>
</div>

</div>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
