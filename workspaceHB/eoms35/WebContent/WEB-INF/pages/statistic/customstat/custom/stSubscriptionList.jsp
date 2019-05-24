
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<content tag="heading">
<center>
	<h2>
		<bean:message key="stSubscriptionForm.head" />
	</h2>
</center>
</content>
<script type="text/javascript">	
function ConfirmDel(){
		var msg = "<bean:message key="safetyManForm.deletetip"/>?";
		if (confirm(msg)==true){
			return true;
		}else{
			return false;
		}
    }
 
function showDetail(link){
		var par = "height=550,width=650,status=yes,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
		var yingjiDrillDetail = window.open(link,'',par);
		yingjiDrillDetail.focus();
}
</script>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<c:url 
        value="/statistic/customstat/stSubscriptions.do?method=newAdd"/>'"
		value="<fmt:message key="button.add"/>" />


</c:set>


<fmt:bundle
	basename="config/statistic/customstat-config/ApplicationResources-custom">
	<display:table name="stSubscriptionList" cellspacing="0"
		cellpadding="0" id="stSubscriptionList" pagesize="20"
		class="table stSubscriptionList"
		requestURI="${app}/custom/stSubscriptions.do?method=xquery"
		sort="external" size="resultSize"
		decorator="com.boco.eoms.commons.statistic.customstat.model.StSubscriptionHelper">
		<logic:present name="stSubscriptionList" property="id">

      <display:column property="subId" 
          paramId="id" paramProperty="id"
         titleKey="stSubscriptionForm.subId"/>
     
      

			<display:column headerClass="sortable"
				titleKey="stSubscriptionForm.subscriber">
				<eoms:id2nameDB id="${stSubscriptionList.subscriber}"
					beanId="tawSystemUserDao" />
			</display:column>
			<display:column property="subscribeTime" 
				 paramId="id" paramProperty="id"
				titleKey="stSubscriptionForm.subscribeTime" />
			<display:column property="item" 
				 paramId="id" paramProperty="id"
				titleKey="stSubscriptionForm.itemName" />
			<display:column property="statMode" 
				 paramId="id" paramProperty="id"
				titleKey="stSubscriptionForm.statMode" />
			<display:column property="statfromdate" 
				 paramId="id" paramProperty="id"
				format="{0,date,yyyy-MM-dd HH:mm:SS}"
				titleKey="stSubscriptionForm.statfromdate" />
			<display:column property="stattodate" 
				 paramId="id" paramProperty="id"
				format="{0,date,yyyy-MM-dd HH:mm:SS}"
				titleKey="stSubscriptionForm.stattodate" />


			<display:column  titleKey="button.view">
				<html:link
					href="${app}/statistic/customstat/stSubscriptions.do?method=look"
					paramId="id" paramProperty="id" paramName="stSubscriptionList"
					titleKey="button.view" onclick="showDetail(this);return false;">
					<fmt:message key="button.view" />
				</html:link>
			</display:column>

			<c:choose>
				<c:when test="${stSubscriptionList.subscriber==sessionform.userid}">
					<display:column  paramId="id"
						paramProperty="id" titleKey="button.edit">
						<html:link
							href="${app}/statistic/customstat/stSubscriptions.do?method=xeditinit"
							paramId="id" paramProperty="id" paramName="stSubscriptionList">
							<bean:message key="button.edit" />
						</html:link>
					</display:column>
					<display:column  titleKey="button.delete">
						<html:link
							href="${app}/statistic/customstat/stSubscriptions.do?method=delete"
							paramId="id" paramProperty="id" paramName="stSubscriptionList"
							onclick="javascript:return ConfirmDel();">
							<bean:message key="button.delete" />
						</html:link>
					</display:column>

				</c:when>
				<c:otherwise>
					<display:column  paramId="id"
						paramProperty="id" titleKey="button.edit">
						<bean:message key="button.edit" />
					</display:column>
					<display:column  titleKey="button.delete">
						<bean:message key="button.delete" />
					</display:column>

				</c:otherwise>
			</c:choose>
			<display:setProperty name="paging.banner.item_name"
				value="stSubscription" />
			<display:setProperty name="paging.banner.items_name"
				value="stSubscriptions" />
		</logic:present>
	</display:table>
</fmt:bundle>
<c:out value="${buttons}" escapeXml="false" />

<%@ include file="/common/footer_eoms.jsp"%>

