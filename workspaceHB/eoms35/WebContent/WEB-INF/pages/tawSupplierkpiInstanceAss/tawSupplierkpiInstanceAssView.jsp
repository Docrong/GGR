<%@ page language="java" import="java.util.*,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String specialType = request.getAttribute("specialType").toString();
String serviceType = request.getAttribute("serviceType").toString();
String assessAttitude = request.getAttribute("assessAttitude").toString();
String degree = request.getAttribute("degree").toString();
String month = request.getAttribute("month").toString();
List supplierTitle = (List) request.getAttribute("supplierTitle");
%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
	colorRows('instance-list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiInstanceAssForm',vbtn:'method.save'});
})
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
<div class="list-title">
	<fmt:message key="tawSupplierkpiInstanceAssDetail.heading"/>
</div>
</fmt:bundle>

<html:form action="saveTawSupplierkpiInstanceAss" method="post" styleId="tawSupplierkpiInstanceAssForm"> 
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30">
		<td width="15%">
			${eoms:a2u('服务类型')}
		</td>
		<td width="35%">
			<B><eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao" /></B>
		</td>
		<td width="15%">
			${eoms:a2u('专业类型')}
		</td>
		<td width="35%">
			<B><eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao" /></B>
		</td>
	</tr>
	<tr height="30">
		<td>
			${eoms:a2u('月份')}
		</td>
		<td>
			<B><%=month%></B>
		</td>
		<td>
			${eoms:a2u('满意度')}
		</td>
		<td width="35%">
			<B><eoms:dict key="dict-supplierkpi" dictId="degree" 
      			itemId="<%=degree%>" beanId="id2nameXML" /></B>
		</td>
	</tr>
	<tr height="60">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessAttitude"/>
		</td>
		<td colspan="3">
			<B><%=assessAttitude%></B>
		</td>
	</tr>
</table>
</div>

<p>
<div class="list-title">
	${eoms:a2u('已填写实例')}
</div>

<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="instance-list-table">

<%if (supplierTitle.size() > 1) {%>
	<tr height="60" class="header">
<%for (int k = 0; k < supplierTitle.size(); k++) {%>
		<td width="10%">
			<%=supplierTitle.get(k).toString()%>
		</td>
<%}%>
	</tr>
	
<%
List rowList = (List)request.getAttribute("rowList");
%>
<%for (int i = 0; i < rowList.size(); i++) {
	List contentList = (List)rowList.get(i);%>
		<tr class="normal">
	<%
	  for (int j = 0; j < contentList.size(); j++) {
	  	TawSupplierkpiInstance instance = (TawSupplierkpiInstance) contentList.get(j);
	%>
			<td>
				<%if (j > 0 && !"---".equals(instance.getExamineContent())) {%>
				<a href="editInstance.do?method=viewInstance&id=<%=instance.getId()%>&specialType=<%=specialType%>&autingState=yishenhe"><%=instance.getExamineContent()%></a>
				<%} else {%>
				<%if (j == 0) {%>
					<%=instance.getKpiName()%>
				<%} else {%>
				<%=instance.getExamineContent()%>
				<%}}%>
			</td>
	<%}%>
		</tr>
<%}%>

<%} else {%>
	<tr height="60" class="header">
		<td width="10%">
			${eoms:a2u('没有供应商定制该专业的评估指标')}
		</td>
	</tr>
<%}%>
	
</table>
</div>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>