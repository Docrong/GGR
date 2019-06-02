<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String moduleId = request.getAttribute("moduleId").toString();
%>

<style type="text/css">
  	body{background-image:none;}
</style>

<script type="text/javascript">
function newInstance() {
  var url = '${app}/workbench/commission/tawWorkbenchCommissionInstances.do?method=newInstance&moduleId=' + <%=moduleId%>;
  window.location.href = url;
}

function reinitFrameSize() {
  var iframe = self.parent.frames['commissionInstanceList'];
  iframe.height = iframe.contentWindow.document.documentElement.scrollHeight;
}
</script>

<div class="list">
	<table width="100%">
		<tr>
			<td align="right">
				<bean:write name="pagerHeader" scope="request" filter="false" />
				<input type="button" class="btn"
					value="<fmt:message key="button.add"/>" onclick="newInstance();" />
			</td>
		</tr>
	</table>
	<table class="listTable" id="list-table">
		<tr height="30" class="header">
			<td nowrap="nowrap" width="20%">
				<bean:message key="tawWorkbenchCommissionInstance.moduleName" />
			</td>
			<td nowrap="nowrap" width="20%">
				<bean:message key="tawWorkbenchCommissionInstance.trustorName" />
			</td>
			<td nowrap="nowrap" width="20%">
				<bean:message key="tawWorkbenchCommissionInstance.startTime" />
			</td>
			<td nowrap="nowrap" width="20%">
				<bean:message key="tawWorkbenchCommissionInstance.endTime" />
			</td>
			<td nowrap="nowrap" width="20%">
				<bean:message key="tawWorkbenchCommissionInstance.operation" />
			</td>
		</tr>
		<logic:iterate id="instanceIt" name="instanceIt">
			<tr height="30">
				<bean:define id="id" name="instanceIt" property="id" />
				<td nowrap="nowrap" width="20%">
					<eoms:dict key="dict-commission" dictId="module"
						itemId="<%=moduleId%>" beanId="id2nameXML" />
				</td>
				<td nowrap="nowrap" width="20%">
					<bean:write name="instanceIt" property="trustorName" />
				</td>
				<td nowrap="nowrap" width="20%">
					<bean:write name="instanceIt" property="startTime" />
				</td>
				<td nowrap="nowrap" width="20%">
					<bean:write name="instanceIt" property="endTime" />
				</td>
				<td nowrap="nowrap" width="20%">
					<a href="javascript:if(confirm('<bean:message key="tawWorkbenchCommission.deleteConfirm" />')){var id='<%=id%>';
					var url='${app}/workbench/commission/tawWorkbenchCommissionInstances.do?method=delInstance&id=';
					url=url+id;location.href=url;}">
					<fmt:message key="button.delete" />
					</a>
				</td>
			</tr>
		</logic:iterate>
	</table>
</div>

<script src="${app}/scripts/util/iframe.js" type="text/javascript" />


<script type="text/javascript">
reinitFrameSize();
</script>

<%@ include file="/common/footer_eoms.jsp"%>
