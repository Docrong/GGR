<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawWorkdaySetDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawWorkdaySetDetail.heading"/></content> -->

<!--对表单的自动生成的处琄1�7-->
<html:form action="saveTawWorkdaySet" method="post" styleId="tawWorkdaySetForm">
    <ul>

        <!--表示对所有的域有敄1�7 -->
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.areaId"/>
		        <html:errors property="areaId"/>
			    -->
            <html:text property="areaId" styleId="areaId" styleClass="text medium"/>
        </li>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.createTime"/>
		        <html:errors property="createTime"/>
			    -->
            <html:text property="createTime" styleId="createTime" styleClass="text medium"/>
        </li>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.deleted"/>
		        <html:errors property="deleted"/>
			    -->
            <html:text property="deleted" styleId="deleted" styleClass="text medium"/>
        </li>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.endTime"/>
		        <html:errors property="endTime"/>
			    -->
            <html:text property="endTime" styleId="endTime" styleClass="text medium"/>
        </li>
        <html:hidden property="id"/>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.startTime"/>
		        <html:errors property="startTime"/>
			    -->
            <html:text property="startTime" styleId="startTime" styleClass="text medium"/>
        </li>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.status"/>
		        <html:errors property="status"/>
			    -->
            <html:text property="status" styleId="status" styleClass="text medium"/>
        </li>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.userId"/>
		        <html:errors property="userId"/>
			    -->
            <html:text property="userId" styleId="userId" styleClass="text medium"/>
        </li>
        <li>
            <!--
		        <eoms:label styleClass="desc" key="tawWorkdaySetForm.workDate"/>
		        <html:errors property="workDate"/>
			    -->
            <html:text property="workDate" styleId="workDate" styleClass="text medium"/>
        </li>
        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
                <fmt:message key="button.save"/>
            </html:submit>
            <!--用自动生成的参数调用Javascript -->
            <html:submit styleClass="button" property="method.delete"
                         onclick="bCancel=true; return confirmDelete('TawWorkdaySet')">
                <fmt:message key="button.delete"/>
            </html:submit>

            <html:cancel styleClass="button" onclick="bCancel=true">
                <fmt:message key="button.cancel"/>
            </html:cancel>
        </li>
    </ul>
    <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>