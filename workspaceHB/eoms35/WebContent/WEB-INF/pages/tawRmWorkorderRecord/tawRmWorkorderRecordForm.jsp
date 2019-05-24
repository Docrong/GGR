
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmWorkorderRecordDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmWorkorderRecordDetail.heading"/></content> -->

<!--对表单的自动生成的处�?-->
<html:form action="saveTawRmWorkorderRecord" method="post" styleId="tawRmWorkorderRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.workOrderId"/>
		        <html:errors property="workOrderId"/>
			    -->
			        <html:text property="workOrderId" styleId="workOrderId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.topic"/>
		        <html:errors property="topic"/>
			    -->
			        <html:text property="topic" styleId="topic" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.receiveOrderId"/>
		        <html:errors property="receiveOrderId"/>
			    -->
			        <html:text property="receiveOrderId" styleId="receiveOrderId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.sendOrderId"/>
		        <html:errors property="sendOrderId"/>
			    -->
			        <html:text property="sendOrderId" styleId="sendOrderId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.replyTime"/>
		        <html:errors property="replyTime"/>
			    -->
			        <html:text property="replyTime" styleId="replyTime" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.receiveTime"/>
		        <html:errors property="receiveTime"/>
			    -->
			        <html:text property="receiveTime" styleId="receiveTime" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderType"/>
		        <html:errors property="orderType"/>
			    -->
			        <html:text property="orderType" styleId="orderType" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderState"/>
		        <html:errors property="orderState"/>
			    -->
			        <html:text property="orderState" styleId="orderState" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.userId"/>
		        <html:errors property="userId"/>
			    -->
			        <html:text property="userId" styleId="userId" styleClass="text medium"/>
		    </li>
		    <li>
		        <!--
		        <eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.workSerial"/>
		        <html:errors property="workSerial"/>
			    -->
			        <html:text property="workSerial" styleId="workSerial" styleClass="text medium"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        <!--用自动生成的参数调用Javascript --> 
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawRmWorkorderRecord')">
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