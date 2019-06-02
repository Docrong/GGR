<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript" src="${app}/scripts/base/prototype.js"></script>
<script type="text/javascript" src="${app}/scripts/form/dynamictable.js"></script>
<script type="text/javascript">
var mainDt = new DynamicTable("main-table","t1");
function addrow(targetId,templateId){
	var node = $(targetId);
	var html = $(templateId).innerHTML;
	insertHtml("beforeend",node,html);
}
</script>
 <input type="button" value="add a row" onclick="mainDt.add()" />
 <input type="button" value="add a row (ext)" onclick="addrow('main-table','t1')" />
 <input type="button" value="update" onclick="mainDt.update()" />
  <table id="main-table" border="1">
	<tr id="t1" class="t1">
		<td>I m main <input type="button" value="test" onclick="alert(getRowIndex(this))" />
		<input type="button" value="del" onclick="removeRow(this)" /></td>
	</tr>
  </table>
  
<%@ include file="/common/footer_eoms.jsp"%>
