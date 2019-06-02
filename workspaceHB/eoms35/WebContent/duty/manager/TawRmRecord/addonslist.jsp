<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.boco.eoms.duty.model.TawRmAddonsTable"%>

<% 	String typeId = request.getAttribute("typeId").toString();
%>
<script language="javascript">
  Ext.onReady(function(){
	colorRows('list-table');
  })
  
  var trueIcon = new Image();
  trueIcon.src = "../images/true.gif";
  var falseIcon = new Image();
  falseIcon.src = "../images/false.gif";

  function load(divID){
    var targetTR;
    targetTR = eval("document.all." + divID);

    if (targetTR.style.display!="block"){
      targetTR.style.display="block";
      event.srcElement.src = trueIcon.src;
    }
    else{
      targetTR.style.display="none";
      event.srcElement.src = falseIcon.src;
    }
  }

  function onAdd()
  {
    var id = <%=typeId%>;
    window.location.href="addnos.do?typeId="+id;
  }
 
  function onRemove(id){
      if (confirm("<bean:message key="modellist.title.warnRemove" />")==true){
        document.tawRmAddonsTableForm.id.value=id;
        document.tawRmAddonsTableForm.action="addonsmove.do?id="+id;
        document.tawRmAddonsTableForm.submit();
      }
  }

</script>
 
<form name="tawRmAddonsTableForm">
<input type="hidden" name="id">

<table class="listTable" id="list-table">
  <caption><bean:message key="modellist.title.formTitle" /></caption>
  
 
  <thead>
  <tr>
	<td class="label"><bean:message key="modellist.title.formName" /></td>
	<td class="label"><bean:message key="modellist.title.formCreater" /></td>
    <td class="label"><bean:message key="modellist.title.formCrtime" /></td>
    <td class="label"><bean:message key="modellist.title.formRoomName" /></td>
    <td class="label"><bean:message key="modellist.title.formEdit" /></td>
    <td class="label"><bean:message key="modellist.title.formdownlist" /></td>
    <td class="label"><bean:message key="modellist.title.formdownLoad" /></td>
    <td class="label"><bean:message key="modellist.title.formRemove" /></td>
    <%--<td><bean:message key="modellist.title.formDetail" /></td>
  --%>
  </tr>
  </thead>
  <tbody>
<%
	TawRmAddonsTable tawRmAddonsTable = null;
	List list = (List) request.getAttribute("AddonsList");
	for (Iterator it = list.iterator(); it.hasNext();) {
		tawRmAddonsTable = (TawRmAddonsTable) it.next();
 
%>
<input type="hidden" name="roomId" value='<%=tawRmAddonsTable.getRoomId()%>' />
<tbody>
	<td>
		<%=tawRmAddonsTable.getName()%>
	</td>
	<td>
		<%=tawRmAddonsTable.getCreatUser()%>
	</td>
	<td>
		<%=tawRmAddonsTable.getCreatTime()%>
	</td>
	<td>
		<%=tawRmAddonsTable.getRoomName()%>
	</td>
	<td>
		<%=tawRmAddonsTable.getRemark()%>
	</td>

	<td>
		<a href='<%=request.getContextPath()%>/duty/manager/ntko/showDetailother.jsp?path=<%=tawRmAddonsTable.getUrl()%>' > <img
				src="${app }/duty/images/an_xs.gif"> </a>
	</td>
	<td>
		<a href="download.do?id=<%=tawRmAddonsTable.getId()%>"> <img
				src="${app }/duty/images/new.gif"> </a>
	</td>
	<td>
		<a href="javascript:onRemove('<%=tawRmAddonsTable.getId()%>')"> <img
				src="${app }/images/icons/icon.gif" > </a>
	</td>
</tbody>
<%
}
%>
</table>
<br>
 
<input type="button"
	value="<bean:message key="modellist.title.btnAdd" />" class="button"
	onClick="onAdd();">

</form>

<%@ include file="/common/footer_eoms.jsp"%>


