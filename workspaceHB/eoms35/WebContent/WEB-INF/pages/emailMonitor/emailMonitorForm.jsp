
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="emailMonitorDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="emailMonitorDetail.heading"/></content> -->

<!--对表单的自动生成的处琄1�7-->
<html:form action="saveEmailMonitor" method="post" styleId="emailMonitorForm"> 
<ul>

    <!--表示对所有的域有敄1�7 -->
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.applyId"/>
		        <html:errors property="applyId"/>
			    -->
			        <html:text property="applyId" styleId="applyId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.content"/>
		        <html:errors property="content"/>
			    -->
			        <html:text property="content" styleId="content" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.dispatchTime"/>
		        <html:errors property="dispatchTime"/>
			    -->
			        <html:text property="dispatchTime" styleId="dispatchTime" styleClass="text medium"/>
		    </li>
	       <html:hidden property="id"/>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.email"/>
		        <html:errors property="email"/>
			    -->
			        <html:text property="email" styleId="email" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.receiverId"/>
		        <html:errors property="receiverId"/>
			    -->
			        <html:text property="receiverId" styleId="receiverId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.buizid"/>
		        <html:errors property="buizid"/>
			    -->
			        <html:text property="buizid" styleId="buizid" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.serviceId"/>
		        <html:errors property="serviceId"/>
			    -->
			        <html:text property="serviceId" styleId="serviceId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.isSendImediat"/>
		        <html:errors property="isSendImediat"/>
			    -->
			        <html:text property="isSendImediat" styleId="isSendImediat" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.deleted"/>
		        <html:errors property="deleted"/>
			    -->
			        <html:text property="deleted" styleId="deleted" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="emailMonitorForm.regetData"/>
		        <html:errors property="regetData"/>
			    -->
			        <html:text property="regetData" styleId="regetData" styleClass="text medium"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('EmailMonitor')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>