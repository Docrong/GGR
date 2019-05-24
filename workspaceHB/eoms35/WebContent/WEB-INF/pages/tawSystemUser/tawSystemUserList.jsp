<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemUserList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sysuser/editTawSystemUser.do"/>'"
        value="<fmt:message key="button.add"/>"/>

 <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sysuser/editTawSystemUser.do?method=searchExits"/>'"
        value="<fmt:message key="button.searchAlluser"/>"/>
    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
        
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemUserList" cellspacing="0" cellpadding="0"
    id="tawSystemUserList" pagesize="25" class="table tawSystemUserList"
    export="true" requestURI="" sort="list">

    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.userid"/>

    <display:column property="username" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.username"/>
      
      <display:column property="password" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.pwd"/>
         
    <display:column property="cptroomname" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.cptroomname"/>

    <display:column property="deptname" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.deptname"/>

    <display:column property="email" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.email"/>

    <display:column property="familyaddress" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.familyaddress"/>

    <display:column property="fax" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.fax"/>

    <display:column property="mobile" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.mobile"/>
         
    <display:column property="phone" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.phone"/>
         
    <display:column property="operuserid" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.operuserid"/>

    <display:column property="sex" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.sex"/>

    <display:column property="savetime" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.savetime"/>

    <display:column property="updatetime" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.updatetime"/>
         
   <display:column property="operremotip" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.operremotip"/>
 <display:column property="remark" sortable="true" headerClass="sortable"
        url="/sysuser/editTawSystemUser.do" paramId="id" paramProperty="id"
         titleKey="tawSystemUserForm.remark"/>
         
  
    <display:setProperty name="paging.banner.item_name" value="tawSystemUser"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemUsers"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemUserList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>