<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.boco.eoms.duty.model.TawRmReplace"%>
<jsp:directive.page import="com.boco.eoms.duty.util.DutyConstacts"/>
<% 	String roomId = (String)request.getAttribute("roomId");
%>
<script language="javascript">
  Ext.onReady(function(){
	colorRows('list-table');
  })
  
  var trueIcon = new Image();
  trueIcon.src = "../images/true.gif";
  var falseIcon = new Image();
  falseIcon.src = "../images/false.gif";
 
 
 
   function onPass(){
   var objs = document.forms[0].id;
   var str  = "";
   		if(objs.length>0){	 
		for(var i=0; i<objs.length; i++) {
    		if(objs[i].checked == true )
      		str = str+","+objs[i].value;
		}
   		}else{
   		alert('buxing');
   		}
   }
   function submitForm(flag){
	 document.getElementById('flag').value=flag;
	 document.forms[0].submit();  
  }
</script>
 
<html:form styleId="tawRmReplaceForm" method="post" action="/tawRmReplace.do?method=xAudit">
<input type = "hidden" name="roomId" value="<%=roomId%>">
<input type="hidden" name="flag" id="flag"/>
<table class="listTable" id="list-table">
  <caption>${eoms:a2u('审核列表')}</caption>
  
 
  <thead>
  <tr>
   <td class="checkColumn header">
    	<input type="checkbox" onclick="eoms.util.checkAll(this,'id')"/>
	  </td>
   <td class="label"><bean:message key="tawRmReplaceForm.workserial" /></td>
	<td class="label"><bean:message key="tawRmReplaceForm.hander" /></td>
	<td class="label"><bean:message key="tawRmReplaceForm.inputdate" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.receiver" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.dutydate" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.roomId" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.reason" /></td>
     <td class="label"><bean:message key="tawRmReplaceForm.flag" /></td>
     <td class="label">${eoms:a2u('备注')}</td>
    <%--<td><bean:message key="modellist.title.formDetail" /></td>
  --%>
  </tr>
  </thead>
  <tbody>
<%
	TawRmReplace tawRmReplace = null;
	List list = (List) request.getAttribute("tawRmReplaceList");
	for (Iterator it = list.iterator(); it.hasNext();) {
		tawRmReplace = (TawRmReplace) it.next();
		String flag = tawRmReplace.getFlag();
 	 
%>
<input type="hidden" name="roomId" value='<%=tawRmReplace.getRoomId()%>' />
 
<tbody>
	<td>
		<input type='checkbox' name="id" value='<%=tawRmReplace.getId() %>'/>
	</td>
	
	<td>
		<%=tawRmReplace.getWorkserial()%>
	</td>
	<td>
		<%=tawRmReplace.getHanderName()%>
	</td>
	<td>
		<%=tawRmReplace.getInputdate()%>
	</td>
	<td>
		<%=tawRmReplace.getReceiverName()%>
	</td>
	<td>
		<%=tawRmReplace.getDutydate()%>
	</td>
 	<td>
		<%=tawRmReplace.getRoomName()%>
	</td>
	<td>
		<%=tawRmReplace.getReason()%>
	</td>
	<td>
					<%if (tawRmReplace.getFlag().equals("0")){%>
				${eoms:a2u('管理员审核中')}
				<%}if(tawRmReplace.getFlag().equals("1")){%>
				${eoms:a2u('管理员审核通过')}
				<%}if (tawRmReplace.getFlag().equals("2")){%>
				${eoms:a2u('管理员审核驳回')}
				<%} %>
					 
					
	</td>
	<td>
		<%=tawRmReplace.getRemark()%>
	</td>
</tbody>
<%
}
%>
</table>
<br>
<input type="button" class="button" onclick="javascript:submitForm('<%=DutyConstacts.DUTY_AUDIT_PASS%>');" value="<bean:message key="tawRmReplaceForm.pass" />"/>
<input type="button" class="button" onclick="javascript:submitForm('<%=DutyConstacts.DUTY_AUDIT_NO_PASS%>');" value="<bean:message key="tawRmReplaceForm.false" />"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>


