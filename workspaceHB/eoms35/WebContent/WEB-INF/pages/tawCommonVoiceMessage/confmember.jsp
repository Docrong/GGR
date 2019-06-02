<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>

<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>
<script type="text/javascript">
var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('form', '${eoms:a2u('会议人员增加')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<script language="javascript">

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawHieApplyForm'});
});
</script>
<script language="javascript">
<!--
function addRow(td, sid, did) {
	var sTb = eval(sid);
	var sRowIndex = td.parentElement.parentElement.rowIndex;
	var sColCount = sTb.rows[sRowIndex - 1].cells.length;

	var dTb = eval(did);
	var dTr = dTb.insertRow();
	dTr.className = "tr_show";

	var arryId = new Array("seledId", "memberName", "memPhone", "memType");
	for (var i = 0; i < sColCount + 1; i++) {
		var dTd = dTr.insertCell();
		if (i < sColCount - 1) {
			var value = sTb.rows[sRowIndex].cells[i].innerHTML;

			//var inputText   =   document.createElement("input");
      		//inputText.type   =   "text";
      		//inputText.value   =   value;
      		//inputText.name   =   arryId[i];
			//inputText.className = "textfield";
			//inputText.disabled = true;
			//dTd.appendChild(inputText);

			var content = "<input type=\"text\" name=\"" + arryId[i] +"\" class=\"textfield\" value=\"" + value + "\"  readOnly=\"true\" />"
			//alert(content);
			dTd.innerHTML = content;
		} else if (i == sColCount - 1){
			content = "<select name=\"joinMode\"><option value=\"0\">${eoms:a2u('加入')}</option><option value=\"1\">${eoms:a2u('监听')}</option><option value=\"2\">${eoms:a2u('卡拉OK')}</option></select>";
			dTd.innerHTML = content;
		} else if (i == sColCount){
		
			var contextPath = document.getElementById("contextpath").value.toString();
			content = "<img src='" + contextPath + "/images/desc.gif' border='0' onclick='removeRow(this)'>";
			dTd.innerHTML = content;
		}
	}
	sTb.deleteRow(sRowIndex);
	if (dTb.rows.length > 1) {
	 		document.getElementById("strutsButton").disabled = false;
 	}
}
function removeRow(td) {
	var sTb = document.getElementById("dtable");
	var sRowIndex = td.parentElement.parentElement.rowIndex;
	var sColCount = sTb.rows[sRowIndex - 1].cells.length;

	var dTb = document.getElementById("stable");
	var dTr = dTb.insertRow();
	dTr.className = "tr_show";

	for (i = 0; i < sColCount - 1; i++) {
		var dTd = dTr.insertCell();
		if (i < sColCount - 2) {
			var content = sTb.rows[sRowIndex].cells[i].all[0].value;
			//alert(sTb.rows[sRowIndex].cells[i].innerHTML);
			dTd.innerHTML = content;
		} else {
			var contextPath = document.getElementById("contextpath").value.toString();
			content = "<img src='" + contextPath + "/images/asc.gif' border='0' onclick='addRow(this, \"stable\", \"dtable\")'>";
			//alert(content);
			dTd.innerHTML = content;
		}
	}

	sTb.deleteRow(sRowIndex);
	if (sTb.rows.length < 2) {
		document.getElementById("strutsButton").disabled = true;
	}
}
function init() {
	document.getElementById("strutsButton").disabled = true;
}
-->
</script>
<body onLoad="init()">
<content tag="heading">${eoms:a2u('会议第二步（选择会议人员信息）')}</content>
<div id="tabs">
<div id="form" class="tab-content">
<input type="hidden" id="contextpath" value="<%=request.getContextPath()%>" />
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
       
	
 <tr class="tr_show">
		<td width="20%" height="25" class="clsfth">${eoms:a2u('会议编号')}</td>
		<td width="80%" height="25" colspan=3>
           <html:text name="tawHieApplyForm" property="confNo" readonly="true"/>
	   </td>
 </tr>

</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table logList" align=center id="stable">

  <tr class="td_label">
    <td width="15%" height="25">${eoms:a2u('成员编号')}</td>
    <td width="15%" height="25">${eoms:a2u('姓名')}</td>
    <td width="15%" height="25">${eoms:a2u('联系方式')}</td>
    <td width="25%" height="25">${eoms:a2u('用户类型')}</td>

      <td><bean:message key="label.addoper"/></td>

  </tr>
  <logic:iterate id="tawConMem" name="ALLMEMS" type="com.boco.eoms.commons.voiceMessage.model.TawConMem">
  <tr class="tr_show">
    <td width="15%" height="25"><bean:write name="tawConMem" property="userId" scope="page"/></td>
    <td width="15%" height="25"><bean:write name="tawConMem" property="userName" scope="page"/></td>
    <td width="15%" height="25"><bean:write name="tawConMem" property="userTel" scope="page"/></td>
    <td width="25%" height="25"><bean:write name="tawConMem" property="userType" scope="page"/></td>
    <td width="25%" height="25"><img src="<%=request.getContextPath()%>/images/asc.gif" border="0" onclick="addRow(this, 'stable', 'dtable')"></td>

  </tr>
	</logic:iterate>
</table>
<br>
<br>
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
<tr>
    <td class="table_title" width="100%">
    	${eoms:a2u('添加成员：')}
    </td>
</tr>
</table>

<html:form action="/TawHieApply/addmems" method="post"
	styleId="tawHieApplyForm" >
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table logList" align=center id="dtable">
<tr class="td_label">
    <td width="15%" height="25">${eoms:a2u('用户编号')}</td>
    <td width="15%" height="25">${eoms:a2u('姓名')}</td>
    <td width="15%" height="25">${eoms:a2u('联系方式')}</td>
    <td width="25%" height="25">${eoms:a2u('用户类型')}</td>
    <td width="20%" height="25">${eoms:a2u('参会方式')}</td>
       <td width="10%" height="25">${eoms:a2u('删除')}</td>

      

  </tr>
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:submit property="strutsButton" styleClass="clsbtn2">
                     ${eoms:a2u('完成')}
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请添加参与会议的人员，添加完毕后，请点击完成')}
  </div>
</div>
</body>
</html>
