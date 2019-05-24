<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="com.boco.eoms.duty.model.TawRmPlanContent"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmPlanContentDetail.title"/></title>
<content tag="heading">
<fmt:message key="tawRmPlanContentDetail.heading"/>
</content>
<%
	TawRmPlanContent tawRmPlanContent=(TawRmPlanContent)request.getAttribute("tawRmPlanContent");
	ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
	String deptName=mgr.id2Name(tawRmPlanContent.getDeptId());
	String monthplanName=request.getParameter("monthplanName");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	String month=request.getParameter("month");
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmPlanContent" method="post" styleId="tawRmPlanContentForm"> 
<ul>

		<html:hidden property="monthplanName" />
		<html:hidden property="roomId" />
		<html:hidden property="startTime" />
		<html:hidden property="endTime" />	
		<html:hidden property="month" />
    <!--表示对所有的域有�? -->
		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.monthplanName" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmPlanContent.getMonthplanName() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.month" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmPlanContent.getMonth() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.content" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmPlanContent.getContent() %>
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
					<eoms:dict key="dict-plancontent" dictId="executeFlag" itemId="<%=tawRmPlanContent.getExecuteFlag()%>" beanId="id2nameXML" />			
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmPlanContentForm.createTime" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmPlanContent.getCreateTime()%>		
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<!-- <input type = "button" value='${eoms:a2u('返回')}' class="button"  onclick="javascript:history.back();"> -->
	        		<html:submit styleClass="button" property="method.searchList2">
	           			<fmt:message key="button.back"/>
	       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>