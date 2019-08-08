<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page language="java" import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;" %>
<script language="JavaScript" type="text/JavaScript" src="${app}/scripts/module/partner/ajax.js"></script>
<script type="text/javascript">
    var checkflag = "false";

    function chooseAll() {
        var objs = document.getElementsByName("checkbox11");
        if (checkflag == "false") {
            for (var i = 0; i < objs.length; i++) {
                objs[i].checked = "checked";
            }
            checkflag = "checked";
        } else if (checkflag == "checked") {
            for (var i = 0; i < objs.length; i++) {
                objs[i].checked = false;
            }
            checkflag = "false";
        }

    }

    function isChecked() {
        var objs = document.getElementsByName("checkbox11");
        document.forms[1].action = "${app}/partner/baseinfo/partnerUsers.do?method=remove";
        var flag = false;
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].checked == true) {
                flag = true;
                break;
            }
        }
        if (flag == true) {
            document.forms[1].submit();
            return true;
        } else if (flag == false) {
            alert("请选择删除项！");
            return false;
        }
    }
</script>

<c:if test="${in==null}">
    <c:set var="buttons">
        <input type="button" style="margin-right: 5px" class="btn"
               onclick="location.href='<html:rewrite page='/partnerUsers.do?method=add&nodeId=${treeNodeId }'/>'"
               value="<fmt:message key="button.add"/>"/>

        <html:button style="margin-right: 5px" property="button1" onclick="isChecked()" styleClass="btn">
            <fmt:message key="button.delete"/>
        </html:button>
    </c:set>
</c:if>

<fmt:bundle basename="config/applicationResources-partner-baseinfo">

    <html:form action="partnerUsers.do?method=search&in=${in }&nodeId=${nodeId}" method="post"
               styleId="partnerUserForm">
        <c:if test="${in!=null}">
            <%@ include file="/WEB-INF/pages/partner/baseinfo/hrefSearch.jsp" %>
        </c:if>
        <input type="hidden" name="treeNodeId" value="${treeNodeId }">
        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="partnerUser.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="partnerUser.name"/>
                </td>
                <td class="content">
                    <input type="text" name="nameSearch" id="name"
                           class="text medium"
                           alt="allowBlank:false,vtext:''"/>
                </td>

                <td class="label">
                    <fmt:message key="partnerUser.areaName"/>
                </td>
                <td class="content">
                    <c:if test="${in!=null}">
                        <input type="text" name="areaNameSearch" id="areaName"
                               class="text medium"
                               alt="allowBlank:true,vtext:''" value="${requestScope.areaName }"/>
                    </c:if>
                    <c:if test="${in==null}">
                        <html:select property="areaId" styleId="areaId" size="1" onchange="changeDep();">
                            <%
                                List listId = (List) request.getAttribute("listId");
                                List listName = (List) request.getAttribute("listName");
                                PartnerUserForm fm = (PartnerUserForm) request.getAttribute("partnerUserForm");
                                String nodeId = fm.getAreaId();
                                for (int i = 0; i < listId.size(); i++) {
                                    if (nodeId.equals(listId.get(i))) {
                            %>
                            <option value="<%=listId.get(i) %>" selected>
                                <%=listName.get(i) %>
                            </option>
                            <%} else { %>
                            <option value="<%=listId.get(i) %>">
                                <%=listName.get(i) %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </html:select>
                    </c:if>
                </td>

            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="partnerUser.deptName"/>
                </td>
                <td class="content">
                    <c:if test="${in!=null}">
                        <input type="text" name="deptNameSearch" id="deptName"
                               class="text medium"
                               alt="allowBlank:true,vtext:''" value="${requestScope.deptName }"/>
                    </c:if>
                    <c:if test="${in==null}">
                        <html:select property="deptId" styleId="deptId" size="1">
                        </html:select>
                    </c:if>
                </td>

                <td class="label">
                    <fmt:message key="partnerUser.phone"/>
                </td>
                <td class="content">
                    <input type="text" name="phoneSearch" id="phone"
                           class="text medium"
                           alt="allowBlank:true,vtext:''"/>
                </td>
            </tr>
            <tr>


                <td class="label">
                    <fmt:message key="partnerUser.email"/>
                </td>
                <td class="content">
                    <input type="text" name="emailSearch" id="email"
                           class="text medium"
                           alt="allowBlank:true,vtext:''"/>
                </td>
            </tr>
        </table>

        <table align="center">
            <tr>
                <td>
                    <input type="submit" class="btn" value="查询"/>

                    <input type="reset" class="btn" value="重置">
                </td>
            </tr>
        </table>
    </html:form>

    <html:form action="partnerUsers" method="post" styleId="partnerUserForm">
        <input type="hidden" name="treeNodeId" value="${treeNodeId }">
        <content tag="heading">
            <fmt:message key="partnerUser.list.heading"/>
        </content>

        <display:table name="partnerUserList" cellspacing="0" cellpadding="0"
                       id="partnerUserList" pagesize="${pageSize}" class="table partnerUserList"
                       export="false"
                       requestURI="${app}/partner/baseinfo/partnerUsers.do?method=search"
                       sort="list" partialList="true" size="resultSize">

            <display:column title="<input type='checkbox' onclick='javascript:chooseAll();'>">
                <input type="checkbox" name="checkbox11" value="<c:out value='${partnerUserList.id}'/>">
            </display:column>

            <display:column property="name" sortable="true"
                            headerClass="sortable" titleKey="partnerUser.name"
                            href="${app}/partner/baseinfo/partnerUsers.do?method=edit" paramId="id" paramProperty="id"/>

            <display:column sortable="true"
                            headerClass="sortable" titleKey="partnerUser.sex" paramId="id" paramProperty="id">
                <eoms:id2nameDB id="${partnerUserList.sex}" beanId="tawSystemDictTypeDao"/>
            </display:column>

            <display:column property="areaName" sortable="true"
                            headerClass="sortable" titleKey="partnerUser.areaName" paramId="id" paramProperty="id"/>

            <display:column property="personCardNo" sortable="true"
                            headerClass="sortable" titleKey="partnerUser.personCardNo" paramId="id" paramProperty="id"/>

            <display:column property="deptName" sortable="true"
                            headerClass="sortable" titleKey="partnerUser.deptName" paramId="id" paramProperty="id"/>

            <display:column property="phone" sortable="true"
                            headerClass="sortable" titleKey="partnerUser.phone" paramId="id" paramProperty="id"/>

            <display:column property="email" sortable="true"
                            headerClass="sortable" titleKey="partnerUser.email" paramId="id" paramProperty="id"/>

            <display:column sortable="true"
                            headerClass="sortable" titleKey="partnerUser.post">
                <eoms:id2nameDB id="${partnerUserList.post}" beanId="tawSystemDictTypeDao"/>
            </display:column>

            <display:setProperty name="paging.banner.item_name" value="partnerUser"/>
            <display:setProperty name="paging.banner.items_name" value="partnerUsers"/>
        </display:table>

        <c:out value="${buttons}" escapeXml="false"/>
    </html:form>
</fmt:bundle>
<script type="text/javascript">
    <c:if test="${in==null}">
    var id = null;
    if (document.getElementById("areaId") != null) {
        id = document.getElementById("areaId").value;
    }
    var url = "<%=request.getContextPath()%>/partner/baseinfo/partnerUsers.do?method=changeDep&id=" + id;
    var fieldName = "deptId";
    var deptId = "<%=((PartnerUserForm)request.getAttribute("partnerUserForm")).getDeptId()%>";
    changeList(url, fieldName, deptId);

    function changeDep() {
        var id = null;
        if (document.getElementById("areaId")) {
            id = document.getElementById("areaId").value;
        }
        var url = "<%=request.getContextPath()%>/partner/baseinfo/partnerUsers.do?method=changeDep&id=" + id;
        var fieldName = "deptId";
        changeList(url, fieldName, "");
    }

    </c:if>
</script>
<%@ include file="/common/footer_eoms.jsp" %>