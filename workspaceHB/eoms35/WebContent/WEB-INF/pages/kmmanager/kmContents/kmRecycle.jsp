<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript">
  function deleteConfirm()
	{
	   if(confirm('确定要删除吗？'))
	{
		return true;
		}else
		return false;
		}
	function clearConfirm()
	{
	  if(confirm('确定清空回收站吗？'))
	  {
	  	return true;
	  }else
	  	return false;
	}
	function changeAction()
	{	
		var revivifyButton=document.getElementById("recyclefrm");
		revivifyButton.action="kmContentss.do?method=revivify";
		revivifyButton.submit();
	}
</script>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
			<div class="header center"><fmt:message key="kmContents.recycle" />
			<img src="${app}/images/icons/icon.gif"/>
			</div>
	</caption>
</table>	
<table width="20%" cellspacing="0" cellpadding="0">
	
	<tr>
	<td>
		<html:form action="/kmContentss.do?method=recycleDelAll" method="post" styleId="kmContentsForm">
			   <input type="submit" ${disbutton} value="<fmt:message key="kmContents.clearup"/>"  class="btn" id="clearbtn" onclick="return clearConfirm()"/>
		</html:form>
		</td>
		<td>
		<html:form action="/kmContentss.do?method=revivifyAll" method="post" styleId="kmContentsForm">
				<input type="submit" ${disbutton} value="<fmt:message key="kmContents.revivifyAll"/>" class="btn"/>
		</html:form>
		</td>
	</tr>
</table></br>
<form id="recyclefrm" action="/kmContentss.do?method=recycleDel" method="post" styleId="kmContentsForm">
    <display:table name="kmContentsRecycleList" cellspacing="0" cellpadding="0"
		id="kmContentsRecycleList" pagesize="${pageSize}" class="table kmContentsRecycleList"
		export="false"
		requestURI="${app}/kmmanager/kmContentss.do?method=reycle"
		sort="list" partialList="true" size="resultSize">

	
	<display:column 
			headerClass="sortable" titleKey="kmContents.contentTitle" paramId="id" paramProperty="id">
		${kmContentsRecycleList.contentTitle}
		
	</display:column>
	<display:column 
			headerClass="sortable" titleKey="kmContents.createUser" paramId="id" paramProperty="id">
		${kmContentsRecycleList.createUser}
		
	</display:column>
	<display:column  headerClass="sortable" titleKey="kmContents.choose" paramId="id" paramProperty="id">
		<input type="submit" value="<fmt:message key="kmContents.deleted"/>" class="btn" onclick="return deleteConfirm()"/>
		<img src="${app}/images/icons/list-delete.gif"/> &nbsp;&nbsp;&nbsp;&nbsp;  
		<input type="button" id="revivify" value="<fmt:message key="kmContents.revivify"/>" class="btn" onclick="return changeAction()"/>
		<img src="${app}/images/icons/refresh.gif"/>
		<input type="hidden" value="${kmContentsRecycleList.themeId}" name="themeId" id="themeId"/>
	</display:column>
	<display:setProperty name="export.Excel" value="true"/>
	</display:table>
</form>

</fmt:bundle>
	
	
<%@ include file="/common/footer_eoms.jsp"%>