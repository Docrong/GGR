<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmLogUniteDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="tawRmLogUniteDetail.heading"/></content> -->

<script language="javascript">

function confirmUnite(){
if ( confirm('是否要合并日志') ){
	return true;
	}else{
	return false;
	}
}

</script>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmLogUnite" method="post" styleId="tawRmLogUniteForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
	       <html:hidden property="userId"/>
	       <html:hidden property="workSerial"/>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.workerNames"/>
				<html:textarea property="workerNames" styleId="workerNames"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.planContent"/>
				<html:textarea property="planContent" styleId="planContent"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.workOrder"/>
				<html:textarea property="workOrder" styleId="workOrder"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.workbenchMemo"/>
				<html:textarea property="workbenchMemo" styleId="workbenchMemo"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.dispatchRecord"/>
				<html:textarea property="dispatchRecord" styleId="dispatchRecord"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.visitRecord"/>
				<html:textarea property="visitRecord" styleId="visitRecord"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.reliefRecord"/>
				<html:textarea property="reliefRecord" styleId="reliefRecord"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.loanRecord"/>
				<html:textarea property="loanRecord" styleId="loanRecord"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.beginTime"/>
			   	<html:text property="beginTime" styleId="beginTime" styleClass="text medium" readonly="true"/>
		    </li>
		    <li>
		        <eoms:label styleClass="desc" key="tawRmLogUniteForm.endTime"/>
			   	<html:text property="endTime" styleId="endTime" styleClass="text medium" readonly="true"/>
		    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=true; return confirmUnite()">
            <fmt:message key="button.unite"/>
        </html:submit>

        <!--<html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/> 
        </html:cancel> -->
    </li>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>