
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="smsMobilesTemplateDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="smsMobilesTemplateDetail.heading"/></content> -->

<!--对表单的自动生成的处琄1�7-->
<html:form action="saveSmsMobilesTemplate" method="post" styleId="smsMobilesTemplateForm"> 
<ul>

    <!--表示对所有的域有敄1�7 -->
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="smsMobilesTemplateForm.name"/>
		        <html:errors property="name"/>
			    -->
			        <html:text property="name" styleId="name" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="smsMobilesTemplateForm.remark"/>
		        <html:errors property="remark"/>
			    -->
			        <html:text property="remark" styleId="remark" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="smsMobilesTemplateForm.users"/>
		        <html:errors property="users"/>
			    -->
			        <html:text property="users" styleId="users" styleClass="text medium"/>
		    </li>
	       <html:hidden property="id"/>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="smsMobilesTemplateForm.mobiles"/>
		        <html:errors property="mobiles"/>
			    -->
			        <html:text property="mobiles" styleId="mobiles" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="smsMobilesTemplateForm.deleted"/>
		        <html:errors property="deleted"/>
			    -->
			        <html:text property="deleted" styleId="deleted" styleClass="text medium"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('SmsMobilesTemplate')">
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