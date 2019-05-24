<jsp:directive.page import="com.boco.eoms.operuser.util.OperuserConstants"/>
<jsp:directive.page import="com.boco.eoms.operuser.model.*"/>
<%@page import="java.util.List,java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>



<fmt:bundle basename="config/applicationResource-operuser">
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="operuser.form.heading"/></div>
	</caption>
	<tr>
		<td  class="label">
			<fmt:message key="operuser.name" />
		</td>
		<td class="content">
			${operuserForm.name}
		</td>
		
		
 
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.deptname" />
		</td>
		<td class="content">
			${operuserForm.deptname}
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.sex" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.sex}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.subarea" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.subarea}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.savetime" />
		</td>
		<td class="content">
			${operuserForm.savetime}
		</td>		
	</tr>
	
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.majortype" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.majortype}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.birthday" />
		</td>
		<td class="content">
			${operuserForm.birthday}
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.jobtype" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.jobtype}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.joblevele" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.joblevele}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.schoollevel" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.schoollevel}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.powerlevel" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.powerlevel}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.prizelevel" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.prizelevel}" beanId="tawSystemDictTypeDao" />
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.worklevel" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${operuserForm.worklevel}" beanId="tawSystemDictTypeDao" />
			
		</td>		
	</tr>
	
	<tr>	
		<td  class="label">
			<fmt:message key="operuser.remark" />
		</td>
		<td class="content">
			${operuserForm.remark}
		</td>		
	</tr>
	
</table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>