<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
.x-form-column{width:320px}
</style>

<script type="text/javascript">

var v;
function initPage(){

	v = new eoms.form.Validation({form:'theform'});
 } 
Ext.onReady(function(){	
   var strUrl = '${app}/sheet/branchindexreduction/branchindexreduction.do?method=showInputEventPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}';  
	var config = {
		url:strUrl,
		callback : initPage
	}
	eoms.util.appendPage("idSpecial",config);
});
</script>
<div id="sheetform">
<html:form action="/branchindexreduction.do?method=performProcessEvent" styleId="theform">
 <div ID="idSpecial"></div>
  <!-- buttons -->
		<div class="x-form-item">
			<div class="form-btns">
    		<html:submit styleClass="btn" property="method.save" styleId="method.save">
            	<fmt:message key="button.save"/>
        	</html:submit></div>
		</div>
</html:form>
</div>