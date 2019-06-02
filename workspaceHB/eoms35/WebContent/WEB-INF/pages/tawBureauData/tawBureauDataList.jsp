<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
 

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/bureaudata/tawBureauDatas.do"/>'"
        value="<fmt:message key="button.add"/>" class="button"/>
 
</c:set>
 
<c:out value="${buttons}" escapeXml="false"/>
<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 
<display:table name="tawBureauDataList" cellspacing="0" cellpadding="0"
    id="tawBureauDataList" pagesize="15" class="table tawBureauDataList"
    export="true" requestURI="${app}/bureaudata/tawBureauDatas.do?method=xsearch" sort="external" partialList="true" size="resultSize">

   <display:column property="cruser" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.cruser"/>
         
    <display:column property="crusdata" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.crusdata"/>
   

    <display:column property="bigNet" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.bigNet"/>
    <display:column property="net" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.net"/>

    <display:column property="factory" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.factory"/>
         

    <display:column property="data" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.data"/>
         
    <display:column property="content" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.content"/>

    <display:column property="type" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.type"/>

 

    <display:column property="title" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.title"/>

   



    <display:column property="producer" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.producer"/>

               
    <display:column property="auditman" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.auditman"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/bureaudata/tawBureauDatas.do?method=edit" paramId="id" paramProperty="id"
         titleKey="tawBureauDataForm.remark"/>
    <display:setProperty name="paging.banner.item_name" value="tawBureauData"/>
    <display:setProperty name="paging.banner.items_name" value="tawBureauDatas"/>
</display:table>

<%@ include file="/common/footer_eoms.jsp"%>

