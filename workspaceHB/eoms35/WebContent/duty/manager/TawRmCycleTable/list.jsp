<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.boco.eoms.duty.model.TawRmCycleTable"%>

<% 	String roomId = request.getAttribute("roomId").toString();
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
  var id = <%=roomId%>;
    window.navigate("add.do?roomId="+id);
  }
 
  function onRemove(id){
      if (confirm("<bean:message key="modellist.title.warnRemove" />")==true){
        document.tawRmCycleTableForm.id.value=id;
        document.tawRmCycleTableForm.action="remove.do?id="+id;
        document.tawRmCycleTableForm.submit();
      }
  }

</script>
 
<form name="tawRmCycleTableForm">
<input type="hidden" name="id">

<table class="listTable" id="list-table">
  <caption>${eoms:a2u('周期附加表管理')}</caption>
  
 
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
	TawRmCycleTable tawRmCycleTable = null;
	List list = (List) request.getAttribute("AddonsList");
	for (Iterator it = list.iterator(); it.hasNext();) {
		tawRmCycleTable = (TawRmCycleTable) it.next();
 
%>
<input type="hidden" name="roomId" value='<%=tawRmCycleTable.getRoomId()%>' />
<tbody>
	<td>
		<%=tawRmCycleTable.getName()%>
	</td>
	<td>
		<%=tawRmCycleTable.getCreatUser()%>
	</td>
	<td>
		<%=tawRmCycleTable.getCreatTime()%>
	</td>
	<td>
		<%=tawRmCycleTable.getRoomName()%>
	</td>
	<td>
		<%=tawRmCycleTable.getRemark()%>
	</td>

	<td>
		<a href='<%=request.getContextPath()%>/duty/manager/ntko/showDetailother.jsp?path=<%=tawRmCycleTable.getUrl()%>' > <img
				src="${app }/duty/images/an_xs.gif"> </a>
	</td>
	<td>
		<a href="view.do?id=<%=tawRmCycleTable.getId()%>"> <img
				src="${app }/duty/images/new.gif"> </a>
	</td>
	<td>
		<a href="javascript:onRemove('<%=tawRmCycleTable.getId()%>')"> <img
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


