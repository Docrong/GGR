
<jsp:directive.page import="com.boco.eoms.workbench.infopub.util.InfopubConstants" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
	function choose(checkbox){
		eoms.util.checkAll(checkbox,'ids');
	}
	function submitForm(status){
		var flag = false;
		var objs = document.getElementsByName("ids");
		for(var i=0; i<objs.length; i++){
			if(objs[i].type.toLowerCase() == "checkbox"){
				if(objs[i].checked){
      				flag="true";
      				break;
				}
			}
		}
		if(flag == "true"){
			document.getElementById('status').value=status;
			document.forms[0].submit();   
			return true;
		}else{
			alert("<bean:message key='threadForm.tips.choosecheckinfo'/>");
			return false;
		}
	}

</script>
<content tag="heading">
<bean:message key="threadAuditHistoryList.heading" />
</content>
<html:form action="/threadAuditHistory.do?method=audit" method="post" styleId="threadAuditHistoryForm">
	<input type="hidden" name="status" id="status" />
	<display:table name="threadAuditHistoryList" cellspacing="0" cellpadding="0" id="threadAuditHistoryList" pagesize="${pageSize }" class="table threadAuditHistoryList" export="true" requestURI="${app}/workbench/infopub/threadAuditHistory.do?method=search"
		sort="external" partialList="true" size="resultSize" decorator="com.boco.eoms.workbench.infopub.displaytag.support.ThreadAuditHistoryListDisplayTagDecorator">

<display:setProperty name="export.rtf" value="false"></display:setProperty>

		<display:column property="id" title="<input type='checkbox' onclick='javascript:choose(this);'/>" />

		<display:column sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.threadId" paramId="id" paramProperty="threadId" href="${app}/workbench/infopub/thread.do?method=detail">
			<eoms:id2nameDB id="${threadAuditHistoryList.threadId}" beanId="threadDao" />
		</display:column>


		<display:column property="status" sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.status" href="${app}/workbench/infopub/threadAuditHistory.do?method=device" paramId="threadId" paramProperty="threadId"/>

		<display:column property="submitTime" sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.submitTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="auditTime" sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.auditTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="orgId" sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.orgId" />

		<display:column property="opinion" sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.opinion" />

		<display:column property="note" sortable="true" headerClass="sortable" titleKey="infopub.threadAuditHistoryForm.note" />

		<display:setProperty name="paging.banner.item_name" value="threadAuditHistory" />
		<display:setProperty name="paging.banner.items_name" value="threadAuditHistorys" />
	</display:table>


	<logic:notEmpty name="threadAuditHistoryList">
		<li>
			<eoms:label styleClass="desc" key="threadAuditHistoryForm.opinion" />
			<html:errors property="opinion" />
			<html:textarea property="opinion" styleId="opinion" styleClass="textarea medium" />
		</li>
		<input type="button" onclick="javascript:submitForm('<%=InfopubConstants.AUDIT_PASS%>');" value="<bean:message key='threadAuditHistoryForm.button.passaudit' />" />

		<input type="button" onclick="javascript:submitForm('<%=InfopubConstants.AUDIT_NO_PASS%>');" value="<bean:message key='threadAuditHistoryForm.button.nopassaudit' />" />
	</logic:notEmpty>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
