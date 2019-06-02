<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
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
    id="tawWorkbenchMemoList" pagesize="${pageSize }" class="table tawWorkbenchMemoList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/workbench/memo/tawWorkbenchMemoNodes.do?method=searchList&folderPath=<%=str%>" 
    decorator="com.boco.eoms.workbench.memo.displaytag.support.MemoListDisplaytagDecorator" >


	<display:column property="title" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.title"/>
           
    <display:column property="content" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.content"/>

    <display:column property="userName" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.userid"/>

    <display:column property="creattime" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.creattime"/>

    <display:column property="level" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.level"/>

    <display:column property="sendflag" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.sendflag"/>

    <display:column property="reciever" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.reciever"/>

    <display:column property="sendmanner" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.sendmanner"/>

    

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
<%@ include file="/common/footer_eoms.jsp"%>