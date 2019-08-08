<%@ page language="java"
         import="java.util.*, com.boco.eoms.commons.system.role.model.TawSystemRole, com.boco.eoms.base.util.StaticMethod" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<%
    String fromTemplateAss = StaticMethod.null2String(request.getAttribute("fromTemplateView").toString());
    String specialType = StaticMethod.null2String(request.getAttribute("specialType").toString());
    String url = "/supplierkpi/viewTawSupplierkpiTemplate.do?method=view&specialType=" + specialType;
%>

<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
        //init Form validation and styles
        //valider({form:'tawSupplierkpiItemForm',vbtn:'method.save'});
    })
</script>

<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <div class="list-title">
        <fmt:message key="tawSupplierkpiItemDetail.heading"/>
    </div>
</fmt:bundle>
<html:form action="saveTawSupplierkpiItem" method="post" styleId="tawSupplierkpiItemForm">
    <div class="list">
        <table cellspacing="0" cellpadding="0" border="0" id="list-table">
            <tr height="40">
                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.kpiName"/>
                </td>
                <td colspan="3">
                    <bean:write name="tawSupplierkpiItemForm" property="kpiName" scope="request"/>
                </td>
            </tr>
            <tr height="40">
                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.dataSource"/>
                </td>
                <td width="30%">
                    <eoms:dict key="dict-supplierkpi" dictId="dataSource" itemId="${tawSupplierkpiItemForm.dataSource}"
                               beanId="id2nameXML"/>
                </td>

                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.dataType"/>
                </td>
                <td width="30%">
                    <eoms:dict key="dict-supplierkpi" dictId="dataType" itemId="${tawSupplierkpiItemForm.dataType}"
                               beanId="id2nameXML"/>
                </td>

            </tr>
            <tr height="40">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.isImpersonality"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="isImpersonality"
                               itemId="${tawSupplierkpiItemForm.isImpersonality}" beanId="id2nameXML"/>
                </td>

                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.unit"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="unit" itemId="${tawSupplierkpiItemForm.unit}"
                               beanId="id2nameXML"/>
                </td>

            </tr>
            <tr height="40">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.assessMoment"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="assessMoment"
                               itemId="${tawSupplierkpiItemForm.assessMoment}" beanId="id2nameXML"/>
                </td>

                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.statictsCycle"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="statictsCycle"
                               itemId="${tawSupplierkpiItemForm.statictsCycle}" beanId="id2nameXML"/>
                </td>

            </tr>
            <!--tr height="40">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.writeManner"/>
		</td>
		<td colspan="3">
			<bean:write name="tawSupplierkpiItemForm" property="writeManner" scope="request"/>
		</td>
	</tr-->
            <tr height="40">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.preActor"/>
                </td>
                <td colspan="3">
                    <bean:write name="tawSupplierkpiItemForm" property="preActor" scope="request"/>
                </td>
            </tr>
            <tr height="40">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.assessContent"/>
                </td>
                <td colspan="3">
                    <bean:write name="tawSupplierkpiItemForm" property="assessContent" scope="request"/>
                </td>
            </tr>
            <tr height="40">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.assessNote"/>
                </td>
                <td colspan="3">
                    <bean:write name="tawSupplierkpiItemForm" property="assessNote" scope="request"/>
                </td>
            </tr>

            <%if ("1".equals(fromTemplateAss)) { %>
            <tr height="30">
                <td colspan="4">
                    <input type="button" class="btn" value="${eoms:a2u('返回')}"
                           onclick="window.location.replace('${app}<%=url %>');"/>
                </td>
            </tr>
            <%} %>
        </table>
    </div>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>