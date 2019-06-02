<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<title><fmt:message key="tawSystemUserDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemUserDetail.heading"/></content>

<html:form action="saveTawSystemUser" method="post" styleId="tawSystemUserForm" styleClass="required-validate"> 
<c:set var="f">tawSystemUserForm</c:set>
<ul>
<html:hidden property="id"/>
<html:hidden property="updatetime"/>
<html:hidden property="savetime"/>
<html:hidden property="operremotip"/>
<html:hidden property="userdegree"/>
<html:hidden property="deleted"/>
<html:hidden property="operuserid"/>
<html:hidden property="cptroomname"/>
<html:hidden property="deptid"/>
<html:hidden property="sex"/>
	<html:hidden property="userid"/>
	<html:hidden property="username"/>
	<html:hidden property="password"/>
	<!-- 
		<li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium required max-length-100" />

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.username"/>
        <html:errors property="username"/>
        <html:text property="username" styleId="username" styleClass="text medium required max-length-100" />
    </li>
     -->
       <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.passwd"/>
        <html:errors property="passsword"/>
        <html:text property="newPassword" styleId="newPassword" styleClass="text medium required max-length-100" value=""/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.email"/>
        <html:errors property="email"/>
        <html:text property="email" styleId="email" styleClass="text medium required max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.familyaddress"/>
        <html:errors property="familyaddress"/>
        <html:text property="familyaddress" styleId="familyaddress" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.fax"/>
        <html:errors property="fax"/>
        <html:text property="fax" styleId="fax" styleClass="text medium  max-length-100"/>

    </li>

	 <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.mobile"/>
        <html:errors property="mobile"/>
        <html:text property="mobile" styleId="mobile" styleClass="text medium required max-length-100"/>

    </li>
   

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.phone"/>
        <html:errors property="phone"/>
        <html:text property="phone" styleId="phone" styleClass="text medium  max-length-100"/>

    </li>

    
    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium max-length-100"/>

    </li>
    
    <!-- role list -->
    <div id="role">
		<fieldset>
			<legend>
				<eoms:label styleClass="desc" key="tawSystemUserForm.myRoles"/>
			</legend>
			<table>
			<c:forEach items="${requestScope.roleList}" var="roles">
				<tr>
					<td width="40%">
					${roles.roleName}<br/>
					</td>
					<td width="60%">
					<c:forEach items="${requestScope.subRoleList}" var="subRoles">
						<c:if test="${subRoles.roleId==roles.roleId}">
						${subRoles.subRoleName}<br/>
						</c:if> 
					</c:forEach>
					</td>
				</tr>
			</c:forEach>
			</table>
		</fieldset>
		<br/>
	</div>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.editPersonal" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
    </li>
    
</ul>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>