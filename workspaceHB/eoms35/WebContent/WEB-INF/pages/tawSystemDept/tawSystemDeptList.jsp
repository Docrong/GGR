<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript">
    Try.these(
      function(){parent.webFXTreeHandler.all["${lastEditId}"].parentNode.reload()},
      function(){parent.webFXTreeHandler.all["${lastNewId}"].reload()}
    );
</script>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/dept/editTawSystemDept.do"/>'"
        value="<fmt:message key="button.add"/>"/>
      <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/dept/editTawSystemDept.do?method=searchExits"/>'"
        value="<fmt:message key="button.searchAll"/>"/>
      
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemDeptList" cellspacing="0" cellpadding="0"
    id="tawSystemDeptList" pagesize="25" class="table tawSystemDeptList"
    export="true" requestURI="" sort="list">

 


   <display:column property="deptId" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptId"/>
         
    <display:column property="deptName" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptName"/>
         
     <display:column property="deptmanager" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptmanager"/>
         
    <display:column property="deptemail" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptemail"/>

    <display:column property="deptfax" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptfax"/>

    <display:column property="deptmobile" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptmobile"/>


    <display:column property="deptphone" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptphone"/>

    <display:column property="deptType" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.deptType"/>

    <display:column property="operremoteip" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.operremoteip"/>

    <display:column property="opertime" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.opertime"/>

    <display:column property="operuserid" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.operuserid"/>

    <display:column property="parentDeptid" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.parentDeptid"/>

    <display:column property="regionflag" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.regionflag"/>

<display:column property="tmporaryManager" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.tmporaryManager"/>
         
    <display:column property="tmporarybegintime" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.tmporarybegintime"/>

    <display:column property="tmporarystopTime" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.tmporarystopTime"/>

    <display:column property="updatetime" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.updatetime"/>
         
<display:column property="remark" sortable="true" headerClass="sortable"
        url="/dept/editTawSystemDept.do" paramId="id" paramProperty="id"
         titleKey="tawSystemDeptForm.remark"/>
         
    <display:setProperty name="paging.banner.item_name" value="tawSystemDept"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemDepts"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemDeptList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>