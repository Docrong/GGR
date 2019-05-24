
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmRenewalDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmRenewalDetail.heading"/></content> -->

<!--对表单的自动生成的处�?-->
<html:form action="saveTawRmRenewal" method="post" styleId="tawRmRenewalForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.testcardId"/>
		        <html:errors property="testcardId"/>
			    -->
			        <html:text property="testcardId" styleId="testcardId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.borrowDate"/>
		        <html:errors property="borrowDate"/>
			    -->
			        <html:text property="borrowDate" styleId="borrowDate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.intendingReturnDate"/>
		        <html:errors property="intendingReturnDate"/>
			    -->
			        <html:text property="intendingReturnDate" styleId="intendingReturnDate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.renewDate"/>
		        <html:errors property="renewDate"/>
			    -->
			        <html:text property="renewDate" styleId="renewDate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.borrowerId"/>
		        <html:errors property="borrowerId"/>
			    -->
			        <html:text property="borrowerId" styleId="borrowerId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.userId"/>
		        <html:errors property="userId"/>
			    -->
			        <html:text property="userId" styleId="userId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmRenewalForm.remark"/>
		        <html:errors property="remark"/>
			    -->
			        <html:text property="remark" styleId="remark" styleClass="text medium"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawRmRenewal')">
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