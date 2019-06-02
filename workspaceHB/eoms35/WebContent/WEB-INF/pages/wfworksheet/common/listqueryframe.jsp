<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ include file="/WEB-INF/pages/wfworksheet/common/listQuery.jsp"%>

<table width="100%" style="border:1px solid #C1DAD7">
  <tr>
    <td><iframe id="list" src="${app}/sheet/softchange/softchange.do?method=showListsendundo" width="100%" height="100%" frameborder="0"></iframe></td>
  </tr>
</table>
<script type="text/javascript">
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
function resize(){
	var el = document.getElementById("list");
	el.style.height = (Ext.get(document.body).getComputedHeight()) - 180 + "px";
}
Ext.onReady(function(){
	resize();
});
</script>


<%@ include file="/common/footer_eoms.jsp"%>
