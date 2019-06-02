
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmReplaceDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmReplaceDetail.heading"/></content> -->

<!--对表单的自动生成的处�?-->
<html:form action="saveTawRmReplace" method="post" styleId="tawRmReplaceForm"> 
<ul>

    <!--表示对所有的域有�? -->
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.dutydate"/>
		        <html:errors property="dutydate"/>
			    -->
			        <html:text property="dutydate" styleId="dutydate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.flag"/>
		        <html:errors property="flag"/>
			    -->
			        <html:text property="flag" styleId="flag" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.hander"/>
		        <html:errors property="hander"/>
			    -->
			        <html:text property="hander" styleId="hander" styleClass="text medium"/>
		    </li>
	       <html:hidden property="id"/>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.inputdate"/>
		        <html:errors property="inputdate"/>
			    -->
			        <html:text property="inputdate" styleId="inputdate" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.reason"/>
		        <html:errors property="reason"/>
			    -->
			        <html:text property="reason" styleId="reason" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.receiver"/>
		        <html:errors property="receiver"/>
			    -->
			        <html:text property="receiver" styleId="receiver" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.remark"/>
		        <html:errors property="remark"/>
			    -->
			        <html:text property="remark" styleId="remark" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.roomId"/>
		        <html:errors property="roomId"/>
			    -->
			        <html:text property="roomId" styleId="roomId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmReplaceForm.workserial"/>
		        <html:errors property="workserial"/>
			    -->
			        <html:text property="workserial" styleId="workserial" styleClass="text medium"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawRmReplace')">
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