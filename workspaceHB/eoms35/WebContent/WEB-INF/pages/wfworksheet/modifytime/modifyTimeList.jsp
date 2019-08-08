<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<content tag="heading"><bean:message bundle="modifytimeForm" key="modifytimeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url
                   value="/sheet/modifyTime/editModifyTime.do?isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"/>'"
           value="<fmt:message key="button.add"/>"/>

</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<display:table name="modifytimeList" cellspacing="0" cellpadding="0"
               id="modifytimeList" pagesize="25" class="table modifytimeList"
               export="true" requestURI="" sort="list" size="resultSize">

    <display:column property="beginTime" sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('变更开始时间')}"/>

    <display:column property="endTime" sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('变更结束时间')}"/>

    <display:column property="functionary" sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('负责人')}"/>

    <display:column sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('地域')}">
        <eoms:id2nameDB id="${modifytimeList.local}" beanId="tawSystemAreaDao"/>
    </display:column>

    <display:column property="metNet" sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('变更网元')}"/>

    <display:column sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('专业(一级)')}">
        <eoms:id2nameDB id="${modifytimeList.specialtyOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('专业(二级)')}">
        <eoms:id2nameDB id="${modifytimeList.specialtyTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('专业(三级)')}">
        <eoms:id2nameDB id="${modifytimeList.specialtyThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column sortable="true" headerClass="sortable" property="type"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('变更类型')}">
    </display:column>

    <display:column property="title" sortable="true" headerClass="sortable"
                    url="/sheet/modifyTime/editModifyTime.do?method=xedit&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}"
                    paramId="id" paramProperty="id"
                    title="${eoms:a2u('变更标题')}"/>

    <display:setProperty name="export.rtf" value="false"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>

</display:table>

<c:if test="${isAdd==1}">
    <c:out value="${buttons}" escapeXml="false"/>
</c:if>


<%@ include file="/common/footer_eoms.jsp" %>

