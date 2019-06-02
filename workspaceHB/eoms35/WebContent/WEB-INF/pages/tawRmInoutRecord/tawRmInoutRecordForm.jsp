
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmInoutRecordDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmInoutRecordDetail.heading"/></content> -->

<!--对表单的自动生成的处�?-->
<html:form action="saveTawRmInoutRecord" method="post" styleId="tawRmInoutRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.testcardId"/>
		        <html:errors property="testcardId"/>
			    -->
			        <html:text property="testcardId" styleId="testcardId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.borrowDate"/>
		        <html:errors property="borrowDate"/>
			    -->
			        <html:text property="borrowDate" styleId="borrowDate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.intendingReturnDate"/>
		        <html:errors property="intendingReturnDate"/>
			    -->
			        <html:text property="intendingReturnDate" styleId="intendingReturnDate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.realReturnDate"/>
		        <html:errors property="realReturnDate"/>
			    -->
			        <html:text property="realReturnDate" styleId="realReturnDate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.borrowerId"/>
		        <html:errors property="borrowerId"/>
			    -->
			        <html:text property="borrowerId" styleId="borrowerId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.outType"/>
		        <html:errors property="outType"/>
			    -->
			        <html:text property="outType" styleId="outType" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.inType"/>
		        <html:errors property="inType"/>
			    -->
			        <html:text property="inType" styleId="inType" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.inState"/>
		        <html:errors property="inState"/>
			    -->
			        <html:text property="inState" styleId="inState" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.remark"/>
		        <html:errors property="remark"/>
			    -->
			        <html:text property="remark" styleId="remark" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmInoutRecordForm.userId"/>
		        <html:errors property="userId"/>
			    -->
			        <html:text property="userId" styleId="userId" styleClass="text medium"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawRmInoutRecord')">
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