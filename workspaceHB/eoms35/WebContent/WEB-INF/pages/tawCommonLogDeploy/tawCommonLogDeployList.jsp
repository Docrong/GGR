<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonLogDeployList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/log/editTawCommonLogDeploy.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonLogDeployList" cellspacing="0" cellpadding="0"
    id="tawCommonLogDeployList" pagesize="25" class="table tawCommonLogDeployList"
    export="true" requestURI="" sort="list">
<display:setProperty name="export.pdf" value="true"/>
     <display:column property="userid" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />USERID
         
 <display:column property="modelid" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />MODELID

    <display:column property="modelname" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
       />MODELNAME
         
         <display:column property="operid" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />OPERID

    <display:column property="opername" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />OPERNAME
         
         
          <display:column property="operdesc" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />OPERDESC
        
         <display:column property="isdebug" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />ISDEBUG
         
         
          <display:column property="savetype" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />SAVETYPE
         
    <display:column property="filesavepath" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
       />FILESAVEPATH

 <display:column property="filecutsize" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />FILECUTSIZE

    <display:column property="noteremark" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogDeploy.do" paramId="id" paramProperty="id"
        />NOTEREMARK

    <display:setProperty name="paging.banner.item_name" value="tawCommonLogDeploy"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonLogDeploys"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonLogDeployList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>