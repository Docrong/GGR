<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmVisitRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmVisitRecordDetail.heading"/></content>

<%
	String recordId = (String) request.getParameter("id");
	String leftTime = (String) request.getAttribute("leftTime");
	String visitorName=request.getParameter("tmpVisitorName");
	String company=request.getParameter("tmpCompany");
	String visitTime=request.getParameter("tmpVisitTime");
	String tmpLeftTime=request.getParameter("tmpLeftTime");
	String receiver=request.getParameter("tmpReceiver");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
%>

<script language="javascript">

function confirmDelete(){
if ( confirm('${eoms:a2u("是否要删除此访问记录")}') ){
	return true;
	}else{
	return false;
	}
}

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmVisitRecordForm'});
});
</script>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmVisitRecord" method="post" styleId="tawRmVisitRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpVisitorName" />
		   <html:hidden property="tmpCompany" />
		   <html:hidden property="tmpVisitTime" />
		   <html:hidden property="tmpLeftTime" />
		   <html:hidden property="tmpReceiver" />
		   <html:hidden property="roomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />
		   <br>


		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.visitorName" />
				</td> 
				<td width="500" colspan="2">
				<%if(recordId==null){%>
					<html:text property="visitorName" styleId="visitorName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入来访者姓名')}'" />
				<%}else{%>
					<html:text property="visitorName" styleId="visitorName"
						styleClass="text medium" readonly="true"/>
				<%}%>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.company" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="company" beanId="selectXML" isQuery="false" selectId="company" alt="allowBlank:false" defaultId="${tawRmVisitRecordForm.company}"/>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.visitTime" />
				</td> 
				<td width="500" colspan="2">
				<%if(recordId==null){%>
					<html:text property="visitTime" styleId="visitTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入来访时间')}'" 
						onclick="popUpCalendar(this, this);" readonly="true"/>
				<%}else{%>
					<html:text property="visitTime" styleId="visitTime"
						styleClass="text medium" readonly="true"/>
				<%}%>
				</td>
			</tr>
			<%if((recordId!=null)&&(leftTime==null||leftTime.equals(""))){%>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.leftTime" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="leftTime" styleId="leftTime"
						styleClass="text medium"
						onclick="popUpCalendar(this, this);" readonly="true"/>
				</td>
			</tr>
			<%}else if((recordId!=null)&&(leftTime!=null&&!leftTime.equals(""))){%>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.leftTime" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="leftTime" styleId="leftTime"
						styleClass="text medium" readonly="true"/>
				</td>
			</tr>
			<%}%>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.reason" />
				</td> 
				<td width="500" colspan="2">
				<%if(recordId==null){%>
					<html:textarea property="reason" styleId="reason"
						styleClass="text medium" rows="5" cols="30"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入来访事由')}'" />
				<%}else{%>
					<html:textarea property="reason" styleId="reason"
						styleClass="text medium" rows="5" cols="30" readonly="true"/>
				<%}%>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.receiver" />
				</td> 
				<td width="500" colspan="2">
				<%if(recordId==null){%>
					<html:text property="receiver" styleId="receiver"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入联系人')}'"/>
				<%}else{%>
					<html:text property="receiver" styleId="receiver"
						styleClass="text medium" readonly="true"/>
				<%}%>
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<%
					if(leftTime==null||leftTime.equals("")){
				%>
				<td>
					<html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            			<fmt:message key="button.save"/>
        			</html:submit>
				</td>
				<%
					} 
				%>
				<%
					if((recordId!=null)&&(leftTime==null||leftTime.equals(""))){
				%>
				<td>
					<html:submit styleClass="button" property="method.delete"
						onclick="bCancel=true; return confirmDelete()">
						<fmt:message key="button.delete" />
					</html:submit>
				</td>

				<%
				}
				%>
				
				<%
				if (recordId != null) {
				%>
				<td>
					<html:submit styleClass="button" property="method.searchList2">
		           		<fmt:message key="button.back"/>
		       		</html:submit>
				</td>
				<%
				}
				%>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>