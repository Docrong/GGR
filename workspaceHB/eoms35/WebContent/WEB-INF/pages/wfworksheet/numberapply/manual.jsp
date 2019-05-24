<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%
	List dataMoudle = (List)request.getAttribute("dataMoudle");
%>

<script type="text/javascript">
var frmReg;
Ext.onReady(function(){
	 frmReg = new eoms.form.Validation({form:'newFormPage'});
});

function onBack()
{
  window.close();
}



</script>

<form name="newFormPage" method="POST" id="newFormPage" action="numberapply.do?method=performManualSave">
<table class="formTable">
	<input type="hidden"   name="id" id="id" value="${id }"  /><!-- 改条记录的主键id -->
	<input type="hidden"   name="sheetKey" id="sheetKey" value="${sheetKey }"  /><!-- 工单的主键id -->
	<input type="hidden"  name="actionForword" id="actionForword" value="${actionForword }"  /><!-- 跳转的标识 -->
	<input type="hidden"  name="commond24" id="commond24" value="${commond24 }"  /><!-- 该条记录24位信令点的值 -->
	<input type="hidden"  name="commond14" id="commond14" value="${commond14 }"  /><!-- 该条记录14位信令点的值 -->
	<input type="hidden"  name="commondid" id="commondid" value="${commondid }"  /><!-- 该条记录的hlrid或者mscid的值 -->

<tr>
	<td colspan="4">
		<iframe id="frameid" src="" width="100%" height="300px" style="display:none"></iframe>
		<input type="hidden"  name="radioidId" id="radioidId"  /><!-- 选中的记录的id值 -->
		<input type="hidden"  name="radioidValue" id="radioidValue"  /><!-- 选中的记录的值 -->
	</td>
</tr>

<tr>
	<td colspan="4">
		<iframe id="frame24" src="" width="100%" height="300px" style="display:none"></iframe>
		<input type="hidden"  name="radio24Id" id="radio24Id"  /><!-- 选中的记录的id值 -->
		<input type="hidden"  name="radio24Value" id="radio24Value"  /><!-- 选中的记录的值 -->
	</td>
</tr>

<tr>
	<td colspan="4">
		<iframe id="frame14" src="" width="100%" height="300px" style="display:none"></iframe>
		<input type="hidden"  name="radio14Id" id="radio14Id"  /><!-- 选中的记录的id值 -->
		<input type="hidden"  name="radio14Value" id="radio14Value"  /><!-- 选中的记录的值 -->
		
	</td>
</tr>
<%for(int i = 0 ; i<dataMoudle.size();i++){
	//‘101340201101340103’是大型分类中HLR和HLR资源中HLRID的字典值的合并
	//‘101340202101340303’是大型分类中MSC和MSC资源中MSCID的字典值的合并
	if(dataMoudle.get(i).equals("101340201101340103")||dataMoudle.get(i).equals("101340202101340303")){ %>
<script type="text/javascript">
	var frame1 = $('frameid');
	frame1.style.display="block";
	frame1.src = "${app}/sheet/numberapply/numberapply.do?method=performManual&sheetKey=${sheetKey}&actionForword=commondid"; 
</script>
<!-- ‘101340201101340101’是大型分类中HLR和HLR资源中24位信令点的字典值的合并 -->
<!-- ‘101340202101340301’是大型分类中MSC和MSC资源中24位信令点的字典值的合并 -->
<%} if(dataMoudle.get(i).equals("101340201101340101")||dataMoudle.get(i).equals("101340202101340301")){ %>
<script type="text/javascript">
	var frame1 = $('frame24');
	frame1.style.display="block";
	frame1.src = "${app}/sheet/numberapply/numberapply.do?method=performManual&sheetKey=${sheetKey}&actionForword=commond24"; 
</script>
<!-- ‘101340201101340102’是大型分类中HLR和HLR资源中14位信令点的字典值的合并 -->
<!-- ‘101340202101340302’是大型分类中MSC和MSC资源中14位信令点的字典值的合并 -->
<%} if(dataMoudle.get(i).equals("101340201101340102")||dataMoudle.get(i).equals("101340202101340302")){ %>
<script type="text/javascript">
	var frame1 = $('frame14');
	frame1.style.display="block";
	frame1.src = "${app}/sheet/numberapply/numberapply.do?method=performManual&sheetKey=${sheetKey}&actionForword=commond14"; 
</script>
<%}} %>
</table>
<input type="submit"  value="保存" class="submit" onclick="fnSelectValue()">
<input type="button" value="关闭"  Onclick="onBack();" class="button">

<script type="text/javascript">
	function fnSelectValue(){
		var radio14Ids =  $("frame14").contentWindow.document.getElementsByName("radio14");
		var radio14Values =  $("frame14").contentWindow.document.getElementsByName("radio14Value");
		for (var i=0; i < radio14Ids.length; i++) {
			if (radio14Ids[i].checked == true) {
				$('radio14Id').value = radio14Ids[i].value;
				$('radio14Value').value = radio14Values[i].value;
				break;
			}
		}
		
		var radio24Ids =  $("frame24").contentWindow.document.getElementsByName("radio24");
		var radio24Vlaues =  $("frame24").contentWindow.document.getElementsByName("radio24Value");
		for (var i=0; i < radio24Ids.length; i++) {
			if (radio24Ids[i].checked == true) {
				$('radio24Id').value = radio24Ids[i].value;
				$('radio24Value').value = radio24Vlaues[i].value;
				break;
			}
		}
		
		var radioidIds =  $("frameid").contentWindow.document.getElementsByName("radioid");
		var radioidVlaues =  $("frameid").contentWindow.document.getElementsByName("radioidValue");
		for (var i=0; i < radioidIds.length; i++) {
			if (radioidIds[i].checked == true) {
				$('radioidId').value = radioidIds[i].value;
				$('radioidValue').value = radioidVlaues[i].value;
				break;
			}
		}
		
	}

</script>
</form>

<%@ include file="/common/footer_eoms.jsp"%>
