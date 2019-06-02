<%@ page language="java" import="java.util.*,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String specialType = request.getAttribute("specialType").toString();
String serviceType = request.getAttribute("serviceType").toString();
%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
	colorRows('kpi-list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiTemplateAssessForm',vbtn:'method.save'});
})
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
<div class="list-title">
	<eoms:id2nameDB id="<%=serviceType%>" beanId="tawSystemDictTypeDao" />
	-
	<eoms:id2nameDB id="<%=specialType%>" beanId="tawSystemDictTypeDao" />
</div>
</fmt:bundle>

<html:form action="saveTawSupplierkpiTemplateAssess" method="post" styleId="tawSupplierkpiTemplateAssessForm"> 
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30">
		<td width="20%">
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.serviceType"/>
		</td>
		<td width="30%">
			<B><eoms:id2nameDB id="<%=serviceType%>" beanId="tawSystemDictTypeDao" /></B>
		</td>
		<td width="20%">
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.specialType"/>
		</td>
		<td width="30%">
			<B><eoms:id2nameDB id="<%=specialType%>" beanId="tawSystemDictTypeDao" /></B>
		</td>
	</tr>
	<tr height="30">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.realAssessor"/>
		</td>
		<td>
			<B><bean:write name="tawSupplierkpiTemplateAssessForm" property="realAssessor" scope="request"/></B>
		</td>
		<td>
			${eoms:a2u('审核时间')}
		</td>
		<td>
			<B><bean:write name="tawSupplierkpiTemplateAssessForm" property="assessTime" scope="request"/></B>
		</td>
	</tr>
	<tr height="60">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessAttitude"/>
		</td>
		<td colspan="3">
			<B><bean:write name="tawSupplierkpiTemplateAssessForm" property="assessAttitude" scope="request"/></B>
		</td>
	</tr>
</table>
</div>
<P>
<!-- kpi列表-->
<div class="list-title">
	${eoms:a2u(' 评估指标列表')}
</div>
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="kpi-list-table">
	<tr class="header" height="30">
		<td width="50%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.kpiName"/>
		</td>
		<td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.dataSource"/>
		</td>
		<td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.dataType"/>
		</td>
		<td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.statictsCycle"/>
		</td>
		<!--td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.writeManner"/>
		</td-->
		<td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.unit"/>
		</td>
		<td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.isImpersonality"/>
		</td>
	</tr>
<logic:iterate id="it" name="it">
	<tr height="30">
		<td>
			<bean:define id="dictId" name="it" property="itemType" />
			<a href="editTawSupplierkpiItem.do?method=view&dictId=<%=dictId%>&fromTemplateAss=1&auditingType=yishenhe ">
				<bean:write name="it" property="kpiName"/>
			</a>
		</td>
		<td>
			<eoms:dict key="dict-supplierkpi" dictId="dataSource" itemId="${it.dataSource}" beanId="id2nameXML" />
		</td>
		<td>
			<eoms:dict key="dict-supplierkpi" dictId="dataType" itemId="${it.dataType}" beanId="id2nameXML" />
		</td>
		<td>
			<eoms:dict key="dict-supplierkpi" dictId="statictsCycle" itemId="${it.statictsCycle}" beanId="id2nameXML" />
		</td>
		<!--td>
			<bean:write name="it" property="id2writeManner"/>
		</td-->
		<td>
			<eoms:dict key="dict-supplierkpi" dictId="unit" itemId="${it.unit}" beanId="id2nameXML" />
		</td>
		<td>
			<eoms:dict key="dict-supplierkpi" dictId="isImpersonality" itemId="${it.isImpersonality}" beanId="id2nameXML" />
		</td>
</logic:iterate>
</table>
</div>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>