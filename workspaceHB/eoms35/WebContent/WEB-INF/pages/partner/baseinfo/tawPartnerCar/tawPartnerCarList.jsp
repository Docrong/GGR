<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<script language="javaScript" type="text/javascript"
        src="${app}/scripts/module/partner/ajax.js"></script>
<%@ page language="java"
         import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;" %>

<c:if test="${in!=null}">
    <%@ include file="/WEB-INF/pages/partner/baseinfo/hrefSearch.jsp" %>
</c:if>
<c:choose>
    <c:when test="${isCity=='yes'}">
        <c:set var="buttons">
            <input type="button" style="margin-right: 5px"
                   onclick="location.href='<html:rewrite page='/tawPartnerCars.do?method=add&nodeId=${nodeId}'/>'"
                   value="<fmt:message key="button.add"/>"/>
        </c:set>
    </c:when>
</c:choose>
<fmt:bundle basename="config/applicationResources-partner-baseinfo">
    <html:form action="tawPartnerCars.do?method=xquery" method="post"
               styleId="tawPartnerCarForm">
        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="tawPartnerCar.list.heading"/></div>
            </caption>
            <tr>
                <td class="label">
                    <fmt:message key="tawPartnerCar.car_number"/>
                </td>
                <td class="content">
                    <html:text property="car_number" styleId="car_number"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.car_number}"/>
                </td>
                <td class="label">
                    <fmt:message key="tawPartnerCar.piece"/>
                </td>
                <td class="content">
                    <eoms:comboBox name="piece" id="piece" initDicId="11210"
                                   defaultValue='${tawPartnerCarForm.piece}'/>
                        <%--<html:text property="piece" styleId="piece"
                                    styleClass="text medium"
                                    alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.piece}" />
                    --%></td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawPartnerCar.area_id"/>
                </td>
                <td class="content">
                    <html:select property="area_id" styleId="area_id" size="1" onchange="changeDep();">
                            <%
							List listId = (List) request.getAttribute("listId");
							List listName = (List) request.getAttribute("listName");
							TawPartnerCarForm fm = (TawPartnerCarForm) request
									.getAttribute("tawPartnerCarForm");
							String nodeId = fm.getArea_id();
							if(nodeId==null){
							nodeId=" ";
							}
							for (int i = 0; i < listId.size(); i++) {
								if (nodeId.equals(listId.get(i))) {
						%>
                    <option value="<%=listId.get(i)%>" selected>
                        <%=listName.get(i)%>
                    </option>
                            <%
						} else {
						%>
                    <option value="<%=listId.get(i)%>">
                        <%=listName.get(i)%>
                    </option>
                            <%
							}
							}
						%>
                    </html:select>
                        <%--<html:text property="area_id" styleId="area_id"
                                    styleClass="text medium"
                                    alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.area_id}" />
                    </td>
                            --%>
                <td class="label">
                    <fmt:message key="tawPartnerCar.dept_id"/>
                </td>
                <td class="content">
                    <html:select property="dept_id" styleId="dept_id" size="1">
                    </html:select>
                        <%--<html:text property="dept_id" styleId="dept_id"
                                    styleClass="text medium"
                                    alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.dept_id}" />
                    --%></td>

            </tr>
            <tr>
                <input type="hidden" name="nodeId" value="<%=request.getAttribute("nodeId") %>">
                <td colspan="4" align="center">
                    <html:submit styleClass="button" property="method.query">
                    查询
                    </html:submit>
            </tr>
        </table>
    </html:form>

    <display:table name="tawPartnerCarList" cellspacing="0" cellpadding="0"
                   id="tawPartnerCarList" pagesize="${pageSize}" class="table tawPartnerCarList"
                   export="false" decorator="com.boco.eoms.partner.baseinfo.util.PartnerCarDecorator"
                   requestURI="${app}/partner/baseinfo/tawPartnerCars.do?method=search"
                   sort="list" partialList="true" size="resultSize">

        <display:column property="car_number" sortable="true"
                        headerClass="sortable" titleKey="tawPartnerCar.car_number"
                        href="${app}/partner/baseinfo/tawPartnerCars.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="dept_id" sortable="true"
                        headerClass="sortable" titleKey="tawPartnerCar.dept_id"
                        href="${app}/partner/baseinfo/tawPartnerCars.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="area_id" sortable="true"
                        headerClass="sortable" titleKey="tawPartnerCar.area_id"
                        href="${app}/partner/baseinfo/tawPartnerCars.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="start_time" sortable="true" format="{0,date,yyyy-mm-dd HH:MM:SS}"
                        headerClass="sortable" titleKey="tawPartnerCar.start_time"
                        href="${app}/partner/baseinfo/tawPartnerCars.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column sortable="true" property="piece" href="${app}/partner/baseinfo/tawPartnerCars.do?method=edit"
                        paramId="id" paramProperty="id"
                        headerClass="sortable" titleKey="tawPartnerCar.piece"/>

        <display:column property="remark" sortable="true"
                        headerClass="sortable" titleKey="tawPartnerCar.remark"
                        href="${app}/partner/baseinfo/tawPartnerCars.do?method=edit" paramId="id" paramProperty="id"/>

        <display:setProperty name="paging.banner.item_name" value="tawPartnerCar"/>
        <display:setProperty name="paging.banner.items_name" value="tawPartnerCars"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>
<script type="text/javascript">

    var id = document.getElementById("area_id").value;
    var url = "<%=request.getContextPath()%>/partner/baseinfo/tawPartnerCars.do?method=changeDep2&id=" + id;
    var fieldName = "dept_id";
    var deptId = "<%=((TawPartnerCarForm) request
							.getAttribute("tawPartnerCarForm")).getDept_id()%>";
    changeList(url, fieldName, deptId);

    function changeDep() {
        var id = document.getElementById("area_id").value;
        var url = "<%=request.getContextPath()%>/partner/baseinfo/tawPartnerCars.do?method=changeDep2&id=" + id;
        var fieldName = "dept_id";
        changeList(url, fieldName, "");
    }

</script>
<%@ include file="/common/footer_eoms.jsp" %>