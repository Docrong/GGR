<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
function listInstancesByModule() {
  var moduleId = document.getElementById('moduleId').value;
  if (moduleId == '' || moduleId < 0) {
    moduleId = '';
  }
  document.all.commissionInstanceList.src = '${app}/workbench/commission/tawWorkbenchCommissionInstances.do?method=listInstances&moduleId=' + moduleId;
}

</script>
<div class="list-title">
	<bean:message key="tawWorkbenchCommission.set" />
</div>
<bean:message key="tawWorkbenchCommission.selectModule" />
<eoms:dict key="dict-commission" dictId="module" isQuery="false"
	defaultId="${defaultId}" selectId="moduleId" beanId="selectXML"
	onchange="listInstancesByModule();" />
<br>

<iframe id='commissionInstanceList' name='commissionInstanceList'
	frameborder=0 scrolling="no" width=100% 
	src='${app}/workbench/commission/tawWorkbenchCommissionInstances.do?method=listInstances&moduleId='>
</iframe>

<%@ include file="/common/footer_eoms.jsp"%>
