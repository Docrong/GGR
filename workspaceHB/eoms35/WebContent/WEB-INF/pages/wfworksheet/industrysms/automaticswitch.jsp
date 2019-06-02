<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String name = (String)request.getAttribute("name");
System.out.println("lizhi:name=" + name);
%>
<script type="text/javascript" src="${app}/scripts/base/eoms.js"></script>

	<table class="formTable">
	<tr>
	<td>
	<input type="button" class="submit" value="自动处理开关" onclick="changeSwitch()"/>
	</td>
	</tr>
	<tbody id='open' style="display:none">
	<tr>
	<td>
	已打开自动处理!
	</td>
	</tr>
	</tbody>
	<tbody id='close' style="display:none">
	<tr>
	<td>
	已关闭自动处理!
	</td>
	</tr>
	</tbody>
	</table>
<script type="text/javascript">
var name = '<%=name%>';
if (name == '1') {
			eoms.form.enableArea('open');
			eoms.form.disableArea('close',true);
} else if (name == '0') {
			eoms.form.enableArea('close');
			eoms.form.disableArea('open',true);
}
 function changeSwitch() {
	Ext.Ajax.request({
		url : "${app}/sheet/industrysms/industrysms.do?method=changeSwitch",				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var name = data[0].name;
			if (name == '1') {
						eoms.form.enableArea('open');
						eoms.form.disableArea('close',true);
			} else if (name == '0') {
						eoms.form.enableArea('close');
						eoms.form.disableArea('open',true);
			}	
		}
	});
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>