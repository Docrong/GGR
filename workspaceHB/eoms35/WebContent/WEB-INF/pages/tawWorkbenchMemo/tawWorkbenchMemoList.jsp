<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style type="text/css">
  	body{background-image:none;}
</style>

<div class="list-title">
<fmt:message key="tawWorkbenchMemoDetail.heading" />
</div>
<div class="list">
<%
		String str = request.getParameter("folderPath").toString();
		%>   
		<input type="hidden" name="folderPath" value=<%=str%> />
 
<display:table name="tawWorkbenchMemoList" cellspacing="0" cellpadding="0"
    id="tawWorkbenchMemoList" pagesize="15" class="table tawWorkbenchMemoList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/workbench/memo/editTawWorkbenchMemo.do?method=search"  >

	<display:column property="title" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.title" href="${app}/workbench/memo/tawWorkbenchMemo.do?method=edit&folderPath=${folderPathStr}" paramId="id" paramProperty="id"/>
         
    <display:column property="content" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.content"/>

    <display:column property="userName" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.userid"/>

    <display:column property="creattime" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.creattime"/>
         
 
    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
<%@ include file="/common/footer_eoms.jsp"%>