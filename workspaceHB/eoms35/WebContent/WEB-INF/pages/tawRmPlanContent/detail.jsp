<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.workplan.model.TawwpExecuteContent"%>
<%@ page import ="com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="com.boco.eoms.workplan.model.TawwpMonthPlan"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmPlanContentDetail.title"/></title>
<content tag="heading">
<fmt:message key="tawRmPlanContentDetail.heading"/>
</content>
<%
	TawwpExecuteContent tawwpExecuteContent=(TawwpExecuteContent)request.getAttribute("tawwpExecuteContent");
	ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
	//ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr)ApplicationContextHolder.getInstance().getBean("tawwpMonthMgr");
	TawwpMonthPlan tawwpMonthPlan=tawwpExecuteContent.getTawwpMonthPlan();
	String month=tawwpMonthPlan.getYearFlag()+"-"+tawwpMonthPlan.getMonthFlag();
	String deptName=mgr.id2Name(tawwpExecuteContent.getDeptId());
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmPlanContent" method="post" styleId="tawRmPlanContentForm"> 
<ul>

    <!--表示对所有的域有�? -->
		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.monthplanName" />
				</td> 
				<td width="500" colspan="2">
					<%=tawwpExecuteContent.getName() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.month" />
				</td> 
				<td width="500" colspan="2">
					<%=month %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.content" />
				</td> 
				<td width="500" colspan="2">
					<%=tawwpExecuteContent.getContent() %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.deptId" />
				</td> 
				<td width="500" colspan="2">
					<%=deptName%>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.excuteFlag" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="executeFlag" itemId="<%=tawwpExecuteContent.getExecuteFlag()%>" beanId="id2nameXML" />			
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
	        		<html:submit styleClass="button" property="method.edit">
	           			<fmt:message key="button.back"/>
	       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>